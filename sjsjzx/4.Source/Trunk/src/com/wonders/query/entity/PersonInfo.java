package com.wonders.query.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("T_GA_RJBXX")
public class PersonInfo {
	/**
	*人id//
	**/
	@Column("RID")
	private String rid;
	/**
	*实有人口类别代码/d_syrklb/
	**/
	@Column("SYRKLBDM")
	private String syrklbdm;
	/**
	*实有人口类别汉字
	**/
	@Column("SYRKLBHZ")
	private String syrklbhz;
	/**
	*证件种类代码/d_zjzl/
	**/
	@Column("ZJZLDM")
	private String zjzldm;
	/**
	*证件种类汉字
	**/
	@Column("ZJZLHZ")
	private String zjzlhz;
	/**
	*证件号码
	**/
	@Column("ZJHM")
	private String zjhm;
	/**
	*姓名
	**/
	@Column("XM")
	private String xm;
	/**
	*姓名全拼
	**/
	@Column("XMQP")
	private String xmqp;
	/**
	*姓名首拼
	**/
	@Column("XMSP")
	private String xmsp;
	/**
	*曾用名
	**/
	@Column("CYM")
	private String cym;
	/**
	*性别代码/d_xb/
	**/
	@Column("XBDM")
	private String xbdm;
	/**
	*性别汉字
	**/
	@Column("XBHZ")
	private String xbhz;
	/**
	*民族代码/d_mz/
	**/
	@Column("MZDM")
	private String mzdm;
	/**
	*民族汉字
	**/
	@Column("MZHZ")
	private String mzhz;
	/**
	*出生日期
	**/
	@Column("CSRQ")
	private String csrq;
	/**
	*出生地行政区划代码/d_xzqh/
	**/
	@Column("CSDXZQHDM")
	private String csdxzqhdm;
	/**
	*出生地行政区划汉字
	**/
	@Column("CSDXZQHHZ")
	private String csdxzqhhz;
	/**
	*出生地详址
	**/
	@Column("CSDXZ")
	private String csdxz;
	/**
	*籍贯代码/d_xzqh/
	**/
	@Column("JGDM")
	private String jgdm;
	/**
	*籍贯汉字
	**/
	@Column("JGHZ")
	private String jghz;
	/**
	*户号
	**/
	@Column("HH")
	private String hh;
	/**
	*户流水号
	**/
	@Column("HLSH")
	private String hlsh;
	/**
	*分户流水号
	**/
	@Column("FHLSH")
	private String fhlsh;
	/**
	*户籍地行政区划代码/d_xzqh/
	**/
	@Column("HJDXZQHDM")
	private String hjdxzqhdm;
	/**
	*户籍地行政区划汉字
	**/
	@Column("HJDXZQHHZ")
	private String hjdxzqhhz;
	/**
	*户籍地路名代码/d_lm/
	**/
	@Column("HJDLMDM")
	private String hjdlmdm;
	/**
	*户籍地路名汉字
	**/
	@Column("HJDLMHZ")
	private String hjdlmhz;
	/**
	*户籍地详址
	**/
	@Column("HJDXZ")
	private String hjdxz;
	/**
	*户籍地址
	**/
	@Column("HJDZ")
	private String hjdz;
	/**
	*户籍地一级门牌
	**/
	@Column("HJDYJMP")
	private String hjdyjmp;
	/**
	*户籍地一级门牌单双号
	**/
	@Column("HJDYJMPDSH")
	private String hjdyjmpdsh;
	/**
	*户籍地二级门牌
	**/
	@Column("HJDEJMP")
	private String hjdejmp;
	/**
	*户籍地二级门牌单双号
	**/
	@Column("HJDEJMPDSH")
	private String hjdejmpdsh;
	/**
	*居住地行政区划代码/d_xzqh/
	**/
	@Column("JZDXZQHDM")
	private String jzdxzqhdm;
	/**
	*居住地行政区划汉字
	**/
	@Column("JZDXZQHHZ")
	private String jzdxzqhhz;
	/**
	*居住地路名代码/d_lm/
	**/
	@Column("JZDLMDM")
	private String jzdlmdm;
	/**
	*居住地路名汉字
	**/
	@Column("JZDLMHZ")
	private String jzdlmhz;
	/**
	*居住地详址
	**/
	@Column("JZDXZ")
	private String jzdxz;
	/**
	*居住地址
	**/
	@Column("JZDZ")
	private String jzdz;
	/**
	*居住地一级门牌
	**/
	@Column("JZDYJMP")
	private String jzdyjmp;
	/**
	*居住地一级门牌单双号
	**/
	@Column("JZDYJMPDSH")
	private String jzdyjmpdsh;
	/**
	*居住地二级门牌
	**/
	@Column("JZDEJMP")
	private String jzdejmp;
	/**
	*居住地二级门牌单双号
	**/
	@Column("JZDEJMPDSH")
	private String jzdejmpdsh;
	/**
	*文化程度代码/d_whcd/
	**/
	@Column("WHCDDM")
	private String whcddm;
	/**
	*文化程度汉字
	**/
	@Column("WHCDHZ")
	private String whcdhz;
	/**
	*婚姻状况代码/d_hyzk/
	**/
	@Column("HYZKDM")
	private String hyzkdm;
	/**
	*婚姻状况汉字
	**/
	@Column("HYZKHZ")
	private String hyzkhz;
	/**
	*配偶姓名
	**/
	@Column("POXM")
	private String poxm;
	/**
	*配偶证件号码
	**/
	@Column("POZJHM")
	private String pozjhm;
	/**
	*职业
	**/
	@Column("ZY")
	private String zy;
	/**
	*职业类别代码/d_zylb/
	**/
	@Column("ZYLBDM")
	private String zylbdm;
	/**
	*职业类别汉字
	**/
	@Column("ZYLBHZ")
	private String zylbhz;
	/**
	*服务处所
	**/
	@Column("FWCS")
	private String fwcs;
	/**
	*宗教信仰代码/d_zjxy/
	**/
	@Column("ZJXYDM")
	private String zjxydm;
	/**
	*宗教信仰汉字
	**/
	@Column("ZJXYHZ")
	private String zjxyhz;
	/**
	*兵役状况代码/d_byzk/
	**/
	@Column("BYZKDM")
	private String byzkdm;
	/**
	*兵役状况汉字
	**/
	@Column("BYZKHZ")
	private String byzkhz;
	/**
	*身高
	**/
	@Column("SG")
	private String sg;
	/**
	*血型代码/d_xx/
	**/
	@Column("XXDM")
	private String xxdm;
	/**
	*血型汉字
	**/
	@Column("XXHZ")
	private String xxhz;
	/**
	*暂住事由代码/d_zzsy/
	**/
	@Column("ZZSYDM")
	private String zzsydm;
	/**
	*暂住事由汉字
	**/
	@Column("ZZSYHZ")
	private String zzsyhz;
	/**
	*与户主关系代码
	**/
	@Column("YHZGXDM")
	private String yhzgxdm;
	/**
	*与户主关系汉字
	**/
	@Column("YHZGXHZ")
	private String yhzgxhz;
	/**
	*与主居住人关系代码
	**/
	@Column("YZJZRGXDM")
	private String yzjzrgxdm;
	/**
	*与主居住人关系汉字
	**/
	@Column("YZJZRGXHZ")
	private String yzjzrgxhz;
	/**
	*国家代码/d_gj/
	**/
	@Column("GJDM")
	private String gjdm;
	/**
	*国家汉字
	**/
	@Column("GJHZ")
	private String gjhz;
	/**
	*英文姓
	**/
	@Column("YWX")
	private String ywx;
	/**
	*英文名
	**/
	@Column("YWM")
	private String ywm;
	/**
	*境外人员身份代码/d_jwrysf/
	**/
	@Column("JWRYSFDM")
	private String jwrysfdm;
	/**
	*境外人员身份汉字
	**/
	@Column("JWRYSFHZ")
	private String jwrysfhz;
	/**
	*停留事由代码/d_tlsy/
	**/
	@Column("TLSYDM")
	private String tlsydm;
	/**
	*停留事由汉字
	**/
	@Column("TLSYHZ")
	private String tlsyhz;
	/**
	*入境时间
	**/
	@Column("RJSJ")
	private String rjsj;
	/**
	*信息级别
	**/
	@Column("XXJB")
	private String xxjb;
	/**
	*组织机构代码/d_jg/
	**/
	@Column("ZZJGDM")
	private String zzjgdm;
	/**
	*组织机构汉字
	**/
	@Column("ZZJGHZ")
	private String zzjghz;
	/**
	*最新更新时间
	**/
	@Column("ZXGXSJ")
	private String zxgxsj;
	/**
	*备注
	**/
	@Column("BZ")
	private String bz;
	/**
	*居住房屋id
	**/
	@Column("JZFWID")
	private String jzfwid;
	/**
	*户籍房屋id
	**/
	@Column("HJFWID")
	private String hjfwid;
	/**
	*地理编码
	**/
	@Column("MAPIDD")
	private String mapidd;
	/**
	*居住地组织机构代码（户籍人员）
	**/
	@Column("JZDZZJGDM")
	private String jzdzzjgdm;
	/**
	*居住地组织机构汉字
	**/
	@Column("JZDZZJGHZ")
	private String jzdzzjghz;
	/**
	*记录入库时间
	**/
	@Column("JLRKSJ")
	private String jlrksj;
	/**
	*居住地街道代码
	**/
	@Column("JZDJDDM")
	private String jzdjddm;
	/**
	*居住地街道汉字
	**/
	@Column("JZDJDHZ")
	private String jzdjdhz;
	/**
	*居住地居委会代码
	**/
	@Column("JZDJWHDM")
	private String jzdjwhdm;
	/**
	*居住地居委会汉字
	**/
	@Column("JZDJWHHZ")
	private String jzdjwhhz;
	/**
	*户籍地街道代码
	**/
	@Column("HJDJDDM")
	private String hjdjddm;
	/**
	*户籍地街道汉字
	**/
	@Column("HJDJDHZ")
	private String hjdjdhz;
	/**
	*户籍地居委会代码
	**/
	@Column("HJDJWHDM")
	private String hjdjwhdm;
	/**
	*户籍地居委会汉字
	**/
	@Column("HJDJWHHZ")
	private String hjdjwhhz;
	/**
	*户籍地组织机构代码
	**/
	@Column("HJDZZJGDM")
	private String hjdzzjgdm;
	/**
	*户籍地组织机构汉字
	**/
	@Column("HJDZZJGHZ")
	private String hjdzzjghz;
	/**
	*居住地建筑物ID
	**/
	@Column("JZDJZWID")
	private String jzdjzwid;
	/**
	*户籍地建筑物ID
	**/
	@Column("HJDJZWID")
	private String hjdjzwid;
	/**
	*null
	**/
	@Column("HJMAPID")
	private String hjmapid;
	/**
	*是否空挂户
	**/
	@Column("IS_KGH")
	private String iskgh;
	/**
	*实有人口子类别代码
	**/
	@Column("SYRKZLBDM")
	private String syrkzlbdm;
	/**
	*实有人口子类别汉字
	**/
	@Column("SYRKZLBHZ")
	private String syrkzlbhz;
	/**
	*居住地建筑物名称
	**/
	@Column("JZDJZWMC")
	private String jzdjzwmc;
	/**
	*户籍地建筑物名称
	**/
	@Column("HJDJZWMC")
	private String hjdjzwmc;
	/**
	*户籍居住地登记人户分离  
	**/
	@Column("FLAG")
	private String flag;
	/**
	*0 初始状态  1吸毒 2 刑释解教'
	**/
	@Column("IS_ZDRY")
	private String iszdry;
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getSyrklbdm() {
		return syrklbdm;
	}
	public void setSyrklbdm(String syrklbdm) {
		this.syrklbdm = syrklbdm;
	}
	public String getSyrklbhz() {
		return syrklbhz;
	}
	public void setSyrklbhz(String syrklbhz) {
		this.syrklbhz = syrklbhz;
	}
	public String getZjzldm() {
		return zjzldm;
	}
	public void setZjzldm(String zjzldm) {
		this.zjzldm = zjzldm;
	}
	public String getZjzlhz() {
		return zjzlhz;
	}
	public void setZjzlhz(String zjzlhz) {
		this.zjzlhz = zjzlhz;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXmqp() {
		return xmqp;
	}
	public void setXmqp(String xmqp) {
		this.xmqp = xmqp;
	}
	public String getXmsp() {
		return xmsp;
	}
	public void setXmsp(String xmsp) {
		this.xmsp = xmsp;
	}
	public String getCym() {
		return cym;
	}
	public void setCym(String cym) {
		this.cym = cym;
	}
	public String getXbdm() {
		return xbdm;
	}
	public void setXbdm(String xbdm) {
		this.xbdm = xbdm;
	}
	public String getXbhz() {
		return xbhz;
	}
	public void setXbhz(String xbhz) {
		this.xbhz = xbhz;
	}
	public String getMzdm() {
		return mzdm;
	}
	public void setMzdm(String mzdm) {
		this.mzdm = mzdm;
	}
	public String getMzhz() {
		return mzhz;
	}
	public void setMzhz(String mzhz) {
		this.mzhz = mzhz;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getCsdxzqhdm() {
		return csdxzqhdm;
	}
	public void setCsdxzqhdm(String csdxzqhdm) {
		this.csdxzqhdm = csdxzqhdm;
	}
	public String getCsdxzqhhz() {
		return csdxzqhhz;
	}
	public void setCsdxzqhhz(String csdxzqhhz) {
		this.csdxzqhhz = csdxzqhhz;
	}
	public String getCsdxz() {
		return csdxz;
	}
	public void setCsdxz(String csdxz) {
		this.csdxz = csdxz;
	}
	public String getJgdm() {
		return jgdm;
	}
	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}
	public String getJghz() {
		return jghz;
	}
	public void setJghz(String jghz) {
		this.jghz = jghz;
	}
	public String getHh() {
		return hh;
	}
	public void setHh(String hh) {
		this.hh = hh;
	}
	public String getHlsh() {
		return hlsh;
	}
	public void setHlsh(String hlsh) {
		this.hlsh = hlsh;
	}
	public String getFhlsh() {
		return fhlsh;
	}
	public void setFhlsh(String fhlsh) {
		this.fhlsh = fhlsh;
	}
	public String getHjdxzqhdm() {
		return hjdxzqhdm;
	}
	public void setHjdxzqhdm(String hjdxzqhdm) {
		this.hjdxzqhdm = hjdxzqhdm;
	}
	public String getHjdxzqhhz() {
		return hjdxzqhhz;
	}
	public void setHjdxzqhhz(String hjdxzqhhz) {
		this.hjdxzqhhz = hjdxzqhhz;
	}
	public String getHjdlmdm() {
		return hjdlmdm;
	}
	public void setHjdlmdm(String hjdlmdm) {
		this.hjdlmdm = hjdlmdm;
	}
	public String getHjdlmhz() {
		return hjdlmhz;
	}
	public void setHjdlmhz(String hjdlmhz) {
		this.hjdlmhz = hjdlmhz;
	}
	public String getHjdxz() {
		return hjdxz;
	}
	public void setHjdxz(String hjdxz) {
		this.hjdxz = hjdxz;
	}
	public String getHjdz() {
		return hjdz;
	}
	public void setHjdz(String hjdz) {
		this.hjdz = hjdz;
	}
	public String getHjdyjmp() {
		return hjdyjmp;
	}
	public void setHjdyjmp(String hjdyjmp) {
		this.hjdyjmp = hjdyjmp;
	}
	public String getHjdyjmpdsh() {
		return hjdyjmpdsh;
	}
	public void setHjdyjmpdsh(String hjdyjmpdsh) {
		this.hjdyjmpdsh = hjdyjmpdsh;
	}
	public String getHjdejmp() {
		return hjdejmp;
	}
	public void setHjdejmp(String hjdejmp) {
		this.hjdejmp = hjdejmp;
	}
	public String getHjdejmpdsh() {
		return hjdejmpdsh;
	}
	public void setHjdejmpdsh(String hjdejmpdsh) {
		this.hjdejmpdsh = hjdejmpdsh;
	}
	public String getJzdxzqhdm() {
		return jzdxzqhdm;
	}
	public void setJzdxzqhdm(String jzdxzqhdm) {
		this.jzdxzqhdm = jzdxzqhdm;
	}
	public String getJzdxzqhhz() {
		return jzdxzqhhz;
	}
	public void setJzdxzqhhz(String jzdxzqhhz) {
		this.jzdxzqhhz = jzdxzqhhz;
	}
	public String getJzdlmdm() {
		return jzdlmdm;
	}
	public void setJzdlmdm(String jzdlmdm) {
		this.jzdlmdm = jzdlmdm;
	}
	public String getJzdlmhz() {
		return jzdlmhz;
	}
	public void setJzdlmhz(String jzdlmhz) {
		this.jzdlmhz = jzdlmhz;
	}
	public String getJzdxz() {
		return jzdxz;
	}
	public void setJzdxz(String jzdxz) {
		this.jzdxz = jzdxz;
	}
	public String getJzdz() {
		return jzdz;
	}
	public void setJzdz(String jzdz) {
		this.jzdz = jzdz;
	}
	public String getJzdyjmp() {
		return jzdyjmp;
	}
	public void setJzdyjmp(String jzdyjmp) {
		this.jzdyjmp = jzdyjmp;
	}
	public String getJzdyjmpdsh() {
		return jzdyjmpdsh;
	}
	public void setJzdyjmpdsh(String jzdyjmpdsh) {
		this.jzdyjmpdsh = jzdyjmpdsh;
	}
	public String getJzdejmp() {
		return jzdejmp;
	}
	public void setJzdejmp(String jzdejmp) {
		this.jzdejmp = jzdejmp;
	}
	public String getJzdejmpdsh() {
		return jzdejmpdsh;
	}
	public void setJzdejmpdsh(String jzdejmpdsh) {
		this.jzdejmpdsh = jzdejmpdsh;
	}
	public String getWhcddm() {
		return whcddm;
	}
	public void setWhcddm(String whcddm) {
		this.whcddm = whcddm;
	}
	public String getWhcdhz() {
		return whcdhz;
	}
	public void setWhcdhz(String whcdhz) {
		this.whcdhz = whcdhz;
	}
	public String getHyzkdm() {
		return hyzkdm;
	}
	public void setHyzkdm(String hyzkdm) {
		this.hyzkdm = hyzkdm;
	}
	public String getHyzkhz() {
		return hyzkhz;
	}
	public void setHyzkhz(String hyzkhz) {
		this.hyzkhz = hyzkhz;
	}
	public String getPoxm() {
		return poxm;
	}
	public void setPoxm(String poxm) {
		this.poxm = poxm;
	}
	public String getPozjhm() {
		return pozjhm;
	}
	public void setPozjhm(String pozjhm) {
		this.pozjhm = pozjhm;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getZylbdm() {
		return zylbdm;
	}
	public void setZylbdm(String zylbdm) {
		this.zylbdm = zylbdm;
	}
	public String getZylbhz() {
		return zylbhz;
	}
	public void setZylbhz(String zylbhz) {
		this.zylbhz = zylbhz;
	}
	public String getFwcs() {
		return fwcs;
	}
	public void setFwcs(String fwcs) {
		this.fwcs = fwcs;
	}
	public String getZjxydm() {
		return zjxydm;
	}
	public void setZjxydm(String zjxydm) {
		this.zjxydm = zjxydm;
	}
	public String getZjxyhz() {
		return zjxyhz;
	}
	public void setZjxyhz(String zjxyhz) {
		this.zjxyhz = zjxyhz;
	}
	public String getByzkdm() {
		return byzkdm;
	}
	public void setByzkdm(String byzkdm) {
		this.byzkdm = byzkdm;
	}
	public String getByzkhz() {
		return byzkhz;
	}
	public void setByzkhz(String byzkhz) {
		this.byzkhz = byzkhz;
	}
	public String getSg() {
		return sg;
	}
	public void setSg(String sg) {
		this.sg = sg;
	}
	public String getXxdm() {
		return xxdm;
	}
	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
	}
	public String getXxhz() {
		return xxhz;
	}
	public void setXxhz(String xxhz) {
		this.xxhz = xxhz;
	}
	public String getZzsydm() {
		return zzsydm;
	}
	public void setZzsydm(String zzsydm) {
		this.zzsydm = zzsydm;
	}
	public String getZzsyhz() {
		return zzsyhz;
	}
	public void setZzsyhz(String zzsyhz) {
		this.zzsyhz = zzsyhz;
	}
	public String getYhzgxdm() {
		return yhzgxdm;
	}
	public void setYhzgxdm(String yhzgxdm) {
		this.yhzgxdm = yhzgxdm;
	}
	public String getYhzgxhz() {
		return yhzgxhz;
	}
	public void setYhzgxhz(String yhzgxhz) {
		this.yhzgxhz = yhzgxhz;
	}
	public String getYzjzrgxdm() {
		return yzjzrgxdm;
	}
	public void setYzjzrgxdm(String yzjzrgxdm) {
		this.yzjzrgxdm = yzjzrgxdm;
	}
	public String getYzjzrgxhz() {
		return yzjzrgxhz;
	}
	public void setYzjzrgxhz(String yzjzrgxhz) {
		this.yzjzrgxhz = yzjzrgxhz;
	}
	public String getGjdm() {
		return gjdm;
	}
	public void setGjdm(String gjdm) {
		this.gjdm = gjdm;
	}
	public String getGjhz() {
		return gjhz;
	}
	public void setGjhz(String gjhz) {
		this.gjhz = gjhz;
	}
	public String getYwx() {
		return ywx;
	}
	public void setYwx(String ywx) {
		this.ywx = ywx;
	}
	public String getYwm() {
		return ywm;
	}
	public void setYwm(String ywm) {
		this.ywm = ywm;
	}
	public String getJwrysfdm() {
		return jwrysfdm;
	}
	public void setJwrysfdm(String jwrysfdm) {
		this.jwrysfdm = jwrysfdm;
	}
	public String getJwrysfhz() {
		return jwrysfhz;
	}
	public void setJwrysfhz(String jwrysfhz) {
		this.jwrysfhz = jwrysfhz;
	}
	public String getTlsydm() {
		return tlsydm;
	}
	public void setTlsydm(String tlsydm) {
		this.tlsydm = tlsydm;
	}
	public String getTlsyhz() {
		return tlsyhz;
	}
	public void setTlsyhz(String tlsyhz) {
		this.tlsyhz = tlsyhz;
	}
	public String getRjsj() {
		return rjsj;
	}
	public void setRjsj(String rjsj) {
		this.rjsj = rjsj;
	}
	public String getXxjb() {
		return xxjb;
	}
	public void setXxjb(String xxjb) {
		this.xxjb = xxjb;
	}
	public String getZzjgdm() {
		return zzjgdm;
	}
	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}
	public String getZzjghz() {
		return zzjghz;
	}
	public void setZzjghz(String zzjghz) {
		this.zzjghz = zzjghz;
	}
	public String getZxgxsj() {
		return zxgxsj;
	}
	public void setZxgxsj(String zxgxsj) {
		this.zxgxsj = zxgxsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getJzfwid() {
		return jzfwid;
	}
	public void setJzfwid(String jzfwid) {
		this.jzfwid = jzfwid;
	}
	public String getHjfwid() {
		return hjfwid;
	}
	public void setHjfwid(String hjfwid) {
		this.hjfwid = hjfwid;
	}
	public String getMapidd() {
		return mapidd;
	}
	public void setMapidd(String mapidd) {
		this.mapidd = mapidd;
	}
	public String getJzdzzjgdm() {
		return jzdzzjgdm;
	}
	public void setJzdzzjgdm(String jzdzzjgdm) {
		this.jzdzzjgdm = jzdzzjgdm;
	}
	public String getJzdzzjghz() {
		return jzdzzjghz;
	}
	public void setJzdzzjghz(String jzdzzjghz) {
		this.jzdzzjghz = jzdzzjghz;
	}
	public String getJlrksj() {
		return jlrksj;
	}
	public void setJlrksj(String jlrksj) {
		this.jlrksj = jlrksj;
	}
	public String getJzdjddm() {
		return jzdjddm;
	}
	public void setJzdjddm(String jzdjddm) {
		this.jzdjddm = jzdjddm;
	}
	public String getJzdjdhz() {
		return jzdjdhz;
	}
	public void setJzdjdhz(String jzdjdhz) {
		this.jzdjdhz = jzdjdhz;
	}
	public String getJzdjwhdm() {
		return jzdjwhdm;
	}
	public void setJzdjwhdm(String jzdjwhdm) {
		this.jzdjwhdm = jzdjwhdm;
	}
	public String getJzdjwhhz() {
		return jzdjwhhz;
	}
	public void setJzdjwhhz(String jzdjwhhz) {
		this.jzdjwhhz = jzdjwhhz;
	}
	public String getHjdjddm() {
		return hjdjddm;
	}
	public void setHjdjddm(String hjdjddm) {
		this.hjdjddm = hjdjddm;
	}
	public String getHjdjdhz() {
		return hjdjdhz;
	}
	public void setHjdjdhz(String hjdjdhz) {
		this.hjdjdhz = hjdjdhz;
	}
	public String getHjdjwhdm() {
		return hjdjwhdm;
	}
	public void setHjdjwhdm(String hjdjwhdm) {
		this.hjdjwhdm = hjdjwhdm;
	}
	public String getHjdjwhhz() {
		return hjdjwhhz;
	}
	public void setHjdjwhhz(String hjdjwhhz) {
		this.hjdjwhhz = hjdjwhhz;
	}
	public String getHjdzzjgdm() {
		return hjdzzjgdm;
	}
	public void setHjdzzjgdm(String hjdzzjgdm) {
		this.hjdzzjgdm = hjdzzjgdm;
	}
	public String getHjdzzjghz() {
		return hjdzzjghz;
	}
	public void setHjdzzjghz(String hjdzzjghz) {
		this.hjdzzjghz = hjdzzjghz;
	}
	public String getJzdjzwid() {
		return jzdjzwid;
	}
	public void setJzdjzwid(String jzdjzwid) {
		this.jzdjzwid = jzdjzwid;
	}
	public String getHjdjzwid() {
		return hjdjzwid;
	}
	public void setHjdjzwid(String hjdjzwid) {
		this.hjdjzwid = hjdjzwid;
	}
	public String getHjmapid() {
		return hjmapid;
	}
	public void setHjmapid(String hjmapid) {
		this.hjmapid = hjmapid;
	}
	public String getIskgh() {
		return iskgh;
	}
	public void setIskgh(String iskgh) {
		this.iskgh = iskgh;
	}
	public String getSyrkzlbdm() {
		return syrkzlbdm;
	}
	public void setSyrkzlbdm(String syrkzlbdm) {
		this.syrkzlbdm = syrkzlbdm;
	}
	public String getSyrkzlbhz() {
		return syrkzlbhz;
	}
	public void setSyrkzlbhz(String syrkzlbhz) {
		this.syrkzlbhz = syrkzlbhz;
	}
	public String getJzdjzwmc() {
		return jzdjzwmc;
	}
	public void setJzdjzwmc(String jzdjzwmc) {
		this.jzdjzwmc = jzdjzwmc;
	}
	public String getHjdjzwmc() {
		return hjdjzwmc;
	}
	public void setHjdjzwmc(String hjdjzwmc) {
		this.hjdjzwmc = hjdjzwmc;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getIszdry() {
		return iszdry;
	}
	public void setIszdry(String iszdry) {
		this.iszdry = iszdry;
	}
	
}
