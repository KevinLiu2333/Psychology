package com.wonders.sjfw.at;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.lang.Strings;

import com.wonders.sjfw.entity.FwConfig;
import com.wonders.sjfw.entity.FwInfo;
import com.wonders.wddc.suite.data.entity.TableConfigBo;
import com.wonders.wddc.suite.data.service.DataService;

public class BaseAct {

	
	/**
	 * 拼装SqL语句.
	 * @param tableName 表名
	 * @param fwConfigList 配置字段列表
	 * @param requestParamMap 传递参数
	 * @return
	 */
	public String genSqlStr(String tableName,List<FwConfig> fwConfigList){
		//查询结果
		String resultSql = this.genResultSql(fwConfigList);
		if(Strings.isBlank(resultSql)){
			resultSql= "*";
		}
		//过滤条件
		String whereSql = this.genWhereSql(fwConfigList);
		if(Strings.isNotBlank(whereSql)){
			whereSql= " WHERE "+whereSql;
		}
		//分组条件
		String groupSql = this.genGroupSql(fwConfigList);
		//拼装语句
		String sqlStr = "SELECT "+resultSql+" FROM "+tableName+" "+whereSql+" "+groupSql;
        return sqlStr;
	}

	
	/**
	 * 生成result条件.
	 * @param fwConfigList 配置项列表
	 * @return
	 */
	protected String genResultSql(List<FwConfig> fwConfigList){
		String resultSql = "";
		//普通查询结果
        for(FwConfig fwConfig : fwConfigList){
        	//查询结果
        	if("2".equals(fwConfig.getConfigType())){
	            if("".equals(resultSql)){
	            	if(Strings.isBlank(fwConfig.getOpType())){
		                resultSql = fwConfig.getColName();
	            	}else{
	            		resultSql = fwConfig.getOpType()+"("+fwConfig.getColName()+") AS "+fwConfig.getColName()+"_"+fwConfig.getOpType();
	            	}
	            }else{
	            	if(Strings.isBlank(fwConfig.getOpType())){
		                resultSql= resultSql+","+fwConfig.getColName();
	            	}else{
	            		resultSql = resultSql+","+fwConfig.getOpType()+"("+fwConfig.getColName()+") AS "+fwConfig.getColName()+"_"+fwConfig.getOpType();
	            	}
	            }
        	}
        	
        }
        //串联group by 字段
        for(FwConfig fwConfig : fwConfigList){
        	//group by 字段的设置
        	if("5".equals(fwConfig.getConfigType())){
                if("".equals(resultSql)){
                	resultSql =fwConfig.getColName();
                }else{
                	resultSql = resultSql+","+fwConfig.getColName();
                }
        	}
        }
        
        return resultSql;
	}
	
	/**
	 * 生成where条件.
	 * @param fwConfigList 配置项列表
	 * @param requestParamMap 传递参数
	 * @return
	 */
	protected String genWhereSql(List<FwConfig> fwConfigList){
		 String whereSql = "";
		 //页面传入的条件
		 for(FwConfig fwConfig : fwConfigList){
	        	//查询条件
	        	if("1".equals(fwConfig.getConfigType())){
	                if("".equals(whereSql)){
	                    whereSql = fwConfig.getColName()+" = @"+fwConfig.getColName()+"";
	                }else{
	                    whereSql= whereSql+" AND "+fwConfig.getColName()+" = @"+fwConfig.getColName()+"";
	                }
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
	 * 生成group by条件.
	 * @param fwConfigList 配置项列表
	 * @return
	 */
	protected String genGroupSql(List<FwConfig> fwConfigList){
		String groupSql = "";
        //where条件--固定
        for(FwConfig fwConfig : fwConfigList){
        	//查询条件
        	if("5".equals(fwConfig.getConfigType())){
                if("".equals(groupSql)){
                	groupSql = "GROUP BY "+fwConfig.getColName();
                }else{
                	groupSql = groupSql+","+fwConfig.getColName();
                }
        	}
        }
	    return groupSql;
	}
	
	

    
    
    
    /**
     * 处理where查询条件
     * @param fwInfo
     * @param request
     */
	public  void dealWhereItem(Dao dao,FwInfo fwInfo,HttpServletRequest request,List<FwConfig> fwConfigList){
    	String[] whereItems = request.getParameterValues("whereItems");
    	String[] whereOps = request.getParameterValues("whereOps");
    	String[] whereValues = request.getParameterValues("whereValues");
    	int index = 0;
    	for(String zyItem : whereItems) {
    		if(Strings.isNotBlank(zyItem)){
	            FwConfig fwConfig = new FwConfig();
	            fwConfig.setFwInfoId(fwInfo.getFwInfoId());
	            fwConfig.setZyItemId(zyItem);
	            fwConfig.setConfigType("3");
	            fwConfig.setOpType(whereOps[index]);
	            fwConfig.setOpValue(whereValues[index]);
                fwConfigList.add(fwConfig);
	            dao.insert(fwConfig);
    		}
            index = index+1;
        }
       
    }
    /**
     * 处理结果信息
     * @param fwInfo
     * @param request
     */
    public  void dealResultItem(Dao dao,FwInfo fwInfo,HttpServletRequest request,List<FwConfig> fwConfigList){
    	String[] resultItems = request.getParameterValues("resultItems");
    	String[] resultOps = request.getParameterValues("resultOps");
    	int index = 0;
    	for(String zyItem : resultItems) {
            FwConfig fwConfig = new FwConfig();
            fwConfig.setFwInfoId(fwInfo.getFwInfoId());
            fwConfig.setZyItemId(zyItem);
            fwConfig.setConfigType("2");
            fwConfig.setOpType(resultOps[index]);
            fwConfigList.add(fwConfig);
            dao.insert(fwConfig);
            index = index+1;
        }
       
    }
    
    
    
    public  void dealFwConfig(Dao dao,FwInfo fwInfo,String zyItems,String configType,List<FwConfig> fwConfigList){
        if(!Strings.isBlank(zyItems)){
            String[] zyItemArray = zyItems.split(",");
            for(String zyItem : zyItemArray) {
                FwConfig fwConfig = new FwConfig();
                fwConfig.setFwInfoId(fwInfo.getFwInfoId());
                fwConfig.setZyItemId(zyItem);
                fwConfig.setConfigType(configType);
                fwConfigList.add(fwConfig);
                dao.insert(fwConfig);
            }
        }
    }
    
    
    public void dealFwSql(Dao dao,DataService dataService,FwInfo fwInfo,List<FwConfig> fwConfigList){
    	 //保存sql
    	TableConfigBo tableConfig =dao.fetch(TableConfigBo.class,Cnd.where("themeId", "=", fwInfo.getZyInfoId()));
        String sqlStr = this.genSqlStr(tableConfig.getViewName(), fwConfigList);
        String multStatId = dataService.insert(fwInfo.getFwName(), sqlStr, tableConfig.getDataSourceId());
        fwInfo.setMultStatId(multStatId);
        dao.update(fwInfo);
    }
    

}
