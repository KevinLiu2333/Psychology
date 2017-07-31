package com.wonders.wddc.suite.data.job;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

/**
 * 动态构造soap请求
 * Created with IntelliJ IDEA.
 * User: Kevin Liu
 * Date: 2017/7/26 0026
 * Time: 8:44
 */
public class DynamicHttpclientCall {

    private String namespace;
    private String methodName = "wddc";
    private String wsdlLocation;
    private String soapResponseData;

    public DynamicHttpclientCall(String namespace, String methodName,
                                 String wsdlLocation) {

        this.namespace = namespace;
        this.methodName = methodName;
        this.wsdlLocation = wsdlLocation;
    }

    public DynamicHttpclientCall(String namespace, String wsdlLocation) {
        this.namespace = namespace;
        this.wsdlLocation = wsdlLocation;
    }

    private int invoke(Map<String, String> patameterMap) throws Exception {
        PostMethod postMethod = new PostMethod(wsdlLocation);
        String soapRequestData = buildSoapRequestData(patameterMap);

        byte[] bytes = soapRequestData.getBytes("utf-8");
        InputStream inputStream = new ByteArrayInputStream(bytes, 0,
                bytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
                bytes.length, "application/soap+xml; charset=utf-8");
        postMethod.setRequestEntity(requestEntity);

        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
        int statusCode = httpClient.executeMethod(postMethod);
        soapResponseData = postMethod.getResponseBodyAsString();

        return statusCode;
    }

    private String buildSoapRequestData(Map<String, String> parameterMap) {
        StringBuilder soapRequestData = new StringBuilder();
        soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        soapRequestData
                .append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                        + " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
                        + " xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">");
        soapRequestData.append("<soap12:Body>");
        soapRequestData.append("<").append(methodName).append(" xmlns=\"").append(namespace).append("\">");
        soapRequestData.append("<").append(methodName).append("Request>");

        Set<String> nameSet = parameterMap.keySet();
        for (String name : nameSet) {
            soapRequestData.append("<").append(name).append(">").append(parameterMap.get(name)).append("</").append(name).append(">");
        }

        soapRequestData.append("</").append(methodName).append("Request>");
        soapRequestData.append("</").append(methodName).append(">");
        soapRequestData.append("</soap12:Body>");
        soapRequestData.append("</soap12:Envelope>");

        return soapRequestData.toString();
    }


//    /**
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        // TODO Auto-generated method stub
//
//        DynamicHttpclientCall dynamicHttpclientCall = new DynamicHttpclientCall(
//                "http://shippingapi.ebay.cn/", "GetAPACShippingPackage",
//                "http://epacketws.pushauction.net/v3/orderservice.asmx?wsdl");
//
//        Map<String, String> patameterMap = new HashMap<>();
//        patameterMap.put("TrackCode", "123");
//        patameterMap.put("Version", "123");
//        patameterMap.put("APIDevUserID", "123");
//        patameterMap.put("APIPassword", "123");
//        patameterMap.put("APISellerUserID", "123");
//        patameterMap.put("MessageID", "123");
//        patameterMap.put("TrackCode", "123");
//
//        String soapRequestData = dynamicHttpclientCall.buildRequestData(patameterMap);
//        System.out.println(soapRequestData);
//
//        int statusCode = dynamicHttpclientCall.invoke(patameterMap);
//        if (statusCode == 200) {
//            System.out.println("调用成功！");
//            System.out.println(dynamicHttpclientCall.soapResponseData);
//        } else {
//            System.out.println("调用失败！错误码：" + statusCode);
//        }
//
//    }
}
