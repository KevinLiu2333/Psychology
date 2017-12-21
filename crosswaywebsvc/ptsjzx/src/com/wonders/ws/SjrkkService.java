package com.wonders.ws;

import java.util.Date;
import java.util.List;

import org.nutz.ioc.Ioc;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.bean.HouseInfoBean;
import com.wonders.ws.dao.KeyChecker;
import com.wonders.ws.dao.ServiceDao;

public class SjrkkService {
	Ioc			ioc	= null;
	ServiceDao	dao	= null;

	/**
	 * 使用身份证号得到人详细信息
	 * 
	 * @param zjhm
	 * @return
	 */
	/*public String getPersonInfo(String key,String zjhm,String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "getPersonInfo");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(ServiceDao.class);
				PersonInfoBean pi = (PersonInfoBean) dao.getPersonInfo(zjhm,serviceUser);
				return WsUitl.getResult(pi, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}*/

	/**
	 * 使用居委代码得到批量用户
	 * 
	 * @param jwdm
	 * @return
	 */
	/*public String queryPersonsByJuWeiDM(String key,String jwdm,String returntype) {
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "queryPersonsByJuWeiDM");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null) {
				dao = ioc.get(ServiceDao.class);
				List<PersonInfoBean> personList = dao.queryPersonListByJuWeiDM(jwdm,serviceUser);
				return WsUitl.ListResult(personList, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	/**
	 * 根据居村委代码查询所属范围的部分房屋信息.
	 * @param jcwdm
	 * @return
	 */
	public String queryHousesByJcwdm(String key,String jcwdm,String returntype){
		try {
			if (ioc == null) {
				ioc = IocSingle.getInstance();
			}
			KeyChecker checker=ioc.get(KeyChecker.class);
			ApiServiceUser serviceUser=checker.keyckeck(key, "queryHousesByJcwdm");
			if(serviceUser==null){
				return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
			}
			if(serviceUser.getEndDate().before(new Date())){
				return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
			}
			if (dao == null){
				dao = ioc.get(ServiceDao.class);
				List<HouseInfoBean> houseList = dao.queryHouseListByJcwdm(jcwdm,serviceUser);
				return WsUitl.ListResult(houseList, returntype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}