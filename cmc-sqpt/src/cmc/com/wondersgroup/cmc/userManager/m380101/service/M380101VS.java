package com.wondersgroup.cmc.userManager.m380101.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.wondersgroup.cmc.model.dto.QueryDTO;
import com.wondersgroup.cmc.userManager.m380101.model.M380101VO;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;
import com.wondersgroup.wssip.model.bo.UaUserInfo;

public interface M380101VS {
	@SuppressWarnings("rawtypes")
	public DataGrid query(QueryDTO dto);
	public void insert(M380101VO vo);
	public void update(M380101VO vo);
	public void delete(M380101VO vo);
	public String checkLoginName(M380101VO vo);
	public List<Map<String, String>> loadRole();
	public UaUserInfo load(M380101VO vo);
	public String changePsd(M380101VO vo);
	public List<Map<String, String>> queryItems(HttpSession session);
	public String getExt1();
	public void saveItems(M380101VO vo);
	public void saveAppids(M380101VO vo);
}
