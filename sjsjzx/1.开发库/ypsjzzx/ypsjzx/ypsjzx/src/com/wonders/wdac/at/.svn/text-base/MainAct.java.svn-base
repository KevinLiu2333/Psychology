package com.wonders.wdac.at;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * @description该类用于为主页提供数据
 * @time2016/6/24
 * @author Administrator
 *
 */
@At("/main")
@IocBean(fields = "dao")
public class MainAct {
	
	private Dao dao;
	
	@At
	@Ok("jsp:wdac.main")
	public Map<String,Object> toMain(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String, Object>();
    	
		return result;
	}

	@At
	@Ok("jsp:wdac.main.index")
	public void toIndex(){
		
	}

}
