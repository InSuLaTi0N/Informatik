package pizzaservice.customer;

import org.salespointframework.useraccount.UserAccount;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;

/**
 * Customer is a kind of extension to the {@link UserAccount}, wich is connected with an @OneToOne
 * to the {@link UserAccount}
 */
@Entity
public class Customer {
	private @Id @GeneratedValue long id;

	private String street;
	private String city;
	private int houseNumber;
	private String plz;

	private String telephoneNumber;
	private int tan;
	private boolean tanUsed;
	private long currentToken = Math.abs(random.nextLong());

	private ArrayList<Date> diningSetList = new ArrayList<Date>();
	protected static SecureRandom random = new SecureRandom();

	@OneToOne
	private UserAccount userAccount;

	public Customer(UserAccount userAccount, String street, String city, int houseNumber, String plz,
					String telephoneNumber, int tan) {
		this.street = street;
		this.city = city;
		this.houseNumber = houseNumber;
		this.plz = plz;

		this.telephoneNumber = telephoneNumber;
		this.tan = tan;
		this.userAccount = userAccount;
		this.tanUsed = false;
	}

	private Customer() {
	}

	//!!!security breach!!! explicit Customer wish
	public int getTan() {
		return tan;
	}

	public long getId() {
		return id;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public String getTelephoneNumber(){return telephoneNumber;}

	public void setTelephoneNumber(String telephoneNumber){this.telephoneNumber = telephoneNumber;}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
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

	public void setPlz(String  plz) {
		this.plz = plz;
	}

	public String getAddress(){
		String address = street + " " + houseNumber + ", " + plz + " " + city;
		return address;
	}

	/**
	 * @param inputTan
	 * this Method checks, if the tan matches with the inputTan
	 *
	 * @return long
	 * if the inputTan is correct, then it return a token
	 * other wise its returning 0
	 */
	public long tanValidation(int inputTan){
		if(inputTan == tan){
			tanUsed = true;
			currentToken = Math.abs(random.nextLong());
			return currentToken;
		}
		return 0;
	}

	/**
	 * this Method checks, if the entered token matches with the currentToken
	 * @param token
	 *
	 * @return int
	 * if the the entered token equals the currentToken
	 * and tanUsed ist true (that means that a token was created with the Tan),
	 * then it generates a ne Tan and returns it,
	 * otherwise it returns 0
	 */
	public int tokenValidation(long token){
		if(token == currentToken && tanUsed){

			tan = random.nextInt(999999);
			while(tan == 0 || tan < 100000){
				tan = random.nextInt(999999);
			}
			currentToken = Math.abs(random.nextLong());
			tanUsed = false;

			return tan;
		} else {
			return 0;
		}
	}

	public ArrayList<Date> getDiningSetList(){return diningSetList;}

	public void setDiningSetList(ArrayList<Date> diningSetList) {this.diningSetList = diningSetList;}
}
