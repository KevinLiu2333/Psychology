package demo.crossorigin;

import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.filter.CrossOriginFilter;

/**
 * 处理跨域请求
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/24 0024
 * Time: 17:54
 */
public class CrossOrigin {

    @Filters(@By(type = CrossOriginFilter.class))
//    跨域请求过滤器
    public void crossOrigin(){

    }


}


