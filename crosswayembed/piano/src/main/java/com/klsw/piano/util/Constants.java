package com.klsw.piano.util;

/**
 * Created by liukun on 2016/11/14.
 */
public interface Constants {
    //用户输错密码锁定时间
    long LOCKTIME = 60 * 1000 * 10;
    //作业上传包/
    String HOMEWORK_UPLOAD = "/upload/homework/";
    //新闻上传包
    String NEWS_UPLOAD = "/upload/news/";
    //头像上传包
    String FAVICON_UPLOAD = "/upload/user/favicon/";
    //用户类型
    String[] USER_TYPE = {"tea", "stu", "org"};
    //每页显示的数量
    Integer PAGE_SIZE = 9;
    //静态资源路径
    String VIDEOHOST = "http://piano-static.klsw.com";
    //siteVersion
    String SITE_VERSION = "?v=1.0";
    //测试IP
    String TEST_IP = "112.26.196.168";
    //微信密码
    String WEIXINSECRET = "448a34255b7ecc853e468adb219ee47e";
    //随机字符源
    String RANDOMSTRINGBASE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //微信source
    String WEIXINSOURCE = "http://piano-static.klsw.com";
    //威客资源
    String WKHOST = "http://wk-static.klsw.com";
    //微信token
    String WXTOKEN = "klsw";

    String ENCODING_AESKEY = "KWYiX5wWVA6tWENFmyYk8fhzd61xA5XSi3pCfYgidpq";

    //不拦截请求路径数组
    String[] EXECLUDE_PATH = {"/Log/log", "/open/work", "/pm/addHomeworkF", "/PMVersion/getVersionList",
            "/PMVersion/getHistoryVersionList", "/serial/UpdateUname", "/serial/UpdateSerial",
            "/serial/register", "/serial/RegisterNoUserName", "/serial/GetNewUserName", "/serial/GetIsLocked"};

    String WEIXINPIC_URL = "http://piano-static.klsw.com";

    //微信分享路径
    String WEIXIN_URL = "http://piano.klsw.com";
    //OSSbucket
    String pianoBucket = "klsw-piano";

    String wkBucket = "klsw-wk";
}
