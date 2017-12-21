package com.kevin.hadoopfile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * 显示一个hadoop文件系统中一些路径的文件信息
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/23
 * Time: 11:09
 */
public class ListStatus {

    public static void main(String[] args) throws Exception{
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri),conf);
        Path[] paths = new Path[args.length];
        for(int i=0;i<paths.length;i++){
            paths[i]= new Path(args[i]);
        }
        FileStatus[] status = fs.listStatus(paths);
        Path[] listedPaths = FileUtil.stat2Paths(status);
        for (Path p:listedPaths){
            System.out.println(p);
        }

    }
}
