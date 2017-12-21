package com.wonders.sms.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class SmsCronTrigger {
	public CronTrigger cronTrigger1 = null;
	public CronTrigger cronTrigger2 = null; 

	public SmsCronTrigger() {
		// 获取发送短信时间
		String cron1 = PropertyUtils.getProperty("sendSmsTime1");
		String cron2 = PropertyUtils.getProperty("sendSmsTime2");
		CronScheduleBuilder schedule1 = CronScheduleBuilder.cronSchedule(cron1);
		CronScheduleBuilder schedule2 = CronScheduleBuilder.cronSchedule(cron2);
		cronTrigger1 = TriggerBuilder.newTrigger().withSchedule(schedule1).build();
		cronTrigger2 = TriggerBuilder.newTrigger().withSchedule(schedule2).build();
	}

}
