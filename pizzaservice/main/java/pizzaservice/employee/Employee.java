package pizzaservice.employee;

import org.javamoney.moneta.Money;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.data.util.Streamable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee is a kind of extension to the {@link UserAccount}, wich is connected with an @OneToOne
 * to the {@link UserAccount}
 */
@Entity
public class Employee {
	private @Id
	@GeneratedValue
	long id;

	private Money salary;
	private int weeklyHours;

	private String street;
	private String city;
	private int houseNumber;
	private String plz;

	@OneToOne
	private UserAccount userAccount;

	public Employee(UserAccount userAccount, Money salary, int weeklyHours, String street, String city,
					int houseNumber, String plz) {

		this.userAccount = userAccount;

		this.salary = salary;
		this.weeklyHours = weeklyHours;
		this.street = street;
		this.city = city;
		this.houseNumber = houseNumber;
		this.plz = plz;

	}

	private Employee() {
	}

	public void setWeeklyHours(int weeklyHours) {
		this.weeklyHours = weeklyHours;
	}

	public int getWeeklyHours() {
		return weeklyHours;
	}

	public long getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public Money getSalary() {
		return salary;
	}

	public void setSalary(Money salary) {
		this.salary = salary;
	}

	public String getRole() {
		Streamable<Role> roles = userAccount.getRoles();

		List<Role> role = roles
				.stream()
				.collect(Collectors.toList());

		var currentRole = role.get(0);
		return currentRole.getName();
	}
}

