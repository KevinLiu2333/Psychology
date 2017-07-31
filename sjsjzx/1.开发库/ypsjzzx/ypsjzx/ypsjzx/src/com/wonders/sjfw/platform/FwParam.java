package com.wonders.sjfw.platform;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.sjfw.entity.FwConfig;
import com.wonders.sjfw.entity.FwInfo;
/**
 * 服务框架内部传参.
 *
 */
public class FwParam {
	/**
	 * 用户访问权限代码
	 */
	public String unitKey;
	/**
	 * 服务访问权限代码
	 */
	public String methodKey;
	/**
	 * 返回数据的结构
	 */
	public String rsStructure ;
	/**
	 * 返回数据结构风格，简化，普通两种
	 */
	public String rsStyle;
	/**
	 * 返回数据格式
	 */
	public String format = FwConstants.FORMAT_JSON;
	/**
	 * 开始调用的时间
	 */
	public Date startDate;
	/**
	 * 服务主键
	 */
	public FwInfo fwInfo;
	/**
	 * 参数map
	 */
	public Map<String,String> paramMap;

	/**
	 * 页面传递参数
	 */
	public Map<String,String> requestParamMap;
	
	/**
	 * 配置项信息
	 */
	public List<FwConfig> fwConfigList;
}
