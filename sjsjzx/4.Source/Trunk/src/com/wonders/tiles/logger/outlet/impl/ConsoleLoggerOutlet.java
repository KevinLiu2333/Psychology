package com.wonders.tiles.logger.outlet.impl;

import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.tiles.logger.entity.LoggerInfo;
import com.wonders.tiles.logger.outlet.LoggerOutlet;

@IocBean
public class ConsoleLoggerOutlet implements LoggerOutlet {

	public void print(LoggerInfo loggerInfo) {
		System.out.println(loggerInfo.formatLoggerInfo());
	}
}
