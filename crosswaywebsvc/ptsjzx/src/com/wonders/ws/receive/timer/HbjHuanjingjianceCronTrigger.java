package com.wonders.ws.receive.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class HbjHuanjingjianceCronTrigger {
	
	public CronTrigger	cronTrigger	= null;
	
	public HbjHuanjingjianceCronTrigger() {
		String cron = PropertyUtils.getProperty("getHbjHuanjingjianceDataTime");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();
	}
}
