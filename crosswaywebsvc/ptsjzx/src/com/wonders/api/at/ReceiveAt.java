package com.wonders.api.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.api.entity.ApiReceive;
import com.wonders.api.entity.ApiReceiveColumn;
import com.wonders.tiles.extend.adaptor.util.ConUtils;

@At("/receive")
@IocBean
public class ReceiveAt {
	
	@Inject
	private Dao dao;
	
	@At
	@Ok("jsp:jsp.api.data")
	public Map<String, Object> toSeeData(HttpServletRequest request,String sjlx){
		Map<String, Object> result=new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		if(!Strings.isEmpty(sjlx)){
			ApiReceive api=dao.fetch(ApiReceive.class,sjlx);
			List<ApiReceiveColumn> columns=dao.query(ApiReceiveColumn.class, Cnd.where("APIID","=",sjlx).and("FLAG", "=", "1"));
			StringBuffer buffer=new StringBuffer();
			List<String> items=new ArrayList<String>();
			for(ApiReceiveColumn col:columns){
				buffer.append(col.getColumnname()).append(",");
				items.add(col.getColumncomment());
			}
		    Sql sql=Sqls.create("select "+buffer.toString()+"id from "+api.getTablename());
		    sql.setPager(pager);
		    sql.setCallback(new SqlCallback() {
				
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					ResultSetMetaData rsmd = rs.getMetaData();
					int col = rsmd.getColumnCount();
					List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
					while(rs.next()){
						Map<String, Object> map=new LinkedHashMap<String, Object>();
						for (int i = 1; i <= col; i++) {
							map.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
						}
						list.add(map);
					}
					return list;
				}
			});
		    dao.execute(sql);
		    List<Map<String, Object>> list=(List<Map<String, Object>>) sql.getResult();
		    sql=Sqls.create("select count(*) from "+api.getTablename());
		    sql.setCallback(new SqlCallback() {
				
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					while(rs.next()){
						return rs.getInt(1);
					}
					return 0;
				}
			});
		    dao.execute(sql);
		    pager.setRecordCount((Integer) sql.getResult());
		    result.put("items", items);
		    result.put("list", list);
		    result.put("flag", "1");
		    result.put("apiid", api.getId());
		}
		result.put("pager", pager);
		result.put("sjlx", sjlx);
		return result;
		
	}
	
	@At
	@Ok("json")
	public Object getSjlx(String dept){
		List<ApiReceive> sheets=dao.query(ApiReceive.class,Cnd.where("type", "=", "RE"));
		Map<String, Object> result =new LinkedHashMap<String, Object>();
		for(ApiReceive re:sheets){
			result.put(re.getId(), re.getServicename());
		}	
		return result;
	}
	@At
	@Ok("jsp:jsp.api.view")
	public Map<String, Object> ViewData(String dataid,String apiid){
		Map<String, Object> result=new HashMap<String, Object>();
		StringBuffer html= new StringBuffer();
		ApiReceive api=dao.fetch(ApiReceive.class,apiid);
		final List<ApiReceiveColumn> column=dao.query(ApiReceiveColumn.class,Cnd.where("APIID", "=", apiid));
		StringBuffer buffer=new StringBuffer();
		for(ApiReceiveColumn col:column){
			buffer.append(col.getColumnname()).append(",");
		}
		String ziduan=buffer.deleteCharAt(buffer.length()-1).toString();
		Sql sql=Sqls.create("select "+ziduan+" from "+api.getTablename()+" where id='"+dataid+"'");
		sql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				// TODO Auto-generated method stub
				while(rs.next()){
					StringBuffer html = new StringBuffer();
					html.append("<table class='table_multilist' width='96%' style='margin:auto'>").append("<tbody>");
					int sum=0;
					for(int i=0;i<column.size();i++){
						if(sum%2==0){
							html.append("<tr>");
						}
						html.append("<td class='label_1' align='center' width='20%'>"+column.get(i).getColumncomment()+":<td>");
						if(rs.getObject(column.get(i).getColumnname())==null){
							html.append("<td class='label_2' align='center' width='30%'><td>");
						}else {
							
								html.append("<td class='label_2' align='center' width='30%'>"+rs.getString(column.get(i).getColumnname())+"<td>");
						}
						if(sum%2!=0){
							html.append("</tr>");
						}
						sum++;
					}
			
					html.append("</tbody>").append("</table>");
					return html.toString();
				}
				return null;
			}
		});
		dao.execute(sql);
		html.append("<div><p style='text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px'><b>"+api.getServicename()+"</b></p></div>");
		html.append(sql.getResult());
		result.put("html", html.toString());
		return result;
	}
	
	/**
	 * 海豚信用
	 */
	@At
	@Ok("jsp:jsp.api.htdata")
	public Map<String, Object> toHtData(HttpServletRequest request,String apiid,String zjhm){
		Map<String, Object> result=new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		if(!Strings.isEmpty(apiid)){
			ApiReceive api=dao.fetch(ApiReceive.class,apiid);
			List<ApiReceiveColumn> columns=dao.query(ApiReceiveColumn.class, Cnd.where("APIID","=",apiid).and("FLAG", "=", "1"));
			StringBuffer buffer=new StringBuffer();
			List<String> items=new ArrayList<String>();
			for(ApiReceiveColumn col:columns){
				buffer.append(col.getColumnname()).append(",");
				items.add(col.getColumncomment());
			}
			buffer.deleteCharAt(buffer.length()-1);
			String sqlstr = "select id,"+buffer.toString()+" from "+api.getTablename();
			String sqlcou = "select count(1) from "+api.getTablename();
			if(!Strings.isEmpty(zjhm)){
				sqlstr = sqlstr + " where ZJHM ='"+zjhm+"'";
				sqlcou = sqlcou + " where ZJHM ='"+zjhm+"'";
			}
		    Sql sql=Sqls.create(sqlstr);
		    sql.setPager(pager);
		    sql.setCallback(new SqlCallback() {
				
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					ResultSetMetaData rsmd = rs.getMetaData();
					int col = rsmd.getColumnCount();
					List<ArrayList<String>> list=new ArrayList<ArrayList<String>>();
					while(rs.next()){
						 ArrayList<String> row = new ArrayList<String>();
						for (int i = 1; i <= col; i++) {
							row.add(rs.getString(rsmd.getColumnName(i)));
						}
						list.add(row);
					}
					return list;
				}
			});
		    dao.execute(sql);
		    List<ArrayList<String>> list=(List<ArrayList<String>>) sql.getResult();
		    sql=Sqls.create(sqlcou);
		    sql.setCallback(new SqlCallback() {
				
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					while(rs.next()){
						return rs.getInt(1);
					}
					return 0;
				}
			});
		    dao.execute(sql);
		    pager.setRecordCount((Integer) sql.getResult());
		    result.put("items", items);
		    result.put("list", list);
		}
		result.put("pager", pager);
		result.put("apiid", apiid);
		result.put("zjhm", zjhm);
		return result;
		
	}
	
	@At
	@Ok("json")
	public Object getHtSjlx(){
		List<ApiReceive> sheets=dao.query(ApiReceive.class,Cnd.where("type", "=", "HT"));
		Map<String, Object> result =new LinkedHashMap<String, Object>();
		for(ApiReceive re:sheets){
			result.put(re.getId(), re.getServicename());
		}	
		return result;
	}
	
	/**
	 * 信用数据统计
	 * @return
	 */
	@At
	@Ok("jsp:jsp.api.htcount")
	public Map<String, Object> toCount(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<ApiReceive> list = dao.query(ApiReceive.class, Cnd.where("TYPE", "=", "HT"));
		int count = 0;
		int all = 0;
		for(ApiReceive receive : list){
			count = dao.count(receive.getTablename());
			receive.setId(count+"");
			all += count;
		}
		result.put("all", all);
		result.put("list", list);
		return result;
		
	}
	
}
