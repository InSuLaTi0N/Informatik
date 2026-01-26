package pizzaservice.employee;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

class EmployeeRegistrationForm {

	@NotBlank(message = "{RegistrationForm.lastName.NotBlank}")
	private final String lastName;

	@NotBlank(message = "{RegistrationForm.firstName.NotBlank}")
	private final String firstName;

	@NotBlank(message = "{RegistrationForm.password.NotBlank}")
	private final String password;

	@NotBlank(message = "{RegistrationForm.password.NotBlank}")
	private final String confirmpassword;

	@DecimalMin("0.01")
	private final double salary;

	@NotBlank(message = "{RegistrationForm.role.NotBlank}")
	private final String role;

	@Min(1)
	private final int weeklyHours;

	@Pattern(regexp="^[A-Z].*", message="{RegistrationForm.street.Pattern}")
	private final String street;

	@Pattern(regexp="^[A-Z].*", message="{RegistrationForm.city.Pattern}")
	private final String city;

	@NotBlank(message = "{RegistrationForm.userName.NotBlank}")
	private final String userName;

	@Pattern(regexp="^[0-9]{5}$", message="{RegistrationForm.plz.Pattern}")
	private final String plz;

	@Min(1)
	private final int houseNumber;

	public EmployeeRegistrationForm(String lastName, String firstName, String password,
									String confirmpassword, String street,
									String houseNumber, String city, String plz,
									String salary, String role, String weeklyHours,
									String userName) {
		this.lastName = lastName.trim();
		this.firstName = firstName.trim();
		this.password = password.trim();
		this.confirmpassword = confirmpassword.trim();
		this.userName = userName.trim();
		this.plz = plz.trim();
		this.city = city.trim();
		this.street = street.trim();
		this.role = role;

		if (salary == null || salary.equals("")) {
			salary = "0";
		} else {
			salary = salary.replaceAll("[^0-9.]", "");
		}
		this.salary = Double.parseDouble(salary);

		if (weeklyHours == null || weeklyHours.equals("")) {
			weeklyHours = "0";
		} else {
			weeklyHours = weeklyHours.replaceAll("[^0-9]", "");
		}
		this.weeklyHours = Integer.parseInt(weeklyHours);

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

	public String getPassword() {
		return password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public double getSalary() {
		return salary;
	}

	public String getRole() {
		return role;
	}

	public int getWeeklyHours() {
		return weeklyHours;
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

	public String getUserName() {
		return userName;
	}
}
