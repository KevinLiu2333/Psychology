package com.wondersgroup.cmc.quartz.utils;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronExpression;
import org.quartz.SchedulerException;

public interface SchedulerUtils {
	/**
	 * 根据 Quartz Cron Expression 调度任务
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	*/
	public void schedule(String cronExpression) throws ParseException,SchedulerException;

	/**
	 * 根据 Quartz Cron Expression 调度任务
	 * @param name Quartz CronTrigger名称
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 */
	public void schedule(String name, String cronExpression) throws ParseException,SchedulerException;

	/**
	 * 根据 Quartz Cron Expression 调度任务
	 * @param cronExpression Quartz CronExpression
	 */
	public void schedule(CronExpression cronExpression) throws SchedulerException;

	/**
	 * 根据 Quartz Cron Expression 调度任务
	 * @param name Quartz CronTrigger名称
	 * @param cronExpression Quartz CronExpression
	 */
	public void schedule(String name, CronExpression cronExpression) throws SchedulerException;

	/**
	 * 在startTime时执行调度一次
	 * @param startTime 调度开始时间
	 */
	public void schedule(Date startTime) throws SchedulerException;

	/**
	 * 在startTime时执行调度一次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 */
	public void schedule(String name, Date startTime) throws SchedulerException;

	/**
	 * 在startTime时执行调度，endTime结束执行调度
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 */
	public void schedule(Date startTime, Date endTime) throws SchedulerException;

	/**
	 * 在startTime时执行调度，endTime结束执行调度
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 */
	public void schedule(String name, Date startTime, Date endTime) throws SchedulerException;

	/**
	 * 在startTime时执行调度，endTime结束执行调度，重复执行repeatCount次
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 */
	public void schedule(Date startTime, Date endTime, int repeatCount) throws SchedulerException;

	/**
	 * 在startTime时执行调度，endTime结束执行调度，重复执行repeatCount次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 */
	public void schedule(String name, Date startTime, Date endTime, int repeatCount) throws SchedulerException;

	/**
	 * 在startTime时执行调度，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param repeatInterval 执行时间隔间
	 */
	public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval) throws SchedulerException;

	/**
	 * 在startTime时执行调度，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param repeatInterval 执行时间隔间
	 */
	public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval) throws SchedulerException;

	/**
	 * 暂停调度
	 * @param triggerName Quartz SimpleTrigger 名称
	 * @param group Trigger分组
	 * @throws SchedulerException 
	 */
	public void pauseTrigger(String triggerName, String group) throws SchedulerException;

	/**
	 * 恢复调度
	 * @param triggerName Quartz SimpleTrigger 名称
	 * @param group Trigger分组
	 * @throws SchedulerException 
	 */
	public void resumeTrigger(String triggerName, String group) throws SchedulerException;

	/**
	 * 移除调度
	 * @param triggerName Quartz SimpleTrigger 名称
	 * @param group Trigger分组
	 * @return
	 * @throws SchedulerException 
	 */
	public boolean removeTrigdger(String triggerName, String group) throws SchedulerException;
}
