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

import com.wondersgroup.sh.search.enums.FetcherTypeEnum;
import com.wondersgroup.sh.search.sync.DefaultDBSynchro;

/**
 * The Class SynchroInfo.
 */
public class SynchroInfo {
	public static final String DefaultDBSynchroImpl = DefaultDBSynchro.class.getName();

	private String id;
	private String name;
	private String implClass;
	private Map params;
	
	public SynchroInfo() {
	}

	public SynchroInfo(String id, String name, String implClass, Map params) {
		this.id = id;
		this.name = name;
		this.implClass = implClass;
		this.params = params;
	}

	public static SynchroInfo newDefaultSynchroInfo(FetcherTypeEnum type) {
		SynchroInfo synchroInfo = new SynchroInfo();
		if( FetcherTypeEnum.DB.equals(type) ) {
			synchroInfo.setId("defaultDBSynchro");
			synchroInfo.setName("defaultDBSynchro");
			synchroInfo.setImplClass(DefaultDBSynchroImpl);
		}
		
		return synchroInfo;
	}
	
	public Element toXml(Element parentEl) {
		Element synchroEl = parentEl.addElement("synchro").addAttribute("impl", this.implClass);
		LuceneConfiguration.paramToXml(synchroEl, this.params);
		return synchroEl;
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

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}
}
