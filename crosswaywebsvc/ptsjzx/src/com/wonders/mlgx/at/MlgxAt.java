package com.wonders.mlgx.at;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.mlgl.entity.TPubSourceApp;
import com.wonders.Constants;
import com.wonders.mlgl.service.CommonService;
import com.wonders.mlgx.entity.TSourceApp;
import com.wonders.mlgx.entity.TSourceApply;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.tiles.workflow.service.WfService;
import com.wonders.utils.DateUtils;
import com.wonders.utils.UserUtils;

@IocBean(fields = "dao")
@At("/mlgx")
public class MlgxAt {
	@Inject("refer:wfService")
	private WfService wfService;
	
	@Inject
	private CommonService commonService;
	private Dao dao;
	
	/**
	 * 资源目录共享-资源申请
	 * @param cri
	 * @param pager
	 * @param request
	 * @param session
	 * @return
	 */
	@At
	@Ok("jsp:jsp.mlgx.apply.source_apply_list")
	public Map<String, Object> toSourceApply(HttpServletRequest request, HttpSession session) {
		String depId = UserUtils.getOrganId(request);
		Criteria cri = ConUtils.makeCri(request);
		Pager pager =ConUtils.makePaginationPager(request);
		//查询条件
		cri.where().andEquals("stApplierId", depId);
		cri.getOrderBy().desc("dtApply");
		List<TSourceApply> list = dao.query(TSourceApply.class, cri, pager);
		pager.setRecordCount(dao.count(TSourceApply.class, cri));
		
		//查询结果
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sourceAppList", list);
		result.put("pager", pager);
		return result;
	}
	
	/**
	 * 资源申请-新增申请任务.
	 * @param sourceprovider
	 * @param date
	 * @return
	 */
	@At("/addApply")
	@Ok("jsp:jsp.mlgx.apply.data_apply_list")
	public Map<String, Object> addApply(Criteria cri) {
		TSourceApply sourceApply = new TSourceApply();
		//sourceApply.setDtApply(date);
		List<TPubSourceApp> list = new ArrayList<TPubSourceApp>();
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("sourceApply", sourceApply);
		map.put("list", list);
		return map;
	}
	
	/**
	 * 资源申请-修改.
	 * @param sourceprovider
	 * @param date
	 * @return
	 */
	@At("/toSourceEdit")
	@Ok("jsp:jsp.mlgx.apply.data_apply_list")
	public Map<String, Object> toSourceEdit(String stApplyId) {
		TSourceApply sourceApply = dao.fetch(TSourceApply.class, stApplyId);
		List<TPubSourceApp> list = new ArrayList<TPubSourceApp>();
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("sourceApply", sourceApply);
		map.put("list", list);
		return map;
	}
	
	/**
	 * 根据部门获取资源信息
	 * @param depId
	 * @return
	 */
	@At
	@Ok("json")
	public Map<String, Object> getSourceName(String depId){
		Criteria cri = Cnd.cri();
		cri.where().and("stStatus", "=", "4");
		cri.where().and("stSourceProvider","=",depId);
		List<TSourceApp> sourceAppList = dao.query(TSourceApp.class, cri);
		
		//资源目录列表
		Map<String,Object> sourceNameMap = new HashMap<String,Object>();
		sourceNameMap.put("sourceAppList", sourceAppList);
		//String test = Json.toJson(sourceNameMap);
		return sourceNameMap;
		
	}
	
	/**
	 * 新增申请任务-提交.
	 * @param sourceprovider
	 * @param date
	 * @return
	 */
    @At
    @Ok("redirect:/mlgx/toSourceApply")
    @Fail("jsp:jsp.mlgx.apply.data_apply_list")
	public View saveSourceApply(@Param("::sourceApply")TSourceApply tSourceApply, HttpSession session,HttpServletRequest request) {
		String depId = DicDataUtils.getInstance().getDicData(Constants.DIC_TEAM_ID, UserUtils.getOrganId(request));
		String stSourceId =tSourceApply.getStSourceId();
		TSourceApp tSourceApp = dao.fetch(TSourceApp.class, stSourceId);
		String stSourceName = tSourceApp.getStSourceName();//资源目录名称
		tSourceApply.setStApplierId(depId);
		tSourceApply.setStApplicant(UserUtils.getName(request));
		tSourceApply.setStAppSeriel(genSourceIdentifier(tSourceApply));
		tSourceApply.setStSourceId(stSourceId);
		tSourceApply.setStSourceName(stSourceName);
		tSourceApply.setStStatus("2");
		String stApplyId = tSourceApply.getStApplyId();
		if(StringUtils.isEmpty(stApplyId)){
			dao.insert(tSourceApply);
		} else {
			dao.update(tSourceApply);
		}
		
        return new ViewWrapper(new JspView("jsp.mlgx.apply.success"), null);
       
	}
	
    //获得申请批次
	private String genSourceIdentifier(TSourceApply tSourceApply){
		String sourceIdentifier = "";
		String sequenceId = tSourceApply.getStApplierId();
		while(sequenceId.length() < 5){
			sequenceId = sequenceId + "0";
		}
		sequenceId += "-" + DateUtils.getCurrDateStr();
		String flowNum = commonService.getSequence(sequenceId, 3);
		sourceIdentifier = sequenceId + "-" + flowNum;
		return sourceIdentifier;
	}
	
	
	/**
	 * 资源申请-保存.
	 * @param sourceprovider
	 * @param date
	 * @return
	 */
	@At
	@Ok("json")
	public View tempSave(@Param("::sourceApply")TSourceApply tSourceApply,HttpSession session,HttpServletRequest request) {
		String depId = DicDataUtils.getInstance().getDicData(Constants.DIC_TEAM_ID, UserUtils.getOrganId(request));
		String stSourceId =tSourceApply.getStSourceId();
		TSourceApp tSourceApp = dao.fetch(TSourceApp.class, stSourceId);
		String stSourceName = tSourceApp.getStSourceName();//资源目录名称
		String stApplyId = tSourceApply.getStApplyId();
		String status = "0";
		
		if(StringUtils.isBlank(stApplyId)){
			tSourceApply.setStApplierId(depId);
			tSourceApply.setStApplicant(UserUtils.getName(request));
			tSourceApply.setStAppSeriel(genSourceIdentifier(tSourceApply));
			tSourceApply.setStSourceId(stSourceId);
			tSourceApply.setStSourceName(stSourceName);
			tSourceApply.setStStatus("1");
			dao.insert(tSourceApply);
			status = "1";
		}else{
			tSourceApply.setStApplierId(depId);
			tSourceApply.setStApplicant(UserUtils.getName(request));
			tSourceApply.setStAppSeriel(genSourceIdentifier(tSourceApply));
			tSourceApply.setStSourceId(stSourceId);
			tSourceApply.setStSourceName(stSourceName);
			dao.update(tSourceApply);
			status = "1";
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("ststus", status);
		return new ServerRedirectView("${ctx}/mlgx/toSourceApply");
	}
	
	/**
	 * 资源申请-查看.
	 * @param stApplyId
	 * @return
	 */
	@At
	@Ok("jsp:jsp.mlgx.apply.view_apply")
	public Map<String,Object> viewApply(@Param("stApplyId")String stApplyId){
		TSourceApply tSourceApply = dao.fetch(TSourceApply.class, stApplyId);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("sourceApply", tSourceApply);
		return map;
	}
	
	
	/**
	 * 资源申请-删除
	 * @param stApplyId
	 * @return
	 */
	@At
	@Ok("json")
	public Map<String, Object> deleteApply(@Param("stApplyId") String stApplyId) {
		int status = 0;
		if(stApplyId != null){
			status = dao.delete(TSourceApply.class, stApplyId);
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("status",status);
		return resultMap;
	}
	
	
	
	/**
	 * 目录共享-资源审核.
	 * @param sourceprovider
	 * @param date
	 * @return
	 */
	@At
	@Ok("jsp:jsp.mlgx.zysh.source_apply_check_list")
	public Map<String, Object> checkSourceApply(HttpServletRequest request, HttpSession session) {
		String depId = UserUtils.getOrganId(request);
		Criteria cri = ConUtils.makeCri(request);
		Pager pager =ConUtils.makePaginationPager(request);
		cri.where().andEquals("stSourceProvider", depId);
		cri.where().andNotEquals("stStatus", "1");
		cri.getOrderBy().desc("dtApply");
		//cri.where().orEquals("status", "2").orEquals("status", "3").orEquals("status", "4");
		//cri.where().andEquals("curNodeId", CommonConstants.NODE_SQSH);
		List<TSourceApply> sourceAppList = dao.query(TSourceApply.class, cri, pager);
		pager.setRecordCount(dao.count(TSourceApply.class, cri));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sourceAppList", sourceAppList);
		result.put("pager", pager);
		return result;
	}
	
	/**
	 * 资源审核-查看.
	 * @param sourceId
	 * @return
	 */
	@At("/queryApply")
	@Ok("jsp:jsp.mlgx.zysh.audit_apply")
	public Map<String, Object> queryApply(Criteria cri,@Param("stApplyId") String stApplyId){
		Map<String, Object> map= new HashMap<String, Object>();
		TSourceApply tSourceApply = dao.fetch(TSourceApply.class, stApplyId);
		cri.where().andEquals("stApplyId", stApplyId);
		map.put("sourceApply", tSourceApply);
		return map;
	}
	
	/**
	 * 资源审核-审核
	 * @param sourceprovider
	 * @param date
	 * @return
	 */
	@At("/auditApply")
	@Ok("jsp:jsp.mlgx.zysh.audit_apply")
	public Map<String, Object> auditApply(Criteria cri, @Param("stApplyId") String stApplyId){
		Map<String, Object> map= new HashMap<String, Object>();
		TSourceApply sourceApply = dao.fetch(TSourceApply.class, stApplyId);
		cri.where().andEquals("stApplyId", stApplyId);
		cri.getOrderBy().asc("stPubId");
		map.put("stApplyId", stApplyId);
		map.put("sourceApply", sourceApply);
		return map;
	}
	
	/**
	 * 资源审核-保存
	 * @param sourceId
	 * @param auditResult
	 * @param opnnMemo
	 * @return
	 */
	@At("/saveApplyAudit")
	public ServerRedirectView saveApplyAudit(@Param("::sourceApply")TSourceApply tSourceApply, HttpSession session,HttpServletRequest request){
		String auditResult = tSourceApply.getStStatus();
		if(tSourceApply != null){
			if(Constants.STATUS_BACK.equals(auditResult)){//退回修改
					tSourceApply.setStStatus(Constants.STATUS_BACK);
					tSourceApply.setDtAgreeApply(new Date());
					dao.update(tSourceApply);
				}else if(Constants.STATUS_APPLIED.equals(auditResult)){//同意申请
					tSourceApply.setStStatus(Constants.STATUS_APPLIED);
					tSourceApply.setDtAgreeApply(new Date());
					dao.update(tSourceApply);
				}
			}
		return new ServerRedirectView("${ctx}/mlgx/checkSourceApply");
	}
	
	/**
	 * 目录共享-资源获取.
	 * @param sourceId
	 * @param auditResult
	 * @param opnnMemo
	 * @return
	 */
	@At("/toGetSource")
	@Ok("jsp:jsp.mlgx.zyhq.source_list")
	public Map<String, Object> downloadQuery(HttpServletRequest request, HttpSession session){
		String depId = UserUtils.getOrganId(request);
		Criteria cri = ConUtils.makeCri(request);
		Pager pager =ConUtils.makePaginationPager(request);
		cri.where().andEquals("stApplierId", depId);
		cri.where().andEquals("stStatus", Constants.STATUS_APPLIED);
		List<TSourceApply> list = dao.query(TSourceApply.class, cri, pager);
		pager.setRecordCount(dao.count(TSourceApply.class, cri));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sourceAppList", list);
		result.put("pager", pager);
		return result;
	}
}
