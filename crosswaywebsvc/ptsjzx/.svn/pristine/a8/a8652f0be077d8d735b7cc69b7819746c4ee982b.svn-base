package com.wonders.sjtb.timer;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.tiles.tools.IocSingle;

@IocBean
public class SjtbJob implements Job{
	Ioc ioc = null;
	SjtbTask sjtbTask =null;
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		if(ioc==null){
			ioc = IocSingle.getInstance();
		}
		if(sjtbTask==null){
			sjtbTask=ioc.get(SjtbTask.class);
			sjtbTask.extract();
		}
	}

}
