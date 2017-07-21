package com.wonders.wdac.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.wdac.entity.SjbdTask;
import com.wonders.wddc.suite.csrq.entity.ColConfigBo;
import com.wonders.wddc.suite.data.entity.TableConfigBo;


@At("/wdac/dataDuibi")
@IocBean(fields = "dao")
public class SjdbAct {

	private Dao dao;
	
	/**
	 * 对比方案设置 - 首页
	 * @return
	 */
	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_index")
	public Map<String, Object> toDuibiIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<SjbdTask> info = dao.query(SjbdTask.class, null);
		result.put("info", info);
		return result;
	}
	
	/**
	 * 对比方案设置 - 删除任务
	 * @param id
	 */
	@At
	public void delectTask(String id){
		dao.delete(SjbdTask.class,id);
	}
	
	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_tjjcrw1")
	public Map<String, Object> toDuibitjjcrw1(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		//id = "DB00001";
		if (id != null && id != "" && id.length() > 0) {
			List<TableConfigBo> list = dao.query(TableConfigBo.class, Cnd.where("DATA_SOURCE_ID","=",id));
			result.put("list", list);
			result.put("dbId", id);
		}else {
			List<TableConfigBo> list = dao.query(TableConfigBo.class, null);
			result.put("list", list);
		}
		return result;
	}
	//
	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_tjjcrw2")
	public Map<String, Object> toDuibitjjcrw2(String themeId){
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<ColConfigBo> info = dao.query(ColConfigBo.class,Cnd.where("THEME_ID","=",themeId) );
		result.put("info", info);
		return result;
	}

	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_tjjcrw3")
	public Map<String, Object> toDuibitjjcrw3(){
		
		return null;
	}
	

	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.bdjgcx")
	public Map<String, Object> toDuibibdjgcx(){
		
		return null;
	}

	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.bdjgtj")
	public Map<String, Object> toDuibibdjgtj(){
		
		return null;
	}

	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_job_gl")
	public Map<String, Object> toJobGli(){
		
		return null;
	}

	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.log")
	public Map<String, Object> toLog(){
		
		return null;
	}

	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_sjnrdb1")
	public Map<String, Object> toDuibisjnrdb1(){
		
		return null;
	}

	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_sjnrdb2")
	public Map<String, Object> toDuibisjnrdb2(){
		
		return null;
	}
	
	@At
	@Ok("jsp:wdac.dataDeal.dataDuibi.duibi_sjnrdb3")
	public Map<String, Object> toDuibisjnrdb3(){
		
		return null;
	}
}
