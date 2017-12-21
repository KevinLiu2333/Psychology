package com.wonders.ws.send.minzhengju;

import java.util.Date;
import java.util.Map;

import org.nutz.ioc.Ioc;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.WsUitl;
import com.wonders.ws.dao.KeyChecker;
import com.wonders.ws.dao.ServiceDao;


public class mzjPeopleService {
	Ioc			ioc	= null;
	ServiceDao	dao	= null;
	
	
	/**
	 * 根据身份证号和姓名查询街道名称，并返回身份证号与姓名是否匹配
	 * @param corp_name,ORGAN_CODE,UNI_SC_ID
	 * @return
	 */
	public String getJiedaotInfoByIdentification(String key,String name,String zjhm,String returntype) {
		try {
			if(ioc==null){
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getJiedaotInfoByIdentification");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if(dao==null){
				dao = ioc.get(ServiceDao.class);
				Map<String, String> result=dao.getJiedaotInfoByIdentification(name,zjhm,serviceUser);
				return WsUitl.getResult(result, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
