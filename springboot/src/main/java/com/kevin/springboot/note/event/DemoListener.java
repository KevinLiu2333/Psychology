package com.kevin.springboot.note.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/4/12
 * Time: 11:16
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {


    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
//        传入事件,自定义处理
        String msg = demoEvent.getMsg();
        System.out.println("我(bean-demoListener)接收到了bean-demoPublisher发布的消息:" + msg);
    }
}









