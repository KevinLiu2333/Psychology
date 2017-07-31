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

import com.wondersgroup.wssip.model.bo.UaRoleInfo;

/**
 * AbstractAffairsapply entity provides the base persistence definition of the Affairsapply entity. @author MyEclipse
 * Persistence Tools
 */
@MappedSuperclass
public abstract class BaseUaUserInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// Fields
	private Long userid;
	private String loginname;
	private String password;
	private String displayname;
	private String status;
	private String authentictype;
	private String certificate;
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;
	private List<UaRoleInfo> roleList = new ArrayList<UaRoleInfo>();

	// Constructors
	@Id
	@GeneratedValue(generator="SEQ_UAUSERID")
	@GenericGenerator(name="SEQ_UAUSERID", strategy="native", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="SEQ_UAUSERID"), @org.hibernate.annotations.Parameter(name="unsaved-value", value="0")})	
	@Column(name = "USERID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	@Column(name = "LOGINNAME", nullable = false, length = 100)
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	@Column(name = "PASSWORD", nullable = false, length = 100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "DISPLAYNAME", nullable = false, length = 100)
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	
	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "AUTHENTICTYPE", nullable = false, length = 60)
	public String getAuthentictype() {
		return authentictype;
	}
	public void setAuthentictype(String authentictype) {
		this.authentictype = authentictype;
	}
	
	@Column(name = "CERTIFICATE", length = 500)
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	@Column(name = "EXT1", length = 200)
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	
	@Column(name = "EXT2", length = 200)
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	
	@Column(name = "EXT3", length = 200)
	public String getExt3() {
		return ext3;
	}	
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	
	@Column(name = "EXT4", length = 200)
	public String getExt4() {
		return ext4;
	}	
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	
	@Column(name = "EXT5", length = 1000)
	public String getExt5() {
		return ext5;
	}
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "UAUSERROLE", joinColumns = { @JoinColumn(name = "USERID") }, inverseJoinColumns = { @JoinColumn(name = "ROLEID") })
	@Fetch(FetchMode.SUBSELECT)
	public List<UaRoleInfo> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<UaRoleInfo> roleList) {
		this.roleList = roleList;
	}
	
	
}