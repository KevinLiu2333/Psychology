package com.wonders.wdac.at;

import com.wonders.wddc.suite.data.job.ApiJob;
import com.wonders.wddc.suite.data.job.Ret;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/27 0027
 * Time: 17:06
 */
@At("/api")
@IocBean(fields = "apiJob")
public class ApiAt {


    private ApiJob apiJob;

    @At
    @Ok("json")
    public Ret api() {
        Ret map = apiJob.getParamsInfo("JK00001");
//        Map<String, String> params = new HashMap<>();
//        params.put("organcode", "1");
//        params.put("returntype", "json");
//        params.put("key", "1346");
        Ret result = apiJob.invokeSoap(null,"http://zong.bjchyedu.cn/ids/services/account?wsdl","webservice_method","http://server.ws.cyjw.opendata.com.cn/");
        return result;
    }

}
