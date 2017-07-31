package com.wonders.tiles.logger.utils;

import com.wonders.tiles.logger.entity.LoggerInfo;

public class LogUtils {

	private static ThreadLocal<LoggerInfo> threadLocal = new ThreadLocal<LoggerInfo>();
	
	public static void createLog(LoggerInfo loggerInfo) {
		if (threadLocal.get() == null) {
			threadLocal.set(loggerInfo);
		}
	}
	
	public static LoggerInfo getLog() {
		return threadLocal.get();
	}
}
