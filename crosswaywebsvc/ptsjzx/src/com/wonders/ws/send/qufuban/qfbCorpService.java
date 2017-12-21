package com.wonders.ws.send.qufuban;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.Ioc;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.WsUitl;
import com.wonders.ws.dao.CorpDao;
import com.wonders.ws.dao.KeyChecker;

public class qfbCorpService {
	Ioc			ioc	= null;
	CorpDao		dao = null;
	
	
	/**
	 * 网厅
	 * 根据法人名称、四个代码查询法人登记信息
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	public String getCorpRegInfoToWangting(String key,String keyword,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpRegInfoToWangting");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				Map<String, String> result=dao.getCorpRegInfoToWangting(keyword,serviceUser);
				return WsUitl.getResult(result, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 网厅
	 * 根据法人名称、四个代码查询法人资质信息
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getCorpLicenseToWangting(String key,String corpname,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpLicenseToWangting");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				Map<String, Object> result=dao.getCorpLicenseToWangting(corpname,serviceUser);
				return WsUitl.ListResult((List) result.get("list"), returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 网厅
	 * 根据法人名称、四个代码查询法人处罚信息
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getCorpCfToWangting(String key,String corpname,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpCfToWangting");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				Map<String, Object> result=dao.getCorpCfToWangting(corpname,serviceUser);
				return WsUitl.ListResult((List) result.get("list"), returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}



}
