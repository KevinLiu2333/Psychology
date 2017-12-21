package com.wonders.mlgl.entity;

import java.util.UUID;
import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

@Table("T_PUB_DATA_ITEM")
public class TPubDataItem {

    @Name
    @Prev(els = @EL("$me.initUUIDStDataItemId()"))
    @Column("ST_DATA_ITEM_ID")
	private String stDataItemId; 
    /**
	 * 外键
	 */
	@Column("ST_PUB_ID")
    private String stPubId;
    /**
	 * 内部标识符
	 */
	@Column("ST_DATAITEM_IDENTIFIER")
    private String stDataitemIdentifier;
    /**
	 * 中文名称
	 */
	@Column("ST_CHINESE_NAME")
    private String stChineseName;
    /**
	 * 定义说明
	 */
	@Column("ST_DEFINE")
    private String stDefine;
    
    public String initUUIDStDataItemId() {
		if (Strings.isEmpty(stDataItemId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.stDataItemId;
	}
    
    public String getStDataItemId() {
		return stDataItemId;
	}
	public void setStDataItemId(String stDataItemId) {
		this.stDataItemId = stDataItemId;
	}
    public String getStPubId() {
		return stPubId;
	}
	public void setStPubId(String stPubId) {
		this.stPubId = stPubId;
	}
    public String getStDataitemIdentifier() {
		return stDataitemIdentifier;
	}
	public void setStDataitemIdentifier(String stDataitemIdentifier) {
		this.stDataitemIdentifier = stDataitemIdentifier;
	}
    public String getStChineseName() {
		return stChineseName;
	}
	public void setStChineseName(String stChineseName) {
		this.stChineseName = stChineseName;
	}
    public String getStDefine() {
		return stDefine;
	}
	public void setStDefine(String stDefine) {
		this.stDefine = stDefine;
	}
    
}
