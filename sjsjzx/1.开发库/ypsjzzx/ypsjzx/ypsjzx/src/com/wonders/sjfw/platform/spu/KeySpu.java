package com.wonders.sjfw.platform.spu;

import java.util.Date;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.sjfw.entity.FwAccess;
import com.wonders.sjfw.entity.FwInfo;
import com.wonders.sjfw.platform.FwConstants;
import com.wonders.sjfw.platform.error.Error201;
import com.wonders.sjfw.platform.error.Error202;
import com.wonders.sjfw.platform.error.Error203;
import com.wonders.sjfw.platform.error.Error205;
import com.wonders.sjfw.platform.error.Error206;
import com.wonders.sjfw.platform.handle.DataHandle;
import com.wonders.sjfw.platform.handle.ErrorHandle;
import com.wonders.sjfw.platform.handle.LogHandle;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.wddc.suite.user.entity.UserUnitBo;
/**
 * 
 * unitKey和methodKey的验证单元.
 *
 */
@IocBean
public class KeySpu {
	
	@Inject
	private DataHandle dataHandle;
	
	@Inject
	private LogHandle logHandle;
	
	@Inject
	private ErrorHandle errorHandle;
	
	/**
	 * 验证MethodKey信息.
	 * @param startDate 开始时间
	 * @param unitKey unitKey
	 * @param methodKey methodKey
	 * @return
	 */
	public String validMethodKey(String unitKey,String methodKey,String format,Date startDate){
		//获取用户信息
        UserUnitBo userUnit = dataHandle.getUserUnitByUnitKey(unitKey);
        //获取申请信息
        FwAccess fwAccess = dataHandle.getFwAccessByMethodKey(methodKey);
        if(fwAccess == null){
            String errorString = errorHandle.errorString(format,Error202.CODE,Error202.DESC);
            logHandle.dealErrorLog(format,Error202.CODE, errorString);
            logHandle.dealFwLog202(unitKey, methodKey, userUnit, startDate, errorString);
            return errorString;
        }else{
        	if("暂停".equals(fwAccess.getUsedStatus())){
                String errorString = errorHandle.errorString(format,Error203.CODE,Error203.DESC);
                logHandle.dealErrorLog(format,Error203.CODE, errorString);
                logHandle.dealFwLog202(unitKey, methodKey, userUnit, startDate, errorString);
                return errorString;
        	}
        	if("临时".equals(fwAccess.getAccessType()) && (new Date()).compareTo(fwAccess.getAccessEndDate()) > 0){
                String errorString = errorHandle.errorString(format,Error203.CODE,Error203.DESC);
                logHandle.dealErrorLog(format,Error203.CODE, errorString);
                logHandle.dealFwLog202(unitKey, methodKey, userUnit, startDate, errorString);
                return errorString;
        	}
        	
        	
        }
        return FwProcessor.VAILD_PARAM_PASS;
	}
	
	/**
	 * 验证unitKey信息.
	 * @param startDate 开始时间
	 * @param unitKey unitKey
	 * @param methodKey methodKey
	 * @return
	 */
	public String validUnitKey(String unitKey,String format,Date startDate){
		//获取用户信息
        UserUnitBo userUnit = dataHandle.getUserUnitByUnitKey(unitKey);
        if(userUnit == null){
            String errorString = errorHandle.errorString(format,Error201.CODE,Error201.DESC);
            logHandle.dealErrorLog(format,Error201.CODE, errorString);
            logHandle.dealFwLog201(unitKey, startDate, errorString);
            return errorString;
        }
        return FwProcessor.VAILD_PARAM_PASS;
	}
	
	/**
	 * 验证unitKey信息.
	 * @param startDate 开始时间
	 * @param unitKey unitKey
	 * @param methodKey methodKey
	 * @return
	 */
	public String validFwCode(String unitKey,String format,String fwCode,Date startDate){
		//获取用户信息
		FwInfo fwInfo = this.dataHandle.getFwInfoByCode(fwCode);
        if(fwInfo == null){
            String errorString = errorHandle.errorString(format,Error205.CODE,Error205.DESC);
            logHandle.dealErrorLog(format,Error205.CODE, errorString);
            logHandle.dealFwLog201(unitKey, startDate, errorString);
            return errorString;
        }
        return FwProcessor.VAILD_PARAM_PASS;
	}
	

	/**
	 * 验证unitKey信息.
	 * @param startDate 开始时间
	 * @param unitKey unitKey
	 * @param methodKey methodKey
	 * @return
	 */
	public String validFwRSConfig(String unitKey,String format,Date startDate,String rsStyle,String rsStructure){
		//获取用户信息
		boolean isOK = true;
		if(Strings.isNotBlank(rsStyle)){
			if(FwConstants.RS_STYLE_COMPLEX.equals(rsStyle) || FwConstants.RS_STYLE_SIMPLE.equals(rsStyle)){
				isOK = true;
			}else{
				isOK = false;
			}
		}
		if(isOK && Strings.isNotBlank(rsStructure)){
			if(FwConstants.RS_STRUCTUREKEY_VALUE.equals(rsStructure) || FwConstants.RS_STRUCTURELIST_MAP.equals(rsStructure) 
					||FwConstants.RS_STRUCTUREMAP_ARRAY.equals(rsStructure) || FwConstants.RS_STRUCTURESINGLE_RESULT.equals(rsStructure)){
				isOK = true;
			}else{
				isOK = false;
			}
		}
        if(!isOK){
            String errorString = errorHandle.errorString(format,Error206.CODE,Error206.DESC);
            logHandle.dealErrorLog(format,Error206.CODE, errorString);
            logHandle.dealFwLog201(unitKey, startDate, errorString);
            return errorString;
        }
        return FwProcessor.VAILD_PARAM_PASS;
	}
	
	
	/**
	 * 自动授权信息.
	 * @param startDate 开始时间
	 * @param unitKey unitKey
	 * @param methodKey methodKey
	 * @return
	 */
	public String autoMethodKey(String unitKey,String fwCode,Date startDate){
		//获取用户授权信息
		FwInfo fwInfo = this.dataHandle.getFwInfoByCode(fwCode);
		FwAccess fwAccess = this.dataHandle.getFwAccessByFwInfoId(unitKey,fwInfo.getFwInfoId());
		if(fwAccess == null){
		    fwAccess = new FwAccess();
			fwAccess.setAccessType("长期");
			fwAccess.setUsedStatus("正常");
			fwAccess.setMethodKey("wd"+System.currentTimeMillis());
			fwAccess.setFwInfoId(fwInfo.getFwInfoId());
			fwAccess.setUnitKey(unitKey);
			dataHandle.saveAutoAccess(fwAccess);
		}
        return fwAccess.getMethodKey();
	}


}
