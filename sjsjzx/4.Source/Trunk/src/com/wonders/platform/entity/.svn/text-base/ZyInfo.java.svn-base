package com.wonders.platform.entity;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.util.Date;
import java.util.UUID;

/**
 * 资源基本信息.
 */
@Table("R_RESOURCE")
public class ZyInfo {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("RESOURCE_ID")
    private String zyInfoId;
    /**
     *名称
     */
    @Column("RESOURCE_NAME")
    private String zyName;
    /**
     *表名称
     */
    @Column("TABLE_NAME")
    private String zyTable;
    /**
     *摘要
     */
    @Column("ABSTRACT_WORD")
    private String zyAbstract;
    /**
     *标签
     */
    @Column("KEY_WORD")
    private String tagLists;
    /**
     *提供单位
     */
    @Column("PROVIDE_DEPARTMENT")
    private String zyUnit;
    /**
     *更新频度
     */
    @Column("UPDATE_RATE")
    private String zyUpdate;
    /**
     *创建时间
     */
    @Column("UPDATE_DATE")
    private Date createTime;
    /**
     *操作时间
     */
    @Column("UPDATE_DATE")
    private Date opTime;

    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(zyInfoId))
            return UUID.randomUUID().toString();
        return this.zyInfoId;
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

    public String getZyTable() {
        return zyTable;
    }

    public void setZyTable(String zyTable) {
        this.zyTable = zyTable;
    }
}
