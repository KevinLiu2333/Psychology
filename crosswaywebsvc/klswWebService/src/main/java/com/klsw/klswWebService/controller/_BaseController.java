package com.klsw.klswWebService.controller;

import com.klsw.klswWebService.captcha.ImageCaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 所有Controller的基类
 */
public class _BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    //生成图片验证码
    public void createImgCaptcha(HttpServletRequest request, HttpServletResponse response, String code) throws IOException {

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
     *
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
        if (index >= 0)
            refererReturn = referer.substring(index, referer.length());
        return refererReturn.contains("/login") ? "/order/list" : refererReturn;
    }

    /**
     * 获取请求IP
     *
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

		
	
	/*public static void main(String[] args) {
        System.out.println(getRequestPathFromReferer("http://localhost:8020/cart?a=1&b=2"));
	}*/

}
