package com.wonders.admin.at;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * 用于统一监控菜单的跳转请求处理.
 * 
 * @author Gray
 */
@IocBean
@At("/admin/uniMon")
public class UnifiedMonitoringAt {
	
	//安全审计跳转
	@At("/toSecAud")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toSecurityAudit(){
		return;
	}
	
	//系统环境监控跳转
	@At("/toEnvMonSys")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toEnvironmentalMonitoringSystem(){
		return;
	}
	
	//交换管理跳转
	@At("/toExcMan")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toExchangeManagement(){
		return;
	}
	
	//运行管理跳转
	@At("/toOpeMan")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toOperationManagement(){
		return;
	}
	
	//日志管理跳转
	@At("/toLogMan")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toLogManagement(){
		return;
	}
	
	//巡检管理跳转
	@At("/toInsMan")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toInspectionManagement(){
		return;
	}
	
	//系统管理跳转
	@At("/toSysMan")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toSystemManagement(){
		return;
	}
	
	//备份恢复管理跳转
	@At("/toBacRecMan")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toBackupRecoveryManagement(){
		return;
	}
}
