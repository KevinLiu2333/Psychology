package com.wonders;

import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.tiles.extend.adaptor.SearchTableAdaptor;
import com.wonders.tiles.extend.setup.ConfigSetup;
import com.wonders.tiles.extend.setup.SessionFilter;


@IocBy(type=ComboIocProvider.class,args={"*org.nutz.ioc.loader.json.JsonLoader","ioc/",
	"*org.nutz.ioc.loader.annotation.AnnotationIocLoader","com.wonders"})
@Modules(scanPackage=true)
@SetupBy(ConfigSetup.class)
@AdaptBy(type = SearchTableAdaptor.class)
@Filters(@By(type=SessionFilter.class))
public class MainModule {

	@At("/index")
	@Ok("jsp:jsp.index")
	public String index(String url) {
		if (Strings.isEmpty(url))
			url = "dwz/user/list";
		return url;
	}
	
	@At("/index/*")
	public View to(String jspName) {
		return new ViewWrapper(new JspView("index-" + jspName), null);
	}
	
	@At("/frame")
	@Ok("jsp:jsp.jh.home")
	public String frame(String url) {
		if (Strings.isEmpty(url))
			url = "dwz/user/list";
		return url;
	}
}
