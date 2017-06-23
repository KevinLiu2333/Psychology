package com.klsw.website.captcha;

import org.apache.commons.lang3.RandomStringUtils;


/**
 * 验证码生成器
 * 
 */
public class CodeGenerator {
	
	 public static String DEFAULT_SEED = "0123456789";
     public static int DEFAULT_LEN = 4;
     
     //随即生成包含验证码的字符串    
     public static String random(String seed, int length) {
	     return RandomStringUtils.random(length, seed);
	 }
}
