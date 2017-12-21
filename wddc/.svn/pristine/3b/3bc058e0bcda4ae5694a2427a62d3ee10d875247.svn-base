package com.wonders.wdac.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.wdac.entity.SuiteTableConfig;
import com.wonders.wddc.suite.csrq.entity.ColumnConfigBo;
import com.wonders.wddc.tiles.tag.entity.TagInfoBo;

@At("/cj")
@IocBean(fields = "dao")
public class CjAct {

	private Dao dao;
	
	@At("/sjcj")
	@Ok("jsp:wdac.cj.table_info")
	public Map<String,Object> sjcj(String keyWord){
		Map<String,Object> result = new HashMap<String,Object>();
		Criteria cri = Cnd.cri();
		cri.getOrderBy().asc("themeId");
		cri.where().and("catalog","=","2");
		if(keyWord != null){
			cri.where().andLike("name", keyWord).orLike("description", keyWord);
			result.put("keyWord", keyWord);
		}
		Criteria criTag = Cnd.cri();
		criTag.where().and("catalog","=","15");
		List<TagInfoBo> tagList = dao.query(TagInfoBo.class, criTag);
		List<SuiteTableConfig> list = dao.query(SuiteTableConfig.class, cri);
		result.put("tableList", list);
		result.put("tagList",tagList);
		return result;
	}
	
	@At("/wjsc")
	@Ok("jsp:wdac.cj.file_upload")
	public void wjsc(){
		
	}
	
	@At
	@Ok("jsp:wdac.cj.table_info")
	public Map<String,Object> infoFilter(String filterName){
		Map<String,Object> result = new HashMap<String,Object>();
		Criteria cri = Cnd.cri();
		cri.getOrderBy().asc("themeId");
		cri.where().and("catalog","=","2");
		cri.where().and("source","=",filterName);
		Criteria criTag = Cnd.cri();
		criTag.where().and("catalog","=","15");
		List<TagInfoBo> tagList = dao.query(TagInfoBo.class, criTag);
		List<SuiteTableConfig> list = dao.query(SuiteTableConfig.class,cri);
		result.put("tableList", list);
		result.put("tagList",tagList);
		return result;
	}
	
	@At
	@Ok("jsp:wdac.cj.column_info")
	public Map<String,Object> columnInfo(String themeId){
		Map<String,Object> result = new HashMap<String,Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("themeId","=",themeId);
		List<ColumnConfigBo> columnList = dao.query(ColumnConfigBo.class, cri);
		result.put("columnList",columnList);
		return result;
	}
}
