package com.wonders.ws.receive.timer;

import com.wonders.util.PropertyUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/1/16
 * Time: 16:09
 */
public class KechuangjiaCronTrigger {

    public CronTrigger cronTrigger	= null;

    public KechuangjiaCronTrigger() {
        String cron = PropertyUtils.getProperty("getKechuangjiaDataTime");
        CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(cron);
        cronTrigger = TriggerBuilder.newTrigger().withSchedule(schedule).build();
    }
}
