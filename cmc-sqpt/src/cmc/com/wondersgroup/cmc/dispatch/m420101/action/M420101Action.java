package com.wondersgroup.cmc.dispatch.m420101.action;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wondersgroup.cmc.dispatch.DispatchContent;
import com.wondersgroup.cmc.dispatch.m420101.model.M420101VO;
import com.wondersgroup.cmc.dispatch.m420101.service.M420101VS;
import com.wondersgroup.cmc.dispatch.model.dto.IfdefineDTO;
import com.wondersgroup.cmc.model.dto.PageQueryDTO;
import com.wondersgroup.cmc.utils.AjaxUtils;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class M420101Action extends BaseAjaxAction {
    private static final long serialVersionUID = 1L;
    private M420101VO vo = new M420101VO();
	private M420101VS m420101VS;
	
	@Override
	public ValueObject getValueObject() {
		return vo;
	}

	public PageQueryDTO getQueryDTO(){
		PageQueryDTO dto = new PageQueryDTO(this.getServletRequest());
		return dto;
	}
	
	public M420101VO getVo() {
		return vo;
	}

	public void setVO(M420101VO vo) {
		this.vo = vo;
	}

	public M420101VS getM420101VS() {
		return m420101VS;
	}

	public void setM420101VS(M420101VS m420101VS) {
		this.m420101VS = m420101VS;
	}
	
	/**
	 * 新增监控定义配置
	 * @return
	 */
	public String add(){
		m420101VS.insert(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
		return AJAX;
	}

	
	/**
	 * 删除接口
	 * @return
	 */
	public String delete(){
		m420101VS.delete(vo);
		this.createJSonData(AjaxUtils.getJsonData(vo));
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
		DataGrid dataGrid = m420101VS.query(dto);
		createJSonData(AjaxUtils.getJsonData(dataGrid));
		return AJAX;
	}
	
	
	/**
	 * 分页查询监控明细
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	public String queryMonDetail() throws ParseException {
		// (1) new 查询条件实例
		PageQueryDTO dto = getQueryDTO();
		// 默认条件
		dto.setParam("valid", DispatchContent.VALID);	//默认有效
		DataGrid dataGrid = m420101VS.queryMonDetail(dto);
		createJSonData(AjaxUtils.getJsonData(dataGrid));
		return AJAX;
	}
	
	
	public String getIfDefine() {
		PageQueryDTO dto = getQueryDTO();
		dto.setParam("valid", DispatchContent.VALID);	//默认有效
		List<IfdefineDTO> IfdefineList = m420101VS.getIfDefine(dto);
		List<Map<String, String>> idtextList = new ArrayList<Map<String,String>>();
		for(int i=0;i<IfdefineList.size();i++){
			Map<String, String> map = new HashMap<String,String>();
			map.put("id", IfdefineList.get(i).getIfdefineid().toString());
			map.put("text", IfdefineList.get(i).getIfdefname());
			map.put("code", IfdefineList.get(i).getIfdefcode());
			idtextList.add(map);
		}
/*		List<Idtext> idtextList = new ArrayList<Idtext>();
		for(int i=0;i<dataGrid.size();i++){
			Idtext idtext = new Idtext();
			idtext.setId(dataGrid.get(i).getIfdefineid().toString());
			idtext.setText(dataGrid.get(i).getIfdefname());
			idtextList.add(idtext);
		}*/
		createJSonData(VOUtils.getJsonDataFromCollection(idtextList));
		return AJAX;
	}
	
}
