package com.wonders.wddc.suite.logger.outlet.impl;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.service.Service;

import com.wonders.wddc.suite.logger.entity.LogChangeBo;
import com.wonders.wddc.suite.logger.entity.LogErrorBo;
import com.wonders.wddc.suite.logger.entity.LogLoginBo;
import com.wonders.wddc.suite.logger.entity.LogOpBo;
import com.wonders.wddc.suite.logger.entity.LogOpDetailBo;
import com.wonders.wddc.suite.logger.outlet.LoggerOutlet;

@IocBean(fields = "dao")
public class DBLoggerOutlet extends Service implements LoggerOutlet{

	@At
	public void logChange(LogChangeBo logChangeBo) {
		dao().insert(logChangeBo);
		
	}

	@At
	public void logError(LogErrorBo logErrorBo) {
		dao().insert(logErrorBo);
		
	}
	
	@At
	public void logLogin(LogLoginBo logLoginBo) {
		dao().insert(logLoginBo);
		
	}

	@At
	public void logOp(LogOpBo logOpBo) {
		dao().insert(logOpBo);
		
	}
	
	@At
	public void logOpDetail(LogOpDetailBo logOpDetailBo) {
		dao().insert(logOpDetailBo);
		
	}

}
