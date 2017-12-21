package com.wonders.sjfw.platform.processor;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;

import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.sjfw.platform.spu.BaseSpu;
import com.wonders.wddc.tiles.dic.DicDataUtils;

public class Biaozhun2Processor extends BaseSpu implements FwProcessor {

	
	@Override
	public String opResult(FwParam spuParam) {
		//返回结果
  	  	Map<String, Object> result = new HashMap<String, Object>();
		//结果查询
        Map<String,String> dicMap = DicDataUtils.getInstance().getDic(Integer.valueOf(spuParam.fwInfo.getConfigUrl()));
        //拼装返回结果
        int resultcount = 0;
		if(dicMap!= null && dicMap.size() >0){
			resultcount = dicMap.size();
			result.put("DATA",dicMap);
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
        Map<String, Object> jsonMap = new HashMap<String, Object>();
   	 	jsonMap.put("SUCCESS","1");
   	 	jsonMap.put("DATA","{字典代码1：字典描述1,字典代码2：字典描述2}");
        String resultJson = Json.toJson(jsonMap);
		return resultJson;
	}
}

