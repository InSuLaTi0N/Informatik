package pizzaservice.catalog;

import org.salespointframework.catalog.Product;

import java.util.Comparator;

public class CatalogComparator implements Comparator<Product> {
	public int compare(Product p1, Product p2) {
		return p1.getName().compareTo(p2.getName());
	}
}
