package com.wonders.wddc.tiles.quartz;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.wonders.wddc.tiles.tools.ClassUtil;

/**
 * 时间管理类
 */
public class QuartzManager {
	private static SchedulerFactory sf = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "wonders_group";
	private static String TRIGGER_GROUP_NAME = "wonders_trigger";
	private static QuartzManager quartzManager = null;
	
	private static String PACKNAME = "com.wonders";
	
	@SuppressWarnings("unchecked")
	public static List<Class> jobclses = ClassUtil.getpackClsimplinterface(PACKNAME, Job.class);
	
	private QuartzManager() {

	}
	public static QuartzManager getInstance() {
		if (quartzManager == null) {
			synchronized (QuartzManager.class) {
				if (quartzManager == null) {
					quartzManager = new QuartzManager();
				}
			}
		}
		return quartzManager;
	}

	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobName
	 *            任务名
	 * @param job
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public <T extends Job> void addJob(String jobName, Class<T> job, String time)
			throws SchedulerException, ParseException {
		Scheduler sched = sf.getScheduler();
		// 任务名，任务组，任务执行类
		JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, JOB_GROUP_NAME).build();
		// 触发器  
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        // 触发器名,触发器组  
        triggerBuilder.withIdentity(jobName, TRIGGER_GROUP_NAME);
        triggerBuilder.startNow();
        // 触发器时间设定  
        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(time));
        // 创建Trigger对象
        CronTrigger trigger = (CronTrigger) triggerBuilder.build();
        // 调度容器设置JobDetail和Trigger
        sched.scheduleJob(jobDetail, trigger);  
		// 启动
		if (!sched.isShutdown())
			sched.start();
	}

	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @param time
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public <T extends Job> void modifyJobTime(String jobName, String time)
			throws SchedulerException, ParseException {
		Scheduler sched = sf.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
		CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);  
		if (trigger == null) {
			return;
		}
		String oldTime = trigger.getCronExpression();
		if (!oldTime.equalsIgnoreCase(time)) { 
			/** 方式一 ：调用 rescheduleJob 开始 */
			// 触发器  
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组  
            triggerBuilder.withIdentity(jobName, TRIGGER_GROUP_NAME);
            triggerBuilder.startNow();
            // 触发器时间设定  
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(time));
            // 创建Trigger对象
            trigger = (CronTrigger) triggerBuilder.build();
            // 方式一 ：修改一个任务的触发时间
            sched.rescheduleJob(triggerKey, trigger);
		}
	}

	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @throws SchedulerException
	 */
	public void removeJob(String jobName) throws SchedulerException {
		Scheduler sched = sf.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
		sched.pauseTrigger(triggerKey);// 停止触发器  
		sched.unscheduleJob(triggerKey);// 移除触发器  
		sched.deleteJob(JobKey.jobKey(jobName, JOB_GROUP_NAME));// 删除任务  
	}
	public <T extends Job> void addJob(String jobName, Class<T> job, String time,Map<String,Object> param)
		throws SchedulerException, ParseException {
			Scheduler sched = sf.getScheduler();
			// 任务名，任务组，任务执行类
			JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, JOB_GROUP_NAME).build();
			for(String key:param.keySet()){
				jobDetail.getJobDataMap().put(key, param.get(key));
			}
			// 触发器  
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
			// 触发器名,触发器组  
			triggerBuilder.withIdentity(jobName, TRIGGER_GROUP_NAME);
			triggerBuilder.startNow();
			// 触发器时间设定  
			triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(time));
			// 创建Trigger对象
			CronTrigger trigger = (CronTrigger) triggerBuilder.build();
			// 调度容器设置JobDetail和Trigger
			sched.scheduleJob(jobDetail, trigger);  
			// 启动
			if (!sched.isShutdown())
				sched.start();
			}
	}
