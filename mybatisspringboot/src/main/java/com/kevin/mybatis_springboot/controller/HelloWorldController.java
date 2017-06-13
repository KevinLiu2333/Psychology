package com.kevin.mybatis_springboot.controller;

import com.kevin.mybatis_springboot.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/13
 * Time: 10:10
 */
@RestController
public class HelloWorldController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("hello")
    public Object helloWorld() {
        return userMapper.getAll();
    }

}
