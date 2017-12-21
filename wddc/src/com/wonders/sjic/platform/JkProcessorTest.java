package com.wonders.sjic.platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.sjic.service.Ret;

@IocBean
public class JkProcessorTest implements JkProcessor{

	@Override
	public List<Map<String, Object>> getParams() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id","213");
		map.put("name","Kevin");
		list.add(map);
		return list;
	}

	@Override
	public String execute(Object arg0) {
		Ret ret = (Ret)arg0;
		if("S".equals(ret.getStatus())){
			List<String> list = (List<String>) ret.getdata();
			System.out.println(list.get(0));
		}
		return "success";
	}

}
