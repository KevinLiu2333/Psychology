package com.wonders.echarts.at;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import com.wonders.echarts.entity.EchartsData;

@IocBean
@At("/echarts")
@Filters
public class EchartsAt {

	@Inject
	private Dao dao;
	@At
	@Ok("json")
	public Object queryjson(String theme_id){
		Criteria cri = Cnd.cri();
		cri.where().and("THEME_ID","=",theme_id);
		cri.getOrderBy().desc("CREATE_DATE");
		List<EchartsData> list = dao.query(EchartsData.class, cri,dao.createPager(1, 1));
		if(list!=null&&list.size()>0){
			JSONObject json = JSONObject.fromObject(list.get(0).getDataJson());
			return json;
		}
		return JSONObject.fromObject("{}");
	}
	@At
	@Ok("json")
	public Object piequery(String theme_id){
		Criteria cri = Cnd.cri();
		cri.where().and("THEME_ID","=",theme_id);
		cri.getOrderBy().desc("CREATE_DATE");
		List<EchartsData> list = dao.query(EchartsData.class, cri,dao.createPager(1, 1));
		if(list!=null&&list.size()>0){
			JSONObject json = JSONObject.fromObject(list.get(0).getDataJson());
			List<String> key = new ArrayList<String>();
			List<String> value = new ArrayList<String>();
			for(Iterator<?> iter = json.keys(); iter.hasNext();){
				String keytemp = (String)iter.next();
				key.add(keytemp);
				value.add(json.getString(keytemp));
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("key", key);
			result.put("value", value);
			Object obj = JSONObject.fromObject(result);
			return JSONObject.fromObject(result);
		}
		return new JSONObject();
	}
}
