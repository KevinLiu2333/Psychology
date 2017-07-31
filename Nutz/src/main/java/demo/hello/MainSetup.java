package demo.hello;

import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/24 0024
 * Time: 17:29
 */
public class MainSetup implements Setup {
    @Override
    public void init(NutConfig nc) {
        Ioc ioc = nc.getIoc(); // 拿到Ioc容器
        Dao dao = ioc.get(Dao.class); // 通过Ioc容器可以拿到你想要的ioc bean

        // 拿到Dao,自然就可以自动建表了
        Daos.createTablesInPackage(dao, "net.wendal.nutzbook.bean", false);
        // 表结构变化了? migration一下
        Daos.migration(dao, "net.wendal.nutzbook.bean", true, false);

        ServletContext sc = nc.getServletContext(); // 也可以拿到容器上下文,为所欲为

//        ioc.get(BackupThread.class).start(); // 当然也可以取出线程对象,然后启动之
    }

    @Override
    public void destroy(NutConfig nc) {
        Ioc ioc = nc.getIoc(); // 可以拿到Ioc容器
        Dao dao = ioc.get(Dao.class);

//        dao.insert(new SysLog("admin", "system shutdown")); // 添加系统日志,记录一下
//        ioc.get(IndexService.class).flush(); // 刷索引
    }
}
