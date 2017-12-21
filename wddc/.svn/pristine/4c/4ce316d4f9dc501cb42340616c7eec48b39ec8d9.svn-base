package com.wonders.wdac.at;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.wdac.entity.PZyInfo;
import com.wonders.wdac.entity.PfZyItem;
import com.wonders.wdac.entity.ZyApply;
import com.wonders.wdac.entity.ZyApplyDetails;
import com.wonders.wdac.service.ZyCoreService;
import com.wonders.wddc.config.SessionFilter;
import com.wonders.wddc.tiles.jk.entity.User;
import com.wonders.wddc.tiles.sn.SnCreator;

@At("/zyapply")
@IocBean(fields = "dao")
public class ZyApplyAct {
	
	private Dao dao;
	
	@Inject
	private ZyCoreService zyCoreService;
	/**
	 * 资源浏览列表.
	 * @param tagId
	 * @param keyWord
	 * @return
	 */
	@At
	@Ok("jsp:wdac.zy.zy_index")
	public Map<String,Object> toZyIndex(HttpServletRequest request,String tagId,String keyWord){
        //放入标签类型
	   	Map<String,Object> result = new HashMap<String, Object>();
    	User sessionUser = (User)request.getSession().getAttribute(SessionFilter.SESSION_USER);
	   	result = this.zyCoreService.toZyIndex(sessionUser, tagId, keyWord);
      
        return result;
	}
	

	 /**
    * 申请资源入口方法 .
    * 进入资源申请界面
    * @return 
    */
   @At
   @Ok("jsp:wdac.zy.apply_result")
   public Map<String, Object> toApplyResult(String zyApplyId) {
   	Map<String,Object> result = new HashMap<String, Object>();
   	//获取组员列表
   	ZyApply zyApply = dao.fetch(ZyApply.class, zyApplyId);
   	//获取资源对象
   	result.put("zyApply", zyApply);
   	return result;
   }    
   

	/**
	  * 进入资源授权页面列表 .
	  * 进入资源申请界面
	  * @return 
	  */
	 @At
	 @Ok("jsp:wdac.zy.apply_list")
	 public Map<String, Object> toApplyList() {
	 	Map<String,Object> result = new HashMap<String, Object>();
	 	//获取组员列表
    	Criteria cri = Cnd.cri();
        cri.where().and("isNewApply", "=", "1");
    	List<ZyApply> zyApplyList = dao.query(ZyApply.class,cri);
	 	//获取资源对象
	 	result.put("zyApplyList", zyApplyList);
	 	return result;
	 }    

	   

	/**
	  * 资源申请审批页面 .
	  * @return 
	  */
	 @At
	 @Ok("jsp:wdac.zy.apply_audit")
	 public Map<String, Object> toApplyAudit(String zyApplyId) {
	 	Map<String,Object> result = new HashMap<String, Object>();
	 	//获取组员列表
	 	ZyApply zyApply = dao.fetch(ZyApply.class, zyApplyId);
	 	List<ZyApplyDetails> zyApplyDetailsList = dao.query(ZyApplyDetails.class, Cnd.where("zyApplyId", "=", zyApplyId));
	 	//获取资源对象
	 	result.put("zyApply", zyApply);
	 	result.put("zyApplyDetailsList", zyApplyDetailsList);
	 	return result;
	 }    
	
	 
	
	 /**
	* 保存资源审批信息 .
	* @return 
	*/
	@At
    @Ok("redirect:${ctx}/zyapply/toApplyList")
	public Map<String, Object> saveApplyAudit(HttpServletRequest request,@Param("::zyApply") ZyApply applyInfo,String zyApplyId) {
    	User sessionUser = (User)request.getSession().getAttribute(SessionFilter.SESSION_USER);
		Map<String,Object> result = new HashMap<String, Object>();
		//获取组员列表
		ZyApply zyApply = dao.fetch(ZyApply.class, zyApplyId);
		if("1".equals(zyApply.getStatus())){
			zyApply.setStatus(applyInfo.getStatus());
			zyApply.setCheckReason(applyInfo.getCheckReason());
			zyApply.setCheckedDate(new Date());
			zyApply.setCheckedPerson(sessionUser.getDisplayName());
		}else{
			zyApply.setFwCode(applyInfo.getFwCode());
			zyApply.setFwDate(new Date());
		}
		dao.update(zyApply);
		return result;
	}    
	
	 /**
     * 申请资源入口方法 .
     * 进入资源申请界面
     * @return 
     */
    @At
    @Ok("jsp:wdac.zy.apply")
    public Map<String, Object> toApply(String zyInfoId) {
    	Map<String,Object> result = new HashMap<String, Object>();
    	//获取组员列表
    	Criteria cri = Cnd.cri();
    	cri.where().and("zyInfoId","=",zyInfoId);
    	List<PfZyItem> zyItemList = dao.query(PfZyItem.class, cri);
    	//获取资源对象
    	PZyInfo pZyInfo = dao.fetch(PZyInfo.class, zyInfoId);
    	result.put("zyItemList", zyItemList);
    	result.put("pZyInfo", pZyInfo);
    	return result;
    }
    

    /**
     * 保存申请服务信息.
     */
    @At
    @Ok("redirect:${ctx}/zyapply/toZyIndex")
    public void saveApply(HttpServletRequest request,@Param("::zyApply") ZyApply applyInfo,String zyInfoId,String zyItemIds){
    	User sessionUser = (User)request.getSession().getAttribute(SessionFilter.SESSION_USER);
    	PZyInfo pZyInfo = dao.fetch(PZyInfo.class, zyInfoId);
    	//查看是否已经申请过
    	Criteria cri = Cnd.cri();
        cri.where().and("userId", "=", sessionUser.getUnitId());
        cri.where().and("isNewApply", "=", "1");
        cri.where().and("zyInfoId", "=", zyInfoId);
    	ZyApply oldZyApply = dao.fetch(ZyApply.class,cri);
    	if(oldZyApply != null){
    		oldZyApply.setIsNewApply("2");
    		dao.update(oldZyApply);
    	}
    	//创建新的申请
    	ZyApply zyApply = new ZyApply();
    	zyApply.setApplyDate(new Date());
    	zyApply.setUserId(sessionUser.getUserId());
    	zyApply.setUserName(sessionUser.getDisplayName());
    	zyApply.setUnitId(sessionUser.getUnitId());
    	zyApply.setUnitName(sessionUser.getUnitName());
    	zyApply.setApplyReason(applyInfo.getApplyReason());
    	zyApply.setApplyTopic(applyInfo.getApplyTopic());
    	zyApply.setLinkmanPhone(applyInfo.getLinkmanPhone());
    	zyApply.setZyInfoId(zyInfoId);
    	zyApply.setAppApplyNum(SnCreator.getInstance().getSN("zyApply", 4, "A"));
    	zyApply.setResourceName(pZyInfo.getZyName());
    	zyApply.setResourceProvider(pZyInfo.getZyUnit());
    	zyApply.setStatus("1");
    	zyApply.setIsNewApply("1");
        dao.insert(zyApply);
        if(zyItemIds != null){
        	String[] idArray = zyItemIds.split(",");
        	for (String zyItemId : idArray) {
        		ZyApplyDetails zyApplyDetails = new ZyApplyDetails();
        		zyApplyDetails.setZyItemId(zyItemId);
        		zyApplyDetails.setZyApplyId(zyApply.getZyApplyId());
        		dao.insert(zyApplyDetails);
        	}
        }
    	
    }
    
}
