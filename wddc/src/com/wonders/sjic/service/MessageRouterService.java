package com.wonders.sjic.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.sjic.IFCheckUtils;
import com.wonders.sjic.entity.HttpClientDTO;
import com.wonders.sjic.entity.InterfaceInfoBo;
import com.wonders.sjic.entity.InterfaceTransferBo;

@IocBean
public class MessageRouterService {
	@Inject
	private Dao dao;
	
	
	/**
	 * 根据接口ID获取接口
	 * @param jkid
	 * @param valid
	 * @return
	 */
	public InterfaceInfoBo getInterface(String jkid,String valid){
		if(Strings.isEmpty(jkid)||Strings.isEmpty(valid)){
			return null;
		}
		InterfaceInfoBo info = dao.fetch(InterfaceInfoBo.class,jkid);
		return info;
	}
	
	
	/**
	 * 更新交易汇总
	 * @param flag
	 * @param ifdefineid
	 */
	public void updateTranssum(Boolean flag,String jkid){
		if(flag == null || jkid == null){
			return;
		}
		int suc = 0;
		int fail = 0;
		if(flag){
			suc = 1;
		} else {
			fail = 1;
		}
		InterfaceTransferBo trans = dao.fetch(InterfaceTransferBo.class,jkid);
		trans.setTransferFailed(trans.getTransferFailed()+fail);
		trans.setTransferSuccess(trans.getTransferSuccess()+suc);
		trans.setTransferSum(trans.getTransferSum()+1);
		dao.update(trans);
	}
	
	/**
	 * 校验WS连通性
	 * @param ifdefine
	 * @return
	 */
	public HttpClientDTO checkWS(InterfaceInfoBo ifdefine) {
		return IFCheckUtils.checkInterface(ifdefine.getType(),ifdefine.getAddress());
	}
}
