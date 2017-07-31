package com.wonders.smrz;

import java.util.HashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/smyz")
@IocBean
public class SmyzAt {
	
	@Inject
	public  SmrzService smrzService;
	
	@At
	@Ok("json")
	public Map<String, Object> test(){
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println("kk:"+smrzService);
		//数据对象
		//result.put("rkk", smrzService.smyz("", ""));
		return result;
		
	}

}
