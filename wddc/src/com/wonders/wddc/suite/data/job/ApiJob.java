package com.wonders.wddc.suite.data.job;

import com.wonders.sjic.DispatchContent;
import com.wonders.sjic.entity.InterfaceInfoBo;
import com.wonders.sjic.entity.InterfaceLogBo;
import com.wonders.sjic.entity.InterfaceTransferBo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/25 0025
 * Time: 14:16
 */
@IocBean
public class ApiJob implements Job {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Inject
    private Dao dao;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    /**
     * 获取接口参数信息
     *
     * @param id 接口id
     * @return 获取结果
     */
    public Ret getParamsInfo(String id) {
        InterfaceInfoBo interfaceInfoBo = dao.fetch(InterfaceInfoBo.class, id);
        if (interfaceInfoBo == null) {
            return Ret.error("接口不存在");
        }
//        取出key和value值
        return Ret.success(getParamsInfo(interfaceInfoBo));
    }

    //    取出接口所需参数信息
    private Map<String, String> getParamsInfo(InterfaceInfoBo interfaceInfoBo) {
        String[] key = interfaceInfoBo.getParam().split(",");
        String[] type = interfaceInfoBo.getParamType().split(",");
        Map<String, String> paramsInfo = new HashMap<>();
        for (int i = 0; i < key.length; i++) {
            paramsInfo.put(key[i], type[i]);
        }
        return paramsInfo;
    }

    public Ret getResult(String id, Map<String, String> params) {
        InterfaceInfoBo interfaceInfoBo = dao.fetch(InterfaceInfoBo.class, id);
        Ret ret = null;
        if (interfaceInfoBo == null) {
            return Ret.error("接口不存在");
        }
        if (DispatchContent.IFSUBTYPE_WSDL.equals(interfaceInfoBo.getType())) {
            //数据库中取出相关参数
            String namespace = interfaceInfoBo.getSpaceName();
            String address = interfaceInfoBo.getAddress();
            String methodName = interfaceInfoBo.getServiceName();
            ret = invokeSoap(params, address, methodName, namespace);
        } else if (DispatchContent.IFSUBTYPE_REST.equals(interfaceInfoBo.getType())) {
            String url = interfaceInfoBo.getAddress();
            ret = invokeRest(url, params);
        } else {
            return Ret.error("接口类型错误");
        }
        InterfaceLogBo logBo = (InterfaceLogBo) ret.getdata();
        //设置接口id
        logBo.setJkId(id);
        //更新数据库接口记录
        InterfaceTransferBo transferBo = dao.fetch(InterfaceTransferBo.class, id);
        transferBo.setTransferSum(transferBo.getTransferSum() + 1);
        if (logBo.getTransferFlag().equals("1")) {
            transferBo.setTransferSuccess(transferBo.getTransferSuccess() + 1);
        } else {
            transferBo.setTransferFailed(transferBo.getTransferFailed() + 1);
        }
        Trans.exec(new Atom() {
            public void run() {
                dao.update(transferBo);
                dao.insert(logBo);
            }
        });

        if (logBo.getTransferFlag().equals("1")) {
            return Ret.success(logBo.getResmsg());
        }
        return Ret.error(logBo.getResmsg());
    }

    private Ret invokeRest(String url, Map<String, String> parameterMap) {
        InterfaceLogBo logBo = new InterfaceLogBo();
        Date date = new Date();
        HttpClient httpClient = null;
        PostMethod post = null;
        try {
            httpClient = new HttpClient();
            post = new PostMethod(url);
            //传递参数
            NameValuePair nvps[] = new NameValuePair[]{};
            Set<String> nameSet = parameterMap.keySet();
            String[] nameArray = (String[]) nameSet.toArray();
//        构造请求参数
            for (int i = 0; i < nameArray.length; i++) {
                NameValuePair nvp = new NameValuePair(nameArray[i], parameterMap.get(nameArray[i]));
                nvps[i] = nvp;
            }
//            设置参数
            post.setRequestBody(nvps);
//            设置超时时间
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            //设置请求开始时间
            logBo.setStartTime(sdf.format(date));
            int statusCode = httpClient.executeMethod(post);
            String responseData = post.getResponseBodyAsString();
            logBo.setResmsg(responseData);
            //通信耗时
            logBo.setTransferTime(String.valueOf(System.currentTimeMillis() - date.getTime()));
            logBo.setResmsg(Json.toJson(parameterMap));
            if (statusCode == 200) {
                logBo.setTransferFlag("1");
            } else {
                logBo.setTransferFlag("0");
                if (statusCode >= 500 && statusCode < 600) {
                    logBo.setErrmsg("连接成功，但访问资源返回有误");
                } else if (statusCode == 404 || statusCode == 405 || statusCode == 415) {
                    logBo.setErrmsg("连接成功，但未找到资源");
                } else {
                    logBo.setErrmsg("未知错误");
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Ret.success(logBo);
    }

    //    构造soap请求
    private PostMethod getSoapPostMethod(String url, String requestString) throws UnsupportedEncodingException {
        PostMethod postMethod = new PostMethod(url);
        byte[] bytes = requestString.getBytes("utf-8");
        InputStream inputStream = new ByteArrayInputStream(bytes, 0,
                bytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
                bytes.length, "application/soap+xml; charset=utf-8");
        postMethod.setRequestEntity(requestEntity);
        return postMethod;
    }


    /**
     * 执行soap接口调用
     * 包含日志构造
     *
     * @param patameterMap 参数列表
     * @param wsdlLocation wsdl地址
     * @param methodName   方法名
     * @param namespace    命名空间
     * @return 调用结果
     */
    public Ret invokeSoap(Map<String, String> patameterMap, String wsdlLocation, String methodName, String namespace) {
        InterfaceLogBo logBo = new InterfaceLogBo();
        Date date = new Date();
        HttpClient httpClient = null;
        PostMethod postMethod = null;
        try {
            String soapRequestData = buildSoapRequestData(patameterMap, methodName, namespace);
            //设置请求参数
            logBo.setReqmsg(soapRequestData);
            //获取postMethod
            postMethod = getSoapPostMethod(wsdlLocation, soapRequestData);
            httpClient = new HttpClient();
            //设置超时时间
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            //设置请求开始时间
            logBo.setStartTime(sdf.format(date));
            int statusCode = httpClient.executeMethod(postMethod);
            String soapResponseData = postMethod.getResponseBodyAsString();
            logBo.setResmsg(soapResponseData);
            //通信耗时
            logBo.setTransferTime(String.valueOf(System.currentTimeMillis() - date.getTime()));
            logBo.setResmsg(soapRequestData);
            if (statusCode == 200) {
                logBo.setTransferFlag("1");
            } else {
                logBo.setTransferFlag("0");
                if (statusCode >= 500 && statusCode < 600) {
                    logBo.setErrmsg("连接成功，但访问资源返回有误");
                } else if (statusCode == 404 || statusCode == 405 || statusCode == 415) {
                    logBo.setErrmsg("连接成功，但未找到资源");
                } else {
                    logBo.setErrmsg("未知错误");
                }

            }
        } catch (IOException e) {
            logBo.setErrmsg("服务调用失败,详细原因:" + e.getMessage());
//            设置本次通信结果
            logBo.setTransferFlag("0");
//            设置通信时间
            logBo.setTransferTime(String.valueOf(System.currentTimeMillis() - date.getTime()));
            e.printStackTrace();
        } finally {
//            释放资源,关闭链接
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
            if (httpClient != null) {
                ((SimpleHttpConnectionManager) httpClient.getHttpConnectionManager()).shutdown();
            }
        }

        return Ret.success(logBo);
    }

    /**
     * 构造soap请求数据
     *
     * @param parameterMap 参数map
     * @param methodName   方法名
     * @param namespace    命名空间
     * @return requestEntity
     */
    private String buildSoapRequestData(Map<String, String> parameterMap, String methodName, String namespace) {
        StringBuilder soapRequestData = new StringBuilder();
        soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        soapRequestData
                .append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                        + " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
                        + " xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">");
        soapRequestData.append("<soap12:Body>");
        soapRequestData.append("<").append(methodName).append(" xmlns=\"").append(namespace).append("\">");
        soapRequestData.append("<").append(methodName).append("Request>");
        if(parameterMap!=null && parameterMap.size()>0){
            Set<String> nameSet = parameterMap.keySet();
            for (String name : nameSet) {
                soapRequestData.append("<").append(name).append(">").append(parameterMap.get(name)).append("</").append(name).append(">");
            }
        }
        soapRequestData.append("</").append(methodName).append("Request>");
        soapRequestData.append("</").append(methodName).append(">");
        soapRequestData.append("</soap12:Body>");
        soapRequestData.append("</soap12:Envelope>");
        return soapRequestData.toString();
    }

}
