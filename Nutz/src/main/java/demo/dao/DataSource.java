package demo.dao;

import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.pager.Pager;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/25 0025
 * Time: 8:50
 */
public class DataSource {

    private Dao getDao() {
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://10.2.8.169:3306/wddc");
        dataSource.setUsername("wddc");
        dataSource.setPassword("wddc");
        // 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
        Dao dao = new NutDao(dataSource);
        return dao;
    }

    @Test
    public void createTable() {
        Dao dao = getDao();
        // 创建表
        dao.create(Ceshi.class, false); // false的含义是,如果表已经存在,就不要删除重建了.
        Ceshi c = new Ceshi();
        c.setName("ABC");
        c.setSex("男");
        c.setAge(25);
        dao.insert(c);
        //对象的自增字段会被自动更新
        System.out.println(c.getId());
    }

    @Test
    public void crud() {
        Dao dao = getDao();
//        dao.drop(Ceshi.class);无条件全部删除
//        insert
        Ceshi ceshi = new Ceshi();
        ceshi.setAge(18);
        ceshi.setSex("女");
        ceshi.setName("玲玲");
        dao.insert(ceshi);
        System.out.println(ceshi.getId());
//        Fetch
//        byId数值型主键
        ceshi = dao.fetch(Ceshi.class, ceshi.getId());
//        byName字符型主键
        ceshi = dao.fetch(Ceshi.class, "玲玲");
//        Update
        ceshi.setAge(20);
        dao.update(ceshi);
        dao.update(ceshi, "^age$");//仅更新age,正则表达式
//        dao.update(list,"^age$");更新一个集合也是可行的
//        根据特定条件更新特定字段
        dao.update(Ceshi.class, Chain.make("age", 19), Cnd.where("age", ">", 150));
        //常用的+1更新
        dao.update(Ceshi.class, Chain.makeSpecial("age", "+1").add("location", "yvr"), Cnd.where("name", "=", "玲玲"));
//        直接删除对象,根据id或name删除都可以
        dao.delete(ceshi);
//        query
        List<Ceshi> list = dao.query(Ceshi.class, null);
//        分页查询 第二页,每页有四条
        List<Ceshi> list2 = dao.query(Ceshi.class, Cnd.where("age", ">", 18), dao.createPager(2, 4));
//        dao.clear(Ceshi.class);无条件清空
        Trans.exec(new Atom() {
            @Override
            public void run() {
//          原子性事务控制
//
            }
        });
    }

    //        将分页信息和查询结果一起返回
    @Test
    public void getPetList() {
        Dao dao = getDao();
        Pager pager = dao.createPager(1, 5);
        List<Ceshi> list = dao.query(Ceshi.class, null, pager);
        pager.setRecordCount(dao.count(Ceshi.class));
        QueryResult queryResult = new QueryResult(list, pager);
    }
}
