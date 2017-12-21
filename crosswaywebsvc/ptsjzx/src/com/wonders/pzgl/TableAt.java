package com.wonders.pzgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.service.Service;

import com.wonders.pzgl.entity.ColumnConfig;
import com.wonders.pzgl.entity.TableConfig;
import com.wonders.pzgl.service.PzglTableService;
import com.wonders.tiles.dic.DicConfigManager;
import com.wonders.tiles.ui.UI;
import com.wonders.tiles.ui.UI.StatusCode;

@At("/config/table")
@IocBean(fields = "dao")
public class TableAt extends Service {
	@Inject
	private PzglTableService pzglTableService;
	@Inject
	private DicConfigManager dicConfigManager;

	@At("/toIndex")
	@Ok("jsp:jsp.pzgl.index")
	public Map<String, Object> toIndex() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	/**
	 * 查询现有表
	 * 
	 * @param pager
	 * @param cri
	 * @param tableName
	 * @return
	 */
	@At("/toTableList")
	@Ok("jsp:jsp.pzgl.table.list")
	public Map<String, Object> toTableList(Pager pager, Criteria cri,
			String tableName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!Strings.isEmpty(tableName)) {
			SqlExpressionGroup exp1 = Cnd.exps("VIEW_NAME", "like", "%"
					+ tableName + "%");
			SqlExpressionGroup exp2 = Cnd.exps("NAME", "like", "%" + tableName
					+ "%");
			cri.where().and(exp1.or(exp2));
		}
		cri.where().andEquals("validity", "1");
		cri.getOrderBy().desc("themeId");
		List<TableConfig> list = dao().query(TableConfig.class, cri, pager);
		int count = dao().count(TableConfig.class, cri);
		pager.setRecordCount(count);
		result.put("list", list);
		result.put("pager", pager);
		result.put("tableName", tableName);
		return result;
	}

	/**
	 * 新增修改页面
	 * 
	 * @param id
	 * @param accountBookId
	 * @return
	 */
	@At
	@Ok("jsp:jsp.pzgl.table.edit")
	public Map<String, Object> toEditTable(@Param("id") Integer id,
			@Param("accountBookId") String accountBookId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (id != null) {
			TableConfig tableConfig = dao().fetch(TableConfig.class, id);
			if (tableConfig != null) {
				result.put("tableConfig", tableConfig);
				Criteria cri = Cnd.cri();
				cri.where().andEquals("themeId", tableConfig.getThemeId());
				cri.where().andIn("columnType", new String[] { "1", "2", "3" });
				cri.getOrderBy().asc("orderNum");
				List<ColumnConfig> columnList = dao().query(ColumnConfig.class,
						cri);
				result.put("columnList", columnList);
			}
		}
		Map<String, String> dicMap = dicConfigManager.getAllDicName();
		result.put("accountBookId", accountBookId);
		result.put("dicMap", dicMap);
		return result;
	}

	/**
	 * 保存表信息
	 * 
	 * @param tableConfig
	 * @param columnList
	 * @param accountBookId
	 * @return
	 */
	@At
	@Ok("json")
	public Map<String, Object> toSaveTable(
			@Param("::tableConfig.") TableConfig tableConfig,
			@Param("::columnList") List<ColumnConfig> columnList,
			@Param("accountBookId") String accountBookId) {
		// 对传回数据进行处理
		for (ColumnConfig column : columnList) {
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
			} else {
				// 为字符和大文本格式
				column.setColType("1");
				column.setDicId(null);
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
		pzglTableService.changeTable(tableConfig, columnList);
		dicConfigManager.reload();
		if (!Strings.isEmpty(accountBookId)) {
			return UI.ajaxDone(StatusCode.OK, "保存成功！",
					"/dcbs/form/toDefine?accountBookId=" + accountBookId
							+ "&themeId=" + tableConfig.getThemeId());
		} else {
			return UI.closeAjaxDone(StatusCode.OK, "tableManageList");
		}
	}

	@At
	@Ok("jsp:jsp.pzgl.table.name_input")
	public void toNameInput() {
	}

	@At
	@Ok("json")
	public Map<String, Object> createDataByTableName(String viewName,String tableName) {
		pzglTableService.createDataByTableName(viewName,tableName);
		return UI.closeAjaxDone(StatusCode.OK, "tableManageList");
	}
	@At
	@Ok("json")
	public Map<String, Object> toDelTable(@Param("id") Integer id) {
		if (id != null) {
			TableConfig tableConfig = dao().fetch(TableConfig.class, id);
			if (tableConfig != null) {
				tableConfig.setValidity("0");
				dao().update(tableConfig);
			}
		}
		return UI.ajaxDone(StatusCode.OK);
	}
}
