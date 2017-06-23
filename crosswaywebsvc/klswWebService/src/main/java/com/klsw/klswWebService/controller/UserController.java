package com.klsw.klswWebService.controller;

import com.klsw.apiCommon.api.model.*;
import com.klsw.klswWebService.service.*;
import com.klsw.klswWebService.util.Constants;
import com.klsw.klswWebService.util.MyFileUtils;
import com.klsw.klswWebService.util.PasswdEncryption;
import com.klsw.klswWebService.util._StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: UserController
 *
 * @author LiuKun
 * @Description: 用户注册登陆相关
 * @date 2016年8月26日
 */
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private VisitorService visitorService;

    @Resource
    private TbCwkService tbCwkService;

    @Resource
    private TbPhonecaptchaService tbPhonecaptchaService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private MyFileUtils myFileUtils;

    @Resource
    private TbLiveRoomService liveRoomService;

    /**
     * 接口：通过手机号验证用户是否存在
     *
     * @param phoneNum 手机号
     * @return
     * @author HKJ
     */
    @RequestMapping(value = "/verifyIdentity")
    @ResponseBody
    public Ret verifyIdentity(@RequestParam("phoneNum") String phoneNum) {
        if (StringUtils.isBlank(phoneNum)) {
            Ret.error("手机号不能为空");
        }
        TbCwk tbCwk = new TbCwk();
        tbCwk.setName(phoneNum);
        try {
            tbCwk = tbCwkService.selectOne(tbCwk);
            if (tbCwk == null) {
                return Ret.error("该手机没有注册过");
            } else {
                return Ret.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return Ret.warn("本次请求发生异常，请稍后重试！");
        }
    }

    @RequestMapping("checkPhoneCaptcha")
    @ResponseBody
    public Ret checkPhoneCaptcha(@RequestParam("username") String username,
                                 @RequestParam("phoneCaptcha") String captcha) {
        try {
            TbCwkphonecaptcha cwkphonecaptcha = new TbCwkphonecaptcha();
            cwkphonecaptcha.setCaptcha(PasswdEncryption.storeCaptcha(username,captcha));
            cwkphonecaptcha.setPhonenum(username);
            cwkphonecaptcha = tbPhonecaptchaService.selectOne(cwkphonecaptcha);
            if (cwkphonecaptcha == null || (new Date().getTime()) - cwkphonecaptcha.getCreatetime().getTime() > 1200000) {
                return Ret.error("验证码错误或已过期");
            }
            return Ret.success();
        } catch (Exception e) {
            return Ret.error("验证码错误或已过期");
        }
    }

    /**
     * 接口：通过手机号和手机验证码进行身份验证
     *
     * @param phoneNum     手机号
     * @param phoneCaptcha 验证码
     * @return
     * @author HKJ
     */
    @RequestMapping(value = "/security")
    @ResponseBody
    public Ret security(@RequestParam("phoneNum") String phoneNum,
                        @RequestParam("phoneCaptcha") String phoneCaptcha) {
        if (StringUtils.isBlank(phoneNum)) {
            return Ret.error("手机号不能为空");
        }
        if (StringUtils.isBlank(phoneCaptcha)) {
            return Ret.error("验证码不能为空");
        }
        TbCwk tbCwk = new TbCwk();
        tbCwk.setName(phoneNum);
        try {
            tbCwk = tbCwkService.selectOne(tbCwk);
            if (tbCwk == null) {
                return Ret.error("没有该手机用户");
            }

            if (!tbPhonecaptchaService.selectBystoreCaptcha(phoneCaptcha, phoneNum)) {
                return Ret.error("验证码错误或验证码过期");
            }
            return Ret.success("S", "身份验证通过", null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return Ret.error("发生异常，请稍后重试!");
        }
    }

    /**
     * 接口：用于绑定手机时获取验证码
     *
     * @param phoneNum 手机号
     * @return
     */
    @RequestMapping(value = "receiveCaptcha")
    @ResponseBody
    public Ret receiveCaptcha(@RequestParam("phoneNum") String phoneNum) {
        try {
            Ret ret = tbCwkService.receiveCaptcha(phoneNum);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return Ret.error("获取验证码失败");
        }
    }


    /**
     * 接口：用于重置密码时获取手机验证码
     *
     * @param phoneNum 手机号
     * @return
     */
    @RequestMapping(value = "getCaptcha")
    @ResponseBody
    public Ret getCaptcha(@RequestParam("phoneNum") String phoneNum) {
        try {
            Ret ret = tbCwkService.getCaptcha(phoneNum);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return Ret.error("获取验证码失败");
        }
    }

    /**
     * @param username 用户名
     * @param @return
     * @return Ret
     * @throws
     * @Description: 接口：用于注册时获取手机验证码
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/getPhoneCaptcha")
    @ResponseBody
    public Ret getPhoneCaptcha(@RequestParam("username") String username) {
        try {
            Ret ret = tbCwkService.getPhoneCaptcha(username);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("获取验证码失败");
        }
    }

    /**
     * @param
     * @return Ret
     * @throws
     * @Description: ticket验证失败
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/ticketFail")
    @ResponseBody
    public Ret testTicket() {
        return Ret.warn("ticket验证失败或请求参数有误");
    }

    /**
     * 接口：通过ticket进行登录
     *
     * @param request 请求
     * @return Ret
     * @throws
     * @Description: ticket登录请求
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/ticketLogin", method = RequestMethod.POST)
    @ResponseBody
    public Ret ticketLogin(HttpServletRequest request) {
        Ret ret = null;
        try {
            ret = tbCwkService.checkTicket(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;

    }

    /**
     * 接口：获取token
     *
     * @param username 用户名
     * @param request  请求
     * @return Ret
     * @throws
     * @Description: 获取token
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/getToken")
    @ResponseBody
    public Ret getToken(@RequestParam("username") String username,
                        HttpServletRequest request) {
        try {
            TbCwk tbCwk = tbCwkService.findByName(username);
            if (tbCwk == null) {
                return Ret.warn("用户名不存在");
            }
            String token = _StringUtils.getToken(tbCwk.getPwd());
            tbCwkService.saveToken(username, token);
            return Ret.success(token);
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("登录失败");

        }


    }

    /**
     * @param username 用户名
     * @param password 密码
     * @param type     用户类型
     * @return Ret
     * @paramp honeCaptcha 加密后的验证码
     * @Description: 接口：威客移动端用户注册
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/regist")
    @ResponseBody
    public Ret regist(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam("type") String type,
                      @RequestParam("phoneCaptcha") String phoneCaptcha) {
        if (!Arrays.asList(Constants.USER_TYPE).contains(type)) {
            return Ret.warn("用户类型错误");
        }
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
            Ret ret = tbCwkService.regist(username, password, phoneCaptcha, type);
            return ret;

        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("注册失败");
        }

    }


    /**
     * 接口：钢琴端威客用户登录
     *
     * @param timestamp 时间戳
     * @param username  用户名
     * @param password  密码
     * @return
     */
    @RequestMapping(value = "/pianoLogin")
    @ResponseBody
    public Ret pianoLogin(@RequestParam("timestamp") String timestamp,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password) {
        try {
            Ret ret = tbCwkService.userLogin(timestamp, username, password);
            if ("S".equals(ret.getStatus())) {
                Map<String, Object> map = new HashMap<>();
                TbCwk tbCwk = (TbCwk) ret.getdata();

                map.put("id", tbCwk.getId());
                map.put("ticket", tbCwk.getTicket());
                map.put("username", tbCwk.getName());
                map.put("nickname", tbCwk.getNickname());
                return Ret.success(map);
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("登录失败");

        }

    }

    /**
     * 接口：威客移动端用户登录
     *
     * @param timestamp 时间戳
     * @param username  用户名
     * @param password  加密后的密码
     * @return Ret
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Ret login(@RequestParam("timestamp") String timestamp,
                     @RequestParam("username") String username,
                     @RequestParam("password") String password) {
        try {
            return tbCwkService.userLogin(timestamp, username, password);
        } catch (Exception e) {
            log.error("msg", e);
            return Ret.error("登录失败");

        }

    }

    /**
     * 接口：威客移动端用户注销登录
     *
     * @param username 用户名
     * @param type     用户类型
     * @return Ret
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Ret logout(@RequestParam("username") String username,
                      @RequestParam("type") String type) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        try {
            TbCwk tbCwk = tbCwkService.findByName(username, type);
            tbCwk.setTicket(PasswdEncryption.createTicket(timestamp, username));
            tbCwkService.updateByPrimaryKey(tbCwk);
            return Ret.success("注销成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.warn("注销失败");
        }
    }

    /**
     * 认证接口
     *
     * @param request        请求
     * @param realName       真实名称
     * @param sex            性别
     * @param teachGrade     所教等级
     * @param certificatePic 资质证书
     * @param username       用户名
     * @param birthdayLong   年龄
     * @param identityCard   身份证
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "authenticate")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Ret authenticate(HttpServletRequest request,
                            @RequestParam("realName") String realName,
                            @RequestParam("sex") String sex,
                            @RequestParam("teachGrade") String teachGrade,
                            @RequestParam("certificatePic") MultipartFile[] certificatePic,
                            @RequestParam("username") String username,
                            @RequestParam("birthday") Long birthdayLong,
                            @RequestParam("identityCard") MultipartFile[] identityCard) throws Exception {
        Date birthday = new Date(birthdayLong);
        //判断传入参数
        if (StringUtils.isEmpty(realName)) {
            return Ret.warn("真实姓名不能为空");
        }
        if (StringUtils.isEmpty(sex)) {
            return Ret.warn("性别不能为空");
        }
        if (StringUtils.isEmpty(teachGrade)) {
            return Ret.warn("授课级别不能为空");
        }

        try {
            TbCwk tbCwk = tbCwkService.findByName(username);
            if (tbCwk == null) {
                return Ret.warn("用户不存在");
            }
            TbCwkteacher tbCwkteacher = new TbCwkteacher();
            tbCwkteacher.setCwkid(tbCwk.getId());
            if (teacherService.selectOne(tbCwkteacher) == null) {
                teacherService.insertSelective(tbCwkteacher);
            }
            tbCwkteacher = teacherService.selectOne(tbCwkteacher);

            Ret ret2, ret3;
            if (Arrays.asList(certificatePic).size() > 0) {
                ret2 = myFileUtils.uploadfile(certificatePic, "image", Constants.CERTIFICATE_UPLOAD, Constants.wkBucket);
                if (!"S".equals(ret2.getStatus())) {
                    return Ret.error("资历证书上传失败");
                } else {
                    tbCwkteacher.setCertificatePath(ret2.getdata().toString());
                }
            } else {
                return Ret.warn("资历证书不能为空");
            }

            if (Arrays.asList(identityCard).size() > 0) {
                ret3 = myFileUtils.uploadfile(identityCard, "image", Constants.IDENTITYCARD_UPLOAD, Constants.wkBucket);
                if (!"S".equals(ret3.getStatus())) {
                    return Ret.error("身份证上传失败");
                } else {
                    tbCwkteacher.setIdentityCard(ret3.getdata().toString());
                }
            } else {
                return Ret.warn("身份证不能为空");
            }

            tbCwk.setRealname(realName);
            tbCwk.setBirthday(birthday);
            tbCwk.setSex(sex);
            tbCwkService.updateByPrimaryKeySelective(tbCwk);

            tbCwkteacher.setTeachGrade(teachGrade);
            tbCwkteacher.setIsauthentication(true);
            tbCwkteacher.setAuthenticationProcess(1);
            teacherService.updateByPrimaryKeySelective(tbCwkteacher);
            return Ret.success("S", "提交成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            throw e;
        }
    }

    /**
     * 获取游客账号
     *
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
    @RequestMapping(value = "getLiveUserInfo")
    public Ret getUserInfo(@RequestParam("userId") Integer userId) {
        try {
            TbCwk user = tbCwkService.selectByPrimaryKey(userId);
            if (user == null) {
                return Ret.error("用户不存在");
            }
            //如果为教师,增加教师相关信息
            if ("tea".equals(user.getType())) {
                TbCwkteacher teacher = teacherService.selectByUserId(userId);
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

}



