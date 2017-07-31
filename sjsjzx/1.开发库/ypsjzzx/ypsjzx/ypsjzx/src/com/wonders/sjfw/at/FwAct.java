package com.wonders.sjfw.at;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import com.wonders.sjfw.entity.FwConfig;
import com.wonders.sjfw.entity.FwInfo;
import com.wonders.wddc.suite.data.service.DataService;
import com.wonders.wddc.tiles.sn.SnCreator;

/**
 * 共享服务功能标准实现.
 */

@At("/fw")
@IocBean(fields = "dao")
public class FwAct extends BaseAct{

    private Dao dao;
    
    @Inject
    private DataService dataService;
    

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
        	this.dao.insert(fwAccess);
        }else{
        	this.dao.update(fwAccess);
        }
        
        result.put("fwAccess",fwAccess);
        return result;

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
     * 新增服务 .
     */
    @At
    @Ok("jsp:sjfw.publish.example")
    public void toExample() {

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

}
