package com.wondersgroup.sh.search.lucene.config;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.dom4j.Element;

public class EraserInfo {
	/** The id. */
	private String id;
	
	/** The name. */
	private String name;
	
	/** The impl class. */
	private String implClass;
	
	/** The params. */
	private Map params;

	public EraserInfo() {
	}

	public EraserInfo(String id, String name, String implClass, Map params) {
		this.id = id;
		this.name = name;
		this.implClass = implClass;
		this.params = params;
	}

	public Element toXml(Element parentEl) {
		Element eraserEl = parentEl.addElement("eraser").addAttribute("impl", this.implClass);
		LuceneConfiguration.paramToXml(eraserEl, this.params);
		return eraserEl;
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("implClass", implClass)
			.append("params", params)
			.toString();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImplClass() {
		return implClass;
	}

	public void setImplClass(String implClass) {
		this.implClass = implClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}
	
	
}
