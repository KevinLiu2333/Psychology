package com.wondersgroup.cmc.quartz.vo;

import java.util.Date;

import com.wondersgroup.cmc.model.BaseQrtzTriggers;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class QrtzTriggersVO extends BaseQrtzTriggers implements ValueObject {

	private static final long serialVersionUID = 1L;
	private Long exeuCount;	//执行次数
	private String type;		//类型
	private String cronExp;		//cron表达式
	private Long exeuSpace;	//执行间隔
	private String beanName;	//实体名称
	private String notes;		//备注
	private String beanDesc;	//bean描述
	private String createrName;	//创建人姓名
	private Date createTime;	//创建时间
	private Long beanId;		//beanID
	private String ext1;		//备注
	private String ext2;		//扩展字段
	private String action;		//操作
	private Date startTime_date;//开始时间
	private Date endTime_date;	//结束时间
	public Long getExeuCount() {
		return exeuCount;
	}
	public void setExeuCount(Long exeuCount) {
		this.exeuCount = exeuCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCronExp() {
		return cronExp;
	}
	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}
	public Long getExeuSpace() {
		return exeuSpace;
	}
	public void setExeuSpace(Long exeuSpace) {
		this.exeuSpace = exeuSpace;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getBeanDesc() {
		return beanDesc;
	}
	public void setBeanDesc(String beanDesc) {
		this.beanDesc = beanDesc;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getBeanId() {
		return beanId;
	}
	public void setBeanId(Long beanId) {
		this.beanId = beanId;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getStartTime_date() {
		return startTime_date;
	}
	public void setStartTime_date(Date startTime_date) {
		this.startTime_date = startTime_date;
	}
	public Date getEndTime_date() {
		return endTime_date;
	}
	public void setEndTime_date(Date endTime_date) {
		this.endTime_date = endTime_date;
	}
}
