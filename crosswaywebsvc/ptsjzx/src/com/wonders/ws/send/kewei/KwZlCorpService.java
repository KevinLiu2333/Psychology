package com.wonders.ws.send.kewei;

import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.tools.IocSingle;
import com.wonders.ws.WsUitl;
import com.wonders.ws.dao.KeyChecker;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.Ioc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 专利查询
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/1/29
 * Time: 14:17
 */
public class KwZlCorpService {
    private Ioc ioc = null;

    public String getCorpInfoToKWZL(String key, String acceptCode, String returntype) {
        if (ioc == null) {
            ioc = IocSingle.getInstance();
        }
        KeyChecker checker = ioc.get(KeyChecker.class);
        ApiServiceUser serviceUser = checker.keyckeck(key, "getCorpInfoToKWZL");
        if (serviceUser == null) {
            return WsUitl.getResult(KeyChecker.ERROR_KEY, returntype);
        }
        if (serviceUser.getEndDate().before(new Date())) {
            return WsUitl.getResult(KeyChecker.OVER_TIME, returntype);
        }
        Dao dao = ioc.get(Dao.class);
        String sqlString = "select uni_sc_id,organ_code,corp_name,person_name,address,business_address,zip,telephone from corp_info where uni_sc_id like '%$acceptCode%' or corp_name like '%$acceptCode%' or organ_code like '%$acceptCode%'";
        Sql sql = Sqls.create(sqlString);
        sql.vars().set("acceptCode", acceptCode);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                List<Map<String, Object>> maps = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("uni_sc_id", resultSet.getString("uni_sc_id"));
                    map.put("organ_code", resultSet.getString("organ_code"));
                    map.put("corp_name", resultSet.getString("corp_name"));
                    map.put("person_name", resultSet.getString("person_name"));
                    map.put("address", resultSet.getString("address"));
                    map.put("business_address", resultSet.getString("business_address"));
                    map.put("zip", resultSet.getString("zip"));
                    map.put("telephone", resultSet.getString("telephone"));
                    maps.add(map);
                }
                return maps;
            }
        });
        dao.execute(sql);
        return WsUitl.ListResult(sql.getList(Map.class), returntype);
    }
}
