package demo.filter;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;

/**
 * 自己定制的过滤器
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/24 0024
 * Time: 15:59
 */
public class MyFilter implements ActionFilter {
    @Override
    public View match(ActionContext actionContext) {
        return null;
    }
}
