package pizzaservice.catalog;

import javax.validation.constraints.NotBlank;

/**
 * A form object for the creation / editing of products.
 */
public class CatalogForm {

	private final String productId;

	@NotBlank(message = "{CatalogForm.name.NotBlank}")
	private final String name;

	@NotBlank(message = "{CatalogForm.price.NotBlank}")
	private final String price;

	public CatalogForm(String productId, String name, String price) {
		if(productId == null){
			productId = "";
		}
		this.productId = productId.trim();

		this.name = name.trim();
		this.price = price.trim();
	}

	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}
}
