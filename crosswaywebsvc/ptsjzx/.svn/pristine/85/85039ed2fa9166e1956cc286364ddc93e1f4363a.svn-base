package com.wonders.ws.receive.task;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.log.entity.InterfaceReceiveLog;
import com.wonders.ws.receive.bean.PtHbjHuanjingjianceDaqiBean;
import com.wonders.ws.receive.bean.PtHbjHuanjingjianceDibiaoBean;
import com.wonders.ws.receive.bean.PtHbjHuanjingjianceZaoshengBean;
import com.wonders.ws.receive.bean.PtHbjStationsInfoBean;
import com.wonders.ws.receive.getMethod.huanjingjiance.IHB_Common_ServiceProxy;

@IocBean
public class HbjHuanjingjianceTask {

	@Inject
	private Dao	dao;

	public void extract() {
		// 环保局环境检测站点信息
		getStationsInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String date = df.format(cal.getTime());
		// 环保局环境检测大气信息
		getDaqi(date);
		// 环保局环境检测噪声信息
		getZaosheng(date);
		// 环保局环境检测地表水信息
		getDibiao(date);
	}

	public void getStationsInfo() {
		IHB_Common_ServiceProxy service = new IHB_Common_ServiceProxy();
		String json = null;
		try {
			json = service.getStationsInfo("A537324D6E2043729BB8FB07F10BA8E8", "json");
			JSONArray ja = JSONArray.fromObject(json);
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(date.format(new Date()));
			log.setInputNum(ja.size());
			log.setInterfaceName("getStationsInfo");
			log.setServiceName("获取环境监测站点信息");
			log.setReceiveName("环保局环境监测信息");
			log.setDeptName("环保局");
			log.setDeptId("DT62");
			log.setCallNum(1);
			log.setTableName("PT_HBJ_HUANJINGJIANCE");
			Sql sql = Sqls.create("truncate table PT_HBJ_HUANJINGJIANCE");
			dao.execute(sql);
			for (int i = 0; i < ja.size(); i++) {
				JSONObject obj = ja.getJSONObject(i);
				PtHbjStationsInfoBean PtHbjStations = new PtHbjStationsInfoBean();
				PtHbjStations.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				PtHbjStations.setApplication(obj.getString("Application"));
				PtHbjStations.setStationid(obj.getString("StationID"));
				PtHbjStations.setStationname(obj.getString("StationName"));
				PtHbjStations.setStatus(obj.getString("Status"));
				PtHbjStations.setUpddatetime(obj.getString("UpdDateTime"));
				dao.insert(PtHbjStations);
			}
			log.setEndDate(date.format(new Date()));
			dao.insert(log);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void getDaqi(String date) {
		IHB_Common_ServiceProxy service = new IHB_Common_ServiceProxy();
		String json = null;
		try {
			json = service.getStationsInfo("A537324D6E2043729BB8FB07F10BA8E8", "json");
			JSONArray ja = JSONArray.fromObject(json);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(df.format(new Date()));
			log.setInputNum(ja.size());
			log.setInterfaceName("getMonitorInfo");
			log.setServiceName("获取环境监测大气信息");
			log.setReceiveName("环保局环境监测信息");
			log.setDeptName("环保局");
			log.setDeptId("DT62");
			log.setCallNum(1);
			log.setTableName("PT_HBJ_HUANJINGJIANCE_DAQI");
			for (int i = 0; i < ja.size(); i++) {
				JSONObject obj = ja.getJSONObject(i);
				String ID = obj.getString("StationID");
				/*
				 * for (int ks = 20160101; ks < 20161231; ks++) { String zj = ks + ""; String a = zj.substring(0, 4); String b = zj.substring(4, 6); String c = zj.substring(6, 8);
				 * String date = a + "-" + b + "-" + c;
				 */
				json = service.getMonitorInfo("1C94242AABDB4EEDA9169EF0156B72DC", "1", "0", ID, date, "json");
				if (json.length() != 0) {
					JSONArray ja2 = JSONArray.fromObject(json);
					for (int j = 0; j < ja2.size(); j++) {
						JSONObject obj2 = ja2.getJSONObject(j);
						PtHbjHuanjingjianceDaqiBean daqi = new PtHbjHuanjingjianceDaqiBean();
						daqi.setId(UUID.randomUUID().toString().replaceAll("-", ""));
						daqi.setCo(obj2.getString("CO"));
						daqi.setHumid(obj2.getString("HUMID"));
						daqi.setNo(obj2.getString("NO"));
						daqi.setNo2(obj2.getString("NO2"));
						daqi.setNox(obj2.getString("NOX"));
						daqi.setO3(obj2.getString("O3"));
						daqi.setPm10(obj2.getString("PM10"));
						daqi.setPm25(obj2.getString("PM25"));
						daqi.setPress(obj2.getString("PRESS"));
						daqi.setSo2(obj2.getString("SO2"));
						daqi.setStatime(obj2.getString("Sta_Time"));
						daqi.setStationid(obj2.getString("StationID"));
						daqi.setTemp(obj2.getString("TEMP"));
						daqi.setWd(obj2.getString("WD"));
						daqi.setWs(obj2.getString("WS"));
						dao.insert(daqi);
					}
				}
				/* } */
			}
			log.setEndDate(df.format(new Date()));
			dao.insert(log);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void getZaosheng(String date) {
		IHB_Common_ServiceProxy service = new IHB_Common_ServiceProxy();
		String json = null;
		try {
			json = service.getStationsInfo("A537324D6E2043729BB8FB07F10BA8E8", "json");
			JSONArray ja = JSONArray.fromObject(json);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(df.format(new Date()));
			log.setInputNum(ja.size());
			log.setInterfaceName("getMonitorInfo");
			log.setServiceName("获取环境监测噪声信息");
			log.setReceiveName("环保局环境监测信息");
			log.setDeptName("环保局");
			log.setDeptId("DT62");
			log.setCallNum(1);
			log.setTableName("PT_HBJ_HUANJINGJIANCE_ZAOSHENG");
			for (int i = 0; i < ja.size(); i++) {
				JSONObject obj = ja.getJSONObject(i);
				String ID = obj.getString("StationID");
				/*
				 * for (int ks = 20150101; ks < 20151231; ks++) { String zj = ks + ""; String a = zj.substring(0, 4); String b = zj.substring(4, 6); String c = zj.substring(6, 8);
				 * String date = a + "-" + b + "-" + c;
				 */
				try {
					json = service.getMonitorInfo("1C94242AABDB4EEDA9169EF0156B72DC", "2", "0", ID, date, "json");
					if (json.length() != 0) {
						JSONArray ja2 = JSONArray.fromObject(json);
						for (int k = 0; k < ja2.size(); k++) {
							JSONObject obj2 = ja2.getJSONObject(k);
							PtHbjHuanjingjianceZaoshengBean zaosheng = new PtHbjHuanjingjianceZaoshengBean();
							zaosheng.setId(UUID.randomUUID().toString().replaceAll("-", ""));
							zaosheng.setAleq(obj2.getString("ALEQ"));
							zaosheng.setL10(obj2.getString("L10"));
							zaosheng.setL50(obj2.getString("L50"));
							zaosheng.setL90(obj2.getString("L90"));
							zaosheng.setLmn(obj2.getString("LMn"));
							zaosheng.setLmx(obj2.getString("LMx"));
							zaosheng.setStatime(obj2.getString("Sta_Time"));
							zaosheng.setStationid(obj2.getString("StationID"));
							dao.insert(zaosheng);
						}
					}
				} catch (Exception e) {
				}

				// }
			}
			log.setEndDate(df.format(new Date()));
			dao.insert(log);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void getDibiao(String date) {
		IHB_Common_ServiceProxy service = new IHB_Common_ServiceProxy();
		String json = null;
		try {
			json = service.getStationsInfo("A537324D6E2043729BB8FB07F10BA8E8", "json");
			JSONArray ja = JSONArray.fromObject(json);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(df.format(new Date()));
			log.setInputNum(ja.size());
			log.setInterfaceName("getMonitorInfo");
			log.setServiceName("获取环境监测地表水信息");
			log.setReceiveName("环保局环境监测信息");
			log.setDeptName("环保局");
			log.setDeptId("DT62");
			log.setCallNum(1);
			log.setTableName("PT_HBJ_HUANJINGJIANCE_DIBIAO");
			for (int i = 0; i < ja.size(); i++) {
				JSONObject obj = ja.getJSONObject(i);
				String ID = obj.getString("StationID");
				/*
				 * for (int ks = 20150101; ks < 20151231; ks++) { String zj = ks + ""; String a = zj.substring(0, 4); String b = zj.substring(4, 6); String c = zj.substring(6, 8);
				 * String date = a + "-" + b + "-" + c;
				 */
				json = service.getMonitorInfo("1C94242AABDB4EEDA9169EF0156B72DC", "3", "0", ID, date, "json");
				if (json.length() != 0) {
					JSONArray ja2 = JSONArray.fromObject(json);
					for (int l = 0; l < ja2.size(); l++) {
						JSONObject obj2 = ja2.getJSONObject(l);
						PtHbjHuanjingjianceDibiaoBean dibiao = new PtHbjHuanjingjianceDibiaoBean();
						dibiao.setId(UUID.randomUUID().toString().replaceAll("-", ""));
						dibiao.setAmmon(obj2.getString("AMMON"));
						dibiao.setCod(obj2.getString("COD"));
						dibiao.setCondu(obj2.getString("CONDU"));
						dibiao.setDo1(obj2.getString("DO"));
						dibiao.setPh(obj2.getString("PH"));
						dibiao.setStatime(obj2.getString("Sta_Time"));
						dibiao.setStationid(obj2.getString("StationID"));
						dibiao.setTn(obj2.getString("TN"));
						dibiao.setTp(obj2.getString("TP"));
						dibiao.setTurbi(obj2.getString("TURBI"));
						dibiao.setWatert(obj2.getString("WATERT"));
						dao.insert(dibiao);
					}

					// }
				}
			}
			log.setEndDate(df.format(new Date()));
			dao.insert(log);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
