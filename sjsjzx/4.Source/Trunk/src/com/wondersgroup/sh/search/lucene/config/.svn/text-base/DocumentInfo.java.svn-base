/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene.config;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.dom4j.Element;

/**
 * The Class DocumentInfo.
 */
public class DocumentInfo implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private FieldInfo documentId;
	private FieldInfo[] fields;

	public DocumentInfo() {
		super();
	}

	public Object clone() throws CloneNotSupportedException {
		DocumentInfo newObject = (DocumentInfo)super.clone();
		newObject.documentId = (FieldInfo)this.documentId.clone();
		if( fields != null ) {
			newObject.fields = new FieldInfo[this.fields.length];
			for (int i = 0; i < fields.length; i++) {
				newObject.fields[i] = (FieldInfo)fields[i].clone();
			}
		}
		return newObject;
	}
	
	public FieldInfo getDefaultField() {
		for (int i = 0; i < fields.length; i++) {
			FieldInfo fieldInfo = fields[i];
			if( fieldInfo.isDefaultField() )
				return fieldInfo;
		}
		return null;
	}
	
	public FieldInfo getField(String name) {
		for (int i = 0; i < fields.length; i++) {
			FieldInfo field = fields[i];
			if( name.equalsIgnoreCase(field.getName()) )
				return field;
		}
		return null;
	}
	
	public Element toXml(Element parenetEl) {
		Element fieldsEl = parenetEl.addElement("fields");
		for(FieldInfo field : this.fields) {
			field.toXml(fieldsEl, this.documentId);
		}	
		return fieldsEl;
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("documentId", documentId)
			.append("fields", fields)
			.toString();
	}
	
	public FieldInfo getDocumentId() {
		return documentId;
	}

	public void setDocumentId(FieldInfo documentId) {
		this.documentId = documentId;
	}

	public FieldInfo[] getFields() {
		return fields;
	}

	public void setFields(FieldInfo[] fields) {
		this.fields = fields;
	}
}
