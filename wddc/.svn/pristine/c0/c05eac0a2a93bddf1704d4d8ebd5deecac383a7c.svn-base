package com.wonders.sjfw.platform.alert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.lang.Strings;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;

import com.wonders.sjfw.entity.FwInfo;
import com.wonders.sjfw.entity.LogFw;
import com.wonders.wddc.tiles.tools.DateUtils;

public class AlertManager {

	private static Map<String, AlertInfo> alertMap = new HashMap<String, AlertInfo>();
	
	private static AlertManager instance = null;
	
	

	private AlertManager() {

	}
	
	public static AlertManager getInstance() {
		if(instance == null){
			instance = new AlertManager();
		}
		return instance;
	}
	
	/**
	 * 初始化所有公开切服务正常的服务预警信息.
	 */
	public void init(){
		
		Dao dao = Mvcs.ctx().getDefaultIoc().get(Dao.class, "dao");
		List<FwInfo> fwLlist = dao.query(FwInfo.class, Cnd.where("openType", "!=", "不公开").and("status", "=", "正常"));
		for(FwInfo fwInfo : fwLlist){
			this.initSingleFw(fwInfo.getFwCode());
		}
		Logs.get().info("服务预警信息初始化完毕！");
	}
	
	/**
	 * 初始化单个的服务应信息 .
	 * @param fwCode 服务代码
	 */
	public void initSingleFw(String fwCode){
		
		Dao dao = Mvcs.ctx().getDefaultIoc().get(Dao.class, "dao");
		String curDateString = DateUtils.date2String(new Date(), DateUtils.FORMAT_DATE);
		Date startDate = DateUtils.string2Date(curDateString+" 00:00:00", DateUtils.FORMAT_DATETIME);
		Date endtDate = DateUtils.string2Date(curDateString+" 23:59:59", DateUtils.FORMAT_DATETIME);
		
        Criteria cri = Cnd.cri();
        cri.where().and("endTime", ">=", startDate);
        cri.where().and("endTime", "<=", endtDate);
        if(Strings.isNotBlank(fwCode)){
            cri.where().and("fwCode", "=", fwCode);
        }
		int usedCount = dao.count(LogFw.class, cri);
		AlertInfo alertInfo= new AlertInfo();
		alertInfo.dayUsedCount = usedCount;
		alertMap.put(fwCode, alertInfo);
	}
	
	/**
	 * 根据服务编号获取预警信息.
	 * @param fwCode
	 * @return
	 */
	public AlertInfo getAlertInfo(String fwCode){
		if(!alertMap.containsKey(fwCode)){
			this.initSingleFw(fwCode);
		}
		return  alertMap.get(fwCode);
	}

	/**
	 * 根据预警级别获取调用总量的预警值.
	 * @param alertLevel
	 * @return
	 */
	public long getDayUsedCountData(String alertLevel){
		if("一级".equals(alertLevel)){
			return  2000;
		}else if("二级".equals(alertLevel)){
			return  1000;
		}else{
			return  500;
		}
	}
	


	/**
	 * 根据预警级别获取数据总量的预警值.
	 * @param alertLevel
	 * @return
	 */
	public long getDayDataCountData(String alertLevel){
		if("一级".equals(alertLevel)){
			return  20000;
		}else if("二级".equals(alertLevel)){
			return  10000;
		}else{
			return  5000;
		}
	}


}
