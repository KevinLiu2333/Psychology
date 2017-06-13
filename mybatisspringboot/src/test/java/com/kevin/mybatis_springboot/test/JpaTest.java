package com.kevin.mybatis_springboot.test;

import com.kevin.mybatis_springboot.entity.TbCWK;
import com.kevin.mybatis_springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/13
 * Time: 11:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void userTest(){
        List<TbCWK> list =  userMapper.getAll();
        System.out.println(list.size());
    }
}
