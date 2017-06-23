package com.klsw.crosswaylive.service;

import com.klsw.common.live.mapper.TbLiveuserMapper;
import com.klsw.common.live.model.Ret;
import com.klsw.common.live.model.TbLiveteacher;
import com.klsw.common.live.model.TbLiveuser;
import com.klsw.crosswaylive.util.Constants;
import com.klsw.crosswaylive.util.PasswdEncryption;
import com.klsw.crosswaylive.util._StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by liukun on 2017/3/11.
 * 用户相关
 */
@Service
public class TbLiveUserService extends _BaseService<TbLiveuser> {
    @Resource
    private TbLiveuserMapper mapper;
    @Resource
    private SmsService smsService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private TbPhoneCaptchaService tbPhoneCaptchaService;

    /**
     * 用于用户注册时获取验证码
     *
     * @param username 用户名
     * @return 结果
     */
    public Ret getPhoneCaptcha(String username) {
        try {
            if (findByName(username) != null) {
                return Ret.warn("手机号已被注册");

            }
            String randomString = RandomStringUtils.randomNumeric(6);
            String content = "验证码是" + randomString + "，请在注册页面输入以完成注册。";
            smsService.sendSms(username, content);
            tbPhoneCaptchaService.saveCaptcha(username, randomString);
            return Ret.success("S", "验证码已发送", null);
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("获取验证码失败");
        }

    }


    /**
     * 将生成的token存入数据库
     *
     * @param username 用户名
     * @param token    token
     */
    public void saveToken(String username, String token) throws Exception {
        TbLiveuser liveuser = findByName(username);
        liveuser.setToken(token);
        updateByPrimaryKey(liveuser);
    }

    /**
     * 注册
     *
     * @param username     用户名
     * @param password     密码
     * @param phoneCaptcha 手机验证码
     * @return 注册结果
     */
    public Ret regist(String username, String password, String phoneCaptcha, String type) {
        try {
            if (findByName(username) != null) {
                return Ret.warn("手机号已被注册");
            }
            if (tbPhoneCaptchaService.selectBystoreCaptcha(phoneCaptcha, username)) {
                Date date = new Date();
                //记录信息
                TbLiveuser liveuser = new TbLiveuser();
                liveuser.setLoginfail(0);
                liveuser.setUsername(username);
                liveuser.setNickname(username);
                liveuser.setPassword(PasswdEncryption.toPasswd(password));
                liveuser.setRegisttime(date);
                liveuser.setPhoneno(username);
                liveuser.setLivebean(0);
                liveuser.setType(type);
                insertSelective(liveuser);
                if ("tea".equals(type)) {
                    TbLiveteacher teacher = new TbLiveteacher();
                    teacher.setUserid(selectOne(liveuser).getId());
                    teacher.setIsauth(0);
                    teacherService.insert(teacher);
                }

                return Ret.success("S", "注册成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("注册失败");
        }
        return Ret.error("验证码错误");
    }

    /**
     * @param username 用户名
     * @return 查询结果
     */
    public TbLiveuser findByName(String username) {

        try {
            TbLiveuser user = new TbLiveuser();
            user.setUsername(username);
            user = selectOne(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 验证用户登录
     *
     * @param timestamp 时间戳
     * @param username  用户名
     * @param password  密码
     * @return 登录验证结果
     */
    public Ret userLogin(String timestamp, String username, String password) throws Exception {
        TbLiveuser liveuser = findByName(username);
        if (liveuser == null) {
            return Ret.warn("用户名错误");
        }

        Integer loginfail = liveuser.getLoginfail() == null ? 0 : liveuser.getLoginfail();
        if (loginfail == 5) {
            Date locktime = liveuser.getLocktime();
            Date date = new Date();
            //如果在锁定时间内,返回警告
            if (date.getTime() - locktime.getTime() < Constants.LOCKTIME) {
                return Ret.warn("您登陆失败次数过多，请稍后再试");
            }
            //如果已解锁
            liveuser.setLoginfail(0);
            //更新数据库
            updateByPrimaryKey(liveuser);
        }

        //检查账号密码
        boolean flag = PasswdEncryption.checkPasswd(
                liveuser.getUsername(), liveuser.getPassword(), liveuser.getToken(), timestamp, password);
        //账号密码验证通过
        if (flag) {
            //存入ticket和登录时间
            String ticket = saveTicket(username, timestamp);
            liveuser.setTicket(ticket);
            //返回ticket和成功状态
            if ("tea".equals(liveuser.getType())) {
                TbLiveteacher teacher = teacherService.selectByUserId(liveuser.getId());
                liveuser.setTeacherInfo(teacher);
            }
            return Ret.success(liveuser);
        }
        liveuser.setLoginfail(liveuser.getLoginfail() + 1);
        liveuser.setToken(_StringUtils.getToken(liveuser.getPassword()));
        //如果已经失败5次,添加上锁时间
        if (liveuser.getLoginfail() + 1 == 5) {
            liveuser.setLocktime(new Date());

        }
        //更新数据库
        updateByPrimaryKey(liveuser);
        return Ret.warn("账号或密码错误");

    }

    /**
     * 生成ticket并保存
     *
     * @param username  用户名
     * @param timestamp 时间戳
     * @return 存储ticket
     */
    public String saveTicket(String username, String timestamp) throws Exception {
        TbLiveuser liveuser = findByName(username);
        String ticket = PasswdEncryption.createTicket(timestamp, username);
        liveuser.setTicket(ticket);
        liveuser.setLogintimestamp(Long.parseLong(timestamp));
        updateByPrimaryKey(liveuser);
        return ticket;
    }

    /**
     * ticket验证
     *
     * @param request 请求
     * @return 验证结果
     */
    public Ret checkTicket(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        String ticket = request.getParameter("ticket");
        if (StringUtils.isBlank(userId) || !StringUtils.isNumeric(userId)) {
            return Ret.warn("用户id不能为空");
        }
        if (StringUtils.isBlank(ticket)) {
            return Ret.warn("ticket不能为空");
        }
        TbLiveuser liveuser = super.selectByPrimaryKey(Integer.parseInt(userId));
        if (liveuser == null) {
            return Ret.warn("用户信息有误");
        }
        if (liveuser.getTicket().equals(ticket)) {
            return Ret.success(liveuser.getId());
        }
        return Ret.warn("ticket错误");
    }

    /**
     * tcp登录验证
     *
     * @param username 用户名
     * @param ticket   ticket
     * @return 验证结果
     */
    public String CheckTicket(String username,
                              String ticket) {
        try {
            TbLiveuser liveuser = new TbLiveuser();
            liveuser.setUsername(username);
            liveuser = selectOne(liveuser);
            if (liveuser == null || !ticket.equals(liveuser.getTicket())) {
                return null;
            }
            return String.valueOf(liveuser.getId());
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 根据直播间状态查询教师列表
     *
     * @param status 直播间状态
     * @param level  直播间等级
     * @return 教师列表
     */
    public List<TbLiveuser> selectTeacherListByliveroomInfo(Integer status, Integer level) {
        return mapper.selectTeacherListByliveroomInfo(status, level);
    }
}




