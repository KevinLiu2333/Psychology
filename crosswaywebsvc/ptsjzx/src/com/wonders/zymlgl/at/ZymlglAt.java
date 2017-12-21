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
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.Constants;
import com.wonders.sjtb.entity.TbColumns;
import com.wonders.sjtb.entity.TbSheet;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.dic.entity.DicDept;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.zymlgl.common.BmTables;
import com.wonders.zymlgl.entity.AuditResult;
import com.wonders.zymlgl.entity.Resource;
import com.wonders.zymlgl.entity.ResourceDetails;
import com.wonders.zymlgl.service.UpdateResourceService;
import com.wonders.zymlgx.service.IncrementNumService;

@IocBean(fields = "dao")
@At("/zymlgl")
public class ZymlglAt {
	private Dao						dao;

	/** 日志 **/
	private static Logger			logger	= Logger.getLogger(ZymlglAt.class);

	@Inject
	private UpdateResourceService	updateResourceService;

	@Inject
	private IncrementNumService		incrementNumService;
	
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
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.zymlgl.content.index")
	@Filters
	public Map<String, Object> toContentIndex(HttpServletRequest request,String resourcename,String dept,String applyFlag, String orderBy, String keyWord, String resourceType, String isSy,String dataflag) {
		Map<String, Object> result = new HashMap<String, Object>();

		Criteria cri =Cnd.cri();
		cri.where().andEquals("validity", "1");
		cri.where().andEquals("status", Constants.DIC_MUYFB);
		if(!Strings.isEmpty(resourceType)){
			cri.where().and("RESOURCE_TYPE", "=", resourceType);
			if("r_frl".equalsIgnoreCase(resourceType)){
				result.put("name1", "法人类型");
				result.put("resourceType", "r_frl");
			}else if("r_rkl".equalsIgnoreCase(resourceType)){
				result.put("name1", "人口类型");
				result.put("resourceType", "r_rkl");
			}else{
				result.put("name1", "经济类型");
				result.put("resourceType", "r_cyjjl");
			}
		}
		if(!Strings.isEmpty(dept)){
			cri.where().and("PROVIDE_DEP_ID", "=", dept);
			result.put("name", dept);
		}
		if(!Strings.isEmpty(resourcename)){
			cri.where().andLike("RESOURCE_NAME", resourcename);
		}
		if(!Strings.isEmpty(orderBy)){
			if("RESOURCE_NAME".equalsIgnoreCase(orderBy)){
				cri.getOrderBy().asc(orderBy);
				result.put("orderBy", orderBy);
			}else{
				cri.getOrderBy().desc(orderBy);
				result.put("orderBy", orderBy);
			}	
		}else{
			cri.getOrderBy().asc("RESOURCE_NAME");
		}
		if(!Strings.isEmpty(dataflag)){
			cri.where().and("DATAFLAG", "=", dataflag);
			result.put("dataflag", dataflag);
		}
		Pager pager = ConUtils.makePaginationPager(request);
		List<Resource> resourcelist =dao.query(Resource.class, cri,pager);
		pager.setRecordCount(dao.count(Resource.class,cri));
		result.put("resourcelist", resourcelist);
		List<String> deptlist =new ArrayList<String>();
		Sql sql =Sqls.create("select distinct PROVIDE_DEP_ID from R_RESOURCE  order by PROVIDE_DEP_ID asc");
        sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				List<String> dept =new ArrayList<String>();
				while(rs.next()){
					dept.add(rs.getString(1));
				}
				return dept;
			}
		});
        dao.execute(sql);
        deptlist =(List<String>) sql.getResult();
        List<Map<String, Object>> countResult = new ArrayList<Map<String,Object>>(); 
        DicDataUtils dicutil = DicDataUtils.getInstance();
        for(String dept1:deptlist){
        	if(!Strings.isEmpty(dept1)){
        	   Map<String, Object> map =new HashMap<String, Object>();
        	   Criteria cri1 =Cnd.cri();
        	   cri1.where().andEquals("validity", "1");
        	   cri1.where().and("PROVIDE_DEP_ID", "=", dept1);
        	   String count =String.valueOf(dao.count(Resource.class, cri1));
        	   String dept2 =dicutil.getDicData(1003, dept1).replaceAll("普陀区", "");
        	   map.put("deptname", dept2);
        	   map.put("count", count);
        	   map.put("dept", dept1);
        	   countResult.add(map);
        	}
        }
        List<String> typelist =new ArrayList<String>();
        typelist.add("r_frl");
		typelist.add("r_rkl");
		typelist.add("r_cyjjl");
        for(String type:typelist){
        	Criteria cri2 =Cnd.cri();
        	cri2.where().andEquals("validity", "1");
			cri2.where().and("RESOURCE_TYPE", "=", type);
			String count =String.valueOf(dao.count(Resource.class,cri2));
			result.put(type, count);
        }
        
        result.put("countResult", countResult);
        result.put("pager", pager);
		return result;

	}

	/**
	 * 查询资源详细信息.
	 * 
	 * @param resourceSubitemType
	 *            资源项子类
	 * @return
	 */
	@At
	@Ok("jsp:jsp.zymlgl.resource-details")
	@Filters
	public Object toDetail(String resourceId, String applyFlag, String isSy) {
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.getOrderBy().asc("orderNum");
		cri.where().andEquals("resourceId", resourceId);
		Resource resource = dao.fetch(Resource.class, Cnd.where("resourceId", "=", resourceId));
		resource.setBrowseCount((resource.getBrowseCount() == null ? 0 : resource.getBrowseCount()) + 1);
		dao.update(resource);
		List<ResourceDetails> fieldDetailsList = dao.query(ResourceDetails.class, cri);

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
	public Map<String, Object> toDeptIndex(String provideDepartment, String keyWord, String orderBy, String isSy) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<DicDept> deptList = dao.query(DicDept.class, null);
		result.put("deptList", deptList);

		List<Resource> resources = new ArrayList<Resource>();

		Criteria cri = searcheByParam(keyWord, orderBy, provideDepartment);
		cri.where().andEquals("validity", "1");
		cri.where().andEquals("status", Constants.DIC_MUYFB);
		resources = dao.query(Resource.class, cri);

		result.put("provideDepartment", provideDepartment);
		result.put("keyWord", keyWord);
		result.put("orderBy", orderBy);
		result.put("count", resources.size());
		result.put("resources", resources);
		result.put("isSy", isSy);

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
	public Object toZxbmList() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	/**
	 * 资源列表,点击在线菜单目录,右边列表展示.
	 * 
	 * @return
	 */
	@At("/sourceList")
	@Ok("jsp:jsp.zymlgl.zxbm.list")
	public Map<String, Object> querySourceList(HttpServletRequest request,HttpSession session, String keyWord, String startDate, String endDate,String state) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		String isAdmin = "1";
		Criteria cri = ConUtils.makeCri(request);
		cri.where().andEquals("validity", "1");
		if(!Strings.isEmpty(state)){
			cri.where().and("status", "=", state);
		}else{
			cri.where().andIn("status",Constants.DIC_BMDFB, Constants.DIC_MUYFB,Constants.DIC_BMTH,Constants.DIC_BMZC);
		}
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
		//String logonName = (String) session.getAttribute(SystemConstants.LOGON_NAME);
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		DicDept dept = dao.fetch(DicDept.class, user.getDept());
		cri.where().andEquals("provideDepId", dept.getDeptCode());
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
			List<ResourceDetails> resourceDetailList = dao.query(ResourceDetails.class, Cnd.where("resourceId", "=", resourceId));
			result.put("dept", user.getDept());
			result.put("loginName", user.getLogonName());
			result.put("resources", resources);
			result.put("resourceDetailList", resourceDetailList);
			return result;
		} else {
			result.put("dept", user.getDept());
			result.put("loginName", user.getLogonName());
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
			String tableName, String opType,HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(Constants.USER);
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
				tempResource.setStatus(Constants.DIC_BMDFB);// 待审核
			}
			tempResource.setResourceType(resources.getResourceType());
			tempResource.setBrowseCount((long) 0);
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
//			updateResourceService.dropTable(tableName); 撤销目录修改会删除表，不安全，删除该功能
			resources.setVersionNumber(resources.getVersionNumber() + 1);
			resources.setProvideDepId(resources.getProvideDepId());
			resources.setUpdateDate(new Date());
			resources.setOpUser(Constants.WAIT_PO);
			resources.setTableChinese(resources.getTableChinese());
			if (Constants.STATUS_TEMP_SAVE.equals(opType)) {
				resources.setStatus(Constants.DIC_BMZC);// 暂存
			} else {
				resources.setStatus(Constants.DIC_BMDFB);// 待审核
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
	public Object toViewResource(String resourceId) {
		Map<String, Object> result = new HashMap<String, Object>();

		Resource resource = dao.fetch(Resource.class, Cnd.where("resourceId", "=", resourceId));
		List<ResourceDetails> resourceDetailsList = dao.query(ResourceDetails.class, Cnd.where("resourceId", "=", resourceId));

		Criteria cri = Cnd.cri();
		cri.where().andEquals("resourceId", resourceId);
		cri.getOrderBy().asc("opnnTime");
		List<AuditResult> auditResultList = dao.query(AuditResult.class, cri);
		result.put("resource", resource);
		result.put("resourceDetailsList", resourceDetailsList);
		result.put("auditResultList", auditResultList);
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
	 * 撤销资源目录.
	 */
	@At
	@Ok("json")
	public void revokeResource(String resourceId){
		if(!Strings.isEmpty(resourceId)){
			Resource resource = dao.fetch(Resource.class, resourceId);
			if(resource != null){
				// 撤销到暂存状态
				resource.setStatus(Constants.DIC_BMZC);
				dao.update(resource);
			}
		}
		
	}

	/**
	 * 资源目录-审核发布列表.
	 */
	@At
	@Ok("jsp:jsp.zymlgl.shfb.check-list")
	public Object checkList(HttpServletRequest request, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(Constants.USER);
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = ConUtils.makeCri(request);
		cri.where().andEquals("validity", "1");
		cri.getOrderBy().desc("updateDate");
		cri.where().andIn("status", Constants.DIC_BMDFB, Constants.DIC_MUYFB,Constants.DIC_BMTH,Constants.DIC_BMZC);
		List<Resource> resourceList = dao.query(Resource.class, cri, pager);
		pager.setRecordCount(dao.count(Resource.class, cri));
		result.put("resourceList", resourceList);
		result.put("pager", pager);
		result.put("user", user);
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
		List<ResourceDetails> resourceDetailsList = dao.query(ResourceDetails.class, Cnd.where("resourceId", "=", resource.getResourceId()));
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
		if (Constants.DIC_MUYFB.equals(opnn.getOpnnType())) {
			if(!Strings.isEmpty(resourceId)){
//				updateResourceService.createTatble(resourceId); 暂时不需要新建表功能
				resource.setStatus(Constants.DIC_MUYFB); 
			}
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

	/**
	 * 数据浏览
	 * */
	@At
	@Ok("jsp:jsp.zymlgl.query-table")
	@Filters
	public Object queryTablie(String table, String resourceId) {
		Map<String, Object> result = new HashMap<String, Object>();

		if (!Strings.isEmpty(resourceId) && !Strings.isEmpty(table)) {
			logger.info("表名为：【" + table + "】" + "资源主键为:【" + resourceId + "】");

			String realTableName = ""; // 数据预览查询的真实表名

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
			sqlBuf.append(" from " + realTableName);

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
	
	
	@At
	@Ok("json")
	public void setdata(){
		List<Resource> resource=dao.query(Resource.class, Cnd.where("validity", "=", "1"));
		for(Resource res:resource){
			if(Strings.isEmpty(res.getDataflag())){
				TbSheet sheet=dao.fetch(TbSheet.class,Cnd.where("SHEET_NAME", "=",res.getResourceName()));
				String tablename="";
				if(sheet!=null){
					List<ResourceDetails> details=dao.query(ResourceDetails.class, Cnd.where("RESOURCE_ID", "=", res.getResourceId()));
					for(ResourceDetails detail:details){
						Criteria cri =Cnd.cri();
						cri.where().and("TABLE_BM", "=", sheet.getTabelBm());
						cri.where().and("SHEET_ID", "=", sheet.getOrderNo());
						cri.where().and("COLUMN_COMMENT", "=", detail.getDataItemName());
						TbColumns column=dao.fetch(TbColumns.class, cri);
						if(column!=null){
						    detail.setFieldCode(column.getColumnName());
						    tablename=column.getTableName();
						    dao.update(detail);
						    
						}
					}
				}
				res.setTableName(tablename);
				dao.update(res);
				
			}
		}
		
	}

}
