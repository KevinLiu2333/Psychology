package com.wonders.wddc.config;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;

import com.wonders.wddc.suite.logger.LogManager;
import com.wonders.wddc.suite.logger.entity.OpLogConfig;
import com.wonders.wddc.suite.logger.service.LogCoreService;

public class OpFilter implements ActionFilter {

	@Override
	public View match(ActionContext context) {
		if(context.getMethod() != null){
			String classPath = context.getMethod().getDeclaringClass().getName();
			String methodName = context.getMethod().getName();
			if(LogManager.logConfigMap.containsKey(classPath+"."+methodName)){
				OpLogConfig opLogConfig = LogManager.logConfigMap.get(classPath+"."+methodName);
				LogCoreService logService = context.getIoc().get(LogCoreService.class);
				logService.insertLogAndSms(opLogConfig.getCatalog(), opLogConfig.getLogType(), opLogConfig.getIsSms(), null,context.getRequest());
			}
		}
		
		return null;
	}

}
