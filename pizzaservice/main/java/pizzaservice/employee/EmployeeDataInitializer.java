package pizzaservice.employee;

import org.javamoney.moneta.Money;
import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Password;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccountManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Initializer for the {@link EmployeeManagement}
 * add some needed Users to the System
 */
@Component
@Order(10)
public class EmployeeDataInitializer implements DataInitializer {

	private final UserAccountManagement userAccountManagement;
	private final EmployeeManagement employeeManagement;
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeDataInitializer.class);
	private final EmployeeRepository employees;
	private final UserAccountManagement userAccounts;

	public EmployeeDataInitializer(UserAccountManagement userAccountManagement, EmployeeManagement employeeManagement,
								   EmployeeRepository employees, UserAccountManagement userAccounts) {
		Assert.notNull(userAccountManagement, "UserAccountManagement must be not null!");
		Assert.notNull(employeeManagement, "EmployeeManagement must be not null");
		this.userAccountManagement = userAccountManagement;
		this.employeeManagement = employeeManagement;
		this.employees = employees;
		this.userAccounts = userAccounts;
	}

	@Override
	public void initialize(){
		if (userAccountManagement.findByUsername("admin").isPresent()){
			return;
		}

		LOG.info("Creating default users and customers.");

		var userAccount = userAccounts.create("Boss", Password.UnencryptedPassword.of("123"),
				Role.of("BOSS"));

		userAccount.setLastname("Wallace");
		userAccount.setFirstname("Marsellus");

		employees.save(new Employee(userAccount, Money.of(100, "EUR"),
				10,
				"Am Sandtorkai", "Hamburg",
				75,
				"20457"));

		List.of(
				new EmployeeRegistrationForm("Stauber", "Kurt", "123", "123","Nötnitzer Str.", "15","Dresden" , "01187",
						"20","ADMIN", "40", "Admin"),
				new EmployeeRegistrationForm("Tzschentke", "Corinna", "123",  "123", "Nötnitzer Str.", "15","Dresden" , "01187",
						"25","ADMIN", "40", "Admin2"),
				new EmployeeRegistrationForm("Wald", "Jan-Philipp", "123", "123","Nötnitzer Str.", "15","Dresden" , "01187",
						"8.50","EMPLOYEE", "40", "Employee1"),
				new EmployeeRegistrationForm("Mann", "Niklas", "123",  "123","Nötnitzer Str.", "15","Dresden" , "01187",
						"9.50","EMPLOYEE", "40", "Employee2")
		).forEach(employeeManagement::addEmployee);
	}
}
