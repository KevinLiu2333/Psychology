package com.kevin.springboot.mongodb.service;

import com.kevin.springboot.mongodb.model.Users;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: qxb-810
 * Date: 2018/5/11
 * Time: 8:56
 */
public interface IuserService {
    void saveUser(Users users);

    Users findUserByName(String name);

    void removeUser(String name);

    void updateUser(String name, String key, String value);

    List<Users> listUser();


}









