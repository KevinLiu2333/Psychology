package com.wonders.tiles.query.statchart.ui;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.query.statchart.StatChartConstans;
import com.wonders.tiles.query.statchart.entity.ChartSave;
import com.wonders.tiles.query.statchart.entity.QueryColumn;
import com.wonders.tiles.query.statchart.entity.QueryTheme;
import com.wonders.tiles.query.statchart.service.StatChartService;
import com.wonders.tiles.query.statform.entity.FormSave;
import com.wonders.tiles.query.statform.service.StatFormService;
import com.wonders.tiles.query.statrenderer.BaseRenderer;
import com.wonders.util.ArrayUtil;
import com.wonders.util.DateUtils;
import com.wonders.util.VelocityUtils;

@At("/query/statChart")
@IocBean
public class StatChartAction{
	
	public static final String[] COL_COLOR = {"AFD8F8","F6BD0F","8BBA00","1D8BD1","F1683C","2AD62A","DBDC25"};
	
	@Inject
    private StatChartService statChartService;

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
	@Ok("jsp:jsp.query.statchart.chart_list")
	public Map<String,Object> toShowList(String type) throws Exception {
		//用于保存返回结果
		Map<String,Object> result = new HashMap<String, Object>();
		List<ChartSave>  charSaveList= statChartService.charSaveList(type);
		result.put("charSaveList", charSaveList);

		//用于保存返回结果
		List<FormSave> querySaveList = statFormService.querySaveList(type);
		result.put("querySaveList", querySaveList);
		return result;
	}
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
	@Ok("jsp:jsp.query.statchart.chart_theme")
	public Map<String,Object> toTheme(String catalog,String saveName,String themeId) throws Exception {
		//用于保存返回结果
		Map<String,Object> result = new HashMap<String, Object>();
		//初始化分类信息
		if(StringUtils.isNotBlank(themeId) && StringUtils.isBlank(catalog)){
			QueryTheme queryTheme = statChartService.getQueryThemeById(themeId);
			if(queryTheme != null){
				catalog = queryTheme.getCatalog();
			}
		}
		//获取所选分类下所有主题列表
		List<QueryTheme> themeList = new ArrayList<QueryTheme>();
		List<String> themeIdList = new ArrayList<String>();
		String [] themeIds = null;
		themeList = statChartService.queryThemeListByCatalog(catalog);
		if(themeList !=null && themeList.size()>0){
			for(int i=0;i<themeList.size();i++){
				themeIdList.add(themeList.get(i).getThemeId());
			}
		themeIds = themeIdList.toArray(new String[themeIdList.size()]);
		}
		//查询条件无名称信息，则显示分类下所有主题
		List<ChartSave> saveList = statChartService.querySave(saveName,themeIds);
		//查询条件有名称信息，则只显示有匹配项的主题
		if(StringUtils.isNotBlank(saveName) ){
			themeIdList.clear();
			themeList.clear();
			if(saveList !=null && saveList.size()>0){
				for(int i=0;i<saveList.size();i++){
					themeIdList.add(saveList.get(i).getThemeId());
				}
				themeList = statChartService.queryThemeList(themeIdList.toArray(new String[themeIdList.size()]));
				saveList = statChartService.querySaveListByTheme(themeIdList.toArray(new String[themeIdList.size()]));
			}
		}
		//所有主题
		List<QueryTheme> catalogList = statChartService.queryCatalogList();
		result.put("catalogList", catalogList);
		result.put("queryThemeList", themeList);
		result.put("saveList", saveList);
		result.put("saveName", saveName);
		result.put("catalog", catalog);
        return result;
	} 
	

	/**
	 * 进入查询页面，包括查询条件及查询结果
	 * @param req
	 * @param saveId 保存定义的主键
	 * @param showType 展示方式： 为空-全显示；1-只显示表格； 2-只显示为图标
	 * @param showTotal 展示方式： 为空-不显示； 不为空-全显示
	 * @param catalogName 图标名称如Pie2D等
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.query.statchart.chart_result")
	public Map<String,Object> showChart(HttpServletRequest req,String saveId,String showType,String showTotal,String catalogName) 
		throws Exception {
		//用于保存返回结果
		Map<String,Object> result = new HashMap<String, Object>();
		//条件Map
		Map<String,String> conMap = new HashMap<String,String>();
		//当前自定义保存信息
		ChartSave chartSave = statChartService.queryChartSaveById(saveId);
		//设置图表显示类型
		if(StringUtils.isNotBlank(catalogName)) chartSave.setCatalogName(catalogName);
		//renderer设置
        Map<String,Object> queryRenderer = null;
        if(StringUtils.isNotBlank(chartSave.getRenderer())){
        	queryRenderer = genRenderString(req,chartSave.getRenderer());
            result.put("queryRenderer", queryRenderer);// 保存查询实例
        }
		//查询条件字段列表
		//where 条件列（列对象列表）
		List<QueryColumn> conditionColList =new ArrayList<QueryColumn>();
		List<QueryColumn> resultColList = statChartService.queryColumnListByColId(StringUtils.split(chartSave.getYstat(), ","));
		if(StringUtils.isNotBlank(chartSave.getConCols()))
		conditionColList = statChartService.queryColumnListByColId(StringUtils.split(chartSave.getConCols(), ","));
		//group 条件列（列对象列表）
		QueryColumn groupColumn = statChartService.queryColumnById(chartSave.getXgroup());
		//设置查询热度
		statChartService.addQueryCount(saveId,chartSave.getQueryCount().intValue()+1);
		//拼接查询条件
		String querySql = genQuerySql(req,chartSave,conditionColList,conMap,queryRenderer);
		//导出结果
		List chartDataList = statChartService.queryAllResultList(querySql);
		String xmlString = genChartData(chartDataList,groupColumn,chartSave,resultColList,conMap);
		//去换行
		xmlString = xmlString.replaceAll("\r\n\t", "");
		xmlString = xmlString.replaceAll("\r\n", "");
		result.put("xmlString", xmlString);
		result.put("chartDataList", chartDataList);
		
		//统计字段名称列表
		List<String> yGroups = new ArrayList<String>();
		//统计字段显示名称列表
		List<String> yGroupNames = new ArrayList<String>();
		//统计字段Id列表
		List<String> yStats = new ArrayList<String>();
		
		if (StringUtils.isNotEmpty(chartSave.getYgroups())) {
			yGroups = Arrays.asList(chartSave.getYgroups().split(","));
		}
		if (StringUtils.isNotEmpty(chartSave.getYaxisNames())) {
			yGroupNames = Arrays.asList(chartSave.getYaxisNames().split(","));
		}
		if (StringUtils.isNotEmpty(chartSave.getYstat())) {
			yStats = Arrays.asList(chartSave.getYstat().split(","));
		}

	    result.put("conditionColList",conditionColList);//保存查询实例
		result.put("showTotal", showTotal); //展示方式
		result.put("showType", showType); //展示方式
	    result.put("groupColumn",groupColumn);//保存查询实例
		result.put("resultColList",resultColList);//保存查询实例
		result.put("chartDataList",chartDataList);//保存查询实例
		result.put("chartSave",chartSave);//保存查询实例
		result.put("conMap", conMap); //查询参数列表
		
		result.put("yGroups", yGroups); //统计字段名称列表
		result.put("yGroupNames", yGroupNames); //统计字段显示名称列表
		result.put("yStats", yStats); //统计字段Id列表
		return result;
	}

	

	/**
	 * 进入查询页面，包括查询条件及查询结果
	 * @param req
	 * @param saveId 保存定义的主键
	 * @param showType 展示方式： 为空-全显示；1-只显示表格； 2-只显示为图标
	 * @param showTotal 展示方式： 为空-不显示； 不为空-全显示
	 * @param catalogName 图标名称如Pie2D等
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.query.statchart.chart_preview")
	public Map<String,Object> preview(HttpServletRequest req,String saveId,String showType,String showTotal,String catalogName) 
		throws Exception {
		//用于保存返回结果
		Map<String,Object> result = new HashMap<String, Object>();
		//条件Map
		Map<String,String> conMap = new HashMap<String,String>();
		//当前自定义保存信息
		ChartSave chartSave = statChartService.queryChartSaveById(saveId);
		//设置图表显示类型
		if(StringUtils.isNotBlank(catalogName)) chartSave.setCatalogName(catalogName);
		//renderer设置
        Map<String,Object> queryRenderer = null;
        if(StringUtils.isNotBlank(chartSave.getRenderer())){
        	queryRenderer = genRenderString(req,chartSave.getRenderer());
            result.put("queryRenderer", queryRenderer);// 保存查询实例
        }
		//查询条件字段列表
		//where 条件列（列对象列表）
		List<QueryColumn> conditionColList =new ArrayList<QueryColumn>();
		List<QueryColumn> resultColList = statChartService.queryColumnListByColId(StringUtils.split(chartSave.getYstat(), ","));
		if(StringUtils.isNotBlank(chartSave.getConCols()))
		conditionColList = statChartService.queryColumnListByColId(StringUtils.split(chartSave.getConCols(), ","));
		//group 条件列（列对象列表）
		QueryColumn groupColumn = statChartService.queryColumnById(chartSave.getXgroup());
		//设置查询热度
		statChartService.addQueryCount(saveId,chartSave.getQueryCount().intValue()+1);
		//拼接查询条件
		String querySql = genQuerySql(req,chartSave,conditionColList,conMap,queryRenderer);
		//导出结果
		List chartDataList = statChartService.queryAllResultList(querySql);
		String xmlString = genChartData(chartDataList,groupColumn,chartSave,resultColList,conMap);
		//去换行
		xmlString = xmlString.replaceAll("\r\n\t", "");
		xmlString = xmlString.replaceAll("\r\n", "");
		result.put("xmlString", xmlString);
		result.put("chartDataList", chartDataList);
		
		//统计字段名称列表
		List<String> yGroups = new ArrayList<String>();
		//统计字段显示名称列表
		List<String> yGroupNames = new ArrayList<String>();
		//统计字段Id列表
		List<String> yStats = new ArrayList<String>();
		
		if (StringUtils.isNotEmpty(chartSave.getYgroups())) {
			yGroups = Arrays.asList(chartSave.getYgroups().split(","));
		}
		if (StringUtils.isNotEmpty(chartSave.getYaxisNames())) {
			yGroupNames = Arrays.asList(chartSave.getYaxisNames().split(","));
		}
		if (StringUtils.isNotEmpty(chartSave.getYstat())) {
			yStats = Arrays.asList(chartSave.getYstat().split(","));
		}

	    result.put("conditionColList",conditionColList);//保存查询实例
		result.put("showTotal", showTotal); //展示方式
		result.put("showType", showType); //展示方式
	    result.put("groupColumn",groupColumn);//保存查询实例
		result.put("resultColList",resultColList);//保存查询实例
		result.put("chartDataList",chartDataList);//保存查询实例
		result.put("chartSave",chartSave);//保存查询实例
		result.put("conMap", conMap); //查询参数列表
		result.put("saveId", saveId);
		result.put("yGroups", yGroups); //统计字段名称列表
		result.put("yGroupNames", yGroupNames); //统计字段显示名称列表
		result.put("yStats", yStats); //统计字段Id列表
		return result;
	}
	/**
	 * 导出excel
	 * @param req
	 * @param saveId 保存定义的主键
	 * @param showType 展示方式： 为空-全显示；1-只显示表格； 2-只显示为图标
	 * @param showTotal 展示方式： 为空-不显示； 不为空-全显示
	 * @param catalogName 图标名称如Pie2D等
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.query.statchart.chart_export")
	public Map<String,Object> export(HttpServletRequest req,String saveId,String showType,String showTotal,String catalogName) 
		throws Exception {
		//用于保存返回结果
		Map<String,Object> result = new HashMap<String, Object>();
		//条件Map
		Map<String,String> conMap = new HashMap<String,String>();
		//当前自定义保存信息
		ChartSave chartSave = statChartService.queryChartSaveById(saveId);
		//设置图表显示类型
		if(StringUtils.isNotBlank(catalogName)) chartSave.setCatalogName(catalogName);
		//renderer设置
        Map<String,Object> queryRenderer = null;
        if(StringUtils.isNotBlank(chartSave.getRenderer())){
        	queryRenderer = genRenderString(req,chartSave.getRenderer());
            result.put("queryRenderer", queryRenderer);// 保存查询实例
        }
		//查询条件字段列表
		//where 条件列（列对象列表）
		List<QueryColumn> conditionColList =new ArrayList<QueryColumn>();
		List<QueryColumn> resultColList = statChartService.queryColumnListByColId(StringUtils.split(chartSave.getYstat(), ","));
		if(StringUtils.isNotBlank(chartSave.getConCols()))
		conditionColList = statChartService.queryColumnListByColId(StringUtils.split(chartSave.getConCols(), ","));
		//group 条件列（列对象列表）
		QueryColumn groupColumn = statChartService.queryColumnById(chartSave.getXgroup());
		//设置查询热度
		statChartService.addQueryCount(saveId,chartSave.getQueryCount().intValue()+1);
		//拼接查询条件
		String querySql = genQuerySql(req,chartSave,conditionColList,conMap,queryRenderer);
		//导出结果
		List chartDataList = statChartService.queryAllResultList(querySql);
		String xmlString = genChartData(chartDataList,groupColumn,chartSave,resultColList,conMap);
		//去换行
		xmlString = xmlString.replaceAll("\r\n\t", "");
		xmlString = xmlString.replaceAll("\r\n", "");
		result.put("xmlString", xmlString);
		result.put("chartDataList", chartDataList);
		
		//统计字段名称列表
		List<String> yGroups = new ArrayList<String>();
		//统计字段显示名称列表
		List<String> yGroupNames = new ArrayList<String>();
		//统计字段Id列表
		List<String> yStats = new ArrayList<String>();
		
		if (StringUtils.isNotEmpty(chartSave.getYgroups())) {
			yGroups = Arrays.asList(chartSave.getYgroups().split(","));
		}
		if (StringUtils.isNotEmpty(chartSave.getYaxisNames())) {
			yGroupNames = Arrays.asList(chartSave.getYaxisNames().split(","));
		}
		if (StringUtils.isNotEmpty(chartSave.getYstat())) {
			yStats = Arrays.asList(chartSave.getYstat().split(","));
		}

	    result.put("conditionColList",conditionColList);//保存查询实例
		result.put("showTotal", showTotal); //展示方式
		result.put("showType", showType); //展示方式
	    result.put("groupColumn",groupColumn);//保存查询实例
		result.put("resultColList",resultColList);//保存查询实例
		result.put("chartDataList",chartDataList);//保存查询实例
		result.put("chartSave",chartSave);//保存查询实例
		result.put("conMap", conMap); //查询参数列表
		result.put("yGroups", yGroups); //统计字段名称列表
		result.put("yGroupNames", yGroupNames); //统计字段显示名称列表
		result.put("yStats", yStats); //统计字段Id列表
		return result;
	}

	/**
	 * 进入查询定义界面，包含查询新增定义及查询定义修改
	 * @param themeId 主题主键
	 * @param saveId 当前自定义保存主键
	 * @param filterType
	 * @param saveName
	 * @param catalog
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.query.statchart.chart_define")
	public Map<String,Object> toDefine(String themeId,String saveId,String filterType,String saveName,String catalog) 
		throws Exception {
		//用于保存返回结果
		Map<String,Object> result = new HashMap<String, Object>();
		String chartRefName = null;
		ChartSave chartSave = new ChartSave();
		
		//修改已经定义的查询配置
		if(StringUtils.isNotBlank(saveId)){
			chartSave = statChartService.queryChartSaveById(saveId);
		    themeId = chartSave.getThemeId();
		    //获取钻取名称
			result.put("chartSave", chartSave);
			if("1".equals(chartSave.getDrillType())){//钻取查询
				chartRefName = statChartService.querySaveById(chartSave.getDrillSaveId()).getName();
			}else if("2".equals(chartSave.getDrillType())){//钻取统计
				chartRefName = statChartService.queryChartSaveById(chartSave.getDrillSaveId()).getName();
			}
		}
		//查询主题相关
		result.put("filterType", filterType);
		result.put("saveName", saveName);
		result.put("catalog", catalog);
		
		List<QueryColumn> columnList =statChartService.queryColumnList(themeId);
		//将已经定义好的查询条件放入Map（配置项主键，配置项主键）
        Map<String,String> conMap = new HashMap<String,String>();
    	if(chartSave!=null && StringUtils.isNotBlank(chartSave.getConCols())){
    		String[] refStrings = chartSave.getConCols().split(",");
    		for(String refString : refStrings) {
    			conMap.put(refString, refString);
    		}
    	}
    	//可选条件
    	List<QueryColumn> unselectedCons = new ArrayList<QueryColumn>();
    	//已选条件
    	List<QueryColumn> selectedCons = new ArrayList<QueryColumn>();
    	for (QueryColumn column : columnList) {
    		if ((column.getColumnType().equals("1") || column.getColumnType().equals("2"))&& !conMap.containsKey(column.getColId()))  {
    			unselectedCons.add(column);
    		} else if (conMap.containsKey(column.getColId())) {
    			selectedCons.add(column);
    		}
    	}
    	//查询条件和查询结果
        result.put("selectedCons", selectedCons);
        result.put("unselectedCons", unselectedCons);
        
      //统计列设置
    	String xGroup = DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,chartSave.getXgroup());
    	Map<String,String> sortMap = new HashMap<String,String>();
    	Map<String,String> syShowMap = new HashMap<String,String>();
    	Map<String,String> vyShowMap = new HashMap<String,String>();
    	String[] yAxisNames = null;
    	String[] yStats = null;
    	String[] sorts = {};
    	String[] syshows = {};
    	String[] vyshows = {};
    	if(StringUtils.isNotBlank(saveId)){
    		yAxisNames = chartSave.getYaxisNames().split(",");
    		yStats = chartSave.getYstat().split(",");
    		if(chartSave.getSortCols() !=null){
    			sorts = chartSave.getSortCols().split(",");
    		}
    		if(chartSave.getSyGroups() !=null){
    			syshows = chartSave.getSyGroups().split(",");
    		}
    		if(chartSave.getVyGroups() !=null){
    			vyshows = chartSave.getVyGroups().split(",");
    		}
    		for(int i=0;i<yStats.length;i++){
    			String ystat = DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,yStats[i]);
    			for(int j=0;j<sorts.length;j++){
    				if(sorts[j].startsWith(ystat)) sortMap.put(yStats[i],sorts[j+1]);
    				if(sorts[j].startsWith(xGroup)) sortMap.put(chartSave.getXgroup(),sorts[j+1]);
    			}
    			for(int j=0;j<syshows.length;j++){
    				if(syshows[j].startsWith(ystat)) syShowMap.put(yStats[i],yStats[i]);
    			}
    			for(int j=0;j<vyshows.length;j++){
    				if(vyshows[j].startsWith(ystat)) vyShowMap.put(yStats[i],yStats[i]);
    			}
    			i++;
    		}
    	}
		result.put("sortMap", sortMap);
		result.put("syShowMap", syShowMap);
		result.put("vyShowMap", vyShowMap);
		result.put("yAxisNames", yAxisNames);
		result.put("yStats", yStats);
//		result.put("sorts", sorts);
		result.put("syshows", syshows);
		result.put("vyshows", vyshows);
    	
		result.put("themeId", themeId);
		result.put("selectRefName", chartRefName);
		result.put("columnList", columnList);
		return result;
	}
	

	/**
	 * 删除已定义图表
	 */
	@At
	@Ok("forward:/query/statChart/toTheme?themeId=${obj.themeId}")
	public Map<String,Object> delChart(String themeId,String saveId) throws Exception {
		statChartService.removeChartSave(saveId);
		Map<String,Object> result = new HashMap<String, Object>();
        result.put("themeId", themeId);
        return result;
	}
	/**
	 * 保存图标定义
	 * @param colConSelect 条件字段
	 * @param yGroups 结果字段
	 * @param yStats 排序字段
	 * @param vyShows 排序字段
	 * @param yAxisNames y轴名称
	 * @param saveId  已定义查询ID
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("forward:/query/statChart/toTheme?themeId=${obj.themeId}")
	public Map<String,Object> saveChart(String [] conSelect,String [] yGroups,String [] yStats,String [] ySorts,String [] vyShows,
			String [] yAxisNames,String saveId,String themeId,String chartCatalog,String name,String saveDesc,String xAxisName,
			String xGroup,String renderer,String xSort,String selectRefId,String refType) 
		throws Exception {
		ChartSave chartSave = null;
		//判断是新增还是更新
		if(StringUtils.isBlank(saveId)){
			chartSave = new ChartSave();
		}else{
			 chartSave = statChartService.queryChartSaveById(saveId);
		}
		//设置定制值
		chartSave.setDrillType("0");
		chartSave.setThemeId(StringUtils.trimToEmpty(themeId));
		chartSave.setCatalogName(StringUtils.trimToEmpty(chartCatalog));
		chartSave.setName(StringUtils.trimToEmpty(name));
		chartSave.setSaveDesc(StringUtils.trimToEmpty(saveDesc));
		chartSave.setXaxisName(StringUtils.trimToEmpty(xAxisName));
		chartSave.setXgroup(StringUtils.trimToEmpty(xGroup));
		chartSave.setRenderer(StringUtils.trimToEmpty(renderer));
		//排序字段
		String sortString = null;
		if(StringUtils.isNotBlank(xSort))
			sortString=DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,chartSave.getXgroup())+","+StringUtils.trimToEmpty(xSort);
		//统计字段名称
		String yGroupsString = null;
		//统计字段及类型
		String yStatString = null;
		//y主轴显示字段
		String syGroupString = null;
		//y副轴显示字段
		String vyGroupString = null;
		//设置统计字段
		//统计列个数
		int ygroupCount = 0;
	    for(int i=0;i<yGroups.length;i++){
	    	if(StringUtils.isNotBlank(yGroups[i])){
	    		ygroupCount++;
	    		String colString =DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,yGroups[i])+"_"+yStats[i];
	    		if(yStatString ==null){
	    			yStatString=yGroups[i]+","+yStats[i];
	    			yGroupsString = colString;
	    		}else{
	    			yStatString +=","+yGroups[i]+","+yStats[i];
	    			yGroupsString += ","+colString;
	    		}
	    		if("1".equals(vyShows[i])){
	    			if(StringUtils.isNotBlank(syGroupString))
	    			syGroupString +=","+colString;
	    			else syGroupString =colString;
	    		}else if("2".equals(vyShows[i])){
	    			if(StringUtils.isNotBlank(vyGroupString))
	    				vyGroupString +=","+colString;
	    			else vyGroupString =colString;
	    		}
	    		if(StringUtils.isNotBlank(ySorts[i])){
	    			if(StringUtils.isNotBlank(sortString))
	    				sortString +=","+colString+","+ySorts[i];
		    			else sortString =colString+","+ySorts[i];
	    		}
	    	}
	    }
	    
	    chartSave.setYgroups(StringUtils.trimToEmpty(yGroupsString));
	    chartSave.setYstat(StringUtils.trimToEmpty(yStatString));
	    chartSave.setVyGroups(StringUtils.trimToEmpty(vyGroupString));
	    chartSave.setSyGroups(StringUtils.trimToEmpty(syGroupString));
	    chartSave.setSortCols(StringUtils.trimToEmpty(sortString));

		chartSave.setYaxisNames(ArrayUtil.strPartArray2StrSplit(yAxisNames, "," , ygroupCount));
		chartSave.setConCols(StringUtils.trimToEmpty(ArrayUtil.strArray2StrSplit(conSelect, ",")));
		//设置钻取字段
		chartSave.setDrillSaveId(StringUtils.trimToEmpty(selectRefId));
		if(StringUtils.isNotBlank(refType))
		chartSave.setDrillType(refType);
		
		//判断是新增还是更新
		if(StringUtils.isBlank(saveId)){
		chartSave.setQueryCount(new Integer(1));
		//当前时间生成一个themeId
		Date date = new Date();		
		chartSave.setSaveId((date.getTime())+"");
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String saveDate = formatter.format(date);		
		chartSave.setSaveDate(DateUtils.string2Timestamp(saveDate, DateUtils.FORMAT_DATE));
		    statChartService.addQuerySave(chartSave);
		}else{
			statChartService.updateQuerySave(chartSave);
		}
		Map<String,Object> result = new HashMap<String, Object>();
        result.put("themeId", chartSave.getThemeId());
        return result;
	}
	

	/**
	 * 进入可钻取已定义查询界面
	 */
	@At
    @Ok("jsp:jsp.query.statchart.chart_ref")
	public Map<String,Object> toChartRef(String selectRefId,@Param("vyShows") String refType) throws Exception {
		//用于保存返回结果
		Map<String,Object> result = new HashMap<String, Object>();
		List<?> saveList = null;
		if(Strings.isBlank(refType)){
			refType= "2";
		}
		if("1".equals(refType))
		 saveList = statChartService.querySaveList("");
		else
		 saveList = statChartService.charSaveList("");
	
		result.put("selectRefId", selectRefId);
		result.put("refType", refType);
		result.put("resultList", saveList);
		return result;
	}
	
	
	/**
	 * 根据图标类型生成图标数据
	 * @param chartDataList 显示数据列表
	 * @param groupColumn 统计列对象
	 * @param chartSave 保存统计对象
	 * @return
	 */
	private String genChartData(List chartDataList,QueryColumn groupColumn,ChartSave chartSave,List resultColList,Map conMap){
		//模板传值Map
		Map velocityMap =  new HashMap();
		Map linkMap =  new HashMap();
		//如果横轴是字典，转化横轴为字典值
		if("2".equals(groupColumn.getEditType()) || "4".equals(groupColumn.getEditType())){	
		    for(int i=0;i<chartDataList.size();i++){
			    Map dataMap = (Map)chartDataList.get(i);
				String xGroup = DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,chartSave.getXgroup());
				String chartLable =null;
				if("2".equals(groupColumn.getEditType()) && dataMap.get(xGroup)!=null){
				 chartLable = DicDataUtils.getInstance().getDicItemName(groupColumn.getDicId().intValue(),new Integer(((BigDecimal)dataMap.get(xGroup)).intValue()));
				}else{
				 chartLable = DicDataUtils.getInstance().getDicItemName(groupColumn.getDicId().intValue(),dataMap.get(xGroup));
				}
				//**********设置钻取连接**********
				if(!"0".equals(chartSave.getDrillType())){
					if("1".equals(chartSave.getDrillType()))
						linkMap.put(chartLable, "detailQuery.do?method=queryResult&saveId="+chartSave.getDrillSaveId()+"&"+xGroup+"="+dataMap.get(xGroup)+genDrillDownParameter(conMap));
					else
						linkMap.put(chartLable, "statChart.do?method=showChart&saveId="+chartSave.getDrillSaveId()+"&"+xGroup+"="+dataMap.get(xGroup)+genDrillDownParameter(conMap));
				}
				//**********设置钻取连接**********
				dataMap.put(xGroup, chartLable);
			}
		}else if(!"0".equals(chartSave.getDrillType())){
			//**********设置钻取连接**********
			  for(int i=0;i<chartDataList.size();i++){
				    Map dataMap = (Map)chartDataList.get(i);
					String xGroup = DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,chartSave.getXgroup());
					    if("1".equals(chartSave.getDrillType()))
							linkMap.put(dataMap.get(xGroup), "query/detailQuery/queryResult?saveId="+chartSave.getDrillSaveId()+"&"+xGroup+"="+dataMap.get(xGroup)+genDrillDownParameter(conMap));
						else
							linkMap.put(dataMap.get(xGroup), "query/statChart/showChart?saveId="+chartSave.getDrillSaveId()+"&"+xGroup+"="+dataMap.get(xGroup)+genDrillDownParameter(conMap));
					
					//**********设置钻取连接**********
				}
		}
		//**********根据不同图表传值*************
		//统计字段名称数组
		String[] yGroupsArray = chartSave.getSyGroups().split(",");
		//统计字段显示名称数组
		String[] yAxisNamesArray = chartSave.getYaxisNames().split(",");
		// 长度为1，为Single Series Charts
		//Y轴主轴名称
		velocityMap.put("yAxisName", StringUtils.trimToEmpty(chartSave.getYaxisNames()));
		if(yGroupsArray.length==1 && StringUtils.isBlank(chartSave.getVyGroups())){
		}else{//Multi-Series Charts
			Map yAxisNamesMap =  new HashMap();
			//Y轴统计组的名称及颜色
			for(int i=0;i<yGroupsArray.length;i++){
				yAxisNamesMap.put(yGroupsArray[i], yAxisNamesArray[i]);
			}
			//*************存在副轴情况 start***************
			if(StringUtils.isNotBlank(chartSave.getVyGroups())){
				String[] vyGroupsArray = chartSave.getVyGroups().split(",");
				Map vyAxisNamesMap =  new HashMap();
				for(int i=0;i<vyGroupsArray.length;i++){
					vyAxisNamesMap.put(vyGroupsArray[i], yAxisNamesArray[yAxisNamesArray.length-1-i]);
				}
				//副轴显示组的名称
				velocityMap.put("vyAxisNamesMap", vyAxisNamesMap);
				//统计字段
				velocityMap.put("vyGroupsArray", vyGroupsArray);
			}
			//*************存在副轴情况  end***************
			velocityMap.put("yAxisNameMap", yAxisNamesMap);//主轴显示组的名称
		}
		//**********根据不同图表传值*************
		//**********共同传值*************
		//x轴名称
		velocityMap.put("xAxisName", StringUtils.trimToEmpty(chartSave.getXaxisName()));
		//chart保存信息
		velocityMap.put("chartName", StringUtils.trimToEmpty(chartSave.getName()));
		//钻取类型，分为查询钻取，图表钻取
		velocityMap.put("drillType", StringUtils.trimToEmpty(chartSave.getDrillType()));
		//统计字段
		velocityMap.put("yGroupsArray", yGroupsArray);
		//X轴group字段名称
		velocityMap.put("xGroup", DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,chartSave.getXgroup()));
		//结果data
		velocityMap.put("chartDataList", chartDataList);
		//设置钻取连接
		if(!"0".equals(chartSave.getDrillType())){
			velocityMap.put("linkMap", linkMap);
		}
		//**********共同传值*************
		
		//转化成的XML数据结果值
		String xmlString = "";
		try {
			xmlString = VelocityUtils.merge(velocityMap, chartSave.getCatalogName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlString;
		
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
			 Map<String,Object> result = (Map<String,Object>)addMethod.invoke(invokeTester, new Object[] {request}); 
			 return (Map<String,Object>)result;
		} catch (Exception e) {
			e.printStackTrace();
		} 
       return null;

   }

	/**
	 * 构建统计语句
	 * @param request
	 * @param chartSave 保存统计对象
	 * @param conditionColList  查询条件字段列表
	 * @param conMap 条件Map
	 * @return QueryCon 查询条件
	 */
	private String genQuerySql(HttpServletRequest request,ChartSave chartSave,List conditionColList,Map conMap,Map queryRenderer){
		QueryTheme queryTheme = statChartService.getQueryThemeById(chartSave.getThemeId()); 
        Map multiMap = new HashMap();
		if(StringUtils.isNotBlank(chartSave.getMultiCols())){
        	String[] refStrings = chartSave.getMultiCols().split(",");
        	for(int i=0;i<refStrings.length;i++) multiMap.put(refStrings[i],refStrings[i]);
        }
		//***********设置各拼接条件****
		//查询where拼接条件,保存条件信息到conMap
		String conString = genQuerywhereClause(request,conditionColList,conMap,multiMap);
		//查询select拼接条件
		String statString = genQueryStatClause(chartSave);
		//查询group by 拼接条件
		String groupString = genQueryGroupClause(chartSave);
		//查询SORT拼接条件
		String sortString = genQuerySortClause(chartSave);
		//***********设置各拼接条件**********
		//***********设置各拼接条件**********
		 if(queryRenderer !=null && queryRenderer.size()>0){
	        	conString +=queryRenderer.get(BaseRenderer.WHERE_SQL);
	        }
		//***********查询语句拼接**********
		//拼接查询语句
		StringBuffer querySql = new StringBuffer();
		querySql.append("SELECT ").append(statString).append(" FROM ").append(queryTheme.getViewName()).append(" WHERE 1=1 ").append(conString).append(groupString);
		if(StringUtils.isNotBlank(sortString))
		querySql.append(" ORDER BY ").append(sortString);
		return querySql.toString();
		
	}

	/**
	 * 将排序定义转为具体查询语法
	 * @param chartSave 保存统计对象
	 * @return 排序定义查询语法
	 */
	private String genQuerySortClause(ChartSave chartSave){
		if(StringUtils.isBlank(chartSave.getSortCols())) return "";
		String[] tempColIds = chartSave.getSortCols().split(",");
		String result=null;
		for(int i=0;i<tempColIds.length;i++){	
			if(result == null)
			result = tempColIds[i]+" "+tempColIds[i+1];
		else
			result += ","+tempColIds[i]+" "+tempColIds[i+1];
			i++;
		}
		return result;
		
	}

	/**
	 * 将结果定义转为具体查询语法
	 * @param chartSave 保存统计对象
	 * @return 结果定义查询语法
	 */
	private String genQueryGroupClause(ChartSave chartSave){
		return " GROUP BY "+DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,chartSave.getXgroup());
		
	}

	/**
	 * 将结果定义转为具体查询语法
	 * @param chartSave 保存统计对象
	 * @return 结果定义查询语法
	 */
	private String genQueryStatClause(ChartSave chartSave){
		if(StringUtils.isBlank(chartSave.getYstat())) return "";
		String[] tempColIds = chartSave.getYstat().split(",");
		String result=DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,chartSave.getXgroup());
		for(int i=0;i<tempColIds.length;i++){
			String colName = DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,tempColIds[i]);
			result += ","+tempColIds[i+1]+"("+colName+") AS "+colName+"_"+tempColIds[i+1];
			i++;
		}
		return result;
		
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
	 * @param request 
	 * @param conditionColList 查询条件字段列表
	 * @param conMap 查询值
	 * @return 条件定义查询语法
	 */
	private String genQuerywhereClause(HttpServletRequest request,List conditionColList,Map conMap, Map multiMap){
		StringBuffer conBuffer = new StringBuffer();
		for(int i=0;i<conditionColList.size();i++){	
			QueryColumn queryColumn = (QueryColumn)conditionColList.get(i);	
			 //如果是多列，不进行装配，在renderer中装配
            if(multiMap.containsKey(queryColumn.getColId())){
            	continue;
            }
			if("3".equals(queryColumn.getEditType())){//日期类型
				String startDate = request.getParameter(queryColumn.getNameLetter()+"staDATE");
				String endtDate = request.getParameter(queryColumn.getNameLetter()+"endDATE");
				if( StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endtDate)){
					conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" between to_date('").append(request.getParameter(queryColumn.getNameLetter()+"staDATE")).append(" 00:00:00','yyyy-mm-dd hh24:mi:ss') and to_date('");
					conBuffer.append(request.getParameter(queryColumn.getNameLetter()+"endDATE")).append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
					conMap.put(queryColumn.getNameLetter()+"endDATE", request.getParameter(queryColumn.getNameLetter()+"endDATE"));
					conMap.put(queryColumn.getNameLetter()+"staDATE", request.getParameter(queryColumn.getNameLetter()+"staDATE"));
				}else if(StringUtils.isBlank(startDate) && StringUtils.isNotBlank(endtDate)){
					conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" < to_date('");
					conBuffer.append(request.getParameter(queryColumn.getNameLetter()+"endDATE")).append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
					conMap.put(queryColumn.getNameLetter()+"endDATE", request.getParameter(queryColumn.getNameLetter()+"endDATE"));
				}else if(StringUtils.isNotBlank(startDate) && StringUtils.isBlank(endtDate)){
					conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" > to_date('").append(request.getParameter(queryColumn.getNameLetter()+"staDATE")).append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
					conMap.put(queryColumn.getNameLetter()+"staDATE", request.getParameter(queryColumn.getNameLetter()+"staDATE"));
				}
			}else if("1".equals(queryColumn.getEditType()) && StringUtils.isNotBlank(request.getParameter(queryColumn.getNameLetter()))){//文本类型
				conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" like '%").append(request.getParameter(queryColumn.getNameLetter())).append("%' ");
				conMap.put(queryColumn.getNameLetter(), request.getParameter(queryColumn.getNameLetter()));
			}else if("4".equals(queryColumn.getEditType()) && StringUtils.isNotBlank(request.getParameter(queryColumn.getNameLetter())) ){//key为文本类型的select类型
				conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" = '").append(request.getParameter(queryColumn.getNameLetter())).append("' ");
				conMap.put(queryColumn.getNameLetter(), request.getParameter(queryColumn.getNameLetter()));
			}else if(("2".equals(queryColumn.getEditType()) || "5".equals(queryColumn.getEditType())) && StringUtils.isNotBlank(request.getParameter(queryColumn.getNameLetter()))){//key为数字类型的select类型及数值类型
				conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" = ").append(request.getParameter(queryColumn.getNameLetter())).append(" ");
				conMap.put(queryColumn.getNameLetter(), request.getParameter(queryColumn.getNameLetter()));
			}
		}
		return conBuffer.toString();
		
	}
	
		   
}
