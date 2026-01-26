package pizzaservice.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pizzaservice.AbstractIntegrationTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class EmployeeManagementTest extends AbstractIntegrationTests {

	@Autowired
	EmployeeManagement employeeManagement;

	@Test
	public void addEmployeeTest() {
		String firstName = "Test";
		String lastName = "Employee1";
		String password = "Passwort";
		String confirmpassword = "Passwort";

		String street = "Alexander Platz";
		String houseNumber = "1";
		String city = "Berlin";
		String plz = "10115";

		String salary = "1500";
		String weeklyHours = "40";
		String role = "EMPLOYEE";

		String username = "User3";

		EmployeeRegistrationForm employeeRegistrationForm = new EmployeeRegistrationForm(lastName, firstName, password,
				confirmpassword, street, houseNumber, city, plz, salary, role, weeklyHours, username);
		assertEquals(confirmpassword, employeeRegistrationForm.getConfirmpassword());


		Employee employee = employeeManagement.addEmployee(employeeRegistrationForm);
		assertThat(employeeManagement.findByID(employee.getId()).equals(employee));
	}

	@Test
	public void updateEmployeeTest() {
		String firstName = "Test_updated";
		String lastName = "Employee1_updated";
		String password = "Passwort_updated";
		String confirmpassword = "Passwort_updated";

		String street = "Alexander Platz_updated";
		String houseNumber = "1";
		String city = "Berlin_updated";
		String plz = "10115";

		String salary = "1500";
		String weeklyHours = "40";
		String role = "EMPLOYEE";

		String username = "User4";

		EmployeeRegistrationForm employeeRegistrationForm = new EmployeeRegistrationForm(lastName, firstName,
				password, confirmpassword, street, houseNumber, city, plz, salary, role, weeklyHours, username);

		firstName = "Test_updated";
		lastName = "Employee1_updated";
		password = "Passwort_updated";
		confirmpassword = "Passwort_updated";

		street = "Alexander Platz_updated";
		houseNumber = "1";
		city = "Berlin_updated";
		plz = "10115";

		salary = "1500";
		weeklyHours = "40";
		role = "EMPLOYEE";

		username = "User4";

		EmployeeRegistrationForm updatedEmployeeRegistrationForm = new EmployeeRegistrationForm(lastName, firstName, password,
				confirmpassword, street, houseNumber, city, plz, salary, role, weeklyHours, username);

		Employee employee2 = employeeManagement.addEmployee(employeeRegistrationForm);
		Employee employee1 = employeeManagement.updateEmployee(employee2, updatedEmployeeRegistrationForm,"BOSS");

		assertEquals(employee2, employeeManagement.findByID(employee1.getId()));
	}

	@Test
	public void addEmployeeWithNullTest() {
		String firstName = "Test";
		String lastName = "Employee1";
		String password = "Passwort";
		String confirmpassword = "Passwort";

		String street = "Alexander Platz";
		String houseNumber = null;
		String city = "Berlin";
		String plz = "01186";

		String salary = null;
		String weeklyHours = null;
		String role = "EMPLOYEE";

		String username = "User5";

		EmployeeRegistrationForm employeeRegistrationForm = new EmployeeRegistrationForm(lastName, firstName, password,
				confirmpassword, street, houseNumber, city, plz, salary, role, weeklyHours, username);
		assertEquals(confirmpassword, employeeRegistrationForm.getConfirmpassword());

		Employee employee = employeeManagement.addEmployee(employeeRegistrationForm);
		assertThat(employeeManagement.findByID(employee.getId()).equals(employee));
	}

	@Test
	public void employeeRoleTest() {
		String firstName = "Test";
		String lastName = "Employee1";
		String password = "Passwort";
		String confirmpassword = "Passwort";

		String street = "Alexander Platz";
		String houseNumber = "1";
		String city = "Berlin";
		String plz = "10115";

		String salary = "1500";
		String weeklyHours = "40";
		String role = "EMPLOYEE";

		String username = "User3";

		EmployeeRegistrationForm employeeRegistrationForm = new EmployeeRegistrationForm(lastName, firstName, password,
				confirmpassword, street, houseNumber, city, plz, salary, role, weeklyHours, username);

		Employee employee = employeeManagement.addEmployee(employeeRegistrationForm);
		assertEquals("EMPLOYEE", employee.getRole());
	}
}
