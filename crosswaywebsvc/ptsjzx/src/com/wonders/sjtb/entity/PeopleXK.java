package com.wonders.sjtb.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("PEOPLE_XK")
public class PeopleXK {
	/**
	 * null
	 **/
	@Column("ID")
	private String	id;
	/**
	 * 行政许可决定书文号
	 **/
	@Column("XK_WSH")
	private String	xkwsh;
	/**
	 * 项目名称
	 **/
	@Column("XK_XMMC")
	private String	xkxmmc;
	/**
	 * 审批类别（0=普通；1=特许；2=认可；3=核准；4=登记；5=其他（须注明）；）
	 **/
	@Column("XK_SPLB")
	private String	xksplb;
	/**
	 * 许可内容
	 **/
	@Column("XK_NR")
	private String	xknr;
	/**
	 * 行政相对人名称
	 **/
	@Column("XK_XDR")
	private String	xkxdr;
	/**
	 * 行政相对人代码_5（居民身份证号）
	 **/
	@Column("XK_XDR_SFZ")
	private String	xkxdrsfz;
	/**
	 * 许可决定日期（格式为YYYY/MM/DD）
	 **/
	@Column("XK_JDRQ")
	private Date	xkjdrq;
	/**
	 * 许可截止期（格式为YYYY/MM/DD）
	 **/
	@Column("XK_JZQ")
	private Date	xkjzq;
	/**
	 * 许可机关
	 **/
	@Column("XK_XZJG")
	private String	xkxzjg;
	/**
	 * 当前状态（0=正常（或空白）；1=撤销；2=异议；3=其他（备注说明）；）
	 **/
	@Column("XK_ZT")
	private String	xkzt;
	/**
	 * 地方编码
	 **/
	@Column("DFBM")
	private String	dfbm;
	/**
	 * 数据更新时间戳（格式为YYYY/MM/DD）
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

	public String getXkwsh() {
		return xkwsh;
	}

	public void setXkwsh(String xkwsh) {
		this.xkwsh = xkwsh;
	}

	public String getXkxmmc() {
		return xkxmmc;
	}

	public void setXkxmmc(String xkxmmc) {
		this.xkxmmc = xkxmmc;
	}

	public String getXksplb() {
		return xksplb;
	}

	public void setXksplb(String xksplb) {
		this.xksplb = xksplb;
	}

	public String getXknr() {
		return xknr;
	}

	public void setXknr(String xknr) {
		this.xknr = xknr;
	}

	public String getXkxdr() {
		return xkxdr;
	}

	public void setXkxdr(String xkxdr) {
		this.xkxdr = xkxdr;
	}

	public String getXkxdrsfz() {
		return xkxdrsfz;
	}

	public void setXkxdrsfz(String xkxdrsfz) {
		this.xkxdrsfz = xkxdrsfz;
	}

	public Date getXkjdrq() {
		return xkjdrq;
	}

	public void setXkjdrq(Date xkjdrq) {
		this.xkjdrq = xkjdrq;
	}

	public Date getXkjzq() {
		return xkjzq;
	}

	public void setXkjzq(Date xkjzq) {
		this.xkjzq = xkjzq;
	}

	public String getXkxzjg() {
		return xkxzjg;
	}

	public void setXkxzjg(String xkxzjg) {
		this.xkxzjg = xkxzjg;
	}

	public String getXkzt() {
		return xkzt;
	}

	public void setXkzt(String xkzt) {
		this.xkzt = xkzt;
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
