package com.wonders.tiles.workflow.entity;

import java.util.UUID;
import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Strings;

@Table("T_DATA_ITEM")
public class TDataItem {

    @Name
    @Prev(els = @EL("$me.initUUIDStDataItemId()"))
    @Column("ST_DATA_ITEM_ID")
	private String stDataItemId; 
    /**
	 * 外键
	 */
	@Column("ST_SOURCE_ID")
    private String stSourceId;
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
    public String getStSourceId() {
		return stSourceId;
	}
	public void setStSourceId(String stSourceId) {
		this.stSourceId = stSourceId;
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
