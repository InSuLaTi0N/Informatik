package pizzaservice.customer;

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
public class CustomerControllerTest extends AbstractIntegrationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	CustomerManagement customerManagement;

	Customer customer;
	CustomerRegistrationForm customerRegistrationForm;

	String firstName;
	String lastName;
	String street;
	String houseNumber;
	String plz;
	String city;
	String telephoneNumber;

	@BeforeEach
	public void setUp(){
		firstName = "Test";
		lastName = "Customer 1";

		street = "Test Street";
		houseNumber = "91";
		plz="11111";
		city="Test City";

		telephoneNumber = "4933";

		customerRegistrationForm = new CustomerRegistrationForm(firstName,
				lastName,  street, city, plz, houseNumber, telephoneNumber);

		customer = customerManagement.addCustomer(customerRegistrationForm, 123456);
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void customerManagerTest() throws Exception {
		mvc.perform(get("/customer"))
				.andExpect(status().isOk())
				.andExpect(view().name("customer"))
				.andExpect(model().attributeExists("customerList"));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void customerUpdateTest() throws Exception {
		mvc.perform(post("/customerUpdate/{customerId}", customer.getId())
				.param("firstName", firstName)
				.param("lastName", lastName)
				.param("street", street)
				.param("houseNumber", houseNumber)
				.param("plz", plz)
				.param("city", city)
				.param("telephoneNumber", telephoneNumber)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("customerUpdate"))
				.andExpect(model().attributeExists("customer", "customerRegistrationForm"));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void updateCustomerTest() throws Exception {
		mvc.perform(post("/customer/updateCustomer/{customerId}", customer.getId())
				.param("firstName", firstName)
				.param("lastName", lastName)
				.param("street", street)
				.param("houseNumber", houseNumber)
				.param("plz", plz)
				.param("city", city + "s")
				.param("telephoneNumber", telephoneNumber)
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/customer"));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void customerRegisterTest() throws Exception {
		mvc.perform(get("/customerRegister")
				.param("firstName", firstName)
				.param("lastName", lastName)
				.param("street", street)
				.param("houseNumber", houseNumber)
				.param("plz", plz)
				.param("city", city)
				.param("telephoneNumber", telephoneNumber)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("customerRegister"))
				.andExpect(model().attributeExists("customerRegistrationForm"));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void registerNewCustomerAlreadyExistsTest() throws Exception {
		mvc.perform(post("/customer/customerRegister")
				.param("firstName", firstName)
				.param("lastName", lastName)
				.param("street", street)
				.param("houseNumber", houseNumber)
				.param("plz", plz)
				.param("city", city)
				.param("telephoneNumber", telephoneNumber)
				.characterEncoding("utf-8"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/customerRegister"))
				.andExpect(flash().attributeExists("customerRegistrationForm"));
	}

	@Test
	@WithMockUser(roles="ADMIN")
	public void registerNewCustomerSuccessTest() throws Exception {
		mvc.perform(post("/customer/customerRegister")
				.param("firstName", firstName + "x")
				.param("lastName", lastName)
				.param("street", street)
				.param("houseNumber", houseNumber)
				.param("plz", plz)
				.param("city", city)
				.param("telephoneNumber", "0300254")
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("customerWelcome"));
	}
}
