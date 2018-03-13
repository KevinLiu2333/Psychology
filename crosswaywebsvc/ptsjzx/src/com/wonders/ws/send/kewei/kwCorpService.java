package com.wonders.ws.send.kewei;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.WsUitl;
import com.wonders.ws.bean.CorpInfoBo;
import com.wonders.ws.bean.CorpLicenseBean;
import com.wonders.ws.dao.CorpDao;
import com.wonders.ws.dao.KeyChecker;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class kwCorpService {
    Ioc ioc = null;
    CorpDao dao = null;

    /**
     * 区科委普陀专利
     * 按关键字查询登记信息
     *
     * @param CORP_NAME,ORGAN_CODE,UNI_SC_ID,REG_NO,TAXPAYERS_CODE
     * @return
     */
    public String getCorpInfoToKewei(String key, String keyword, String returntype) {
        try {
            if (ioc == null) {
                ioc = IocSingle.getInstance();
            }
            KeyChecker checker = ioc.get(KeyChecker.class);
            ApiServiceUser serviceUser = checker.keyckeck(key, "getCorpInfoToKewei");
            if (serviceUser == null) {
                return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
            }
            if (serviceUser.getEndDate().before(new Date())) {
                return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
            }
            if (dao == null) {
                dao = ioc.get(CorpDao.class);
                Map<String, String> result = dao.getCorpInfoToKewei(keyword, serviceUser);
                return WsUitl.getResult(result, returntype);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param key        调用key
     * @param acceptCode 组织机构代码/统一信用代码/法人名称
     * @param returntype 返回值类型
     * @return
     */
    public String getCorpInfoToKeweiOA(String key, String acceptCode, String returntype) {
        if (ioc == null) {
            ioc = IocSingle.getInstance();
        }
        KeyChecker checker = ioc.get(KeyChecker.class);
        ApiServiceUser serviceUser = checker.keyckeck(key, "getCorpInfoToKeweiOA");
        if (serviceUser == null) {
            return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
        }
        if (serviceUser.getEndDate().before(new Date())) {
            return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
        }
        Dao dao = ioc.get(Dao.class);
        List<CorpInfoBo> corpInfoBos = dao.query(CorpInfoBo.class, Cnd.where("uni_sc_id", "like", "%" + acceptCode + "%").or("corp_name", "like", "%" + acceptCode + "%").or("organ_code", "like", "%" + acceptCode + "%"));
        return WsUitl.ListResult(corpInfoBos, returntype);
    }

    /**
     * @param key        key
     * @param acceptCode 统一信用代码/组织机构代码/法人名称
     * @param returntype 返回类型
     * @return
     */
    public String getLicenseInfoToKeweiOA(String key, String acceptCode, String returntype) {
        if (ioc == null) {
            ioc = IocSingle.getInstance();
        }
        KeyChecker checker = ioc.get(KeyChecker.class);
        ApiServiceUser serviceUser = checker.keyckeck(key, "getLicenseInfoToKeweiOA");
        if (serviceUser == null) {
            return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
        }
        if (serviceUser.getEndDate().before(new Date())) {
            return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
        }
        Dao dao = ioc.get(Dao.class);
        CorpInfoBo corpInfoBo = dao.fetch(CorpInfoBo.class, Cnd.where("uni_sc_id", "like", "%" + acceptCode + "%").or("corp_name", "like", "%" + acceptCode + "%").or("organ_code", "like", "%" + acceptCode + "%"));
        if (corpInfoBo == null) {
            return WsUitl.getResult(null, returntype);
        }
        List<CorpLicenseBean> corpLicenseBeans = dao.query(CorpLicenseBean.class, Cnd.where("corpinfoid", "=", corpInfoBo.getCorpInfoId()));
        return WsUitl.ListResult(corpLicenseBeans, returntype);

    }

}