package demo.ioc;

import org.nutz.ioc.loader.annotation.IocBean;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/24 0024
 * Time: 15:25
 */
@IocBean(name = "petM")//构造模块时,会通过ioc容器获取,而不直接调用默认构造函数
public class PetModule {
}
