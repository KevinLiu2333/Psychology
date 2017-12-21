package com.wonders.ws.receive.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class TzbShenghuoCronTrigger {
	
	public CronTrigger	cronTrigger	= null;
	
	public TzbShenghuoCronTrigger() {
		String cron = PropertyUtils.getProperty("getQfbShenghuoDataTime");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();
	}
}
