package LogTest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by liukun on 2017/1/5.
 * Log日志测试
 */
public class Log {

    @Test
    public void test() {
        try {
            HttpClient httpClient = new HttpClient();
            PostMethod postMethod = new PostMethod("http://localhost:8028/log/log");
            postMethod.setRequestBody(new FileInputStream(new File("d:/log.1")));
            httpClient.executeMethod(postMethod);
            int responsecode = postMethod.getStatusCode();
            System.out.println(responsecode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
