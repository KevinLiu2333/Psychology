package com.wonders.jh.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("JK_JHJD_JHXQ")
public class JiaoHuan {
	@Column("ID")
	private String id ;
	@Column("JIANKONGDUIXIANG")
	private String jiankongduixiang ;
	@Column("DUIXIANGBIANMA")
	private String duixiangbianma ;
	@Column("WANGLUOZHUANGTAI")
	private String wangluozhuangtai ;
	@Column("WANGLUOZHUANGTAI1")
	private String wangluozhuangtai1 ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getWangluozhuangtai() {
		return wangluozhuangtai;
	}
	public void setWangluozhuangtai(String wangluozhuangtai) {
		this.wangluozhuangtai = wangluozhuangtai;
	}
	public String getWangluozhuangtai1() {
		return wangluozhuangtai1;
	}
	public void setWangluozhuangtai1(String wangluozhuangtai1) {
		this.wangluozhuangtai1 = wangluozhuangtai1;
	}
	public String getJiedianzhuangtai() {
		return jiedianzhuangtai;
	}
	public void setJiedianzhuangtai(String jiedianzhuangtai) {
		this.jiedianzhuangtai = jiedianzhuangtai;
	}
	public String getJiedianzhuangtai1() {
		return jiedianzhuangtai1;
	}
	public void setJiedianzhuangtai1(String jiedianzhuangtai1) {
		this.jiedianzhuangtai1 = jiedianzhuangtai1;
	}
	public String getShujuyuan() {
		return shujuyuan;
	}
	public void setShujuyuan(String shujuyuan) {
		this.shujuyuan = shujuyuan;
	}
	public String getShujuyuan1() {
		return shujuyuan1;
	}
	public void setShujuyuan1(String shujuyuan1) {
		this.shujuyuan1 = shujuyuan1;
	}
	public String getQianzhijiip() {
		return qianzhijiip;
	}
	public void setQianzhijiip(String qianzhijiip) {
		this.qianzhijiip = qianzhijiip;
	}
	public String getYingpanrongliang() {
		return yingpanrongliang;
	}
	public void setYingpanrongliang(String yingpanrongliang) {
		this.yingpanrongliang = yingpanrongliang;
	}
	public String getYiyongyingpanrongliang() {
		return yiyongyingpanrongliang;
	}
	public void setYiyongyingpanrongliang(String yiyongyingpanrongliang) {
		this.yiyongyingpanrongliang = yiyongyingpanrongliang;
	}
	public String getNeicunrongliang() {
		return neicunrongliang;
	}
	public void setNeicunrongliang(String neicunrongliang) {
		this.neicunrongliang = neicunrongliang;
	}
	public String getYiyongneicunrongliang() {
		return yiyongneicunrongliang;
	}
	public void setYiyongneicunrongliang(String yiyongneicunrongliang) {
		this.yiyongneicunrongliang = yiyongneicunrongliang;
	}
	public String getCpurongliang() {
		return cpurongliang;
	}
	public void setCpurongliang(String cpurongliang) {
		this.cpurongliang = cpurongliang;
	}
	public String getYiyongcpurongliang() {
		return yiyongcpurongliang;
	}
	public void setYiyongcpurongliang(String yiyongcpurongliang) {
		this.yiyongcpurongliang = yiyongcpurongliang;
	}
	public long getDaifashujuzongliang() {
		return daifashujuzongliang;
	}
	public void setDaifashujuzongliang(long daifashujuzongliang) {
		this.daifashujuzongliang = daifashujuzongliang;
	}
	public long getZongjieshouliang() {
		return zongjieshouliang;
	}
	public void setZongjieshouliang(long zongjieshouliang) {
		this.zongjieshouliang = zongjieshouliang;
	}
	public long getZongfasongliang() {
		return zongfasongliang;
	}
	public void setZongfasongliang(long zongfasongliang) {
		this.zongfasongliang = zongfasongliang;
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
	@Column("JIEDIANZHUANGTAI")
	private String jiedianzhuangtai ;
	@Column("JIEDIANZHUANGTAI1")
	private String jiedianzhuangtai1 ;
	@Column("SHUJUYUAN")
	private String shujuyuan ;
	@Column("SHUJUYUAN1")
	private String shujuyuan1 ;
	@Column("QIANZHIJIIP")
	private String qianzhijiip ;
	@Column("YINGPANRONGLIANG")
	private String yingpanrongliang ;
	@Column("YIYONGYINGPANRONGLIANG")
	private String yiyongyingpanrongliang ;
	@Column("NEICUNRONGLIANG")
	private String neicunrongliang ;
	@Column("YIYONGNEICUNRONGLIANG")
	private String yiyongneicunrongliang ;
	@Column("CPURONGLIANG")
	private String cpurongliang ;
	@Column("YIYONGCPURONGLIANG")
	private String yiyongcpurongliang ;
	@Column("DAIFASHUJUZONGLIANG")
	private long daifashujuzongliang ;
	@Column("ZONGJIESHOULIANG")
	private long zongjieshouliang ;
	@Column("ZONGFASONGLIANG")
	private long zongfasongliang ;
	@Column("XINZENGSHIJIAN")
	private Date xinzengshijian ;
	@Column("XIUGAISHIJIAN")
	private Date xiugaishijian ;
	@Column("SHANCHUSHIJIAN")
	private Date shanchushijian ;
	@Column("ZUIHOUGENGXINSHIJIAN")
	private Date zuihougengxinshijian ;

}
