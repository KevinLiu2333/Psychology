package com.wonders.sjdc.at;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.sjdc.service.ZcCoreService;
import com.wonders.sjfw.platform.FwCoreService;
import com.wonders.wdac.service.ZyCoreService;
import com.wonders.wddc.config.SessionFilter;
import com.wonders.wddc.tiles.jk.entity.User;

@IocBean
@At("/open")
public class OpenAct {
	
	@Inject
	private FwCoreService fwCoreService;
	
	@Inject
	private ZyCoreService zyCoreService;
	
	@Inject
	private ZcCoreService zcCoreService;
	/**
	 * 资源浏览列表--更多页面.
	 * @param tagId
	 * @param keyWord
	 * @return
	 */
	@At
	@Ok("jsp:sjdc.open.zy_more")
	public Map<String,Object> toZyIndex(HttpServletRequest request,String tagId,String keyWord){
        //放入标签类型
	   	Map<String,Object> result = new HashMap<String, Object>();
    	User sessionUser = (User)request.getSession().getAttribute(SessionFilter.SESSION_USER);
	   	result = this.zyCoreService.toZyIndex(sessionUser, tagId, keyWord);
      
        return result;
	}
	

	
	/**
     * 浏览服务信息--服务更多.
     * @param request
     * @param tagId
     * @param keyWord
     * @param usedStatus
     * @return
     */
    @At
    @Ok("jsp:sjdc.open.fw_more")
    public Map<String,Object> toFwIndex(HttpServletRequest request,String tagId,String keyWord,String usedStatus) {
        Map<String,Object> result = new HashMap<String,Object>();
        User sessionUser = (User)request.getSession().getAttribute(SessionFilter.SESSION_USER);
        result = fwCoreService.toIndex(sessionUser, tagId, keyWord, usedStatus);

        return result;
    }
    
    /**
     * 浏览政策服务--服务更多.
     * @param request
     * @param tagId
     * @param keyWord
     * @param usedStatus
     * @return
     */
    @At
    @Ok("jsp:sjdc.open.zc_more")
    public Map<String,Object> toZcIndex(HttpServletRequest request,String tagId,String keyWord,String usedStatus) {
        Map<String,Object> result = new HashMap<String,Object>();
        User sessionUser = (User)request.getSession().getAttribute(SessionFilter.SESSION_USER);
        result =zcCoreService.toIndex(sessionUser, tagId, keyWord, usedStatus);

        return result;
    }
    
    

	/**
	 * 资源浏览列表--资源详细页面.
	 * @param tagId
	 * @param keyWord
	 * @return
	 */
	@At
	@Ok("jsp:sjdc.open.zy_detail")
	public Map<String,Object> toZyDetail(HttpServletRequest request,String zyInfoId){
        //放入标签类型
	   	Map<String,Object> result = new HashMap<String, Object>();
	   	result.put("zyInfoId", zyInfoId);
      
        return result;
	}
	

	/**
     * 浏览服务信息--服务详细页面.
     * @param request
     * @param tagId
     * @param keyWord
     * @param usedStatus
     * @return
     */
    @At
    @Ok("jsp:sjdc.open.fw_detail")
    public Map<String,Object> toFwDetail(HttpServletRequest request,String fwCode) {
        Map<String,Object> result = new HashMap<String,Object>();
	   	result.put("fwCode", fwCode);

        return result;
    }

}
