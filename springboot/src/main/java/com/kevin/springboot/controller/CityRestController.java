package com.kevin.springboot.controller;


import com.kevin.springboot.mapper.CityMapper;
import com.kevin.springboot.model.City;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class CityRestController {

    @Resource
    private CityMapper cityMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public City findOneCity(@PathVariable("id") Integer id) {

        ValueOperations ops = redisTemplate.opsForValue();
        String key = "City" + id;
        City city = (City) ops.get(key);
        if (city == null) {
            city = cityMapper.selectByPrimaryKey(id);
            ops.set(key,city);
            System.out.println("数据库中获取" + key);
        } else {
            System.out.println("缓存中获取" + key);
        }
        return city;
    }

}
