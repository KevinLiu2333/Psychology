package com.klsw.crosswaylive.controller;

import com.klsw.crosswaylive.service.TbLiveUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by liukun on 2017/5/17.
 */
@Component
public class _BaseContoller {

    @Resource
    protected TbLiveUserService userService;

}
