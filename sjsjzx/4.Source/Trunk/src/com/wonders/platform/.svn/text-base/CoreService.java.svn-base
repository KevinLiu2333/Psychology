package com.wonders.platform;

import com.wonders.platform.entity.*;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.extend.filter.SessionFilter;
import com.wonders.utils.CookieUtils;
import com.wonders.zymlgx.entity.ApplyCount;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * 核心服务，平台服务入口.
 */

@At("/sj")
@IocBean(fields = "dao")
@Filters
public class CoreService {

    private Dao dao;

    @At
    @Ok("raw")
    public String services(HttpServletRequest request) {
        //1.验证参数
        String userKey = request.getParameter("userKey");
        String methodKey = request.getParameter("methodKey");
        //2.获取配置
        Criteria cri = Cnd.cri();
        FwApply fwApply = dao.fetch(FwApply.class, Cnd.where("methodKey","=",methodKey));
        if(fwApply == null){
            return "error201:methodKey错误";
        }
        ZyInfo zyInfo = dao.fetch(ZyInfo.class, Cnd.where("zyInfoId","=",fwApply.getZyInfoId()));

        List<FwConfig> fwConfigList = dao.query(FwConfig.class, Cnd.where("fwApplyId","=",fwApply.getFwApplyId()));
        Map<String,String> paramMap = new HashMap<String,String>();
        //3.数据检索
        String resultSql = "";
        for(FwConfig fwConfig : fwConfigList){
            if("".equals(resultSql)){
                resultSql = fwConfig.getEnName();
            }else{
                resultSql= resultSql+","+fwConfig.getEnName();
            }
            paramMap.put(fwConfig.getEnName().toLowerCase(),fwConfig.getCnName());
        }
        String whereSql = "";
        boolean whereFlag = false;
        Enumeration<?> paramNames = request.getParameterNames();
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            String paramValue = "";
            if (values == null || values.length == 0) {//NOSONAR
            } else if (values.length > 1) {
            } else {
                if (!Strings.isBlank(values[0])) {
                    paramValue=values[0];
                }
            }
            if(!"".equals(paramValue) && paramMap.containsKey(paramName.toLowerCase())){

                whereFlag = true;
                if("".equals(whereSql)){
                    whereSql = paramName+"='"+paramValue+"'";
                }else{
                    whereSql= whereSql+" and "+paramName+"='"+paramValue+"'";
                }
            }
        }
        if(whereFlag){
            String sqlStr = "select "+resultSql+" from "+zyInfo.getZyTable()+" where "+whereSql;
            System.out.println("*****sql******:"+sqlStr);
            Sql sql = Sqls.create(sqlStr);
            sql.setCallback(new SqlCallback() {
                public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                        return rs2MapList(rs);
                }
            });
            dao.execute(sql);
            List<HashMap<String,Object>> resultList =  (List<HashMap<String,Object>>)sql.getResult();
            if(resultList== null && resultList.size() ==0){
                return "提示:无数据";
            }else{
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("DATA",resultList);
                result.put("META",paramMap);
               return  Json.toJson(result);
            }

        }else{
            return "error101:参数错误或者无参数";
        }
    }


    @At
    @Ok("jsp:jsp.platform.example")
    public Map<String, Object> example(HttpServletRequest request,HttpSession session){

        String fwApplyId = request.getParameter("fwApplyId");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        List<FwConfig> fwConfigList = dao.query(FwConfig.class, Cnd.where("fwApplyId","=",fwApplyId));
        FwApply fwApply = dao.fetch(FwApply.class, fwApplyId);
        Map<String,String> metaMap = new HashMap<String,String>();
        Map<String,String> dataMap = new HashMap<String,String>();
        for(FwConfig fwConfig : fwConfigList){
            metaMap.put(fwConfig.getEnName().toLowerCase(),fwConfig.getCnName());
            dataMap.put(fwConfig.getEnName().toLowerCase(),"返回值");
        }
        jsonMap.put("DATA",dataMap);
        jsonMap.put("META",metaMap);
        String resultJson = Json.toJson(jsonMap);
        int count=0;
        User user = (User) session
		.getAttribute(SystemConstants.SYSTEM_USER);
        if(resultJson !=null)
        {	String applyId=request.getParameter("fwApplyId");
	        Criteria cri1 = Cnd.cri();
			cri1.where().andEquals("applyId", applyId);
			cri1.where().andEquals("userId", user.getUserId());
        	ApplyCount pet = dao.fetch(ApplyCount.class, cri1);
        	if(pet!=null && pet.getUserId().equals(user.getUserId()))
        	{
        	pet.setApplydate(new Date());
        	pet.setCount(pet.getCount()+1);
        	pet.setUserId(user.getUserId());
        	dao.update(pet);
        	}else{
        	ApplyCount  date =new ApplyCount();
        	date.setApplyId(applyId);
        	date.setApplydate(new Date());
        	date.setUserId(user.getUserId());
        	date.setCount(count+1);
        	dao.insert(date);
        	}
        	
        }
        result.put("resultJson",resultJson);
        result.put("fwConfigList",fwConfigList);
        result.put("fwApply",fwApply);
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        result.put("basePath",basePath);
        return result;
    }

    private List<HashMap<String,Object>> rs2MapList(ResultSet rs){
        if (rs == null) {
            return null;
        }
        List<HashMap<String,Object>> rsMapList = new ArrayList<HashMap<String,Object>>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();

            while (rs.next()) {
                String tableName = rsmd.getTableName(1);
                HashMap<String,Object> rsMap = new HashMap<String,Object>();
                for (int i = 1; i <= count; ++i) {
                    String columnName = rsmd.getColumnName(i);
                    //rsMap.put(tableName.toUpperCase()+"."+columnName.toUpperCase(), rs.getObject(columnName));
                    rsMap.put(columnName.toUpperCase(), rs.getObject(columnName));
                }

                rsMapList.add(rsMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rsMapList;
    }


}
