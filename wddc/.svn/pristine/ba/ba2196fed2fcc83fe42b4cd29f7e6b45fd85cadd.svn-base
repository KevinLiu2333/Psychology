package com.wonders.wddc.suite.logger.entity;

import org.nutz.lang.segment.CharSegment;
import org.nutz.lang.segment.Segment;

public class LogChangeBo {

	private static final Segment SEGMENT = new CharSegment(
			"用户:[${userName}(${userId})]\n" +
			"在[${recordTime}]访问了:([${callingClass}]/[${callingMethod}])方法\n" +
			"描述为:[${description}]\n" +
			"传入参数:\n[${args}]\n" +
			"得到的返回值为:\n[${resultObj}]\n" +
			"${other}");
	
	public static String formatLoggerInfo() {
		
		return SEGMENT.toString();
	}
}
