package com.wonders.ws.receive.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class QfbWangtingCronTrigger {
	
	public CronTrigger	cronTrigger	= null;
	
	public QfbWangtingCronTrigger() {
		String cron = PropertyUtils.getProperty("getQfbWangtingDataTime");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();
	}
}
