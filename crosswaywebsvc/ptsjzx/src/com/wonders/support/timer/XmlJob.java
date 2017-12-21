package com.wonders.support.timer;

import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.xml2db.xml.operator.Operator;

@IocBean
public class XmlJob implements Job{
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Operator.execute();
	}

}
