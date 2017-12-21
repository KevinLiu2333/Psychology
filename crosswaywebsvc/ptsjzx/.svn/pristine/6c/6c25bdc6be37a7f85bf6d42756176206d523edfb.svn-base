package com.wonders.ws.dao;

import com.wonders.api.entity.ApiLogWebService;
import com.wonders.api.entity.ApiServiceUser;
import com.wonders.echarts.entity.EchartsData;
import com.wonders.ws.WsUitl;
import com.wonders.ws.bean.HouseInfoBean;
import com.wonders.ws.bean.PersonInfoBo;
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@IocBean
public class ServiceDao {

    @Inject
    private Dao dao;

    @Inject
    private Dao dao1;

	/*
     * public PersonInfoBean getPersonInfo(String zjhm,ApiServiceUser serviceUser) { Criteria cri = Cnd.cri(); cri.where().and("ZJHM", "=", zjhm); List<PersonInfoBean> personList =
	 * dao1.query(PersonInfoBean.class, cri);
	 * 
	 * ApiLogWebService log=WsUitl.createlog(serviceUser, personList.size()); dao.insert(log);
	 * 
	 * return personList.get(0); }
	 */

	/*
     * public List<PersonInfoBean> queryPersonListByJuWeiDM(String jwdm,ApiServiceUser serviceUser) { Criteria cri = Cnd.cri(); cri.where().and("JZDJWHDM", "=", jwdm);
	 * List<PersonInfoBean> personList = dao1.query(PersonInfoBean.class, cri);
	 * 
	 * ApiLogWebService log=WsUitl.createlog(serviceUser, personList.size()); dao.insert(log);
	 * 
	 * return personList; }
	 */

    public List<HouseInfoBean> queryHouseListByJcwdm(String jcwdm, ApiServiceUser serviceUser) {
        Criteria cri = Cnd.cri();
        cri.where().and("JCWDM", "=", jcwdm);
        List<HouseInfoBean> houseList = dao1.query(HouseInfoBean.class, cri);

        ApiLogWebService log = WsUitl.createlog(serviceUser, houseList.size());
        dao.insert(log);

        return houseList;
    }

    // 根据身份证号和姓名查询街道名称，并返回身份证号与姓名是否匹配
    @SuppressWarnings("unchecked")
    public Map<String, String> getJiedaotInfoByIdentification(final String name, final String zjhm, ApiServiceUser serviceUser) {
        Sql sql = Sqls.create("select XM,ZJHM,HJDJDHZ from T_GA_RJBXX " + "where ZJHM = '" + zjhm + "' ");
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
                if (rs.next()) {
                    Map<String, String> result = new HashMap<String, String>();
                    String jieguo = rs.getString("xm");
                    if (jieguo.equals(name)) {
                        result.put("xm", rs.getString("XM"));
                        result.put("hjdjdhz", rs.getString("HJDJDHZ"));
                        return result;
                    } else if (!(jieguo.equals(name))) {
                        result.put("result", "身份证号与姓名不匹配！！");
                        return result;
                    }
                } else if (!Strings.isEmpty(zjhm)) {
                    Map<String, String> result = new HashMap<String, String>();
                    result.put("result", "查无此人！！");
                    return result;
                }
                return null;
            }
        });
        dao.execute(sql);
        Map<String, String> result = (Map<String, String>) sql.getResult();
        ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
        dao.insert(log);
        return result;
    }

    // 根据身份证号查询人基本信息
    // 民政局
    @SuppressWarnings("unchecked")
    public Map<String, String> getInfoByZjhmToMzjTaizhang(String SFZH, ApiServiceUser serviceUser) {
        Sql sql = Sqls.create("select XM,XBDM,XBHZ,CSRQ,ZJHM,MZDM,MZHZ,HYZKDM,HYZKHZ,WHCDDM,WHCDHZ,"
                + "HH,HJDXZQHDM,HJDXZQHHZ,HJDJDDM,HJDJDHZ,HJDJWHDM,HJDJWHHZ,HJDZ,JZDXZQHDM,JZDXZQHHZ,"
                + "JZDJDDM,JZDJDHZ,JZDJWHDM,JZDJWHHZ,JZDZ from t_ga_rjbxx where ZJHM = '" + SFZH + "' ");
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
                if (rs.next()) {
                    Map<String, String> result = new HashMap<String, String>();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int col = rsmd.getColumnCount();
                    for (int i = 1; i <= col; i++) {
                        result.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
                    }
                    return result;
                }
                return null;
            }
        });
        dao.execute(sql);
        Map<String, String> result = (Map<String, String>) sql.getResult();
        ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
        dao.insert(log);
        return result;
    }

    // 根据姓名+出生日期查询人基本信息
    // 民政局
    @SuppressWarnings("unchecked")
    public Map<String, Object> getInfoByXmToMzjTaizhang(String XM, String CSRQ, ApiServiceUser serviceUser) {
        Map<String, Object> result = new HashMap<String, Object>();
        Sql sql = Sqls.create("select XM,XBDM,XBHZ,CSRQ,ZJHM,MZDM,MZHZ,HYZKDM,HYZKHZ,WHCDDM,WHCDHZ,"
                + "HH,HJDXZQHDM,HJDXZQHHZ,HJDJDDM,HJDJDHZ,HJDJWHDM,HJDJWHHZ,HJDZ,JZDXZQHDM,JZDXZQHHZ,"
                + "JZDJDDM,JZDJDHZ,JZDJWHDM,JZDJWHHZ,JZDZ from t_ga_rjbxx "
                + "where XM = '" + XM + "' and CSRQ = '" + CSRQ + "' ");
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
                List<Object> result = new ArrayList<Object>();
                while (rs.next()) {
                    Map<String, String> map = new HashMap<String, String>();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int col = rsmd.getColumnCount();
                    for (int i = 1; i <= col; i++) {
                        map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
                    }
                    result.add(map);
                }
                return result;
            }
        });
        dao.execute(sql);
        List<Object> list = (List<Object>) sql.getResult();
        if (list.size() == 0) {
            result.put("list", null);
        } else {
            result.put("list", list);
        }
        ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
        dao.insert(log);
        return result;
    }

    // 根据居委会代码返回统计信息
    public Map<String, Object> getInfoByJwhdmToMzjTaizhang(String JWHDM, ApiServiceUser serviceUser) {
        Map<String, Object> result = new HashMap<String, Object>();
        Criteria cri = Cnd.cri();
        cri.where().and("THEME_ID", "=", 56);
        cri.getOrderBy().desc("CREATE_DATE");
        List<EchartsData> list = dao.query(EchartsData.class, cri, dao.createPager(1, 1));
        JSONObject json = JSONObject.fromObject(list.get(0).getDataJson());
        String ja = json.get(JWHDM).toString();
        result.put("result", ja);
        ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
        dao.insert(log);
        return result;
    }

    //    根据年月信息获取增量人口身份号列表接口
    public Map<String, Object> getInfoByyuefenToMzjDztz(String nianyue, ApiServiceUser serviceUser) {
        Map<String, Object> map = new HashMap<>();

        Sql sql = Sqls.create("select ZJHM from T_RJBXX_CHANGE where date_time=@nianyue");
        sql.params().set("nianyue", nianyue);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Map<String, String>> list = new ArrayList<>();
                while (rs.next()) {
                    Map<String, String> map = new HashMap<>();
                    map.put("zjhm", rs.getString("ZJHM"));
                    list.add(map);
                }
                return list;
            }
        });
        dao.execute(sql);
        List<Map<String, String>> list = (List<Map<String, String>>) sql.getResult();
//        查无结果
        if (list.size() == 0) {
            return null;
        }
        ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
        dao.insert(log);
        map.put("data", list);
        return map;
    }

    public PersonInfoBo getInfoByZjhmToMzjDztz(String zjhm, ApiServiceUser serviceUser) {
        PersonInfoBo personInfoBo = dao.fetch(PersonInfoBo.class, zjhm);
        ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
        dao.insert(log);
        return personInfoBo;
    }
}
