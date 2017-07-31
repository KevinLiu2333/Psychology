package com.wondersgroup.cmc.dispatch.m430101.action;

import java.util.HashMap;
import java.util.List;

import com.wondersgroup.cmc.dispatch.model.dto.IfmondefDTO;
import com.wondersgroup.cmc.dispatch.subsys.service.SubsysService;
import com.wondersgroup.cmc.utils.AjaxUtils;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

public class M430101Action extends BaseAjaxAction {
    private static final long serialVersionUID = 1L;
	private SubsysService subsysService;

	public void setSubsysService(SubsysService subsysService) {
		this.subsysService = subsysService;
	}
	
	/**
	 * 加载接口
	 * @return
	 */
	public String load() {
		List<IfmondefDTO> ifmondefDTOs = subsysService.getAllMoninfo();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ifmons", ifmondefDTOs);
		createJSonData(AjaxUtils.getJsonData(map));
		return AJAX;
	}

}
