package pizzaservice;

public class Configuration {
	public long ovenDelay = 20000;
	public long diningSetDeadline = 900000;
	/*
	 * the cron expression for the payroll scheduling
	 * can only be externalized into a property file
	 * which is why it is in application.properties
	 */
	public long deletionTime = 90000;
}
