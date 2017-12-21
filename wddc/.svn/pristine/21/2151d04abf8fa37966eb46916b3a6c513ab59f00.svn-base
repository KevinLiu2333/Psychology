package com.wonders.wddc.tiles.tag.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.wddc.tiles.tag.entity.TagInfoBo;
import com.wonders.wddc.tiles.tag.entity.TagTypeBo;

@At("/kernel/tag")
@IocBean(fields = "dao")
public class TagAct {

	private Dao dao;
	
	/**
     * 获取所有标签信息 孟振乾  
     */
    @At("/mgr/*")
    @Ok("jsp:wddc.tiles.tag.list")
    public  Object toTagList(String catalog){
        //获取所有标签
    	Map<String,Object>  result= new HashMap<String,Object>();
        Criteria cri = Cnd.cri();
        cri.where().and("catalog","=",catalog);
        cri.getOrderBy().asc("orders");
        List<TagInfoBo> tagInfoList = dao.query(TagInfoBo.class, cri);
        //获取标签类型列表
        Criteria cri1 = Cnd.cri();
        cri1.where().and("catalog","=",catalog);
        List<TagTypeBo> tagTypeList = dao.query(TagTypeBo.class, cri1);
        
        result.put("tagInfoList",tagInfoList);
        result.put("tagTypeList",tagTypeList);
        result.put("catalog",catalog);
       
        return result;
    }
	
	/**
     * 增加新的标签类型(包括catalog和type) 同时添加
     * @param tagType 标签类型对象
     */
    @At
    @Ok("redirect:/kernel/tag/mgr/${catalog}")
    public void toAddType(String catalog,String typeName){
    	
    	Criteria cri = Cnd.cri();
    	cri.where().and("typeName", "=", typeName);
    	TagTypeBo type = dao.fetch(TagTypeBo.class, cri);
    	if(type == null){
    		TagTypeBo newType = new TagTypeBo();
    		newType.setCatalog(catalog);
    		newType.setTypeName(typeName);
    		newType.setShowStyle("info");
        	dao.insert(newType);
    	}
    	
    }
    
    @At
    @Ok("redirect:/kernel/tag/mgr/${catalog}")
    public void addTagInfo(String catalog,String typeName,String tagName){
    	
    	Criteria cri = Cnd.cri();
    	cri.where().and("catalog", "=", catalog).and("type", "=", typeName).and("showName", "=", tagName);
    	TagInfoBo tagInfo = dao.fetch(TagInfoBo.class, cri);
    	
    	int maxOrders = maxOrders(catalog, typeName);
    	
    	if(tagInfo == null){
    		TagInfoBo newTag = new TagInfoBo();
    		newTag.setCatalog(catalog);
    		newTag.setShowName(tagName);
    		newTag.setType(typeName);
    		newTag.setOrders(maxOrders+1);
        	dao.insert(newTag);
    	}
    }
    
    @At
    @Ok("redirect:/kernel/tag/mgr/${catalog}")
    public void deleteTagInfo(String catalog ,String tagId){
    	
    	dao.delete(TagInfoBo.class, tagId);
    }
    
    /**
     * 获取某一catalog的type的标签的最大orders
     */
    public int maxOrders(String catalog,String typeName){
    	
    	int orders = 0;
    	Criteria cri = Cnd.cri();
    	cri.where().and("catalog", "=", catalog).and("type", "=", typeName);
    	cri.getOrderBy().desc("orders");
    	List<TagInfoBo> list = dao.query(TagInfoBo.class, cri);
    	if (null != list && list.size() > 0) {
    		 orders = (int)list.get(0).getOrders();
    	}
    	return orders;
    }
    
    /**
     * 标签向前移动
     */
    @At
    @Ok("redirect:/kernel/tag/mgr/${catalog}")
    public void movePosition(String catalog,String tagId,String position){
    	//1.获取该标签的orders
    	Criteria cri = Cnd.cri();
    	cri.where().and("catalog", "=", catalog).and("tagId", "=", tagId);
    	TagInfoBo currTag = dao.fetch(TagInfoBo.class, cri);
    	int tagOrders = currTag.getOrders();
    	String tagType = currTag.getType();
    	//2.判断当前标签orders是否大于1
    	
    		if(tagOrders >1){
    			if("forward".equals(position)){
        			Criteria newCri = Cnd.cri();
            		int beforOrders = tagOrders-1;
            		newCri.where().and("catalog", "=", catalog).and("type", "=", tagType).and("orders", "=", beforOrders);
            		TagInfoBo beforTag = dao.fetch(TagInfoBo.class, newCri);
            		//3.将第一步的标签的orders-1
            		currTag.setOrders(tagOrders-1);
            		//4.将第二步的标签的orders+1
            		beforTag.setOrders(tagOrders);
            		//5.更新两个标签
            		dao.update(beforTag);
            		dao.update(currTag);
        		}
    		}
    		if(tagOrders >= 1){
    			if("backward".equals(position)){
	    			int maxOrders = maxOrders(catalog, tagType);
	    			if(maxOrders != tagOrders){
	    				Criteria newCri = Cnd.cri();
	            		int backOrders = tagOrders+1;
	            		newCri.where().and("catalog", "=", catalog).and("type", "=", tagType).and("orders", "=", backOrders);
	            		TagInfoBo backTag = dao.fetch(TagInfoBo.class, newCri);
	            		//3.将第一步的标签的orders+1
	            		currTag.setOrders(tagOrders+1);
	            		//4.将第二步的标签的orders+1
	            		backTag.setOrders(tagOrders);
	            		//5.更新两个标签
	            		dao.update(backTag);
	            		dao.update(currTag);
	    			}
    			
    		}
    		
    	}
    	
    }
    /**
     * 该方法为打标签功能提供数据
     * @param catalog
     * @return
     */
    @At
    @Ok("json")
    public Map<String,Object> tagList(String catalog){
    	Map<String,Object>  result= new HashMap<String,Object>();
        Criteria cri = Cnd.cri();
        cri.where().and("catalog","=",catalog);
        cri.getOrderBy().asc("orders");
        List<TagInfoBo> tagInfoList = dao.query(TagInfoBo.class, cri);
        
        result.put("tagList", tagInfoList);
        return result;
    }
	
	
    /**
     * 获取标签信息.
     * @return
     */
    @At
    @Ok("json")
    public Map<String, Object> tagAllData(String catalog){
        Map<String, Object> result = new HashMap<String, Object>();
        Criteria cri = Cnd.cri();
        if(Strings.isNotBlank(catalog)){
        	cri.where().and("catalog", "=", catalog);
        }
        List<TagInfoBo> tagList = dao.query(TagInfoBo.class, cri);
        result.put("result",tagList);
        return result;
    }
	
	
    
}
