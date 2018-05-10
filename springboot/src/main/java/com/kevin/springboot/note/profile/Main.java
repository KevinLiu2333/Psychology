//package com.kevin.springboot.note.profile;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
///**
// * Created with IntelliJ IDEA.
// * User: Kevin
// * Date: 2018/4/12
// * Time: 10:38
// */
//public class Main {
//
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
////        设置当前配置文件
//        context.getEnvironment().setActiveProfiles("prod");
////        注册bean配置类
//        context.register(ProfileConfig.class);
////        刷新容器
//        context.refresh();
//        DemoBean demoBean = context.getBean(DemoBean.class);
//        System.out.println(demoBean.getContent());
//        context.close();
//    }
//}
