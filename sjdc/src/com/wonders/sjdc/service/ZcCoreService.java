package com.wonders.sjdc.service;

import java.util.Date;
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

import com.wonders.sjfw.entity.FwApply;
import com.wonders.sjfw.entity.FwInfo;
import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.sjfw.platform.spu.KeySpu;
import com.wonders.sjfw.platform.spu.ParamSpu;
import com.wonders.sjfw.platform.spu.SpuFactory;
import com.wonders.wddc.config.WddcConstants;
import com.wonders.wddc.tiles.jk.entity.User;
import com.wonders.wddc.tiles.tag.entity.TagInfoBo;
import com.wonders.wddc.tiles.tag.entity.TagTypeBo;

@IocBean
@Filters
public class ZcCoreService {
	@Inject
	private KeySpu keySpu;

	@Inject
	private ParamSpu paramSpu;

	@Inject
	public Dao dao;

    public Map<String,Object> toIndex(User sessionUser,String tagId,String keyWord,String usedStatus) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object>  applyMap= new HashMap<String,Object>();
        String applyIds = "";
        if(sessionUser != null){
	        List<FwApply> fwApplyList = dao.query(FwApply.class,Cnd.where("unitId","=",sessionUser.getUnitId()));
	        for(FwApply fwApply :fwApplyList){
	            applyMap.put(fwApply.getFwInfoId(),fwApply);
	            if("".equals(applyIds)){
	                applyIds = fwApply.getFwInfoId();
	            }else{
	                applyIds = applyIds+","+fwApply.getFwInfoId();
	            }
	        }
        }
        //放入所有服务信息
        result.put("fwInfoList", fwInfoList(tagId,keyWord,usedStatus,applyIds));
        //放入标签信息
        List<TagTypeBo> tagTypeList = dao.query(TagTypeBo.class, Cnd.where("catalog","=","12"));
        result.put("typeList", tagTypeList);
        //放入标签信息
        result.put("keyWord", keyWord);
        //放入标签信息
        result.put("usedStatus", usedStatus);
        //放入标签类型

    	//获取catalog为"12"的页面标签集合
        List<TagInfoBo> tagList = dao.query(TagInfoBo.class, Cnd.where("catalog", "=","12").getOrderBy().asc("orders"),null);
        result.put("tagList",tagList);
        //获取已申请的服务信息

        //分页信息
        result.put("applyMap",applyMap);

        return result;
    }
    
    /**
     * 查询服务的列表信息.
     * @param tagId
     * @param keyWord
     * @param usedStatus
     * @param applyIds
     * @return
     */
    private List<FwInfo> fwInfoList(String tagId,String keyWord,String usedStatus,String applyIds){
        Criteria cri = Cnd.cri();
        if(!Strings.isBlank(tagId)){
        	TagInfoBo tagInfo = dao.fetch(TagInfoBo.class,Cnd.where("tagId", "=", tagId));
            cri.where().andLike("tagList", tagInfo.getShowName());

        }
        if(!Strings.isBlank(keyWord)){
            cri.where().orLike("fwAbstract", keyWord).orLike("tagList", keyWord).orLike("fwName", keyWord);;
        }
        if("0".equals(usedStatus)){
            cri.where().andNotIn("fwInfoId", applyIds.split(","));
        }
        if("1".equals(usedStatus)){
            cri.where().andIn("fwInfoId", applyIds.split(","));
        }
        List<FwInfo> fwInfoList = dao.query(FwInfo.class, cri);
        return fwInfoList;
}
}
