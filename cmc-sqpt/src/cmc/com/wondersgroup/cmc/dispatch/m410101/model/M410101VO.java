package com.wondersgroup.cmc.dispatch.m410101.model;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class M410101VO implements ValueObject {
    private static final long serialVersionUID = -7839807000163835033L;
	private Long iftransdetailid;
    private String iftype;
    private String ifdefineid;
    private String ifsubtype;
    private String dataext;
    private String jsonData;
    private String queryText;
    private String key;
    private String ifuri;
    private Long ifuserid;
	private String ifdefineids;
	private String ifuserids;
	public Long getIftransdetailid() {
		return iftransdetailid;
	}
	public void setIftransdetailid(Long iftransdetailid) {
		this.iftransdetailid = iftransdetailid;
	}
	public String getIftype() {
		return iftype;
	}
	public void setIftype(String iftype) {
		this.iftype = iftype;
	}
	public String getIfdefineid() {
		return ifdefineid;
	}
	public void setIfdefineid(String ifdefineid) {
		this.ifdefineid = ifdefineid;
	}
	public String getIfsubtype() {
		return ifsubtype;
	}
	public void setIfsubtype(String ifsubtype) {
		this.ifsubtype = ifsubtype;
	}
	public String getDataext() {
		return dataext;
	}
	public void setDataext(String dataext) {
		this.dataext = dataext;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public String getQueryText() {
		return queryText;
	}
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getIfuri() {
		return ifuri;
	}
	public void setIfuri(String ifuri) {
		this.ifuri = ifuri;
	}
	public Long getIfuserid() {
		return ifuserid;
	}
	public void setIfuserid(Long ifuserid) {
		this.ifuserid = ifuserid;
	}
	public String getIfdefineids() {
		return ifdefineids;
	}
	public void setIfdefineids(String ifdefineids) {
		this.ifdefineids = ifdefineids;
	}
	public String getIfuserids() {
		return ifuserids;
	}
	public void setIfuserids(String ifuserids) {
		this.ifuserids = ifuserids;
	}
}
