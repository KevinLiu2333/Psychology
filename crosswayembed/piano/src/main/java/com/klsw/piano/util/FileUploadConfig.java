package com.klsw.piano.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="file.upload")
public class FileUploadConfig {
	private String mid2Mp3;
	private String sf2Path;
	private String savePath;
	private String maxSize;
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
	public String getMid2Mp3() {
		return mid2Mp3;
	}
	public void setMid2Mp3(String mid2Mp3) {
		this.mid2Mp3 = mid2Mp3;
	}
	public String getSf2Path() {
		return sf2Path;
	}
	public void setSf2Path(String sf2Path) {
		this.sf2Path = sf2Path;
	}

}
