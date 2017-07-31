package com.wonders.zymlgl.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.Constants;
import com.wonders.echarts.timer.EchartsJob;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.entity.DicDept;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.zymlgl.common.BmTables;
import com.wonders.zymlgl.entity.AuditResult;
import com.wonders.zymlgl.entity.Resource;
import com.wonders.zymlgl.entity.ResourceDetails;
import com.wonders.zymlgl.service.UpdateResourceService;
//import com.wonders.zymlgx.service.IncrementNumService;

@IocBean(fields = "dao")
@At("/zymlgl")
public class ZymlglAt {
	private Dao						dao;

	/** 日志 **/
	private static Logger			logger	= Logger.getLogger(ZymlglAt.class);

	@Inject
	private UpdateResourceService	updateResourceService;


	/**
	 * 资源目录-按内容分类.
	 * 
	 * @param applyFlag
	 *            是否可用申请标记
	 * @param orderBy
	 *            排序字段
	 * @param keyWord
	 *            关键字
	 * @param resourceType
	 *            资源类型
	 * @return
	 */
	@At
	@Ok("jsp:jsp.zymlgl.content.index")
	@Filters
	public Object toContentIndex(HttpSession session,String applyFlag, String orderBy, String keyWord, String resourceType, String isSy,String userId,String provideDepId) {
		Map<String, Object> result = new HashMap<String, Object>();

		Criteria cri1 = Cnd.cri();
		Criteria cri2 = Cnd.cri();
		Criteria cri3 = Cnd.cri();
		User user = (User) session
		.getAttribute(SystemConstants.SYSTEM_USER);
		if (!Strings.isEmpty(keyWord)) {
			if (Constants.R_TYPE_PEOPLE.equals(resourceType)) {
				cri1.where().andLike("keyWord", keyWord.trim());
			}
			if (Constants.R_TYPE_CORP.equals(resourceType)) {
				cri2.where().andLike("keyWord", keyWord.trim());
			}
			if (Constants.R_TYPE_HOUSE.equals(resourceType)) {
				cri3.where().andLike("keyWord", keyWord.trim());
			}
		}
		if (Strings.isEmpty(resourceType)) {
			resourceType = Constants.R_TYPE_PEOPLE;
		}
		if (!Strings.isEmpty(orderBy)) {
			cri1.getOrderBy().asc(orderBy);
			cri2.getOrderBy().asc(orderBy);
			cri3.getOrderBy().asc(orderBy);
		}
		
		cri1.where().andEquals("resourceType", Constants.R_TYPE_PEOPLE);
		cri1.where().andEquals("status", Constants.DIC_MUYFB);
		int rklCount = dao.query(Resource.class, cri1).size();
		result.put("rklCount", rklCount);
		if(resourceType.equals(Constants.R_TYPE_PEOPLE))
		{
		
		List<Resource> RkResourceList = dao.query(Resource.class, cri1);
		result.put("RkResourceList", RkResourceList);
		}
		
		cri2.where().andEquals("resourceType", Constants.R_TYPE_CORP);
		cri2.where().andEquals("status", Constants.DIC_MUYFB);
		
		int frlCount = dao.query(Resource.class, cri2).size();
		result.put("frlCount", frlCount);
		if(resourceType.equals(Constants.R_TYPE_CORP))
		{
		List<Resource> FrResourceList = dao.query(Resource.class, cri2);
		
		result.put("FrResourceList", FrResourceList);
		}
		
		cri3.where().andEquals("resourceType", Constants.R_TYPE_HOUSE);
		cri3.where().andEquals("status", Constants.DIC_MUYFB);
	
		int fwlCount = dao.query(Resource.class, cri3).size();
		result.put("fwlCount", fwlCount);
		if(resourceType.equals(Constants.R_TYPE_HOUSE))
		{
		List<Resource> JjResourceList = dao.query(Resource.class, cri3);
		result.put("JjResourceList", JjResourceList);
		}
		
		String provideDepartment = null;
		List<DicDept> deptList = dao.query(DicDept.class, null);
		result.put("deptList", deptList);
		Criteria cri = searcheByParam(keyWord, orderBy, provideDepartment);
		
			Criteria cri11 = searcheByParam(keyWord, orderBy, provideDepartment);
			cri11.where().andEquals("validity", "1");
			cri11.where().andEquals("resourceType", "r_ztl");
			cri11.where().andEquals("status", Constants.DIC_MUYFB);
			cri11.where().andEquals("provideDepId","068001");
			int kwlCount=dao.query(Resource.class, cri11).size();
			result.put("kwlCount", kwlCount);
			Criteria cri12 = searcheByParam(keyWord, orderBy, provideDepartment);
			cri12.where().andEquals("validity", "1");
			cri12.where().andEquals("resourceType", "r_ztl");
			cri12.where().andEquals("status", Constants.DIC_MUYFB);
			cri12.where().andEquals("provideDepId","017001");
			int tjlCount=dao.query(Resource.class, cri12).size();
			result.put("tjlCount", tjlCount);
			
			Criteria cri13 = searcheByParam(keyWord, orderBy, provideDepartment);
			cri13.where().andEquals("validity", "1");
			cri13.where().andEquals("resourceType", "r_ztl");
			cri13.where().andEquals("status", Constants.DIC_MUYFB);
			cri13.where().andEquals("provideDepId","X07060");
			int szblCount=dao.query(Resource.class, cri13).size();
			result.put("szblCount", szblCount);
			Criteria cri14= searcheByParam(keyWord, orderBy, provideDepartment);
			cri14.where().andEquals("validity", "1");
			cri14.where().andEquals("resourceType", "r_ztl");
			cri14.where().andEquals("status", Constants.DIC_MUYFB);
			cri14.where().andEquals("provideDepId","035001");
			int gtlCount=dao.query(Resource.class, cri14).size();
			result.put("gtlCount", gtlCount);
			Criteria cri15= searcheByParam(keyWord, orderBy, provideDepartment);
			cri15.where().andEquals("validity", "1");
			cri15.where().andEquals("resourceType", "r_ztl");
			cri15.where().andEquals("status", Constants.DIC_MUYFB);
			cri15.where().andEquals("provideDepId","037001");
			int fglCount=dao.query(Resource.class, cri15).size();
			result.put("fglCount", fglCount);
		
		if(!Strings.isEmpty(provideDepId)||!"null".equals(provideDepId) )
		{
			cri.where().andEquals("validity", "1");
			cri.where().andEquals("resourceType", "r_ztl");
			cri.where().andEquals("status", Constants.DIC_MUYFB);
			cri.where().andEquals("provideDepId",provideDepId);
		
		if(resourceType.equals("r_zti"))
		{
			List<Resource> resources = new ArrayList<Resource>();
			resources = dao.query(Resource.class, cri);
			result.put("resources", resources);
			
		}
		}
		result.put("resourceType", resourceType);
		result.put("applyFlag", applyFlag);
		result.put("keyWord", keyWord);
		result.put("isSy", isSy);
		result.put("userId", userId);
		result.put("user", user);
		return result;

	}

	/**
	 * 查询资源详细信息.
	 * 
	 * @param resourceSubitemType
	 *            资源项子类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.zymlgl.resource-details")
	@Filters
	public Object toDetail(HttpSession session, String resourceId, String applyFlag, String isSy)
	  {
	    Map result = new HashMap();
	    Criteria cri = Cnd.cri();
	    cri.getOrderBy().asc("orderNum");
	    cri.where().andEquals("resourceId", resourceId);
	    Resource resource = (Resource)this.dao.fetch(Resource.class, Cnd.where("resourceId", "=", resourceId));
	    resource.setBrowseCount(Long.valueOf(((resource.getBrowseCount() == null) ? 0L : resource.getBrowseCount().longValue()) + 1L));
	    this.dao.update(resource);
	    List fieldDetailsList = this.dao.query(ResourceDetails.class, cri);
	    User user = (User)session
	      .getAttribute("user");
	    result.put("user", user);
	    result.put("resource", resource);
	    result.put("fieldDetailsList", fieldDetailsList);
	    result.put("applyFlag", applyFlag);
	    result.put("isSy", isSy);
	    return result;
	  }

	/**
	 * 资源目录-按部门分类.
	 * 
	 * @param provideDepartment
	 * @param keyWord
	 * @param orderBy
	 * @return
	 */
	@At
	@Ok("jsp:jsp.zymlgl.dept.dept")
	@Filters
	public Map<String, Object> toDeptIndex(String provideDepartment, String keyWord, String orderBy, String isSy,HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<DicDept> deptList = dao.query(DicDept.class, null);
		result.put("deptList", deptList);
		User user = (User) session
		.getAttribute(SystemConstants.SYSTEM_USER);
		List<Resource> resources = new ArrayList<Resource>();

		Criteria cri = searcheByParam(keyWord, orderBy, provideDepartment);
		cri.where().andEquals("validity", "1");
		resources = dao.query(Resource.class, cri);
		
		result.put("provideDepartment", provideDepartment);
		result.put("keyWord", keyWord);
		result.put("orderBy", orderBy);
		result.put("count", resources.size());
		result.put("resources", resources);
		result.put("isSy", isSy);
		result.put("user", user);

		return result;
	}

	/**
	 * 根据参数查询结果.
	 * 
	 * @param keyWord
	 *            关键字
	 * @param orderBy
	 *            排序
	 * @param provideDepartment
	 *            资源提供部门
	 * @return
	 */
	private Criteria searcheByParam(String keyWord, String orderBy, String provideDepartment) {
		Criteria cri = Cnd.cri();
		if (!Strings.isEmpty(keyWord)) {
			cri.where().andLike("keyWord", keyWord);
		}
		if (!Strings.isEmpty(orderBy)) {
			cri.getOrderBy().asc(orderBy);
		}
		if (!Strings.isEmpty(provideDepartment)) {
			cri.where().andEquals("provideDepartment", provideDepartment);
		}
		return cri;
	}

	/**
	 * 在线编目.
	 */
	@At
	@Ok("jsp:jsp.zymlgl.zxbm.tree-list")
	public Object toZxbmList(HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 当前登录人
		User user = (User) session.getAttribute(Constants.USER);
		result.put("usre", user);
		return result;
	}
	
	@At
	@Ok("jsp:jsp.zymlgl.zxbm.tree-time")
	public Object time() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	@At
	@Ok("json")
	public void counttime(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		EchartsJob  ej=new EchartsJob();
		
		ej.execute(jobexecutioncontext);
	}


	/**
	 * 资源列表,点击在线菜单目录,右边列表展示.
	 * 
	 * @return
	 */
	@At("/sourceList")
	@Ok("jsp:jsp.zymlgl.zxbm.list")
	public Map<String, Object> querySourceList(HttpSession session,HttpServletRequest request,String keyWord, String startDate, String endDate) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		String isAdmin = "1";
		Criteria cri = ConUtils.makeCri(request);
		cri.where().andEquals("validity", "1");
		if (!Strings.isEmpty(startDate)) {
			cri.where().and("updateDate", ">", startDate);
		}
		if(!Strings.isEmpty(keyWord))
		{
			cri.where().andLike("keyWord", "%keyWord%");
		}
		if (!Strings.isEmpty(endDate)) {
			cri.where().and("updateDate", "<", endDate);
		}
		cri.getOrderBy().desc("updateDate");
		User user = (User) session.getAttribute(Constants.USER);
		DicDept dept = dao.fetch(DicDept.class, user.getDept());
		if (!"1".equals(user.getAdmin())) {// 如果不是管理员
			isAdmin = "0";
			cri.where().andEquals("provideDepId", dept.getDeptCode());
		}

		if ("1".equals(user.getType())) {
			cri.where().andIn("status", Constants.DIC_BMDSH, Constants.DIC_BMDFB, Constants.DIC_MUYFB);
		}
		List<Resource> resourceList = dao.query(Resource.class, cri, pager);
		pager.setRecordCount(dao.count(Resource.class, cri));
		result.put("isAdmin", isAdmin);
		result.put("user", user);
		result.put("resourceList", resourceList);
		result.put("pager", pager);
		return result;
	}

	/**
	 * 编目-编辑.
	 */
	@At
	@Ok("jsp:jsp.zymlgl.zxbm.bm-edit")
	public Object toBmEdit(String resourceId, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 当前登录人
		User user = (User) session.getAttribute(Constants.USER);

		if (!Strings.isEmpty(resourceId)) {
			Resource resources = dao.fetch(Resource.class, Cnd.where("resourceId", "=", resourceId));
			List<ResourceDetails> resourceDetailList = dao.query(ResourceDetails.class, Cnd.where("resourceId", "=", resourceId).asc("orderNum"));
			result.put("dept", user.getDept());
			result.put("loginName", user.getLogonName());
			result.put("resources", resources);
			result.put("resourceDetailList", resourceDetailList);
			result.put("user", user);
			return result;
		} else {
			result.put("dept", user.getDept());
			result.put("loginName", user.getLogonName());
			result.put("user", user);
			return result;
		}
	}

	/**
	 * 在线编目-保存操作.
	 * 
	 * @param resources
	 *            资源主表
	 * @param resourceDetailList
	 *            资源子表详细信息
	 */
	@At
	@Ok("jsp:jsp.zymlgl.success")
	public View saveBm(@Param("::resources") Resource resources, @Param("::resourceDetailList") List<ResourceDetails> resourceDetailList, HttpServletRequest request,
			String tableName, String opType) {
		Map<String, Object> result = new HashMap<String, Object>();
		//String userName = CookieUtils.getCookie(request, SessionFilter.LOGON_NAME);
		//User user = dao.fetch(User.class, Cnd.where("logon_name", "=", userName));
		// 新增资源信息
		if (Strings.isEmpty(resources.getResourceId())) {
			// 保存资源主表信息
			Resource tempResource = new Resource();
			tempResource.setValidity("1");
			// 版本号
			tempResource.setCreateTime(new Date());
			tempResource.setVersionNumber(1);
			tempResource.setResourceName(resources.getResourceName());
			tempResource.setInfoSysName(resources.getInfoSysName());
			tempResource.setSysAbstract(resources.getSysAbstract());
			tempResource.setProvideDepId(resources.getProvideDepId());
			tempResource.setProvideDepartment(resources.getProvideDepartment());
			tempResource.setBusLinkman(resources.getBusLinkman());
			tempResource.setBusLinkmanPhone(resources.getBusLinkmanPhone());
			tempResource.setJointLinkman(resources.getJointLinkman());
			tempResource.setJointLinkmanPhone(resources.getJointLinkmanPhone());
			tempResource.setJointType(resources.getJointType());
			tempResource.setShareProperty(resources.getShareProperty());
			tempResource.setOpenProperty(resources.getOpenProperty());
			tempResource.setUpdateRate(resources.getUpdateRate());
			tempResource.setSysUrl(resources.getSysUrl());
			tempResource.setKeyWord(resources.getKeyWord());
			tempResource.setAbstractWord(resources.getAbstractWord());
			tempResource.setTableName(resources.getTableName());
			tempResource.setUpdateDate(new Date());
			tempResource.setOpUser(Constants.WAIT_PO);
			tempResource.setTableChinese(resources.getTableChinese());
			if (Constants.STATUS_TEMP_SAVE.equals(opType)) {
				tempResource.setStatus(Constants.DIC_BMZC);// 暂存
			} else {
				tempResource.setStatus(Constants.DIC_BMDSH);// 待审核
			}
			tempResource.setResourceType(resources.getResourceType());
			dao.insert(tempResource);

			// 保存资源项子表信息
			ResourceDetails resourceDetails = null;
			int i = 0;
			for (ResourceDetails detail : resourceDetailList) {
				if (!"".equals(detail.getDataItemName()) && !"".equals(detail.getDataItemType()) && !"".equals(detail.getShareProperty())) {
					resourceDetails = new ResourceDetails();
					resourceDetails.setDataItemName(detail.getDataItemName());
					resourceDetails.setFieldCode(detail.getFieldCode());
					resourceDetails.setDataItemType(detail.getDataItemType());
					resourceDetails.setMemo(detail.getMemo());
					resourceDetails.setShareProperty(detail.getShareProperty());
					resourceDetails.setNoShareReason(detail.getNoShareReason());
					resourceDetails.setOpenProperty(detail.getOpenProperty());
					resourceDetails.setNoOpenReason(detail.getNoOpenReason());
					resourceDetails.setResourceId(tempResource.getResourceId());
					resourceDetails.setOrderNum(i);
					dao.insert(resourceDetails);
				}
				i++;
			}
		} else { // update资源信息
					// 修改资源目录信息时，需要dorp上一个版本的table
			updateResourceService.dropTable(tableName);
			resources.setVersionNumber(resources.getVersionNumber() + 1);
			resources.setProvideDepId(resources.getProvideDepId());
			resources.setUpdateDate(new Date());
			resources.setOpUser(Constants.WAIT_PO);
			resources.setTableChinese(resources.getTableChinese());
			if (Constants.STATUS_TEMP_SAVE.equals(opType)) {
				resources.setStatus(Constants.DIC_BMZC);// 暂存
			} else {
				resources.setStatus(Constants.DIC_BMDSH);// 待审核
			}
			resources.setValidity("1");
			dao.update(resources);

			String deleteSql = "delete from R_RESOURCE_DETAILS r where r.RESOURCE_ID = '" + resources.getResourceId() + "'";
			Sql sql = Sqls.create(deleteSql);
			dao.execute(sql);

			// 保存资源项子表信息
			ResourceDetails resourceDetails = null;
			int i = 0;
			for (ResourceDetails detail : resourceDetailList) {
				if (!"".equals(detail.getDataItemName()) && !"".equals(detail.getDataItemType()) && !"".equals(detail.getShareProperty())) {
					resourceDetails = new ResourceDetails();
					resourceDetails.setDataItemName(detail.getDataItemName());
					resourceDetails.setFieldCode(detail.getFieldCode());
					resourceDetails.setDataItemType(detail.getDataItemType());
					resourceDetails.setMemo(detail.getMemo());
					resourceDetails.setShareProperty(detail.getShareProperty());
					resourceDetails.setNoShareReason(detail.getNoShareReason());
					resourceDetails.setOpenProperty(detail.getOpenProperty());
					resourceDetails.setNoOpenReason(detail.getNoOpenReason());
					resourceDetails.setResourceId(resources.getResourceId());
					resourceDetails.setOrderNum(i);
					dao.insert(resourceDetails);
				}
				i++;
			}
		}

		Criteria cri = ConUtils.makeCri(request);
		Pager pager = ConUtils.makePaginationPager(request);
		List<Resource> resourceList = dao.query(Resource.class, cri, pager);
		pager.setRecordCount(dao.count(Resource.class, cri));
		result.put("resourceList", resourceList);
		result.put("pager", pager);
		return new ViewWrapper(new JspView("jsp.jkfw.success"), result);
	}

	/**
	 * 在线编目-查看.
	 */
	@At
	@Ok("jsp:jsp.zymlgl.zxbm.resource-view")
	public Object toViewResource(String resourceId, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();

		Resource resource = dao.fetch(Resource.class, Cnd.where("resourceId", "=", resourceId));
		List<ResourceDetails> resourceDetailsList = dao.query(ResourceDetails.class, Cnd.where("resourceId", "=", resourceId).asc("orderNum"));
		User user = (User) session.getAttribute(Constants.USER);
		Criteria cri = Cnd.cri();
		cri.where().andEquals("resourceId", resourceId);
		cri.getOrderBy().asc("opnnTime");
		List<AuditResult> auditResultList = dao.query(AuditResult.class, cri);
		result.put("resource", resource);
		result.put("resourceDetailsList", resourceDetailsList);
		result.put("auditResultList", auditResultList);
		result.put("user", user);
		return result;
	}

	/**
	 * 删除资源主表和子表信息.
	 */
	@At
	@Ok("json")
	public void delResource(String resourceId) {
		if (!Strings.isEmpty(resourceId)) {
			Resource resource = dao.fetch(Resource.class, resourceId);
			if (resource != null) {
				// List<ResourceDetails> resourceDetailsList =
				// dao.query(ResourceDetails.class, Cnd.where("resourceId", "=",
				// resource.getResourceId()));
				// if(resourceDetailsList.size() >0){
				// for(ResourceDetails resourceDetails : resourceDetailsList){
				// dao.delete(resourceDetails);
				// }
				// }
				// 逻辑删除
				resource.setValidity("0");
				dao.update(resource);
			}
		}
	}

	/**
	 * 资源目录-审核发布列表.
	 */
	@At
	@Ok("jsp:jsp.zymlgl.shfb.check-list")
	public Object checkList(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = ConUtils.makeCri(request);
		cri.where().andEquals("validity", "1");
		cri.where().andEquals("opUser", Constants.OP_USER_TYPE_LEADER);
		cri.getOrderBy().desc("updateDate");
		cri.where().andIn("status", Constants.DIC_BMDFB, Constants.DIC_MUYFB);
		List<Resource> resourceList = dao.query(Resource.class, cri, pager);
		pager.setRecordCount(dao.count(Resource.class, cri));
		result.put("resourceList", resourceList);
		result.put("pager", pager);
		return result;
	}

	/**
	 * 资源目录-审核发布.
	 */
	@At
	@Ok("jsp:jsp.zymlgl.shfb.resource-edit")
	public Object toCheckResource(String resourceId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Resource resource = dao.fetch(Resource.class, resourceId);
		List<ResourceDetails> resourceDetailsList = dao.query(ResourceDetails.class, Cnd.where("resourceId", "=", resource.getResourceId()).asc("orderNum"));
		result.put("resource", resource);
		result.put("resourceDetailsList", resourceDetailsList);
		return result;
	}

	/**
	 * 资源审核发布-保存审批结果.
	 */
	@At("/publishResource")
	@Ok("json")
	public ViewWrapper saveAuditResult(@Param("::opnn") AuditResult opnn, String resourceId, String opUserType, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(Constants.USER);
		opnn.setResourceId(resourceId);
		Date opDate = new Date();
		opnn.setOpnnTime(opDate);
		opnn.setOpnnPerson(user.getLogonName());
		dao.insert(opnn);
		// 修改资源状态
		Resource resource = dao.fetch(Resource.class, Cnd.where("resourceId", "=", resourceId));
		if (resource.getStatus().equals(Constants.DIC_BMDSH) && opnn.getOpnnType().equals(Constants.DIC_BMDFB)) {
			resource.setOpUser("1");
			resource.setStatus(Constants.DIC_BMDFB);
		} else if (resource.getStatus().equals(Constants.DIC_BMDFB) && opnn.getOpnnType().equals(Constants.DIC_BMDFB)) {
			resource.setOpUser("1");
			resource.setStatus(Constants.DIC_MUYFB);
			// 根据"在线编目"填写的表名和字段名create table
			updateResourceService.createTatble(resourceId);
		} else if (opnn.getOpnnType().equals(Constants.DIC_BMTH)) {
			resource.setStatus(Constants.DIC_BMTH);
			resource.setOpUser("0");
		}
		resource.setUpdateDate(opDate);
		/*
		 * if(!Strings.isEmpty(opUserType)){ //如果上一步是委办领导操作，则设置本次操作为科委操作 if(Constants.OP_USER_TYPE_LEADER.equals(opUserType)){ resource.setOpUser(Constants.OP_USER_TYPE_KW); }else
		 * if(Constants.WAIT_PO.equals(opUserType)){ resource.setOpUser(Constants.OP_USER_TYPE_LEADER); } }
		 */
		dao.update(resource);

		result.put("opFlag", "success");

		return new ViewWrapper(new JspView("jsp.zymlgl.shfb.resource-edit"), result);
	}
	
	
	@At
	@Ok("jsp:jsp.zymlgl.renkou")
	@Filters
	public Object renkou()
	{
		return dao;
		
	}
	

	/**
	 * 数据浏览
	 * */
	@At
	@Ok("jsp:jsp.zymlgl.query-table")
	@Filters
	public Object queryTablie(String table, String resourceId,String dataMon,HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(Constants.USER);
		if (!Strings.isEmpty(resourceId) && !Strings.isEmpty(table)) {
			logger.info("表名为：【" + table + "】" + "资源主键为:【" + resourceId + "】");

			String realTableName = ""; // 数据预览查询的真实表名
			if(table.equals("CORP_INFO")|| table=="CORP_INFO")
			{
				realTableName = table;
			}else
			if (BmTables.getBmTables().contains(table)) {
				realTableName = table;
			} else {
				realTableName = Constants.PREFIX_TABLE + table;
			}

			Criteria cri = Cnd.cri();
			cri.getOrderBy().asc("orderNum");
			cri.where().andEquals("resourceId", resourceId);

			final List<ResourceDetails> resourceDetailsList = dao.query(ResourceDetails.class, cri);

			StringBuffer sqlBuf = new StringBuffer("select ");
			for (ResourceDetails resourceDetails : resourceDetailsList) {
				sqlBuf.append(resourceDetails.getFieldCode());
				sqlBuf.append(",");
			}
			sqlBuf.deleteCharAt(sqlBuf.length() - 1);
			if(!Strings.isEmpty(dataMon))
			{
				sqlBuf.append(" from " + realTableName +" where date1="+"'"+dataMon+"'"+" and rownum<=10") ;
			}else
			{
			sqlBuf.append(" from " + realTableName +" where rownum<=10");
			}

			Sql exeSql = Sqls.create(sqlBuf.toString());

			logger.info("执行的SQL：" + exeSql.toString());

			exeSql.setCallback(new SqlCallback() {
				public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
					List<String> list = new ArrayList<String>();

					while (rs.next()) {
						for (ResourceDetails details : resourceDetailsList) {
							list.add(rs.getString(details.getFieldCode()));
						}
					}
					return list;
				}
			});
			dao.execute(exeSql);
			logger.info("-----------> exceute successfunl !");
			result.put("resourceDetais", exeSql.getResult());
			result.put("resourceDetailsList", resourceDetailsList);
		} else {
			logger.info("table后resourcId为空！");
		}
		result.put("user", user);
		result.put("table", table);
		result.put("resourceId", resourceId);
		
		return result;

	}

	/**
	 * 提交审核.
	 */
	@At
	@Ok("json")
	public void submitAudit(String resourceId) {
		if (!Strings.isEmpty(resourceId)) {
			Resource resource = dao.fetch(Resource.class, resourceId);
			resource.setStatus(Constants.DIC_BMDSH);
			dao.update(resource);
		}
	}

}
