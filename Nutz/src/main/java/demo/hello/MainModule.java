package demo.hello;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/21 0021
 * Time: 9:27
 */
public class MainModule {

    @At("/hello")
//    按照
    @Ok("jsp:jsp.hello")
    public String doHello() {
        return "Hello Nutz";
    }
}
