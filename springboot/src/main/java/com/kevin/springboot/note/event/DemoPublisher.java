package com.kevin.springboot.note.event;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/4/12
 * Time: 11:19
 */
@Component
public class DemoPublisher {

    @Resource
    private ApplicationContext applicationContext;

//    发布事件
    public void publish(String msg) {
        applicationContext.publishEvent(new DemoEvent(this, msg));
    }
}
