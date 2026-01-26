package pizzaservice.catalog;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.salespointframework.catalog.Catalog;
import org.salespointframework.catalog.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pizzaservice.AbstractIntegrationTests;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.salespointframework.core.Currencies.EURO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class CatalogControllerTest extends AbstractIntegrationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	Catalog catalog;

	CatalogController catalogController;

	Product product;

	@BeforeEach
	public void setUp() {
		product = new Product("Test Produkt", Money.of(30, EURO));
		product.addCategory(new Category().ingredient);
		catalog.save(product);

		catalogController = new CatalogController(catalog);
	}

	@WithMockUser(roles="EMPLOYEE")
	@Test
	public void catalogNotAdminTest() throws Exception {
		mvc.perform(get("/catalog"))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void catalogTest() throws Exception {
		mvc.perform(get("/catalog"))
				.andExpect(status().isOk())
				.andExpect(view().name("catalog"))
				.andExpect(model().attributeExists("current", "drinks", "ingredients", "salads"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void catalogIngredientTest() throws Exception {
		mvc.perform(post("/catalog")
				.param("category", "ingredients"))
				.andExpect(status().isOk())
				.andExpect(view().name("catalog"))
				.andExpect(model().attribute("current", "ingredients"))
				.andExpect(model().attributeExists("current", "ingredients"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void catalogDrinkTest() throws Exception {
		mvc.perform(post("/catalog")
				.param("category", "drinks"))
				.andExpect(status().isOk())
				.andExpect(view().name("catalog"))
				.andExpect(model().attribute("current", "drinks"))
				.andExpect(model().attributeExists("current", "drinks"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void catalogSaladTest() throws Exception {
		mvc.perform(post("/catalog")
				.param("category", "salads"))
				.andExpect(status().isOk())
				.andExpect(view().name("catalog"))
				.andExpect(model().attribute("current", "salads"))
				.andExpect(model().attributeExists("current", "salads"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void catalogAddTest() throws Exception {
		mvc.perform(get("/catalog/add"))
				.andExpect(status().isOk())
				.andExpect(view().name("catalogAdd"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void addToCatalogTest() throws Exception {
		String name = "New Product Name";
		String price = Float.toString((float) 54.25);
		String category = new Category().salad;

		mvc.perform(post("/catalog/add/submit")
				.param("name", name)
				.param("price", price)
				.param("category", category)
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/catalog"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void catalogEditTest() throws Exception {
		mvc.perform(post("/catalog/edit")
				.param("productId", product.getId().toString())
				.param("name", product.getName())
				.param("price", product.getPrice().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("catalogEdit"))
				.andExpect(model().attributeExists("catalogForm"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void editProductTest() throws Exception {
		String name = "Edited Product Name";
		Float price = (float) 15.49;

		mvc.perform(post("/catalog/edit/submit")
				.param("productId", product.getId().toString())
				.param("name", name)
				.param("price", price.toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/catalog"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void deleteProductTest() throws Exception {
		mvc.perform(post("/catalog/delete")
				.param("productId", product.getId().toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/catalog"));
	}

	@Test
	public void getProductTest() {
		Product foundProduct = catalogController.getProduct(product.getId().toString());
		assertThat(foundProduct.getId().toString().equals(product.getId().toString()));
	}

	@Test
	public void getProductExceptionTest() {
		String id = "asfsdg";
		Assertions.assertThrows(NoSuchElementException.class, () -> catalogController.getProduct(id));
	}
}
