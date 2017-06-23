package com.klsw.wk.hades;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用程序主入口
 * @author liulixi
 * @version 2017年6月16日10:20:15
 */
@SpringBootApplication
@MapperScan(basePackages = "com.klsw.wk.hades.dao")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}