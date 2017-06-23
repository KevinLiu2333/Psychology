package com.klsw.weikesite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * 
 */
@Configuration//
@ComponentScan("com.klsw.weikesite")//
@EnableAutoConfiguration//
public class WKApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(WKApplicationMain.class, args);
	}

}
