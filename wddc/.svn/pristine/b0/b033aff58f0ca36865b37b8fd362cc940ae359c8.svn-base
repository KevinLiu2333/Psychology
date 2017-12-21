package com.wonders.sjfw.platform.handle;

import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import com.wonders.sjfw.platform.jk.FwHandle;
/**
 * 
 *错误处理的操作 .
 *
 */
@IocBean(fields = "dao")
public class ErrorHandle implements FwHandle{

	public Dao dao;
    
	/**
	 * 生成错误的代码.
	 * @param format 代码格式
	 * @param errorCode 错误代码
	 * @param errorString 错误描述
	 * @return
	 */
	public String errorString(String format,String errorCode,String errorString){
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("ERROR_CODE",errorCode);
        errorMap.put("ERROR_MEMO",errorString);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("SUCCESS","0");
        result.put("ERROR",errorMap);
        return  Json.toJson(result);
    }
}
