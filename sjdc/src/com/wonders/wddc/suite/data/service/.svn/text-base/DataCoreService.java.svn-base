package com.wonders.wddc.suite.data.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.wddc.config.WddcConstants;
import com.wonders.wddc.suite.csrq.entity.ColConfigBo;
import com.wonders.wddc.suite.data.adapter.DBAdapter;
import com.wonders.wddc.suite.data.entity.DBinfoBo;
import com.wonders.wddc.suite.data.entity.MultStatInfoBo;
import com.wonders.wddc.suite.data.entity.TableConfigBo;
import com.wonders.wddc.tiles.jk.DBpool;
/**
 * 数据服务类
 * @author wonders
 *
 */
@IocBean
public class DataCoreService {

	public final static String SINGLE_RESULT = "SR"; 	//单一结果
	public final static String KEY_VALUE = "KV";		//单条记录 key-value
	public final static String LIST_MAP = "LM";		//list_map
	public final static String MAP_ARRAY = "MA";		//map数组
	
	@Inject
	private DBpool dbPool;
	@Inject
	private Dao dao;
	/**
	 * 返回所有服务数据项列表：格式 为map（id：name）
	 * @return
	 */
	public Map<String, String> getDatas(){
		List<MultStatInfoBo> list = dao.query(MultStatInfoBo.class, Cnd.where("TYPE","=","2"));
		Map<String, String> result = new HashMap<String, String>();
		for(MultStatInfoBo bo:list){
			result.put(bo.getId(), bo.getName());
		}
		return result;
	}
	
	/**
	 * 返回所有表配置信息.
	 * @return
	 */
	public List<TableConfigBo> getTableConfig(){
		List<TableConfigBo> list = dao.query(TableConfigBo.class, Cnd.where("validity","=","1"));
		return list;
	}
	
	/**
	 * 返回制定表的表字段配置信息.
	 * @return
	 */
	public List<ColConfigBo> getColumnConfig(String themeId){
		List<ColConfigBo> list = dao.query(ColConfigBo.class, Cnd.where("themeId","=",themeId));
		return list;
	}
	
	/**
	 * 服务插入数据项,返回报表id
	 * @return
	 */
	public String insert(String name,String sql,String databaseid){
		MultStatInfoBo bo = new MultStatInfoBo();
		bo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		bo.setDatabaseid(databaseid);
		bo.setName(name);
		bo.setSql(sql);
		dao.insert(bo);
		return bo.getId();
	}
	public Object gainresult(String id,Map<String, String> params){
		return gainresult(id, params,LIST_MAP);
	}
	/**
	 * 获取结果
	 * @param id	统计项id
	 * @param param	参数
	 * @param type	返回参数类型（具体见静态变量）
	 * @return
	 */
	public Object gainresult(String id,Map<String, String> params,String type){
		MultStatInfoBo bo = dao.fetch(MultStatInfoBo.class,id);
		if(bo == null){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(WddcConstants.RS_MAP_COUNT, "0");
			result.put("ERROR", "1");
			result.put("MSG", "统计项id无效:-->"+id);
			return result;
		}
		DBinfoBo dbinfo = dao.fetch(DBinfoBo.class,bo.getDatabaseid());
		if(dbinfo == null){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(WddcConstants.RS_MAP_COUNT, "0");
			result.put("ERROR", "1");
			result.put("MSG", "统计项没有正确配置数据库:-->"+id);
			return result;
		}
		//创建dao --- NutDao创建dao(废弃)
		Dao dao1 = DBAdapter.getDao(dbinfo);
		String sqlstr = bo.getSql();
		Sql sql = Sqls.create(sqlstr);
		//查找sql中的参数
		sqlstr = sqlstr.trim();
		List<String> paramlist = new ArrayList<String>();
		while(sqlstr.indexOf("@")>=0){
			sqlstr = sqlstr.substring(sqlstr.indexOf("@")+1);
			if(sqlstr.indexOf(" ")<0){//SQL未结束
				paramlist.add(sqlstr);
			}else{
				paramlist.add(sqlstr.substring(0,sqlstr.indexOf(" ")));
			}
		}
		for(String param : paramlist){
			sql.params().set(param, params.get(param));
		}
		if(SINGLE_RESULT.equals(type)){
			sql.setCallback(new SingleResultCallBack());
		}
		if(KEY_VALUE.equals(type)){
			sql.setCallback(new KeyValueCallBack());
		}
		if(MAP_ARRAY.equals(type)){
			sql.setCallback(new MapArrayCallBack());
		}
		if(type==null||LIST_MAP.equals(type)){
			sql.setCallback(new ListMapCallBack());
		}
		dao1.execute(sql);
		Object result = sql.getResult();
		return result;
	}
	/**
	 * 单一记录 key_value sql处理
	 * @author wonders
	 *
	 */
	class KeyValueCallBack implements SqlCallback{
		@Override
		public Object invoke(Connection conn, ResultSet rs, Sql sql)
				throws SQLException {
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, String> re = new HashMap<String, String>();
			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			int count = 0;
			if(rs.next()){
				for (int i = 1; i <= col; i++) {
					re.put(rsmd.getColumnLabel(i).toLowerCase(), 
							rs.getString(rsmd.getColumnLabel(i)));
				}
				count++;
			}
			result.put(WddcConstants.RS_MAP_DATA, re);
			result.put(WddcConstants.RS_MAP_COUNT, ""+count);
			return result;
		}
	}
	/**
	 * list_map SQL 处理
	 * @author wonders
	 *
	 */
	class ListMapCallBack implements SqlCallback{
		@Override
		public Object invoke(Connection conn, ResultSet rs, Sql sql)
				throws SQLException {
			Map<String, Object> result = new HashMap<String, Object>();
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			int count = 0;
			while(rs.next()){
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 1; i <= col; i++) {
					map.put(rsmd.getColumnLabel(i).toLowerCase(), 
							rs.getString(rsmd.getColumnLabel(i)));
				}
				list.add(map);
				count++;
			}
			result.put(WddcConstants.RS_MAP_DATA, list);
			result.put(WddcConstants.RS_MAP_COUNT, ""+count);
			return result;
		}
	}
	/**
	 * 单一结果SQL处理
	 * @author wonders
	 *
	 */
	class SingleResultCallBack implements SqlCallback{
		@Override
		public Object invoke(Connection conn, ResultSet rs, Sql sql)
			throws SQLException {
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, String> re = new HashMap<String, String>();
			int count = 0;
			if(rs.next()){
				re.put("result", rs.getString(1));
				count = 1;
			}else {
				re.put("result", "null");
				count = 0;
			}
			result.put(WddcConstants.RS_MAP_DATA, re);
			result.put(WddcConstants.RS_MAP_COUNT, ""+count);
			return result;
		}
	}
	/**
	 * map数组SQL处理
	 * @author wonders
	 *
	 */
	class MapArrayCallBack implements SqlCallback{
		@Override
		public Object invoke(Connection conn, ResultSet rs, Sql sql)
				throws SQLException {
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, List<String>> re = new HashMap<String, List<String>>();
			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			for (int i = 1; i <= col; i++) {
				re.put(rsmd.getColumnLabel(i).toLowerCase(), new ArrayList<String>());
			}
			int count=0;
			while(rs.next()){
				for (int i = 1; i <= col; i++) {
					re.get(rsmd.getColumnLabel(i).toLowerCase()).add(rs.getString(rsmd.getColumnLabel(i)));
				}
				count++;
			}
			result.put(WddcConstants.RS_MAP_DATA, re);
			result.put(WddcConstants.RS_MAP_COUNT, ""+count);
			return result;
		}
	}
}
