package com.wonders.wddc.suite.data.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;

import net.sf.json.JSONObject;

/**
 * sql处理类
 * @author dwl
 *
 */
public class SqlProcessor {
	
	/**
	 * 处理占位符的sql
	 * @param originalSql
	 * @param param
	 * @return
	 * @throws ParseException
	 */
	public static Sql toDealSql(String originalSql,String param) throws ParseException{
		Sql sql = Sqls.create(originalSql);
		String[] temp = null;
		String tempSql = originalSql;
		if(originalSql != null&& originalSql !=""){
			//判断字符串中是否含有'@'符
			//将紧挨@符后的字符串保存打list中
			//将字符串对应的数据从request中取出来
			//如果以字母D开头，将其转化为Date类型
			if(-1<(originalSql.indexOf("@"))){
				temp = originalSql.split("@");
				//@符号的个数
				int count = temp.length-1;
				//处理@符号
				//修改，动参统一传一个json过来，类似 param:"{S_sex:'1'}"
				JSONObject jsonObject = JSONObject.fromObject(param);
				for(int i=1;i<=count;i++){
					//获取@符以及其后面紧邻的字符串
					int tempIndex = tempSql.indexOf("@");
					tempSql = tempSql.substring(tempIndex);
					//第一个空格出现的位置
					int trimIndex = tempSql.indexOf(" ");
					//@后面紧邻的字符串
					String parameterStr = tempSql.substring(1,trimIndex);
					if(parameterStr.startsWith("D_")){
						Date dateParameter = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObject.getString(parameterStr));
						sql.params().set(parameterStr, dateParameter);
					}else {
						sql.params().set(parameterStr, jsonObject.get(parameterStr));
					}
					tempSql = tempSql.substring(trimIndex);
				}
				
			}
			
		}
		return sql;
	}
}
