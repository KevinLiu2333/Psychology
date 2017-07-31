/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * The Class QueryStringComposer.
 */
public class QueryStringComposer {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(QueryStringComposer.class);
	
	/**
	 * Compose.
	 * 
	 * @param and the and
	 * @param or the or
	 * @param full the full
	 * @param not the not
	 * 
	 * @return the string
	 */
	public String compose(String and, String or, String full, String not) {
		 String queryKey="";
		 //+a +b
		 if(StringUtils.isNotBlank(and)){
			 String[] temp = and.split("\\s+");
			 for(int i=0;i<temp.length;i++){
				 queryKey +="+" + temp[i] + " " ;
			 }
		 }
		 //a b
		 if(StringUtils.isNotBlank(or)){
			 String[] temp = or.split("\\s+");
			 for(int i=0;i<temp.length;i++){
				 queryKey+= temp[i] + " ";
			 }
		 }	
		 //"a b c d"
		 if(StringUtils.isNotBlank(full)){
			 queryKey += "\"" + full + "\"";
		 }
		 //-a -b
		 if(StringUtils.isNotBlank(not)){
			 String[] temp = not.split("\\s+");
			 for(int i=0;i<temp.length;i++){
				 queryKey +="-" + temp[i] + " " ;
			 }
		 }
		 logger.debug("queryKey="+queryKey);
		
		 return queryKey;
	}
	
}
