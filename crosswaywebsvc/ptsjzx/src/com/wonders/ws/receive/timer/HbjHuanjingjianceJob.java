package com.wonders.ws.receive.timer;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.ws.receive.task.HbjHuanjingjianceTask;

@IocBean
public class HbjHuanjingjianceJob implements Job {
	Ioc						ioc						= null;
	HbjHuanjingjianceTask	HbjHuanjingjianceTask	= null;

	@Override
	public void execute(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		HbjHuanjingjianceTask hbjHuanjingjianceTask = Mvcs.ctx.getDefaultIoc().get(HbjHuanjingjianceTask.class);
		hbjHuanjingjianceTask.extract();
	}

}
