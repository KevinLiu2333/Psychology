package com.wonders.ws.receive.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class BuildingCronTrigger {
	
	public CronTrigger	cronTrigger	= null;
	
	public BuildingCronTrigger() {
		String cron = PropertyUtils.getProperty("getBuildingDataTime");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();
	}
}
