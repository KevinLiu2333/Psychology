package com.kevin.mybatis_springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevin.mybatis_springboot.entity.TbCWK;
import com.kevin.mybatis_springboot.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
        PageHelper.startPage(1,5);
        List<TbCWK> list  =  userMapper.getAll();
        PageInfo<TbCWK> pageInfo = new PageInfo<TbCWK>(list);
        return pageInfo;
    }

}
