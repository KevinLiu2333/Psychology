package com.wonders.wddc.suite.data.job;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.wddc.tiles.mission.at.missionAct;
/**
 * 任务定时器
 * @author wanda
 *
 */
@IocBean
public class TaskCompJob implements Job{
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		missionAct  act = Mvcs.ctx().getDefaultIoc().get(missionAct.class);
		act.checkComp();
		act.insertComp();
	}
}
