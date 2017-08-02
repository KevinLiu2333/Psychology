package com.wonders.sjfw.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

/**
 * 服务调用.
 */
@Table("PF_FW_USED")
public class FwUsed {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("FW_USED_ID")
    private String fwUsedId;
    /**
     * 调用单位id
     */
    @Column("UNIT_ID")
    private String unitId;
    /**
     * 调用单位
     */
    @Column("UNIT_NAME")
    private String unitName;

    @Column("FW_INFO_ID")
    private String fwInfoId;

    @Column("FW_LOG_ID")
    private String fwLogId;

    @Column("NIAN")
    private String nian;

    @Column("YUE")
    private String yue;

    @Column("TIAN")
    private String tian;

    @Column("XIAOSHI")
    private String xiaoshi;

    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(fwUsedId))
            return UUID.randomUUID().toString();
        return this.fwUsedId;
    }

    public String getFwUsedId() {
        return fwUsedId;
    }

    public void setFwUsedId(String fwUsedId) {
        this.fwUsedId = fwUsedId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getFwInfoId() {
        return fwInfoId;
    }

    public void setFwInfoId(String fwInfoId) {
        this.fwInfoId = fwInfoId;
    }

    public String getFwLogId() {
        return fwLogId;
    }

    public void setFwLogId(String fwLogId) {
        this.fwLogId = fwLogId;
    }

    public String getNian() {
        return nian;
    }

    public void setNian(String nian) {
        this.nian = nian;
    }

    public String getYue() {
        return yue;
    }

    public void setYue(String yue) {
        this.yue = yue;
    }

    public String getTian() {
        return tian;
    }

    public void setTian(String tian) {
        this.tian = tian;
    }

    public String getXiaoshi() {
        return xiaoshi;
    }

    public void setXiaoshi(String xiaoshi) {
        this.xiaoshi = xiaoshi;
    }
}
