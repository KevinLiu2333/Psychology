package com.wonders.test;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.junit.Test;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/28 0028
 * Time: 10:01
 */
public class ApiTest {

    @Test
    public void test1() throws Exception {
        Service service = new Service();
        Call call = (Call)service.createCall();
        URL endpoint = new URL("http://zong.bjchyedu.cn/ids/services/account?wsdl");
        call.setTargetEndpointAddress(endpoint);
        call.setUseSOAPAction(true);
        System.out.println();
    }
}
