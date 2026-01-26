package pizzaservice.catalog;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Catalog;
import org.salespointframework.catalog.Product;
import org.salespointframework.core.DataInitializer;
import org.salespointframework.inventory.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.money.MonetaryAmount;
import java.util.HashMap;
import java.util.Map;

import static org.salespointframework.core.Currencies.EURO;

/**
 * A {@link DataInitializer} implementation that adds ingredients, drinks and salads to the {@link Catalog}
 * for the application on application startup.
 *
 * @see <a href="https://st.inf.tu-dresden.de/SalesPoint/api/org/salespointframework/catalog/Product.html">Product</a>
 * @see <a href="https://st.inf.tu-dresden.de/SalesPoint/api/org/salespointframework/catalog/Catalog.html">Catalog</a>
 * @see <a href="https://st.inf.tu-dresden.de/SalesPoint/api/org/salespointframework/core/DataInitializer.html">DataInitializer</a>
 */
@Component
public class CatalogDataInitializer implements DataInitializer {

	private final Catalog catalog;
	private final Inventory inventory;

	private static final Logger LOG = LoggerFactory.getLogger(CatalogDataInitializer.class);

	public CatalogDataInitializer(Catalog catalog, @Qualifier("uniqueInventory") Inventory inventory) {
		Assert.notNull(catalog, "Catalog must not be null!");
		Assert.notNull(inventory, "Inventory must not be null!");

		this.catalog = catalog;
		this.inventory = inventory;
	}

	/*
	 * (non-Javadoc)
	 * @see org.salespointframework.core.DataInitializer#initialize()
	 */
	@Override
	public void initialize() {

		LOG.info("Creating products for the catalog.");

		Map<String, MonetaryAmount> ingredients = new HashMap<>();
		ingredients.put("Ananas", Money.of(0.99, EURO));
		ingredients.put("BBQ Sauce", Money.of(0.99, EURO));
		ingredients.put("Broccoli", Money.of(0.99, EURO));
		ingredients.put("Champignons", Money.of(0.99, EURO));
		ingredients.put("Cherrytomaten", Money.of(1.39, EURO));
		ingredients.put("Frühlingszwiebeln", Money.of(0.99, EURO));
		ingredients.put("Hähnchenbrust", Money.of(1.69, EURO));
		ingredients.put("Jalapenos", Money.of(0.99, EURO));
		ingredients.put("Käse (Mozzarella)", Money.of(1.69, EURO));
		ingredients.put("Mais", Money.of(0.99, EURO));
		ingredients.put("Paprika", Money.of(0.99, EURO));
		ingredients.put("Rinderhack", Money.of(1.39, EURO));
		ingredients.put("Röstzwiebeln", Money.of(0.99, EURO));
		ingredients.put("Salami", Money.of(1.39, EURO));
		ingredients.put("Sauce hollandaise", Money.of(0.99, EURO));
		ingredients.put("Schinken", Money.of(1.39, EURO));
		ingredients.put("Teriyaki Sauce", Money.of(0.99, EURO));
		ingredients.put("Thunfisch", Money.of(1.69, EURO));
		ingredients.put("Tomaten", Money.of(0.99, EURO));
		ingredients.put("Würstchen", Money.of(1.39, EURO));

		for(String s : ingredients.keySet()){
			Product ingredient = new Product(s, ingredients.get(s));
			ingredient.addCategory(new Category().ingredient);
			catalog.save(ingredient);
		}

		Map<String, MonetaryAmount> salads = new HashMap<>();
		salads.put("Hähnchensalat", Money.of(7.49,"EUR"));
		salads.put("Kartoffelsalat", Money.of(6.49,"EUR"));

		for(String s : salads.keySet()){
			Product salad = new Product(s, salads.get(s));
			salad.addCategory(new Category().salad);
			catalog.save(salad);
		}

		Map<String, MonetaryAmount> drinks = new HashMap<>();
		drinks.put("Coca-Cola 500ml", Money.of(1.59, EURO));
		drinks.put("Sprite 500ml", Money.of(1.59, EURO));

		for(String s : drinks.keySet()){
			Product drink = new Product(s, drinks.get(s));
			drink.addCategory(new Category().drink);
			catalog.save(drink);
		}
	}
}
