package org.hbs.edutel.sender;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Anand
 */
@Service
public class EmailSchedulerService implements Serializable
{
	private static final long	serialVersionUID	= 1958518665419239648L;

	private boolean				isRunning			= false;

	@Value("${edutel.email.delay}")
	private String				emailDelay;

	@Autowired
	MessageEmailSender			emailSender;

	@Scheduled(fixedDelayString = "${edutel.email.delay}", initialDelayString = "30000")
	public void scheduleEmail()
	{
		try
		{
			if (!isRunning)
			{
				System.out.println(">>>" + new Date() + ">>>scheduleEmail with milliseconds>>>>>>> " + emailDelay);
				isRunning = true;
				ExecutorService executor = Executors.newFixedThreadPool(1);

				executor.execute(new Runnable() {

					@Override
					public void run()
					{
						try
						{
							System.out.println("Started at " + new Date());
							emailSender.sendMessageToUserByMedia();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
						finally
						{
							System.out.println("Finished By " + new Date());
						}
					}
				});

				executor.shutdown();
				while ( !executor.isTerminated() )
					;
				System.out.println("Finished all threads");
			}
			else
			{
				System.out.println("New Schedule Time Reached, But Earlier Still Running...");
			}
		}
		finally
		{
			isRunning = false;
		}

	}

}
