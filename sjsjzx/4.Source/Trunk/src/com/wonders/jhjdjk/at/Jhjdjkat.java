package com.wonders.jhjdjk.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.jhjdjk.entity.JkJhjdJsjk;





@At("/jhjdjk")
@IocBean
public class Jhjdjkat {
	
	@Inject
	private Dao dao;
	
	@At
	@Ok("jsp:jsp.jh.jhjd.detail")
	public Map<String, Object> toJkJhjdJsjk(String belongju) {
		Map<String, Object>  result=new HashMap<String, Object> ();
		Criteria cri=Cnd.cri();
		cri.where().and("DUIXIANGBIANMA","=",belongju);
		cri.getOrderBy().asc("ORDER_NO");
		List<JkJhjdJsjk> contents=dao.query(JkJhjdJsjk.class,cri);
		result.put("contents", contents);
		return result;
	}
	
	
	
	
	

}
