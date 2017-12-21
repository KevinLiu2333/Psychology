package com.wonders.ws.receive.task;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.rpc.ParameterMode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.log.entity.InterfaceReceiveLog;
import com.wonders.ws.receive.bean.CommunityAcceptanceAffairsApplyBean;
import com.wonders.ws.receive.bean.CommunityAcceptanceAffairsDefineBean;
import com.wondersgroup.cmc.utils.Base64Utils;

@IocBean
public class CommunityAcceptanceTask {

	@Inject
	private Dao	dao;

	public void extract() {
		// 获取民政局社区事务受理系统事项定义信息
		Map<String, Object> param1 = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, +1);
		String date1 = df.format(new Date());
		String date2 = df.format(cal.getTime());
		param1.put("startdate", date1);
		param1.put("enddate", date2);
		getAffairsDefine("getAffairsDefine", param1);

		// 获取民政局社区事务受理系统事项受理信息
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("startdate", date1);
		param2.put("enddate", date2);
		//param2.put("startdate", "20140101");
		//param2.put("enddate", "20161205");
		getAffairsApply("getAffairsApply", param2);

	}

	public void getAffairsDefine(String methodName, Map<String, Object> param) {
		String url = "http://10.208.18.10:8080/cmc_qxcics/services/PtDataService?wsdl";
		Service service = new Service();
		Call call;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(url));
			call.setOperation(methodName);
			call.addParameter("RedString", XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			JSONObject object = JSONObject.fromObject(param);
			Object[] params = new Object[] { Base64Utils.encodeString((object.toString()).getBytes()) };
			String str = (String) call.invoke(params);
			String result = new String(Base64Utils.decodeBytes(str), "utf-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray ja = jsonObject.getJSONArray("rtnList");
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	        InterfaceReceiveLog log = new InterfaceReceiveLog();
	        log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	        log.setInputDate(df.format(new Date()));
	        log.setInputNum(ja.size());
	        log.setInterfaceName("getAffairsDefine");
	        log.setServiceName("获取社区事务受理事项定义信息");
	        log.setReceiveName("社区事务受理系统");
	        log.setDeptName("民政局");
	        log.setDeptId("DT67");
	        log.setCallNum(1);
	        log.setTableName("PT_MZJ_SHEQU_GETAFFAIRSDEFINE");
	        Sql sql =Sqls.create("truncate table PT_MZJ_SHEQU_GETAFFAIRSDEFINE");
	        dao.execute(sql);
			for (int i = 0; i < ja.size(); i++) {
				JSONObject obj = ja.getJSONObject(i);
				CommunityAcceptanceAffairsDefineBean CAB = (CommunityAcceptanceAffairsDefineBean) JSONObject.toBean(obj, CommunityAcceptanceAffairsDefineBean.class);
				dao.insert(CAB);
			}
			log.setEndDate(df.format(new Date()));
	        dao.insert(log);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getAffairsApply(String methodName, Map<String, Object> param) {
		String url = "http://10.208.18.10:8080/cmc_qxcics/services/PtDataService?wsdl";
		Service service = new Service();
		Call call;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(url));
			call.setOperation(methodName);
			call.addParameter("RedString", XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			JSONObject object = JSONObject.fromObject(param);
			Object[] params = new Object[] { Base64Utils.encodeString((object.toString()).getBytes()) };
			String str = (String) call.invoke(params);
			String result = new String(Base64Utils.decodeBytes(str), "utf-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray ja = jsonObject.getJSONArray("rtnList");
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	        InterfaceReceiveLog log = new InterfaceReceiveLog();
	        log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	        log.setInputDate(df.format(new Date()));
	        log.setInputNum(ja.size());
	        log.setInterfaceName("getAffairsApply");
	        log.setServiceName("获取社区事务受理事项受理信息");
	        log.setReceiveName("社区事务受理系统");
	        log.setDeptName("民政局");
	        log.setDeptId("DT67");
	        log.setCallNum(1);
	        log.setTableName("PT_MZJ_SHEQU_GETAFFAIRSAPPLY");
			for (int i = 0; i < ja.size(); i++) {
				JSONObject obj = ja.getJSONObject(i);
				CommunityAcceptanceAffairsApplyBean CAB = new CommunityAcceptanceAffairsApplyBean();
				CAB.setAcceptorgan(obj.getString("acceptorgan"));
				CAB.setAcceptorganname(obj.getString("acceptorganname"));
				CAB.setAccepttime(obj.getString("accepttime"));
				CAB.setAffairscode(obj.getString("affairscode"));
				CAB.setAffairsname(obj.getString("affairsname"));
				CAB.setApplyno(obj.getString("applyno"));
				CAB.setEndtime(obj.getString("endtime"));
				CAB.setFullname(obj.getString("fullname"));
				CAB.setIdno(obj.getString("idno"));
				CAB.setStagestatus(obj.getString("stagestatus"));
				CAB.setStagestatusdesc(obj.getString("stagestatusdesc"));
				CAB.setStagestatusname(obj.getString("stagestatusname"));
				dao.insert(CAB);
			}
			log.setEndDate(df.format(new Date()));
	        dao.insert(log);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
