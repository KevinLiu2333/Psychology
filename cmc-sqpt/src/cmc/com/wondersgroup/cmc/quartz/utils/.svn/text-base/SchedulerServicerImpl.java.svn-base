package com.wondersgroup.cmc.quartz.utils;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;

public class SchedulerServicerImpl implements SchedulerUtils{
	public Scheduler scheduler;
	private JobDetail jobDetail;

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}
	
	/**
	 * 根据 Quartz Cron Expression 调度任务
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	*/
	public void schedule(String cronExpression) throws ParseException,SchedulerException {
		this.schedule(null, cronExpression);
	}
	
	/**
	 * 根据 Quartz Cron Expression 调度任务
	 * @param name Quartz CronTrigger名称
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 * @throws SchedulerException 
	*/
	public void schedule(String name, String cronExpression) throws ParseException, SchedulerException {
		this.schedule(name, new CronExpression(cronExpression));
	}

	/**
	 * 根据 Quartz Cron Expression 调度任务
	 * @param cronExpression Quartz CronExpression
	 * @throws SchedulerException
	*/
	public void schedule(CronExpression cronExpression) throws SchedulerException {
		this.schedule(null, cronExpression);
	}

	/**
	 * 根据 Quartz Cron Expression 调度任务
	 * @param name Quartz CronTrigger名称
	 * @param cronExpression Quartz CronExpression
	*/
	public void schedule(String name, CronExpression cronExpression) throws SchedulerException {
		this.scheduler.addJob(jobDetail, true);
		CronTrigger cronTrigger = new CronTrigger(name, Scheduler.DEFAULT_GROUP, jobDetail.getName(),
					Scheduler.DEFAULT_GROUP);
		cronTrigger.setCronExpression(cronExpression);
		cronTrigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
		this.scheduler.scheduleJob(cronTrigger);
		this.scheduler.rescheduleJob(name, Scheduler.DEFAULT_GROUP, cronTrigger);
	}

	/**
	 * 在startTime时执行调度一次
	 * @param startTime 调度开始时间
	*/
	public void schedule(Date startTime) throws SchedulerException {
		this.schedule(startTime, null);
	}

	/**
	 * 在startTime时执行调度一次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	*/
	public void schedule(String name, Date startTime) throws SchedulerException {
		this.schedule(name, startTime, null);
	}

	/**
	 * 在startTime时执行调度，endTime结束执行调度
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	*/
	public void schedule(Date startTime, Date endTime) throws SchedulerException {
		this.schedule(startTime, endTime, 0);
	}

	/**
	 * 在startTime时执行调度，endTime结束执行调度
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	*/
	public void schedule(String name, Date startTime, Date endTime) throws SchedulerException {
		this.schedule(name, startTime, endTime, 0);
	}

	/**
	 * 在startTime时执行调度，endTime结束执行调度，重复执行repeatCount次
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	*/
	public void schedule(Date startTime, Date endTime, int repeatCount) throws SchedulerException {
		this.schedule(null, startTime, endTime, repeatCount);
	}

	/**
	 * 在startTime时执行调度，endTime结束执行调度，重复执行repeatCount次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @throws SchedulerException 
	 */
	public void schedule(String name, Date startTime, Date endTime, int repeatCount) throws SchedulerException {
		this.schedule(name, startTime, endTime, repeatCount, 0L);
	}

	/**
	 * 在startTime时执行调度，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param repeatInterval 执行时间隔间
	 * @throws SchedulerException 
	 */
	public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval) throws SchedulerException {
		this.schedule(null, startTime, endTime, repeatCount, repeatInterval);
	}

	/**
	 * 在startTime时执行调度，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param repeatInterval 执行时间隔间 单位毫秒
	 * @throws SchedulerException 
	*/
	public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval) throws SchedulerException{
		this.scheduler.addJob(jobDetail, true);
		Date now = new Date();
		if(startTime.before(now)){
			startTime = now;
		}
		SimpleTrigger simpleTrigger = new SimpleTrigger(name, Scheduler.DEFAULT_GROUP, jobDetail.getName(),Scheduler.DEFAULT_GROUP, startTime, endTime, repeatCount, repeatInterval);
		simpleTrigger.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT);
		this.scheduler.scheduleJob(simpleTrigger);
		this.scheduler.rescheduleJob(name, Scheduler.DEFAULT_GROUP, simpleTrigger);
	}

	/**
	 * 功能描述：暂停任务 
	 * @param triggerName Quartz SimpleTrigger 名称
	 * @param group Trigger分组
	 * @throws SchedulerException 
	 */
	public void pauseTrigger(String triggerName, String group) throws SchedulerException {
		scheduler.pauseTrigger(triggerName, group);// 停止触发器
	}

	/**
	 * 功能描述：恢复调度
	 * @param triggerName Quartz SimpleTrigger 名称
	 * @param group Trigger分组
	 * @throws SchedulerException 
	*/
	public void resumeTrigger(String triggerName, String group) throws SchedulerException {
		this.scheduler.resumeTrigger(triggerName, group);// 重启触发器
	}

	/**
	 * 功能描述：移除调度 
	 * @param triggerName Quartz SimpleTrigger 名称
	 * @param group Trigger分组
	 * @return
	 * @throws SchedulerException 
	 */
	public boolean removeTrigdger(String triggerName, String group) throws SchedulerException {
		this.scheduler.pauseTrigger(triggerName, group);// 停止触发器
		return this.scheduler.unscheduleJob(triggerName, group);// 移除触发器
	}
}
