//package com.kevin.springboot.mongodb;
//
//import com.kevin.springboot.mongodb.model.Users;
//import com.kevin.springboot.mongodb.service.IuserService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: qxb-810
// * Date: 2018/5/11
// * Time: 9:52
// */
//@Component
//public class MongodbTest implements CommandLineRunner {
//
//    @Resource
//    private IuserService userService;
//
//    @Override
//    public void run(String... strings) throws Exception {
//        try {
//            Users users = new Users("1", "小明", 10);
//            users.setAddress("青岛市");
//            userService.saveUser(users);
//            List<Users> list = userService.listUser();
//            System.out.println("一共这么多人" + list.size());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
