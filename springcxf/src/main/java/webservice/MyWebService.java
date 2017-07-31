package webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/28 0028
 * Time: 18:17
 */
@WebService
public interface MyWebService {
    int add(@WebParam(name = "firstA") int a, @WebParam(name = "firstB") int b);

    int minus(@WebParam(name = "secondA") int a, @WebParam(name = "secondB") int b);

}
