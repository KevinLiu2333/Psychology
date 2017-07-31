package com.wondersgroup.cmc.commons.event.service;

import com.wondersgroup.cmc.commons.event.model.dto.BusilogCreateDTO;

public abstract interface BusinessLogService {
	public abstract void createBusinessLog(BusilogCreateDTO paramBusilogCreateDTO);
}
