package com.wondersgroup.cmc.utils.pagequery;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.struts2.ServletActionContext;

import com.wondersgroup.framework.core5.business.context.Environment;
import com.wondersgroup.framework.core5.business.context.UserContext;
import com.wondersgroup.framework.core5.business.context.support.BusinessContextUtils;

public class JdbcDaoLogUtils {
	private static UserContext userContext;
	private static Environment environment;
	private static String url;
	private static String businessType;
	private static String operatorId;
	private static String operatorName;
	
	 
	public static void setContext(){
		userContext = BusinessContextUtils.getUserContext();
		environment = BusinessContextUtils.getEnvironment();
		url = "";
		if(ServletActionContext.getRequest()!=null){
			url = StringUtils.isNotEmpty(ServletActionContext.getRequest().getRequestURI()) ? ServletActionContext.getRequest().getRequestURI() : "" ;
		}
		businessType = StringUtils.isNotEmpty(BusinessContextUtils.getEnvironment().getBusinessType()) ? BusinessContextUtils.getEnvironment().getBusinessType() : "";
		operatorId = StringUtils.isNotEmpty(userContext.getOperatorId()) ? userContext.getOperatorId() : "";
		operatorName = StringUtils.isNotEmpty(userContext.getOperatorName()) ? userContext.getOperatorName() : "";
	}
	
	public static void doLog(Log log, String content){
		setContext();
		 
		if(log.isInfoEnabled()) {
			 log.info(String.format("[t-%d][%s(%s)~%s~%s][%s]%s  \n└─ %s", new Object[] {
					Long.valueOf(Thread.currentThread().getId()), operatorName, operatorId, environment.getIpAddress(), environment.getMacAddress(), businessType, url,
					content
			 }));
		}
	}
	
	public static void doLog(Log log, long costtime, String pretext, String sql, String args){
		doLog(log, costtime, pretext, sql, args, true);
	}
	
	public static void doLog(Log log, long costtime, String pretext, String sql, String args, boolean success){
		 
		 setContext();
		 String flag = success?"√":"×";
		 int timeAsLongLog = 50000; //长时间判断   
		 
		 
		 if(log.isInfoEnabled()) {
			 log.info(String.format("___JDBC SQL EXECUTED__ [" + flag + "][%dms][t-%d][%s(%s)~%s~%s][%s]%s  \n└─[%s] sql=[%s] ;args=[%s] \n└─[★SQL★] %s", new Object[] {
					Long.valueOf(costtime), Long.valueOf(Thread.currentThread().getId()), operatorName, operatorId, environment.getIpAddress(), environment.getMacAddress(), businessType, url,
					pretext, sql, args, beautifySQL(sql, args)
			 }));
			 
			 //长时间处理
			 if(costtime > timeAsLongLog) {
				 log.info(String.format("___JDBC SQL EXECUTED__ [" + flag + "!!!][t-%dms][%d][%s(%s)~%s~%s][%s]%s  \n└─[%s] sql=[%s] ;args=[%s] \n└─[★SQL★] %s", new Object[] {
						Long.valueOf(costtime), Long.valueOf(Thread.currentThread().getId()), operatorName, operatorId, environment.getIpAddress(), environment.getMacAddress(), businessType, url,
						pretext, sql, args, beautifySQL(sql, args)
					}));
			 }
		}
	}

	//*************************************************//
	
	public static String beautifySQL(String sql, String args) {
		
		//System.out.println("sql==" + sql + "  args=="  + args);
		if(StringUtils.isEmpty(sql)){
			return "";
		}
		
		if(sql.indexOf("?")<0){
			return sql;
		}
		//只处理带?语句
		sql = sql+ " "; //处理后空格后确保最后带1空格
		
		sql = replaceStr(sql, "?", "#_!");
		String[] str = sql.split("#_!");
		int fcount = str.length -1; // ?的个数
		
		//args [...]
		String _args = args.substring(1, args.length()-1);
		String[] values = _args.split(",");
		
		if(values.length != fcount) {
			return "*** SQL 参数个数不符! ";
		}
		
		String rtn = ""; 
		for(int i=0; i<fcount; i++){
			String value = values[i];
			//处理双引号
			value = replaceStr(value, "\"", "\'");
			rtn = rtn + str[i] + value;
		}
		rtn = rtn + str[fcount];
		
		
		
		return rtn;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String arrayToString(Object[] obj){
		ArrayList list = new ArrayList();
		for(int i=0; i<obj.length; i++){
			String value = obj[i].toString();
			String par = "";
			if("java.lang.String".equals(obj[i].getClass().getName())){
				par = "\"" +  value + "\"";
			}else{
				par = value;
			}
			
			list.add(par);
		}
		
		return list.toString();
	}
	
	public static String replaceStr(String _strSrc, String _strOld, String _strNew){
		if(_strSrc == null){
			return null;
		}
		char srcBuff[] = _strSrc.toCharArray();
		int nSrcLen = srcBuff.length;
		if(nSrcLen == 0) {
			return "";
		}
		char oldStrBuff[] = _strOld.toCharArray();
		int nOldStrLen = oldStrBuff.length;
		if(nOldStrLen == 0 || nOldStrLen > nSrcLen) {
			return _strSrc;
		}
		StringBuffer retBuff = new StringBuffer(nSrcLen * (1 + _strNew.length() / nOldStrLen));
		boolean bIsFound = false;
		for(int i = 0; i < nSrcLen;){
			bIsFound = false;
			if(srcBuff[i] == oldStrBuff[0]){
				int j;
				for(j = 1; j < nOldStrLen && i + j < nSrcLen && srcBuff[i + j] == oldStrBuff[j]; j++);
				bIsFound = j == nOldStrLen;
			}
			if(bIsFound){
				retBuff.append(_strNew);
				i += nOldStrLen;
			} else {
				int nSkipTo;
				if(i + nOldStrLen >= nSrcLen) {
					nSkipTo = nSrcLen - 1;
				} else {
					nSkipTo = i;
				}
				while(i <= nSkipTo) {
					retBuff.append(srcBuff[i]);
					i++;
				}
			}
		}

		return retBuff.toString();
	}
}
