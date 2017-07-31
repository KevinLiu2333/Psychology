/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.sync;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.wondersgroup.sh.search.enums.SyncTypeEnum;

public class SyncInfo implements Serializable {
	private static final long serialVersionUID = 1507580208402723995L;
	
	protected Object id;
	protected Object syncId;
	protected SyncTypeEnum type;
	
	/**
	 * Instantiates a new sync info.
	 * 
	 * @param id the id
	 * @param syncId the sync id
	 * @param type the type
	 */
	public SyncInfo(Object id, Object syncId, SyncTypeEnum type) {
		this.id = id;
		this.syncId = syncId;
		this.type = type;
	}

	public static List getIds(List syncInfos) {
		if( syncInfos == null || syncInfos.isEmpty() )
			return new ArrayList();
		
		List ids = new ArrayList();
		for (Iterator iter = syncInfos.iterator(); iter.hasNext();) {
			SyncInfo syncInfo = (SyncInfo) iter.next();
			ids.add(syncInfo.getId());
		}
		return ids;
	}
	

	public String toString() { 
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("id", id)
			.append("syncId", syncId)
			.append("type", type)
			.toString();
	}
	
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getSyncId() {
		return syncId;
	}

	public void setSyncId(Object syncId) {
		this.syncId = syncId;
	}

	public SyncTypeEnum getType() {
		return type;
	}

	public void setType(SyncTypeEnum type) {
		this.type = type;
	}
}
