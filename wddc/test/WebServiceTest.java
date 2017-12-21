package com.wonders.sjic.service;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/11/22
 * Time: 16:17
 */
public class WebServiceTest {

    public static void main(String[] args) throws MalformedURLException, ServiceException, RemoteException {
        String endpoint = "http://31.1.2.140/ptsjzx/services/kwWzfPersonService";
        URL url = new URL(endpoint);
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(url);
        call.setOperationName(new QName("http://31.1.2.140/ptsjzx/services/kwWzfPersonService","getInfoByZjhmAndTableToKwWeizoufang" ));
        call.addParameter("key", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("ZJHM", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("table", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("returntype", XMLType.XSD_STRING, ParameterMode.IN);
        Object[] objects = new Object[]{"0b78826b56c6492488823877edcdba76","13010820140708184X","T_GA_RJBXX_20170205","json"};
        System.out.println((String) call.invoke(objects));
    }
}
