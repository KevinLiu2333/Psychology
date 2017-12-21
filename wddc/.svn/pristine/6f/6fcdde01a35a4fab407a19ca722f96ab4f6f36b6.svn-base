package com.wonders.sjfw.platform.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.json.Json;

import com.wonders.sjfw.entity.FwConfig;
import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.sjfw.platform.spu.BaseSpu;
import com.wonders.wddc.config.WddcConstants;
/**
 * jiekou5处理过程.
 *
 */
public class Jiekou5Processor extends BaseSpu implements FwProcessor {

	
	@Override
	public String opResult(FwParam spuParam) {
		//返回结果
  	  	Map<String, Object> result = new HashMap<String, Object>();
		//结果查询
  	    Map<String,Object> resultMap =  this.exeResult(spuParam.fwInfo.getMultStatId(),spuParam.paramMap,spuParam.rsStructure);
        //拼装返回结果
        int resultcount = Integer.parseInt((String) resultMap.get(WddcConstants.RS_MAP_COUNT));
		if(resultcount > 0){
			result.put(WddcConstants.RS_MAP_DATA,resultMap.get(WddcConstants.RS_MAP_DATA));
	    }else {
            result.put(WddcConstants.RS_MAP_DATA,"无数据");
	    }
        result.put("SUCCESS","1");
        //设置meta的map信息
        Map<String,String> metaMap = new HashMap<String,String>();
        for(FwConfig fwConfig : spuParam.fwConfigList){
        	//查询结果
        	if("2".equals(fwConfig.getConfigType())){
	            metaMap.put(fwConfig.getColName().toUpperCase(),fwConfig.getColComment());
        	}
        }
        result.put("META",metaMap);
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
        Map<String,String> metaMap = new HashMap<String,String>();
        Map<String,String> dataMap = new HashMap<String,String>();
  	  	//获取配置项
		List<FwConfig> fwConfigList = dataHandle.findFwConfigByFwInfoId(spuParam.fwInfo.getFwInfoId());
        for(FwConfig fwConfig : fwConfigList){
            metaMap.put(fwConfig.getColName().toUpperCase(),fwConfig.getColName());
            dataMap.put(fwConfig.getColName().toUpperCase(),"返回值");
        }
   	 	jsonMap.put("SUCCESS","1");
   	 	jsonMap.put("DATA",dataMap);
   	 	jsonMap.put("META",metaMap);
        String resultJson = Json.toJson(jsonMap);
		return resultJson;
	}

}

