package com.wonders.echarts.task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;

import com.wonders.echarts.entity.DicFrType;
import com.wonders.echarts.entity.EchartsData;
import com.wonders.query.entity.CorpGQ;
import com.wonders.query.entity.CorpLHRH;
import com.wonders.query.entity.CorpXJR;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.zymlgl.entity.Resource;

@At("/test")
@IocBean
public class EchartsTask {

	@Inject
	private Dao dao;
	@Inject
	private Dao dao1;

	/**
	 * 统计
	 */
	public void count() {
		
		countpeopleBase();
		countpeopleBswq();
		countpeopleJZjz();
		countpeopleJwry();
		countpeopleByAge();
		countpeopleByAgeBq();
		countpeopleByAgeBql();
		countpeopleByAgeWq();
		countpeopleByAgeWql();
		countpeopleByAgeLh();
		countpeopleByAgeLhl();
		countpeopleByAgeJw();
		countpeopleByAgeJwl();
		countpeopleHj();
		countpeopleByWhcd();
		countpeoplerhfl1();
		countpeoplerhfl2();
		countpeoplerhfl3();
		countpeopleRhfl4();
		countpeopleJZhj();
		countCorpInfo();
		countCorpSum();
		fundDispersed();
		countFRCl();
		countFRBg();
		countFRZx();
		countFRSDCY();
		countFRLX();
		countFRghyzszb();
		countFRzjfb();
		countFRjdcf();
		countPeopleBar();
		countPeopleNlfb();
		countPeopleBqhjryqs();
		countPeopleBqlhryqs();
		countpeopleRhfl3();
		countpeopleRhfl1();
		countpeopleRhfl2();
		countpeoplbtj();
		countpeoplePublic();
		countFRBar();
		sjjh();
		countLZF();
		zyTj();
		countpeopleBsbq();
		countpeopleLhry();
		countpeopleRhfl1();
		countpeopleRhfl2();
	}
	
	/**
	 * 统计各廉租房
	 */
	public void countLZF() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		// 动迁房
		Sql sql = Sqls.create("select count(1) from PROJECT_DONG_QIAN ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("dqf",sql.getResult());
		// 保障房
		sql = Sqls.create("select count(1) from PROJECT_NG_YOU_CHAN_QUAN ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("bzf",sql.getResult());
		//公租房
		sql = Sqls.create("select count(1) from PROJECT_NG_ZU ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("gzf",sql.getResult());
		//旧城改造
		sql = Sqls.create("select count(1) from PROJECT_JIU_GAI_FANG ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("jcgz",sql.getResult());
		//廉租房
		sql = Sqls.create("select count(1) from PROJECT_LIAN_ZU ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("lzf",sql.getResult());
		
		
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("70");
		data.setThemeName("廉租房统计");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	

	
	

	/**
	 * 统计各街镇居住人口
	 */
	public void countpeopleJZjz() {
		Map<String, String> jiezhen = DicDataUtils.getInstance().getDic(1077);
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (String key : jiezhen.keySet()) {
			Sql sql = Sqls
					.create("select sum(c) from(select count(1) c from t_ga_rjbxx where JZDJDDM ='"
							+ key
							+ "' union select count(1) c from t_ga_rjbxx where hjdjddm = '"
							+ key + "' and JZDJDDM is null)");
			sql.setCallback(new getOneStringCallBack());
			dao1.execute(sql);
			result.put(jiezhen.get(key), (String) sql.getResult());
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("1");
		data.setThemeName("各街镇自然人居住人口统计");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	


	/**
	 * 统计人口基本
	 */
	public void countpeopleBase() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		// 人口 总数
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		String peoplesum = (String) sql.getResult();
		result.put("rkzs", peoplesum);
		// 当月人口变化

		// 本月最新
		sql = Sqls.create("select * from (select OUTPUTNUM from T_ETLLOG where STATUS ='S' and JOBID='T_GA_RJBXX_PT' order by end_time desc ) where rownum<=1");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		String outputnum1 = (String) sql.getResult();
		// 上月最新
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		sql = Sqls
				.create("select * from (select OUTPUTNUM from T_ETLLOG where STATUS ='S' and JOBID='T_GA_RJBXX_PT' and end_time<'"
						+ new SimpleDateFormat("yyyyMMddHHmmss")
								.format(calendar.getTime())
						+ "' order by end_time desc ) where rownum<=1");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		String outputnum2 = (String) sql.getResult();
		if (outputnum1 == null || outputnum2 == null) {
			result.put("rkbh", "0");
		} else {
			result.put(
					"rkbh",
					""
							+ (Integer.parseInt(outputnum1) - Integer
									.parseInt(outputnum2)));
		}
		// 户籍人口占比 百分比
		sql = Sqls
				.create("select count(1) from t_ga_rjbxx where SYRKLBDM='01' and hjdxzqhdm = '310117'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		String hjrk = (String) sql.getResult();
		result.put("hjrkzb",
				Integer.parseInt(hjrk) * 100 / Integer.parseInt(peoplesum) + "");
		// 60岁以上老人占比 百分比
		String now = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String lssysrq = "" + (Long.parseLong(now) - 600000);
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq<'"
				+ lssysrq + "' and hjdxzqhdm = '310117' and SYRKLBDM='01' ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		String lssyslr = (String) sql.getResult();
		sql = Sqls.create("select count(1) from t_ga_rjbxx where hjdxzqhdm = '310117' and SYRKLBDM='01' ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		String bqhj = (String) sql.getResult();
		result.put("lssyslrzb",
				Integer.parseInt(lssyslr) * 100 / Integer.parseInt(bqhj)
						+ "");
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("2");
		data.setThemeName("自然人基本情况");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}

	/**
	 * 统计各年龄段
	 */
	public void countpeopleByAge() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String nowMonthDay = new SimpleDateFormat("MMdd").format(new Date());
		int nowYear = Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date()));
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 7) + nowMonthDay + "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("0~6岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 19) + nowMonthDay + "' and csrq<='" + (nowYear - 7)
				+ nowMonthDay + "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("7~18岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 36) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 19) + nowMonthDay + "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("19~35岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 60) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 36) + nowMonthDay + "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("36~59岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 70) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 60) + nowMonthDay + "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("60~69岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 80) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 70) + nowMonthDay + "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("70~79岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 90) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 80) + nowMonthDay + "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("80~89岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 100) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 90) + nowMonthDay + "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("90~99岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq <= '"
				+ (nowYear - 100) + nowMonthDay + "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("100岁及以上", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("3");
		data.setThemeName("各年龄段自然人统计");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}

	
	
	/**
	 * 实有人口类别分析
	 */
	public void countpeopleHj() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("本区户籍", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where hjdxzqhdm != '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("外区户籍", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where syrklbdm = '02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("来沪人员", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where syrklbdm = '03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("境外人员", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("4");
		data.setThemeName("实有人口类别分析");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}

	

	

	

	

	/**
	 * 统计文化程度
	 */
	public void countpeopleByWhcd() {
		String bsdm = "('02','05')";
		String ssdm = "('03','10','11')";
		String bkdm = "('04','19','20','21','28','95')";
		String zkdm = "('30','31','38','50','51')";
		String gzdm = "('29','39','59','40','41','42','48','60','61','62','63','68','94','97')";
		String czdm = "('49','69','70','71','72','73','78','79','80','81','88','89','90','92','93','99')";
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Sql sql = Sqls
				.create("select count(1) from t_ga_rjbxx where WHCDDM in "
						+ bsdm);
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("博士", sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where WHCDDM in "
				+ ssdm);
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("硕士", sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where WHCDDM in "
				+ bkdm);
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("本科", sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where WHCDDM in "
				+ zkdm);
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("专科", sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where WHCDDM in "
				+ gzdm);
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("高中", sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where WHCDDM in "
				+ czdm);
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("初中及以下", sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("5");
		data.setThemeName("文化程度分析");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	

	/**
	 * 统计各街镇户籍人口
	 */
	/**
	 * 统计各街镇居住人口
	 */
	public void countpeopleJZhj() {
		Map<String, String> jiezhen = DicDataUtils.getInstance().getDic(1077);
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (String key : jiezhen.keySet()) {
			Sql sql = Sqls
					.create("select sum(c) from(select count(1) c from t_ga_rjbxx where HJDJDDM ='"
							+ key
							+ "'  union select count(1) c from t_ga_rjbxx where JZDJDDM = '"
							+ key + "' and HJDJDDM is null)");
			sql.setCallback(new getOneStringCallBack());
			dao1.execute(sql);
			result.put(jiezhen.get(key), (String) sql.getResult());
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("7");
		data.setThemeName("各街镇自然人户籍人口统计");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 法人类型
	 */
	public void countFRLX() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		Sql sql = Sqls.create("select count(1) from corp_info where CORP_TYPE in(00010100) and CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("公司", (String) sql.getResult());
		sql = Sqls.create("select count(1) from corp_info where CORP_TYPE in(00010200) and CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("分公司", (String) sql.getResult());
		sql = Sqls.create("select count(1) from corp_info where CORP_TYPE in(00010800) and CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("个人独资企业", (String) sql.getResult());
		sql = Sqls.create("select count(1) from corp_info where CORP_TYPE in(00010000,00010300,00010400,00010500,00010600,00010700,00010900) and CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("其他企业", (String) sql.getResult());
		sql = Sqls.create("select count(1) from corp_info where CORP_TYPE in(00040100) and CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("个体工商户", (String) sql.getResult());
		sql = Sqls.create("select count(1) from corp_info where "
				+ "CORP_TYPE in(00020000,00030000,00030100,00030200,00030300,00040000,00040200,00040300,00040400,00050000,00040500,00040600,00040800,00040900) and CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("其他法人", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("8");
		data.setThemeName("法人类型");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	


	/**
	 * 法人类型统计
	 * */
	public void countCorpInfo() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		Criteria cri = Cnd.cri();
		List<DicFrType> contents = dao.query(DicFrType.class, cri);
		EchartsData data = new EchartsData();
		Sql sql;
		for (int i = 0; i < contents.size(); i++) {
			if (contents.get(i).getId() != null
					|| contents.get(i).getId().isEmpty()) {
				if (contents.get(i).getId().substring(0, 5).equals("00010")) {
					sql = Sqls
							.create("select count(1) from corp_info where corp_type like '%"
									+ contents.get(i).getId().substring(0, 5)
									+ "%'");
					sql.setCallback(new getOneStringCallBack());
					dao.execute(sql);
					result.put("企业类型", (String) sql.getResult());
				}
				if (contents.get(i).getId().substring(0, 5).equals("00020")) {
					sql = Sqls
							.create("select count(1) from corp_info where corp_type like '%"
									+ contents.get(i).getId().substring(0, 5)
									+ "%'");
					sql.setCallback(new getOneStringCallBack());
					dao.execute(sql);
					result.put("事业单位", (String) sql.getResult());
				}
				if (contents.get(i).getId().substring(0, 5).equals("00030")) {
					sql = Sqls
							.create("select count(1) from corp_info where corp_type like '%"
									+ contents.get(i).getId().substring(0, 5)
									+ "%'");
					sql.setCallback(new getOneStringCallBack());
					dao.execute(sql);
					result.put("社会组织", (String) sql.getResult());
				}
				if (contents.get(i).getId().substring(0, 5).equals("00040")) {
					sql = Sqls
							.create("select count(1) from corp_info where corp_type like '%"
									+ contents.get(i).getId().substring(0, 5)
									+ "%'");
					sql.setCallback(new getOneStringCallBack());
					dao.execute(sql);
					result.put("其它", (String) sql.getResult());
				}
			}
		}
		data.setCreateDate(new Date());
		data.setThemeId("10");
		data.setThemeName("法人类型");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 法人类型总数
	 * */
	public void countCorpSum() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		EchartsData data = new EchartsData();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Sql sql = Sqls.create("select count(1) from corp_info");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("sq", (String) sql.getResult());

		sql = Sqls
				.create("select  count(1) from corp_info c where to_char(c.change_date,'yyyymm')>="
						+ sdf.format(new Date())
						+ " and to_char(c.change_date,'yyyymm')<"
						+ sdf.format(new Date()) + 1);
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("BG", (String) sql.getResult());

		sql = Sqls
				.create("select  count(1) from corp_info c where to_char(c.repeal_date,'yyyymm')>="
						+ sdf.format(new Date())
						+ " and to_char(c.repeal_date,'yyyymm')<"
						+ sdf.format(new Date()) + 1);
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("ZX", (String) sql.getResult());

		data.setCreateDate(new Date());
		data.setThemeId("12");
		data.setThemeName("法人总数");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/***
	 * 资金分布
	 * ***/
	public void fundDispersed() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String middletableString = "select case  when ltrim(rtrim(c.CURRENCY)) = '人民币' or CURRENCY is null or ltrim(rtrim(c.CURRENCY)) = 'NULL' then c.REG_CAPITAL"
				+ " else REG_CAPITAL * (select RATE  from DIC_RATE d where d.CURRENCY = ltrim(rtrim(c.CURRENCY)) ) "
				+ "end REG_CAPITAL from corp_info c where CORP_STATUS='0001' and CORP_INFO_ID not in (SELECT CORP_INFO_ID FROM CORP_INFO WHERE AREA_CODE NOT IN ('310117','松江') AND ADDRESS NOT LIKE '%松江%' AND BUSINESS_ADDRESS NOT LIKE '%松江%' AND TAXPAYERS_CODE NOT LIKE '310117%' AND REG_NO NOT LIKE '310117%' AND CORP_STATUS = '0001')";
		EchartsData data = new EchartsData();
		Sql sql = Sqls
				.create("select count(1) from ("
						+ middletableString
						+ ") c where C.REG_CAPITAL is null or (C.REG_CAPITAL>0 and C.REG_CAPITAL<=1)");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("0~1", (String) sql.getResult());

		sql = Sqls.create("select count(1) from (" + middletableString
				+ ") c where C.REG_CAPITAL>1 and C.REG_CAPITAL<=2");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("1~2", (String) sql.getResult());

		sql = Sqls.create("select count(1) from (" + middletableString
				+ ") c where C.REG_CAPITAL>2 and C.REG_CAPITAL<=5");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("2~5", (String) sql.getResult());

		sql = Sqls.create("select count(1) from (" + middletableString
				+ ") c where C.REG_CAPITAL>5 and C.REG_CAPITAL<=10");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("5~10", (String) sql.getResult());

		sql = Sqls.create("select count(1) from (" + middletableString
				+ ") c where C.REG_CAPITAL>10 and C.REG_CAPITAL<=50");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("10~50", (String) sql.getResult());

		sql = Sqls.create("select count(1) from (" + middletableString
				+ ") c where C.REG_CAPITAL>50 and C.REG_CAPITAL<=100");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("50~100", (String) sql.getResult());

		sql = Sqls.create("select count(1) from (" + middletableString
				+ ") c where C.REG_CAPITAL>100 and C.REG_CAPITAL<=300");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("100~300", (String) sql.getResult());

		sql = Sqls.create("select count(1) from (" + middletableString
				+ ") c where C.REG_CAPITAL>300 and C.REG_CAPITAL<=500");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("300~500", (String) sql.getResult());

		sql = Sqls.create("select count(1) from (" + middletableString
				+ ") c where C.REG_CAPITAL>500 and C.REG_CAPITAL<=1000");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("500~1000", (String) sql.getResult());

		sql = Sqls.create("select count(1) from (" + middletableString
				+ ") c where C.REG_CAPITAL>1000 and C.REG_CAPITAL<=2000");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("1000~2000", (String) sql.getResult());

		sql = Sqls.create("select count(1) from (" + middletableString
				+ ") c where C.REG_CAPITAL>2000");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("2000以上", (String) sql.getResult());

		data.setCreateDate(new Date());
		data.setThemeId("13");
		data.setThemeName("注册资金分布");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}

	
	

	// 统计三大产业
	public void countFRSDCY() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Sql sql = Sqls
				.create("select count(1) from CORP_INFO where INDUSTRY_CODE like 'A%' and CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("第一产业", sql.getResult());
		sql = Sqls
				.create("select count(1) from CORP_INFO where (INDUSTRY_CODE like 'B%'"
						+ " or INDUSTRY_CODE like 'C%' or INDUSTRY_CODE like 'D%' or INDUSTRY_CODE like 'E%') and CORP_STATUS = '0001' ");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("第二产业", sql.getResult());
		sql = Sqls
				.create("select count(1) from CORP_INFO where (INDUSTRY_CODE like 'F%' or INDUSTRY_CODE like 'G%' "
						+ "or INDUSTRY_CODE like 'H%' or INDUSTRY_CODE like 'I%' or INDUSTRY_CODE like 'J%' or INDUSTRY_CODE like 'K%' "
						+ "or INDUSTRY_CODE like 'L%' or INDUSTRY_CODE like 'M%' or INDUSTRY_CODE like 'N%' or INDUSTRY_CODE like 'O%' "
						+ "or INDUSTRY_CODE like 'P%' or INDUSTRY_CODE like 'Q%' or INDUSTRY_CODE like 'R%' or INDUSTRY_CODE like 'S%' "
						+ "or INDUSTRY_CODE like 'T%') and CORP_STATUS = '0001' ");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("第三产业", sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("14");
		data.setThemeName("三大产业");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/**
	 * 统计各街本市本区户籍人口
	 */
	public void countpeopleBsbq() {
		Map<String, String> jiezhen = DicDataUtils.getInstance().getDic(1077);
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (String key : jiezhen.keySet()) {
			Sql sql = Sqls
					.create("select sum(c) from(select count(1) c from t_ga_rjbxx where JZDJDDM ='"
							+ key
							+ "' and hjdxzqhdm = '310117' and syrklbdm = '01'"
							+ " union select count(1) c from t_ga_rjbxx where hjdjddm = '"
							+ key + "' and JZDJDDM is null and hjdxzqhdm = '310117' and syrklbdm = '01')");
			sql.setCallback(new getOneStringCallBack());
			dao1.execute(sql);
			result.put(jiezhen.get(key), (String) sql.getResult());
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("15");
		data.setThemeName("各街镇本市本区户籍人口");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/**
	 * 统计各街本市外区户籍人口
	 */
	public void countpeopleBswq() {
		Map<String, String> jiezhen = DicDataUtils.getInstance().getDic(1077);
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (String key : jiezhen.keySet()) {
			Sql sql = Sqls
					.create("select sum(c) from(select count(1) c from t_ga_rjbxx where JZDJDDM ='"
							+ key
							+ "' and hjdxzqhdm != '310117' and syrklbdm = '01'"
							+ " union select count(1) c from t_ga_rjbxx where hjdjddm = '"
							+ key + "' and JZDJDDM is null and hjdxzqhdm != '310117' and syrklbdm = '01')");
			sql.setCallback(new getOneStringCallBack());
			dao1.execute(sql);
			result.put(jiezhen.get(key), (String) sql.getResult());
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("16");
		data.setThemeName("各街镇本市外区户籍人口");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/**
	 * 统计各街来沪人员人口
	 */
	public void countpeopleLhry() {
		Map<String, String> jiezhen = DicDataUtils.getInstance().getDic(1077);
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (String key : jiezhen.keySet()) {
			Sql sql = Sqls
					.create("select sum(c) from(select count(1) c from t_ga_rjbxx where JZDJDDM ='"
							+ key
							+ "'  and syrklbdm = '02'"
							+ " union select count(1) c from t_ga_rjbxx where hjdjddm = '"
							+ key + "' and JZDJDDM is null  and syrklbdm = '02')");
			sql.setCallback(new getOneStringCallBack());
			dao1.execute(sql);
			result.put(jiezhen.get(key), (String) sql.getResult());
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("17");
		data.setThemeName("各街镇来沪人员人口");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/**
	 * 统计各街境外人员人口
	 */
	public void countpeopleJwry() {
		Map<String, String> jiezhen = DicDataUtils.getInstance().getDic(1077);
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (String key : jiezhen.keySet()) {
			Sql sql = Sqls
					.create("select sum(c) from(select count(1) c from t_ga_rjbxx where JZDJDDM ='"
							+ key
							+ "'  and syrklbdm = '03'"
							+ " union select count(1) c from t_ga_rjbxx where hjdjddm = '"
							+ key + "' and JZDJDDM is null  and syrklbdm = '03')");
			sql.setCallback(new getOneStringCallBack());
			dao1.execute(sql);
			result.put(jiezhen.get(key), (String) sql.getResult());
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("18");
		data.setThemeName("各街镇境外人员人口");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 统计各年龄段（本区户籍）
	 */
	public void countpeopleByAgeBq() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String nowMonthDay = new SimpleDateFormat("MMdd").format(new Date());
		int nowYear = Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date()));
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 7) + nowMonthDay + "' and hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("0~6岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 19) + nowMonthDay + "' and csrq<='" + (nowYear - 7)
				+ nowMonthDay + "' and hjdxzqhdm = '310117' and syrklbdm = '01' ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("7~18岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 36) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 19) + nowMonthDay + "' and hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("19~35岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 60) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 36) + nowMonthDay + "' and hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("36~59岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq <= '"
				+ (nowYear - 60) + nowMonthDay + "' and hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("60岁及以上", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("19");
		data.setThemeName("各年龄段自然人统计（本区）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	
	/**
	 * 统计各年龄段（外区户籍）
	 */
	public void countpeopleByAgeWq() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String nowMonthDay = new SimpleDateFormat("MMdd").format(new Date());
		int nowYear = Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date()));
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 7) + nowMonthDay + "' and hjdxzqhdm != '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("0~6岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 19) + nowMonthDay + "' and csrq<='" + (nowYear - 7)
				+ nowMonthDay + "' and hjdxzqhdm != '310117' and syrklbdm = '01' ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("7~18岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 36) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 19) + nowMonthDay + "' and hjdxzqhdm != '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("19~35岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 60) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 36) + nowMonthDay + "' and hjdxzqhdm != '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("36~59岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq <= '"
				+ (nowYear - 60) + nowMonthDay + "' and hjdxzqhdm != '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("60岁及以上", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("20");
		data.setThemeName("各年龄段自然人统计（外区户籍）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 统计各年龄段（来沪）
	 */
	public void countpeopleByAgeLh() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String nowMonthDay = new SimpleDateFormat("MMdd").format(new Date());
		int nowYear = Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date()));
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 7) + nowMonthDay + "' and syrklbdm = '02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("0~6岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 19) + nowMonthDay + "' and csrq<='" + (nowYear - 7)
				+ nowMonthDay + "' and syrklbdm = '02' ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("7~18岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >='"
				+ (nowYear - 36) + nowMonthDay + "' and csrq<'"
				+ (nowYear - 19) + nowMonthDay + "' and syrklbdm = '02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("19~35岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 60) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 36) + nowMonthDay + "' and syrklbdm = '02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("35~59岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq <= '"
				+ (nowYear - 60) + nowMonthDay + "' and syrklbdm = '02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("60岁及以上", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("21");
		data.setThemeName("各年龄段自然人统计（来沪）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 统计各年龄段（本区户籍（老））
	 */
	public void countpeopleByAgeBql() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String nowMonthDay = new SimpleDateFormat("MMdd").format(new Date());
		int nowYear = Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date()));
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 70) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 60) + nowMonthDay + "' and hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("60~69岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 80) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 70) + nowMonthDay + "' and hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("70~79岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 90) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 80) + nowMonthDay + "' and hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("80~89岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 100) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 90) + nowMonthDay + "' and hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("90~99岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq <= '"
				+ (nowYear - 100) + nowMonthDay + "' and hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("100岁及以上", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("23");
		data.setThemeName("各年龄段自然人统计（本区（老））");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	
	/**
	 * 统计各年龄段（外区户籍（老））
	 */
	public void countpeopleByAgeWql() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String nowMonthDay = new SimpleDateFormat("MMdd").format(new Date());
		int nowYear = Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date()));
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 70) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 60) + nowMonthDay + "' and hjdxzqhdm != '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("60~69岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 80) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 70) + nowMonthDay + "' and hjdxzqhdm != '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("70~79岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 90) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 80) + nowMonthDay + "' and hjdxzqhdm != '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("80~89岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 100) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 90) + nowMonthDay + "' and hjdxzqhdm != '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("90~99岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq <= '"
				+ (nowYear - 100) + nowMonthDay + "' and hjdxzqhdm != '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("100岁及以上", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("24");
		data.setThemeName("各年龄段自然人统计（外区户籍（老））");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	
	/**
	 * 统计各年龄段（来沪（老））
	 */
	public void countpeopleByAgeLhl() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String nowMonthDay = new SimpleDateFormat("MMdd").format(new Date());
		int nowYear = Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date()));
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 70) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 60) + nowMonthDay + "' and syrklbdm = '02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("60~69岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 80) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 70) + nowMonthDay + "' and syrklbdm = '02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("70~79岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 90) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 80) + nowMonthDay + "' and syrklbdm = '02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("80~89岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 100) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 90) + nowMonthDay + "' and syrklbdm = '02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("90~99岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq <= '"
				+ (nowYear - 100) + nowMonthDay + "' and syrklbdm = '02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("100岁及以上", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("25");
		data.setThemeName("各年龄段自然人统计（来沪（老））");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/**
	 * 统计各年龄段（境外）
	 */
	public void countpeopleByAgeJw() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String nowMonthDay = new SimpleDateFormat("MMdd").format(new Date());
		int nowYear = Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date()));
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 7) + nowMonthDay + "' and syrklbdm = '03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("0~6岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 19) + nowMonthDay + "' and csrq<='" + (nowYear - 7)
				+ nowMonthDay + "' and syrklbdm = '03' ");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("7~18岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >='"
				+ (nowYear - 36) + nowMonthDay + "' and csrq<'"
				+ (nowYear - 19) + nowMonthDay + "' and syrklbdm = '03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("19~35岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 60) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 36) + nowMonthDay + "' and syrklbdm = '03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("35~59岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq <= '"
				+ (nowYear - 60) + nowMonthDay + "' and syrklbdm = '03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("60岁及以上", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("26");
		data.setThemeName("各年龄段自然人统计（境外）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/**
	 * 统计各年龄段（境外（老））
	 */
	public void countpeopleByAgeJwl() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String nowMonthDay = new SimpleDateFormat("MMdd").format(new Date());
		int nowYear = Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date()));
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 70) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 60) + nowMonthDay + "' and syrklbdm = '03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("60~69岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 80) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 70) + nowMonthDay + "' and syrklbdm = '03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("70~79岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 90) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 80) + nowMonthDay + "' and syrklbdm = '03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("80~89岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 100) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 90) + nowMonthDay + "' and syrklbdm = '03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("90~99岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq <= '"
				+ (nowYear - 100) + nowMonthDay + "' and syrklbdm = '03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("100岁及以上", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("27");
		data.setThemeName("各年龄段自然人统计（境外（老））");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}

	
	
	/**
	 * 法人登记走势（成立）
	 * 
	 * **/
	public void countFRCl() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		String year = new SimpleDateFormat("yyyy").format(new Date());
		for (int i = 2000; i <= Integer.parseInt(year); i++) {
			Sql sql = Sqls
					.create("select count(1) from corp_info where to_char(ESTABLISH_DATE,'yyyy')="
							+ i);
			sql.setCallback(new getOneStringCallBack());
			dao.execute(sql);
			String re = (String) sql.getResult();
			if (Strings.isEmpty(re))
				re = "0";
			result.put(i + "年", re);
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("28");
		data.setThemeName("法人登记走势（成立）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/**
	 * 法人登记走势（变更）
	 * 
	 * **/
	public void countFRBg() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		String year = new SimpleDateFormat("yyyy").format(new Date());
		for (int i = 2000; i <= Integer.parseInt(year); i++) {
			Sql sql = Sqls
					.create("select count(1) from corp_info where to_char(change_date,'yyyy')="
							+ i);
			sql.setCallback(new getOneStringCallBack());
			dao.execute(sql);
			String re = (String) sql.getResult();
			if (Strings.isEmpty(re))
				re = "0";
			result.put(i + "年", re);
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("29");
		data.setThemeName("法人登记走势（变更 ）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/**
	 * 法人登记走势（注销）
	 * 
	 * **/
	public void countFRZx() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		String year = new SimpleDateFormat("yyyy").format(new Date());
		for (int i = 2000; i <= Integer.parseInt(year); i++) {
			Sql sql = Sqls
					.create("select count(1) from corp_info where to_char(repeal_date,'yyyy')="
							+ i);
			sql.setCallback(new getOneStringCallBack());
			dao.execute(sql);
			String re = (String) sql.getResult();
			if (Strings.isEmpty(re))
				re = "0";
			result.put(i + "年", re);
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("30");
		data.setThemeName("法人登记走势（注销）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}

	/**
	 * 首页-法人栏
	 *
	 */
	public void countFRBar(){
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Sql sql = Sqls.create("SELECT COUNT(1) FROM CORP_INFO WHERE CORP_STATUS = '0001' and ESTABLISH_DATE is not null");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("松江区实有法人数", sql.getResult());
		
		
		sql = Sqls.create("select count(1) from corp_info where (area_code like '310117' or area_code like '%松江%') "
				+ "and CORP_STATUS = '0001' "
				+ "and CORP_TYPE in (00010000,00010100,00010200,00010300,00010400,00010500,00010600,00010700,00010800,00010900)");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("松江区注册企业", sql.getResult());
		sql = Sqls.create("select count(1) from corp_info where BUSINESS_ADDRESS like '%松江%' "
				+ "and CORP_STATUS = '0001' "
				+ "and CORP_TYPE in (00010000,00010100,00010200,00010300,00010400,00010500,00010600,00010700,00010800,00010900)");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("松江区经营企业", sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM CORP_INFO WHERE ESTABLISH_DATE>=Trunc(SYSDATE, 'MONTH') and  ESTABLISH_DATE<=sysdate AND (AREA_CODE='310117' OR AREA_CODE='松江')");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("法人本月新增", sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM CORP_INFO WHERE REPEAL_DATE>=Trunc(SYSDATE, 'MONTH') AND REPEAL_DATE<=SYSDATE AND (AREA_CODE='310117' OR AREA_CODE='松江')");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("法人本月变更", sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM CORP_INFO WHERE CHANGE_DATE>=Trunc(SYSDATE, 'MONTH') AND CHANGE_DATE<=SYSDATE AND (AREA_CODE='310117' OR AREA_CODE='松江')");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("法人本月注销", sql.getResult());
		
		sql = Sqls.create("select count(*) from CORP_INFO where CORP_STATUS='0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("法人登记", sql.getResult());
		
		sql = Sqls.create("select count(1) from corp_license");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("法人资质", sql.getResult());
		
		sql = Sqls.create("select count(1) from punish_note_enty");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("法人处罚", sql.getResult());
		
		sql = Sqls.create("select count(1) from CORP_AFTER_PERMIT");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("后置审批", sql.getResult());
		
		
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("31");
		data.setThemeName("首页法人栏");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	public void sjjh()
	{
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Sql sql = Sqls.create("SELECT COUNT(1) FROM T_GA_RJBXX");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("市公安", sql.getResult());
		
		sql = Sqls.create("select count(1) from CORP_INFO");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("市经信委", sql.getResult());
		Criteria cri1 = Cnd.cri();
		int count1=dao.count(CorpXJR.class,cri1);
		int count2=dao.count(CorpGQ.class,cri1);
		int count3=dao.count(CorpLHRH.class,cri1);
		result.put("科委", count1+count2+count3);
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("100");
		data.setThemeName("数据交换");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/**
	 * 首页-各行业法人总数占比
	 *
	 */
	public void countFRghyzszb(){
		Sql sql = Sqls.create("SELECT t.VALUE,COUNT(*) FROM corp_info c,dic_fr_industry_type t WHERE length(t.ID) = 1 AND c.INDUSTRY_CODE LIKE t.ID||'%' AND c.CORP_STATUS='0001' GROUP BY t.VALUE ORDER BY COUNT(*) DESC");
		sql.setCallback(new SqlCallback(){
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				Map<String, Object> r = new LinkedHashMap<String, Object>();
				int i = 0;
				int value=0;
				while (rs.next()) {
					if(i > 6){
						value += Integer.valueOf(rs.getString(2));
					}else{
						r.put(rs.getString(1),rs.getString(2));
					}
					i++;
				}
				r.put("其他",value);
				return r;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		Map<String, Object> result = sql.getObject(Map.class);
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("32");
		data.setThemeName("首页各行业法人总数占比");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 首页-法人资金分布
	 *
	 */
	public void countFRzjfb(){
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Sql sql = Sqls.create("SELECT COUNT(1) FROM CORP_INFO WHERE REG_CAPITAL<50 AND CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("<50", sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM CORP_INFO WHERE REG_CAPITAL>=50 AND REG_CAPITAL<100 AND CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("50-100万", sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM CORP_INFO WHERE REG_CAPITAL>=100 AND REG_CAPITAL<200 AND CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("100-200万", sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM CORP_INFO WHERE REG_CAPITAL>=200 AND REG_CAPITAL<500 AND CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put("200-500万", sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM CORP_INFO WHERE REG_CAPITAL >= 500 AND CORP_STATUS = '0001'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put(">=500万", sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("33");
		data.setThemeName("首页法人资金分布");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 首页-法人季度处罚情况
	 *
	 */
	public void countFRjdcf(){
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Sql sql = Sqls.create("SELECT COUNT(1) FROM PUNISH_NOTE_ENTY WHERE to_char(PUNISH_DATE,'YYYY-Q')=to_char(SYSDATE, 'YYYY-Q')");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		Object value = sql.getResult();
		sql = Sqls.create("SELECT SUBSTR(to_char(SYSDATE,'YYYY-Q'),0,4)||'年第'||SUBSTR(to_char(SYSDATE,'YYYY-Q'),6,1)||'季度' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put(sql.getResult().toString(), value);
		sql = Sqls.create("SELECT COUNT(1) FROM PUNISH_NOTE_ENTY WHERE to_char(PUNISH_DATE,'YYYY-Q')=to_char(ADD_MONTHS(SYSDATE,-3), 'YYYY-Q')");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		value = sql.getResult();
		sql = Sqls.create("SELECT SUBSTR(to_char(ADD_MONTHS(SYSDATE,-3),'YYYY-Q'),0,4)||'年第'||SUBSTR(to_char(ADD_MONTHS(SYSDATE,-3),'YYYY-Q'),6,1)||'季度' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put(sql.getResult().toString(), value);
		sql = Sqls.create("SELECT COUNT(1) FROM PUNISH_NOTE_ENTY WHERE to_char(PUNISH_DATE,'YYYY-Q')=to_char(ADD_MONTHS(SYSDATE,-6), 'YYYY-Q')");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		value = sql.getResult();
		sql = Sqls.create("SELECT SUBSTR(to_char(ADD_MONTHS(SYSDATE,-6),'YYYY-Q'),0,4)||'年第'||SUBSTR(to_char(ADD_MONTHS(SYSDATE,-6),'YYYY-Q'),6,1)||'季度' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put(sql.getResult().toString(), value);
		sql = Sqls.create("SELECT COUNT(1) FROM PUNISH_NOTE_ENTY WHERE to_char(PUNISH_DATE,'YYYY-Q')=to_char(ADD_MONTHS(SYSDATE,-9), 'YYYY-Q')");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		value = sql.getResult();
		sql = Sqls.create("SELECT SUBSTR(to_char(ADD_MONTHS(SYSDATE,-9),'YYYY-Q'),0,4)||'年第'||SUBSTR(to_char(ADD_MONTHS(SYSDATE,-9),'YYYY-Q'),6,1)||'季度' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put(sql.getResult().toString(), value);
		sql = Sqls.create("SELECT COUNT(1) FROM PUNISH_NOTE_ENTY WHERE to_char(PUNISH_DATE,'YYYY-Q')=to_char(ADD_MONTHS(SYSDATE,-12), 'YYYY-Q')");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		value = sql.getResult();
		sql = Sqls.create("SELECT SUBSTR(to_char(ADD_MONTHS(SYSDATE,-12),'YYYY-Q'),0,4)||'年第'||SUBSTR(to_char(ADD_MONTHS(SYSDATE,-12),'YYYY-Q'),6,1)||'季度' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put(sql.getResult().toString(), value);
		sql = Sqls.create("SELECT COUNT(1) FROM PUNISH_NOTE_ENTY WHERE to_char(PUNISH_DATE,'YYYY-Q')=to_char(ADD_MONTHS(SYSDATE,-15), 'YYYY-Q')");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		value = sql.getResult();
		sql = Sqls.create("SELECT SUBSTR(to_char(ADD_MONTHS(SYSDATE,-15),'YYYY-Q'),0,4)||'年第'||SUBSTR(to_char(ADD_MONTHS(SYSDATE,-15),'YYYY-Q'),6,1)||'季度' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		result.put(sql.getResult().toString(), value);
		result.put(sql.getResult().toString(), value);
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("34");
		data.setThemeName("首页法人季处罚情况");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 首页-人口栏 
	 *
	 */
	public void countPeopleBar(){
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Sql sql = Sqls.create("SELECT COUNT(1) FROM t_ga_rjbxx");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("松江区实有人口总数", sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM t_ga_rjbxx WHERE syrklbdm='01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("本市户籍人口", sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("本区户籍人口", sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM T_GA_RJBXX WHERE syrklbdm = '01' AND HJDXZQHHZ NOT LIKE '%松江%'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("外区户籍人口", sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM t_ga_rjbxx WHERE syrklbdm='02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("外来人员", sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM t_ga_rjbxx WHERE syrklbdm='03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("境外人员", sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("35");
		data.setThemeName("首页人口栏 ");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 首页-自然人年龄分布
	 *
	 */
	public void countPeopleNlfb() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		String nowMonthDay = new SimpleDateFormat("MMdd").format(new Date());
		int nowYear = Integer.parseInt(new SimpleDateFormat("yyyy")
				.format(new Date()));
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 7) + nowMonthDay + "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("0~6岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 19) + nowMonthDay + "' and csrq<='" + (nowYear - 7)
				+ nowMonthDay+ "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("7~18岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 61) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 19) + nowMonthDay+ "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("19~60岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq >'"
				+ (nowYear - 80) + nowMonthDay + "' and csrq<='"
				+ (nowYear - 61) + nowMonthDay+ "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("61~79岁", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where csrq <= '"
				+ (nowYear - 80) + nowMonthDay+ "'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put(">=80", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("36");
		data.setThemeName("首页自然人年龄分布");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	
	/**
	 * 首页—本区户籍人员年度趋势图
	 *
	 */
	public void countPeopleBqhjryqs() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Sql sql = Sqls.create("SELECT COUNT(1) FROM t_ga_rjbxx WHERE syrklbdm='01' AND hjdxzqhdm = '310117'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("今年", sql.getResult());
		sql = Sqls.create("SELECT to_char(ADD_MONTHS(SYSDATE,-12),'YYYY')||'年' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put(sql.getResult().toString(), "894700");
		sql = Sqls.create("SELECT to_char(ADD_MONTHS(SYSDATE,-24),'YYYY')||'年' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put(sql.getResult().toString(), "892600");
		sql = Sqls.create("SELECT to_char(ADD_MONTHS(SYSDATE,-36),'YYYY')||'年' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put(sql.getResult().toString(), "885800");
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("37");
		data.setThemeName("首页本区户籍人员年度趋势图");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 首页—来沪人员年度趋势图
	 *
	 */
	public void countPeopleBqlhryqs() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		Sql sql = Sqls.create("SELECT COUNT(1) FROM t_ga_rjbxx WHERE syrklbdm='02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("今年", sql.getResult());
		sql = Sqls.create("SELECT to_char(ADD_MONTHS(SYSDATE,-12),'YYYY')||'年' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put(sql.getResult().toString(), "343500");
		sql = Sqls.create("SELECT to_char(ADD_MONTHS(SYSDATE,-24),'YYYY')||'年' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put(sql.getResult().toString(), "347500");
		sql = Sqls.create("SELECT to_char(ADD_MONTHS(SYSDATE,-36),'YYYY')||'年' FROM dual");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put(sql.getResult().toString(), "350400");
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("38");
		data.setThemeName("首页来沪人员年度趋势图");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/**
	 * 实有人口类别统计
	 */
	public void countpeoplbtj() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		Sql sql = Sqls.create("select count(1) from t_ga_rjbxx where hjdxzqhdm = '310117' and syrklbdm = '01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("bqhj", (String) sql.getResult());
		sql = Sqls.create("select count(*) from T_GA_RJBXX where syrklbdm='01'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("hjry", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where syrklbdm = '02'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("lhry", (String) sql.getResult());
		sql = Sqls.create("select count(1) from t_ga_rjbxx where syrklbdm = '03'");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("jwry", (String) sql.getResult());
		
		sql = Sqls.create("select count(1) from t_ga_rjbxx");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("rkzs", (String) sql.getResult());
		
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("39");
		data.setThemeName("实有人口类别分析");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/**
	 * 统计人户分离（人在户在）
	 */
	public void countpeoplerhfl1() {
		Sql sql1 = Sqls
				.create("select id from T_ECHARTS_RHFL where DATAMONTH =to_char(sysdate,'yyyymm') and TYPE = '人在户在'");
		sql1.setCallback(new getOneStringCallBack());
		dao.execute(sql1);
		String id = (String) sql1.getResult();
		if (Strings.isEmpty(id)) {
			Sql countsql = Sqls
					.create("SELECT COUNT(1) FROM T_GA_RJBXX WHERE HJDXZQHHZ LIKE '%松江%' "
							+ "AND (JZFWID=HJFWID OR (JZFWID IS NULL AND HJFWID IS NOT NULL))");
			countsql.setCallback(new getOneStringCallBack());
			dao1.execute(countsql);
			String count = (String) countsql.getResult();
			Sql insertsqlSql = Sqls
					.create("insert into T_ECHARTS_RHFL (ID,DATAMONTH,VALUE,CREATE_DATE,TYPE) values (@ID,to_char(sysdate,'yyyymm'),@VALUE,sysdate,'人在户在')");
			insertsqlSql.params().set("ID",
					UUID.randomUUID().toString().replaceAll("-", ""));
			insertsqlSql.params().set("VALUE", count);
			dao.execute(insertsqlSql);
		}

		Sql sql = Sqls
				.create("select * from (select * from T_ECHARTS_RHFL order by DATAMONTH desc) where TYPE = '人在户在' and rownum<=3 order by DATAMONTH asc ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql)
					throws SQLException {
				Map<String, String> result = new LinkedHashMap<String, String>();
				while (rs.next()) {
					result.put(rs.getString("DATAMONTH"), rs.getString("VALUE"));
				}
				return result;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		Map<String, String> result = (Map<String, String>) sql.getResult();
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("40");
		data.setThemeName("人户分离情况（人在户在）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);

	}
	/**
	 * 统计人户分离（户在人不在）
	 */
	public void countpeoplerhfl2() {
		Sql sql1 = Sqls
				.create("select id from T_ECHARTS_RHFL where DATAMONTH =to_char(sysdate,'yyyymm') and TYPE = '户在人不在'");
		sql1.setCallback(new getOneStringCallBack());
		dao.execute(sql1);
		String id = (String) sql1.getResult();
		if (Strings.isEmpty(id)) {
			Sql countsql = Sqls
					.create("SELECT COUNT(1) FROM T_GA_RJBXX WHERE HJDXZQHHZ LIKE '%松江%' "
							+ "AND JZFWID IS NOT NULL AND JZFWID!=HJFWID");
			countsql.setCallback(new getOneStringCallBack());
			dao1.execute(countsql);
			String count = (String) countsql.getResult();
			Sql insertsqlSql = Sqls
					.create("insert into T_ECHARTS_RHFL (ID,DATAMONTH,VALUE,CREATE_DATE,TYPE) values (@ID,to_char(sysdate,'yyyymm'),@VALUE,sysdate,'户在人不在')");
			insertsqlSql.params().set("ID",
					UUID.randomUUID().toString().replaceAll("-", ""));
			insertsqlSql.params().set("VALUE", count);
			dao.execute(insertsqlSql);
		}

		Sql sql = Sqls
				.create("select * from (select * from T_ECHARTS_RHFL order by DATAMONTH desc) where TYPE = '户在人不在' and rownum<=3 order by DATAMONTH asc ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql)
					throws SQLException {
				Map<String, String> result = new LinkedHashMap<String, String>();
				while (rs.next()) {
					result.put(rs.getString("DATAMONTH"), rs.getString("VALUE"));
				}
				return result;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		Map<String, String> result = (Map<String, String>) sql.getResult();
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("41");
		data.setThemeName("人户分离情况（户在人不在）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);

	}
	/**
	 * 统计人户分离（人在户不在）
	 */
	public void countpeoplerhfl3() {
		Sql sql1 = Sqls
				.create("select id from T_ECHARTS_RHFL where DATAMONTH =to_char(sysdate,'yyyymm') and TYPE = '人在户不在'");
		sql1.setCallback(new getOneStringCallBack());
		dao.execute(sql1);
		String id = (String) sql1.getResult();
		if (Strings.isEmpty(id)) {
			Sql countsql = Sqls
					.create("SELECT COUNT(1) FROM T_GA_RJBXX WHERE JZDXZQHHZ LIKE '%松江%' "
							+ "AND HJFWID IS NOT NULL AND JZFWID!=HJFWID");
			countsql.setCallback(new getOneStringCallBack());
			dao1.execute(countsql);
			String count = (String) countsql.getResult();
			Sql insertsqlSql = Sqls
					.create("insert into T_ECHARTS_RHFL (ID,DATAMONTH,VALUE,CREATE_DATE,TYPE) values (@ID,to_char(sysdate,'yyyymm'),@VALUE,sysdate,'人在户不在')");
			insertsqlSql.params().set("ID",
					UUID.randomUUID().toString().replaceAll("-", ""));
			insertsqlSql.params().set("VALUE", count);
			dao.execute(insertsqlSql);
		}

		Sql sql = Sqls
				.create("select * from (select * from T_ECHARTS_RHFL order by DATAMONTH desc) where TYPE = '人在户不在' and rownum<=3 order by DATAMONTH asc ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql)
					throws SQLException {
				Map<String, String> result = new LinkedHashMap<String, String>();
				while (rs.next()) {
					result.put(rs.getString("DATAMONTH"), rs.getString("VALUE"));
				}
				return result;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		Map<String, String> result = (Map<String, String>) sql.getResult();
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("42");
		data.setThemeName("人户分离情况（人在户不在）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);

	}
	
	
	/**
	 * 统计各街镇人户分离（人在户在）
	 */
	public void countpeopleRhfl1() {
		Map<String, String> jiezhen = DicDataUtils.getInstance().getDic(1077);
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (String key : jiezhen.keySet()) {
			Sql sql = Sqls
					.create("SELECT COUNT(1) FROM T_GA_RJBXX WHERE HJDXZQHHZ LIKE '%松江%' "
							+ "AND (JZFWID=HJFWID OR (JZFWID IS NULL AND HJFWID IS NOT NULL)) "
							+ "AND HJDJDDM LIKE '17%' and HJDJDDM = '"+key+"' ");
			sql.setCallback(new getOneStringCallBack());
			dao1.execute(sql);
			result.put(jiezhen.get(key), (String) sql.getResult());
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("43");
		data.setThemeName("统计各街镇人户分离（人在户在）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 统计各街镇人户分离（户在人不在）
	 */
	public void countpeopleRhfl2() {
		Map<String, String> jiezhen = DicDataUtils.getInstance().getDic(1077);
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (String key : jiezhen.keySet()) {
			Sql sql = Sqls
					.create("SELECT COUNT(1) FROM T_GA_RJBXX WHERE HJDXZQHHZ LIKE '%松江%' "
							+ "AND JZFWID IS NOT NULL AND JZFWID!=HJFWID "
							+ " and HJDJDDM = '"+key+"'");
			sql.setCallback(new getOneStringCallBack());
			dao1.execute(sql);
			result.put(jiezhen.get(key), (String) sql.getResult());
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("44");
		data.setThemeName("统计各街镇人户分离（户在人不在）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 统计各街镇人户分离（人在户在）
	 */
	public void countpeopleRhfl3() {
		Map<String, String> jiezhen = DicDataUtils.getInstance().getDic(1077);
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (String key : jiezhen.keySet()) {
			Sql sql = Sqls
					.create("SELECT COUNT(1) FROM T_GA_RJBXX WHERE JZDXZQHHZ LIKE '%松江%' "
							+ "AND HJFWID IS NOT NULL AND JZFWID!=HJFWID "
							+ "AND JZDJDDM LIKE '17%' and HJDJDDM = '"+key+"'");
			sql.setCallback(new getOneStringCallBack());
			dao1.execute(sql);
			result.put(jiezhen.get(key), (String) sql.getResult());
		}
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("45");
		data.setThemeName("统计各街镇人户分离（人在户在）");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 统计当前人户分离
	 */
	public void countpeopleRhfl4() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		Sql sql = Sqls.create("SELECT COUNT(1) FROM T_GA_RJBXX WHERE HJDXZQHHZ LIKE '%松江%' "
				+ "AND (JZFWID=HJFWID OR (JZFWID IS NULL AND HJFWID IS NOT NULL))");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("rzhz", (String) sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM T_GA_RJBXX WHERE HJDXZQHHZ LIKE '%松江%' "
				+ "AND JZFWID IS NOT NULL AND JZFWID!=HJFWID");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("hzrbz", (String) sql.getResult());
		sql = Sqls.create("SELECT COUNT(1) FROM T_GA_RJBXX WHERE JZDXZQHHZ LIKE '%松江%' "
				+ "AND HJFWID IS NOT NULL AND JZFWID!=HJFWID");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		result.put("rzhbz", (String) sql.getResult());
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("46");
		data.setThemeName("统计当前人户分离");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	/**
	 * 统计实有，法人，房屋。
	 */
	public void countpeoplePublic() {

		Map<String, String> result = new LinkedHashMap<String, String>();
		// 人口 总数
		Sql sql = Sqls.create("select count(1) from T_GA_RJBXX");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		String peoplesum = (String) sql.getResult();
		result.put("syrk", peoplesum);
		
		// 保障房
		Sql sql1 = Sqls.create("select SUM(HOUSE_AREA) from PROJECT_NG_YOU_CHAN_QUAN");
		sql1.setCallback(new getOneStringCallBack());
		dao1.execute(sql1);
		String peoplesum1 = (String) sql1.getResult();
		result.put("ryfw", peoplesum1);
		
		// 时有法人
		Sql sql2 = Sqls.create("SELECT COUNT(1) FROM CORP_INFO WHERE CORP_STATUS = '0001' and ESTABLISH_DATE is not null");
		sql2.setCallback(new getOneStringCallBack());
		dao1.execute(sql2);
		String peoplesum2 = (String) sql2.getResult();
		result.put("syfr", peoplesum2);
				
		
		
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("77");
		data.setThemeName("法人人口保障房");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	/***
	 * 资源接入项统计
	 * ***/
	public void   zyTj()
	{
		Map<String, String> result = new LinkedHashMap<String, String>();
		// 公安分局
		Sql sql = Sqls.create("select count(1) from T_GA_RJBXX");
		sql.setCallback(new getOneStringCallBack());
		dao1.execute(sql);
		String peoplesum = (String) sql.getResult();
		result.put("syrk", peoplesum);
		//社治办
		Sql sql1=null;
		int count = 0;
		Criteria cri = Cnd.cri();
		cri.where().andEquals("provideDepId","X07060");
		cri.where().andEquals("resourceType", "r_ztl");
		cri.where().andEquals("status", "10715");
		List<Resource> resourceList = dao.query(Resource.class,cri);
		for(int i=0;i<resourceList.size();i++)
		{
			String table="BM_"+resourceList.get(i).getTableName();
			sql1 = Sqls.create("select count(1) from "+ table);
			sql.setCallback(new getOneStringCallBack());
			dao1.execute(sql1);
			String SL=(String)sql1.getResult();
			if(SL==null)
			{
				SL="0";
			}
			count+=Integer.parseInt(SL);
			result.put("szb", String.valueOf(count));
			}
		//科委
		
		Sql sql2=null;
		int count1 = 0;
		Criteria cri1 = Cnd.cri();
		cri1.where().andEquals("provideDepId","068001");
		cri1.where().andEquals("resourceType", "r_ztl");
		cri1.where().andEquals("status", "10715");
		List<Resource> resourceList1 = dao.query(Resource.class,cri1);
		String table1=null;
		for(int j=0;j<resourceList1.size();j++)
		{
			if(resourceList1.get(j).getTableName().equals("CORP_XJR")||resourceList1.get(j).getTableName().endsWith("CORP_LHRH")||resourceList1.get(j).getTableName().equals("CORP_GQ"))
			{
				table1=resourceList1.get(j).getTableName();
			}else{
			 table1="BM_"+resourceList1.get(j).getTableName();
			}
			sql2 = Sqls.create("select count(1) from "+ table1);
			sql2.setCallback(new getOneStringCallBack());
			dao1.execute(sql2);
			String SL1=(String)sql2.getResult();
			if(SL1==null)
			{
				SL1="0";
			}
			count1+=Integer.parseInt(SL1);
			result.put("kw", String.valueOf(count1));
			}
		
		
		EchartsData data = new EchartsData();
		data.setCreateDate(new Date());
		data.setThemeId("67");
		data.setThemeName("资源接入数量");
		data.setDataJson(JSONObject.fromObject(result).toString());
		dao.insert(data);
	}
	
	
	
	private class getOneStringCallBack implements SqlCallback {
		@Override
		public Object invoke(Connection conn, ResultSet rs, Sql sql)
				throws SQLException {
			if (rs.next()) {
				return rs.getString(1);
			}
			return null;
		}

	}
}
