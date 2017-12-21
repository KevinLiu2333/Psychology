package com.wonders.ws.receive.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("PT_HBJ_HUANJINGJIANCE_DIBIAO")
public class PtHbjHuanjingjianceDibiaoBean {
	/**
	 * COD
	 **/
	@Column("COD")
	private String	cod;
	/**
	 * 氨氮
	 **/
	@Column("AMMON")
	private String	ammon;
	/**
	 * 总磷
	 **/
	@Column("TP")
	private String	tp;
	/**
	 * 总氮
	 **/
	@Column("TN")
	private String	tn;
	/**
	 * PH值
	 **/
	@Column("PH")
	private String	ph;
	/**
	 * 溶解氧
	 **/
	@Column("DO")
	private String	do1;
	/**
	 * 浊度
	 **/
	@Column("TURBI")
	private String	turbi;
	/**
	 * 电导率
	 **/
	@Column("CONDU")
	private String	condu;
	/**
	 * 水温
	 **/
	@Column("WATERT")
	private String	watert;
	/**
	 * 监测站点
	 **/
	@Column("STATIONID")
	private String	stationid;
	/**
	 * 监测时间
	 **/
	@Column("STA_TIME")
	private String	statime;
	/**
	 * null
	 **/
	@Column("ID")
	private String	id;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getAmmon() {
		return ammon;
	}

	public void setAmmon(String ammon) {
		this.ammon = ammon;
	}

	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public String getTn() {
		return tn;
	}

	public void setTn(String tn) {
		this.tn = tn;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getDo1() {
		return do1;
	}

	public void setDo1(String do1) {
		this.do1 = do1;
	}

	public String getTurbi() {
		return turbi;
	}

	public void setTurbi(String turbi) {
		this.turbi = turbi;
	}

	public String getCondu() {
		return condu;
	}

	public void setCondu(String condu) {
		this.condu = condu;
	}

	public String getWatert() {
		return watert;
	}

	public void setWatert(String watert) {
		this.watert = watert;
	}

	public String getStationid() {
		return stationid;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public String getStatime() {
		return statime;
	}

	public void setStatime(String statime) {
		this.statime = statime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
