package com.wondersgroup.cmc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * AbstractQrtzTriggerConfigRelevance entity provides the base persistence
 * definition of the QrtzTriggerConfigRelevance entity.
 */
@MappedSuperclass
public abstract class BaseQrtzTriggerConfigRelevance implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	private Long triggerConfigId;
	private String triggerName;
	private String validity;
	private String creater;
	private Date createTime;
	private String ext1;
	private String ext2;
	private String triggerType;
	private Date startTime;
	private Date endTime;
	private String cronExpr;
	private Long exeucount;
	private Long exeuspace;

	// Property accessors
	@Id
	@GeneratedValue(generator="SEQ_QRTZ_TRIGGER_RELEVANCE")
	@GenericGenerator(name="SEQ_QRTZ_TRIGGER_RELEVANCE", strategy="native", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="SEQ_QRTZ_TRIGGER_RELEVANCE"), @org.hibernate.annotations.Parameter(name="unsaved-value", value="0")})
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TRIGGER_CONFIG_ID", nullable = true, length = 10)
	public Long getTriggerConfigId() {
		return triggerConfigId;
	}
	
	public void setTriggerConfigId(Long triggerConfigId) {
		this.triggerConfigId = triggerConfigId;
	}
	
	@Column(name = "TRIGGER_NAME", length = 300)
	public String getTriggerName() {
		return this.triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	@Column(name = "VALIDITY", length = 4)
	public String getValidity() {
		return this.validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	@Column(name = "CREATER", length = 30)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "EXT1", length = 100)
	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "EXT2", length = 100)
	public String getExt2() {
		return this.ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Column(name = "TRIGGER_TYPE", length = 20)
	public String getTriggerType() {
		return this.triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME")
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_TIME")
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "CRON_EXPR", length = 1000)
	public String getCronExpr() {
		return this.cronExpr;
	}

	public void setCronExpr(String cronExpr) {
		this.cronExpr = cronExpr;
	}

	@Column(name = "EXEUCOUNT", precision = 10, scale = 0)
	public Long getExeucount() {
		return this.exeucount;
	}

	public void setExeucount(Long exeucount) {
		this.exeucount = exeucount;
	}

	@Column(name = "EXEUSPACE", precision = 10, scale = 0)
	public Long getExeuspace() {
		return this.exeuspace;
	}

	public void setExeuspace(Long exeuspace) {
		this.exeuspace = exeuspace;
	}

}