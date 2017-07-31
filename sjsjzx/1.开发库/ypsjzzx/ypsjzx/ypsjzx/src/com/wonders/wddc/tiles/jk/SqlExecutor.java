package com.wonders.wddc.tiles.jk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.wddc.suite.csrq.entity.ColConfigBo;
import com.wonders.wddc.suite.data.entity.DBinfoBo;
import com.wonders.wddc.suite.data.entity.TableConfigBo;

/**
 * 接口提供类
 * @author wanda
 *
 */
@IocBean
public class  SqlExecutor {
	@Inject
	private SQLService sqlService;
	
	/**
	 * 无返回值 执行SQL语句(增、删、改等操作)
	 * @param toDealSql
	 * @param dataSourceId
	 * @throws Exception 
	 */
	public void dealSql(String toDealSql,String dataSourceId) throws Exception{
		
		sqlService.dealSql(toDealSql, dataSourceId);
	}
	
	/**
	 * 执行文件内容
	 * @param file
	 * @param dataSourceId
	 * @throws Exception 
	 */
	public void dealFile(String filePath,String dataSourceId) throws Exception{
		
		sqlService.dealFile(filePath, dataSourceId);
	}
	
	/**
	 * 执行SQL语句,返回Map<String,String> 类型
	 * @param toDealSql
	 * @param dataSourceId
	 * @return
	 * @throws Exception 
	 */
	public Map<String,String> getMapResult(String toDealSql,String dataSourceId) throws Exception {
		
		return sqlService.getStrMapResult(toDealSql, dataSourceId);
	}
	
	/**
	 * 执行SQL语句，并做Map To Json字符串处理
	 * @param toDealSql
	 * @param dataSourceId
	 * @return
	 * @throws Exception 
	 */
	public String getMap2JsonResult(String toDealSql,String dataSourceId) throws Exception{
		
		return sqlService.getMap2JsonResult(toDealSql, dataSourceId);
		
	}
	
	/**
	 * 执行SQL语句，返回List<Map<String,String>>类型
	 * @param toDealSql
	 * @param dataSourceId
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String,String>>  getListMapResult(String toDealSql,String dataSourceId) throws Exception {
		
		return sqlService.getListMapResult(toDealSql, dataSourceId);
	}
	
	/**
	 * 执行SQL语句，并做List<Map> To Json处理
	 * @param toDealSql
	 * @param dataSourceId
	 * @return
	 * @throws Exception 
	 */
	public String getListMap2JsonResult(String toDealSql,String dataSourceId) throws Exception {
		
		return sqlService.getListMap2JsonResult(toDealSql, dataSourceId);
	}
	
	/**
	 * 获取JavaBean
	 * @param toDealSql
	 * @param dataSourceId
	 * @param returnType
	 * @return
	 * @throws Exception 
	 */
	public <T> T getBeanResult(String toDealSql,String dataSourceId,Class<T> cl) throws Exception{
		
		return sqlService.getBeanResult(toDealSql, dataSourceId, cl);
	}
	
	/**
	 * 执行SQL语句，并做Bean To Json 处理
	 * @param <T>
	 * @param toDealSql
	 * @param dataSourceId
	 * @param cl
	 * @return
	 * @throws Exception 
	 */
	public <T> String getBean2JsonResult(String toDealSql,String dataSourceId,Class<T> cl) throws Exception{
		
		return sqlService.getBean2JsonResult(toDealSql, dataSourceId, cl);
	}
	
	/**
	 * 获取JavaBean集合
	 * @param toDealSql
	 * @param dataSourceId
	 * @param returnType
	 * @return
	 * @throws Exception 
	 */
	public <T> List<T>  getBeanList(String toDealSql,String dataSourceId,Class<T> cl) throws Exception{
		
		return sqlService.getBeanList(toDealSql, dataSourceId, cl);
	}
	
	/**
	 * 执行SQL语句，并做List<Bean> To Json 处理
	 * @param <T>
	 * @param toDealSql
	 * @param dataSourceId
	 * @param cl
	 * @return
	 * @throws Exception 
	 */
	public <T> String getListBean2JsonResult(String toDealSql,String dataSourceId,Class<T> cl) throws Exception{
		
		return sqlService.getListBean2JsonResult(toDealSql, dataSourceId, cl);
	}
	
	/**
	 * 根据创建表语句创建库表,并反向生成配置
	 * @param createSql
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 */
	public void createTable(String createTabSql,String dataSourceId) throws Exception{
		sqlService.createTable(createTabSql, dataSourceId);
	}
	
	/**
	 * 读取数据，根据表结构查询语句，获取表的信息(表名称、表描述)
	 * @param tableName  表名称
	 * @param dataSourceId 数据源id
	 * @return
	 * @throws Exception 
	 */
	public  Map<String,String> getTableConfigBo(String tableName,String dataSourceId) throws Exception {
		
		return sqlService.getTableConfigBo(tableName, dataSourceId);
	}
	/**
	 * 读取数据库，根据数据源id和表名称获取表的列信息
	 * @param sql
	 * @param typeDao
	 * @return
	 * @throws Exception 
	 */
	public  List<Map<String,String>> getColConfigList(String tableName,String dataSourceId) throws Exception {
		
		return sqlService.getColConfigList(tableName, dataSourceId);
	}
	
	/**
	 * 根据存储过程SQL语句，执行存储过程
	 * @param CLRSql
	 */
	public  void CLRExcute(String CLRSql,String dataSourceId) {
		sqlService.CLRExcute(CLRSql, dataSourceId);
	}
	
	public List<String> getSchemTabNameList(String dataSourceId) throws Exception{
		return sqlService.getSchemTabNameList(dataSourceId);
	}
	/**
	 * 根据数据源和表名获取表配置
	 * @param tableName
	 * @param dataSourceId
	 * @return
	 * @throws Exception
	 */
	public  TableConfigBo getTableConfigBoBean(String tableName,String dataSourceId) throws Exception {
		return sqlService.getTableConfigBoBean(tableName, dataSourceId);
	}
	
	/**
	 * 读取库表，根据表名称和数据源id获取表的列信息
	 * @param tableName 表名称
	 * @param dataSourceId 数据源id
	 * @return
	 * @throws Exception 
	 */
	public  List<ColConfigBo> getColConfigBoList(String tableName,String dataSourceId) throws Exception {
		
		return sqlService.getColConfigBoList(tableName, dataSourceId);
	}
}
