package com.wonders.wdac.at;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.wdac.entity.TagInfoBo;
import com.wonders.wddc.tiles.sn.SnCreator;
import com.wonders.wddc.tiles.tools.ExcelUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author wonders
 * 数据标签管理
 */
@IocBean
@At("/wdac/sjbq")
public class SjbqAct {
	@Inject
	private Dao dao;
	
	
	/**
	 * @return
	 * 标签列表首页
	 */
	@At
	@Ok("jsp:wdac.dataDeal.sjbq.index")
	public Map<String, Object> toIndex(){
		Map<String, Object> result =new HashMap<String, Object>();
		List<TagInfoBo> list = dao.query(TagInfoBo.class, Cnd.where("t_status","=","1"));
		result.put("list", list);
		return result;
		
	}
	
	/**
	 * @param id
	 * @return
	 * 标签详情
	 */
	@At
	@Ok("jsp:wdac.dataDeal.sjbq.taginfo")
	public Map<String, Object> toTagInfo(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		TagInfoBo tagInfo = dao.fetch(TagInfoBo.class,id);
		List<TagInfoBo> childtag = dao.query(TagInfoBo.class, Cnd.where("tag_number","like",tagInfo.getTagNumber()+"%").and("tag_level", "=", tagInfo.getLevel()+1));
		result.put("taginfo", tagInfo);
		result.put("childtag", childtag);
		return result;
		
	}
	
	/**
	 * @param taginfo
	 * @return
	 * 保存新增标签
	 */
	@At
	public View addTag(@Param("::taginfo.")TagInfoBo taginfo){
		//修改
	    if(!Strings.isEmpty(taginfo.getId())){
	    	TagInfoBo info = dao.fetch(TagInfoBo.class,taginfo.getId());
	    	info.setTagName(taginfo.getTagName());
	    	info.setTagType(taginfo.getTagType());
	    	info.setTagProperty(taginfo.getTagProperty());
	    	info.setTagDesc(taginfo.getTagDesc());
	    	int count = dao.count(TagInfoBo.class,Cnd.where("tag_number", "like", info.getTagNumber()+"%"));
	    	//判断是否有下级标签
	    	//没有下级标签，可以修改级别信息
	    	if(count<=1){	    		
	    		TagInfoBo pretag = dao.fetch(TagInfoBo.class,taginfo.getPreTagId());
	    		info.setPreTagName(pretag.getPreTagName());
	    		//标签级别发生变化
	    		if(info.getLevel()!= taginfo.getLevel()){
	    			if(1==taginfo.getLevel()){
	    				info.setTagNumber(SnCreator.getInstance().getSN("tagNum_"+info.getTagType(), 2, info.getTagType()));
	    			}else{
	    				info.setTagNumber(SnCreator.getInstance().getSN("tagNum_"+pretag.getTagNumber(), 3, pretag.getTagNumber()));
	    			}
	    			
	    		}else if(!info.getPreTagId().equals(taginfo.getPreTagId())){//级别无变化，上级标签变化
	    			info.setTagNumber(SnCreator.getInstance().getSN("tagNum_"+pretag.getTagNumber(), 3, pretag.getTagNumber()));
	    		}
	    		info.setLevel(taginfo.getLevel());
	    		info.setPreTagId(taginfo.getPreTagId());
	    		info.setPreTagName(pretag.getTagName());
	    	}
	    	info.setLastUpdate(new Date());
	    	dao.update(info);
	    }else{//新增
	    	taginfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			if (1==taginfo.getLevel()) {
				taginfo.setTagNumber(SnCreator.getInstance().getSN("tagNum_"+taginfo.getTagType(), 2, taginfo.getTagType()));
			}else{
				TagInfoBo pretaginfo = dao.fetch(TagInfoBo.class,taginfo.getPreTagId());
				taginfo.setPreTagName(pretaginfo.getTagName());
				taginfo.setTagNumber(SnCreator.getInstance().getSN("tagNum_"+pretaginfo.getTagNumber(), 3,pretaginfo.getTagNumber()));
			}
			taginfo.settDo(0);
			taginfo.settAccess(0);
			taginfo.settStatus("1");
			taginfo.setCreateDate(new Date());
			taginfo.setLastUpdate(new Date());
			dao.insert(taginfo);
	    }
		
		return new ServerRedirectView("/wdac/sjbq/toIndex");
		
	}
	
	/**
	 * @param id
	 * 删除标签
	 */
	@At
	public void delTaginfo(String id){
		TagInfoBo taginfo = dao.fetch(TagInfoBo.class,id);
		taginfo.settStatus("0");
		dao.update(TagInfoBo.class,Chain.make("t_status", "0"),Cnd.where("tag_number", "like", taginfo.getTagNumber()+"%"));
		dao.update(taginfo);
	}
	
	/**
	 * 跳转导入页面
	 */
	@At
	@Ok("jsp:wdac.dataDeal.sjbq.import")
	public Map<String, Object> toImport(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", id);
		return result;
		
	}
	
	/**
	 * @param file
	 * @param preid 上级标签id
	 * @return
	 * 标签批量导入
	 */
	@SuppressWarnings("deprecation")
	@At
	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/wdac/upload/" })
	public View importTags(TempFile file,String preid){
		TagInfoBo preTag = dao.fetch(TagInfoBo.class,preid);
		ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(file.getFile(),2);
		for(int i = 0 ;i < result.size() ;i++){
			List<Object> list = result.get(i);			
		    if(!Strings.isEmpty(list.get(0).toString().trim())){
		    	TagInfoBo info = new TagInfoBo();
		    	info.setTagName(list.get(0).toString());
				info.setTagType(list.get(1).toString());
				info.setTagProperty(list.get(2).toString());
				info.setTagDesc(list.get(3).toString());
				info.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				info.setPreTagId(preTag.getId());
				info.setLevel(preTag.getLevel()+1);
				info.setPreTagName(preTag.getTagName());
				info.setTagNumber(SnCreator.getInstance().getSN("tagNum_"+preTag.getTagNumber(), 3,preTag.getTagNumber()));
				info.setCreateDate(new Date());
				info.setLastUpdate(new Date());
				info.settDo(0);
				info.settAccess(0);
				info.settStatus("1");
				dao.insert(info);
		    }
								
		}
		return new ServerRedirectView("/wdac/sjbq/toIndex");
	}
	
	/**
	 * @param id
	 * @return
	 * 新增页面
	 */
	@At
	@Ok("jsp:wdac.dataDeal.sjbq.addindex")
	public TagInfoBo toAddIndex(String id){
		if(!Strings.isEmpty(id)){
			TagInfoBo infoBo =dao.fetch(TagInfoBo.class,id);
			return infoBo;
		}

		return null;
	}
	
	/**
	 * @param id
	 * @return
	 * 上下级标签
	 */
	@At
	@Ok("json")
	public Map<String, Object> dicSelect(int id){
		id--;
		Sql sql =Sqls.create("select id,tag_name from sjcl_tag where tag_level = '"+id+"'");
		sql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				Map<String, Object> map =new HashMap<String, Object>();
				while(rs.next()){
					map.put(rs.getString(1), rs.getString(2));
				}
				return map;
			}
		});
		dao.execute(sql);
		Map<String, Object> result = (Map<String, Object>) sql.getResult();
		return result;
	}
	
	/**
	 * @param id
	 * @return
	 * 判断是否含有下级标签
	 */
	@At
	@Ok("json")
	public int hasChild(String id){
		TagInfoBo infoBo =dao.fetch(TagInfoBo.class,id);
		int count = dao.count(TagInfoBo.class,Cnd.where("tag_number", "like", infoBo.getTagNumber()+"%"));
		return count;	
	}
	
	/**
	 * @return
	 * 标签总量统计首页
	 */
	@At
	@Ok("jsp:wdac.dataDeal.sjbq.bqzltj")
	public Map<String, Object> tobqtjIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, List<TagInfoBo>> data = new HashMap<String, List<TagInfoBo>>();
		List<TagInfoBo> list = dao.query(TagInfoBo.class, Cnd.where("t_status","=","1").and("tag_level","=",1));
		List<TagInfoBo> taglist = dao.query(TagInfoBo.class, Cnd.where("t_status","=","1").and("tag_level",">",1));
		for(TagInfoBo info : taglist){
			if(data.containsKey(info.getPreTagId())){
				data.get(info.getPreTagId()).add(info);
			}else{
				List<TagInfoBo> child = new ArrayList<TagInfoBo>();
				child.add(info);
				data.put(info.getPreTagId(), child);
			}
		}
		List<Map<String, Object>> list1 =new ArrayList<Map<String,Object>>();
		for(TagInfoBo info:list){
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list2 =new ArrayList<Map<String,Object>>();
			map.put("name", info.getTagName());
			if(data.containsKey(info.getId())&&data.get(info.getId()).size()>0){
				for(TagInfoBo child2:data.get(info.getId())){
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("name", child2.getTagName());
					List<Map<String, String>> list3 = new ArrayList<Map<String,String>>();
					if(data.containsKey(child2.getId())&&data.get(child2.getId()).size()>0){
						for(TagInfoBo child3:data.get(child2.getId())){
							Map<String, String> map3 =new HashMap<String, String>();
							if(data.get(child3.getId())==null){
								map3.put("name", child3.getTagName()+getStr(0));
							}else{
								map3.put("name", child3.getTagName()+getStr(data.get(child3.getId()).size()));
							}
							list3.add(map3);
						}
					}
					map2.put("children", list3);
					list2.add(map2);
				}
			}
			map.put("children", list2);
			list1.add(map);
			info.setLevel(dao.count(TagInfoBo.class,Cnd.where("t_status","=","1").and("tag_level","=",2).and("tag_number","like",info.getTagNumber()+"%")));
			info.settAccess(dao.count(TagInfoBo.class,Cnd.where("t_status","=","1").and("tag_level","=",3).and("tag_number","like",info.getTagNumber()+"%")));
			info.settDo(dao.count(TagInfoBo.class,Cnd.where("t_status","=","1").and("tag_level","=",4).and("tag_number","like",info.getTagNumber()+"%")));
		}
		result.put("data", JSONArray.fromObject(list1));
		result.put("father", list);
		return result;
		
	}
	
	/**
	 * @return
	 * 标签数据统计首页
	 */
	@At
	@Ok("jsp:wdac.dataDeal.sjbq.bqsltj")
	public Map<String, Object> tobqsjIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, List<TagInfoBo>> data = new HashMap<String, List<TagInfoBo>>();
		List<TagInfoBo> list = dao.query(TagInfoBo.class, Cnd.where("t_status","=","1").and("tag_level","=",1));
		List<TagInfoBo> taglist = dao.query(TagInfoBo.class, Cnd.where("t_status","=","1").and("tag_level",">",1).and("tag_level", "<",4));
		for(TagInfoBo info : taglist){
			if(data.containsKey(info.getPreTagId())){
				data.get(info.getPreTagId()).add(info);
			}else{
				List<TagInfoBo> child = new ArrayList<TagInfoBo>();
				child.add(info);
				data.put(info.getPreTagId(), child);
			}
		}
		List<Map<String, Object>> list1 =new ArrayList<Map<String,Object>>();
		for(TagInfoBo info:list){
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list2 =new ArrayList<Map<String,Object>>();
			map.put("name", info.getTagName()+getStr(info.gettDo()));
			if(data.containsKey(info.getId())&&data.get(info.getId()).size()>0){
				for(TagInfoBo child2:data.get(info.getId())){
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("name", child2.getTagName()+getStr(child2.gettDo()));
					List<Map<String, String>> list3 = new ArrayList<Map<String,String>>();
					if(data.containsKey(child2.getId())&&data.get(child2.getId()).size()>0){
						for(TagInfoBo child3:data.get(child2.getId())){
							Map<String, String> map3 =new HashMap<String, String>();
							map3.put("name", child3.getTagName()+getStr(child3.gettDo()));
							list3.add(map3);
						}
					}
					map2.put("children", list3);
					list2.add(map2);
				}
			}
			map.put("children", list2);
			list1.add(map);
		}
		result.put("data", JSONArray.fromObject(list1));
		result.put("father", list);
		return result;
		
	}
	
	/**
	 * @return
	 * 标签热度统计
	 */
	@At
	@Ok("jsp:wdac.dataDeal.sjbq.bqrdtj")
	public Map<String, Object> tobqrdIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<TagInfoBo> taglist = dao.query(TagInfoBo.class, Cnd.where("t_status", "=", "1").desc("t_access"),dao.createPager(1, 10));
		Map<String, Object> map= new HashMap<String, Object>();
		List<String> name = new ArrayList<String>();
		List<Integer> value = new ArrayList<Integer>();
		for(TagInfoBo info:taglist){
			name.add(info.getTagName());
			value.add(info.gettAccess());
		}
		map.put("name", name);
		map.put("value", value);
		map.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		result.put("taglist", taglist);
	    result.put("data", JSONObject.fromObject(map));
		return result;
		
	}
	
	/**
	 * 字符串拼装
	 * @param str
	 * @return
	 */
	public String getStr(int str){
		return "("+str+")";
		
	}
	
}
