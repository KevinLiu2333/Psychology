package com.kevin.nutzbook.module;

import com.kevin.nutzbook.bean.User;
import com.kevin.nutzbook.bean.UserProfile;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.DaoException;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.util.Daos;
import org.nutz.img.Images;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.Scope;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/14
 * Time: 9:45
 */
@IocBean
@At("/user/profile")
@Filters(@By(type = CheckSession.class, args = {"me", "/"})) // 检查当前Session是否带me这个属性
public class UserProfileModule extends BaseModule {
    private static final Log log = Logs.get();

    public UserProfile get(@Attr(scope = Scope.SESSION, value = "me") int userId) {
        UserProfile profile = Daos.ext(dao, FieldFilter.locked(UserProfile.class, "avatar")).fetch(UserProfile.class, userId);
        if (profile == null) {
            profile = new UserProfile();
            profile.setUserId(userId);
            profile.setCreateTime(new Date());
            profile.setUpdateTime(new Date());
            dao.insert(profile);
        }
        return profile;
    }

    @At
    @AdaptBy(type = JsonAdaptor.class)//对前端传入的json数据进行处理
    @Ok("void")
    public void update(@Param("..") UserProfile profile, @Attr(scope = Scope.SESSION, value = "me") int userId) {
        if (profile == null)
            return;
        profile.setUserId(userId);//修正userId,防止恶意修改其他用户的信息
        profile.setUpdateTime(new Date());
        profile.setAvatar(null); // 不准通过这个方法更新
        UserProfile old = get(userId);
        // 检查email相关的更新
        if (old.getEmail() == null) {
            // 老的邮箱为null,所以新的肯定是未check的状态
            profile.setEmailChecked(false);
        } else {
            if (profile.getEmail() == null) {
                profile.setEmail(old.getEmail());
                profile.setEmailChecked(old.isEmailChecked());
            } else if (!profile.getEmail().equals(old.getEmail())) {
                // 设置新邮箱,果断设置为未检查状态
                profile.setEmailChecked(false);
            } else {
                profile.setEmailChecked(old.isEmailChecked());
            }
        }
        Daos.ext(dao, FieldFilter.create(UserProfile.class, null, "avatar", true)).update(profile);
    }

    @At
    @Aop(TransAop.READ_COMMITTED)//事务控制
    public Object delete(@Param("id") int id, @Attr("me") int me) {
        if (me == id) {
            return new NutMap().setv("ok", false).setv("msg", "不能删除当前用户!!");
        }
        dao.delete(User.class, id); // 再严谨一些的话,需要判断是否为>0
        dao.clear(UserProfile.class, Cnd.where("userId", "=", me));
        return new NutMap().setv("ok", true);
    }

    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp/user_avatar", "8192", "utf-8", "20000", "102400"})
    @POST
    @Ok(">>:/user/profile")
    @At("/avatar")
    public void uploadAvatar(@Param("file") TempFile tf,
                             @Attr(scope = Scope.SESSION, value = "me") int userId,
                             AdaptorErrorContext err) {
        String msg = null;
        if (err != null && err.getAdaptorErr() != null) {
            msg = "文件大小不符合规定";
        } else if (tf == null) {
            msg = "空文件";
        } else {
            UserProfile profile = get(userId);
            try {
                BufferedImage image = Images.read(tf.getFile());
                image = Images.zoomScale(image, 128, 128, Color.WHITE);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                Images.writeJpeg(image, out, 0.8f);
                profile.setAvatar(out.toByteArray());
                dao.update(profile, "^avatar$");
            } catch (DaoException e) {
                log.info("System Error", e);
                msg = "系统错误";
            } catch (Throwable e) {
                msg = "图片格式错误";
            }
        }

        if (msg != null)
            Mvcs.getHttpSession().setAttribute("upload-error-msg", msg);
    }

    @Ok("raw:jpg")
    @At("/avatar")
    @GET
    public Object readAvatar(@Attr(scope=Scope.SESSION, value="me")int userId, HttpServletRequest req) throws SQLException {
        UserProfile profile = Daos.ext(dao, FieldFilter.create(UserProfile.class, "^avatar$")).fetch(UserProfile.class, userId);
        if (profile == null || profile.getAvatar() == null) {
            return new File(req.getServletContext().getRealPath("/rs/user_avatar/none.jpg"));
        }
        return profile.getAvatar();
    }

    @At("/")
    @GET
    @Ok("jsp:jsp.user.profile")
    public UserProfile index(@Attr(scope=Scope.SESSION, value="me")int userId) {
        return get(userId);
    }
}













