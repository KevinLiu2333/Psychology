package com.wonders.wbj.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("DATA_FILE")
public class DataFile {

	@Column("ID")
	private String id;
	@Column("FILENAME")
	private String filename;
	@Column("FILELOCAL")
	private String filelocal;
	@Column("ILLUSTRATION")
	private String illustration;
	@Column("UPDATE_DATE")
	private Date updatedate;
	
	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getIllustration() {
		return illustration;
	}

	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilelocal() {
		return filelocal;
	}

	public void setFilelocal(String filelocal) {
		this.filelocal = filelocal;
	}
	
}
