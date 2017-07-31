/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene.config;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.dom4j.Element;

import com.wondersgroup.sh.search.enums.FetcherTypeEnum;
import com.wondersgroup.sh.search.lucene.DBFetcher;
import com.wondersgroup.sh.search.lucene.Fetcher;
import com.wondersgroup.sh.search.lucene.FileFetcher;
import com.wondersgroup.sh.search.lucene.LuceneSearchException;

/**
 * The Class FetcherInfo.
 */
public abstract class FetcherInfo {
	protected String id;
	protected String name;
	protected String indexId;
	protected SynchroInfo synchro;
	protected EraserInfo eraser;
	protected TaskInfo task;
	
	protected FetcherInfo(String id, String name, String indexId) {
		this.id = id;
		this.name = name;
		this.indexId = indexId;
	}

	public static FetcherInfo newInstance(String id, String name, String type, String indexId) {
		FetcherInfo fetcherInfo = null;
		FetcherTypeEnum typeEnum = FetcherTypeEnum.getEnum(type.toUpperCase());
		if( FetcherTypeEnum.DB.equals(typeEnum) ) {
			fetcherInfo = new DBFetcherInfo(id, name, indexId);
		}
		else if( FetcherTypeEnum.FILE.equals(typeEnum) ) {
			fetcherInfo = new FileFetcherInfo(id, name, indexId);
		}
		else {
			throw new IllegalArgumentException("未知的Fetcher类型:" + type);
		}
		
		return fetcherInfo;
	}
	
	public boolean equals(Object other) {
		if( this == other )
			return true;
		
		if( !(other instanceof FetcherInfo) )
			return false;
		
		FetcherInfo castOther = (FetcherInfo)other;
		return new EqualsBuilder()
			.append(this.id, castOther.id)
			.isEquals();
	}
	
	public Element toXml(Element parentEl) {
		Element fetcherEl = parentEl.addElement("fetcher").addAttribute("id", this.id);
		fetcherEl.addElement("index").addAttribute("ref", this.indexId);
		this.addElement(fetcherEl);
		
		if( this.synchro != null ) {
			this.synchro.toXml(fetcherEl);
		}

		if( this.eraser != null ) {
			this.eraser.toXml(fetcherEl);
		}
		
		if( this.task != null ) {
			this.task.toXml(fetcherEl);
		}
		return fetcherEl;
	}
	
	protected abstract void addElement(Element fetcherEl);
	
	public Fetcher newFetcherInstance(LuceneConfiguration configuration) {
		if( this instanceof DBFetcherInfo ) 
			return new DBFetcher(configuration, id);
		else if( this instanceof FileFetcherInfo )
			return new FileFetcher(configuration, id);
		else
			throw new LuceneSearchException("未知的FetcherInfo类型。");
	}
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("id", id)
			.append("name", name)
			.append("indexId", indexId)
			.append("synchro", synchro)
			.append("eraser", eraser)
			.toString();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SynchroInfo getSynchro() {
		return synchro;
	}

	public void setSynchro(SynchroInfo synchro) {
		this.synchro = synchro;
	}

	public EraserInfo getEraser() {
		return eraser;
	}

	public void setEraser(EraserInfo eraser) {
		this.eraser = eraser;
	}

	public TaskInfo getTask() {
		return task;
	}

	public void setTask(TaskInfo task) {
		this.task = task;
	}
}
