package com.kevin.springboot.service;

import com.kevin.springboot.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/23
 * Time: 14:08
 */
@Service
public class CityService extends _BaseService<City>{
    private static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 简单的redis缓存方法
     * 获取城市逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     */
    public City findCityById(Integer id) {
        // 从缓存中获取城市信息
        String key = "city_" + id;
        ValueOperations operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = (City) operations.get(key);

            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString());
            return city;
        }

        // 从 DB 中获取城市信息
        City city = null;
        try {
            city = super.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 插入缓存 设置缓存时间
        operations.set(key, city, 1000000, TimeUnit.SECONDS);
        LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());

        return city;
    }
}
