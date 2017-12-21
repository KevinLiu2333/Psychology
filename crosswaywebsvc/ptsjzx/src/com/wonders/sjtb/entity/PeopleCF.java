package com.wonders.sjtb.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("PEOPLE_CF")
public class PeopleCF {

	/**
	 * null
	 **/
	@Column("ID")
	private String	id;
	/**
	 * 行政处罚决定书文号
	 **/
	@Column("CF_WSH")
	private String	cfwsh;
	/**
	 * 处罚名称
	 **/
	@Column("CF_CFMC")
	private String	cfcfmc;
	/**
	 * 处罚类别1（0=警告；1=罚款；2=没收违法所得、没收非法财物；3=责令停产停业；4=暂扣或者吊销许可证、暂扣或者吊销执照；5=行政拘留；6=其他（需注明） ）
	 **/
	@Column("CF_CFLB1")
	private String	cfcflb1;
	/**
	 * 处罚类别1（0=警告；1=罚款；2=没收违法所得、没收非法财物；3=责令停产停业；4=暂扣或者吊销许可证、暂扣或者吊销执照；5=行政拘留；6=其他（需注明） （如3项及以上处罚类别，可在[处罚类别2]中填写））
	 **/
	@Column("CF_CFLB2")
	private String	cfcflb2;
	/**
	 * 处罚事由
	 **/
	@Column("CF_SY")
	private String	cfsy;
	/**
	 * 处罚依据
	 **/
	@Column("CF_YJ")
	private String	cfyj;
	/**
	 * 行政相对人名称
	 **/
	@Column("CF_XDR_MC")
	private String	cfxdrmc;
	/**
	 * 行政相对人代码_5(居民身份证号)
	 **/
	@Column("CF_XDR_SFZ")
	private String	cfxdrsfz;
	/**
	 * 处罚结果
	 **/
	@Column("CF_JG")
	private String	cfjg;
	/**
	 * 处罚决定日期（格式为：YYYY/MM/DD）
	 **/
	@Column("CF_JDRQ")
	private Date	cfjdrq;
	/**
	 * 处罚机关
	 **/
	@Column("CF_XZJG")
	private String	cfxzjg;
	/**
	 * 当前状态（0=正常（或空白）； 1=撤销； 2=异议； 3=其他（备注说明）； ）
	 **/
	@Column("CF_ZT")
	private String	cfzt;
	/**
	 * 地方编码
	 **/
	@Column("DFBM")
	private String	dfbm;
	/**
	 * 数据更新时间戳（格式为：YYYY/MM/DD）
	 **/
	@Column("SJC")
	private Date	sjc;
	/**
	 * 备注
	 **/
	@Column("BZ")
	private String	bz;
	/**
	 * 所属批次
	 **/
	@Column("SSPC")
	private String	sspc;
	/**
	 * null
	 **/
	@Column("TBDEPT")
	private String	tbdept;
	/**
	 * null
	 **/
	@Column("DATA_MON")
	private String	datamon;
	/**
	 * null
	 **/
	@Column("UPLOADID")
	private String	uploadid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCfwsh() {
		return cfwsh;
	}

	public void setCfwsh(String cfwsh) {
		this.cfwsh = cfwsh;
	}

	public String getCfcfmc() {
		return cfcfmc;
	}

	public void setCfcfmc(String cfcfmc) {
		this.cfcfmc = cfcfmc;
	}

	public String getCfcflb1() {
		return cfcflb1;
	}

	public void setCfcflb1(String cfcflb1) {
		this.cfcflb1 = cfcflb1;
	}

	public String getCfcflb2() {
		return cfcflb2;
	}

	public void setCfcflb2(String cfcflb2) {
		this.cfcflb2 = cfcflb2;
	}

	public String getCfsy() {
		return cfsy;
	}

	public void setCfsy(String cfsy) {
		this.cfsy = cfsy;
	}

	public String getCfyj() {
		return cfyj;
	}

	public void setCfyj(String cfyj) {
		this.cfyj = cfyj;
	}

	public String getCfxdrmc() {
		return cfxdrmc;
	}

	public void setCfxdrmc(String cfxdrmc) {
		this.cfxdrmc = cfxdrmc;
	}

	public String getCfxdrsfz() {
		return cfxdrsfz;
	}

	public void setCfxdrsfz(String cfxdrsfz) {
		this.cfxdrsfz = cfxdrsfz;
	}

	public String getCfjg() {
		return cfjg;
	}

	public void setCfjg(String cfjg) {
		this.cfjg = cfjg;
	}

	public Date getCfjdrq() {
		return cfjdrq;
	}

	public void setCfjdrq(Date cfjdrq) {
		this.cfjdrq = cfjdrq;
	}

	public String getCfxzjg() {
		return cfxzjg;
	}

	public void setCfxzjg(String cfxzjg) {
		this.cfxzjg = cfxzjg;
	}

	public String getCfzt() {
		return cfzt;
	}

	public void setCfzt(String cfzt) {
		this.cfzt = cfzt;
	}

	public String getDfbm() {
		return dfbm;
	}

	public void setDfbm(String dfbm) {
		this.dfbm = dfbm;
	}

	public Date getSjc() {
		return sjc;
	}

	public void setSjc(Date sjc) {
		this.sjc = sjc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSspc() {
		return sspc;
	}

	public void setSspc(String sspc) {
		this.sspc = sspc;
	}

	public String getTbdept() {
		return tbdept;
	}

	public void setTbdept(String tbdept) {
		this.tbdept = tbdept;
	}

	public String getDatamon() {
		return datamon;
	}

	public void setDatamon(String datamon) {
		this.datamon = datamon;
	}

	public String getUploadid() {
		return uploadid;
	}

	public void setUploadid(String uploadid) {
		this.uploadid = uploadid;
	}

}
