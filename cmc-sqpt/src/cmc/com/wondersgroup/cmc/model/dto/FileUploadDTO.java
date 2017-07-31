package com.wondersgroup.cmc.model.dto;

import java.io.Serializable;

public class FileUploadDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String filename;
	private String filecontent;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilecontent() {
		return filecontent;
	}
	public void setFilecontent(String filecontent) {
		this.filecontent = filecontent;
	}
}
