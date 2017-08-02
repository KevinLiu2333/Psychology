package com.wonders.sjfw.fwbean;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.sjfw.platform.FwConstants;
import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwBean;
import com.wonders.wddc.suite.data.entity.DataFileCustomJsonBo;
import com.wonders.wddc.suite.data.entity.DataFileDataBo;
import com.wonders.wddc.suite.data.entity.DataFileLinkBo;
import com.wonders.wddc.suite.data.service.DataCoreService;

@IocBean
public class DataFileFwBean implements FwBean{
	@Inject
	private DataCoreService service;
	@Inject
	private Dao dao;
	@SuppressWarnings("unchecked")
	public Map<String, Object> genResult(FwParam fwParam){
		//返回结果
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		List<DataFileLinkBo> linkList = dao.query(DataFileLinkBo.class, Cnd.where("datafileid","=",fwParam.requestParamMap.get("id")));
		List<DataFileCustomJsonBo> customList = dao.query(DataFileCustomJsonBo.class, Cnd.where("datafileid","=",fwParam.requestParamMap.get("id")));
		Map<String, Object> resultMap= new LinkedHashMap<String, Object>();
		Map<String, Object> customMap = new LinkedHashMap<String, Object>();
		DataFileDataBo resultCacheMap =dao.fetch(DataFileDataBo.class, fwParam.requestParamMap.get("id"));
		int countSum = 0;
		if (resultCacheMap != null) {
			result.put(FwConstants.RS_MAP_DATA, JSONObject.fromObject(resultCacheMap.getData()));
			countSum ++;
			result.put(FwConstants.RS_MAP_COUNT, ""+countSum);
		}else {
			for(DataFileCustomJsonBo link:customList){
				customMap.put(link.getCustomkey(), link.getCustomvalue());
			}
			for (DataFileLinkBo link:linkList){
				data = (Map<String, Object>)service.gainresult(link.getDataid() ,fwParam.requestParamMap, link.getType());
				countSum += Integer.parseInt((String)data.get(FwConstants.RS_MAP_COUNT));
				if ("ERROR".equals(data.get("ERROR"))) {
					result.put("ERRORDATA", data);
				}else {
					resultMap.put("custom_json",customMap);
					resultMap.put(link.getName(),data.get(FwConstants.RS_MAP_DATA));
				}
			}
			result.put(FwConstants.RS_MAP_DATA, resultMap);
			result.put(FwConstants.RS_MAP_COUNT, ""+countSum);
		}
		
	    return result;
	}
}

