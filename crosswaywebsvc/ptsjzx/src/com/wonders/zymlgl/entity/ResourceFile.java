package com.wonders.zymlgl.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * 资源目录-上传文件信息.
 */
@Table("R_RESOURCE_FILE")
public class ResourceFile {
	@Column("ID")
	private String id;
	/**文件名**/
	@Column("FILE_NAME")
	private String fileName;
	/**数据文件版本号**/
	@Column("VERSION_NUM")
	private int versionNum;
	/**上传时间**/
	@Column("UPLOAD_DATE")
	private Date uploadDate;
	/**上传人**/
	@Column("UPLOAD_PERSON")
	private String uploadPerson;
	/**是否有效**/
	@Column("VALID")
	private String valid;
	/**错误标题**/
	@Column("ERROR_TITLE")
	private String errorTitle;
	/**错误内容**/
	@Column("ERROR_CONTENT")
	private String errorContent;
	/**数据年份**/
	@Column("DATA_YEAR")
	private String dataYear;
	/**数据月份**/
	@Column("DATA_MON")
	private String dataMon;
	@Column("DATA_COUNT")
	private int dataCount;
	/**所属资源id**/
	@Column("RESOURCE_ID")
	private String resourceId;
	/**模板类型(R001-资源目录模板,D001-资源数据模板)**/
	@Column("TEMPLET_TYPE")
	private String templetType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getErrorTitle() {
		return errorTitle;
	}

	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}

	public String getErrorContent() {
		return errorContent;
	}

	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}

	public String getDataYear() {
		return dataYear;
	}

	public void setDataYear(String dataYear) {
		this.dataYear = dataYear;
	}

	public String getDataMon() {
		return dataMon;
	}

	public void setDataMon(String dataMon) {
		this.dataMon = dataMon;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getTempletType() {
		return templetType;
	}

	public void setTempletType(String templetType) {
		this.templetType = templetType;
	}
}
