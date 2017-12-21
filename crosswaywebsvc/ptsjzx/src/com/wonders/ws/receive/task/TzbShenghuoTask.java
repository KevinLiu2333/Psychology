package com.wonders.ws.receive.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import sun.misc.BASE64Decoder;

import com.wonders.log.entity.InterfaceReceiveLog;
import com.wonders.ws.receive.bean.PtQfbShenghuoAreaBean;
import com.wonders.ws.receive.bean.PtQfbShenghuoDataBean;
import com.wonders.ws.receive.bean.PtQfbShenghuoTypeBean;
import com.wonders.ws.receive.getMethod.HttpClientUtil;

@IocBean
public class TzbShenghuoTask {

	@Inject
	private Dao	dao;

	public void extract() {

		// 地理位置信息
		getQfbShenghuoArea();
		// 类型信息
		getQfbShenghuoType();
		// 数据信息
		getQfbShenghuoData();

	}

	public void getQfbShenghuoArea() {
		try {
			String URL = "http://ptsh.shpt.gov.cn/webservice/maptypelist.ashx";
			Map<String, Object> params = new HashMap<String, Object>();
			String res = null;
			res = HttpClientUtil.get(URL, params);
			JSONObject result = new JSONObject(res);
			JSONObject obj = result.getJSONObject("Data");
			JSONArray area = obj.getJSONArray("Area");
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(date.format(new Date()));
			log.setInputNum(area.length());
			log.setInterfaceName("getQfbShenghuoArea");
			log.setServiceName("获取普陀指南地理位置信息");
			log.setReceiveName("普陀指南");
			log.setDeptName("投资办");
			log.setDeptId("DT50");
			log.setCallNum(1);
			log.setTableName("PT_QFB_SHENGHUO_AREA");
			Sql sql = Sqls.create("truncate table PT_QFB_SHENGHUO_AREA");
			dao.execute(sql);
			for (int i = 0; i < area.length(); i++) {
				PtQfbShenghuoAreaBean bean1 = new PtQfbShenghuoAreaBean();
				JSONObject obj1 = area.getJSONObject(i);
				bean1.setAreacode(obj1.getString("AreaCode"));
				bean1.setAreaname(getFromBase64(obj1.getString("AreaName")));
				bean1.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				dao.insert(bean1);
			}
			log.setEndDate(date.format(new Date()));
			dao.insert(log);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public void getQfbShenghuoType() {
		try {
			String URL = "http://ptsh.shpt.gov.cn/webservice/maptypelist.ashx";
			Map<String, Object> params = new HashMap<String, Object>();
			String res = null;
			res = HttpClientUtil.get(URL, params);
			JSONObject result = new JSONObject(res);
			JSONObject obj = result.getJSONObject("Data");
			JSONArray type = obj.getJSONArray("Type");
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(date.format(new Date()));
			log.setInterfaceName("getQfbShenghuoType");
			log.setServiceName("获取普陀指南类型信息");
			log.setReceiveName("普陀指南");
			log.setDeptName("投资办");
			log.setDeptId("DT50");
			log.setCallNum(1);
			log.setTableName("PT_QFB_SHENGHUO_TYPE");
			Sql sql = Sqls.create("truncate table PT_QFB_SHENGHUO_TYPE");
			dao.execute(sql);
			int sum = 0;
			for (int i = 0; i < type.length(); i++) {
				JSONObject obj2 = type.getJSONObject(i);
				for (Iterator<String> iterator = obj2.keys(); iterator.hasNext();) {
					String zhonglei = iterator.next();
					JSONObject obj21 = obj2.getJSONObject(zhonglei);
					String code1 = obj21.getString("TypeCode");
					String level1 = obj21.getString("typelevel");
					JSONArray array21 = obj21.getJSONArray("TypeList");
					for (int j = 0; j < array21.length(); j++) {
						PtQfbShenghuoTypeBean bean2 = new PtQfbShenghuoTypeBean();
						JSONObject obj211 = array21.getJSONObject(j);
						bean2.setId(UUID.randomUUID().toString().replaceAll("-", ""));
						bean2.setTypecode(code1);
						bean2.setTypecode2(obj211.getString("TypeCode"));
						bean2.setTypelevel(level1);
						bean2.setTypelevel2(obj211.getString("typelevel"));
						bean2.setTypename(getFromBase64(zhonglei));
						bean2.setTypename2(getFromBase64(obj211.getString("TypeName")));
						dao.insert(bean2);
						sum++;
					}
				}
			}
			log.setInputNum(sum);
			log.setEndDate(date.format(new Date()));
			dao.insert(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getQfbShenghuoData() {
		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(date.format(new Date()));
			log.setInterfaceName("getQfbShenghuoData");
			log.setServiceName("获取普陀指南数据信息");
			log.setReceiveName("普陀指南");
			log.setDeptName("投资办");
			log.setDeptId("DT50");
			log.setCallNum(1);
			log.setTableName("PT_QFB_SHENGHUO_DATA");
			Sql sql = Sqls.create("truncate table PT_QFB_SHENGHUO_DATA");
			dao.execute(sql);
			int sum = 0;
			List<PtQfbShenghuoTypeBean> list = dao.query(PtQfbShenghuoTypeBean.class, Cnd.where("TYPELEVEL2", "=", "2"));
			for (int i = 0; i < list.size(); i++) {
				String typeId = list.get(i).getTypecode2();
				int j = 1;
				while (true) {
					String URL = "http://ptsh.shpt.gov.cn/webservice/mapdatalist.ashx?typeId=" + typeId + "&curPage=" + j + "&page=50";
					Map<String, Object> params = new HashMap<String, Object>();
					String res = null;
					res = HttpClientUtil.get(URL, params);
					JSONObject result = new JSONObject(res);
					if (result.getString("Msg").equals("获取成功")) {
						JSONObject obj = result.getJSONObject("Data");
						JSONArray array = obj.getJSONArray("List");
						for (int k = 0; k < array.length(); k++) {
							PtQfbShenghuoDataBean bean3 = new PtQfbShenghuoDataBean();
							JSONObject obj31 = array.getJSONObject(k);
							JSONArray array2 = obj31.getJSONArray("PhoneList");
							StringBuffer str = new StringBuffer();
							for (int l = 0; l < array2.length(); l++) {
								JSONObject obj311 = array2.getJSONObject(l);
								str.append(getFromBase64(obj311.getString("Phone")) + ",");
							}
							bean3.setAddress(getFromBase64(obj31.getString("Address")));
							bean3.setId(UUID.randomUUID().toString().replaceAll("-", ""));
							bean3.setImage(getFromBase64(obj31.getString("Image")));
							bean3.setName(getFromBase64(obj31.getString("Name")));
							bean3.setPhonelist(str.substring(0, str.length() - 1));
							bean3.setX(obj31.getString("X"));
							bean3.setY(obj31.getString("Y"));
							bean3.setTypecode(list.get(i).getTypecode());
							bean3.setTypecode2(typeId);
							bean3.setTypelevel(list.get(i).getTypelevel());
							bean3.setTypelevel2(list.get(i).getTypelevel2());
							bean3.setTypename(list.get(i).getTypename());
							bean3.setTypename2(list.get(i).getTypename2());
							dao.insert(bean3);
							sum++;
						}
						j++;
					} else {
						break;
					}
				}
			}
			log.setInputNum(sum);
			log.setEndDate(date.format(new Date()));
			dao.insert(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Base64解码
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
