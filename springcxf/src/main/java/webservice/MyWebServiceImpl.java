package webservice;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/28 0028
 * Time: 18:19
 */

import javax.jws.WebService;

@WebService(endpointInterface = "webservice.MyWebService")
public class MyWebServiceImpl implements MyWebService{
    @Override
    public int add(int a, int b) {
        System.out.println(a + "+" + b + "=" + (a + b));
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        System.out.println(a + "-" + b + "=" + (a - b));
        return a - b;
    }
}
