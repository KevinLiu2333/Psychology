package com.wonders.wddc.suite.logger.outlet.impl;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.wddc.suite.logger.entity.LogChangeBo;
import com.wonders.wddc.suite.logger.entity.LogErrorBo;
import com.wonders.wddc.suite.logger.entity.LogLoginBo;
import com.wonders.wddc.suite.logger.entity.LogOpBo;
import com.wonders.wddc.suite.logger.entity.LogOpDetailBo;
import com.wonders.wddc.suite.logger.outlet.LoggerOutlet;

public class FileLoggerOutlet implements LoggerOutlet{
	
	private Log log = Logs.get();
	

	@Override
	public void logChange(LogChangeBo logChangeBo) {
		log.info(LogChangeBo.formatLoggerInfo());
		
	}

	@Override
	public void logError(LogErrorBo logErrorBo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logLogin(LogLoginBo logLoginBo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logOp(LogOpBo logOpBo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logOpDetail(LogOpDetailBo logOpDetailBo) {
		// TODO Auto-generated method stub
		
	}

}
