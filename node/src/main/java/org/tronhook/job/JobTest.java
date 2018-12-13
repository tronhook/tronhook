package org.tronhook.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class JobTest implements Job{


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("=>hello =>"+System.currentTimeMillis());
		
	}

	
}
