package com.wonders.tiles.query.detailquery.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.tiles.query.detailquery.DetailQueryConstants;
import com.wonders.tiles.query.detailquery.entity.QueryColumn;
import com.wonders.tiles.query.detailquery.entity.QuerySave;
import com.wonders.tiles.query.detailquery.entity.QueryTheme;
import com.wonders.util.NutzSqlUtil;

@IocBean
public class DetailQueryService{
	
	@Inject
	private Dao dao;
	private Log log = Logs.get();

	/**
	 * 获取全部主题分类列表
	 * @return QueryTheme对象列表
	 */
	public List<QueryTheme> queryCatalogList(){
		Sql sql = Sqls.create("select CATALOGS from QUERY_STAT_THEMES group by CATALOGS");
		try{
			//设置sql回调，以实现group by形式的查询
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection con, ResultSet rs, Sql sql)
						throws SQLException {
					List<QueryTheme> resultList = new ArrayList<QueryTheme>();
					while(rs.next()){
						QueryTheme queryTheme = new QueryTheme();
						queryTheme.setCatalog(rs.getString("CATALOGS"));
						resultList.add(queryTheme);
					}
					return resultList;
				}
			});
			dao.execute(sql);
			return sql.getList(QueryTheme.class);
		}catch (Exception e) {
			log.error("执行数据库查询语句出错，查询语句："+sql);
		}
		return null;
	}

	/**
	 * 根据分类获取主题列表
	 * @param catalog 分类
	 * @return QueryTheme对象列表
	 */
	public List<QueryTheme> queryThemeListByCatalog(String catalog){
		if(Strings.isBlank(catalog)){
			return dao.query(QueryTheme.class, null);
		}
		return dao.query(QueryTheme.class, Cnd.where("catalog", "=", catalog));
	}

	/**
	 * 根据名称获取查询定义
	 * @param saveId 保存定义的主键
	 * @return 
	 */
	public List<QuerySave> querySaveByName(String saveName){
		return dao.query(QuerySave.class, Cnd.where("name", "like", "%"+saveName+"%"));
	}
	/**
	 * 根据分类、名称获取查询定义
	 * @param saveName
	 * @return
	 */
	public List<QuerySave> querySave(String saveName,String [] themeIds){
		Criteria cri = Cnd.cri();
		if(!Strings.isBlank(saveName)){
			cri.where().and("name", "like", "%"+saveName+"%");
		}
		if(themeIds!=null && themeIds.length>0){
			cri.where().andIn("themeId", themeIds);
		}
		cri.getOrderBy().desc("queryCount");
		return dao.query(QuerySave.class, cri);
	}
	/**
	 * 获取全部主题列表
	 * @returnQueryTheme 对象列表
	 */
	public List<QueryTheme> queryAllThemeList(){
		//返回全部列表
		return dao.query(QueryTheme.class, null);
	}

	/**
	 * 根据主题id串接字符串查询主题列表
	 * @param themeIds 主题id数组
	 * @return QueryTheme对象列表
	 */
	public List<QueryTheme> queryThemeList(String [] themeIds){
		Criteria cri = Cnd.cri();
		cri.where().andIn("themeId", themeIds);
		return dao.query(QueryTheme.class, cri);
	}

	/**
	 * 根据主题主键获取主题所属的已定义查询
	 * @param themeIds 主题主键字符串（用逗号隔开）
	 * @return
	 */
	public List<QueryColumn> queryPkByThemeId(String themeId){
		Criteria cri = Cnd.cri();
		cri.where().and("isPk", "=", "1").and("themeId", "=", themeId);
		return dao.query(QueryColumn.class, cri);
	}
	
	/**
	 * 根据主题主键获取主题所属的已定义查询
	 * @param themeIds 主题主键数组
	 * @return
	 */
	public List<QuerySave> querySaveListByTheme(String [] themeIds){
		Criteria cri = Cnd.cri();
		cri.where().andIn("themeId", themeIds);
		cri.getOrderBy().desc("queryCount");
		return dao.query(QuerySave.class, cri);
	}
	
	/**
	 * 根据主键获取查询定义
	 * @param saveId 保存定义的主键
	 * @return 
	 */
	public QuerySave querySaveById(String saveId){
		return dao.fetch(QuerySave.class, saveId);
	}

	/**
	 * 根据主键获取查询定义
	 * @param refIds 保存定义的主键
	 * @return 
	 */
	public List<QuerySave> querySaveByRefId(String [] refIds){
		Criteria cri = Cnd.cri();
		cri.where().andIn("saveId", refIds);
		cri.getOrderBy().desc("queryCount");
		return dao.query(QuerySave.class, cri);
	}
	
	/**
	 * 根据列编号查询列对象
	 * @param colIds 列编号
	 * @return QueryColumn对象列表
	 */
	public List<QueryColumn> queryColumnListByColId(String [] colIds){
		Criteria cri = Cnd.cri();
		cri.where().andIn("colId",colIds);
		cri.getOrderBy().asc("colId");
		return dao.query(QueryColumn.class, cri);
	}
	
	

	/**
	 * 用户保存的查询全部
	 * @return
	 */
	public List<QuerySave> querySaveList(){
		Criteria cri = Cnd.cri();
		cri.getOrderBy().desc("queryCount");
		return dao.query(QuerySave.class, cri) ;
	}
	
	
	/**
	 * 根据主键查询主题
	 * @param themeId 主题主键
	 * @return
	 */
	public QueryTheme getQueryThemeById(String themeId){
		return dao.fetch(QueryTheme.class, themeId);
	}
	

	/**
	 * 根据主键查询定义字段
	 * @param themeId 主题主键
	 * @return
	 */
	public List<QueryColumn> queryColumnList(String themeId){
		return dao.query(QueryColumn.class, Cnd.where("themeId", "=", themeId));
	}


	/**
	 * 新增一条查询定义
	 * @param query 定义对象
	 */
	public void addQuerySave(QuerySave query){
		dao.insert(query);
		
	}

	/**
	 * 修改一条查询定义
	 * @param query 定义对象
	 */
	public void updateQuerySave(QuerySave query){
		dao.update(query);	
		
	}
	
	/**
	 * 分页查询结果
	 * @param 
	 * @return
	 */
	public List queryFineResultList(String querySql,Pager pager){	
		try{
			Sql sql = Sqls.create(querySql);
			sql.setPager(pager);
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection con, ResultSet rs, Sql sql)
						throws SQLException {
					return (List)NutzSqlUtil.rs2MapList(rs);
				}
			});
			dao.execute(sql);
			List list = sql.getList(List.class);
			return list;
		}catch (Exception e) {
			log.error("执行数据库查询语句出错，查询语句："+querySql);
		}
		return null;
	}
	/**
	 * 查询记录数.
	 * @param 
	 * @return
	 */
	public int queryCount(String countSql){
		try{
			Sql sql = Sqls.create(countSql);
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection con, ResultSet rs, Sql sql)
						throws SQLException {
					int count = 0;
					while(rs.next()){
						count = rs.getInt("count");
					}
					return count;
				}
			});
			dao.execute(sql);
			return sql.getInt();
		}catch (Exception e) {
			log.error("查询数据库总记录数出错，查询语句："+countSql);
		}
		return 0;
	}
	/**
	 * 查询全部结果 
	 * @param querySql 查询语句
	 * @return
	 */
	public List queryAllResultList(String querySql){
		try{
			Sql sql = Sqls.create(querySql);
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection con, ResultSet rs, Sql sql)
						throws SQLException {
					return (List)NutzSqlUtil.rs2MapList(rs);
				}
			});
			dao.execute(sql);
			List list = sql.getList(List.class);
			return list;
		}catch (Exception e) {
			log.error("执行数据库查询语句出错，查询语句："+querySql);
		}
		return null;
	}
	
	/**
	 * 删除保存的查询定义
	 * @param saveId  查询定义主键
	 */
	public void removeQuerySave(String saveId){
		dao.delete(QuerySave.class, saveId);
	}

	/**
	 * 设置查询热度
	 * @param saveId 查询定义主键
	 * @param queryCount 热度值
	 */
	public void addQueryCount(String saveId,int queryCount){
		dao.update(QuerySave.class, Chain.make("queryCount", queryCount), Cnd.where("saveId", "=", saveId));
	}

	/**
	 * 保存查询主题
	 * @param queryTheme
	 */
	public QueryTheme addQueryTheme(QueryTheme queryTheme) {
		return dao.insert(queryTheme);
	}
	
	/**
	 * 查询当前数据库用户的所有表和视图
	 * @return
	 */
	public List<String> getDbTableNameList() {
		String querySql = "select table_name from user_tables union select view_name as th from user_views";
		List<String> tableNameList = new ArrayList<String>();
		Sql sql = Sqls.create(querySql);
		sql.setCallback(new SqlCallback() {

			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<String> list = new ArrayList<String>();
				while (rs.next()) {
					list.add(rs.getString("table_name"));
				}
				return list;
			}
		});
		dao.execute(sql);
		tableNameList = sql.getList(String.class);
		return tableNameList;
	}
	
	/**
	 * 获取最大的主题ID
	 * @return
	 */
	public int getMaxThemeId() {
		String querySql = "select max(to_number(THEME_ID)) as MAXID from QUERY_STAT_THEMES";
		int maxId = 1000;
		Sql sql = Sqls.create(querySql);
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				if (rs.next()) {
					return rs.getInt("MAXID");
				}
				return null;
			}
		});
		dao.execute(sql);
		maxId = sql.getInt();
		return maxId;
	}
	
	/**
	 * 将主题所对应的表的所有字段初始化到QUERY_STAT_COLUMNS表中
	 * @param tableId
	 * @param tableNameLetter
	 */
	@SuppressWarnings("unchecked")
	public void saveQueryColumns(QueryTheme queryTheme) {
		
		String tableNameLetter = queryTheme.getViewName();
		
		String queryColumnSql = "select A.DATA_TYPE as data_type,A.COLUMN_NAME as column_name,B.COMMENTS as comments " +
				"from USER_TAB_COLUMNS a,USER_COL_COMMENTS b where A.COLUMN_NAME = B.COLUMN_NAME and A.TABLE_NAME = B.TABLE_NAME and A.TABLE_NAME = @tableName";
		
		Sql sql = Sqls.create(queryColumnSql);
		sql.params().set("tableName", tableNameLetter);
		sql.setCallback(new SqlCallback() {

			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Map<String,String>> columnMapList = new ArrayList<Map<String,String>>();
				//key:列英文名 value:列中文名(注释)
				while (rs.next()) {
					Map<String,String> columnMap = new HashMap<String, String>();
					columnMap.put("column_name", rs.getString("column_name"));
					columnMap.put("comments", rs.getString("comments"));
					columnMap.put("data_type", rs.getString("data_type"));
					columnMapList.add(columnMap);
				}
				return columnMapList;
			}
		});
		dao.execute(sql);
		List<Map<String,String>> columnMapList = sql.getObject(List.class);
		
		int cursor = 1;
		List<QueryColumn> queryColumnList = new ArrayList<QueryColumn>();

		for (Map<String,String> columnMap : columnMapList) {
			String themeId = queryTheme.getThemeId();
			//默认的字段ID是themeId加上三位字段序号(如表名是2014,第一个字段为2014001)
			String colId = themeId + String.format("%03d", cursor);
			String columnNameLetter = columnMap.get("column_name");
			String columnName = columnMap.get("comments");
			String dataType = columnMap.get("data_type");
			
			QueryColumn queryColumn = new QueryColumn();
			queryColumn.setColId(colId);
			queryColumn.setThemeId(themeId);
			queryColumn.setName(columnName);
			queryColumn.setNameLetter(columnNameLetter);
			
			//判断字段类型
			if (dataType.equals("VARCHAR2") || dataType.equals("CLOB")) {
				//字符型
				queryColumn.setEditType(DetailQueryConstants.EDIT_TYPE_CHAR);
			} else if (dataType.startsWith("NUMBER") || dataType.startsWith("INTEGER")) {
				//数值型
				queryColumn.setEditType(DetailQueryConstants.EDIT_TYPE_NUMBER);
			} else if (dataType.equals("DATE") || dataType.indexOf("TIMESTAMP") >= 0) {
				//日期型
				queryColumn.setEditType(DetailQueryConstants.EDIT_TYPE_DATE);
			} else if (dataType.equals("CHAR")) {
				//字典型
				queryColumn.setEditType(DetailQueryConstants.EDIT_TYPE_DIC_CHAR);
			}
			
			//查询和结果字段,非主键,有效
			
			queryColumn.setColumnType("1");
			queryColumn.setValidity(DetailQueryConstants.FLAG_YES);
			queryColumn.setIsPk(DetailQueryConstants.FLAG_NO);
			
			queryColumnList.add(queryColumn);
			
			cursor++;
		}
		
		dao.insert(queryColumnList);
		
	}
	
}

