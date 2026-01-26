package pizzaservice.order;

import javax.validation.constraints.NotBlank;

/**
 * A form object for validation of customer credentials.
 */
public class AuthenticationForm {

	@NotBlank(message = "{AuthenticationForm.telephoneNumber.NotBlank}")
	private final String telephoneNumber;

	@NotBlank(message = "{AuthenticationForm.tan.NotBlank}")
	private final String tan;

	public AuthenticationForm(String telephoneNumber, String tan) {
		this.telephoneNumber = telephoneNumber.trim();
		if(tan == null){
			tan = "irrelevant";  // when used to return dining sets
		}
		this.tan = tan.trim();
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public String getTan() {
		return tan;
	}
}
