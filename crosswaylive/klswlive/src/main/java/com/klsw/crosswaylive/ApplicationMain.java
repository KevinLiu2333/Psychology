package com.klsw.crosswaylive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liukun on 2017/3/9.
 * 启动类
 */
@Configuration//
@ComponentScan("com.klsw.crosswaylive")//
@EnableAutoConfiguration//
public class ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

}
