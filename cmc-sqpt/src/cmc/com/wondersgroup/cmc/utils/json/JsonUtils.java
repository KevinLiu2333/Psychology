package com.wondersgroup.cmc.utils.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * Json数据处理工具
 */
public class JsonUtils {
	
	
	/**
	 * 获取Json环境上下文默认设置
	 * 
	 * @param dateFormat Date类型的转换格式
	 * @return JsonConfig对象
	 */
	public static JsonConfig getJsonConfig(String dateFormat) {
		JsonDateValueProcessor beanProcessor = new JsonDateValueProcessor();
		if (dateFormat != null) {
			DateFormat df = new SimpleDateFormat(dateFormat);
			beanProcessor.setDateFormat(df);
		}

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, beanProcessor);
		return jsonConfig;
	}
	
	
	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param bean bean对象
	 * @return JSON字符串
	 */
	public static String getJsonData(Object bean) {
		return JSONObject.fromObject(bean, getJsonConfig(null)).toString();
	}
	
}