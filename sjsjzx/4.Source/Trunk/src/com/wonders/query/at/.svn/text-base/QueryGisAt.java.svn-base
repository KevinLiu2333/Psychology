package com.wonders.query.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.query.entity.CorpInfo;
import com.wonders.query.entity.PersonInfo;

@At("/query/gis")
@IocBean
public class QueryGisAt {
	@Inject
	private Dao dao;
	@Inject
	private Dao dao1;
	@Inject
	private Dao dao2;
	
	@At
	public View getCropgis(String corpinfoid){
		Map<String, Object> result = new HashMap<String, Object>();
		CorpInfo ci=dao.fetch(CorpInfo.class, Cnd.where("CORP_INFO_ID","=",corpinfoid));
		Sql sql =Sqls.create("select x,y from V_GET_XY where addr=f_format_mph_str('"+ci.getAddress()+"')");
		sql.setCallback(new getGisCallBack());
		dao2.execute(sql);
		@SuppressWarnings("unchecked")
		Map<String, Object> gis =(Map<String, Object>) sql.getResult();
		if(gis==null){
			return new ViewWrapper(new JspView("jsp.query.nogis"), null);
		}
		result.put("gis", gis);
		sql = Sqls.create("select CORP_NAME from CORP_INFO where CORP_INFO_ID ='"+corpinfoid+"'");
		sql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
					throws SQLException {
				while (rs.next()) {
					return rs.getString(1);
				}
				return "";
			}
		});
		dao.execute(sql);
		result.put("corpname", sql.getResult());
		return new ViewWrapper(new JspView("jsp.query.corpgis"), result);
	}
	@SuppressWarnings("unchecked")
	@At
	public View getPersonGis(String rid){
		Map<String, Object> result = new HashMap<String, Object>();
		PersonInfo person = dao1.fetch(PersonInfo.class,Cnd.where("RID","=",rid));
		boolean flag = true;
		if(flag&&!Strings.isEmpty(person.getJzfwid())&&person.getJzfwid().length()>=30&&!Strings.isEmpty(person.getJzdjddm())&&person.getJzdjddm().startsWith("07")){
			Sql sql =Sqls.create("select x,y from V_GET_RK_XY where mphid='"+person.getJzfwid().substring(0,30)+"'");
			sql.setCallback(new getGisCallBack());
			dao2.execute(sql);
			Map<String, Object> gis=(Map<String, Object>) sql.getResult();
			if(gis!=null){
				flag = false;
				result.put("gis", gis);
				result.put("state", "1");
			}
		}
		if(flag&&!Strings.isEmpty(person.getHjfwid())&&person.getHjfwid().length()>=30&&!Strings.isEmpty(person.getHjdjddm())&&person.getHjdjddm().startsWith("07")){
			Sql sql =Sqls.create("select x,y from V_GET_RK_XY where mphid='"+person.getHjfwid().substring(0,30)+"'");
			sql.setCallback(new getGisCallBack());
			dao2.execute(sql);
			Map<String, Object> gis=(Map<String, Object>) sql.getResult();
			if(gis!=null){
				flag = false;
				result.put("gis", gis);
				result.put("state", "1");
			}
		}
		if(flag&&!Strings.isEmpty(person.getJzdjwhdm())&&!Strings.isEmpty(person.getJzdjddm())&&person.getJzdjddm().startsWith("17")){
			flag = false;
			Map<String, Object> gis = new HashMap<String, Object>();
			gis.put("x", "0");
			gis.put("y", "0");
			result.put("gis", gis);
			result.put("jwh",person.getJzdjwhdm() );
			result.put("state", "2");
		}
		if(flag&&!Strings.isEmpty(person.getHjdjwhdm())&&!Strings.isEmpty(person.getHjdjddm())&&person.getHjdjddm().startsWith("17")){
			flag = false;
			Map<String, Object> gis = new HashMap<String, Object>();
			gis.put("x", "0");
			gis.put("y", "0");
			result.put("gis", gis);
			result.put("jwh",person.getHjdjwhdm() );
			result.put("state", "2");
		}
		result.put("xm", person.getXm());
		if(flag){
			return new ViewWrapper(new JspView("jsp.query.nogis"), null);
		}else {
			return new ViewWrapper(new JspView("jsp.query.persongis"), result);
		}
	}
	
	private class getGisCallBack implements SqlCallback{

		@Override
		public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
				throws SQLException {
			Map<String, Object> map =new HashMap<String, Object>();
			while (rs.next()) {
				
				map.put("x", rs.getString("x"));
				map.put("y", rs.getString("y"));
				return map;
			}
			return null;
		}
		
	}
}
