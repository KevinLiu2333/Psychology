package com.wonders.sjfw.platform.processor;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;
import org.nutz.mvc.Mvcs;

import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwBean;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.sjfw.platform.spu.BaseSpu;
import com.wonders.wddc.config.WddcConstants;

public class Dingzhi2Processor extends BaseSpu implements FwProcessor {

	@Override
	public String opResult(FwParam spuParam) {
		//返回结果
  	  	Map<String, Object> result = new HashMap<String, Object>();
  	  	//获取执行处理的类
    	Class<?> fwBeanClass = null;
		try {
			fwBeanClass = Class.forName(spuParam.fwInfo.getConfigUrl());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//根据类获取执行处理的bean
		FwBean fwBean = (FwBean)Mvcs.getIoc().get(fwBeanClass);
		//获取参数验证的结果
		Map<String, Object> resultMap = fwBean.genResult(spuParam);
		 int resultcount = Integer.parseInt((String)resultMap.get(WddcConstants.RS_MAP_COUNT));
			if(resultcount > 0){
				result.put(WddcConstants.RS_MAP_DATA,resultMap.get(WddcConstants.RS_MAP_DATA));
		    }else {
	          result.put(WddcConstants.RS_MAP_DATA,"无数据");
		    }
		    result.put("SUCCESS","1");
		    //结果设置
		    String resultString = this.genResultString(spuParam, resultMap,result);
	    //返回结果
	    return resultString;
	}

	@Override
	public String validParam(FwParam spuParam) {
		return FwProcessor.VAILD_PARAM_PASS;
	}

	@Override
	public String opExample(FwParam spuParam) {
		return spuParam.fwInfo.getConfigMemo();
	}
}

