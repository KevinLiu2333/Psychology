package com.wondersgroup.cmc.dispatch.subsys.service.impl;

import java.util.List;

import com.wondersgroup.cmc.dispatch.DispatchContent;
import com.wondersgroup.cmc.dispatch.model.dto.IfmondefDTO;
import com.wondersgroup.cmc.dispatch.subsys.service.SubsysService;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;

public class SubsysServiceImpl implements SubsysService{

	/**
	 * 查询本平台所有监控对象及状态
	 */
	@Override
	public List<IfmondefDTO> getAllSubsysinfo() {
		String sql = "select t.lsmonstatus,t.lsmondesc,t.lsmontime,p.ifdefcode,p.ifdefname,p.iftype from ifmondef t,ifdefine p where t.ifdefineid = p.ifdefineid and t.valid = ? and p.valid = ? ";
		return CommonJdbcDaoUtils.query(sql, IfmondefDTO.class,DispatchContent.VALID,DispatchContent.VALID );
	}

	/**
	 * 查询本平台所有监控及状态（含下级平台上传）
	 */
	@Override
	public List<IfmondefDTO> getAllMoninfo() {
		String sql = "  select b.ifdefineid,b.ifdefcode,b.ifdefname,b.iftype,a.lsmontime,a.lsmonstatus,a.lsmondesc,c.submonsum,c.submonfail,c.submondetail " +
				" from ifmondef a left join ifdefine b on a.valid = ? and b.valid = ? and a.ifdefineid = b.ifdefineid  left join ifsubmon c on a.ifmondefid = c.ifmondefid ";
		return CommonJdbcDaoUtils.query(sql, IfmondefDTO.class,DispatchContent.VALID,DispatchContent.VALID );
	}

}
