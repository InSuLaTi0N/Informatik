package pizzaservice.order;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.salespointframework.catalog.Catalog;
import org.salespointframework.catalog.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pizzaservice.AbstractIntegrationTests;
import pizzaservice.catalog.Category;
import pizzaservice.customer.Customer;
import pizzaservice.customer.CustomerManagement;
import pizzaservice.customer.CustomerRegistrationForm;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.salespointframework.core.Currencies.EURO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class OrderControllerTest extends AbstractIntegrationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	CustomerManagement customerManagement;

	@Autowired
	OrderManager orderManager;

	@Autowired
	Catalog catalog;

	OrderController orderController;
	Customer customer;
	MyOrder myOrder;

	Product ingredient;
	Product salad;
	Product drink;

	@BeforeEach
	public void setUp() {
		customer = customerManagement.addCustomer(new CustomerRegistrationForm
				("Andrej", "Bereza", "Unter den Linden", "Berlin", "101145", "5", "999999"), 123456);

		myOrder = new MyOrder(customer.getUserAccount(), "test address");
		myOrder.setCustomerToken(customer.tanValidation(123456));
		orderManager.save(myOrder);

		ingredient = new Product("Käse", Money.of(1.35, EURO));
		ingredient.addCategory(new Category().ingredient);

		salad = new Product("Vegetarischer Salat", Money.of(15, EURO));
		salad.addCategory(new Category().salad);

		drink = new Product("Der Drink", Money.of(1.99, EURO));
		drink.addCategory(new Category().drink);

		catalog.save(ingredient);
		catalog.save(salad);
		catalog.save(drink);

		orderController = new OrderController(orderManager, customerManagement, catalog);
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderLandingTest() throws Exception {
		mvc.perform(get("/order"))
				.andExpect(status().isOk())
				.andExpect(view().name("order"))
				.andExpect(model().attributeExists("orders", "timeFormatter"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderNewTest() throws Exception {
		mvc.perform(get("/order/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderNew"))
				.andExpect(model().attributeExists("authenticationForm"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderAuthenticateSuccessfulTest() throws Exception {
		mvc.perform(post("/order/new/authenticate")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("tan", "123456")
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderAddress"))
				.andExpect(model().attributeExists("telephoneNumber", "customerToken"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderAuthenticateWrongTelephoneNumberTest() throws Exception {
		mvc.perform(post("/order/new/authenticate")
				.param("telephoneNumber", "978532213")
				.param("tan", "123456")
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/order/new"))
				.andExpect(flash().attributeExists("authenticationForm"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderAuthenticateWrongTanTest() throws Exception {
		mvc.perform(post("/order/new/authenticate")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("tan", "654321")
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/order/new"))
				.andExpect(flash().attributeExists("authenticationForm"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderItemsDefaultAddressTest() throws Exception {
		mvc.perform(post("/order/new/items")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("customerToken", Long.toString(customer.tanValidation(123456)))
				.param("address", "defaultAddress")
				.param("otherAddress", "")
				.param("street", "")
				.param("houseNumber", "1")
				.param("city", "")
				.param("plz", "")
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderItems"))
				.andExpect(model().attributeExists("currentPizza", "ingredients", "salads", "drinks", "order", "telephoneNumber"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderItemsPickUpTest() throws Exception {
		mvc.perform(post("/order/new/items")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("customerToken", Long.toString(customer.tanValidation(123456)))
				.param("address", "pickUp")
				.param("otherAddress", "gets ignored")
				.param("street", "")
				.param("houseNumber", "1")
				.param("city", "")
				.param("plz", "")
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderItems"))
				.andExpect(model().attributeExists("currentPizza", "ingredients", "salads", "drinks", "order", "telephoneNumber"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderAddIngredientTest() throws Exception {
		mvc.perform(post("/order/new/items/addIngredient")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("productId", ingredient.getId().toString())
				.param("orderId", myOrder.getId().toString())
				.param("amount", Integer.toString(2))
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderItems"))
				.andExpect(model().attributeExists("order", "currentPizza", "ingredients", "salads", "drinks", "telephoneNumber"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderAddPizzaTest() throws Exception {
		mvc.perform(post("/order/new/items/addPizza")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderItems"))
				.andExpect(model().attributeExists("order", "currentPizza", "ingredients", "salads", "drinks", "telephoneNumber"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderAddTest() throws Exception {
		orderAddPizzaTest();
		mvc.perform(post("/order/new/items/add")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("productId", salad.getId().toString())
				.param("orderId", myOrder.getId().toString())
				.param("amount", Integer.toString(2))
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderItems"))
				.andExpect(model().attributeExists("order", "currentPizza", "ingredients", "salads", "drinks", "telephoneNumber"));
	}


	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderAddDiningSetTest() throws Exception {
		mvc.perform(post("/order/new/items/addPizza")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderItems"))
				.andExpect(model().attributeExists("order", "currentPizza", "ingredients", "salads", "drinks", "telephoneNumber"));
		mvc.perform(post("/order/new/items/addDiningSet")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("orderId", myOrder.getId().toString())
				.param("amount", Integer.toString(2))
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderItems"))
				.andExpect(model().attributeExists("order", "currentPizza", "ingredients", "salads", "drinks", "telephoneNumber"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderFinishErrorTest() throws Exception {
		myOrder.setCustomerToken(3123112);
		mvc.perform(post("/order/new/items/done")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("error"))
				.andExpect(model().attributeExists("error"));
	}


	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderFinishFreeDrinkTest() throws Exception {
		orderManager.addProduct(myOrder.getId().toString(), salad, 100);
		mvc.perform(post("/order/new/items/done")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderFreeDrink"))
				.andExpect(model().attributeExists("order", "drinks", "telephoneNumber", "newTan"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderFinishNoFreeDrinkTest() throws Exception {
		mvc.perform(post("/order/new/items/done")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderComplete"))
				.andExpect(model().attributeExists("order", "date", "time", "customer", "deliveryTime", "newTan"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void orderFreeDrinkTest() throws Exception {
		mvc.perform(post("/order/new/items/freeDrink/done")
				.param("productId", drink.getId().toString())
				.param("orderId", myOrder.getId().toString())
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("newTan", Integer.toString(customer.tokenValidation(myOrder.getCustomerToken())))
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderComplete"))
				.andExpect(model().attributeExists("order", "date", "time", "customer", "deliveryTime", "newTan"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void statePizzaTest() throws Exception {
		mvc.perform(get("/order/view/inPreparation"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderPizza"))
				.andExpect(model().attributeExists("timeFormatter", "unpreparedPizzas"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void preparePizzaTest() throws Exception {
		mvc.perform(post("/order/new/items/addPizza")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderItems"))
				.andExpect(model().attributeExists("order", "currentPizza", "ingredients", "salads", "drinks", "telephoneNumber"));
		mvc.perform(post("/order/view/inPreparation/submit")
				.param("orderId", myOrder.getId().toString())
				.param("orderLineInt", Integer.toString(0))
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/order/view/inPreparation"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void stateDoneTest() throws Exception {
		mvc.perform(get("/order/view/done"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderDone"))
				.andExpect(model().attributeExists("timeFormatter", "orders"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void deliverTest() throws Exception {
		mvc.perform(post("/order/new/items/addPizza")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderItems"))
				.andExpect(model().attributeExists("order", "currentPizza", "ingredients", "salads", "drinks", "telephoneNumber"));
		mvc.perform(post("/order/view/done/submit")
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/order/view/done"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void statePickUpTest() throws Exception {
		mvc.perform(get("/order/view/pickUp"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderPickUp"))
				.andExpect(model().attributeExists("timeFormatter", "orders"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void pickUpTest() throws Exception {
		mvc.perform(post("/order/new/items/addPizza")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderItems"))
				.andExpect(model().attributeExists("order", "currentPizza", "ingredients", "salads", "drinks", "telephoneNumber"));
		mvc.perform(post("/order/view/pickUp/submit")
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/order/view/pickUp"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void stateInDeliveryTest() throws Exception {
		mvc.perform(get("/order/view/inDelivery"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderInDelivery"))
				.andExpect(model().attributeExists("timeFormatter", "orders"));
	}

	@Test
	@WithMockUser(roles = "EMPLOYEE")
	public void completeTest() throws Exception {
		mvc.perform(post("/order/new/items/addPizza")
				.param("telephoneNumber", customer.getTelephoneNumber())
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("orderItems"))
				.andExpect(model().attributeExists("order", "currentPizza", "ingredients", "salads", "drinks", "telephoneNumber"));
		mvc.perform(post("/order/view/inDelivery/submit")
				.param("orderId", myOrder.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/order/view/inDelivery"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void ovenTest() throws Exception {
		mvc.perform(get("/oven"))
				.andExpect(status().isOk())
				.andExpect(view().name("oven"))
				.andExpect(model().attributeExists("orderManager"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void setOvenTest() throws Exception {
		mvc.perform(post("/oven/done")
				.param("amount", Integer.toString(5))
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/branch"));
	}

	@Test
	public void getProductTest() {
		Product foundSalad = orderController.getProduct(salad.getId().toString());
		assertThat(foundSalad.getId().toString().equals(salad.getId().toString()));
	}

	@Test
	public void getProductExceptionTest() {
		String id = "asfsdg";
		Assertions.assertThrows(NoSuchElementException.class, () -> orderController.getProduct(id));
	}
}
