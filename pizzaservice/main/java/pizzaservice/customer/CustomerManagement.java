package pizzaservice.customer;

import org.salespointframework.useraccount.Password;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManagement;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pizzaservice.Configuration;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * this class represents the main logic for the Customer DataBase
 * its managing the Customers and
 * its using the {@link UserAccountManagement} to manage the {@link UserAccount},
 * wich are linked to the {@link Customer}
 */
@Service
@Transactional
public class CustomerManagement {

	public static final Role CUSTOMER_ROLE = Role.of("CUSTOMER");

	private final CustomerRepository customers;
	private final UserAccountManagement userAccounts;

	protected static SecureRandom random = new SecureRandom();

	CustomerManagement(CustomerRepository customers, UserAccountManagement userAccounts) {
		Assert.notNull(customers, "CustomerRepository must not be null!");
		Assert.notNull(userAccounts, "UserAccountManagement must not be null!");

		this.customers = customers;
		this.userAccounts = userAccounts;
	}

	/**
	 * Method to Create a {@link Customer} and a {@link UserAccount}
	 *
	 * @param customerRegistrationForm in the {@link CustomerRegistrationForm} are all information, wich were needed to create a Customer,
	 *                                 except the tan
	 * @param tan                      initial tan wich is given to the Constructor of the {@link Customer}
	 * @return Customer
	 * returns the created Customer
	 */
	public Customer addCustomer(CustomerRegistrationForm customerRegistrationForm, int tan) {
		var password = Password.UnencryptedPassword.of("123passwort");

		var userAccount = userAccounts.create(customerRegistrationForm.getTelephoneNumber(), password, CUSTOMER_ROLE);

		userAccount.setLastname(customerRegistrationForm.getLastName());
		userAccount.setFirstname(customerRegistrationForm.getFirstName());

		userAccount = userAccounts.save(userAccount);

		return customers.save(new Customer(userAccount, customerRegistrationForm.getStreet(),
				customerRegistrationForm.getCity(), customerRegistrationForm.getHouseNumber(),
				customerRegistrationForm.getPlz(), customerRegistrationForm.getTelephoneNumber(), tan));
	}

	/**
	 * Method to update the {@link Customer} and his linked {@link UserAccount}
	 *
	 * @param customer                 is the customer, wich should get updated
	 * @param customerRegistrationForm is the new form, wich the new attributes contains
	 * @return Customer
	 * returns the updated customer
	 */
	public Customer updateCustomer(Customer customer, CustomerRegistrationForm customerRegistrationForm) {
		customer.setCity(customerRegistrationForm.getCity());
		customer.setStreet(customerRegistrationForm.getStreet());
		customer.setHouseNumber(customerRegistrationForm.getHouseNumber());
		customer.setPlz(customerRegistrationForm.getPlz());

		customer.setTelephoneNumber(customerRegistrationForm.getTelephoneNumber());

		var userAccount = customer.getUserAccount();
		userAccount.setFirstname(customerRegistrationForm.getFirstName());
		userAccount.setLastname(customerRegistrationForm.getLastName());
		customer.setUserAccount(userAccount);
		userAccount = userAccounts.save(userAccount);

		return customers.save(customer);
	}


	public Streamable<Customer> findAll() {
		return customers.findAll();
	}

	/**
	 * Method to find one {@link Customer} in the Database with the telephoneNumber
	 *
	 * @param telephoneNumber
	 * @return the customer if he exist, otherwise it return null
	 */
	public Customer findOne(String telephoneNumber) {
		Streamable<Customer> customerStreamable = findAll();

		Map<String, Customer> customerMap = customerStreamable
				.stream()
				.collect(Collectors.toMap(customer -> customer.getTelephoneNumber(), customer -> customer));

		if (customerMap.containsKey(telephoneNumber)) {
			return customerMap.get(telephoneNumber);
		}

		return null;
	}

	/**
	 * Method Used to avoid two similar userNames
	 *
	 * @param telephoneNumber
	 * @return boolean
	 * if name used return false
	 * else return true
	 */
	public boolean checkUser(String telephoneNumber) {
		return customers.findAll().filter(Customer -> Customer.
				getUserAccount().getUsername().equals(telephoneNumber)).isEmpty();
	}

	public boolean checkUpdatedUser(Customer customer, String telephoneNumber) {
		if(customer.getTelephoneNumber().equals(telephoneNumber)){
			return true;
		} else {
			return customers.findAll().filter(Customer -> Customer.
					getUserAccount().getUsername().equals(telephoneNumber)).isEmpty();
		}
	}


	/**
	 * Method create a int between 10^5 and 10^6
	 *
	 * @return int that will be used as tan
	 */
	public int createTan() {
		int tan = random.nextInt(999999);
		while (tan == 0 || tan < 100000) {
			tan = random.nextInt(999999);
		}
		return tan;
	}

	/**
	 * Method searches {@link Customer} in the Database by id
	 *
	 * @param id individual id of the {@link Customer}
	 * @return Customer when found, otherwise null
	 */
	public Customer findByID(long id) {
		var customer = customers.findById(id).orElse(null);
		return customer;
	}

	/**
	 * Method to adding diningSets in shape of {@link Date} to the {@link Customer}
	 *
	 * @param customer
	 * @param amount   says how many Sets will get added
	 */
	public void addDiningSet(Customer customer, int amount) {
		ArrayList<Date> diningSetList = customer.getDiningSetList();

		Date date = new Date(System.currentTimeMillis());
		for (int i = 0; i < amount; i++) {
			diningSetList.add(date);
		}

		customer.setDiningSetList(diningSetList);
		customers.save(customer);
	}

	/**
	 * Method to delete one diningSet from a certain {@link Customer}
	 *
	 * @param customer Checks if the a {@link Date}, that's younger than to weeks
	 * @return when there is one, then return true
	 * otherwise false
	 */
	public boolean deleteOneDiningSet(Customer customer) {
		Configuration configuration = new Configuration();

		ArrayList<Date> diningSetList = customer.getDiningSetList();
		int length = diningSetList.toArray().length;
		Date date;

		if (diningSetList.isEmpty()) {
			return false;
		} else {
			Date currentDate = new Date(System.currentTimeMillis());

			for (int i = 0; i < length; i++) {
				date = diningSetList.get(i);

				if ((currentDate.getTime() - configuration.diningSetDeadline) < date.getTime()) {
					diningSetList.remove(date);
					customer.setDiningSetList(diningSetList);
					customers.save(customer);
					return true;
				} else {
					diningSetList.remove(date);
					i--;
				}
				if (diningSetList.isEmpty()) {
					break;
				}
			}
			customer.setDiningSetList(diningSetList);
			customers.save(customer);
			return false;
		}
	}

	public long tanValidation(int inputTan, long id) {
		Customer customer = findByID(id);
		long token = customer.tanValidation(inputTan);
		customers.save(customer);
		return token;
	}

	public int tokenValidation(long token, long id) {
		Customer customer = findByID(id);
		int tan = customer.tokenValidation(token);
		customers.save(customer);
		return tan;
	}

	/**
	 * Method to delete diningSets with the deleteOneDiningSet method
	 *
	 * @param customer
	 * @param amount   takes the amount of deletions
	 * @return how many deletions really happened as int
	 */
	public int deleteDiningSets(Customer customer, int amount) {
		//return amount of deleted diningSets
		int originalAmount = amount;

		while (0 < amount) {
			if (deleteOneDiningSet(customer)) {
				amount--;
			} else {
				return (originalAmount - amount);
			}
		}
		return originalAmount;
	}

	public int checkDiningSets(Customer customer) {
		int amount = 0;

		Configuration configuration = new Configuration();

		ArrayList<Date> diningSetList = customer.getDiningSetList();
		int length = diningSetList.toArray().length;
		Date date;

		if (diningSetList.isEmpty()) {
			return 0;
		} else {
			Date currentDate = new Date(System.currentTimeMillis());

			for (int i = 0; i < length; i++) {
				date = diningSetList.get(i);

				if ((currentDate.getTime() - configuration.diningSetDeadline) < date.getTime()) {
					amount++;

				}
			}
		}
		return amount;
	}
}
