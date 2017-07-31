package com.wondersgroup.cmc.dispatch.m410101.action;


import java.text.ParseException;

import com.wondersgroup.cmc.dispatch.DispatchContent;
import com.wondersgroup.cmc.dispatch.m410101.model.M410101VO;
import com.wondersgroup.cmc.dispatch.m410101.service.M410101VS;
import com.wondersgroup.cmc.dispatch.model.bo.Ifdefine;
import com.wondersgroup.cmc.dispatch.model.bo.Iftransdetail;
import com.wondersgroup.cmc.dispatch.model.bo.Ifuser;
import com.wondersgroup.cmc.dispatch.model.dto.HttpClientDTO;
import com.wondersgroup.cmc.model.dto.PageQueryDTO;
import com.wondersgroup.cmc.utils.AjaxUtils;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class M410101Action extends BaseAjaxAction {
    private static final long serialVersionUID = 1L;
    private M410101VO vo = new M410101VO();
	private M410101VS m410101VS;
	
	@Override
	public ValueObject getValueObject() {
		return vo;
	}

	public PageQueryDTO getQueryDTO(){
		PageQueryDTO dto = new PageQueryDTO(this.getServletRequest());
		return dto;
	}
	
	public M410101VO getVo() {
		return vo;
	}

	public void setVO(M410101VO vo) {
		this.vo = vo;
	}

	public M410101VS getM410101VS() {
		return m410101VS;
	}

	public void setM410101VS(M410101VS m410101VS) {
		this.m410101VS = m410101VS;
	}
	
	/**
	 * 新增接口
	 * @return
	 */
	public String add(){
		m410101VS.insert(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}

	/**
	 * 修改接口
	 * @return
	 */
	public String edit(){
		m410101VS.update(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
	
	/**
	 * 删除接口
	 * @return
	 */
	public String delete(){
		m410101VS.delete(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
	
	/**
	 * 测试接口
	 * @return
	 */
	public String test(){
		HttpClientDTO status = m410101VS.test(vo);
		this.createJSonData(AjaxUtils.getJsonData(status));
		return AJAX;
	}
	
	/**
	 * 加载接口
	 * @return
	 */
	public String load() {
		Ifdefine ifdefine = m410101VS.load(vo);
		createJSonData(AjaxUtils.getJsonData(ifdefine));
		return AJAX;
	}

	/**
	 * 分页查询接口
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String query() {
		// (1) new 查询条件实例
		PageQueryDTO dto = getQueryDTO();
		// 默认条件
		dto.setParam("valid", DispatchContent.VALID);	//默认有效
		DataGrid dataGrid = m410101VS.query(dto);
		createJSonData(AjaxUtils.getJsonData(dataGrid));
		return AJAX;
	}
	
	/**
	 * 分页查询监控明细
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	public String querytransdetail() throws ParseException {
		// (1) new 查询条件实例
		PageQueryDTO dto = getQueryDTO();
		// 默认条件
		DataGrid dataGrid = m410101VS.querytransdetail(dto);
		createJSonData(AjaxUtils.getJsonData(dataGrid));
		return AJAX;
	}
	
	/**
	 * 加载接口
	 * @return
	 */
	public String loaderr() {
		Iftransdetail iftransdetail = m410101VS.loaderr(vo);
		createJSonData(AjaxUtils.getJsonData(iftransdetail));
		return AJAX;
	}
	
	/**
	 * 加载接口
	 * @return
	 */
	public String loadxml() {
		Iftransdetail iftransdetail = m410101VS.loadxml(vo);
		createJSonData(AjaxUtils.getJsonData(iftransdetail));
		return AJAX;
	}
	
	/**
	 * 重新调用接口
	 * @return
	 */
	public String resendxml(){
		m410101VS.resendxml(vo);
		createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
	
	/**
	 * 新增接口用户
	 * @return
	 */
	public String adduser(){
		m410101VS.insertuser(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}

	/**
	 * 修改接口用户
	 * @return
	 */
	public String edituser(){
		m410101VS.updateuser(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
	
	/**
	 * 删除接口用户
	 * @return
	 */
	public String deleteuser(){
		m410101VS.deleteuser(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
	
	/**
	 * 加载接口用户
	 * @return
	 */
	public String loaduser() {
		Ifuser ifuser = m410101VS.loaduser(vo);
		createJSonData(AjaxUtils.getJsonData(ifuser));
		return AJAX;
	}

	/**
	 * 分页查询接口用户
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryusers() {
		// (1) new 查询条件实例
		PageQueryDTO dto = getQueryDTO();
		// 默认条件
		dto.setParam("valid", DispatchContent.VALID);	//默认有效
		DataGrid dataGrid = m410101VS.queryusers(dto);
		createJSonData(AjaxUtils.getJsonData(dataGrid));
		return AJAX;
	}
	
	/**
	 * 查询接口用户授权/未授权列表
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String listblindingif() {
		// (1) new 查询条件实例
		PageQueryDTO dto = getQueryDTO();
		// 默认条件
		dto.setParam("iftype", DispatchContent.IFTYPE_IF);	//默认条线接口
		dto.setParam("valid", DispatchContent.VALID);	//默认有效
		DataGrid dataGrid = m410101VS.listblindingif(dto);
		createJSonData(AjaxUtils.getJsonData(dataGrid));
		return AJAX;
	}
	
	/**
	 * 接口用户授权
	 * @return
	 */
	public String addifuserauth(){
		m410101VS.addifuserauth(vo);
		createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
	
	/**
	 * 接口用户授权移除
	 * @return
	 */
	public String removeifuserauth(){
		m410101VS.removeifuserauth(vo);
		createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}
}
