package pizzaservice.catalog;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.quantity.Quantity;
import org.springframework.util.Assert;

import javax.money.MonetaryAmount;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.salespointframework.core.Currencies.EURO;

/**
 * A {@link Product} extension that holds information about its ingredients.
 *
 * @see <a href="https://st.inf.tu-dresden.de/SalesPoint/api/org/salespointframework/catalog/Product.html">Product</a>
 */
@Entity
public class Pizza extends Product {

	@ElementCollection
	private List<String> ingredientStrings;
	private MonetaryAmount total;

	@SuppressWarnings({ "deprecation" })
	public Pizza(){
		super("Pizza", Money.of(0, EURO));
		this.ingredientStrings = new ArrayList<>();
		this.total = Money.of(4.99, EURO); // basic price
	}

	/**
	 * Adds an ingredient with the given amount.
	 *
	 * <br>
	 * - the name and amount is added as a String to the list of ingredients<br>
	 * - the associated cost is added to the total
	 *
	 * @param ingredient a {@link Product} with only the ingredient {@link Category}, not null
	 * @param amount an integer greater than 0
	 */
	public void addIngredient(Product ingredient, int amount){
		Assert.notNull(ingredient, "Ingredient must not be null!");
		Assert.isTrue(ingredient.getCategories().stream().count() == 1, "Ingredient must only have one category!");
		Assert.isTrue(ingredient.getCategories().stream().findFirst().orElse("").equals(new Category().ingredient)
				, "Ingredient must have the right category!");
		Assert.isTrue(amount > 0, "Amount must be greater than 0!");

		Quantity quantity = Quantity.of(amount);
		boolean skip = false;
		for(String s : ingredientStrings){
			String[] result = s.split(", ");
			if(result[0].equals(ingredient.getName())) {
				ingredientStrings.remove(s);
				int num = parseInt(result[1].replaceAll("[^0-9]", ""), 10);
				int result_quantity = quantity.getAmount().intValue() + num;
				ingredientStrings.add(ingredient.getName() + ", Anzahl: " + result_quantity + "\n");
				skip = true;
				break;
			}
		}
		if(!skip){
			ingredientStrings.add(ingredient.getName() +", Anzahl: " + quantity.toString() + "\n");
		}
		total = total.add(ingredient.getPrice().multiply(quantity.getAmount()));
	}

	public MonetaryAmount getTotal(){
		return total;
	}

	/**
	 * Gets a formatted String containing all ingredients with their associated amount.
	 *
	 * @return a String, potentially empty, not null
	 */
	public String getIngredientString(){
		StringBuilder result = new StringBuilder();
		for(String s : ingredientStrings){
			result.append(s);
		}
		return result.toString();
	}

	public List<String> getIngredientStrings() {
		return ingredientStrings;
	}
}
