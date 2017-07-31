package com.wondersgroup.cmc.userManager.m380101.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.wondersgroup.cmc.model.dto.QueryDTO;
import com.wondersgroup.cmc.userManager.m380101.model.M380101VO;
import com.wondersgroup.cmc.userManager.m380101.service.M380101VS;
import com.wondersgroup.cmc.utils.AjaxUtils;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;
import com.wondersgroup.wssip.application.SessionConstants;
import com.wondersgroup.wssip.model.bo.UaRoleInfo;
import com.wondersgroup.wssip.model.bo.UaUserInfo;
import com.wondersgroup.wssip.util.StringTools;


public class M380101Action extends BaseAjaxAction{
	private static final long serialVersionUID = 1L;
	private M380101VO vo = new M380101VO();
	private M380101VS m380101VS;

	@Override
	public ValueObject getValueObject() {
		return vo;
	}
	
	
	@SuppressWarnings("rawtypes")
	public String query() {
		// (1) new 查询条件实例
		QueryDTO dto = new QueryDTO(this.getServletRequest());	
		// 默认条件
		dto.setParam("status", "1");	//默认有效
		DataGrid dataGrid = m380101VS.query(dto);
		createJSonData(AjaxUtils.getJsonData(dataGrid));
		return AJAX;
	}
	
	public String add(){
		m380101VS.insert(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
	
	public String edit(){
		m380101VS.update(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
	
	public String load() {
		UaUserInfo user = m380101VS.load(vo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", user);
		String roleids = "";
		for (UaRoleInfo role : user.getRoleList()){
			roleids = roleids + role.getRoleid() + ",";
		}
		if (StringTools.hasText(roleids)){
			roleids = roleids.substring(0,roleids.length()-1);
		}
		map.put("roleids", roleids);
		createJSonData(AjaxUtils.getJsonData(map));
		return AJAX;
	}
	
	public String delete(){
		m380101VS.delete(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
	
	public String loadRole(){
		List<Map<String, String>> roleList = m380101VS.loadRole();
		String str = VOUtils.getJsonDataFromCollection(roleList);
		str = str.replace("\"id\"", "id");
		str = str.replace("\"text\"", "text");
		createJSonData(str);
		return AJAX;
	}
	
	public String checkLoginName(){
		String flag = m380101VS.checkLoginName(vo);
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("flag", flag);
		createJSonData(AjaxUtils.getJsonData(resultMap));
		return AJAX;
	}
	public String changePsd(){
		String flag = m380101VS.changePsd(vo);
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("flag", flag);
		createJSonData(AjaxUtils.getJsonData(resultMap));
		return AJAX;
	}
	
	public String queryItems(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		List<Map<String, String>> roleList = m380101VS.queryItems(session);
		String str = VOUtils.getJsonDataFromCollection(roleList);
		str = str.replace("\"id\"", "id");
		str = str.replace("\"text\"", "text");
		createJSonData(str);
		return AJAX;
	}
	
	public String getExt1(){
		String ext1 = m380101VS.getExt1();
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("ext1", ext1);
		createJSonData(AjaxUtils.getJsonData(resultMap));
		return AJAX;
	}
	
	public String saveItems(){
		m380101VS.saveItems(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
	
	public String saveAppids(){
		m380101VS.saveAppids(vo);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(StringTools.hasText(vo.getExt2())){
			session.setAttribute(SessionConstants.APPIDS, vo.getExt2());
		}else {
			if(session.getAttribute(SessionConstants.APPIDS) != null){
				session.removeAttribute(SessionConstants.APPIDS);
			}
		}
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
	
	
	public M380101VO getVo() {
		return vo;
	}

	public void setVo(M380101VO vo) {
		this.vo = vo;
	}

	public M380101VS getM380101VS() {
		return m380101VS;
	}

	public void setM380101VS(M380101VS m380101vs) {
		m380101VS = m380101vs;
	}
	
	
}
