package com.wonders.wddc.suite.data.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.service.Service;

import com.wonders.wddc.suite.csrq.entity.ColConfigBo;
import com.wonders.wddc.suite.csrq.entity.ColumnConfigBo;
import com.wonders.wddc.suite.data.entity.TableConfigBo;
import com.wonders.wddc.suite.data.service.TableService;
import com.wonders.wddc.tiles.jk.SqlExecutor;

@At("/suite/config/table")
@IocBean(fields = "dao")
public class TableAct extends Service {

	@Inject
	private TableService	tableService;
	@Inject
	private SqlExecutor		sqlExecutor;

	/**
	 * 查询现有表
	 * 
	 * @param pager
	 * @param cri
	 * @param tableName
	 * @return
	 */
	@At("/toTableList")
	@Ok("jsp:wddc.suite.data.table.index")
	public Map<String, Object> toTableList(Pager pager, Criteria cri, String tableName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!Strings.isEmpty(tableName)) {
			SqlExpressionGroup exp1 = Cnd.exps("VIEW_NAME", "like", "%" + tableName + "%");
			SqlExpressionGroup exp2 = Cnd.exps("NAME", "like", "%" + tableName + "%");
			cri.where().and(exp1.or(exp2));
		}
		cri.where().andEquals("validity", "1");
		cri.getOrderBy().desc("themeId");
		List<TableConfigBo> list = dao().query(TableConfigBo.class, cri, pager);
		int count = dao().count(TableConfigBo.class, cri);
		pager.setRecordCount(count);
		result.put("list", list);
		result.put("pager", pager);
		result.put("tableName", tableName);
		return result;
	}

	/**
	 * 新增或者修改页面
	 * 
	 * @param id
	 * @param accountBookId
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.table.edit1")
	public Map<String, Object> toEditTable(@Param("id") Integer id) {

		Map<String, Object> result = new HashMap<String, Object>();
		// 修改时需要的回填数据
		if (id != null) {
			TableConfigBo tableConfig = dao().fetch(TableConfigBo.class, id);
			if (tableConfig != null) {
				result.put("tableConfig", tableConfig);
				Criteria cri = Cnd.cri();
				cri.where().andEquals("themeId", tableConfig.getThemeId());
				// cri.where().andIn("colType", new String[] {"1","2","3","4"});
				cri.getOrderBy().asc("orderNum");

				List<ColConfigBo> columnList = dao().query(ColConfigBo.class, cri);
				// 返回字段列表

				result.put("columnList", columnList);
			}
		}
		return result;
	}

	/**
	 * 跳转到创建表SQL语句页面
	 */
	@At
	@Ok("jsp:wddc.suite.data.table.add")
	public void toAddTable() {
	}

	/**
	 * 根据SQL语句生成库表
	 * 
	 * @param TableConfigBo
	 * @param columnList
	 * @param accountBookId
	 * @return
	 */
	@At("/toSaveTable")
	@Ok("redirect:/suite/config/table/toTableList")
	public void toSaveTable(

			@Param("::tableConfig.") TableConfigBo tableConfig, @Param("::columnList") List<ColumnConfigBo> columnList) {

		// 对传入的数据进行处理 是否按照原始要求进行？？？2016-11-08
		for (ColumnConfigBo column : columnList) {

			column.setNameLetter(column.getNameLetter().toUpperCase());
			if ("3".equals(column.getEditType())) {
				// 为时间格式
				column.setColType("2");
				column.setDicId(null);
			} else if ("5".equals(column.getEditType())) {
				// 为数值格式
				column.setColType("3");
				column.setDicId(null);
			} else if ("2".equals(column.getEditType())) {
				// 为字典格式
				column.setColType("1");
				if (!"1".equalsIgnoreCase(column.getIsMultiple())) {
					column.setIsMultiple("0");
				}
			} else if ("6".equals(column.getEditType())) {
				// 富文本，为clob
				column.setColType("6");
				column.setDicId(null);
			} else if ("1".equals(column.getEditType())) {
				// 为字符格式
				column.setColType("1");
				column.setDicId(null);
			} else {
				column.setColType("4");
			}
			// 不是主键，设置对应值为0
			if (!"1".equals(column.getIsPk())) {
				column.setIsPk("0");
			}
			// 无效字段，设置对应值为0
			if (!"1".equals(column.getValidity())) {
				column.setValidity("0");
			}
		}
		// 根据传入的值去修改表结构
		tableService.changeTable(tableConfig, columnList);

	}

	/**
	 * 根据SQL语句生成库表
	 * 
	 * @param TableConfigBo
	 * @param columnList
	 * @param accountBookId
	 * @return
	 */
	@At("/toSaveTableTest")
	@Ok("json")
	public void toSaveTableTest(String dataSourceId, String crTabSQL) {
		// 根据传入的值去修改表结构
		tableService.createTableTest(crTabSQL, dataSourceId);
		// 由表生成配置信息
		// tableService.createDataByTableNameTest(dataSourceId, tableName, catalog, tableComment);

	}

	@At
	@Ok("redirect:/suite/config/table/toTableList")
	public /* Map<String, Object> */void toDelTable(@Param("id") Integer themeId) {

		if (themeId != null) {

			TableConfigBo tableConfig = dao().fetch(TableConfigBo.class, themeId);
			if (tableConfig != null) {
				tableConfig.setValidity("0");
				dao().update(tableConfig);

			}
		}
		// return UI.ajaxDone(StatusCode.OK);
	}

	/**
	 * 跳转到由库表生成配置的页面
	 */
	@At
	@Ok("jsp:wddc.suite.data.table.name_input")
	public void toNameInput() {
	}

	/**
	 * 根据数据源和表名生成表配置和字段配置
	 * 
	 * @param dataSouceId
	 * @param viewName
	 * @param tableCatalog
	 */
	@At
	@Ok("redirect:/suite/config/table/toTableList")
	public void createDataByTableName(@Param("dataSouceId") String dataSourceId, @Param("viewName") String viewName, @Param("tableCatalog") String tableCatalog,
			String tableComment) {
		tableService.createDataByTableNameTest(dataSourceId, viewName, tableCatalog, tableComment);
	}

	/**
	 * 获取最大的themeId
	 * 
	 * @param dataSouceId
	 * @param viewName
	 * @param tableCatalog
	 */
	@At
	@Ok("json")
	public int getMaxThemeId() {
		Criteria cri = Cnd.cri();
		cri.getOrderBy().desc("themeId");
		List<TableConfigBo> TableConfigBoList = dao().query(TableConfigBo.class, cri);
		if (TableConfigBoList != null && TableConfigBoList.size() > 0) {
			TableConfigBo maxTableConfigBo = TableConfigBoList.get(0);

			return maxTableConfigBo.getThemeId();
		} else {
			return 0;
		}
	}

	/*
	 * 验证表名是否存在
	 */

	@At
	@Ok("json")
	public Map<String, Object> checkTable(String viewName) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "0");
		try {
			if (!Strings.isEmpty(viewName)) {
				TableConfigBo table = dao().fetch(TableConfigBo.class, Cnd.where("viewName", "=", viewName));
				if (table != null) {
					result.put("result", "1");
				}
			}
		} catch (Exception e) {
			result.put("result", "0");
		}
		return result;
	}

	/**
	 * 更新表配置 待更新。。。。孟振乾 2017-05-10
	 * 
	 * @param themeId
	 *            表配置id
	 * @param tableDec
	 *            表描述
	 */
	@At
	@Ok("json")
	public String toUpdateTable(String themeId, String tableDec, String tabCatalog) {

		String result = "false";
		if (themeId != null) {
			TableConfigBo tableConfig = dao().fetch(TableConfigBo.class, Integer.parseInt(themeId));
			if (tableConfig != null) {
				if (tableDec != null && tableDec != "") {
					tableConfig.setName(tableDec);
				}
				if (tabCatalog != null && tabCatalog != "") {
					tableConfig.setCatalog(tabCatalog);
				}
				dao().update(tableConfig);
				result = "ok";
			}
		}
		return result;
	}

	/**
	 * 更新字段配置
	 * 
	 * @param colId
	 *            字段配置id
	 * @param colDec
	 *            字段描述
	 */
	@At
	@Ok("json")
	public String toUpdateColumn(String colCfgId, String colDec) {

		String result = "false";
		if (colCfgId != null) {
			ColConfigBo columnConfig = dao().fetch(ColConfigBo.class, Integer.parseInt(colCfgId));
			if (columnConfig != null) {
				columnConfig.setColComment(colDec);
				dao().update(columnConfig);
				result = "ok";
			}
		}
		return result;
	}

	/**
	 * 给该字段字典赋值
	 * 
	 * @param colId
	 * @param dicId
	 * @return
	 */
	@At
	@Ok("json")
	public String setDic(String colId, String dicId) {
		String result = "false";
		ColConfigBo column = dao().fetch(ColConfigBo.class, Integer.parseInt(colId));
		if (colId != null) {
			column.setDicId(dicId);
			dao().update(column);
			result = "ok";
		}
		return result;
	}

	/**
	 * 更新 有问题 待改正 mzq 2017-05-10
	 * 
	 * @param tableConfig
	 * @param columnList
	 * @param type
	 *            操作类型 1、一键跟新；2、重新生成
	 * @return
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("json")
	public String updateAll(@Param("themeId") int themeId
	// ,@Param("::columnList") List<ColumnConfigBo> columnList
	) throws Exception {
		// 1、保留当前 columnConfig、tableConfig 信息
		TableConfigBo oldTableConfig = dao().fetch(TableConfigBo.class, themeId);
		List<ColConfigBo> oldColConfigList = dao().query(ColConfigBo.class, Cnd.where("THEME_ID", "=", oldTableConfig.getThemeId()));
		// key:字段英文名 value:字段配置信息
		Map<String, ColConfigBo> oldColMap = new HashMap<String, ColConfigBo>();
		for (ColConfigBo colConfig : oldColConfigList) {
			oldColMap.put(colConfig.getColName(), colConfig);
		}

		// 2、重读表结构 获取表信息 字段信息

		List<ColConfigBo> newColConfigList = sqlExecutor.getColConfigBoList(oldTableConfig.getViewName(), oldTableConfig.getDataSourceId());
		// Map<String,ColConfigBo> newColMap =new HashMap<String,ColConfigBo>();

		// for(ColConfigBo colConfig:newColConfigList){
		// newColMap.put(colConfig.getColName(), colConfig);
		// }

		// List<ColConfigBo> toDelList = new ArrayList<ColConfigBo>();
		// List<ColConfigBo> toUpdateList = new ArrayList<ColConfigBo>();
		// List<ColConfigBo> toInsertList = new ArrayList<ColConfigBo>();
		// 3、判断需要删除的元素
		Set<String> oldSet = new HashSet<String>();
		// Set<String> newSet = new HashSet<String>();

		oldSet = oldColMap.keySet();
		/*
		 * newSet = newColMap.keySet();
		 * 
		 * //需要删除和修改的配置 for(ColConfigBo colConfig:oldColConfigList){ if(newSet != null){ if(!newSet.contains(colConfig.getColName())){ toDelList.add(colConfig); }else{
		 * colConfig.setColType(newColMap.get(colConfig.getColName()).getColType()); String colComment = newColMap.get(colConfig.getColName()).getColComment(); if(colComment !=
		 * null && colComment != ""){ colConfig.setColComment(colComment); } toUpdateList.add(colConfig); } } }
		 * 
		 * //需要新增的配置 for(ColConfigBo colConfig:newColConfigList){ if(oldSet != null){ //新增 if(!oldSet.contains(colConfig.getColName())){ Criteria cri2 = Cnd.cri();
		 * cri2.getOrderBy().desc("colCfgId"); List<ColConfigBo> colConfigList = dao().query(ColConfigBo.class, cri2); int maxId = 0; if(colConfigList != null &&
		 * colConfigList.size()>0){ ColConfigBo maxColumnConfig = colConfigList.get(0); maxId = maxColumnConfig.getColCfgId()+1; }else{ maxId = 1; } colConfig.setColCfgId(maxId);
		 * colConfig.setThemeId(themeId); toInsertList.add(colConfig);
		 * 
		 * }
		 * 
		 * 
		 * } }
		 * 
		 * for(ColConfigBo colConfig:toDelList){ dao().delete(colConfig); }
		 * 
		 * for(ColConfigBo colConfig:toUpdateList){ dao().update(colConfig); }
		 * 
		 * for(ColConfigBo colConfig:toInsertList){ dao().insert(colConfig); }
		 */

		// 一次搞定
		for (ColConfigBo colConfig : newColConfigList) {
			if (oldSet != null) {
				// 新增
				if (!oldSet.contains(colConfig.getColName())) {
					Criteria cri2 = Cnd.cri();
					cri2.getOrderBy().desc("colCfgId");
					List<ColConfigBo> colConfigList = dao().query(ColConfigBo.class, cri2);
					int maxId = 0;
					if (colConfigList != null && colConfigList.size() > 0) {
						ColConfigBo maxColumnConfig = colConfigList.get(0);
						maxId = maxColumnConfig.getColCfgId() + 1;
					} else {
						maxId = 1;
					}
					colConfig.setColCfgId(maxId);
					colConfig.setThemeId(themeId);
					/* toInsertList.add(colConfig); */
					dao().insert(colConfig);

				}
				// 更新
				if (oldSet.contains(colConfig.getColName())) {
					ColConfigBo toUpdate = oldColMap.get(colConfig.getColName());

					toUpdate.setColType(colConfig.getColType());
					String colComment = colConfig.getColComment();
					if (colComment != null && colComment != "") {
						toUpdate.setColComment(colComment);
					}
					dao().update(toUpdate);
					oldColMap.remove(colConfig.getColName());
				}
			}
		}

		// 删除
		// 遍历map
		for (String key : oldColMap.keySet()) {
			dao().delete(oldColMap.get(key));
		}
		return "ok";

	}

	// 获取可以使用的表名称集合
	@At
	@Ok("json")
	public List<String> getTabNameList(@Param("dataSourceId") String dataSourceId) throws Exception {
		List<String> tabConfigNameList = null;
		List<String> dbSchemTabNameList = null;
		try {
			tabConfigNameList = tableService.getTabConfNameList(dataSourceId);
			dbSchemTabNameList = sqlExecutor.getSchemTabNameList(dataSourceId);
			if (tabConfigNameList != null && tabConfigNameList.size() > 0) {
				if (dbSchemTabNameList != null && dbSchemTabNameList.size() > 0) {
					dbSchemTabNameList.removeAll(tabConfigNameList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		;

		// 测试TableConfigBo
		// String tableName = "suite_test";
		// Map<String,String> tableConfigMap = sqlExecutor.getTableConfigBo(tableName, dataSourceId);
		// TableConfigBo table = sqlExecutor.getTableConfigBoBean(tableName, dataSourceId);
		// List<Map<String,String>> mapColConfigList = sqlExecutor.getColConfigList(tableName, dataSourceId);
		return dbSchemTabNameList;
	}

	/**
	 * 删除表配置和字段配置
	 * 
	 * @param themeId
	 */
	@At
	@Ok("redirect:/suite/config/table/toTableList")
	public void deletTableConfig(String themeId) {
		TableConfigBo tabConfig = dao().fetch(TableConfigBo.class, Integer.parseInt(themeId));
		if (tabConfig != null) {
			dao().delete(tabConfig);
		}
		List<ColConfigBo> colConfigList = dao().query(ColConfigBo.class, Cnd.where("THEME_ID", "=", Integer.parseInt(themeId)));
		if (colConfigList.size() > 0 && colConfigList != null) {
			for (int i = 0; i < colConfigList.size(); i++) {
				dao().delete(colConfigList.get(i));
			}
		}

	}

	/**
	 * 根据数据源提供配置表的表名和表描述
	 * 
	 * @param dataSourceId
	 *            数据源id
	 * @return 包含 表中文和表英文名的 Json串
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("json")
	public Object getCfgTabNameListJson(@Param("dataSourceId") String dataSourceId) {
		String tableNameSql = "SELECT NAME ,VIEW_NAME FROM SUITE_TABLE_CONFIG WHERE DATA_SOURCE_ID='" + dataSourceId + "'";
		Sql existsSql = Sqls.create(tableNameSql);
		Map<String, String> result = new HashMap<String, String>();
		existsSql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				Map<String, String> colMap = new HashMap<String, String>();
				while (rs.next()) {
					colMap.put(rs.getString("VIEW_NAME").toUpperCase(), rs.getString("NAME"));
				}
				return colMap;
			}
		});
		dao().execute(existsSql);
		result = (Map<String, String>) existsSql.getResult();
		return result;
	}

	/**
	 * 根据数据源和库表名获取字段配置信息
	 * 
	 * @param dataSourceId
	 *            数据源id
	 * @return 包含 表中文和表英文名的 Json串
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("json")
	public Object getCfgColsJson(@Param("dataSourceId") String dataSourceId, String tableName) {
		List<ColConfigBo> result = new ArrayList<ColConfigBo>();
		// 根据数据源id和表名获取表配置id
		TableConfigBo tabCfg = dao().fetch(TableConfigBo.class, Cnd.where("dataSourceId", "=", dataSourceId).and("viewName", "=", tableName));
		// 根据表配置id获取字段配置信息
		result = dao().query(ColConfigBo.class, Cnd.where("themeId", "=", tabCfg.getThemeId()));
		return result;
	}

}
