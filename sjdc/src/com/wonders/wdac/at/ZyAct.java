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
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.wdac.entity.PZyInfo;
import com.wonders.wdac.entity.PfZyItem;
import com.wonders.wddc.suite.logger.service.LogCoreService;
import com.wonders.wddc.config.SessionFilter;
import com.wonders.wddc.suite.logger.service.LogCoreService;
import com.wonders.wddc.tiles.adaptor.util.ConUtils;
import com.wonders.wddc.tiles.jk.entity.User;
import com.wonders.wddc.tiles.tag.entity.TagInfoBo;
import com.wonders.wddc.tiles.tag.entity.TagTypeBo;

@At("/zy")
@IocBean(fields = "dao")
public class ZyAct {
	private Dao dao;
	@Inject
	private LogCoreService logService;
	 /**
	 * 资源列表,点击在线菜单目录,右边列表展示.
	 * 
	 * @return
	 */
	@At("/sourceList")
	@Ok("jsp:wdac.zy.compile_list")
	public Map<String, Object> querySourceList(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SessionFilter.SESSION_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = ConUtils.makeCri(request);
		if ("user".equals(user.getUserType())) {// 如果不是管理员
			cri.where().andEquals("zyUnit",user.getUnitName());
		}

		if ("admin".equals(user.getUserType())) {
			cri.where().andIn("status", "待提交", "待审核", "待发布","已发布");
		}
		List<PZyInfo> resourceList = dao.query(PZyInfo.class, cri);
		result.put("user", user);
		result.put("resourceList", resourceList);
		return result;
	}
	/**
	 * 跳转新增资源目录页面
	 */
	@At("/toCompileEdit")
	@Ok("jsp:wdac.zy.compile_edit")
	public void toCompileEdit(){
		
	}
	/**
	 * 资源目录新增或修改后保存
	 * @param PZyInfo
	 * @param zyItemList
	 * @param request
	 * @param opType
	 */
	@At
	@Ok("redirect:/zy/sourceList")
	public void saveBm(@Param("::PZyInfo") PZyInfo PZyInfo,@Param("::zyItemList") List<PfZyItem> zyItemList,
			HttpServletRequest request,String opType) {
		User user = (User) request.getSession().getAttribute(SessionFilter.SESSION_USER);
		Criteria cri = ConUtils.makeCri(request);
		//新增资源目录保存
		if("".equals(PZyInfo.getZyInfoId())){
			PZyInfo.setCreateTime(new Date());
			PZyInfo.setOpTime(new Date());
			PZyInfo.setUnitId(user.getUserId());
			PZyInfo.setItemCount(zyItemList.size());
			if("1".equals(opType)){
				PZyInfo.setStatus("待提交");
			}else{
				PZyInfo.setStatus("待审核");
			}
			dao.insert(PZyInfo);
		}
		//修改资源目录保存
		if(!"".equals(PZyInfo.getZyInfoId())){
			PZyInfo.setUnitId(user.getUserId());
			PZyInfo.setOpTime(new Date());
			if("1".equals(opType)){
				PZyInfo.setStatus("待提交");
			}else{
				PZyInfo.setStatus("待审核");
			}
			dao.update(PZyInfo);
			cri.where().and("zyInfoId","=",PZyInfo.getZyInfoId());
			List<PfZyItem> deleZyItemList = dao.query(PfZyItem.class, cri);
			//删除就的资源项信息
			for(PfZyItem deleZyItem : deleZyItemList){
				dao.delete(deleZyItem);
			}
		}
		//插入资源项信息
		for(PfZyItem zyItem : zyItemList ){
			if(!"".equals(zyItem)){
			zyItem.setZyInfoId(PZyInfo.getZyInfoId());
			dao.insert(zyItem);
			}
		}
	}
	/**
	 * 新增和修改资源目录
	 * @param zyInfoId
	 * @param request
	 * @return
	 */
	@At
	@Ok("jsp:wdac.zy.compile_edit")
	public Object toBmEdit(String zyInfoId, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute(SessionFilter.SESSION_USER);
			PZyInfo PZyInfo = dao.fetch(PZyInfo.class, Cnd.where("zyInfoId", "=", zyInfoId));
			String tagLists = null;
			if(!Strings.isEmpty(PZyInfo.getTagLists())){
				String[] tagList = PZyInfo.getTagLists().split(",");
				for(int i=0;i<tagList.length;i++){
					if(i==0){
						tagLists="'"+ tagList[i]+"'";
					}else{
						tagLists=tagLists+",'"+tagList[i]+"'";
					}
				}
				tagLists = "["+tagLists+"]";
			}
			List<PfZyItem> zyItemList = dao.query(PfZyItem.class, Cnd.where("zyInfoId", "=", zyInfoId));
			result.put("user", user);
			result.put("PZyInfo", PZyInfo);
			result.put("tagLists", tagLists);
			result.put("zyItemList", zyItemList);
			return result;
	}
	
	/**
	 * 删除资源主表和子表信息.
	 */
	@At
	@Ok("json")
	public void delResource(String zyInfoId) {
		if (!Strings.isEmpty(zyInfoId)) {
			PZyInfo PZyInfo = dao.fetch(PZyInfo.class, zyInfoId);
			if (PZyInfo != null) {
				List<PfZyItem> zyLitemList =
					dao.query(PfZyItem.class, Cnd.where("zyInfoId", "=",
							PZyInfo.getZyInfoId()));
				if(zyLitemList.size() >0){
					for(PfZyItem zyItem : zyLitemList){
						dao.delete(zyItem);
					}
				}
				dao.delete(PZyInfo);
			}
		}
	}
	
	 /**
	 * 资源列表,点击在线菜单目录,右边列表展示.
	 * 
	 * @return
	 */
	@At
	@Ok("jsp:wdac.zy.audit_list")
	public Map<String, Object> queryZyList(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SessionFilter.SESSION_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = ConUtils.makeCri(request);
		cri.where().andIn("status", "待审核");
		List<PZyInfo> resourceList = dao.query(PZyInfo.class, cri);
		result.put("user", user);
		result.put("resourceList", resourceList);
		return result;
	}
	/**
	 * 显示待发布资源目录信息
	 * @param request
	 * @return
	 */
	@At
	@Ok("jsp:wdac.zy.publish_list")
	public Map<String, Object> queryFbList(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SessionFilter.SESSION_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = ConUtils.makeCri(request);
		cri.where().andIn("status", "待发布");
		List<PZyInfo> resourceList = dao.query(PZyInfo.class, cri);
		result.put("user", user);
		result.put("resourceList", resourceList);
		return result;
	}
	/**
	 * 显示资源目录审批详细信息
	 * @param zyInfoId
	 * @return
	 */
	@At
	@Ok("jsp:wdac.zy.audit_edit")
	public Object toCheckResource(String zyInfoId) {
		Map<String, Object> result = new HashMap<String, Object>();
		PZyInfo PZyInfo = dao.fetch(PZyInfo.class, zyInfoId);
		List<PfZyItem> zyItemList = dao.query(PfZyItem.class, Cnd.where("zyInfoId", "=", zyInfoId));
		result.put("PZyInfo", PZyInfo);
		result.put("zyItemList", zyItemList);
		return result;
	}
	/**
	 * 资源目录审批信息保存
	 * @param PZyInfo
	 * @param request
	 */
	@At
	@Ok("redirect:/zy/queryZyList")
	public void saveAudit(@Param("::PZyInfo") PZyInfo PZyInfo,
			HttpServletRequest request) {
		PZyInfo newPZyInfo = dao.fetch(PZyInfo.class, PZyInfo.getZyInfoId());
		newPZyInfo.setAuditReason(PZyInfo.getAuditReason());
		newPZyInfo.setAuditResult(PZyInfo.getAuditResult());
		newPZyInfo.setOpTime(new Date());
		if("审核不通过".equals(PZyInfo.getAuditResult())){
			newPZyInfo.setStatus("待提交");
		}else{
			newPZyInfo.setStatus("待发布");
		}
		dao.update(newPZyInfo);
	}
	/**
	 * 发布资源目录
	 * @param zyInfoId
	 * @param request
	 */
	@At
	@Ok("json")
	public void releaseZy(String zyInfoId,HttpServletRequest request) {
		if (!Strings.isEmpty(zyInfoId)) {
			PZyInfo PZyInfo = dao.fetch(PZyInfo.class, zyInfoId);
			if (PZyInfo != null) {
				PZyInfo.setOpTime(new Date());
				PZyInfo.setStatus("已发布");
				dao.update(PZyInfo);
			}
		}
	}
	/**
	 * 资源目录首页跳转详细信息
	 * @param zyInfoId
	 * @param type
	 * @return
	 */
	@At
	@Ok("jsp:wdac.zy.zy_Info")
	public Object toDetail(String zyInfoId,String type) {
		Map<String, Object> result = new HashMap<String, Object>();
		PZyInfo PZyInfo = dao.fetch(PZyInfo.class, zyInfoId);
		List<PfZyItem> zyItemList = dao.query(PfZyItem.class, Cnd.where("zyInfoId", "=", zyInfoId));
		result.put("type", type);
		result.put("PZyInfo", PZyInfo);
		result.put("zyItemList", zyItemList);
		return result;
	}
	/**
	 * 
	 * @param request
	 * @param tableName
	 * @return
	 */
	@At
	@Ok("json")
	public Map<String,Object> getTableName(HttpServletRequest request,String tableName){
		Map<String,Object> result = new HashMap<String,Object>();
		Criteria cri = ConUtils.makeCri(request);
		cri.where().and("zyTable","=",tableName);
		PZyInfo zyInfo = dao.fetch(PZyInfo.class,cri);
		List<PfZyItem> zyItemList = dao.query(PfZyItem.class, Cnd.where("zyInfoId", "=", zyInfo.getZyInfoId()));
		result.put("zyItemList", zyItemList);
		return result;
	}
}
