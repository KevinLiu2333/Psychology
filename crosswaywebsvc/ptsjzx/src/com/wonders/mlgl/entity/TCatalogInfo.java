package com.wonders.mlgl.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("T_CATALOG_INFO")
public class TCatalogInfo {

    @Name
    @Prev(els = @EL("$me.initUUIDStCatId()"))
    @Column("ST_CAT_ID")
	private String stCatId; 
    /**
	 * 委办局代码
	 */
	@Column("ST_SOURCE_PROVIDER")
    private String stSourceProvider;
    /**
	 * 节点号
	 */
	@Column("ST_NODE_ID")
    private String stNodeId;
    /**
	 * 节点名称
	 */
	@Column("ST_NODE_NAME")
    private String stNodeName;
    /**
	 * 父节点代码
	 */
	@Column("ST_PARENT_NODE_ID")
    private String stParentNodeId;
    /**
	 * 层级
	 */
	@Column("NM_LEVEL_ID")
    private Integer nmLevelId;
    /**
	 * 是否可编辑
	 */
	@Column("ST_IF_MODIFYABLE")
    private String stIfModifyable;
    /**
	 * 创建人ID
	 */
	@Column("ST_CREATE_USER_ID")
    private String stCreateUserId;
    /**
	 * 创建日期
	 */
	@Column("DT_CREATE_DATE")
    private Date dtCreateDate;
    /**
	 * 最后修改人ID
	 */
	@Column("ST_MODIFY_USER_ID")
    private String stModifyUserId;
    /**
	 * 最后修改日期
	 */
	@Column("DT_MODIFY_DATE")
    private Date dtModifyDate;
    /**
	 * 节点排序
	 */
	@Column("NM_NODE_ORDER")
    private Integer nmNodeOrder;
	
	public String initUUIDStCatId() {
		if (Strings.isEmpty(stCatId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.stCatId;
	}
	
	public String getStCatId() {
		return stCatId;
	}
	public void setStCatId(String stCatId) {
		this.stCatId = stCatId;
	}
	public String getStSourceProvider() {
		return stSourceProvider;
	}
	public void setStSourceProvider(String stSourceProvider) {
		this.stSourceProvider = stSourceProvider;
	}
	public String getStNodeId() {
		return stNodeId;
	}
	public void setStNodeId(String stNodeId) {
		this.stNodeId = stNodeId;
	}
	public String getStNodeName() {
		return stNodeName;
	}
	public void setStNodeName(String stNodeName) {
		this.stNodeName = stNodeName;
	}
	public String getStParentNodeId() {
		return stParentNodeId;
	}
	public void setStParentNodeId(String stParentNodeId) {
		this.stParentNodeId = stParentNodeId;
	}
	public Integer getNmLevelId() {
		return nmLevelId;
	}
	public void setNmLevelId(Integer nmLevelId) {
		this.nmLevelId = nmLevelId;
	}
	public String getStIfModifyable() {
		return stIfModifyable;
	}
	public void setStIfModifyable(String stIfModifyable) {
		this.stIfModifyable = stIfModifyable;
	}
	public String getStCreateUserId() {
		return stCreateUserId;
	}
	public void setStCreateUserId(String stCreateUserId) {
		this.stCreateUserId = stCreateUserId;
	}
	public Date getDtCreateDate() {
		return dtCreateDate;
	}
	public void setDtCreateDate(Date dtCreateDate) {
		this.dtCreateDate = dtCreateDate;
	}
	public String getStModifyUserId() {
		return stModifyUserId;
	}
	public void setStModifyUserId(String stModifyUserId) {
		this.stModifyUserId = stModifyUserId;
	}
	public Date getDtModifyDate() {
		return dtModifyDate;
	}
	public void setDtModifyDate(Date dtModifyDate) {
		this.dtModifyDate = dtModifyDate;
	}
	public Integer getNmNodeOrder() {
		return nmNodeOrder;
	}
	public void setNmNodeOrder(Integer nmNodeOrder) {
		this.nmNodeOrder = nmNodeOrder;
	}

}
