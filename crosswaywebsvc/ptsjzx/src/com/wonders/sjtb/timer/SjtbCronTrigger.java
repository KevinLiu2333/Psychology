package com.wonders.sjtb.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class SjtbCronTrigger {
	
	public CronTrigger	cronTrigger	= null;
	
	public SjtbCronTrigger() {
		String cron = PropertyUtils.getProperty("extractsjtbDataTime");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();
	}
}
