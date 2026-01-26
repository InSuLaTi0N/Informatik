package pizzaservice.order;

import org.salespointframework.order.OrderLine;
import pizzaservice.catalog.Category;
import pizzaservice.catalog.Pizza;

/**
 * An enum that describes what state an {@link OrderLine} is in.
 *
 * <br><br>
 * In this context there are two different types of {@link OrderLine} objects:<br>
 * - those that contain a {@link Pizza} are called "Pizza"<br>
 * - those that contain a Product with the salad or drink {@link Category} are called "Salad" or "Drink"
 * <br><br>
 * There are six states:<br>
 * - IN_PREPARTION: "Pizza" objects start in this state<br>
 * - PREPARED: the "Pizza" can now be shoved into the oven<br>
 * - BAKING: the "Pizza" has been shoved into the oven, lasts for five minutes<br>
 * - DONE: "Salad" and "Drink" objects start in this state, "Pizza" objects land in this state after BAKING<br>
 * - IN_DELIVERY: the "Pizza"/"Salad"/"Drink" is being delivered<br>
 * - DELIVERED: the "Pizza"/"Salad"/"Drink" has been delivered
 */
public enum OrderLineState {
	IN_PREPARATION, PREPARED, BAKING, DONE, IN_DELIVERY, DELIVERED
}
