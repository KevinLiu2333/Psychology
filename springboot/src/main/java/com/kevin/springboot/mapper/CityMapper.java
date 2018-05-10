package com.kevin.springboot.mapper;


import com.kevin.springboot.MyMapper;
import com.kevin.springboot.model.City;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper extends MyMapper<City> {
}