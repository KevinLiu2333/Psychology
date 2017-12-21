package com.wonders.wddc.tiles.ueditor.upload;

import com.wonders.wddc.tiles.ueditor.PathFormat;
import com.wonders.wddc.tiles.ueditor.define.AppInfo;
import com.wonders.wddc.tiles.ueditor.define.BaseState;
import com.wonders.wddc.tiles.ueditor.define.FileType;
import com.wonders.wddc.tiles.ueditor.define.State;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public final class Base64Uploader {

	public static State save(String content, Map<String, Object> conf) {
		
		byte[] data = decode(content);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}

		String suffix = FileType.getSuffix("JPG");

		String savePath = PathFormat.parse((String) conf.get("savePath"),
				(String) conf.get("filename"));
		
		savePath = savePath + suffix;
		String physicalPath = (String) conf.get("rootPath") + savePath;

		State storageState = StorageManager.saveBinaryFile(data, physicalPath);

		if (storageState.isSuccess()) {
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}

		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64.decodeBase64(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}
	
}