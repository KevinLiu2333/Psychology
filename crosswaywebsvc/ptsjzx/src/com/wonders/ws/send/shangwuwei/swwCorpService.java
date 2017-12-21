package com.wonders.ws.send.shangwuwei;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.Ioc;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.WsUitl;
import com.wonders.ws.dao.CorpDao;
import com.wonders.ws.dao.KeyChecker;

public class swwCorpService {
	Ioc		ioc	= null;
	CorpDao	dao	= null;
	/**
	 * 商务委 
	 * 根据法人名称、社会统一信用代码、组织机构代码返回登记信息
	 * @param CORP_NAME,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	public String getCorpRegInfoToShangwuwei(String key, String keyword, String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker = ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser = checker.keyckeck(key, "getCorpRegInfoToShangwuwei");
			if (serviceUser == null) {
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if (serviceUser.getEndDate().before(new Date())) {
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(CorpDao.class);
				Map<String, String> result = dao.getCorpRegInfoToShangwuwei(keyword, serviceUser);
				return WsUitl.getResult(result, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * 商务委
	 * 根据法人名称、社会统一信用代码、组织机构代码返回资质信息
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getCorpLicenseToShangwuwei(String key,String keyword,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpLicenseToShangwuwei");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				Map<String, Object> result=dao.getCorpLicenseToShangwuwei(keyword,serviceUser);
				return WsUitl.ListResult((List) result.get("list"), returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 商务委
	 * 根据法人名称、社会统一信用代码、组织机构代码返回处罚信息
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getCorpCfToShangwuwei(String key,String keyword,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpCfToShangwuwei");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				Map<String, Object> result=dao.getCorpCfToShangwuwei(keyword,serviceUser);
				return WsUitl.ListResult((List) result.get("list"), returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
