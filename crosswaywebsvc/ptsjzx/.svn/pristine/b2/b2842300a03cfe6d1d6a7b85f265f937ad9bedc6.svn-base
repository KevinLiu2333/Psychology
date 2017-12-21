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

public class qfbXingzhengCorpService {
	Ioc		ioc	= null;
	CorpDao	dao	= null;

	/**
	 * 行政服务中心 
	 * 根据法人名称、组织机构代码、社会统一信用代码查询法人登记信息
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	public String getCorpInfoToXingzheng(String key, String keyword, String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker = ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser = checker.keyckeck(key, "getCorpInfoToXingzheng");
			if (serviceUser == null) {
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if (serviceUser.getEndDate().before(new Date())) {
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(CorpDao.class);
				Map<String, String> result = dao.getCorpInfoToXingzheng(keyword, serviceUser);
				return WsUitl.getResult(result, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 行政服务中心 
	 * 根据法人名称、组织机构代码、社会统一信用代码查询法人资质信息
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getCorpLicenseToXingzheng(String key, String keyword, String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker = ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser = checker.keyckeck(key, "getCorpLicenseToXingzheng");
			if (serviceUser == null) {
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if (serviceUser.getEndDate().before(new Date())) {
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(CorpDao.class);
				Map<String, Object> result = dao.getCorpLicenseToXingzheng(keyword, serviceUser);
				return WsUitl.ListResult((List) result.get("list"), returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 行政服务中心 
	 * 根据法人名称、组织机构代码、社会统一信用代码查询法人处罚信息
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getCorpCfToXingzheng(String key, String keyword, String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker = ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser = checker.keyckeck(key, "getCorpCfToXingzheng");
			if (serviceUser == null) {
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if (serviceUser.getEndDate().before(new Date())) {
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(CorpDao.class);
				Map<String, Object> result = dao.getCorpCfToXingzheng(keyword, serviceUser);
				return WsUitl.ListResult((List) result.get("list"), returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 行政服务中心 
	 * 根据法人名称、组织机构代码、社会统一信用代码查询网上大厅信息
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getWangtingToXingzheng(String key, String keyword, String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker = ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser = checker.keyckeck(key, "getWangtingToXingzheng");
			if (serviceUser == null) {
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if (serviceUser.getEndDate().before(new Date())) {
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(CorpDao.class);
				Map<String, Object> result = dao.getWangtingToXingzheng(keyword, serviceUser);
				return WsUitl.ListResult((List) result.get("list"), returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 行政服务中心 
	 * 根据法人名称、组织机构代码、社会统一信用代码查询网上大厅信息
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getXyxxToXingzheng(String key, String keyword, String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker = ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser = checker.keyckeck(key, "getXyxxToXingzheng");
			if (serviceUser == null) {
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if (serviceUser.getEndDate().before(new Date())) {
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(CorpDao.class);
				Map<String, Object> result = dao.getXyxxToXingzheng(keyword, serviceUser);
				return WsUitl.ListResult((List) result.get("list"), returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 行政服务中心 
	 * 根据法人名称、组织机构代码、社会统一信用代码查询一数一源数据填报信息
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getYsyyToXingzheng(String key, String keyword, String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker = ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser = checker.keyckeck(key, "getYsyyToXingzheng");
			if (serviceUser == null) {
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if (serviceUser.getEndDate().before(new Date())) {
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(CorpDao.class);
				Map<String, Object> result = dao.getYsyyToXingzheng(keyword, serviceUser);
				return WsUitl.ListResult((List) result.get("list"), returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
