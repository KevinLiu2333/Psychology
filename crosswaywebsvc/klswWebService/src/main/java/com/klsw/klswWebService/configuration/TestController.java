package com.klsw.klswWebService.configuration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/17
 * Time: 9:08
 */
@RestController
@RequestMapping("test")
public class TestController {


    @RequestMapping
    public String test(@RequestParam(value = "id",required = false) String id, @RequestParam(value = "name",required = false) String name) {
        if(id==null){
            return "12345";
        }
        return id+name;
    }

}
