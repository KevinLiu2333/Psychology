package com.kevin.hadoopurl;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.net.URI;

/**
 * 将文件两次写入标准输出:在写一次后,定位到文件的开头再次读入数据流
 * 通过使用seek两次以标准输出格式显示hadoop文件系统的文件
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/16
 * Time: 19:26
 */
public class FileSystemDoubleCat {
    public static void main(String[] args) throws Exception {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FSDataInputStream in = null;//实现了PositionedReadable接口.从一个指定位置读取一部分数据
        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
            in.seek(0);
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
