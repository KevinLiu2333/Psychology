package com.klsw.klswWebService.server.constants;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by liukun on 2017/3/24.
 * ctx参数表
 */
public enum Attributes {
    userInfo,login;
    public static final List<String> LIST = Lists.newArrayList("login","username","usertype");
}
