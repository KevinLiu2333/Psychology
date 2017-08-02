package com.wonders.sjfw.platform.spu;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.sjfw.entity.FwAccess;
import com.wonders.sjfw.entity.FwConfig;
import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.handle.DataHandle;

/**
 * 
 * 服务参数组装处理单元.
 *
 */
@IocBean
public class ParamSpu {
	
	@Inject
	private DataHandle dataHandle;
	/**
	 * 组装外部服务框架参数.
	 * @param request
	 * @param unitKey
	 * @param methodKey
	 * @return
	 */
	public FwParam putOutParam(HttpServletRequest request,String unitKey,String methodKey,String format,String rsStyle,String rsStructure,Date startDate){
	       FwParam spuParam = new FwParam();
	        //3.1组装key/format信息对象
	        spuParam.unitKey = unitKey;
	        spuParam.methodKey = methodKey;
	        spuParam.format = format;
	        spuParam.startDate = startDate;
        	spuParam.rsStyle = rsStyle;
        	spuParam.rsStructure = rsStructure;
	        //3.2组装服务相关对象
	        FwAccess fwAccess = dataHandle.getFwAccessByMethodKey(methodKey);
	        spuParam.fwInfo = dataHandle.getFwInfoById(fwAccess.getFwInfoId());
	        //3.3组装配置参数对象
	        spuParam.fwConfigList = dataHandle.findFwConfigByFwInfoId(spuParam.fwInfo.getFwInfoId());
	        Map<String,String> paramMap = new HashMap<String,String>();
	        for(FwConfig fwConfig : spuParam.fwConfigList){
	        	//查询条件
	        	if("1".equals(fwConfig.getConfigType())){
		            paramMap.put(fwConfig.getColName().toUpperCase(),fwConfig.getColName());
	        	}
	        }
	        spuParam.paramMap = paramMap;
	        //3.4组装传入参数对象
	        Map<String,String> requestParamMap = new HashMap<String,String>();
	        Enumeration<?> paramNames = request.getParameterNames();
	        while (paramNames != null && paramNames.hasMoreElements()) {
	            String paramName = (String) paramNames.nextElement();
	            if("methodKey".equals(paramName)){
	            	continue;
	            }
	            if("unitKey".equals(paramName)){
	            	continue;
	            }
	            String[] values = request.getParameterValues(paramName);
	            String paramValue = "";
	            if (values == null || values.length == 0) {//NOSONAR
	            } else if (values.length > 1) {
	            } else {
	                if (!Strings.isBlank(values[0])) {
	                    paramValue=values[0];
	                    requestParamMap.put(paramName, paramValue);
	                    if(paramMap.containsKey(paramName)){
	    		            paramMap.put(paramName, paramValue);
	                    }
	                }
	            }
	            
	          
	        }
	        spuParam.requestParamMap = requestParamMap;
	        return spuParam;
	}
	
	/**
	 * 组装内部服务框架参数.
	 * @param request
	 * @param unitKey
	 * @param methodKey
	 * @return
	 */
	public FwParam putInnerParam(Map<String,String> paramMap,String unitKey,String methodKey,String format,Date startDate){
		   if(paramMap == null){
			   paramMap = new HashMap<String,String>();
		   }
	       FwParam spuParam = new FwParam();
	        //3.1组装key/format信息对象
	        spuParam.unitKey = unitKey;
	        spuParam.methodKey = methodKey;
	        spuParam.format = format;
	        spuParam.startDate = startDate;
	        //3.2组装服务相关对象
	        FwAccess fwAccess = dataHandle.getFwAccessByMethodKey(methodKey);
	        spuParam.fwInfo = dataHandle.getFwInfoById(fwAccess.getFwInfoId());
	        //3.3组装配置参数对象
	        spuParam.paramMap = paramMap;
	        spuParam.requestParamMap = paramMap;
	        //设置返回值
	        if(paramMap.containsKey("rsStructure")){
	        	spuParam.rsStructure = paramMap.get("rsStructure");
	        }
	        if(paramMap.containsKey("rsStyle")){
	        	spuParam.rsStyle = paramMap.get("rsStyle");
	        }
	        return spuParam;
	}
	
	
	/**
	 * 组装查看样例example.
	 * @param request
	 * @param fwCode 服务代码
	 * @return
	 */
	public FwParam putExampleParam(HttpServletRequest request,String fwCode,String format){
	    FwParam spuParam = new FwParam();
        spuParam.fwInfo = dataHandle.getFwInfoByCode(fwCode);
        spuParam.format = format;
        spuParam.fwConfigList = dataHandle.findFwConfigByFwInfoId(spuParam.fwInfo.getFwInfoId());
        return spuParam;
	}

}
