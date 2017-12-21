package com.wonders.tiles.tools;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

/**
 * Ioc单例.
 *
 */
public class IocSingle {
	
	private static Ioc instance = null;
	
	private IocSingle(){
		
	}
	
	public static Ioc getInstance() {
		if(instance==null){
			try {
				synchronized(IocSingle.class){
					if(instance==null){
							instance = new NutIoc(new ComboIocLoader("*org.nutz.ioc.loader.json.JsonLoader", "ioc/",
								"*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.wonders"));
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return instance;
	}
}
