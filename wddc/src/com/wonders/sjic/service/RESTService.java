package com.wonders.sjic.service;

import com.wonders.sjic.entity.InterfaceInfoBo;
import com.wonders.sjic.entity.InterfaceLogBo;
import org.nutz.dao.Dao;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/8/7 0007
 * Time: 11:08
 */
@IocBean
public class RESTService implements _BaseService {

    @Inject
    private Dao dao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public Ret execute(InterfaceInfoBo infoBo, Map<String, Object> params) {
        InterfaceLogBo logBo = new InterfaceLogBo();
        logBo.setJkId(infoBo.getId());
        logBo.setId(UUID.randomUUID().toString());
        logBo.setReqMsg(Json.toJson(params));
        URL url = null;
        String responseString = null;
        String errMsg;
        String address = infoBo.getAddress();
        try {
            url = new URL(address);
            Date date = new Date();
            logBo.setStartTime(sdf.format(date));
            Response response = Http.post2(address, params, 3 * 1000);
            logBo.setTransferTime(String.valueOf(System.currentTimeMillis() - date.getTime()));
            responseString = response.getContent("utf-8");
            logBo.setResMsg(responseString);
            int status = response.getStatus();
            if (status == 200) {
                logBo.setTransferFlag("1");
                return Ret.success(responseString);
            } else {
                logBo.setTransferFlag("0");

                return null;
            }
        } catch (MalformedURLException e) {
            errMsg = "接口地址不符合url规范";
            logBo.setErrMsg(errMsg);
            return Ret.error("接口地址不符合url规范");
        }

    }
}
