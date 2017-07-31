/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene.config;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.dom4j.Element;
import org.dom4j.dom.DOMCDATA;

public class TableMapper {
	private String tableName;
	private String condition;
	private List<FieldMapper> fieldMappers;
	private List<EntityBridgeInfo> entityBridges;
	private String select;
	private String order;
	
	public TableMapper() {
	}

	public TableMapper(String tableName) {
		this.tableName = tableName;
	}

	public FieldMapper getFieldMapper(String fieldName) {
		for (Iterator iter = fieldMappers.iterator(); iter.hasNext();) {
			FieldMapper fieldMapper = (FieldMapper) iter.next();
			if( fieldName.equalsIgnoreCase(fieldMapper.getFieldName()) )
				return fieldMapper;
		}
		return null;
	}
	
	public String constructSQL() {
		String retSql = StringUtils.isNotBlank(select) ? select : "select * from " + tableName;
		boolean hasWhere = retSql.toLowerCase().indexOf("where") > 0;
		if( StringUtils.isNotBlank(condition) ) {
			retSql += (hasWhere ? " and " : " where ") + condition; 
		}
		else {
			retSql += hasWhere ? "" : " where 1=1 "; 
		}
		return retSql;
	}
	
	public String constructIncrementSQL(String documentIdName) {
		return "select * from (" + this.constructSQL() + ") where " + getFieldMapper(documentIdName).getSourceName() + "=?";
	}
	
	public Element toXml(Element parentEl) {
		Element tableEl = parentEl.addElement("table").addAttribute("name", this.tableName);
		if( StringUtils.isNotBlank(this.select) ) {
			tableEl.addElement("select").add(new DOMCDATA(this.select));
		}
		
		if( StringUtils.isNotBlank(this.condition) ) {
			tableEl.addElement("condition").addText(this.condition);
		}
		
		Element fieldsEl = tableEl.addElement("fields");
		for( FieldMapper mapper : this.fieldMappers ) {
			mapper.toXml(fieldsEl);
		}

		if( this.entityBridges != null && !this.entityBridges.isEmpty() ) {
			Element bridgesEl = parentEl.addElement("bridges");
			for( EntityBridgeInfo bridge : this.entityBridges ) {
				bridge.toXml(bridgesEl);
			}
		}
		return tableEl;
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("tableName", tableName)
			.append("condition", condition)
			.append("fieldMappers", fieldMappers)
			.append("entityBridges", entityBridges)
			.append("select", select)
			.toString();
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<FieldMapper> getFieldMappers() {
		return fieldMappers;
	}

	public void setFieldMappers(List fieldMappers) {
		this.fieldMappers = fieldMappers;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<EntityBridgeInfo> getEntityBridges() {
		return entityBridges;
	}

	public void setEntityBridges(List<EntityBridgeInfo> entityBridges) {
		this.entityBridges = entityBridges;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
