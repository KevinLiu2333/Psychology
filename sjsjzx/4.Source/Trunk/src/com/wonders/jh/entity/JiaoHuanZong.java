package com.wonders.jh.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


@Table("JK_JHJD_JHXXL")
public class JiaoHuanZong {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getRijiaohuanzongliang() {
		return rijiaohuanzongliang;
	}
	public void setRijiaohuanzongliang(long rijiaohuanzongliang) {
		this.rijiaohuanzongliang = rijiaohuanzongliang;
	}
	public long getDuixiangjiaohuanzongliang() {
		return duixiangjiaohuanzongliang;
	}
	public void setDuixiangjiaohuanzongliang(long duixiangjiaohuanzongliang) {
		this.duixiangjiaohuanzongliang = duixiangjiaohuanzongliang;
	}
	public long getJiaohuanzongliang() {
		return jiaohuanzongliang;
	}
	public void setJiaohuanzongliang(long jiaohuanzongliang) {
		this.jiaohuanzongliang = jiaohuanzongliang;
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
	@Column("ID")
	private String id ;
	@Column("RIJIAOHUANZONGLIANG")
	private long rijiaohuanzongliang ;
	@Column("DUIXIANGJIAOHUANZONGLIANG")
	private long duixiangjiaohuanzongliang ;
	@Column("JIAOHUANZONGLIANG")
	private long jiaohuanzongliang ;
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
