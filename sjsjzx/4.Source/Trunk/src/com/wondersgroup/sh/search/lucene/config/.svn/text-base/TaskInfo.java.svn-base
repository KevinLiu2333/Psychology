package com.wondersgroup.sh.search.lucene.config;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.dom4j.Element;

public class TaskInfo {
	private String id;
	private String name;
	private String implClass;
	private Map<String, String> params;
	
	public TaskInfo() {
	}

	public TaskInfo(String id, String name, String implClass, Map<String, String> params) {
		this.id = id;
		this.name = name;
		this.implClass = implClass;
		this.params = params;
	}

	public Element toXml(Element parentEl) {
		Element taskEl = parentEl.addElement("task").addAttribute("id", id);
		taskEl.addElement("task-class").addText(this.implClass);
		LuceneConfiguration.paramToXml(taskEl, this.params);
		return taskEl;
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("id", id)
			.append("name", name)
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImplClass() {
		return implClass;
	}

	public void setImplClass(String implClass) {
		this.implClass = implClass;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
