package com.wonders.sjfw.platform.processor;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;

import com.wonders.sjfw.platform.FwConstants;
import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.sjfw.platform.spu.BaseSpu;
/**
 * jiekou1处理过程.
 *
 */
public class Jiekou1Processor extends BaseSpu implements FwProcessor {

	
	@Override
	public String opResult(FwParam spuParam) {
		//返回结果
  	  	Map<String, Object> result = new HashMap<String, Object>();
		//结果查询
  	    Map<String,Object> resultMap =  this.exeResult(spuParam.fwInfo.getMultStatId(),spuParam.paramMap,spuParam.rsStructure);
        //拼装返回结果
        int resultcount = Integer.parseInt((String)resultMap.get(FwConstants.RS_MAP_COUNT));
		if(resultcount >0){
			result.put(FwConstants.RS_MAP_DATA,"1");
	    }else {
	    	result.put(FwConstants.RS_MAP_DATA,"0");
	    }
	    result.put("SUCCESS","1");
	    //结果设置
	    String resultString = this.genResultString(spuParam, resultMap,result);
	    //调用成功日志
	    this.genSeccessLog(spuParam, resultString, resultcount);
	    //返回结果
	    return resultString;
	}

	@Override
	public String validParam(FwParam spuParam) {
		return this.validParamDefault(spuParam);
	}

	@Override
	public String opExample(FwParam spuParam) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
   	 	jsonMap.put("SUCCESS","1");
   	 	jsonMap.put("RESULT","1");
        String resultJson = Json.toJson(jsonMap);
		return resultJson;
	}

}
