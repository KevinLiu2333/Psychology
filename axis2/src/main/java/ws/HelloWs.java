package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/21
 * Time: 15:33
 */
@WebService
public class HelloWs {
    @WebMethod
    public String sayHello(String name) {
        if (name == null) {
            return "Hello";
        }
        return "Hello, " + name + "!";
    }
}
