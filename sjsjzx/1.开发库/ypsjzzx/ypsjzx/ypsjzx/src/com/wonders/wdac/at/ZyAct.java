package com.wonders.wdac.at;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.wdac.config.filter.SessionFilter;
import com.wonders.wdac.entity.PZyInfo;
import com.wonders.wdac.entity.PfZyItem;
import com.wonders.wddc.suite.logger.service.LogService;
import com.wonders.wddc.tiles.adaptor.util.ConUtils;
import com.wonders.wddc.tiles.jk.entity.User;
import com.wonders.wddc.tiles.tag.entity.TagInfoBo;
import com.wonders.wddc.tiles.tag.entity.TagTypeBo;

@At("/zy")
@IocBean(fields = "dao")
public class ZyAct {
	private Dao dao;
	@Inject
	private LogService logService;
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

	@At("/toCompileEdit")
	@Ok("jsp:wdac.zy.compile_edit")
	public void toCompileEdit(){
		
	}
	
	@At
	@Ok("redirect:/zy/sourceList")
	public void saveBm(@Param("::PZyInfo") PZyInfo PZyInfo,@Param("::zyItemList") List<PfZyItem> zyItemList,
			HttpServletRequest request,String opType) {
		User user = (User) request.getSession().getAttribute(SessionFilter.SESSION_USER);
		Criteria cri = ConUtils.makeCri(request);
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
	
	@At
	@Ok("redirect:/zy/queryZyList")
	public void saveAudit(@Param("::PZyInfo") PZyInfo PZyInfo,
			HttpServletRequest request) {
		PZyInfo newPZyInfo = dao.fetch(PZyInfo.class, PZyInfo.getZyInfoId());
		Date opTime = new Date();
		if("审核不通过".equals(PZyInfo.getAuditResult())){
			newPZyInfo.setAuditReason(PZyInfo.getAuditReason());
			newPZyInfo.setAuditResult(PZyInfo.getAuditResult());
			newPZyInfo.setOpTime(opTime);
			newPZyInfo.setStatus("待提交");
		}else{
			newPZyInfo.setAuditReason(PZyInfo.getAuditReason());
			newPZyInfo.setAuditResult(PZyInfo.getAuditResult());
			newPZyInfo.setOpTime(opTime);
			newPZyInfo.setStatus("待发布");
		}
		dao.update(newPZyInfo);
	}
	
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
	
	@At
	@Ok("jsp:wdac.zy.zy_index")
	public Map<String,Object> toZyIndex(String tagId,String keyWord){
		Map<String,Object> result = new HashMap<String,Object>();
        //放入标签类型
        List<TagTypeBo> tagTypeList = dao.query(TagTypeBo.class, Cnd.where("catalog","=","11"));
        result.put("typeList", tagTypeList);
        //放入catalog为"11"的页面标签集合
        result.put("tagList", tagList());
        result.put("keyWord", keyWord);
        //放入资源数据
        result.put("zyInfoList",zyInfoList(tagId,keyWord));
        return result;
	}
	
	public List<TagInfoBo> tagList(){
    	List<TagInfoBo> tagList = dao.query(TagInfoBo.class, Cnd.where("catalog", "=","11").getOrderBy().asc("orders"),null);
    	return tagList;
    }
	
	 public List<PZyInfo> zyInfoList(String tagId,String keyWord){
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
