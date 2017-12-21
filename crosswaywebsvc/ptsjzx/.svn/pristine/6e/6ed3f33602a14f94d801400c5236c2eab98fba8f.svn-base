package com.wonders.zymlgl.entity;

import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

/**
 * 资源项字段信息.
 */
@Table("R_RESOURCE_DETAILS")
public class ResourceDetails {
	@Name
    @Prev(els = @EL("$me.initUUIDDetailsId()"))
	@Column("DETAILS_ID")
	private String detailsId;
	/**资源项名称**/
	@Column("DATA_ITEM_NAME")
	private String dataItemName;
	/**字段英文名称**/
	@Column("FIELD_CODE")
	private String fieldCode;
	/**数据类型(1-字符,2-日期,3-数字)**/
	@Column("DATA_ITEM_TYPE")
	private String dataItemType;
	/**备注**/
	@Column("MEMO")
	private String memo;
	/**共享属性**/
	@Column("SHARE_PROPERTY")
	private String shareProperty;
	/**不共享理由**/
	@Column("NO_SHARE_REASON")
	private String noShareReason;
	/**公开属性(如果共享属性不对外共享，则公开属性不对外公开)**/
	@Column("OPEN_PROPERTY")
	private String openProperty;
	/**不公开理由**/
	@Column("NO_OPEN_REASON")
	private String noOpenReason;
	/**排序字段**/
	@Column("ORDER_NUM")
	private int orderNum;
	/**资源主键(本表外键)**/
	@Column("RESOURCE_ID")
	private String resourceId;
	
	public String initUUIDDetailsId() {
		if (Strings.isEmpty(detailsId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.detailsId;
	}
	
	public String getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}

	public String getDataItemName() {
		return dataItemName;
	}
	public void setDataItemName(String dataItemName) {
		this.dataItemName = dataItemName;
	}
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	public String getDataItemType() {
		return dataItemType;
	}
	public void setDataItemType(String dataItemType) {
		this.dataItemType = dataItemType;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getShareProperty() {
		return shareProperty;
	}
	public void setShareProperty(String shareProperty) {
		this.shareProperty = shareProperty;
	}
	public String getNoShareReason() {
		return noShareReason;
	}
	public void setNoShareReason(String noShareReason) {
		this.noShareReason = noShareReason;
	}
	public String getOpenProperty() {
		return openProperty;
	}
	public void setOpenProperty(String openProperty) {
		this.openProperty = openProperty;
	}
	public String getNoOpenReason() {
		return noOpenReason;
	}
	public void setNoOpenReason(String noOpenReason) {
		this.noOpenReason = noOpenReason;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
}
