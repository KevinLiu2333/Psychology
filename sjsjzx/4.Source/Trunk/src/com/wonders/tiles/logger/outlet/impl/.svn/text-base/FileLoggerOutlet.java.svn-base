package com.wonders.tiles.logger.outlet.impl;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.tiles.logger.entity.LoggerInfo;
import com.wonders.tiles.logger.outlet.LoggerOutlet;

public class FileLoggerOutlet implements LoggerOutlet{
	
	private Log log = Logs.get();
	
	public void print(LoggerInfo loggerInfo) {
		log.info(loggerInfo.formatLoggerInfo());
	}

}
