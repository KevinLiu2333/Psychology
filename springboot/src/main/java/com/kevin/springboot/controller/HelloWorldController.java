package com.kevin.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/18
 * Time: 17:18
 */
@Controller
@RequestMapping
public class HelloWorldController {


    @RequestMapping("hello")
    public String helloWorld(){
        return  "hello";
    }
}
