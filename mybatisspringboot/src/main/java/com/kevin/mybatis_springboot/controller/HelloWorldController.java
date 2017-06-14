package com.kevin.mybatis_springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevin.common.mapper.TbCwkMapper;
import com.kevin.common.model.TbCwk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Resource
    private TbCwkMapper cwkMapper;

    @RequestMapping("hello")
    public Object helloWorld() {
        PageHelper.startPage(1, 5);
        List<TbCwk> list = cwkMapper.selectAll();
        return new PageInfo<TbCwk>(list);
    }

}
