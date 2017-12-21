package com.wonders.sjic.platform;

import java.util.List;
import java.util.Map;

/**
 * 接口Proccessor接口
 * User: Kevin
 * Date: 2017/8/15
 * Time: 16:46
 */
public interface JkProcessor {

    List<Map<String, Object>> getParams();

    String execute(Object arg0);
}
