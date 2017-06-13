package com.kevin.mybatis_springboot.mapper;

import com.kevin.mybatis_springboot.entity.TbCWK;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/13
 * Time: 10:36
 */
@Component
public interface UserMapper{


    @Select("SELECT id,name FROM tb_CWK order by id asc")
            @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    List<TbCWK> getAll();
}
