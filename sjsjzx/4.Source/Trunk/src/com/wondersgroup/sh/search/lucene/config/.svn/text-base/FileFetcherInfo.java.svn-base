/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene.config;

import org.dom4j.Element;

import com.wondersgroup.sh.search.enums.FetcherTypeEnum;

/**
 * The Class FileFetcherInfo.
 */
public class FileFetcherInfo extends FetcherInfo {
	private String datasourceId;
	private String filePath;
	
	public FileFetcherInfo(String id, String name, String indexId) {
		super(id, name, indexId);
	}
	
	public FileFetcherInfo(String id, String name, String indexId, String filePath) {
		super(id, name, indexId);
		this.filePath = filePath;
	}

	protected void addElement(Element fetcherEl) {
		fetcherEl.addAttribute("type", FetcherTypeEnum.FILE.getName())
			.addAttribute("xsi:type", "fileFetcherType");
		fetcherEl.addElement("path").addText(this.filePath);
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	public String getDatasourceId() {
		return datasourceId;
	}

}
