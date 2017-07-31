package com.wonders.search.timer;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.search.task.IndexTask;

@IocBean
public class IndexJob implements Job{
	Ioc ioc = null;
	IndexTask indextask=null;
	@Override
	public void execute(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		try {
			if(ioc == null){
				ioc = new NutIoc(new ComboIocLoader("*org.nutz.ioc.loader.json.JsonLoader", "ioc/", "*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
						"com.wonders"));
			}
			if(indextask == null){
				indextask = ioc.get(IndexTask.class);
				indextask.fullIndex();
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
