package com.wonders.ws.receive.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class MzjYiliaoCronTrigger {
	
	public CronTrigger	cronTrigger	= null;
	
	public MzjYiliaoCronTrigger() {
		String cron = PropertyUtils.getProperty("getMzjYiliaoDataTime");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();
	}
}
