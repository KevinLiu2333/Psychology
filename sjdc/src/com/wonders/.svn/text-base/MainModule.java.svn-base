package com.wonders;

import org.nutz.mvc.View;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.wddc.config.ConfigSetup;
import com.wonders.wddc.tiles.adaptor.SearchTableAdaptor;


@IocBy(type=ComboIocProvider.class,args={"*org.nutz.ioc.loader.json.JsonLoader","ioc/",
	"*org.nutz.ioc.loader.annotation.AnnotationIocLoader","com.wonders"})
@Modules(scanPackage=true)
@AdaptBy(type = SearchTableAdaptor.class)
@SetupBy(ConfigSetup.class)
public class MainModule {

	@At("/to/*")
	public View to(String jspPath)
	{
		return new ViewWrapper(new JspView("jsp."+jspPath), null);

	}
	@At("/sn/*")
	public View sn(String jspPath)
	{
		return new ViewWrapper(new JspView("jsp.sn."+jspPath), null);

	}
	@At("/into")
	public View into(String jspPath){
		return new ViewWrapper(new JspView("ypsjzx."+jspPath), null);
	}
}
