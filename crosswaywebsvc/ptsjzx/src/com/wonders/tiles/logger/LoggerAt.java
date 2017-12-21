package com.wonders.tiles.logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.service.Service;

import com.wonders.tiles.logger.annotation.Logger;
import com.wonders.tiles.logger.entity.LoggerInfo;
import com.wonders.tiles.logger.entity.LoggerPro;

@At("/logger")
@IocBean(fields = "dao")
public class LoggerAt extends Service {

	@At
	@Ok("jsp:jsp.logger.about")
	public void about() {
	}

	@At("/list")
	@Ok("jsp:jsp.logger.list")
	public Map<String, Object> getData(Criteria criteria, Pager pager) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 5.结果查询
		List<LoggerInfo> list = dao().query(LoggerInfo.class, criteria, pager);
		// 6.回传数据
		pager.setRecordCount(dao().count(LoggerInfo.class, criteria));
		result.put("list", list);
		result.put("pager", pager);
		return result;
	}

	@At
	@Ok("json")
	@Logger(msg = "简单log")
	public Map<Integer, Integer> testLog1(@Param("num") int num) {
		Map<Integer, Integer> dataMap = getRandomMap(num);
		return dataMap;
	}

	@At
	@Ok("json")
	@Logger(msg = "扩展log")
	public Map<Integer, Integer> testLog2(LoggerPro loggerPro, @Param("num") int num) {
		Map<Integer, Integer> dataMap = getRandomMap(num);
		loggerPro.appendProcessMsg("生成了" + num + "个随机数");
		return dataMap;
	}

	@At
	@Ok("json")
	@Logger(msg = "异常log")
	public int testLog3() throws Exception{
		int i = 3/0;
		return i;
	}

	private Map<Integer, Integer> getRandomMap(int num) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < num; i++) {
			map.put(i, new Random().nextInt(100));
		}
		return map;
	}
}
