package com.wonders.sjfw.entity;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.util.Date;
import java.util.UUID;

/**
 * 服务基本信息.
 */
@Table("PF_FW_INFO")
public class FwInfo {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("FW_INFO_ID")
    private String fwInfoId;

    @Column("ZY_INFO_ID")
    private String zyInfoId;
    /**
     * 服务代码
     */
    @Column("FW_CODE")
    private String fwCode;
    /**
     * 服务名称
     */
    @Column("FW_NAME")
    private String fwName;
    /**
     * 摘要
     */
    @Column("FW_ABSTRACT")
    private String fwAbstract;
    /**
     * 类型
     */
    @Column("FW_TYPE")
    private String fwType;
    /**
     * 类型代码
     */
    @Column("FW_TYPE_CODE")
    private String fwTypeCode;
    /**
     * 标签
     */
    @Column("TAG_LIST")
    private String tagList;
    /**
     * 公开类型
     */
    @Column("OPEN_TYPE")
    private String openType;
    /**
     * 申请类型
     */
    @Column("APPLY_TYPE")
    private String applyType;
    /**
     * 预警级别
     */
    @Column("ALERT_LEVEL")
    private String alertLevel;
    /**
     * 状态
     */
    @Column("STATUS")
    private String status;
    /**
     * 创建时间
     */
    @Column("CREATE_TIME")
    private Date createTime;
    /**
     * 操作时间
     */
    @Column("OP_TIME")
    private Date opTime;
    /**
     * 申请次数
     */
    @Column("APPLY_COUNT")
    private int applyCount;
    /**
     * 调用次数
     */
    @Column("USED_COUNT")
    private int usedCount;

    /**
     * 文件主键
     */
    @Column("CONFIG_FILE_ID")
    private String fileId;

    /**
     * 配置URL
     */
    @Column("CONFIG_URL")
    private String configUrl;

    /**
     * URL说明
     */
    @Column("CONFIG_MEMO")
    private String configMemo;

    /**
     * URL类型
     */
    @Column("CONFIG_URL_TYPE")
    private String urlType;

    /**
     * 配置结果类型
     */
    @Column("CONFIG_RESULT_TYPE")
    private String resultType;

    /**
     * 配置结果类型
     */
    @Column("MULT_STAT_ID")
    private String multStatId;
    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(fwInfoId))
            return UUID.randomUUID().toString();
        return this.fwInfoId;
    }

    public String[] getTags() {
        if(!Strings.isBlank(tagList)){
            return tagList.split(",");
        }
        return null;
    }

    public String getFwName() {
        return fwName;
    }

    public void setFwName(String fwName) {
        this.fwName = fwName;
    }

    public String getFwAbstract() {
        return fwAbstract;
    }

    public void setFwAbstract(String fwAbstract) {
        this.fwAbstract = fwAbstract;
    }

    public String getFwType() {
        return fwType;
    }

    public void setFwType(String fwType) {
        this.fwType = fwType;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }
    
    public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFwInfoId() {
        return fwInfoId;
    }

    public void setFwInfoId(String fwInfoId) {
        this.fwInfoId = fwInfoId;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public int getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public String getZyInfoId() {
        return zyInfoId;
    }

    public void setZyInfoId(String zyInfoId) {
        this.zyInfoId = zyInfoId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

	public String getConfigUrl() {
		return configUrl;
	}

	public void setConfigUrl(String configUrl) {
		this.configUrl = configUrl;
	}

	public String getConfigMemo() {
		return configMemo;
	}

	public void setConfigMemo(String configMemo) {
		this.configMemo = configMemo;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getFwTypeCode() {
		return fwTypeCode;
	}

	public void setFwTypeCode(String fwTypeCode) {
		this.fwTypeCode = fwTypeCode;
	}

	public String getMultStatId() {
		return multStatId;
	}

	public void setMultStatId(String multStatId) {
		this.multStatId = multStatId;
	}

	public String getFwCode() {
		return fwCode;
	}

	public void setFwCode(String fwCode) {
		this.fwCode = fwCode;
	}


    
}
