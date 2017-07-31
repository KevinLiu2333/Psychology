package com.wonders.sjfw.platform.spu;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.quartz.SchedulerException;

import com.wonders.sjfw.entity.FwConfig;
import com.wonders.sjfw.entity.LogFw;
import com.wonders.sjfw.platform.FwConstants;
import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.error.Error101;
import com.wonders.sjfw.platform.handle.DataHandle;
import com.wonders.sjfw.platform.handle.ErrorHandle;
import com.wonders.sjfw.platform.handle.LogHandle;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.wddc.suite.csrq.service.ReportService;
import com.wonders.wddc.suite.data.service.DataService;
import com.wonders.wddc.suite.user.entity.UserUnitBo;
/**
 * 
 *基础的处理单元操作.
 *
 */
@IocBean
public abstract class BaseSpu {

	@Inject
	public DataHandle dataHandle;
	
	@Inject
	public LogHandle logHandle;
	
	@Inject
	public ErrorHandle errorHandle;
	

    @Inject
    private DataService dataService;
    
    @Inject
    private ReportService reportService;
    /**
     * 报表处理方法
     * @param id
     * @param params
     * @return
     * @throws ParseException 
     * @throws SchedulerException 
     */
    public Map<String, Object> reportExe(String id,String type,Map<String, String> params) throws SchedulerException, ParseException{
    	if("1".equals(type)){//更新报表
    		return reportService.refreshdata(id, params);
    	}
    	if("2".equals(type)){//获取报表
    		return reportService.toResult(id, (String)params.get("date"), params);
    	}
    	if("3".equals(type)){//动态字典
    		return reportService.toDynamicDicResult(id, (String)params.get("dic"));
    	}
    	if("4".equals(type)){//动态参数
    		return reportService.toDynamicParamResult(id, params);
    	}
    	if("5".equals(type)){//动态参数及动态字典
    		return reportService.toAllDynamicResult(id, (String)params.get("dic"), params);
    	}
    	Map<String, Object> re = new HashMap<String, Object>();
    	re.put("state", "fail");
    	re.put("msg", "参数有误");
    	return re;
    }
    
    public String genResultString(FwParam spuParam,Map<String, Object> jieguoMap,Map<String, Object> resultMap){
    	 String resultString = null;
 	    //判断结果返回风格
 	    if(Strings.isNotBlank(spuParam.rsStyle) && FwConstants.RS_STYLE_SIMPLE.equals(spuParam.rsStyle)){
 		    resultString = Json.toJson(jieguoMap.get(FwConstants.RS_MAP_DATA));
 	    }else{
 		    resultString = Json.toJson(resultMap);
 	    }
 	    return resultString;
    }
    
	/**
	 * 执行sql语句.
	 * @param sqlStr sql语句
	 * @return
	 */
	public Map<String,Object> exeResult(String id,Map<String, String> params,String type){
		Map<String,Object> resultMap =  (Map<String,Object>)dataService.gainresult(id, params,type);
	    return resultMap;
		
	}
	
	/**
	 * 默认的参数验证的实现.
	 * @param spuParam
	 * @return
	 */
	public String validParamDefault(FwParam spuParam) {
		boolean validResult = isALLParam(spuParam.paramMap, spuParam.requestParamMap);
		if(validResult){
			return FwProcessor.VAILD_PARAM_PASS;
		}else{
			//生成错误数据
            String errorString = errorHandle.errorString(spuParam.format,Error101.CODE,Error101.DESC);
            //记录错误的log
            logHandle.dealErrorLog(spuParam.format,Error101.CODE, errorString);
            //记录服务的log
            UserUnitBo userUnit = dataHandle.getUserUnitByUnitKey(spuParam.unitKey);
            logHandle.dealFwLog101(spuParam, userUnit, errorString);
			return errorString;
		}
	}
	
	/**
	 * 生成服务调用成功信息和日志.
	 * @param spuParam
	 * @return
	 */
	public void genSeccessLog(FwParam spuParam,String resultString,int resultcount) {
        //记录服务的log
        UserUnitBo userUnit = dataHandle.getUserUnitByUnitKey(spuParam.unitKey);
        //记录服务成功log
        String whereSql = this.genWhereSql(spuParam.fwConfigList, spuParam.requestParamMap);
        LogFw logfw = logHandle.dealFwLogOk(spuParam, userUnit, resultString, resultcount, whereSql);
        //记录服务的使用的信息
        dataHandle.addFwUsed(spuParam.fwInfo, userUnit, logfw);
	}
	
	 /**
     * 判断参数是否通过.
     * @param paramMap 参数map
     * @param password 密码
     * @return
     */
	protected boolean isALLParam(Map<String,String> paramMap,Map<String,String> requestParamMap){
		//如果不需要传递参数
		if(paramMap.size() ==0){
        	return true;
        }
		//调用未传递参数
		if(requestParamMap.size() ==0){
        	return false;
        }
		//设置返回结果
		boolean whereFlag = true;
		//传入参数要和设置参数保持一致
		Set<String> paramNames = paramMap.keySet();
		for (String key : paramNames) {  
			String paramValue = requestParamMap.get(key);
			if(Strings.isNotBlank(paramValue)){
			}else{
				whereFlag = false;
			}
		} 
		
    	return whereFlag;
    }

	/**
	 * 生成where条件.
	 * @param fwConfigList 配置项列表
	 * @param requestParamMap 传递参数
	 * @return
	 */
	protected String genWhereSql(List<FwConfig> fwConfigList,Map<String,String> requestParamMap){
		 String whereSql = "";
		 //页面传入的条件
	     Set<String> paramNames = requestParamMap.keySet();
	     for (String paramName : paramNames) {  
				String paramValue = requestParamMap.get(paramName);
			 if("".equals(whereSql)){
                    whereSql = paramName+"='"+paramValue+"'";
                }else{
                    whereSql= whereSql+" AND "+paramName+"='"+paramValue+"'";
                }
		 }	 
	     //where条件--固定
	     for(FwConfig fwConfig : fwConfigList){
	        	//查询条件
	        	if("3".equals(fwConfig.getConfigType())){
	                if("".equals(whereSql)){
	                    whereSql = fwConfig.getColName()+" "+fwConfig.getOpType()+" '"+fwConfig.getOpValue()+"'";
	                }else{
	                    whereSql= whereSql+" AND "+fwConfig.getColName()+" "+fwConfig.getOpType()+" '"+fwConfig.getOpValue()+"'";
	                }
	        	}
	      }
	     return whereSql;
	}
	

	
	
    /**
     * 生成普通sql的结果.
     * @param rs 结果集 .
     * @return
     */
	protected List<HashMap<String,Object>> rs2MapList(ResultSet rs){
		//验证是否为空
        if (rs == null) {
            return null;
        }
        //返回结果
        List<HashMap<String,Object>> rsMapList = new ArrayList<HashMap<String,Object>>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();

            while (rs.next()) {
                HashMap<String,Object> rsMap = new HashMap<String,Object>();
               // 按照字段和值的方式来组装
                for (int i = 1; i <= count; ++i) {
                    String columnName = rsmd.getColumnName(i);
                    rsMap.put(columnName.toUpperCase(), rs.getObject(columnName));
                }

                rsMapList.add(rsMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rsMapList;
    }
	
	
    /**
     * 生成统计sql的结果.
     * @param rs
     * @return
     */
	protected List<HashMap<String,Object>> rs2MapListByCount(ResultSet rs){
		//验证是否为空
        if (rs == null) {
            return null;
        }
        //返回结果
        List<HashMap<String,Object>> rsMapList = new ArrayList<HashMap<String,Object>>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();

            while (rs.next()) {
                HashMap<String,Object> rsMap = new HashMap<String,Object>();
                //如果是一个分组，进行特殊的处理，按照分组和值的方式来组装
                if(count == 2){
                	rsMap.put(rs.getString(2), rs.getObject(1));
                }else{
                //如果是多个分组，按照字段和值的方式来组装
	                for (int i = 1; i <= count; ++i) {
	                    String columnName = rsmd.getColumnName(i);
	                    rsMap.put(columnName.toUpperCase(), rs.getObject(columnName));
	                }
                }
                rsMapList.add(rsMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rsMapList;
    }
}
