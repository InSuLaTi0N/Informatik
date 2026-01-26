package pizzaservice.order;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.order.ChargeLine;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderLine;
import org.salespointframework.payment.Cash;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.util.Assert;

import javax.money.MonetaryAmount;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.salespointframework.core.Currencies.EURO;

/**
 * An {@link Order} extension with additional functionality.
 *
 * <br><br>
 * It allows for:<br>
 * - the order address to differ from the address of the {@link UserAccount} linked to the {@link MyOrder}<br>
 * - an extra token for the associated {@link UserAccount} to be saved
 * - storage of additional information about {@link OrderLine} and {@link ChargeLine} objects<br>
 * - state management of individual {@link OrderLine} objects with an {@link OrderLineState}
 * @see <a href="https://st.inf.tu-dresden.de/SalesPoint/api/org/salespointframework/order/Order.html">Order</a>
 */
@Entity(name = "orderAlter")
public class MyOrder extends Order {

	private String address;
	private long customerToken;
	private boolean containsPizza = false;
	private boolean canBeDeleted = false;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<OrderLine, OrderLineState> orderLineStateMap;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<OrderLine, String> orderLineStringMap;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<ChargeLine, String> chargeLineStringMap;

	@ElementCollection
	private Map<OrderLine, Integer> orderLineIntMap;
	private int currentOrderLineInt = 0;

	public MyOrder(UserAccount userAccount, String address){
		super(userAccount, Cash.CASH);

		Assert.hasText(address, "Address must have text!");

		this.address = address;
		this.orderLineStateMap = new LinkedHashMap<>();
		this.orderLineStringMap = new LinkedHashMap<>();
		this.chargeLineStringMap = new LinkedHashMap<>();
		this.orderLineIntMap = new LinkedHashMap<>();
		this.customerToken = 0;
	}

	@SuppressWarnings({ "unused", "deprecation" })
	public MyOrder() {

	}

	public String getAddress(){
		return address;
	}

	public void setAddress(String address) {
		Assert.hasText(address, "Address must have text!");
		this.address = address;
	}

	public long getCustomerToken() {
		return customerToken;
	}

	public void setCustomerToken(long customerToken) {
		this.customerToken = customerToken;
	}

	public boolean containsPizza() {
		return containsPizza;
	}

	public void setContainsPizza(boolean containsPizza) {
		this.containsPizza = containsPizza;
	}

	public boolean canBeDeleted() {
		return canBeDeleted;
	}

	public void setCanBeDeleted(boolean canBeDeleted) {
		this.canBeDeleted = canBeDeleted;
	}

	/**
	 * Determines whether the {@link MyOrder} is for pick up by the customer or to be delivered.
	 *
	 * @return a boolean:<br>
	 * true if the {@link MyOrder} is for pick up<br>
	 * false otherwise
	 */
	public boolean isPickUp() {
		return address.equals("Selbstabholung");
	}

	public String getCustomerName(){
		return getUserAccount().getFirstname() + " " + getUserAccount().getLastname();
	}

	public Map<OrderLine, OrderLineState> getOrderLineStateMap(){
		return orderLineStateMap;
	}

	public Map<OrderLine, String> getOrderLineStringMap() {
		return orderLineStringMap;
	}

	public Map<ChargeLine, String> getChargeLineStringMap() {
		return chargeLineStringMap;
	}

	public int getCurrentOrderLineInt() {
		return currentOrderLineInt;
	}

	/**
	 * Gets a formatted String containing information about the
	 * {@link OrderLine} and {@link ChargeLine} objects attached to the {@link MyOrder}.
	 *
	 * @return a String, potentially empty, not null
	 */
	public String getOrderString(){
		StringBuilder builder = new StringBuilder();
		for(OrderLine ol : orderLineStringMap.keySet()){
			builder.append(orderLineStringMap.get(ol));
		}
		for(ChargeLine cl : chargeLineStringMap.keySet()){
			builder.append(chargeLineStringMap.get(cl));
		}
		return builder.toString();
	}

	/**
	 * Gets the integer associated with the given {@link OrderLine}.
	 *
	 * @param orderLine an {@link OrderLine}, not null, must be attached to the {@link MyOrder}
	 * @return an integer, not negative
	 */
	public int getOrderLineInt(OrderLine orderLine){
		Assert.notNull(orderLine, "OrderLine must not be null!");
		Assert.isTrue(orderLineIntMap.containsKey(orderLine), "OrderLine must in the OrderLineIntMap!");

		return orderLineIntMap.get(orderLine);
	}

	/**
	 * Gets the {@link OrderLine} associated with the given int.
	 *
	 * @param orderLineInt an integer, not negative, must be associated with an {@link OrderLine}
	 * @return an {@link OrderLine}, not null
	 */
	public OrderLine getOrderLine(int orderLineInt){
		Assert.isTrue(orderLineInt > -1, "OrderLineInt must not be negative!");
		Assert.isTrue(orderLineIntMap.containsValue(orderLineInt), "OrderLineInt must be in the OrderLineIntMap!");

		for (OrderLine ol : orderLineIntMap.keySet()){
			if(orderLineIntMap.get(ol) == orderLineInt){
				return ol;
			}
		}
		return null;
	}

	/**
	 * Gets a list of all {@link OrderLine} objects with the {@link OrderLineState} IN_PREPARATION.
	 *
	 * @return a list of {@link OrderLine} objects
	 */
	public List<OrderLine> getUnpreparedPizzas(){
		List<OrderLine> unpreparedPizzas = new ArrayList<>();
		for (OrderLine ol : orderLineStateMap.keySet()){
			if (checkOrderLineState(ol, OrderLineState.IN_PREPARATION)){
				unpreparedPizzas.add(ol);
			}
		}
		return unpreparedPizzas;
	}

	/**
	 * Checks whether the given {@link OrderLine} is in a certain {@link OrderLineState}.
	 *
	 * @param orderLine an {@link OrderLine}, not null, must attached to the {@link MyOrder}
	 * @param state an {@link OrderLineState}, not null
	 * @return a boolean
	 */
	public boolean checkOrderLineState(OrderLine orderLine, OrderLineState state){
		Assert.notNull(orderLine, "OrderLine must not be null!");
		Assert.isTrue(orderLineStateMap.containsKey(orderLine), "OrderLine must in the OrderLineStateMap!");
		Assert.notNull(state, "State must not be null!");

		boolean result = false;
		if (getOrderLineStateMap().get(orderLine).equals(state)){
			result = true;
		}
		return result;
	}

	/**
	 * Sets the {@link OrderLineState} for the given {@link OrderLine}.
	 *
	 * @param orderLine an {@link OrderLine}, not null, must be attached to the {@link MyOrder}
	 * @param state an {@link OrderLineState}, not null
	 */
	public void setOrderLineState(OrderLine orderLine, OrderLineState state){
		Assert.notNull(orderLine, "OrderLine must not be null!");
		Assert.isTrue(orderLineStateMap.containsKey(orderLine), "OrderLine must in the OrderLineStateMap!");
		Assert.notNull(state, "State must not be null!");

		orderLineStateMap.put(orderLine, state);
		if(orderLine.getProductName().contains("Pizza")){
			String substr = orderLine.getProductName().
					substring(0,orderLine.getProductName().indexOf("Status: ")+"Status: ".length());
			substr += state.name();
			orderLineStringMap.put(orderLine, substr + "\n\n");
		} else {
			orderLineStringMap.put(orderLine, orderLine.getProductName() +
					", Anzahl: " + orderLine.getQuantity().toString() + "\n" + "Status: " + state.name() + "\n\n");
		}
	}

	/**
	 * Overrides addOrderLine of the superclass to populate the maps in the {@link MyOrder}.
	 *
	 * @param product a {@link Product}, not null
	 * @param quantity a {@link Quantity}, not null
	 * @return an {@link OrderLine}, not null
	 *
	 * @see <a href="https://tud.link/ozhr">Order.addOrderLine</a>
	 */
	@Override
	public OrderLine addOrderLine(Product product, Quantity quantity){
		Assert.notNull(product, "Product must not be null!");
		Assert.notNull(quantity, "Quantity must not be null!");

		for(OrderLine ol : orderLineStateMap.keySet()){
			if(ol.getProductIdentifier().equals(product.getId())){
				Quantity newQuantity = ol.getQuantity().add(quantity);
				int oldOrderLineInt = orderLineIntMap.get(ol);

				orderLineStateMap.remove(ol);
				orderLineStringMap.remove(ol);
				orderLineIntMap.remove(ol);
				super.remove(ol);

				OrderLine orderLine = super.addOrderLine(product, newQuantity);
				orderLineStateMap.put(orderLine, OrderLineState.DONE);
				orderLineStringMap.put(orderLine, product.getName() +
						", Anzahl: " + newQuantity.getAmount().toString() + "\n" + "Status: " + OrderLineState.DONE.name() + "\n\n");
				orderLineIntMap.put(orderLine, oldOrderLineInt);

				return orderLine;
			}
		}

		OrderLine orderLine = super.addOrderLine(product, quantity);
		orderLineStateMap.put(orderLine, OrderLineState.DONE);
		orderLineStringMap.put(orderLine, product.getName() +
				", Anzahl: " + quantity.getAmount().toString() + "\n" + "Status: " + OrderLineState.DONE.name() + "\n\n");
		orderLineIntMap.put(orderLine, currentOrderLineInt);
		currentOrderLineInt++;

		return orderLine;
	}

	/**
	 * Overrides addChargeLine of the superclass to populate the maps in the {@link MyOrder}.
	 *
	 * @param price a {@link MonetaryAmount} in EURO, not null, not 0
	 * @param description a String, not null, not empty
	 * @return a {@link ChargeLine}, not null

	 * @see <a href="https://tud.link/6s1i">Order.addChargeLine</a>
	 */
	@Override
	public ChargeLine addChargeLine(MonetaryAmount price, String description){
		Assert.notNull(price, "Price must not be null!");
		Assert.isTrue(!price.isEqualTo(Money.of(0, EURO)), "Price must not be 0!");
		Assert.hasText(description, "Description must have text!");

		ChargeLine chargeLine = super.addChargeLine(price, description);
		chargeLineStringMap.put(chargeLine, chargeLine.getDescription() + "\n\n");
		return chargeLine;
	}
}
