package com.wonders.jh.entity.serve;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 
 *流程定义对象
 */
@Table("FLOW_NODE_CONFIG")
public class FlowNodeConfig implements java.io.Serializable {

	/**
	 * flow_node_config
	 */
	private static final long serialVersionUID = 1L;

	@Name
	@Column("NODE_NO")
	private String nodeNo;
	
	@Column("FLOW_NO")
	private String flowNo;
	
	@Column("NODE_NAME")
	private String nodeName;
	
	@Column("LAST_NODE_NO")
	private String lastNodeNo;
	
	@Column("NEXT_NODE_NO")
	private String nextNodeNo;
	
	@Column("CREATE_DATE")
	private Date createDate;
	
	@Column("IS_VALID")
	private String isValid;

	public FlowNodeConfig(String nodeNo, String flowNo, String nodeName,
			String lastNodeNo, String nextNodeNo,
			Date createDate, String isValid) {
		super();
		this.nodeNo = nodeNo;
		this.flowNo = flowNo;
		this.nodeName = nodeName;
		this.lastNodeNo = lastNodeNo;
		this.nextNodeNo = nextNodeNo;
		this.createDate = createDate;
		this.isValid = isValid;
	}

	public FlowNodeConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getLastNodeNo() {
		return lastNodeNo;
	}

	public void setLastNodeNo(String lastNodeNo) {
		this.lastNodeNo = lastNodeNo;
	}

	public String getNextNodeNo() {
		return nextNodeNo;
	}

	public void setNextNodeNo(String nextNodeNo) {
		this.nextNodeNo = nextNodeNo;
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
