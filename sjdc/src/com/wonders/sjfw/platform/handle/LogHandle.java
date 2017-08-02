package com.wonders.sjfw.platform.handle;

import java.util.Date;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.sjfw.entity.LogFw;
import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwHandle;
import com.wonders.wddc.suite.logger.entity.LogErrorBo;
import com.wonders.wddc.suite.logger.service.LogCoreService;
import com.wonders.wddc.suite.user.entity.UserUnitBo;
/**
 * 日志处理的操作.
 *
 */
@IocBean(fields = "dao")
public class LogHandle implements FwHandle {

	public Dao dao;
	
    @Inject
    private LogCoreService logService;
    
	/**
	 * 处理error log日志
	 * @param format 格式
	 * @param errorCode 错误代码
	 * @param errorString 错误描述
	 */
	public void dealErrorLog(String format,String errorCode,String errorString){
        LogErrorBo logError = new LogErrorBo();
        logError.setErrorType("FW");
        logError.setErrorCode(errorCode);
        logError.setErrorContent(errorString);
        logError.setErrorTime(new Date());
        dao.insert(logError);
    }
	
	/**
	 * 处理fw log日志-201错误.
	 * @param format 格式
	 * @param errorCode 错误代码
	 * @param errorString 错误描述
	 */
	public LogFw dealFwLog201(String unitKey,Date startDate,String errorString){
        LogFw logfw = new LogFw();
        logfw.setStartTime(startDate);
		logfw.setOpStatus("0");
        logfw.setUserKey(unitKey);
        logfw.setOpResult(errorString);
        logfw.setEndTime(new Date());
        logfw.setUsedTime((int) (System.currentTimeMillis()-startDate.getTime()));
        logfw = dao.insert(logfw);
        return logfw;
    }

	/**
	 * 处理fw log日志-202错误.
	 * @param format 格式
	 * @param errorCode 错误代码
	 * @param errorString 错误描述
	 */
	public LogFw dealFwLog202(String unitKey,String methodKey,UserUnitBo userUnit,Date startDate,String errorString){
        LogFw logfw = new LogFw();
        logfw.setStartTime(startDate);
        logfw.setOpStatus("0");
        logfw.setUnitId(userUnit.getUnitId());
        logfw.setUnitName(userUnit.getUnitName());
        logfw.setMethodKey(methodKey);
        logfw.setUserKey(unitKey);
        logfw.setOpResult(errorString);
        logfw.setEndTime(new Date());
        logfw.setUsedTime((int) (System.currentTimeMillis()-startDate.getTime()));
        logfw = dao.insert(logfw);
        //插入总日志记录
        logService.insertOpLog("xt", "xt102", userUnit.getUnitId(), userUnit.getUnitName(),logfw.getFwLogId());
        logService.insertOpLog("fw", "fw003", userUnit.getUnitId(), userUnit.getUnitName(),logfw.getFwLogId());
        return logfw;
    }


	/**
	 * 处理fw log日志-101错误.
	 * @param format 格式
	 * @param errorCode 错误代码
	 * @param errorString 错误描述
	 */
	public LogFw dealFwLog101(FwParam spuParam,UserUnitBo userUnit,String errorString){
        LogFw logfw = new LogFw();
        logfw.setOpStatus("0");
        logfw.setUnitId(userUnit.getUnitId());
        logfw.setUnitName(userUnit.getUnitName());
        logfw.setMethodKey(spuParam.methodKey);
        logfw.setUserKey(spuParam.unitKey);
        logfw.setFwInfoId(spuParam.fwInfo.getFwInfoId());
        logfw.setOpResult(errorString);
        logfw.setEndTime(new Date());
        logfw.setUsedTime((int) (System.currentTimeMillis()-spuParam.startDate.getTime()));
        logfw = dao.insert(logfw);
        //插入总日志记录
        logService.insertOpLog("xt", "xt102", userUnit.getUnitId(), userUnit.getUnitName(),logfw.getFwLogId());
        logService.insertOpLog("fw", "fw003", userUnit.getUnitId(), userUnit.getUnitName(),logfw.getFwLogId());
        return logfw;
    }
	

	/**
	 * 处理fw log日志-301错误.
	 * @param format 格式
	 * @param errorCode 错误代码
	 * @param errorString 错误描述
	 */
	public LogFw dealFwLog301(FwParam spuParam,UserUnitBo userUnit,String errorString){
        LogFw logfw = new LogFw();
        logfw.setOpStatus("0");
        logfw.setUnitId(userUnit.getUnitId());
        logfw.setUnitName(userUnit.getUnitName());
        logfw.setMethodKey(spuParam.methodKey);
        logfw.setUserKey(spuParam.unitKey);
        logfw.setFwInfoId(spuParam.fwInfo.getFwInfoId());
        logfw.setOpResult(errorString);
        logfw.setEndTime(new Date());
        logfw.setUsedTime((int) (System.currentTimeMillis()-spuParam.startDate.getTime()));
        logfw = dao.insert(logfw);
        //插入总日志记录
        logService.insertOpLog("xt", "xt102", userUnit.getUnitId(), userUnit.getUnitName(),logfw.getFwLogId());
        logService.insertOpLog("fw", "fw003", userUnit.getUnitId(), userUnit.getUnitName(),logfw.getFwLogId());
        return logfw;
    }
	

	/**
	 * 处理fw log日志-正确调用.
	 * @param format 格式
	 * @param errorCode 错误代码
	 * @param errorString 错误描述
	 */
	public LogFw dealFwLogOk(FwParam spuParam,UserUnitBo userUnit,String resultString,int resultcount,String whereSql){
        LogFw logfw = new LogFw();
        logfw.setOpStatus("1");
        logfw.setUnitId(userUnit.getUnitId());
        logfw.setUnitName(userUnit.getUnitName());
        logfw.setMethodKey(spuParam.methodKey);
        logfw.setUserKey(spuParam.unitKey);
        logfw.setFwInfoId(spuParam.fwInfo.getFwInfoId());
        logfw.setOpParam(whereSql);
        logfw.setResultCount(resultcount);
        logfw.setOpResult(resultString);
        logfw.setEndTime(new Date());
        logfw.setUsedTime((int) (System.currentTimeMillis()-spuParam.startDate.getTime()));
        logfw = dao.insert(logfw);
        //插入总日志记录
        logService.insertOpLog("fw", "fw003", userUnit.getUnitId(), userUnit.getUnitName(),logfw.getFwLogId());
        return logfw;
    }
}
