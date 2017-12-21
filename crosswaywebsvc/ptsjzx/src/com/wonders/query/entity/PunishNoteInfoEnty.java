package com.wonders.query.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("PUNISH_NOTE_INFO_ENTY")
public class PunishNoteInfoEnty {
	/**
	 * 监管信息明细主键
	 **/
	@Column("PUNISH_INFO_ID")
	private double	punishinfoid;
	/**
	 * 法人库监管数据序号
	 **/
	@Column("PUNISH_ENTY_ID")
	private double	punishentyid;
	/**
	 * 事项类型
	 **/
	@Column("PUNISH_TYPE")
	private String	punishtype;
	/**
	 * 违法事实
	 **/
	@Column("ILLEGAL_CONTEXT")
	private String	illegalcontext;
	/**
	 * 证据
	 **/
	@Column("ILLEGAL_EVIDENCE")
	private String	illegalevidence;
	/**
	 * 违反规定
	 **/
	@Column("ILLEGAL_RULE")
	private String	illegalrule;
	/**
	 * 业务注册时间
	 **/
	@Column("BUSI_REG_TIME")
	private Date	busiregtime;
	/**
	 * 业务更新时间
	 **/
	@Column("BUSI_UPD_TIME")
	private Date	busiupdtime;
	/**
	 * 事项类型名称
	 **/
	@Column("PUNISH_TYPE_NAME")
	private String	punishtypename;
	/**
	 * 事项类型等级
	 **/
	@Column("PUNISH_LEVEL")
	private String	punishlevel;
	/**
	 * 欠税税种
	 **/
	@Column("OWE_TAX_TYPE")
	private String	owetaxtype;
	/**
	 * 欠税金额
	 **/
	@Column("OWE_TAX_AMT")
	private double	owetaxamt;
	/**
	 * 产品名称
	 **/
	@Column("PRODUCT_NAME")
	private String	productname;
	/**
	 * 商标
	 **/
	@Column("BRAND")
	private String	brand;
	/**
	 * 规格型号
	 **/
	@Column("PRODUCT_MODEL_NO")
	private String	productmodelno;
	/**
	 * 生产日期/批号
	 **/
	@Column("PRODUCT_BATCH_NO")
	private String	productbatchno;
	/**
	 * 检验结果
	 **/
	@Column("EXAM_RESULT")
	private String	examresult;

	public double getPunishinfoid() {
		return punishinfoid;
	}

	public void setPunishinfoid(double punishinfoid) {
		this.punishinfoid = punishinfoid;
	}

	public double getPunishentyid() {
		return punishentyid;
	}

	public void setPunishentyid(double punishentyid) {
		this.punishentyid = punishentyid;
	}

	public String getPunishtype() {
		return punishtype;
	}

	public void setPunishtype(String punishtype) {
		this.punishtype = punishtype;
	}

	public String getIllegalcontext() {
		return illegalcontext;
	}

	public void setIllegalcontext(String illegalcontext) {
		this.illegalcontext = illegalcontext;
	}

	public String getIllegalevidence() {
		return illegalevidence;
	}

	public void setIllegalevidence(String illegalevidence) {
		this.illegalevidence = illegalevidence;
	}

	public String getIllegalrule() {
		return illegalrule;
	}

	public void setIllegalrule(String illegalrule) {
		this.illegalrule = illegalrule;
	}

	public Date getBusiregtime() {
		return busiregtime;
	}

	public void setBusiregtime(Date busiregtime) {
		this.busiregtime = busiregtime;
	}

	public Date getBusiupdtime() {
		return busiupdtime;
	}

	public void setBusiupdtime(Date busiupdtime) {
		this.busiupdtime = busiupdtime;
	}

	public String getPunishtypename() {
		return punishtypename;
	}

	public void setPunishtypename(String punishtypename) {
		this.punishtypename = punishtypename;
	}

	public String getPunishlevel() {
		return punishlevel;
	}

	public void setPunishlevel(String punishlevel) {
		this.punishlevel = punishlevel;
	}

	public String getOwetaxtype() {
		return owetaxtype;
	}

	public void setOwetaxtype(String owetaxtype) {
		this.owetaxtype = owetaxtype;
	}

	public double getOwetaxamt() {
		return owetaxamt;
	}

	public void setOwetaxamt(double owetaxamt) {
		this.owetaxamt = owetaxamt;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductmodelno() {
		return productmodelno;
	}

	public void setProductmodelno(String productmodelno) {
		this.productmodelno = productmodelno;
	}

	public String getProductbatchno() {
		return productbatchno;
	}

	public void setProductbatchno(String productbatchno) {
		this.productbatchno = productbatchno;
	}

	public String getExamresult() {
		return examresult;
	}

	public void setExamresult(String examresult) {
		this.examresult = examresult;
	}

}
