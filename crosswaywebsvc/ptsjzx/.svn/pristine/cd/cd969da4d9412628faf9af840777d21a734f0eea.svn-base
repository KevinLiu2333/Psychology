package com.wonders.ws.send.minzhengju;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.WsUitl;
import com.wonders.ws.bean.PersonInfoBo;
import com.wonders.ws.dao.KeyChecker;
import com.wonders.ws.dao.ServiceDao;
import org.nutz.ioc.Ioc;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class mzjTaizhangPeopleService {
    Ioc ioc = null;
    ServiceDao dao = null;

    /**
     * 根据身份证号查询人基本信息
     *
     * @param zjhm
     * @return
     */
    public String getInfoByZjhmToMzjTaizhang(String key, String SFZH, String returntype) {
        try {
            if (ioc == null) {
                ioc = IocSingle.getInstance();
            }
            KeyChecker checker = ioc.get(KeyChecker.class);
            ApiServiceUser serviceUser = checker.keyckeck(key, "getInfoByZjhmToMzjTaizhang");
            if (serviceUser == null) {
                return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
            }
            if (serviceUser.getEndDate().before(new Date())) {
                return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
            }
            if (dao == null) {
                dao = ioc.get(ServiceDao.class);
                Map<String, String> result = dao.getInfoByZjhmToMzjTaizhang(SFZH, serviceUser);
                return WsUitl.getResult(result, returntype);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据姓名和出生日期查询人基本信息
     *
     * @param zjhm
     * @return
     */
    @SuppressWarnings("rawtypes")
    public String getInfoByXmToMzjTaizhang(String key, String XM, String CSRQ, String returntype) {
        try {
            if (ioc == null) {
                ioc = IocSingle.getInstance();
            }
            KeyChecker checker = ioc.get(KeyChecker.class);
            ApiServiceUser serviceUser = checker.keyckeck(key, "getInfoByXmToMzjTaizhang");
            if (serviceUser == null) {
                return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
            }
            if (serviceUser.getEndDate().before(new Date())) {
                return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
            }
            if (dao == null) {
                dao = ioc.get(ServiceDao.class);
                Map<String, Object> result = dao.getInfoByXmToMzjTaizhang(XM, CSRQ, serviceUser);
                return WsUitl.ListResult((List) result.get("list"), returntype);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据居委会代码返回统计信息
     *
     * @param JWHDM
     * @return
     */
    public String getInfoByJwhdmToMzjTaizhang(String key, String JWHDM, String returntype) {
        try {
            if (ioc == null) {
                ioc = IocSingle.getInstance();
            }
            KeyChecker checker = ioc.get(KeyChecker.class);
            ApiServiceUser serviceUser = checker.keyckeck(key, "getInfoByJwhdmToMzjTaizhang");
            if (serviceUser == null) {
                return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
            }
            if (serviceUser.getEndDate().before(new Date())) {
                return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
            }
            if (dao == null) {
                dao = ioc.get(ServiceDao.class);
                Map<String, Object> result = dao.getInfoByJwhdmToMzjTaizhang(JWHDM, serviceUser);
                return WsUitl.getResult(result, returntype);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据年月信息获取增量人口身份号列表接口
     *
     * @param key        身份验证参数
     * @param nianyue    年月
     * @param returntype 返回类型
     * @return 查询结果
     */
    public String getInfoByyuefenToMzjDztz(String key, String nianyue, String returntype) {
        Map<String, Object> result;
        try {
            if (ioc == null) {
                ioc = IocSingle.getInstance();
            }
            KeyChecker checker = ioc.get(KeyChecker.class);
            ApiServiceUser serviceUser = checker.keyckeck(key, "getInfoByyuefenToMzjDztz");
            if (serviceUser == null) {
                return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
            }
            if (serviceUser.getEndDate().before(new Date())) {
                return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
            }
            if (dao == null) {
                dao = ioc.get(ServiceDao.class);
            }
            result = dao.getInfoByyuefenToMzjDztz(nianyue, serviceUser);
        } catch (Exception e) {
            return WsUitl.getResult("{ msg:\"内部错误，请联系管理员！\" }", returntype);
        }

        return WsUitl.getResult(result, returntype);
    }


    /**
     * 根据身份证号码获取人员详细信息
     *
     * @param key        接口调用验证参数
     * @param zjhm       证件号码
     * @param returntype 返回值类型
     * @return 查询结果
     */
    public String getInfoByZjhmToMzjDztz(String key, String zjhm, String returntype) {
        PersonInfoBo result;
        try {
            if (ioc == null) {
                ioc = IocSingle.getInstance();
            }
            KeyChecker checker = ioc.get(KeyChecker.class);
            ApiServiceUser serviceUser = checker.keyckeck(key, "getInfoByZjhmToMzjDztz");
            if (serviceUser == null) {
                return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
            }
            if (serviceUser.getEndDate().before(new Date())) {
                return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
            }
            if (dao == null) {
                dao = ioc.get(ServiceDao.class);
            }
            result = dao.getInfoByZjhmToMzjDztz(zjhm, serviceUser);
        } catch (Exception e) {
            return WsUitl.getResult("{ msg:\"内部错误，请联系管理员！\" }", returntype);
        }

        return WsUitl.getResult(result, returntype);
    }


}
