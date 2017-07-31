/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene.config;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.lucene.document.Field;
import org.dom4j.Element;

import com.wondersgroup.sh.search.bridge.FieldBridge;
import com.wondersgroup.sh.search.bridge.LuceneOptions;
import com.wondersgroup.sh.search.enums.IndexEnum;
import com.wondersgroup.sh.search.enums.StoreEnum;
import com.wondersgroup.sh.search.enums.TermVectorEnum;
import com.wondersgroup.sh.search.enums.TypeEnum;

/**
 * The Class FieldInfo.
 */
public class FieldInfo implements LuceneOptions, Serializable, Cloneable {
	private static final long serialVersionUID = -2197005054157018073L;
	private String name;
	private boolean defaultField;
	private StoreEnum store = StoreEnum.NO;
	private IndexEnum index = IndexEnum.TOKENIZED;
	private TermVectorEnum termVector = TermVectorEnum.NO;
	private float boost = 1.0f;
	private TypeEnum type;
	private String resolution;
	private FieldBridge bridge;
	private String analyzerClass;
	private boolean documentId;
	
	public FieldInfo(String name, boolean defaultField, StoreEnum store, IndexEnum index, TermVectorEnum termVector, float boost) {
		this.name = name;
		this.defaultField = defaultField;
		this.store = store;
		this.index = index;
		this.termVector = termVector;
		this.boost = boost;
	}

	public Object clone() throws CloneNotSupportedException {
		FieldInfo newObject = (FieldInfo)super.clone();
		return newObject;
	}
	
	public Element toXml(Element parentEl, FieldInfo documentId) {
		Element fieldEl = parentEl.addElement("field")
			.addAttribute("name", this.name)
			.addAttribute("index", this.index.getName())
			.addAttribute("store", this.store.getName())
			.addAttribute("termVector", this.termVector.getName());

		if( this.defaultField ) {
			fieldEl.addAttribute("default", Boolean.TRUE.toString());
		}

		if( TypeEnum.DATE.equals(this.type) ) {
			fieldEl.addAttribute("type", TypeEnum.DATE.getName()).addAttribute("resolution", this.resolution);
		}

		if( documentId.getName().equals(this.name) ) {
			fieldEl.addAttribute("id", Boolean.TRUE.toString());
		}
		
		if( StringUtils.isNotBlank(analyzerClass) ) {
			fieldEl.addAttribute("analyzer", analyzerClass);
		}
		return fieldEl;
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("name", name)
			.append("defaultField", defaultField)
			.append("store", store.getName())
			.append("index", index.getName())
			.append("termVector", termVector.getName())
			.append("boost", boost)
			.append("type", type)
			.append("resolution", resolution)
			.append("analyzer", analyzerClass)
			.toString();
	}
	
	public Float getBoost() {
		return new Float(boost);
	}

	public Field.Store getStore() {
		return store.luceneFieldStore();
	}
	
	public Field.Index getIndex() {
		return index.luceneFieldIndex();
	}
	
	public Field.TermVector getTermVector() {
		return termVector.luceneTermVector();
	}

	public void setBoost(float boost) {
		this.boost = boost;
	}

	public FieldBridge getBridge() {
		return bridge;
	}

	public void setBridge(FieldBridge bridge) {
		this.bridge = bridge;
	}

	public boolean isDefaultField() {
		return defaultField;
	}

	public void setDefaultField(boolean defaultField) {
		this.defaultField = defaultField;
	}

	public void setIndex(IndexEnum index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStore(StoreEnum store) {
		this.store = store;
	}

	public void setTermVector(TermVectorEnum termVector) {
		this.termVector = termVector;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public String getAnalyzerClass() {
		return analyzerClass;
	}

	public void setAnalyzerClass(String analyzerClass) {
		this.analyzerClass = analyzerClass;
	}

	public boolean isDocumentId() {
		return documentId;
	}

	public void setDocumentId(boolean documentId) {
		this.documentId = documentId;
	}
}
