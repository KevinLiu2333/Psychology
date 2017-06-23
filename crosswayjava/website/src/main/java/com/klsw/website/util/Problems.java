package com.klsw.website.util;

import java.util.HashMap;
import java.util.Map;

public class Problems {
	
	/*
	 * 2**：操作成功收到，分析、接受
	 * 3**：完成此请求必须进一步处理
	 * 4**：请求包含一个错误语法或不能完成
	 * 5**：服务器执行一个完全有效请求失败

	 * 100——客户必须继续发出请求
	 * 101——客户要求服务器根据请求转换HTTP协议版本

	 * 200——交易成功
	 * 201——提示知道新文件的URL
	 * 202——接受和处理、但处理未完成
	 * 203——返回信息不确定或不完整
	 * 204——请求收到，但返回信息为空
	 * 205——服务器完成了请求，用户代理必须复位当前已经浏览过的文件
	 * 206——服务器已经完成了部分用户的GET请求

	 * 300——请求的资源可在多处得到
	 * 301——删除请求数据
	 * 302——在其他地址发现了请求数据
	 * 303——建议客户访问其他URL或访问方式
	 * 304——客户端已经执行了GET，但文件未变化
	 * 305——请求的资源必须从服务器指定的地址得到
	 * 306——前一版本HTTP中使用的代码，现行版本中不再使用
	 * 307——申明请求的资源临时性删除

	 * 400——错误请求，如语法错误
	 * 401——请求授权失败
	 * 402——保留有效ChargeTo头响应
	 * 403——请求不允许
	 * 404——没有发现文件、查询或URl
	 * 405——用户在Request-Line字段定义的方法不允许
	 * 406——根据用户发送的Accept拖，请求资源不可访问
	 * 407——类似401，用户必须首先在代理服务器上得到授权
	 * 408——客户端没有在用户指定的饿时间内完成请求
	 * 409——对当前资源状态，请求不能完成
	 * 410——服务器上不再有此资源且无进一步的参考地址
	 * 411——服务器拒绝用户定义的Content-Length属性请求
	 * 412——一个或多个请求头字段在当前请求中错误
	 * 413——请求的资源大于服务器允许的大小
	 * 414——请求的资源URL长于服务器允许的长度
	 * 415——请求资源不支持请求项目格式
	 * 416——请求中包含Range请求头字段，在当前请求资源范围内没有range指示值，请求也不包含If-Range请求头字段
	 * 417——服务器不满足请求Expect头字段指定的期望值，如果是代理服务器，可能是下一级服务器不能满足请求

	 * 500——服务器产生内部错误
	 * 501——服务器不支持请求的函数
	 * 502——服务器暂时不可用，有时是为了防止发生系统过载
	 * 503——服务器过载或暂停维修
	 * 504——关口过载，服务器使用另一个关口或服务来响应用户，等待时间设定值较长
	 * 505——服务器不支持或拒绝支请求头中指定的HTTP版本
	 */
	
	/**
	 * 成功
	 */
	public static final int OK = 200;
	
	/**
	 * 系统错误
	 */
	public static final int ERROR_500 = 500;
	
	public static final int ERROR_404 = 404;
	
	public static final int ERROR_401 = 401;
	
	/************************************************* 系统提示代码区(以1开头) *************************************************/
	
	
	
	/************************************************* 系统提示代码区 *************************************************/
	
	/************************************************* 验证码提示代码区(以2开头) *************************************************/
	
	/**
	 * 图片验证码错误
	 */
	public static final int IMG_CAPTCHA_ERROR = 2001;
	
	/**
	 * 短信验证码错误
	 */
	public static final int SMS_CAPTCHA_ERROR = 2002;
	
	/**
	 * 短信发送频繁
	 */
	public static final int SMS_SEND_MORE = 2003;
	
	/**
	 * 短信发送失败
	 */
	public static final int SMS_SEND_ERROR = 2004;
	
	/************************************************* 验证码提示代码区 *************************************************/
	
	/************************************************* 业务提示代码区(以3开头) *************************************************/

	/**
	 * 业务逻辑错误
	 */
	public static final int BUSINESS_ERROR = 3000;
	
	/**
	 * 用户不存在
	 */
	public static final int USER_IS_NULL = 3001;
	
	/**
	 * 用户已存在
	 */
	public static final int USER_IS_EXIST = 3002;
	
	/**
	 * 手机号码不存在
	 */
	public static final int MOBILE_IS_NULL = 3003;
	
	/**
	 * 手机号码已存在
	 */
	public static final int MOBILE_IS_EXIST = 3004;
	
	/**
	 * 邮箱不存在
	 */
	public static final int EMAIL_IS_NULL = 3005;
	
	/**
	 * 邮箱已存在
	 */
	public static final int EMAIL_IS_EXIST = 3006;
	
	/************************************************* 业务提示代码区 *************************************************/
	
	private static final Map<Integer, String> CAUSEMAP = mapToCodeCause();

    private static Map<Integer, String> mapToCodeCause() {
    	Map<Integer, String> map = new HashMap<>();
    	
    	// -----------------------------------------
    	map.put(OK, "成功");
    	map.put(ERROR_500, "系统错误");
    	map.put(ERROR_500, "页面不存在");
    	
    	// ------------------- 1 -------------------
    	
    	
    	// ------------------- 2 -------------------
    	map.put(IMG_CAPTCHA_ERROR, "图片验证码错误");
    	map.put(SMS_CAPTCHA_ERROR, "短信验证码错误");
    	map.put(SMS_SEND_MORE, "短信发送频繁");
    	map.put(SMS_SEND_ERROR, "短信发送失败");
    	
    	// ------------------- 3 -------------------
    	map.put(BUSINESS_ERROR, "业务逻辑错误");
    	map.put(USER_IS_NULL, "用户不存在");
    	map.put(USER_IS_EXIST, "用户已存在");
    	map.put(MOBILE_IS_NULL, "手机号码不存在");
    	map.put(MOBILE_IS_EXIST, "手机号码已存在");
    	map.put(EMAIL_IS_NULL, "邮箱不存在");
    	map.put(EMAIL_IS_EXIST, "邮箱已存在");
    	
    	return map;
    }

    /**
     * 根据提示代码获取提示信息
     * @param code 提示代码
     * @return 提示信息
     */
    public static String getCauseByCode(int code) {
    	return CAUSEMAP.get(code);
    }
}
