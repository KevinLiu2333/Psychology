package com.wonders.ws.dao;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.api.entity.ApiService;
import com.wonders.api.entity.ApiServiceUser;

@IocBean
public class KeyChecker {

	public static String OVER_TIME = "{ errorMsg:\"key已过期，需重新申请！\" }";
	public static String ERROR_KEY = "{ errorMsg:\"key无效或该服务已被管理员关闭！\" }";
	@Inject
	private Dao dao;
	
	public ApiServiceUser keyckeck(String key,String method){
		Criteria cri=Cnd.cri();
		cri.where().and("KEY","=",key);
		cri.where().and("METHOD","=",method);
		cri.getOrderBy().desc("APPLY_DATE");
		List<ApiServiceUser> list=dao.query(ApiServiceUser.class,cri);
		if(list==null||list.size()==0){
			return null;
		}
		else {
			ApiService service = dao.fetch(ApiService.class,list.get(0).getServiceId());
			if("0".equals(service.getStatus())){
				return null;
			}
			return list.get(0);
		}
	}
}
