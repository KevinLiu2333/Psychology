package JSONTest;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;


public class TestUtils {

    public static final String DOMAIN = "http://localhost:8089/";

    public static String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        int size = 0;

        try {
            while ((size = is.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                sb1.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb1.toString();
    }

    /**
     * @param nvps      参数列表
     * @param url       url
     * @param fileList  文件列表
     * @return String
     * @author LiuKun
     */
    public static String getPostRequest(NameValuePair[] nvps, String url, Map<String, File> fileList) throws Exception {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        if (fileList != null) {
            Part[] parts = new Part[0];
            for (NameValuePair nvp : nvps) {
                StringPart sp = new StringPart(nvp.getName(), nvp.getValue(), "UTF-8");
                parts = Arrays.copyOf(parts, parts.length + 1);
                parts[parts.length - 1] = sp;
            }
            for (String fileName : fileList.keySet()) {
                FilePart fp = new FilePart(fileName, fileList.get(fileName), null, "UTF-8");
                parts = Arrays.copyOf(parts, parts.length + 1);
                parts[parts.length - 1] = fp;
            }
            postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
        } else {
            postMethod.setRequestBody(nvps);
        }
        httpClient.executeMethod(postMethod);
        String response = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();
        return response;
    }

    @Test
    public void name() throws Exception {
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod("http://www.google.com");
        client.executeMethod(getMethod);
        System.out.println(getMethod.getResponseBodyAsString());

    }

    @Test
    public void test() throws Exception{
        FileInputStream fis = new FileInputStream("d:/test/test.txt");
        fis.close();
    }
}



