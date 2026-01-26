package pizzaservice.order;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.order.OrderLine;
import org.salespointframework.order.OrderManagement;
import org.salespointframework.order.OrderStatus;
import org.salespointframework.quantity.Quantity;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pizzaservice.Configuration;
import pizzaservice.catalog.Pizza;
import pizzaservice.customer.Customer;

import javax.money.Monetary;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.salespointframework.core.Currencies.EURO;

/**
 * A class that is used to manage {@link MyOrder} objects. It also contains the logic responsible for the oven functionality.
 *
 * @see <a href="https://st.inf.tu-dresden.de/SalesPoint/api//org/salespointframework/order/OrderManagement.html">OrderManagement</a>
 * @see OrderScheduler
 */
@Service
public class OrderManager {

	private OrderManagement<MyOrder> orderManagement;
	private int ovenCount;
	private LocalDateTime nextBakingTime;

	OrderManager(OrderManagement<MyOrder> orderManagement) {
		Assert.notNull(orderManagement, "OrderManagement must not be null!");

		this.orderManagement = orderManagement;
		this.ovenCount = 2;
		this.nextBakingTime = LocalDateTime.now();
	}

	public int getOvenCount() {
		return ovenCount;
	}

	public void setOvenCount(int newOvenCount) {
		Assert.isTrue(newOvenCount > 0, "NewOvenCount must be greater than 0!");

		ovenCount = newOvenCount;
	}

	public void save(MyOrder myOrder){
		Assert.notNull(myOrder, "Order must not be null!");
		orderManagement.save(myOrder);
	}

	public void delete(MyOrder myOrder){
		Assert.notNull(myOrder, "Order must not be null!");
		orderManagement.delete(myOrder);
	}

	/**
	 * Gets the {@link MyOrder} associated with the given orderId.
	 *
	 * <br>
	 * If a {@link MyOrder} belongs to the given orderId, then it has to be in the {@link OrderStatus} OPEN to be returned.
	 *
	 * @param orderId a String, not null, not empty
	 * @return a {@link MyOrder}, not null
	 * @throws NoSuchElementException
	 */
	public MyOrder getOpenOrder(String orderId){
		Assert.hasText(orderId, "OrderId must have text!");

		for (MyOrder o : orderManagement.findBy(OrderStatus.OPEN)){
			if(o.getId().toString().equals(orderId)){
				return o;
			}
		}
		throw new NoSuchElementException("No open Order with the given OrderId was found!");
	}

	/**
	 * Gets an Iterable of {@link MyOrder} objects according to the given {@link OrderLineState}.
	 *
	 * <br>
	 * All {@link OrderLine} objects must have the given {@link OrderLineState} to be added.
	 * @param state an {@link OrderLineState}, not null
	 * @return an Iterable of {@link MyOrder} objects, can be empty
	 */
	public Iterable<MyOrder> getOrders(OrderLineState state) {
		Assert.notNull(state, "State must not be null!");

		Streamable<MyOrder> originalOrders = orderManagement.findBy(OrderStatus.OPEN);
		List<MyOrder> myOrders = new ArrayList<>();
		originalOrders.forEach(myOrder -> {
			boolean isState = true;
			for(OrderLine ol : myOrder.getOrderLineStateMap().keySet()){
				if(!myOrder.checkOrderLineState(ol, state)){
					isState = false;
					break;
				}
			}
			if (isState) {
				myOrders.add(myOrder);
			}
		});
		return myOrders;
	}

	private Iterable<MyOrder> getOrdersForBackend(OrderLineState state){
		Assert.notNull(state, "State must not be null!");

		Streamable<MyOrder> originalOrders = orderManagement.findBy(OrderStatus.OPEN);
		List<MyOrder> myOrders = new ArrayList<>();
		originalOrders.forEach(myOrder -> {
			for(OrderLine ol : myOrder.getOrderLineStateMap().keySet()){
				if (myOrder.checkOrderLineState(ol, state)){
					myOrders.add(myOrder);
					break;
				}
			}
		});
		return myOrders;
	}

	/**
	 * Gets an Iterable of {@link MyOrder} objects according to the given {@link OrderStatus}.<br>
	 * @param orderStatus an {@link OrderStatus}, not null
	 * @return an Iterable of {@link MyOrder} objects, can be empty
	 */
	public Streamable<MyOrder> getOrders(OrderStatus orderStatus) {
		Assert.notNull(orderStatus, "OrderStatus must not be null!");
		return orderManagement.findBy(orderStatus);
	}

	/**
	 * Gets a Map of all {@link MyOrder} objects that have at least one {@link OrderLine} in the {@link OrderLineState} IN_PREPARATION.
	 *
	 * @return a Map, where the keys are {@link MyOrder} objects and the values are Lists of {@link OrderLine} objects, can be empty
	 */
	public Map<MyOrder, List<OrderLine>> getUnpreparedPizzas() {
		Iterable<MyOrder> ordersInPreparation = getOrdersForBackend(OrderLineState.IN_PREPARATION);
		Map<MyOrder, List<OrderLine>> unpreparedPizzas = new LinkedHashMap<>();
		for (MyOrder o : ordersInPreparation) {
			for (OrderLine ol : o.getUnpreparedPizzas()) {
				if (unpreparedPizzas.get(o) == null) {
					List<OrderLine> newList = new ArrayList<>();
					newList.add(ol);
					unpreparedPizzas.put(o, newList);
				} else {
					List<OrderLine> current = unpreparedPizzas.get(o);
					current.add(ol);
					unpreparedPizzas.put(o, current);
				}
			}
		}
		return unpreparedPizzas;
	}

	/**
	 * Sets the {@link OrderLineState} of the selected {@link OrderLine}
	 * in the {@link MyOrder} associated with the given orderId to PREPARED.
	 *
	 * @param orderId a String, not null, not empty
	 * @param orderLineInt an integer, not negative, must be associated with an {@link OrderLine}
	 */
	public void preparePizza(String orderId, int orderLineInt) {
		Assert.hasText(orderId, "OrderId must have text!");

		Map<MyOrder, List<OrderLine>> unpreparedPizzas = getUnpreparedPizzas();
		for (MyOrder o : unpreparedPizzas.keySet()) {
			if (o.getId().toString().equals(orderId)) {
				OrderLine pizza = o.getOrderLine(orderLineInt);
				o.setOrderLineState(pizza, OrderLineState.PREPARED);
				save(o);
				break;
			}
		}
	}

	private void preparedToBaking() {
		int bakingCount = 0;
		Iterable<MyOrder> ordersPrepared = getOrdersForBackend(OrderLineState.PREPARED);
		for (MyOrder o : ordersPrepared) {
			for (OrderLine ol : o.getOrderLineStateMap().keySet()) {
				if (o.checkOrderLineState(ol, OrderLineState.PREPARED) && bakingCount < ovenCount) {
					o.setOrderLineState(ol, OrderLineState.BAKING);
					save(o);
					bakingCount++;
				}
			}
		}
	}

	private void bakingToDone() {
		Iterable<MyOrder> ordersBaking = getOrdersForBackend(OrderLineState.BAKING);
		for (MyOrder o : ordersBaking) {
			for (OrderLine ol : o.getOrderLineStateMap().keySet()) {
				if (o.checkOrderLineState(ol, OrderLineState.BAKING)) {
					o.setOrderLineState(ol, OrderLineState.DONE);
					save(o);
				}
			}
		}
	}

	/**
	 * Simulates the oven functionality.
	 *
	 * <br>
	 * This method is automatically called every five minutes and
	 * should not be called manually to uphold the desired time between function calls.
	 *
	 * <br><br>
	 * In its first step it sets the {@link OrderLineState} of all {@link OrderLine} objects that are in BAKING to DONE.<br>
	 * In its second step it sets the {@link OrderLineState} of all {@link OrderLine} objects that are in PREPARED to BAKING.
	 */
	public void oven() {
		nextBakingTime = LocalDateTime.now().plusSeconds(new Configuration().ovenDelay / 1000);

		for(MyOrder o : getOrders(OrderStatus.OPEN)){
			if(o.canBeDeleted() && Duration.between(o.getDateCreated(), LocalDateTime.now()).toMillis()
					> new Configuration().deletionTime){
				delete(o);
			}
		}

		bakingToDone();
		preparedToBaking();
	}

	public LocalDateTime getNextBakingTime(){
		return nextBakingTime;
	}

	/**
	 * Delivers the {@link MyOrder} associated with the given orderId.
	 *
	 * <br>
	 * Delivering a {@link MyOrder} means that the {@link OrderLineState}
	 * of all {@link OrderLine} objects in the {@link MyOrder} will be switched to IN_DELIVERY.
	 *
	 * @param orderId a String, not null, not empty
	 * @throws NoSuchElementException
	 */
	public void deliverOrder(String orderId) {
		Assert.hasText(orderId, "OrderId must have text!");

		MyOrder myOrder = getOpenOrder(orderId);

		for(OrderLine ol : myOrder.getOrderLineStateMap().keySet()){
			myOrder.setOrderLineState(ol, OrderLineState.IN_DELIVERY);
		}

		save(myOrder);
	}

	/**
	 * Completes the {@link MyOrder} associated with the given orderId.
	 *
	 * <br>
	 * Completing a {@link MyOrder} means that the {@link OrderLineState}
	 * of all {@link OrderLine} objects in the {@link MyOrder} will be switched to DELIVERED.
	 *
	 * @param orderId a String, not null, not empty
	 * @throws NoSuchElementException
	 */
	public void completeOrder(String orderId) {
		Assert.hasText(orderId, "OrderId must have text!");

		MyOrder myOrder = getOpenOrder(orderId);
		orderManagement.payOrder(myOrder);

		for(OrderLine ol : myOrder.getOrderLineStateMap().keySet()){
			myOrder.setOrderLineState(ol, OrderLineState.DELIVERED);
		}

		save(myOrder);
		orderManagement.completeOrder(myOrder);
	}

	/**
	 * Adds the given {@link Product} with given amount to the {@link MyOrder} associated with the given orderId.
	 *
	 * @param orderId a String, not null, not empty
	 * @param product a {@link Product}, not null, not a {@link Pizza}
	 * @param amount an integer, greater than 0
	 * @return a {@link MyOrder}, not null
	 * @throws NoSuchElementException
	 */
	public MyOrder addProduct(String orderId, Product product, int amount){
		Assert.hasText(orderId, "OrderId must have text!");
		Assert.notNull(product, "Product must not be null!");
		Assert.isTrue(product.getCategories().stream().count() == 1, "Product must only have one category!");
		Assert.isTrue(!product.getClass().equals(Pizza.class), "Product must not be a Pizza!");
		Assert.isTrue(amount > 0, "Amount must be greater than 0!");

		MyOrder myOrder = getOpenOrder(orderId);
		myOrder.addOrderLine(product, Quantity.of(amount));
		save(myOrder);
		return myOrder;
	}

	/**
	 * Adds one {@link Pizza} to the {@link MyOrder} associated with the given orderId.
	 *
	 * @param orderId a String, not null, not empty
	 * @param pizza not null
	 * @return a {@link MyOrder}, not null
	 * @throws NoSuchElementException
	 */
	public MyOrder addPizza(String orderId, Pizza pizza){
		Assert.hasText(orderId, "OrderId must have text!");
		Assert.notNull(pizza, "Pizza must not be null!");

		MyOrder myOrder = getOpenOrder(orderId);

		pizza.setName(pizza.getName() + "\n" + "Zutaten: " + "\n" + pizza.getIngredientString() +
				"Status: " + OrderLineState.IN_PREPARATION.name() + "\n\n");
		OrderLine orderLine = myOrder.addOrderLine(pizza, Quantity.of(1));
		myOrder.setContainsPizza(true);
		myOrder.getOrderLineStateMap().put(orderLine, OrderLineState.IN_PREPARATION);
		myOrder.getOrderLineStringMap().put(orderLine, pizza.getName());

		save(myOrder);
		return myOrder;
	}

	/**
	 * Adds a dining set to the {@link MyOrder} associated with the given orderId.
	 *
	 * @param orderId a String, not null, not empty, {@link MyOrder} must contain a {@link Pizza}
	 * @param amount an integer, greater than 0
	 * @return a {@link MyOrder}, not null
	 * @throws NoSuchElementException
	 */
	public MyOrder addDiningSet(String orderId, int amount){
		Assert.hasText(orderId, "OrderId must have text!");
		Assert.isTrue(amount > 0, "Amount must be greater than 0!");

		MyOrder myOrder = getOpenOrder(orderId);

		Assert.isTrue(myOrder.containsPizza(), "Order must contain a Pizza!");

		for(int i = 0; i < amount; i++){
			myOrder.addChargeLine(Money.of(15, EURO), "Essgarnitur");
		}
		save(myOrder);
		return myOrder;
	}

	/**
	 * Creates a {@link MyOrder} with a negative total for the given {@link Customer}
	 * with the given amount of dining sets, that the customer is returning.
	 *
	 * @param customer a {@link Customer}, not null
	 * @param amount an integer, greater than 0
	 */
	public void returnDiningSets(Customer customer, int amount){
		Assert.notNull(customer, "Customer must not be null!");
		Assert.isTrue(amount > 0, "Amount must be greater than 0!");

		MyOrder myOrder = new MyOrder(customer.getUserAccount(), customer.getAddress());
		for(int i = 0; i < amount; i++){
			myOrder.addChargeLine(Money.of(15, EURO).negate(), "Rückgabe Essgarnitur");
		}

		orderManagement.payOrder(myOrder);
		orderManagement.completeOrder(myOrder);
	}

	/**
	 * Adds a 10% discount to the {@link MyOrder} associated with the given orderId if it is for pick up.
	 *
	 * @param orderId a String, not null, not empty
	 * @throws NoSuchElementException
	 */
	public void discount(String orderId){
		Assert.hasText(orderId, "OrderId must have text!");

		MyOrder myOrder = getOpenOrder(orderId);
		if(myOrder.isPickUp()){

			myOrder.addChargeLine(myOrder.getOrderLines().getTotal().negate().divide(10).with(Monetary.getDefaultRounding()),
					"Rabatt für Selbstabholung");
			save(myOrder);
		}
	}

	/**
	 * Checks if the {@link MyOrder} associated with the given orderId is eligible for a free drink.
	 *
	 * @param orderId a String, not null, not empty
	 * @return a boolean:<br>
	 * true if the price of all {@link OrderLine} objects in the {@link MyOrder} is greater than 30 EURO<br>
	 * false otherwise
	 * @throws NoSuchElementException
	 */
	public boolean freeDrink(String orderId){
		Assert.hasText(orderId, "OrderId must have text!");

		MyOrder myOrder = getOpenOrder(orderId);
		if(myOrder.getOrderLines().getTotal().isGreaterThan(Money.of(30, EURO))){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the estimated point in time when the {@link MyOrder} associated with the given orderId will go out for delivery.
	 *
	 * @param orderId a String, not null, not empty
	 * @return a {@link LocalDateTime}
	 * @throws NoSuchElementException
	 */
	public LocalDateTime getDeliveryTime(String orderId){
		Assert.hasText(orderId, "OrderId must have text!");

		// these numbers are subject to further adjustments
		int preparationTime = 5; // minutes
		int deliveryTime = 15; // minutes

		int unprepared = 0;
		int prepared = 0;

		for(MyOrder o : getOrders(OrderStatus.OPEN)){
			for(OrderLine ol : o.getOrderLineStateMap().keySet()){
				if (o.checkOrderLineState(ol, OrderLineState.IN_PREPARATION)){
					unprepared += 1;
				}
				if (o.checkOrderLineState(ol, OrderLineState.PREPARED)){
					prepared += 1;
				}
			}
		}

		int bakingTime = (unprepared + (unprepared+prepared)/ovenCount) * 5; // minutes

		// round up to next quarter hour
		LocalDateTime estimatedTime = LocalDateTime.now().plusMinutes(preparationTime + (long) bakingTime + deliveryTime);
		int oldMinute = estimatedTime.getMinute();
		int newMinute = 15 * (int) Math.floor(oldMinute / 15.0);
		return estimatedTime.plusMinutes((long) newMinute - oldMinute + 15).truncatedTo(MINUTES);
	}

	/**
	 * Deletes all {@link MyOrder} objects in the {@link OrderStatus} OPEN with a total of 0 EURO.
	 */
	public void deleteEmptyOrders(){
		for(MyOrder o : orderManagement.findBy(OrderStatus.OPEN)){
			if(o.getTotal().isEqualTo(Money.of(0, EURO))){
				delete(o);
			}
		}
	}
}
