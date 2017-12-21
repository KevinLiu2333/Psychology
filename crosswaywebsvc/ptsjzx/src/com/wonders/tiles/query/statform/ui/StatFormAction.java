package com.wonders.tiles.query.statform.ui;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.query.report.Report;
import com.wonders.tiles.query.report.ReportConstants;
import com.wonders.tiles.query.report.ReportManager;
import com.wonders.tiles.query.statform.StatFormConstans;
import com.wonders.tiles.query.statform.entity.FormSave;
import com.wonders.tiles.query.statform.entity.QueryColumn;
import com.wonders.tiles.query.statform.entity.QueryTheme;
import com.wonders.tiles.query.statform.service.StatFormService;
import com.wonders.tiles.query.statrenderer.BaseRenderer;
import com.wonders.util.ArrayUtil;
import com.wonders.util.DateUtils;
import com.wonders.util.VelocityUtils;

@At("/query/statForm")
@IocBean
public class StatFormAction  {

	@Inject
    private StatFormService statFormService;
	/**
	 * 进入主题页面
	 * @param catalog 主题分类
	 * @param filterType 选择类型
	 * @param saveName 选择过滤名称
	 * @param themeId 主题id
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.query.statform.form_list")
	public Map<String,Object> toShowList(String type) throws Exception {
		//用于保存返回结果
		Map<String,Object> result = new HashMap<String, Object>();
		List<FormSave> querySaveList = statFormService.querySaveList(type);
		result.put("querySaveList", querySaveList);
		return result;
	}
    /**
     * 进入主题页面
     */
	@At
	@Ok("jsp:jsp.query.statform.form_theme")
    public Map<String,Object> toTheme(String catalog,String saveName,String themeId) throws Exception {
		//用于保存返回结果
		Map<String,Object> result = new HashMap<String, Object>();
		//初始化分类信息
		if(StringUtils.isNotBlank(themeId) && StringUtils.isBlank(catalog)){
			QueryTheme queryTheme = statFormService.getQueryThemeById(themeId);
			if(queryTheme != null){
				catalog = queryTheme.getCatalog();
			}
		}
		//获取所选分类下所有主题列表
		List<QueryTheme> themeList = new ArrayList<QueryTheme>();
		List<String> themeIdList = new ArrayList<String>();
		String [] themeIds = null;
		themeList = statFormService.queryThemeListByCatalog(catalog);
		if(themeList !=null && themeList.size()>0){
			for(int i=0;i<themeList.size();i++){
				themeIdList.add(themeList.get(i).getThemeId());
			}
		themeIds = themeIdList.toArray(new String[themeIdList.size()]);
		}
		//查询条件无名称信息，则显示分类下所有主题
		List<FormSave> saveList = statFormService.querySave(saveName,themeIds);
		//查询条件有名称信息，则只显示有匹配项的主题
		if(StringUtils.isNotBlank(saveName) ){
			themeIdList.clear();
			themeList.clear();
			if(saveList !=null && saveList.size()>0){
				for(int i=0;i<saveList.size();i++){
					themeIdList.add(saveList.get(i).getThemeId());
				}
				themeList = statFormService.queryThemeList(themeIdList.toArray(new String[themeIdList.size()]));
				saveList = statFormService.querySaveListByTheme(themeIdList.toArray(new String[themeIdList.size()]));
			}
		}
		//所有主题
		List<QueryTheme> catalogList = statFormService.queryCatalogList();
		result.put("catalogList", catalogList);
		result.put("queryThemeList", themeList);
		result.put("saveList", saveList);
		result.put("saveName", saveName);
		result.put("catalog", catalog);
        return result;
    }

	/**
	 * 进入展示页面，包括查询条件及报表结果及图表(一个行分组、一个列分组，则可以显示为图表,其他的不能显示图表)
	 * @param req
	 * @param saveId 保存定义的主键
	 * @param showType 展示方式（一个行分组、一个列分组情况下使用）： 为空-全显示；1-只显示表格； 2-只显示为图标
	 * @param catalogName 图标类型（一个行分组、一个列分组情况下使用），默认为ScrollColumn2D
	 * @param exchangeGroup 分组设置（一个行分组、一个列分组情况下使用），默认为1 (1--不换 2--行列互换)
	 * @param drillType 钻取类型 1 钻取表  2钻取图  3钻取图表 空值不钻取
	 * @param drillId 钻取主键 即saveId
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.query.statform.form_preview")
    public Map<String,Object> preview(HttpServletRequest req,String saveId,String showType,String catalogName,String exchangeGroup,
    		String drillType,String drillId) throws Exception {
    	//用于保存返回结果
    	Map<String,Object> result = new HashMap<String, Object>();
    	//参数初始化
        if (StringUtils.isBlank(catalogName))
            catalogName = "ScrollColumn2D";
        if (StringUtils.isBlank(exchangeGroup))
            exchangeGroup = "1";
        // 条件Map
        Map<String,String> conMap = new HashMap<String,String>();
        // 当前自定义保存信息
        FormSave formSave = statFormService.queryFormSaveById(saveId);
		//renderer设置
        Map<String,Object> queryRenderer = null;
        if(StringUtils.isNotBlank(formSave.getRenderer())){
        	queryRenderer = genRenderString(req,formSave.getRenderer());
            result.put("queryRenderer", queryRenderer);// 保存查询实例
        }

        // 查询条件字段列表
        // where 条件列列表（列对象列表）
        List<QueryColumn> conditionColList = new ArrayList<QueryColumn>();
        if (StringUtils.isNotBlank(formSave.getConCols()))
            conditionColList = statFormService.queryColumnListByColId(StringUtils.split(formSave.getConCols(), ","));
        // 设置查询热度
        statFormService.addQueryCount(saveId, formSave.getQueryCount().intValue() + 1);
        // 拼接查询条件，查询结果语句
        String querySql = genQuerySql(req, formSave, conditionColList, conMap,queryRenderer);
        // 统计行列数据
        Map<String,Object> groupDataMap = genGroupDataMap(req, formSave, conditionColList,queryRenderer);
        String  formString = "";
        // ******生成报表开始************************************************************
        List formDataList = statFormService.queryAllResultList(querySql);
        
        if(formDataList!=null && formDataList.size()>0){
        String xmlString = genFormData(formDataList, formSave, groupDataMap);

        Report groupReport = ReportManager.toReport(xmlString);
        formString = groupReport.toHtml();
        // ******生成报表结束************************************************************

        // *******生成图表开始（如果是一个行分组、一个列分组，则可以显示为图表）******************
        if (StringUtils.isNotBlank(formSave.getColGroups()) && formSave.getColGroups().split(",").length == 1
                && formSave.getRowGroups().split(",").length == 1) {

            String chartXmlString = genChartData(formSave, catalogName, groupReport,drillType,drillId,conMap);
            // 去掉换行和空格
            chartXmlString = chartXmlString.replaceAll("\r\n\t", "");
            chartXmlString = chartXmlString.replaceAll("\r\n", "");
            result.put("chartFlag", "1"); // 返回图表标识
            result.put("chartXmlString", chartXmlString); // 返回图表数据
        }
        // ******生成图表结束************************************************************
        }
        
		Map<String, String> multiMap = new HashMap<String, String>();
		if (StringUtils.isNotBlank(formSave.getMultiCols())) {
			String[] refStrings = formSave.getMultiCols().split(",");
			for (int i = 0; i < refStrings.length; i++)
				multiMap.put(refStrings[i], refStrings[i]);
		}

        result.put("catalogName", catalogName); // 返回图表数据
        result.put("drillType", drillType); // 钻取类型
        result.put("drillId", drillId); // 钻取连接主键
        result.put("conditionColList", conditionColList);// 返回条件列列表
        result.put("formSave", formSave);// 返回保存实例
        result.put("conMap", conMap); // 查询参数列表
        result.put("showType", showType); // 展示方式（一个行分组、一个列分组情况下使用）
        result.put("formString",formString ); // 返回表格展示数据
        result.put("multiMap", multiMap); //
        return result;
    }

	/**
	 * 进入展示页面，包括查询条件及报表结果及图表(一个行分组、一个列分组，则可以显示为图表,其他的不能显示图表)
	 * @param req
	 * @param saveId 保存定义的主键
	 * @param showType 展示方式（一个行分组、一个列分组情况下使用）： 为空-全显示；1-只显示表格； 2-只显示为图标
	 * @param catalogName 图标类型（一个行分组、一个列分组情况下使用），默认为ScrollColumn2D
	 * @param exchangeGroup 分组设置（一个行分组、一个列分组情况下使用），默认为1 (1--不换 2--行列互换)
	 * @param drillType 钻取类型 1 钻取表  2钻取图  3钻取图表 空值不钻取
	 * @param drillId 钻取主键 即saveId
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.query.statform.form_result")
    public Map<String,Object> showForm(HttpServletRequest req,String saveId,String showType,String catalogName,String exchangeGroup,
    		String drillType,String drillId) throws Exception {
    	//用于保存返回结果
    	Map<String,Object> result = new HashMap<String, Object>();
    	//参数初始化
        if (StringUtils.isBlank(catalogName))
            catalogName = "ScrollColumn2D";
        if (StringUtils.isBlank(exchangeGroup))
            exchangeGroup = "1";
        // 条件Map
        Map<String,String> conMap = new HashMap<String,String>();
        // 当前自定义保存信息
        FormSave formSave = statFormService.queryFormSaveById(saveId);
		//renderer设置
        Map<String,Object> queryRenderer = null;
        if(StringUtils.isNotBlank(formSave.getRenderer())){
        	queryRenderer = genRenderString(req,formSave.getRenderer());
            result.put("queryRenderer", queryRenderer);// 保存查询实例
        }

        // 查询条件字段列表
        // where 条件列列表（列对象列表）
        List<QueryColumn> conditionColList = new ArrayList<QueryColumn>();
        if (StringUtils.isNotBlank(formSave.getConCols()))
            conditionColList = statFormService.queryColumnListByColId(StringUtils.split(formSave.getConCols(), ","));
        // 设置查询热度
        statFormService.addQueryCount(saveId, formSave.getQueryCount().intValue() + 1);
        // 拼接查询条件，查询结果语句
        String querySql = genQuerySql(req, formSave, conditionColList, conMap,queryRenderer);
        // 统计行列数据
        Map<String,Object> groupDataMap = genGroupDataMap(req, formSave, conditionColList,queryRenderer);
        String  formString = "";
        // ******生成报表开始************************************************************
        List formDataList = statFormService.queryAllResultList(querySql);
        
        if(formDataList!=null && formDataList.size()>0){
        String xmlString = genFormData(formDataList, formSave, groupDataMap);

        Report groupReport = ReportManager.toReport(xmlString);
        formString = groupReport.toHtml();
        // ******生成报表结束************************************************************

        // *******生成图表开始（如果是一个行分组、一个列分组，则可以显示为图表）******************
        if (StringUtils.isNotBlank(formSave.getColGroups()) && formSave.getColGroups().split(",").length == 1
                && formSave.getRowGroups().split(",").length == 1) {

            String chartXmlString = genChartData(formSave, catalogName, groupReport,drillType,drillId,conMap);
            // 去掉换行和空格
            chartXmlString = chartXmlString.replaceAll("\r\n\t", "");
            chartXmlString = chartXmlString.replaceAll("\r\n", "");
            result.put("chartFlag", "1"); // 返回图表标识
            result.put("chartXmlString", chartXmlString); // 返回图表数据
        }
        // ******生成图表结束************************************************************
        }
        
		Map<String, String> multiMap = new HashMap<String, String>();
		if (StringUtils.isNotBlank(formSave.getMultiCols())) {
			String[] refStrings = formSave.getMultiCols().split(",");
			for (int i = 0; i < refStrings.length; i++)
				multiMap.put(refStrings[i], refStrings[i]);
		}

        result.put("catalogName", catalogName); // 返回图表数据
        result.put("drillType", drillType); // 钻取类型
        result.put("drillId", drillId); // 钻取连接主键
        result.put("conditionColList", conditionColList);// 返回条件列列表
        result.put("formSave", formSave);// 返回保存实例
        result.put("conMap", conMap); // 查询参数列表
        result.put("showType", showType); // 展示方式（一个行分组、一个列分组情况下使用）
        result.put("formString",formString ); // 返回表格展示数据
        result.put("multiMap", multiMap); //
        return result;
    }

    /**
     * 进入报表定义界面，包含报表新增定义及报表定义修改
     * @param themeId 主题主键
     * @param saveId 当前自定义保存主键
     * @return
     * @throws Exception
     */
	@At
	@Ok("jsp:jsp.query.statform.form_define")
    public Map<String,Object> toDefine(String themeId,String saveId,String filterType,String saveName,String catalog) 
    	throws Exception {
		//返回结果保存map
    	Map<String,Object> result = new HashMap<String, Object>();
    	FormSave formSave = new FormSave();
        // 修改已经定义的查询配置
        if (StringUtils.isNotBlank(saveId)) {
            formSave = statFormService.queryFormSaveById(saveId);
            themeId = formSave.getThemeId();
            // 获取钻取名称
            result.put("formSave", formSave);
        }
        // 主题所包括的配置列列表
        List<QueryColumn> columnList = statFormService.queryColumnList(themeId);
        
        //将已经定义好的查询条件放入Map（配置项主键，配置项主键）
        Map<String,String> conMap = new HashMap<String,String>();
    	if(StringUtils.isNotBlank(formSave.getConCols())){
    		String[] refStrings = formSave.getConCols().split(",");
    		for(int i=0;i<refStrings.length;i++) conMap.put(refStrings[i],refStrings[i]);
    	}
		// 可选条件
		List<QueryColumn> unselectedCons = new ArrayList<QueryColumn>();
		// 已选条件
		List<QueryColumn> selectedCons = new ArrayList<QueryColumn>();

		for (QueryColumn column : columnList) {
			if ((column.getColumnType().equals("1") || column.getColumnType().equals("2")) && !conMap.containsKey(column.getColId())) {
				unselectedCons.add(column);
			} else if (conMap.containsKey(column.getColId())) {
				selectedCons.add(column);
			}
//			if ((column.getColumnType().equals("1") || column.getColumnType().equals("3")) && !rsMap.containsKey(column.getColId())) {
//				unselectedResults.add(column);
//			} else if (rsMap.containsKey(column.getColId())) {
//				selectedResults.add(column);
//			}
		}
		
		List<String> colGroup = new ArrayList<String>();
		List<String> rowGroup = new ArrayList<String>();
		List<String> statCols = new ArrayList<String>();
		List<String> statDescs = new ArrayList<String>();
		List<String> statMethods = new ArrayList<String>();
		List<String> groupSorts = new ArrayList<String>();
		
		if (StringUtils.isNotBlank(formSave.getStatCols())) {
			statCols= Arrays.asList(formSave.getStatCols().split(","));
		}
		if (StringUtils.isNotBlank(formSave.getStatDesc())) {
			statDescs = Arrays.asList(formSave.getStatDesc().split(","));
		}
		if (StringUtils.isNotBlank(formSave.getStatMethod())) {
			statMethods = Arrays.asList(formSave.getStatMethod().split(","));
		}
		if (StringUtils.isNotBlank(formSave.getRowGroups())) {
			rowGroup = Arrays.asList(formSave.getRowGroups().split(","));
		}
		if (StringUtils.isNotBlank(formSave.getColGroups())) {
			colGroup = Arrays.asList(formSave.getColGroups().split(","));
		}
		if (StringUtils.isNotBlank(formSave.getGroupsSort())) {
			groupSorts = Arrays.asList(formSave.getGroupsSort().split(","));
		}

		//查询主题相关
		result.put("filterType", filterType);
		result.put("saveName", saveName);
		result.put("catalog", catalog);
		
        result.put("themeId", themeId);
        result.put("columnList", columnList);
        result.put("unselectedCons", unselectedCons);
        result.put("selectedCons", selectedCons);
        
        result.put("colGroup", colGroup);
		result.put("rowGroup", rowGroup);
		result.put("statCols", statCols);
		result.put("statDescs", statDescs);
		result.put("statMethods", statMethods);
		result.put("groupSorts", groupSorts);
        return result;
    }

    /**
     * 删除已定义报表
     * @param themeId 主题主键
     * @param saveId 当前自定义保存主键
     * @return
     * @throws Exception
     */
     
	@At
	@Ok("forward:/query/statForm/toTheme?themeId=${obj.themeId}")
    public Map<String,Object> delForm(String themeId,String saveId) throws Exception {
        statFormService.removeFormSave(saveId);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("themeId", themeId);
        return result;
    }

    /**
     * 保存报表
     * @param colConSelect 查询条件
     * @param groups 分组字段
     * @param groupsTypes 分组类型；1-行分组和2-列分组
     * @param groupSorts 分组排序
     * @param statCols 统计字段
     * @param statMethods 统计方法
     * @param statDescs 统计名称
     * @param saveId 已定义查询ID
     * @return
     * @throws Exception
     */
	@At
	@Ok("forward:/query/statForm/toTheme?themeId=${obj.themeId}")
    public Map<String,Object> saveForm(String[] conSelect,String[] groups,String[] groupsTypes,String[] groupSorts,
    		String[] statCols,String[] statMethods,String[] statDescs,String saveId,String themeId,String totalFlag,
    		String statColsUnit,String name,String saveDesc,String renderer) throws Exception {
        FormSave formSave = null;
        // 判断是新增还是更新
        if (StringUtils.isBlank(saveId)) {
            formSave = new FormSave();
        } else {
            formSave = statFormService.queryFormSaveById(saveId);
        }
        // 行分组字符串
        String rowGroups = "";
        // 列分组字符串
        String colGroups = "";
        // 分组有效字段长度
        int groupIndex = 0;
        // 统计有效字段长度
        int statIndex = 0;
        // 设置行分组字符串
        for (int i = 0; i < groups.length; i++) {
            if (StringUtils.isNotBlank(groups[i])) {
                groupIndex++;
                if ("1".equals(groupsTypes[i]))
                    rowGroups += groups[i] + ",";
                else
                    colGroups += groups[i] + ",";
            }
        }
        // 设置列分组字符串
        for (int i = 0; i < statCols.length; i++) {
            if (StringUtils.isNotBlank(statCols[i])) {
                statIndex++;
            }
        }
        // 设置报表属性
        
        formSave.setThemeId(StringUtils.trimToEmpty(themeId));
        formSave.setTotalFlag(StringUtils.trimToEmpty(totalFlag));
        formSave.setStatColsUnit(StringUtils.trimToEmpty(statColsUnit));
        formSave.setName(StringUtils.trimToEmpty(name));
        formSave.setSaveDesc(StringUtils.trimToEmpty(saveDesc));
        formSave.setConCols(StringUtils.trimToEmpty(ArrayUtil.strArray2StrSplit(conSelect, ",")));
        formSave.setStatCols(StringUtils.trimToEmpty(ArrayUtil.strPartArray2StrSplit(statCols, ",", statIndex)));
        formSave.setStatMethod(StringUtils.trimToEmpty(ArrayUtil.strPartArray2StrSplit(statMethods, ",", statIndex)));
        formSave.setStatDesc(StringUtils.trimToEmpty(ArrayUtil.strPartArray2StrSplit(statDescs, ",", statIndex)));
        formSave.setGroupsSort(StringUtils.trimToEmpty(ArrayUtil.strPartArray2StrSplit(groupSorts, ",", groupIndex)));
        formSave.setColGroups(genFormatSring(colGroups));
        formSave.setRowGroups(genFormatSring(rowGroups));
		formSave.setRenderer(StringUtils.trimToEmpty(renderer));
        // 判断是新增还是更新
        if (StringUtils.isBlank(saveId)) {
            formSave.setQueryCount(new Integer(1));
            // 当前时间生成一个saveId
            Date date = new Date();
            formSave.setSaveId((date.getTime()) + "");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String saveDate = formatter.format(date);
            formSave.setSaveDate(DateUtils.string2Timestamp(saveDate, DateUtils.FORMAT_DATE));
            formSave.setTotalFlag("0");
            statFormService.addFormSave(formSave);
        } else {
            statFormService.updateFormSave(formSave);
        }
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("themeId", formSave.getThemeId());
        return result;
    }

	 /**
   * 将排序定义转为具体查询语法
   * 
   * @param colIds
   * @return 排序定义查询语法
   */
  @SuppressWarnings("unchecked")
private Map<String,Object> genRenderString(HttpServletRequest request,String render) {
  	try {
			Class classType = Class.forName(render);
			 Object invokeTester = classType.newInstance();  
			 Method addMethod = classType.getMethod("genWhereClause", new Class[] {HttpServletRequest.class});     
			 Object result = addMethod.invoke(invokeTester, new Object[] {request}); 
			 return (Map)result;
		} catch (Exception e) {
			e.printStackTrace();
		} 
      return null;

  }
    /**
     * 生成图表xml数据，当存在一个行分组，一个列分组，一个统计列的时候，多个统计列中的一个
     * 
     * @param formDataList
     *            值数据
     * @param formSave
     *            保存保存对象
     * @param groupDataMap
     *            行列分组内容列表
     * @return
     */
    private String genChartData(FormSave formSave, String catalogName, Report groupReport,String drillType,String drillId,Map conMap) {
        // 模板传值Map
        Map velocityMap = new HashMap();
        // ****************整理行列对应表格显示数据********************************
        String[] xGroup = groupReport.firstRowGroupArray();
        String[] yGroup = groupReport.firstColGroupArray();
        LinkedHashMap dataList = groupReport.firstCrossData();
        LinkedHashMap drillList = new LinkedHashMap();
        
        //****************钻取设置**********************************************
        //***基本原理：根据xGroup 和 yGroup 设置钻取URL参数，如果是字典，还需要反翻译为key***
        if(StringUtils.isNotBlank(drillType)){
        	//x轴列属性对象
        	QueryColumn xColumn = statFormService.queryColumnById(formSave.getRowGroups());
        	//y轴列属性对象
        	QueryColumn yColumn = statFormService.queryColumnById(formSave.getColGroups());
        	//将key和value颠倒的字典MAP
        	Map xDicMap = null;
        	Map yDicMap = null;
        	if("2".equals(xColumn.getEditType()) || "4".equals(xColumn.getEditType())){
        		xDicMap =reverseDicMap(xColumn.getDicId().intValue());
        	}
        	if("2".equals(yColumn.getEditType()) || "4".equals(yColumn.getEditType())){
        		yDicMap =reverseDicMap(yColumn.getDicId().intValue());
        	}
        	int index=0;
        	//设置URL，按照顺序设置，放到vm中式也是按照顺序放置
	        for(int i=0;i<yGroup.length;i++){
	        	 for(int j=0;j<xGroup.length;j++){
	        		 StringBuffer drillString = new StringBuffer();
	        		 //区分图表类型
	        		 if("1".equals(drillType)){
		        		 drillString.append("detailQuery.do?method=queryResult&saveId="+drillId);//基本URL
	        		 }else if("2".equals(drillType)){
		        		 drillString.append("statChart.do?method=showChart&saveId="+drillId);//基本URL
	        		 }
	        		 //设置y轴，翻译字段
	        		 if("2".equals(yColumn.getEditType()) || "4".equals(yColumn.getEditType())){
	        			 drillString.append("&"+DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL,formSave.getColGroups())+"="+yDicMap.get(yGroup[i]));//y轴
	        		 }else{
	        			 drillString.append("&"+DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL,formSave.getColGroups())+"="+yGroup[i]);//y轴
	 	        	 }
	        		 //设置x轴，翻译字段
	        		 if("2".equals(xColumn.getEditType()) || "4".equals(xColumn.getEditType())){
		        		 drillString.append("&"+DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL,formSave.getRowGroups())+"="+xDicMap.get(xGroup[j]));//x轴
		        	 }else{
		        		 drillString.append("&"+DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL,formSave.getRowGroups())+"="+xGroup[j]);//x轴
		        	 }
	        		 drillString.append(genDrillDownParameter(conMap));
	        		 drillList.put(new Integer(index), drillString.toString());
	        		 index++;
	             }	
	        }
	        velocityMap.put("drillList", drillList);
	        velocityMap.put("drillType", drillType);
        }else{
        	velocityMap.put("drillType", "0");
        }
        //****************钻取设置**********************************************
        
        // 设置变量
        velocityMap.put("yGroupsArray", yGroup);
        velocityMap.put("xGroupsArray", xGroup);
        velocityMap.put("chartDataList", dataList);
        velocityMap.put("chartDataListKey", dataList.keySet());
        velocityMap.put("chartName", formSave.getName());

        // 转化成的XML数据结果值
        String xmlString = "";
        try {
        	xmlString = VelocityUtils.merge(velocityMap, "To" + catalogName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlString;

    }
    
    private String genDicString(QueryColumn groupColumn,Object key){
    	String result = null;
    	if("2".equals(groupColumn.getEditType()) && key!=null){
    		result = DicDataUtils.getInstance().getDicItemName(groupColumn.getDicId().intValue(),new Integer(((BigDecimal)key).intValue()));
		}else{
			result = DicDataUtils.getInstance().getDicItemName(groupColumn.getDicId().intValue(),""+key);
		}
    	return result;
    }
    
    
    private String formatData(Object data){
    	String result = "";
    	if(data != null){
    		DecimalFormat format = new DecimalFormat("#0.00");
    		return format.format(data);
		}
    	return result;
    }
    
    /**
     * 将字典map的key与value置换位置
     * @param dicId 字典ID
     * @return
     */
    private Map reverseDicMap(int dicId){
    	Map reverseMap = new HashMap();
    	Map dicMap = DicDataUtils.getInstance().getDic(dicId);
    	Iterator iter =dicMap.keySet().iterator();
    	while(iter.hasNext()){
    		Object keyString = iter.next();
    		Object valueString = dicMap.get(keyString);
    		reverseMap.put(valueString, keyString);
    	}
		return reverseMap;
            
    	
    }
    /**ap(
     * 生成报表xml数据
     * 
     * @param formDataList
     *            值数据
     * @param formSave
     *            保存保存对象
     * @param groupDataMap
     *            行列分组内容列表
     * @return
     */
    private String genFormData(List<Map<String,Object>> formDataList, FormSave formSave, Map<String,Object> groupDataMap) {
        // 模板传值Map
        Map<String,Object> velocityMap = new HashMap<String,Object>();
        // 列名数组
        String[] colGroupArray = {};
        // 行名数组
        String[] rowGroupArray = formSave.getRowGroups().split(",");
        // 统计列主键数组（包括行和列统计）
        String groupString = formSave.getRowGroups();
        if (StringUtils.isNotBlank(formSave.getColGroups())) {
            groupString += "," + formSave.getColGroups();
            // 只包含一个统计列时
            if (formSave.getStatCols().split(",").length > 1)
                colGroupArray = (formSave.getColGroups() + "," + StatFormConstans.STAT_DATA_NAME).split(",");
            else
                colGroupArray = formSave.getColGroups().split(",");
        }
        // 获取统计列（包括行和列统计）字段列表
        List<QueryColumn> groupColList = statFormService.queryColumnListByColId(StringUtils.split(groupString, ","));
        // 行分组内容Map
        Map<String,String> groupMap = new HashMap<String,String>();
        // 行分组名称Map
        Map<String,Object> groupNameMap = new HashMap<String,Object>();
        // ****************翻译字典及整理行列显示数据********************************
    	for(int i=0;i<groupColList.size();i++){
			QueryColumn groupCol = (QueryColumn)groupColList.get(i);
			groupNameMap.put(groupCol.getNameLetter(), groupCol.getName());
			if("2".equals(groupCol.getEditType()) || "4".equals(groupCol.getEditType())){
				 for(int j=0;j<formDataList.size();j++){
					    Map<String,Object> dataMap = (Map<String,Object>)formDataList.get(j);
					    String chartLable = this.genDicString(groupCol, dataMap.get(groupCol.getNameLetter()));
					    dataMap.put(groupCol.getNameLetter(), chartLable);
				 }
			}
			//行分组字典翻译及行显示数据整理
			for(int k=0;k<rowGroupArray.length;k++){
				if(groupCol.getColId().equals(rowGroupArray[k])){
					rowGroupArray[k] = groupCol.getNameLetter();
					String result="";
					List<Object> rowList = (List<Object>)groupDataMap.get(groupCol.getNameLetter());
					for(int j=0;j<rowList.size();j++){
						 Map rowDataMap = (Map)rowList.get(j);
						if("2".equals(groupCol.getEditType()) || "4".equals(groupCol.getEditType())){
							result += this.genDicString(groupCol, rowDataMap.get(groupCol.getNameLetter()))+ReportConstants.DATA_SEPARATOR;
						}else{
							result +=rowDataMap.get(groupCol.getNameLetter())+ReportConstants.DATA_SEPARATOR;
						}
					}
					if(StringUtils.isNotBlank(result)){
						groupMap.put(groupCol.getNameLetter(), result.substring(0, result.length()-1));
					}else{
						groupMap.put(groupCol.getNameLetter(), "");
					}
				}
			}
			
			//列分组字典翻译及列显示数据整理
			for(int k=0;k<colGroupArray.length;k++){
				if(groupCol.getColId().equals(colGroupArray[k])){
					colGroupArray[k] = groupCol.getNameLetter();
					String result="";
					List colList = (List)groupDataMap.get(groupCol.getNameLetter());
					for(int j=0;j<colList.size();j++){
						 Map colDataMap = (Map)colList.get(j);
						if("2".equals(groupCol.getEditType()) || "4".equals(groupCol.getEditType())){
							result += this.genDicString(groupCol, colDataMap.get(groupCol.getNameLetter()))+ReportConstants.DATA_SEPARATOR;
						}else{
							result +=colDataMap.get(groupCol.getNameLetter())+ReportConstants.DATA_SEPARATOR;
						}
					}
					if(StringUtils.isNotBlank(result)){
						groupMap.put(groupCol.getNameLetter(), result.substring(0, result.length()-1));
					}else{
						groupMap.put(groupCol.getNameLetter(), "");
					}
				}
			}
		}
        // ****************翻译字典及整理行列显示数据********************************
        // 整理后数据列表
        List dataList = new ArrayList();
        // ****************整理行列对应表格显示数据********************************
        // 包含多个统计列时
        if (formSave.getStatCols().split(",").length > 1) {
            // 设置统计列名称
            groupMap.put(StatFormConstans.STAT_DATA_NAME, formSave.getStatDesc().replaceAll(",",
                    ReportConstants.DATA_SEPARATOR));
            groupNameMap.put(StatFormConstans.STAT_DATA_NAME, "");// 设置统计列显示名称
            //velocityMap.put("dataName", "");// 设置数据显示名称
            String[] statArray = formSave.getStatCols().split(",");
            String[] methodArray = formSave.getStatMethod().split(",");
            String[] statDescArray = formSave.getStatDesc().split(",");
            for (int k = 0; k < statArray.length; k++) {
                String colName = DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL, statArray[k]);
                for (int j = 0; j < formDataList.size(); j++) {
                    Map dataMap = (Map) formDataList.get(j);
                    StringBuffer dataString = new StringBuffer();
                    // 拼列分组
                    for (int i = 0; i < colGroupArray.length; i++) {
                        if (!StatFormConstans.STAT_DATA_NAME.equals(colGroupArray[i]))
                            dataString.append(ReportConstants.DATA_SEPARATOR).append(dataMap.get(colGroupArray[i]));
                    }
                    dataString.append(ReportConstants.DATA_SEPARATOR).append(statDescArray[k]);
                    // 拼行分组
                    for (int i = 0; i < rowGroupArray.length; i++) {
                        dataString.append(ReportConstants.DATA_SEPARATOR).append(dataMap.get(rowGroupArray[i]));
                    }
                    if(dataMap.get(colName + "_" + methodArray[k]) == null){
                    	 dataString.append(ReportConstants.DATA_SEPARATOR).append("0");
                    }else{
                    	 dataString.append(ReportConstants.DATA_SEPARATOR).append(formatData(
                                 dataMap.get(colName + "_" + methodArray[k])));
                    }
                    dataList.add(dataString.toString());
                }
            }
        } else {// 只包含一个统计列时
            //velocityMap.put("dataName", formSave.getStatDesc());// 设置数据显示名称
            for (int j = 0; j < formDataList.size(); j++) {
                Map dataMap = (Map) formDataList.get(j);
                StringBuffer dataString = new StringBuffer();
                for (int i = 0; i < colGroupArray.length; i++) {
                    dataString.append(ReportConstants.DATA_SEPARATOR).append(dataMap.get(colGroupArray[i]));
                }
                for (int i = 0; i < rowGroupArray.length; i++) {
                    dataString.append(ReportConstants.DATA_SEPARATOR).append(dataMap.get(rowGroupArray[i]));
                }
                if( dataMap.get(DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL,formSave.getStatCols())+ "_" + formSave.getStatMethod()) ==null){
                	dataString.append(ReportConstants.DATA_SEPARATOR).append("0");
                }else{
                	dataString.append(ReportConstants.DATA_SEPARATOR).append(formatData(dataMap.get(DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL,formSave.getStatCols())+ "_" + formSave.getStatMethod())));
                }
                
                dataList.add(dataString.toString());
            }
        }
        // ****************整理行列对应表格显示数据********************************
        // 设置变量

        velocityMap.put("dataName", StringUtils.trimToEmpty(formSave.getStatColsUnit()));// 设置数据显示名称
        velocityMap.put("groupMap", groupMap);
        velocityMap.put("groupNameMap", groupNameMap);
        velocityMap.put("dataList", dataList);
        velocityMap.put("rowGroupArray", rowGroupArray);
        velocityMap.put("colGroupArray", colGroupArray);
        velocityMap.put("totalFlag", formSave.getTotalFlag());
        // 转化成的XML数据结果值
        String xmlString = "";
        try {
            xmlString = VelocityUtils.merge(velocityMap, StatFormConstans.VM_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlString;
    }

    /**
     * 查询统计字段的的数值，保存在map中，主键为查询字段名称，值为list
     * 
     * @param request
     * @param chartSave
     *            保存统计对象
     * @param conditionColList
     *            查询条件字段列表
     * @param conMap
     *            条件Map
     * @return QueryCon 查询条件
     */
    private Map<String,Object> genGroupDataMap(HttpServletRequest request, FormSave formSave, List<QueryColumn> conditionColList,Map<String,Object> queryRenderer) {
        QueryTheme queryTheme = statFormService.getQueryThemeById(formSave.getThemeId());
        Map<String,String> multiMap = new HashMap<String,String>();
        if(StringUtils.isNotBlank(formSave.getMultiCols())){
        	String[] refStrings = formSave.getMultiCols().split(",");
        	for(int i=0;i<refStrings.length;i++) multiMap.put(refStrings[i],refStrings[i]);
        }
        // ***********设置各拼接条件****
        // 查询where拼接条件,保存条件信息到conMap
        String conString = genQuerywhereClause(request, conditionColList, new HashMap<String,String>(),multiMap);
        // ***********设置各拼接条件**********
		 if(queryRenderer !=null && queryRenderer.size()>0){
	        	conString +=queryRenderer.get(BaseRenderer.WHERE_SQL);
	        }
        Map<String,Object> groupDataMap = new HashMap<String,Object>();
        // 获取排序列数组
        String[] sortArray = formSave.getGroupsSort().split(",");
        // 设置排阻字段数组
        String groupString = formSave.getRowGroups();
        if (StringUtils.isNotBlank(formSave.getColGroups()))
            groupString += "," + formSave.getColGroups();
        String[] groupArray = groupString.split(",");
        // 查询统计字段的的数值
        for (int i = 0; i < sortArray.length; i++) {
            String groupName = DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL, groupArray[i]);
            StringBuffer querySql = new StringBuffer();
            querySql.append("SELECT ").append(groupName).append(" FROM ").append(queryTheme.getViewName()).append(
                    " WHERE ").append(groupName).append(" IS NOT NULL ").append(conString).append(" GROUP BY ").append(
                    groupName).append(" ORDER BY ").append(groupName).append(" ").append(sortArray[i]);
            groupDataMap.put(groupName, statFormService.queryAllResultList(querySql.toString()));
        }
        return groupDataMap;

    }

    /**
     * 构建统计语句
     * 
     * @param request
     * @param chartSave
     *            保存统计对象
     * @param conditionColList
     *            查询条件字段列表
     * @param conMap
     *            条件Map
     * @return QueryCon 查询条件
     */
    private String genQuerySql(HttpServletRequest request, FormSave formSave, List<QueryColumn> conditionColList, Map<String,String> conMap,Map<String,Object> queryRenderer) {
        QueryTheme queryTheme = statFormService.getQueryThemeById(formSave.getThemeId());
        Map<String,String> multiMap = new HashMap<String,String>();
        if(StringUtils.isNotBlank(formSave.getMultiCols())){
        	String[] refStrings = formSave.getMultiCols().split(",");
        	for(int i=0;i<refStrings.length;i++) multiMap.put(refStrings[i],refStrings[i]);
        }
        // ***********设置各拼接条件****
        // 查询where拼接条件,保存条件信息到conMap
        String conString = genQuerywhereClause(request, conditionColList, conMap,multiMap);
        // 查询select拼接条件
        String statString = genQueryStatClause(formSave);
        // 查询group by 拼接条件
        String groupString = genQueryGroupClause(formSave);
        // 查询SORT拼接条件
        String sortString = genQuerySortClause(formSave);
        // ***********设置各拼接条件**********
		 if(queryRenderer !=null && queryRenderer.size()>0){
	        	conString +=queryRenderer.get(BaseRenderer.WHERE_SQL);
	        }
        // ***********查询语句拼接**********
        // 拼接查询语句
        StringBuffer querySql = new StringBuffer();
        querySql.append("SELECT ").append(statString).append(" FROM ").append(queryTheme.getViewName()).append(
                " WHERE 1=1 ").append(conString).append(groupString);
        if (StringUtils.isNotBlank(sortString))
            querySql.append(" ORDER BY ").append(sortString);
        return querySql.toString();

    }

    /**
     * 将排序定义转为具体查询语法
     * 
     * @param chartSave
     *            保存统计对象
     * @return 排序定义查询语法
     */
    private String genQuerySortClause(FormSave formSave) {
        if (StringUtils.isBlank(formSave.getGroupsSort()))
            return "";
        // 获取排序列数组
        String[] sortArray = formSave.getGroupsSort().split(",");
        // 设置排阻字段数组
        String groupString = formSave.getRowGroups();
        if (StringUtils.isNotBlank(formSave.getColGroups()))
            groupString += "," + formSave.getColGroups();
        String[] groupArray = groupString.split(",");
        String result = null;
        for (int i = 0; i < sortArray.length; i++) {
            if (result == null)
                result = DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL, groupArray[i]) + " "
                        + sortArray[i];
            else
                result += "," + DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL, groupArray[i])
                        + " " + sortArray[i];
        }
        return result;

    }

    /**
     * 将结果定义转为具体查询语法
     * 
     * @param chartSave
     *            保存统计对象
     * @return 结果定义查询语法
     */
    private String genQueryGroupClause(FormSave formSave) {
        String result = null;
        // 设置排阻字段数组group字段
        String groupString = formSave.getRowGroups();
        if (StringUtils.isNotBlank(formSave.getColGroups()))
            groupString += "," + formSave.getColGroups();
        String[] groupArray = groupString.split(",");
        for (int i = 0; i < groupArray.length; i++) {
            if (result == null)
                result = DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL, groupArray[i]);
            else
                result += "," + DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL, groupArray[i]);
        }
        return " GROUP BY " + result;

    }

    /**
     * 将结果定义转为具体查询语法
     * 
     * @param chartSave
     *            保存统计对象
     * @return 结果定义查询语法
     */
    private String genQueryStatClause(FormSave formSave) {
        String result = null;
        // 设置排阻字段数组group字段
        String groupString = formSave.getRowGroups();
        if (StringUtils.isNotBlank(formSave.getColGroups()))
            groupString += "," + formSave.getColGroups();
        String[] groupArray = groupString.split(",");
        for (int i = 0; i < groupArray.length; i++) {
            if (result == null)
                result = DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL, groupArray[i]);
            else
                result += "," + DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL, groupArray[i]);
        }
        // //设置排阻字段数组stat字段
        String[] statArray = formSave.getStatCols().split(",");
        String[] methodArray = formSave.getStatMethod().split(",");
        for (int i = 0; i < statArray.length; i++) {
            String colName = DicDataUtils.getInstance().getDicItemName(StatFormConstans.DIC_QUERY_COL, statArray[i]);
            result += "," + methodArray[i] + "(" + colName + ") AS " + colName + "_" + methodArray[i];
        }
        return result;

    }

    /**
     * 去掉字符串后的分隔符
     * 
     * @param stirng
     * @return
     */
    private String genFormatSring(String stirng) {
        if (StringUtils.isBlank(stirng))
            return "";
        else
            return stirng.substring(0, stirng.length() - 1);
    }
    /**
	 * 钻取参数带入
	 * @param conMap
	 * @return
	 */
   private String genDrillDownParameter(Map conMap){
	   String result="";
       Iterator iter2 = conMap.keySet().iterator();
       while (iter2.hasNext()) {
    	   String keyString = (String)iter2.next();
    	   result += "&"+keyString+"="+conMap.get(keyString);
       }
       return result;
   }
    /**
     * 将条件定义转为具体查询语法
     * 
     * @param request
     * @param conditionColList
     *            查询条件字段列表
     * @param conMap
     *            查询值
     * @return 条件定义查询语法
     */
    private String genQuerywhereClause(HttpServletRequest request, List<QueryColumn> conditionColList, Map<String,String> conMap, Map<String,String> multiMap) {
        StringBuffer conBuffer = new StringBuffer();
        for (int i = 0; i < conditionColList.size(); i++) {
            QueryColumn queryColumn = (QueryColumn) conditionColList.get(i);
            //如果是多列，不进行装配，在renderer中装配
            if(multiMap.containsKey(queryColumn.getColId())){
            	continue;
            }
            if ("3".equals(queryColumn.getEditType())) {// 日期类型
                String startDate = request.getParameter(queryColumn.getNameLetter() + "staDATE");
                String endtDate = request.getParameter(queryColumn.getNameLetter() + "endDATE");
                if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endtDate)) {
                    conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" between to_date('").append(
                            request.getParameter(queryColumn.getNameLetter() + "staDATE")).append(
                            " 00:00:00','yyyy-mm-dd hh24:mi:ss') and to_date('");
                    conBuffer.append(request.getParameter(queryColumn.getNameLetter() + "endDATE")).append(
                            " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
                    conMap.put(queryColumn.getNameLetter() + "endDATE", request.getParameter(queryColumn
                            .getNameLetter()
                            + "endDATE"));
                    conMap.put(queryColumn.getNameLetter() + "staDATE", request.getParameter(queryColumn
                            .getNameLetter()
                            + "staDATE"));
                } else if (StringUtils.isBlank(startDate) && StringUtils.isNotBlank(endtDate)) {
                    conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" < to_date('");
                    conBuffer.append(request.getParameter(queryColumn.getNameLetter() + "endDATE")).append(
                            " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
                    conMap.put(queryColumn.getNameLetter() + "endDATE", request.getParameter(queryColumn
                            .getNameLetter()
                            + "endDATE"));
                } else if (StringUtils.isNotBlank(startDate) && StringUtils.isBlank(endtDate)) {
                    conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" > to_date('").append(
                            request.getParameter(queryColumn.getNameLetter() + "staDATE")).append(
                            " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
                    conMap.put(queryColumn.getNameLetter() + "staDATE", request.getParameter(queryColumn
                            .getNameLetter()
                            + "staDATE"));
                }
            } else if ("1".equals(queryColumn.getEditType())
                    && StringUtils.isNotBlank(request.getParameter(queryColumn.getNameLetter()))) {// 文本类型
                conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" like '%").append(
                        request.getParameter(queryColumn.getNameLetter())).append("%' ");
                conMap.put(queryColumn.getNameLetter(), request.getParameter(queryColumn.getNameLetter()));
            } else if ("4".equals(queryColumn.getEditType())
                    && StringUtils.isNotBlank(request.getParameter(queryColumn.getNameLetter()))) {// key为文本类型的select类型
                conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" = '").append(
                        request.getParameter(queryColumn.getNameLetter())).append("' ");
                conMap.put(queryColumn.getNameLetter(), request.getParameter(queryColumn.getNameLetter()));
            } else if (("2".equals(queryColumn.getEditType()) || "5".equals(queryColumn.getEditType()))
                    && StringUtils.isNotBlank(request.getParameter(queryColumn.getNameLetter()))) {// key为数字类型的select类型及数值类型
                conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" = ").append(
                        request.getParameter(queryColumn.getNameLetter())).append(" ");
                conMap.put(queryColumn.getNameLetter(), request.getParameter(queryColumn.getNameLetter()));
            }
        }
        return conBuffer.toString();
    }

}
