package demo.filter;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.filter.CheckSession;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/24 0024
 * Time: 15:55
 */
public class Filter {

    @At
//    内置过滤器
    @Filters(@By(type = CheckSession.class, args = {"user", "/login.jsp"}))
//    也可以交给ioc容器配置
//    @Filters(@By(type=MyFilter.class, args={"ioc:myFilter"}))
    public void checkSession() {

    }
}
