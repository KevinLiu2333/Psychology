package com.wonders.pzgl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.ViewWrapper;
import org.nutz.service.Service;

import com.wonders.pzgl.entity.ColRelationConfig;
import com.wonders.pzgl.entity.ColumnConfig;
import com.wonders.pzgl.entity.Dic;
import com.wonders.pzgl.entity.DwLog;
import com.wonders.pzgl.entity.FormConfig;
import com.wonders.pzgl.entity.TableConfig;
import com.wonders.pzgl.service.QueryService;
import com.wonders.pzgl.util.ArrayUtil;
import com.wonders.pzgl.util.CamelCaseUtils;
import com.wonders.pzgl.util.VelocityUtils;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.dic.entity.DicDept;
import com.wonders.tiles.ui.UI;
import com.wonders.tiles.ui.UI.StatusCode;
import com.wonders.util.PropertyUtils;
import com.wonders.utils.CookieUtils;

@At("/config/form")
@IocBean(fields = "dao")
public class FormAt extends Service {

	@Inject
	private QueryService queryService;

	@At
	@Ok("jsp:jsp.pzgl.form.list")
	public Map<String, Object> toFormList(Criteria cri, Pager pager,
			String formName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!Strings.isEmpty(formName)) {
			SqlExpressionGroup exp1 = Cnd.exps("DREAMFORM_NAME", "like", "%"
					+ formName + "%");
			cri.where().and(exp1);
		}
		cri.getOrderBy().desc("createTime");
		List<FormConfig> list = dao().query(FormConfig.class, cri, pager);
		int count = dao().count(FormConfig.class, cri);
		pager.setRecordCount(count);
		result.put("list", list);
		result.put("pager", pager);
		result.put("formName", formName);
		return result;
	}

	/**
	 * 进入查询定义界面，包含查询新增定义及查询定义修改
	 */
	@At
	@Ok("jsp:jsp.pzgl.form.form_edit")
	public Map<String, Object> toEditForm(@Param("id") String dreamformId,
			String ifEdit, String[] editSelectChild,
			@Param("::formConfig.") FormConfig formConfig) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取当前所有库表列表
		List<TableConfig> themeList = new ArrayList<TableConfig>();
		// 子表可选条件
		List<ColumnConfig> unselectedEditsChild = new ArrayList<ColumnConfig>();
		// 子表已选条件
		List<ColumnConfig> selectedEditsChild = new ArrayList<ColumnConfig>();
		// 父表所有列
		List<ColumnConfig> columnListParent = new ArrayList<ColumnConfig>();
		// 子表所有列
		List<ColumnConfig> columnListChild = new ArrayList<ColumnConfig>();
		// 基础表所有列
		List<ColumnConfig> columnListBase = new ArrayList<ColumnConfig>();
		columnListBase = queryService.queryColumnList(new Integer(1));
		result.put("columnListBase", columnListBase);
		// 关联关系
		List<ColRelationConfig> relationList = new ArrayList<ColRelationConfig>();
		// 查询出现有所有表
		themeList = queryService.queryThemeListByCatalog(null);
		result.put("themeList", themeList);
		// ifEdit作用，判断是否为编辑页面自身提交刷新 ifEdit=1为编辑页面自身提交
		if (StringUtils.isEmpty(ifEdit)) {
			// 为空，表示不为自身提交，需要读取字段数据
			if (StringUtils.isNotEmpty(dreamformId)) {
				// 为修改的情况
				formConfig = queryService.queryFormConfig(dreamformId);
				columnListParent = queryService.queryColumnList(formConfig
						.getDreamformThemeId());
				// 将已经定义好的查询条件放入Map（配置项主键，配置项主键）
				Map<String, String> editMap = new HashMap<String, String>();
				if (StringUtils.isNotBlank(formConfig.getDreamformEditCols())) {
					String[] refStrings = formConfig.getDreamformEditCols()
							.split(",");
					for (String refString : refStrings) {
						editMap.put(refString, refString);
					}
				}
				if ("1".equals(formConfig.getIfHaveChild())) {
					columnListChild = queryService.queryColumnList(formConfig
							.getChildTableId());
					editMap = new HashMap<String, String>();
					if (StringUtils.isNotBlank(formConfig.getChildEditCols())) {
						String[] refStrings = formConfig.getChildEditCols()
								.split(",");
						for (String refString : refStrings) {
							editMap.put(refString, refString);
						}
					}
					for (ColumnConfig column : columnListChild) {
						if ((column.getColumnType().equals("1") || column
								.getColumnType().equals("2"))
								&& !editMap.containsKey("" + column.getColId())) {
							unselectedEditsChild.add(column);
						} else if (editMap.containsKey("" + column.getColId())) {
							selectedEditsChild.add(column);

						}
					}
				}
				Criteria cri = Cnd.cri();
				cri.where().and("dreamformId", "=", dreamformId);
				cri.getOrderBy().asc("listNum");
				relationList = dao().query(ColRelationConfig.class, cri);
				result.put("relationList", relationList);
				// 查询主题相关
				result.put("formConfig", formConfig);
				result.put("columnListParent", columnListParent);
				result.put("columnListChild", columnListChild);
				// 查询条件和查询结果
				result.put("unselectedEditsChild", unselectedEditsChild);
				result.put("selectedEditsChild", selectedEditsChild);
			}
		} else if ("1".equals(ifEdit)) {
			columnListParent = queryService.queryColumnList(formConfig
					.getDreamformThemeId());
			// 将已经定义好的查询条件放入Map（配置项主键，配置项主键）
			Map<String, String> editMap = new HashMap<String, String>();
			formConfig.setChildEditCols(ArrayUtil.strArray2StrSplit(
					editSelectChild, ","));
			if (StringUtils.isNotBlank(formConfig.getDreamformEditCols())) {
				String[] refStrings = formConfig.getDreamformEditCols().split(
						",");
				for (String refString : refStrings) {
					editMap.put(refString, refString);
				}
			}
			if ("1".equals(formConfig.getIfHaveChild())) {
				columnListChild = queryService.queryColumnList(formConfig
						.getChildTableId());
				editMap = new HashMap<String, String>();
				if (StringUtils.isNotBlank(formConfig.getChildEditCols())) {
					String[] refStrings = formConfig.getChildEditCols().split(
							",");
					for (String refString : refStrings) {
						editMap.put(refString, refString);
					}
				}
				for (ColumnConfig column : columnListChild) {
					if ((column.getColumnType().equals("1") || column
							.getColumnType().equals("2"))
							&& !editMap.containsKey("" + column.getColId())) {
						unselectedEditsChild.add(column);
					} else if (editMap.containsKey("" + column.getColId())) {
						selectedEditsChild.add(column);

					}
				}
			}
			// 查询主题相关
			result.put("formConfig", formConfig);
			result.put("columnListParent", columnListParent);
			result.put("columnListChild", columnListChild);
			// 查询条件和查询结果
			result.put("unselectedEditsChild", unselectedEditsChild);
			result.put("selectedEditsChild", selectedEditsChild);
		}
		return result;
	}

	@At("/toSaveForm")
	@Ok("redirect:/config/form/toDesignEdit?dreamformId=${obj.dreamformId}")
	public Map<String, Object> toSaveForm(
			@Param("::colList") List<ColRelationConfig> colList,
			String[] editSelectChild,
			@Param("::formConfig.") FormConfig formConfig) throws Exception {
		String generDetailHtml = "";
		String generListHtml = "";
		// 判断是新增还是更新
		if (StringUtils.isBlank(formConfig.getDreamformId())) {
			String editCols = "";
			for (ColRelationConfig colRelationConfig : colList) {
				if (!Strings.isEmpty(colRelationConfig.getEditCol())) {
					editCols = editCols + colRelationConfig.getEditCol() + ",";
				}
			}
			if (!",".equals(editCols)) {
				editCols = editCols.substring(0, editCols.length() - 1);
			}
			formConfig.setDreamformEditCols(editCols);
			Date dateNow = new Date();
			formConfig.setDreamformId((dateNow.getTime()) + "");
			if ("1".equals(formConfig.getIfHaveChild())) {
				formConfig.setChildEditCols(ArrayUtil.strArray2StrSplit(
						editSelectChild, ","));
			} else {
				formConfig.setChildTableId(null);
				formConfig.setChildEditCols(null);
				formConfig.setChildTableCol(null);
				formConfig.setParentTableCol(null);
			}
			if ("1".equals(formConfig.getIfHaveChild())) {
				generDetailHtml = generHtmlDate(formConfig,
						"GenerTableWithChildHtml", colList);
				generListHtml = generHtmlDate(formConfig,
						"GenerListWithChildHtml", colList);
			} else {
				generDetailHtml = generHtmlDate(formConfig, "GenerTableHtml",
						colList);
				generListHtml = generHtmlDate(formConfig, "GenerListHtml",
						colList);
			}
			formConfig.setDreamformContent(generDetailHtml);
			formConfig.setDreamformContentList(generListHtml);
			formConfig.setCreateTime(dateNow);
			HttpSession session = Mvcs.getHttpSession();
			formConfig.setCreateUser("admin");
			dao().insert(formConfig);
			for (int i = 0; i < colList.size(); i++) {
				ColRelationConfig colRelationConfig = colList.get(i);
				if (!Strings.isEmpty(colRelationConfig.getEditCol())) {
					colRelationConfig.setDreamformId(formConfig
							.getDreamformId());
					if (Strings.isEmpty(colRelationConfig.getRelCol())) {
						colRelationConfig.setTableId("");
					}
					dao().insert(colRelationConfig);
				}
			}
		} else {
			FormConfig formConfigOld = dao().fetch(FormConfig.class,
					formConfig.getDreamformId());
			String editCols = "";
			List<ColRelationConfig> relationConfigListOld = dao().query(
					ColRelationConfig.class,
					Cnd.where("dreamformId", "=", formConfig.getDreamformId()));
			for (ColRelationConfig colRelationConfig : relationConfigListOld) {
				dao().delete(colRelationConfig);
			}
			Collections.sort(colList, new ListNumComparator());
			for (int i = 0; i < colList.size(); i++) {
				ColRelationConfig colRelationConfig = colList.get(i);
				if (!Strings.isEmpty(colRelationConfig.getEditCol())) {
					editCols = editCols + colRelationConfig.getEditCol() + ",";
					if (Strings.isEmpty(colRelationConfig.getRelCol())) {
						colRelationConfig.setTableId("");
					}
					colRelationConfig.setDreamformId(formConfig
							.getDreamformId());
					dao().insert(colRelationConfig);
				}
			}
			if (!",".equals(editCols)) {
				editCols = editCols.substring(0, editCols.length() - 1);
			}
			formConfig.setDreamformEditCols(editCols);
			if ("1".equals(formConfig.getIfHaveChild())) {
				formConfig.setChildEditCols(ArrayUtil.strArray2StrSplit(
						editSelectChild, ","));
			} else {
				formConfig.setChildTableId(null);
				formConfig.setChildEditCols(null);
				formConfig.setChildTableCol(null);
				formConfig.setParentTableCol(null);
			}
			formConfig.setDreamformContent(formConfigOld.getDreamformContent());
			if ("1".equals(formConfig.getIfHaveChild())) {
				generListHtml = generHtmlDate(formConfig,
						"GenerListWithChildHtml", colList);
			} else {
				generListHtml = generHtmlDate(formConfig, "GenerListHtml",
						colList);
			}
			formConfig.setDreamformContentList(generListHtml);
			formConfig.setCreateTime(new Date());
			HttpSession session = Mvcs.getHttpSession();
			formConfig.setCreateUser("admin");
			dao().update(formConfig);
		}
		TableConfig queryTheme = dao().fetch(TableConfig.class,
				formConfig.getDreamformThemeId());
		String catalog = queryTheme.getCatalog();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("dreamformId", formConfig.getDreamformId());
		result.put("catalog", catalog);
		return result;
	}

	/**
	 * 生成html字符串.
	 * 
	 * @param saveId
	 * @param vmName
	 * @return
	 */
	private String generHtmlDate(FormConfig formConfig, String vmName,
			List<ColRelationConfig> colList) {
		TableConfig queryTheme = queryService.getQueryThemeById(formConfig
				.getDreamformThemeId());
		String resultCol = formConfig.getDreamformEditCols();
		String[] resultCols = resultCol.split(",");
		List<ColumnConfig> list = queryService
				.queryColumnListByColId(resultCols);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Dic> dicList = new ArrayList<Dic>();
		if ("1".equals(formConfig.getIfHaveChild())) {
			String[] childResultCols = formConfig.getChildEditCols().split(",");
			List<ColumnConfig> childList = queryService
					.queryColumnListByColId(childResultCols);
			// 遍历子表单所有字段，如果是字典且字典项多选，则取出所有字典项放入Map中
			for (ColumnConfig col : childList) {
				if ("1".equalsIgnoreCase(col.getIsMultiple())
						&& null != col.getDicId()) {
					dicList = getDicList(col.getDicId());
				}
			}
			TableConfig childTheme = queryService.getQueryThemeById(formConfig
					.getChildTableId());
			result.put("dicList", dicList);
			result.put("childTheme", childTheme);
			result.put("childList", childList);
		}
		// 遍历子表单所有字段，如果是字典且字典项多选，则取出所有字典项放入Map中
		for (ColumnConfig col : list) {
			if ("1".equalsIgnoreCase(col.getIsMultiple())
					&& null != col.getDicId()) {
				dicList = getDicList(col.getDicId());
			}
		}
		int colNum = Integer.parseInt(formConfig.getFormColNum());
		while (list.size() % (colNum / 2) != 0) {
			list.add(new ColumnConfig());
		}
		result.put("dicList", dicList);
		result.put("list", list);
		result.put("queryTheme", queryTheme);
		result.put("colNum", colNum);
		Map<String, String> colRelMap = new HashMap<String, String>();
		/****** 新增加关于读取基础库的功能 **/
		for (ColRelationConfig colRelationConfig : colList) {
			if (!Strings.isEmpty(colRelationConfig.getEditCol())) {
				if (!Strings.isEmpty(colRelationConfig.getRelCol())) {
					ColumnConfig colEdit = dao().fetch(ColumnConfig.class,
							Integer.parseInt(colRelationConfig.getEditCol()));
					ColumnConfig colRel = dao().fetch(ColumnConfig.class,
							Integer.parseInt(colRelationConfig.getRelCol()));
					colRelMap.put(colEdit.getNameLetter(),
							CamelCaseUtils.toCamelCase(colRel.getNameLetter()));
				} else {
					ColumnConfig colEdit = dao().fetch(ColumnConfig.class,
							Integer.parseInt(colRelationConfig.getEditCol()));
					colRelMap.put(colEdit.getNameLetter(), "");
				}
			}
		}
		result.put("colRelMap", colRelMap);
		try {
			String designDate = VelocityUtils.merge(result, vmName);
			return designDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据字典Id获取字典项
	 * 
	 * @param dicId
	 * @return
	 */
	public List<Dic> getDicList(Integer dicId) {
		Map<String, String> map = DicDataUtils.getInstance().getDic(dicId);
		List<Dic> dicList = new ArrayList<Dic>();
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String dicKey = it.next();
			Dic dic = new Dic();
			dic.setDicKey(dicKey);
			dic.setDicValue(map.get(dicKey));
			dicList.add(dic);
		}
		return dicList;
	}

	/**
	 * 选择台帐定义：电子表单预览页面.
	 * 
	 * @param accountBookId
	 *            |台帐主键
	 * @return
	 */
	@At
	public View toDesignEdit(String dreamformId, String view) {
		Map<String, Object> result = new HashMap<String, Object>();
		String content = getContent(dreamformId);
		result.put("content", content);
		result.put("dreamformId", dreamformId);
		result.put("view", view);
		return new ViewWrapper(new JspView("jsp.pzgl.form.edit_design"), result);
	}

	/**
	 * 根据台帐Id获取台帐预览界面信息.
	 * 
	 * @param accountBookId
	 * @return
	 */
	private String getContent(String dreamformId) {
		String content = null;
		FormConfig formConfig = dao().fetch(FormConfig.class, dreamformId);
		if (formConfig != null) {
			content = formConfig.getDreamformContent();
		}
		return content;
	}

	/**
	 * 完成定义
	 * 
	 * @param accountBookId
	 * @param dreamFormId
	 * @param saveId
	 * @return
	 */
	@At
	@Ok("redirect:/config/form/toFormList")
	public Map<String, Object> finishDesign() {
		Map<String, Object> result = new HashMap<String, Object>();
		return UI.closeAjaxDone(StatusCode.OK, "保存成功！");
	}

	/**
	 * 刷新
	 * 
	 * @param saveId
	 * @return
	 */
	@At
	@Ok("redirect:/config/form/toDesignEdit?dreamformId=${obj.dreamformId}")
	public Map<String, Object> refreshDesign(String dreamformId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("dreamformId", dreamformId);
		return result;
	}

	/**
	 * 重置
	 * 
	 * @param saveId
	 * @return
	 */
	@At
	@Ok("redirect:/config/form/toDesignEdit?dreamformId=${obj.dreamformId}")
	public Map<String, Object> resetDesign(String dreamformId) {
		FormConfig formConfig = dao().fetch(FormConfig.class, dreamformId);
		List<ColRelationConfig> colList = dao().query(ColRelationConfig.class,
				Cnd.where("dreamformId", "=", formConfig.getDreamformId()));
		if ("1".equals(formConfig.getIfHaveChild())) {
			String formContent = generHtmlDate(formConfig,
					"GenerTableWithChildHtml", colList);
			String dreamformContentList = generHtmlDate(formConfig,
					"GenerListWithChildHtml", colList);
			formConfig.setDreamformContent(formContent);
			formConfig.setDreamformContentList(dreamformContentList);
		} else {
			String formContent = generHtmlDate(formConfig, "GenerTableHtml",
					colList);
			String dreamformContentList = generHtmlDate(formConfig,
					"GenerListHtml", colList);
			formConfig.setDreamformContent(formContent);
			formConfig.setDreamformContentList(dreamformContentList);
		}
		dao().update(formConfig);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("dreamformId", dreamformId);
		return result;
	}

	@At("/toDelForm")
	@Ok("json")
	public Map<String, Object> toDelForm(@Param("id") String dreamformId) {
		if (StringUtils.isNotEmpty(dreamformId)) {
			FormConfig formConfig = dao().fetch(FormConfig.class, dreamformId);
			if (formConfig != null) {
				dao().delete(formConfig);
			}
		}
		return UI.ajaxDone(StatusCode.OK);
	}

	/**
	 * 打开编辑页面
	 * 
	 * @param dreamformId
	 * @return
	 */
	@At
	@Ok("jsp:jsp.pzgl.form.design")
	public Map<String, Object> toDesign(@Param("dreamformId") String dreamformId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!Strings.isBlank(dreamformId)) {
			FormConfig formConfig = dao().fetch(FormConfig.class, dreamformId);
			map.put("formConfig", formConfig);
		}
		return map;
	}

	/**
	 * 保存设计器的设计的内容（HTML），调用ajax方式,如果是新增，务必将返回对象处理，避免重复新增.
	 * 
	 * @param dreamformId
	 * @param content
	 * @return
	 */
	@At
	@Ok("json")
	public Map<String, Object> saveDesign(
			@Param("dreamformId") String dreamformId,
			@Param("content") String content) {
		FormConfig formConfig = null;
		// 修改
		if (!Strings.isBlank(dreamformId)) {
			formConfig = dao().fetch(FormConfig.class, dreamformId);
			formConfig.setDreamformContent(content);
			dao().update(formConfig);
		} else {// 新增
			formConfig = new FormConfig();
			formConfig.setDreamformContent(content);
			dao().update(formConfig);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("formConfig", formConfig);
		return UI.ajaxDone(StatusCode.OK, "保存成功！");
	}

	/**
	 * 进入报表结果页面
	 * 
	 * @param dreamformId
	 *            报表主键
	 * @param busId
	 *            业务主键 新增为空 ，修改必须设置值
	 * @param op
	 *            操作类型 目前只定义了 view：查看
	 * @param type
	 *            有关联字段时,新增人员操作标识 0-有关联字段,但基础库没有人员信息;1-跳转到select_person页面
	 * @return
	 */
	@At
	public View result(String dreamformId, String busId, String op,
			String taskNo, String type, String name, String idCard,
			HttpServletRequest request) {
		FormConfig formConfig = dao().fetch(FormConfig.class, dreamformId);
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		List<ColRelationConfig> colList = dao().query(ColRelationConfig.class,
				Cnd.where("dreamformId", "=", dreamformId));
		int flag = 0;
		for (ColRelationConfig colRelationConfig : colList) {
			if (!Strings.isEmpty(colRelationConfig.getRelCol())) {
				flag = 1;
				break;
			}
		}

		if ("0".equals(type)) {
			flag = 0;
		}
		// 非新增情况
		if (!Strings.isEmpty(busId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dreamForm", formConfig);
			map.put("busId", busId);
			map.put("dreamformId", dreamformId);
			map.put("op", op);
			map.put("taskNo", taskNo);
//			String n = CookieUtils.getCookie(request, SessionFilter.LOGON_NAME);
//			map.put("userName", n);
//			User user = dao()
//					.fetch(User.class, Cnd.where("logon_name", "=", n));
//			DicDept dept = dao().fetch(DicDept.class, user.getDept());
//			map.put("department", dept.getDeptName());
			return new ViewWrapper(new JspView("jsp.pzgl.form.result"), map);
			// 不包含关联字段
		} else if (flag == 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dreamForm", formConfig);
			map.put("busId", busId);
			map.put("dreamformId", dreamformId);
			map.put("op", op);
			map.put("taskNo", taskNo);
			map.put("uuid", uuid);
			String n = CookieUtils.getCookie(request, SystemConstants.LOGON_NAME);
			map.put("userName", n);
			User user = dao()
					.fetch(User.class, Cnd.where("logon_name", "=", n));
			DicDept dept = dao().fetch(DicDept.class, user.getDept());
			map.put("department", dept.getDeptName());
			return new ViewWrapper(new JspView("jsp.pzgl.form.result"), map);
			// 包含关联字段
		} else {
			if ("1".equals(type)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dreamForm", formConfig);
				map.put("busId", busId);
				map.put("dreamformId", dreamformId);
				map.put("op", op);
				map.put("taskNo", taskNo);
				String n = CookieUtils.getCookie(request,
						SystemConstants.LOGON_NAME);
				map.put("userName", n);
				User user = dao().fetch(User.class,
						Cnd.where("logon_name", "=", n));
				DicDept dept = dao().fetch(DicDept.class, user.getDept());
				map.put("department", dept.getDeptName());
				return new ViewWrapper(new JspView(
						"jsp.dreamconfig.form.select_person"), map);
			}
		}
		return new ServerRedirectView("/config/form/personSelect?dreamformId="
				+ dreamformId + "&taskNo=" + taskNo);
	}

	/**
	 * 保单页面初始化时,加载业务数据信息（修改功能），ajax调用方式.
	 * 
	 * @param dicId
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@At
	@Ok("json")
	public String getInitData(String busId, String dreamformId,
			HttpSession session) throws SQLException, ClassNotFoundException {
		DwLog log=new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateUser(((User)session.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User)session.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		FormConfig formConfig = dao().fetch(FormConfig.class, dreamformId);
		log.setOperateType("查询"+formConfig.getDreamformName()+"详细信息");
		
		TableConfig queryTheme = queryService.getQueryThemeById(formConfig
				.getDreamformThemeId());
		List<ColumnConfig> columnList = queryService.queryColumnList(formConfig
				.getDreamformThemeId());
		// 获取主键信息
		ColumnConfig idcolumn = this.getTablePkInfo(columnList);
		// 拼查询语句
		String querysql = "select * from " + queryTheme.getViewName()
				+ " where " + idcolumn.getNameLetter() + "='" + busId + "'";
		log.setQueryTable(queryTheme.getViewName());
		log.setQueryCondition( idcolumn.getNameLetter() + "='" + busId + "'");
		Properties info = new java.util.Properties();

		info.put("user", PropertyUtils.getProperty("jdbc.user"));
		info.put("password", PropertyUtils.getProperty("jdbc.password"));
		info.put("ResultSetMetaDataOptions", "1");
		Connection conn = DriverManager.getConnection(
				PropertyUtils.getProperty("jdbc.url"), info);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(querysql);
		List<HashMap<String, Object>> result = rs2MapList(rs,
				queryTheme.getViewName());
		//log.setOperateResult(result.toString());
		log.setQueryCount(""+1);
		rs.close();
		stmt.close();
		conn.close();
		dao().insert(log);
		// mySql下可以使用下面注释掉的代码
		// Sql sql = Sqls.create(querysql);
		// //设置返回值
		// sql.setCallback(new SqlCallback() {
		// public Object invoke(Connection conn, ResultSet rs, Sql sql)throws
		// SQLException {
		// return rs2MapList(rs);
		// }
		// });
		// this.dao().execute(sql);
		if ("1".equals(formConfig.getIfHaveChild())) {
			// 取出RESULT，开始增加子表信息
			// List<Map<String,Object>> result = (List<Map<String, Object>>)
			// sql.getResult();
			// 取出子表的信息
			TableConfig childTable = queryService.getQueryThemeById(formConfig
					.getChildTableId());
			// 取出主表的关联列
			ColumnConfig columnLinkParent = queryService
					.queryColumnByColId(Integer.parseInt(formConfig
							.getParentTableCol()));
			// 取出子表的关联列
			ColumnConfig columnLinkChild = queryService
					.queryColumnByColId(Integer.parseInt(formConfig
							.getChildTableCol()));
			for (Map<String, Object> mapEach : result) {
				String sqlChild = "";
				sqlChild = "select * from "
						+ childTable.getViewName()
						+ " where "
						+ columnLinkChild.getNameLetter()
						+ "="
						+ mapEach.get(queryTheme.getViewName().toUpperCase()
								+ "."
								+ columnLinkParent.getNameLetter()
										.toUpperCase());
				info.put("user", PropertyUtils.getProperty("jdbc.user"));
				info.put("password", PropertyUtils.getProperty("jdbc.password"));
				info.put("ResultSetMetaDataOptions", "1");
				Connection conn2 = DriverManager.getConnection(
						PropertyUtils.getProperty("jdbc.url"), info);
				Statement stmt2 = conn2.createStatement();
				ResultSet rs2 = stmt2.executeQuery(sqlChild);
				List<HashMap<String, Object>> result2 = rs2MapList(rs2,
						childTable.getViewName());
				rs2.close();
				stmt2.close();
				conn2.close();
				// Sql exeSqlChild = Sqls.create(sqlChild);
				// exeSqlChild.setCallback(new SqlCallback(){
				// public Object invoke(Connection conn, ResultSet rs, Sql
				// sql)throws SQLException {
				// return rs2MapList(rs);
				// }
				// });
				// this.dao().execute(exeSqlChild);
				// mapEach.put("childList", exeSqlChild.getResult());
				mapEach.put("childList", result2);
			}
			return Json.toJson(result);
		} else {
			return Json.toJson(result);
		}
	}

	/**
	 * 获取表主键的配置信息.
	 * 
	 * @param columnList
	 * @return
	 */
	private ColumnConfig getTablePkInfo(List<ColumnConfig> columnList) {
		for (ColumnConfig column : columnList) {
			if ("1".equals(column.getIsPk())) {
				return column;
			}
		}
		return null;
	}

	/**
	 * 处理sql查询语句的返回值
	 * 
	 * @param rs
	 * @return
	 */
	private List<HashMap<String, Object>> rs2MapList(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		List<HashMap<String, Object>> rsMapList = new ArrayList<HashMap<String, Object>>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();

			while (rs.next()) {
				String tableName = rsmd.getTableName(1);
				HashMap<String, Object> rsMap = new HashMap<String, Object>();
				for (int i = 1; i <= count; ++i) {
					String columnName = rsmd.getColumnName(i);
					// rsMap.put(tableName.toUpperCase()+"."+columnName.toUpperCase(),
					// rs.getObject(columnName));
					rsMap.put(columnName.toUpperCase(),
							rs.getObject(columnName));
				}

				rsMapList.add(rsMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rsMapList;
	}

	/**
	 * 处理sql查询语句的返回值
	 * 
	 * @param rs
	 * @return
	 */
	private List<HashMap<String, Object>> rs2MapList(ResultSet rs,
			String tableName) {
		if (rs == null) {
			return null;
		}
		List<HashMap<String, Object>> rsMapList = new ArrayList<HashMap<String, Object>>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();

			while (rs.next()) {
				HashMap<String, Object> rsMap = new HashMap<String, Object>();
				for (int i = 1; i <= count; ++i) {
					String columnName = rsmd.getColumnName(i);
					if (tableName.toUpperCase().equals("CORP_LICENSE")
							&& (columnName.equals("PERMISSION_CONTEXT") || columnName
									.equals("PERMISSION_DECISION_CONTEXT")))
						continue;
					if(tableName.toUpperCase().equals("CORP_INFO")&&columnName
							.equals("AREA_CODE")){
						rsMap.put(tableName.toUpperCase() + "."
								+ columnName.toUpperCase(),
								"上海市普陀区");
						continue;
					}
					if (tableName.toUpperCase().equals("T_GA_RJBXX")
							&& (columnName.equals("XM") || columnName
									.equals("XMQP"))) {
						String xm = (String) rs.getObject(columnName);
						if (xm != null && !xm.equals("")) {
							if (xm.length() < 3) {
								rsMap.put(tableName.toUpperCase() + "."
										+ columnName.toUpperCase(),
										xm.charAt(0) + "*");
							} else {
								rsMap.put(
										tableName.toUpperCase() + "."
												+ columnName.toUpperCase(),
										xm.charAt(0)
												+ Strings.dup("*",
														xm.length() - 1)
												+ xm.charAt(xm.length() - 1));
							}
							continue;
						}
					}
					if (tableName.toUpperCase().equals("T_GA_RJBXX")
							&& columnName.equals("ZJHM")) {
						String zjhm = (String) rs.getObject(columnName);
						if (zjhm != null) {
							if (zjhm.length() == 18) {
								rsMap.put(tableName.toUpperCase() + "."
										+ columnName.toUpperCase(),
										zjhm.substring(0, 6) + "********"
												+ zjhm.substring(14, 18));
							} else {
								rsMap.put(
										tableName.toUpperCase() + "."
												+ columnName.toUpperCase(),
										Strings.dup('*', zjhm.length() / 2)
												+ zjhm.substring(
														zjhm.length() / 2,
														zjhm.length()));
							}
						}
						continue;
					}
					rsMap.put(
							tableName.toUpperCase() + "."
									+ columnName.toUpperCase(),
							rs.getObject(columnName));
				}
				rsMapList.add(rsMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rsMapList;
	}

	/**
	 * 保单页面初始化时初始化字典信息，ajax调用方式.
	 * 
	 * @param dicId
	 * @return
	 */
	@At
	@Ok("json")
	public String getDic(@Param("dicId") Integer dicId) {
		Map<String, String> map = DicDataUtils.getInstance().getDic(dicId);
		List<Dic> dicList = new ArrayList<Dic>();
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String dicKey = it.next();
			Dic dic = new Dic();
			dic.setDicKey(dicKey);
			dic.setDicValue(map.get(dicKey));
			dicList.add(dic);
		}
		return Json.toJson(dicList);
	}
}
