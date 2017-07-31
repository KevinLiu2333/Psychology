package com.wonders.support.timer;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.dp.at.SqlInfoAt;

@IocBean
public class JsonJob implements Job{
	Ioc ioc = null;
	SqlInfoAt sqlInfoAt = null;
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			if(ioc == null){
				ioc = new NutIoc(new ComboIocLoader("*org.nutz.ioc.loader.json.JsonLoader", "ioc/", "*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
						"com.wonders"));
			}
			if(sqlInfoAt == null){
				sqlInfoAt = ioc.get(SqlInfoAt.class);
				sqlInfoAt.saveJson();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
