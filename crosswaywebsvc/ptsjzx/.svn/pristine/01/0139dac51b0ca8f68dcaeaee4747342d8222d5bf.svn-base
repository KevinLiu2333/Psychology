package com.wonders.admin.at;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.view.ServerRedirectView;

/**
 * 用于资源目录管理菜单的跳转请求处理.
 * @author Gray
 */
@IocBean
@At("/admin/resDirMan")
public class ResourceDirectoryManagementAt {
	
	//注入Dao
	@Inject
	private Dao dao;
	
	/**
	 * 资源目录跳转.
	 */
	@At("/toResDir")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toResourceDirectory(){
		return;
	}
	
	/**
	 * 数据资源跳转.
	 */
	@At("/toDatRes")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toDataResource(){
		return;
	}
	
	/**
	 * 目录浏览跳转.
	 */
	@At("/toDirBro")
	@Ok("jsp:jsp.admin.directory_browse")
	public void toDirectoryBrowse(){
		return;
	}
	
	/**
	 * 目录查询跳转.
	 */
	@At("/toCatQue")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toCatalogQuery(){
		// 跳转
		return;
	}
	
	/**
	 * 统计汇总跳转.
	 */
	@At("/toStaCol")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toStatisticsCollect(){
		return;
	}
}
