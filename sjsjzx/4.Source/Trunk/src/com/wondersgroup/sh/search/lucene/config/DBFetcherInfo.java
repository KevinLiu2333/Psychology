/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene.config;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.dom4j.Element;

import com.wondersgroup.sh.search.enums.FetcherTypeEnum;

/**
 * The Class DBFetcherInfo.
 */
public class DBFetcherInfo extends FetcherInfo {
	private String datasourceId;
	private TableMapper tableMapper;
	
	public DBFetcherInfo(String id, String name, String indexId) {
		super(id, name, indexId);
	}

	protected void addElement(Element fetcherEl) {
		fetcherEl.addAttribute("type", FetcherTypeEnum.DB.getName())
			.addAttribute("xsi:type", "dbFetcherType");
		
		fetcherEl.addElement("datasource").addAttribute("ref", this.datasourceId);
		this.tableMapper.toXml(fetcherEl);
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.appendSuper(super.toString())
			.append("datasourceId", datasourceId)
			.append("tableMapper", tableMapper)
			.toString();
	}
	
	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	public TableMapper getTableMapper() {
		return tableMapper;
	}

	public void setTableMapper(TableMapper tableMapper) {
		this.tableMapper = tableMapper;
	}
}
