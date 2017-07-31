package com.wonders.echarts.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.wonders.util.PropertyUtils;

public class EchartsCronTrigger {
	public CronTrigger	cronTrigger	= null;

	public EchartsCronTrigger() {
		// 获取读取xml到数据库的时间
		String cron = PropertyUtils.getProperty("makeEchartsDataTime");
		CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
		cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();
	}
	
}
