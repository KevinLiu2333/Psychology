package com.wonders.wdac.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("pf_zy_info")
public class PZyInfo {

	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("ZY_INFO_ID")
	private String zyInfoId;
	
	@Column("ZY_NAME")
	private String zyName;
	
	@Column("ZY_TABLE")
	private String zyTable;
	
	@Column("ZY_ABSTRACT")
	private String zyAbstract;
	
	@Column("TAG_LISTS")
	private String tagLists;
	
	@Column("ZY_UNIT")
	private String zyUnit;
	
	@Column("ZY_UPDATE")
	private String zyUpdate;
	
	@Column("CREATE_TIME")
	private Date createTime;
	
	@Column("OP_TIME")
	private Date opTime;
	
	@Column("ITEM_COUNT")
	private Integer itemCount;
	
	@Column("UNIT_ID")
	private String unitId;
	
	@Column("STATUS")
	private String status;
	
	@Column("AUDIT_RESULT")
	private String auditResult;
	
	@Column("AUDIT_REASON")
	private String auditReason;
	
	@Column("UPDATE_DESC")
	private String updateDesc;
	
	@Column("DATA_ORGIN")
	private String dataOrgin;
	
	public String getDateSourceId() {
		return dateSourceId;
	}

	public void setDateSourceId(String dateSourceId) {
		this.dateSourceId = dateSourceId;
	}
	//关联的数据源
	@Column("DATA_SOURCE_ID")
	private String dateSourceId;
	
	public String initUUID() {
		if (Strings.isEmpty(zyInfoId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.zyInfoId;
	}
	
	public String[] getTags() {
        if(!Strings.isBlank(tagLists)){
            return tagLists.split(",");
        }
        return null;
    }

	public String getZyInfoId() {
		return zyInfoId;
	}

	public void setZyInfoId(String zyInfoId) {
		this.zyInfoId = zyInfoId;
	}

	public String getZyName() {
		return zyName;
	}

	public void setZyName(String zyName) {
		this.zyName = zyName;
	}

	public String getZyTable() {
		return zyTable;
	}

	public void setZyTable(String zyTable) {
		this.zyTable = zyTable;
	}

	public String getZyAbstract() {
		return zyAbstract;
	}

	public void setZyAbstract(String zyAbstract) {
		this.zyAbstract = zyAbstract;
	}

	public String getTagLists() {
		return tagLists;
	}

	public void setTagLists(String tagLists) {
		this.tagLists = tagLists;
	}

	public String getZyUnit() {
		return zyUnit;
	}

	public void setZyUnit(String zyUnit) {
		this.zyUnit = zyUnit;
	}

	public String getZyUpdate() {
		return zyUpdate;
	}

	public void setZyUpdate(String zyUpdate) {
		this.zyUpdate = zyUpdate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getAuditReason() {
		return auditReason;
	}

	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}

	public String getUpdateDesc() {
		return updateDesc;
	}

	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}

	public String getDataOrgin() {
		return dataOrgin;
	}

	public void setDataOrgin(String dataOrgin) {
		this.dataOrgin = dataOrgin;
	}
	
}
