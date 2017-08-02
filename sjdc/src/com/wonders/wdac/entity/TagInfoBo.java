package com.wonders.wdac.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.*;

/**
 * @author wonders
 *标签实体类
 */
@Table("sjcl_tag")
public class TagInfoBo  {
	@Name
	@Column("id")
	private String id;
	//标签名
	@Column("tag_name")
	private String tagName;
	//标签类型
	@Column("tag_type")
	private String tagType;
	//标签级别
	@Column("tag_level")
	private Integer level;
	//描述
	@Column("tag_desc")
	private String tagDesc;
	//上级标签id
	@Column("pre_tag_id")
	private String preTagId;
	//上级标签名
	@Column("pre_tag_name")
	private String preTagName;
	//标签编号
	@Column("tag_number")
	private String tagNumber;
	//状态
	@Column("t_status")
	private String tStatus;
	//打标签次数
	@Column("t_do")
	private Integer tDo;
	//标签访问次数
	@Column("t_access")
	private Integer tAccess;
	//创建者ID
	@Column("operator_id")
	private String operatorId;
	//创建时间
	@Column("create_date")
	private Date createDate;
	//更新时间
	@Column("last_update")
	private Date lastUpdate;
	//标签属性
	@Column("tag_property")
	private String tagProperty;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagType() {
		return tagType;
	}
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getTagDesc() {
		return tagDesc;
	}
	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}
	public String getPreTagId() {
		return preTagId;
	}
	public void setPreTagId(String preTagId) {
		this.preTagId = preTagId;
	}
	public String getPreTagName() {
		return preTagName;
	}
	public void setPreTagName(String preTagName) {
		this.preTagName = preTagName;
	}
	public String getTagNumber() {
		return tagNumber;
	}
	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}
	public String gettStatus() {
		return tStatus;
	}
	public void settStatus(String tStatus) {
		this.tStatus = tStatus;
	}
	public Integer gettDo() {
		return tDo;
	}
	public void settDo(Integer tDo) {
		this.tDo = tDo;
	}
	public Integer gettAccess() {
		return tAccess;
	}
	public void settAccess(Integer tAccess) {
		this.tAccess = tAccess;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getTagProperty() {
		return tagProperty;
	}
	public void setTagProperty(String tagProperty) {
		this.tagProperty = tagProperty;
	}
	
	
	
}