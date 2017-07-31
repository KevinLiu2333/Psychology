package com.wonders.wddc.suite.csrq.at;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;
import org.nutz.service.Service;

import com.wonders.wddc.suite.csrq.comparator.ListNumComparator;
import com.wonders.wddc.suite.csrq.entity.ColRelationConfigBo;
import com.wonders.wddc.suite.csrq.entity.ColumnConfigBo;
import com.wonders.wddc.suite.csrq.entity.FormConfigBo;
import com.wonders.wddc.suite.csrq.service.QueryService;
import com.wonders.wddc.suite.data.entity.TableConfigBo;
import com.wonders.wddc.tiles.dic.DicDataUtils;
import com.wonders.wddc.tiles.dic.entity.Dic;
import com.wonders.wddc.tiles.tools.CamelCaseUtils;
import com.wonders.wddc.tiles.tools.UI;
import com.wonders.wddc.tiles.tools.VelocityUtils;
import com.wonders.wddc.tiles.tools.UI.StatusCode;

@At("/suite/config/form")
@IocBean(fields = "dao")
public class FormAct extends Service {

	@Inject
    private QueryService queryService;
	@At
	@Ok("jsp:wddc.suite.csrq.form.list")
	public Map<String,Object> toFormList(Criteria cri,String formName){
		Map<String,Object> result = new HashMap<String,Object>();
		if (!Strings.isEmpty(formName)) {
			SqlExpressionGroup exp1 = Cnd.exps("DREAMFORM_NAME", "like", "%"	+ formName + "%");
			cri.where().and(exp1);
		}
		cri.getOrderBy().desc("createTime");
		List<FormConfigBo> list = dao().query(FormConfigBo.class, cri);
		result.put("list", list);
		result.put("formName", formName);
		return result;
	}
	

	/**
    * 进入查询定义界面，包含查询新增定义及查询定义修改
    */
	@At
	@Ok("jsp:wddc.suite.csrq.form.form_edit")
	public Map<String,Object> toEditForm(@Param("dreamformId") String dreamformId,String ifEdit, String editSelectChild,@Param("::formConfig.")FormConfigBo formConfig) throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		//获取当前所有库表列表
		List<TableConfigBo> themeList = new ArrayList<TableConfigBo>();
		//子表可选条件
		List<ColumnConfigBo> unselectedEditsChild = new ArrayList<ColumnConfigBo>();
		//子表已选条件
		List<ColumnConfigBo> selectedEditsChild = new ArrayList<ColumnConfigBo>();
		//父表所有列
		List<ColumnConfigBo> columnListParent = new ArrayList<ColumnConfigBo>();
		//子表所有列
		List<ColumnConfigBo> columnListChild = new ArrayList<ColumnConfigBo>();
		//基础表所有列
		List<ColumnConfigBo> columnListBase = new ArrayList<ColumnConfigBo>();
		columnListBase = queryService.queryColumnList(new Integer(1));
		result.put("columnListBase", columnListBase);
		//关联关系
		List<ColRelationConfigBo> relationList = new ArrayList<ColRelationConfigBo>();
		//查询出现有所有表
		themeList = queryService.queryThemeListByCatalog(null);
		result.put("themeList", themeList);
	    //ifEdit作用，判断是否为编辑页面自身提交刷新 ifEdit=1为编辑页面自身提交
	    if (StringUtils.isEmpty(ifEdit)){
	    	//为空，表示不为自身提交，需要读取字段数据
	    	if (StringUtils.isNotEmpty(dreamformId)){
	    		//为修改的情况
	    		formConfig = queryService.queryFormConfig(dreamformId);
	    		columnListParent = queryService.queryColumnList(formConfig.getDreamformThemeId());
	    		//将已经定义好的查询条件放入Map（配置项主键，配置项主键）
	            Map<String,String> editMap = new HashMap<String,String>();
	    		if(StringUtils.isNotBlank(formConfig.getDreamformEditCols())){
		    		String[] refStrings = formConfig.getDreamformEditCols().split(",");
		    		for(String refString : refStrings) {
		    			editMap.put(refString, refString);
		    		}
		    	}
		    	if ("1".equals(formConfig.getIfHaveChild())){
		    		columnListChild = queryService.queryColumnList(formConfig.getChildTableId());
		    		editMap = new HashMap<String,String>();
		    		if(StringUtils.isNotBlank(formConfig.getChildEditCols())){
	    	    		String[] refStrings = formConfig.getChildEditCols().split(",");
	    	    		for(String refString : refStrings) {
	    	    			editMap.put(refString, refString);
	    	    		}
	    	    	}
	    	    	for (ColumnConfigBo column : columnListChild) {
	    	    		if ((column.getColumnType().equals("1") || column.getColumnType().equals("2"))&& !editMap.containsKey(""+column.getColId()))  {
	    	    			unselectedEditsChild.add(column);
	    	    		} else if (editMap.containsKey(""+column.getColId())) {
	    	    			selectedEditsChild.add(column);
	    	    			
	    	    		}
	    	    	}
		    	}
		    	Criteria cri = Cnd.cri();
		    	cri.where().and("dreamformId", "=", dreamformId);
		    	cri.getOrderBy().asc("listNum");
		    	relationList = dao().query(ColRelationConfigBo.class, cri);
		    	result.put("relationList", relationList);
		    	//查询主题相关
		    	result.put("formConfig", formConfig);
		        result.put("columnListParent", columnListParent);
		        result.put("columnListChild",columnListChild);
		        //查询条件和查询结果
		        result.put("unselectedEditsChild", unselectedEditsChild);
		        result.put("selectedEditsChild", selectedEditsChild);
	    	}
	    } else if ("1".equals(ifEdit)){
	    	columnListParent = queryService.queryColumnList(formConfig.getDreamformThemeId());
	    	//将已经定义好的查询条件放入Map（配置项主键，配置项主键）
	        Map<String,String> editMap = new HashMap<String,String>();
	        formConfig.setChildEditCols(editSelectChild);
	        if(StringUtils.isNotBlank(formConfig.getDreamformEditCols())){
	    		String[] refStrings = formConfig.getDreamformEditCols().split(",");
	    		for(String refString : refStrings) {
	    			editMap.put(refString, refString);
	    		}
	    	}
	    	if ("1".equals(formConfig.getIfHaveChild())){
	    		columnListChild = queryService.queryColumnList(formConfig.getChildTableId());
	    		editMap = new HashMap<String,String>();
	    		if(StringUtils.isNotBlank(formConfig.getChildEditCols())){
		    		String[] refStrings = formConfig.getChildEditCols().split(",");
		    		for(String refString : refStrings) {
		    			editMap.put(refString, refString);
		    		}
		    	}
		    	for (ColumnConfigBo column : columnListChild) {
		    		if ((column.getColumnType().equals("1") || column.getColumnType().equals("2"))&& !editMap.containsKey(""+column.getColId()))  {
		    			unselectedEditsChild.add(column);
		    		} else if (editMap.containsKey(""+column.getColId())) {
		    			selectedEditsChild.add(column);
		    			
		    		}
		    	}
	    	}
	    	//查询主题相关
	    	result.put("formConfig", formConfig);
	        result.put("columnListParent", columnListParent);
	        result.put("columnListChild",columnListChild);
	        //查询条件和查询结果
	        result.put("unselectedEditsChild", unselectedEditsChild);
	        result.put("selectedEditsChild", selectedEditsChild);
	    }
	    return result;
	}
	/**
	 * 保存表单
	 * @param colList
	 * @param editSelectChild
	 * @param formConfig
	 * @return
	 * @throws Exception
	 */
	@At
    @Ok("redirect:/suite/config/form/toDesignEdit?dreamformId=${obj.dreamformId}")
    public Map<String,Object> toSaveForm(@Param("::colList") List<ColRelationConfigBo> colList,String editSelectChild,@Param("::formConfig.")FormConfigBo formConfig) throws Exception {
        String generDetailHtml = "";
        String generListHtml = "";
        // 判断是新增还是更新
        if (StringUtils.isBlank(formConfig.getDreamformId())) {  
        	String editCols = "";
        	for (ColRelationConfigBo colRelationConfig : colList){
        		if (!Strings.isEmpty(colRelationConfig.getEditCol())){
	        		editCols = editCols + colRelationConfig.getEditCol() + ",";
        		}
        	}
        	if (!",".equals(editCols)){
        		editCols = editCols.substring(0, editCols.length()-1);
        	}
        	formConfig.setDreamformEditCols(editCols);
        	Date dateNow = new Date();
            formConfig.setDreamformId((dateNow.getTime()) + "");
            if ("1".equals(formConfig.getIfHaveChild())){
            	formConfig.setChildEditCols(editSelectChild);
            } else {
            	formConfig.setChildTableId(null);
            	formConfig.setChildEditCols(null);
            	formConfig.setChildTableCol(null);
            	formConfig.setParentTableCol(null);
            }
            if ("1".equals(formConfig.getIfHaveChild())){
            	generDetailHtml = generHtmlDate(formConfig,"GenerTableWithChildHtml",colList);
            	generListHtml = generHtmlDate(formConfig,"GenerListWithChildHtml",colList);
            } else {
            	generDetailHtml = generHtmlDate(formConfig,"GenerTableHtml",colList);
            	generListHtml = generHtmlDate(formConfig,"GenerListHtml",colList);
            }
            formConfig.setDreamformContent(generDetailHtml);
            formConfig.setDreamformContentList(generListHtml);
            formConfig.setCreateTime(dateNow);
            //此处去掉了use 2016-10-24
            /*User user = (User)session.getAttribute("logonName");
            formConfig.setCreateUser(user.getDisplayName());*/
            dao().insert(formConfig);
            for (int i=0;i<colList.size();i++){
            	ColRelationConfigBo colRelationConfig = colList.get(i);
        		if (!Strings.isEmpty(colRelationConfig.getEditCol())){
	        		colRelationConfig.setDreamformId(formConfig.getDreamformId());
	        		if (Strings.isEmpty(colRelationConfig.getRelCol())){
	        			colRelationConfig.setTableId("");
	        		}
	        		dao().insert(colRelationConfig);
        		}
        	}
        } else {
        	FormConfigBo formConfigOld = dao().fetch(FormConfigBo.class, formConfig.getDreamformId());
        	String editCols = "";
        	List<ColRelationConfigBo> relationConfigListOld = dao().query(ColRelationConfigBo.class, Cnd.where("dreamformId","=",formConfig.getDreamformId()));
        	for (ColRelationConfigBo colRelationConfig : relationConfigListOld){
        		dao().delete(colRelationConfig);
        	}
        	Collections.sort(colList, new ListNumComparator());
        	for (int i=0;i<colList.size();i++){
        		ColRelationConfigBo colRelationConfig = colList.get(i);
        		if (!Strings.isEmpty(colRelationConfig.getEditCol())){
	        		editCols = editCols + colRelationConfig.getEditCol() + ",";
	        		if (Strings.isEmpty(colRelationConfig.getRelCol())){
	        			colRelationConfig.setTableId("");
	        		}
	        		colRelationConfig.setDreamformId(formConfig.getDreamformId());
	        		dao().insert(colRelationConfig);
        		}
        	}
        	if (!",".equals(editCols)){
        		editCols = editCols.substring(0, editCols.length()-1);
        	}
        	formConfig.setDreamformEditCols(editCols);
        	if ("1".equals(formConfig.getIfHaveChild())){
            	formConfig.setChildEditCols(editSelectChild);
            } else {
            	formConfig.setChildTableId(null);
            	formConfig.setChildEditCols(null);
            	formConfig.setChildTableCol(null);
            	formConfig.setParentTableCol(null);
            }
        	formConfig.setDreamformContent(formConfigOld.getDreamformContent());
        	if ("1".equals(formConfig.getIfHaveChild())){
        		generListHtml = generHtmlDate(formConfig,"GenerListWithChildHtml",colList);
        	} else {
        		generListHtml = generHtmlDate(formConfig,"GenerListHtml",colList);
        	}
            formConfig.setDreamformContentList(generListHtml);
            formConfig.setCreateTime(new Date());
            /*HttpSession session = Mvcs.getHttpSession();
            User user = (User)session.getAttribute("logonName");
            formConfig.setCreateUser(user.getDisplayName());*/
            dao().update(formConfig);
        }
        TableConfigBo queryTheme = dao().fetch(TableConfigBo.class,formConfig.getDreamformThemeId());
        String catalog = queryTheme.getCatalog();
        Map<String, Object> result = new HashMap<String, Object>();
		result.put("dreamformId", formConfig.getDreamformId());
		result.put("catalog", catalog);
    	return result;
    }
	
	/**
     * 生成html字符串.
     * @param saveId
     * @param vmName
     * @return
     */
	private String generHtmlDate(FormConfigBo formConfig,String vmName,List<ColRelationConfigBo> colList){
		TableConfigBo queryTheme = queryService.getQueryThemeById(formConfig.getDreamformThemeId());
		String resultCol = formConfig.getDreamformEditCols();
		String[] resultCols = resultCol.split(",");
		List<ColumnConfigBo> list = queryService.queryColumnListByColId(resultCols);
		Map<String,Object> result = new HashMap<String,Object>();
		List<Dic> dicList = new ArrayList<Dic>();
		if ("1".equals(formConfig.getIfHaveChild())) {
			String[] childResultCols = formConfig.getChildEditCols().split(",");
			List<ColumnConfigBo> childList = queryService.queryColumnListByColId(childResultCols);
			// 遍历子表单所有字段，如果是字典且字典项多选，则取出所有字典项放入Map中
			for (ColumnConfigBo col : childList) {
				if ("1".equalsIgnoreCase(col.getIsMultiple()) && null != col.getDicId()) {
					dicList = getDicList(col.getDicId());
				}
			}
			TableConfigBo childTheme = queryService.getQueryThemeById(formConfig.getChildTableId());
			result.put("dicList", dicList);
			result.put("childTheme", childTheme);
			result.put("childList", childList);
		}
		//遍历子表单所有字段，如果是字典且字典项多选，则取出所有字典项放入Map中
		for(ColumnConfigBo col : list){
			if("1".equalsIgnoreCase(col.getIsMultiple()) && null != col.getDicId()){
				dicList = getDicList(col.getDicId());
			}
		}
		int colNum = Integer.parseInt(formConfig.getFormColNum());
		while (list.size() % (colNum / 2) != 0) {
			list.add(new ColumnConfigBo());
		}
		result.put("dicList", dicList);
		result.put("list", list);
		result.put("queryTheme", queryTheme);
		result.put("colNum", colNum);
		Map<String,String> colRelMap = new HashMap<String,String>();
		/******新增加关于读取基础库的功能 **/
		for (ColRelationConfigBo colRelationConfig : colList){
			if (!Strings.isEmpty(colRelationConfig.getEditCol())){
				if (!Strings.isEmpty(colRelationConfig.getRelCol())){
					ColumnConfigBo colEdit = dao().fetch(ColumnConfigBo.class,Integer.parseInt(colRelationConfig.getEditCol()));
					ColumnConfigBo colRel = dao().fetch(ColumnConfigBo.class,Integer.parseInt(colRelationConfig.getRelCol()));
					colRelMap.put(colEdit.getNameLetter(), CamelCaseUtils.toCamelCase(colRel.getNameLetter()));
				} else {
					ColumnConfigBo colEdit = dao().fetch(ColumnConfigBo.class,Integer.parseInt(colRelationConfig.getEditCol()));
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
	 * @param dicId
	 * @return
	 */
	public List<Dic> getDicList(Integer dicId){
		Map<String, String> map = DicDataUtils.getInstance().getDic(dicId);
		List<Dic> dicList = new ArrayList<Dic>();
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String dicKey = it.next();
			Dic dic = new Dic();
			dic.setKey(dicKey);
			dic.setValue(map.get(dicKey));
			dicList.add(dic);
		}
		return dicList;
	}
	/**
	 * 删除表单
	 * @param dreamformId
	 * @return
	 */
	@At("/toDelForm")
	@Ok("redirect:/suite/config/form/toFormList")
	public void /*Map<String,Object> */ toDelForm(@Param("dreamformId") String dreamformId){
		if (StringUtils.isNotEmpty(dreamformId)){
			FormConfigBo formConfig = dao().fetch(FormConfigBo.class, dreamformId);
			if (formConfig != null){
				dao().delete(formConfig);
			}
		}
		//return UI.ajaxDone(StatusCode.OK);
	}
	
	/**
	 * 选择台帐定义：电子表单预览页面.
	 * @param accountBookId|台帐主键
	 * @return
	 */
	@At
	public View toDesignEdit(String dreamformId,String view) {
		Map<String, Object> result = new HashMap<String, Object>();
		String content = getContent(dreamformId);
		result.put("content", content);
		result.put("dreamformId", dreamformId);
		result.put("view", view);
		return new ViewWrapper(new JspView("wddc.suite.csrq.form.edit_design"),result);
	}
	
	/**
	 * 根据台帐Id获取台帐预览界面信息.
	 * @param accountBookId
	 * @return
	 */
	private String getContent(String dreamformId){
		String content = null;
		FormConfigBo formConfig = dao().fetch(FormConfigBo.class,dreamformId);
		if(formConfig!=null){
			content = formConfig.getDreamformContent();
		}
		return content;
	}
	
	/**
	 * 打开编辑页面
	 * @param dreamformId
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.form.design")
	public Map<String, Object> toDesign(@Param("dreamformId") String dreamformId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!Strings.isBlank(dreamformId)){
			FormConfigBo formConfig = dao().fetch(FormConfigBo.class,dreamformId);
			map.put("formConfig", formConfig);
		}
		return map;
	}
	
	/**
     * 刷新
     * @param saveId
     * @return
     */
	@At
	@Ok("redirect:/suite/config/form/toDesignEdit?dreamformId=${obj.dreamformId}")
    public Map<String,Object> refreshDesign(String dreamformId){
        Map<String, Object> result = new HashMap<String, Object>();
		result.put("dreamformId", dreamformId);
        return result;
	}
	
	/**
     * 重置
     * @param saveId
     * @return
     */
	@At
	@Ok("redirect:/suite/config/form/toDesignEdit?dreamformId=${obj.dreamformId}")
    public Map<String,Object> resetDesign(String dreamformId){
		FormConfigBo formConfig = dao().fetch(FormConfigBo.class,dreamformId);
		List<ColRelationConfigBo> colList = dao().query(ColRelationConfigBo.class, Cnd.where("dreamformId","=",formConfig.getDreamformId()));
		if ("1".equals(formConfig.getIfHaveChild())){
			String formContent = generHtmlDate(formConfig,"GenerTableWithChildHtml",colList);
			String dreamformContentList = generHtmlDate(formConfig,"GenerListWithChildHtml",colList);
			formConfig.setDreamformContent(formContent);
			formConfig.setDreamformContentList(dreamformContentList);
		} else {
			String formContent = generHtmlDate(formConfig,"GenerTableHtml",colList);
			String dreamformContentList = generHtmlDate(formConfig,"GenerListHtml",colList);
			formConfig.setDreamformContent(formContent);
			formConfig.setDreamformContentList(dreamformContentList);
		}
        dao().update(formConfig);
        Map<String, Object> result = new HashMap<String, Object>();
		result.put("dreamformId", dreamformId);
        return result;
	}
	
	/**
	 * 完成定义
	 * @param dreamFormId
	 * @param saveId
	 * @return
	 */
	@At
	@Ok("redirect:/suite/config/form/toFormList")
	public Map<String, Object> finishDesign() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	/**
	 * 保存设计器的设计的内容（HTML），调用ajax方式,如果是新增，务必将返回对象处理，避免重复新增.
	 * @param dreamformId
	 * @param content
	 * @return
	 */
	@At
	@Ok("json")
	public Map<String,Object> saveDesign(@Param("dreamformId") String dreamformId,@Param("content") String content) {
		FormConfigBo formConfig = null;
		//修改
		if(!Strings.isBlank(dreamformId)){
			formConfig = dao().fetch(FormConfigBo.class, dreamformId);
			formConfig.setDreamformContent(content);
			dao().update(formConfig);
		}else{//新增
			formConfig = new FormConfigBo();
			formConfig.setDreamformContent(content);
			dao().update(formConfig);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dreamformId", dreamformId);
		map.put("formConfig", formConfig);
		return map;
	}
}
