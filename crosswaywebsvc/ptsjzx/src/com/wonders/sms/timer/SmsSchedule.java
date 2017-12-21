package com.wonders.sms.timer;

import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

@IocBean
public class SmsSchedule {
	SchedulerFactory sf1 = null;
	Scheduler sched1 = null;
	JobDetail job1 = null;
	SchedulerFactory sf2 = null; 
	Scheduler sched2 = null;
	JobDetail job2 = null;

	/**
	 * 启动定时器.
	 */
	public void startSchedule() {
		sf1 = new StdSchedulerFactory();
		sf2 = new StdSchedulerFactory();
		try {
			sched1 = sf1.getScheduler();
			sched2 = sf2.getScheduler();
			job1 = JobBuilder.newJob(SendSmsJob.class).build();
			job2 = JobBuilder.newJob(SendSmsJob.class).build();
			CronTrigger cTrigger1 = new SmsCronTrigger().cronTrigger1;
			CronTrigger cTrigger2 = new SmsCronTrigger().cronTrigger2;
			sched1.scheduleJob(job1, cTrigger1);
			sched2.scheduleJob(job2, cTrigger2);
			sched1.start();
			sched2.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 停止定时器.
	 */
	public void shutdownSchedule() {
		if (null != sched1) {
			try {
				sched1.shutdown();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		
		if(null != sched2){
			try {
				sched2.shutdown();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}

}
