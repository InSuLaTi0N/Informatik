package pizzaservice.customer;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaservice.order.OrderManager;

import javax.validation.Valid;

@Controller
class CustomerController {

	private final CustomerManagement customerManagement;
	private final OrderManager orderManager;

	CustomerController(CustomerManagement customerManagement, OrderManager orderManager) {
		Assert.notNull(customerManagement, "CustomerManagement cannot be null!");
		Assert.notNull(orderManager, "OrderManager cannot be null!");
		this.customerManagement = customerManagement;
		this.orderManager = orderManager;
	}

	@GetMapping("/customer")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String customer(Model model) {
		model.addAttribute("customerList", customerManagement.findAll());
		return "customer";
	}

	@GetMapping("/customerRegister")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String customerRegister(Model model) {
		if(!model.containsAttribute("customerRegistrationForm")){
			model.addAttribute("customerRegistrationForm", new CustomerRegistrationForm(
					"","", "", "", "", "", ""));
		}
		return "customerRegister";
	}

	@PostMapping("/customer/customerRegister")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String registerNewCustomer(Model model,
							   @Valid CustomerRegistrationForm customerRegistrationForm,
							   Errors result, RedirectAttributes redirectAttributes) {
		boolean sameUser = false;
		if (!customerManagement.checkUser(customerRegistrationForm.getTelephoneNumber())) {
			result.rejectValue("telephoneNumber", "{RegistrationForm.telephoneNumber.AlreadyExists}",
					"Es existiert bereits ein Kunde unter der eingegebenen Telefonnummer!");
			redirectAttributes.addFlashAttribute(
					"org.springframework.validation.BindingResult.customerRegistrationForm", result);
			redirectAttributes.addFlashAttribute("customerRegistrationForm", customerRegistrationForm);
			sameUser = true;
		}

		if(sameUser || error(customerRegistrationForm, result, redirectAttributes)){
			return "redirect:/customerRegister";
		}

		String tel = customerRegistrationForm.getTelephoneNumber();
		int tan = customerManagement.createTan();

		customerManagement.addCustomer(customerRegistrationForm, tan);

		model.addAttribute("tel", tel);
		model.addAttribute("tan", tan);
		return "customerWelcome";
	}

	@GetMapping("/customerUpdate/{customerId}")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String customerUpdateGet(Model model, @PathVariable String customerId){
		long customerID = Long.parseLong(customerId);
		var customer = customerManagement.findByID(customerID);

		model.addAttribute("customer", customer);
		if(!model.containsAttribute("customerRegistrationForm")){
			CustomerRegistrationForm customerRegistrationForm = new CustomerRegistrationForm(
					customer.getUserAccount().getFirstname(),
					customer.getUserAccount().getLastname(),
					customer.getStreet(),
					customer.getCity(),
					customer.getPlz(),
					Integer.toString(customer.getHouseNumber()),
					customer.getTelephoneNumber());
			model.addAttribute("customerRegistrationForm", customerRegistrationForm);
		}
		return "customerUpdate";
	}

	@PostMapping("/customerUpdate/{customerId}")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String customerUpdate(Model model, @PathVariable String customerId){
		long customerID = Long.parseLong(customerId);
		var customer = customerManagement.findByID(customerID);

		model.addAttribute("customer", customer);
		if(!model.containsAttribute("customerRegistrationForm")){
			CustomerRegistrationForm customerRegistrationForm = new CustomerRegistrationForm(
					customer.getUserAccount().getFirstname(),
					customer.getUserAccount().getLastname(),
					customer.getStreet(),
					customer.getCity(),
					customer.getPlz(),
					Integer.toString(customer.getHouseNumber()),
					customer.getTelephoneNumber());
			model.addAttribute("customerRegistrationForm", customerRegistrationForm);
		}
		return "customerUpdate";
	}

	@PostMapping("/customer/updateCustomer/{customerId}")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String updateCustomer(Model model, @PathVariable String customerId,
						  @Valid CustomerRegistrationForm customerRegistrationForm,
						  Errors result, RedirectAttributes redirectAttributes){
		long customerID = Long.parseLong(customerId);
		var customer = customerManagement.findByID(customerID);

		boolean sameUser = false;
		if (!customerManagement.checkUpdatedUser(customer, customerRegistrationForm.getTelephoneNumber())) {
			result.rejectValue("telephoneNumber", "{RegistrationForm.telephoneNumber.AlreadyExists}",
					"Es existiert bereits ein Kunde unter der eingegebenen Telefonnummer!");
			redirectAttributes.addFlashAttribute(
					"org.springframework.validation.BindingResult.customerRegistrationForm", result);
			redirectAttributes.addFlashAttribute("customerRegistrationForm", customerRegistrationForm);
			sameUser = true;
		}

		if(sameUser || error(customerRegistrationForm, result, redirectAttributes)){
			redirectAttributes.addFlashAttribute("customer", customer);
			return "redirect:/customerUpdate/{customerId}";
		}


		customerManagement.updateCustomer(customer, customerRegistrationForm);

		model.addAttribute("customerList", customerManagement.findAll());
		return "redirect:/customer";
	}

	@PostMapping("/customer/return/{customerId}")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String returnDiningSets(Model model, @PathVariable String customerId) {
		long customerID = Long.parseLong(customerId);
		var customer = customerManagement.findByID(customerID);

		int amount = customerManagement.checkDiningSets(customer);

		model.addAttribute("customer", customer);
		model.addAttribute("amount", amount);

		return "returnDiningSets";
	}

	@PostMapping("/customer/return/done/{customerId}")
	@PreAuthorize("hasAnyRole('BOSS','EMPLOYEE','ADMIN')")
	String returnDiningSetsDone(Model model, @PathVariable String customerId, String amount){
		long customerID = Long.parseLong(customerId);
		var customer = customerManagement.findByID(customerID);

		customerManagement.deleteDiningSets(customer, Integer.parseInt(amount));
		orderManager.returnDiningSets(customer, Integer.parseInt(amount));

		return "redirect:/customer";
	}

	private boolean error(CustomerRegistrationForm customerRegistrationForm,
						  Errors result, RedirectAttributes redirectAttributes){
		boolean error = false;
		if(result.hasErrors()){
			error = true;
		}

		try {
			long telephoneNumber = Long.parseLong(customerRegistrationForm.getTelephoneNumber());
			if(telephoneNumber < 0){
				result.rejectValue("telephoneNumber", "{RegistrationForm.telephoneNumber.NotPositive}",
						"Die Telefonnummer muss eine positive Zahl sein!");
				error = true;
			}
		} catch(NumberFormatException e) {
			result.rejectValue("telephoneNumber", "{RegistrationForm.telephoneNumber.NotNumber}",
					"Die Telefonnummer muss eine Zahl sein!");
			error = true;
		}

		if(error){
			redirectAttributes.addFlashAttribute(
					"org.springframework.validation.BindingResult.customerRegistrationForm", result);
			redirectAttributes.addFlashAttribute("customerRegistrationForm", customerRegistrationForm);
		}
		return error;
	}
}
