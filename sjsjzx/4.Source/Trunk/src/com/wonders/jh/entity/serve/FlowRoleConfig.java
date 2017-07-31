package com.wonders.jh.entity.serve;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 
 *流程定义对象
 */
@Table("FLOW_ROLE_CONFIG")
public class FlowRoleConfig implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Name
	@Column("NO")
	private String no;
	
	@Column("ROLE_ID")
	private String roleId;
	
	@Column("FLOW_TYPE")
	private String flowType;
	
	@Column("CREATE_DATE")
	private Date createDate;
	
	@Column("IS_VALID")
	private String isValid;

	public FlowRoleConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlowRoleConfig(String no, String roleId, String flowType,
			Date createDate, String isValid) {
		super();
		this.no = no;
		this.roleId = roleId;
		this.flowType = flowType;
		this.createDate = createDate;
		this.isValid = isValid;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
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
