package com.kevin.hadoopurl;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 用URLStreamHandler以标准输出格式显示Hadoop文件系统的文件
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/16
 * Time: 18:58
 */
public class URLCat {

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args) {
        InputStream in = null;
        try{
            in = new URL(args[0]).openStream();
            IOUtils.copyBytes(in,System.out,4096,false);
        }catch (IOException e){

        }finally {
            IOUtils.closeStream(in);
        }
    }
}
