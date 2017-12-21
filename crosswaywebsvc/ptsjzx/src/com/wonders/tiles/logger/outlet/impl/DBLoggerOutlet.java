package com.wonders.tiles.logger.outlet.impl;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.service.Service;

import com.wonders.tiles.logger.entity.LoggerInfo;
import com.wonders.tiles.logger.outlet.LoggerOutlet;

@IocBean(fields = "dao")
public class DBLoggerOutlet extends Service implements LoggerOutlet{

	@At
	public void print(LoggerInfo loggerInfo) {
		dao().insert(loggerInfo);
	}

}
