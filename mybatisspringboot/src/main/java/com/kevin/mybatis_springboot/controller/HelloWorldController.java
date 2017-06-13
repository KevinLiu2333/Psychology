package com.kevin.mybatis_springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/13
 * Time: 10:10
 */
@RestController
public class HelloWorldController {

    @RequestMapping("hello")
    public String helloWorld() {
        return "helloWorld!";
    }

}
