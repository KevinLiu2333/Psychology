package com.wondersgroup.cmc.commons.event;

import java.util.Date;

import com.wondersgroup.cmc.commons.event.model.dto.BusilogCreateDTO;
import com.wondersgroup.cmc.commons.event.service.BusinessLogService;
import com.wondersgroup.cmc.utils.UserContextUtils;

public class BusilogUtils {
	private static BusinessLogService businessLogService;
	
	public void setBusinessLogService(BusinessLogService businessLogService) {
		BusilogUtils.businessLogService = businessLogService;
	}
	
	private static void populateBusinessLog(BusilogCreateDTO busilogCreateDTO) {
		busilogCreateDTO.setBusitype("default");
		busilogCreateDTO.setOperatorcode(UserContextUtils.getOperatorId());
		busilogCreateDTO.setOperatorname(UserContextUtils.getOperatorName());
		busilogCreateDTO.setOperorgan(UserContextUtils.getDepartmentCode());
		if (busilogCreateDTO.getOperorgan()==null){
			busilogCreateDTO.setOperorgan("unknown");
		}
		busilogCreateDTO.setOpertime(new Date());
	 }
	
	public static BusilogCreateDTO createBusinessLog(){
	    return createBusinessLog(new BusilogCreateDTO());
	}
	public static BusilogCreateDTO createBusinessLog(BusilogCreateDTO busilogCreateDTO){
	    populateBusinessLog(busilogCreateDTO);
	    businessLogService.createBusinessLog(busilogCreateDTO);
	    return busilogCreateDTO;
	}
	
	public static BusilogCreateDTO createBusinessLog(String busiType){
		BusilogCreateDTO busilogCreateDTO = new BusilogCreateDTO();
		populateBusinessLog(busilogCreateDTO);
		if ( busiType!=null && !"".equals(busiType) ) busilogCreateDTO.setBusitype(busiType);
		businessLogService.createBusinessLog(busilogCreateDTO);
		return busilogCreateDTO;
	}
}
