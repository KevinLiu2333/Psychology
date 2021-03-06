package com.wonders.sjfw.fwbean;


import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwBean;
import com.wonders.wddc.suite.data.entity.DataFileDataBo;
import com.wonders.wddc.suite.data.entity.DataFileInfoBo;
import com.wonders.wddc.suite.data.service.DataFileCoreService;

@IocBean
public class DataFileFwBean implements FwBean{
	@Inject
	private DataFileCoreService dataFileCoreService;
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> genResult(FwParam fwParam){
		//返回结果
		Map<String, Object> result = new LinkedHashMap<String, Object>();
	
		DataFileDataBo resultDataCache =dataFileCoreService.getDataFileDataBo(fwParam.requestParamMap.get("id"));
		
		DataFileInfoBo dataFileInfoBo = dataFileCoreService.getDataFileInfoBo(fwParam.requestParamMap.get("id"));

		//'0':'不缓存','1':'缓存到数据库','2':'缓存到文件'
		if("1".equals(dataFileInfoBo.getCacheType())){
			if(resultDataCache !=null){
				result = dataFileCoreService.getCacheType1(resultDataCache);
			}else{
				result = dataFileCoreService.genDataFileResult(fwParam.requestParamMap.get("id"),fwParam.paramMap);
			}
		}else if("2".equals(dataFileInfoBo.getCacheType())){
			//拼装文件路径
			File cacheFile = dataFileCoreService.getCacheFile(dataFileInfoBo);
			
			if(cacheFile.exists()){
				result = dataFileCoreService.getCacheType2(fwParam.requestParamMap.get("id"),cacheFile);
			}else{
				result = dataFileCoreService.genDataFileResult(fwParam.requestParamMap.get("id"),fwParam.paramMap);
			}
		}else{
			result = dataFileCoreService.genDataFileResult(fwParam.requestParamMap.get("id"),fwParam.paramMap);
		}
		
	    return result;
	}

	
	
}

