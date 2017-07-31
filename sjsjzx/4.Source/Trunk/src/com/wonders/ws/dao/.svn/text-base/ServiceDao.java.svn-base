package com.wonders.ws.dao;

import java.sql.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.api.entity.ApiLogWebService;
import com.wonders.ws.bean.HouseInfoBean;
import com.wonders.ws.bean.LogWebServiceBean;
import com.wonders.ws.bean.PersonInfoBean;
@IocBean
public class ServiceDao {

	@Inject
	private Dao	dao;
	
	
	public PersonInfoBean getPersonInfo(String zjhm) {
		ApiLogWebService aps=new ApiLogWebService();
		//调用时间
		aps.setCallDate(new Date(System.currentTimeMillis()));
		//调用方法名
		aps.setCallServiceName("getPersonInfo()");
		//调用用户
		aps.setUserLoginname("社治办");
		
		Criteria cri = Cnd.cri();
		cri.where().and("ZJHM", "=", zjhm);
		List<PersonInfoBean> personList = dao.query(PersonInfoBean.class, cri);
		if(personList!=null)
		{
			aps.setCallResult(personList.get(0).getXm());
		}else{
			aps.setCallResult("失败");
		}
		LogWebServiceBean bean = new LogWebServiceBean();
		bean.setUserId("shezhiban");
		bean.setCallDate(new Date(System.currentTimeMillis()));
		bean.setCallResult(1);
		bean.setCallMethod("getPersonInfo");
		dao.insert(bean);
		dao.insert(aps);

		return personList.get(0);
	}
	public List<PersonInfoBean> queryPersonListByJuWeiDM(String jwdm) {
		ApiLogWebService aps=new ApiLogWebService();
		//调用时间
		aps.setCallDate(new Date(System.currentTimeMillis()));
		//调用方法名
		aps.setCallServiceName("queryPersonListByJuWeiDM()");
		//调用用户
		aps.setUserLoginname("社治办");
		
		Criteria cri = Cnd.cri();
		cri.where().and("JZDJWHDM", "=", jwdm);
		List<PersonInfoBean> personList = dao.query(PersonInfoBean.class, cri);
		if(personList!=null)
		{
			int  io=personList.size();
			String str = String.valueOf(io);
			aps.setCallResult(str+"个");
		}
		else{
			aps.setCallResult("失败");
		}
		LogWebServiceBean bean = new LogWebServiceBean();
		bean.setUserId("shezhiban");
		bean.setCallDate(new Date(System.currentTimeMillis()));
		bean.setCallResult(personList.size());
		bean.setCallMethod("queryPersonListByJuWeiDM");
		dao.insert(bean);
		dao.insert(aps);

		return personList;
	}

	public List<HouseInfoBean> queryHouseListByJcwdm(String jcwdm) {
		Criteria cri = Cnd.cri();
		cri.where().and("JCWDM", "=", jcwdm);
		List<HouseInfoBean> houseList = dao.query(HouseInfoBean.class, cri);
		
		LogWebServiceBean bean = new LogWebServiceBean();
		bean.setUserId("shezhiban");
		bean.setCallDate(new Date(System.currentTimeMillis()));
		bean.setCallResult(houseList.size());
		bean.setCallMethod("queryHouseListByJcwdm");
		dao.insert(bean);
		
		return houseList; 
	}

}
