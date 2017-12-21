package com.wonders.ws.receive.timer;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.ws.receive.task.BuildingTask;

@IocBean
public class BuildingJob implements Job{
	Ioc ioc = null;
	BuildingTask BuildingTask =null;
	@Override
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		BuildingTask buildingTask = Mvcs.ctx.getDefaultIoc().get(BuildingTask.class);
		buildingTask.extract();
	}

}
