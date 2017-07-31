/**
 * 
 */
package com.wonders.tiles.ui;

import java.util.HashMap;
import java.util.Map;


/**
 * DWZ 页面工具
 * 
 * @author Gordon
 *
 */
public class ZymlUI extends UI{

	public enum CallbackType {
		CLOSE_CURENT
	}
	
	public static Map<String, Object> divSearch(StatusCode statusCode, String rel) {
		return divSearch(statusCode, null, rel, null);
	}
	
	public static Map<String, Object> divSearch(StatusCode statusCode, String msg, String rel) {
		return divSearch(statusCode, msg, rel, null);
	}
	
	public static Map<String, Object> divSearch(StatusCode statusCode, String rel, CallbackType callbackType) {
		return divSearch(statusCode, null, rel, callbackType);
	}
	
	public static Map<String, Object> divSearch(StatusCode statusCode, String msg, String rel, CallbackType callbackType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusCode", statusCode.code());
		map.put("message", (statusCode == StatusCode.OK) ? (msg != null ? msg : "操作成功！") : (msg != null ? msg : "操作失败！"));
		map.put("rel", rel);
		if(callbackType == CallbackType.CLOSE_CURENT){
			map.put("callbackType", "closeCurrent");
		}
		return map;
	}

}
