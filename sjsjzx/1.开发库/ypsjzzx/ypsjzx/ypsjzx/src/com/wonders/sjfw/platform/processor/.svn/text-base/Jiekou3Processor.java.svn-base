package com.wonders.sjfw.platform.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.json.Json;

import com.wonders.sjfw.entity.FwConfig;
import com.wonders.sjfw.platform.FwConstants;
import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.sjfw.platform.spu.BaseSpu;

public class Jiekou3Processor extends BaseSpu implements FwProcessor {

	
	@Override
	public String opResult(FwParam spuParam) {
		//返回结果
  	  	Map<String, Object> result = new HashMap<String, Object>();
		//结果查询
  	  Map<String,Object> resultMap =  this.exeResult(spuParam.fwInfo.getMultStatId(),spuParam.paramMap,spuParam.rsStructure);
      //拼装返回结果
      int resultcount = Integer.parseInt((String)resultMap.get(FwConstants.RS_MAP_COUNT));
		if(resultcount > 0){
			result.put(FwConstants.RS_MAP_DATA,resultMap.get(FwConstants.RS_MAP_DATA));
	    }else {
          result.put(FwConstants.RS_MAP_DATA,"无数据");
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
	     Map<String,String> dataMap = new HashMap<String,String>();
	  	 //获取配置项
	     List<FwConfig> fwConfigList = dataHandle.findFwConfigByFwInfoId(spuParam.fwInfo.getFwInfoId());
	     for(FwConfig fwConfig : fwConfigList){
	    	 if("2".equals(fwConfig.getConfigType())){
         		dataMap.put((fwConfig.getColName()+"_"+fwConfig.getOpType()).toUpperCase(),"返回值");
         	}
         	if("5".equals(fwConfig.getConfigType())){
         		dataMap.put(fwConfig.getColName().toUpperCase(),"返回值");
         	}
	    }
	    jsonMap.put("SUCCESS","1");
	    jsonMap.put("DATA",dataMap);
        String resultJson = Json.toJson(jsonMap);
		return resultJson;
	}

}

