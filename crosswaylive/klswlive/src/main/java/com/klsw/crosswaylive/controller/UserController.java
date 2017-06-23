package com.klsw.crosswaylive.controller;

import com.klsw.common.live.model.*;
import com.klsw.crosswaylive.service.TbLiveRoomService;
import com.klsw.crosswaylive.service.TeacherService;
import com.klsw.crosswaylive.service.VisitorService;
import com.klsw.crosswaylive.util.Constants;
import com.klsw.crosswaylive.util.MyFileUtils;
import com.klsw.crosswaylive.util._StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by liukun on 2017/3/11.
 * 用户相关
 */
@RestController
public class UserController extends _BaseContoller {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private VisitorService visitorService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private MyFileUtils myFileUtils;
    @Resource
    private TbLiveRoomService liveRoomService;


    /**
     * @param username 用户名
     * @return Ret
     */
    @RequestMapping(value = "/getPhoneCaptcha")
    public Ret getPhoneCaptcha(@RequestParam("username") String username) {
        try {
            return userService.getPhoneCaptcha(username);
        } catch (Exception e) {
            log.error("msg", e);
            return Ret.error("获取验证码失败");
        }
    }

    /**
     * @param username     用户名
     * @param password     密码
     * @param phoneCaptcha 加密后的验证码
     * @param type         用户类型
     * @return Ret
     */
    @RequestMapping(value = "/regist")
    public Ret regist(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam("phoneCaptcha") String phoneCaptcha,
                      @RequestParam("type") String type) {
        if (StringUtils.isBlank(username)) {
            return Ret.warn("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return Ret.warn("密码不能为空");
        }
        if (StringUtils.isBlank(phoneCaptcha)) {
            return Ret.warn("手机验证码不能为空");
        }
        try {
            return userService.regist(username, password, phoneCaptcha, type);
        } catch (Exception e) {
            log.error("msg", e);
            return Ret.error("注册失败");
        }

    }

    /**
     * 用户登录
     *
     * @param timestamp 时间戳
     * @param username  用户名
     * @param password  加密后的密码
     * @return Ret
     */
    @RequestMapping(value = "/login")
    public Ret login(@RequestParam("timestamp") String timestamp,
                     @RequestParam("username") String username,
                     @RequestParam("password") String password) {
        try {
            return userService.userLogin(timestamp, username, password);
        } catch (Exception e) {
            log.error("msg", e);
            return Ret.error("登录失败");
        }
    }

    /**
     * 获取token(登录前的操作)
     *
     * @param username 用户名
     * @return Ret
     */
    @RequestMapping(value = "/getToken")
    public Ret getToken(@RequestParam("username") String username) {
        try {
            TbLiveuser liveuser = userService.findByName(username);
            if (liveuser == null) {
                return Ret.warn("用户名不存在");
            }
            String token = _StringUtils.getToken(liveuser.getPassword());
            userService.saveToken(username, token);
            return Ret.success(token);
        } catch (Exception e) {
            log.error("msg", e);
            return Ret.error("登录失败");
        }
    }

    /**
     * @param sn 手机sn
     * @return 结果
     */
    @RequestMapping(value = "getVisitorAccount")
    public Ret getVisitorAccount(@RequestParam("sn") String sn) {
        if (StringUtils.isBlank(sn)) {
            return Ret.warn("请输入设备sn");
        }
        try {
            TbLivevisitor livevisitor = new TbLivevisitor();
            String username;
            livevisitor.setSn(sn);
            livevisitor = visitorService.selectOne(livevisitor);
            if (livevisitor == null) {
                username = _StringUtils.getVisitor();
                livevisitor = new TbLivevisitor();
                livevisitor.setUsername(username);
                while (visitorService.select(livevisitor).size() > 0) {
                    username = _StringUtils.getVisitor();
                    livevisitor.setUsername(username);
                }
                livevisitor.setSn(sn);
                visitorService.insert(livevisitor);
            }
            return Ret.success("visitor", livevisitor);
        } catch (Exception e) {
            log.error("msg", e);
        }
        return Ret.error("获取失败");
    }

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    @RequestMapping(value = "getUserInfo")
    public Ret getUserInfo(@RequestParam("userId") Integer userId) {
        try {
            TbLiveuser user = userService.selectByPrimaryKey(userId);
            if (user == null) {
                return Ret.error("用户不存在");
            }
            //如果为教师,增加教师相关信息
            if ("tea".equals(user.getType())) {
                TbLiveteacher teacher = teacherService.selectByUserId(userId);
                if (teacher == null) {
                    teacher = new TbLiveteacher();
                    teacher.setUserid(userId);
                    teacher.setIsauth(0);
                    teacherService.insert(teacher);
                }
                user.setTeacherInfo(teacher);
                TbLiveroom tbLiveroom = new TbLiveroom();
                tbLiveroom.setStatus(0);
                tbLiveroom.setAnchorid(userId);
                user.setOffCourse(liveRoomService.selectCount(tbLiveroom));
                tbLiveroom.setStatus(2);
                user.setCompletedCouse(liveRoomService.selectCount(tbLiveroom));
            } else {
                user.setCompletedCouse(liveRoomService.selectAppointLiveroomCount(userId, 2));
                user.setOffCourse(liveRoomService.selectAppointLiveroomCount(userId, 0));
            }
            return Ret.success(user);
        } catch (Exception e) {
            log.info("msg", e);
            return Ret.error("未查到用户信息");
        }
    }

    /**
     * @param userId        用户id
     * @param avatar        头像文件
     * @param details       个人介绍
     * @param nickname      昵称
     * @param sex           性别
     * @param birthday      生日
     *                      以下仅限教师
     * @param region        地区
     * @param graduatedUniv 毕业院校
     * @return 修改结果
     */
    @RequestMapping(value = "modifyUserInfo")
    public Ret getUserInfo(@RequestParam("userId") Integer userId,
                           @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                           @RequestParam(value = "pianoGrade", required = false) Integer pianoGrade,
                           @RequestParam(value = "details", required = false) String details,
                           @RequestParam(value = "nickname", required = false) String nickname,
                           @RequestParam(value = "sex", required = false) String sex,
                           @RequestParam(value = "birthday", required = false) String birthday,
                           @RequestParam(value = "region", required = false) String region,
                           @RequestParam(value = "graduatedUniv", required = false) String graduatedUniv) {
        try {
            TbLiveuser liveuser = userService.selectByPrimaryKey(userId);
            if (liveuser == null) {
                return Ret.error("用户不存在");
            }
            if (avatar != null) {
                MultipartFile[] files = new MultipartFile[]{avatar};
                String packagePath = "user/avator/" + liveuser.getUsername() + "/";
                Ret ret = myFileUtils.uploadfile(files, "image", packagePath);
                if ("S".equals(ret.getStatus())) {
                    liveuser.setAvatar(ret.getdata().toString());
                }
            }
            if (details != null) {
                liveuser.setDetails(details);
            }
            if (!StringUtils.isBlank(nickname)) {
                liveuser.setNickname(nickname);
            }
            if (!StringUtils.isBlank(sex)) {
                liveuser.setSex(sex);
            }
            if (!StringUtils.isBlank(birthday)) {
                Date birth = DateUtils.parseDate(birthday, Constants.parsePattern);
                liveuser.setBirthday(birth);
            }
            if (pianoGrade != null) {
                liveuser.setPianograde(pianoGrade);
            }
            if ("tea".equals(liveuser.getType())) {
                TbLiveteacher teacher = teacherService.selectByUserId(userId);
                boolean flag = false;
                if (teacher == null) {
                    flag = true;
                    teacher = new TbLiveteacher();
                    teacher.setUserid(userId);
                }
                if (!StringUtils.isBlank(region)) {
                    teacher.setRegion(region);
                }
                if (!StringUtils.isBlank(graduatedUniv)) {
                    teacher.setGraduatedUniv(graduatedUniv);
                }
                if (flag) {
                    teacherService.insert(teacher);
                } else {
                    teacherService.updateByPrimaryKey(teacher);
                }
            }
            userService.updateByPrimaryKeySelective(liveuser);
            return Ret.success("修改成功");
        } catch (Exception e) {
            log.error("msg", e);
        }
        return Ret.error("修改失败");
    }
}


