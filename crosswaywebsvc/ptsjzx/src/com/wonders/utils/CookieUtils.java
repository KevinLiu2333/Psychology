package com.wonders.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * Cookie 辅助类
 * 
 * 
 * 
 */
public class CookieUtils {
    /**
     * 每页条数cookie名称
     */
    public static final String COOKIE_PAGE_SIZE = "_cookie_page_size";
    /**
     * 默认每页条数
     */
    public static final int DEFAULT_SIZE = 20;
    /**
     * 最大每页条数
     */
    public static final int MAX_SIZE = 200;

    /**
     * 获得cookie的每页条数
     * 
     * 使用_cookie_page_size作为cookie name
     * 
     * @param request
     *            HttpServletRequest
     * @return default:20 max:200
     */
    public static int getPageSize(HttpServletRequest request) {
        // Assert.notNull(request);
        String ps = getCookie(request, COOKIE_PAGE_SIZE);
        int count = 0;
        if (ps != null) {
            if (NumberUtils.isDigits(ps)) {
                count = Integer.parseInt(ps);
            }
        }
        if (count <= 0) {
            count = DEFAULT_SIZE;
        } else if (count > MAX_SIZE) {
            count = MAX_SIZE;
        }
        return count;
    }

    /**
     * 获得cookie
     * 
     * @param request
     *            HttpServletRequest
     * @param name
     *            cookie name
     * @return if exist return cookie, else return null.
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        String value = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (name.equals(c.getName())) {
                    try {
                        value = URLDecoder.decode(c.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                    }

                    return value;
                }
            }
        }
        return null;
    }

    /**
     * 根据部署路径，将cookie保存在根目录。
     * 
     * @param request
     * @param response
     * @param name
     * @param value
     * @param expiry
     * @param domain
     * @return
     */
    public static Cookie addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
            Integer expiry, String domain) {
        try {
            // 关键点
            value = URLEncoder.encode(value, "UTF-8");

        } catch (UnsupportedEncodingException e) {
        }

        Cookie cookie = new Cookie(name, value);
        if (expiry != null) {
            cookie.setMaxAge(expiry);
        }
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        response.addCookie(cookie);
        return cookie;
    }

    /**
     * 取消cookie
     * 
     * @param request
     * @param response
     * @param name
     * @param domain
     */
    public static void cancleCookie(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }
}
