package com.wonders.mlgl.at;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;
import org.nutz.service.EntityService;

import com.wonders.Constants;
import com.wonders.mlgl.entity.TCatalogInfo;
import com.wonders.mlgx.entity.TSourceApp;
import com.wonders.utils.BeanUtils;

@IocBean(fields = "dao")
@At("/mlgl/tree")
public class TreeAt extends EntityService<Object> {
	
	/**
	 * 跳转新增节点页面
	 * @param parentCatId
	 * @return
	 */
	@At("/add")
	@Ok("jsp:jsp.mlgl.zxbm.tree.addNode")
	public Map<String, Object> toAdd(@Param("parentCatId") String parentCatId){
		Map<String, Object> result= new HashMap<String, Object>();
		result.put("parentCatId", parentCatId);
		return result;
	}
	
	/**
	 * 跳转修改节点页面
	 * @param stCatId
	 * @return
	 */
	@At("/edit")
	@Ok("jsp:jsp.mlgl.zxbm.tree.editNode")
	public Map<String, Object> toEdit(@Param("stCatId") String stCatId){
		TCatalogInfo catalog = new TCatalogInfo();
		if (StringUtils.isNotEmpty(stCatId)){
			catalog = dao().fetch(TCatalogInfo.class,stCatId);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("catalog",catalog);
		return result;
	}
	
	/**
	 * 新增节点保存
	 * @param parentCatId
	 * @param addCatalog
	 * @return
	 */
	@At("/save")
	@Ok("json")
	public ViewWrapper saveNodeInfo(@Param("parentCatId") String parentCatId, @Param("..") TCatalogInfo addCatalog){
		// 查出父节点
		TCatalogInfo parentNode = dao().fetch(TCatalogInfo.class,parentCatId);
		addCatalog.setDtCreateDate(new Date());
		//新增人
		addCatalog.setStCreateUserId("");
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			addCatalog.setStSourceProvider(parentNode.getStSourceProvider());
			addCatalog.setStParentNodeId(parentNode.getStNodeId());
			addCatalog.setStIfModifyable(Constants.NODE_IF_MODIFIABLE_YES);
			List<TCatalogInfo> brotherNode = dao().query(TCatalogInfo.class, Cnd.where("stParentNodeId","=",parentNode.getStNodeId()).and("stSourceProvider","=",parentNode.getStSourceProvider()));
			if(brotherNode != null && brotherNode.size() > 0){
				int maxId = maxNodeCode(brotherNode);
				String maxNodeId = "00" + (maxId + 1);
				maxNodeId = maxNodeId.substring(maxNodeId.length() - 2, maxNodeId.length());
				addCatalog.setStNodeId(parentNode.getStNodeId() + Constants.NODE_ID_SEPERATOR + maxNodeId);
			}else{
				addCatalog.setStNodeId(parentNode.getStNodeId() + Constants.NODE_ID_SEPERATOR + "01");
			}
			addCatalog = dao().insert(addCatalog);
			result.put("status", "success");
			result.put("parentNode", parentNode);
			result.put("addCatalog", addCatalog);
		} catch (Exception e){
			e.printStackTrace();
			result.put("status", "exception");
		}
		//return result;
		return new ViewWrapper(new JspView("jsp.mlgl.zxbm.tree.success"), result);
	}
	
	/**
	 * 修改节点保存
	 * @param catalog
	 * @return
	 */
	@At("/update")
	@Ok("json")
	public ViewWrapper updateNodeInfo(@Param("..") TCatalogInfo catalog){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			TCatalogInfo catalogInDB = dao().fetch(TCatalogInfo.class,catalog.getStCatId());
			BeanUtils.copyProperties(catalogInDB, catalog);
			catalogInDB.setDtModifyDate(new Date());
			//修改人
			catalogInDB.setStModifyUserId("");
			dao().update(catalogInDB);
			result.put("status", "success");
			result.put("catalog", catalogInDB);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "exception");
		}
		return new ViewWrapper(new JspView("jsp.mlgl.zxbm.success"), result);
	}
	
	@At("/delete")
	@Ok("json")
	public Map<String, Object> deleteNodeInfo(@Param("stCatId") String stCatId){
		String status ="0";
		if(StringUtils.isNotBlank(stCatId)){
			TCatalogInfo catalog = dao().fetch(TCatalogInfo.class,stCatId);
			Criteria cri = Cnd.cri();
			if(catalog != null){
				// 串接子节点主键
				String stCatIds = "";
				List<TCatalogInfo> catList = dao().query(TCatalogInfo.class, Cnd.where("stSourceProvider","=",catalog.getStSourceProvider()).and("stNodeId","like",catalog.getStNodeId() + "%"));
				for(TCatalogInfo child : catList){
					stCatIds  +=  "'" + child.getStCatId() + "',";
				}
				if(StringUtils.isNotBlank(stCatIds)){
					stCatIds = stCatIds.substring(0, stCatIds.length()-1);
					cri.where().and("stCatId", "in", stCatIds);
				}
				int count = dao().count(TSourceApp.class, cri);
				if(count > 0){
					//return UI.ajaxDone(StatusCode.FAIL, "删除失败！请先删除该目录及其子目录下的信息资源！");
					status="2";
				}
			}
			dao().clear(TCatalogInfo.class,Cnd.where("stSourceProvider","=",catalog.getStSourceProvider()).and("stNodeId","like",catalog.getStNodeId() + "%"));
			//return ZymlUI.divSearch(StatusCode.OK, "ajaxDiv");
			status="1";
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", status);
		return result;
	}
	
	/**
	 * 取得同级最大序列号
	 * @param childList
	 * @return
	 */
	private int maxNodeCode(List<TCatalogInfo> childList) {
		int max = 0;
		for (int i = 0; i < childList.size(); i++){
			TCatalogInfo node = childList.get(i);
			String nodeCode[] = node.getStNodeId().split(Constants.NODE_ID_SEPERATOR);
			int index = Integer.parseInt(nodeCode[nodeCode.length - 1]);
			if (index > max){
				max = index;
			}
		}
		return max;
	}
}
