package com.wonders.xml2db.xml.operator;

import java.io.File;

import org.dom4j.Element;

public interface IReadXml{
	
	/**获取xml根节点**/
	public Element getXmlRoot();
	
	/**获取xml根节点**/
	public Element getXmlRoot(File file);
	
	/**循环读取xml数据到数据库**/
	public void readXml(Element root);
	
	/**遍历并读取指定目录下的xml文件**/
	public void readXmlsByPath(String path);

}
