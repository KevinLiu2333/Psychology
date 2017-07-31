package com.wonders.jh.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


@Table("JK_JHJD_JSJK")
public class JieShou {
	@Column("ID")
	private String id ;
	@Column("SUOSHUYEWU")
	private String suoshuyewu ;
	@Column("YEWUBIAO")
	private String yewubiao ;
	@Column("DANGRIXINZENG")
	private long dangrixinzeng ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSuoshuyewu() {
		return suoshuyewu;
	}
	public void setSuoshuyewu(String suoshuyewu) {
		this.suoshuyewu = suoshuyewu;
	}
	public String getYewubiao() {
		return yewubiao;
	}
	public void setYewubiao(String yewubiao) {
		this.yewubiao = yewubiao;
	}
	public long getDangrixinzeng() {
		return dangrixinzeng;
	}
	public void setDangrixinzeng(long dangrixinzeng) {
		this.dangrixinzeng = dangrixinzeng;
	}
	public long getDangrigengxin() {
		return dangrigengxin;
	}
	public void setDangrigengxin(long dangrigengxin) {
		this.dangrigengxin = dangrigengxin;
	}
	public long getDangrishanchu() {
		return dangrishanchu;
	}
	public void setDangrishanchu(long dangrishanchu) {
		this.dangrishanchu = dangrishanchu;
	}
	public long getJieshouzongliang() {
		return jieshouzongliang;
	}
	public void setJieshouzongliang(long jieshouzongliang) {
		this.jieshouzongliang = jieshouzongliang;
	}
	public Date getXinzengshijian() {
		return xinzengshijian;
	}
	public void setXinzengshijian(Date xinzengshijian) {
		this.xinzengshijian = xinzengshijian;
	}
	public Date getXiugaishijian() {
		return xiugaishijian;
	}
	public void setXiugaishijian(Date xiugaishijian) {
		this.xiugaishijian = xiugaishijian;
	}
	public Date getShanchushijian() {
		return shanchushijian;
	}
	public void setShanchushijian(Date shanchushijian) {
		this.shanchushijian = shanchushijian;
	}
	public Date getZuihougengxinshijian() {
		return zuihougengxinshijian;
	}
	public void setZuihougengxinshijian(Date zuihougengxinshijian) {
		this.zuihougengxinshijian = zuihougengxinshijian;
	}
	public String getJiankongduixiang() {
		return jiankongduixiang;
	}
	public void setJiankongduixiang(String jiankongduixiang) {
		this.jiankongduixiang = jiankongduixiang;
	}
	public String getDuixiangbianma() {
		return duixiangbianma;
	}
	public void setDuixiangbianma(String duixiangbianma) {
		this.duixiangbianma = duixiangbianma;
	}
	@Column("DANGRIGENGXIN")
	private long dangrigengxin ;
	@Column("DANGRISHANCHU")
	private long dangrishanchu ;
	@Column("JIESHOUZONGLIANG")
	private long jieshouzongliang ;
	@Column("XINZENGSHIJIAN")
	private Date xinzengshijian ;
	@Column("XIUGAISHIJIAN")
	private Date xiugaishijian ;
	@Column("SHANCHUSHIJIAN")
	private Date shanchushijian ;
	@Column("ZUIHOUGENGXINSHIJIAN")
	private Date zuihougengxinshijian ;
	@Column("JIANKONGDUIXIANG")
	private String jiankongduixiang ;
	@Column("DUIXIANGBIANMA")
	private String duixiangbianma ;
	
}
