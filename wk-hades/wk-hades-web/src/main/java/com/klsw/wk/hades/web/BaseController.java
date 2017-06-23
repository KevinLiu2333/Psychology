package com.klsw.wk.hades.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.Page;
import com.klsw.wk.hades.util.Pager;

/**
 * Controller 基类
 * @author liulixi
 * @version 2017年6月15日17:48:11
 */
public abstract class BaseController {

    protected Logger LOG = LoggerFactory.getLogger(this.getClass());
    public Map<String, Object> map = new HashMap<String, Object>();

    protected void setResponseSuccessMessage(Map<String,Object> map,Object data,String msg){
        map.put("success", Boolean.TRUE);
        map.put("data", data);
        map.put("message", msg);
    }

    protected void setResponseFailedMessage(Map<String,Object> map,Object data,String msg){
        map.put("success", Boolean.FALSE);
        map.put("data", data);
        map.put("message", msg);
    }

    protected void setPagerParam(Map<String,Object> parameters, Pager pager,Page<Object> page){
        parameters.put("isSuccess", Boolean.TRUE);
        parameters.put("nowPage", pager.getNowPage());
        parameters.put("pageSize", pager.getPageSize());
        parameters.put("pageCount", page.getPages());
        parameters.put("recordCount", page.getTotal());
        parameters.put("startRecord", page.getStartRow());
    }
}
