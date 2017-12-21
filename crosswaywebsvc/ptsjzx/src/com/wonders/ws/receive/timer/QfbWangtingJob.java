package com.wonders.ws.receive.timer;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.ws.receive.task.QfbWangtingTask;

@IocBean
public class QfbWangtingJob implements Job {
	Ioc				ioc					= null;
	QfbWangtingTask	QfbWangtingTaskTask	= null;

	@Override
	public void execute(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		QfbWangtingTask qfbWangtingTask = Mvcs.ctx.getDefaultIoc().get(QfbWangtingTask.class);
		qfbWangtingTask.extract();
	}

}
