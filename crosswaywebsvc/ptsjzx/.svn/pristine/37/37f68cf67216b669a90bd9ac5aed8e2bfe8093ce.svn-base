package com.wonders.admin.at.unifiedmonitoring;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.admin.service.unifiedmonitoring.AlarmListService;
import com.wonders.admin.util.ResultRules;

/**
 * 统一监控菜单中报警列表请求处理.
 * 
 * @author Gray
 *
 */
@IocBean
@At("/admin/uniMon")
public class AlarmListAt {
	
	// 注入报警列表的业务层.
	@Inject("refer:alarmListServiceImpl")
	private AlarmListService alService;
	
	/**
	 * 报警列表跳转.
	 * 
	 * @return View 返回页面,并携带值.
	 */
	@At("/toAlarmList")
	public View toAlarmList() {
		// 获取第一页的内容
		ResultRules result = alService.getAlarmListByPage(1);
		// 返回页面,并携带值
		return new ViewWrapper(new JspView("jsp.admin.uniMon.alarm_list"), result);
	}
}
