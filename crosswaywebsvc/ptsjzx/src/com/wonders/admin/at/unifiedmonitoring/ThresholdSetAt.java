package com.wonders.admin.at.unifiedmonitoring;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.admin.service.unifiedmonitoring.ThresholdSetService;
import com.wonders.admin.util.ResultRules;

/**
 * 统一监控阀值设置的请求处理.
 * 
 * @author Gray
 *
 */
@IocBean
@At("/admin/uniMon")
public class ThresholdSetAt {

	// 注入阀值设置的业务层.
	@Inject("refer:thresholdSetServiceImpl")
	private ThresholdSetService alService;
	
	/**
	 * 阀值设置跳转.
	 * 
	 * @return View 返回页面,并携带值.
	 */
	@At("/toThrSet")
	public View toThresholdValueSet() {
		// 获取数据库中阀值的数据.
		ResultRules result = alService.getAllThreshold();
		// 返回页面,并携带值
		return new ViewWrapper(new JspView("jsp.admin.uniMon.threshold_set"), result);
	}

	/**
	 * 通过指定的
	 * @param id
	 *            唯一ID值.
	 * @param value
	 *            设置值.
	 * @param level
	 *            设置等级.
	 * @return 规定的规则.
	 */
	@At("/alarmListTurn")
	public ResultRules updateThresholdSet(Integer id, String threshold, Integer alarmLevel,HttpSession session) {
		// 获取返回值
		ResultRules result = alService.updateThreshold(id, threshold, alarmLevel, session);
		// 返回成功状态
		return result;
	}
	

}
