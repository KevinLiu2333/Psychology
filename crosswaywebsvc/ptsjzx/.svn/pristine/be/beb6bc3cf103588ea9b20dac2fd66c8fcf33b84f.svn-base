package com.wonders.admin.at;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.view.ServerRedirectView;

/**
 * 用于法人库应用的请求.
 * 
 * @author Gray
 */
@IocBean
@At("/admin/corLibApp")
public class CorporateLibaryApplyAt {

	// 数据采集跳转
	@At("/toDatCoo")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toDataCoolection() {
		// 跳转
		return;
	}

	// 数据查询跳转
	@At("/toDatQue")
	public View toDataQuery() {
		// XXX代码太多太乱，没时间看，跳转
		return new ServerRedirectView("/config/query/toResult?saveId=1456475836432");
	}

	// 数据报表跳转
	@At("/toDatRep")
	@Ok("forward:/gray/common/in_construction.jsp")
	public void toDataReports() {
		return;
	}

	// 图标分析跳转
	@At("/toLcoAna")
	public View toLconAnalysis() {
		// 跳转
		return new ServerRedirectView("/zx/toFrjbqk");
	}
}
