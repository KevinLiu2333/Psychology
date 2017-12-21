package com.wonders.tiles.authority.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
@Table("UA_ORG")
public class Org {

	@Name
	@Column("ORG_CODE")
	private String orgCode;
	@Column("ORG_NAME")
	private String orgName;
	@Column("ORG_PARENT_ID")
	private String orgParentId;
	@Column("ORG_ID")
	private String orgId;
	@Column("LAST_UPDATE_TIME")
	private String lastUpdateTime;
	@Column("CREATE_TIME")
	private String createTime;
	@Column("ORG_SEQUENCE")
	private String orgSequence;
	@Column("UNIT_ID")
	private String unitId;
	@Column("STATU")
	private String statu;
	
	/**
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * @param orgCode
	 *            the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName
	 *            the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the orgSequence
	 */
	public String getOrgSequence() {
		return orgSequence;
	}

	/**
	 * @param orgSequence
	 *            the orgSequence to set
	 */
	public void setOrgSequence(String orgSequence) {
		this.orgSequence = orgSequence;
	}

	/**
	 * @return the statu
	 */
	public String getStatu() {
		return statu;
	}

	/**
	 * @param statu the statu to set
	 */
	public void setStatu(String statu) {
		this.statu = statu;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TsOrg [orgCode=" + orgCode + ", orgName=" + orgName + ", orgSequence=" + orgSequence + ", statu=" + statu + ",orgParentId="+orgParentId+",orgId="+orgId+",lastUpdateTime="+lastUpdateTime+",createTime="+createTime+",unitId="+unitId+"]";
	}

	public String getOrgParentId() {
		return orgParentId;
	}

	public void setOrgParentId(String orgParentId) {
		this.orgParentId = orgParentId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

}
