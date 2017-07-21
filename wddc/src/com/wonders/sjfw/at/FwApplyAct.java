package com.wonders.sjfw.at;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.sjfw.entity.FwApply;
import com.wonders.sjfw.entity.FwConfig;
import com.wonders.sjfw.entity.FwInfo;
import com.wonders.wddc.config.SessionFilter;
import com.wonders.wddc.tiles.jk.entity.User;
import com.wonders.wddc.tiles.tag.entity.TagInfoBo;
import com.wonders.wddc.tiles.tag.entity.TagTypeBo;

@At("/fw/apply")
@IocBean(fields = "dao")
public class FwApplyAct {

    private Dao dao;
    
    @At
    @Ok("jsp:sjfw.index")
    public Map<String,Object> toIndex(HttpServletRequest request,String tagId,String keyWord,String usedStatus) {
        tagId = request.getParameter("tagId");
        keyWord = request.getParameter("keyWord");
        usedStatus = request.getParameter("usedStatus");
        Map<String,Object> result = new HashMap<String,Object>();

        Map<String,Object>  applyMap= new HashMap<String,Object>();
        User sessionUser = (User)request.getSession().getAttribute(SessionFilter.SESSION_USER);
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
        result.put("fwInfoList", fwInfoList( tagId,keyWord,usedStatus,applyIds));
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
    
    public List<FwInfo> fwInfoList(String tagId,String keyWord,String usedStatus,String applyIds){
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
    
    /**
     * 获取接口数据.
     */
    @At
    @Ok("json")
    public Map<String,Object> allFwData(){
        Map<String,Object> result = new HashMap<String,Object>();
        Criteria cri = Cnd.cri();
        cri.getOrderBy().desc("applyCount");
        List<FwInfo> fwInfoList = dao.query(FwInfo.class,cri);
        result.put("result", fwInfoList);
        return result;
    }
    
    /**
	 * 获取服务总数量 .
	 */
	@At
	@Ok("json")
	public Map<String,Object> fwCount() {
		Map<String,Object> result = new HashMap<String,Object>();
		int count = dao.count(FwInfo.class);
		//放入服务信息
		result.put("fwCount", count);
		return result;
	}
	

    /**
     * 服务申请使用 .
     */
    @At
    @Ok("jsp:sjfw.apply.apply_used")
    public Map<String, Object> toApplyUsed(String fwInfoId) {
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        FwInfo fwInfo = dao.fetch(FwInfo.class,fwInfoId);
        List<FwConfig> fwConfigList = dao.query(FwConfig.class, Cnd.where("fwInfoId","=",fwInfoId));
        //数据列表值
        result.put("fwInfo",fwInfo);
        result.put("fwConfigList",fwConfigList);
        return result;
    }
    

    /**
     * 保存申请使用.
     */
    @At
    @Ok("json")
    public Map<String, Object> saveApplyUsed(HttpServletRequest request,String reason,String fwInfoId) {
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        User sessionUser = (User)request.getSession().getAttribute(SessionFilter.SESSION_USER);
        FwInfo fwInfo = dao.fetch(FwInfo.class,fwInfoId);
        FwApply fwApply = dao.fetch(FwApply.class,Cnd.where("fwInfoId","=",fwInfoId).and("unitId","=",sessionUser.getUnitId()));
        if(fwApply == null){
            fwApply = new FwApply();
            fwApply.setFwInfoId(fwInfoId);
            fwApply.setApplyReason(reason);
            fwApply.setApplyTime(new Date());
            fwApply.setApplyUserId(sessionUser.getUserId());
            fwApply.setApplyUserName(sessionUser.getDisplayName());
            fwApply.setMethodKey("wd"+System.currentTimeMillis());
            fwApply.setUsedStatus("1");
            fwApply.setAuditStatus("1");
            fwApply.setUnitName(sessionUser.getUnitName());
            fwApply.setUnitId(sessionUser.getUnitId());
            fwApply.setAlertLevel(fwInfo.getAlertLevel());
            fwApply = dao.insert(fwApply);
        }else{
            fwApply.setApplyReason(reason);
            dao.update(fwApply);
        }
        //申请次数增加
        fwInfo.setApplyCount(fwInfo.getApplyCount()+1);
        dao.update(fwInfo);
        //数据列表值
        result.put("fwApply",fwApply);
        return result;
    }
    
    /**
     * 服务申请完成 .
     */
    @At
    @Ok("jsp:sjfw.apply.finish")
    public Map<String,Object> toFwsqFinish(String fwApplyId) {
        FwApply fwApply = dao.fetch(FwApply.class,fwApplyId);
        FwInfo fwInfo = dao.fetch(FwInfo.class,fwApply.getFwInfoId());
        List<FwConfig> fwConfigList = dao.query(FwConfig.class, Cnd.where("fwInfoId","=",fwApply.getFwInfoId()));
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        //数据列表值
        result.put("fwApply",fwApply);
        result.put("fwInfo",fwInfo);
        result.put("fwConfigList",fwConfigList);
        return result;

    }
    

    /**
     * 获取服务的json数据 .
     * 进入服务申请界面
     * @return 
     */
    @At
    @Ok("json")
    public Map<String, Object> fwInfoData(String fwInfoId) {
    	Map<String,Object> result = new HashMap<String, Object>();
        FwInfo fwInfo = dao.fetch(FwInfo.class,fwInfoId);
    	result.put("fwInfo", fwInfo);
    	return result;
    }



}
