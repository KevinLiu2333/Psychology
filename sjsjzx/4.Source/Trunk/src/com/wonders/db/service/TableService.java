package com.wonders.db.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.Service;

import com.wonders.db.entity.ColEntity;
import com.wonders.db.entity.TableEntity;

@IocBean(fields = "dao")
public class TableService extends Service {

	public TableEntity getTableInfo(final String tableName)
	{
		TableEntity entity=new TableEntity();
		entity.setName(tableName);
		String chinesestr="select comments from all_tab_comments where table_name = upper('"+tableName+"')";
		Sql chinesesql=Sqls.create(chinesestr);
		chinesesql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				String result="";
				while (rs.next()){
					result = rs.getString(1);
				}
				
				return result;
			}
		});
		dao().execute(chinesesql);
		entity.setChinesename((String) chinesesql.getResult());
		String countstr="select count(*) from "+tableName;
		Sql countsql=Sqls.create(countstr);
		countsql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				String result="";
				while (rs.next()) {
					result=rs.getString(1);
				}
				
				return result;
			}
		});
		dao().execute(countsql);
		entity.setCounts((String) countsql.getResult());
		return entity;
	}
	
	public List<Map<String, String>> getcoldata(final String viewName) {
		String colStr = "select "
				+ "t.COLUMN_NAME, "
				+ "t.DATA_TYPE, "
				+ "t.DATA_LENGTH, "
				+ "C.COMMENTS "
				+ "from user_tab_columns t ,all_col_comments c "
				+ "where "
				+ " T.COLUMN_NAME = C.COLUMN_NAME and T.TABLE_NAME = C.TABLE_NAME "
				+ "and t.table_name = upper('" + viewName + "')";
		Sql colSql = Sqls.create(colStr);
		colSql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				List<ColEntity> result = new ArrayList<ColEntity>();
				while (rs.next()) {
					ColEntity colEntity=new ColEntity();
					colEntity.setColumnName(rs.getString("COLUMN_NAME"));
					colEntity.setComments(rs.getString("COMMENTS"));
					colEntity.setDataType(rs.getString("DATA_TYPE"));
					colEntity.setDataLength(rs.getString("DATA_LENGTH"));
					if(colEntity.getDataType().equals("DATE")||colEntity.getDataType().equals("date"))
					{
						colEntity.setDataType("日期");
					}
					else if (colEntity.getDataType().equals("number")||colEntity.getDataType().equals("NUMBER")) {
						colEntity.setDataType("数字");
					}else if (colEntity.getDataType().equals("char")||colEntity.getDataType().equals("CHAR")) {
						colEntity.setDataType("字典");
					}else if (colEntity.getDataType().equals("varchar2")||colEntity.getDataType().equals("VARCHAR2")) {
						colEntity.setDataType("字符");
					}
					result.add(colEntity);
				}
				return result;
			}
		});
		dao().execute(colSql);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> colMapList = (List<Map<String, String>>) colSql
				.getResult();
		return colMapList;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getTablenameByprefix(String prefix)
	{
		String sqlStr="SELECT t.table_name FROM USER_TABLES t where t.table_name like '"+prefix+"%'";
		Sql sql=Sqls.create(sqlStr);
		sql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				List<String> tablenames=new ArrayList<String>();
				while (rs.next()) {
					tablenames.add(rs.getString(1));
				}
				return tablenames;
			}
		});
		dao().execute(sql);
		return (List<String>) sql.getResult();
	}
}
