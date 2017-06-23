package com.klsw.website.util;

public class HtmlUtils {

	private static final String BODY_PREFIX = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body>";
	private static final String BODY_AFTER = "</body></html>";
	
	public static String genCompleteHtml(String html) {
		StringBuilder sb = new StringBuilder();
		sb.append(BODY_PREFIX).append(html).append(BODY_AFTER);
		return sb.toString();
	}
}
