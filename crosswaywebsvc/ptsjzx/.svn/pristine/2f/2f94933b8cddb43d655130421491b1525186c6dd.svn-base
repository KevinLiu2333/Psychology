package com.wonders.zymlgx.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.Constants;
import com.wonders.db.entity.Xdb_files;
import com.wonders.log.entity.OperateLog;
import com.wonders.qxkz.QXConstants;
import com.wonders.sjtb.entity.TbColumns;
import com.wonders.sjtb.entity.TbSheet;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.dic.entity.DicDept;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.utils.CookieUtils;
import com.wonders.zymlgl.entity.Resource;
import com.wonders.zymlgl.entity.ResourceDetails;
import com.wonders.zymlgl.service.ApplyResourceService;
import com.wonders.zymlgx.entity.ResourceApply;
import com.wonders.zymlgx.entity.ResourceApplyDetails;
import com.wonders.zymlgx.service.IncrementNumService;
import com.wonders.zymlgx.service.QueryData;

@IocBean
@At("/zymlgx")
public class ZymlgxAt {
	@Inject
	private Dao dao;
	@Inject
	private QueryData queryData;
	
	@Inject
	private ApplyResourceService applyResourceService;
	
	@Inject
	private IncrementNumService incrementNumService;
	
	/**
	 * 资源目录-申请列表.
	 */
	@At
	@Ok("jsp:jsp.zymlgx.apply.apply-list")
	public Object toResourceApplyList(HttpServletRequest request, HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = ConUtils.makeCri(request);
		cri.where().andEquals("isSubmit", "1");
		User user = (User) session.getAttribute(Constants.USER);
		cri.getOrderBy().desc("applyDate");
		//如果是各委办领导
		if("1".equals(user.getType())||"2".equals(user.getType())){
			List<User> userList = dao.query(User.class, Cnd.where("dept", "=", user.getDept()));
			String[] userIds = new String[userList.size()];
			int index = 0;
			for(User u : userList){
				userIds[index] = u.getUserId();
				index ++;
			}
			cri.where().andIn("userId", userIds);
		}else{
			cri.where().andEquals("userId", user.getUserId());
		}
		Pager pager =ConUtils.makePaginationPager(request);
		List<ResourceApply> resourceApplyList = dao.query(ResourceApply.class, cri, pager);
		pager.setRecordCount(dao.count(ResourceApply.class, cri));
		result.put("resourceApplyList", resourceApplyList); 
		result.put("pager", pager);
		return result;
	}
	
	/**
	 * 修改资源申请信息.
	 */
	@At
	@Ok("jsp:jsp.zymlgx.apply.apply-edit")
	public Object editApply(String applyId){
		Map<String, Object> result = new HashMap<String, Object>();
		ResourceApply resourceApply = dao.fetch(ResourceApply.class, applyId);
		result.put("resourceApply", resourceApply);
		return result;
	}
	
	/**
	 * 首页进入资源目录申请的申请说明页面.
	 */
	@At
	@Ok("jsp:jsp.zymlgx.apply.apply-introduce")
	public Object toApplyView(String resourceId){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resourceId", resourceId);
		return result;
	}
	
	@At
	@Ok("jsp:jsp.zymlgx.apply.item-select")
	public Object toApiItemSelect(HttpServletRequest request, String resourceId, String applyId){
		Criteria cri =  ConUtils.makeCri(request);
		cri.where().andEquals("resourceId", resourceId);
		List<ResourceDetails> resourceList = dao.query(ResourceDetails.class, cri);
		Map<String, Object> result = new HashMap<String, Object>();
		//数据对象
		result.put("resourceId", resourceId);
		if(!Strings.isEmpty(applyId)){
			result.put("applyId", applyId);
		}
		result.put("resourceList", resourceList);
		return result;
	}
	
	/**
	 * 进入填写申请api页面.
	 */
	@At
	@Ok("jsp:jsp.zymlgx.apply.apply-edit")
	public Object toEditApply(String detailsIds, String resourceId, String applyId, HttpServletRequest request, HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String userName = CookieUtils.getCookie(request, SystemConstants.DISPLAY_NAME);
		result.put("userName", userName);
		User user = (User) session.getAttribute(Constants.USER);
		DicDept dept = dao.fetch(DicDept.class,user.getDept());
		String date = sdf.format(new Date());
		Resource tempResource = dao.fetch(Resource.class, resourceId);
		
		if(Strings.isEmpty(applyId)){
			//>>>>资源申请主表
			ResourceApply resourceApply = new ResourceApply(); 
			resourceApply.setUserId(user.getUserId());
			
			resourceApply.setResourceType(tempResource.getResourceType());
			resourceApply.setResourceName(tempResource.getResourceName());
			resourceApply.setResourceId(resourceId);
			Resource resource = dao.fetch(Resource.class, Cnd.where("resourceId", "=", resourceId));
			if(resource != null){
				resourceApply.setResourceProvider(resource.getProvideDepId());
			}		
			dao.insert(resourceApply);
			//>>>>资源申请子表
			ResourceApplyDetails applyDetails = null;
			String[] detailsIdsStr = detailsIds.split(",");
			for(String detailsId : detailsIdsStr){
				applyDetails = new ResourceApplyDetails(); 
				applyDetails.setResourceDetailsId(detailsId);
				applyDetails.setApplyId(resourceApply.getApplyId());
				dao.insert(applyDetails);
			}
			result.put("resourceApply", resourceApply);
			
		}else{//>>>>申请的资源被退回修改
			ResourceApply resourceApply = dao.fetch(ResourceApply.class, applyId);
			resourceApply.setUserId(user.getUserId());
			resourceApply.setResourceType(tempResource.getResourceType());
			resourceApply.setResourceName(tempResource.getResourceName());
			resourceApply.setResourceId(resourceId);
			Resource resource = dao.fetch(Resource.class, Cnd.where("resourceId", "=", resourceId));
			if(resource != null){
				resourceApply.setResourceProvider(resource.getProvideDepId());
			}
			dao.update(resourceApply);
			
			//>>>>修改申请资源子表
			//先删除子表申请的old信息
			List<ResourceApplyDetails> resourceApplyDetailsList = dao.query(ResourceApplyDetails.class, Cnd.where("applyId", "=", applyId));
			for(ResourceApplyDetails oldResourceApplyDetails : resourceApplyDetailsList){
				dao.delete(oldResourceApplyDetails);
			}
			//重新增加修改后的资源项信息
			String[] detailsIdsStr = detailsIds.split(",");
			ResourceApplyDetails newResourceApplyDetails = null;
			for(String detailsId : detailsIdsStr){
				newResourceApplyDetails = new ResourceApplyDetails();
				newResourceApplyDetails.setResourceDetailsId(detailsId);
				newResourceApplyDetails.setApplyId(applyId);
				dao.insert(newResourceApplyDetails);
			}
			
			result.put("resourceApply", resourceApply);
		}
		
		result.put("date", date);
		result.put("applyDept", dept.getDeptCode());
		result.put("resourceId", resourceId);
		return result;
	}
	
	@At
	@Ok("jsp:jsp.zymlgx.apply.apply-result")
	public Object toApplyAgreement(@Param("::resourceApply") ResourceApply resourceApply, HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(Constants.USER);
		DicDept dept = dao.fetch(DicDept.class,user.getDept());
		
		//申请的资源项
		List<String> resourceItems = null;
		//普遍共享
		List<String> ptgx = null;
		//按需共享
		List<String> axgx = null;
		
		ResourceApply tempResourceApply = null;
		if(!Strings.isEmpty(resourceApply.getApplyId())){
			tempResourceApply = dao.fetch(ResourceApply.class, Cnd.where("applyId", "=", resourceApply.getApplyId()));
			if(tempResourceApply != null){
				tempResourceApply.setApplyTopic(resourceApply.getApplyTopic());
				tempResourceApply.setApplyReason(resourceApply.getApplyReason());
				tempResourceApply.setResourceProvider(resourceApply.getResourceProvider());
				tempResourceApply.setUserId(user.getUserId());
				tempResourceApply.setLinkmanPhone(resourceApply.getLinkmanPhone());
				tempResourceApply.setApplyDate(new Date()); 
				tempResourceApply.setFileIds(resourceApply.getFileIds());
				tempResourceApply.setResourceType(resourceApply.getResourceType());
				tempResourceApply.setLinkmanMail(resourceApply.getLinkmanMail());
				tempResourceApply.setStatus(Constants.STATUS_APPLY_UNCHECKED);
				tempResourceApply.setOpUser("1");
				resourceItems = applyResourceService.getResourcItemsByapplyId(resourceApply.getApplyId());
				ptgx = applyResourceService.getPbgxByApplyId(resourceApply.getApplyId());
				axgx = applyResourceService.getAxgxByApplyId(resourceApply.getApplyId());
				if(axgx.size()==0){
					tempResourceApply.setStatus(Constants.STATUS_APPLYED_NUCHECKED); 
				}
				dao.update(tempResourceApply);
			}
			
		}
		
		if(!Strings.isEmpty(resourceApply.getFileIds())){
			Criteria cri2 = Cnd.cri();
			cri2.where().and("fid", "=", resourceApply.getFileIds());
			Xdb_files file = dao.fetch(Xdb_files.class, cri2);
			result.put("file", file.getFilenamelocal().subSequence(0, file.getFilenamelocal().indexOf(".doc")));
		}
		//资源类型
		String resourceType = DicDataUtils.getInstance().getDicItemName(1054, resourceApply.getResourceType());
		
		result.put("resourceType", resourceType);
		result.put("resourceItems", resourceItems.size() > 0 ? resourceItems : "");
		result.put("ptgx", ptgx.size() > 0 ? ptgx : "");
		result.put("axgx", axgx.size() > 0 ? axgx : "");
		result.put("resourceApply", resourceApply);
		result.put("applyDept", dept.getDeptCode());
		return result;
	}
	
	/**
	 * 将集合转为String在页面显示.
	 */
	public String getChineseByList(List<String> list){
		if(list != null && list.size() > 0){
			StringBuffer buf = new StringBuffer();
			for(int i=0; i<list.size(); i++){
				buf.append(list.get(i)).append("、");
			}
			return buf.deleteCharAt(buf.length()-1).toString();
		}else{
			return null;
		}
	}
	
	/**
	 * 申请提交操作.
	 */
	@At
	@Ok("json")
	public String submit(String applyId, HttpServletRequest request,HttpSession session){
		String flag = "0";
		Criteria cri = Cnd.cri();
		cri.where().and("applyId", "=", applyId);
		ResourceApply resourceApply = dao.fetch(ResourceApply.class, cri);
		if(resourceApply != null){
			resourceApply.setIsSubmit("1"); 
			dao.update(resourceApply);
			flag = "1";
		}
		//提交时进行日志记录
		User user = (User) session.getAttribute(Constants.USER);
		
		OperateLog log = new OperateLog();
		log.setOperateDate(new Date());
		log.setOperateType("数据资源项申请");
		log.setOperateResult("申请成功");
		log.setOperateUser(user.getDisplayName());
		dao.insert(log);
		return flag;
	}
	
	/**
	 * 提交审核后重新加载到查看结果页面.
	 * @param isPrint 是否打印标记
	 */
	@At
	@Ok("redirect:/zymlgx/toResourceApplyList")
	public void reloadAgreement(String applyId, String isPrint, HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
	
		//申请的资源项
		List<String> resourceItems = null;
		//普遍共享
		List<String> ptgx = null;
		//按需共享
		List<String> axgx = null;
		if(!Strings.isEmpty(applyId)){
			ResourceApply resourceApply = dao.fetch(ResourceApply.class, Cnd.where("applyId", "=", applyId));
			if(!Strings.isEmpty(resourceApply.getResourceId())){
				Resource resource = dao.fetch(Resource.class, resourceApply.getResourceId());
				if("1".equals(resource.getShareProperty())){
					resourceApply.setStatus(Constants.STATUS_APPLY_PROVIDE_DEMO);
				}
			}
			resourceApply.setIsSubmit("1");
			dao.update(resourceApply);
			result.put("resourceApply", resourceApply);
			//资源类型
			String resourceType = DicDataUtils.getInstance().getDicItemName(1054, resourceApply.getResourceType());
			result.put("resourceType", resourceType);
			
			resourceItems = applyResourceService.getResourcItemsByapplyId(resourceApply.getApplyId());
			ptgx = applyResourceService.getPbgxByApplyId(resourceApply.getApplyId());
			axgx = applyResourceService.getAxgxByApplyId(resourceApply.getApplyId());
			result.put("resourceItems", resourceItems.size() > 0 ? resourceItems : "");
			result.put("ptgx", ptgx.size() > 0 ? ptgx : "");
			result.put("axgx", axgx.size() > 0 ? axgx : "");
			if(!Strings.isEmpty(resourceApply.getFileIds())){
				Criteria cri2 = Cnd.cri();
				cri2.where().and("fid", "=", resourceApply.getFileIds());
				Xdb_files file = dao.fetch(Xdb_files.class, cri2);
				result.put("file", file.getFilenamelocal().subSequence(0, file.getFilenamelocal().indexOf(".doc")));
			}
		}
		
		User user = (User) session.getAttribute(Constants.USER);
		DicDept dept = dao.fetch(DicDept.class,user.getDept());
		result.put("applyDept", dept.getDeptCode());
				
//		if("1".equals(isPrint)){
//			return new ViewWrapper(new JspView("jsp.zymlgx.apply.print-view"), result);
//		}else{
//			
//			return new ViewWrapper(new JspView("jsp.zymlgx.apply.resource-apply-result"), result);
//		}
	}
	
	/**
	 * 资源申请-查看
	 */
	@At
	@Ok("jsp:jsp.zymlgx.apply.apply-view")
	public Map<String,Object> viewApply(@Param("applyId")String applyId){
		Map<String, Object> result= new HashMap<String, Object>();
		ResourceApply resourceApply = dao.fetch(ResourceApply.class, applyId);
		List<String> resourceNameList = new ArrayList<String>();
		result.put("resourceApply", resourceApply);
		List<ResourceApplyDetails> resourceApplyDetailsList = dao.query(ResourceApplyDetails.class, Cnd.where("applyId", "=", applyId));
		for(ResourceApplyDetails resourceApplyDetails : resourceApplyDetailsList){
			String resourceDetailsId = resourceApplyDetails.getResourceDetailsId();
			List<ResourceDetails> resourceDetailsList = dao.query(ResourceDetails.class, Cnd.where("detailsId", "=", resourceDetailsId));
			for(ResourceDetails resourceDetails : resourceDetailsList){
				resourceNameList.add(resourceDetails.getDataItemName());
			}
		}
		User user = dao.fetch(User.class, resourceApply.getUserId());
		result.put("resourceNameList", resourceNameList);
		result.put("resourceApply", resourceApply);
		result.put("applyuser", user);
		return result;
	}
	
	/**
	 * 资源目录-审核列表.
	 */
	@At
	@Ok("jsp:jsp.zymlgx.check.check-list")
	public Object checkResourceApply(HttpServletRequest request ,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = ConUtils.makeCri(request);
		Pager pager =ConUtils.makePaginationPager(request);
		cri.where().andEquals("isSubmit", "1");
		cri.getOrderBy().desc("applyDate");
		User user = (User) session.getAttribute(Constants.USER);
//		List<ResourceApply> resourceApplyList = dao.query(ResourceApply.class, cri, pager);
//		cri.where().andEquals("resourceProvider", user.getDept());
		if("1".equals(user.getType())){
			cri.where().andIn("status", Constants.STATUS_APPLY_UNCHECKED,Constants.STATUS_APPLY_NOPASS,Constants.STATUS_APPLYED_NUCHECKED);
			cri.where().andEquals("resourceProvider", user.getDept());
		}else if("2".equals(user.getType())){
			cri.where().andIn("status",Constants.STATUS_APPLY_NOPASS,Constants.STATUS_APPLYED_NUCHECKED,Constants.STATUS_APPLY_PROVIDE_DEMO);
		}
		List<ResourceApply> resourceApplyList = dao.query(ResourceApply.class, cri, pager);
		
		for(ResourceApply resourceApply : resourceApplyList){
			if(!Strings.isEmpty(resourceApply.getUserId())){
				User applyUser = dao.fetch(User.class, resourceApply.getUserId());
				if(applyUser != null){
					resourceApply.setUserId(applyUser.getDept());
				}
			}
		}
		
		pager.setRecordCount(dao.count(ResourceApply.class, cri));
		result.put("user", user);
		result.put("resourceApplyList", resourceApplyList);
		result.put("pager", pager);
		return result;
	}
	
	/**
	 * 资源申请-审核.
	 */
	@At
	@Ok("jsp:jsp.zymlgx.check.apply-check")
	public Object auditApply(String applyId, HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		ResourceApply resourceApply = dao.fetch(ResourceApply.class, Cnd.where("applyId", "=", applyId));
		List<String> resourceNameList = new ArrayList<String>();
		List<ResourceApplyDetails> resourceApplyDetailsList = dao.query(ResourceApplyDetails.class, Cnd.where("applyId", "=", applyId));
		for(ResourceApplyDetails resourceApplyDetails : resourceApplyDetailsList){
			String resourceDetailsId = resourceApplyDetails.getResourceDetailsId();
			List<ResourceDetails> resourceDetailsList = dao.query(ResourceDetails.class, Cnd.where("detailsId", "=", resourceDetailsId));
			for(ResourceDetails resourceDetails : resourceDetailsList){
				resourceNameList.add(resourceDetails.getDataItemName());
			}
		}
		User user = (User) session.getAttribute(Constants.USER);
		resourceApply.setCheckedPerson(user.getDisplayName());
		User user1 = dao.fetch(User.class, resourceApply.getUserId());
		
		result.put("applyuser", user1);
		result.put("user", user);
		result.put("resourceNameList", resourceNameList);
		result.put("resourceApply", resourceApply);
		return result;
	}
	
	@At
    @Ok("redirect:/zymlgx/checkResourceApply")
	public void saveApplyAudit(@Param("::resourceApply") ResourceApply resourceApply, HttpServletRequest request,HttpSession session){
		if(!Strings.isEmpty(resourceApply.getApplyId())){
			User user = (User) session.getAttribute(Constants.USER);
			// 申请编号前缀
			String applyNumPrefix = IncrementNumService.APPLY_NUMBER_PREFIX + user.getDept();
			
			ResourceApply tempResourceApply = dao.fetch(ResourceApply.class, resourceApply.getApplyId());
			if(tempResourceApply != null){
				tempResourceApply.setCheckedPerson(resourceApply.getCheckedPerson());
				tempResourceApply.setCheckedLinkmanPhone(resourceApply.getCheckedLinkmanPhone());
				tempResourceApply.setStatus(resourceApply.getStatus());
				if("1".equals(user.getType())){ 
					//设置下一步又科委审核
					tempResourceApply.setOpUser("2");
				}else if("2".equals(user.getType())){
					if(Constants.STATUS_APPLY_PROVIDE_DEMO.equals(resourceApply.getStatus())){
						tempResourceApply.setStatus(Constants.STATUS_APPLY_PROVIDE_DEMO);
						// 申请成功，设置编号
						tempResourceApply.setAppApplyNum(applyNumPrefix + "_" + incrementNumService.getNum(IncrementNumService.ID_NAME));
					}
					if(Constants.STATUS_APPLY_NOPASS.equals(resourceApply.getStatus())){
						tempResourceApply.setStatus(Constants.STATUS_APPLY_NOPASS);
					}
				}
				
				tempResourceApply.setNoPassReason(resourceApply.getNoPassReason());
				dao.update(tempResourceApply);
				
				OperateLog log = new OperateLog();
				log.setOperateDate(new Date());
				log.setOperateUser(user.getDisplayName());
				if("1".equals(user.getType())){
					if(Constants.STATUS_APPLY_NOPASS.equals(resourceApply.getStatus())){
						log.setOperateType("退回修改");
					}
					if(Constants.STATUS_APPLYED_NUCHECKED.equals(resourceApply.getStatus())){
						log.setOperateType("申请成功"); 
					}
					log.setOperateResult("委办局领导审核成功");
				}
				if("2".equals(user.getType())){
					if(Constants.STATUS_APPLY_NOPASS.equals(resourceApply.getStatus())){
						log.setOperateType("退回修改");
					}
					if(Constants.STATUS_APPLY_PROVIDE_DEMO.equals(resourceApply.getStatus())){
						log.setOperateType("申请成功"); 
					}
					log.setOperateResult("科委审核成功");
				}
			}
		}
	}
	
	/**
	 * 资源申请-查看.
	 */
	@At
	@Ok("jsp:jsp.zymlgx.check.apply-view")
	public Object queryApply(String applyId, HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> resourceNameList = new ArrayList<String>();
		if(!Strings.isEmpty(applyId)){
			ResourceApply resourceApply = dao.fetch(ResourceApply.class, Cnd.where("applyId", "=", applyId));
			result.put("resourceApply", resourceApply);
			List<ResourceApplyDetails> resourceApplyDetailsList = dao.query(ResourceApplyDetails.class, Cnd.where("applyId", "=", applyId));
			for(ResourceApplyDetails resourceApplyDetails : resourceApplyDetailsList){
				String resourceDetailsId = resourceApplyDetails.getResourceDetailsId();
				ResourceDetails resourceDetail = dao.fetch(ResourceDetails.class, Cnd.where("detailsId", "=", resourceDetailsId));
				if(resourceDetail!=null){
					resourceNameList.add(resourceDetail.getDataItemName());
				}
			}
			result.put("resourceNameList", resourceNameList);
			User user1 = dao.fetch(User.class, resourceApply.getUserId());
			result.put("applyuser", user1);
		}
		User user = (User) session.getAttribute(Constants.USER);
		result.put("user", user);
		
		return result;
	}
	
	/**
	 * 资源目录-数据预览.
	 * @param contentStr  资源详细信息IDS
	 * @param resourceId  资源目录主键
	 * @return
	 */
	@At
	@Ok("jsp:jsp.zymlgx.apply.data-view")
	public Object dataPreView(String contentStr, String resourceId){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if(!Strings.isEmpty(resourceId)){
			Resource resource = dao.fetch(Resource.class, resourceId);
			String tableName = resource.getTableName();
			if(!Strings.isEmpty(tableName)){
				jsonMap = getData(tableName);
			}
		}
		return result;
	} 
	
	private Map<String, Object> getData(String tableName) {
		String sql = "select "  ;
		Sql exeSql = Sqls.create(sql);
		return null;
	}

	
	/**
	 * 资源获取.
	 */
	@At
	@Ok("jsp:jsp.zymlgx.achieve.resource-list")
	public Object toGetResource(HttpServletRequest request, HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = ConUtils.makeCri(request);
		cri.where().andEquals("isSubmit", "1");
		User user = (User) session.getAttribute(Constants.USER);
		cri.where().andEquals("userId", user.getUserId());
		cri.getOrderBy().desc("applyDate");
		Pager pager =ConUtils.makePaginationPager(request);
		List<ResourceApply> resourceApplyList = dao.query(ResourceApply.class, cri, pager);
		pager.setRecordCount(dao.count(ResourceApply.class, cri));
		result.put("resourceApplyList", resourceApplyList); 
		result.put("pager", pager);
		return result;
	}
	
	
	/**
	 * 资源数据统计
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.zymlgx.count")
	public Object toCount(){
		Map<String, Object> result =new HashMap<String, Object>();
		List<String> deptlist =new ArrayList<String>();
		Sql sql =Sqls.create("select ST_DEPT_CODE from DIC_MUNICIPAL_DEPT where IS_TB_DEPT = '1' order by ST_DEPT_CODE asc");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				List<String> list =new ArrayList<String>();
				while(rs.next()){
					list.add(rs.getString(1));
				}
				return list;
			}
		});
		dao.execute(sql);
        deptlist =(List<String>) sql.getResult();
		List<String> typelist =new ArrayList<String>();
		typelist.add("r_frl");
		typelist.add("r_rkl");
		typelist.add("r_cyjjl");
		DicDataUtils dicutil = DicDataUtils.getInstance();
		List<Map<String, Object>> deptcount =new ArrayList<Map<String,Object>>();
		int zzy =0;
		int zsj =0;
        for(String dept:deptlist){
        	if(!Strings.isEmpty(dept)){
        	  int zycount = 0;
    		  int sjcount = 0;
        	  Map<String, Object> map =new HashMap<String, Object>();
        	  String dept1 =dicutil.getDicData(1003, dept);
  			  String dept2 =dept1.replaceAll("普陀区", "");
  			  map.put("dept", dept2);
              for(String type:typelist){
        			Criteria cri =Cnd.cri();
        			cri.where().andEquals("validity", "1");
        			cri.where().and("PROVIDE_DEP_ID", "=", dept);
        			cri.where().and("RESOURCE_TYPE", "=", type);
        			int count =dao.count(Resource.class, cri);
        			zycount +=  count;
        			map.put(type, count);
        			List<Resource> resource =dao.query(Resource.class, cri);
        			int b=0;
        			for(Resource re:resource){
        				int a =dao.count(ResourceDetails.class, Cnd.where("RESOURCE_ID", "=", re.getResourceId()));
        				b=b+a;
        			}
        			sjcount +=b;
        			map.put(type+"_sjx", b);
        	 }
             zzy =zzy+zycount;
             zsj =zsj+sjcount;
        	 map.put("zycount", zycount);
        	 map.put("sjcount", sjcount);
             deptcount.add(map);
        	}
        	
        }
        List<String> countlist =new ArrayList<String>();
        for(String type:typelist){
        	Criteria cri1 =Cnd.cri();
        	cri1.where().andEquals("validity", "1");
			cri1.where().and("RESOURCE_TYPE", "=", type);
			String zcount =String.valueOf(dao.count(Resource.class, cri1));
			countlist.add(zcount);
			List<Resource> resource =dao.query(Resource.class, cri1);
			int c =0;
			for(Resource res:resource){
				int a =dao.count(ResourceDetails.class, Cnd.where("RESOURCE_ID", "=", res.getResourceId()));
				c=c+a;
			}
			countlist.add(String.valueOf(c));	
        }
        result.put("countlist", countlist);
        result.put("zzy", zzy);
        result.put("zsj", zsj);
        result.put("deptcount", deptcount);
		return result;
	}
	
	@At
	@Ok("json")
	public void deleteApply(String applyid){
		ResourceApply apply=dao.fetch(ResourceApply.class,applyid);
		apply.setIsSubmit("0");
		dao.update(apply);
	}
	
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.zymlgx.viewdata")
	public Map<String, Object> seeData(String applyId,HttpServletRequest request){
		Map<String, Object> result =new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		ResourceApply resourceApply = dao.fetch(ResourceApply.class, applyId);
		StringBuffer buffer =new StringBuffer();
		List<String> itemlist =new ArrayList<String>();
		List<ResourceApplyDetails> resourceApplyDetailsList = dao.query(ResourceApplyDetails.class, Cnd.where("applyId", "=", applyId));
		for(ResourceApplyDetails resourceApplyDetails : resourceApplyDetailsList){
			String resourceDetailsId = resourceApplyDetails.getResourceDetailsId();
			ResourceDetails details =dao.fetch(ResourceDetails.class,resourceDetailsId);
			itemlist.add(details.getDataItemName());
		}
		result.put("itemlist", itemlist);
		result.put("name", resourceApply.getResourceName());
		List<TbSheet> sheet=dao.query(TbSheet.class, Cnd.where("SHEET_NAME", "=", resourceApply.getResourceName()));
		String tablename="";
		List<String> columnit=new ArrayList<String>();
		String itemdm="data_year";
		if(sheet.size()>0){
			String tablebm=sheet.get(0).getTabelBm();
			int sheetid=sheet.get(0).getOrderNo();
			for(String item:itemlist){
				Criteria cri =Cnd.cri();
				cri.where().and("TABLE_BM", "=", tablebm);
				cri.where().and("SHEET_ID", "=", sheetid);
				cri.where().and("COLUMN_COMMENT", "=", item);
			    List<TbColumns> column=dao.query(TbColumns.class, cri);
			    if(column.size()>0){
			    	itemdm=column.get(0).getColumnName();
			    	columnit.add(itemdm);
			    	tablename=column.get(0).getTableName();
			    	buffer.append(itemdm).append(",");
			    }
			}
		}else{
			result.put("flag", "0");
		}
		columnit.add("data_year");
		columnit.add("id");
		result.put("column", columnit);
		final List<String> columnitem=new ArrayList<String>();
		List<Map<String, Object>> listdata =new ArrayList<Map<String,Object>>();
		columnitem.addAll(columnit);
		String items=buffer.append("data_year").append(",id").toString();
		if(!Strings.isEmpty(tablename)){
			Sql sql=Sqls.create("select "+items+" from "+tablename+" where "+itemdm+" is not null");
			sql.setPager(pager);
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
					while(rs.next()){
						Map< String, Object> map=new LinkedHashMap<String, Object>();
						for(String clo:columnitem){
							map.put(clo, rs.getString(clo));
						}
						list.add(map);
					}
					return list;
				}
			});
			dao.execute(sql);
			listdata=(List<Map<String, Object>>) sql.getResult();
			Sql sql1=Sqls.create("select count(1) count from "+tablename+" where "+itemdm+" is not null");
			sql1.setCallback(new SqlCallback() {
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					while(rs.next()){
						return rs.getInt("count");
					}
					return 0;
				}
			});
			dao.execute(sql1);
			pager.setRecordCount((Integer) sql1.getResult());
			result.put("flag", "1");
		}
		result.put("pager", pager);
		result.put("applyId", applyId);
		result.put("listdata", listdata);
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.zymlgx.datadetails")
	public Map<String, Object> viewData(String applyId,String dataId){
		Map< String, Object> result =new HashMap<String, Object>();
		ResourceApply resourceApply = dao.fetch(ResourceApply.class, applyId);
		StringBuffer buffer =new StringBuffer();
		List<String> itemlist =new ArrayList<String>();
		List<ResourceApplyDetails> resourceApplyDetailsList = dao.query(ResourceApplyDetails.class, Cnd.where("applyId", "=", applyId));
		for(ResourceApplyDetails resourceApplyDetails : resourceApplyDetailsList){
			String resourceDetailsId = resourceApplyDetails.getResourceDetailsId();
			ResourceDetails details =dao.fetch(ResourceDetails.class,resourceDetailsId);
			itemlist.add(details.getDataItemName());
		}
		List<TbSheet> sheet=dao.query(TbSheet.class, Cnd.where("SHEET_NAME", "=", resourceApply.getResourceName()));
		String tablename="";
		List<TbColumns> tbcolumn= new ArrayList<TbColumns>();
		if(sheet.size()>0){
			String tablebm=sheet.get(0).getTabelBm();
			int sheetid=sheet.get(0).getOrderNo();
			for(String item:itemlist){
				Criteria cri =Cnd.cri();
				cri.where().and("TABLE_BM", "=", tablebm);
				cri.where().and("SHEET_ID", "=", sheetid);
				cri.where().and("COLUMN_COMMENT", "=", item);
			    List<TbColumns> column=dao.query(TbColumns.class, cri);
			    if(column.size()>0){
			    	String itemdm=column.get(0).getColumnName();
			    	tbcolumn.addAll(column);
			    	tablename=column.get(0).getTableName();
			    	buffer.append(itemdm).append(",");
			    }
			}
		}
		final List<TbColumns> tbcolumns= new ArrayList<TbColumns>();
		tbcolumns.addAll(tbcolumn);
		List<Map<String, Object>> listdata =new ArrayList<Map<String,Object>>();
		String items=buffer.deleteCharAt(buffer.length()-1).toString();
		if(!Strings.isEmpty(tablename)){
			Sql sql=Sqls.create("select "+items+" from "+tablename+" where id = '"+dataId+"'");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
					while(rs.next()){
						Map< String, Object> map=new HashMap<String, Object>();
//						for(String clo:columnitem){
//							map.put(clo, rs.getString(clo));
//						}
						for(TbColumns col:tbcolumns){
							map.put(col.getColumnComment(), rs.getString(col.getColumnName()));
						}
						list.add(map);
					}
					return list;
				}
			});
			dao.execute(sql);
			listdata=(List<Map<String, Object>>) sql.getResult();
		}
		result.put("list", listdata);
		return result;
		
		
	}
	
	
	/**
	 * 首页预警
	 * @return
	 */
	@At
	@Ok("json")
	public int getSpyj(HttpSession session){
		User user = (User) session.getAttribute(Constants.USER);
		int yjsl = 0;
		if("1".equals(user.getType())){
			yjsl =dao.count(ResourceApply.class,Cnd.where("STATUS", "=", Constants.STATUS_APPLY_UNCHECKED).and("IS_SUBMIT", "=", "1"));	
		}else if("2".equals(user.getType())){
			yjsl =dao.count(ResourceApply.class,Cnd.where("STATUS", "=", Constants.STATUS_APPLYED_NUCHECKED).and("IS_SUBMIT", "=", "1"));
		}
		return yjsl;
		
	}
	
}

