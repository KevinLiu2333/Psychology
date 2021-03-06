package com.wonders.wddc.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.nutz.mvc.annotation.Filters;

import com.wonders.sjfw.platform.alert.AlertManager;
import com.wonders.wddc.suite.csrq.entity.ReportInfoBo;
import com.wonders.wddc.suite.csrq.job.FormTimingJob;
import com.wonders.wddc.suite.logger.LogManager;
import com.wonders.wddc.suite.logger.entity.OpLogConfig;
import com.wonders.wddc.tiles.dic.DicConfigManager;
import com.wonders.wddc.tiles.quartz.QuartzLoader;
import com.wonders.wddc.tiles.quartz.QuartzManager;
import com.wonders.wddc.tiles.sn.SnCreator;
import com.wonders.wddc.tiles.switches.Switches;
import com.wonders.wddc.tiles.switches.entity.SwitchInfoBo;
import com.wonders.wddc.tiles.tools.PropertyUtils;

@Filters
public class ConfigSetup implements Setup{

	private Log log = Logs.get();
	@Override
	public void destroy(NutConfig nc) {
		
	}

	@Override
	public void init(NutConfig nc){
		Dao dao = Mvcs.ctx().getDefaultIoc().get(Dao.class);
		Ioc ioc = nc.getIoc();
		//字典加载
		DicConfigManager manager = ioc.get(DicConfigManager.class);
		manager.loadAllDic();
		//序号取号器
		SnCreator.getInstance().init();
		//定时器加载
		QuartzLoader quartzLoader = ioc.get(QuartzLoader.class);
		quartzLoader.loadAllTask(PropertyUtils.getProperty("project_name"));
		List<ReportInfoBo> reportList = dao.query(ReportInfoBo.class,null);
		for(ReportInfoBo reportBo : reportList){
			String fixedTime = reportBo.getFixedTime();
			if("1".equals(reportBo.getIscache())&&fixedTime != null&&!fixedTime.equals("")){
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("id", reportBo.getId());
				String jobName = "job_"+reportBo.getId();
				try {
					QuartzManager.getInstance().removeJob(jobName);
					QuartzManager.getInstance().addJob(jobName,FormTimingJob.class,fixedTime,param);
					log.info(reportBo.getName()+" 缓存已启动！");
				} catch (Exception e) {
					log.error(reportBo.getName()+" 缓存启动失败",e);
				} 
			}
		}
		//加载日志管理器的配置
		List<OpLogConfig> opLogConfigList = dao.query(OpLogConfig.class, null);
		LogManager.getInstance().loadConfig(opLogConfigList);
		//加载服务已经信息
		AlertManager.getInstance().init();
		//加载开关配置
		List<SwitchInfoBo> switchInfoBos = dao.query(SwitchInfoBo.class, null);
		Switches.getInstance().loadSwitch(switchInfoBos);
	}

}
