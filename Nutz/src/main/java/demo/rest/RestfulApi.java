package demo.rest;

import org.nutz.mvc.annotation.*;

/**
 * 默认支持get post delete update四种方法
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/24 0024
 * Time: 16:57
 */
public class RestfulApi {

    @At("/user/?")
    @GET
    public User getUser(int userId) {
        return null;
    }

    @At("/user/?")
    @POST
    public User updateUser(int userId, @Param("..") User user) {
        return null;
    }

    @At("/user/?")
    @DELETE
    public void deleteUser(int userId) {
        // TODO 这里是实现代码
    }

    // 任意自选方法
    @At(value = "/user/?", methods = "anything")
    public void fuckUser(int userId) {
        // TODO 这里是实现代码
    }
}





