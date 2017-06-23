package com.klsw.piano.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by liukun on 2017/4/19.
 * 微信相关
 */
public class WxUtils {
    public static String appId = "wx8da0e4adbeb68b5a";

    public static String shareUrl(String url) throws UnsupportedEncodingException {
        return String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=klsw#wechat_redirect", appId, URLEncoder.encode(url, "utf-8"));
    }
}
