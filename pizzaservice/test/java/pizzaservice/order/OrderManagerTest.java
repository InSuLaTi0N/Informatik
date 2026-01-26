package pizzaservice.order;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.salespointframework.catalog.Product;
import org.salespointframework.order.OrderLine;
import org.salespointframework.order.OrderStatus;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import pizzaservice.AbstractIntegrationTests;
import pizzaservice.catalog.Category;
import pizzaservice.catalog.Pizza;
import pizzaservice.customer.Customer;
import pizzaservice.customer.CustomerManagement;

import javax.money.Monetary;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.salespointframework.core.Currencies.EURO;

public class OrderManagerTest extends AbstractIntegrationTests {

	@Autowired
	OrderManager orderManager;

	@Autowired
	CustomerManagement customerManagement;

	@Test
	public void ovenCountTest(){
		assertThat(orderManager.getOvenCount() == 2);
		orderManager.setOvenCount(4);
		assertThat(orderManager.getOvenCount() == 4);
	}

	@Test
	public void repositoryTest(){
		UserAccount userAccount = customerManagement.findOne("1234").getUserAccount();
		MyOrder myOrder = new MyOrder(userAccount, "test");
		orderManager.save(myOrder);

		assertThat(orderManager.getOpenOrder(myOrder.getId().toString()).equals(myOrder));

		Exception exception1 = assertThrows(NoSuchElementException.class, () -> {
			orderManager.getOpenOrder("0");
		});

		assertThat(exception1.getMessage().equals("No open Order with the given OrderId was found!"));

		orderManager.delete(myOrder);
		Exception exception2 = assertThrows(NoSuchElementException.class, () -> {
			orderManager.getOpenOrder(myOrder.getId().toString());
		});

		assertThat(exception2.getMessage().equals("No open Order with the given OrderId was found!"));
	}

	@Test
	public void orderLogicTest(){
		UserAccount userAccount = customerManagement.findOne("1234").getUserAccount();

		Product ingredient1 = new Product("Käse", Money.of(1.30, EURO));
		ingredient1.addCategory(new Category().ingredient);
		Product ingredient2 = new Product("Salami", Money.of(3.45, EURO));
		ingredient2.addCategory(new Category().ingredient);

		Product drink = new Product("Coca-Cola", Money.of(1.30, EURO));
		drink.addCategory(new Category().drink);

		Pizza pizza = new Pizza();
		pizza.addIngredient(ingredient1, 2);
		pizza.addIngredient(ingredient2, 4);

		MyOrder myOrder = new MyOrder(userAccount, "test1");
		orderManager.save(myOrder);
		assertThat(!orderManager.getOrders(OrderStatus.OPEN).isEmpty());

		myOrder = orderManager.addPizza(myOrder.getId().toString(), pizza);
		assertThat(myOrder.containsPizza());
		assertThat(myOrder.checkOrderLineState(myOrder.getOrderLine(0), OrderLineState.IN_PREPARATION));

		myOrder = orderManager.addProduct(myOrder.getId().toString(),drink, 1);
		assertThat(myOrder.getOrderLineStateMap().get(myOrder.getOrderLine(1)).equals(
				drink.getName() + ", Anzahl: " + 1 + "\n" + "Status: " + OrderLineState.DONE.name() + "\n\n"));

		myOrder = orderManager.addProduct(myOrder.getId().toString(),drink, 1);
		assertThat(myOrder.getOrderLineStateMap().get(myOrder.getOrderLine(1)).equals(
				drink.getName() + ", Anzahl: " + 2 + "\n" + "Status: " + OrderLineState.DONE.name() + "\n\n"));

		myOrder = orderManager.addDiningSet(myOrder.getId().toString(), 2);
		assertThat(myOrder.getOrderLineStateMap().size() == 1);
		assertThat(myOrder.getOrderLineStringMap().size() == 1);
		assertThat(myOrder.getChargeLineStringMap().size() == 1);

		Map<MyOrder, List<OrderLine>> unpreparedPizzas = orderManager.getUnpreparedPizzas();
		assertThat(unpreparedPizzas.size() == 1);
		assertThat(unpreparedPizzas.get(myOrder).equals(new OrderLine[]{myOrder.getOrderLine(0)}));

		orderManager.preparePizza(myOrder.getId().toString(), 0);
		assertThat(myOrder.checkOrderLineState(myOrder.getOrderLine(0), OrderLineState.PREPARED));

		unpreparedPizzas = orderManager.getUnpreparedPizzas();
		assertThat(unpreparedPizzas.size() == 0);

		orderManager.deliverOrder(myOrder.getId().toString());
		assertThat(myOrder.checkOrderLineState(myOrder.getOrderLine(0), OrderLineState.IN_DELIVERY));
		assertThat(orderManager.getOrders(OrderLineState.IN_DELIVERY).iterator().hasNext());
		assertThat(!orderManager.getOrders(OrderStatus.OPEN).isEmpty());
		assertThat(orderManager.getOrders(OrderStatus.COMPLETED).isEmpty());

		orderManager.completeOrder(myOrder.getId().toString());
		assertThat(myOrder.checkOrderLineState(myOrder.getOrderLine(0), OrderLineState.DELIVERED));
		assertThat(orderManager.getOrders(OrderLineState.DELIVERED).iterator().hasNext());
		assertThat(orderManager.getOrders(OrderStatus.OPEN).isEmpty());
		assertThat(!orderManager.getOrders(OrderStatus.COMPLETED).isEmpty());

		orderManager.delete(myOrder);
	}

	@Test
	public void discountTest(){
		UserAccount userAccount = customerManagement.findOne("1234").getUserAccount();

		Product ingredient1 = new Product("Käse", Money.of(1.30, EURO));
		ingredient1.addCategory(new Category().ingredient);
		Product ingredient2 = new Product("Salami", Money.of(3.45, EURO));
		ingredient2.addCategory(new Category().ingredient);

		Pizza pizza = new Pizza();
		pizza.addIngredient(ingredient1, 2);
		pizza.addIngredient(ingredient2, 4);
		pizza.setPrice(pizza.getTotal());

		MyOrder myOrder = new MyOrder(userAccount, "test1");
		orderManager.save(myOrder);

		myOrder = orderManager.addPizza(myOrder.getId().toString(), pizza);
		assertThat(myOrder.getTotal().equals(Money.of(4.99, EURO).
				add(ingredient1.getPrice()).add(ingredient2.getPrice())));

		orderManager.discount(myOrder.getId().toString());
		assertThat(myOrder.getTotal().equals(pizza.getTotal()));

		myOrder.setAddress("Selbstabholung");
		orderManager.save(myOrder);

		orderManager.discount(myOrder.getId().toString());
		assertThat(myOrder.getTotal().equals(pizza.getTotal().
				add(pizza.getTotal().negate().divide(10).with(Monetary.getDefaultRounding()))));

		orderManager.delete(myOrder);
	}

	@Test
	public void freeDrinkTest(){
		UserAccount userAccount = customerManagement.findOne("1234").getUserAccount();

		Product ingredient1 = new Product("Käse", Money.of(1.30, EURO));
		ingredient1.addCategory(new Category().ingredient);
		Product ingredient2 = new Product("Salami", Money.of(3.45, EURO));
		ingredient2.addCategory(new Category().ingredient);

		Pizza pizza1 = new Pizza();
		pizza1.addIngredient(ingredient1, 2);
		pizza1.addIngredient(ingredient2, 4);
		pizza1.setPrice(pizza1.getTotal());

		Pizza pizza2 = new Pizza();
		pizza2.addIngredient(ingredient1, 2);
		pizza2.addIngredient(ingredient2, 4);
		pizza2.setPrice(pizza2.getTotal());

		Pizza pizza3 = new Pizza();
		pizza3.addIngredient(ingredient1, 2);
		pizza3.addIngredient(ingredient2, 4);
		pizza3.setPrice(pizza3.getTotal());

		Pizza pizza4 = new Pizza();
		pizza4.addIngredient(ingredient1, 2);
		pizza4.addIngredient(ingredient2, 4);
		pizza4.setPrice(pizza4.getTotal());

		MyOrder myOrder = new MyOrder(userAccount, "test1");
		orderManager.save(myOrder);

		orderManager.addPizza(myOrder.getId().toString(), pizza1);

		assertThat(!orderManager.freeDrink(myOrder.getId().toString()));

		orderManager.addPizza(myOrder.getId().toString(), pizza2);
		orderManager.addPizza(myOrder.getId().toString(), pizza3);
		orderManager.addPizza(myOrder.getId().toString(), pizza4);

		assertThat(orderManager.freeDrink(myOrder.getId().toString()));

		orderManager.delete(myOrder);
	}

	@Test
	public void returnDiningSetsTest(){
		Customer customer = customerManagement.findOne("1234");
		UserAccount userAccount = customer.getUserAccount();

		MyOrder myOrder = new MyOrder(userAccount, "test1");
		orderManager.save(myOrder);

		Product ingredient1 = new Product("Käse", Money.of(1.30, EURO));
		ingredient1.addCategory(new Category().ingredient);
		Product ingredient2 = new Product("Salami", Money.of(3.45, EURO));
		ingredient2.addCategory(new Category().ingredient);

		Pizza pizza = new Pizza();
		pizza.addIngredient(ingredient1, 2);
		pizza.addIngredient(ingredient2, 4);
		pizza.setPrice(pizza.getTotal());

		orderManager.addPizza(myOrder.getId().toString(), pizza);

		myOrder = orderManager.addDiningSet(myOrder.getId().toString(), 4);
		assertThat(myOrder.getTotal().equals(pizza.getTotal().add(Money.of(15, EURO).multiply(4))));

		orderManager.returnDiningSets(customer, 2);
		assertThat(myOrder.getTotal().equals(Money.of(15, EURO).multiply(2)));

		orderManager.returnDiningSets(customer, 2);
		assertThat(myOrder.getTotal().equals(Money.of(15, EURO).multiply(0)));
	}

	@Test
	public void deliveryTimeTest(){
		UserAccount userAccount = customerManagement.findOne("1234").getUserAccount();

		Product ingredient1 = new Product("Käse", Money.of(1.30, EURO));
		ingredient1.addCategory(new Category().ingredient);
		Product ingredient2 = new Product("Salami", Money.of(3.45, EURO));
		ingredient2.addCategory(new Category().ingredient);

		Pizza pizza1 = new Pizza();
		pizza1.addIngredient(ingredient1, 2);
		pizza1.addIngredient(ingredient2, 4);
		pizza1.setPrice(pizza1.getTotal());

		Pizza pizza2 = new Pizza();
		pizza2.addIngredient(ingredient1, 2);
		pizza2.addIngredient(ingredient2, 4);
		pizza2.setPrice(pizza2.getTotal());

		Pizza pizza3 = new Pizza();
		pizza3.addIngredient(ingredient1, 2);
		pizza3.addIngredient(ingredient2, 4);
		pizza3.setPrice(pizza3.getTotal());

		Pizza pizza4 = new Pizza();
		pizza4.addIngredient(ingredient1, 2);
		pizza4.addIngredient(ingredient2, 4);
		pizza4.setPrice(pizza4.getTotal());

		MyOrder myOrder = new MyOrder(userAccount, "test1");
		orderManager.save(myOrder);

		orderManager.addPizza(myOrder.getId().toString(), pizza1);
		orderManager.addPizza(myOrder.getId().toString(), pizza2);
		orderManager.addPizza(myOrder.getId().toString(), pizza3);
		orderManager.addPizza(myOrder.getId().toString(), pizza4);

		int preparationTime = 5;
		int deliveryTime = 15;
		int bakingTime = (4 + (4+0)/orderManager.getOvenCount()) * 5;

		LocalDateTime estimatedTime = LocalDateTime.now().plusMinutes(preparationTime + (long) bakingTime + deliveryTime);
		int oldMinute = estimatedTime.getMinute();
		int newMinute = 15 * (int) Math.floor(oldMinute / 15.0);
		LocalDateTime expectedTime =  estimatedTime.plusMinutes((long) newMinute - oldMinute + 15).truncatedTo(MINUTES);

		assertThat(orderManager.getDeliveryTime(myOrder.getId().toString()).equals(expectedTime));

		orderManager.delete(myOrder);
	}

	@Test
	public void deleteEmptyOrdersTest(){
		UserAccount userAccount = customerManagement.findOne("1234").getUserAccount();

		MyOrder myEmptyOrder = new MyOrder(userAccount, "test1");

		orderManager.save(myEmptyOrder);
		assertThat(!orderManager.getOrders(OrderStatus.OPEN).isEmpty());

		orderManager.deleteEmptyOrders();
		assertThat(orderManager.getOrders(OrderStatus.OPEN).isEmpty());

		orderManager.delete(myEmptyOrder);
	}
}
