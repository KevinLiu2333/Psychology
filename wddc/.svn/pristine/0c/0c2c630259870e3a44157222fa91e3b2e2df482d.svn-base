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
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.sjfw.entity.FwApply;
import com.wonders.sjfw.entity.FwConfig;
import com.wonders.sjfw.entity.FwInfo;
import com.wonders.sjfw.platform.FwCoreService;
import com.wonders.wddc.config.SessionFilter;
import com.wonders.wddc.tiles.jk.entity.User;

@At("/fw/apply")
@IocBean(fields = "dao")
public class FwApplyAct {

    private Dao dao;
    
    private FwCoreService fwCoreService;
    /**
     * 浏览服务信息.
     * @param request
     * @param tagId
     * @param keyWord
     * @param usedStatus
     * @return
     */
    @At
    @Ok("jsp:sjfw.index")
    public Map<String,Object> toIndex(HttpServletRequest request,String tagId,String keyWord,String usedStatus) {
        Map<String,Object> result = new HashMap<String,Object>();

        User sessionUser = (User)request.getSession().getAttribute(SessionFilter.SESSION_USER);
        fwCoreService.toIndex(sessionUser, tagId, keyWord, usedStatus);

        return result;
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
