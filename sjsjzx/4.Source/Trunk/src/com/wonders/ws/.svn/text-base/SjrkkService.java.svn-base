package com.wonders.ws;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

import com.wonders.ws.bean.HouseInfoBean;
import com.wonders.ws.bean.PersonInfoBean;
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
	public String getPersonInfo(String zjhm) {
		try {
			if (ioc == null) {
				ioc = new NutIoc(new ComboIocLoader("*org.nutz.ioc.loader.json.JsonLoader", "ioc/", "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.wonders"));
			}
			if (dao == null) {
				dao = ioc.get(ServiceDao.class);
				PersonInfoBean pi = (PersonInfoBean) dao.getPersonInfo(zjhm);
				JSONObject json = new JSONObject(pi);
				return json.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 使用居委代码得到批量用户
	 * 
	 * @param jwdm
	 * @return
	 */
	public String queryPersonsByJuWeiDM(String jwdm) {
		try {
			if (ioc == null) {
				ioc = new NutIoc(new ComboIocLoader("*org.nutz.ioc.loader.json.JsonLoader", "ioc/", "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.wonders"));
			}
			if (dao == null) {
				dao = ioc.get(ServiceDao.class);
				List<PersonInfoBean> personList = dao.queryPersonListByJuWeiDM(jwdm);
				JSONArray json = new JSONArray(personList);
				return json.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据居村委代码查询所属范围的部分房屋信息.
	 * @param jcwdm
	 * @return
	 */
	public String queryHousesByJcwdm(String jcwdm){
		try {
			if (ioc == null) {
				ioc = new NutIoc(new ComboIocLoader("*org.nutz.ioc.loader.json.JsonLoader", "ioc/", "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.wonders"));
			}
			if (dao == null){
				dao = ioc.get(ServiceDao.class);
				List<HouseInfoBean> houseList = dao.queryHouseListByJcwdm(jcwdm);
				JSONArray json = new JSONArray(houseList);
				return json.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}