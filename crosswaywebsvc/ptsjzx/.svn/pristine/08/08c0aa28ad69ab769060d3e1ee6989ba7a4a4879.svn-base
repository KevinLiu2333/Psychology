package com.wonders.mlgx.entity;

import java.util.Date;
import java.util.UUID;
import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

@Table("T_SOURCE_APP")
public class TSourceApp {

    @Name
    @Prev(els = @EL("$me.initUUIDStSourceId()"))
    @Column("ST_SOURCE_ID")
	private String stSourceId; 
    /**
	 * 信息资源名称
	 */
	@Column("ST_SOURCE_NAME")
    private String stSourceName;
    /**
	 * 资源提供单位
	 */
	@Column("ST_SOURCE_PROVIDER")
    private String stSourceProvider;
    /**
	 * 资源提供方地址
	 */
	@Column("ST_SOURCE_PROV_ADDR")
    private String stSourceProvAddr;
    /**
	 * 目录ID
	 */
	@Column("ST_CAT_ID")
    private String stCatId;
    /**
	 * 信息资源标识符
	 */
	@Column("ST_SOURCE_IDENTIFIER")
    private String stSourceIdentifier;
    /**
	 * 信息资源摘要
	 */
	@Column("ST_SOURCE_ABS")
    private String stSourceAbs;
    /**
	 * 关键字
	 */
	@Column("ST_KEY_WORDS")
    private String stKeyWords;
    /**
	 * 负责人
	 */
	@Column("MANAGER")
    private String manager;
    /**
	 * 负责人电话
	 */
	@Column("MANAGER_PHONE")
    private String managerPhone;
    /**
	 * 共享方式
	 */
	@Column("ST_SHARE_TYPE")
    private String stShareType;
    /**
	 * 共享更新频度
	 */
	@Column("ST_SHARE_FRE")
    private String stShareFre;
    /**
	 * 共享频度间隔时间（分钟）
	 */
	@Column("NM_SHARE_INTERVAL")
    private Integer nmShareInterval;
    /**
	 * 共享交换方式
	 */
	@Column("ST_SHARE_EX_TYPE")
    private String stShareExType;
    /**
	 * 公开方式
	 */
	@Column("ST_PUB_TYPE")
    private String stPubType;
    /**
	 * 公开更新频度
	 */
	@Column("ST_PUB_FRE")
    private String stPubFre;
    /**
	 * 公开频度间隔时间（分钟）
	 */
	@Column("NM_PUB_INTERVAL")
    private Integer nmPubInterval;
    /**
	 * 公开资源获取方式
	 */
	@Column("ST_PUB_GET_TYPE")
    private String stPubGetType;
    /**
	 * 公开收费方式
	 */
	@Column("ST_PUB_CHARGE_TYPE")
    private String stPubChargeType;
    /**
	 * 国家主题分类一级
	 */
	@Column("ST_GJZT_FIRST_ID")
    private String stGjztFirstId;
    /**
	 * 国家主题分类二级
	 */
	@Column("ST_GJZT_SEC_ID")
    private String stGjztSecId;
    /**
	 * 是否有信息化系统
	 */
	@Column("ST_IF_HAVE_SYSTEM")
    private String stIfHaveSystem;
    /**
	 * 系统名称
	 */
	@Column("ST_SYSTEM_NAME")
    private String stSystemName;
    /**
	 * 系统在线地址
	 */
	@Column("ST_SYSTEM_ADDR")
    private String stSystemAddr;
    /**
	 * 创建日期
	 */
	@Column("DT_CREATE_DATE")
    private Date dtCreateDate;
    /**
	 * 创建人ID
	 */
	@Column("ST_CREATE_USER_ID")
    private String stCreateUserId;
    /**
	 * 状态
	 */
	@Column("ST_STATUS")
    private String stStatus;
    /**
	 * 发布日期
	 */
	@Column("DT_PUB_DATE")
    private Date dtPubDate;
    /**
	 * 发布人ID
	 */
	@Column("ST_PUB_USER_ID")
    private String stPubUserId;
    /**
	 * 是否有效
	 */
	@Column("ST_VALID")
    private String stValid;
	/**
	 * 部门主题
	 */
	@Column("ST_BMZT")
    private String stBmzt;
	
    public String initUUIDStSourceId() {
		if (Strings.isEmpty(stSourceId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.stSourceId;
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

	public String getStSourceProvider() {
		return stSourceProvider;
	}

	public void setStSourceProvider(String stSourceProvider) {
		this.stSourceProvider = stSourceProvider;
	}

	public String getStSourceProvAddr() {
		return stSourceProvAddr;
	}

	public void setStSourceProvAddr(String stSourceProvAddr) {
		this.stSourceProvAddr = stSourceProvAddr;
	}

	public String getStCatId() {
		return stCatId;
	}

	public void setStCatId(String stCatId) {
		this.stCatId = stCatId;
	}

	public String getStSourceIdentifier() {
		return stSourceIdentifier;
	}

	public void setStSourceIdentifier(String stSourceIdentifier) {
		this.stSourceIdentifier = stSourceIdentifier;
	}

	public String getStSourceAbs() {
		return stSourceAbs;
	}

	public void setStSourceAbs(String stSourceAbs) {
		this.stSourceAbs = stSourceAbs;
	}

	public String getStKeyWords() {
		return stKeyWords;
	}

	public void setStKeyWords(String stKeyWords) {
		this.stKeyWords = stKeyWords;
	}

	public String getStShareType() {
		return stShareType;
	}

	public void setStShareType(String stShareType) {
		this.stShareType = stShareType;
	}

	public String getStShareFre() {
		return stShareFre;
	}

	public void setStShareFre(String stShareFre) {
		this.stShareFre = stShareFre;
	}

	public Integer getNmShareInterval() {
		return nmShareInterval;
	}

	public void setNmShareInterval(Integer nmShareInterval) {
		this.nmShareInterval = nmShareInterval;
	}

	public String getStShareExType() {
		return stShareExType;
	}

	public void setStShareExType(String stShareExType) {
		this.stShareExType = stShareExType;
	}

	public String getStPubType() {
		return stPubType;
	}

	public void setStPubType(String stPubType) {
		this.stPubType = stPubType;
	}

	public String getStPubFre() {
		return stPubFre;
	}

	public void setStPubFre(String stPubFre) {
		this.stPubFre = stPubFre;
	}

	public Integer getNmPubInterval() {
		return nmPubInterval;
	}

	public void setNmPubInterval(Integer nmPubInterval) {
		this.nmPubInterval = nmPubInterval;
	}

	public String getStPubGetType() {
		return stPubGetType;
	}

	public void setStPubGetType(String stPubGetType) {
		this.stPubGetType = stPubGetType;
	}

	public String getStPubChargeType() {
		return stPubChargeType;
	}

	public void setStPubChargeType(String stPubChargeType) {
		this.stPubChargeType = stPubChargeType;
	}

	public String getStGjztFirstId() {
		return stGjztFirstId;
	}

	public void setStGjztFirstId(String stGjztFirstId) {
		this.stGjztFirstId = stGjztFirstId;
	}

	public String getStGjztSecId() {
		return stGjztSecId;
	}

	public void setStGjztSecId(String stGjztSecId) {
		this.stGjztSecId = stGjztSecId;
	}

	public String getStIfHaveSystem() {
		return stIfHaveSystem;
	}

	public void setStIfHaveSystem(String stIfHaveSystem) {
		this.stIfHaveSystem = stIfHaveSystem;
	}

	public String getStSystemName() {
		return stSystemName;
	}

	public void setStSystemName(String stSystemName) {
		this.stSystemName = stSystemName;
	}

	public String getStSystemAddr() {
		return stSystemAddr;
	}

	public void setStSystemAddr(String stSystemAddr) {
		this.stSystemAddr = stSystemAddr;
	}

	public Date getDtCreateDate() {
		return dtCreateDate;
	}

	public void setDtCreateDate(Date dtCreateDate) {
		this.dtCreateDate = dtCreateDate;
	}

	public String getStCreateUserId() {
		return stCreateUserId;
	}

	public void setStCreateUserId(String stCreateUserId) {
		this.stCreateUserId = stCreateUserId;
	}

	public String getStStatus() {
		return stStatus;
	}

	public void setStStatus(String stStatus) {
		this.stStatus = stStatus;
	}

	public Date getDtPubDate() {
		return dtPubDate;
	}

	public void setDtPubDate(Date dtPubDate) {
		this.dtPubDate = dtPubDate;
	}

	public String getStPubUserId() {
		return stPubUserId;
	}

	public void setStPubUserId(String stPubUserId) {
		this.stPubUserId = stPubUserId;
	}

	public String getStValid() {
		return stValid;
	}

	public void setStValid(String stValid) {
		this.stValid = stValid;
	}

	public String getStBmzt() {
		return stBmzt;
	}

	public void setStBmzt(String stBmzt) {
		this.stBmzt = stBmzt;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
    
	
}
