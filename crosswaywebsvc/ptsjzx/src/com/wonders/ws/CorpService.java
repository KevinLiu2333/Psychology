package com.wonders.ws;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.Ioc;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.bean.CorpInfoBean;
import com.wonders.ws.bean.CorpLicenseBean;
import com.wonders.ws.bean.PunishNoteEntyBean;
import com.wonders.ws.dao.CorpDao;
import com.wonders.ws.dao.KeyChecker;

public class CorpService {
	Ioc			ioc	= null;
	CorpDao		dao = null;
	/**
	 * 根据组织机构代码查询法人信息
	 * @param organ_code
	 * @return
	 */
	public String getCorpInfo(String key,String organcode,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpInfo");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				CorpInfoBean bean=dao.getCropInfoBean(organcode,serviceUser);
				return WsUitl.getResult(bean, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 根据法人名称获取法人列表
	 * @param cropname
	 * @return
	 */
	public String getCorpInfosByCropName(String key,String corpname,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpInfosByCropName");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				List<CorpInfoBean> list=dao.getCropInfoBeansByCropName(corpname,serviceUser);
				return WsUitl.ListResult(list, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据organcode获取法人资质信息
	 * @param organcode
	 * @return
	 */
	public String getCorpLicense(String key,String organcode,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpLicense");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				CorpLicenseBean bean=dao.getCorpLicenseBean(organcode,serviceUser);
				return WsUitl.getResult(bean, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据单位名称获取法人资质信息列表
	 * @param key
	 * @param corpname
	 * @return
	 */
	public String getCorpLicensesByCorpName(String key,String corpname,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpLicensesByCorpName");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				List<CorpLicenseBean> list=dao.getCorpLicenseBeansByCorpName(corpname, serviceUser);
				return WsUitl.ListResult(list, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据组织机构代码获取法人处罚信息
	 * @param key
	 * @param organcode
	 * @return
	 */
	public String getPunishNoteEntyByOrganCode(String key,String organcode,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getPunishNoteEntyByOrganCode");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				List<PunishNoteEntyBean> list=dao.getPunishNoteEntyBeansByOrganCode(organcode, serviceUser);
				return WsUitl.ListResult(list, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据法人名称获取法人处罚信息
	 * @param key
	 * @param organcode
	 * @return
	 */
	public String getPunishNoteEntyByCorpname(String key,String corpname,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getPunishNoteEntyByCorpname");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				List<PunishNoteEntyBean> list=dao.getPunishNoteEntyBeansByCorpname(corpname, serviceUser);
				return WsUitl.ListResult(list, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据法定代表人获取法人列表
	 * @param key
	 * @param personname
	 * @return
	 */
	public String getCorpInfosByPersonName(String key,String personname,String returntype){
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpInfosByPersonName");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(CorpDao.class);
				List<CorpInfoBean> list=dao.getCorpInfoBeansByPersonName(personname, serviceUser);
				return WsUitl.ListResult(list, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 /**
		 * 根据注册资金获取法人列表
		 * @param key
		 * @param regcapital3
		 * @return
		 */
		public String getCorpInfosByRegcapital(String key,String regcapital,String returntype){
			 try {
					if(ioc==null){
						ioc = IocSingle.getInstance();
					}
					KeyChecker checker=ioc.get(KeyChecker.class);
					ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpInfosByRegcapital");
					if(serviceUser==null){
						return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
					}
					if(serviceUser.getEndDate().before(new Date())){
						return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
					}
					if(dao==null){
						dao = ioc.get(CorpDao.class);
						List<CorpInfoBean> list=dao.getCorpInfoBeansByRegcapital(Double.parseDouble(regcapital), serviceUser);
						return WsUitl.ListResult(list, returntype);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			 
		 }
		
		 
		 /**
		 * 根据注册日期获取法人列表
		 * @param key
		 * @param establishdate
		 * @return
		 */
		public String getCorpInfosByEstablishDate(String key,String establishdate,String returntype){
			 try {
					if(ioc==null){
						ioc = IocSingle.getInstance();
					}
					KeyChecker checker=ioc.get(KeyChecker.class);
					ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpInfosByEstablishDate");
					if(serviceUser==null){
						return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
					}
					if(serviceUser.getEndDate().before(new Date())){
						return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
					}
					if(dao==null){
						dao = ioc.get(CorpDao.class);
						List<CorpInfoBean> list=dao.getCorpInfoBeansByEstablishDate(establishdate, serviceUser);
						return WsUitl.ListResult(list, returntype);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			 
		 }
		/**
		 * 根据法人名称、组织机构代码或统一社会信用代码查询法人信息
		 * @param corp_name,ORGAN_CODE,UNI_SC_ID
		 * @return
		 */
		public String getCorpInfoByNameOrgancodeUniscid(String key,String keyword,String returntype){
			try {
				if(ioc==null){
					ioc = IocSingle.getInstance();
				}
				KeyChecker checker=ioc.get(KeyChecker.class);
				ApiServiceUser serviceUser=checker.keyckeck(key, "getCorpInfoByNameOrgancodeUniscid");
				if(serviceUser==null){
					return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
				}
				if(serviceUser.getEndDate().before(new Date())){
					return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
				}
				if(dao==null){
					dao = ioc.get(CorpDao.class);
					Map<String, String> result=dao.getCorpInfoByNameOrgancodeUniscid(keyword,serviceUser);
					return WsUitl.getResult(result, returntype);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		
}
