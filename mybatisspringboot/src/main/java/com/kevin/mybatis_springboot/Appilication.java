package com.kevin.mybatis_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/13
 * Time: 10:20
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.kevin.mybatis_springboot")
public class Appilication {
    public static void main(String[] args) {
        SpringApplication.run(Appilication.class);
    }
}
