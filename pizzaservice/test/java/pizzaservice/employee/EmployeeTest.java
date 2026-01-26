package pizzaservice.employee;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.salespointframework.useraccount.UserAccount;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeTest {

	@Mock
	UserAccount userAccount;

	@Test
	public void constructorTest() {
		Money salary = Money.of(1000, "EUR");

		Employee employee = new Employee(userAccount,salary,40,"Alexander Platz","Berlin", 1 , "10115");

		assertThat("Alexander Platz".equals(employee.getStreet()));
		assertThat("Berlin".equals(employee.getCity()));
		assertThat(1 == employee.getHouseNumber());
		assertThat("10115" == employee.getPlz());
		assertThat(salary.equals(employee.getSalary()));
		assertThat(40 == employee.getWeeklyHours());
	}
}
