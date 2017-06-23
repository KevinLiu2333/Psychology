package com.klsw.crosswaylive.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.Random;

/**
 * 对帐号口令加密
 */
public class PasswdEncryption {
    private static final MessageDigest md;
	private static final BASE64Encoder b64Encoder;

    static {
        try {
            md = MessageDigest.getInstance("MD5", "SUN");
            b64Encoder = new BASE64Encoder();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * byte数组转成md5工具
     *
     * @param bys 字节组
     * @return md5结果
     */
    public static String md5String(byte[] bys) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int j = bys.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = bys[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }

        return new String(str);

    }


    /**
     * 验证用户登录
     *
     * @param username      用户名
     * @param storepassword 存储的密码
     * @param token         token
     * @param timestamp     时间戳
     * @param password      客户端传入的密码
     * @return 验证结果
     */
    public static boolean checkPasswd(String username,
                                      String storepassword,
                                      String token,
                                      String timestamp,
                                      String password) {
        try {
            //第一次md5:token+密文密码
            md.reset();
            md.update(token.getBytes("utf-8"));
            md.update(storepassword.getBytes("utf-8"));
            byte[] bys1 = md.digest();
            String ret1 = md5String(bys1);
            //第二次md5:时间戳+用户名+第一次md5的结果
            md.reset();
            md.update(timestamp.getBytes("utf-8"));
            md.update(username.getBytes("utf-8"));
            md.update(ret1.getBytes("utf-8"));
            byte[] bys2 = md.digest();
            String ret2 = md5String(bys2);
            md.reset();
            //第三次md5:第二次md5的结果+token
            md.update(ret2.getBytes("utf-8"));
            md.update(token.getBytes("utf-8"));
            byte[] bys3 = md.digest();
            String pwd = md5String(bys3);
            if (pwd.equalsIgnoreCase(password)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;

    }

    /**
     * 将客户输入的密码加密
     *
     * @param inputPasswd 加密前密码
     * @return 加密后密码
     */
    public static String toPasswd(String inputPasswd) {
        byte[] salt = getSaltOfASCII(2);
        return toPasswd(inputPasswd, salt);
    }

    /**
     * 将客户输入的密码加密
     *
     * @param inputPasswd 客户输入的密码
     * @param salt        盐
     * @return 加密后的字符串
     */
    public static String toPasswd(String inputPasswd, byte[] salt) {
        String pwd = "";

        try {
            md.reset();
            md.update(salt);
            md.update(inputPasswd.getBytes("UTF-8"));
            byte[] bys = md.digest();

            pwd += (char) salt[0];
            pwd += (char) salt[1];
            pwd += md5String(bys);
        } catch (Exception ex) {
            ex.printStackTrace();
            pwd = "";
        }

        return pwd;
    }

    /**
     * 返回指定长度的盐(ASCII码)
     *
     * @param len 长度
     * @return 盐
     */
    public static byte[] getSaltOfASCII(int len) {
        byte[] salt = new byte[len];
        Random rand = new Random();

        for (int i = 0; i < len; i++) {
            // salt[i] = 'A';
            salt[i] = (byte) ((rand.nextInt('~' - '!') + '!') & 0x007f);
        }
        return salt;
    }

    /**
     * @param timestamp 时间戳
     * @param username  用户名
     * @return ticket
     */
    public static String createTicket(String timestamp, String username) {
        try {
            md.reset();
            md.update(timestamp.getBytes("utf-8"));
            md.update(username.getBytes("utf-8"));
            byte[] bys = md.digest();
            String ticket = md5String(bys);
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 生成验证码密文
     *
     * @param username 用户名
     * @param captcha  用户名对应的验证码
     * @return 密文验证码
     */
    public static String storeCaptcha(String username, String captcha) {
        try {
            md.reset();
            md.update(username.getBytes("utf-8"));
            md.update(captcha.getBytes("utf-8"));
            byte[] bys = md.digest();
            String storeCaptcha = md5String(bys);
            return storeCaptcha;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 客户端根据参数生成密文
     *
     * @param token     token值
     * @param password  用户输入明文密码
     * @param timestamp 时间戳
     * @param username  用户名
     * @return 加密密码
     */
    public static String createPassword(String token, String password, String timestamp, String username) throws Exception {
        password = PasswdEncryption.toPasswd(password, token.substring(0, 2).getBytes("utf-8"));
        md.reset();
        md.update(token.getBytes("utf-8"));
        md.update(password.getBytes("utf-8"));
        byte[] bys1 = md.digest();
        String ret1 = md5String(bys1);
        md.reset();
        md.update(timestamp.getBytes("utf-8"));
        md.update(username.getBytes("utf-8"));
        md.update(ret1.getBytes("utf-8"));
        byte[] bys2 = md.digest();
        String ret2 = md5String(bys2);
        md.reset();
        md.update(ret2.getBytes("utf-8"));
        md.update(token.getBytes("utf-8"));
        byte[] bys3 = md.digest();
        String pwd = md5String(bys3);
        return pwd;
    }

    /**
     * 方法作用：生成并返回密码
     *
     * @param userName 用户名
     * @param userPwd  密码
     * @return
     */
    public static String getPwd(String userName, String userPwd) {
        try {
            md.reset();
            md.update(userName.getBytes("utf-8"));
            md.update(userPwd.getBytes("utf-8"));
            byte[] bys = md.digest();
            String password = md5String(bys).substring(8, 24);
            return password;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

//    public static void main(String[] args) {
//        System.out.println();
//    }
}