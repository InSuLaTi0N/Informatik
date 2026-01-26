package pizzaservice.catalog;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Catalog;
import org.salespointframework.catalog.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
public class CatalogController {

	private Catalog catalog;

	public CatalogController(Catalog catalog) {
		Assert.notNull(catalog, "Catalog must not be null!");
		this.catalog = catalog;
	}

	@GetMapping("/catalog")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String catalog(Model model) {
		model.addAttribute("current", "all");
		model.addAttribute("ingredients", catalog.findByCategory(new Category().ingredient).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("drinks", catalog.findByCategory(new Category().drink).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("salads", catalog.findByCategory(new Category().salad).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		return "catalog";
	}

	@PostMapping("/catalog")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String catalogSorted(Model model, String category) {

		if (category.equals("ingredients")) {
			model.addAttribute("current", "ingredients");
			model.addAttribute("ingredients", catalog.findByCategory(new Category().ingredient).
					stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
			model.addAttribute("drinks", null);
			model.addAttribute("salads", null);
		} else if (category.equals("drinks")) {
			model.addAttribute("current", "drinks");
			model.addAttribute("ingredients", null);
			model.addAttribute("drinks", catalog.findByCategory(new Category().drink).
					stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
			model.addAttribute("salads", null);
		} else if (category.equals("salads")) {
			model.addAttribute("current", "salads");
			model.addAttribute("ingredients", null);
			model.addAttribute("drinks", null);
			model.addAttribute("salads", catalog.findByCategory(new Category().salad).
					stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		} else {
			model.addAttribute("current", "all");
			model.addAttribute("ingredients", catalog.findByCategory(new Category().ingredient).
					stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
			model.addAttribute("drinks", catalog.findByCategory(new Category().drink).
					stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
			model.addAttribute("salads", catalog.findByCategory(new Category().salad).
					stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		}
		return "catalog";
	}

	@GetMapping("/catalog/add")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String catalogAdd(Model model){
		if(!model.containsAttribute("catalogForm")){
			model.addAttribute("catalogForm", new CatalogForm("","",""));
		}
		return "catalogAdd";
	}

	@PostMapping("/catalog/add/submit")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String addToCatalog(@Valid CatalogForm catalogForm, Errors result, String category,
						RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.catalogForm", result);
			redirectAttributes.addFlashAttribute("catalogForm", catalogForm);
			return "redirect:/catalog/add";
		}

		if(Double.parseDouble(catalogForm.getPrice()) < 0.01){
			result.rejectValue("price", "{CatalogForm.price.NotNegative}", "Der Preis darf nicht geringer als 0.01 € sein!");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.catalogForm", result);
			redirectAttributes.addFlashAttribute("catalogForm", catalogForm);
			return "redirect:/catalog/add";
		}

		Product product = new Product(catalogForm.getName(), Money.of(Double.parseDouble(catalogForm.getPrice()), "EUR"));

		if(category.equals("ingredient")){
			product.addCategory(new Category().ingredient);
			catalog.save(product);
		} else if(category.equals("drink")){
			product.addCategory(new Category().drink);
			catalog.save(product);
		} else{
			product.addCategory(new Category().salad);
			catalog.save(product);
		}
		return "redirect:/catalog";
	}

	@GetMapping("/catalog/edit")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String catalogEditGet(Model model, String productId, String name, String price){
		if(!model.containsAttribute("catalogForm")){
			model.addAttribute("catalogForm", new CatalogForm(productId, name, price));
		}
		return "catalogEdit";
	}

	@PostMapping("/catalog/edit")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String catalogEdit(Model model, String productId, String name, String price){
		if(!model.containsAttribute("catalogForm")){
			model.addAttribute("catalogForm", new CatalogForm(productId, name, price));
		}
		return "catalogEdit";
	}

	@PostMapping("/catalog/edit/submit")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String editProduct(@Valid CatalogForm catalogForm, Errors result, RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.catalogForm", result);
			redirectAttributes.addFlashAttribute("catalogForm", catalogForm);
			return "redirect:/catalog/edit";
		}

		if(Double.parseDouble(catalogForm.getPrice()) < 0.01){
			result.rejectValue("price", "{CatalogForm.price.NotNegative}",
					"Der Preis darf nicht geringer als 0.01 € sein!");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.catalogForm", result);
			redirectAttributes.addFlashAttribute("catalogForm", catalogForm);
			return "redirect:/catalog/edit";
		}

		Product product = getProduct(catalogForm.getProductId());
		product.setName(catalogForm.getName());
		product.setPrice(Money.of(Double.parseDouble(catalogForm.getPrice()), "EUR"));
		catalog.save(product);
		return "redirect:/catalog";
	}

	@PostMapping("/catalog/delete")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String deleteProduct(String productId){
		Product product = getProduct(productId);
		catalog.delete(product);
		return "redirect:/catalog";
	}

	/**
	 * Gets the {@link Product} associated with the given productId.
	 *
	 * @param productId not null, not empty
	 * @return a {@link Product}, not null
	 * @throws NoSuchElementException
	 */
	Product getProduct(String productId){
		Assert.hasText(productId, "ProductId must have text!");
		Product product = null;
		for (Object o : catalog.findAll()) {
			product = (Product) o;
			if(product.getId().toString().equals(productId)){
				return product;
			}
		}
		throw new NoSuchElementException("No Product with the given ProductId was found!");
	}
}
