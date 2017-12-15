package com.kevin.springboot.rpc.http;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/12/5
 * Time: 15:24
 */
public class ByteUtil {

    public static int bytes2Int(byte[] bytes) {
        //jvm会自动补24个高位1.&0xff可以将高的24位置为0，低8位保持原样。这样做的目的就是为了保证二进制数据的一致性,避免符号位补位造成的干扰。
        //最小八位
        int num = bytes[3] & 0xFF;
        //9-16位 <<位移8位,补0,通过&0xFF00将其他位补0
        num |= ((bytes[2] << 8) & 0xFF00);
        //17-24位 <<位移16位,同上
        num |= ((bytes[1] << 16) & 0xFF0000);
        //25-32位,同上
        num |= ((bytes[0] << 24) & 0xFF000000);
        return num;
    }

    public static byte[] int2ByteArray(int i) {
        byte[] result = new byte[4];
        //去除符号位补位的干扰
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

}
