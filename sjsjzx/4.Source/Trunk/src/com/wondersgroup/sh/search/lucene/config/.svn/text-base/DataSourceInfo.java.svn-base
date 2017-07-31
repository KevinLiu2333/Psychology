/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene.config;

import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.dom4j.Element;

/**
 * The Class DataSourceInfo.
 */
public class DataSourceInfo {
	private String id;
	private String className;
	private String destroyMethod;
	private Properties properties;
	
	public DataSourceInfo() {
	}

	public DataSourceInfo(String id, String className, Properties properties) {
		this.id = id;
		this.className = className;
		this.properties = properties;
	}

	public Element toXml(Element parentEl) {
		Element dsEl = parentEl.addElement("datasource").addAttribute("id", this.id);
		for(Entry entry : this.properties.entrySet()) {
			String name = (String)entry.getKey();
			String value = (String)entry.getValue();
			if( "password".equalsIgnoreCase(name) ) {
				value = "ENC(" + LuceneConfiguration.encrypt(value) + ")";
			}
			dsEl.addElement("property")
				.addAttribute("name", name)
				.addText(value);
		}
		return dsEl;	
	}
	
	public boolean equals(Object other) {
		if( this == other )
			return true;
		
		if( !(other instanceof DataSourceInfo) )
			return false;
		
		DataSourceInfo castOther = (DataSourceInfo)other;
		return new EqualsBuilder()
			.append(this.id, castOther.id)
			.isEquals();
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("id", id)
			.append("className", className)
			.append("destroyMethod", destroyMethod)
			.append("properties", properties)
			.toString();
	}
	
	/**
	 * Gets the class name.
	 * 
	 * @return the class name
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Sets the class name.
	 * 
	 * @param className the new class name
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Gets the destroy method.
	 * 
	 * @return the destroy method
	 */
	public String getDestroyMethod() {
		return destroyMethod;
	}

	/**
	 * Sets the destroy method.
	 * 
	 * @param destroyMethod the new destroy method
	 */
	public void setDestroyMethod(String destroyMethod) {
		this.destroyMethod = destroyMethod;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Sets the properties.
	 * 
	 * @param properties the new properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
