package pizzaservice.customer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * a form to collect every needed Attribute to add/edit a {@link Customer}
 */
public class CustomerRegistrationForm {

	@NotBlank(message = "{RegistrationForm.firstName.NotBlank}")
	private final String firstName;

	@NotBlank(message = "{RegistrationForm.lastName.NotBlank}")
	private final String lastName;

	@Pattern(regexp="^[A-Z].*", message="{RegistrationForm.street.Pattern}")
	private final String street;

	@Pattern(regexp="^[A-Z].*", message="{RegistrationForm.city.Pattern}")
	private final String city;

	@Pattern(regexp="^[0-9]{5}$", message="{RegistrationForm.plz.Pattern}")
	private final String plz;

	@Min(1)
	private final int houseNumber;

	@NotBlank(message = "{RegistrationForm.telephoneNumber.NotBlank}")
	private final String telephoneNumber;

	public CustomerRegistrationForm(String firstName, String lastName, String street,
									String city, String plz, String houseNumber,
									String telephoneNumber) {
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
		this.street = street.trim();
		this.city = city.trim();
		this.plz = plz.trim();
		this.telephoneNumber = telephoneNumber.trim();

		if (houseNumber == null || houseNumber.equals("")) {
			houseNumber = "1";
		} else {
			houseNumber = houseNumber.replaceAll("[^0-9]", "");
		}
		this.houseNumber = Integer.parseInt(houseNumber);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getPlz() {
		return plz;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}
}
