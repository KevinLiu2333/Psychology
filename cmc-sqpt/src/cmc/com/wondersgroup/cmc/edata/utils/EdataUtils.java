package com.wondersgroup.cmc.edata.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.wondersgroup.cmc.edata.download.common.DownloadServiceDelegate;
import com.wondersgroup.cmc.edata.download.common.DownloadServiceService;
import com.wondersgroup.cmc.edata.download.model.common.GetRequestData;
import com.wondersgroup.cmc.edata.download.model.common.GetResponseData;
import com.wondersgroup.cmc.edata.download.model.common.Item;
import com.wondersgroup.cmc.edata.upload.common.UploadServiceDelegate;
import com.wondersgroup.cmc.edata.upload.common.UploadServiceService;
import com.wondersgroup.cmc.edata.upload.model.common.PutRequestData;
import com.wondersgroup.cmc.edata.upload.model.common.PutResponseData;
import com.wondersgroup.cmc.utils.Base64Utils;
import com.wondersgroup.cmc.utils.WebServiceCall;
import com.wondersgroup.cmc.utils.WebServiceCallArgument;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.wssip.util.StringTools;

public class EdataUtils {
	public final static String Edata_DEFALUT_NAMESPACE = "http://ws.cmc.wondersgroup.com/";	//命名空间
	private static String uploadWsdlLocation;
	private static String downloadWsdlLocation;
	private static int timeout;
	private static String type;
	private static String ftpWsdlLocation;
	private static UploadServiceService uploadServiceService;
	private static UploadServiceDelegate uploadServiceDelegate;
	private static DownloadServiceService downloadServiceService;
	private static DownloadServiceDelegate downloadServiceDelegate;
	

	public void setUploadWsdlLocation(String uploadWsdlLocation) {
		EdataUtils.uploadWsdlLocation = uploadWsdlLocation;
	}

	public void setDownloadWsdlLocation(String downloadWsdlLocation) {
		EdataUtils.downloadWsdlLocation = downloadWsdlLocation;
	}

	public void setTimeout(int timeout) {
		EdataUtils.timeout = timeout;
	}

	public void setType(String type) {
		EdataUtils.type = type;
	}

	public void setFtpWsdlLocation(String ftpWsdlLocation) {
		EdataUtils.ftpWsdlLocation = ftpWsdlLocation;
	}

	public void init(){
		if("edata".equals(type.trim())){
			try {
				//先尝试连接
				URL url = new URL(uploadWsdlLocation);
				URLConnection connection = url.openConnection();
				connection.setConnectTimeout(timeout);
				connection.getInputStream();
				url = new URL(downloadWsdlLocation);
				connection = url.openConnection();
				connection.setConnectTimeout(timeout);
				connection.getInputStream();
				//再初始化
				EdataUtils.uploadServiceService = new UploadServiceService(uploadWsdlLocation);
				EdataUtils.uploadServiceDelegate = EdataUtils.uploadServiceService.getUploadServicePort();
				EdataUtils.downloadServiceService = new DownloadServiceService(downloadWsdlLocation);
				EdataUtils.downloadServiceDelegate = EdataUtils.downloadServiceService.getDownloadServicePort();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获得文件流
	 * @param file
	 * @return
	 */
	private static byte[] readBytes(String filepath) {
		if (filepath == null){
			return null;
		}
		
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		byte[] bytes = new byte[1024];
		int count = 0;

		try {
			while ((count = fileInputStream.read(bytes)) != -1) {
				buffer.write(bytes, 0, count);
			}
			return buffer.toByteArray();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				fileInputStream.close();
				buffer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获得文件流
	 * @param file
	 * @return
	 */
	private static byte[] readBytes(File file) {
		if (file == null){
			return null;
		}
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		byte[] bytes = new byte[1024];
		int count = 0;

		try {
			while ((count = fileInputStream.read(bytes)) != -1) {
				buffer.write(bytes, 0, count);
			}
			return buffer.toByteArray();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				fileInputStream.close();
				buffer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 上传(如果有错误直接抛出)
	 * @param request
	 * @return
	 */
	public static PutResponseData upload(byte[] datas,String catalog,String idcard,String type) {
		PutRequestData putRequestData = new PutRequestData();
		putRequestData.setCatalog(catalog);
		putRequestData.setIdcard(idcard);
		putRequestData.setType(type);
		putRequestData.setData(datas);
		return EdataUtils.upload(putRequestData,true);
	}
	
	/**
	 * 上传(如果有错误直接抛出)
	 * @param request
	 * @return
	 */
	public static PutResponseData upload(String filename,String catalog,String idcard,String type) {
		PutRequestData putRequestData = new PutRequestData();
		putRequestData.setCatalog(catalog);
		putRequestData.setIdcard(idcard);
		putRequestData.setType(type);
		putRequestData.setData(readBytes(filename));
		return EdataUtils.upload(putRequestData,true);
	}
	
	/**
	 * 上传(如果有错误直接抛出)
	 * @param file
	 * @param catalog
	 * @param idcard
	 * @param type
	 * @return
	 */
	public static PutResponseData upload(File file,String catalog,String idcard,String type) {
		PutRequestData putRequestData = new PutRequestData();
		putRequestData.setCatalog(catalog);
		putRequestData.setIdcard(idcard);
		putRequestData.setType(type);
		putRequestData.setData(readBytes(file));
		return EdataUtils.upload(putRequestData,true);
	}
	
	/**
	 * 上传(如果有错误直接抛出)
	 * @param request
	 * @return
	 */
	public static PutResponseData upload(PutRequestData request) {
		return EdataUtils.upload(request,true);
	}

	/**
	 * 上传
	 * @param request
	 * @param throwable 是否直接抛出错误
	 * @return
	 */
	public static PutResponseData upload(PutRequestData request,Boolean throwable) {
		if("edata".equals(type.trim())){
			return edataupload(request, throwable);
		} else {
			return ftpaupload(request, throwable);
		}
	}

	public static PutResponseData ftpaupload(PutRequestData request,Boolean throwable) {
		try {
			URL url_ = new URL(ftpWsdlLocation);
			URLConnection connection = url_.openConnection();
			connection.setConnectTimeout(timeout);
			connection.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("小文件库连接失败");
		}
		WebServiceCall webServiceCall = new WebServiceCall();
		List<WebServiceCallArgument> args = new ArrayList<WebServiceCallArgument>();
		WebServiceCallArgument arg = new WebServiceCallArgument();
		arg.setArgName("b");
		arg.setArgType(org.apache.axis.encoding.XMLType.XSD_BASE64);
		try {
			arg.setArgValue(String.format("{data:'%s',catalog:'%s',idcard:'%s',type:'%s'}",Base64Utils.encodeString(request.getData()),request.getCatalog(),request.getIdcard(),request.getType()).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		args.add(arg);
		String json = webServiceCall.callService(ftpWsdlLocation, "upload", args, org.apache.axis.encoding.XMLType.XSD_STRING);
		if(StringTools.isEmpty(json)){
			throw new BusinessException("小文件库连接失败");
		} else {
			PutResponseData responseData = null;
			try {
				JSONObject jsonObject = JSONObject.fromObject(json);
				responseData = new PutResponseData();
				responseData.setSuccess(jsonObject.getBoolean("success"));
				responseData.setMessage(jsonObject.getString("msg"));
				responseData.setUrl(jsonObject.getString("url"));
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("小文件库连接失败");
			}
			if (responseData.isSuccess()) {
				return responseData;
			} else {
				if(throwable){
					throw new BusinessException(responseData.getMessage());
				} else {
					return responseData;
				}
			}
		}
	}
	
	public static PutResponseData edataupload(PutRequestData request,Boolean throwable) {
		if(uploadServiceDelegate == null){
			try {
				//先尝试连接
				URL url = new URL(uploadWsdlLocation);
				URLConnection connection = url.openConnection();
				connection.setConnectTimeout(timeout);
				connection.getInputStream();
				//再连接
				uploadServiceService = new UploadServiceService(uploadWsdlLocation);
				EdataUtils.uploadServiceDelegate = uploadServiceService.getUploadServicePort();
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("小文件库连接失败");
			}
		}
		PutResponseData responseData = null;
		try {
			responseData = uploadServiceDelegate.upload(request);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("小文件库连接失败");
		}
		if (responseData.isSuccess()){
			return responseData;
		} else {
			if(throwable){
				throw new BusinessException(responseData.getMessage());
			} else {
				return responseData;
			}
		}
	}
	
	/**
	 * 下载(如果有错误直接抛出)
	 * @param url
	 * @return
	 */
	public static GetResponseData download(String url){
		return EdataUtils.download(url, true);
	}
	
	/**
	 * 下载
	 * @param url
	 * @param throwable 是否直接抛出错误
	 * @return
	 */
	public static GetResponseData download(String url,Boolean throwable){
		if("edata".equals(type.trim())){
			return edatadownload(url, throwable);
		} else {
			return ftpdownload(url, throwable);
		}
		
	}
	
	public static GetResponseData ftpdownload(String url,Boolean throwable) {
		try {
			URL url_ = new URL(ftpWsdlLocation);
			URLConnection connection = url_.openConnection();
			connection.setConnectTimeout(timeout);
			connection.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("小文件库连接失败");
		}
		WebServiceCall webServiceCall = new WebServiceCall();
		List<WebServiceCallArgument> args = new ArrayList<WebServiceCallArgument>();
		WebServiceCallArgument arg = new WebServiceCallArgument();
		arg.setArgName("b");
		arg.setArgType(org.apache.axis.encoding.XMLType.XSD_BASE64);
		try {
			arg.setArgValue(String.format("{url:'%s'}",url).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		args.add(arg);
		String json = webServiceCall.callService(ftpWsdlLocation, "download", args, org.apache.axis.encoding.XMLType.XSD_STRING);
		if(StringTools.isEmpty(json)){
			throw new BusinessException("小文件库连接失败");
		} else {
			GetResponseData resp = null;
			try {
				JSONObject jsonObject = JSONObject.fromObject(json);
				resp = new GetResponseData();
				resp.setSuccess(jsonObject.getBoolean("success"));
				resp.setMessage(jsonObject.getString("msg"));
				Item item = new Item();
				if(resp.isSuccess()){
					item.setData(Base64Utils.decodeBytes(jsonObject.getString("data")));
				}
				resp.setItem(item);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("小文件库连接失败");
			}
			if (resp.isSuccess()) {
				return resp;
			} else {
				if(throwable){
					throw new BusinessException(resp.getMessage());
				} else {
					return resp;
				}
			}
		}
	}
	
	public static GetResponseData edatadownload(String url,Boolean throwable) {
		if(downloadServiceDelegate == null){
			try {
				URL url_ = new URL(downloadWsdlLocation);
				URLConnection connection = url_.openConnection();
				connection.setConnectTimeout(timeout);
				connection.getInputStream();
				EdataUtils.downloadServiceService = new DownloadServiceService(downloadWsdlLocation);
				EdataUtils.downloadServiceDelegate = EdataUtils.downloadServiceService.getDownloadServicePort();
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("小文件库连接失败");
			}
		}
		GetRequestData request = new GetRequestData();
		request.setUrl(url);
		GetResponseData resp = null;
		try {
			resp = downloadServiceDelegate.download(request);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("小文件库连接失败");
		}
		if (resp.isSuccess()) {
			return resp;
		} else {
			if(throwable){
				throw new BusinessException(resp.getMessage());
			} else {
				return resp;
			}
		}
	}
}
