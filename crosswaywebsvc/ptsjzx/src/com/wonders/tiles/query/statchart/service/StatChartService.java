package com.wonders.tiles.query.statchart.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.tiles.query.detailquery.entity.QuerySave;
import com.wonders.tiles.query.statchart.entity.ChartSave;
import com.wonders.tiles.query.statchart.entity.QueryColumn;
import com.wonders.tiles.query.statchart.entity.QueryTheme;
import com.wonders.util.NutzSqlUtil;


@IocBean 
public class StatChartService{
	
	@Inject
	private Dao dao;
	private Log log = Logs.get();

	
	/**
	 * 获取全部主题列表
	 * @return QueryTheme对象列表
	 */
	public List<QueryTheme> queryAllThemeList(){
		return dao.query(QueryTheme.class, null);
		
	}

	/**
	 * 获取全部主题列表
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
	 * 根据用户权限获取主题列表
	 * @param themeIdList 人员权限List
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
	public List<ChartSave> querySaveByName(String saveName){
		return dao.query(ChartSave.class, Cnd.where("name", "like", "%"+saveName+"%"));
	}
	
	/**
	 * 根据用户权限获取主题列表
	 * @param themeIdList 人员权限List
	 * @return QueryTheme对象列表
	 */
	public List<QueryTheme> queryThemeList(String [] themeIds){
		return dao.query(QueryTheme.class, Cnd.where("themeId", "in", themeIds));
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
	 * @param themeIds 主题主键字符串（用逗号隔开）
	 * @return
	 */
	public List<ChartSave> querySaveListByTheme(String [] themeIds){
		Criteria cri = Cnd.cri();
		cri.where().andIn("themeId", themeIds);
		cri.getOrderBy().desc("queryCount");
		return dao.query(ChartSave.class, cri);
	}
	/**
	 * 根据分类、名称获取查询定义
	 * @param saveName
	 * @return
	 */
	public List<ChartSave> querySave(String saveName,String [] themeIds){
		Criteria cri = Cnd.cri();
		if(!Strings.isBlank(saveName)){
			cri.where().and("name", "like", "%"+saveName+"%");
		}
		if(themeIds!=null && themeIds.length>0){
			cri.where().andIn("themeId", themeIds);
		}
		cri.getOrderBy().desc("queryCount");
		return dao.query(ChartSave.class, cri);
	}
	/**
	 * 根据主键获取图标定义
	 * @param saveId 保存定义的主键
	 * @return 
	 */
	public ChartSave queryChartSaveById(String saveId){
		return dao.fetch(ChartSave.class, saveId);
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
	 * 根据列编号查询列对象
	 * @param colIds 列编号（逗号隔开）
	 * @return QueryColumn对象列表
	 */
	public List<QueryColumn> queryColumnListByColId(String [] colIds){
		Criteria cri = Cnd.cri();
		cri.where().andIn("colId",colIds);
		return dao.query(QueryColumn.class, cri);
	}

	/**
	 * 根据列编号查询列对象
	 * @param colIds 列编号（逗号隔开）
	 * @return QueryColumn对象列表
	 */
	public QueryColumn queryColumnById(String colId){
		return dao.fetch(QueryColumn.class, colId);
	}
	

	/**
	 * 用户保存的查询定义查询全部
	 * @return
	 */
	public List<QuerySave> querySaveList(String type){
		Criteria cri = Cnd.cri();
		cri.getOrderBy().desc("queryCount");
		cri.where().andIn("themeId", getThemeIds(type));
		return dao.query(QuerySave.class, cri) ;
	}

	private String[] getThemeIds(String type){
		String catalog = "";
		if("1".equals(type)){
			catalog="自然人专题";
		}else if("2".equals(type)){
			catalog="法人专题";
		}else if("3".equals(type)){
			catalog="经济专题";
		}
		List<QueryTheme> themeList = queryThemeListByCatalog(catalog);
		String [] themeIds = {""};
		List<String> themeIdList = new ArrayList<String>();
		if(themeList !=null && themeList.size()>0){
			for(int i=0;i<themeList.size();i++){
				themeIdList.add(themeList.get(i).getThemeId());
			}
			themeIds = themeIdList.toArray(new String[themeIdList.size()]);
		}
		return themeIds;
	}
	/**
	 * 用户保存的统计定义查询全部
	 * @return
	 */
	public List<ChartSave> charSaveList(String type){
		Criteria cri = Cnd.cri();
		cri.getOrderBy().desc("queryCount");
		cri.where().andIn("themeId", getThemeIds(type));
		return dao.query(ChartSave.class, cri) ;
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
	public void addQuerySave(ChartSave query){
		dao.insert(query);	
	}

	/**
	 * 修改一条查询定义
	 * @param query 定义对象
	 */
	public void updateQuerySave(ChartSave query){
		dao.update(query);
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
	public void removeChartSave(String saveId){
		dao.delete(ChartSave.class, saveId);	
	}

	/**
	 * 设置查询热度
	 * @param saveId 查询定义主键
	 * @param queryCount 热度值
	 */
	public void addQueryCount(String saveId,int queryCount){
		dao.update(ChartSave.class, Chain.make("queryCount", queryCount), Cnd.where("saveId", "=", saveId));
	}
	
	
}