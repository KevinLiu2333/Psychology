package com.wonders.sjic.service;

import com.wonders.sjic.entity.InterfaceInfoBo;
import com.wonders.sjic.entity.InterfaceLogBo;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/8/7 0007
 * Time: 11:03
 */
@IocBean
public class WSDLService implements _BaseService {

    @Inject
    private Dao dao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static Service service;
    private static Call call;

    static {
        service = new Service();
        try {
            call = (Call) service.createCall();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ret execute(InterfaceInfoBo infoBo, Map<String, Object> params) {
        InterfaceLogBo logBo = new InterfaceLogBo();
        logBo.setJkId(infoBo.getId());
        logBo.setId(UUID.randomUUID().toString());
        logBo.setReqMsg(Json.toJson(params));
        URL url = null;
        String responseString = null;
        try {
//            处理endpoint
            String endpoint = infoBo.getAddress();
            endpoint = endpoint.endsWith("?wsdl") || endpoint.endsWith("?WSDL") ? endpoint.substring(0, endpoint.lastIndexOf("?")) : endpoint;
            url = new URL(endpoint);
        } catch (MalformedURLException e) {
            logBo.setErrMsg("接口地址不符合url规范");
//            记录日志
            dao.insert(infoBo);
            return Ret.error("接口地址不符合url规范");
        }
//        设置endpoint
        call.setTargetEndpointAddress(url);
//        设置WSDL里面描述的接口名称(要调用的方法)
        call.setOperationName(new QName(infoBo.getSpaceName(), infoBo.getServiceName()));
//        设置被调用方法的返回值类型
        call.setReturnType(XMLType.XSD_STRING);
//        设置超时时间
        call.setTimeout(3000);
        List<Object> valueList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            call.addParameter(entry.getKey(), XMLType.XSD_STRING, ParameterMode.IN);
            valueList.add(entry.getValue());
        }
        Object[] valueArray = valueList.toArray();
        Date now = new Date();
        try {
            logBo.setStartTime(sdf.format(now));
            responseString = (String) call.invoke(valueArray);
        } catch (RemoteException e) {
//            通讯错误
            logBo.setErrMsg(e.getMessage());
            logBo.setTransferTime(String.valueOf(System.currentTimeMillis() - now.getTime()));
//            记录日志
            dao.insert(logBo);
            return Ret.error("通讯错误:" + e.getMessage());
        }
//        成功,返回成功结果
        logBo.setResMsg(responseString);
        dao.insert(logBo);
        return Ret.success(responseString);
    }

//    public static void main(String[] args) {
//        String a = "http://localhost:8080/ptsjzx/services/CorpService?wsdl";
//        System.out.println(a.substring(0,a.lastIndexOf("?")));
//    }
}
