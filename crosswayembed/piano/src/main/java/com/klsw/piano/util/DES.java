package com.klsw.piano.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by liukun on 2016/11/2.
 * DES加密解密
 */
public class DES {
    //DES解密，返回解密后的原字符串
    public static String decrypt(String decryptString, String decryptKey, String iv)
            throws Exception {
        IvParameterSpec iv1 = new IvParameterSpec(iv.getBytes());
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv1);
        //解密时将"-"替换为"/","_"替换为"+"
        decryptString = decryptString.replace("-", "/").replace("_", "+");
        byte[] bytes = Base64.decode(decryptString);
        bytes = cipher.doFinal(bytes);
        return new String(bytes);
    }

    //DES加密，返回加密字符串
    public static String encrypt(String encryptString, String encryptKey, String iv)
            throws Exception {
        IvParameterSpec iv1 = new IvParameterSpec(iv.getBytes());
        DESKeySpec dks = new DESKeySpec(encryptKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv1);
        String base64String = Base64.encode(cipher.doFinal(encryptString.getBytes()));
        //"/"替换为"-","+"替换为"_"
        base64String = base64String.replace("/", "-").replace("+", "_");
        return base64String;
    }
//
//    public static void main(String[] args) {
//        try {
//            System.out.println(encrypt("wk16", "crossway", "waycross"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
