package com.wonders.ws.receive.timer;

import com.wonders.ws.receive.task.KechuangjiaTask;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/1/16
 * Time: 16:15
 */
@IocBean
public class KechuangjiaJob implements Job {

    Ioc ioc = null;
    KechuangjiaTask kechuangjiaTask = null;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        KechuangjiaTask kechuangjiaTask = Mvcs.ctx.getDefaultIoc().get(KechuangjiaTask.class);
        kechuangjiaTask.extract();
    }
}
