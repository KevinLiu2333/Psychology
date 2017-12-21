package jobs;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/25
 * Time: 15:31
 */
public class RAMQuartz {
    private static Logger _log = LoggerFactory.getLogger(RAMQuartz.class);

    public static void main(String[] args) throws SchedulerException {
//        创建sheduler的工厂
        SchedulerFactory sf = new StdSchedulerFactory();
//        从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();

//        创建JobDetail
        JobDetail jb = JobBuilder.newJob(RAMJob.class).withDescription("this is a ram job").withIdentity("ramJob", "ramGroup").build();

//        任务运行的时间,SimpleSchedule类型触发器有效
        long time = System.currentTimeMillis() + 3 * 1000L;//3秒后启动任务
        Date statTime = new Date(time);

//        创建Trigger
//        使用SimpleScheduleBuilder或者CronScheduleBuilder
        Trigger t = TriggerBuilder.newTrigger().withDescription("").startAt(statTime).withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")).build();//两秒一次
//        注册任务和定时器
        scheduler.scheduleJob(jb, t);
//        启动调度器
        scheduler.start();
        _log.info("启动时间: " + new Date());
    }
}







