package com.kevin.springboot.interfaces;

import com.kevin.springboot.model.City;
import com.kevin.springboot.service.CityService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 简单的提供restful服务的接口
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/19
 * Time: 13:40
 */
@RestController
@RequestMapping("api")
public class RestfulApi {

    @Resource
    private CityService cityService;


    /**
     * get类型的rest接口,返回city列表,默认数据类型为json
     *
     * @return citys
     */
    @RequestMapping(value = "city", method = RequestMethod.GET)
    public List<City> findAllActors() {
        try {
            return cityService.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    /**
//     * 常用restfulApi的格式,添加路径变量参数
//     *
//     * @param name 名字
//     * @return city
//     */
//    @RequestMapping(value = "city/{name}")
//    public City findCity(@PathVariable("name") String name) {
//        City city = new City();
//        city.setName(name);
//        try {
//            city = cityService.selectOne(city);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return city;
//    }

    /**
     * 插入城市
     *
     * @param city 城市
     * @return 插入结果
     */
    @RequestMapping(value = "city", method = RequestMethod.PUT)
    public String putCity(City city) {
        String result;
        try {
            cityService.insert(city);
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
            result = "error";
        }
        return result;
    }

    /**
     * 根据城市id查询城市信息
     *
     * @param id id
     * @return 信息
     */
    @RequestMapping(value = "city/{id}")
    public City findCity(@PathVariable("id") Integer id) {
        return cityService.findCityById(id);
    }

}
