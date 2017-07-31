package com.wondersgroup.cmc.quartz.dispatcher;

import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.WebApplicationContext;

import com.wondersgroup.cmc.model.bo.BlQuartz;
import com.wondersgroup.cmc.model.bo.QrtzTriggerConfig;
import com.wondersgroup.cmc.model.bo.QrtzTriggerConfigRelevance;
import com.wondersgroup.cmc.quartz.utils.QrtzTriggerConfigUtils;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;

public class MyQuartzJobBean extends QuartzJobBean{
	private static final Logger logger = Logger.getLogger(MyQuartzJobBean.class);
	private static final String APPLICATION_CONTEXT_KEY = "applicationContext";

	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		WebApplicationContext webApplicationContext = null;
		try {
			webApplicationContext = (WebApplicationContext) jobexecutioncontext.getScheduler().getContext().get(APPLICATION_CONTEXT_KEY);
		} catch (SchedulerException e) {
			logger.error("获取WebApplicationContext异常:"+e.getMessage());
			e.printStackTrace();
			return;
		}
		
		logger.info("触发器触发时间："+ System.currentTimeMillis());
		//如果为空，则标识系统可能正在启动（目前测试来看只有在项目正在启动过程中才会报空）或者其他原因
		if(null == webApplicationContext) {
			logger.error("获取WebApplicationContext为空，调度任务将不会被执行。");
            return;
        }

		//暂不校验是否开启调度
		boolean checkSystemParam = true;
		if (!checkSystemParam){
			logger.error("触发器系统参数状态为关闭，调度任务将不会被执行。");
            return;
		}
		
		//开始调度
		Trigger trigger = jobexecutioncontext.getTrigger();
		String triggerName = trigger.getName();
		logger.info("触发调度名称：" + triggerName);
		
		QrtzTriggerConfigUtils qrtzTriggerConfigUtils = null;
		try {
			qrtzTriggerConfigUtils = (QrtzTriggerConfigUtils) webApplicationContext.getBean("qrtzTriggerConfigUtils");
		} catch (Exception e) {
			logger.error("初始化触发器bean发生异常："+ e.getMessage());
			e.printStackTrace();
            return;
		}
		
		//查询任务对象
		QrtzTriggerConfig qrtzTriggerConfig = qrtzTriggerConfigUtils.findBeanByTriggerName(triggerName);
		if (null == qrtzTriggerConfig) {
			return;
		}
        
		//校验IP
		boolean checkIp = true;
		//暂不校验IP
		//checkIp = checkHostIp(qrtzTriggerConfig.getExt2());
		// 如果不是符合要求的主机，则直接返回
		if (!checkIp){
			logger.error("执行调度任务的主机为非法IP，调度任务将不会被执行。");
            return;
        }
		
		//保存调度日志
		BlQuartz blQuartz = new BlQuartz();
		blQuartz.setTriggerTime(new Date());	//触发时间
		blQuartz.setTriggerTaskName(qrtzTriggerConfig.getBeanDesc());	//触发任务名称
		QrtzTriggerConfigRelevance qrtzTriggerConfigRelevance = qrtzTriggerConfigUtils.findTriggerByTriggerName(triggerName);
		blQuartz.setRelationId(qrtzTriggerConfigRelevance.getId().toString());	// 关联调度和任务配置中间表
		blQuartz.setTriggerTaskDesc(qrtzTriggerConfig.getNotes());		//触发任务描述
		blQuartz.setCreateDate(new Date());
		CommonHibernateDaoUtils.save(blQuartz);
		
		//初始化调度参数
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("contactId", blQuartz.getId() + "");
		String jsonString = qrtzTriggerConfig.getExt1();
		if (!StringUtils.isEmpty(jsonString)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			Iterator<?> keyIter = jsonObject.keys();
			String key;
			Object value;
			Map<String, Object> map = new HashMap<String, Object>();
			while (keyIter.hasNext()) {
				key = (String) keyIter.next();
				value = jsonObject.get(key);
				map.put(key, value);
			}
			filter.putAll(map);
		}
		Object obj = null;
		try {
			obj = webApplicationContext.getBean(qrtzTriggerConfig.getBeanName());
			if(obj == null){
				throw new Exception("未查询到["+qrtzTriggerConfig.getBeanName()+"]对应的实体");
			}
		} catch (Exception e) {
			logger.error("初始化调度服务bean发生异常："+ e.getMessage());
			blQuartz.setNotes("初始化调度服务bean发生异常："+ e.getMessage());
			CommonHibernateDaoUtils.update(blQuartz);
			e.printStackTrace();
            return;
		}
		try {
			logger.info("执行调度方法开始...");
			qrtzTriggerConfigUtils.dispatcher(obj, filter);
		} catch (IllegalArgumentException e) {
			logger.error("调度失败："+ e.getMessage());
			blQuartz.setNotes("调度失败："+ e.getMessage());
			CommonHibernateDaoUtils.update(blQuartz);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.error("调度失败："+ e.getMessage());
			blQuartz.setNotes("调度失败："+ e.getMessage());
			CommonHibernateDaoUtils.update(blQuartz);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.error("调度失败："+ e.getMessage());
			blQuartz.setNotes("调度失败："+ e.getMessage());
			CommonHibernateDaoUtils.update(blQuartz);
			e.printStackTrace();
		} catch (SecurityException e) {
			logger.error("调度失败："+ e.getMessage());
			blQuartz.setNotes("调度失败："+ e.getMessage());
			CommonHibernateDaoUtils.update(blQuartz);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			logger.error("调度失败："+ e.getMessage());
			blQuartz.setNotes("调度失败："+ e.getMessage());
			CommonHibernateDaoUtils.update(blQuartz);
			e.printStackTrace();
		}
		
		logger.info("触发器触发完成时间："+ System.currentTimeMillis());
	}
	
	//校验主机ip
	@SuppressWarnings("unused")
	private boolean checkHostIp(String ipStr) {
		// 如果是空，表示不做限定，直接返回true,
		if (StringUtils.isEmpty(ipStr)){
			return true;
		}
		
		try {
			//获取当前运行主机IP
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress();
			String[] ips = ipStr.split(";");
			for (String string : ips) {
				 if (string.equals(ip)){
					 return true;
				 }
			}
		} catch (UnknownHostException e) {
			logger.error("未知主机错误："+ e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
