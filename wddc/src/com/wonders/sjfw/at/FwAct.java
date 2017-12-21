package com.wonders.sjfw.at;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.sjfw.entity.FwAccess;
import com.wonders.sjfw.entity.FwApply;
import com.wonders.sjfw.entity.FwConfig;
import com.wonders.sjfw.entity.FwInfo;
import com.wonders.sjfw.entity.LogFw;
import com.wonders.wddc.config.SessionFilter;
import com.wonders.wddc.suite.data.service.DataCoreService;
import com.wonders.wddc.suite.logger.entity.LogOpBo;
import com.wonders.wddc.suite.logger.service.LogCoreService;
import com.wonders.wddc.suite.user.entity.UserUnitBo;
import com.wonders.wddc.tiles.jk.entity.User;
import com.wonders.wddc.tiles.sn.SnCreator;

/**
 * 共享服务功能标准实现.
 */

@At("/fw")
//为什么不可以直接用注解注入呢
@IocBean(fields = "dao")
public class FwAct extends BaseAct{

    private Dao dao;
    
    @Inject
    private DataCoreService dataService;
    
    @Inject
    private LogCoreService logCoreService;
    

    /**
     * 进入具体的服务发布页面.
     */
    @At("/publish/*")
    public View publish(String jspPath,String applyId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("uuid",UUID.randomUUID().toString());
        result.put("applyId",applyId);
        return new ViewWrapper(new JspView("sjfw.publish.add."+jspPath), result);
    }

    /**
     * 进入具体的服务发布页面.
     */
    @At("/edit/*")
    public View toEdit(String jspPath,String fwInfoId) {
        Map<String, Object> result = new HashMap<String, Object>();
        //服务对象
        FwInfo fwInfo = dao.fetch(FwInfo.class,fwInfoId);

        List<FwConfig> fwConfigList = dao.query(FwConfig.class, Cnd.where("fwInfoId","=",fwInfoId));
        
        result.put("fwInfo",fwInfo);
        result.put("fwConfigList",fwConfigList);
        return new ViewWrapper(new JspView("sjfw.publish.add."+jspPath), result);
    }
    
    /**
     * 查看具体的服务发布页面.
     */
    @At("/view/*")
    @Ok("jsp:sjfw.publish.view")
    public Map<String, Object> toView(String fwInfoId) {
        Map<String, Object> result = new HashMap<String, Object>();
        //服务对象
        FwInfo fwInfo = dao.fetch(FwInfo.class,fwInfoId);
        List<FwConfig> fwConfigList = dao.query(FwConfig.class, Cnd.where("fwInfoId","=",fwInfoId));
        
        result.put("fwInfo",fwInfo);
        result.put("fwConfigList",fwConfigList);
        return result;
    }

	/**
	 * 查看服务详细日志.
	 */
	@At("/log/*")
	@Ok("jsp:sjfw.log.fw_detail")
	public Object toFwlogDetail(String dlId) {
		//6.回传数据
		Map<String,Object>  result= new HashMap<String,Object>();
		LogFw logFw = dao.fetch(LogFw.class, dlId);
		if(logFw != null){
	        FwInfo fwInfo = dao.fetch(FwInfo.class,logFw.getFwInfoId());
			result.put("fwInfo", fwInfo);
		}
		result.put("fwLog", logFw);
		return result; 
	}

    /**
     * 服务发布列表 .
     */
    @At
    @Ok("jsp:sjfw.publish.list")
    public Map<String, Object> toFwfbList() {
        Criteria cri = Cnd.cri();
        //dao.query()为条件查询
        List<FwInfo> fwInfoList = dao.query(FwInfo.class,cri);
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        //数据列表值
        result.put("fwInfoList",fwInfoList);
        return result;

    }

    /**
     * 服务发布列表 .
     */
    @At
    @Ok("jsp:sjfw.access.access_info")
    public Map<String, Object> toFwAccessList(String fwInfoId) {
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        Criteria cri = Cnd.cri();
        //dao.query()为条件查询
        cri.where().and("fwInfoId", "=", fwInfoId);
        List<FwAccess> fwAccessList = dao.query(FwAccess.class,cri);
        //数据列表值
        result.put("fwAccessList",fwAccessList);

        FwInfo fwInfo = dao.fetch(FwInfo.class,fwInfoId);
        
        result.put("fwInfo",fwInfo);
        return result;

    }
 
    /**
     * 编辑服务授权页面 .
     */
    @At
    @Ok("jsp:sjfw.access.access_add")
    public Map<String, Object> editFwAccess(String fwInfoId,String fwAccessId) {
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        if(Strings.isNotBlank(fwAccessId)){
	        FwAccess fwAccess = dao.fetch(FwAccess.class,fwAccessId);
	        result.put("fwAccess",fwAccess);
        }
        result.put("fwInfoId",fwInfoId);
        return result;

    }
    /**
     * 保存服务授权信息.
     * @param fwInfoId
     * @param fwAccess
     * @return
     */
     @At
     @Ok("redirect:/fw/toFwAccessList?fwInfoId=${fwInfoId}")
    public Map<String, Object> saveFwAccessList(String fwInfoId,@Param("::fwAccess") FwAccess fwAccess) {
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        //设置服务主键
        fwAccess.setFwInfoId(fwInfoId);
        if(Strings.isBlank(fwAccess.getFwAccessId())){
    		fwAccess.setMethodKey("wd"+System.currentTimeMillis());
        	this.dao.insert(fwAccess);
        }else{
        	this.dao.update(fwAccess);
        }
        //设置申请成功次数
        this.addFwApplyCount(fwInfoId);
        result.put("fwAccess",fwAccess);
        return result;

    }
     
     /**
      * 申请时进行服务授权信息.
      * @param fwInfoId
      * @param fwAccess
      * @return
      */
      @At
      @Ok("json")
     public Map<String, Object> apply2FwAccess(String fwCode,String unitId,@Param("::fwAccess") FwAccess fwAccess) {
         //回传数据
         Map<String,Object>  result= new HashMap<String,Object>();
         result.put("result","1");
    	  try{
	         //设置服务主键
	         Criteria cri = Cnd.cri();
	         cri.where().and("fwCode", "=", fwCode);
	         FwInfo fwInfo = dao.fetch(FwInfo.class,cri);
	         UserUnitBo userUnit = dao.fetch(UserUnitBo.class, unitId);
	        
	         FwAccess oldFwAccess = dao.fetch(FwAccess.class,Cnd.where("fwInfoId", "=", fwInfo.getFwInfoId()).and("unitKey", "=", userUnit.getUnitKey()));
	         //判断是否重复授权
	         if(oldFwAccess != null){
    	         result.put("result","0");
    	         return result;
	         }
	         
	         fwAccess.setFwInfoId(fwInfo.getFwInfoId());
	         fwAccess.setUnitKey(userUnit.getUnitKey());
	
			  fwAccess.setMethodKey("wd"+System.currentTimeMillis());
	         if(Strings.isBlank(fwAccess.getFwAccessId())){
	         	this.dao.insert(fwAccess);
	         }else{
	         	this.dao.update(fwAccess);
	         }
	         //设置申请成功次数
	         this.addFwApplyCount(fwInfo.getFwInfoId());
    	  }catch(Exception e){
    	         result.put("result","0");
    		  e.printStackTrace();
    	  }
         return result;

     }
    

  	/**
  	 * 设置申请成功次数.
  	 * @param fwInfoId 服务主键
  	 */
  	public void addFwApplyCount(String fwInfoId){
        //设置服务申请数量
        FwInfo fwInfo = dao.fetch(FwInfo.class,fwInfoId);
        fwInfo.setApplyCount(fwInfo.getApplyCount()+1);
        dao.update(fwInfo);
  	}
    
    /**
     * 发布服务列表 .
     */
    @At
    @Ok("jsp:sjfw.publish.add")
    public Map<String, Object> toPublish(String fwApplyId) {
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        //数据列表值
        result.put("applyId",fwApplyId);
        return result;

    }
    
    /**
     * 新增服务 .
     */
    @At
    @Ok("jsp:sjfw.publish.edit")
    public void toEdit() {

    }
   

    /**
     * 保存服务页面 .
     */
    @At
    @Ok("redirect:/fw/toFwfbList")
    public void saveBiaozhun1(@Param("::fwInfo") FwInfo fwInfo) {
        fwInfo.setOpTime(new Date());
        fwInfo.setCreateTime(new Date());
        fwInfo.setFwCode(SnCreator.getInstance().getSN("wdfw", 5, "fw"));
        dao.insert(fwInfo);
    }

    /**
     * 保存服务页面jiekou1 .
     */
    @At
    @Ok("redirect:/fw/toFwfbList")
    public void saveLink(HttpServletRequest request,@Param("::fwInfo") FwInfo fwInfo) {
        fwInfo.setOpTime(new Date());
        fwInfo.setCreateTime(new Date());
        fwInfo.setFwCode(SnCreator.getInstance().getSN("wdfw", 5, "fw"));
        fwInfo = dao.insert(fwInfo);
       
    }
    /**
     * 保存服务页面jiekou1 .
     */
    @At
    @Ok("redirect:/fw/toFwfbList")
    public void saveJiekou1(HttpServletRequest request,@Param("::fwInfo") FwInfo fwInfo,String zyItems) {
        fwInfo.setOpTime(new Date());
        fwInfo.setCreateTime(new Date());
        fwInfo.setFwCode(SnCreator.getInstance().getSN("wdfw", 5, "fw"));
        fwInfo = dao.insert(fwInfo);
        List<FwConfig> fwConfigList = new ArrayList<FwConfig>();
        this.dealFwConfig(dao,fwInfo,zyItems,"1",fwConfigList);
        this.dealWhereItem(dao,fwInfo,request,fwConfigList);
        fwConfigList = dao.query(FwConfig.class, Cnd.where("fwInfoId", "=", fwInfo.getFwInfoId()));
        this.dealFwSql(dao, dataService, fwInfo, fwConfigList);
    }

    /**
     * 保存服务页面jiekou2.
     */
    @At
    @Ok("redirect:/fw/toFwfbList")
    public void saveJiekou2(HttpServletRequest request,@Param("::fwInfo") FwInfo fwInfo,String zyItems,String resultOps) {
        fwInfo.setOpTime(new Date());
        fwInfo.setCreateTime(new Date());
        fwInfo.setFwCode(SnCreator.getInstance().getSN("wdfw", 5, "fw"));
        fwInfo = dao.insert(fwInfo);
        List<FwConfig> fwConfigList = new ArrayList<FwConfig>();
        this.dealFwConfig(dao,fwInfo,zyItems,"1",fwConfigList);
        this.dealWhereItem(dao,fwInfo,request,fwConfigList);
        this.dealResultItem(dao,fwInfo, request,fwConfigList);
        fwConfigList = dao.query(FwConfig.class, Cnd.where("fwInfoId", "=", fwInfo.getFwInfoId()));
        this.dealFwSql(dao, dataService, fwInfo, fwConfigList);
    }


    /**
     * 保存服务页面jiekou3.
     */
    @At
    @Ok("redirect:/fw/toFwfbList")
    public void saveJiekou3(HttpServletRequest request,@Param("::fwInfo") FwInfo fwInfo,String zyItems,String groupItems,String resultOps) {
        fwInfo.setOpTime(new Date());
        fwInfo.setCreateTime(new Date());
        fwInfo.setFwCode(SnCreator.getInstance().getSN("wdfw", 5, "fw"));
        fwInfo = dao.insert(fwInfo);
        List<FwConfig> fwConfigList = new ArrayList<FwConfig>();
        this.dealFwConfig(dao,fwInfo,zyItems,"1",fwConfigList);
        this.dealFwConfig(dao,fwInfo,groupItems,"5",fwConfigList);
        this.dealWhereItem(dao,fwInfo,request,fwConfigList);
        this.dealResultItem(dao,fwInfo, request,fwConfigList);
        fwConfigList = dao.query(FwConfig.class, Cnd.where("fwInfoId", "=", fwInfo.getFwInfoId()));
        this.dealFwSql(dao, dataService, fwInfo, fwConfigList);
        
    }

    
    /**
     * 保存服务页面 .
     */
    @At
    @Ok("redirect:/fw/toFwfbList")
    public void saveJiekou4(HttpServletRequest request,@Param("::fwInfo") FwInfo fwInfo,String zyItems,String zyResultItems) {
        fwInfo.setOpTime(new Date());
        fwInfo.setCreateTime(new Date());
        fwInfo.setFwCode(SnCreator.getInstance().getSN("wdfw", 5, "fw"));
        fwInfo = dao.insert(fwInfo);
        List<FwConfig> fwConfigList = new ArrayList<FwConfig>();
        this.dealFwConfig(dao,fwInfo,zyItems,"1",fwConfigList);
        this.dealFwConfig(dao,fwInfo,zyResultItems,"2",fwConfigList);
        fwConfigList = dao.query(FwConfig.class, Cnd.where("fwInfoId", "=", fwInfo.getFwInfoId()));
        this.dealFwSql(dao, dataService, fwInfo, fwConfigList);
    }
    
    /**
     * 获取表配置信息.
     * @return
     */
    @At
    @Ok("json")
    public Map<String, Object> zyInfoAll(){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("result",this.dataService.getTableConfig());
        return result;
    }
    
    /**
     * 获取表字典配置信息 .
     * @param zyInfoId
     * @return
     */
    @At
    @Ok("json")
    public Map<String, Object> zyItemByInfoId(String zyInfoId){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("result",this.dataService.getColumnConfig(zyInfoId));
        return result;
    }
    

    /**
     * 所有服务列表信息 .
     */
    @At
    @Ok("json")
    public Map<String, Object> allFwData() {
        Criteria cri = Cnd.cri();
        //dao.query()为条件查询
        List<FwInfo> fwInfoList = dao.query(FwInfo.class,cri);
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        //数据列表值
        result.put("result",fwInfoList);
        return result;

    }
    
    /**
     * 获取服务授权信息 .
     */
    @At
    @Ok("json")
    public Map<String, Object> fwAccessData(String fwCode,String unitId) {
        Criteria cri = Cnd.cri();
        cri.where().and("fwCode", "=", fwCode);
        FwInfo fwInfo = dao.fetch(FwInfo.class,cri);
        UserUnitBo userUnit = dao.fetch(UserUnitBo.class, unitId);
        FwAccess fwAccess = dao.fetch(FwAccess.class,Cnd.where("fwInfoId", "=", fwInfo.getFwInfoId()).and("unitKey", "=", userUnit.getUnitKey()));
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        //数据列表值
        result.put("result",fwAccess);
        return result;

    }
    

    
    /**
     * 判断服务是否已经授权 .
     */
    @At
    @Ok("json")
    public Map<String, Object> fwIsAccess(String fwInfoId,String unitKey) {
        FwAccess fwAccess = dao.fetch(FwAccess.class,Cnd.where("fwInfoId", "=", fwInfoId).and("unitKey", "=", unitKey));
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        //数据列表值
        result.put("result",fwAccess);
        return result;

    }
    
    
    /**
     * 服务预警列表 .
     */
    @At
    @Ok("jsp:sjfw.alarm.list")
    public Map<String,Object> toAlarmList() {
        Criteria cri = Cnd.cri();
        //dao.query()为条件查询
        List<FwInfo> fwInfoList = dao.query(FwInfo.class,cri);
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        //数据列表值
        result.put("fwInfoList",fwInfoList);
        return result;

    }
    
    /**
     * 进入服务预警设置 页面.
     * @return 
     */
    @At
    @Ok("jsp:sjfw.alarm.alarm_set")
    public Map<String,Object> toAlarm(String fwInfoId) {
    	Criteria cri = Cnd.cri();
        cri.where().and("fwInfoId", "=", fwInfoId);
    	FwInfo fwList = dao.fetch(FwInfo.class, cri);
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("fwList",fwList);
		return result;
    }
    

    /**
     * 保存预警等级信息
     */
    @At
    @Ok("redirect:/fw/toAlarmList")
    public void saveLevel(String fwInfoId,String alertLevel,HttpServletRequest request){
    	FwInfo oldfwInfo = dao.fetch(FwInfo.class,fwInfoId);
    	//记录日志
    	String opDesc = "由"+oldfwInfo.getAlertLevel()+"变为"+alertLevel;
    	logCoreService.insertOpLogAndDetail("yj", "yj102", "预警设置", opDesc, fwInfoId, request);
    	//更新预警信息
    	oldfwInfo.setAlertLevel(alertLevel);
    	dao.update(oldfwInfo);
    }
    
    /**
     * 服务授权（由授权状态改为发布状态）
     */
    @At
    @Ok("redirect:/fw/toAppLyAudit")
    public void saveApplyAudit(String fwApplyId,String auditMemo,String auditStatus){
    	if(fwApplyId != null){
	    	 Criteria cri = Cnd.cri();
	    	 cri.where().and("fwApplyId","=",fwApplyId);
	    	 FwApply fwApply = dao.fetch(FwApply.class,cri);
	    	 fwApply.setAuditStatus("2");
	    	 fwApply.setAuditMemo(auditMemo);
	    	 fwApply.setAuditStatus(auditStatus);
	    	 fwApply.setAuditTime(new Date());
	    	 dao.update(fwApply);
    	 }
    }
    
    /**
     * 进入服务审批页面 .
     */
    @At
    @Ok("jsp:sjfw.access.audit")
    public Map<String, Object> toAppLyAudit(String fwApplyId,HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute(SessionFilter.SESSION_USER);
    	Map<String, Object> result = new HashMap<String,Object>();
    	Criteria cri = Cnd.cri();
    	cri.where().and("fwApplyId","=",fwApplyId);
    	FwApply fwApply = dao.fetch(FwApply.class,cri);
    	Criteria criConfig = Cnd.cri();
    	criConfig.where().and("fwApplyId","=",fwApply.getFwApplyId());
    	List<FwConfig> fwConfiList = dao.query(FwConfig.class, criConfig);
    	
    	result.put("userName", user.getUnitName());
    	result.put("logonName", user.getLogonName());
    	result.put("itemList", fwConfiList);
    	result.put("fwApply", fwApply);
    	return result;
    }
    
    /**
     * 服务授权列表 .
     */
    @At
    @Ok("jsp:sjfw.access.apply_list")
    public Map<String,Object> toAppLyAuditList() {
        Criteria cri = Cnd.cri();
        //dao.query()为条件查询
        cri.getOrderBy().desc("applyTime");
        List<FwApply> fwApplyList = dao.query(FwApply.class,cri);
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        //数据列表值
        result.put("fwApplyList",fwApplyList);
        return result;

    }
    

}
