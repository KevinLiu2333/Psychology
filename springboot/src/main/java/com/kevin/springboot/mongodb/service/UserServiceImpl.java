package com.kevin.springboot.mongodb.service;

import com.kevin.springboot.mongodb.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: qxb-810
 * Date: 2018/5/11
 * Time: 9:00
 */
@Component("userService")
public class UserServiceImpl implements IuserService {

    @Autowired
    MongoOperations mongoTemplate;

    @Override
    public void saveUser(Users users) {
        mongoTemplate.save(users);
    }

    @Override
    public Users findUserByName(String name) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), Users.class);
    }

    @Override
    public void removeUser(String name) {
        mongoTemplate.remove(new Query(Criteria.where("name").is(name)), Users.class);

    }

    @Override
    public void updateUser(String name, String key, String value) {
        mongoTemplate.updateFirst(new Query(Criteria.where("name").is(name)), Update.update(key, value), Users.class);
    }

    @Override
    public List<Users> listUser() {
        return mongoTemplate.findAll(Users.class);
    }
}





