package pizzaservice.branch;

import org.javamoney.moneta.Money;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.salespointframework.accountancy.Accountancy;
import org.salespointframework.accountancy.AccountancyEntry;
import org.springframework.beans.factory.annotation.Autowired;
import pizzaservice.AbstractIntegrationTests;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.salespointframework.core.Currencies.EURO;

public class InvoiceTest extends AbstractIntegrationTests {

	@Autowired
	Accountancy accountancy;

	@Test
	public void invoiceTest(){
		MonetaryAmount total = Money.of(1300, EURO);
		LocalDate endDate = LocalDate.now().plusWeeks(1);
		LocalDate startDate = endDate.minusWeeks(1);
		Invoice invoice = new Invoice(accountancy,startDate,endDate);
		accountancy.add(new AccountancyEntry(invoice.getTotal().negate(), "to 0 EURO"));
		accountancy.add(new AccountancyEntry(total, "test"));
		invoice = new Invoice(accountancy,startDate,endDate);

		assertTrue(total.isEqualTo(invoice.getTotal()));
		Assert.assertNotNull(accountancy);
		Assert.assertNotNull(startDate);
		Assert.assertNotNull(endDate);
		Assert.assertFalse(endDate.isBefore(startDate));
	}
}
