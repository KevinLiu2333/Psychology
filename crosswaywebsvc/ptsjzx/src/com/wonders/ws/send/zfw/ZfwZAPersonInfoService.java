package com.wonders.ws.send.zfw;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.WsUitl;
import com.wonders.ws.bean.ZfwPersonInfo;
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
 * Time: 15:43
 */
@WebService
public class ZfwZAPersonInfoService {

    private Ioc ioc = null;

    public String getPersonInfoToZFWZA(String key, String acceptCode, String returntype) {
        if (ioc == null) {
            ioc = IocSingle.getInstance();
        }
        KeyChecker checker = ioc.get(KeyChecker.class);
        ApiServiceUser serviceUser = checker.keyckeck(key, "getPersonInfoToZFWZA");
        if (serviceUser == null) {
            return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
        }
        if (serviceUser.getEndDate().before(new Date())) {
            return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
        }
        Dao dao = ioc.get(Dao.class);
        List<ZfwPersonInfo> personInfos = dao.query(ZfwPersonInfo.class, Cnd.where("zjhm", "=", acceptCode).or("hjdz", "like", "%" + acceptCode + "%").or("jzdz", "like", "%" + acceptCode + "%"));
        return WsUitl.ListResult(personInfos, returntype);
    }
}
