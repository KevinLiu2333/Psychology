package soaptest;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/28 0028
 * Time: 10:28
 */
public class ApiTest {

    @Test
    public void jdkTest() {
        String endpoint = "http://localhost:9000/HelloWorld?wsdl";
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();
        } catch (ServiceException e) {
            e.printStackTrace();
            return;
        }
        call.setTargetEndpointAddress(endpoint);
        // WSDL里面描述的接口名称(要调用的方法)
        call.setOperationName(new QName("http://example/", "sayHelloWorldFrom"));
        // 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)
        call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);
        // 设置被调用方法的返回值类型
        call.setReturnType(XMLType.XSD_STRING);
        //设置方法中参数的值
        Object[] paramValues = new Object[]{"liukun"};
        call.setTimeout(3000);
        // 给方法传递参数，并且调用方法
        String result = null;
        try {
            result = (String) call.invoke(paramValues);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("result is " + result);
    }

    @Test
    public void cxfTest() {
        String endpoint = "http://localhost:8080/server/web-publish";
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();
        } catch (ServiceException e) {
            e.printStackTrace();
            return;
        }
        call.setTargetEndpointAddress(endpoint);
        // WSDL里面描述的接口名称(要调用的方法)
        call.setOperationName(new QName("http://webservice/", "add"));
        // 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)
        call.addParameter("firstA", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("firstB", XMLType.XSD_STRING, ParameterMode.IN);
        // 设置被调用方法的返回值类型
        call.setReturnType(XMLType.XSD_STRING);
        //设置方法中参数的值
        Object[] paramValues = new Object[]{"1", "4"};
        call.setTimeout(3000);
        // 给方法传递参数，并且调用方法
        String result = null;
        try {
            result = (String) call.invoke(paramValues);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("result is " + result);
    }

    @Test
    public void axisTest() {
        String endpoint = "http://localhost:8080/services/Test?wsdl";
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();
        } catch (ServiceException e) {
            e.printStackTrace();
            return;
        }
        call.setTargetEndpointAddress(endpoint);
        // WSDL里面描述的接口名称(要调用的方法)
        call.setOperationName(new QName("http://localhost:8080/services/Test", "saveOrUpdateBusinessCollect"));
        // 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)
        call.addParameter("dataxml", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("businessxml", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("authenticatexml", XMLType.XSD_STRING, ParameterMode.IN);
        // 设置被调用方法的返回值类型
        call.setReturnType(XMLType.XSD_STRING);
        //设置方法中参数的值
        Object[] paramValues = new Object[]{"liukun", "json","abc"};
        call.setTimeout(3000);
        // 给方法传递参数，并且调用方法
        String result = null;
        try {
            result = (String) call.invoke(paramValues);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("result is " + result);
    }

    @Test
    public void axis2Test() {
        String endpoint = "http://localhost:8080/services/testWebService";
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();
        } catch (ServiceException e) {
            e.printStackTrace();
            return;
        }
        call.setTargetEndpointAddress(endpoint);
        // WSDL里面描述的接口名称(要调用的方法)
        call.setOperationName(new QName("http://control.webservice.com", "getMessege"));
        // 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)
        call.addParameter("params", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("return", XMLType.XSD_STRING, ParameterMode.IN);
        // 设置被调用方法的返回值类型
        call.setReturnType(XMLType.XSD_STRING);
        //设置方法中参数的值
        Object[] paramValues = new Object[]{"liukun", "json"};
        call.setTimeout(3000);
        // 给方法传递参数，并且调用方法
        String result = null;
        try {
            result = (String) call.invoke(paramValues);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("result is " + result);
    }

    @Test
    public void xfireTest() {
        String endpoint = "http://localhost:8080/services/Test";
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();
        } catch (ServiceException e) {
            e.printStackTrace();
            return;
        }
        call.setTargetEndpointAddress(endpoint);
        // WSDL里面描述的接口名称(要调用的方法)
        call.setOperationName(new QName("haople", "say"));
        // 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)
        call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);
        // 设置被调用方法的返回值类型
        call.setReturnType(XMLType.XSD_STRING);
        //设置方法中参数的值
        Object[] paramValues = new Object[]{"liukun"};
        call.setTimeout(3000);
        // 给方法传递参数，并且调用方法
        String result = null;
        try {
            result = (String) call.invoke(paramValues);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("result is " + result);
    }


//
//    @Test
//    public void test2() throws Exception {
//        DynamicHttpclientCall dynamicHttpclientCall = new DynamicHttpclientCall("http://example/", new QName("http://example/", "sayHelloWorldFrom").toString(), "http://localhost:9000/HelloWorld?wsdl");
//        Map<String, String> params = new HashMap<>();
//        params.put("arg0", "liukun");
//        System.out.println(dynamicHttpclientCall.invoke(params));
//    }


}
