package com.kevin.hadoopurl;


import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/16
 * Time: 17:42
 */
public class UrlStream {



    @Test
    public void url(){
        InputStream in = null;
        try {
            in = new URL("hdfs://host/path").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeStream(in);
        }
    }
}
