package com.wonders.zx.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

/**
 * @author Wanda
 * 核心标签库实体类
 *
 */
@Table("SJTZ_CORE_TAGLIBS")
public class CoreTaglibsBo {
	/**
	 * ID
	 */
	@Name
    @Prev(els = @EL("$me.initUUIDId()"))
	@Column("ID")
	private String id;
	
	/**
	 * 标签名
	 */
	@Column("T_NAME")
	private String tagName;
	
	/**
	 * 标签描述
	 */
	@Column("T_DESC")
	private String tagDesc;
	
	/**
	 * 标签属性
	 */
	@Column("T_ATTR")
	private String tagAttr;
	
	/**
	 * 上级标签id
	 */
	@Column("PRE_TAG_ID")
	private String tagPreId;
	
	/**
	 * 下级标签id
	 */
	@Column("SUB_TAG_ID")
	private String tagSubId;
	
	/**
	 * 打标签次数
	 */
	@Column("T_DO")
	private String tagDoCount;
	
	/**
	 * 标签访问次数
	 */
	@Column("T_ACCESS")
	private String tagAccess;
	
	/**
	 * 标签状态
	 */
	@Column("T_STATUS")
	private String tagStatus;
	
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

	public String getTagDesc() {
		return tagDesc;
	}

	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}

	public String getTagAttr() {
		return tagAttr;
	}

	public void setTagAttr(String tagAttr) {
		this.tagAttr = tagAttr;
	}

	public String getTagPreId() {
		return tagPreId;
	}

	public void setTagPreId(String tagPreId) {
		this.tagPreId = tagPreId;
	}

	public String getTagSubId() {
		return tagSubId;
	}

	public void setTagSubId(String tagSubId) {
		this.tagSubId = tagSubId;
	}

	public String getTagDoCount() {
		return tagDoCount;
	}

	public void setTagDoCount(String tagDoCount) {
		this.tagDoCount = tagDoCount;
	}

	public String getTagAccess() {
		return tagAccess;
	}

	public void setTagAccess(String tagAccess) {
		this.tagAccess = tagAccess;
	}

	public String getTagStatus() {
		return tagStatus;
	}

	public void setTagStatus(String tagStatus) {
		this.tagStatus = tagStatus;
	}

	/**
	 * @return
	 * 自动生成UUID
	 */
	public String initUUIDId() {
		if (Strings.isEmpty(id))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.id;
	}
	
	
	
	
}
