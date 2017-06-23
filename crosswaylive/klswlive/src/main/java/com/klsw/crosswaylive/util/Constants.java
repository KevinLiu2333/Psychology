package com.klsw.crosswaylive.util;

public interface Constants {

    String[] parsePattern = new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm","yyy-MM-dd"};
    //aliyun keySecret
    String accessKeySecret = "PHXKpCu6FlM5aIJFinaCxUrMSkCp79";

    //推流地址公共部分
    String PUBLIC_PUSH_STREAM_PATH = "rtmp://video-center.alivecdn.com/alivemovie/courses?vhost=live.klsw.com&auth_key=";
    //拉流地址公共部分
    String PUBLIC_PULL_STREAM_PATH = "rtmp://live.klsw.com/alivemovie/courses?auth_key=";
    //鉴权key
    String PRIVATE_KEY = "klsw0208";
    //有效时间
    long EFFECTIVE_TIME = 20 * 60 * 1000;
    //生成推流、拉流地址格式
    String CREATE_STREAM_PATH = "/alivemovie/courses-timestamp-0-0-privateKey";
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
    //最多人数
    Integer MAX_AUD = 20;
    String liveBucket = "klsw-live";
}
