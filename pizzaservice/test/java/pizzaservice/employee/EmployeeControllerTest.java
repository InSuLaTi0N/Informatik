package pizzaservice.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pizzaservice.AbstractIntegrationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class EmployeeControllerTest extends AbstractIntegrationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	EmployeeManagement employeeManagement;

	Employee employee;
	EmployeeRegistrationForm employeeRegistrationForm;

	String firstName;
	String lastName;
	String password;
	String confirmpassword;

	String street;
	String city;
	String plz;
	String houseNumber;

	String salary;
	String weeklyHours;
	String role;

	String userName;

	@BeforeEach
	public void setUp() {
		firstName = "Test";
		lastName = "Employee1";
		password = "Passwort";
		confirmpassword = "Passwort";

		street = "Alexander Platz";
		houseNumber = "1";
		city = "Berlin";
		plz = "10115";

		salary = "1500";
		weeklyHours = "40";
		role = "EMPLOYEE";

		userName = "Test Employee1";

		employeeRegistrationForm = new EmployeeRegistrationForm(lastName, firstName, password,
				confirmpassword, street, houseNumber, city, plz, salary, role, weeklyHours, userName);

		employee = employeeManagement.addEmployee(employeeRegistrationForm);
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void employeeTest() throws Exception {
		mvc.perform(get("/employee"))
				.andExpect(view().name("employee"))
				.andExpect(model().attributeExists("employeeList"));
	}


	@Test
	@WithMockUser(roles = "ADMIN")
	public void employeeAddTest() throws Exception {
		mvc.perform(get("/employeeAdd"))
				.andExpect(view().name("employeeAdd"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void registerNewEmployeeNameExistsTest() throws Exception {
		mvc.perform(post("/employeeAdd")
				.param("firstName", firstName)
				.param("lastName", lastName)
				.param("userName", "Employee2")
				.param("password", password)
				.param("confirmpassword", confirmpassword)
				.param("street", street)
				.param("houseNumber", houseNumber)
				.param("plz", plz)
				.param("city", city)
				.param("salary", salary)
				.param("role", role)
				.param("weeklyHours", weeklyHours))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/employeeAdd"))
				.andExpect(flash().attributeExists("employeeRegistrationForm"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void registerNewEmployeeNoPasswordMatchTest() throws Exception {
		mvc.perform(post("/employeeAdd")
				.param("firstName", firstName)
				.param("lastName", lastName)
				.param("userName", userName)
				.param("password", password + "123")
				.param("confirmpassword", confirmpassword)
				.param("street", street)
				.param("houseNumber", houseNumber)
				.param("plz", plz)
				.param("city", city)
				.param("salary", salary)
				.param("role", role)
				.param("weeklyHours", weeklyHours))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/employeeAdd"))
				.andExpect(flash().attributeExists("employeeRegistrationForm"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void registerNewEmployeeWrongRoleTest() throws Exception {
		mvc.perform(post("/employeeAdd")
				.param("firstName", firstName)
				.param("lastName", lastName)
				.param("userName", userName)
				.param("password", password)
				.param("confirmpassword", confirmpassword)
				.param("street", street)
				.param("houseNumber", houseNumber)
				.param("plz", plz)
				.param("city", city)
				.param("salary", salary)
				.param("role", role + " MASTER")
				.param("weeklyHours", weeklyHours))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/employeeAdd"))
				.andExpect(flash().attributeExists("employeeRegistrationForm"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void registerNewEmployeeSuccessTest() throws Exception {
		mvc.perform(post("/employeeAdd")
				.param("firstName", firstName)
				.param("lastName", lastName)
				.param("userName", userName + "x")
				.param("password", password)
				.param("confirmpassword", confirmpassword)
				.param("street", street)
				.param("houseNumber", houseNumber)
				.param("plz", plz)
				.param("city", city)
				.param("salary", salary)
				.param("role", role)
				.param("weeklyHours", weeklyHours))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/employee"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void employeeUpdateTest() throws Exception {
		mvc.perform(post("/employeeManagerUpdateEmployee/{employeeId}", employee.getId())
				.param("firstName", firstName)
				.param("lastName", lastName)
				.param("userName", userName)
				.param("password", password)
				.param("confirmpassword", confirmpassword)
				.param("street", street)
				.param("houseNumber", houseNumber)
				.param("plz", plz)
				.param("city", city)
				.param("salary", salary)
				.param("role", role)
				.param("weeklyHours", weeklyHours))
				.andExpect(status().isOk())
				.andExpect(view().name("employeeUpdate"))
				.andExpect(model().attributeExists("employeeRegistrationForm"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void updateEmployeeTest() throws Exception {
		mvc.perform(post("/employeeUpdate/{employeeId}", employee.getId())
				.param("firstName", firstName + "x")
				.param("lastName", lastName)
				.param("userName", userName)
				.param("password", password)
				.param("confirmpassword", confirmpassword)
				.param("street", street)
				.param("houseNumber", houseNumber)
				.param("plz", plz)
				.param("city", city)
				.param("salary", salary)
				.param("role", role)
				.param("weeklyHours", weeklyHours))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/employee"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void deleteEmployeeTest() throws Exception {
		mvc.perform(post("/employeeManagerDeleteEmployee/{employeeId}", employee.getId()))
				.andExpect(status().isOk())
				.andExpect(view().name("employee"))
				.andExpect(model().attributeExists("employeeList"));
	}
}
