package com.wonders.wdac.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.Filters;

import com.wonders.wdac.entity.PZyInfo;
import com.wonders.wdac.entity.ZyApply;
import com.wonders.wddc.tiles.jk.entity.User;
import com.wonders.wddc.tiles.tag.entity.TagInfoBo;
import com.wonders.wddc.tiles.tag.entity.TagTypeBo;

@IocBean
@Filters
public class ZyCoreService {

	@Inject
	public Dao dao;

	/**
	 * 根据条件查询资源列表.
	 * @param tagId 标签主键
	 * @param keyWord 关键字
	 * @return
	 */
	public Map<String,Object> toZyIndex(User sessionUser,String tagId,String keyWord){
		Map<String,Object> result = new HashMap<String,Object>();
        //放入标签类型
        List<TagTypeBo> tagTypeList = dao.query(TagTypeBo.class, Cnd.where("catalog","=","11"));
        Map<String,Object>  applyMap= new HashMap<String,Object>();
        if(sessionUser != null){
        String applyIds = "";
        List<ZyApply> fwApplyList = dao.query(ZyApply.class,Cnd.where("unitId","=",sessionUser.getUnitId()).and("isNewApply", "=", "1"));
        for(ZyApply zyApply :fwApplyList){
            applyMap.put(zyApply.getZyInfoId(),zyApply);
            if("".equals(applyIds)){
                applyIds = zyApply.getZyInfoId();
            }else{
                applyIds = applyIds+","+zyApply.getZyInfoId();
            }
        }
        }
        result.put("typeList", tagTypeList);
        //放入catalog为"11"的页面标签集合
        result.put("tagList", tagList());
        result.put("keyWord", keyWord);
        result.put("applyMap",applyMap);
        //放入资源数据
        result.put("zyInfoList",zyInfoList(tagId,keyWord));
        return result;
	}
	

	/**
	 * 获取服务标签列表.
	 * @return
	 */
	private List<TagInfoBo> tagList(){
    	List<TagInfoBo> tagList = dao.query(TagInfoBo.class, Cnd.where("catalog", "=","11").getOrderBy().asc("orders"),null);
    	return tagList;
    }
	
	/**
	 * 获取资源列表.
	 * @param tagId
	 * @param keyWord
	 * @return
	 */
	private List<PZyInfo> zyInfoList(String tagId,String keyWord){
    	Criteria cri = Cnd.cri();
    	if(!Strings.isBlank(tagId)){
    		TagInfoBo tagInfo = dao.fetch(TagInfoBo.class,Cnd.where("tagId", "=", tagId));
            cri.where().andLike("tagLists", tagInfo.getShowName());
    	}

        if(!Strings.isBlank(keyWord)){
            cri.where().orLike("zyAbstract", keyWord).orLike("tagLists", keyWord).orLike("zyName", keyWord);
        }
        cri.where().andEquals("status", "已发布");
    	List<PZyInfo> zyInfoList = dao.query(PZyInfo.class, cri);
    	return zyInfoList;
	}
}
