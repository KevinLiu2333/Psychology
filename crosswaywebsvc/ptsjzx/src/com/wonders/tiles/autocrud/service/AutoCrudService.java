package com.wonders.tiles.autocrud.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.tiles.autocrud.AutoCrudConstants;
import com.wonders.tiles.autocrud.entity.AppColumn;
import com.wonders.tiles.autocrud.entity.AppTable;
import com.wonders.util.ListMapUtil;
import com.wonders.util.NutzSqlUtil;

@IocBean
public class AutoCrudService {

	@Inject
	private Dao dao;

	private Log logger = Logs.get();

	/**
	 * 根据主键删除某条记录
	 * 
	 * @param tableName
	 * @param pkName
	 * @param pkId
	 * @return
	 */
	public void removeRecordByPK(String tableName, String pkName, String pkId) {
		Sql sql = Sqls.create("DELETE FROM $tableName WHERE $pkName = '$pkId'");
		sql.vars().set("tableName", tableName).set("pkName", pkName).set("pkId", pkId);
		dao.execute(sql);
	}

	/**
	 * 根据主键查询某条记录
	 * 
	 * @param tableName
	 * @param pkName
	 * @param pkId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getRecordByPK(String tableName, String pkName, String pkId) {
		Sql sql = Sqls.create("SELECT * FROM $tableName WHERE $pkName = '$pkId'");
		sql.vars().set("tableName", tableName).set("pkName", pkName).set("pkId", pkId);
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				return NutzSqlUtil.rs2Map(rs);
			}
		});
		dao.execute(sql);
		Map<String, Object> resultMap = sql.getObject(HashMap.class);
		return resultMap;
	}

	/**
	 * 保存或更新数据记录
	 * 
	 * @param tableName
	 * @param colList
	 */
	public void saveOrUpdateRecord(String tableName, List<AppColumn> colList) {
		if (colList == null)
			return;

		// 1、获得主键字段 判断新增or修改 组装语法
		StringBuffer sqlDelete = new StringBuffer();

		AppColumn pkCol = (AppColumn) ListMapUtil.getObjectFromListById(colList, "getIsPk", "1");
		// 判断新增or修改
		boolean flag = false;
		if (StringUtils.isNotEmpty(pkCol.getValue())) {
			// 修改-删除
			sqlDelete.append("DELETE FROM ").append(tableName).append(" WHERE ").append(pkCol.getNameLetter()).append(" = '").append(pkCol.getValue()).append(
					"'");

			flag = true;
		}

		// 2、新增语法
		StringBuffer sqlInsert = new StringBuffer();

		StringBuffer sqlName = new StringBuffer();// 字段
		StringBuffer sqlValue = new StringBuffer();// 值

		int colSize = colList.size();
		for (int i = 0; i < colSize; i++) {
			AppColumn col = colList.get(i);

			sqlName.append(col.getNameLetter()).append(",");

			if (StringUtils.isNotEmpty(col.getValue())) {
				switch (col.getDataType().intValue()) {
				case AutoCrudConstants.COLUMN_TYPE_CHAR:
					sqlValue.append("'").append(col.getValue()).append("',");
					break;
				case AutoCrudConstants.COLUMN_TYPE_NUMBER:
				case AutoCrudConstants.COLUMN_TYPE_DIC:
					sqlValue.append(col.getValue()).append(",");
					break;
				case AutoCrudConstants.COLUMN_TYPE_DATE:
					sqlValue.append("TO_DATE('").append(col.getValue()).append("','yyyy-mm-dd'),");
					break;
				}
			} else {
				// 如果页面值为空，且初始值不为空
				// 可以解决PK或一些内部规则的编码
				if (StringUtils.isEmpty(col.getInitValue())) {
					sqlValue.append("NULL").append(",");
				} else {
					sqlValue.append(col.getInitValue()).append(",");
				}
			}
		}

		sqlInsert.append("INSERT INTO ").append(tableName).append("(").append(sqlName.subSequence(0, sqlName.length() - 1)).append(")").append(" VALUES (")
				.append(sqlValue.subSequence(0, sqlValue.length() - 1)).append(")");

		logger.debug("sqlInsert:" + sqlInsert);

		// 3、进行数据库操作

		// 修改-删除数据
		if (flag) {
			Sql deleteSql = Sqls.create(sqlDelete.toString());
			dao.execute(deleteSql);
		}

		// 插入新数据

		Sql insertSql = Sqls.create(sqlInsert.toString());
		dao.execute(insertSql);
	}

	/**
	 * 获得分页查询结果对象，目前查询采用了硬编码的方式，有待改进
	 * 
	 * @param countSql
	 * @param qrySql
	 * @param pageSize
	 * @param currentPage
	 * @return PageList
	 */
	public List<Object> getQueryResult(String countSql, String qrySql, int pageSize, int currentPage) {
		// 1.接收传来的pager参数构造分页对象
		Pager pager = new Pager();
		pager.setPageSize(pageSize);
		pager.setPageNumber(currentPage);

		// 2.设置回调函数,查询结果列表
		Sql sql = Sqls.create(qrySql);
		sql.setPager(pager);
		sql.setCallback(new SqlCallback() {

			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				return NutzSqlUtil.rs2MapList(rs);
			}
		});

		dao.execute(sql);
		List<Object> resultMapList = sql.getList(Object.class);
		logger.debug("qrySql:" + qrySql);
		return resultMapList;
	}

	/**
	 * 获得表信息列表
	 * 
	 * @return 表信息列表（com.wonders.tiles.autocrud.entity.AppTable）
	 */
	public List<AppTable> getTableList() {
		Sql sql = Sqls.create("SELECT * FROM QUERY_APP_TABLE WHERE VALIDITY = '1'");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(AppTable.class));
		dao.execute(sql);
		List<AppTable> tableList = sql.getList(AppTable.class);
		return tableList;
	}

	/**
	 * 获得字段信息列表
	 * 
	 * @return 字段信息列表（com.wonders.tiles.autocrud.entity.AppColumn）
	 */
	public List<AppColumn> getColumnList() {
		Sql sql = Sqls.create("SELECT * FROM QUERY_APP_COLUMN WHERE VALIDITY = '1' ORDER BY LIST_NUM");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(AppColumn.class));
		dao.execute(sql);
		List<AppColumn> columnList = sql.getList(AppColumn.class);
		return columnList;
	}

	/**
	 * 获取总记录数
	 * 
	 * @param countSql
	 * @return
	 */
	public int getCount(String countSql) {
		Sql sql = Sqls.create(countSql);
		sql.setCallback(new SqlCallback() {

			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				if (rs.next()) {
					return rs.getInt("count");
				}
				return 0;
			}
		});
		dao.execute(sql);
		int count = sql.getInt();
		return count;
	}

	/**
	 * 生成查询条件的where语句
	 * @param sbWhere
	 * @param name 字段名
	 * @param value 值
	 * @param queryList 查询字段列表
	 * @return
	 */
	public String buildQuerySql(StringBuilder sbWhere,String name, String value, List<AppColumn> queryList) {

		if (name.length() > 2) {
			if (name.substring(0, 2).equals(AutoCrudConstants.QUERY_COLUMN)) {
				// 去掉前缀"Q_"
				name = name.substring(2);

				if (name.indexOf(AutoCrudConstants.DATE_START) > 0 || name.indexOf(AutoCrudConstants.DATE_END) > 0) {
					// 时间类型
					if (name.substring(name.length() - 2).equals(AutoCrudConstants.DATE_START)) {
						name = name.substring(0, name.length() - 2);
						AppColumn column = (AppColumn) ListMapUtil.getObjectFromListById(queryList, "getColId", name);

						sbWhere.append("TO_CHAR(").append(column.getNameLetter()).append(",'yyyy-mm-dd') >= '").append(value).append("' AND ");
					}
					if (name.substring(name.length() - 2).equals(AutoCrudConstants.DATE_END)) {
						name = name.substring(0, name.length() - 2);
						AppColumn column = (AppColumn) ListMapUtil.getObjectFromListById(queryList, "getColId", name);

						sbWhere.append("TO_CHAR(").append(column.getNameLetter()).append(",'yyyy-mm-dd') <= '").append(value).append("' AND ");
					}

				} else {
					AppColumn column = (AppColumn) ListMapUtil.getObjectFromListById(queryList, "getColId", name);
					switch (column.getDataType().intValue()) {
					case AutoCrudConstants.COLUMN_TYPE_CHAR:
						sbWhere.append(column.getNameLetter()).append(" like '%").append(value).append("%' AND ");
						break;
					case AutoCrudConstants.COLUMN_TYPE_NUMBER:
					case AutoCrudConstants.COLUMN_TYPE_DIC:
						sbWhere.append(column.getNameLetter()).append(" = ").append(value).append(" AND ");
						break;
					}
				}
			}
		}
		return sbWhere.toString();
	}
	
	/**
	 * 将表的所有字段初始化到SYS_APP_COLUMN中
	 * @param tableId
	 * @param tableNameLetter
	 */
	@SuppressWarnings("unchecked")
	public void saveAllColumns(String tableId, String tableNameLetter) {
		
		String AppColumnSql = "select A.DATA_TYPE as data_type,A.COLUMN_NAME as column_name,A.DATA_LENGTH as data_length,B.COMMENTS as comments " +
				"from USER_TAB_COLUMNS a,USER_COL_COMMENTS b where A.COLUMN_NAME = B.COLUMN_NAME and A.TABLE_NAME = B.TABLE_NAME and A.TABLE_NAME = @tableName";
		
		Sql sql = Sqls.create(AppColumnSql);
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
					columnMap.put("data_length", rs.getString("data_length"));
					columnMapList.add(columnMap);
				}
				return columnMapList;
			}
		});
		dao.execute(sql);
		List<Map<String,String>> columnMapList = sql.getObject(List.class);
		
		int cursor = 1;
		List<AppColumn> appColumnList = new ArrayList<AppColumn>();

		for (Map<String,String> columnMap : columnMapList) {
			//默认的字段ID是tableId加上三位字段序号(如表名是2014,第一个字段为2014001)
			String colId = tableId + String.format("%03d", cursor);
			String columnNameLetter = columnMap.get("column_name");
			String columnName = columnMap.get("comments");
			String dataType = columnMap.get("data_type");
			String dataLength = columnMap.get("data_length");
			
			AppColumn appColumn = new AppColumn();
			appColumn.setColId(colId);
			appColumn.setTableId(tableId);
			appColumn.setName(columnName);
			appColumn.setNameLetter(columnNameLetter);
			appColumn.setListNum(Integer.valueOf(colId));
			appColumn.setDataLength(Integer.valueOf(dataLength));
			
			//判断字段类型
			if (dataType.equals("VARCHAR2") || dataType.equals("CLOB")) {
				//字符型
				appColumn.setDataType(AutoCrudConstants.COLUMN_TYPE_CHAR);
			} else if (dataType.startsWith("NUMBER") || dataType.startsWith("INTEGER")) {
				//数值型
				appColumn.setDataType(AutoCrudConstants.COLUMN_TYPE_NUMBER);
			} else if (dataType.equals("DATE") || dataType.indexOf("TIMESTAMP") >= 0) {
				//日期型
				appColumn.setDataType(AutoCrudConstants.COLUMN_TYPE_DATE);
			} else if (dataType.equals("CHAR")) {
				//字典型
				appColumn.setDataType(AutoCrudConstants.COLUMN_TYPE_DIC);
			}
		
			//默认非主键,非外键,非必录项,列表字段,非查询条件,非遗传,显示,有效
			appColumn.setIsMandatory(AutoCrudConstants.FLAG_NO);
			appColumn.setIsPk(AutoCrudConstants.FLAG_NO);
			appColumn.setIsFk(AutoCrudConstants.FLAG_NO);
			appColumn.setIsListCol(AutoCrudConstants.FLAG_YES);
			appColumn.setIsQueryCol(AutoCrudConstants.FLAG_NO);
			appColumn.setIsInherit(AutoCrudConstants.FLAG_NO);
			appColumn.setIsShow(AutoCrudConstants.FLAG_YES);
			appColumn.setValidity(AutoCrudConstants.FLAG_YES);
			
			appColumnList.add(appColumn);
			
			cursor++;
		}
		
		dao.insert(appColumnList);
		
	}
	
}
