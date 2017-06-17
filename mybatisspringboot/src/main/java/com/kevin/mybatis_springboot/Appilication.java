package com.kevin.mybatis_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/13
 * Time: 10:20
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.kevin.mybatis_springboot")
@MapperScan("com.kevin.common.mapper")
@EnableSwagger2
public class Appilication {
    public static void main(String[] args) {
        SpringApplication.run(Appilication.class);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kevin.mybatis_springboot.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("KLSW威客在线接口文档")
                .description("更多接口描述信息持续更新中。。。")
                .termsOfServiceUrl("http://www.klsw.com/")
                .version("1.0")
                .build();
    }
}
