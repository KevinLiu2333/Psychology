package com.wondersgroup.wssip.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.wssip.model.bo.UaAuthInfo;

/**
 * AbstractAffairsapply entity provides the base persistence definition of the Affairsapply entity. @author MyEclipse
 * Persistence Tools
 */
@MappedSuperclass
public abstract class BaseUaRoleInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// Fields
	private Long roleid;
	private String rolename;
	private String roleenname;
	private String status;
	private String roletype;
	private List<UaAuthInfo> authList = new ArrayList<UaAuthInfo>();
	// Constructors
	@Id
	@GeneratedValue(generator="SEQ_UAROLEID")
	@GenericGenerator(name="SEQ_UAROLEID", strategy="native", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="SEQ_UAROLEID"), @org.hibernate.annotations.Parameter(name="unsaved-value", value="0")})	
	@Column(name = "ROLEID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	
	@Column(name = "ROLENAME", nullable = false, length = 100)
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	@Column(name = "ROLEENNAME", nullable = false, length = 100)
	public String getRoleenname() {
		return roleenname;
	}
	public void setRoleenname(String roleenname) {
		this.roleenname = roleenname;
	}
	
	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "ROLETYPE", nullable = false, length = 20)
	public String getRoletype() {
		return roletype;
	}
	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "UAROLEAUTH", joinColumns = { @JoinColumn(name = "ROLEID") }, inverseJoinColumns = { @JoinColumn(name = "NODEID") })
	@Fetch(FetchMode.SUBSELECT)
	public List<UaAuthInfo> getAuthList() {
		return authList;
	}
	public void setAuthList(List<UaAuthInfo> authList) {
		this.authList = authList;
	}
	
}