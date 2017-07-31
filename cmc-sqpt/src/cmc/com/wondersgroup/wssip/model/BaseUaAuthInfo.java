package com.wondersgroup.wssip.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * AbstractAffairsapply entity provides the base persistence definition of the Affairsapply entity. @author MyEclipse
 * Persistence Tools
 */
@MappedSuperclass
public abstract class BaseUaAuthInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// Fields
	private Long nodeid;
	private String nodecode;
	private String nodename;
	private String nodedesc;
	private String nodetype;
	private String url;
	private Long parentnodeid;
	private Long nodeorder;
	private String status;
	private String icon1;
	// Constructors
	@Id
	@GeneratedValue(generator="SEQ_UAAUTHID")
	@GenericGenerator(name="SEQ_UAAUTHID", strategy="native", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="SEQ_UAAUTHID"), @org.hibernate.annotations.Parameter(name="unsaved-value", value="0")})	
	@Column(name = "NODEID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getNodeid() {
		return nodeid;
	}
	public void setNodeid(Long nodeid) {
		this.nodeid = nodeid;
	}
	
	@Column(name = "NODECODE", nullable = false, length = 100)
	public String getNodecode() {
		return nodecode;
	}
	public void setNodecode(String nodecode) {
		this.nodecode = nodecode;
	}
	
	@Column(name = "NODENAME", nullable = false, length = 100)
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	
	@Column(name = "NODEDESC", nullable = false, length = 200)
	public String getNodedesc() {
		return nodedesc;
	}
	public void setNodedesc(String nodedesc) {
		this.nodedesc = nodedesc;
	}
	
	@Column(name = "NODETYPE", nullable = false, length = 10)
	public String getNodetype() {
		return nodetype;
	}
	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}
	
	@Column(name = "URL", nullable = false, length = 100)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name = "PARENTNODEID", precision = 16, scale = 0)
	public Long getParentnodeid() {
		return parentnodeid;
	}
	public void setParentnodeid(Long parentnodeid) {
		this.parentnodeid = parentnodeid;
	}
	
	@Column(name = "NODEORDER", nullable = false, precision = 4, scale = 0)
	public Long getNodeorder() {
		return nodeorder;
	}
	public void setNodeorder(Long nodeorder) {
		this.nodeorder = nodeorder;
	}
	
	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "ICON1", nullable = false, length = 100)
	public String getIcon1() {
		return icon1;
	}
	public void setIcon1(String icon1) {
		this.icon1 = icon1;
	}
	
}