package com.wonders;

import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.wonders.wddc.config.ConfigSetup;
import com.wonders.wddc.config.OpFilter;
import com.wonders.wddc.tiles.adaptor.SearchTableAdaptor;

@IocBy(type=ComboIocProvider.class,args={"*org.nutz.ioc.loader.json.JsonLoader","ioc/",
	"*org.nutz.ioc.loader.annotation.AnnotationIocLoader","com.wonders"})
@Modules(scanPackage=true)
@AdaptBy(type = SearchTableAdaptor.class)
@Filters({@By(type = OpFilter.class)})
@SetupBy(ConfigSetup.class)
public class MainModule {

}
