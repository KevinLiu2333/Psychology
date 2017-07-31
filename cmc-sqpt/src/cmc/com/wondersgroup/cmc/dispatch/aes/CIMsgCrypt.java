package com.wondersgroup.cmc.dispatch.aes;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CIMsgCrypt {
	private static Charset CHARSET = Charset.forName("utf-8");
	private Base64 base64 = new Base64();
	private byte[] aesKey;
	private String token;
	private String userid;
	
	/**
	 * 构造函数
	 * @param token 平台上设置的token
	 * @param encodingAesKey 平台上设置的EncodingAESKey
	 * @param userid 平台userid
	 * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
	 */
	public CIMsgCrypt(String token, String encodingAesKey, String userid) throws AesException {
		if (encodingAesKey.length() != 43) {
			throw new AesException(AesException.IllegalAesKey);
		}

		this.token = token;
		this.userid = userid;
		aesKey = Base64.decodeBase64(encodingAesKey + "=");
	}
	
	// 生成4个字节的网络字节序
	private byte[] getNetworkBytesOrder(int sourceNumber) {
		byte[] orderBytes = new byte[4];
		orderBytes[3] = (byte) (sourceNumber & 0xFF);
		orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
		orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
		orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
		return orderBytes;
	}

	// 还原4个字节的网络字节序
	private int recoverNetworkBytesOrder(byte[] orderBytes) {
		int sourceNumber = 0;
		for (int i = 0; i < 4; i++) {
			sourceNumber <<= 8;
			sourceNumber |= orderBytes[i] & 0xff;
		}
		return sourceNumber;
	}
	
	// 随机生成16位字符串
	private String getRandomStr() {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * 对明文进行加密.
	 * @param text 需要加密的明文
	 * @return 加密后base64编码的字符串
	 * @throws AesException aes加密失败
	 */
	private String encrypt(String randomStr, String text) throws AesException {
		ByteGroup byteCollector = new ByteGroup();
		byte[] randomStrBytes = randomStr.getBytes(CHARSET);
		byte[] textBytes = text.getBytes(CHARSET);
		byte[] networkBytesOrder = getNetworkBytesOrder(textBytes.length);
		byte[] useridBytes = userid.getBytes(CHARSET);
		
		//randomStr + networkBytesOrder + text + userid
		byteCollector.addBytes(randomStrBytes);
		byteCollector.addBytes(networkBytesOrder);
		byteCollector.addBytes(textBytes);
		byteCollector.addBytes(useridBytes);
		
		//使用自定义的填充方式对明文进行补位填充
		byte[] padBytes = PKCS7Encoder.encode(byteCollector.size());
		byteCollector.addBytes(padBytes);
		
		byte[] unencrypted = byteCollector.toBytes();
		
		try {
			// 设置加密模式为AES的CBC模式
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

			// 加密
			byte[] encrypted = cipher.doFinal(unencrypted);

			// 使用BASE64对加密后的字符串进行编码
			String base64Encrypted = base64.encodeToString(encrypted);
			return base64Encrypted;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.EncryptAESError);
		}
	}
	
	/**
	 * 对密文进行解密.
	 * @param text 需要解密的密文
	 * @return 解密得到的明文
	 * @throws AesException aes解密失败
	 */
	private String decrypt(String text) throws AesException {
		byte[] original;
		try {
			// 设置解密模式为AES的CBC模式
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
			cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

			// 使用BASE64对密文进行解码
			byte[] encrypted = Base64.decodeBase64(text);

			// 解密
			original = cipher.doFinal(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.DecryptAESError);
		}
		
		String data, from_userid;
		try {
			// 去除补位字符
			byte[] bytes = PKCS7Encoder.decode(original);

			// 分离16位随机字符串,网络字节序和AppId
			byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

			int xmlLength = recoverNetworkBytesOrder(networkOrder);

			data = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
			from_userid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length),
					CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.IllegalBuffer);
		}
		
		if (!from_userid.equals(this.userid)) {
			throw new AesException(AesException.ValidateAppidError);
		}
		return data;
	}
	/**
	 * 对要发送的信息进行AES-CBC加密,并生成安全签名
	 * @param message 发送信息，json格式的字符串
	 * @param timeStamp 时间戳
	 * @param nonce 随机串
	 * @return 加密后的密文，包括msg_signature, timestamp, nonce, encrypt的json格式的字符串
	 * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
	 */
	public String encryptMsg(String message, String timeStamp, String nonce) throws AesException {
		// 加密
		String encrypt = this.encrypt(this.getRandomStr(), message);
		// 生成安全签名
		if (timeStamp == "") {
			timeStamp = Long.toString(System.currentTimeMillis());
		}
		
		String signature = SHA1.getSHA1(token, timeStamp, nonce, encrypt);
		String result = JSONParse.generate(encrypt, signature, timeStamp, nonce);
		return result;
	}
	
	/**
	 * 检验消息的真实性，并且获取解密后的明文.
	 * @param msgSignature 签名串
	 * @param timeStamp 时间戳
	 * @param nonce 随机串
	 * @param message 密文，对应POST请求的数据
	 * @return 解密后的原文
	 * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
	 */
	public String decryptMsg(String msgSignature, String timeStamp, String nonce, String message) throws AesException {
		// 提取密文
		String[] encrypt = JSONParse.extract(message);

		// 验证安全签名
		String signature = SHA1.getSHA1(token, timeStamp, nonce, encrypt[0].toString());

		// 和URL中的签名比较是否相等
		if (!signature.equals(msgSignature)) {
			throw new AesException(AesException.ValidateSignatureError);
		}
		// 解密
		String result = decrypt(encrypt[0].toString());
		return result;
	}
}
