package com.wonders.admin.at;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.view.UTF8JsonView;
import org.nutz.mvc.view.ViewWrapper;

/**
 * 用于数据资源交互菜单的跳转请求处理.
 * @author Gray
 */
@IocBean
@At("/admin/datResExc")
public class DataResourceExchangeAt {

	//注入Dao
	@Inject
	private Dao dao;
	
	/**
	 * 交换管理跳转.
	 * @return
	 */
	@At("/toExcMan")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toExchangeManage(){
		return;
	}
	
	/**
	 * 交换监督跳转.
	 */
	@At("/toExcSup")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toExchangeSupervies(){
		return;
	}
}
