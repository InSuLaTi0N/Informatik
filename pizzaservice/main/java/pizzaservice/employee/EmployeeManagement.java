package pizzaservice.employee;

import org.javamoney.moneta.Money;
import org.salespointframework.useraccount.Password;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManagement;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * this class represents the main logic for the Employee DataBase
 * its managing the Employee and
 * its using the {@link UserAccountManagement} to manage the {@link UserAccount},
 * wich are linked to the {@link Employee}
 */
@Service
@Transactional
public class EmployeeManagement {

	public Role employeeRole;

	private final EmployeeRepository employees;
	private final UserAccountManagement userAccounts;

	EmployeeManagement(EmployeeRepository employees, UserAccountManagement userAccounts) {

		Assert.notNull(employees, "EmpolyeeRepository must not be null!");
		Assert.notNull(userAccounts, "UserAccountManagement must not be null!");

		this.employees = employees;
		this.userAccounts = userAccounts;
	}

	/**
	 * add an {@link Employee} to the {@link EmployeeManagement}
	 * @param employeeRegistrationForm is the input for all needed attributes to add an {@link Employee}
	 * @return the created employee
	 */
	public Employee addEmployee(EmployeeRegistrationForm employeeRegistrationForm) {

		var password = Password.UnencryptedPassword.of(employeeRegistrationForm.getPassword());

		Money salary = Money.of(employeeRegistrationForm.getSalary(), "EUR");

		if (employeeRegistrationForm.getRole().equals("ADMIN")){
			employeeRole = Role.of("ADMIN");
		} else {
			employeeRole = Role.of("EMPLOYEE");
		}

		var userAccount = userAccounts.create(employeeRegistrationForm.getUserName(), password, employeeRole);

		userAccount.setLastname(employeeRegistrationForm.getLastName());
		userAccount.setFirstname(employeeRegistrationForm.getFirstName());

		return employees.save(new Employee(userAccount,salary,
				employeeRegistrationForm.getWeeklyHours(),
				employeeRegistrationForm.getStreet(), employeeRegistrationForm.getCity(),
				employeeRegistrationForm.getHouseNumber(),
				employeeRegistrationForm.getPlz()));
	}

	/**
	 * Method to Update a {@link Employee}
	 * @param employee which will updated
	 * @param employeeRegistrationForm contains the updated data
	 * update all attributes except the password and the userName of the {@link UserAccount}
	 *
	 * @return the updated employee
	 */
	public Employee updateEmployee(Employee employee, EmployeeRegistrationForm employeeRegistrationForm, String role) {
		if (employee.getRole().equals("BOSS")){
			if (role.equals("BOSS")){
				employee.setCity(employeeRegistrationForm.getCity());
				employee.setHouseNumber(employeeRegistrationForm.getHouseNumber());
				employee.setStreet(employeeRegistrationForm.getStreet());
				employee.setPlz(employeeRegistrationForm.getPlz());

				Money salary = Money.of(employeeRegistrationForm.getSalary(), "EUR");
				employee.setSalary(salary);

				employee.setWeeklyHours(employeeRegistrationForm.getWeeklyHours());

				var userAccount = employee.getUserAccount();

				userAccount.setLastname(employeeRegistrationForm.getLastName());
				userAccount.setFirstname(employeeRegistrationForm.getFirstName());

				userAccount.add(employeeRole);
				userAccounts.save(userAccount);

				return employees.save(employee);
			} else {
				return employee;
			}
		}

		employee.setCity(employeeRegistrationForm.getCity());
		employee.setHouseNumber(employeeRegistrationForm.getHouseNumber());
		employee.setStreet(employeeRegistrationForm.getStreet());
		employee.setPlz(employeeRegistrationForm.getPlz());

		Money salary = Money.of(employeeRegistrationForm.getSalary(), "EUR");
		employee.setSalary(salary);

		employee.setWeeklyHours(employeeRegistrationForm.getWeeklyHours());

		var userAccount = employee.getUserAccount();

		userAccount.setLastname(employeeRegistrationForm.getLastName());
		userAccount.setFirstname(employeeRegistrationForm.getFirstName());

		employeeRole = Role.of("ADMIN");
		userAccount.remove(employeeRole);
		employeeRole = Role.of("EMPLOYEE");
		userAccount.remove(employeeRole);

		if (employeeRegistrationForm.getRole().equals("ADMIN")){
			employeeRole = Role.of("ADMIN");
		} else {
			employeeRole = Role.of("EMPLOYEE");
		}

		userAccount.add(employeeRole);
		userAccounts.save(userAccount);

		return employees.save(employee);
	}

	/**
	 * delete the given employee
	 * @param employee
	 * @return the old employee object
	 */
	public Employee deleteEmployee(Employee employee, String role) {
		boolean permissionError = false;
		if (employee.getRole().equals("ADMIN")){
			if (role.equals("BOSS")){
				var useraccount = employee.getUserAccount();
				userAccounts.delete(useraccount);
				employees.delete(employee);
				return employee;
			} else {
				permissionError = true;
			}
		}
		if (employee.getRole().equals("BOSS")){
			permissionError = true;
		}
		if(!permissionError){
			var useraccount = employee.getUserAccount();
			userAccounts.delete(useraccount);
			employees.delete(employee);
			return employee;
		} else {
			return null;
		}

	}

	public Employee deleteAdmin(Employee employee) {
		if (employee.getRole().equals("ADMIN")){
			var useraccount = employee.getUserAccount();
			userAccounts.delete(useraccount);
			employees.delete(employee);
			return employee;
		} else {
			return null;
		}
	}

	public Streamable<Employee> findAll() {
		return employees.findAll();
	}

	/**
	 * Method Used to avoid two similar userNames
	 *
	 * @param name
	 *
	 * @return boolean
	 * if name used return false
	 * else return true
	 */
	public boolean checkUser(String name) {
		return employees.findAll().filter(Employee -> Employee.getUserAccount().getUsername().equals(name)).isEmpty();
	}

	/**
	 * Method searches {@link Employee} in the Database by id
	 * @param id individual id of the {@link Employee}
	 * @return Employee when found, otherwise null
	 */
	public Employee findByID(long id) {
		var employee = employees.findById(id).orElse(null);
		return employee;
	}
}
