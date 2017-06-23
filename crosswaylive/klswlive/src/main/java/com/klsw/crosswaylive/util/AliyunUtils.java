package com.klsw.crosswaylive.util;

import java.security.MessageDigest;
import java.util.Date;

/**
 * Created by liukun on 2017/3/20.
 * 阿里云相关工具
 */
public class AliyunUtils {
    /**
     * @param streamName 流名称
     * @return 生成推流地址
     * @throws Exception 异常
     */
    public static String getPushStreamPath(String streamName) throws Exception {
        String url = "rtmp://video-center.alivecdn.com/Live/" + streamName + "?vhost=live.klsw.com&auth_key=";
        String uri = "/Live/" + streamName;
        long timestamp = System.currentTimeMillis() / 1000 + 1000;
        String rand = "0";
        String uid = "0";
        String privateKey = "klsw0208";
        String sstring = uri + "-" + timestamp + "-" + rand + "-" + uid + "-" + privateKey;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(sstring.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuilder buf = new StringBuilder();
        for (byte aB : b) {
            i = aB;
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        String a = timestamp + "-" + "0" + "-" + "0" + "-" + buf.toString();
        return url + a;
    }

    /**
     * 生成拉流地址
     *
     * @param streamName 流信息
     * @return 拉流地址
     */
    public static String getPullStreamPath(String streamName){
        long timestamp = (new Date().getTime() + Constants.EFFECTIVE_TIME) / 1000;
        String pullStreamPath = Constants.CREATE_STREAM_PATH.replace("alivemovie", "Live")
                .replace("courses", streamName).replace("timestamp", String.valueOf(timestamp))
                .replace("privateKey", Constants.PRIVATE_KEY);
        pullStreamPath = Constants.PUBLIC_PULL_STREAM_PATH.replace("alivemovie", "Live").replace("courses", streamName) + timestamp + "-0-0-"
                + MD5Utils.MD5(pullStreamPath).toLowerCase();
        return pullStreamPath;
    }

//    public static void main(String[] args) {
//        System.out.println(getPullStreamPath("yindian"));
//    }
}

