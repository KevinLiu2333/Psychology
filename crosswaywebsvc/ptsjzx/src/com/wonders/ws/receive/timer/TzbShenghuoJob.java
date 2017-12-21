package com.wonders.ws.receive.timer;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.ws.receive.task.TzbShenghuoTask;

@IocBean
public class TzbShenghuoJob implements Job {
	Ioc				ioc				= null;
	TzbShenghuoTask	QfbShenghuoTask	= null;

	@Override
	public void execute(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		TzbShenghuoTask qfbShenghuoTask = Mvcs.ctx.getDefaultIoc().get(TzbShenghuoTask.class);
		qfbShenghuoTask.extract();
	}

}
