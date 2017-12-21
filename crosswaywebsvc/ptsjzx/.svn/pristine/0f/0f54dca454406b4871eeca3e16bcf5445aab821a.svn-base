package com.wonders.api.at;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.api.entity.ApiApply;
import com.wonders.api.entity.ApiService;
import com.wonders.api.entity.ApiServiceUser;
import com.wonders.db.entity.Xdb_files;
import com.wonders.log.entity.OperateLog;
import com.wonders.qxkz.QXConstants;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.dic.entity.DicApi;
import com.wonders.tiles.dic.entity.DicDept;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.utils.CookieUtils;

@At("/api")
@IocBean
public class ApiAt {
	@Inject
	private Dao dao;

	@At
	@Ok("jsp:jsp.api.api_list")
	public Map<String, Object> toApiList(HttpServletRequest request,
			String servicename) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		cri.where().and("VALID", "=", "1");
		if (servicename != null && !servicename.equals("")) {
			cri.where().and("SERVICE_NAME", "like", "%" + servicename + "%");
		}
		cri.getOrderBy().asc("SERVICE_NAME");
		List<ApiService> services = dao.query(ApiService.class, cri, pager);
		pager.setRecordCount(dao.count(ApiService.class, cri));
		result.put("servicename", servicename);
		result.put("services", services);
		result.put("pager", pager);
		return result;
	}

	@At
	@Ok("jsp:jsp.api.api_edit")
	public Map<String, Object> toApiEdit(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!Strings.isEmpty(id)) {
			ApiService service = dao.fetch(ApiService.class,
					Cnd.where("id", "=", id));
			result.put("service", service);
		}
		return result;
	}

	@At
	@Ok("jsp:jsp.api.success")
	public void saveApi(@Param("::service.") ApiService service) {
		if (service != null) {
			if (Strings.isEmpty(service.getId())) {
				service.setValid("1");
				service.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				dao.insert(service);
			} else {
				dao.update(service);
			}
		}
	}

	@At
	@Ok("jsp:jsp.api.api_info")
	public Map<String, Object> toApiInfo(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!Strings.isEmpty(id)) {
			ApiService service = dao.fetch(ApiService.class,
					Cnd.where("id", "=", id));
			result.put("service", service);
		}
		return result;
	}

	@At
	public void deleteApi(String id) {
		if (!Strings.isEmpty(id)) {
			ApiService service = dao.fetch(ApiService.class,
					Cnd.where("id", "=", id));
			service.setValid("0");
			dao.update(service);
		}
	}

	@At
	@Ok("jsp:jsp.api.apply_list")
	public Map<String, Object> toApplyList(HttpServletRequest request,
			String servicename, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		cri.where().and("VALID", "=", "1");
		if (servicename != null && !servicename.equals("")) {
			cri.where().and("SERVICE_NAME", "like", "%" + servicename + "%");
		}
		cri.getOrderBy().asc("SERVICE_NAME");
		List<ApiService> services = dao.query(ApiService.class, cri, pager);
		for (ApiService service : services) {
			Criteria cri2 = Cnd.cri();
			cri2.where().and("SERVICE_ID", "=", service.getId());
			User user = (User) session
					.getAttribute(SystemConstants.SYSTEM_USER);
			cri2.where().and("USER_ID", "=", user.getUserId());
			cri2.where().and("DEPT_ID", "=", user.getDept());
			cri2.getOrderBy().desc("APPLY_DATE");
			List<ApiServiceUser> list = dao.query(ApiServiceUser.class, cri2,
					dao.createPager(1, 1));
			if (list.size() == 0) {
				service.setServiceUser(null);
			} else {
				service.setServiceUser(list.get(0));
			}
		}
		pager.setRecordCount(dao.count(ApiService.class, cri));
		result.put("nowdate", new Date());
		result.put("servicename", servicename);
		result.put("services", services);
		result.put("pager", pager);
		return result;
	}

	@At
	@Ok("jsp:jsp.api.apply1")
	public Map<String, Object> toApply1(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", id);
		return result;
	}

	@At
	public View xieyi() throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		return new ViewWrapper(new JspView("jsp.api.xieyi"), result);
	}

	@At
	public View toApply2(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		ApiService service = dao.fetch(ApiService.class,
				Cnd.where("ID", "=", id));
		result.put("service", service);
		return new ViewWrapper(new JspView("jsp.api.apply2"), result);
	}

	@At
	@Ok("jsp:jsp.api.applay_success")
	public void saveApply(@Param("::serviceuser.") ApiServiceUser serviceuser,
			HttpSession session) {
		if (Strings.isEmpty(serviceuser.getId())) {
			serviceuser.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		serviceuser.setDeptId(user.getDept());
		serviceuser.setUserId(user.getUserId());
		serviceuser.setState("1");
		serviceuser.setApplyDate(new Date());
		serviceuser.setUserDispalyname(user.getLogonName());
		dao.insert(serviceuser);
	}

	@At
	@Ok("jsp:jsp.api.audit_list")
	public Map<String, Object> toAuditList(HttpServletRequest request,
			String state) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		if (!Strings.isEmpty(state)) {
			cri.where().and("STATE", "=", state);
		}
		cri.getOrderBy().desc("APPLY_DATE");
		List<ApiServiceUser> apiServiceUsers = dao.query(ApiServiceUser.class,
				cri, pager);
		pager.setRecordCount(dao.count(ApiServiceUser.class, cri));
		result.put("state", state);
		result.put("apiserviceusers", apiServiceUsers);
		result.put("pager", pager);
		return result;
	}

	@At
	@Ok("jsp:jsp.api.audit")
	public Map<String, Object> toAudit(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		ApiServiceUser serviceUser=dao.fetch(ApiServiceUser.class,Cnd.where("ID","=",id));
		result.put("serviceuser", serviceUser);
		return result;
	}
	@At
	@Ok("jsp:jsp.api.success")
	public void saveAudit(HttpSession session ,String id,String state,String auditopinion){
		ApiServiceUser serviceUser=dao.fetch(ApiServiceUser.class, Cnd.where("ID","=",id));
		if(serviceUser!=null){
			Date date=new Date();
			serviceUser.setState(state);
			serviceUser.setCheckedDate(date);
			serviceUser.setAuditOpinion(auditopinion);
			User user=(User) session.getAttribute(SystemConstants.SYSTEM_USER);
			serviceUser.setAuditUser(user.getUserId());
			OperateLog log = new OperateLog();
			log.setOperateUser(user.getLogonName());
			log.setOperateDept(user.getDept());
			log.setOperateDate(new Date());
			log.setOperateType("审核API");
			if(state.equals("2")){//通过
				serviceUser.setKey(UUID.randomUUID().toString().replaceAll("-", ""));
				serviceUser.setBeginDate(date);
				int applydays=Integer.parseInt(serviceUser.getApplyDay());
				Calendar   calendar   =   new   GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(Calendar.DATE,applydays);
				serviceUser.setEndDate(calendar.getTime());
				log.setOperateResult("通过"+serviceUser.getUserDispalyname()+"的申请");
			}else {
				log.setOperateResult("退回"+serviceUser.getUserDispalyname()+"的申请");
			}
			dao.insert(log);
			dao.update(serviceUser);
		}
	}
	@At
	@Ok("jsp:jsp.api.apply_result")
	public Map<String, Object> toApplyResult(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		ApiServiceUser serviceUser=dao.fetch(ApiServiceUser.class,Cnd.where("ID","=",id));
		result.put("serviceUser", serviceUser);
		return result;
	}
	@At
	@Ok("jsp:jsp.api.call_count")
	public Map<String, Object> toCallCount(HttpSession session,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		User user=(User) session.getAttribute(SystemConstants.SYSTEM_USER);
		String condition=null;
		if(user!=null&&user.getRoleId()!=null&&user.getRoleId().indexOf("2") >=0){
			condition="";
		}else {
			condition=" and USER_ID='"+user.getUserId()+"'";
		}
		
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri=Cnd.cri();
		cri.where().andEquals("VALID", "1");
		cri.getOrderBy().asc("SERVICE_NAME");
		List<ApiService> list = dao.query(ApiService.class,cri ,pager);
		for(ApiService service:list){
			String calltime="select count(1) times from API_LOG_WS where CALL_SERVICE_ID='"+service.getId()+"'"+condition;
			String callresult="select sum(CALL_RESULT) callresult from API_LOG_WS where CALL_SERVICE_ID='"+service.getId()+"'"+condition;
			Sql calltimeSql=Sqls.create(calltime);
			Sql callresultsSql=Sqls.create(callresult);
			calltimeSql.setCallback(new SqlCallback() {
				
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
						throws SQLException {
					while (rs.next()) {
						return rs.getInt("times");
					}
					return null;
				}
			});
			callresultsSql.setCallback(new SqlCallback() {
				
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
						throws SQLException {
					while (rs.next()) {
						return rs.getLong("callresult");
					}
					return null;
				}
			});
			dao.execute(callresultsSql,calltimeSql);
			service.setCalltime((Integer) calltimeSql.getResult());
			service.setCallresult((Long)callresultsSql.getResult());
		}
		pager.setRecordCount(dao.count(ApiService.class,cri));
		result.put("list", list);
		result.put("pager", pager);
		return result;
	}
	/**
	 * 首页进入api申请的申请说明页面.
	 */
	@At
	@Ok("jsp:jsp.api.apply_introduce")
	public void toApplyView(){
		
	}
	
	@At
	@Ok("jsp:jsp.api.item_select")
	public Object toApiItemSelect(HttpServletRequest request){
		Criteria cri =  ConUtils.makeCri(request);
		cri.where().andEquals("validity", "1");
		ConUtils.makePaginationOrder(request, cri, "orderBy", "asc");
		List<DicApi> apiList = dao.query(DicApi.class, cri);
		Map<String, Object> result = new HashMap<String, Object>();
		//数据对象
		result.put("rows", apiList);
		return result;
	}
	
	/**
	 * 进入填写申请api页面.
	 */
	@At
	@Ok("jsp:jsp.api.api_apply_edit")
	public Object toEditApply(@Param("::api.") ApiApply api, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String userName = CookieUtils.getCookie(request, SystemConstants.LOGON_NAME);
		result.put("userName", userName);
		User user= dao.fetch(User.class, Cnd.where("logon_name","=",userName));
		DicDept dept = dao.fetch(DicDept.class,user.getDept());
		ApiApply apiApply = new ApiApply();
//		apiApply.setApiType(api.getApiType());
//		apiApply.setDataType(api.getDataType());
//		apiApply.setShareType(api.getShareType());
		apiApply.setApplyInfo(api.getApplyInfo());
		apiApply.setCheckedUserId(user.getUserId());
		String date = sdf.format(new Date());
		apiApply.setStatus("1");
		apiApply.setDepartment(dept.getDeptName());
		result.put("apiApply", apiApply);
		result.put("date", date);
		return result;
	}
	
	@At
	@Ok("jsp:jsp.api.api_apply_result")
	public Object toApplyAgreement(@Param("::api.") ApiApply api, HttpServletRequest request){
		String userName = CookieUtils.getCookie(request, SystemConstants.LOGON_NAME);
		User user= dao.fetch(User.class, Cnd.where("logon_name","=",userName));
		DicDept dept = dao.fetch(DicDept.class,user.getDept());
		
		Map<String, Object> result = new HashMap<String, Object>();
		String[] applyInfo =api.getApplyInfo().split(",");
		//申请的资源项
		List<String> resourceItems = new ArrayList<String>();
		//申请的数据类型中文
		Set<String> apiTypesChinese = new HashSet<String>();
		Set<String> apiTypes = new HashSet<String>();
		//共享类型
		Set<String> shareTypes = new HashSet<String>();
		Criteria cri = null;
		for(String info : applyInfo){
			cri = Cnd.cri();
			cri.where().and("fieldCode", "=", info);
			DicApi dicApi = dao.fetch(DicApi.class, cri);
			shareTypes.add(dicApi.getShareType());
			apiTypesChinese.add(DicDataUtils.getInstance().getDicData(1054, dicApi.getApiType()));
			apiTypes.add(dicApi.getApiType());
			String applyContent = dicApi.getFieldName();
			resourceItems.add(applyContent);
		}
		
		List<String> ptgxList = new ArrayList<String>();
		List<String> axgxList = new ArrayList<String>();
		//申请批次
		String applyBatchId = UUID.randomUUID().toString().replace("-", "");
		
		//根据数据资源类型分别存储资源项
		Iterator<String> itor = apiTypes.iterator();
		ApiApply apiApply = null;
		StringBuffer apiContent = null;//存入数据库的api内容
		while(itor.hasNext()){
			String apiType = itor.next();
			cri = Cnd.cri();
			cri.where().and("apiType", "=", apiType);
			apiApply = new ApiApply(); 
			apiApply.setApplyDate(new Date());
			List<DicApi> apiList = dao.query(DicApi.class, cri);
			
			//匹配当前申请资源项中哪些是普通共享哪些是按需共享
			Criteria cri1 = null;
			apiContent = new StringBuffer();
			for(String info : applyInfo){ 
				cri1 = Cnd.cri();
				for(DicApi dicApi : apiList){
					if(dicApi.getFieldCode().equalsIgnoreCase(info)){
						cri1.where().and("fieldCode", "=", info);
						cri1.where().and("apiType", "=", apiType);
						DicApi tempApi = dao.fetch(DicApi.class, cri1); 
						if(tempApi != null){
							apiContent.append(info).append("-").append(tempApi.getShareType()).append(",");
							//准备共享类型到页面显示
							if("1".equals(tempApi.getShareType())){
								ptgxList.add(info);
							}
							if("2".equals(tempApi.getShareType())){
								axgxList.add(info);
							}
						}
					}
				}
			}
			if(!Strings.isEmpty(apiContent)){
				apiContent.deleteCharAt(apiContent.length()-1);
			}
			
			apiApply.setApplyTopic(api.getApplyTopic());
			apiApply.setApplyReason(api.getApplyReason());
			apiApply.setStatus("1");
			apiApply.setUserId(user.getUserId());
			apiApply.setDepartment(dept.getDeptName());
			apiApply.setApplyBatch(applyBatchId);
			apiApply.setApiType(apiType);
			apiApply.setApplyInfo(apiContent.toString());//申请api字段内容
			apiApply.setFileIds(api.getFileIds());
			dao.insert(apiApply); 
		}
		
		if(!Strings.isEmpty(api.getFileIds())){
			Criteria cri2 = Cnd.cri();
			cri2.where().and("fid", "=", api.getFileIds());
			Xdb_files file = dao.fetch(Xdb_files.class, cri2);
			result.put("file", file.getFilenamelocal().subSequence(0, file.getFilenamelocal().indexOf(".doc")));
		}
		result.put("apiTypesChinese", getStringBySet(apiTypesChinese));
		result.put("resourceItems", getChineseByList(resourceItems));
		String ptgx = "";
		String axgx = "";
		if(ptgxList != null && ptgxList.size() > 0){
			ptgx = getChineseByCodes(ptgxList, "1");
		}
		result.put("ptgx", ptgx);
		if(axgxList != null && axgxList.size() > 0){
			axgx = getChineseByCodes(axgxList, "2");
		}
		result.put("axgx", axgx);
		result.put("apiApply", apiApply);
		return result;
	}
	
	/**
	 * 根据字段首拼和共享类型获取对应的中文名称.
	 * @param codes
	 * @param shareType
	 * @return
	 */
	public String getChineseByCodes(List<String> codes, String shareType){
		StringBuffer buf = new StringBuffer();
		Criteria cri = null;
		for(int i=0; i<codes.size(); i++){
			cri = Cnd.cri();
			cri.where().and("fieldCode", "=", codes.get(i)); 
			cri.where().and("shareType", "=", shareType);
			DicApi dicApi = dao.fetch(DicApi.class, cri);
			buf.append(dicApi.getFieldName()).append("、");
		}
		return buf.deleteCharAt(buf.length()-1).toString();
	}
	
	/**
	 * 根据字段code获取中文名称.
	 * 查询DicApi.
	 */
	public String getFieldNames(String str){
		StringBuffer buf = new StringBuffer();
		if(!Strings.isEmpty(str)){
			String[] strs = str.split(",");
			Criteria cri = null;
			for(String s : strs){
				cri = Cnd.cri();
				cri.where().and("fieldCode", "=", s);
				DicApi dicApi = dao.fetch(DicApi.class, cri);
				buf.append(dicApi.getFieldName()).append("、");
			}
			return buf.deleteCharAt(buf.length()-1).toString();
		}else{
			return null;
		}
	}
	
	public String getStringByList(List<String> list){
		StringBuffer buf = new StringBuffer();
		for(int i=0; i<list.size(); i++){
			buf.append(list.get(i)).append(",");
		}
		return buf.deleteCharAt(buf.length()-1).toString();
	}
	/**
	 * 将集合转为String在页面显示.
	 */
	public String getStringBySet(Set<String> set){
		StringBuffer buf = new StringBuffer();
		Iterator<String> itor = set.iterator();
		while(itor.hasNext()){
			buf.append(itor.next()).append("、");
		}
		return buf.deleteCharAt(buf.length()-1).toString();
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
	 * 提交审核操作.
	 * @param applyBatch 申请批次.
	 */
	@At
	@Ok("json")
	public String submit(String applyBatch, HttpServletRequest request){
		String flag = "0";
		Criteria cri = Cnd.cri();
		cri.where().and("applyBatch", "=", applyBatch);
		List<ApiApply> apiApplyList = dao.query(ApiApply.class, cri);
		for(ApiApply api : apiApplyList){
			api.setIsSubmit("1"); 
			dao.update(api);
			flag = "1";
		}
		//提交时进行日志记录
		String userName = CookieUtils.getCookie(request, SystemConstants.LOGON_NAME);
		User user= dao.fetch(User.class, Cnd.where("logon_name","=",userName));
		
		OperateLog log = new OperateLog();
		log.setOperateDate(new Date());
		log.setOperateType("api数据申请");
		log.setOperateResult("申请成功");
		log.setOperateDept(user.getDept());
		log.setOperateUser(user.getDisplayName());
		dao.insert(log);
		return flag;
	}
	
	/**
	 * 校验人口类数据和法人类数据是否同时被勾选.
	 */
	@At
	@Ok("json")
	public String checkApplyData(String content){
		String flag = "";
		String[] fields = content.split(",");
		Criteria cri1 = null;
		Criteria cri2 = null;
		for(int i=0; i<fields.length; i++){
			cri1 = Cnd.cri();
			cri1.where().and("fieldCode", "=", fields[i]);
			DicApi dicApi = dao.fetch(DicApi.class, cri1);
			for(int j=fields.length-1; j>0; j--){
				cri2 = Cnd.cri();
				cri2.where().and("fieldCode", "=", fields[j]);
				DicApi tempDicApi = dao.fetch(DicApi.class, cri2);
				if("1".equals(dicApi.getApiType()) && "2".equals(tempDicApi.getApiType())){
					flag = "1";
					break;
				}
			}
		}
		return flag;
	}
	
	@At
	@Ok("jsp:jsp.api.api-check-view")
	public Map<String, Object> checkDataView(String content){
		Map<String, Object> result = new HashMap<String, Object>();
		String[] fields = content.split(",");
		Criteria cri = null;
		List<String> peopleItem = new ArrayList<String>();
		List<String> corpItem = new ArrayList<String>();
		for(String field : fields){
			cri = Cnd.cri();
			cri.where().and("fieldCode", "=", field);
			DicApi dicApi = dao.fetch(DicApi.class, cri);
			if("1".equals(dicApi.getApiType())){
				peopleItem.add(dicApi.getFieldName());
			}
			if("2".equals(dicApi.getApiType())){
				corpItem.add(dicApi.getFieldName());
			}
		}
		result.put("peopleItem", getChineseByList(peopleItem));
		result.put("corpItem", getChineseByList(corpItem));
		return result;
	}
	
	/**
	 * 提交审核后重新加载到查看结果页面.
	 */
	@At
	//@Ok("jsp:jsp.api.api_apply_result")
	public View reloadAgreement(String applyBatch, String isPrint){
		Map<String, Object> result = new HashMap<String, Object>();
		Set<String> apiTypeSet = new HashSet<String>();
		StringBuffer resourceItems = new StringBuffer();//申请的数据项
		ApiApply apiApply = null;
		
		StringBuffer axgx = new StringBuffer();//按需共享
		StringBuffer ptgx = new StringBuffer();//普通共享
		Criteria cri = Cnd.cri();
		cri.where().and("applyBatch", "=", applyBatch);
		List<ApiApply> apiApplyList = dao.query(ApiApply.class, cri);
		for(ApiApply apply : apiApplyList){
			apiApply = apiApplyList.get(0);
			apiTypeSet.add(apply.getApiType());
			String[] fields = apply.getApplyInfo().split(",");
			for(String field : fields){
				resourceItems.append(field.substring(0, field.length()-2)).append(",");
				//普通共享
				if(field.endsWith("-1")){
					ptgx.append(field.substring(0, field.length()-2)).append(",");
				}
				//按需共享
				if(field.endsWith("-2")){
					axgx.append(field.substring(0, field.length()-2)).append(",");
				}
			}
		}
		if(!Strings.isEmpty(ptgx)){
			ptgx.deleteCharAt(ptgx.length()-1);
		}
		if(!Strings.isEmpty(axgx)){
			axgx.deleteCharAt(axgx.length()-1);
		}
		if(!Strings.isEmpty(resourceItems)){
			resourceItems.deleteCharAt(resourceItems.length()-1);
		}
		
		StringBuffer buf = new StringBuffer();
		Iterator<String> itor = apiTypeSet.iterator();
		while(itor.hasNext()){
			String apiType = DicDataUtils.getInstance().getDicData(1054, itor.next());
			buf.append(apiType).append("、");
		}
		if(!Strings.isEmpty(apiApply.getFileIds())){
			Criteria cri2 = Cnd.cri();
			cri2.where().and("fid", "=", apiApply.getFileIds());
			Xdb_files file = dao.fetch(Xdb_files.class, cri2);
			result.put("file", file.getFilenamelocal().subSequence(0, file.getFilenamelocal().indexOf(".doc")));
		}
		result.put("apiApply", apiApply);
		result.put("apiTypesChinese", buf.deleteCharAt(buf.length()-1).toString());
		result.put("resourceItems", getFieldNames(resourceItems.toString()));//需要转为中文
		result.put("ptgx", getFieldNames(ptgx.toString()));
		result.put("axgx", getFieldNames(axgx.toString())); 
		if("1".equals(isPrint)){
			return new ViewWrapper(new JspView("jsp.api.print-view"), result);
		}else{
			return new ViewWrapper(new JspView("jsp.api.api_apply_result"), result);
		}
	}
	
	/**
	 * api审核.
	 */
	@At
	@Ok("jsp:jsp.api.api-audit-list")
	public Map<String, Object> toApiAuditList(HttpServletRequest request, String applyBatch){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = ConUtils.makeCri(request);
		cri.where().and("isSubmit", "=", "1");
		cri.getOrderBy().desc("applyDate");
		Set<String> applyBatchs = new HashSet<String>();
		List<ApiApply> apiApplyList = dao.query(ApiApply.class,cri, pager);
		List<ApiApply> list = new ArrayList<ApiApply>();
		
		//取出所有意见申请的api
		for(ApiApply apiApply : apiApplyList){
			applyBatchs.add(apiApply.getApplyBatch());
		}
		//去除统一批次申请中重复的数据
		for(String batch : applyBatchs){
			list.add(dao.fetch(ApiApply.class, Cnd.where("applyBatch", "=", batch)));
		}
		//显示申请者名称
		for(ApiApply apiApply : list){
			apiApply.setUserId(dao.fetch(User.class, apiApply.getUserId()).getDisplayName());
		}		
		
		pager.setRecordCount(list.size());
		result.put("apiApplyList", list);
		result.put("pager", pager);
		return result;
	}
	
	/**
	 * api申请审核.
	 */
	@At
	@Ok("jsp:jsp.api.api-audit-edit")
	public Map<String, Object> toApiAuditEdit(HttpServletRequest request, String applyBatch){
		Map<String, Object> result = new HashMap<String, Object>();
		List<ApiApply> apiApplyList = dao.query(ApiApply.class, Cnd.where("applyBatch", "=", applyBatch));
		StringBuffer apiInfoStr = new StringBuffer();
		List<String> apiTypes = new ArrayList<String>();
		ApiApply apiApply = null;
		for(ApiApply api : apiApplyList){
			apiApply = api;
			String[] apiInfo = api.getApplyInfo().split(",");
			for(String info : apiInfo){
				apiInfoStr.append((String) info.subSequence(0, info.length()-2)).append(",");
			}
			String apiType = api.getApiType();
			if("1".equals(apiType)){
				apiTypes.add("人口类");
			}
			if("2".equals(apiType)){
				apiTypes.add("法人类");
			}
			if("3".equals(apiType)){
				apiTypes.add("经济类");
			}
		}
		apiInfoStr.deleteCharAt(apiInfoStr.length()-1);
		
		result.put("apiInfoStr", getFieldNames(apiInfoStr.toString()));
		result.put("apiApply", apiApply);
		result.put("apiTypes", getChineseByList(apiTypes));
		result.put("applyBatch", applyBatch);
		
		return result;
	}
	
	/**
	 * api申请详细信息.
	 */
	@At 
	@Ok("jsp:jsp.api.api-apply-details")
	public Map<String, Object> showDetails(String applyBatch){
		Map<String, Object> result = new HashMap<String, Object>();
		List<ApiApply> apiApplyList = dao.query(ApiApply.class, Cnd.where("applyBatch", "=", applyBatch));
		StringBuffer apiInfoStr = new StringBuffer();
		List<String> apiTypes = new ArrayList<String>();
		ApiApply apiApply = null;
		for(ApiApply api : apiApplyList){
			apiApply = api;
			String[] apiInfo = api.getApplyInfo().split(",");
			for(String info : apiInfo){
				apiInfoStr.append((String) info.subSequence(0, info.length()-2)).append(",");
			}
			String apiType = api.getApiType();
			if("1".equals(apiType)){
				apiTypes.add("人口类");
			}
			if("2".equals(apiType)){
				apiTypes.add("法人类");
			}
			if("3".equals(apiType)){
				apiTypes.add("经济类");
			}
		}
		apiInfoStr.deleteCharAt(apiInfoStr.length()-1);
		
		result.put("apiInfoStr", getFieldNames(apiInfoStr.toString()));
		result.put("apiApply", apiApply);
		result.put("apiTypes", getChineseByList(apiTypes));
		return result;
	}
	
	/**
	 * api审核操作.
	 */
	@At
	@Ok("jsp:jsp.api.success")
	public void saveAipAudit(@Param("::apiApply.") ApiApply apiApply, String applyBatch, HttpSession session,HttpServletRequest request){
		Criteria cri = Cnd.cri();
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		OperateLog log = new OperateLog();
		log.setOperateDate(new Date());
		log.setOperateUser(user.getLogonName());
		log.setId(user.getDept());
		log.setOperateType("api审核");
		cri.where().and("USER_ID", "=", user.getUserId());
		List<ApiApply> apiApplyList = dao.query(ApiApply.class, Cnd.where("applyBatch", "=", applyBatch));
		if(apiApplyList != null && apiApplyList.size() > 0){
			for(ApiApply apply : apiApplyList){
				if(Strings.isEmpty(apiApply.getNoPassReason())){
					apply.setStatus("2");//通过
					log.setOperateResult("通过");
				}else{
					apply.setStatus("3");//退回
					log.setOperateResult("退回");
				}
				apply.setCheckedDate(apiApply.getCheckedDate());
				apply.setCheckedUserId(user.getUserId());
				apply.setNoPassReason(apiApply.getNoPassReason());
				dao.update(apply);
			}
		}
		//进行日志记录
		log.setOperateDate(new Date());
		log.setOperateType("api数据审核");
		log.setOperateUser(user.getDisplayName());
		dao.insert(log);
	}
	
	/**
	 * 被退回的申请.
	 */
	@At
	@Ok("jsp:jsp.api.api-back-view")
	public Object showGoBack(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String userName = CookieUtils.getCookie(request, SystemConstants.LOGON_NAME);
		User user= dao.fetch(User.class, Cnd.where("logon_name","=",userName));
		Criteria cri = Cnd.cri();
		cri.where().and("userId", "=", user.getUserId());
		cri.where().and("status", "=", "3"); //退回状态
		Pager pager = ConUtils.makePaginationPager(request);
		List<ApiApply> apiApplyList = dao.query(ApiApply.class, cri, pager);
		//显示申请者名称
		for(ApiApply apiApply : apiApplyList){
			apiApply.setUserId(dao.fetch(User.class, apiApply.getUserId()).getDisplayName());
		}
		pager.setRecordCount(apiApplyList.size());
		result.put("apiApplyList", apiApplyList);
		//数据对象
		result.put("rows", apiApplyList);
		//分页信息
        result.put("pager",pager);
		return result;		
	}
	
	@At
	@Ok("json")
	public Object isExistBackData(HttpServletRequest request){
		String flag = "";
		String userName = CookieUtils.getCookie(request, SystemConstants.LOGON_NAME);
		User user= dao.fetch(User.class, Cnd.where("logon_name","=",userName));
		Criteria cri = Cnd.cri();
		cri.where().and("userId", "=", user.getUserId());
		cri.where().and("status", "=", "3"); //退回状态
		List<ApiApply> apiApplyList = dao.query(ApiApply.class, cri);
		if(apiApplyList != null && apiApplyList.size() > 0){
			flag = "1";
		}
		return flag;
	}
	
	/**
	 * 已申请的api数据.
	 */
	@At
	@Ok("jsp:jsp.api.api-applied-view")
	public Object showApplied(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		String userName = CookieUtils.getCookie(request, SystemConstants.LOGON_NAME);
		User user= dao.fetch(User.class, Cnd.where("logon_name","=",userName));
		Criteria cri = ConUtils.makeCri(request);
		cri.where().and("userId", "=", user.getUserId());
		cri.where().and("isSubmit", "=", "1"); //已提交
		List<ApiApply> apiApplyList = dao.query(ApiApply.class, cri, pager);
		for(ApiApply apiApply : apiApplyList){
			apiApply.setUserId(user.getDisplayName());
		}
		pager.setRecordCount(apiApplyList.size());
		result.put("apiApplyList", apiApplyList);
		//数据对象
		result.put("rows", apiApplyList);
		//分页信息
        result.put("pager",pager);
		return result;
	}
}
