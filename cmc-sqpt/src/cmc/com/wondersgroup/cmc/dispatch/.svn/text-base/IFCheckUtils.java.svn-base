package com.wondersgroup.cmc.dispatch;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.net.telnet.TelnetClient;

import com.wondersgroup.cmc.dispatch.model.dto.HttpClientDTO;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.wssip.util.StringTools;

public class IFCheckUtils{
	
	/**
	 * 测试url访问
	 * @param ifsubtype
	 * @param address
	 * @return
	 */
	private static HttpClientDTO urlcheck(String ifsubtype,String address){
		URL url = null;
		HttpClientDTO httpClientDTO = new HttpClientDTO();
		//创建URL
		try {
			url = new URL(address);
		} catch (Exception e) {
			e.printStackTrace();
			httpClientDTO.setCode(DispatchContent.LSMONSTATUS_404);
			httpClientDTO.setDetailmsg("CI:url["+address+"]填写有误。");
			return httpClientDTO;
		}
		//尝试连接
		try {
			URLConnection connection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) connection;
			httpUrlConnection.setConnectTimeout(DispatchContent.DEFALUT_CONNET_TO);
			httpUrlConnection.setReadTimeout(DispatchContent.DEFALUT_READ_TO);
			httpUrlConnection.connect();
			int code = httpUrlConnection.getResponseCode();
			String msg = httpUrlConnection.getResponseMessage();
			if(code == 200){
				httpClientDTO.setCode(DispatchContent.LSMONSTATUS_200);
				httpClientDTO.setDetailmsg("CI:访问成功，["+msg+"]。");
			} else if(code >= 500 && code < 600){
				httpClientDTO.setCode(DispatchContent.LSMONSTATUS_500);
				httpClientDTO.setDetailmsg("CI:连接成功，但访问资源返回有误，["+msg+"]。");
			}  else if(code == 404){
				httpClientDTO.setCode(DispatchContent.LSMONSTATUS_404);
				httpClientDTO.setDetailmsg("CI:连接成功，但未找到资源，["+msg+"]。");
			}	else if(code == 405 || code == 415){
				if(DispatchContent.IFSUBTYPE_REST.equals(ifsubtype)){
					httpClientDTO.setCode(DispatchContent.LSMONSTATUS_200);
					httpClientDTO.setDetailmsg("CI:连接成功，["+msg+"]。");
				} else {
					httpClientDTO.setCode(DispatchContent.LSMONSTATUS_404);
					httpClientDTO.setDetailmsg("CI:连接成功，但未找到资源，["+msg+"]。");
				}
			} else{
				httpClientDTO.setCode(DispatchContent.LSMONSTATUS_404);
				httpClientDTO.setDetailmsg("CI:访问失败，["+msg+"]。");
			}
			try {
				httpUrlConnection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
            return httpClientDTO;
		} catch (Exception e) {
			e.printStackTrace();
			httpClientDTO.setCode(DispatchContent.LSMONSTATUS_404);
			httpClientDTO.setDetailmsg("CI:连接超时。");
			return httpClientDTO;
		}
	}
	
	private static HttpClientDTO telnetcheck(String address) {
		HttpClientDTO httpClientDTO = new HttpClientDTO();
		String[] addresses = address.split(":");
		if(addresses.length != 2){
			httpClientDTO.setCode(DispatchContent.LSMONSTATUS_404);
			httpClientDTO.setDetailmsg("CI:url["+address+"]填写有误。");
			return httpClientDTO;
		}
		String ip = addresses[0];
		int port = 0;
		try {
			port = Integer.valueOf(addresses[1]);
		} catch (Exception e) {
			httpClientDTO.setCode(DispatchContent.LSMONSTATUS_404);
			httpClientDTO.setDetailmsg("CI:url["+address+"]端口填写填写有误。");
			return httpClientDTO;
		}
		TelnetClient telnet = null;
		try {
			telnet = new TelnetClient("VT220");
			telnet.connect(ip, port);
			telnet.disconnect();
			telnet = null;
			httpClientDTO.setCode(DispatchContent.LSMONSTATUS_200);
			httpClientDTO.setDetailmsg("CI:连接成功。");
			return httpClientDTO;
		} catch (Exception e) {
			e.printStackTrace();
			httpClientDTO.setCode(DispatchContent.LSMONSTATUS_404);
			httpClientDTO.setDetailmsg("CI:连接失败，详细原因["+e.getMessage()+"]。");
			return httpClientDTO;
		} finally {
			try {
				if(telnet!=null && !telnet.isConnected()) {
					telnet.disconnect();
					telnet = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			telnet = null;
		}
	}
	
	/**
	 * 校验连通性
	 * @param ifsubtype
	 * @param url
	 * @return
	 */
	public static HttpClientDTO checkInterface(String ifsubtype,String url){
		if(StringTools.isEmpty(ifsubtype)){
			throw new BusinessException("传入参数 ifsubtype 为空");
		}
		
		if(StringTools.isEmpty(url)){
			throw new BusinessException("传入参数 url 为空");
		}
		
		if(DispatchContent.IFSUBTYPE_WSDL.equals(ifsubtype) ||DispatchContent.IFSUBTYPE_REST.equals(ifsubtype)||DispatchContent.IFSUBTYPE_APP.equals(ifsubtype)){
			return IFCheckUtils.urlcheck(ifsubtype,url);
		} else if(DispatchContent.IFSUBTYPE_TEL.equals(ifsubtype)){
			return IFCheckUtils.telnetcheck(url);
		} else {
			throw new BusinessException("CI:未知的校验类型["+ifsubtype+"]");
		}
	}

}
