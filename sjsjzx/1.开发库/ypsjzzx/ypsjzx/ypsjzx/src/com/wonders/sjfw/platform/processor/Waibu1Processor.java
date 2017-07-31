package com.wonders.sjfw.platform.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;

import com.wonders.sjfw.platform.FwConstants;
import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwBean;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.sjfw.platform.spu.BaseSpu;
/**
 * 外部链接接口服务.
 *
 */
public class Waibu1Processor extends BaseSpu implements FwProcessor {

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
		Map<String, Object> re = fwBean.genResult(spuParam);
        //拼装返回结果
        int resultcount = 0;
		if(re!=null){
			result.put("DATA",re.get(FwConstants.RS_MAP_DATA));
			resultcount=Integer.parseInt((String)re.get(FwConstants.RS_MAP_COUNT));
	    }else {
	    	result.put("DATA","无数据");
	    }
	    result.put("SUCCESS","1");
	    String resultString = Json.toJson(result);
	    //调用成功日志
	    this.genSeccessLog(spuParam, resultString, resultcount);
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
