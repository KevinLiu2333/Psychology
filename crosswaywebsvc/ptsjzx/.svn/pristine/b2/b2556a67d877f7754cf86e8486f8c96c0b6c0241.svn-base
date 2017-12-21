package com.wonders.tiles.autocrud.ui;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.tiles.autocrud.AutoCrudConstants;
import com.wonders.tiles.autocrud.cacher.AutoCrudCache;
import com.wonders.tiles.autocrud.entity.AppColumn;
import com.wonders.tiles.autocrud.entity.AppTable;
import com.wonders.tiles.autocrud.service.AutoCrudService;
import com.wonders.tiles.extend.ui.UI;
import com.wonders.tiles.extend.ui.UI.StatusCode;
import com.wonders.util.DeepCloneUtil;
import com.wonders.util.ListMapUtil;

@At("/autocrud")
@IocBean
public class AutoCrudAt {

	@Inject
	private AutoCrudService autoCrudService;

	@Inject
	private AutoCrudCache autoCrudCache;

	/**
	 * 打开业务表单列表页面
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.autocrud.table_list")
	public Map<String, Object> toTableList() {

		// 所有表
		List<AppTable> tableList = autoCrudCache.getAppTableList();

		// 顶层表
		List<AppTable> ancestorTableList = (List<AppTable>) ListMapUtil.getSubListFromListById(tableList, "getFatherId", null);

		// 从内存中读出所有配置的表,传到页面
		Map<String, Object> objMap = new HashMap<String, Object>();
		objMap.put("tableList", tableList);
		objMap.put("ancestorTableList", ancestorTableList);

		return objMap;
	}

	/**
	 * 打开业务表单信息查询列表页面
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.autocrud.table_data_list")
	public Map<String, Object> toTableDataList(HttpServletRequest request) {

		// ========页面上需要的参数
		String fkId = null; // 外键
		String tableId = null; // 表编号
		List<AppTable> tableList = new ArrayList<AppTable>(); // 所有表
		AppTable table = null; // 当前表
		List<AppTable> subTableList = new ArrayList<AppTable>(); // 当前表的子表
		List<AppColumn> columnList = new ArrayList<AppColumn>(); // 当前表的字段
		List<AppColumn> queryList = new ArrayList<AppColumn>(); // 当前表的查询字段
		List<AppColumn> resultList = new ArrayList<AppColumn>(); // 当前表的结果字段
		List<Object> formList = new ArrayList<Object>(); // 当前表的结果列表
		Pager pager = null; // 当前表的分页对象
		AppColumn pkCol = null; // 主键
		Map<String, String> parameterMap = new HashMap<String, String>(); // 查询参数回传

		// ==================================================
		// 表编号
		tableId = request.getParameter("tableId");
		// 表列表
		tableList = autoCrudCache.getAppTableList();
		// 表
		table = (AppTable) ListMapUtil.getObjectFromListById(tableList, "getTableId", tableId);
		// 表中字段列表
		columnList = table.getAppColumnList();
		// 查询条件字段列表
		queryList = (List<AppColumn>) ListMapUtil.getSubListFromListById(columnList, "getIsQueryCol", "1");
		// 查询结果字段列表
		resultList = (List<AppColumn>) ListMapUtil.getSubListFromListById(columnList, "getIsListCol", "1");

		// ==================================================

		StringBuilder sbSelect = new StringBuilder("SELECT ");
		StringBuilder sbFrom = new StringBuilder("FROM ");
		StringBuilder sbWhere = new StringBuilder("WHERE 1=1 AND ");
		if (resultList != null && resultList.size() > 0) {
			// 1、拼接SELECT语法
			// 增加主键列
			pkCol = (AppColumn) ListMapUtil.getObjectFromListById(resultList, "getIsPk", "1");
			if (pkCol == null) {
				pkCol = (AppColumn) ListMapUtil.getObjectFromListById(columnList, "getIsPk", "1");
				
				if (pkCol != null) {
					//配置了主键
					resultList.add(pkCol);
				}
			}

			int resultSize = resultList.size();
			for (int i = 0; i <= resultSize - 1; i++) {
				AppColumn col = resultList.get(i);
				if (i < resultSize - 1)
					sbSelect.append(col.getNameLetter()).append(",");
				else {
					sbSelect.append(col.getNameLetter()).append(" ");
				}
			}

			// 2、拼接FROM语法
			sbFrom.append(table.getNameLetter()).append(" ");

			// 3、拼接WHERE语法

			// 增加外键条件
			AppColumn fkCol = (AppColumn) ListMapUtil.getObjectFromListById(columnList, "getIsFk", "1");
			fkId = request.getParameter("fkId");// 外键值
			if (fkCol != null && StringUtils.isNotEmpty(fkId)) {
				sbWhere.append(fkCol.getNameLetter()).append(" = '").append(fkId).append("' AND ");
			}

			Enumeration<?> em = request.getParameterNames();

			while (em.hasMoreElements()) {
				String name = (String) em.nextElement();
				String value = request.getParameter(name);
				// 如果值不为空，则进行条件拼接
				if (!StringUtils.isEmpty(value)) {
					// 保存起来以便页面利用
					parameterMap.put(name, value);
					autoCrudService.buildQuerySql(sbWhere, name, value, queryList);
				}

			}

		}

		String strWhere = null;
		if (sbWhere.substring(sbWhere.length() - 4).equals(AutoCrudConstants.QUERY_AND))
			strWhere = sbWhere.substring(0, sbWhere.length() - 4);

		// 4、拼接查询语法
		String querySyntax = sbSelect.toString() + sbFrom.toString() + strWhere;
		String queryCountSyntax = "SELECT COUNT(1) AS count " + sbFrom.toString() + strWhere;

		// 5、查询数据库获得结果集
		int currentPage = StringUtils.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
		int numPerPage = StringUtils.isEmpty(request.getParameter("numPerPage")) ? AutoCrudConstants.DEFAULT_PAGE_SIZE : Integer.parseInt(request.getParameter("numPerPage"));

		formList = autoCrudService.getQueryResult(queryCountSyntax, querySyntax, numPerPage, currentPage);

		// 构造分页对象
		pager = new Pager();
		pager.setPageNumber(currentPage);
		pager.setPageSize(numPerPage);
		pager.setRecordCount(autoCrudService.getCount(queryCountSyntax));

		subTableList = (List<AppTable>) ListMapUtil.getSubListFromListById(tableList, "getFatherId", tableId);

		// 将页面需要的结果组成一个Map
		Map<String, Object> objMap = new HashMap<String, Object>();

		objMap.put("fkId", fkId);
		objMap.put("tableId", tableId);
		objMap.put("tableList", tableList);
		objMap.put("table", table);
		objMap.put("subTableList", subTableList);
		objMap.put("columnList", columnList);
		objMap.put("queryList", queryList);
		objMap.put("resultList", resultList);
		objMap.put("formList", formList);
		objMap.put("pager", pager);
		objMap.put("pkCol", pkCol);
		objMap.put("parameterMap", parameterMap);

		return objMap;
	}

	/**
	 * 准备新增一条记录
	 */
	@At
	@Ok("jsp:jsp.autocrud.table_data_info")
	public Map<String, Object> toAddRow(HttpServletRequest request) {
		// ========页面上需要的参数
		String tableId = request.getParameter("tableId");
		// 表ID
		String fkId = request.getParameter("fkId");
		// 外键ID
		AppTable table = null; // 当前表
		List<AppColumn> columnList = new ArrayList<AppColumn>(); // 字段列表

		List<AppTable> tableList = autoCrudCache.getAppTableList();
		table = (AppTable) ListMapUtil.getObjectFromListById(tableList, "getTableId", tableId);
		columnList = table.getAppColumnList();

		// 将页面需要的结果组成一个Map
		Map<String, Object> objMap = new HashMap<String, Object>();

		objMap.put("tableId", tableId);
		objMap.put("fkId", fkId);
		objMap.put("table", table);
		objMap.put("columnList", columnList);

		return objMap;
	}

	/**
	 * 新增一条记录
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("json")
	public Map<String, Object> addRow(HttpServletRequest request) {
		// ==================================================
		// 表编号
		String tableId = request.getParameter("tableId");
		// 表列表
		List<AppTable> tableList = autoCrudCache.getAppTableList();
		// 表
		AppTable table = (AppTable) ListMapUtil.getObjectFromListById(tableList, "getTableId", tableId);
		// 表中字段列表
		List<AppColumn> columnList = table.getAppColumnList();
		// ==================================================

		// clone一个以免改变内存对象
		List<AppColumn> cloneList = (List<AppColumn>) DeepCloneUtil.deepClone(columnList);

		// 1、将用户在页面填写的内容填充到对应的字段对象中
		// String fkId = null;
		Enumeration<?> em = request.getParameterNames();
		String newTableId = ""; //新增表的ID
		String newTableNameLetter = ""; //新增表的英文名
		while (em.hasMoreElements()) {
			String name = (String) em.nextElement();
			String value = request.getParameter(name);

			// 如果值不为空，则进行条件拼接
			if (!StringUtils.isEmpty(value)) {
				AppColumn column = (AppColumn) ListMapUtil.getObjectFromListById(cloneList, "getColId", name);
				if (column != null) {
					column.setValue(value);
				}
				if (AutoCrudConstants.TABLE_ID_COL.equals(name)) {
					newTableId = value; 
				}
				if (AutoCrudConstants.TABLE_NAMELETTER_COL.equals(name)) {
					newTableNameLetter = value; 
				}
			}
		}
		
		String isGenAllCols = request.getParameter("isGenAllCols");
		//如果是修改配置表,可以选择是否生成字段
		if (StringUtils.isNotEmpty(isGenAllCols) && isGenAllCols.equals("1")) {
			autoCrudService.saveAllColumns(newTableId,newTableNameLetter);
		}

		// 内容保存到数据库
		autoCrudService.saveOrUpdateRecord(table.getNameLetter(), cloneList);

		// 跳转方法的参数
		return UI.closeAjaxDone(StatusCode.OK, "autocrudTableDataList", "保存成功");
	}

	/**
	 * 修改一条记录
	 * 
	 * @param request
	 */
	@At
	@Ok("jsp:jsp.autocrud.table_data_info")
	public Map<String, Object> toUpdateRow(HttpServletRequest request) {
		// ========页面上需要的参数
		String tableId = request.getParameter("tableId");
		; // 表ID
		String fkId = request.getParameter("fkId");
		; // 外键ID
		AppTable table = null; // 当前表
		List<AppColumn> columnList = new ArrayList<AppColumn>(); // 字段列表
		Map<String, Object> recordMap = new HashMap<String, Object>(); // 当前记录

		// ==================================================
		// 表列表
		List<AppTable> tableList = autoCrudCache.getAppTableList();
		// 表
		table = (AppTable) ListMapUtil.getObjectFromListById(tableList, "getTableId", tableId);
		// 表中字段列表
		columnList = table.getAppColumnList();
		// ==================================================

		AppColumn pkCol = (AppColumn) ListMapUtil.getObjectFromListById(columnList, "getIsPk", "1");
		String pkId = request.getParameter(pkCol.getNameLetter());

		// 到数据库取数据
		if (pkId != null) {
			recordMap = autoCrudService.getRecordByPK(table.getNameLetter(), pkCol.getNameLetter(), pkId);
		}

		// 将页面需要的结果组成一个Map
		Map<String, Object> objMap = new HashMap<String, Object>();

		objMap.put("tableId", tableId);
		objMap.put("fkId", fkId);
		objMap.put("table", table);
		objMap.put("columnList", columnList);
		objMap.put("recordMap", recordMap);

		return objMap;
	}

	/**
	 * 删除一条记录
	 * 
	 * @param request
	 */
	@At
	@Ok("json")
	public Map<String, Object> toDeleteRow(HttpServletRequest request) {
		// ==================================================
		// 表编号
		String tableId = request.getParameter("tableId");
		// 表列表
		List<AppTable> tableList = autoCrudCache.getAppTableList();
		// 表
		AppTable table = (AppTable) ListMapUtil.getObjectFromListById(tableList, "getTableId", tableId);
		// 表中字段列表
		List<AppColumn> columnList = table.getAppColumnList();
		// ==================================================

		AppColumn pkCol = (AppColumn) ListMapUtil.getObjectFromListById(columnList, "getIsPk", "1");
		String[] pkIds = request.getParameterValues(pkCol.getNameLetter());

		for (String pkId : pkIds) {
			autoCrudService.removeRecordByPK(table.getNameLetter(), pkCol.getNameLetter(), pkId);
		}

		return UI.ajaxDone(StatusCode.OK, "成功删除了" + pkIds.length + "条记录");

	}

	/**
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.autocrud.table_data_lookup_list")
	public Map<String, Object> toTableDataLookUpList(HttpServletRequest request) {

		// ========页面上需要的参数
		String tableId = null; // 表编号
		List<AppTable> tableList = new ArrayList<AppTable>(); // 所有表
		AppTable table = null; // 当前表
		List<AppColumn> columnList = new ArrayList<AppColumn>(); // 当前表的字段
		List<AppColumn> queryList = new ArrayList<AppColumn>(); // 当前表的查询字段
		List<AppColumn> resultList = new ArrayList<AppColumn>(); // 当前表的结果字段
		List<Object> formList = new ArrayList<Object>(); // 当前表的结果列表
		Pager pager = null; // 当前表的分页对象
		AppColumn pkCol = null; // 主键
		Map<String, String> parameterMap = new HashMap<String, String>(); // 查询参数回传
		String fkName = null; //外键名称

		// ==================================================
		fkName = request.getParameter("fkName");
		// 表列表
		tableList = autoCrudCache.getAppTableList();
		// 子表编号
		String childTableId = request.getParameter("childTableId");
		// 子表
		AppTable childTable = (AppTable) ListMapUtil.getObjectFromListById(tableList, "getTableId", childTableId);
		// 表编号
		tableId = request.getParameter("tableId");
		//如果是从查找页面过来的直接从request中取,否则从字表中取
		if (Strings.isEmpty(tableId)) {
			tableId = childTable.getFatherId();
		}
		// 表
		table = (AppTable) ListMapUtil.getObjectFromListById(tableList, "getTableId", tableId);
		// 表中字段列表
		columnList = table.getAppColumnList();
		// 查询条件字段列表
		queryList = (List<AppColumn>) ListMapUtil.getSubListFromListById(columnList, "getIsQueryCol", "1");
		// 查询结果字段列表
		resultList = (List<AppColumn>) ListMapUtil.getSubListFromListById(columnList, "getIsListCol", "1");

		// ==================================================

		StringBuilder sbSelect = new StringBuilder("SELECT ");
		StringBuilder sbFrom = new StringBuilder("FROM ");
		StringBuilder sbWhere = new StringBuilder("WHERE 1=1 AND ");
		if (resultList != null && resultList.size() > 0) {
			// 1、拼接SELECT语法
			// 增加主键列
			pkCol = (AppColumn) ListMapUtil.getObjectFromListById(resultList, "getIsPk", "1");
			if (pkCol == null) {
				pkCol = (AppColumn) ListMapUtil.getObjectFromListById(columnList, "getIsPk", "1");
				resultList.add(pkCol);
			}

			int resultSize = resultList.size();
			for (int i = 0; i <= resultSize - 1; i++) {
				AppColumn col = resultList.get(i);
				if (i < resultSize - 1)
					sbSelect.append(col.getNameLetter()).append(",");
				else {
					sbSelect.append(col.getNameLetter()).append(" ");
				}
			}

			// 2、拼接FROM语法
			sbFrom.append(table.getNameLetter()).append(" ");

			// 3、拼接WHERE语法

			Enumeration<?> em = request.getParameterNames();

			while (em.hasMoreElements()) {
				String name = (String) em.nextElement();
				String value = request.getParameter(name);
				// 如果值不为空，则进行条件拼接
				if (!StringUtils.isEmpty(value)) {
					// 保存起来以便页面利用
					parameterMap.put(name, value);
					autoCrudService.buildQuerySql(sbWhere, name, value, queryList);
				}

			}

		}

		String strWhere = null;
		if (sbWhere.substring(sbWhere.length() - 4).equals(AutoCrudConstants.QUERY_AND))
			strWhere = sbWhere.substring(0, sbWhere.length() - 4);

		// 4、拼接查询语法
		String querySyntax = sbSelect.toString() + sbFrom.toString() + strWhere;
		String queryCountSyntax = "SELECT COUNT(1) AS count " + sbFrom.toString() + strWhere;

		// 5、查询数据库获得结果集
		int currentPage = StringUtils.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
		// 每页10行
		formList = autoCrudService.getQueryResult(queryCountSyntax, querySyntax, AutoCrudConstants.DEFAULT_PAGE_SIZE, currentPage);

		// 构造分页对象
		pager = new Pager();
		pager.setPageNumber(currentPage);
		pager.setPageSize(AutoCrudConstants.DEFAULT_PAGE_SIZE);
		pager.setRecordCount(autoCrudService.getCount(queryCountSyntax));

		// 将页面需要的结果组成一个Map
		Map<String, Object> objMap = new HashMap<String, Object>();

		objMap.put("tableId", tableId);
		objMap.put("tableList", tableList);
		objMap.put("table", table);
		objMap.put("columnList", columnList);
		objMap.put("queryList", queryList);
		objMap.put("resultList", resultList);
		objMap.put("formList", formList);
		objMap.put("pager", pager);
		objMap.put("pkCol", pkCol);
		objMap.put("parameterMap", parameterMap);
		objMap.put("fkName",fkName);

		return objMap;

	}
	
	/**
	 * 重启缓存
	 */
	@At
	@Ok("forward:/autocrud/toTableList")
	public void reloadCache() {
		autoCrudCache.load();
	}
}
