package com.wonders.tiles.workflow.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 流程意见对象
 */

@Table("WF_OPNN")
public class WfOpnn implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	@Name
	@Column("OPNN_ID")
	private String opnnId;

	@Column("BUS_ID")
	private String busId;

	@Column("NODE_NAME")
	private String nodeName;

	@Column("USER_ID")
	private String userId;

	@Column("USER_NAME")
	private String userName;

	@Column("OPNN_TIME")
	private Date opnnTime;

	@Column("OPNN_TYPE")
	private String opnnType;

	@Column("OPNN_MEMO")
	private String opnnMemo;

	@Column("INSTANCE_NO")
	private String instanceNo;
	
	@Column("NODE_ID")
	private String nodeId;

	@Column("INSTANCE_ID")
	private String instanceId;

	public Date getOpnnTime() {
		return opnnTime;
	}

	public void setOpnnTime(Date opnnTime) {
		this.opnnTime = opnnTime;
	}

	/** default constructor */
	public WfOpnn() {
	}

	/** minimal constructor */
	public WfOpnn(String opnnId) {
		this.opnnId = opnnId;
	}

	public String getOpnnId() {
		return this.opnnId;
	}

	public void setOpnnId(String opnnId) {
		this.opnnId = opnnId;
	}

	public String getBusId() {
		return this.busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getOpnnType() {
		return this.opnnType;
	}

	public void setOpnnType(String opnnType) {
		this.opnnType = opnnType;
	}

	public String getOpnnMemo() {
		return this.opnnMemo;
	}

	public void setOpnnMemo(String opnnMemo) {
		this.opnnMemo = opnnMemo;
	}

	public String getInstanceNo() {
		return instanceNo;
	}

	public void setInstanceNo(String instanceNo) {
		this.instanceNo = instanceNo;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

}
