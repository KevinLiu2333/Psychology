package com.wondersgroup.cmc.commons.event.service.impl;

import com.wondersgroup.cmc.commons.event.model.bo.BusilogInfo;
import com.wondersgroup.cmc.commons.event.model.dto.BusilogCreateDTO;
import com.wondersgroup.cmc.commons.event.service.BusinessLogService;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.util.BeanTools;

public class BusinessLogServiceImpl implements BusinessLogService {
	
	public void createBusinessLog(BusilogCreateDTO busilogCreateDTO){
		BusilogInfo busilog = new BusilogInfo();
		BeanTools.copyProperties(busilogCreateDTO, busilog);
	    CommonHibernateDaoUtils.save(busilog);
	    busilogCreateDTO.setBusilogid(busilog.getBusilogid());
	}
}
