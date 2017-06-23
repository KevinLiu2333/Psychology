package com.klsw.klswWebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 程序入口
 */
@SpringBootConfiguration
@ComponentScan("com.klsw")//组件扫描
@EnableAutoConfiguration//启用自动配置
@EnableTransactionManagement
@EnableSwagger2
public class ApplicationMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationMain.class, args);
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.klsw.klswWebService.controller"))
                .paths(PathSelectors.any())
                .build();
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

