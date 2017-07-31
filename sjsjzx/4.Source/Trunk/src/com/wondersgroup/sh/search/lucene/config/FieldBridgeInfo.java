/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene.config;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.dom4j.Element;

/**
 * The Class FieldBridgeInfo.
 */
public class FieldBridgeInfo {
	protected String className;
	protected Map parameters;
	
	public FieldBridgeInfo() {
	}

	public FieldBridgeInfo(String className, Map parameters) {
		this.className = className;
		this.parameters = parameters;
	}

	public Element toXml(Element parentEl) {
		Element bridgeEl = parentEl.addElement("bridge").addAttribute("impl", this.className);
		LuceneConfiguration.paramToXml(bridgeEl, this.parameters);
		return bridgeEl;	
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("className", className)
			.append("parameters", parameters)
			.toString();
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
}
