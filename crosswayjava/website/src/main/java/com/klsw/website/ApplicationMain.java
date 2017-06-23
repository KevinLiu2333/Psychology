package com.klsw.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 程序入口
 */
@Configuration//配置控制
@ComponentScan("com.klsw.website")//组件扫描
@EnableAutoConfiguration//启用自动配置
public class ApplicationMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
