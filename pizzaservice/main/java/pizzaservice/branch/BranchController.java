package pizzaservice.branch;

import org.javamoney.moneta.Money;
import org.salespointframework.accountancy.Accountancy;
import org.salespointframework.accountancy.AccountancyEntry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaservice.employee.Employee;
import pizzaservice.employee.EmployeeManagement;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.salespointframework.core.Currencies.EURO;

@Controller
@EnableScheduling
public class BranchController {

	private final Accountancy accountancy;
	private final EmployeeManagement employeeManagement;
	private final DateTimeFormatter timeFormatter;

	BranchController(Accountancy accountancy, EmployeeManagement employeeManagement) {
		Assert.notNull(accountancy, "Accountancy must not be null!");
		Assert.notNull(employeeManagement, "EmployeeManagement must not be null!");

		this.accountancy = accountancy;
		accountancy.add(new AccountancyEntry(Money.of(100000, EURO), "Andere Einkünfte"));
		this.employeeManagement = employeeManagement;
		this.timeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	}

	@GetMapping("/branch")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	public String accountancy(Model model) {
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusWeeks(1);

		Invoice invoice = new Invoice(accountancy, startDate, endDate);

		if(!model.containsAttribute("invoiceForm")){
			model.addAttribute("invoiceForm", new InvoiceForm(startDate.format(timeFormatter),
					endDate.format(timeFormatter)));
		}

		model.addAttribute("invoice", invoice);
		model.addAttribute("timeFormatter", timeFormatter);

		return "branch";
	}

	@PostMapping("/branch")
	@PreAuthorize("hasAnyRole('BOSS','ADMIN')")
	public String setAccountancy(Model model, @Valid InvoiceForm invoiceForm, Errors result,
								 RedirectAttributes redirectAttributes) {
		LocalDate startDate = LocalDate.parse(invoiceForm.getStart());
		LocalDate endDate = LocalDate.parse(invoiceForm.getEnd());

		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.invoiceForm", result);
			redirectAttributes.addFlashAttribute("invoiceForm", invoiceForm);
			return "redirect:/branch";
		}

		if(startDate.isAfter(endDate)) {
			result.rejectValue("start", "{InvoiceForm.error}",
					"Das Startdatum darf nicht hinter dem Enddatum liegen!");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.invoiceForm", result);
			redirectAttributes.addFlashAttribute("invoiceForm", invoiceForm);
			return "redirect:/branch";
		}

		Invoice invoice = new Invoice(accountancy, startDate, endDate);

		model.addAttribute("invoice", invoice);
		model.addAttribute("timeFormatter", timeFormatter);

		return "branch";
	}

	/**
	 * Adds an {@link AccountancyEntry} to the {@link Accountancy} every friday at 12:00 local time.<br>
	 * The {@link AccountancyEntry} contains the amount of money paid to all {@link Employee}s
	 * according to their salary and weekly hours.
	 *
	 * @see <a href="https://st.inf.tu-dresden.de/SalesPoint/api/org/salespointframework/accountancy/AccountancyEntry.html">AccountancyEntry</a>
	 * @see <a href="https://st.inf.tu-dresden.de/SalesPoint/api/org/salespointframework/accountancy/Accountancy.html">Accountancy</a>
	 */
	@Scheduled(cron="${payroll.cron}")
	public void payroll(){
		for(Employee e : employeeManagement.findAll()){
			Money salary = e.getSalary().multiply(e.getWeeklyHours());
			String description = "Name: " + e.getUserAccount().getFirstname() + " " + e.getUserAccount().getLastname()
					+ "\n Wochenstunden: " + e.getWeeklyHours() + "\n Stundenlohn: " + e.getSalary().getNumber() + " €";
			accountancy.add(new AccountancyEntry(salary.negate(), description));
		}
	}
}
