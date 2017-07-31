package com.wonders.wddc.suite.csrq.at;

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
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;
import org.nutz.service.EntityService;

import com.wonders.wddc.suite.csrq.constants.ConfigConstants;
import com.wonders.wddc.suite.csrq.entity.ColumnConfigBo;
import com.wonders.wddc.suite.csrq.entity.FormConfigBo;
import com.wonders.wddc.suite.csrq.entity.QueryConBo;
import com.wonders.wddc.suite.csrq.entity.QueryConfigBo;
import com.wonders.wddc.suite.csrq.renderer.BaseRenderer;
import com.wonders.wddc.suite.csrq.service.QueryService;
import com.wonders.wddc.suite.data.entity.TableConfigBo;
import com.wonders.wddc.tiles.adaptor.util.ConUtils;
import com.wonders.wddc.tiles.dic.DicDataUtils;
import com.wonders.wddc.tiles.jk.entity.User;
import com.wonders.wddc.tiles.tools.DateUtils;
import com.wonders.wddc.tiles.tools.UI;
import com.wonders.wddc.tiles.tools.VelocityUtils;
import com.wonders.wddc.tiles.tools.UI.StatusCode;


/**
 * 详细查询Action
 * 
 * 
 */
@At("/suite/config/query")
@IocBean(name = "queryconfig", fields = "dao")
public class QueryAct extends EntityService<QueryConfigBo> {
	
	@Inject
    private QueryService queryService;
	/**
	 * 进入查询列表页面
	 * @param roleDesc
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.query.list")
	public Map<String, Object> toQueryList(Criteria criteria, Pager pager) {
		criteria.getOrderBy().desc("saveDate");
		List<QueryConfigBo> list = dao().query(QueryConfigBo.class, criteria, pager);
		int count = dao().count(QueryConfigBo.class, criteria);
		pager.setRecordCount(count);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("pager", pager);
		return result;
	}
	

    /**
     * 进入查询定义界面，包含查询新增定义及查询定义修改
     */
    @At
    @Ok("jsp:wddc.suite.csrq.query.edit")
    public Map<String,Object> toEditQuery(Integer themeId,String saveId) throws Exception {
    	Map<String,Object> result = new HashMap<String, Object>();
    	QueryConfigBo querySave = new QueryConfigBo();
        // 修改已经定义的查询配置
        if (StringUtils.isNotBlank(saveId)) {
            querySave = queryService.querySaveById(saveId);
            themeId = querySave.getThemeId();
            result.put("querySave", querySave);
        }
        //根据表配置主键themeId获取字段配置信息（该表所具有的所有字段）
        List<ColumnConfigBo> columnList = queryService.queryColumnList(themeId);
        
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
    		for(ColumnConfigBo queryColumn : columnList) {
    		    for(String rsString : rsStrings) {
    				if(rsString.equals(""+queryColumn.getColId())) {
    					rsMap.put(rsString, queryColumn.getName());
    				}
    			}
    		}	
    	}
    	
    	//可选条件
    	List<ColumnConfigBo> unselectedCons = new ArrayList<ColumnConfigBo>();
    	//已选条件
    	List<ColumnConfigBo> selectedCons = new ArrayList<ColumnConfigBo>();
    	
    	//可选结果
    	List<ColumnConfigBo> unselectedResults = new ArrayList<ColumnConfigBo>();
    	//已选结果
    	List<ColumnConfigBo> selectedResults = new ArrayList<ColumnConfigBo>();
    	
    	for (ColumnConfigBo column : columnList) {
    		if ((column.getColumnType().equals("1") || column.getColumnType().equals("2"))&& !conMap.containsKey(""+column.getColId()))  {
    			unselectedCons.add(column);
    		} else if (conMap.containsKey(""+column.getColId())) {
    			selectedCons.add(column);
    		}
    		if ((column.getColumnType().equals("1") || column.getColumnType().equals("3")) && !rsMap.containsKey(""+column.getColId())){
    			unselectedResults.add(column);
    		} else if (rsMap.containsKey(""+column.getColId())) {
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
		//设置条件字段
		String[] whereCols = null;
        String[] whereJoins = null;
        String[] whereValues = null;
        if(!Strings.isBlank(querySave.getWhereCols())){
        	whereCols = querySave.getWhereCols().split(",");
        	whereJoins = querySave.getWhereJoins().split(",");
        	whereValues = querySave.getWhereValues().split(",");
            result.put("whereCols", whereCols);
            result.put("whereJoins", whereJoins);
            result.put("whereValues", whereValues);
        }

    	List<TableConfigBo> themeList = queryService.queryThemeListByCatalog(null);
		result.put("themeList", themeList);
		//查询主题相关
        result.put("themeId", themeId);
        result.put("columnList", columnList);
        
        //查询条件和查询结果
        result.put("selectedCons", selectedCons);
        result.put("unselectedCons", unselectedCons);
        result.put("selectedResults", selectedResults);
        result.put("unselectedResults", unselectedResults);
        result.put("orderMap", orderMap);
        result.put("rsMap", rsMap);
        return result;
    }
    /**
     * 删除列表配置信息
     */
    @At
	@Ok("redirect:/suite/config/query/toQueryList")
    public Map<String,Object> toDelQuery(String saveId) throws Exception {
        queryService.removeQuerySave(saveId);
		return null;
    }
    
    /**
     * 进入绑定表单页面
     */
    @At
	@Ok("jsp:wddc.suite.csrq.query.bind_form")
    public Map<String,Object> toBindForm(String saveId,String formId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!Strings.isBlank(formId)){
			FormConfigBo formConfig = dao().fetch(FormConfigBo.class,formId);

			result.put("formConfig", formConfig);
		}
		Criteria cri = Cnd.cri();
		cri.getOrderBy().desc("createTime");
		List<FormConfigBo> formList = dao().query(FormConfigBo.class,cri);
		result.put("formList", formList);
		result.put("saveId", saveId);
		result.put("formId", formId);
		return result;
    }
    
    /**
     * 列表配置绑定表单
     */
    @At
	@Ok("redirect:/suite/config/query/toQueryList")
    public Map<String,Object> saveBindForm(String saveId,String formId) throws Exception {
    	Map<String,Object> result = new HashMap<String,Object>();
    	//设置绑定
        QueryConfigBo querySave = queryService.querySaveById(saveId);
        querySave.setFormId(formId);
        this.dao().update(querySave);//没有起作用
        //设置rel
		FormConfigBo formConfig = dao().fetch(FormConfigBo.class,formId);
		formConfig.setConfigId(saveId);
        this.dao().update(formConfig);
		return result;
    }
    
    /**
     * 列表配置取消表单绑定
     */
    @At
    @Ok("redirect:/suite/config/query/toQueryList")
    public Map<String,Object> cancelBindForm(String saveId) throws Exception {
        QueryConfigBo querySave = queryService.querySaveById(saveId);
        querySave.setFormId(null);
        this.dao().update(querySave);
		return null;
    }
    
    /**
     * 进入查询页面，包括查询条件及查询结果
     */
    @SuppressWarnings("unchecked")
	@At
    public View toResult(HttpServletRequest req,String saveId,String queryFlag,String exportFlag,String viewFlag,String taskNo,String catalogueId) throws Exception {
    	//User userInfo = (User)req.getSession().getAttribute("logonName");
    	Map<String,Object> result = new HashMap<String, Object>();
    	// 条件Map
        Map<String,String> conMap = new HashMap<String,String>();
        // 根据字段id查询条件字段列表
        List<ColumnConfigBo> conditionColList = new ArrayList<ColumnConfigBo>();
        //renderer设置
        Map<String,Object> queryRenderer = null;
    	FormConfigBo formConfig = null;
    	//根据saveId获取查询配置
    	QueryConfigBo querySave = queryService.querySaveById(saveId);
    	//修改   如果绑定了表单，获取表单信息
    	if(	!Strings.isEmpty(querySave.getFormId()) ){
    		//根据表单id获取表单配置
    		formConfig = dao().fetch(FormConfigBo.class, querySave.getFormId());
    	}
    	//表单有无过滤条件标识
    	String tagFlag = "0";
    	//修改于2016-10-24表单过滤条件
    	if(formConfig != null && formConfig.getTagWhereSql()!=null && formConfig.getTagWhereSql()!=""){
    		tagFlag = "1";
    	}
    	result.put("tagFlag", tagFlag);
    	
        if(querySave != null ){
        	String [] conCols = StringUtils.split(querySave.getConCols(), ",");//查询条件列编号
        	//根据查询字段id获取查询条件字段
            conditionColList = queryService.queryColumnListByColId(conCols);
            //判断renderer是否为空
            if (StringUtils.isNotBlank(querySave.getRenderer())) {
            	queryRenderer = genRenderString(req,querySave.getRenderer());
                result.put("queryRenderer", queryRenderer);// 保存查询实例
            }
        }
        //组建 whereSql 语句
        String whereSql = "";
        if(!Strings.isBlank(querySave.getWhereCols())){
        	String[] whereCols = querySave.getWhereCols().split(",");
        	String[] whereJoins = querySave.getWhereJoins().split(",");
        	String[] whereValues = querySave.getWhereValues().split(",");
        	for(int i=0;i<whereCols.length;i++){
        		whereSql = whereSql+" and "+DicDataUtils.getInstance().getDicData(ConfigConstants.DIC_QUERY_COL, whereCols[i])+" "+whereJoins[i]+"'"+whereValues[i]+"'";
        	}
        }
        if(!Strings.isBlank(querySave.getWhereAppend())){
        	whereSql = whereSql+" and "+querySave.getWhereAppend();
        }//whereSQL组装完毕
        //感觉此处有问题
       if(whereSql !="" && queryRenderer != null){
        	queryRenderer = new HashMap<String,Object>();
        	queryRenderer.put(BaseRenderer.WHERE_SQL, whereSql+queryRenderer.get("whereSql"));
        	result.put("queryRenderer", queryRenderer);
       }
        TableConfigBo queryTheme = queryService.getQueryThemeById(querySave.getThemeId());
        result.put("queryTheme", queryTheme);// 保存查询实例
        result.put("querySave", querySave);// 保存查询实例
        result.put("queryFlag", queryFlag);// 查询标识
        result.put("viewFlag", viewFlag);//导航条标识
        
        result.put("conditionColList", conditionColList);// 标题列表
        if (querySave != null && StringUtils.isBlank(queryFlag)) {// 查询结果 
            // 拼接查询条件
            List<ColumnConfigBo> pkList = queryService.queryPkByThemeId(querySave.getThemeId());
            QueryConBo queryCon = genQueryPageResult(req, querySave, conditionColList, conMap, pkList,queryRenderer,taskNo);//修改，根据taskId取数据
            // 结果展示字段列表
            List<ColumnConfigBo> resultColList = new ArrayList<ColumnConfigBo>();
            resultColList = queryService.queryColumnListByColId(StringUtils.split(querySave.getRsCols(), ","));
            if(pkList != null && pkList.size()>0){
            	 result.put("pkList", pkList.get(0));// 返回主键列表
            }
           
            result.put("resultColList", resultColList);//  返回结果展示字段列表
            // excel导出结果
            if (StringUtils.isNotBlank(exportFlag)) {
                List<Object> resultList = queryService.queryAllResultList(queryCon.getQuerySql());
                result.put("resultList", resultList);
                return new ViewWrapper(new JspView("wddc.suite.csrq.query.export"),result);
            }
            // 查询结果列表
            Pager pager =ConUtils.makePaginationPager(req);
			int rsListCount = queryService.queryCount(queryCon.getCountSql());
			List rsList = queryService.queryFineResultList(queryCon.getQuerySql(),pager);
            result.put("rsList", rsList);
            result.put("rsListCount", rsListCount);
            pager.setRecordCount(rsListCount);
            result.put("pager", pager);
            
        }
        //加入ps_account_book中是否需要审核标志位的过滤
        result.put("conMap", conMap); // 查询参数列表
        return new ViewWrapper(new JspView("wddc.suite.csrq.query.result"),result);
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
    private QueryConBo genQueryPageResult(HttpServletRequest request, QueryConfigBo querySave, List<ColumnConfigBo> conditionColList,
            Map<String,String> conMap, List<ColumnConfigBo> pkList,Map<String,Object> queryRenderer,String taskNo) {
    	TableConfigBo queryTheme = queryService.getQueryThemeById(querySave.getThemeId());
    	//User sessionUser = (User)request.getSession().getAttribute("logonName");  
        // ***********设置各拼接条件****
        // 查询where拼接条件,保存条件信息到conMap
        String conString = genQueryOrderClause(request, conditionColList, conMap);
        // 查询select拼接条件
        String resString = genQueryResultClause(querySave.getRsCols(), pkList);
        // 查询sort拼接条件
        String sortString = genQuerySortClause(querySave.getSortCols());
        // ***********设置各拼接条件**********

        if(queryRenderer !=null && queryRenderer.size()>0){
        	conString +=queryRenderer.get(BaseRenderer.WHERE_SQL);  //by fengjia
        	resString+=",BELONG_TOWN,BELONG_COMMUNITY ";
        }
        // ***********查询语句拼接**********
        // 拼接查询语句，加入TASK_NO过滤条件
        StringBuffer querySql = new StringBuffer();
        querySql.append("SELECT ").append(resString).append(" FROM ").append(queryTheme.getViewName()).append(
                " WHERE 1=1 ");
        
        // 拼接查询记录数语句
        String countSql = "select count(*) as count from " + queryTheme.getViewName() + " where 1=1 ";
        
        // ***********查询语句拼接**********

        // **********设置分页信息*************
        int currentPage = 1; // 当前页数
        if (StringUtils.isNotEmpty(request.getParameter("pageNum"))) {
            currentPage = Integer.parseInt(request.getParameter("pageNum"));
        }
        // 每页记录数
        int pageSize = 10;
        // **********设置分页信息*************
        QueryConBo queryCon = new QueryConBo();
        queryCon.setCurrentPage(currentPage);
        queryCon.setPageSize(pageSize);
        queryCon.setCountSql(countSql);
        queryCon.setQuerySql(querySql.toString());
        return queryCon;

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
    private String genQueryOrderClause(HttpServletRequest request, List<ColumnConfigBo> conditionColList, Map<String,String> conMap) {
        StringBuffer conBuffer = new StringBuffer();
        for (int i = 0; i < conditionColList.size(); i++) {
        	ColumnConfigBo queryColumn = (ColumnConfigBo) conditionColList.get(i);
            
            if ("3".equals(queryColumn.getEditType())) {// 日期类型
                String startDate = request.getParameter(queryColumn.getNameLetter() + "staDATE");
                String endtDate = request.getParameter(queryColumn.getNameLetter() + "endDATE");
                if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endtDate)) {
                    conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" between '").append(
                            request.getParameter(queryColumn.getNameLetter() + "staDATE")).append(
                            " 00:00:00' and '");
                    conBuffer.append(request.getParameter(queryColumn.getNameLetter() + "endDATE")).append(
                            " 23:59:59'");
                    conMap.put(queryColumn.getNameLetter() + "endDATE", request.getParameter(queryColumn
                            .getNameLetter()
                            + "endDATE"));
                    conMap.put(queryColumn.getNameLetter() + "staDATE", request.getParameter(queryColumn
                            .getNameLetter()
                            + "staDATE"));
                } else if (StringUtils.isBlank(startDate) && StringUtils.isNotBlank(endtDate)) {
                    conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" < '");
                    conBuffer.append(request.getParameter(queryColumn.getNameLetter() + "endDATE")).append(
                            " 23:59:59' ");
                    conMap.put(queryColumn.getNameLetter() + "endDATE", request.getParameter(queryColumn
                            .getNameLetter()
                            + "endDATE"));

                } else if (StringUtils.isNotBlank(startDate) && StringUtils.isBlank(endtDate)) {
                    conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" > '").append(
                            request.getParameter(queryColumn.getNameLetter() + "staDATE")).append(
                            " 00:00:00'");
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
    
    /**
     * 将结果定义转为具体查询语法
     * 
     * @param colIds
     *            结果定义
     * @return 结果定义查询语法
     */
    private String genQueryResultClause(String colIds, List<ColumnConfigBo> pkList) {
        if (StringUtils.isBlank(colIds))
            return "";
        String[] tempColIds = colIds.split(",");
        String result = null;
        // 设置查询PK
        if (pkList != null && pkList.size() > 0) {
            for (int i = 0; i < pkList.size(); i++) {
            	ColumnConfigBo queryColumn = (ColumnConfigBo) pkList.get(i);
                boolean flag = false;// 否已经设置的PK为返回列 true 是 false不是
                for (int j = 0; j < tempColIds.length; j++) {// 查询是否已经设置的PK为返回列
                    if (queryColumn.getNameLetter().equals(DicDataUtils.getInstance().getDicData(ConfigConstants.DIC_QUERY_COL, tempColIds[j])))
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
            if (result == null){
            	ColumnConfigBo columnConfigTmp = queryService.queryColumnByColId(Integer.parseInt(tempColIds[i]));
            	result = columnConfigTmp.getNameLetter();
            }
            else {
            	ColumnConfigBo columnConfigTmp = queryService.queryColumnByColId(Integer.parseInt(tempColIds[i]));
            	result += "," + columnConfigTmp.getNameLetter();
            }
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
                result = DicDataUtils.getInstance().getDicData(ConfigConstants.DIC_QUERY_COL, tempColIds[i])
                        + " " + tempColIds[i + 1];
            else
                result += ","
                        + DicDataUtils.getInstance().getDicData(ConfigConstants.DIC_QUERY_COL, tempColIds[i])
                        + " " + tempColIds[i + 1];
            i++;
        }
        return result;

    }
    
    /**
     * 保存自定义查询
     */
    @At
	@Ok("redirect:/suite/config/query/toQueryList")
    public Map<String,Object> toSaveQuery(HttpServletRequest req,String  conSelect,String  resultSelect,String sortSelect,String editSelect,String saveId,
    		String name,String saveDesc,String viewType,Integer themeId,String renderer,String  whereCols,String whereJoins,String whereValues,String whereAppend,String tagList) throws Exception {
    	QueryConfigBo query = new QueryConfigBo();
        // 判断是新增还是更新
        if (!StringUtils.isBlank(saveId)) {
            query = queryService.querySaveById(saveId);
        }
        // 设置定制值
        query.setName(StringUtils.trimToEmpty(name));
        query.setSaveDesc(StringUtils.trimToEmpty(saveDesc));
        query.setTagList(tagList);
        query.setConCols(conSelect);
        query.setRsCols(resultSelect);
        query.setSortCols(sortSelect);
        query.setThemeId(themeId);
        query.setRenderer(StringUtils.trimToEmpty(renderer));
        query.setViewType(viewType);
        String wherecol = null;
        String whereJoin = null;
        String whereValue = null;
        String[] whereColsNew = whereCols.split(",");
        String[] whereJoinsNew = whereJoins.split(",");
        String[] whereValuesNew = whereValues.split(",");
               
        for(int i=0;i<whereColsNew.length;i++){
	    	if(StringUtils.isNotBlank(whereColsNew[i])){
	    		if(wherecol ==null){
	    			wherecol=whereColsNew[i];
	    			whereJoin = whereJoinsNew[i];
	    			whereValue = whereValuesNew[i];
	    		}else{
	    			wherecol += ","+whereColsNew[i];
	    			whereJoin += ","+whereJoinsNew[i];
	    			whereValue += ","+whereValuesNew[i];
	    		}
	    	}
	    }
        
        query.setWhereCols(wherecol);
        query.setWhereJoins(whereJoin);
        query.setWhereValues(whereValue);
        query.setWhereAppend(whereAppend);
        
        if (StringUtils.isBlank(saveId)) {
            query.setSaveId(System.currentTimeMillis()+"");
            //设置时间信息
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String saveDate = formatter.format(date);
            query.setSaveDate(DateUtils.string2Timestamp(saveDate, DateUtils.FORMAT_DATE));
            /*//设置用户信息
            SessionUser userInfo = (SessionUser)req.getSession().getAttribute("sessionUser");
            query.setUserId(userInfo.getUserId());
            query.setUserName(userInfo.getDisplayName());*/
            query.setRsPreview(this.genRsPreviewHtml(query, "GenerListHtml"));
        	this.dao().insert(query);
        }else{
            query.setRsPreview(this.genRsPreviewHtml(query,"GenerListHtml"));
            this.dao().update(query);
        }
		return null;
    }
    
    /**
     * 生成预览页面html字符串.
     * @param saveId
     * @param vmName
     * @return
     */
	private String genRsPreviewHtml(QueryConfigBo querySave, String vmName){
		Integer themeId = querySave.getThemeId();
		TableConfigBo queryTheme = queryService.getQueryThemeById(themeId);
		String resultCol = querySave.getRsCols();
		String[] resultCols = resultCol.split(",");
		List<ColumnConfigBo> list = queryService.queryColumnListByColId(resultCols);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("list", list);
		result.put("queryTheme", queryTheme);
		
		try {
			String designDate = VelocityUtils.merge(result, vmName);
			return designDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}