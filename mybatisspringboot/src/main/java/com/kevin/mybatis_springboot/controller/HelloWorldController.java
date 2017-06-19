package com.kevin.mybatis_springboot.controller;

import com.kevin.common.mapper.TbCwkMapper;
import com.kevin.common.model.TbCwk;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/13
 * Time: 10:10
 */
@Api(value = "swagger测试", description = "swagger测试用例")
@RestController
public class HelloWorldController {
    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Resource
    private TbCwkMapper cwkMapper;

    @ApiOperation(value = "第一个接口", notes = "第一个接口")
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public Object helloWorld(@RequestParam("userId") @ApiParam(name = "userId", value = "用户Id") Integer userId) {
        TbCwk tbCwk = cwkMapper.selectByPrimaryKey(userId);
        return tbCwk;
    }

    @ApiOperation(value = "下载", notes = "下载apk")
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public String download() {
        return "redirect:WMShua.apk";
    }

}
