package com.wonders.ws.receive.timer;

import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/1/16
 * Time: 16:14
 */
@IocBean
public class KechuangjiaSchedule {
    SchedulerFactory sf		= null;
    Scheduler sched	= null;
    JobDetail			job		= null;

    /**
     * 启动定时器
     */
    public void startSchedule() {
        sf = new StdSchedulerFactory();
        try {
            sched = sf.getScheduler();
            job = JobBuilder.newJob(KechuangjiaJob.class).build();
            CronTrigger cTrigger = new KechuangjiaCronTrigger().cronTrigger;
            sched.scheduleJob(job, cTrigger);
            sched.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止定时器
     */
    public void shutdownSchedule() {
        if (null != sched) {
            try {
                sched.shutdown();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }
}
