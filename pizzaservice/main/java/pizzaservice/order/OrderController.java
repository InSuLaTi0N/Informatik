package pizzaservice.order;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Catalog;
import org.salespointframework.catalog.Product;
import org.salespointframework.order.OrderStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaservice.catalog.CatalogComparator;
import pizzaservice.catalog.Category;
import pizzaservice.catalog.Pizza;
import pizzaservice.customer.Customer;
import pizzaservice.customer.CustomerManagement;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.salespointframework.core.Currencies.EURO;

@Controller
public class OrderController {

	private OrderManager orderManager;
	private CustomerManagement customerManagement;
	private OrderScheduler orderScheduler;
	private DateTimeFormatter timeFormatter;
	private Catalog catalog;
	private Pizza currentPizza;
	private int diningSetsLeft = 15;

	OrderController(OrderManager orderManager, CustomerManagement customerManagement, Catalog catalog) {
		Assert.notNull(customerManagement, "CustomerManagement must not be null!");
		Assert.notNull(catalog, "Catalog must not be null!");

		this.orderManager = orderManager;
		this.customerManagement = customerManagement;
		this.orderScheduler = new OrderScheduler();
		this.timeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		this.catalog = catalog;
		this.currentPizza = new Pizza();

		orderScheduler.scheduleJob(orderManager);
	}

	@GetMapping("/order")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderLanding(Model model){
		model.addAttribute("timeFormatter", timeFormatter);
		model.addAttribute("orders", orderManager.getOrders(OrderStatus.OPEN));
		model.addAttribute("nextBakingTime", orderManager.getNextBakingTime().
				format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		return "order";
	}

	@GetMapping("order/new")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderNew(Model model){
		if(!model.containsAttribute("authenticationForm")){
			model.addAttribute("authenticationForm", new AuthenticationForm("",""));
		}
		if(catalog.findByCategory(new Category().ingredient).isEmpty()
				|| catalog.findByCategory(new Category().salad).isEmpty()
				|| catalog.findByCategory(new Category().drink).isEmpty()){
			model.addAttribute("error", "Das Sortiment muss mindestens ein Produkt von jeder Kategorie enthalten!");
			return "error";
		}
		return "orderNew";
	}

	@PostMapping("order/new/authenticate")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderAuthenticate(Model model, @Valid AuthenticationForm authenticationForm, Errors result,
							 RedirectAttributes redirectAttributes){
		boolean error = false;

		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authenticationForm",
					result);
			redirectAttributes.addFlashAttribute("authenticationForm", authenticationForm);
			error = true;
		}

		Customer customer = customerManagement.findOne(authenticationForm.getTelephoneNumber());

		if(customer == null){
			if(!error){
				result.rejectValue("telephoneNumber","{AuthenticationForm.telephoneNumber.NotFound}",
						"Unter der eingegebenen Telefonnummer existiert kein Kunde!");
				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authenticationForm",
						result);
				redirectAttributes.addFlashAttribute("authenticationForm", authenticationForm);
				error = true;
			}
		} else {
			if(!error){
				long customerToken = customerManagement.tanValidation(Integer.parseInt(authenticationForm.getTan()),
						customer.getId());

				if(customerToken == 0){
					result.rejectValue("tan","{AuthenticationForm.tan.NotBlank}", "Die eingegebene TAN ist falsch!");
					redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authenticationForm",
							result);
					redirectAttributes.addFlashAttribute("authenticationForm", authenticationForm);
					error = true;
				} else {
					model.addAttribute("telephoneNumber", authenticationForm.getTelephoneNumber());
					model.addAttribute("customerToken", customerToken);
				}
			}
		}
		if(error){
			return "redirect:/order/new";
		} else {
			return "orderAddress";
		}
	}

	@PostMapping("/order/new/items")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderItems(Model model, String telephoneNumber, String customerToken, String address,
					  @Valid OtherAddressForm otherAddressForm, Errors result, String otherAddress){
		Customer customer = customerManagement.findOne(telephoneNumber);

		// Verify if Address is correct, if not return to form
		if(address.equals("otherAddress")) {
			if (result.hasErrors()) {
				model.addAttribute("otherAddressForm", otherAddressForm);
				model.addAttribute("telephoneNumber", telephoneNumber);
				model.addAttribute("customerToken", customerToken);
				return "orderOtherAddress";
			} else {
				otherAddress = otherAddressForm.getStreet() + " " + otherAddressForm.getHouseNumber() + ", " +
						otherAddressForm.getPlz() + " " + otherAddressForm.getCity();
			}
		}

		currentPizza = new Pizza();
		model.addAttribute("currentPizza", currentPizza);
		model.addAttribute("ingredients", catalog.findByAllCategories(new Category().ingredient).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("salads", catalog.findByAllCategories(new Category().salad).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("drinks", catalog.findByAllCategories(new Category().drink).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("diningSetsLeft", diningSetsLeft);

		if(address.equals("defaultAddress")){
			MyOrder myOrder = new MyOrder(customer.getUserAccount(), customer.getAddress());
			myOrder.setCustomerToken(Long.parseLong(customerToken));
			myOrder.setCanBeDeleted(true);
			orderManager.save(myOrder);
			model.addAttribute("order", myOrder);
		} else if(address.equals("otherAddress")){
			MyOrder myOrder = new MyOrder(customer.getUserAccount(), otherAddress);
			myOrder.setCustomerToken(Long.parseLong(customerToken));
			myOrder.setCanBeDeleted(true);
			orderManager.save(myOrder);
			model.addAttribute("order", myOrder);
		} else {
			MyOrder myOrder = new MyOrder(customer.getUserAccount(), "Selbstabholung");
			myOrder.setCustomerToken(Long.parseLong(customerToken));
			myOrder.setCanBeDeleted(true);
			orderManager.save(myOrder);
			model.addAttribute("order", myOrder);
		}
		model.addAttribute("telephoneNumber", telephoneNumber);
		return "orderItems";
	}

	@PostMapping("/order/new/address")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderOtherAddress(Model model, String telephoneNumber, String customerToken){
		if(!model.containsAttribute("otherAddressForm")){
			model.addAttribute("otherAddressForm", new OtherAddressForm("", "", "", ""));
			model.addAttribute("telephoneNumber", telephoneNumber);
			model.addAttribute("customerToken", customerToken);
		}
		return "orderOtherAddress";
	}

	@PostMapping("/order/new/items/addIngredient")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderAddIngredient(Model model, String telephoneNumber, String productId, String orderId, int amount){
		MyOrder myOrder = orderManager.getOpenOrder(orderId);
		Product product = getProduct(productId);
		currentPizza.addIngredient(product, amount);

		model.addAttribute("order", myOrder);
		model.addAttribute("currentPizza", currentPizza);
		model.addAttribute("ingredients", catalog.findByAllCategories(new Category().ingredient).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("salads", catalog.findByAllCategories(new Category().salad).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("drinks", catalog.findByAllCategories(new Category().drink).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("diningSetsLeft", diningSetsLeft);
		model.addAttribute("telephoneNumber", telephoneNumber);
		return "orderItems";
	}

	@PostMapping("/order/new/items/addPizza")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderAddPizza(Model model, String telephoneNumber, String orderId){
		currentPizza.setPrice(currentPizza.getTotal());
		MyOrder myOrder = orderManager.addPizza(orderId, currentPizza);
		currentPizza = new Pizza();

		model.addAttribute("order", myOrder);
		model.addAttribute("currentPizza", currentPizza);
		model.addAttribute("ingredients", catalog.findByAllCategories(new Category().ingredient).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("salads", catalog.findByAllCategories(new Category().salad).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("drinks", catalog.findByAllCategories(new Category().drink).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("diningSetsLeft", diningSetsLeft);
		model.addAttribute("telephoneNumber", telephoneNumber);
		return "orderItems";
	}

	@PostMapping("/order/new/items/add")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderAdd(Model model, String telephoneNumber, String productId, String orderId, int amount){
		Product product = getProduct(productId);
		MyOrder myOrder = orderManager.addProduct(orderId, product, amount);

		model.addAttribute("order", myOrder);
		model.addAttribute("currentPizza", currentPizza);
		model.addAttribute("ingredients", catalog.findByAllCategories(new Category().ingredient).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("salads", catalog.findByAllCategories(new Category().salad).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("drinks", catalog.findByAllCategories(new Category().drink).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("diningSetsLeft", diningSetsLeft);
		model.addAttribute("telephoneNumber", telephoneNumber);
		return "orderItems";
	}

	@PostMapping("/order/new/items/addDiningSet")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderAddDiningSet(Model model, String telephoneNumber, String orderId, int amount){

		Customer customer = customerManagement.findOne(telephoneNumber);
		MyOrder myOrder = orderManager.getOpenOrder(orderId);

		myOrder = orderManager.addDiningSet(orderId, amount); // fails if order doesn't contain a Pizza!
		customerManagement.addDiningSet(customer, amount);

		model.addAttribute("order", myOrder);
		model.addAttribute("currentPizza", currentPizza);
		model.addAttribute("ingredients", catalog.findByAllCategories(new Category().ingredient).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("salads", catalog.findByAllCategories(new Category().salad).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
		model.addAttribute("drinks", catalog.findByAllCategories(new Category().drink).
				stream().sorted(new CatalogComparator()).collect(Collectors.toList()));

		diningSetsLeft -= amount;
		model.addAttribute("diningSetsLeft", diningSetsLeft);
		model.addAttribute("telephoneNumber", telephoneNumber);
		return "orderItems";
	}

	@PostMapping("/order/new/items/done")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderFinish(Model model, String telephoneNumber, String orderId){
		Customer customer = customerManagement.findOne(telephoneNumber);
		MyOrder myOrder = orderManager.getOpenOrder(orderId);

		int newTan = customerManagement.tokenValidation(myOrder.getCustomerToken(),customer.getId());
		if(newTan == 0){
			orderManager.delete(myOrder);
			model.addAttribute("error", "Fehler bei Validierung des Kundentokens");
			return "error";
		} else {
			orderManager.discount(orderId);
			model.addAttribute("order", myOrder);
			if(orderManager.freeDrink(orderId) && !catalog.findByAllCategories(new Category().drink).isEmpty()){
				model.addAttribute("drinks", catalog.findByAllCategories(new Category().drink).
						stream().sorted(new CatalogComparator()).collect(Collectors.toList()));
				model.addAttribute("telephoneNumber", telephoneNumber);
				model.addAttribute("newTan", newTan);
				return "orderFreeDrink";
			} else {
				myOrder.setCanBeDeleted(false);
				orderManager.save(myOrder);
				model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
				model.addAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
				model.addAttribute("customer", customer);
				model.addAttribute("deliveryTime", orderManager.getDeliveryTime(orderId).format(timeFormatter));
				model.addAttribute("newTan", newTan);
				return "orderComplete";
			}
		}
	}

	@PostMapping("/order/new/items/freeDrink/done")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderFreeDrink(Model model, String productId, String orderId, String telephoneNumber, String newTan){
		Product product = new Product("GRATIS " + getProduct(productId).getName(), Money.of(0, EURO));
		product.addCategory(new Category().drink);

		MyOrder myOrder = orderManager.addProduct(orderId, product, 1);
		Customer customer = customerManagement.findOne(telephoneNumber);

		myOrder.setCanBeDeleted(false);
		orderManager.save(myOrder);
		model.addAttribute("order", myOrder);
		model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
		model.addAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		model.addAttribute("customer", customer);
		model.addAttribute("deliveryTime", orderManager.getDeliveryTime(orderId).format(timeFormatter));
		model.addAttribute("newTan", newTan);
		return "orderComplete";
	}

	@PostMapping("/order/new/cancel")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String orderCancel(String orderId){
		MyOrder order = orderManager.getOpenOrder(orderId);
		orderManager.delete(order);

		return "redirect:/order";
	}

	@GetMapping("/order/view/inPreparation")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String statePizza(Model model){
		model.addAttribute("timeFormatter", timeFormatter);
		model.addAttribute("unpreparedPizzas", orderManager.getUnpreparedPizzas());
		return "orderPizza";
	}

	@PostMapping("/order/view/inPreparation/submit")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String preparePizza(String orderId, int orderLineInt){
		orderManager.preparePizza(orderId, orderLineInt);
		return "redirect:/order/view/inPreparation";
	}

	@GetMapping("/order/view/done")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String stateDone(Model model){
		model.addAttribute("timeFormatter", timeFormatter);
		List myOrders = new ArrayList<>();
		for(MyOrder o : orderManager.getOrders(OrderLineState.DONE)){
			if(!o.isPickUp()){
				myOrders.add(o);
			}
		}
		model.addAttribute("orders", myOrders);
		return "orderDone";
	}

	@PostMapping("/order/view/done/submit")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String deliver(String orderId){
		orderManager.deliverOrder(orderId);
		return "redirect:/order/view/done";
	}

	@GetMapping("/order/view/pickUp")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String statePickUp(Model model){
		model.addAttribute("timeFormatter", timeFormatter);
		List myOrders = new ArrayList<>();
		for(MyOrder o : orderManager.getOrders(OrderLineState.DONE)){
			if(o.isPickUp()){
				myOrders.add(o);
			}
		}
		model.addAttribute("orders", myOrders);
		return "orderPickUp";
	}

	@PostMapping("/order/view/pickUp/submit")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String pickUp(String orderId){
		orderManager.completeOrder(orderId);
		return "redirect:/order/view/pickUp";
	}

	@GetMapping("/order/view/inDelivery")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String stateInDelivery(Model model){
		model.addAttribute("timeFormatter", timeFormatter);
		model.addAttribute("orders",orderManager.getOrders(OrderLineState.IN_DELIVERY));
		return "orderInDelivery";
	}

	@PostMapping("/order/view/inDelivery/submit")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String complete(String orderId){
		orderManager.completeOrder(orderId);
		return "redirect:/order/view/inDelivery";
	}

	@GetMapping("/oven")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String oven(Model model){
		model.addAttribute("orderManager", orderManager);
		return "oven";
	}

	@PostMapping("/oven/done")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String setOven(int amount){
		orderManager.setOvenCount(amount);
		orderScheduler.scheduleJob(orderManager);
		return "redirect:/branch";
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
