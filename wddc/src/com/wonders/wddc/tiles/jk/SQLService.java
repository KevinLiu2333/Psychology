package com.wonders.wddc.tiles.jk;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONObject;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import com.wonders.wddc.suite.csrq.entity.ColConfigBo;
import com.wonders.wddc.suite.data.entity.DBinfoBo;
import com.wonders.wddc.suite.data.entity.TableConfigBo;

/**
 * SQL执行实现类
 * @author wanda
 *
 */
@IocBean
public class SQLService  {
	@Inject
	private DBpool dbPool;
	@Inject
	private Dao dao;
	
	/**
	 * 无返回值 执行SQL语句(增、删、改等操作)
	 * @param toDealSql
	 * @param dataSourceId
	 */
	public void dealSql(String toDealSql,String dataSourceId) throws Exception{
		Connection conn = dbPool.getDataSourceById(dataSourceId).getConnection();
		Statement stmt = conn.createStatement();
		stmt.execute(toDealSql);
		DBCPAssist.releaseConnection(null, stmt, conn);
		
	}
	
	public long getCountResult(String toDealSql,String dataSourceId){
		return 0;
	}
	
	/**
	 * 执行文件内容  后期要加上事物
	 * @param sqlFile  sql文件的路径
	 * @param dataSourceId
	 */
	public void dealFile(String sqlFile,String dataSourceId) throws Exception{
		Connection conn = dbPool.getDataSourceById(dataSourceId).getConnection();
		Statement stmt = null;
		try {
			conn.setAutoCommit(false);
			List<String> sqlList = loadSql(sqlFile);
			if(sqlList == null || sqlList.size()==0){
				System.out.println("对不起，该文件不可执行或者改文件是空文件");
				return;
			}
			stmt = conn.createStatement();  
		    for (String sql : sqlList) {  
		        stmt.addBatch(sql);  
		    }  
		    int[] rows = stmt.executeBatch();
		    System.out.println("Row count:" + Arrays.toString(rows)); 
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			DBCPAssist.releaseConnection(null, stmt, conn);
		}
	}
	
	/**
	 * 执行SQL语句,返回Map<String,String> 类型
	 * @param toDealSql
	 * @param dataSourceId
	 * @return
	 */
	public Map<String,String> getStrMapResult(String toDealSql,String dataSourceId) throws Exception{
		
		return getListMapResult(toDealSql,dataSourceId).get(0);
	}
	
	/**
	 * 执行SQL语句，获取由Map类型转化为Json的数据
	 * @param toDealSql
	 * @param dataSourceId
	 * @return
	 */
	public String getMap2JsonResult(String toDealSql,String dataSourceId) throws Exception{
		
		return Json.toJson(getStrMapResult(toDealSql,dataSourceId));
		
	}
	
	/**
	 * 执行SQL语句，获取由Map类型转化为Json的数据
	 * @param toDealSql
	 * @param dataSourceId
	 * @return
	 */
	public String getMap2JsonObjResult(String toDealSql,String dataSourceId) throws Exception{
		JSONObject json = JSONObject.fromObject(getStrMapResult(toDealSql,dataSourceId)); 
		return json.toString();
		
	}
	
	/**
	 * 执行SQL语句，返回List<Map<String,String>>类型
	 * @param toDealSql
	 * @param dataSourceId
	 * @return
	 */
	public List<Map<String,String>>  getListMapResult(String toDealSql,String dataSourceId)throws Exception {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Connection conn = dbPool.getDataSourceById(dataSourceId).getConnection();
		Statement stmt = null;
		ResultSet resultSet = null;
		
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(toDealSql);
			//ResultSetMetaData可用于获取关于 ResultSet 对象中列的类型和属性信息的对象。
			ResultSetMetaData rsmd = resultSet.getMetaData();
			//获得返回此 ResultSet 对象中的列数
			int count = rsmd.getColumnCount();
			
			while(resultSet.next()){
				Map<String,String> map=new HashMap<String,String>();
				for(int i=1;i<=count;i++){
				//获取指定列的表目录名称
				String cols_name=rsmd.getColumnLabel(i).toUpperCase();
				//以 Java 编程语言中 Object 的形式获取此 ResultSet 对象的当前行中指定列的值
				Object cols_value= resultSet.getObject(i);
				//把数据库中的字段名和值对应为一个map对象中的一个键值对
				if(cols_value == null){  
	                cols_value = "";  
	            }  
				map.put(cols_name, cols_value.toString());
			}
			//把每条对象封装成的map对象放进list中
			list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{  
			DBCPAssist.releaseConnection(resultSet, stmt, conn);
        }  
		return list;
	}
	
	/**
	 * 获取有List<Map<String,String>> 转化为Json字符串的数据
	 * @param toDealSql
	 * @param dataSourceId
	 * @return
	 */
	public String getListMap2JsonResult(String toDealSql,String dataSourceId) throws Exception{
		//JSONArray json = JSONArray.fromObject(getListMapResult(toDealSql,dataSourceId)); 
		
		return Json.toJson(getListMapResult(toDealSql,dataSourceId));
	}
	
	/**
	 * 获取JavaBean
	 * @param toDealSql
	 * @param dataSourceId
	 * @param returnType
	 * @return
	 */
	public <T> T getBeanResult(String toDealSql,String dataSourceId,Class<T> cl)  throws Exception{
		
		return getBeanList(toDealSql,dataSourceId,cl).size()>0?getBeanList(toDealSql,dataSourceId,cl).get(0):null;
		
	}
	
	/**
	 * 由Bean类型数据转化为Json字符串的数据******************？？？**********************
	 * @param <T>
	 * @param toDealSql
	 * @param dataSourceId
	 * @param cl
	 * @return
	 */
	public <T> String getBean2JsonResult(String toDealSql,String dataSourceId,Class<T> cl) throws Exception{
		
		return Json.toJson(getBeanResult(toDealSql,dataSourceId,cl));
	}
	
	/**
	 * 获取JavaBean集合
	 * @param toDealSql
	 * @param dataSourceId
	 * @param returnType
	 * @return
	 */
	public <T> List<T>  getBeanList(String toDealSql,String dataSourceId,Class<T> cl) throws Exception{
		List<T> list = new ArrayList<T>();  
		Connection conn = dbPool.getDataSourceById(dataSourceId).getConnection();
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery(toDealSql);
		//ResultSetMetaData可用于获取关于 ResultSet 对象中列的类型和属性信息的对象。
		ResultSetMetaData rsmd = resultSet.getMetaData();
		//获得返回此 ResultSet 对象中的列数
		int count = rsmd.getColumnCount();
		while(resultSet.next()){
			//通过反射机制创建一个实例  
			T resultObject = null;  
			resultObject = cl.newInstance();
			for(int i=1;i<=count;i++){
				//获取指定列的表目录名称
				String cols_name=rsmd.getColumnLabel(i).toLowerCase();
				//以 Java 编程语言中 Object 的形式获取此 ResultSet 对象的当前行中指定列的值
				Object cols_value = resultSet.getObject(cols_name);
				//System.out.println("---------关键点------");
				//System.out.println(cols_value);
				//注意数字型问题
				//if(cols_value == null ){  
				//	cols_value = "";  
				//}  
				Field field = cl.getDeclaredField(cols_name);  
				field.setAccessible(true); //打开javabean的访问权限  
				field.set(resultObject, cols_value);
			}
		//把每条对象封装成的map对象放进list中
			list.add(resultObject);
		}
			DBCPAssist.releaseConnection(resultSet,stmt,conn);
		return list;
	}
	
	/**
	 * 由List<T> 转化为Json的数据
	 * @param <T>
	 * @param toDealSql
	 * @param dataSourceId
	 * @param cl
	 * @return
	 */
	public <T> String getListBean2JsonResult(String toDealSql,String dataSourceId,Class<T> cl) throws Exception{
		//JSONArray.fromObject(getBeanList(toDealSql,dataSourceId,cl)); 
		return Json.toJson(getBeanList(toDealSql,dataSourceId,cl));
	}
	
	/**
	 * 根据创建表语句创建库表,并反向生成配置
	 * @param createSql
	 * @return
	 * @throws Exception 
	 */
	public void createTable(String createTabSql,String dataSourceId) throws Exception {
		dealSql(createTabSql,dataSourceId);
	}
	
	/**
	 * 根据存储过程SQL语句，执行存储过程
	 * @param CLRSql
	 */
	public  void CLRExcute(String CLRSql,String dataSourceId) {
		
	}
	
	/**
	 * 根据表结构查询语句，获取表的信息(表名称、表描述)
	 * @param selSql
	 * @param typeDao
	 * @return
	 * @throws Exception 
	 */
	public  Map<String,String> getTableConfigBo(String tableName,String dataSourceId) throws Exception {
		Map<String,String> tableConfigMap = null;
		DBinfoBo dbInfo = dao.fetch(DBinfoBo.class, dataSourceId);//此处看的不爽
		String toDealSql ="";
		if(dbInfo != null){
			dbPool.createDataSource(dbInfo);
			IDataSource dataSource =  dbPool.getDataSourceById(dataSourceId);
			String dsType =dataSource.getDataSourceType();
			if("MYSQL".equalsIgnoreCase(dsType)){
				toDealSql = " SELECT  TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME ='" + tableName+"'";
			}else if("ORACLE".equalsIgnoreCase(dsType)){
				toDealSql = "待查。。。。。。 ";
			}
			tableConfigMap = getStrMapResult( toDealSql, dataSourceId);
			tableConfigMap.put("tableName", tableName.toUpperCase());
		}
		return tableConfigMap;
	}
	
	/**
	 * 根据表结构查询语句，获取表的信息(表名称、表描述)还有一些地方没有解决，暂时不用！！！！！
	 * @param selSql
	 * @param typeDao
	 * @return
	 * @throws Exception 
	 */
	public  TableConfigBo getTableConfigBoBean(String tableName,String dataSourceId) throws Exception {
		//待修改。。。。
		DBinfoBo dbInfo = dao.fetch(DBinfoBo.class, dataSourceId);//此处看的不爽
		TableConfigBo tableConfig = null;
		String toDealSql = "";
		if(dbInfo != null){
			dbPool.createDataSource(dbInfo);
			IDataSource dataSource =  dbPool.getDataSourceById(dataSourceId);
			String dsType =dataSource.getDataSourceType();
			if("MYSQL".equalsIgnoreCase(dsType)){
				//根据表名获取表注释
				toDealSql = " SELECT  TABLE_COMMENT AS 'name' FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME ='" + tableName+"'";
			}else if("ORACLE".equalsIgnoreCase(dsType)){
				//select *  from user_tab_comments 可以获取所有表的表名、表注释
				toDealSql = "select comments as name from user_tab_comments where table_name ='" + tableName+"'";
			}
			tableConfig = getBeanResult( toDealSql, dataSourceId,TableConfigBo.class);
			tableConfig.setDbname(dataSource.getDataBaseName());
			tableConfig.setViewName(tableName.toUpperCase());
			tableConfig.setDataSourceId(dataSourceId);
		}
		return tableConfig;
	}
	
	/**
	 * 读取库表，根据表名称和数据源id获取表的列信息
	 * @param tableName 表名称
	 * @param dataSourceId 数据源id
	 * @return
	 * @throws Exception 
	 */
	public  List<Map<String,String>> getColConfigList(String tableName,String dataSourceId) throws Exception {
		List<Map<String,String>> mapColConfigList = null;
		DBinfoBo dbInfo = dao.fetch(DBinfoBo.class, dataSourceId);//此处看的不爽
		String toDealSql ="";
		if(dbInfo != null){
			mapColConfigList = new ArrayList<Map<String,String>>();
			dbPool.createDataSource(dbInfo);
			IDataSource dataSource =  dbPool.getDataSourceById(dataSourceId);
			String dsType =dataSource.getDataSourceType();
			if("MYSQL".equalsIgnoreCase(dsType)){
				toDealSql = " SELECT"
					  +"  COLUMN_NAME as 'colName',"
					  +"COLUMN_COMMENT as 'colComment'," 
					  +	"DATA_TYPE as 'colTypeName' ,"
					  +"COLUMN_TYPE as 'colType' ,"
					  +"COLUMN_KEY as 'isPk' "
					  +"FROM information_schema.COLUMNS "
					  +"where TABLE_SCHEMA ='"+dbInfo.getDbEnName()+"'"
					  +"and  TABLE_NAME = '"+tableName+"'";
					 
			}else if("ORACLE".equalsIgnoreCase(dsType)){
				toDealSql = "select c.COLUMN_NAME colName,d.comments colComment,c.DATA_TYPE colTypeName,c. DATA_TYPE||'('||c.DATA_LENGTH||')' colType,"
							+"  (select col.column_name "
							+"	from user_constraints con,  user_cons_columns col"
							+"	where con.constraint_name = col.constraint_name "
							+"	and con.constraint_type='P' "
							+"	and col.table_name = 'DIC_YJYB') isPk"
							+"	from USER_TAB_COLS c,user_col_comments d"
							+"	where c.table_name = d.table_name"
							+"	and c.column_name = d.column_name"
							+"	and c.table_name = '"+tableName+"'";
					
					
			}
			
			mapColConfigList = getListMapResult( toDealSql, dataSourceId);
			
		}
		return mapColConfigList;
	}
	
	/**
	 * 读取库表，根据表名称和数据源id获取表的列信息
	 * @param tableName 表名称
	 * @param dataSourceId 数据源id
	 * @return
	 * @throws Exception 
	 */
	public  List<ColConfigBo> getColConfigBoList(String tableName,String dataSourceId) throws Exception {
		List<ColConfigBo> colConfigList = null;
		DBinfoBo dbInfo = dao.fetch(DBinfoBo.class, dataSourceId);//此处看的不爽
		String toDealSql ="";
		if(dbInfo != null){
			colConfigList = new ArrayList<ColConfigBo>();
			dbPool.createDataSource(dbInfo);
			IDataSource dataSource =  dbPool.getDataSourceById(dataSourceId);
			String dsType =dataSource.getDataSourceType();
			if("MYSQL".equalsIgnoreCase(dsType)){
				toDealSql = " SELECT"
					  +" COLUMN_NAME as 'colName',"
					  +" COLUMN_COMMENT as 'colComment'," 
					  +" DATA_TYPE as 'colTypeName' ,"
					  +" COLUMN_TYPE as 'colType' ,"
					  +" COLUMN_KEY as 'isPk'"
					  +" FROM information_schema.COLUMNS "
					  +" where TABLE_NAME like '"+tableName+"'";
			}else if("ORACLE".equalsIgnoreCase(dsType)){
				toDealSql = "待写。。。。。。 ";
			}
			
			colConfigList = getBeanList( toDealSql, dataSourceId,ColConfigBo.class);
			
		}
		return colConfigList;
	}
	
	/** 
     * 读取 SQL 文件，获取 SQL 语句 *****有一个要求，必须是规范的SQL执行脚本文件*****
     * @param sqlFile SQL 脚本文件 
     * @return List<sql> 返回所有 SQL 语句的 List 
     * @throws Exception 
     */  
    private List<String> loadSql(String sqlFile) throws Exception {  
        List<String> sqlList = new ArrayList<String>();  
        File file = new File(sqlFile);
        if (file == null) {
        	return null;
        } 
        try {  
            InputStream sqlFileIn = new FileInputStream(sqlFile);  
            StringBuffer sqlSb = new StringBuffer();  
            byte[] buff = new byte[1024];  
            int byteRead = 0;  
            while ((byteRead = sqlFileIn.read(buff)) != -1) {  
                sqlSb.append(new String(buff, 0, byteRead));  
            }  
  
            // Windows 下换行是 /r/n, Linux 下是 /n  
            String[] sqlArr = sqlSb.toString().split("(;//s*//r//n)|(;//s*//n)");  
            for (int i = 0; i < sqlArr.length; i++) {  
                String sql = sqlArr[i].replaceAll("--.*", "").trim();  
                if (!sql.equals("")) {  
                    sqlList.add(sql);  
                }  
            }  
            return sqlList;  
        } catch (Exception ex) {  
            throw new Exception(ex.getMessage());  
        } 
    } 
    
    /**
     * 读取SQL执行脚本文件   备用方法
     * @param filePath
     * @return
     */
    public String readFile(String filePath) {
    	String doc = null;
    	File file = new File(filePath);
    	if (file == null) {
    	return null;
    	}
    	try {
    	// 用文件输入流构建输入流
    	InputStream is = new BufferedInputStream(new FileInputStream(file));
    	BufferedReader bReader = new BufferedReader(new InputStreamReader(
    	is, "utf-8"));
    	StringBuilder sb = new StringBuilder();
    	String rLine = null;
    	while ((rLine = bReader.readLine()) != null) {
    	String tmp_rLine = rLine;
    	int str_len = tmp_rLine.length();
    	if (str_len > 0) {
    	sb.append(tmp_rLine + " ");
    	}
    	tmp_rLine = null;
    	}
    	doc = sb.toString();
    	bReader.close();
    	} catch (FileNotFoundException e) {
    	e.printStackTrace();
    	} catch (IOException e) {
    	e.printStackTrace();
    	}
    	return doc;
	}
    
	/**
	 * 读数据库表，根据数据源id读取库表英文名集合
	 * @param dataSouceId
	 * @return
	 * @throws Exception 
	 * 注意：此时的结果是大写的字符串
	 */
	public List<String> getSchemTabNameList(String dataSourceId) throws Exception{
		/*List<String> tableNameList = null;
		List<Map<String,String>> mapTabNameList = null;
		if(dataSourceId != null && dataSourceId != ""){
			IDataSource dataSource = dbPool.getDataSourceById(dataSourceId);
			String sql ="";
			if(dataSource != null){
				String dsType = dbPool.getDataSourceById(dataSourceId).getDataSourceType();
				if("MYSQL".equalsIgnoreCase(dsType)){
					sql = " SELECT UPPER(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = "+dataSource.getDataBaseName();
				}else if("ORACLE".equalsIgnoreCase(dsType)){
					sql = "select a.TABLE_NAME from user_tables a order by TABLE_NAME ";
				}
				mapTabNameList = new ArrayList<Map<String,String>>();
				mapTabNameList = getListMapResult(sql, dataSourceId);
				
			}
		}
		
		if(mapTabNameList != null && mapTabNameList.size()>0){
			tableNameList = new ArrayList<String>();
			for(Map<String,String> map:mapTabNameList){
				String tableName = (String) map.get("TABLE_NAME");
				tableNameList.add(tableName.toUpperCase());
			}
		}
		return tableNameList;*/
		//一下代码用于测试环境
		List<Map<String,String>> mapTabNameList = null;
		List<String> tableNameList = null;
		DBinfoBo dbInfo = dao.fetch(DBinfoBo.class, dataSourceId);//此处看的不爽
		String sql ="";
		if(dbInfo != null){
			mapTabNameList = new ArrayList<Map<String,String>>();
			dbPool.createDataSource(dbInfo);
			IDataSource dataSource =  dbPool.getDataSourceById(dataSourceId);
			String dsType =dataSource.getDataSourceType();
			if("MYSQL".equalsIgnoreCase(dsType)){
				sql = " SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '"+dataSource.getDataBaseName()+"'";
			}else if("ORACLE".equalsIgnoreCase(dsType)){
				sql = "select a.TABLE_NAME from user_tables a order by TABLE_NAME ";
			}
			mapTabNameList =getListMapResult(sql, dataSourceId);
		}
		if(mapTabNameList != null && mapTabNameList.size()>0){
			tableNameList = new ArrayList<String>();
			for(Map<String,String> map:mapTabNameList){
				String tableName = (String) map.get("TABLE_NAME");
				tableNameList.add(tableName.toUpperCase());
			}
		}
		return tableNameList;
		
	}
	
	
}
