package com.wonders.wddc.tiles.tag.entity;

import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

import java.util.UUID;

/**
 * 标签分类.
 */
@Table("TILES_TAG_TYPE")
public class TagTypeBo {
    /**
     * 主键
     */
    @Name
    @Prev(els = @EL("$me.initUUID()"))
    @Column("TYPE_ID")
    private String typeId;
    /**
     *名称
     */
    @Column("TYPE_NAME")
    private String typeName;

    /**
     *分类
     */
    @Column("CATALOG")
    private String catalog;
    /**
     *显示样式
     */
    @Column("SHOW_STYLE")
    private String showStyle;

    /**
     * 生成uuid主键
     * @return
     */
    public String initUUID() {
        if (Strings.isEmpty(typeId))
            return UUID.randomUUID().toString();
        return this.typeId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

	public String getShowStyle() {
		return showStyle;
	}

	public void setShowStyle(String showStyle) {
		this.showStyle = showStyle;
	}
    
}
