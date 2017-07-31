package com.wonders.platform.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;
import org.nutz.lang.Strings;

/**
 * 申请项配置信息.
 */
@Table("R_RESOURCE_APPLY_DETAILS")
@View("V_RESOURCE_APPLY")
public class FwConfig {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("APPLY_DETAILS_ID")
    private String fwConfigId;
    /**
     * 主键
     */
    @Column("RESOURCE_DETAILS_ID")
    private String zyItemId;
    /**
     * 类型
     */
    @Column("TYPE")
    private String configType;
    /**
     * 验证条件
     */
    @Column("CHECK_CODE")
    private String paramValid;

    @Column("APPLY_ID")
    private String fwApplyId;

    /**
     *中文名称
     */
    @Column("DATA_ITEM_NAME")
    @Readonly
    private String cnName;
    /**
     *英文名称
     */
    @Column("FIELD_CODE")
    @Readonly
    private String enName;
    /**
     *资源类型
     */
    @Column("DATA_ITEM_TYPE")
    @Readonly
    private String itemType;
    /**
     *资源描述
     */
    @Column("MEMO")
    @Readonly
    private String itemDesc;
    /**
     *公开方式
     */
    @Column("OPEN_PROPERTY")
    @Readonly
    private String openType;



    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(fwConfigId))
            return UUID.randomUUID().toString();
        return this.fwConfigId;
    }

    public String getFwConfigId() {
        return fwConfigId;
    }

    public void setFwConfigId(String fwConfigId) {
        this.fwConfigId = fwConfigId;
    }

    public String getZyItemId() {
        return zyItemId;
    }

    public void setZyItemId(String zyItemId) {
        this.zyItemId = zyItemId;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getParamValid() {
        return paramValid;
    }

    public void setParamValid(String paramValid) {
        this.paramValid = paramValid;
    }

    public String getFwApplyId() {
        return fwApplyId;
    }

    public void setFwApplyId(String fwApplyId) {
        this.fwApplyId = fwApplyId;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

}
