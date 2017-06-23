package com.klsw.klswWebService.util;

public class Constants {
    public final static Integer MAX_AUD=20;
    public static final String liveBucket = "klsw-live";
    public static final String[] parsePattern = new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm","yyy-MM-dd"};
    //用户输错密码锁定时间
    public static final long LOCKTIME = 60 * 1000 * 10;
    public static final Integer ORDER_STATUS_NOT_PAY = 1;

    public static final Integer ORDER_STATUS_PAY = 2;

    //货款到账
    public static final Integer ORDER_STATUS_GET = 3;

    public static final Integer ORDER_STATUS_CLOSED = 10;

    public static final Integer ORDER_STATUS_FINISHED = 20;
    ;
    //作业上传包
    public static final String HOMEWORK_UPLOAD = "/upload/homework/";
    //系统消息上传包
    public static final String MESSAGE_UPLOAD = "/upload/message/";
    //新闻上传包
    public static final String NEWS_UPLOAD = "/upload/news/";
    //头像上传包
    public static final String FAVICON_UPLOAD = "/upload/user/favicon/";
    //资历证书上传包
    public static final String CERTIFICATE_UPLOAD = "/upload/user/certification/";
    //身份证上传包
    public static final String IDENTITYCARD_UPLOAD = "/upload/user/identity/";
    //反馈上传包
    public static final String FEEDIMAGE_UPLOAD = "/upload/feed/feedImage/";
    //用户类型
    public final static String[] USER_TYPE = {"tea", "stu", "org"};
    //每页显示的数量
    public final static Integer PAGE_SIZE = 9;
    //微信分享路径
    public static final String WEIXIN_URL = "http://piano.klsw.com";
    //琴上资源路径
    public static final String VIDEOHOST = "http://piano-static.klsw.com";
    //威客资源路径
    public static final String WKHOST = "http://wk-static.klsw.com";
    //siteVersion
    public static final String SITE_VERSION = "?v=1.0";

    public static final String WEIXINPIC_URL = "http://piano-static.klsw.com";
    //节点
    public static final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

    public static final String accessKeyId = "LTAIksw4QT0KaoXY";

    public static final String accessKeySecret = "PHXKpCu6FlM5aIJFinaCxUrMSkCp79";

    public static final String pianoBucket = "klsw-piano";

    public static final String wkBucket = "klsw-wk";

    //威客教师批改包月价格最大值
    public static final Float MAX_PRICE = 1000000f;
    
    //威客学生签约老师试用次数
    public static final Integer TRY_USE_COUNT = 2;
    
    //威客学生签约老师包月次数
    public static final Integer MONTH_COUNT = 30;
}
