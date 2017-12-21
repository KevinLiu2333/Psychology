package com.wonders.sjfw.platform.spu;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.alert.AlertInfo;
import com.wonders.sjfw.platform.alert.AlertManager;
import com.wonders.sjfw.platform.error.Error207;
import com.wonders.sjfw.platform.handle.DataHandle;
import com.wonders.sjfw.platform.handle.ErrorHandle;
import com.wonders.sjfw.platform.handle.LogHandle;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.wddc.suite.user.entity.UserUnitBo;

@IocBean
public class AlertSpu {
	@Inject
	private DataHandle dataHandle;
	@Inject
	private LogHandle logHandle;
	
	@Inject
	private ErrorHandle errorHandle;
	/**
	 * 判断是否预警.
	 * @param fwInfo
	 * @return
	 */
	public String isAlert(FwParam fwParam){
		AlertInfo alertInfo = AlertManager.getInstance().getAlertInfo(fwParam.fwInfo.getFwCode());
		long dayUsedCount = AlertManager.getInstance().getDayUsedCountData(fwParam.fwInfo.getAlertLevel());
		if(alertInfo.dayUsedCount >= dayUsedCount){
	        UserUnitBo userUnit = dataHandle.getUserUnitByUnitKey(fwParam.unitKey);
            String errorString = errorHandle.errorString(fwParam.format,Error207.CODE,Error207.DESC);
            logHandle.dealErrorLog(fwParam.format,Error207.CODE, errorString);
            logHandle.dealFwLog2Series("yj101",fwParam.unitKey, fwParam.methodKey, userUnit, fwParam.startDate, Error207.CODE,errorString);
            return errorString;
		}
        return FwProcessor.VAILD_PARAM_PASS;
	}
	
	/**
	 * 动态调整预警信息.
	 * @param fwInfo
	 * @return
	 */
	public void changeAlertInfo(String fwCode,long dataCount){
		AlertInfo alertInfo = AlertManager.getInstance().getAlertInfo(fwCode);
		//调用次数
		alertInfo.dayUsedCount = alertInfo.dayUsedCount+1;
		//调用条数
		alertInfo.dayDataCount = alertInfo.dayDataCount+dataCount;
	}
	
	
}
