package demo.hello;

import demo.chain.MyActionChainMaker;
import org.nutz.mvc.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/21 0021
 * Time: 9:27
 */
@ChainBy(type = MyActionChainMaker.class, args = {})
@Localization(value = "msgs/" ,defaultLocalizationKey="zh_CN")//声明本地化字符串位置
//应用启动和关闭时的额外处理
@SetupBy(MainSetup.class)
public class MainModule {


    @At("/hello/?/?")//at中支持路径参数,用?做单层通配符,*做多层通配符
    /**
     *     入口函数，可申明如下注释
     @At - 入口函数对应的 URL 入口函数主要标记，可声明多个URL
     @Ok - 成功视图
     @Fail - 失败视图
     @AdaptBy - HTTP 参数适配方式
     @Filter - 过滤器
     @Encoding - 输入输出编码 默认utf-8
     适配器:四种常用适配器
     @AdaptBy(type = PairAdaptor.class)默认适配器:键值对
     @AdaptBy(type = JsonAdaptor.class)json适配器
     @AdaptBy(type = VoidAdaptor.class)空适配器,不做任何解析
      *     视图支持以下几种
     JSP － 采用 JSP 模板输出网页
     Redirect － 客户端重定向
     Forward － 服务器端中转
     Json － 将对象输出成 Json 字符串
     void - 什么都不做
     Raw - 二进制输出,图片输出,文件下载等
      * 如果占位符名称以 "obj." 开头，则表示应该用入口函数的返回对象的某一个字段来填充
     "obj.name" 表示对象的 "name" 字段
     如果占位符名称以 "p." 开头，，用 HTTP 参数表的值来填充
     "p.abc" 就表示 HTTP 参数中的 "abc" 参数
     如果占位符名称以 "req_attr." 开头，，用 req 的attr的值来填充
     "req_attr.abc" 就表示 req.getAttribute("abc")
     如果占位符名称以 "session_attr." 开头，，用 session 的attr的值来填充
     "session_attr.abc" 就表示 session.getAttribute("abc")
     如果参数表没有值，则用空字符串来填充
     */
    @Ok("jsp:jsp.hello")
    public String doHello(@Param("id") Integer id,
                          @Param("name") String name,
//                          ..表明接受的的对象对应整个http参数表
                          @Param("..") Object object,
//                          甚至可以指定前缀获取参数
                          @Param("::user.") Object obejct2,
//                          同时支持泛型 参数为abc.obj.name时,会自动生成jk对象
                          @Param("::abc.") Abc<jk> abc,
//                          @Attr注解可以获取req或session里的attr
                          @Attr("me") Object object3) {
//        return null;走默认视图
        return "Hello " + name;
    }

    class Abc<T> {
        T obj;
    }

    class jk {
        String name;
    }
}
