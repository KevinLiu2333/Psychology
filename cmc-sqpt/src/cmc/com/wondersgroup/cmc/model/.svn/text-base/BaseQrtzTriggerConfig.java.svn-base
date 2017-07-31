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
 * AbstractQrtzTriggerConfig entity provides the base persistence definition of
 * the QrtzTriggerConfig entity.
 */
@MappedSuperclass
public abstract class BaseQrtzTriggerConfig implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	private String beanName;
	private String beanDesc;
	private String notes;
	private String validity;
	private Date createTime;
	private String creater;
	private String ext1;
	private String ext2;

	// Property accessors
	@Id
	@GeneratedValue(generator="SEQ_QRTZ_TRIGGER_CONFIG")
	@GenericGenerator(name="SEQ_QRTZ_TRIGGER_CONFIG", strategy="native", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="SEQ_QRTZ_TRIGGER_CONFIG"), @org.hibernate.annotations.Parameter(name="unsaved-value", value="0")})
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "BEAN_NAME", length = 300)
	public String getBeanName() {
		return this.beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	@Column(name = "BEAN_DESC", length = 300)
	public String getBeanDesc() {
		return this.beanDesc;
	}

	public void setBeanDesc(String beanDesc) {
		this.beanDesc = beanDesc;
	}

	@Column(name = "NOTES", length = 1000)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "VALIDITY", length = 4)
	public String getValidity() {
		return this.validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATER", length = 30)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Column(name = "EXT1", length = 500)
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

}