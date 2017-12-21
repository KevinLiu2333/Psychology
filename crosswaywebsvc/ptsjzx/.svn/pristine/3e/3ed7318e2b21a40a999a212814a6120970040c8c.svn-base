package com.wonders.ws.send.huanbaoju;

import java.util.Date;
import java.util.Map;

import org.nutz.ioc.Ioc;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.WsUitl;
import com.wonders.ws.dao.CorpDao;
import com.wonders.ws.dao.KeyChecker;

public class hbjCorpService {
	Ioc		ioc	= null;
	CorpDao	dao	= null;

	/**
	 * 环保局 
	 * 按关键字查询登记信息（不包括纳税人识别号）
	 * @param CORP_NAME,ORGAN_CODE,UNI_SC_ID,REG_NO
	 * @return
	 */
	public String getCorpRegInfoToHuanbaoju(String key, String keyword, String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker = ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser = checker.keyckeck(key, "getCorpRegInfoToHuanbaoju");
			if (serviceUser == null) {
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if (serviceUser.getEndDate().before(new Date())) {
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(CorpDao.class);
				Map<String, String> result = dao.getCorpRegInfoToHuanbaoju(keyword, serviceUser);
				return WsUitl.getResult(result, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 环保局 
	 * 按关键字查询法人资质信息
	 * @param CORP_NAME,ORGAN_CODE,UNI_SC_ID,REG_NO
	 * @return
	 */
	public String getCorpLicenseToHuanbaoju(String key, String licenseName, String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker = ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser = checker.keyckeck(key, "getCorpLicenseToHuanbaoju");
			if (serviceUser == null) {
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if (serviceUser.getEndDate().before(new Date())) {
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(CorpDao.class);
				Map<String, Object> result = dao.getCorpLicenseToHuanbaoju(licenseName, serviceUser);
				return WsUitl.getResult(result, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
