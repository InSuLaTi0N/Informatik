package pizzaservice.employee;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
class EmployeeController {

	private final EmployeeManagement employeeManagement;

	EmployeeController(EmployeeManagement employeeManagement) {
		Assert.notNull(employeeManagement, "EmployeeManagement cant be null!");
		this.employeeManagement = employeeManagement;
	}

	@GetMapping("/employee")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String employee(Model model) {
		model.addAttribute("employeeList", employeeManagement.findAll());
		return "employee";
	}

	@GetMapping("/employeeAdd")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String employeeAdd(Model model) {
		if (!model.containsAttribute("employeeRegistrationForm")) {
			model.addAttribute("employeeRegistrationForm", new EmployeeRegistrationForm(
					"", "", "", "", "",
					"", "", "", "", "", "", ""));
		}
		return "employeeAdd";
	}

	@PostMapping("/employeeAdd")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String registerNewEmployee(@Valid EmployeeRegistrationForm employeeRegistrationForm,
							   Errors result, RedirectAttributes redirectAttributes) {
		boolean error = false;

		if (!employeeManagement.checkUser(employeeRegistrationForm.getUserName())) {
			result.rejectValue("userName", "{RegistrationForm.userName.AlreadyExists}",
					"Es existiert bereits ein Mitarbeiter unter dem eingegebenen Nutzernamen!");
			error = true;
		}
		if (!employeeRegistrationForm.getPassword().equals(employeeRegistrationForm.getConfirmpassword())) {
			result.rejectValue("confirmpassword", "{RegistrationForm.confirmpassword.NotSame}",
					"Die Passwörter müssen übereinstimmen!");
			error = true;
		}

		if (error) {
			redirectAttributes.addFlashAttribute(
					"org.springframework.validation.BindingResult.employeeRegistrationForm", result);
			redirectAttributes.addFlashAttribute("employeeRegistrationForm", employeeRegistrationForm);
		}
		if (error || error(employeeRegistrationForm, result, redirectAttributes)) {
			return "redirect:/employeeAdd";
		}

		employeeManagement.addEmployee(employeeRegistrationForm);
		return "redirect:/employee";
	}

	@GetMapping("/employeeManagerUpdateEmployee/{employeeId}")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String employeeUpdateGet(Model model, @PathVariable String employeeId) {
		long employeeID = Long.parseLong(employeeId);
		var employee = employeeManagement.findByID(employeeID);

		model.addAttribute("employee", employee);
		if (!model.containsAttribute("employeeRegistrationForm")) {
			EmployeeRegistrationForm employeeRegistrationForm = new EmployeeRegistrationForm(
					employee.getUserAccount().getLastname(),
					employee.getUserAccount().getFirstname(),
					employee.getUserAccount().getPassword().toString(),
					employee.getUserAccount().getPassword().toString(),
					employee.getStreet(),
					Integer.toString(employee.getHouseNumber()),
					employee.getCity(),
					employee.getPlz(),
					BigDecimal.valueOf(employee.getSalary().getNumber().doubleValueExact()).toPlainString(),
					employee.getRole(),
					Integer.toString(employee.getWeeklyHours()),
					employee.getUserAccount().getUsername());
			model.addAttribute("employeeRegistrationForm", employeeRegistrationForm);
		}
		return "employeeUpdate";
	}

	@PostMapping("/employeeManagerUpdateEmployee/{employeeId}")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String employeeUpdate(Model model, @PathVariable String employeeId) {
		long employeeID = Long.parseLong(employeeId);
		var employee = employeeManagement.findByID(employeeID);

		model.addAttribute("employee", employee);
		if (!model.containsAttribute("employeeRegistrationForm")) {
			EmployeeRegistrationForm employeeRegistrationForm = new EmployeeRegistrationForm(
					employee.getUserAccount().getLastname(),
					employee.getUserAccount().getFirstname(),
					employee.getUserAccount().getPassword().toString(),
					employee.getUserAccount().getPassword().toString(),
					employee.getStreet(),
					Integer.toString(employee.getHouseNumber()),
					employee.getCity(),
					employee.getPlz(),
					BigDecimal.valueOf(employee.getSalary().getNumber().doubleValueExact()).toPlainString(),
					employee.getRole(),
					Integer.toString(employee.getWeeklyHours()),
					employee.getUserAccount().getUsername());
			model.addAttribute("employeeRegistrationForm", employeeRegistrationForm);
		}
		return "employeeUpdate";
	}

	@PostMapping("/employeeUpdate/{employeeId}")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String updateEmployee(Model model, @PathVariable String employeeId,
						  @Valid EmployeeRegistrationForm employeeRegistrationForm,
						  Errors result, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		long employeeID = Long.parseLong(employeeId);
		var employee = employeeManagement.findByID(employeeID);

		if (error(employeeRegistrationForm, result, redirectAttributes)) {
			redirectAttributes.addFlashAttribute("employee", employee);
			return "redirect:/employeeManagerUpdateEmployee/{employeeId}";
		}

		if (request.isUserInRole("BOSS")) {
			employeeManagement.updateEmployee(employee, employeeRegistrationForm, "BOSS");
		} else {
			employeeManagement.updateEmployee(employee, employeeRegistrationForm, "ADMIN");
		}

		return "redirect:/employee";
	}

	@PostMapping("/employeeManagerDeleteEmployee/{employeeId}")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	String deleteEmployee(Model model, @PathVariable String employeeId, HttpServletRequest request) {
		long employeeID = Long.parseLong(employeeId);
		var employee = employeeManagement.findByID(employeeID);

		if (request.isUserInRole("BOSS")) {
			employeeManagement.deleteEmployee(employee, "BOSS");
		} else {
			employeeManagement.deleteEmployee(employee, "ADMIN");
		}


		model.addAttribute("employeeList", employeeManagement.findAll());
		return "employee";
	}

	private boolean error(EmployeeRegistrationForm employeeRegistrationForm,
						  Errors result, RedirectAttributes redirectAttributes) {
		boolean error = false;

		if (result.hasErrors()) {
			error = true;
			redirectAttributes.addFlashAttribute(
					"org.springframework.validation.BindingResult.employeeRegistrationForm", result);
			redirectAttributes.addFlashAttribute("employeeRegistrationForm", employeeRegistrationForm);
		}

		return error;
	}
}
