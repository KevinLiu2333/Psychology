package com.wonders.wddc.suite.user.entity;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.util.UUID;

/**
 * 系统标签信息.
 */
@Table("SUITE_USER_UNIT")
public class UserUnitBo {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("UNIT_ID")
    private String unitId;
    /**
     *单位名称
     */
    @Column("UNIT_NAME")
    private String unitName;

    /**
     *用户key
     */
    @Column("UNIT_KEY")
    private String unitKey;
    /**
     *用户等级
     */
    @Column("UNIT_LEVEL")
    private String unitLevel;
    /**
     *单位描述
     */
    @Column("UNIT_MEMO")
    private String unitMemo;

    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(unitId))
            return UUID.randomUUID().toString();
        return this.unitId;
    }
    /**
     * 秘钥的后四位
     * @return
     */
    public String getUnitKeyDisplay() {
       String showString = "********"+this.unitKey.substring(15);
        return showString;
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

    public String getUnitKey() {
        return unitKey;
    }

    public void setUnitKey(String unitKey) {
        this.unitKey = unitKey;
    }

    public String getUnitLevel() {
        return unitLevel;
    }

    public void setUnitLevel(String unitLevel) {
        this.unitLevel = unitLevel;
    }

    public String getUnitMemo() {
        return unitMemo;
    }

    public void setUnitMemo(String unitMemo) {
        this.unitMemo = unitMemo;
    }
}
