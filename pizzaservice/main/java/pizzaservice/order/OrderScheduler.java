package pizzaservice.order;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pizzaservice.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * A class responsible for scheduling the oven task for an {@link OrderManager} object.
 */
@Component
public class OrderScheduler {

	private ThreadPoolTaskScheduler taskScheduler;

	OrderScheduler(){
		taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(1);
		taskScheduler.initialize();
	}

	private ScheduledFuture ovenJob;

	/**
	 * Schedules the oven task for the given {@link OrderManager} to be executed every five minutes.<br>
	 * If the oven task is already scheduled, then it will be canceled and scheduled again.
	 *
	 * @param orderManager not null
	 */
	public synchronized void scheduleJob(OrderManager orderManager) {
		Assert.notNull(orderManager, "OrderManager must not be null!");

		if (ovenJob != null) {
			ovenJob.cancel(true);
		}

		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			Date currentDate = cal.getTime();

			ovenJob = taskScheduler.scheduleAtFixedRate(new ScheduledMethodRunnable(orderManager, "oven"),
					currentDate, new Configuration().ovenDelay);
		} catch (NoSuchMethodException e) {  // this cannot happen, because orderManager definitely has a method called "oven"
			e.printStackTrace();
		}
	}
}
