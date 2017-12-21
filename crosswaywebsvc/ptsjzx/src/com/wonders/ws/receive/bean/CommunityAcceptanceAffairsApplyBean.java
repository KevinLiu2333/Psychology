package com.wonders.ws.receive.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("PT_MZJ_SHEQU_GETAFFAIRSAPPLY")
public class CommunityAcceptanceAffairsApplyBean {
	/**
	*申请人姓名
	**/
	@Column("FULLNAME")
	private String fullname;
	/**
	*申请人证件号码
	**/
	@Column("IDNO")
	private String idno;
	/**
	*受理编号
	**/
	@Column("ID")
	private String applyno;
	/**
	*事项代码
	**/
	@Column("AFFAIRSCODE")
	private String affairscode;
	/**
	*事项名称
	**/
	@Column("AFFAIRSNAME")
	private String affairsname;
	/**
	*办理时间
	**/
	@Column("ACCEPTTIME")
	private String accepttime;
	/**
	*受理机构代码
	**/
	@Column("ACCEPTORGAN")
	private String acceptorgan;
	/**
	*受理机构名称
	**/
	@Column("ACCEPTORGANNAME")
	private String acceptorganname;
	/**
	*办结时间
	**/
	@Column("ENDTIME")
	private String endtime;
	/**
	*事项状态代码
	**/
	@Column("STAGESTATUS")
	private String stagestatus;
	/**
	*事项状态名称
	**/
	@Column("STAGESTATUSDESC")
	private String stagestatusdesc;
	/**
	*事项状态说明
	**/
	@Column("STAGESTATUSNAME")
	private String stagestatusname;
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getApplyno() {
		return applyno;
	}
	public void setApplyno(String applyno) {
		this.applyno = applyno;
	}
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
	public String getAccepttime() {
		return accepttime;
	}
	public void setAccepttime(String accepttime) {
		this.accepttime = accepttime;
	}
	public String getAcceptorgan() {
		return acceptorgan;
	}
	public void setAcceptorgan(String acceptorgan) {
		this.acceptorgan = acceptorgan;
	}
	public String getAcceptorganname() {
		return acceptorganname;
	}
	public void setAcceptorganname(String acceptorganname) {
		this.acceptorganname = acceptorganname;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getStagestatus() {
		return stagestatus;
	}
	public void setStagestatus(String stagestatus) {
		this.stagestatus = stagestatus;
	}
	public String getStagestatusdesc() {
		return stagestatusdesc;
	}
	public void setStagestatusdesc(String stagestatusdesc) {
		this.stagestatusdesc = stagestatusdesc;
	}
	public String getStagestatusname() {
		return stagestatusname;
	}
	public void setStagestatusname(String stagestatusname) {
		this.stagestatusname = stagestatusname;
	}

	

}
