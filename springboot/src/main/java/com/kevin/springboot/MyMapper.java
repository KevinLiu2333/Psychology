package com.kevin.springboot;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 根据需求定义mapper,因为测试项目用的mysql数据库,故额外继承了MySqlMapper
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/20
 * Time: 15:10
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
