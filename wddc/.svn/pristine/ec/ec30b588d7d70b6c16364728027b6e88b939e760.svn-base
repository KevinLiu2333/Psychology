package com.wonders.wddc.suite.logger.at;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.wddc.suite.logger.LogManager;
import com.wonders.wddc.suite.logger.entity.OpLogConfig;
import com.wonders.wddc.suite.logger.service.LogCoreService;
import com.wonders.wddc.tiles.tools.ClassUtil;

@At("/suite/log")
@IocBean
public class LogAct {
	
	@Inject
	private Dao dao;
	
	@Inject
	private LogCoreService logService;
	/**
	 * 日志配置列表页面.
	 * 
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.logger.config_list")
	public Map<String, Object> configList() {
		Map<String, Object> result = new HashMap<String, Object>();
        Criteria cri = Cnd.cri();
        List<OpLogConfig> configList = dao.query(OpLogConfig.class, cri);
        result.put("configList",configList);
		return result;
	}

    /**
	 * 进入日志配置修改页面.
	 * @param logConfigId
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.logger.config_edit")
		public Map<String, Object> toConfigEdit(String logConfigId){
		Map<String, Object> result = new HashMap<String, Object>();
		if(!Strings.isEmpty(logConfigId)){
			OpLogConfig opLogConfig = dao.fetch(OpLogConfig.class, logConfigId);
	        result.put("config",opLogConfig);
			
		}
		return result;
	}

	@At
	@Ok("json")
	public Object getClassDic(){
		Map<String, String> result = new HashMap<String, String>();
	    List<Class> jobclses =LogManager.getInstance().jobclses;
		for(Class classObj:jobclses){
			result.put(classObj.getName(),classObj.getName());
		}
		return result;
	}
	
	@At
	@Ok("json")
	public Object getMethodDic(String className){
		Map<String, String> result = new HashMap<String, String>();
		List<Method> methodList = ClassUtil.getMethodByClassName(className);
		for(Method method:methodList){
			result.put(method.getName(),method.getName());
		}
		return result;
	}
	
	/**
	 * 新增，配置修改功能的保存操作.
	 */
	
	@At
    @Ok("redirect:/suite/log/configList")
	public void saveConfig(@Param("::config.") OpLogConfig opLogConfig){
		if(Strings.isEmpty(opLogConfig.getLogConfigId())){
			dao.insert(opLogConfig);
		}else{
			dao.update(opLogConfig);
		}

	}
	/**
	 * 删除，配置修改功能的删除操作.
	 */
	
	@At
    @Ok("redirect:/suite/log/configList")
	public void delConfig(String logConfigId){
		if(Strings.isNotBlank(logConfigId)){
			dao.delete(OpLogConfig.class, logConfigId);
		}

	}
	
	/**
	 * 服务代码查看.
	 * 
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.logger.code")
	public Map<String, Object> viewCode() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	/**
	 * 记录日志操作--对外公开.
	 * 
	 * @return
	 */
	@At
	@Ok("json")
	public Map<String, Object> logOp(HttpServletRequest request, String catalog,String logType,String detailLogId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", "1");
		try{
			this.logService.insertOpLog(catalog, logType,detailLogId, request);
		}catch(Exception e){
			result.put("success", "0");
		}
		return result;
	}

}
