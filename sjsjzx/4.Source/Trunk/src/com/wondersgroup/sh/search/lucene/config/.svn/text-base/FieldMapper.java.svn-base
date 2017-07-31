/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene.config;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.dom4j.Element;

/**
 * The Class FieldMapper.
 */
public class FieldMapper {
	
	/** The field name. */
	private String fieldName;
	
	/** The source name. */
	private String sourceName;
	
	/** The resolution. */
	private String resolution;
	
	/** The discriminator. */
	private String discriminator;
	
	/** The bridge. */
	private FieldBridgeInfo bridge;
	
	/** const value */
	private String value;
	
	/**
	 * Instantiates a new field mapper.
	 */
	public FieldMapper() {
	}

	/**
	 * Instantiates a new field mapper.
	 * 
	 * @param fieldName the field name
	 * @param sourceName the source name
	 * @param bridge the bridge
	 */
	public FieldMapper(String fieldName, String sourceName, FieldBridgeInfo bridge) {
		this.fieldName = fieldName;
		this.sourceName = sourceName;
		this.bridge = bridge;
	}

	public Element toXml(Element parentEl) {
		Element fieldEl = parentEl.addElement("field").addAttribute("name", this.fieldName);
		if( !this.fieldName.equals(this.sourceName) )
			fieldEl.addAttribute("column", this.sourceName);
		
		if( StringUtils.isNotBlank(this.value) ) 
			fieldEl.addAttribute("value", this.value);
		
		if( StringUtils.isNotBlank(this.discriminator) ) 
			fieldEl.addAttribute("discriminator", this.discriminator);
		
		if( this.bridge != null ) {
			this.bridge.toXml(fieldEl);
		}		
		return fieldEl;
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("fieldName", fieldName)
			.append("sourceName", sourceName)
			.append("resolution", resolution)
			.append("discriminator", discriminator)
			.append("bridge", bridge)
			.append("value", value)
			.toString();
	}
	
	/**
	 * Gets the bridge.
	 * 
	 * @return the bridge
	 */
	public FieldBridgeInfo getBridge() {
		return bridge;
	}

	/**
	 * Sets the bridge.
	 * 
	 * @param bridge the new bridge
	 */
	public void setBridge(FieldBridgeInfo bridge) {
		this.bridge = bridge;
	}

	/**
	 * Gets the field name.
	 * 
	 * @return the field name
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Sets the field name.
	 * 
	 * @param fieldName the new field name
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Gets the source name.
	 * 
	 * @return the source name
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * Sets the source name.
	 * 
	 * @param sourceName the new source name
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	 * Gets the discriminator.
	 * 
	 * @return the discriminator
	 */
	public String getDiscriminator() {
		return discriminator;
	}

	/**
	 * Sets the discriminator.
	 * 
	 * @param discriminator the new discriminator
	 */
	public void setDiscriminator(String discriminator) {
		this.discriminator = discriminator;
	}

	/**
	 * Gets the resolution.
	 * 
	 * @return the resolution
	 */
	public String getResolution() {
		return resolution;
	}

	/**
	 * Sets the resolution.
	 * 
	 * @param resolution the new resolution
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
