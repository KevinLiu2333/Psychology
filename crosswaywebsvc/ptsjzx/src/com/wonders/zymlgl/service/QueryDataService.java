package com.wonders.zymlgl.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.tiles.authority.entity.User;

@IocBean
public class QueryDataService {
	@Inject
	private Dao	dao;

	public List<Map<String, Object>> queryDataByTableName(String tableName, String uploadUser) {
		String querySql = "select UPLOAD_USER_ID,UPLOAD_DATE,VERSION_NUM from " + tableName.toUpperCase();
		if (!Strings.isEmpty(uploadUser)) {
			User user = dao.fetch(User.class, Cnd.where("displayName", "=", uploadUser));
			querySql += " where UPLOAD_USER_ID=" + user.getUserId();
		}
		Sql exeSql = Sqls.create(querySql);
		exeSql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
				Map<String, Object> map;
				while (rs.next()) {
					map = new HashMap<String, Object>();
					map.put("userId", rs.getObject(1));
					map.put("uploadDate", rs.getObject(2));
					map.put("versionNum", rs.getObject(3));
					resultList.add(map);
				}
				return resultList;
			}
		});
		dao.execute(exeSql);
		List<Map<String, Object>> result = (List<Map<String, Object>>) exeSql.getResult();
		return result;
	}
}
