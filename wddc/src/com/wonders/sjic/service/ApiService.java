package com.wonders.sjic.service;

import com.wonders.sjic.entity.InterfaceInfoBo;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/8/7 0007
 * Time: 10:23
 */
@IocBean
public class ApiService {

    @Inject
    private Dao dao;

    @Inject
    private WSDLService wsdlService;

    private RESTService restService;

    public Ret getResult(String id, List<Map<String, Object>> paramsList) {
        //返回结果集
        List<String> resultList = new ArrayList<>();
        InterfaceInfoBo infoBo = dao.fetch(InterfaceInfoBo.class, id);
        if (infoBo == null) {
            return Ret.error("接口信息有误");
        }
//        wsdl
        if ("1".equals(infoBo.getType())) {
            for (Map<String, Object> params : paramsList) {
                Ret ret = wsdlService.execute(infoBo, params);
                if (!"S".equals(ret.getStatus())) {
                    resultList.add(ret.getMessage());
                } else {
                    resultList.add((String) ret.getdata());
                }

            }
        }else if("2".equals(infoBo.getType())){
            for (Map<String, Object> params : paramsList) {
                Ret ret = restService.execute(infoBo, params);
                if (!"S".equals(ret.getStatus())) {
                    resultList.add(ret.getMessage());
                } else {
                    resultList.add((String) ret.getdata());
                }

            }
        }

        return null;
    }


}
