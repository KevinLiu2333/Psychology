package com.wonders.tiles.extend.setup;

import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.nutz.mvc.annotation.Filters;

import com.wonders.echarts.timer.EchartsSchedule;
import com.wonders.search.timer.IndexSchedule;
import com.wonders.support.timer.JsonSchedule;
import com.wonders.support.timer.XmlSchedule;
import com.wonders.tiles.dic.DicConfigManager;

@Filters
public class ConfigSetup implements Setup {

	public void init(NutConfig nc) {
		Ioc ioc = nc.getIoc();
		DicConfigManager manager = ioc.get(DicConfigManager.class);
		manager.loadAllDic();
		
		//启动定时器
		XmlSchedule xmlSchedule = ioc.get(XmlSchedule.class);
		xmlSchedule.startSchedule();
		JsonSchedule jsonSchedule = ioc.get(JsonSchedule.class);
		jsonSchedule.startSchedule();
		EchartsSchedule echartsSchedule = ioc.get(EchartsSchedule.class);
		echartsSchedule.startSchedule();
		IndexSchedule indexSchedule = ioc.get(IndexSchedule.class);
		indexSchedule.startSchedule();
	}

	public void destroy(NutConfig nc) {
		Ioc ioc = nc.getIoc();
		
		//停止定时器
		XmlSchedule xmlSchedule = ioc.get(XmlSchedule.class);
		xmlSchedule.shutdownSchedule();
		JsonSchedule jsonSchedule = ioc.get(JsonSchedule.class);
		jsonSchedule.shutdownSchedule();
		EchartsSchedule echartsSchedule = ioc.get(EchartsSchedule.class);
		echartsSchedule.shutdownSchedule();
	}

}
