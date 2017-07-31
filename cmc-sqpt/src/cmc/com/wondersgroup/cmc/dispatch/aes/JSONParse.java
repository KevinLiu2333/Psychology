package com.wondersgroup.cmc.dispatch.aes;

import net.sf.json.JSONObject;

public class JSONParse {
	
	/**
	 * 解析加密json
	 * @param jsontext
	 * @return
	 * @throws AesException
	 */
	public static String[] extract(String jsontext) throws AesException {
		String[] result = new String[4];
		try {
			JSONObject jsonObject = JSONObject.fromObject(jsontext);
			result[0] = jsonObject.getString("Encrypt");
			result[1] = jsonObject.getString("MsgSignature");
			result[2] = jsonObject.getString("TimeStamp");
			result[3] = jsonObject.getString("Nonce");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ParseJsonError);
		}
	}
	
	
	/**
	 * 生成json消息
	 * @param encrypt 加密后的消息密文
	 * @param signature 安全签名
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @return 生成的json字符串
	 */
	public static String generate(String encrypt, String signature, String timestamp, String nonce) {
		String format = "{Encrypt:'%1$s',MsgSignature:'%2$s',TimeStamp:'%3$s',Nonce:'%4$s'}";
		return String.format(format, encrypt, signature, timestamp, nonce);
	}
}
