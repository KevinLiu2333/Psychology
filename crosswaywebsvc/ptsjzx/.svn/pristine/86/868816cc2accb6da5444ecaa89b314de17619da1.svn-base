package com.wonders.platform.entity;


import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.util.UUID;

/**
 * 系统资源项信息.
 */
@Table("R_RESOURCE_DETAILS")
public class ZyItem {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("DETAILS_ID")
    private String zyItemId;
    /**
     *中文名称
     */
    @Column("DATA_ITEM_NAME")
    private String cnName;
    /**
     *英文名称
     */
    @Column("FIELD_CODE")
    private String enName;
    /**
     *资源类型
     */
    @Column("DATA_ITEM_TYPE")
    private String itemType;
    /**
     *资源描述
     */
    @Column("MEMO")
    private String itemDesc;
    /**
     *公开方式
     */
    @Column("OPEN_PROPERTY")
    private String openType;
    /**
     *外键
     */
    @Column("RESOURCE_ID")
    private String zyInfoId;


    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(zyItemId))
            return UUID.randomUUID().toString();
        return this.zyItemId;
    }

    public String getZyItemId() {
        return zyItemId;
    }

    public void setZyItemId(String zyItemId) {
        this.zyItemId = zyItemId;
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

    public String getZyInfoId() {
        return zyInfoId;
    }

    public void setZyInfoId(String zyInfoId) {
        this.zyInfoId = zyInfoId;
    }

}
