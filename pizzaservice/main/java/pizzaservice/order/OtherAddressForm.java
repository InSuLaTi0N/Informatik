package pizzaservice.order;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class OtherAddressForm {

	@Pattern(regexp="^[A-Z].*", message="{RegistrationForm.street.Pattern}")
	private final String street;

	@Min(1)
	private final int houseNumber;

	@Pattern(regexp="^[A-Z].*", message="{RegistrationForm.city.Pattern}")
	private final String city;

	@Pattern(regexp="^[0-9]{5}$", message="{RegistrationForm.plz.Pattern}")
	private final String plz;

	public OtherAddressForm(String street, String houseNumber, String city, String plz) {
		this.plz = plz.trim();
		this.city = city.trim();
		this.street = street.trim();

		if (houseNumber == null || houseNumber.equals("")) {
			houseNumber = "1";
		} else {
			houseNumber = houseNumber.replaceAll("[^0-9]", "");
		}
		this.houseNumber = Integer.parseInt(houseNumber);
	}

	public String getStreet() {
		return street;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public String getCity() {
		return city;
	}

	public String getPlz() {
		return plz;
	}
}
