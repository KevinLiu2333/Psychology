package com.kevin.hadoop20170816;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;

/**
 * 运行一个hadoop程序
 * Created with IntelliJ IDEA.
 * User: Kevoin
 * Date: 2017/8/14
 * Time: 16:02
 */
public class MaxTemperature {
    /**
     * 当我们在hadoop集群上运行这个作业时,我们把代码打包成一个JAR文件(hadoop集群会分发这个包).
     * 我们没有明确指定jar文件的名称,而是在JobConf构造函数中传递一个类,Hadoop会找到这个包含此类的jar文件
     *
     * @param args 入参
     * @throws IOException 流异常
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage:MaxTemperature<input path><output path>");
            System.exit(-1);
//            指定各种参数
            JobConf conf = new JobConf(MaxTemperature.class);
            conf.setJobName("Max temperature");
//            输入文件路径 可以是单个文件,目录或文件模式的路径
            FileInputFormat.addInputPath(conf, new Path(args[0]));
//            输出文件路径 运行作业前该目录不应该存在
            FileOutputFormat.setOutputPath(conf, new Path(args[1]));
//            mapper
            conf.setMapperClass(MaxTemperatureMapper.class);
//            reducer
            conf.setReducerClass(MaxTemperatureReducer.class);
            JobClient.runJob(conf);
        }
    }
}
