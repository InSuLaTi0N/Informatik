package pizzaservice.customer;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.salespointframework.useraccount.UserAccount;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {
	@Mock
	UserAccount userAccount;

	@Test
	public void customerTest(){
		String telephoneNumber = "56345674574";
		int tan = 34;
		String address = "Gret-palucca";
		userAccount = Mockito.mock(UserAccount.class);

		var customer = new Customer(userAccount, "Münchner Str." , "Dresden" , 1 ,"01187", telephoneNumber, tan);
		assertThat(address.equals(customer.getAddress()));
		assertThat(telephoneNumber.equals(customer.getTelephoneNumber()));
		assertThat(userAccount==customer.getUserAccount());

		customer.setStreet("Nürnberger Platz");
		customer.setHouseNumber(15);
		customer.setCity("Dresden");
		customer.setPlz("1189");

		String telephoneNumb = "fg4gf45g";

		customer.setTelephoneNumber(telephoneNumb);

		assertThat("Nürnberger Platz".equals(customer.getStreet()));
		assertThat(15 == customer.getHouseNumber());
		assertThat("Dresden".equals(customer.getCity()));
		assertThat("1187" == customer.getPlz());
		assertThat(telephoneNumb.equals(customer.getTelephoneNumber()));
	}

	@Test
	public void tanTokenSystemTest(){
		String telephoneNumber = "56345674574";
		int tan = 123456;
		String address = "Gret-palucca";
		userAccount = Mockito.mock(UserAccount.class);

		var customer = new Customer(userAccount, "Münchner Str." , "Dresden" , 1 ,  "1187",telephoneNumber, tan);

		long wrongToken = customer.tanValidation(1);
		assertThat(wrongToken== 0);

		int wrongTan = customer.tokenValidation(wrongToken);
		assertThat(wrongTan == 0);

		long token = customer.tanValidation(tan);
		assertThat(token != 0);

		wrongTan = customer.tokenValidation(wrongToken);
		assertThat(wrongTan == 0);

		int newTan = customer.tokenValidation(token);
		assertThat(newTan != 0);
	}
}
