package com.wonders.search.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class IndexCronTrigger {
	
	public CronTrigger	cronTrigger	= null;
	
	public IndexCronTrigger(){
		// 获取读取xml到数据库的时间
		String cron = PropertyUtils.getProperty("makeIndex");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();
	}
}
