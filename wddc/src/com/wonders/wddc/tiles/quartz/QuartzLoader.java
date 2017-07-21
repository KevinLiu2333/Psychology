package com.wonders.wddc.tiles.quartz;

import java.text.ParseException;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.SchedulerException;

import com.wonders.wddc.tiles.quartz.entity.QuartzTaskBo;

@IocBean
public class QuartzLoader {
	private static Log log =Logs.get();
	@Inject
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	public void loadAllTask(String projectName){
		List<QuartzTaskBo> list = dao.query(QuartzTaskBo.class, Cnd.where("current_state","=","1").and("project_name","=",projectName));
		if(list!=null&&list.size()>0){
			for(QuartzTaskBo task : list){
				try {
					Class cls = Class.forName(task.getJobclass());
					QuartzManager.getInstance().addJob(task.getId(), cls , task.getExcutetime());
					log.info(task.getName()+" 已添加到任务队列！");
				} catch (ClassNotFoundException e) {
					log.error(e);
				} catch (SchedulerException e) {
					log.error(e);
				} catch (ParseException e) {
					log.error(e);
				}
			}
		}
	}
}
