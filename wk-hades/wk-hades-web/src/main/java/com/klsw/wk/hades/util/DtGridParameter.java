package com.klsw.wk.hades.util;

import net.sf.json.JSONObject;

import com.klsw.wk.hades.domain.BaseModel;



/**
 * Dtgrid parameter 封装
 * @author liulixi
 * @version 2017年6月20日10:24:58
 */
public class DtGridParameter<T extends BaseModel> {
	private Boolean isExport;
	
	private int pageSize;
	
	private int startRecord;
	
	private int nowPage;
	
	private int recordCount;
	
	private int pageCount;
	
	private T parameters;
	
	// "fastQueryParameters":{},"advanceQueryConditions":[],"advanceQuerySorts":[]

	public Boolean getIsExport() {
		return isExport;
	}

	public void setIsExport(Boolean isExport) {
		this.isExport = isExport;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRecord() {
		return startRecord;
	}

	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public static void getParameters(String dtGridPager) {
		JSONObject json = JSONObject.fromObject(dtGridPager);
	}

	public void setParameters(T parameters) {
		parameters.setPage(getNowPage());
		parameters.setRows(getPageSize());
		this.parameters = parameters;
	}
}