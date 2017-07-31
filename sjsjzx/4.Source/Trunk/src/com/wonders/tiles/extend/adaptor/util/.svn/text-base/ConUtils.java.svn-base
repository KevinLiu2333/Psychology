package com.wonders.tiles.extend.adaptor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.lang.Strings;

public class ConUtils {

	private static final int DEFAULT_PAGE_NUMBER = 10;

	/*
     * 页面上的查询条件命名示例：
     * filter_类型名_属性名(与实体类属性对应)_条件(大小写不分)
     * 字符串：<input type="text" name="filter_str_myName_like" value="${ str_myName_like}">
     * like:模糊 eq:精确
     * 数值：<input type="text" name="filter_num_myAge_ge" value="${ num_myAge_ge}">
     * eq:相等 gt:大于 lt:小于 ge:大于等于 le:小于等于
     * 日期：<input type="text" name="filter_dt_myBirthday_start" value="${ dt_myBirthday_start }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
     * start:开始时间 end:结束时间 eq:相等
     * 
     */
    
    /**
     * 组装查询条件
     * @param request
     * @return
     */
    public static Criteria makeCri(HttpServletRequest request){
        Criteria cri = Cnd.cri();
        //从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
        Map<String, Object> filterParamMap = getParametersStartingWith(request, "filter_");
        //分析参数Map,构造PropertyFilter列表
        Iterator <String> iterator = filterParamMap.keySet().iterator();
        while(iterator.hasNext()) {
            String filterName = iterator.next();
            Object value = filterParamMap.get(filterName);
            String[] colName = filterName.split("_");
            SqlExpressionGroup expressionGroup = null;
            if (value instanceof String) {
                if (colName[0].equals("str")) {
                    expressionGroup = buildStrCondition(colName[1],colName[2],value);
                } else if (colName[0].equals("num")) {
                    expressionGroup = buildNumCondition(colName[1],colName[2],value);
                } else if  (colName[0].equals("dt")) {
                    expressionGroup = buildDtCondition(colName[1],colName[2],value);
                }
                request.setAttribute(filterName, value);
            } else if (value instanceof String[]) { //复选框
                
            }
            if (null != expressionGroup) {
                cri.where().and(expressionGroup);
            }
        }
        return cri;
    }
    
    /**
     * 组装分页对象
     * @param request
     * @return
     */
    public static Pager makePager (HttpServletRequest request) {
        int pageSize = Integer.valueOf(request.getParameter("pageSize")==null?DEFAULT_PAGE_NUMBER+"":request.getParameter("pageSize"));
        int pageNumber = Integer.valueOf(request.getParameter("pageNumber")==null?"1":request.getParameter("pageNumber"));
        Pager pager = new Pager();
        pager.setPageNumber(pageNumber);
        pager.setPageSize(pageSize);
        return pager;
    }

    /**
     * 组装easyui分页对象.
     * @param request
     * @return
     */
   /* public static Pager makePaginationPager (HttpServletRequest request) {
    	int pageSize = 10;
    	if(!Strings.isBlank(request.getParameter("rows"))){
    		System.out.println(request.getParameter("rows"));
    		pageSize = Integer.valueOf(request.getParameter("rows"));
    	}
    	int pageNumber = 1;
    	if(!Strings.isBlank(request.getParameter("page"))){
    		pageSize = Integer.valueOf(request.getParameter("page"));
    	}
    	Pager pager = new Pager();
        pager.setPageNumber(pageNumber);
        pager.setPageSize(pageSize);
        return pager;
    }*/
    public static Pager makePaginationPager(HttpServletRequest request)
    {
      int pageSize = Integer.valueOf((request.getParameter("rows") == null) ? "10" : request.getParameter("rows")).intValue();
      int pageNumber = Integer.valueOf((request.getParameter("page") == null) ? "1" : request.getParameter("page")).intValue();
      Pager pager = new Pager();
      pager.setPageNumber(pageNumber);
      pager.setPageSize(pageSize);
      return pager;
    }


    /**
     * 组装easyui排序对象.
     * @param request
     * @return
     */
    public static void makePaginationOrder(HttpServletRequest request,Criteria cri,String defaultSort,String defaultOrder) {
        String sort = request.getParameter("sort");
        if(!Strings.isBlank(sort)){
            String order = request.getParameter("order");
            setOrderBy(cri,sort,order);
        }else if(!Strings.isBlank(defaultSort) && !Strings.isBlank(defaultOrder)){
            setOrderBy(cri,defaultSort,defaultOrder);
        }
    }
    
    /**
     * 设置排序
     * @param cri
     * @param sort
     * @param order
     */
    public static void setOrderBy(Criteria cri,String sort , String order){
        if("asc".equals(order)){
            cri.getOrderBy().asc(sort);
        }else{
            cri.getOrderBy().desc(sort);
        }
    }

    public static SqlExpressionGroup buildStrCondition(String conName, String conType, Object value) {
        Criteria cri = Cnd.cri();
        if (conType.equalsIgnoreCase("EQ")) {
            return cri.where().and(conName, "=", value);
        } else if (conType.equalsIgnoreCase("LIKE")) {
            return cri.where().and(conName, "like", "%" + value + "%");
        }
        return null;
    }

    public static SqlExpressionGroup buildNumCondition(String conName, String conType, Object value) {
        Criteria cri = Cnd.cri();
        Long num = Long.valueOf(value.toString()); //将字符型参数转成数值型
        /** 如果属性定义的不是Long型，这样就不行了*/
        if (conType.equalsIgnoreCase("EQ")) {
            return cri.where().and(conName, "=", num);
        } else if (conType.equalsIgnoreCase("LT")) {
            return cri.where().and(conName, "<", num);
        } else if (conType.equalsIgnoreCase("GT")) {
            return cri.where().and(conName, ">", num);
        } else if (conType.equalsIgnoreCase("LE")) {
            return cri.where().and(conName, "<=", num);
        } else if (conType.equalsIgnoreCase("GE")) {
            return cri.where().and(conName, ">=", num);
        }
        return null;
    }

    public static SqlExpressionGroup buildDtCondition(String conName, String conType, Object value) {
        if (Strings.isEmpty(value.toString())) {
            return null;
        }
        Criteria cri = Cnd.cri();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        /** 目前只支持这种日期格式*/
        Date date = null;
        try {
            date = sdf.parse(value.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (conType.equalsIgnoreCase("START")) {
            return cri.where().and(conName, ">=", date);
        } else if (conType.equalsIgnoreCase("END")) {
            return cri.where().and(conName, "<=", date);
        } else if (conType.equalsIgnoreCase("EQ")) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
            return cri.where().and(conName, ">=", date).and(conName, "<=", c.getTime());
        }
        return null;
    }
    
    private static Map<String, Object> getParametersStartingWith(HttpServletRequest request, String prefix) {
        Enumeration<?> paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        if (prefix == null) {
            prefix = "";
        }
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if (values == null || values.length == 0) {//NOSONAR
                    // Do nothing, no values found at all.
                } else if (values.length > 1) {
                    params.put(unprefixed, values);
                } else {
                    if (!Strings.isBlank(values[0])) {
                        params.put(unprefixed, values[0]);
                    }
                }
            }
        }
        return params;
    }
}
