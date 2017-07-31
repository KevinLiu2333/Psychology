package com.wonders.wddc.tiles.tag.entity;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.util.UUID;

/**
 * 系统标签信息.
 */
@Table("TILES_TAG_INFO")
public class TagInfoBo {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("TAG_ID")
    private String tagId;
    /**
     *分类
     */
    @Column("CATALOG")
    private String catalog;
    /**
     *类型
     */
    @Column("TYPE")
    private String type;
    /**
     *名称
     */
    @Column("SHOW_NAME")
    private String showName;
    /**
     *使用数量
     */
    @Column("USED_COUNT")
    private int usedCount;

    /**
     *使用数量
     */
    @Column("ORDERS")
    private int orders;

    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(tagId))
            return UUID.randomUUID().toString();
        return this.tagId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }
}
