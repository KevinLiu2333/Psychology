package com.wonders.admin.service.unifiedmonitoring;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.admin.util.ResultRules;

/**
 * 阀值列表的业务层接口.
 * 
 * @author Gray
 *
 */
@IocBean
public interface ThresholdSetService {

	/**
	 * 获取所有的阀值设置并返回.
	 * 
	 * @return 规定的规则.
	 */
	public ResultRules getAllThreshold();

	/**
	 * 将传入的数据打包,并进行阀值设置表的数据更新.
	 * 
	 * @param id
	 *            唯一ID值.
	 * @param threshold
	 *            设置值.
	 * @param alarmLevel
	 *            设置等级.
	 * @param session
	 * 			  session.
	 * @return 规定的规则.
	 */
	public ResultRules updateThreshold(Integer id, String threshold, Integer alarmLevel, HttpSession session);
}
