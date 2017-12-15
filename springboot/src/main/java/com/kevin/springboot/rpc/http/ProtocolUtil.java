package com.kevin.springboot.rpc.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/12/5
 * Time: 15:12
 */
public class ProtocolUtil {
    public static Request readRequest(InputStream input) throws IOException{

        //读取编码
        byte[] encodeByte = new byte[1];
        input.read(encodeByte);
        byte encode = encodeByte[0];

        //读取命令长度
        byte[] commandLengthBytes = new byte[4];
        input.read(commandLengthBytes);

        return null;
    }
}
