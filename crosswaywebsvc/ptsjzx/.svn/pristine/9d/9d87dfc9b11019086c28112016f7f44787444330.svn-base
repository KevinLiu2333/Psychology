package com.wonders.mlgx.entity;

import java.util.Date;
import java.util.UUID;
import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

@Table("T_SOURCE_APPLY")
public class TSourceApply {
    @Name
    @Prev(els = @EL("$me.initUUIDStApplyId()"))
    @Column("ST_APPLY_ID")
	private String stApplyId; 
    /**
	 * 申请日期
	 */
    @Column("DT_APPLY")
	private Date dtApply; 
    /**
	 * 资源提供单位
	 */    
    @Column("ST_SOURCE_PROVIDER")
	private String stSourceProvider; 
    /**
	 * 申请批次
	 */
    @Column("ST_APP_SERIEL_NO")
	private String stAppSeriel; 
    /**
	 * 办理意见
	 * 1：同意；0：不同意；2：退回
	 */
    @Column("IF_AGREE")
	private String ifAgree; 
    /**
	 * 申请人
	 */
    @Column("ST_APPLICANT")
	private String stApplicant; 
    /**
	 * 资源申请单位
	 */    
    @Column("ST_APPLIER_ID")
	private String stApplierId;
    /**
	 * 备注
	 */    
    @Column("MEMO")
	private String memo;
    
    @Column("ST_STATUS")
	private String stStatus;
    
    @Column("DT_AGREE_APPLY")
	private Date dtAgreeApply; 
    
    @Column("ST_CHECK_USER_ID")
	private String stCheckUserId;
    
    @Column("ST_SOURCE_GAIN_TYPE")
	private String stSourceGainType;
    
    @Column("ST_SOURCE_GAIN_MEMO")
	private String stSourceGainMemo;
    
    /**
     * 申请期限
     */
    @Column("APPLY_DAYS")
    private String applyDays;
    
    /**
     * 申请人姓名
     */
    @Column("ST_APPLIER_NAME")
    private String stApplierName;
    
    /**
     * 申请人联系方式
     */
    @Column("ST_APPLIER_TEL")
    private String stApplierTel;
    
    /**
     *申请资源外键(ST_PUB_SOURCE_APP主键) 
     */
    @Column("ST_SOURCE_ID")
    private String stSourceId;
    
    /**
     * 申请资源名称
     */
    @Column("ST_SOURCE_NAME")
    private String stSourceName;
    
    /**
     * 审核人姓名
     */
    @Column("ST_AUDITER_NAME")
    private String stAuditerName;
    
    /**
     * 审核人联系方式
     */
    @Column("ST_AUDITER_TEL")
    private String stAuditerTel;
    
    /**
     * 审核不通过原因
     */
    @Column("NO_PASS_REASON")
    private String noPassReason;
    
	public String getStSourceGainType() {
		return stSourceGainType;
	}
	public void setStSourceGainType(String stSourceGainType) {
		this.stSourceGainType = stSourceGainType;
	}
	public String getStSourceGainMemo() {
		return stSourceGainMemo;
	}
	public void setStSourceGainMemo(String stSourceGainMemo) {
		this.stSourceGainMemo = stSourceGainMemo;
	}
	public Date getDtAgreeApply() {
		return dtAgreeApply;
	}
	public void setDtAgreeApply(Date dtAgreeApply) {
		this.dtAgreeApply = dtAgreeApply;
	}
	public String getStCheckUserId() {
		return stCheckUserId;
	}
	public void setStCheckUserId(String stCheckUserId) {
		this.stCheckUserId = stCheckUserId;
	}
	public String getStStatus() {
		return stStatus;
	}
	public void setStStatus(String stStatus) {
		this.stStatus = stStatus;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStApplyId() {
		return stApplyId;
	}
	public void setStApplyId(String stApplyId) {
		this.stApplyId = stApplyId;
	}
	public Date getDtApply() {
		return dtApply;
	}
	public void setDtApply(Date dtApply) {
		this.dtApply = dtApply;
	}
	public String getStSourceProvider() {
		return stSourceProvider;
	}
	public void setStSourceProvider(String stSourceProvider) {
		this.stSourceProvider = stSourceProvider;
	}
	public String getStAppSeriel() {
		return stAppSeriel;
	}
	public void setStAppSeriel(String stAppSeriel) {
		this.stAppSeriel = stAppSeriel;
	}
	public String getIfAgree() {
		return ifAgree;
	}
	public void setIfAgree(String ifAgree) {
		this.ifAgree = ifAgree;
	}
	public String getStApplicant() {
		return stApplicant;
	}
	public void setStApplicant(String stApplicant) {
		this.stApplicant = stApplicant;
	}
	public String getStApplierId() {
		return stApplierId;
	}
	public void setStApplierId(String stApplierId) {
		this.stApplierId = stApplierId;
	}   
    public String initUUIDStApplyId() {
		if (Strings.isEmpty(stApplyId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.stApplyId;
	}
	public String getApplyDays() {
		return applyDays;
	}
	public void setApplyDays(String applyDays) {
		this.applyDays = applyDays;
	}
	public String getStApplierName() {
		return stApplierName;
	}
	public void setStApplierName(String stApplierName) {
		this.stApplierName = stApplierName;
	}
	public String getStAuditerName() {
		return stAuditerName;
	}
	public void setStAuditerName(String stAuditerName) {
		this.stAuditerName = stAuditerName;
	}
	public String getStAuditerTel() {
		return stAuditerTel;
	}
	public void setStAuditerTel(String stAuditerTel) {
		this.stAuditerTel = stAuditerTel;
	}
	public String getNoPassReason() {
		return noPassReason;
	}
	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}
	public String getStApplierTel() {
		return stApplierTel;
	}
	public void setStApplierTel(String stApplierTel) {
		this.stApplierTel = stApplierTel;
	}
	public String getStSourceId() {
		return stSourceId;
	}
	public void setStSourceId(String stSourceId) {
		this.stSourceId = stSourceId;
	}
	public String getStSourceName() {
		return stSourceName;
	}
	public void setStSourceName(String stSourceName) {
		this.stSourceName = stSourceName;
	}
}
