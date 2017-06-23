package com.klsw.crosswaylive.server.constants;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by liukun on 2017/3/24.
 * 类型
 */
public enum Types {
    login, chat, raise, convert,startliving,joinliving,startinteracting,shareopern,paint,exit;
    public static final List<String> LIST = Lists.newArrayList("login", "chat", "raise", "convert","startliving","joinliving","startinteracting","exit","shareopern","paint");

}
