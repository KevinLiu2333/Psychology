package com.wonders.sjic.service;

import com.wonders.sjic.entity.InterfaceInfoBo;
import com.wonders.sjic.entity.InterfaceLogBo;
import com.wonders.sjic.entity.InterfaceTransferBo;
import org.nutz.dao.Dao;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

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
public class RESTApiService implements _BaseService {

    @Inject
    private Dao dao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public Ret execute(InterfaceInfoBo infoBo, Map<String, Object> params) {
        InterfaceLogBo logBo = new InterfaceLogBo();
        Ret ret = null;
        InterfaceTransferBo transferBo = dao.fetch(InterfaceTransferBo.class, infoBo.getId());
        if (transferBo == null) {
            transferBo = new InterfaceTransferBo();
            transferBo.setJkId(infoBo.getId());
            transferBo.setTransferFailed(0);
            transferBo.setTransferSuccess(0);
            transferBo.setTransferSum(0);
            dao.insert(transferBo);
        }
        transferBo.setTransferSum(transferBo.getTransferSum()+1);
//        日志记录:接口id
        logBo.setJkId(infoBo.getId());
//        日志记录:日志id
        logBo.setId(UUID.randomUUID().toString());
//        日志记录:请求参数
        logBo.setReqMsg(Json.toJson(params));
        URL url = null;
        String responseString = null;
        String errMsg;
        String address = infoBo.getAddress();
        InterfaceTransferBo finalTransferBo = transferBo;
        try {
            url = new URL(address);
            Date date = new Date();
//            日志记录:开始时间
            logBo.setStartTime(sdf.format(date));
            Response response = Http.post2(address, params, 3 * 1000);
//            日志记录:通信时长
            logBo.setTransferTime(String.valueOf(System.currentTimeMillis() - date.getTime()));
            responseString = response.getContent("utf-8");
            logBo.setResMsg(responseString);
            int code = response.getStatus();
            if (code == 200 || code == 405 || code == 415) {
                logBo.setTransferFlag("1");
                finalTransferBo.setTransferSuccess(finalTransferBo.getTransferSuccess()+1);
                ret = Ret.success(responseString);
            } else {
                logBo.setTransferFlag("0");
                finalTransferBo.setTransferSuccess(finalTransferBo.getTransferFailed()+1);
                if (code >= 500 && code < 600) {
                    errMsg = "连接成功，但访问资源返回有误";
                } else if (code == 404) {
                    errMsg = "连接成功，但未找到资源";
                } else {
                    errMsg = "未知异常";
                }
                logBo.setErrMsg(errMsg);
                ret = Ret.error(errMsg);
            }
        } catch (MalformedURLException e) {
        	finalTransferBo.setTransferSuccess(finalTransferBo.getTransferFailed()+1);
            logBo.setTransferFlag("0");
            errMsg = "接口地址不符合url规范";
            logBo.setErrMsg(errMsg);
            ret = Ret.error(errMsg);
        }finally{
            Trans.exec(new Atom(){
                public void run() {
					dao.update(finalTransferBo);
                    dao.insert(logBo);
                }
            });
        }
        return ret;
    }
}
