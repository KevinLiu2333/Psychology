package com.wonders.zymlgx.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.zymlgl.entity.Resource;

@IocBean
public class QueryData {
	@Inject
	private Dao dao;
	
	/**
	 * 
	 * @param fieldCode
	 * @param resourceId
	 * @return
	 */
	public List getData(final String fieldCode, final String resourceId){
		String tableName = getTableNameById(resourceId);
		if(!Strings.isEmpty(tableName)){
			String sql = "select " + fieldCode + " from " + tableName;
			Sql exeSql = Sqls.create(sql); 
			exeSql.setCallback(new SqlCallback(){
				public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
					List<String> list = new ArrayList<String>();
					while(rs.next()){ 
						list.add(rs.getString(fieldCode));
					}
					return list;
				}
			});
			dao.execute(exeSql);
			List result = (List) exeSql.getResult();
	    	return result;
		}else{
			return null;
		}
		
	}
	
	/**
	 * 根据资源主键获取表名.
	 * @param resourceId 资源主键
	 */
	public String getTableNameById(String resourceId){
		Resource resource = dao.fetch(Resource.class, Cnd.where("resourceId", "=", resourceId));
		return resource.getTableName();
	}

}
