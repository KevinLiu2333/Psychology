package com.wonders.jhjdjk.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;




@Table("JK_JHJD_JSJK")
public class JkJhjdJsjk {
	
	
	@Name
	@Column("ID")
	private String id;
	@Column("SUOSHUYEWU")
	private String suoshuyewu;
	@Column("YEWUBIAO")
	private String yewubiao;
	@Column("DANGRIXINZENG")
	private int dangrixinzeng;
	@Column("DANGRIGENGXIN")
	private int dangrigengxin;
	@Column("DANGRISHANCHU")
	private int dangrishanchu;
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
	public int getDangrixinzeng() {
		return dangrixinzeng;
	}
	public void setDangrixinzeng(int dangrixinzeng) {
		this.dangrixinzeng = dangrixinzeng;
	}
	public int getDangrigengxin() {
		return dangrigengxin;
	}
	public void setDangrigengxin(int dangrigengxin) {
		this.dangrigengxin = dangrigengxin;
	}
	public int getDangrishanchu() {
		return dangrishanchu;
	}
	public void setDangrishanchu(int dangrishanchu) {
		this.dangrishanchu = dangrishanchu;
	}
	public int getJieshouzongliang() {
		return jieshouzongliang;
	}
	public void setJieshouzongliang(int jieshouzongliang) {
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
	@Column("JIESHOUZONGLIANG")
	private int jieshouzongliang;
	@Column("XINZENGSHIJIAN")
	private Date xinzengshijian;
	@Column("XIUGAISHIJIAN")
	private Date xiugaishijian;
	@Column("SHANCHUSHIJIAN")
	private Date shanchushijian;
	@Column("ZUIHOUGENGXINSHIJIAN")
	private Date zuihougengxinshijian;
	@Column("JIANKONGDUIXIANG")
	private String jiankongduixiang;

}
