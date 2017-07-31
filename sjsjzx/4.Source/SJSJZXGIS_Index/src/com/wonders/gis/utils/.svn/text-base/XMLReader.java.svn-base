package com.wonders.gis.utils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
public class XMLReader {
	private Element root = null ;
	private Document doc;
	private String fileName ="";
	//-------------------------------------------------------------------------
	/** 构造函数
	 * @param fileName 输入XML文件名
	 */
	public XMLReader(String fileName) {
		this.fileName = fileName;
		try {
			SAXReader reader = new SAXReader();
			doc = reader.read(new FileInputStream(new File(fileName)));
			root = doc.getRootElement();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** 构造函数
	 * @param stream 输入XML格式的stream
	 */
	public XMLReader(InputStream stream) {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(stream);
			root = doc.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 通过 xpath 获取 XML 节点的数据内容
	 * @param xpath XML 路径 sample:"//root/data"
	 * @return String 结果
	 */
	public String getValueByXPath(String xpath){
		List list = root.selectNodes(xpath);
		Element e = null ;
		if(list.size() > 0){
			e = (Element)list.get(0);
		}
		return e.getTextTrim();
	}
	
	/** 通过 xpath 获取 XML 节点的节点集
	 * @param xpath XML 路径 sample:"//root/data"
	 * @return 节点集
	 */
	public List getListByXPath(String xpath){
		return root.selectNodes(xpath);
	}
	
	/**  通过 xpath 获取 XML 节点的属性
	 * @param xpath XML 路径 sample:"//root/data"
	 * @param name 属性标签名称
	 * @return 属性的值
	 */
	public String getAtributeValue(String xpath, String name){
		List list = root.selectNodes(xpath);
		Element e = null ;
		if(list.size() > 0){
			e = (Element)list.get(0);
		}
		return e.attributeValue(name);
	}
	
	/** set 属性值
	 * @param xpath XML 路径 sample:"//root/data"
	 * @param index xpath的索引
	 * @param name 属性名称
	 * @param value 属性的值
	 */
	public void setAtributeValue(String xpath, int index, String name, String value){
		List list = root.selectNodes(xpath);
		try{
			Element e = (Element)list.get(index);
			if (e != null){
				e.attribute(name).setValue(value);
				//e.setAttributeValue(name, value);
			}
		}
		catch(Exception ex){
			
		}
	}
	
	/** 添加元素
	 * @param parentPath 父节点名称
	 * @param index 索引
	 * @param name 元素名称
	 * @return 元素节点
	 */
	public Element addElement(String parentPath, int index, String name){
		Element e = null;
		try{
			List list = root.selectNodes(parentPath);
			Element parent = (Element)list.get(index);
			e = addElement(parent, name);
		}
		catch(Exception ex){
		}
		return e;
	}	
	
	/** 添加元素
	 * @param parent 父节点
	 * @param name 元素名称
	 * @return 元素节点
	 */
	private Element addElement(Element parent, String name){
		Element e = null;
		try{
			e = parent.addElement(name);
			e.setText("");
			e.setParent(parent);
		}
		catch(Exception ex){
		}
		return e;
	}
	
	/** 添加属性
	 * @param xpath XML 路径 sample:"//root/data"
	 * @param index 索引
	 * @param name 属性名称
	 * @param value 属性的值
	 */
	public void addAtribute(String xpath, int index, String name, String value){
		try{
			List list = root.selectNodes(xpath);
			Element e = (Element)list.get(index);
			addAtribute(e, name, value);
		}
		catch(Exception ex){
		}
	}
	
	/** 添加属性
	 * @param e 节点
	 * @param name 属性名称
	 * @param value 属性的值
	 */
	private void addAtribute(Element e, String name, String value){
		try{
			e.addAttribute(name, value);
		}
		catch(Exception ex){
		}
	}	
	
	/** 设置节点的值
	 * @param xpath XML 路径 sample:"//root/data"
	 * @param value 值
	 */
	public void setTextValue(String xpath, String value){
		List list = root.selectNodes(xpath);
		try{
			Element e = (Element)list.get(0);
			if (e != null){
				e.setText(value);
			}
		}
		catch(Exception ex){
			
		}
	}	
	
	/** 保存xml文件
	 * 
	 */
	public void saveXMLFile(){
		try{
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileName), doc.asXML().length());
			OutputFormat format = new OutputFormat();
			format.setEncoding(doc.getXMLEncoding());
			format.setNewLineAfterDeclaration(true);
			format.setXHTML(true);
			XMLWriter writer = new XMLWriter(stream, format);
			writer.write(doc);
		}
		catch(Exception ex){
		}
	}
}
