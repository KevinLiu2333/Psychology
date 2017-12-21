package com.wonders.api.task.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class ApiCronTrigger {
	public CronTrigger	cronTrigger	= null;

	public ApiCronTrigger() {
		// 获取读取xml到数据库的时间
		String cron = PropertyUtils.getProperty("ApiWarning");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();
	}
	
}
