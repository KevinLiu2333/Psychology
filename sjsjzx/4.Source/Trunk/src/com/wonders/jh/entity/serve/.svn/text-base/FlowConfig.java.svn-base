package com.wonders.jh.entity.serve;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 
 *流程定义对象
 */
@Table("FLOW_CONFIG")
public class FlowConfig implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Name
	@Column("FLOW_NO")
	private String flowNo;
	
	@Column("FLOW_NAME")
	private String flowName;
	
	@Column("CREATE_DATE")
	private Date createDate;
	
	@Column("IS_VALID")
	private String isValid;

	public FlowConfig() {
		// TODO Auto-generated constructor stub
	}

	
	public FlowConfig(String flowNo, String flowName, Date createDate,
			String isValid) {
		super();
		this.flowNo = flowNo;
		this.flowName = flowName;
		this.createDate = createDate;
		this.isValid = isValid;
	}


	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
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
