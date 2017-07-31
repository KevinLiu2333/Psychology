package com.wonders.support.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class XmlCronTrigger {
	public CronTrigger	cronTrigger	= null;

	public XmlCronTrigger() {
		// 获取读取xml到数据库的时间
		String cron = PropertyUtils.getProperty("readXmlTime");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();

	}
}
