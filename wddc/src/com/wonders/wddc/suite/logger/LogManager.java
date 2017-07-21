package com.wonders.wddc.suite.logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wonders.wddc.suite.logger.entity.OpLogConfig;
import com.wonders.wddc.tiles.tools.ClassUtil;

public class LogManager {

	private static LogManager logManager = null;
	private static String PACKNAME = "com.wonders";
	@SuppressWarnings("unchecked")
	public static List<Class> jobclses = ClassUtil.getpackClsByEndStr(PACKNAME,"Act","At");
	
	public static Map<String,OpLogConfig> logConfigMap = new HashMap<String,OpLogConfig>();
	
	private LogManager() {

	}
	public static LogManager getInstance() {
		if (logManager == null) {
			logManager = new LogManager();
		}
		return logManager;
	}
	
	public void loadConfig(List<OpLogConfig> opLogConfigList){
		for(OpLogConfig opLogConfig : opLogConfigList){
			logConfigMap.put(opLogConfig.getMethodPath()+"."+opLogConfig.getMethodName(), opLogConfig);
		}
	}
	
}
