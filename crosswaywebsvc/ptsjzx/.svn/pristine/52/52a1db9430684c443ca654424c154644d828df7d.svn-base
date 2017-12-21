package com.wonders.zymlgl.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

/**
 * 资源主表
 */
@Table("R_RESOURCE")
public class Resource {
	@Name
    @Prev(els = @EL("$me.initUUIDResourceId()"))
	@Column("RESOURCE_ID")
	private String resourceId;
	/**资源创建时间**/
	@Column("CREATE_TIME")
	private Date createTime;
	/**更新时间**/
	@Column("UPDATE_DATE")
	private Date updateDate;
	/**浏览量**/
	@Column("BROWSE_COUNT")
	private Long browseCount;
	/**下载量**/
	@Column("DOWLOAD_COUNT")
	private Long dowloadCount;
	/**关键字**/
	@Column("KEY_WORD")
	private String keyWord;
	/**提供科室**/
	@Column("PROVIDE_DEPARTMENT")
	private String provideDepartment;
	/**资源类型(r_rkl:人口类资源,r_frl:法人类资源,r_fwl:房屋类)**/
	@Column("RESOURCE_TYPE")
	private String resourceType;
	/**资源子项类型(1-人基本信息2-人户分离信息3-房屋信息4-法人登记信息5-法人资质信息6-法人监管信息7-两化融合信息8-高企和小巨人企业)**/
	@Column("RESOURCE_SUBITEM_TYPE")
	private String resourceSubitemType;
	/**有效性(1-有效,0-无效)**/
	@Column("VALIDITY")
	private String validity;
	/**版本号**/
	@Column("VERSON_NUMBER")
	private int versionNumber;
	
	/**摘要**/
	@Column("ABSTRACT_WORD")
	private String abstractWord;
	/**信息系统名称**/
	@Column("INFO_SYS_NAME")
	private String infoSysName;
	/**系统链接**/
	@Column("SYS_URL")
	private String sysUrl;
	/**系统简介**/
	@Column("SYS_ABSTRACT")
	private String sysAbstract;
	/**业务联系人**/
	@Column("BUS_LINKMAN")
	private String busLinkman;
	/**业务联系人电话**/
	@Column("BUS_LINKMAN_PHONE")
	private String busLinkmanPhone;
	/**对接联系人**/
	@Column("JOINT_LINKMAN")
	private String jointLinkman;
	/**对接联系人电话**/
	@Column("JOINT_LINKMAN_PHONE")
	private String jointLinkmanPhone;
	/**对接方式**/
	@Column("JOINT_TYPE")
	private String jointType;
	/**资源共享属性**/
	@Column("SHARE_PROPERTY")
	private String shareProperty;
	/**资源公开属性**/
	@Column("OPEN_PROPERTY")
	private String openProperty;
	/**资源更新频度**/
	@Column("UPDATE_RATE")
	private String updateRate;
	/**对应数据库表名称**/
	@Column("TABLE_NAME")
	private String tableName;
	/**资源目录名称**/
	@Column("RESOURCE_NAME")
	private String resourceName;
	/**提供科室部门ID**/
	@Column("PROVIDE_DEP_ID")
	private String provideDepId;
	/**资源状态(1-暂存,2-待审核,3-已审核通过,4-已退回)**/
	@Column("STATUS")
	private String status;
	/**操作者类型(1-委办领导,2-科委)**/
	@Column("OP_USER")
	private String opUser;
	@Column("DATAFLAG")
	private String dataflag;
	
	/**对应数据库表名称**/
	@Column("TABLE_CHINESE")
	private String tableChinese;
	
	public String getTableChinese() {
		return tableChinese;
	}

	public void setTableChinese(String tableChinese) {
		this.tableChinese = tableChinese;
	}

	public String initUUIDResourceId() {
		if (Strings.isEmpty(resourceId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.resourceId;
	}
	
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getBrowseCount() {
		return browseCount;
	}
	public void setBrowseCount(Long browseCount) {
		this.browseCount = browseCount;
	}
	public Long getDowloadCount() {
		return dowloadCount;
	}
	public void setDowloadCount(Long dowloadCount) {
		this.dowloadCount = dowloadCount;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getProvideDepartment() {
		return provideDepartment;
	}
	public void setProvideDepartment(String provideDepartment) {
		this.provideDepartment = provideDepartment;
	}
	
	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	public String getResourceSubitemType() {
		return resourceSubitemType;
	}

	public void setResourceSubitemType(String resourceSubitemType) {
		this.resourceSubitemType = resourceSubitemType;
	}

	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getAbstractWord() {
		return abstractWord;
	}

	public void setAbstractWord(String abstractWord) {
		this.abstractWord = abstractWord;
	}

	public String getInfoSysName() {
		return infoSysName;
	}

	public void setInfoSysName(String infoSysName) {
		this.infoSysName = infoSysName;
	}

	public String getSysAbstract() {
		return sysAbstract;
	}

	public void setSysAbstract(String sysAbstract) {
		this.sysAbstract = sysAbstract;
	}

	public String getBusLinkman() {
		return busLinkman;
	}

	public void setBusLinkman(String busLinkman) {
		this.busLinkman = busLinkman;
	}

	public String getJointLinkman() {
		return jointLinkman;
	}

	public void setJointLinkman(String jointLinkman) {
		this.jointLinkman = jointLinkman;
	}

	public String getJointType() {
		return jointType;
	}

	public void setJointType(String jointType) {
		this.jointType = jointType;
	}

	public String getShareProperty() {
		return shareProperty;
	}

	public void setShareProperty(String shareProperty) {
		this.shareProperty = shareProperty;
	}

	public String getUpdateRate() {
		return updateRate;
	}

	public void setUpdateRate(String updateRate) {
		this.updateRate = updateRate;
	}

	public String getOpenProperty() {
		return openProperty;
	}

	public void setOpenProperty(String openProperty) {
		this.openProperty = openProperty;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getProvideDepId() {
		return provideDepId;
	}

	public void setProvideDepId(String provideDepId) {
		this.provideDepId = provideDepId;
	}

	public String getSysUrl() {
		return sysUrl;
	}

	public void setSysUrl(String sysUrl) {
		this.sysUrl = sysUrl;
	}

	public String getBusLinkmanPhone() {
		return busLinkmanPhone;
	}

	public void setBusLinkmanPhone(String busLinkmanPhone) {
		this.busLinkmanPhone = busLinkmanPhone;
	}

	public String getJointLinkmanPhone() {
		return jointLinkmanPhone;
	}

	public void setJointLinkmanPhone(String jointLinkmanPhone) {
		this.jointLinkmanPhone = jointLinkmanPhone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getOpUser() {
		return opUser;
	}

	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDataflag() {
		return dataflag;
	}

	public void setDataflag(String dataflag) {
		this.dataflag = dataflag;
	}
	
}
