package pizzaservice.order;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.core.DataInitializer;
import org.salespointframework.inventory.Inventory;
import org.salespointframework.inventory.UniqueInventory;
import org.salespointframework.order.OrderManagement;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pizzaservice.catalog.Category;
import pizzaservice.catalog.Pizza;
import pizzaservice.customer.CustomerManagement;
import pizzaservice.customer.CustomerRegistrationForm;

import static org.salespointframework.core.Currencies.EURO;

@Component
class OrderDataInitializer implements DataInitializer {

	private final OrderManager orderManager;
	private final Inventory inventory;
	private final CustomerManagement customerManagement;
	private final UserAccountManagement userAccountManagement;

	private static final Logger LOG = LoggerFactory.getLogger(OrderDataInitializer.class);

	OrderDataInitializer(OrderManagement orderManagement, UniqueInventory inventory,
						 CustomerManagement customerManagement, UserAccountManagement userAccountManagement) {
		Assert.notNull(orderManagement, "OrderManagement must not be null!");
		Assert.notNull(inventory, "Inventory must not be null!");
		Assert.notNull(customerManagement, "CustomerManagement must not be null!");
		Assert.notNull(userAccountManagement, "UserAccountManagement must not be null!");

		this.orderManager = new OrderManager(orderManagement);
		this.inventory = inventory;
		this.customerManagement = customerManagement;
		this.userAccountManagement = userAccountManagement;
	}

	/*
	 * (non-Javadoc)
	 * @see org.salespointframework.core.DataInitializer#initialize()
	 */
	@Override
	public void initialize() {

		LOG.info("Creating test orders.");

		UserAccount testUser1 = customerManagement.addCustomer(new CustomerRegistrationForm
				("Andrej", "Bereza", "ABP", "Dresden", "01187", "1", "1234"), 123456).getUserAccount();
		UserAccount testUser2 = customerManagement.addCustomer(new CustomerRegistrationForm
				("Ludwig", "Pauly",  "ABP", "Dresden", "01187", "3", "12345"), 123456).getUserAccount();
		UserAccount testUser3 = customerManagement.addCustomer(new CustomerRegistrationForm
				("Friedemann", "Weickert", "ABP", "Dresden", "01187", "3", "123456"), 123456).getUserAccount();

		MyOrder testOrder1 = new MyOrder(testUser1, "APB E001");
		MyOrder testOrder2 = new MyOrder(testUser2, "APB E002");
		MyOrder testOrder3 = new MyOrder(testUser3, "APB E003");

		Pizza testPizza1 = new Pizza();
		Pizza testPizza2 = new Pizza();
		Pizza testPizza3 = new Pizza();
		Pizza testPizza4 = new Pizza();
		Pizza testPizza5 = new Pizza();
		Pizza testPizza6 = new Pizza();
		Pizza testPizza7 = new Pizza();

		orderManager.save(testOrder1);
		orderManager.save(testOrder2);
		orderManager.save(testOrder3);

		Product ingredient1 = new Product("Ananas", Money.of(0.99, EURO));
		ingredient1.addCategory(new Category().ingredient);
		Product ingredient2 = new Product("Bacon", Money.of(1.39, EURO));
		ingredient2.addCategory(new Category().ingredient);
		Product ingredient3 = new Product("BBQ Sauce", Money.of(0.99, EURO));
		ingredient3.addCategory(new Category().ingredient);
		Product ingredient4 = new Product("Blattspinat", Money.of(0.99, EURO));
		ingredient4.addCategory(new Category().ingredient);
		Product ingredient5 = new Product("Broccoli", Money.of(0.99, EURO));
		ingredient5.addCategory(new Category().ingredient);
		Product ingredient6 = new Product("Burger Sauce", Money.of(0.99, EURO));
		ingredient6.addCategory(new Category().ingredient);
		Product ingredient7 = new Product("Champignons", Money.of(0.99, EURO));
		ingredient7.addCategory(new Category().ingredient);

		testPizza1.addIngredient(ingredient1, 2);
		testPizza1.setPrice(testPizza1.getTotal());
		testPizza2.addIngredient(ingredient2, 2);
		testPizza2.setPrice(testPizza2.getTotal());
		testPizza3.addIngredient(ingredient3, 2);
		testPizza3.setPrice(testPizza3.getTotal());
		testPizza4.addIngredient(ingredient4, 2);
		testPizza4.setPrice(testPizza4.getTotal());
		testPizza5.addIngredient(ingredient5, 2);
		testPizza5.setPrice(testPizza5.getTotal());
		testPizza6.addIngredient(ingredient6, 2);
		testPizza6.setPrice(testPizza6.getTotal());
		testPizza7.addIngredient(ingredient7, 2);
		testPizza7.setPrice(testPizza7.getTotal());

		Product testDrink1 = new Product("Sprite 500ml", Money.of(2.49, EURO));
		testDrink1.addCategory(new Category().drink);

		Product testSalad = new Product("Salat Deluxe", Money.of(8.99, EURO));
		testSalad.addCategory(new Category().salad);

		Product testDrink2 = new Product("Club Mate 500ml", Money.of(1.49, EURO));
		testDrink2.addCategory(new Category().drink);

		orderManager.addPizza(testOrder1.getId().toString(), testPizza1);
		orderManager.addPizza(testOrder1.getId().toString(), testPizza2);
		orderManager.addPizza(testOrder1.getId().toString(), testPizza3);
		orderManager.addProduct(testOrder1.getId().toString(), testDrink1, 3);

		orderManager.addPizza(testOrder2.getId().toString(), testPizza4);
		orderManager.addPizza(testOrder2.getId().toString(), testPizza5);
		orderManager.addProduct(testOrder2.getId().toString(), testSalad, 1);

		orderManager.addPizza(testOrder3.getId().toString(), testPizza6);
		orderManager.addPizza(testOrder3.getId().toString(), testPizza7);
		orderManager.addProduct(testOrder3.getId().toString(), testDrink2, 4);
	}
}

