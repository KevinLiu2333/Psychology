package com.klsw.crosswaylive.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.druid.util.Base64;



public class SignUtil {	
	
	
	/**
	 * 获取签名URL
	 * @param paramList  用于签名的参数集合(不包含签名参数和阿里云keySecret)
	 * @param paramMap   参数名称和对应值表(不包含签名参数及对应值)
	 * @return
	 * @throws Exception 
	 */
	public static String getSignature(List<String> paramList, Map<String, String> paramMap) throws Exception {
		
		//构造规范化字符串
		StringBuffer sBuffer = new StringBuffer("");
		Collections.sort(paramList);//将参数集合按字典排序
		for(int i=0;i<paramList.size();i++) {
			sBuffer.append(paramList.get(i)).append("=").append(canonicalizedString(paramMap.get(paramList.get(i))));
		}
		
		//构造签名字符串
		String stringToSign = "GET&%2F&" + sBuffer.toString();
		
		//获取加密后的字节数组
		byte[] data = HmacSHA1Encrypt(stringToSign, Constants.accessKeySecret + "&");
		
		//获取签名值
		String signature = canonicalizedString(Base64.byteArrayToBase64(data));
		
		//向参数集合和参数表中添加新元素
		paramList.add("Signature");
		paramMap.put("Signature", signature);
		
		//构造签名URL并返回
		sBuffer = new StringBuffer("http://cdn.aliyuncs.com/?");
		for(int i=0;i<paramList.size();i++) {
			sBuffer.append(paramList.get(i)).append("=").append(paramMap.get(paramList.get(i)));
			if(i<paramList.size()-1) {
				sBuffer.append("&");
			}
		}
		return sBuffer.toString();
	}
	
	
	/**
	 * 生成规范化的字符串
	 * @param original
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String canonicalizedString(String original) throws UnsupportedEncodingException {
		return  URLEncoder.encode(original, "utf-8")
				.replace("+", "%20")
				.replace("*", "%2A")
				.replace("%7E", "~");
	}
	
	
	/**  
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名  
     * @param encryptText 被签名的字符串  
     * @param encryptKey  密钥  
     * @return  
     * @throws Exception  
     */    
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception     
    {         
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称  
        SecretKey secretKey = new SecretKeySpec(encryptKey.getBytes("utf-8"), "HmacSHA1"); 
        
        //生成一个指定 Mac 算法 的 Mac 对象  
        Mac mac = Mac.getInstance("HmacSHA1");  
        
        //用给定密钥初始化 Mac 对象  
        mac.init(secretKey);    
          
        //获取被签名字符串的字节数组
        byte[] text = encryptText.getBytes("utf-8");   
        
        //完成 Mac操作，返回结果   
        return mac.doFinal(text);    
    }   
}
