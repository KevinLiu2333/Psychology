package com.wonders.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.fr.ImportXml2Table;

public class ImpFrkDataJob implements Job {

	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		ImportXml2Table.execute();
		
	}
}