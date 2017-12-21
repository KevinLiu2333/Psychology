package com.wonders.echarts.timer;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.echarts.task.EchartsTask;
@IocBean
public class EchartsJob implements Job{
	Ioc ioc = null;
	EchartsTask echartsTask=null;
	
	@Override
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		EchartsTask echartsTask = Mvcs.ctx.getDefaultIoc().get(EchartsTask.class);
		echartsTask.count();
	}

}
