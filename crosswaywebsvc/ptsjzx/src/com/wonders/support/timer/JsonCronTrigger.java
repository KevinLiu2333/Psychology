package com.wonders.support.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class JsonCronTrigger {
	public CronTrigger	cronTrigger	= null;

	public JsonCronTrigger() {
		// 获取读取xml到数据库的时间
		String cron = PropertyUtils.getProperty("makeJsonTime");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();

	}
}
