package pizzaservice.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pizzaservice.AbstractIntegrationTests;

import static org.junit.Assert.*;

public class CustomerManagementTest extends AbstractIntegrationTests {

	@Autowired
	CustomerManagement customerManagement;

	@Test
	public void checkIfCustomerIsAdded() {
		String firstName = "Test";
		String lastName = "Customer 1";
		String telephoneNumber = "49333 333333";

		CustomerRegistrationForm customerRegistrationForm = new CustomerRegistrationForm(firstName, lastName,
				"Unter den Linden", "Berlin", "10115", "1", telephoneNumber);

		Customer customer = customerManagement.addCustomer(customerRegistrationForm, 123456);
		assertEquals(customer, customerManagement.findByID(customer.getId()));
	}

	@Test
	public void updateCustomerTest() {
		String firstName = "Test";
		String lastName = "Customer 1";
		String telephoneNumber = "49333 333333";

		CustomerRegistrationForm customerRegistrationForm = new CustomerRegistrationForm(firstName, lastName,
				"Unter den Linden", "Berlin", "10115", "1", telephoneNumber);

		Customer customer = customerManagement.addCustomer(customerRegistrationForm, 123456);

		firstName = "Test_updated";
		lastName = "Customer 1_updated";
		telephoneNumber = "49333 333333_updated";

		CustomerRegistrationForm updatedCustomerRegistrationForm = new CustomerRegistrationForm(firstName, lastName,
				"Unter den Linden", "Berlin", "10116", "5", telephoneNumber);

		Customer updatedCustomer = customerManagement.updateCustomer(customer, updatedCustomerRegistrationForm);
		assertEquals(updatedCustomer, customerManagement.findByID(updatedCustomer.getId()));
	}

	@Test
	public void findAndCheckCustomerTest() {
		String firstName = "Test";
		String lastName = "Customer 1";
		String telephoneNumber = "49333333333";

		CustomerRegistrationForm customerRegistrationForm = new CustomerRegistrationForm(firstName, lastName,
				"Unter den Linden", "Berlin", "10116", "5", telephoneNumber);

		Customer customer = customerManagement.addCustomer(customerRegistrationForm, 123456);

		assertEquals(customer, customerManagement.findOne("49333333333"));
		assertFalse(customerManagement.checkUser("49333333333"));
		assertTrue(customerManagement.checkUser("abcdefgh"));
	}

	@Test
	public void tanCreationTest() {
		for (int i = 0; i < 100; i++) {
			assertTrue(customerManagement.createTan() < 1000000);
			assertTrue(customerManagement.createTan() > 100000);
		}
	}

	@Test
	public void diningSetLogicTest(){
		String firstName = "Test";
		String lastName = "Customer 1";
		String telephoneNumber = "49333333333";

		CustomerRegistrationForm customerRegistrationForm = new CustomerRegistrationForm(firstName, lastName,
				"Unter den Linden", "Berlin", "10116", "5", telephoneNumber);

		Customer customer = customerManagement.addCustomer(customerRegistrationForm, 123456);
		assertTrue(customerManagement.findByID(customer.getId()).getDiningSetList().isEmpty());

		customerManagement.addDiningSet(customer,2);
		assertFalse(customerManagement.findByID(customer.getId()).getDiningSetList().isEmpty());

		int	returnedSet = customerManagement.deleteDiningSets(customerManagement.findByID(customer.getId()),3);

		assertTrue(returnedSet == 2);
		assertTrue(customerManagement.findByID(customer.getId()).getDiningSetList().isEmpty());
	}
}
