package com.kevin.springboot.mongodb.controller;

import com.kevin.springboot.mongodb.model.Users;
import com.kevin.springboot.mongodb.service.IuserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: qxb-810
 * Date: 2018/5/11
 * Time: 11:31
 */
@RestController
public class MongoDBController {
    @Resource
    private IuserService userService;

    @RequestMapping(value = "/mongo/user/get/{name}", method = RequestMethod.GET)
    public Users getUser(@PathVariable String name) {
        Users users = userService.findUserByName(name);
        return users;
    }

    @RequestMapping(value = "mongo/user/set/{name}", method = RequestMethod.GET)
    public String setUser(@PathVariable String name) {
        Users users = new Users(UUID.randomUUID().toString(), name, 18);
        userService.saveUser(users);
        return "ok";
    }
}
