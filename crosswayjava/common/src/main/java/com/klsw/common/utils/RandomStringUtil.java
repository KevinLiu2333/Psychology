package com.klsw.common.utils;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringUtil {

	private static final char[] cs = new char[] { 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9' };

	private static final int csLength = cs.length;

	public static String random(int length) {
		StringBuilder token = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			token.append(cs[random.nextInt(csLength - 1)]);
		}
		return token.toString();
	}
	
	/**
	 * 生成订单流水号：时间戳＋5位随机数
	 * @return
	 */
	public static String genSerial() {
		StringBuilder serial = new StringBuilder();
		serial.append(DateUtils.getDateRandom());
		serial.append(RandomStringUtils.randomNumeric(5));
		return serial.toString();
	}
}
