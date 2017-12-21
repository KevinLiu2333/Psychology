package com.wonders.zymlgl.service;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.zymlgl.entity.ResourceDetails;
import com.wonders.zymlgx.entity.ResourceApplyDetails;

@IocBean
public class ApplyResourceService {
	@Inject
	private Dao dao;
	
	/**
	 * 根据申请ID获取资源项中文名称.
	 * @param applyId 申请ID
	 */
	public List<String> getResourcItemsByapplyId(String applyId){
		List<String> resourceItems = new ArrayList<String>();
		List<ResourceDetails> resourceDetailsList = getResourceDetailsByApplyId(applyId);
		if(resourceDetailsList != null && resourceDetailsList.size() > 0){
			for(ResourceDetails resourceDetails : resourceDetailsList){
				if(resourceDetails != null){
					if(!Strings.isEmpty(resourceDetails.getDataItemName())){
						resourceItems.add(resourceDetails.getDataItemName());
					}
				}
			}
		}
		return resourceItems;
	}

	/**
	 * 获取普遍共享资源项中文名称.
	 */
	public List<String> getPbgxByApplyId(String applyId) {
		List<String> pbgxList = new ArrayList<String>();
		List<ResourceDetails> resourceDetailsList = getResourceDetailsByApplyId(applyId);
		for(ResourceDetails applyDetails : resourceDetailsList){
			if("1".equals(applyDetails.getShareProperty())){
				pbgxList.add(applyDetails.getDataItemName());
			}
		}
		return pbgxList;
	}

	/**
	 * 获取按需共享资源项中文名称.
	 */
	public List<String> getAxgxByApplyId(String applyId) {
		List<String> axgxList = new ArrayList<String>();
		List<ResourceDetails> resourceDetailsList = getResourceDetailsByApplyId(applyId);
		for(ResourceDetails applyDetails : resourceDetailsList){
			if("2".equals(applyDetails.getShareProperty())){
				axgxList.add(applyDetails.getDataItemName());
			}
		}
		return axgxList;
	}
	
	private List<ResourceDetails> getResourceDetailsByApplyId(String applyId){
		List<ResourceDetails> resourceDetailsList = new ArrayList<ResourceDetails>();
		List<ResourceApplyDetails> resourceApplyDetailsList = dao.query(ResourceApplyDetails.class, Cnd.where("applyId", "=", applyId));
		for(ResourceApplyDetails applyDetails : resourceApplyDetailsList){
			ResourceDetails resourceDetails = dao.fetch(ResourceDetails.class, Cnd.where("detailsId", "=", applyDetails.getResourceDetailsId()));
			resourceDetailsList.add(resourceDetails);
		}
		return resourceDetailsList;
	}

}
