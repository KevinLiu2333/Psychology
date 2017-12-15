package com.kevin.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * springboot启动类
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/18
 * Time: 14:17
 */
@SpringBootApplication
@EnableAutoConfiguration
//扫描注解
@ComponentScan("com.kevin.springboot")
//mapper扫描注解,隶属于mybatis
@MapperScan("com.kevin.springboot.mapper")
//swagger2注解
@EnableSwagger2
//支持缓存
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kevin.springboot.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("在线接口文档信息测试")
                .description("更多接口描述信息持续更新中。。。")
                .version("1.0")
                .build();
    }
}
