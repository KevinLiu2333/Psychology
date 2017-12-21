package com.wonders.xml2db.xml.operator;

import org.dom4j.Element;

import com.wonders.utils.PropertyUtils;

public class Operator {
	public static void main(String args[]){
		Operator.execute();
	}
	/**
	 * 执行读取增量xml数据到数据库.
	 */
	public static void execute(){
//		Element root = Factory.getIOperateInstance().getXmlRoot();
//		Factory.getIOperateInstance().readXml(root); 
		
		String path = PropertyUtils.getProperty("filePath");
		Factory.getIOperateInstance().readXmlsByPath(path);
	}
	
	
}
