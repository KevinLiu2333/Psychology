package com.wonders.sjfw.platform.processor;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;

import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.sjfw.platform.spu.BaseSpu;
/**
 * 报表处理过程
 * @author wonders
 *
 */
public class Dingzhi3Processor extends BaseSpu implements FwProcessor{

	@Override
	public String opExample(FwParam spuParam) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("SUCCESS","1");
		Map<String, String> re = new HashMap<String, String>();
		re.put("state", "success");
		re.put("result", "&lt;table&gt;&lt;/table&gt;");
		result.put("DATA", re);
		String resultString = Json.toJson(result);
		return resultString;
	}

	@Override
	public String opResult(FwParam spuParam) {
		//返回结果
  	  	Map<String, Object> result = new HashMap<String, Object>();
  	  	//返回结果
  	  	int count=0;
  	  	try {
			Map<String, Object> re = reportExe(spuParam.requestParamMap.get("id"), spuParam.requestParamMap.get("type"), spuParam.requestParamMap);
			result.put("DATA",re);
			result.put("SUCCESS","1");
			count=1;
  	  	} catch (Exception e) {
			e.printStackTrace();
			if(spuParam.requestParamMap.get("id")==null||spuParam.requestParamMap.get("type")==null){
				result.put("DATA","参数错误！");
			}else {
				result.put("DATA","系统错误！");
			}
			result.put("FAILED","0");
		}
	    String resultString = Json.toJson(result);
	    this.genSeccessLog(spuParam, resultString, count);
		return resultString;
	}

	@Override
	public String validParam(FwParam spuParam) {
		return this.validParamDefault(spuParam);
	}

}
