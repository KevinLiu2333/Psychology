package com.wondersgroup.cmc.dispatch.m420101.service;


import java.text.ParseException;
import java.util.List;

import com.wondersgroup.cmc.dispatch.m420101.model.M420101VO;
import com.wondersgroup.cmc.dispatch.model.dto.IfdefineDTO;
import com.wondersgroup.cmc.model.dto.PageQueryDTO;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;

public interface M420101VS {
	 /**
     * 新增监控定义配置
     * @param vo
     */
    public void insert(M420101VO vo);
	/**
	 * 删除监控定义配置
	 * @param vo
	 */
	public void delete(M420101VO vo);
	/**
     * 分页查询监控
     * @param vo
     * return
     */
	@SuppressWarnings("rawtypes")
	public DataGrid query(PageQueryDTO dto);
	
	/**
     * 分页查询监控明细
     * @param vo
     * return
	 * @throws ParseException 
     */
	@SuppressWarnings("rawtypes")
	public DataGrid queryMonDetail(PageQueryDTO dto) throws ParseException;
	
	public List<IfdefineDTO> getIfDefine(PageQueryDTO dto);
}
