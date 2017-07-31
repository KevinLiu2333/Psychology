package com.wonders.dp.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.dp.common.Constants;
import com.wonders.dp.entity.TjJsonInfo;
import com.wonders.dp.entity.TjSqlInfo;
import com.wonders.dp.utils.AddressUtils;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.util.PropertyUtils;

@At("/sqlinfo")
@IocBean(fields = "dao")
public class SqlInfoAt {
	private Logger logger = Logger.getLogger(SqlInfoAt.class);
	
	private Dao dao;
	
	@At("/tosqlinfo")
    @Ok("jsp:jsp.dp.tjsql.sqlinfo")
    public Object tosqlinfo(HttpServletRequest request, HttpSession session){
//    	User user = (User)session.getAttribute(SystemConstants.SYSTEM_USER);
    	Criteria cri =  ConUtils.makeCri(request);
    	cri.where().and("state","=","1");
    	cri.getOrderBy().desc("createdate");
    	Pager pager =ConUtils.makePaginationPager(request);
    	List<TjSqlInfo> list = dao.query(TjSqlInfo.class,cri,pager);
		pager.setRecordCount(dao.count(TjSqlInfo.class,cri));
    	Map<String,Object>  result= new HashMap<String,Object>();
    	//数据总数
    	result.put("total",pager.getRecordCount());
    	//数据对象
    	result.put("rows", list);
    	//分页信息
    	result.put("pager",pager);
    	return result;
    }
    
    /**
     * 进入Sqlinfo新增编辑页面.
     * @param sqlid
     * @return
     */
    @At
    @Ok("jsp:jsp.dp.tjsql.sqlinfo_edit")
    public Map<String, Object> tosqlinfo_edit(@Param("sqlid") String sqlid){
    	if(!Strings.isEmpty(sqlid) && sqlid != null){
			TjSqlInfo info = dao.fetch(TjSqlInfo.class, sqlid);
    		Map<String, Object> result = new HashMap<String, Object>();
            result.put("info", info);
            return result;
    	}else{
    		return null;
    	}
    }
    
    
    /***
    *测试sql
    **/
    @At
    @Ok("json")
    public Object testSql(String sqlvalue, HttpSession session, HttpServletRequest request) {
    	System.out.println(sqlvalue);
    	 Map<String,Object>  result= new HashMap<String,Object>();
    	 String err="";
    	 try {
    	//String sql="select * from UA_USER_AUTHORITY_MAP";
    	 Sql exeTotalSql = Sqls.create(sqlvalue);
 
    	 exeTotalSql.setCallback(new SqlCallback(){
             public Object invoke(Connection conn,ResultSet rs,Sql sql) {
            	 String tableHtml="<table class='table_multilist' border='1'><tr>";
                try {
                	 ResultSetMetaData rsmd = rs.getMetaData();
                     int count = rsmd.getColumnCount();
                     int j=0;
                     String tdHtml="<tr>";
                     while (rs.next()){
                         for (int i=1;i<=count;i++){
                        	 if (j==0) {
                        		 tableHtml+="<td>"+rsmd.getColumnName(i)+"</td>";
							}
                        	 tdHtml+="<td>"+rs.getObject(i)+"</td>";
                         }
                         j++;
                         tdHtml+="</tr>";
                     }
                     tableHtml+="</tr>"+tdHtml+"</table>";
                     return tableHtml;
				} catch (Exception e) {
					e.getMessage();
				}
                return tableHtml;
             }
         });
    	 dao.execute(exeTotalSql);
    	 result.put("total", exeTotalSql.getResult());
       	} catch (Exception e) {
       		err=e.getMessage();
       		result.put("total", err);
		}
    	return result;
    }
   
    /**
     * sql管理页面 下拉框
     * **/
    @At
    @Ok("json")
    public Object queryselect(@Param("diccode") final String diccode, HttpServletRequest request){
    			String sql = "select key_value,display_value from DIC_RESOURCE where catalog = '1043'";
    			Sql exeTotalSql = Sqls.create(sql);
    			 exeTotalSql.setCallback(new SqlCallback(){
    	             public Object invoke(Connection conn,ResultSet rs,Sql sql) {
    	            	 String options = "";
    	                try {
    	                     while (rs.next()){
    	                    	 String option = "";
    	                         if (rs.getObject(1).equals(diccode)) {
    	                        	 option = "<option value='" +rs.getObject(1) + "' selected>" + rs.getObject(2) + "</option>";
								}else {
									 option = "<option value='" + rs.getObject(1) + "'>" + rs.getObject(2) + "</option>";
								}
    	                         options += option;
    	                     }
    	                     return options;
    					} catch (Exception e) {
    						e.getMessage();
    					}
    	                return options;
    	             }
    	         });
    			//6.回传数据
    			Map<String,Object>  result = new HashMap<String,Object>();
    			dao.execute(exeTotalSql);
    	    	result.put("options", exeTotalSql.getResult());
    			return result; 
    }
    /***
     * 保存SQL
     * **/
    @At
    @Ok("redirect:/sqlinfo/tosqlinfo")
    public void savesqlinfo(@Param("::info.") TjSqlInfo info ,HttpServletRequest request, HttpSession session){
        User user = (User)session.getAttribute(SystemConstants.SYSTEM_USER);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        
    	String sqlid=info.getSqlid();
    	if (StringUtils.isEmpty(info.getSqlid())) {
    		info.setSqlcode(sdf.format(new Date()));
    		info.setCreateuser(user.getDisplayName());
			info.setCreatedate(new Date());
			info.setUpdatedate(new Date());
			info.setState("1");
			dao.insert(info);
		}else {
			TjSqlInfo tjSqlInfo=dao.fetch(TjSqlInfo.class, sqlid);
			info.setUpdatedate(new Date());
			info.setUpdateuser(user.getDisplayName());
			info.setState(tjSqlInfo.getState());
			info.setCreatedate(tjSqlInfo.getCreatedate());
			info.setCreateuser(tjSqlInfo.getCreateuser());
			info.setSqlcode(tjSqlInfo.getSqlcode());
			dao.update(info);
		}
    	
    }
    
    
    /**
	 * 刪除SQL.
	 * */
	@At
    @Ok("json")
	public Object delsqlinfo(@Param("sqlid") String sqlid){
		if(StringUtils.isNotEmpty(sqlid)){
			TjSqlInfo info = dao.fetch(TjSqlInfo.class, sqlid);
			info.setState("0");
	    	int result = dao.update(info);
	    	return result+"";
	    }else {
	        return "0";
	    }
	}
	
	/**
	 * 生成json并保存.
	 */
	@At
	@Ok("redirect:/sqlinfo/tosqlinfo")
	public void saveJson(){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Criteria cri =  Cnd.cri();
    	cri.where().and("state", "=", "1");
    	List<TjSqlInfo> list = dao.query(TjSqlInfo.class,cri);
    	for(int i=0;i<list.size();i++){
    		TjSqlInfo info = list.get(i);
    		logger.info("info.getSqlvalue() : " + info.getSqlvalue());
    		
    		String json = "";
    		if(Constants.PEOPLE_TOP_THREE.equals(info.getDiccode())){
    			json = getJson(info.getSqlvalue());
    		}else if(info.getSqlvalue().contains("|")){
            	String sqls[] = info.getSqlvalue().split("\\|");
            	json = getJson(sqls, info.getDiccode());
            }else{
            	json = getJson(info.getSqlvalue(), info.getDiccode());
            }
    		
    		logger.info("生成的json为:" + json); 
    		Criteria cri1 =  Cnd.cri();
	    	cri1.where().and("sql_code", "=", info.getSqlcode());
//	    	cri1.where().and("to_char(create_date,'yyyy-MM-dd')","=",sdf.format(new Date()));
	    	TjJsonInfo ji = dao.fetch(TjJsonInfo.class, cri1);
	    	
    		TjJsonInfo jsonInfo = new TjJsonInfo();
    		jsonInfo.setSqlid(info.getSqlid());
    		jsonInfo.setSqlcode(info.getSqlcode());
    		jsonInfo.setJsonvalue(json);
    		jsonInfo.setCreatedate(new Date());
    		jsonInfo.setUpdatedate(new Date());
    		jsonInfo.setQuerydate(new Date());
    		if (ji == null) {
    			dao.insert(jsonInfo);
			}else {
				jsonInfo.setJsonid(ji.getJsonid());
				jsonInfo.setCreatedate(ji.getCreatedate());
				dao.update(jsonInfo);
			}
    	}
	}
	
	/**
	 * 适用于：只查询两个字段信息，字段包含年份的除外。
	 * <p>
	 * 用于各街镇人口top三.
	 *  
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getJson(String sql){
		Sql exeTotalSql = Sqls.create(sql);
		exeTotalSql.setCallback(new SqlCallback(){
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				Map<String, String> map = new LinkedHashMap<String, String>();
				while(rs.next()){ 
					map.put(String.valueOf(rs.getObject(1)), String.valueOf(rs.getObject(2)));
				}
				return map;
			}
		});
		dao.execute(exeTotalSql);
		Map<Object, Object> jsonMap = (Map<Object, Object>) exeTotalSql.getResult();
    	return Json.toJson(jsonMap);
	}
	
	/**
	 * 封装json.
	 * 
	 * 适用于按年统计
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getJson(String sql, final String dicCode){
    	try {
    		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    		
    		//String sql="select c.establish_date,count(*) from corp_info c group by c.establish_date order by c.establish_date asc";
        	 Sql exeTotalSql = Sqls.create(sql);
     
        	 exeTotalSql.setCallback(new SqlCallback(){
                 public Object invoke(Connection conn, ResultSet rs, Sql sql) {
                	Map<String, String> map = new TreeMap<String, String>(
                			new Comparator<String>() {
		                        public int compare(String obj1, String obj2) {
		                            return obj1.compareTo(obj2);
		                        } 
                    });
                	//用于存储不需要统计的年份
                	Set<Object> delSet = new HashSet<Object>(); 
                	int temp = 2010;
                	if(Constants.CORP_REG.equals(dicCode) || Constants.CORP_DIF.equals(dicCode)){
                		temp = Integer.parseInt(PropertyUtils.getProperty("corp_count_year"));
                	}
                	if(Constants.DP_RFFLCY.equals(dicCode)){
                		temp = Integer.parseInt(PropertyUtils.getProperty("rhfl_count_year"));
                	}
                    try {
                    	while (rs.next()) {
                    		Object yearStr = rs.getObject(1);
                    		
                    		if(yearStr != null && !"".equals(yearStr)){  
                    			int tempValue = Integer.parseInt(String.valueOf(yearStr).substring(0, 4).trim());
                    			if(tempValue < temp){
                    				delSet.add(tempValue);
                        		}
                    			//处理人房分离的登记时间为String类型的时间
                    			if(Constants.DP_RFFLCY.equals(dicCode)){
                    				yearStr = sdf.parse(String.valueOf(yearStr).substring(0, 4).trim());
                    			}
                    			yearStr = sdf.format(yearStr) + "年";
                    			Object count = rs.getObject(2);
                    			Object preCount = map.get(yearStr);
                    			int total = 0;
                        		if(map.containsKey(yearStr)){
                        			total = Integer.parseInt(String.valueOf(count)) + Integer.parseInt(String.valueOf(preCount));
                        		}else{ 
                        			total = Integer.parseInt(String.valueOf(count));
                        		}
                        		
                        		map.put(yearStr.toString(), String.valueOf(total));
                    		}
                    	}
    				} catch (Exception e) {
    					e.getMessage();
    				}
                    //删除不需要统计的年份
                    Set<String> set = map.keySet();
                    Iterator<String> iter = set.iterator();
                	while(iter.hasNext()){ 
                		String key = (String) iter.next();
                		for(Object o : delSet){
                        	if(key.contains(String.valueOf(o))){
                        		iter.remove();
                        		map.remove(key);
                        	} 
                        }
                    }
                    
                    return map;
                 }
             });
        	 
        	 dao.execute(exeTotalSql);
        	 Map<Object, Object> map = (Map<Object, Object>) exeTotalSql.getResult();
        	 return Json.toJson(map);
           	} catch (Exception e) {
           		e.printStackTrace();
    		}
		return null;
    }
	
	/**
	 * 封装json串.
	 * 
	 * 适用于多个sql的分阶段统计，如：<p> 1、统计20-30岁人数。 <p>
	 * 2、沪籍人数和非沪籍人数。<p>3、法人总数和法人登记数和登记与办公差异数。
	 * @param sqls 数组
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public String getJson(final String[] sqls, final String dicCode){
		final Map<Object, Object> map = new LinkedHashMap<Object, Object>();
		Map<Object, Object> jsoMap = new HashMap<Object, Object>();
		List<String> keys = null;
		if(Constants.CORP_KEY.equals(dicCode)){
			keys = Constants.getCorpList();
		}
		if(Constants.PEOPLE_KEY.equals(dicCode)){
			keys = Constants.getPeopleList();
		}
		if(Constants.HOUSE_KEY.equals(dicCode)){
			keys = Constants.getHouseList();
		} 
		if(Constants.DP_AGE_STAGE.equals(dicCode)){
			keys = Constants.getDpAgeStageList();
		}
		if(Constants.NBXT_AGE_STAGE.equals(dicCode)){
			keys = Constants.getNbxtAgeStageList();
		}
		if(Constants.HJ_TYPE.equals(dicCode)){
			keys = Constants.getHjTypeList();
		}
		if(Constants.DP_FR_ZCZJFB.equals(dicCode)){
			keys = Constants.getDpFrZczList();
		}
		if(Constants.DP_JZ_FW.equals(dicCode)){
			keys = Constants.getDpJzFwList();
		}
		if(Constants.DP_GWBYJHL.equals(dicCode)){
			keys = Constants.getDpGwbdwList();
		}
		if(Constants.DP_FRLX.equals(dicCode)){
			keys = Constants.getDpFrlxList();
		}
		if(Constants.NBXT_SYTJ.equals(dicCode)){
			keys = Constants.getNbxtSytjList();
		}
		if(Constants.DP_JHJKTJ.equals(dicCode)){
			keys = Constants.getDpJhjkList();
		}
		if(Constants.DP_30DAY_SQLTJ.equals(dicCode) || Constants.DP_30DAY_SHTGTJ.equals(dicCode) || 
				Constants.DP_30DAY_XZLTJ.equals(dicCode)){
			keys = Constants.getDp30DayTjList();
		}
		if(Constants.DP_JKYJ.equals(dicCode)){
			keys = Constants.getDpJkyjList();
		}
		if(keys.size() != sqls.length){
			logger.info("*********************统计的sql个数对应不上!*********************");
		}
		
		//Nutz每次执行完回调函数初始值还是从头开始，所以需要中间值来将变量转为final
		final List<String> finalKeys = keys;
		int index = 0;
		
		for(final String sql : sqls){ 
			//Nutz每次执行完回调函数其值从头开始，所以需要中间值来记录上一次的index
			final int finalIndex = index;
			Sql exeTotalSql = Sqls.create(sql); 
			exeTotalSql.setCallback(new SqlCallback(){
				public Object invoke(Connection conn, ResultSet rs, Sql tempsql) throws SQLException {
					long total = 0;
					while(rs.next()){  
						try {
							//如果是办公地差异化
//							if(Constants.CORP_DIF_COUNT.equals(finalKeys.get(finalIndex))){
//								if(AddressUtils.isDifAddress(String.valueOf(rs.getObject(1)), String.valueOf(rs.getObject(2)))){
//									total ++;
//								}
//								map.put(finalKeys.get(finalIndex), total); 	 							
//							}else{ 
								map.put(finalKeys.get(finalIndex), rs.getObject(1));
//							}
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
					return map;
				}
			});
			dao.execute(exeTotalSql);
			index ++;
			jsoMap = (Map<Object, Object>) exeTotalSql.getResult();
		}
		
		return Json.toJson(jsoMap);
	}
	
	/**
	 * 获取注册地与办公地差异数量.
	 * @param businessAddress 实际经营地址
	 * @param address 经营场所
	 * @return
	 */
	private Long getDifAddressCount(String sql){
		long count = 0;
		String tempSql = "select corp_info_id from corp_info";
		
		List<Object> IdList = getResultBySql(tempSql);
		Iterator<Object> iter = IdList.iterator();
		while(iter.hasNext()){
			String id = String.valueOf(iter.next()); 
			String sql1 = "select c.ADDRESS from corp_info c where c.corp_info_id='" + id +"'";
			String sql2 = "select c.BUSINESS_ADDRESS from corp_info c where c.corp_info_id='" + id +"'";
			String address = (String) getResultBySql(sql1).get(0);
			String businessAddress = (String) getResultBySql(sql2).get(0);
			//对比是否是同一个地址
			if(AddressUtils.isDifAddress(address, businessAddress)){
				count ++;
			}
		}
		//select count(*) from corp_info c where C.ADDRESS<>C.BUSINESS_ADDRESS
		return count;
	}
	
	/**
	 * 根据sql获取查询结果.
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Object> getResultBySql(String sql){
		List<Object> list = new ArrayList<Object>();
		try {
			Sql exeTotalSql = Sqls.create(sql);
			exeTotalSql.setCallback(new SqlCallback(){
				public Object invoke(Connection arg0, ResultSet rs, Sql sql)throws SQLException {
					List<Object> list = new ArrayList<Object>();
					while(rs.next()){
						list.add(rs.getObject(1));
					}
					return list;
				} 
			});
			dao.execute(exeTotalSql);
			list = (List<Object>) exeTotalSql.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取注册地与办公地差异化.
	 * @param address
	 * @param busAddress
	 * @return
	 */
	public long getDifCount(String address, String busAddress){
		long count = 0;
		if(AddressUtils.isDifAddress(address, busAddress)){
			count ++;
		}
		return count;
	}
}
