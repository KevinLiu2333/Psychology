package com.wonders.ws.receive.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("PT_MZJ_SHEQU_GETAFFAIRSDEFINE")
public class CommunityAcceptanceAffairsDefineBean {
	/**
	*事项编码
	**/
	@Column("ID")
	private String affairscode;
	/**
	*事项名称
	**/
	@Column("AFFAIRSNAME")
	private String affairsname;
	/**
	*事项所属委办局
	**/
	@Column("ORGANCODE")
	private String organcode;
	/**
	*事项所属委办局名称
	**/
	@Column("ORAGANNAME")
	private String oraganname;
	/**
	*事务流转类型
	**/
	@Column("AFFAIRSTOPERTYPE")
	private String affairstopertype;
	/**
	*事务流转类型名称
	**/
	@Column("AFFAIRSTOPERTYPENAME")
	private String affairstopertypename;
	/**
	*承诺办结时限
	**/
	@Column("PROMISEDURATION")
	private String promiseduration;
	/**
	*办结时限类型
	**/
	@Column("PROMISEDURATIONTYPE")
	private String promisedurationtype;
	/**
	*办结时限类型描述
	**/
	@Column("PROMISEDURATIONTYPENAME")
	private String promisedurationtypename;
	/**
	*父事项代码
	**/
	@Column("PARENTCODE")
	private String parentcode;
	/**
	*通办范围
	**/
	@Column("USERANGE")
	private String userange;
	/**
	*事项上线状态
	**/
	@Column("ONLINESTATUS")
	private String onlinestatus;
	/**
	*事项上线状态描述
	**/
	@Column("ONLINESTATUSNAME")
	private String onlinestatusname;
	/**
	*状态更新日期
	**/
	@Column("OPERATORTIME")
	private String operatortime;
	/**
	*null
	**/
	@Column("ORGANNAME")
	private String	organname;

	public String getAffairscode() {
		return affairscode;
	}

	public void setAffairscode(String affairscode) {
		this.affairscode = affairscode;
	}

	public String getAffairsname() {
		return affairsname;
	}

	public void setAffairsname(String affairsname) {
		this.affairsname = affairsname;
	}

	public String getOrgancode() {
		return organcode;
	}

	public void setOrgancode(String organcode) {
		this.organcode = organcode;
	}

	public String getOraganname() {
		return oraganname;
	}

	public void setOraganname(String oraganname) {
		this.oraganname = oraganname;
	}

	public String getAffairstopertype() {
		return affairstopertype;
	}

	public void setAffairstopertype(String affairstopertype) {
		this.affairstopertype = affairstopertype;
	}

	public String getAffairstopertypename() {
		return affairstopertypename;
	}

	public void setAffairstopertypename(String affairstopertypename) {
		this.affairstopertypename = affairstopertypename;
	}

	public String getPromiseduration() {
		return promiseduration;
	}

	public void setPromiseduration(String promiseduration) {
		this.promiseduration = promiseduration;
	}

	public String getPromisedurationtype() {
		return promisedurationtype;
	}

	public void setPromisedurationtype(String promisedurationtype) {
		this.promisedurationtype = promisedurationtype;
	}

	public String getPromisedurationtypename() {
		return promisedurationtypename;
	}

	public void setPromisedurationtypename(String promisedurationtypename) {
		this.promisedurationtypename = promisedurationtypename;
	}

	public String getParentcode() {
		return parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public String getUserange() {
		return userange;
	}

	public void setUserange(String userange) {
		this.userange = userange;
	}

	public String getOnlinestatus() {
		return onlinestatus;
	}

	public void setOnlinestatus(String onlinestatus) {
		this.onlinestatus = onlinestatus;
	}

	public String getOnlinestatusname() {
		return onlinestatusname;
	}

	public void setOnlinestatusname(String onlinestatusname) {
		this.onlinestatusname = onlinestatusname;
	}

	public String getOperatortime() {
		return operatortime;
	}

	public void setOperatortime(String operatortime) {
		this.operatortime = operatortime;
	}

	public String getOrganname() {
		return organname;
	}

	public void setOrganname(String organname) {
		this.organname = organname;
	}

}
