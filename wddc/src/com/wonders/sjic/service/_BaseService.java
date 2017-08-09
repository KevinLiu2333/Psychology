package com.wonders.sjic.service;

import com.wonders.sjic.entity.InterfaceInfoBo;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/8/7 0007
 * Time: 13:25
 */
public interface  _BaseService {
    Ret execute(InterfaceInfoBo infoBo,Map<String,Object> params);
}
