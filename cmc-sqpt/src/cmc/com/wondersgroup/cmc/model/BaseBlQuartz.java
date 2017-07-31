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
 * AbstractBlQuartz entity provides the base persistence definition of the
 * BlQuartz entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class BaseBlQuartz implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	private Date triggerTime;
	private String triggerTaskName;
	private String relationId;
	private String triggerTaskDesc;
	private String notes;
	private Date createDate;
	private String createBy;

	// Property accessors
	@Id
	@GeneratedValue(generator="SEQ_BL_QUARTZ")
	@GenericGenerator(name="SEQ_BL_QUARTZ", strategy="native", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="SEQ_BL_QUARTZ"), @org.hibernate.annotations.Parameter(name="unsaved-value", value="0")})
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRIGGER_TIME")
	public Date getTriggerTime() {
		return this.triggerTime;
	}

	public void setTriggerTime(Date triggerTime) {
		this.triggerTime = triggerTime;
	}

	@Column(name = "TRIGGER_TASK_NAME", length = 300)
	public String getTriggerTaskName() {
		return this.triggerTaskName;
	}

	public void setTriggerTaskName(String triggerTaskName) {
		this.triggerTaskName = triggerTaskName;
	}

	@Column(name = "RELATION_ID", length = 20)
	public String getRelationId() {
		return this.relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	@Column(name = "TRIGGER_TASK_DESC", length = 1000)
	public String getTriggerTaskDesc() {
		return this.triggerTaskDesc;
	}

	public void setTriggerTaskDesc(String triggerTaskDesc) {
		this.triggerTaskDesc = triggerTaskDesc;
	}

	@Column(name = "NOTES", length = 1000)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_BY", length = 30)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

}