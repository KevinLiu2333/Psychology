package com.wonders;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.ioc.provider.ComboIocProvider;


@IocBy(type=ComboIocProvider.class,args={"*org.nutz.ioc.loader.json.JsonLoader","ioc/",
	"*org.nutz.ioc.loader.annotation.AnnotationIocLoader","com.wonders"})
@Modules(scanPackage=true)
public class MainModule {

}
