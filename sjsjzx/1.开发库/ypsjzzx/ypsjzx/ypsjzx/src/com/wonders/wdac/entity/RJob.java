package com.wonders.wdac.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("ac_job")
public class RJob {

	@Name
	@Column("ID_JOB")
	private String idJob;
	
	@Column("ID_DIRECTORY")
	private int idDirectory;
	
	@Column("JOB_NAME")
	private String jobName;
	
	@Column("DESCRIPTION")
	private String description;
	
	@Column("EXTENDED_DESCRIPTION")
	private String extendedDescription;
	
	@Column("JOB_VERSION")
	private String jobVersion;
	
	@Column("JOB_STATUS")
	private int jobStatus;
	
	@Column("ID_DATEBASE_LOG")
	private int idDatebaseLog;
	
	@Column("TABLE_NAME_LOG")
	private String tableNameLog; 
	
	@Column("CREATED_USER")
	private String createdUser;
	
	@Column("CREATED_DATE")
	private Date createdDate;
	
	@Column("MODIFIED_USER")
	private String modifiedUser;
	
	@Column("MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column("USE_BATCH_ID")
	private int useBatchId;
	
	@Column("PASS_BATCH_ID")
	private int passBatchID;
	
	@Column("USE_LOGFIELD")
	private int useLogfield;
	
	@Column("SHARED_FILE")
	private String sharedFile;

	public String getIdJob() {
		return idJob;
	}

	public void setIdJob(String idJob) {
		this.idJob = idJob;
	}

	public int getIdDirectory() {
		return idDirectory;
	}

	public void setIdDirectory(int idDirectory) {
		this.idDirectory = idDirectory;
	}

	public String getjobName() {
		return jobName;
	}

	public void setjobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExtendedDescription() {
		return extendedDescription;
	}

	public void setExtendedDescription(String extendedDescription) {
		this.extendedDescription = extendedDescription;
	}

	public String getJobVersion() {
		return jobVersion;
	}

	public void setJobVersion(String jobVersion) {
		this.jobVersion = jobVersion;
	}

	public int getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(int jobStatus) {
		this.jobStatus = jobStatus;
	}

	public int getIdDatebaseLog() {
		return idDatebaseLog;
	}

	public void setIdDatebaseLog(int idDatebaseLog) {
		this.idDatebaseLog = idDatebaseLog;
	}

	public String getTableNameLog() {
		return tableNameLog;
	}

	public void setTableNameLog(String tableNameLog) {
		this.tableNameLog = tableNameLog;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getUseBatchId() {
		return useBatchId;
	}

	public void setUseBatchId(int useBatchId) {
		this.useBatchId = useBatchId;
	}

	public int getPassBatchID() {
		return passBatchID;
	}

	public void setPassBatchID(int passBatchID) {
		this.passBatchID = passBatchID;
	}

	public int getUseLogfield() {
		return useLogfield;
	}

	public void setUseLogfield(int useLogfield) {
		this.useLogfield = useLogfield;
	}

	public String getSharedFile() {
		return sharedFile;
	}

	public void setSharedFile(String sharedFile) {
		this.sharedFile = sharedFile;
	}
	
}