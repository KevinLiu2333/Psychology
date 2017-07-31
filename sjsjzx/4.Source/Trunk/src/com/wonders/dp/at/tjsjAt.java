package com.wonders.dp.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.dp.entity.TjJsonInfo;
import com.wonders.dp.entity.TjSqlInfo;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.extend.adaptor.util.ConUtils;

@At("/tjsj")
@IocBean(fields = "dao")
public class tjsjAt {
	   private Dao dao;
	   
	   /**
	    * 封装json
	    * **/
	 public String getjson(String sql,final String diccode){
	    	try {
//	        	String sql="select nation,count(1) from xj_person  group by  nation";
	        	 Sql exeTotalSql = Sqls.create(sql);
	     
	        	 exeTotalSql.setCallback(new SqlCallback(){
	                 public Object invoke(Connection conn,ResultSet rs,Sql sql) {
	                	 Map<Object, Object> jsonMap=new HashMap<Object, Object>();
	                    try {
	                    	 ResultSetMetaData rsmd = rs.getMetaData();
	                         while (rs.next()){
	                        	 String title=DicDataUtils.getInstance().getDicData(Integer.valueOf(diccode), (String) rs.getObject(1));
	                        	 if(StringUtils.isEmpty(title)){
	                        		 	title="其他";
	                        	 }
	                            	 jsonMap.put(title, rs.getObject(2));
	                         }
	    				} catch (Exception e) {
	    					// TODO: handle exception
	    				 e.getMessage();
	    				}
	                    return jsonMap;
	                 }
	             });
	        	// DicDataUtils.getInstance().getDicData(3001, 1);
	        	 dao.execute(exeTotalSql);
	        	 Map<Object, Object> map =(Map<Object, Object>) exeTotalSql.getResult();
	        	 String json=Json.toJson(map);
//	        	 System.out.println(map);
//	        	 System.out.println(json);
//	        	 System.out.println(Json.fromJson(json ));
//	        	 Map<Object, Object> maps=(Map<Object, Object>) Json.fromJson(json );
	        	 return json;
	           	} catch (Exception e) {
	    		}
			return null;
	    	
	    }
	 
	 /***
	  * 生成json  并保存
	  * */
	    @At()
	    @Ok("redirect:/sqlinfo/tosqlinfo")
	    public void savejson(HttpServletRequest request, HttpSession session){
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    	Criteria cri =  ConUtils.makeCri(request);
	    	cri.where().and("state","=","1");
	    	List<TjSqlInfo> list = dao.query(TjSqlInfo.class,cri);
	    	for(int i=0;i<list.size();i++){
	    		TjSqlInfo info=list.get(i);
	    		System.out.println("info.getSqlvalue()  :   "+info.getSqlvalue());
	    		String json=getjson(info.getSqlvalue(),info.getDiccode());
	    		System.out.println(json);
	    		Criteria cri1 =  ConUtils.makeCri(request);
		    	cri1.where().and("sql_code","=",info.getSqlcode());
		    	cri1.where().and("to_char(create_date,'yyyy-MM-dd')","=",sdf.format(new Date()));
		    	TjJsonInfo ji=dao.fetch(TjJsonInfo.class,cri1);
		    	
		    	
	    		TjJsonInfo jsonInfo=new TjJsonInfo();
	    		jsonInfo.setSqlid(info.getSqlid());
	    		jsonInfo.setSqlcode(info.getSqlcode());
	    		jsonInfo.setJsonvalue(json);
	    		jsonInfo.setCreatedate(new Date());
	    		jsonInfo.setUpdatedate(new Date());
	    		jsonInfo.setQuerydate(new Date());
	    		if (ji==null) {
	    			dao.insert(jsonInfo);
				}else {
					jsonInfo.setJsonid(ji.getJsonid());
					jsonInfo.setCreatedate(ji.getCreatedate());
					dao.update(jsonInfo);
				}
	    		
	    	}
	    	
	    }
	   
	    @At
	    @Ok("json")
	    public ArrayList<ArrayList<Object>> queryjson(@Param("::info.") TjJsonInfo info ,HttpServletRequest request, HttpSession session){
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    	ArrayList<ArrayList<Object>> result=new ArrayList<ArrayList<Object>>();
	    	Criteria cri =  ConUtils.makeCri(request);
	    	cri.where().and("sql_code","=",info.getSqlcode());
	    	cri.where().and("to_char(query_date,'yyyy-MM-dd')","=",sdf.format(new Date()));
	    	cri.getOrderBy().desc("query_date");
	    	List<TjJsonInfo> jsonlist=dao.query(TjJsonInfo.class, cri);
	    	
	    	if (jsonlist==null) {
			}else {
				TjJsonInfo jsonInfo=jsonlist.get(0);
				ArrayList<Object> title=new ArrayList<Object>();
				ArrayList<Object> data=new ArrayList<Object>();	
				
				Map<Object, Object> maps=(HashMap<Object, Object>) Json.fromJson(jsonInfo.getJsonvalue() );
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
	    @Ok("json")
	    public ArrayList<Object> piequery(@Param("::info.") TjJsonInfo info ,HttpServletRequest request, HttpSession session){
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    	ArrayList<Object> result=new ArrayList<Object>();
	    	Criteria cri =  ConUtils.makeCri(request);
	    	cri.where().and("sql_code","=",info.getSqlcode());
	    	cri.where().and("to_char(query_date,'yyyy-MM-dd')","=",sdf.format(new Date()));
	    	cri.getOrderBy().desc("query_date");
	    	List<TjJsonInfo> jsonlist=dao.query(TjJsonInfo.class, cri);
	    	if (jsonlist==null) {
			}else {
				TjJsonInfo jsonInfo=jsonlist.get(0);
				Map<Object, Object> maps=(HashMap<Object, Object>) Json.fromJson(jsonInfo.getJsonvalue() );
				for (Map.Entry<Object, Object> m : maps.entrySet()) {
					
					Map<Object, Object> hashmap=new HashMap<Object, Object>();
					hashmap.put("value", m.getValue());
					hashmap.put("name", m.getKey());
					result.add(hashmap);
				}
			}
	    	return result;
	    }
	    
	    @At()
	    @Ok("jsp:tjsj.testjs")
	    public void query(){
	    }
}
