package com.klsw.weikesite.utils;


import com.klsw.apiCommon.api.model.TbCwk;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestUtils {

    public static final String DOMAIN = "http://wksvc.klsw.com";

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
     * @param @param  request
     *                请求
     * @param @param  list
     *                除信息验证外的其他参数
     * @param @param  url
     *                地址
     * @param @param  files
     *                文件
     * @param @return
     * @param @throws Exception
     * @return String
     * @throws
     * @Description: 登陆后的请求操作
     * @author LiuKun
     * @date 2016年9月22日
     */
    public static String loggedInRequest(HttpServletRequest request, List<NameValuePair> list, String url, List<File> files) throws Exception {
        TbCwk tbCwk = (TbCwk) request.getSession(true).getAttribute("user");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String username = tbCwk.getName();
        String type = tbCwk.getType();
        String ticket = tbCwk.getTicket();
        NameValuePair nvp1 = new NameValuePair("username", username);
        NameValuePair nvp2 = new NameValuePair("type", type);
        NameValuePair nvp3 = new NameValuePair("ticket", ticket);
        nvps.add(nvp1);
        nvps.add(nvp2);
        nvps.add(nvp3);
        if (list != null) {
            for (NameValuePair nvp : list) {
                nvps.add(nvp);
            }
        }
        NameValuePair[] nvpsArray = new NameValuePair[nvps.size()];
        nvpsArray = nvps.toArray(nvpsArray);
        String response = TestUtils.getPostRequest(nvpsArray, url, files);
        return response;

    }

    /**
     * @param @param  nvps
     *                参数列表
     * @param @param  url
     *                url
     * @param @param  fileList
     *                文件列表
     * @param @return
     * @param @throws Exception
     * @return String
     * @throws
     * @Description: 测试工具
     * @author LiuKun
     * @date 2016年9月1日
     */
    public static String getPostRequest(NameValuePair[] nvps, String url, List<File> fileList) throws Exception {
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
            for (File file : fileList) {
                FilePart fp = new FilePart("file", file, null, "UTF-8");
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
}
