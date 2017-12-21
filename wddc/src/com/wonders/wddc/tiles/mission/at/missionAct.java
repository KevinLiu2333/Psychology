package com.wonders.wddc.tiles.mission.at;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.record.formula.functions.Replace;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.wddc.suite.logger.LogConstants;
import com.wonders.wddc.suite.logger.entity.BaseLog;
import com.wonders.wddc.suite.logger.service.LogCoreService;
import com.wonders.wddc.tiles.adaptor.util.ConUtils;
import com.wonders.wddc.tiles.mission.entity.TaskCompletionBo;
import com.wonders.wddc.tiles.mission.entity.TaskConfigBo;
import com.wonders.wddc.tiles.sn.SnCreator;
import com.wonders.wddc.tiles.tools.DateUtils;

@At("/kernel/mission")
@IocBean
public class missionAct {
	@Inject
	private Dao dao;
	@Inject
	private LogCoreService LogCoreService;
	@At
	@Ok("jsp:wddc.tiles.mission.index")
	public Map<String, Object> toIndex(){
		Map<String, Object> result = new HashMap<String,Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("taskFlag","=",1);
		List<TaskConfigBo> taskList = dao.query(TaskConfigBo.class, cri);
		result.put("taskList", taskList);
		return result;
	}
	
	@At
	@Ok("jsp:wddc.tiles.mission.mission_edit")
	public Map<String, Object> toEdit(String taskId){
		Map<String, Object> result = new HashMap<String,Object>();
		if(!"".equals(taskId)){
			Criteria cri = Cnd.cri();
			cri.where().and("taskId","=",taskId);
			TaskConfigBo taskConfig = dao.fetch(TaskConfigBo.class,cri);
			result.put("TaskConfig", taskConfig);
		}
		return result;
	}
	
	@At
	@Ok("redirect:/kernel/mission/toIndex")
	public void saveConfig(@Param("::TaskConfig") TaskConfigBo TaskConfig){
		if (!"".equals(TaskConfig.getTaskId())) {
			Criteria cri = Cnd.cri();
			cri.where().and("taskId","=",TaskConfig.getTaskId());
			TaskConfigBo taskConfigBo = dao.fetch(TaskConfigBo.class,cri);
			taskConfigBo.setTaskName(TaskConfig.getTaskName());
			taskConfigBo.setTaskDesc(TaskConfig.getTaskDesc());
			taskConfigBo.setTaskFreqDay(TaskConfig.getTaskFreqDay());
			taskConfigBo.setTaskFreqTime(TaskConfig.getTaskFreqTime());
			taskConfigBo.setCreateDate(new Date());
			taskConfigBo.setLogType(TaskConfig.getLogType());
			taskConfigBo.setIsIgnoreWarning(TaskConfig.getIsIgnoreWarning());
			dao.update(taskConfigBo);
		}else{
			TaskConfig.setTaskId(SnCreator.getInstance().getSN("wdtask", 5, "task"));
			TaskConfig.setCreateDate(new Date());
			TaskConfig.setTaskFlag("1");
			dao.insert(TaskConfig);
		}
	}
	/**
	 * 新增新生成任务
	 * @throws ParseException 
	 */
	@At
	public void insertComp(){
		Criteria cri = Cnd.cri();
		cri.where().and("taskFlag","=","1");
		List<TaskConfigBo> taskList = dao.query(TaskConfigBo.class, cri);
		for(TaskConfigBo taskConfig : taskList){
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String taskDate = sdf.format(nowDate);
			DateUtils.string2Date(taskDate, DateUtils.FORMAT_DATE);
		    TaskCompletionBo taskCompletion = new TaskCompletionBo();
		    taskCompletion.setTaskId(taskConfig.getTaskId());
		    taskCompletion.setTaskName(taskConfig.getTaskName());
		    taskCompletion.setTaskSetDay(taskConfig.getTaskFreqDay());
		    taskCompletion.setTaskSetTime(taskConfig.getTaskFreqTime());
		    taskCompletion.setTaskDate(nowDate);
		    if("是".equals(taskConfig.getIsIgnoreWarning())){
		    	taskCompletion.setIsIgnoreWarning("1");
		    }else{
		    	taskCompletion.setIsIgnoreWarning("0");
		    }
		    dao.insert(taskCompletion);
		}
	}
	/**
	 * 每天结算任务
	 * @throws ParseException 
	 */
	@At
	public void checkComp(){
		Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例  
		ca.setTime(new Date()); // 设置时间为当前时间 
	    ca.add(Calendar.DATE, -1);// 日期减1  
	    Date resultDate = ca.getTime(); //结果
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String taskDate = sdf.format(resultDate);
		DateUtils.string2Date(taskDate, DateUtils.FORMAT_DATE);
		Criteria cri = Cnd.cri();
		cri.where().and("taskDate","=",resultDate);
		List<TaskCompletionBo> compList = dao.query(TaskCompletionBo.class, cri);
		for(TaskCompletionBo taskComp : compList){
			Criteria criT = Cnd.cri();
			criT.where().and("taskId","=",taskComp.getTaskId());
			TaskConfigBo taskConfig = dao.fetch(TaskConfigBo.class,criT);
			Date nowDate = new Date();
			Calendar caT = Calendar.getInstance();// 得到一个Calendar的实例  
			caT.setTime(new Date()); // 设置时间为当前时间 
		    caT.add(Calendar.DATE, -Integer.parseInt(taskConfig.getTaskFreqDay()));// 日期减1  
		    Date lastDate = caT.getTime(); //结果
		    Criteria criR = Cnd.cri();
		    criR.where().and("logTime","<=",nowDate);
		    criR.where().and("logTime",">=",lastDate);
		    criR.where().and("logType","=",taskConfig.getLogType());
		    int freqTime = Integer.parseInt(taskConfig.getTaskFreqTime());
		    int actulTime = dao.count(BaseLog.class, criR);
		    String taskActualTime = Integer.toString(actulTime);
		    String logType = taskConfig.getLogType().replaceAll("jh", "jy");
		    taskComp.setTaskActualTime(taskActualTime);
		    if(freqTime >= actulTime){
		    	taskComp.setIsWarning("1");
		    	LogCoreService.insertOpLog("yj", logType, LogConstants.GLY_UNIT_ID, LogConstants.GLY_UNIT_NAME, taskComp.getCompId());
		    }else{
		    	taskComp.setIsWarning("0");
		    }
		    dao.update(taskComp);
		}
	}
	/**
	 * 查看任务进度
	 * @param taskId
	 */
	@At
	public void checkOneComp(String taskId){
		Criteria cri = Cnd.cri();
		cri.where().and("taskId","=",taskId);
		TaskCompletionBo taskComp = dao.fetch(TaskCompletionBo.class,cri);
		Criteria criT = Cnd.cri();
		cri.where().and("taskId","=",taskComp.getTaskId());
		TaskConfigBo taskConfig = dao.fetch(TaskConfigBo.class,criT);
		Date nowDate = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date()); // 设置时间为当前时间 
	    ca.add(Calendar.DATE, -Integer.parseInt(taskConfig.getTaskFreqDay()));// 日期减1  
	    Date lastDate = ca.getTime(); //结果
	    Criteria criR = Cnd.cri();
	    criR.where().and("logTime","<=",nowDate);
	    criR.where().and("logTime",">=",lastDate);
	    criR.where().and("logType","=",taskConfig.getLogType());
	    int actulTime = dao.count(BaseLog.class, criR);
	    String taskActualTime = Integer.toString(actulTime);
	    taskComp.setTaskActualTime(taskActualTime);
	    dao.update(taskComp);
	}
	
	/**
	 * 查看日志.
	 */
	@At
	@Ok("jsp:wddc.tiles.mission.mission_comp")
	public Object toViewComp(HttpServletRequest request) {
		//1.适配页面的查询参数
		Criteria cri =  ConUtils.makeCri(request);
		
		//设置固定参数，可以多个条件查询
		cri.where().and("isWarning","NOT IS",null);
		//2.适配页面的分页参数
		Pager pager =ConUtils.makePaginationPager(request);
		//4.适配页面的排序参数		
		ConUtils.makePaginationOrder(request, cri, "taskDate", "desc");
		//5.结果查询
		//dao.query()为条件查询
		List<TaskCompletionBo> list = dao.query(TaskCompletionBo.class,cri,pager);
		pager.setRecordCount(dao.count(TaskCompletionBo.class,cri));
		//6.回传数据
		Map<String,Object>  result= new HashMap<String,Object>();
		//数据总数
		result.put("total",pager.getRecordCount());
		//数据对象
		result.put("rows", list);
		//分页信息
        result.put("pager",pager);
		return result; 
	}
}

