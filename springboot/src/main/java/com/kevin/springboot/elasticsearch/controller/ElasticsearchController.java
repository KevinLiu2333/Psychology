package com.kevin.springboot.elasticsearch.controller;

import com.google.gson.Gson;
import com.kevin.springboot.elasticsearch.model.Employee;
import com.kevin.springboot.elasticsearch.service.EmployeeRespository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: qxb-810
 * Date: 2018/5/11
 * Time: 16:16
 */
@RestController
@RequestMapping("es")
public class ElasticsearchController {

    @Resource
    private EmployeeRespository er;

    //增加
    @RequestMapping("/add")
    public String add() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("xuxu");
        employee.setAge(26);
        employee.setAbout("i am in peking");
        er.save(employee);
        return "success";
    }

    //删除
    @RequestMapping("/delete")
    public String delete() {
        er.delete("1");
        return "success";
    }

    //局部更新
    @RequestMapping("/update")
    public String update() {
        Employee employee = er.queryEmployeeById("1");
        employee.setFirstName("哈哈");
        er.save(employee);
        System.err.println("update a obj");
        return "success";
    }

    //查询
    @RequestMapping("/query")
    public Employee query() {
        Employee accountInfo = er.queryEmployeeById("1");
        System.err.println(new Gson().toJson(accountInfo));
        return accountInfo;
    }

}




