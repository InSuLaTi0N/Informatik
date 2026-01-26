package pizzaservice.catalog;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.salespointframework.catalog.Product;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.salespointframework.core.Currencies.EURO;

public class PizzaTest {

	@Test
	public void PizzaLogicTest(){
		Pizza pizza = new Pizza();

		Product ingredient = new Product("Käse",Money.of(10, EURO));
		ingredient.addCategory(new Category().ingredient);

		int amount = 1;

		MonetaryAmount expectedTotal1 = Money.of(10, EURO);
		MonetaryAmount expectedTotal2 = Money.of(20, EURO);

		String expectedString1 = ingredient.getName() + ", Anzahl" + amount + "\n";
		String expectedString2 = ingredient.getName() + ", Anzahl" + 2*amount + "\n";

		List<String> expectedIngredientStrings1 = new ArrayList<>();
		expectedIngredientStrings1.add(expectedString1);

		List<String> expectedIngredientStrings2 = new ArrayList<>();
		expectedIngredientStrings2.add(expectedString2);

		pizza.addIngredient(ingredient, amount);
		assertThat(pizza.getTotal().equals(expectedTotal1));
		assertThat(pizza.getIngredientString().equals(expectedString1));
		assertThat(pizza.getIngredientStrings().equals(expectedIngredientStrings1));

		pizza.addIngredient(ingredient, amount);
		assertThat(pizza.getTotal().equals(expectedTotal2));
		assertThat(pizza.getIngredientString().equals(expectedString2));
		assertThat(pizza.getIngredientStrings().equals(expectedIngredientStrings2));
	}
}