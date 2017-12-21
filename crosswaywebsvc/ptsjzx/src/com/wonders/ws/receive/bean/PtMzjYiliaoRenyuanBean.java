package com.wonders.ws.receive.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("PT_MZJ_YILIAO_RENYUAN")
public class PtMzjYiliaoRenyuanBean {
	/**
	 * 身份证号
	 **/
	@Column("SFZH")
	private String	sfzh;
	/**
	 * 姓名，长度1-32位字符
	 **/
	@Column("XM")
	private String	xm;
	/**
	 * 性别
	 **/
	@Column("XB")
	private String	xb;
	/**
	 * 证件类型
	 **/
	@Column("ZJLX")
	private String	zjlx;
	/**
	 * 户籍类型
	 **/
	@Column("HJLX")
	private String	hjlx;
	/**
	 * 电话号码
	 **/
	@Column("DHHM")
	private String	dhhm;
	/**
	 * 居住地址
	 **/
	@Column("JZDZ")
	private String	jzdz;
	/**
	 * 人员类型
	 **/
	@Column("RYLX")
	private String	rylx;
	/**
	 * 户籍地址
	 **/
	@Column("HJDZ")
	private String	hjdz;
	/**
	 * 工作地点
	 **/
	@Column("GZDD")
	private String	gzdd;
	/**
	 * 民族
	 **/
	@Column("MZ")
	private String	mz;
	/**
	 * 婚姻状态
	 **/
	@Column("HYZT")
	private String	hyzt;
	/**
	 * 教育程度
	 **/
	@Column("JYCD")
	private String	jycd;
	/**
	 * 实时结算状态
	 **/
	@Column("SSJSZT")
	private String	ssjszt;
	/**
	 * 更新日期
	 **/
	@Column("GXRQ")
	private String	gxrq;
	
	@Column("ID")
	private String	id;

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getHjlx() {
		return hjlx;
	}

	public void setHjlx(String hjlx) {
		this.hjlx = hjlx;
	}

	public String getDhhm() {
		return dhhm;
	}

	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	}

	public String getJzdz() {
		return jzdz;
	}

	public void setJzdz(String jzdz) {
		this.jzdz = jzdz;
	}

	public String getRylx() {
		return rylx;
	}

	public void setRylx(String rylx) {
		this.rylx = rylx;
	}

	public String getHjdz() {
		return hjdz;
	}

	public void setHjdz(String hjdz) {
		this.hjdz = hjdz;
	}

	public String getGzdd() {
		return gzdd;
	}

	public void setGzdd(String gzdd) {
		this.gzdd = gzdd;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getHyzt() {
		return hyzt;
	}

	public void setHyzt(String hyzt) {
		this.hyzt = hyzt;
	}

	public String getJycd() {
		return jycd;
	}

	public void setJycd(String jycd) {
		this.jycd = jycd;
	}

	public String getSsjszt() {
		return ssjszt;
	}

	public void setSsjszt(String ssjszt) {
		this.ssjszt = ssjszt;
	}

	public String getGxrq() {
		return gxrq;
	}

	public void setGxrq(String gxrq) {
		this.gxrq = gxrq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
