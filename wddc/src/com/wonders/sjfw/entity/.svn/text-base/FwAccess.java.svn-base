package com.wonders.sjfw.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("pf_fw_access")
public class FwAccess {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("fw_access_id")
    private String fwAccessId;
    /**
     * 申请主键
     */
    @Column("FW_APPLY_ID")
    private String fwApplyId;

    @Column("FW_INFO_ID")
    private String fwInfoId;

    @Column("method_key")
    private String methodKey;

    @Column("unit_key")
    private String unitKey;

    @Column("used_status")
    private String usedStatus;

    @Column("access_type")
    private String accessType;

    @Column("access_end_date")
    private Date accessEndDate;
    
    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(fwAccessId))
            return UUID.randomUUID().toString();
        return this.fwAccessId;
    }
    

	public String getFwAccessId() {
		return fwAccessId;
	}

	public void setFwAccessId(String fwAccessId) {
		this.fwAccessId = fwAccessId;
	}

	public String getFwApplyId() {
		return fwApplyId;
	}

	public void setFwApplyId(String fwApplyId) {
		this.fwApplyId = fwApplyId;
	}

	public String getFwInfoId() {
		return fwInfoId;
	}

	public void setFwInfoId(String fwInfoId) {
		this.fwInfoId = fwInfoId;
	}

	public String getMethodKey() {
		return methodKey;
	}

	public void setMethodKey(String methodKey) {
		this.methodKey = methodKey;
	}

	public String getUnitKey() {
		return unitKey;
	}

	public void setUnitKey(String unitKey) {
		this.unitKey = unitKey;
	}

	public String getUsedStatus() {
		return usedStatus;
	}

	public void setUsedStatus(String usedStatus) {
		this.usedStatus = usedStatus;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public Date getAccessEndDate() {
		return accessEndDate;
	}

	public void setAccessEndDate(Date accessEndDate) {
		this.accessEndDate = accessEndDate;
	}
    
    
}
