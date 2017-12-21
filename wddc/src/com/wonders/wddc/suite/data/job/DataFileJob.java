package com.wonders.wddc.suite.data.job;

import java.util.LinkedHashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.wddc.suite.data.service.DataFileCoreService;
/**
 * @author wonders
 * 文件缓存job
 */
@IocBean
public class DataFileJob implements Job {

	@Override
	public void execute(JobExecutionContext param) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap dataMap = param.getJobDetail().getJobDataMap();
		String datafileid =dataMap.getString("id");
		String cachetype =dataMap.getString("type");
		DataFileCoreService service = Mvcs.ctx().getDefaultIoc().get(DataFileCoreService.class);
		Map<String, String> paramMap = new LinkedHashMap<String, String>();
		if("1".equals(cachetype)){
			service.createCacheType1(datafileid,paramMap);
		}if("2".equals(cachetype)){
			service.createCacheType2(datafileid,paramMap);
		}

		Logs.get().info("执行组装服务"+datafileid+"的缓存设置(类型："+cachetype+")！");
	}
	
	
}
