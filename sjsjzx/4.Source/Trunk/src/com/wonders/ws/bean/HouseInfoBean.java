package com.wonders.ws.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("T_GA_FWJBXX")
public class HouseInfoBean {
	/**居住房屋类型**/
	@Column("JZFWLX")
	private String jzfwlx;
	/**房主实际居住地详址**/
	@Column("FZSJJZDZXZ")
	private String fzsjjzdzxz;
	/**门牌号**/
	@Column("MLPH")
	private String mlph;
	/**户号**/
	@Column("HH")
	private String hh;
	/**房主证件类型**/
	@Column("FZZJLX")
	private String fzzjlx;
	/**房主证件号码**/
	@Column("FZZJHM")
	private String fzzjhm;
	/**房主姓名**/
	@Column("FZXM")
	private String fzxm;
	/**所属居村委会**/
	@Column("JCWMC")
	private String jcwmc;
	/**房屋编码,对应人口库的JZFWID**/
	@Column("FWBM")
	private String fwbm;
	
	public String getJzfwlx() {
		return jzfwlx;
	}
	public void setJzfwlx(String jzfwlx) {
		this.jzfwlx = jzfwlx;
	}
	public String getFzsjjzdzxz() {
		return fzsjjzdzxz;
	}
	public void setFzsjjzdzxz(String fzsjjzdzxz) {
		this.fzsjjzdzxz = fzsjjzdzxz;
	}
	public String getMlph() {
		return mlph;
	}
	public void setMlph(String mlph) {
		this.mlph = mlph;
	}
	public String getHh() {
		return hh;
	}
	public void setHh(String hh) {
		this.hh = hh;
	}
	public String getFzzjlx() {
		return fzzjlx;
	}
	public void setFzzjlx(String fzzjlx) {
		this.fzzjlx = fzzjlx;
	}
	public String getFzzjhm() {
		return fzzjhm;
	}
	public void setFzzjhm(String fzzjhm) {
		this.fzzjhm = fzzjhm;
	}
	public String getFzxm() {
		return fzxm;
	}
	public void setFzxm(String fzxm) {
		this.fzxm = fzxm;
	}
	public String getJcwmc() {
		return jcwmc;
	}
	public void setJcwmc(String jcwmc) {
		this.jcwmc = jcwmc;
	}
	public String getFwbm() {
		return fwbm;
	}
	public void setFwbm(String fwbm) {
		this.fwbm = fwbm;
	}

}
