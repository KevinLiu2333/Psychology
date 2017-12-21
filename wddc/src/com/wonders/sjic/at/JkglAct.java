package com.wonders.sjic.at;

import com.wonders.sjic.StringUtils;
import com.wonders.sjic.entity.HttpClientDTO;
import com.wonders.sjic.entity.InterfaceInfoBo;
import com.wonders.sjic.entity.InterfaceTransferBo;
import com.wonders.sjic.service.ApiService;
import com.wonders.sjic.service.Ret;
import com.wonders.wddc.tiles.sn.SnCreator;
import org.apache.commons.collections.map.HashedMap;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 接口管理
 *
 * @author Wanda
 */
@At("/sjic/jkgl")
@IocBean
public class JkglAct {

    @Inject
    private Dao dao;

    @Inject
    private ApiService apiService;

    /**
     * 首页
     *
     * @return
     */
    @At
    @Ok("jsp:sjic.jkgl.index")
    public Map<String, Object> toIndex() {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        List<InterfaceInfoBo> infoList = dao.query(InterfaceInfoBo.class, Cnd.where("status", "=", "1"));
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (InterfaceInfoBo info : infoList) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            InterfaceTransferBo transferBo = dao.fetch(InterfaceTransferBo.class, info.getId());
            map.put("info", info);
            map.put("detail", transferBo);
            list.add(map);
        }
        result.put("list", list);
        return result;
    }

    /**
     * 编辑&新增
     *
     * @param id
     * @return
     */
    @At
    @Ok("jsp:sjic.jkgl.edit_view")
    public Map<String, Object> edit(String id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Sql sql = Sqls.create("select join_id,join_name from PF_JOIN_INFO");
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection arg0, ResultSet rs, Sql sql) throws SQLException {
                Map<String, String> map = new HashMap<>();
                while (rs.next()) {
                    map.put(rs.getString("join_id"), rs.getString("join_name"));
                }
                return map;
            }
        });
        dao.execute(sql);
        Map<String, String> joinMap = (Map<String, String>) sql.getResult();
        result.put("joinMap", joinMap);
        if (!Strings.isEmpty(id)) {
            InterfaceInfoBo info = dao.fetch(InterfaceInfoBo.class, id);
            result.put("info", info);
        }
        return result;
    }

    /**
     * 失效
     *
     * @param id
     */
    @At
    public void deleteInfo(String id) {
        InterfaceInfoBo infoBo = dao.fetch(InterfaceInfoBo.class, id);
        infoBo.setStatus("0");
        dao.update(infoBo);
    }

    /**
     * @param info
     * @return 保存修改
     */
    @At
    public View toSaveInfo(@Param("::info.") InterfaceInfoBo info) {
        if (Strings.isEmpty(info.getId())) {
            InterfaceTransferBo transferBo = new InterfaceTransferBo();
            info.setId(SnCreator.getInstance().getSN("sjic", 5, "JK"));
            info.setStatus("1");
            info.setUpdateTime(new Date());
            transferBo.setJkId(info.getId());
            transferBo.setTransferSuccess(0);
            transferBo.setTransferSum(0);
            transferBo.setTransferFailed(0);
            dao.insert(transferBo);
            dao.insert(info);
        } else {
            info.setUpdateTime(new Date());
            dao.update(info);
        }
        return new ServerRedirectView("/sjic/jkgl/toIndex");
    }

    /**
     * @param id
     * @return 测试连接
     */
    @At
    @Ok("json")
    public HttpClientDTO checkUrl(String id, String params, HttpServletRequest request) {
        HttpClientDTO status = new HttpClientDTO();
        List<Map<String, Object>> lMaps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        if (!StringUtils.isEmpty(params)) {
            map = (Map<String, Object>) Json.fromJson(params);
        } else {
            map = new HashedMap();
        }
        lMaps.add(map);
        Ret ret = apiService.getResult(id, lMaps, request);
        if ("S".equals(ret.getStatus())) {
            List<String> strings = (List<String>) ret.getdata();
            status.setDetailmsg(strings.get(0));
        } else {
            status.setDetailmsg(ret.getMessage());
        }
        return status;
    }

}
