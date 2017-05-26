package com.phychology.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kevin on 2016/12/1.
 * 测试
 */
@Controller
public class TestController {
    @RequestMapping(value = "/aa")
    public String test() {
        return "aa";
    }
}
