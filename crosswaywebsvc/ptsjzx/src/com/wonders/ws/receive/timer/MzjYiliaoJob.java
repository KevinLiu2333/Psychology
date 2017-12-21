package com.wonders.ws.receive.timer;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.ws.receive.task.MzjYiliaoTask;

@IocBean
public class MzjYiliaoJob implements Job {
	Ioc				ioc					= null;
	MzjYiliaoTask	MzjYiliaoTask	= null;

	@Override
	public void execute(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		MzjYiliaoTask mzjYiliaoTask = Mvcs.ctx.getDefaultIoc().get(MzjYiliaoTask.class);
		mzjYiliaoTask.extract();
	}

}
