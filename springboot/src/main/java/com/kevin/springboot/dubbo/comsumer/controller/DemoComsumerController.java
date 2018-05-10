//package com.kevin.springboot.dubbo.comsumer.controller;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.kevin.springboot.dubbo.provider.TestDubboService;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created with IntelliJ IDEA.
// * User: Kevin
// * Date: 2018/4/20
// * Time: 17:06
// */
//@RestController
//public class DemoComsumerController {
//    @Reference(version = "1.0.0",
//            application = "${dubbo.application.id}",
//            url = "dubbo://localhost:12345")
//    private TestDubboService testDubboService;
//
//    @RequestMapping("/sayHello")
//    public String sayHello(@RequestParam String name) {
//        return testDubboService.getName(name);
//    }
//}
