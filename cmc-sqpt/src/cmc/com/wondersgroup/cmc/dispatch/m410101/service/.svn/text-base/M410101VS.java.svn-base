package com.wondersgroup.cmc.dispatch.m410101.service;


import java.text.ParseException;

import com.wondersgroup.cmc.dispatch.m410101.model.M410101VO;
import com.wondersgroup.cmc.dispatch.model.bo.Ifdefine;
import com.wondersgroup.cmc.dispatch.model.bo.Iftransdetail;
import com.wondersgroup.cmc.dispatch.model.bo.Ifuser;
import com.wondersgroup.cmc.dispatch.model.dto.HttpClientDTO;
import com.wondersgroup.cmc.model.dto.PageQueryDTO;
import com.wondersgroup.cmc.utils.pagequery.DataGrid;

public interface M410101VS {
	 /**
     * 新增接口
     * @param vo
     */
    public void insert(M410101VO vo);
    /**
     * 修改接口
     * @param vo
     */
	public void update(M410101VO vo);
	/**
	 * 删除接口
	 * @param vo
	 */
	public void delete(M410101VO vo);
	/**
	 * 加载
	 * @param vo
	 * @return
	 */
	public Ifdefine load(M410101VO vo);
	/**
     * 测试接口
     * @param vo
     * return
     */
	public HttpClientDTO test(M410101VO vo);
	/**
     * 分页查询接口
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
	public DataGrid querytransdetail(PageQueryDTO dto) throws ParseException;
	
	/**
	 * 加载
	 * @param vo
	 * @return
	 */
	public Iftransdetail loadxml(M410101VO vo);
	
	/**
	 * 加载
	 * @param vo
	 * @return
	 */
	public Iftransdetail loaderr(M410101VO vo);
	
	/**
	 * 重新调用接口
	 * @param vo
	 */
	public void resendxml(M410101VO vo);
	
	/**
	 * 新增接口用户
	 * @param vo
	 */
	public void insertuser(M410101VO vo);
	
	/**
	 * 修改接口用户
	 * @param vo
	 */
	public void updateuser(M410101VO vo);
	
	/**
	 * 删除接口用户
	 * @param vo
	 */
	public void deleteuser(M410101VO vo);
	
	/**
	 * 加载接口用户
	 * @param vo
	 * @return
	 */
	public Ifuser loaduser(M410101VO vo);
	
	/**
	 * 分页查询接口用户
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid queryusers(PageQueryDTO dto);
	
	/**
	 * 查询接口用户授权/未授权列表
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid listblindingif(PageQueryDTO dto);
	
	/**
	 * 接口用户授权
	 * @param vo
	 */
	public void addifuserauth(M410101VO vo);
	
	/**
	 * 接口用户授权移除
	 * @param vo
	 */
	public void removeifuserauth(M410101VO vo);
	
}
