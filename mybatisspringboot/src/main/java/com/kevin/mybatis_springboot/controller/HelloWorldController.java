package com.kevin.mybatis_springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevin.common.mapper.TbCwkMapper;
import com.kevin.common.model.TbCwk;
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
    private TbCwkMapper cwkMapper;

    @RequestMapping("hello")
    public Object helloWorld() {
        PageHelper.startPage(1,5);
        List<TbCwk> list  =  cwkMapper.selectAll();
        PageInfo<TbCwk> pageInfo = new PageInfo<TbCwk>(list);
        return pageInfo;
    }

}
