package com.klsw.klswWebService.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="file.upload")
public class FileUploadConfig {

	String savePath;
	String maxSize;
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}
}
