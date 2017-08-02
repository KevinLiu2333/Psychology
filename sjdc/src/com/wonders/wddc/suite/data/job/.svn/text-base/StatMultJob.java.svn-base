package com.wonders.wddc.suite.data.job;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.wddc.suite.data.at.StatMultAct;
/**
 * 报表统计任务类
 * @author hezhihan
 *
 */
@IocBean
public class StatMultJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		StatMultAct  act = Mvcs.ctx().getDefaultIoc().get(StatMultAct.class);
		act.countAll();
	}
	
}
