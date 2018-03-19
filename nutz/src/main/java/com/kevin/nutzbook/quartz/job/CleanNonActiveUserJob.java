package com.kevin.nutzbook.quartz.job;

import com.kevin.nutzbook.bean.User;
import com.kevin.nutzbook.bean.UserProfile;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/14
 * Time: 16:40
 */
@IocBean
public class CleanNonActiveUserJob implements Job {

    private static final Log log = Logs.get();

    @Inject
    protected Dao dao;

    /**
     * 先清除id>10且创建时间大于1天的邮箱未激活或者没填邮箱的用户, 请注意Cnd.exps的用法,构建多条件语句必备
     * 第二段是自定义sql,这里用到参数(转为?号),也用到变量(直接替入)
     * 这里并没有太明显的会抛出异常的情况,所以不必加上事务了.
     * 良好的日志是基本素养哦
     *
     * @param context
     * @throws JobExecutionException
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.debug("clean Non-Active User , start");
        Date deadtime = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L); // 一天, 测试的时候可以改成1小时之类的
        Cnd cnd = Cnd.where("userId", ">", 10).and("createTime", "<", deadtime).and(Cnd.exps("emailChecked", "=", false).or("email", "IS", null));
        int deleted = dao.clear(UserProfile.class, cnd);
        log.debugf("delete %d UserProfile", deleted);

        Sql sql = Sqls.create("delete from $user_table where id > 10 and not exists (select 1 from $user_profile_table where $user_table.id = uid ) and ct < @deadtime");
        sql.vars().set("user_table", dao.getEntity(User.class).getTableName());
        sql.vars().set("user_profile_table", dao.getEntity(UserProfile.class).getTableName());
        sql.params().set("deadtime", deadtime);
        dao.execute(sql);
        log.debugf("delete %d User", sql.getUpdateCount());

        log.debug("clean Non-Active User , Done");
    }
}
