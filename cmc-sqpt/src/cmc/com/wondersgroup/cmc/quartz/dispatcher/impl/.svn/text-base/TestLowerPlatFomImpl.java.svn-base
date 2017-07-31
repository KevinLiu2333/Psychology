package com.wondersgroup.cmc.quartz.dispatcher.impl;

import java.util.List;
import java.util.Map;

import com.wondersgroup.cmc.dispatch.DispatchContent;
import com.wondersgroup.cmc.dispatch.ifcheck.service.IfCheckVS;
import com.wondersgroup.cmc.dispatch.model.bo.Ifdefine;
import com.wondersgroup.cmc.quartz.dispatcher.TriggerDispatcher;
import com.wondersgroup.cmc.utils.pagequery.BaseQueryVSImpl;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;

public class TestLowerPlatFomImpl extends BaseQueryVSImpl implements TriggerDispatcher{
	private IfCheckVS ifCheckVS;
	
	public void setIfCheckVS(IfCheckVS ifCheckVS) {
		this.ifCheckVS = ifCheckVS;
	}
	public Object executeMethod(Map<String, Object> filter) {
		//查询监控对象
		String sql = new String("SELECT a.* FROM IFDEFINE a,IFMONDEF b WHERE a.IFDEFINEID = b.IFDEFINEID AND a.VALID = '1' AND b.VALID = '1' AND a.iftype = ? ");
		List<Ifdefine> ifdefineList = CommonJdbcDaoUtils.query(sql, Ifdefine.class, DispatchContent.IFTYPE_SUBSYS);
		for(int i=0;i<ifdefineList.size();i++){
			ifCheckVS.ifcheck(ifdefineList.get(i));
		}
		return null;
	}

}
