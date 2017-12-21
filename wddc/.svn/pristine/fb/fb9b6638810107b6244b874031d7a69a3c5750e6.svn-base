package com.wonders.sjic.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import com.wonders.sjic.entity.InterfaceClassBo;
import com.wonders.sjic.platform.JkProcessor;

@At("/ic")
@IocBean
@Filters
public class IcOpenService {

	@Inject
	private Dao dao;
	
	@Inject
	private ApiService apiService;
	
	@At
	@Ok("raw")
	public String services(String classId,HttpServletRequest request) {
		try {
			InterfaceClassBo classBo = dao.fetch(InterfaceClassBo.class,classId);
			if(classBo==null){
				return "fail";
			}
//			获取执行处理的类
			String classPath = classBo.getClassPath();
			String jkid = classBo.getJkid();
			Class<?> processorClass = Class.forName(classPath);
			JkProcessor processor = (JkProcessor) Mvcs.ctx().getDefaultIoc().get(processorClass);
			List<Map<String,Object>> mapList = processor.getParams(); 
			Ret ret = apiService.getResult(jkid, mapList,request);
			String resultString = processor.execute(ret);
			return resultString;
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	public String services(String classId){
		return this.services(classId, null);
	}
	
}







