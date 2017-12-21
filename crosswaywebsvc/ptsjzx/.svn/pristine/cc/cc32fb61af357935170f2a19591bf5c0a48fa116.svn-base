package com.wonders.ws.receive.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class CommunityAcceptanceCronTrigger {
	
	public CronTrigger	cronTrigger	= null;
	
	public CommunityAcceptanceCronTrigger() {
		String cron = PropertyUtils.getProperty("getCommunityAcceptanceDataTime");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();
	}
}
