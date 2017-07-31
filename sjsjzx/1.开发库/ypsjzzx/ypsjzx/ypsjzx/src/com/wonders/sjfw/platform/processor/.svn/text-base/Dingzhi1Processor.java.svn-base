package com.wonders.sjfw.platform.processor;

import java.util.HashMap;
import java.util.Map;

import org.nutz.lang.Strings;

import com.wonders.sjfw.platform.FwConstants;
import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.sjfw.platform.spu.BaseSpu;

public class Dingzhi1Processor extends BaseSpu implements FwProcessor {

	@Override
	public String opResult(FwParam spuParam) {
		//返回结果
  	  	Map<String, Object> result = new HashMap<String, Object>();
		//结果查询--优先传入参数--随后是固定参数
  	  	String rsStructure = spuParam.rsStructure;
  	  	if(Strings.isBlank(rsStructure)){
  	  		rsStructure = spuParam.fwInfo.getResultType();
  	  	}
  	  	
  	    Map<String,Object> resultMap =  this.exeResult(spuParam.fwInfo.getMultStatId(),spuParam.paramMap,rsStructure);
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
		return FwProcessor.VAILD_PARAM_PASS;
	}

	@Override
	public String opExample(FwParam spuParam) {
		return spuParam.fwInfo.getConfigMemo();
	}
}

