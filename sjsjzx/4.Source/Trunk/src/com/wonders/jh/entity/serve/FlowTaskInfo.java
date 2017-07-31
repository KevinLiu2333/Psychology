package com.wonders.jh.entity.serve;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 
 *流程定义对象
 */
@Table("FLOW_TASK_INFO")
public class FlowTaskInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Name
	@Column("TASK_ID")
	private String taskId;
	
	@Column("FLOW_NO")
	private String flowNo;
	
	@Column("BUSINESS_NO")
	private String businessNo;
	
	@Column("START_DATE")
	private Date startDate;
	
	@Column("END_DATE")
	private Date endDate;
	
	@Column("TASK_STATUS")
	private String taskStatus;
	
	@Column("CREATE_BY")
	private String createBy;
	
	@Column("HANDLE_BY")
	private String handleBy;
	
	@Column("CREATE_DATE")
	private Date createDate;
	
	@Column("IS_VALID")
	private String isValid;

	public FlowTaskInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlowTaskInfo(String taskId, String flowNo, String businessNo,
			Date startDate, Date endDate, String taskStatus, String createBy,
			String handleBy, Date createDate, String isValid) {
		super();
		this.taskId = taskId;
		this.flowNo = flowNo;
		this.businessNo = businessNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.taskStatus = taskStatus;
		this.createBy = createBy;
		this.handleBy = handleBy;
		this.createDate = createDate;
		this.isValid = isValid;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getHandleBy() {
		return handleBy;
	}

	public void setHandleBy(String handleBy) {
		this.handleBy = handleBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	
}
