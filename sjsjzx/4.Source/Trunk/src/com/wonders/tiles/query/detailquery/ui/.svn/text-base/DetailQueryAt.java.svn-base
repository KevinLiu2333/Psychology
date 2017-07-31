package com.wonders.tiles.query.detailquery.ui;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.query.detailquery.DetailQueryConstants;
import com.wonders.tiles.query.detailquery.entity.QueryColumn;
import com.wonders.tiles.query.detailquery.entity.QueryCon;
import com.wonders.tiles.query.detailquery.entity.QuerySave;
import com.wonders.tiles.query.detailquery.entity.QueryTheme;
import com.wonders.tiles.query.detailquery.service.DetailQueryService;
import com.wonders.tiles.query.statrenderer.BaseRenderer;
import com.wonders.util.ArrayUtil;
import com.wonders.util.DateUtils;

/**
 * 详细查询Action
 * 
 * 
 */
@At("/query/detailQuery")
@IocBean
public class DetailQueryAt {
	
	@Inject
    private DetailQueryService detailQueryService;
	
    /**
     *进入主题页面
     */
    @At
	@Ok("jsp:jsp.query.detailquery.query_theme")
    public Map<String,Object> toTheme(String catalog,String saveName,String themeId) 
    		throws Exception {
    	//用于保存返回结果
		Map<String,Object> result = new HashMap<String, Object>();
		//初始化分类信息
		if(StringUtils.isNotBlank(themeId) && StringUtils.isBlank(catalog)){
			QueryTheme queryTheme = detailQueryService.getQueryThemeById(themeId);
			if(queryTheme != null){
				catalog = queryTheme.getCatalog();
			}
		}
		//获取所选分类下所有主题列表
		List<QueryTheme> themeList = new ArrayList<QueryTheme>();
		List<String> themeIdList = new ArrayList<String>();
		String [] themeIds = null;
		themeList = detailQueryService.queryThemeListByCatalog(catalog);
		if(themeList !=null && themeList.size()>0){
			for(int i=0;i<themeList.size();i++){
				themeIdList.add(themeList.get(i).getThemeId());
			}
		themeIds = themeIdList.toArray(new String[themeIdList.size()]);
		}
		//查询条件无名称信息，则显示分类下所有主题
		List<QuerySave> saveList = detailQueryService.querySave(saveName,themeIds);
		//查询条件有名称信息，则只显示有匹配项的主题
		if(StringUtils.isNotBlank(saveName) ){
			themeIdList.clear();
			themeList.clear();
			if(saveList !=null && saveList.size()>0){
				for(int i=0;i<saveList.size();i++){
					themeIdList.add(saveList.get(i).getThemeId());
				}
				themeList = detailQueryService.queryThemeList(themeIdList.toArray(new String[themeIdList.size()]));
				saveList = detailQueryService.querySaveListByTheme(themeIdList.toArray(new String[themeIdList.size()]));
			}
		}
		//所有主题
		List<QueryTheme> catalogList = detailQueryService.queryCatalogList();
		result.put("catalogList", catalogList);
		result.put("queryThemeList", themeList);
		result.put("saveList", saveList);
		result.put("saveName", saveName);
		result.put("catalog", catalog);
        return result;
    }

    /**
     * 进入查询页面，包括查询条件及查询结果
     */
    @SuppressWarnings("unchecked")
	@At
    public View queryResult(HttpServletRequest req,String saveId,String queryFlag,String exportFlag,String guideFlag,Pager pager) throws Exception {
    	Map<String,Object> result = new HashMap<String, Object>();
    	// 条件Map
        Map<String,String> conMap = new HashMap<String,String>();
        // 查询条件字段列表
        List<QueryColumn> conditionColList = new ArrayList<QueryColumn>();
        // 当前自定义保存信息
        QuerySave querySave = detailQueryService.querySaveById(saveId);

        //renderer设置
        Map<String,Object> queryRenderer = null;
        if(querySave != null ){
        	String [] conCols = StringUtils.split(querySave.getConCols(), ",");
            conditionColList = detailQueryService.queryColumnListByColId(conCols);
            if (StringUtils.isNotBlank(querySave.getRenderer())) {
            	queryRenderer = genRenderString(req,querySave.getRenderer());
                result.put("queryRenderer", queryRenderer);// 保存查询实例
            }
        }
        result.put("querySave", querySave);// 保存查询实例
        result.put("queryFlag", queryFlag);// 查询标识
        result.put("guideFlag", guideFlag);//导航条标识
        result.put("conditionColList", conditionColList);// 标题列表
        if (querySave != null && StringUtils.isBlank(queryFlag)) {// 查询结果
            // 设置查询热度
            detailQueryService.addQueryCount(saveId, querySave.getQueryCount().intValue() + 1);
            // 拼接查询条件
            List<QueryColumn> pkList = detailQueryService.queryPkByThemeId(querySave.getThemeId());
            QueryCon queryCon = genQueryPageResult(req, querySave, conditionColList, conMap, pkList,queryRenderer);
            // 结果展示字段列表
            List<QueryColumn> resultColList = new ArrayList<QueryColumn>();
            resultColList = detailQueryService.queryColumnListByColId(StringUtils.split(querySave.getRsCols(), ","));

            result.put("pkList", pkList);// 返回主键列表
            result.put("resultColList", resultColList);//  返回结果展示字段列表
            // excel导出结果
            if (StringUtils.isNotBlank(exportFlag)) {
                List<Object> resultList = detailQueryService.queryAllResultList(queryCon.getQuerySql());
                result.put("resultList", resultList);
                return new ViewWrapper(new JspView("jsp.query.detailquery.query_resultSave"),result);
            }
            // 结果详细信息
            if ("1".equals(querySave.getShowDetail())) {
            	result.put("conMap", conMap); //查询参数列表
                List resultList = detailQueryService.queryAllResultList(queryCon.getQuerySql());
                result.put("resultList", resultList);
                return new ViewWrapper(new JspView("jsp.query.detailquery.query_detail"),result);
            }
            // 查询结果列表
			int rsListCount = detailQueryService.queryCount(queryCon.getCountSql());
			List rsList = detailQueryService.queryFineResultList(queryCon.getQuerySql(),pager);
            result.put("rsList", rsList);
            result.put("rsListCount", rsListCount);
            pager.setRecordCount(rsListCount);
            result.put("pager", pager);
            // 钻取列表
            if (StringUtils.isNotBlank(querySave.getDrillSaveIds())) {
            	List<QuerySave> refColList = detailQueryService.querySaveByRefId(StringUtils.split(querySave.getDrillSaveIds(),','));
                result.put("refColList", refColList);
                	
            }
        }
        
        result.put("conMap", conMap); // 查询参数列表
        
        return new ViewWrapper(new JspView("jsp.query.detailquery.query_result"),result);
    }

    /**
     * 进入查询定义界面，包含查询新增定义及查询定义修改
     */
    @At
    @Ok("jsp:jsp.query.detailquery.query_define")
    public Map<String,Object> toDefine(String themeId,String saveId,String filterType,String saveName,String catalog) throws Exception {
    	Map<String,Object> result = new HashMap<String, Object>();
    	QuerySave querySave = new QuerySave();
    	List<QuerySave> saveList = new ArrayList<QuerySave>();
        // 修改已经定义的查询配置
        if (StringUtils.isNotBlank(saveId)) {
            querySave = detailQueryService.querySaveById(saveId);
            // 获取钻取已定义查询列表
            if (StringUtils.isNotBlank(querySave.getDrillSaveIds())) {
                saveList = detailQueryService.querySaveByRefId(StringUtils.split(querySave.getDrillSaveIds(),','));
                result.put("saveList", saveList);
            }
            themeId = querySave.getThemeId();
            result.put("querySave", querySave);
        }
        List<QueryColumn> columnList = detailQueryService.queryColumnList(themeId);
        
        //将已经定义好的查询条件放入Map（配置项主键，配置项主键）
        Map<String,String> conMap = new HashMap<String,String>();
    	if(StringUtils.isNotBlank(querySave.getConCols())){
    		String[] refStrings = querySave.getConCols().split(",");
    		for(String refString : refStrings) {
    			conMap.put(refString, refString);
    		}
    	}
    	
    	//将已经定义好的结果条件放入Map（配置项主键，配置项名称），便于排序设置
    	Map<String,String> rsMap = new HashMap<String,String>();
    	if(StringUtils.isNotBlank(querySave.getRsCols())){
    		String[] rsStrings = querySave.getRsCols().split(",");
    		for(QueryColumn queryColumn : columnList) {
    		    for(String rsString : rsStrings) {
    				if(rsString.equals(queryColumn.getColId())) {
    					rsMap.put(rsString, queryColumn.getName());
    				}
    			}
    		}	
    	}
    	
    	String selectRefName = "";
    	for(QuerySave tempQuerySave : saveList){
    		if("".equals(selectRefName)) {
    			selectRefName = tempQuerySave.getName();
    		} else {
    			selectRefName+=","+ tempQuerySave.getName();
    		}
        }
    	
    	//可选条件
    	List<QueryColumn> unselectedCons = new ArrayList<QueryColumn>();
    	//已选条件
    	List<QueryColumn> selectedCons = new ArrayList<QueryColumn>();
    	
    	//可选结果
    	List<QueryColumn> unselectedResults = new ArrayList<QueryColumn>();
    	//已选结果
    	List<QueryColumn> selectedResults = new ArrayList<QueryColumn>();
    	
    	for (QueryColumn column : columnList) {
    		if ((column.getColumnType().equals("1") || column.getColumnType().equals("2"))&& !conMap.containsKey(column.getColId()))  {
    			unselectedCons.add(column);
    		} else if (conMap.containsKey(column.getColId())) {
    			selectedCons.add(column);
    		}
    		if ((column.getColumnType().equals("1") || column.getColumnType().equals("3")) && !rsMap.containsKey(column.getColId())){
    			unselectedResults.add(column);
    		} else if (rsMap.containsKey(column.getColId())) {
    			selectedResults.add(column);
    		}
    	}
    	
    	//保存排序信息的map(key:字段ID,value:升序/降序)
    	Map<String,String> orderMap = new TreeMap<String, String>();
    	
		if(StringUtils.isNotBlank(querySave.getSortCols())){
			String[] sortStrings = querySave.getSortCols().split(",");
			for(int i=0;i<sortStrings.length;i+=2){
				orderMap.put(sortStrings[i], sortStrings[i+1]);
			}
		}

		//查询主题相关
        result.put("filterType", filterType);
        result.put("saveName", saveName);
        result.put("catalog", catalog);
		
        result.put("themeId", themeId);
        result.put("columnList", columnList);
        
        //查询条件和查询结果
        result.put("selectRefName", selectRefName);
        result.put("selectedCons", selectedCons);
        result.put("unselectedCons", unselectedCons);
        result.put("selectedResults", selectedResults);
        result.put("unselectedResults", unselectedResults);
        result.put("orderMap", orderMap);
        result.put("rsMap", rsMap);
        return result;
    }

    /**
     * 进入可钻取已定义查询界面
     */
    @At
    @Ok("jsp:jsp.query.detailquery.query_ref")
    public Map<String,Object> toQueryRef(String selectRefIds) throws Exception {
    	Map<String,Object> result = new HashMap<String, Object>();
        // 获取结果字段
        // String [] colRsSelect = request.getParameterValues("resultSelect");
        List<QuerySave> saveList = detailQueryService.querySaveList();
        /*********************************************************************************************************************************************
         * List resultList = new ArrayList(); //过滤可钻取的查询列表，条件：只有B查询结果字段完全包含A查询定义的条件字段，B查询方可钻取A查询 if(saveList !=null){ for(int i=0;i<saveList.size();i++){
         * QuerySave querySave =(QuerySave)saveList.get(i); if(ArrayUtil.strArrayContainsArray(colRsSelect, querySave.getConCols().split(",")))
         * resultList.add(querySave); } }
         */
		Map<String, String> refMap = new HashMap<String, String>();
		if (StringUtils.isNotBlank(selectRefIds)) {
			String[] refStrings = selectRefIds.split(",");
			for (int i = 0; i < refStrings.length; i++)
				refMap.put(refStrings[i], refStrings[i]);
		}
        result.put("selectRefIds", selectRefIds);
        result.put("resultList", saveList);
        result.put("refMap", refMap);
        return result;
    }

    /**
     * 删除已定义查询
     */
    @At
    @Ok("forward:/query/detailquery/toTheme?themeId=${obj.themeId}")
    public Map<String,Object> delQuery(String themeId,String saveId) throws Exception {
        detailQueryService.removeQuerySave(saveId);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("themeId", themeId);
        return result;
    }

    /**
     * 保存自定义查询
     */
    @At
    @Ok("forward:/query/detailquery/toTheme?themeId=${obj.themeId}")
    public Map<String,Object> saveQuery(String [] conSelect,String [] resultSelect,String [] sortSelect,String saveId,
    		String name,String refName,String saveDesc,String showDetail,String selectRefIds,String themeId,String renderer) throws Exception {
        QuerySave query = null;
        // 判断是新增还是更新
        if (StringUtils.isBlank(saveId)) {
            query = new QuerySave();
        } else {
            query = detailQueryService.querySaveById(saveId);
        }

        // 设置定制值
        query.setName(StringUtils.trimToEmpty(name));
        query.setRefName(StringUtils.trimToEmpty(refName));
        query.setSaveDesc(StringUtils.trimToEmpty(saveDesc));
        query.setShowDetail(StringUtils.trimToEmpty(showDetail));

        // UserInfo userInfo = (UserInfo)request.getSession().getAttribute(CommonConstants.SESSION_USER_INFO);
        // String userId = String.valueOf(userInfo.getUserId());
        // query.setUserId(userId);

        query.setConCols(ArrayUtil.strArray2StrSplit(conSelect, ","));
        query.setRsCols(ArrayUtil.strArray2StrSplit(resultSelect, ","));
        query.setSortCols(ArrayUtil.strArray2StrSplit(sortSelect, ","));
        query.setDrillSaveIds(StringUtils.trimToEmpty(selectRefIds));
        query.setThemeId(StringUtils.trimToEmpty(themeId));
        query.setRenderer(StringUtils.trimToEmpty(renderer));
        // 判断是新增还是更新
        if (StringUtils.isBlank(saveId)) {
            query.setQueryCount(new Integer(1));
            // 当前时间生成一个themeId
            Date date = new Date();
            query.setSaveId((date.getTime()) + "");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String saveDate = formatter.format(date);
            query.setSaveDate(DateUtils.string2Timestamp(saveDate, DateUtils.FORMAT_DATE));
            detailQueryService.addQuerySave(query);
        } else {
            detailQueryService.updateQuerySave(query);
        }
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("themeId", themeId);
        return result;
    }
    
	/**
	 * 进入新建主题页面
	 */
	@At
	@Ok("jsp:jsp.query.detailquery.query_theme_create")
	public Map<String, Object> toQueryThemeCreate() {
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取所有数据表和视图的名称
		List<String> tableNameList = detailQueryService.getDbTableNameList();
		// 获取已经配置过主题的表或视图名称
		List<QueryTheme> queryThemeList = detailQueryService.queryThemeListByCatalog(null);
		List<String> themeTableList = new ArrayList<String>();
		for (QueryTheme queryTheme : queryThemeList) {
			themeTableList.add(queryTheme.getViewName());
		}
		tableNameList.removeAll(themeTableList);
		result.put("tableNameList", tableNameList);
		result.put("maxThemeId", detailQueryService.getMaxThemeId());
		return result;
	}

	/**
	 * 保存查询主题
	 */
	@At
	@Ok("forward:/query/detailQuery/toTheme?themeId=${obj.themeId}")
	public Map<String,Object> saveQueryTheme(@Param("..")QueryTheme queryTheme,@Param("isCreateColumn") String isCreateColumn) {
		Map<String,Object> result = new HashMap<String, Object>();
		queryTheme = detailQueryService.addQueryTheme(queryTheme);
		//自动生成QueryTheme表对应的中的列到queryColumn中
		if ("1".equals(isCreateColumn)) {
			detailQueryService.saveQueryColumns(queryTheme);
		}
        result.put("themeId", queryTheme.getThemeId());
        return result;
	}
	
	/**
	 * 进入向导页面
	 */
	@At
	@Ok("jsp:jsp.query.guide.guide")
	public Map<String, Object> toGuide() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<QueryTheme> themeList = detailQueryService.queryThemeListByCatalog(null);
		result.put("themeList", themeList);
		return result;
	}

    /**
     * 构建查询
     * 
     * @param request
     * @param querySave
     *            当前自定义保存信息
     * @param conditionColList
     *            查询条件字段列表
     * @param conMap
     *            条件Map
     * @return QueryCon 查询条件
     */
    private QueryCon genQueryPageResult(HttpServletRequest request, QuerySave querySave, List<QueryColumn> conditionColList,
            Map<String,String> conMap, List<QueryColumn> pkList,Map<String,Object> queryRenderer) {
        QueryTheme queryTheme = detailQueryService.getQueryThemeById(querySave.getThemeId());
        Map<String,String> multiMap = new HashMap<String,String>();
		if(StringUtils.isNotBlank(querySave.getMultiCols())){
        	String[] refStrings = querySave.getMultiCols().split(",");
        	for(int i=0;i<refStrings.length;i++) multiMap.put(refStrings[i],refStrings[i]);
		}
        // ***********设置各拼接条件****
        // 查询where拼接条件,保存条件信息到conMap
        String conString = genQueryOrderClause(request, conditionColList, conMap,multiMap);
        // 查询select拼接条件
        String resString = genQueryResultClause(querySave.getRsCols(), pkList);
        // 查询sort拼接条件
        String sortString = genQuerySortClause(querySave.getSortCols());
        // ***********设置各拼接条件**********

        if(queryRenderer !=null && queryRenderer.size()>0){
        	conString +=queryRenderer.get(BaseRenderer.WHERE_SQL);  //by fengjia
        }
        // ***********查询语句拼接**********
        // 拼接查询语句
        StringBuffer querySql = new StringBuffer();
        querySql.append("SELECT ").append(resString).append(" FROM ").append(queryTheme.getViewName()).append(
                " WHERE 1=1 ").append(conString);
        if (StringUtils.isNotBlank(sortString))
            querySql.append(" ORDER BY ").append(sortString);
        // 拼接查询记录数语句
        String countSql = "select count(*) as count from " + queryTheme.getViewName() + " where 1=1 " + conString;
        // ***********查询语句拼接**********

        // **********设置分页信息*************
        int currentPage = 1; // 当前页数
        if (StringUtils.isNotEmpty(request.getParameter("pageNum"))) {
            currentPage = Integer.parseInt(request.getParameter("pageNum"));
        }
        // 每页记录数
        int pageSize = 10;
        // **********设置分页信息*************

        QueryCon queryCon = new QueryCon();
        queryCon.setCurrentPage(currentPage);
        queryCon.setPageSize(pageSize);
        queryCon.setCountSql(countSql);
        queryCon.setQuerySql(querySql.toString());
        return queryCon;

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
			 return (Map<String,Object>)result;
		} catch (Exception e) {
			e.printStackTrace();
		} 
        return null;

    }
    /**
     * 将结果定义转为具体查询语法
     * 
     * @param colIds
     *            结果定义
     * @return 结果定义查询语法
     */
    private String genQueryResultClause(String colIds, List<QueryColumn> pkList) {
        if (StringUtils.isBlank(colIds))
            return "";
        String[] tempColIds = colIds.split(",");
        String result = null;
        // 设置查询PK
        if (pkList != null && pkList.size() > 0) {
            for (int i = 0; i < pkList.size(); i++) {
                QueryColumn queryColumn = (QueryColumn) pkList.get(i);
                boolean flag = false;// 否已经设置的PK为返回列 true 是 false不是
                for (int j = 0; j < tempColIds.length; j++) {// 查询是否已经设置的PK为返回列
//                	String a= queryColumn.getNameLetter();
//                	String b = DicDataUtils.getInstance().getDicData(DetailQueryConstants.DIC_QUERY_COL, tempColIds[i]);
                    if (queryColumn.getNameLetter().equals(DicDataUtils.getInstance().getDicData(DetailQueryConstants.DIC_QUERY_COL, tempColIds[j])))
                        flag = true;
                }
                if (!flag) {// 如果没有设置PK为返回列，进行设置
                    if (result == null)
                        result = queryColumn.getNameLetter();
                    else
                        result += "," + queryColumn.getNameLetter();
                }
            }
        }
        // 设置查询列
        for (int i = 0; i < tempColIds.length; i++) {
            if (result == null)
                result = DicDataUtils.getInstance().getDicData(DetailQueryConstants.DIC_QUERY_COL, tempColIds[i]);
            else
                result += ","
                        + DicDataUtils.getInstance().getDicData(DetailQueryConstants.DIC_QUERY_COL, tempColIds[i]);
        }
        return result;

    }

    /**
     * 将排序定义转为具体查询语法
     * 
     * @param colIds
     * @return 排序定义查询语法
     */
    private String genQuerySortClause(String colIds) {
        if (StringUtils.isBlank(colIds))
            return "";
        String[] tempColIds = colIds.split(",");
        String result = null;
        for (int i = 0; i < tempColIds.length; i++) {
            if (result == null)
                result = DicDataUtils.getInstance().getDicData(DetailQueryConstants.DIC_QUERY_COL, tempColIds[i])
                        + " " + tempColIds[i + 1];
            else
                result += ","
                        + DicDataUtils.getInstance().getDicData(DetailQueryConstants.DIC_QUERY_COL, tempColIds[i])
                        + " " + tempColIds[i + 1];
            i++;
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
    private String genQueryOrderClause(HttpServletRequest request, List<QueryColumn> conditionColList, Map<String,String> conMap, Map<String,String> multiMap) {
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