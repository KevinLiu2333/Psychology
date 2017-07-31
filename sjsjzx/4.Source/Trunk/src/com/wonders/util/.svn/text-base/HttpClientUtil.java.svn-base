/**
 * HttpClientUtil.java
 * gov.communitycloud.app.util
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014-8-21 		zoven
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
*/

package com.wonders.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * ClassName:HttpClientUtil
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   zoven
 * @version  
 * @since    Ver 1.1
 * @Date	 2014-8-21		下午4:25:13
 *
 * @see 	 
 */
public class HttpClientUtil {

	/**
	 * 
	 * 执行一个HTTP POST请求，返回请求响应的HTML 
	 * @param url 请求的URL地址 
	 * @param params 请求的查询参数,可以为null 
	 * @return 返回请求响应的HTML
	 */
	public static String send(String url, Map<String, String> params){
		String response = null;
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		// 设置Post数据
		if (!params.isEmpty()) {
			int i = 0;
			NameValuePair[] data = new NameValuePair[params.size()];
			for (Entry<String, String> entry : params.entrySet()) {
				data[i] = new NameValuePair(entry.getKey(), entry.getValue());
				i++;
			}
			postMethod.setRequestBody(data);
		}
		try {
			client.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return response;
	}
	
	/**
	 * 
	 * 执行一个HTTP get请求，返回请求响应的HTML 
	 * @param url 请求的URL地址 
	 * @param params 请求的查询参数,可以为null 
	 * @return 返回请求响应的HTML
	 */
	public static String get(String url, Map<String, Object> params){
		String response = null;
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		// 设置Post数据
		if (!params.isEmpty()) {
			int i = 0;
			NameValuePair[] data = new NameValuePair[params.size()];
			for (Entry<String, Object> entry : params.entrySet()) {
				data[i] = new NameValuePair(entry.getKey(),(String) entry.getValue());
				i++;
			}
			getMethod.setQueryString(data);
		}
		try {
			client.executeMethod(getMethod);
			if (getMethod.getStatusCode() == HttpStatus.SC_OK) {
				response = getMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return response;
	}
	
	/**
	 * 上传文件
	 * @param url
	 * @param file
	 * @param params
	 * @return
	 */
	public static String postFile(String url, File file, Map<String, String> params) {
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		try {
			FilePart filePart = new FilePart(file.getName(), file);
			List<Part> partlist = new ArrayList<Part>();
			partlist.add(filePart);
			if (!params.isEmpty()) {
				Part strPart = null;
				for (Entry<String, String> entry : params.entrySet()) {
					strPart = new StringPart(entry.getKey(), entry.getValue());
					partlist.add(strPart);
				}
			}
			Part[] parts = (Part[]) partlist.toArray(new Part[partlist.size()]);
			//设置参数
			postMethod.setRequestEntity(new MultipartRequestEntity(parts,
					postMethod.getParams()));
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				return postMethod.getResponseBodyAsString();
			}
			
			return postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "系统内部错误";
	}
	
}

