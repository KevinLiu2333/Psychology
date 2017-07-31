/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.lucene.queryParser.QueryParser.Operator;
import org.dom4j.Element;

public class IndexInfo implements Serializable, Cloneable {
	private static final long serialVersionUID = -1474131893512062434L;
	public static final String DefaultAnalyzerClass = "org.apache.lucene.analysis.cjk.CJKAnalyzer";
	public static final String DefaultOperator_AND = "AND";
	public static final String DefaultOperator_OR = "OR";
	
	private String id;
	private String name;	
	private boolean defaultIndex;
	private boolean abstractIndex;
	private String indexPath;
	private String analyzerClass;
	private String defaultOperator;
	private DocumentInfo document;
	private String parentId;
	
	public IndexInfo() {
		super();
	}
	
	public IndexInfo(String id, String name, String indexPath) {
		this.id = id;
		this.name = name;
		this.indexPath = indexPath;
	}

	public static List<String> extractIds(List<IndexInfo> indexes) {
		List<String> retList = new ArrayList<String>();
		for(IndexInfo index : indexes) {
			retList.add(index.getId());
		}
		return retList;
	}
	
	public Object clone() throws CloneNotSupportedException {
		IndexInfo newObject = (IndexInfo)super.clone();
		newObject.document = (DocumentInfo)this.document.clone();
		return newObject;
	}

	public Operator getDefaultOperator() {
		if( IndexInfo.DefaultOperator_AND.equalsIgnoreCase(defaultOperator) ) 
			return Operator.AND;
		else if( IndexInfo.DefaultOperator_OR.equalsIgnoreCase(defaultOperator) )
			return Operator.OR;
		else
			return Operator.AND;
	}
	
	public Element toXml(Element parentEl) {
		Element indexEl = parentEl.addElement("index")
			.addAttribute("id", this.id)
			.addAttribute("name", this.name);

		if( this.defaultIndex ) { 
			indexEl.addAttribute("default", Boolean.toString(this.defaultIndex));
		}
		
		if( StringUtils.isBlank(this.parentId) ) {
			if( this.abstractIndex ) {
				indexEl.addAttribute("abstract", Boolean.toString(this.abstractIndex));
			}
			indexEl.addElement("path").addText(this.indexPath);
			indexEl.addElement("analyzer").addAttribute("impl", this.analyzerClass);
			indexEl.addElement("QueryParser").addAttribute("defaultOperator", this.defaultOperator);
			this.document.toXml(indexEl);
		}
		else {
			indexEl.addAttribute("parent", this.parentId);
		}
		return indexEl;
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("id", id)
			.append("name", name)
			.append("indexPath", indexPath)
			.append("analyzerClass", analyzerClass)
			.append("parentId", parentId)
			.append("document", document)
			.toString();
	}
	
	public String getAnalyzerClass() {
		return analyzerClass;
	}

	public void setAnalyzerClass(String analyzerClass) {
		this.analyzerClass = analyzerClass;
	}

	public DocumentInfo getDocument() {
		return document;
	}
	
	public void setDocument(DocumentInfo document) {
		this.document = document;
	}
	
	public String getIndexPath() {
		return indexPath;
	}
	
	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDefaultIndex() {
		return defaultIndex;
	}

	public void setDefaultIndex(boolean defaultIndex) {
		this.defaultIndex = defaultIndex;
	}

	public void setDefaultOperator(String defaultOperator) {
		this.defaultOperator = defaultOperator;
	}

	public boolean isAbstractIndex() {
		return abstractIndex;
	}

	public void setAbstractIndex(boolean abstractIndex) {
		this.abstractIndex = abstractIndex;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
