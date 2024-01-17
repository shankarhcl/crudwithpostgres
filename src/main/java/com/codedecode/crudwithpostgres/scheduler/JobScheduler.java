package com.codedecode.crudwithpostgres.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class JobScheduler {
	
	
	@Scheduled(cron="0 */2 * * * *")
	public void jobScheduled() {
		System.out.println("Job will run in  every 2 minute");
	}

}
