package com.klsw.piano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by liukun on 2016/10/31.
 * 测试
 */
@Configuration//
@ComponentScan("com.klsw.piano")//
@EnableAutoConfiguration//
@EnableScheduling
public class PianoApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(PianoApplicationMain.class, args);
    }

}
