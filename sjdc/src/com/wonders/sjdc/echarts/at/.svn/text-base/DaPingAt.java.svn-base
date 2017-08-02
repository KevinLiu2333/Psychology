package com.wonders.sjdc.echarts.at;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.sjdc.echarts.entity.TjJsonInfo;
import com.wonders.wddc.tiles.adaptor.util.ConUtils;

@IocBean
@At("/dp")
public class DaPingAt {
	@Inject
	private Dao dao;

	@At
	@Ok("jsp:jsp.dp.index")
	@Filters
	public Map<String, Object> toDpIndex() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	/**
	 * 大屏：法人信息统计.
	 * 
	 * @return
	 */
	@At
	@Ok("jsp:jsp.dp.corp_info")
	@Filters
	public Map<String, Object> toCorpInfo() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	/**
	 * 大屏：房屋信息统计.
	 * 
	 * @return
	 */
	@At
	@Ok("jsp:jsp.dp.house_info")
	@Filters
	public Map<String, Object> toHouseInfo() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	/**
	 * 大屏：交换监控.
	 */
	@At
	@Ok("jsp:jsp.dp.ctl")
	@Filters
	public Map<String, Object> toCtl(){
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	@At
	@Filters
	@Ok("json")
	public ArrayList<ArrayList<Object>> queryjson(
			@Param("::info.") TjJsonInfo info, HttpServletRequest request,
			HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
		Criteria cri = ConUtils.makeCri(request);
		cri.where().and("sql_code", "=", info.getSqlcode());
//		cri.where().and("to_char(query_date,'yyyy-MM-dd')", "=",
//				sdf.format(new Date()));
		cri.getOrderBy().desc("query_date");
		List<TjJsonInfo> jsonlist = dao.query(TjJsonInfo.class, cri);

		if (jsonlist == null||jsonlist.size()==0) {
		} else {
			TjJsonInfo jsonInfo = jsonlist.get(0);
			ArrayList<Object> key = new ArrayList<Object>();
			ArrayList<Object> value = new ArrayList<Object>();

			Map<Object, Object> maps = (HashMap<Object, Object>) Json.fromJson(jsonInfo.getJsonvalue());
			for (Map.Entry<Object, Object> m : maps.entrySet()) {
				key.add(m.getKey());
				value.add(m.getValue());
			}
			result.add(key); 
			result.add(value);
		}

		return result;
	}

	@At
	@Filters
	@Ok("json")  
	public ArrayList<Object> piequery(@Param("::info.") TjJsonInfo info,
			HttpServletRequest request, HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Object> result = new ArrayList<Object>();
		Criteria cri = ConUtils.makeCri(request);
		cri.where().and("sql_code", "=", info.getSqlcode());
		//cri.where().and("to_char(query_date,'yyyy-MM-dd')", "=",
		//		sdf.format(new Date()));
		cri.getOrderBy().desc("query_date");
		List<TjJsonInfo> jsonlist = dao.query(TjJsonInfo.class, cri);
		if (jsonlist != null && jsonlist.size() > 0) {
			TjJsonInfo jsonInfo = jsonlist.get(0);
			Map<Object, Object> maps = (HashMap<Object, Object>) Json.fromJson(jsonInfo.getJsonvalue());
			for (Map.Entry<Object, Object> m : maps.entrySet()) {
				Map<Object, Object> hashmap = new HashMap<Object, Object>();
				hashmap.put("value", m.getValue());
				hashmap.put("name", m.getKey());
				result.add(hashmap);
			}
		}
		return result;
	}
	
}
