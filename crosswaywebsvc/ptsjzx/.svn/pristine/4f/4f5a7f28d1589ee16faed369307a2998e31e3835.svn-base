package com.wonders.ws.receive.timer;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.ws.receive.task.CommunityAcceptanceTask;

@IocBean
public class CommunityAcceptanceJob implements Job {
	Ioc						ioc						= null;
	CommunityAcceptanceTask	CommunityAcceptanceTask	= null;

	@Override
	public void execute(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		CommunityAcceptanceTask communityAcceptanceTask = Mvcs.ctx.getDefaultIoc().get(CommunityAcceptanceTask.class);
		communityAcceptanceTask.extract();
	}

}
