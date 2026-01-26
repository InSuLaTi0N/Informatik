package pizzaservice.branch;

import org.javamoney.moneta.Money;
import org.salespointframework.accountancy.Accountancy;
import org.salespointframework.accountancy.AccountancyEntry;
import org.salespointframework.time.Interval;
import org.springframework.data.util.Streamable;
import org.springframework.util.Assert;

import javax.money.MonetaryAmount;
import java.time.Duration;
import java.time.LocalDate;

import static org.salespointframework.core.Currencies.EURO;

/**
 * An invoice that provides the accountancy information for a period between two dates.
 *
 * <br><br>
 * It provides the following information:<br>
 * - a Streamable consisting of {@link AccountancyEntry} objects<br>
 * - the total amount of money generated <br>
 *
 * @see <a href="https://st.inf.tu-dresden.de/SalesPoint/api/org/salespointframework/accountancy/AccountancyEntry.html">AccountancyEntry</a>
 */
public class Invoice {

	private Streamable<AccountancyEntry> entries;
	private MonetaryAmount total;
	private LocalDate startDate;
	private LocalDate endDate;

	/**
	 * Creates an {@link Invoice} object for the given time frame.
	 * The startDate and endDate can be the same date, which means the {@link Invoice} will hold information for one day in time.
	 *
	 * @param accountancy not null
	 * @param startDate not null
	 * @param endDate not null, cannot be before startDate
	 */
	public Invoice(Accountancy accountancy, LocalDate startDate, LocalDate endDate){
		Assert.notNull(accountancy, "Accountancy must not be null!");
		Assert.notNull(startDate, "Start date must not be null!");
		Assert.notNull(endDate, "End date must not be null!");
		Assert.isTrue(!endDate.isBefore(startDate), "End date must not be before the start date!");

		this.startDate = startDate;
		this.endDate = endDate;

		Duration duration = Duration.between(startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());
		Interval interval = Interval.from(startDate.atStartOfDay()).to(endDate.plusDays(1).atStartOfDay());

		this.entries = accountancy.find(interval);

		this.total = Money.of(0, EURO);
		for(Interval i : accountancy.salesVolume(interval, duration).keySet()){
			total = total.add(accountancy.salesVolume(interval, duration).get(i));
		}
	}

	public Streamable<AccountancyEntry> getEntries() {
		return entries;
	}

	public MonetaryAmount getTotal() {
		return total;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}
}
