package com.wonders.wddc.tiles.quartz.at;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;
import org.quartz.SchedulerException;

import com.wonders.wddc.tiles.quartz.QuartzManager;
import com.wonders.wddc.tiles.quartz.entity.QuartzTaskBo;
import com.wonders.wddc.tiles.tools.PropertyUtils;
/**
 * 定时器任务at
 * @author vcixp
 *
 */
@IocBean
@At("/kernel/quartz")
public class QuartzAct {

	@Inject
	private Dao dao;
	private Log log = Logs.get();
	/**
	 * 首页
	 * @return
	 */
	@At
	@Ok("jsp:wddc.tiles.quartz.index")
	public Map<String, Object> toIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", dao.query(QuartzTaskBo.class, Cnd.where("project_name", "=", PropertyUtils.getProperty("project_name"))));
		return result;
	}
	/**
	 * 编辑
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:wddc.tiles.quartz.edit")
	public Map<String, Object> toEdit(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("task", dao.fetch(QuartzTaskBo.class,Cnd.where("ID","=",id)));
		List<String> clses = new ArrayList<String>();
		for( Class cls:QuartzManager.jobclses){
			clses.add(cls.getName());
		}
		result.put("dic", clses );
		return result;
	}
	/**
	 * 保存
	 * @param task
	 * @return
	 */
	@At
	public View saveTask(@Param("::task.") QuartzTaskBo task){
		if(Strings.isEmpty(task.getId())){
			task.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			task.setCurrentstate("0");
			task.setProjectname(PropertyUtils.getProperty("project_name"));
			dao.insert(task);
		}else {
			dao.update(task);
		}
		return new ServerRedirectView("/kernel/quartz/toIndex");
	}
	/**
	 * 转换任务状态  state = 1 开启任务、state=0 关闭任务
	 * @param id
	 * @param state
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("json")
	public Object switchTask(String id,String state){
		QuartzTaskBo task = dao.fetch(QuartzTaskBo.class,Cnd.where("id","=",id));
		if(task == null){
			log.error("当前任务已被删除！");
			return "当前任务已被删除！";
		}
		Class cls = null;
		try {
			cls = Class.forName(task.getJobclass());
		} catch (ClassNotFoundException e) {
			log.error("无法找到类！");
			log.error(e);
			return "无法找到类！";
		}
		try {
			if(!Strings.isEmpty(state)&&state.equals("1")){
				QuartzManager.getInstance().addJob(task.getId(), cls, task.getExcutetime());
				task.setCurrentstate(state);
				dao.update(task);
				log.info(task.getName()+" 已添加到任务队列！");
			}
			if(!Strings.isEmpty(state)&&state.equals("0")){
				QuartzManager.getInstance().removeJob(task.getId());
				task.setCurrentstate(state);
				dao.update(task);
				log.info(task.getName()+" 已添加从任务队列中删除！");
			}
		} catch (SchedulerException e) {
			log.error(e);
			return e.getMessage();
		} catch (ParseException e) {
			log.error(e);
			return e.getMessage();
		}
		return "1";
	}
	/**
	 * 删除
	 * @param id
	 */
	@At
	public void deleteTask(String id){
		QuartzTaskBo task = dao.fetch(QuartzTaskBo.class,Cnd.where("id","=",id));
		if(task!=null){
			dao.delete(task);
		}
	}
}
