package com.wonders.ws.send.zfw;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.WsUitl;
import com.wonders.ws.bean.CorpInfoBo;
import com.wonders.ws.dao.KeyChecker;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/2/6
 * Time: 13:54
 */
@WebService
public class ZfwZACorpInfoService {
    private Ioc ioc = null;


    public String getCorpInfoToZFWZA(String key, String acceptCode, String returntype) {
        if (ioc == null) {
            ioc = IocSingle.getInstance();
        }
        KeyChecker checker = ioc.get(KeyChecker.class);
        ApiServiceUser serviceUser = checker.keyckeck(key, "getCorpInfoToZFWZA");
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
}
