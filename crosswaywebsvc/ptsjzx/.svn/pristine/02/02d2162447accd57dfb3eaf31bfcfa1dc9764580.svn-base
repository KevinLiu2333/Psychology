package com.wonders.dp.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.dp.entity.TjJsonInfo;
import com.wonders.echarts.entity.EchartsData;
import com.wonders.tiles.extend.adaptor.util.ConUtils;

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
			ArrayList<Object> title = new ArrayList<Object>();
			ArrayList<Object> data = new ArrayList<Object>();

			Map<Object, Object> maps = (HashMap<Object, Object>) Json
					.fromJson(jsonInfo.getJsonvalue());
			for (Map.Entry<Object, Object> m : maps.entrySet()) {
				title.add(m.getKey());
				data.add(m.getValue());
			}
			result.add(title);
			result.add(data);
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
			Map<Object, Object> maps = (HashMap<Object, Object>) Json
					.fromJson(jsonInfo.getJsonvalue());
			for (Map.Entry<Object, Object> m : maps.entrySet()) {
				Map<Object, Object> hashmap = new HashMap<Object, Object>();
				hashmap.put("value", m.getValue());
				hashmap.put("name", m.getKey());
				result.add(hashmap);
			}
		}
		return result;
	}
	
	
	
	
	@At
	@Filters
	@Ok("json")  
	public ArrayList<Object> piequery1(@Param("::info.") TjJsonInfo info,
			HttpServletRequest request, HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Object> result = new ArrayList<Object>();
		Criteria cri = ConUtils.makeCri(request);
		cri.where().and("THEME_ID", "=", info.getSqlcode());
		//cri.where().and("to_char(query_date,'yyyy-MM-dd')", "=",
		//		sdf.format(new Date()));
		cri.getOrderBy().desc("CREATE_DATE");
		List<EchartsData> jsonlist = dao.query(EchartsData.class, cri);
		if (jsonlist != null && jsonlist.size() > 0) {
			EchartsData jsonInfo = jsonlist.get(0);
			Map<Object, Object> maps = (HashMap<Object, Object>) Json
					.fromJson(jsonInfo.getDataJson());
			for (Map.Entry<Object, Object> m : maps.entrySet()) {
				Map<Object, Object> hashmap = new HashMap<Object, Object>();
				hashmap.put("value", m.getValue());
				hashmap.put("name", m.getKey());
				result.add(hashmap);
			}
		}
		return result;
	}
	@At
	@Filters
	@Ok("json")  
	public ArrayList<Object> piequery2(@Param("::info.") TjJsonInfo info,String mark,
			HttpServletRequest request, HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Object> result = new ArrayList<Object>();
		Criteria cri = ConUtils.makeCri(request);
		cri.where().and("THEME_ID", "=", info.getSqlcode());
		if(mark!=null || mark.isEmpty())
		{
			cri.where().and("MARK", "=", mark);
		}
		//cri.where().and("to_char(query_date,'yyyy-MM-dd')", "=",
		//		sdf.format(new Date()));
		cri.getOrderBy().desc("CREATE_DATE");
		List<EchartsData> jsonlist = dao.query(EchartsData.class, cri);
		if (jsonlist != null && jsonlist.size() > 0) {
			EchartsData jsonInfo = jsonlist.get(0);
			Map<Object, Object> maps = (HashMap<Object, Object>) Json
					.fromJson(jsonInfo.getDataJson());
			for (Map.Entry<Object, Object> m : maps.entrySet()) {
				Map<Object, Object> hashmap = new HashMap<Object, Object>();
				hashmap.put("value", m.getValue());
				hashmap.put("name", m.getKey());
				result.add(hashmap);
			}
		}
		return result;
	}
	private class getOneStringCallBack implements SqlCallback{
		@Override
		public Object invoke(Connection conn, ResultSet rs, Sql sql)
				throws SQLException {
			if(rs.next())
			{
				return rs.getString(1);
			}
			return null;
		}
		
	}
}
