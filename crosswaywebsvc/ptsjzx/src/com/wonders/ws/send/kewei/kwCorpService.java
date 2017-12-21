package com.wonders.ws.send.kewei;

import java.util.Date;
import java.util.Map;

import org.nutz.ioc.Ioc;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.WsUitl;
import com.wonders.ws.dao.CorpDao;
import com.wonders.ws.dao.KeyChecker;

public class kwCorpService {
	Ioc		ioc	= null;
	CorpDao	dao	= null;

	/**
	 * 区科委普陀专利
	 * 按关键字查询登记信息
	 * @param CORP_NAME,ORGAN_CODE,UNI_SC_ID,REG_NO,TAXPAYERS_CODE
	 * @return
	 */
	public String getCorpInfoToKewei(String key, String keyword, String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker = ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser = checker.keyckeck(key, "getCorpInfoToKewei");
			if (serviceUser == null) {
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if (serviceUser.getEndDate().before(new Date())) {
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(CorpDao.class);
				Map<String, String> result = dao.getCorpInfoToKewei(keyword, serviceUser);
				return WsUitl.getResult(result, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}