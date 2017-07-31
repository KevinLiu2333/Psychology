package com.wonders.jh.entity.serve;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 
 *部门申请服务 ip 中间存储表
 */
@Table("SERVE_DEPT_IP")
public class ServeDeptIp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Name
	@Column("ID")
	private String id;
	
	@Column("DEPT_NO")
	private String deptNo;
	
	@Column("SERVE_ID")
	private String serveId;
	
	@Column("CLIENT_IP")
	private String clientIp;
	
	@Column("IS_PASS")
	private String isPass;
	
	@Column("IS_VALID")
	private String isValid;

	
	@Column("CHECK_DEPT")
	private String checkDept;

	
	@Column("CHECK_DATE")
	private Date checkDate;

	
	@Column("APPLY_DATE")
	private Date applyDate;
	

	@Column("CLIENT_KEY")
	private String clientKey;

	public ServeDeptIp() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public ServeDeptIp(String id, String deptNo, String serveId,
			String clientIp, String isPass, String isValid, String checkDept,
			Date checkDate, Date applyDate,String clientKey) {
		super();
		this.id = id;
		this.deptNo = deptNo;
		this.serveId = serveId;
		this.clientIp = clientIp;
		this.isPass = isPass;
		this.isValid = isValid;
		this.checkDept = checkDept;
		this.checkDate = checkDate;
		this.applyDate = applyDate;
		this.clientKey = clientKey;
	}



	public String getCheckDept() {
		return checkDept;
	}



	public void setCheckDept(String checkDept) {
		this.checkDept = checkDept;
	}



	public Date getApplyDate() {
		return applyDate;
	}



	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getServeId() {
		return serveId;
	}

	public void setServeId(String serveId) {
		this.serveId = serveId;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getIsPass() {
		return isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}



	public Date getCheckDate() {
		return checkDate;
	}



	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}



	public String getClientKey() {
		return clientKey;
	}



	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	
	
}
