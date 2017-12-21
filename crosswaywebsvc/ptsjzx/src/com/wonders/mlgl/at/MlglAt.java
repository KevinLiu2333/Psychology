package com.wonders.mlgl.at;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;
import com.wonders.mlgl.entity.TCatalogInfo;
import com.wonders.mlgl.entity.TDataItem;
import com.wonders.mlgl.entity.TPubDataItem;
import com.wonders.mlgl.entity.TPubSourceApp;
import com.wonders.mlgl.entity.VCurSourceApp;
import com.wonders.mlgl.entity.view.VSourceTree;
import com.wonders.mlgl.service.CommonService;
import com.wonders.mlgx.entity.TSourceApp;
import com.wonders.Constants;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.dic.entity.DicResource;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.tiles.ui.UI;
import com.wonders.tiles.ui.ZymlUI;
import com.wonders.tiles.ui.UI.StatusCode;
import com.wonders.tiles.workflow.entity.WfOpnn;
import com.wonders.tiles.workflow.service.WfService;
import com.wonders.util.DateUtils;
import com.wonders.utils.BeanUtils;
import com.wonders.utils.UserUtils;

@IocBean(fields = "dao")
@At("/mlgl")
public class MlglAt {
	@Inject("refer:wfService")
	private WfService wfService;
	@Inject
	private CommonService commonService;
	
	private Dao dao;
	
	/**
	 * 进入在线编目菜单
	 * @param pager
	 * @param session
	 * @return
	 */
	@At("/list")
	@Ok("jsp:jsp.mlgl.zxbm.tree_list")
	public void queryZxbmList( Pager pager,HttpSession session,HttpServletRequest request) {
//		String depId = UserUtils.getOrganId(request);
//		List<TCatalogInfo> nodeList = new ArrayList<TCatalogInfo>();
//		Criteria cri = Cnd.cri();
//		cri.where().andEquals("stSourceProvider", depId);
//		cri.getOrderBy().asc("nmNodeOrder");
//		nodeList = dao.query(TCatalogInfo.class, cri);
//		String treeJson = Json.toJson(nodeList);
//		
//		Criteria sourceCri = Cnd.cri();
//		sourceCri.where().andEquals("stSourceProvider", depId).andEquals("stValid", Constants.IS_VALID_YES);
//		sourceCri.getOrderBy().desc("dtCreateDate");
//		List<VCurSourceApp> list = dao.query(VCurSourceApp.class, sourceCri, pager);
//		pager.setRecordCount(dao.count(VCurSourceApp.class, sourceCri));
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("treeJson", treeJson);
//		map.put("list", list);
//		map.put("pager", pager);
//		return map;
	}
	
	/**
	 * 在线编码-树展示.
	 * @param session
	 * @param request
	 * @return
	 */
	@At
	@Ok("jsp:jsp.mlgl.zxbm.tree")
	public Map<String,Object> toLeftTree(HttpSession session,HttpServletRequest request) {
		String depId = UserUtils.getOrganId(request);
		List<TCatalogInfo> nodeList = new ArrayList<TCatalogInfo>();
		Criteria cri = Cnd.cri();
		cri.where().andEquals("stSourceProvider", depId);
		cri.getOrderBy().asc("nmNodeOrder");
		nodeList = dao.query(TCatalogInfo.class, cri);
		String treeJson = Json.toJson(nodeList);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("treeJson", treeJson);
		return map;
	}
	
	
	/**
	 * 资源目录检索-进入树列表页面.
	 * @param pager
	 * @param session
	 * @return
	 * toSourceQueryTree
	 */
	@At("/treeList")
	@Ok("jsp:jsp.mlgl.query.tree_list")
	public void queryTreeList() {
		
	}
	
	
	/**
	 * 资源目录检索-树展示.
	 * @param session
	 * @param request
	 * @return  sourceQueryList
	 */
	@At
	@Ok("jsp:jsp.mlgl.query.tree")
	public Map<String,Object> toSourceQueryTree(HttpSession session,HttpServletRequest request) {
		List<VSourceTree> nodeList = new ArrayList<VSourceTree>();
		//默认显示国家主题
		Criteria cri = Cnd.cri();
		cri.where().andEquals("stType", "1");
		cri.getOrderBy().asc("stCode");
		nodeList = dao.query(VSourceTree.class, cri);
		String treeJson = Json.toJson(nodeList);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("treeJson", treeJson);
		return map;
	}
	
	/**
	 * 资源目录检索-资源列表展示.
	 * @param session
	 * @param request
	 * @return 
	 */
	@At
	@Ok("jsp:jsp.mlgl.query.list")
	public Map<String,Object> sourceQueryList(HttpSession session,HttpServletRequest request){
		Criteria sourceCri = Cnd.cri();
		Pager pager = ConUtils.makePaginationPager(request);
		sourceCri.where().andEquals("stValid", Constants.IS_VALID_YES).andEquals("stVersion", Constants.VERSION_LASTEST);
		sourceCri.getOrderBy().desc("dtPubDate");
		List<TPubSourceApp> list = dao.query(TPubSourceApp.class, sourceCri, pager);
		pager.setRecordCount(dao.count(TPubSourceApp.class, sourceCri));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pager", pager);
		return map;
	}
	
	/**
	 * 资源目录检索-ajax获取树内容.
	 * @param stType 1-国家主题  2-部门主题  3-局委办主题
	 * @return
	 */
	@At
	@Ok("json")
	public Object getTree(@Param("stType") String stType){
		List<VSourceTree> nodeList = new ArrayList<VSourceTree>();
		Criteria cri = Cnd.cri();
		cri.where().andEquals("stType", stType);
		cri.getOrderBy().asc("stCode");
		nodeList = dao.query(VSourceTree.class, cri);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("treeJson", nodeList);
	//	String test = Json.toJson(nodeList);
		return map;
	}
	
	/**
	 * 资源目录检索-查看功能.
	 * @param pubSourceId
	 * @return
	 * type 用于查看页面 文字说明 类型的判断
	 */
	@At
	@Ok("jsp:jsp.mlgl.query.view_source")
	public Map<String, Object> toViewQuery(@Param("pubSourceId") String pubSourceId,String type){
		Map<String, Object> map= new HashMap<String, Object>();
		TPubSourceApp pubSourceApp = dao.fetch(TPubSourceApp.class, pubSourceId);
		List<TPubDataItem> pubDataItemList = dao.query(TPubDataItem.class, Cnd.where("stPubId", "=", pubSourceId));
		map.put("sourceApp", pubSourceApp);
		map.put("dataItemList", pubDataItemList);
		map.put("type", type);
		return map;
	}
	
	/**
	 * 资源目录检索-检索功能.
	 * @return
	 */
	@At("/querySource")
	@Ok("jsp:jsp.mlgl.query.list")
	public Map<String, Object> querySource(@Param("stNodeId") String stNodeId,HttpServletRequest request){
		Criteria cri = ConUtils.makeCri(request);
		Pager pager =ConUtils.makePaginationPager(request);
		cri.where().andLike("stSourceIdentifier", stNodeId.replace(Constants.NODE_ID_SEPERATOR, "") + "%");
		cri.where().andEquals("stValid", Constants.IS_VALID_YES);
		cri.where().andEquals("stVersion", Constants.VERSION_LASTEST);
		cri.getOrderBy().desc("dtPubDate");
		List<TPubSourceApp> list = dao.query(TPubSourceApp.class, cri, pager);
		pager.setRecordCount(dao.count(TPubSourceApp.class, cri));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pager", pager);
		map.put("stNodeId", stNodeId);
		return map;
	}
	
	/**
	 * 审核发布-展示页面
	 * @param cri
	 * @param pager
	 * @return
	 */
	@At("/checkList")
	@Ok("jsp:jsp.mlgl.shfb.check_list")
	public Map<String, Object> toCheckList(HttpServletRequest request){
		Criteria cri = ConUtils.makeCri(request);
		Pager pager =ConUtils.makePaginationPager(request);
		Map<String, Object> map= new HashMap<String, Object>();
		cri.where().andIn("curNodeId", new String[]{Constants.NODE_SHFB, Constants.NODE_ZXBM});
		cri.getOrderBy().desc("curNodeId").asc("dtReceiveDate");
		List<VCurSourceApp> sourceAppList = dao.query(VCurSourceApp.class, cri, pager);
		pager.setRecordCount(dao.count(VCurSourceApp.class, cri));
		map.put("sourceAppList", sourceAppList);
		map.put("pager", pager);
		return map;
	}
	
	/**
	 * 审核发布-查看/审核发布功能.
	 * @param sourceId
	 * @return
	 * type 用于查看页面 文字说明 类型的判断
	 * op 用于区别是查看还是审核发布 view-查看  send-审核发布
	 */
	@At("/auditSource")
	@Ok("jsp:jsp.mlgl.shfb.audit_source")
	public Map<String, Object> toAudit(@Param("sourceId") String sourceId,String type,String op){
		Map<String, Object> map= new HashMap<String, Object>();
		TSourceApp sourceApp = dao.fetch(TSourceApp.class, sourceId);
		List<TDataItem> dataItemList = dao.query(TDataItem.class, Cnd.where("stSourceId", "=", sourceId));
		List<WfOpnn> opnnList = wfService.wfOpnnMemo(sourceId);
		map.put("sourceApp", sourceApp);
		map.put("dataItemList", dataItemList);
		map.put("opnnList", opnnList);
		map.put("type", type);
		map.put("op", op);
		return map;
	}
	
	/**
	 * 资源审核发布-保存审批结果.
	 * @param sourceId
	 * @param auditResult
	 * @param opnnMemo
	 * @return
	 */
	@At("/publishSource")
	@Ok("json")
	public ViewWrapper saveSourceAudit(@Param("sourceId") String sourceId,@Param("auditResult") String auditResult,@Param("opnnMemo") String opnnMemo, HttpSession session){
		//当前登录人
		User user = (User) session.getAttribute(Constants.USER);
		if(StringUtils.isNotBlank(sourceId)){
			TSourceApp sourceApp = dao.fetch(TSourceApp.class, sourceId);
			Map<String, String> variableMap = new HashMap<String, String>();
			if(sourceApp != null){
				VCurSourceApp curSourceApp = dao.fetch(VCurSourceApp.class, sourceId);
				String instanceId = curSourceApp.getInstanceId();
				if(Constants.STATUS_BACK.equals(auditResult)){
					//退回
					variableMap.put("nn", Constants.NODE_ZXBM);
					//流程操作人
					if(wfService.sendForward(user.getUserId(), instanceId, variableMap, opnnMemo)){
						sourceApp.setStStatus(Constants.STATUS_BACK);
						dao.update(sourceApp);
				//		return UI.closeAjaxDone(StatusCode.OK, "queryCheckList");
						return new ViewWrapper(new JspView("jsp.mlgl.shfb.success"), null);
					}else{
						//return UI.ajaxDone(StatusCode.FAIL);
					}
				}else if(Constants.STATUS_PUBLISHED.equals(auditResult)){
					//发布
					variableMap.put("nn", Constants.NODE_ZXBM);
					//流程操作人
					if(wfService.sendForward(user.getUserId(), instanceId, variableMap, opnnMemo)){
						sourceApp.setStStatus(Constants.STATUS_PUBLISHED);
						sourceApp.setDtPubDate(new Date());
						//发布人
						sourceApp.setStPubUserId(user.getUserId());
						dao.update(sourceApp);
						backUpSourceApp(sourceApp);
					//	return UI.closeAjaxDone(StatusCode.OK, "queryCheckList");
						return new ViewWrapper(new JspView("jsp.mlgl.shfb.success"), null);
					}else{
						//return UI.ajaxDone(StatusCode.FAIL);
					}
				}else if(Constants.STATUS_CANCEL.equals(auditResult)){
					//撤销
					if(wfService.sendForward("1", instanceId, variableMap, opnnMemo)){
						sourceApp.setStStatus(Constants.STATUS_PUBLISHED);
						sourceApp.setStValid(Constants.IS_VALID_NO);
						sourceApp.setDtPubDate(new Date());
						//撤销人
						sourceApp.setStPubUserId(user.getUserId());
						dao.update(sourceApp);
						backUpSourceApp(sourceApp);
						//return UI.closeAjaxDone(StatusCode.OK, "queryCheckList");
						return new ViewWrapper(new JspView("jsp.mlgl.shfb.success"), null);
					}else{
					//	return UI.ajaxDone(StatusCode.FAIL);
					}
				}
			}else{
				//return UI.ajaxDone(StatusCode.FAIL);
			}
		}
		//return UI.ajaxDone(StatusCode.FAIL);
		return new ViewWrapper(new JspView("jsp.mlgl.shfb.success"), null);
	}
	
	/**
	 * 已发布数据入库
	 * @param sourceApp
	 */
	private void backUpSourceApp(TSourceApp sourceApp){
		//将原先的最新版本置为历史
		TPubSourceApp hisPubSource = dao.fetch(TPubSourceApp.class, Cnd.where("stSourceId","=",sourceApp.getStSourceId()).and("stVersion", "=", Constants.VERSION_LASTEST));
		if(hisPubSource != null){
			hisPubSource.setStVersion(Constants.VERSION_HIS);
			dao.update(hisPubSource);
		}
		
		//将现有数据存入历史表并记录为最新版本
		TPubSourceApp pubSource = new TPubSourceApp();
		BeanUtils.copyProperties(pubSource, sourceApp);
		pubSource.setStVersion(Constants.VERSION_LASTEST);
		pubSource = dao.insert(pubSource);
		
		//数据项存入历史库
		List<TDataItem> dataItemList = dao.query(TDataItem.class, Cnd.where("stSourceId","=",sourceApp.getStSourceId()));
		for(TDataItem dataItem : dataItemList){
			TPubDataItem pubDataItem = new TPubDataItem();
			BeanUtils.copyProperties(pubDataItem, dataItem);
			pubDataItem.setStDataItemId("");
			pubDataItem.setStPubId(pubSource.getStPubId());
			dao.insert(pubDataItem);
		}
	}
	
	
	/**
	 * 资源列表,点击在线菜单目录,右边列表展示.
	 * @param cri
	 * @param pager
	 * @param stCatId
	 * @return
	 */
	@At("/sourceList")
	@Ok("jsp:jsp.mlgl.zxbm.list")
	public Map<String, Object> querySourceList(@Param("stCatId") String stCatId, HttpSession session,HttpServletRequest request){
		Criteria cri = ConUtils.makeCri(request);
		Pager pager =ConUtils.makePaginationPager(request);
		TCatalogInfo catalog = new TCatalogInfo();
		if(StringUtils.isNotEmpty(stCatId)){
			catalog = dao.fetch(TCatalogInfo.class, Cnd.where("stCatId", "=", stCatId));
		}
		//根据用户获取部门代码
		String depId = DicDataUtils.getInstance().getDicData(Constants.DIC_TEAM_ID, UserUtils.getOrganId(request));
		cri.where().andEquals("stSourceProvider",depId);
		if(catalog != null){
			//串接子节点主键
			String stCatIds = "";
			Criteria catalogCri = Cnd.cri();
			catalogCri.where().andEquals("stSourceProvider", catalog.getStSourceProvider()).andLike("stNodeId", catalog.getStNodeId() + "%");
			List<TCatalogInfo> catList = dao.query(TCatalogInfo.class, catalogCri);
			for(TCatalogInfo child : catList){
				stCatIds  +=  "'" + child.getStCatId() + "',";
			}
			if(StringUtils.isNotBlank(stCatIds)){
				stCatIds = stCatIds.substring(0, stCatIds.length()-1);
				cri.where().and("stCatId", "in", stCatIds);
			}
		}
		cri.where().andEquals("stValid", Constants.IS_VALID_YES);
		cri.getOrderBy().desc("dtCreateDate");
		List<VCurSourceApp> list = dao.query(VCurSourceApp.class, cri, pager);
		pager.setRecordCount(dao.count(VCurSourceApp.class, cri));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pager", pager);
		map.put("catalog", catalog);
		return map;
	}
	
	/**
	 * 进入新增资源页面
	 * @param stCatId
	 * @return
	 */
	@At("/addSource")
	@Ok("jsp:jsp.mlgl.zxbm.add_source")
	public Map<String, Object> toAdd(@Param("stCatId") String stCatId,HttpServletRequest request){
		Map<String, Object> map= new HashMap<String, Object>();
		//根据用户获取部门代码
		String depId = DicDataUtils.getInstance().getDicData(Constants.DIC_TEAM_ID, UserUtils.getOrganId(request));
		TSourceApp sourceApp = new TSourceApp();
		sourceApp.setStCatId(stCatId);
		sourceApp.setStSourceProvider(depId);
		sourceApp.setStSourceProvAddr(DicDataUtils.getInstance().getDicData(Constants.DIC_DEPT_ADDR, depId));
		sourceApp.setStSourceIdentifier(genSourceIdentifier(sourceApp));
		map.put("sourceApp", sourceApp);
		return map;
	}
	
	/**
	 * 根据一级国家主题获取二级国家主题
	 * @param stGjztFirstId
	 * @return
	 */
	@At
	@Ok("json")
	public Object getGjztSecDic(@Param("stGjztFirstId") String stGjztFirstId){
		Map<String, String> dicMap = new LinkedHashMap<String, String>();
		Criteria cri = Cnd.cri();
		cri.where().andEquals("catalog", Constants.DIC_GJZT).andEquals("parentId", stGjztFirstId).andEquals("validity", "1");
		cri.getOrderBy().asc("listNum");
		List<DicResource> list = dao.query(DicResource.class,cri);
		for(DicResource dicRes : list){
			dicMap.put(dicRes.getKeyValue(), dicRes.getDisplayValue());
		}
		return dicMap;
	}
	
	/**
	 * 修改资源项.
	 * @param sourceId
	 * @return
	 */
	@At("/modifySource")
	@Ok("jsp:jsp.mlgl.zxbm.add_source")
	public Map<String, Object> toModify(@Param("sourceId") String sourceId){
		Map<String, Object> map= new HashMap<String, Object>();
		TSourceApp sourceApp = dao.fetch(TSourceApp.class, sourceId);
		List<TDataItem> dataItemList = dao.query(TDataItem.class, Cnd.where("stSourceId", "=", sourceId));
		List<WfOpnn> opnnList = wfService.wfOpnnMemo(sourceId);
		map.put("sourceApp", sourceApp);
		map.put("dataItemList", dataItemList);
		map.put("opnnList", opnnList);
		return map;
	}
	
	/**
	 * 在线编码-查看资源.
	 * @param sourceId
	 * @return
	 */
	@At("/toView")
	@Ok("jsp:jsp.mlgl.zxbm.view_basic_information")
	public Map<String, Object> toView(@Param("sourceId") String sourceId){
		Map<String, Object> map= new HashMap<String, Object>();
		TSourceApp sourceApp = dao.fetch(TSourceApp.class, sourceId);
		List<TDataItem> dataItemList = dao.query(TDataItem.class, Cnd.where("stSourceId", "=", sourceId));
		List<WfOpnn> opnnList = wfService.wfOpnnMemo(sourceId);
		map.put("sourceApp", sourceApp);
		map.put("dataItemList", dataItemList);
		map.put("opnnList", opnnList);
		return map;
	}
	
	
	/**
	 * 生成信息资源标识符
	 * @param sourceApp
	 * @return
	 */
	private String genSourceIdentifier(TSourceApp sourceApp){
		String sourceIdentifier = "";
		TCatalogInfo cat = dao.fetch(TCatalogInfo.class, sourceApp.getStCatId());
		String sequenceId = cat.getStNodeId().replace(Constants.NODE_ID_SEPERATOR, "");
		while(sequenceId.length() < 10){
			sequenceId = sequenceId + "0";
		}
		sequenceId += "-" + DateUtils.getYear();
		String flowNum = commonService.getSequence(sequenceId, 3);
		sourceIdentifier = sequenceId + "-" + flowNum;
		return sourceIdentifier;
	}
	
	/**
	 * 进入撤销申请页面
	 * @param sourceId
	 * @return
	 */
	@At("/cancelSource")
	@Ok("jsp:jsp.mlgl.zxbm.cancel_source")
	public Map<String, Object> toCancel(@Param("sourceId") String sourceId){
		Map<String, Object> map= new HashMap<String, Object>();
		TSourceApp sourceApp = dao.fetch(TSourceApp.class, sourceId);
		List<TDataItem> dataItemList = dao.query(TDataItem.class, Cnd.where("stSourceId", "=", sourceId));
		List<WfOpnn> opnnList = wfService.wfOpnnMemo(sourceId);
		map.put("sourceApp", sourceApp);
		map.put("dataItemList", dataItemList);
		map.put("opnnList", opnnList);
		return map;
	}
	
	/**
	 * 提交撤销申请
	 * @param sourceId
	 * @param opnnMemo
	 * @return
	 */
	@At
	@Ok("json")
	public Map<String, Object> saveCancelAudit(@Param("sourceId") String sourceId,@Param("opnnMemo") String opnnMemo){
		if(StringUtils.isNotBlank(sourceId)){
			TSourceApp sourceApp = dao.fetch(TSourceApp.class, sourceId);
			Map<String, String> variableMap = new HashMap<String, String>();
			if(sourceApp != null){
				VCurSourceApp curSourceApp = dao.fetch(VCurSourceApp.class, sourceId);
				String instanceId = curSourceApp.getInstanceId();
				if(wfService.sendForward("1", instanceId, variableMap, opnnMemo)){
					sourceApp.setStStatus(Constants.STATUS_CANCEL);
					dao.update(sourceApp);
					return ZymlUI.divSearch(StatusCode.OK,"ajaxDiv",ZymlUI.CallbackType.CLOSE_CURENT);
				}
			}
		}
		return UI.ajaxDone(StatusCode.FAIL);
	}
	
	/**
	 * 保存/提交资源项
	 * @param sourceApp
	 * @param dataItemMap
	 * @param session
	 * @return
	 */
	@At("/saveSource")
	public ViewWrapper saveSourceApp(@Param("::sourceApp.") TSourceApp sourceApp, @Param("::dataItem.") Map<String, Object> dataItemMap,String opType,HttpSession session,HttpServletRequest request){
		//保存部门主题信息
		TCatalogInfo catalog = dao.fetch(TCatalogInfo.class,sourceApp.getStCatId());
		if(catalog != null && catalog.getStNodeId().length() > 6){
			String stBmzt = catalog.getStNodeId().substring(6, 7);
			if(!Constants.SHIJU_CODE.equals(sourceApp.getStSourceProvider())){
				sourceApp.setStBmzt(stBmzt);
			}
		}
		if(StringUtils.isBlank(sourceApp.getStSourceId())){
			//新增
			sourceApp.setDtCreateDate(new Date());
			//获取新增人
			sourceApp.setStCreateUserId(UserUtils.getUserId(request));
			sourceApp = dao.insert(sourceApp);
//			saveDataItem(sourceApp.getStSourceId(),dataItemMap);
		}else{
			//修改
			TSourceApp sourceInDB = dao.fetch(TSourceApp.class, sourceApp.getStSourceId());
			BeanUtils.copyProperties(sourceInDB, sourceApp);
			sourceInDB.setNmPubInterval(sourceApp.getNmPubInterval());
			sourceInDB.setNmShareInterval(sourceApp.getNmShareInterval());
			sourceApp.setStCreateUserId(UserUtils.getUserId(request));
			dao.update(sourceInDB);
//			saveDataItem(sourceInDB.getStSourceId(),dataItemMap);
		}
		if(Constants.STATUS_UNCHECKED.equals(sourceApp.getStStatus())){
			VCurSourceApp curApp = dao.fetch(VCurSourceApp.class, sourceApp.getStSourceId());
			Map<String, String> startVariableMap = new HashMap<String, String>();
			if(StringUtils.isBlank(curApp.getInstanceId())){
				//新起流程
				wfService.start("1", Constants.WF_CODE_ZYML, sourceApp.getStSourceId(), startVariableMap);
			}
			curApp = dao.fetch(VCurSourceApp.class, sourceApp.getStSourceId());
			if(Constants.NODE_ZXBM.equals(curApp.getCurNodeId())){
				//起流程人
				wfService.sendForward("1", curApp.getInstanceId(), startVariableMap, "");
			}
		}
		//return ZymlUI.divSearch(StatusCode.OK,"ajaxDiv",ZymlUI.CallbackType.CLOSE_CURENT);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("opType", opType);
		return new ViewWrapper(new JspView("jsp.mlgl.zxbm.success"), result);
	}
	
	/**
	 * 保存数据项
	 * @param sourceId
	 * @param dataItemMap
	 */
	private void saveDataItem(String sourceId, Map<String, Object> dataItemMap){
		if(StringUtils.isNotBlank(sourceId)){
			//清除原数据
			dao.clear(TDataItem.class,Cnd.where("stSourceId","=",sourceId));
			List<Map<String, Object>> dataItemList = (ArrayList<Map<String, Object>>)dataItemMap.get("list");
			if(dataItemList != null && dataItemList.size() > 0){
				for(int i = 0; i < dataItemList.size(); i++){
					TDataItem dataItem = new TDataItem();
					dataItem.setStSourceId(sourceId);
					dataItem.setStDataitemIdentifier(String.valueOf(dataItemList.get(i).get("stDataitemIdentifier")));
					dataItem.setStChineseName(String.valueOf(dataItemList.get(i).get("stChineseName")));
					dataItem.setStDefine(String.valueOf(dataItemList.get(i).get("stDefine")));
					dao.insert(dataItem);
				}
			}
		}
	}
}
