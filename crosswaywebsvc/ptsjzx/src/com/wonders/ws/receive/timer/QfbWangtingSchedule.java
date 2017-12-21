package com.wonders.ws.receive.timer;

import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

@IocBean
public class QfbWangtingSchedule {
	SchedulerFactory sf = null;
	Scheduler sched = null;
	JobDetail job = null;
	
	/**
	 * 启动定时器
	 */
	public void startSchedule(){
		sf = new StdSchedulerFactory();
		try {
			sched=sf.getScheduler();
			job=JobBuilder.newJob(QfbWangtingJob.class).build();
			CronTrigger cTrigger=new QfbWangtingCronTrigger().cronTrigger;
			sched.scheduleJob(job, cTrigger);
			sched.start();
		}catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 停止定时器
	 */
	public void shutdownSchedule() {
		if (null != sched) {
			try {
				sched.shutdown();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}
}
