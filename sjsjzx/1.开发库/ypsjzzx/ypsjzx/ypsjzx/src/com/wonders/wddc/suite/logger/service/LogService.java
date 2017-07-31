package com.wonders.wddc.suite.logger.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import com.wonders.wddc.suite.logger.LogConstants;
import com.wonders.wddc.suite.logger.entity.BaseLog;
import com.wonders.wddc.suite.logger.entity.LogErrorBo;
import com.wonders.wddc.tiles.jk.UserService;
import com.wonders.wddc.tiles.jk.entity.User;
import com.wonders.wddc.tiles.tools.DateUtils;

@IocBean
public class LogService {
	@Inject
	private Dao dao;
	/**
	 * 记录服务的操作日志.
	 * @param catalog
	 * @param logType
	 * @param request
	 */
	public void insertOpLog(String catalog, String logType, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute(UserService.SESSION_USER);
		BaseLog baseLog =this.genLogInfo(catalog, logType, user);
		dao.insert(baseLog);
		
	}
	/**
	 * 记录服务的操作日志--面向用户.
	 * @param catalog
	 * @param logType
	 * @param user
	 */
	public void insertOpLog(String catalog, String logType,User user){
		BaseLog baseLog =this.genLogInfo(catalog, logType, user);
		dao.insert(baseLog);
		
	}
	
	/**
	 * 记录操作信息，设置是否发布消息通知 .
	 * @param catalog
	 * @param logType
	 * @param isSms
	 * @param request
	 */
	public void insertLogAndSms(String catalog, String logType, String isSms,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute(UserService.SESSION_USER);
		BaseLog baseLog =this.genLogInfo(catalog, logType, user);
		baseLog.setIsSms(isSms);
		if("1".equals(isSms)){
			baseLog.setSmsMsg(baseLog.getUserName()+"于"+DateUtils.date2String(new Date(), DateUtils.FORMAT_DATE)+"浏览了"+logType+"功能（临时）。");
		}
		dao.insert(baseLog);
		
	}
	
	/**
	 * 记录登录错误日志.
	 * @param request
	 * @param logonName 登录名称
	 * @param password 密码
	 * @param errorMsg 错误消息
	 */
	public void insertLoginErrorLog(HttpServletRequest request,String logonName, String password,String errorMsg){
		//记录操作日志
		this.insertOpLog("xt", "xt101", request);
		//记录错误日志
		LogErrorBo logErrorBo = new LogErrorBo();
		logErrorBo.setErrorCode("xt101");
		logErrorBo.setErrorType("LOGIN");
		Map<String, Object> contentMap = new HashMap<String, Object>();
		contentMap.put("logonName", logonName);
		contentMap.put("password", password);
		contentMap.put("errorMsg", errorMsg);
		logErrorBo.setErrorContent(Json.toJson(contentMap));
		logErrorBo.setErrorTime(new Date());
		dao.insert(logErrorBo);
		
	}
	
	
	/**
	 * 生成日志对对象
	 * @param catalog 大分类
	 * @param logType 小分类
	 * @param user 用户
	 * @return
	 */
	private BaseLog genLogInfo(String catalog, String logType,User user){
		BaseLog baseLog = new BaseLog();
		baseLog.setCatalog(catalog);
		baseLog.setLogType(logType);
		Date insertDate = new Date();
		baseLog.setLogTime(insertDate);
		baseLog.setNian(DateUtils.format(insertDate, "yyyy"));
		baseLog.setYue(DateUtils.format(insertDate, "MM"));
		baseLog.setTian(DateUtils.format(insertDate, "dd"));
		baseLog.setXiaoshi(DateUtils.format(insertDate, "HH"));
		if(user != null){
			baseLog.setUnitId(user.getUnitId());
			baseLog.setUnitName(user.getUnitName());
			baseLog.setUserId(user.getUserId());
			baseLog.setUserName(user.getDisplayName());
		}else{
			baseLog.setUserId(LogConstants.YK_USER_ID);
			baseLog.setUserName(LogConstants.YK_USER_NAME);
			baseLog.setUnitId(LogConstants.YK_UNIT_ID);
			baseLog.setUnitName(LogConstants.YK_UNIT_NAME);
		}
		return baseLog;
	}

}
