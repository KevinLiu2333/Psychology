package com.wondersgroup.cmc.dispatch.ifcheck.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;

import com.wondersgroup.cmc.dispatch.DispatchContent;
import com.wondersgroup.cmc.dispatch.IFCheckUtils;
import com.wondersgroup.cmc.dispatch.ifcheck.service.IfCheckVS;
import com.wondersgroup.cmc.dispatch.model.bo.Ifdefine;
import com.wondersgroup.cmc.dispatch.model.bo.Ifmondef;
import com.wondersgroup.cmc.dispatch.model.bo.Ifmondetail;
import com.wondersgroup.cmc.dispatch.model.dto.HttpClientDTO;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.util.BeanTools;

public class IfCheckVSImpl implements IfCheckVS {
	private static final Logger logger = Logger.getLogger(IfCheckVSImpl.class);
	/**
	  * 校验接口联通性
	  * @param ifdefine
	  */
	public void ifcheck(Ifdefine ifdefine) {
		//校验接口
		HttpClientDTO httpClientDTO = IFCheckUtils.checkInterface(ifdefine.getIfsubtype(), ifdefine.getIfuri());
		try {
			//保存接口校验信息
			Ifmondef ifmondef = CommonHibernateDaoUtils.get(" from Ifmondef t where t.ifdefineid=? AND t.valid = ? ", ifdefine.getIfdefineid(),DispatchContent.VALID);
			ifmondef.setLsmontime(new Date());//上次监控时间
			ifmondef.setLsmondesc(httpClientDTO.getDetailmsg());//上次监控备注
			ifmondef.setLsmonstatus(httpClientDTO.getCode());//上次监控状态
			CommonHibernateDaoUtils.update(ifmondef);//更新监控定义配置表
			Ifmondetail ifmondetail = new Ifmondetail();
			BeanTools.copyProperties(ifmondef, ifmondetail);
			CommonHibernateDaoUtils.save(ifmondetail);//插入监控明细表
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CI:保存校验接口信息失败，失败原因["+e.getMessage()+"]");
		}
	}

}
