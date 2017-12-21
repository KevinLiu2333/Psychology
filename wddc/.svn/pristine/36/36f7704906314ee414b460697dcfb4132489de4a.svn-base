/**
 * 
 */
package com.wonders.wddc.tiles.tools;

import java.util.HashMap;
import java.util.Map;

import org.nutz.lang.Strings;

/**
 * DWZ 页面工具
 * 
 * @author Gordon
 *
 */
public class UI {

	public enum AjaxType {
		DIALOG, NAVTAB, FORWARD
	}
	
	public enum StatusCode {
		OK {
			@Override
			public String code() {
				return "200";
			}
		}, FAIL {
			@Override
			public String code() {
				return "300";
			}
		}, TIMEOUT{
			@Override
			public String code() {
				return "301";
			}
		}, EXCEPTION {
			@Override
			public String code() {
				return "500";
			}
		};
		
		public abstract String code();
	}

	/**
	 * DwzAjax服务器端响应
	 * 
	 * @param statusCode
	 *            状态码
	 * @param navTabId
	 *            要刷新的页面的rel
	 * @return
	 */
	public static Map<String, Object> ajaxDone(StatusCode statusCode, String navTabId, String msg, AjaxType type, String forwardUrl) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusCode", statusCode.code());
		map.put("message", (statusCode == StatusCode.OK) ? (msg != null ? msg : "操作成功！") : (msg != null ? msg : "操作失败！"));
		map.put("navTabId", navTabId);
		map.put("forwardUrl", forwardUrl);
		map.put("callbackType", Strings.isEmpty(forwardUrl)? (type == AjaxType.DIALOG ? "closeCurrent" : "") : "forward");
		return map;
	}
	
	
	public static Map<String, Object> ajaxDone(StatusCode statusCode) {
		return ajaxDone(statusCode, null, null, AjaxType.NAVTAB, null);
	}
	

	public static Map<String, Object> ajaxDone(StatusCode statusCode, String msg) {
		return ajaxDone(statusCode, null, msg, AjaxType.NAVTAB, null);
	}
	

	public static Map<String, Object> ajaxDone(StatusCode statusCode, String msg, String forwardUrl) {
		return ajaxDone(statusCode, null, msg, AjaxType.NAVTAB, forwardUrl);
	}
	
	public static Map<String, Object> closeAjaxDone(StatusCode statusCode) {
		return ajaxDone(statusCode, null, null, AjaxType.DIALOG, null);
	}
	
	public static Map<String, Object> closeAjaxDone(StatusCode statusCode, String navTabId) {
		return ajaxDone(statusCode, navTabId, null, AjaxType.DIALOG, null);
	}
	
	
	public static Map<String, Object> closeAjaxDone(StatusCode statusCode, String navTabId, String msg) {
		return ajaxDone(statusCode, navTabId, msg, AjaxType.DIALOG, null);
	}
	
	public static Map<String, Object> closeAjaxDone(StatusCode statusCode, String navTabId, String msg,  String forwardUrl) {
		return ajaxDone(statusCode, navTabId, msg, AjaxType.DIALOG, forwardUrl);
	}

}
