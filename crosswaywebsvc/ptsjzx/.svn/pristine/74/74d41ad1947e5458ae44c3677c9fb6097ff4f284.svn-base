package com.wonders.ws;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.Ioc;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.dao.KeyChecker;
import com.wonders.ws.dao.LyCorpDao;

/**
 * 提供给楼宇系统的webservice(得到全部法人数据，参看api楼宇sheet)
 * 
 * @author
 * 
 */
public class LyCropService {
	Ioc ioc = null;
	LyCorpDao dao = null;

	public String getCropByOrganCodely(String key,String organcode,String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCropByOrganCodely");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(LyCorpDao.class);
			}
			Map<String, Object> result=dao.getCropByOrganCode(organcode,serviceUser);
			return WsUitl.getResult(result, returntype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getCropByCorpNamely(String key,String corpname,String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getCropByCorpNamely");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(LyCorpDao.class);
			}
			List<Map<String, Object>> result=dao.getCropByCorpName(corpname,serviceUser);
			return WsUitl.ListResult(result, returntype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
