package pizzaservice.order;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.salespointframework.catalog.Product;
import org.salespointframework.order.ChargeLine;
import org.salespointframework.order.OrderLine;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import pizzaservice.AbstractIntegrationTests;
import pizzaservice.catalog.Category;
import pizzaservice.catalog.Pizza;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.salespointframework.core.Currencies.EURO;

public class MyOrderTest extends AbstractIntegrationTests {

	@Spy
	UserAccount userAccount;

	@Test
	public void constructorTest(){
		MyOrder myOrder1 = new MyOrder(userAccount, "test");
		assertThat(!myOrder1.containsPizza());
		assertThat(myOrder1.getCurrentOrderLineInt() == 0);

		MyOrder myOrder2 = new MyOrder();
		assertNotNull(myOrder2);
	}

	@Test
	public void addressTest(){
		MyOrder myOrder = new MyOrder(userAccount, "test");
		assertThat(myOrder.getAddress().equals("test"));

		myOrder.setAddress("Other test");
		assertThat(myOrder.getAddress().equals("Other test"));
	}

	@Test
	public void customerTokenTest(){
		MyOrder myOrder = new MyOrder(userAccount, "test");
		assertThat(myOrder.getCustomerToken() == 0);

		long newCustomerToken = 123456;
		myOrder.setCustomerToken(newCustomerToken);
		assertThat(myOrder.getCustomerToken() == newCustomerToken);
	}


	@Test
	public void containsPizzaTest(){
		MyOrder myOrder = new MyOrder(userAccount, "test");
		assertThat(!myOrder.containsPizza());

		Pizza pizza = new Pizza();
		myOrder.addOrderLine(pizza, Quantity.of(1));
		myOrder.setContainsPizza(true);
		assertThat(myOrder.containsPizza());
	}

	@Test
	public void pickUpTest(){
		MyOrder myOrder = new MyOrder(userAccount, "test");
		assertThat(!myOrder.isPickUp());

		myOrder.setAddress("Selbstabholung");
		assertThat(myOrder.isPickUp());
	}

	@Test
	public void customerNameTest(){
		UserAccount testUserAccount = userAccount.setFirstname("Andrej").setLastname("Bereza");

		String expectedString = "Andrej Bereza";
		MyOrder myOrder = new MyOrder(testUserAccount, "test");

		assertThat(myOrder.getCustomerName().equals(expectedString));
	}

	@Test
	public void mapTest(){
		MyOrder myOrder = new MyOrder(userAccount, "test");

		Product product = new Product("Salat", Money.of(10, EURO));
		product.addCategory(new Category().salad);

		int amount = 3;

		OrderLine orderLine = myOrder.addOrderLine(product, Quantity.of(amount));
		ChargeLine chargeLine = myOrder.addChargeLine(Money.of(15, EURO),"Essgarnitur");

		Map<OrderLine, OrderLineState> expectedOrderLineStateMap = new HashMap<>();
		expectedOrderLineStateMap.put(orderLine, OrderLineState.DONE);

		Map<OrderLine, String> expectedOrderLineStringMap = new HashMap<>();
		expectedOrderLineStringMap.put(orderLine, product.getName() +
				", Anzahl: " + amount + "\n" + "Status: " + OrderLineState.DONE.name() + "\n\n");

		Map<ChargeLine, String> expectedChargeLineStringMap = new HashMap<>();
		expectedChargeLineStringMap.put(chargeLine, chargeLine.getDescription() + "\n\n");

		assertThat(myOrder.getOrderLineStateMap().equals(expectedOrderLineStateMap));
		assertThat(myOrder.getOrderLineStringMap().equals(expectedOrderLineStringMap));
		assertThat(myOrder.getChargeLineStringMap().equals(expectedChargeLineStringMap));
	}

	@Test
	public void orderStringTest(){
		MyOrder myOrder = new MyOrder(userAccount, "test");

		Product product = new Product("Salat", Money.of(10, EURO));
		product.addCategory(new Category().salad);

		int amount = 3;

		myOrder.addOrderLine(product, Quantity.of(amount));
		ChargeLine chargeLine = myOrder.addChargeLine(Money.of(15, EURO),"Essgarnitur");

		String expectedOrderString = product.getName() +
				", Anzahl: " + amount + "\n" + "Status: " + OrderLineState.DONE.name() + "\n\n" +
				chargeLine.getDescription() + "\n\n";

		assertThat(myOrder.getOrderString().equals(expectedOrderString));
	}

	@Test
	public void orderLineIntMapTest() {
		MyOrder myOrder = new MyOrder(userAccount, "test");

		Product product = new Product("Salat", Money.of(10, EURO));
		product.addCategory(new Category().salad);

		int amount = 3;

		OrderLine orderLine = myOrder.addOrderLine(product, Quantity.of(amount));

		assertThat(myOrder.getOrderLineInt(orderLine) == 0);
		assertThat(myOrder.getOrderLine(0).equals(orderLine));
	}
}