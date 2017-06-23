package com.klsw.website.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.klsw.common.mall.model.TMallUser;
import com.klsw.website.captcha.ImageCaptcha;
import com.klsw.website.util.Constants;

/**
 * 所有Controller的基类
 */
public class _BaseController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected Integer getUserId(HttpServletRequest request) {
		TMallUser user = (TMallUser)request.getSession().getAttribute(Constants.USER);
		return user.getId();
	}
	
	//生成图片验证码
	public void createImgCaptcha(HttpServletRequest request, HttpServletResponse response, String code) throws IOException{
		
		final ImageCaptcha imageCaptcha = new ImageCaptcha();
		final String randomString = imageCaptcha.generator();

		// 存入 SESSION
		request.getSession().setAttribute(code, randomString);
		
		int captchaLen = 4;//数字长度 就是数字个数 必须小于等于code的个数
		imageCaptcha.render(randomString, captchaLen, response.getOutputStream());
		
//				return new StreamingOutput() {
//					@Override
//					public void write(OutputStream output) throws IOException, WebApplicationException {
//						int captchaLen = 4;//数字长度 就是数字个数 必须小于等于code的个数
//						imageCaptcha.render(randomString, captchaLen, output);
//					}
//				};
	}
	
	/**
	 * 获取请求域名
	 * @param request
	 * @return
	 */
	protected String getDomain(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String domain = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString().replace("http://", "");
		return domain;
	}
	
	protected static String getRequestPathFromReferer(String referer) {
		String refererReturn = "";
		referer = referer.replaceAll("http://", "");
		int index = referer.indexOf("/");
		if(index >= 0) 
			refererReturn = referer.substring(index, referer.length());
		return refererReturn.contains("/login") ? "/order/list" : refererReturn;
	}
	
	/**
	 * 获取请求IP
	 * @param request
	 * @return
	 */
	protected String getIp(HttpServletRequest request) {
		int ipLenth = 20;//IP地址的最大长度
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.length() < ipLenth ? ip : "";
	}
	
	protected Map<String, Integer> getShoppingCart(HttpServletRequest request) {
		
		Cookie[] cookieArray = request.getCookies();
		
		Map<String, Integer> resultMap = Maps.newHashMap();
		
		String cookieValue = "";
		
		if(cookieArray != null) {
			try {
				for(Cookie cookie : cookieArray) {
					String cookieName = URLDecoder.decode(cookie.getName(), "utf-8");
					if(getCartName().equals(cookieName)) {
							cookieValue = URLDecoder.decode(cookie.getValue(), "utf-8");
						break;
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotBlank(cookieValue)) {
			String[] products = cookieValue.split("\\|");
			for(String productInfo : products) {
				String[] productInfoItem = productInfo.split(",");
				Integer count = Integer.valueOf(productInfoItem[1]);
				if(count > 0) {
					resultMap.put(productInfoItem[0], count);
				}
			}
		}
		
		return resultMap;
	}
	
	protected void writeShoppingCartToCookie(HttpServletResponse response, Map<String, Integer> shoppingCart) {
		
		StringBuilder shoppingCartInfo = new StringBuilder();
		if(shoppingCart != null) {
			for (String key : shoppingCart.keySet()) {
				shoppingCartInfo.append(key).append(",").append(shoppingCart.get(key)).append("|");
			}
			
			String cartdata = shoppingCartInfo.substring(0, shoppingCartInfo.length() - 1).toString();
			String cookieValue = null;
			try {
				cookieValue = URLEncoder.encode(cartdata, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			Cookie cookie = new Cookie(getCartName(), cookieValue);
			cookie.setMaxAge(30 * 24 * 60 * 60);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		
	}
	
	protected void clearCart(HttpServletResponse response) {
		Cookie cookie = new Cookie(getCartName(), "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	private String getCartName() {
		try {
			return URLEncoder.encode("_klswCart", "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*public static void main(String[] args) {
		System.out.println(getRequestPathFromReferer("http://localhost:8020/cart?a=1&b=2"));
	}*/
}
