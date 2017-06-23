package com.klsw.website.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="file.upload")
public class FileUploadConfig {

	String savePath;
	String maxSize;
	String rootPath;
	String url;
	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
