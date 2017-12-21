package com.wonders.api.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.api.entity.ApiLogWebService;
import com.wonders.api.entity.ApiService;
@IocBean
public class ApiWarning {
	
	@Inject
	private Dao dao;
	
	public List<ApiService> getApi(){
		List<ApiService> result = new ArrayList<ApiService>();
		List<ApiService> list = dao.query(ApiService.class, Cnd.where("VALID","=","1").and("STATUS", "=", "1"));
		int count = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String data = format.format(new Date());
		for(ApiService api : list){
			count = dao.count(ApiLogWebService.class,Cnd.where("CALL_SERVICE_ID", "=", api.getId()).and("to_char(CALL_DATE,'yyyyMMdd')", "=", data));
			if(count<1){
				result.add(api);
			}
		}
		return result;
		
		
	}
}
