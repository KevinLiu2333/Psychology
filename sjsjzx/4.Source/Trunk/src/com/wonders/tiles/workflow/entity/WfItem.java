package com.wonders.tiles.workflow.entity;


public class WfItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String instanceId;
	
	private String busId;
	
	private String nextNodeId;
	
	private String signUserId;
	
	private String memo;
	
	private String userId;
	
	private String sendOuts;
	
	private String backNodeId;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getNextNodeId() {
		return nextNodeId;
	}

	public void setNextNodeId(String nextNodeId) {
		this.nextNodeId = nextNodeId;
	}

	public String getSignUserId() {
		return signUserId;
	}

	public void setSignUserId(String signUserId) {
		this.signUserId = signUserId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSendOuts() {
		return sendOuts;
	}

	public void setSendOuts(String sendOuts) {
		this.sendOuts = sendOuts;
	}

	public String getBackNodeId() {
		return backNodeId;
	}

	public void setBackNodeId(String backNodeId) {
		this.backNodeId = backNodeId;
	}


}
