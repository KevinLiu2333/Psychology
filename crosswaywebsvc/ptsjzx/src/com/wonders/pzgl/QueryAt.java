package com.wonders.pzgl;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.wonders.pzgl.entity.ColumnConfig;
import com.wonders.pzgl.entity.DwLog;
import com.wonders.pzgl.entity.FormConfig;
import com.wonders.pzgl.entity.PsAccountBookTask;
import com.wonders.pzgl.entity.QueryCon;
import com.wonders.pzgl.entity.QueryConfig;
import com.wonders.pzgl.entity.TableConfig;
import com.wonders.pzgl.renderer.BaseRenderer;
import com.wonders.pzgl.service.QueryService;
import com.wonders.pzgl.util.ArrayUtil;
import com.wonders.pzgl.util.VelocityUtils;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.tiles.ui.UI;
import com.wonders.tiles.ui.UI.StatusCode;
import com.wonders.utils.DateUtils;

@At("/config/query")
@IocBean(name = "queryconfig", fields = "dao")
public class QueryAt extends EntityService<QueryConfig> {
	
	@Inject
    private QueryService queryService;
	
	/**
	 * 进入查询列表页面
	 * @param roleDesc
	 * @return
	 */
	@At
	@Ok("jsp:jsp.pzgl.query.list")
	public Map<String, Object> toQueryList(Criteria criteria, Pager pager) {
		criteria.getOrderBy().desc("saveDate");
		List<QueryConfig> list = dao().query(QueryConfig.class, criteria, pager);
		int count = dao().count(QueryConfig.class, criteria);
		pager.setRecordCount(count);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("pager", pager);
		return result;
	}
	/**
     * 进入查询定义界面，包含查询新增定义及查询定义修改
     */
    @At("/toEditQuery")
    @Ok("jsp:jsp.pzgl.query.edit")
    public Map<String,Object> toEditQuery(Integer themeId,String saveId) throws Exception {
    	Map<String,Object> result = new HashMap<String, Object>();
    	QueryConfig querySave = new QueryConfig();
        // 修改已经定义的查询配置
        if (StringUtils.isNotBlank(saveId)) {
            querySave = queryService.querySaveById(saveId);
            themeId = querySave.getThemeId();
            result.put("querySave", querySave);
        }
        List<ColumnConfig> columnList = queryService.queryColumnList(themeId);
        
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
    		for(ColumnConfig queryColumn : columnList) {
    		    for(String rsString : rsStrings) {
    				if(rsString.equals(""+queryColumn.getColId())) {
    					rsMap.put(rsString, queryColumn.getName());
    				}
    			}
    		}	
    	}
    	
    	//可选条件
    	List<ColumnConfig> unselectedCons = new ArrayList<ColumnConfig>();
    	//已选条件
    	List<ColumnConfig> selectedCons = new ArrayList<ColumnConfig>();
    	
    	//可选结果
    	List<ColumnConfig> unselectedResults = new ArrayList<ColumnConfig>();
    	//已选结果
    	List<ColumnConfig> selectedResults = new ArrayList<ColumnConfig>();
    	
    	for (ColumnConfig column : columnList) {
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

    	List<TableConfig> themeList = queryService.queryThemeListByCatalog(null);
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
	@Ok("json")
    public Map<String,Object> toDelQuery(String saveId) throws Exception {
        queryService.removeQuerySave(saveId);
		return UI.ajaxDone(StatusCode.OK);
    }
    /**
     * 进入绑定表单页面
     */
    @At
	@Ok("jsp:jsp.pzgl.query.bind_form")
    public Map<String,Object> toBindForm(String saveId,String formId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(!Strings.isBlank(formId)){
			FormConfig formConfig = dao().fetch(FormConfig.class,formId);

			result.put("formConfig", formConfig);
		}
		Criteria cri = Cnd.cri();
		cri.getOrderBy().desc("createTime");
		List<FormConfig> formList = dao().query(FormConfig.class,cri);
		result.put("formList", formList);
		result.put("saveId", saveId);
		result.put("formId", formId);
		return result;
    }
    /**
     * 列表配置取消表单绑定
     */
    @At
	@Ok("json")
    public Map<String,Object> cancelBindForm(String saveId) throws Exception {
        QueryConfig querySave = queryService.querySaveById(saveId);
        querySave.setFormId(null);
        this.dao().update(querySave);
		return UI.ajaxDone(StatusCode.OK);
    }
    /**
     * 进入查询页面，包括查询条件及查询结果
     */
    @SuppressWarnings("unchecked")
	@At
    public View toResult(HttpSession session,HttpServletRequest req,String saveId,String queryFlag,String exportFlag,String viewFlag,String onekey) throws Exception {
    	//日志
    	DwLog log = new DwLog();
    	log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
    	log.setOperateDate(new Date());
    	
    	log.setOperateUser(((User)session.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
    	log.setOperateDept(((User)session.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
    	QueryConfig querySave = queryService.querySaveById(saveId);
    	log.setOperateType("查询"+querySave.getName());
    	Map<String,Object> result = new HashMap<String, Object>();
    	// 条件Map
        Map<String,String> conMap = new HashMap<String,String>();
        // 查询条件字段列表
        List<ColumnConfig> conditionColList = new ArrayList<ColumnConfig>();

        //renderer设置
        Map<String,Object> queryRenderer = null;
        if(querySave != null ){
        	String [] conCols = StringUtils.split(querySave.getConCols(), ",");
            conditionColList = queryService.queryColumnListByColId(conCols);
            if (StringUtils.isNotBlank(querySave.getRenderer())) {
            	queryRenderer = genRenderString(req,querySave.getRenderer());
                result.put("queryRenderer", queryRenderer);// 保存查询实例
            }
        }
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
        }
        if(whereSql !=""){
//        	queryRenderer = new HashMap<String,Object>();
        	queryRenderer.put(BaseRenderer.WHERE_SQL, whereSql+queryRenderer.get("whereSql"));
        	result.put("queryRenderer", queryRenderer);
        }
        TableConfig queryTheme = queryService.getQueryThemeById(querySave.getThemeId());
        log.setQueryTable(queryTheme.getViewName());
        result.put("queryTheme", queryTheme);// 保存查询实例
        result.put("querySave", querySave);// 保存查询实例
        result.put("queryFlag", queryFlag);// 查询标识
        result.put("viewFlag", viewFlag);//导航条标识
        PsAccountBookTask task = new PsAccountBookTask();
        result.put("accountBookTask", task);
        result.put("conditionColList", conditionColList);// 标题列表
        if (querySave != null && StringUtils.isBlank(queryFlag)) {// 查询结果
            // 拼接查询条件
            List<ColumnConfig> pkList = queryService.queryPkByThemeId(querySave.getThemeId());
            QueryCon queryCon = genQueryPageResult(req, querySave, conditionColList, conMap, pkList,queryRenderer,log);//修改，根据taskId取数据
            // 结果展示字段列表
            List<ColumnConfig> resultColList = new ArrayList<ColumnConfig>();
            resultColList = queryService.queryColumnListByColId(StringUtils.split(querySave.getRsCols(), ","));
            
            result.put("pkList", pkList.get(0));// 返回主键列表
            result.put("resultColList", resultColList);//  返回结果展示字段列表
            // excel导出结果
            if (StringUtils.isNotBlank(exportFlag)) {
                List<Object> resultList = queryService.queryAllResultList(queryCon.getQuerySql());
                result.put("resultList", resultList);
                return new ViewWrapper(new JspView("jsp.pzgl.query.export"),result);
            }
            // 查询结果列表
            Pager pager =ConUtils.makePaginationPager(req);
            int rsListCount;
            //如果查询人口库无条件情况下为空
            List rsList;
            if(saveId.equals("1460623452768")&&(req.getParameter("XM")==null||req.getParameter("XM").equals(""))
            		&&(req.getParameter("XMSP")==null||req.getParameter("XMSP").equals(""))
            		&&(req.getParameter("ZJHM")==null||req.getParameter("ZJHM").equals(""))){
            	rsListCount=0;
            	rsList=new ArrayList();
            }else if(saveId.equals("1456475836432")&&(req.getParameter("queryflag")==null||req.getParameter("queryflag").equals(""))){
            	rsListCount=0;
            	rsList=new ArrayList();
			}
            else {
            	rsListCount = queryService.queryCount(queryCon.getCountSql());
    			rsList = queryService.queryFineResultList(queryCon.getQuerySql(),pager);
			}
            //log.setOperateResult(rsList.toString());
            log.setQueryCount(""+rsList.size());
			//查人口库时用*去遮住身份证号和姓名
			if(saveId.equals("1460623452768"))
			{
				renkoukujiaxin(rsList);
			}
            result.put("rsList", rsList);
            result.put("rsListCount", rsListCount);
            pager.setRecordCount(rsListCount);
            result.put("pager", pager);
           
        }
        dao().insert(log);
        result.put("conMap", conMap); // 查询参数列表
        if(onekey!=null)//一键查询
        {
        	result.put("onekey", "onekey"); 
        }
      
        return new ViewWrapper(new JspView("jsp.pzgl.query.result"),result);
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
    private QueryCon genQueryPageResult(HttpServletRequest request, QueryConfig querySave, List<ColumnConfig> conditionColList,
            Map<String,String> conMap, List<ColumnConfig> pkList,Map<String,Object> queryRenderer,DwLog log) {
    	TableConfig queryTheme = queryService.getQueryThemeById(querySave.getThemeId());
             
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
        	
        }
        // ***********查询语句拼接**********
        // 拼接查询语句，加入TASK_NO过滤条件
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
        log.setQueryCondition(conString);
        QueryCon queryCon = new QueryCon();
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
    private String genQueryOrderClause(HttpServletRequest request, List<ColumnConfig> conditionColList, Map<String,String> conMap) {
        StringBuffer conBuffer = new StringBuffer();
        for (int i = 0; i < conditionColList.size(); i++) {
        	ColumnConfig queryColumn = (ColumnConfig) conditionColList.get(i);
            
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
                conBuffer.append(" and ").append(queryColumn.getNameLetter()).append(" = '").append(
                        request.getParameter(queryColumn.getNameLetter())).append("' ");
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
    private String genQueryResultClause(String colIds, List<ColumnConfig> pkList) {
        if (StringUtils.isBlank(colIds))
            return "";
        String[] tempColIds = colIds.split(",");
        String result = null;
        // 设置查询PK
        if (pkList != null && pkList.size() > 0) {
            for (int i = 0; i < pkList.size(); i++) {
            	ColumnConfig queryColumn = (ColumnConfig) pkList.get(i);
                boolean flag = false;// 否已经设置的PK为返回列 true 是 false不是
                for (int j = 0; j < tempColIds.length; j++) {// 查询是否已经设置的PK为返回列
//                	String a= queryColumn.getNameLetter();
//                	String b = DicDataUtils.getInstance().getDicData(DetailQueryConstants.DIC_QUERY_COL, tempColIds[i]);
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
            	ColumnConfig columnConfigTmp = queryService.queryColumnByColId(Integer.parseInt(tempColIds[i]));
            	result = columnConfigTmp.getNameLetter();
                //result = DicDataUtils.getInstance().getDicData(ConfigConstants.DIC_QUERY_COL, tempColIds[i]);
            }
            else {
            	ColumnConfig columnConfigTmp = queryService.queryColumnByColId(Integer.parseInt(tempColIds[i]));
            	result += "," + columnConfigTmp.getNameLetter();
                //result += "," + DicDataUtils.getInstance().getDicData(ConfigConstants.DIC_QUERY_COL, tempColIds[i]);
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
	@Ok("json")
    public Map<String,Object> toSaveQuery(HttpServletRequest req,String [] conSelect,String [] resultSelect,String [] sortSelect,String [] editSelect,String saveId,
    		String name,String saveDesc,String viewType,Integer themeId,String renderer,String [] whereCols,String [] whereJoins,String [] whereValues,String whereAppend) throws Exception {
    	QueryConfig query = new QueryConfig();
        // 判断是新增还是更新
        if (!StringUtils.isBlank(saveId)) {
            query = queryService.querySaveById(saveId);
        }
        // 设置定制值
        query.setName(StringUtils.trimToEmpty(name));
        query.setSaveDesc(StringUtils.trimToEmpty(saveDesc));
        
        query.setConCols(ArrayUtil.strArray2StrSplit(conSelect, ","));
        query.setRsCols(ArrayUtil.strArray2StrSplit(resultSelect, ","));
        query.setSortCols(ArrayUtil.strArray2StrSplit(sortSelect, ","));
        query.setThemeId(themeId);
        query.setRenderer(StringUtils.trimToEmpty(renderer));
        query.setViewType(viewType);
        String wherecol = null;
        String whereJoin = null;
        String whereValue = null;
        for(int i=0;i<whereCols.length;i++){
	    	if(StringUtils.isNotBlank(whereCols[i])){
	    		if(wherecol ==null){
	    			wherecol=whereCols[i];
	    			whereJoin = whereJoins[i];
	    			whereValue = whereValues[i];
	    		}else{
	    			wherecol += ","+whereCols[i];
	    			whereJoin += ","+whereJoins[i];
	    			whereValue += ","+whereValues[i];
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
            //设置用户信息
            query.setUserId("admin");
            query.setUserName("admin");
            query.setRsPreview(this.genRsPreviewHtml(query, "GenerListHtml"));
        	this.dao().insert(query);
        }else{
            query.setRsPreview(this.genRsPreviewHtml(query,"GenerListHtml"));
            this.dao().update(query);
        }
		return UI.closeAjaxDone(StatusCode.OK,"queryconfig");
    }
    /**
     * 生成预览页面html字符串.
     * @param saveId
     * @param vmName
     * @return
     */
	private String genRsPreviewHtml(QueryConfig querySave, String vmName){
		Integer themeId = querySave.getThemeId();
		TableConfig queryTheme = queryService.getQueryThemeById(themeId);
		String resultCol = querySave.getRsCols();
		String[] resultCols = resultCol.split(",");
		List<ColumnConfig> list = queryService.queryColumnListByColId(resultCols);
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
	/**
     * 列表配置绑定表单
     */
    @At
	@Ok("json")
    public Map<String,Object> saveBindForm(String saveId,String formId) throws Exception {
    	//设置绑定
        QueryConfig querySave = queryService.querySaveById(saveId);
        querySave.setFormId(formId);
        this.dao().update(querySave);
        //设置rel
		FormConfig formConfig = dao().fetch(FormConfig.class,formId);
		formConfig.setConfigId(saveId);
        this.dao().update(formConfig);
		return UI.closeAjaxDone(StatusCode.OK,"queryconfig");
    }
    
    /**
     * 人口库姓名和身份证号去掉
     * @param list
     */
    private void renkoukujiaxin(List list){
    	for(Object obj:list){
    		Map<String, String> map=(Map<String, String>) obj;
    		String xm=map.get("XM");
    		if(xm!=null){
    			if(xm.length()==2||xm.length()==1)
    			{
    				map.put("XM", xm.charAt(0)+"*");
    			}
    			if(xm.length()==3){
    				map.put("XM", xm.charAt(0)+"*"+xm.charAt(2));
    			}
    			if(xm.length()>3){
    				map.put("XM", xm.charAt(0)+Strings.dup('*', xm.length()-2)+xm.charAt(xm.length()-1));
    			}
    		}
    		String zjhm=map.get("ZJHM");
    		if(zjhm!=null){
    			if(zjhm.length()==18){
    				map.put("ZJHM", zjhm.substring(0,6)+"********"+zjhm.substring(14,18));
    			}else {
					map.put("ZJHM", Strings.dup('*', zjhm.length()/2)+zjhm.substring(zjhm.length()/2,zjhm.length()));
				}
    		}
    	}
    }
}
