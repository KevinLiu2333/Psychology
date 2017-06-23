package com.klsw.klswWebService.service;


import com.klsw.apiCommon.api.mapper.TbCwkMapper;
import com.klsw.apiCommon.api.mapper.TbCwkstudentMapper;
import com.klsw.apiCommon.api.mapper.TbCwkteacherMapper;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwkstudent;
import com.klsw.apiCommon.api.model.TbCwkteacher;
import com.klsw.apiCommon.api.model.TeacherParam;
import com.klsw.apiCommon.api.model.WKStudent;
import com.klsw.apiCommon.api.model.WKTeacher;
import com.klsw.klswWebService.util.Constants;
import com.klsw.klswWebService.util.PasswdEncryption;
import com.klsw.klswWebService.util._StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TbCwkService extends _BaseService<TbCwk> {


    @Autowired
    private TbCwkMapper tbCwkMapper;

    @Autowired
    private SmsService smsService;

    @Autowired
    private TbPhonecaptchaService tbPhonecaptchaService;

    @Autowired
    private TbCwkteacherMapper teacherMapper;

    @Autowired
    private TbCwkstudentMapper studentMapper;

    /**
     * @param username 用户名
     * @param type     用户类型
     * @return Integer
     * @author LiuKun
     */
    public Integer selectId(String username, String type)  throws Exception{
        TbCwk tbCwk = new TbCwk();
        tbCwk.setName(username);
        tbCwk = selectOne(tbCwk);
        return tbCwk.getId();
    }

    /**
     * @param username 用户名
     * @return TbCwk
     * @author LiuKun
     */
    public TbCwk findByName(String username) {
        TbCwk tbCwk = new TbCwk();
        tbCwk.setName(username);
        try {
            tbCwk = super.selectOne(tbCwk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbCwk;
    }

    /**
     * 通过用户名和用户类型查询用户
     *
     * @param username 用户名
     * @param type     用户类型
     * @return 威客用户
     */
    public TbCwk findByName(String username, String type) {
        TbCwk tbCwk = new TbCwk(username, type);
        try {
            tbCwk = super.selectOne(tbCwk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbCwk;
    }

    /**
     * 将生成的token存入数据库
     *
     * @param username 用户名
     * @param token    token
     */
    public void saveToken(String username, String token) {
        TbCwk tbCwk = findByName(username);
        tbCwk.setToken(token);
        tbCwkMapper.updateByPrimaryKey(tbCwk);
    }

    /**
     * 生成ticket并保存
     *
     * @param username  用户名
     * @param timestamp 时间戳
     */
    public String saveTicket(String username, String timestamp) {
        TbCwk tbCwk = findByName(username);
        String ticket = PasswdEncryption.createTicket(timestamp, username);
        tbCwk.setTicket(ticket);
        tbCwk.setLogintimestamp(Long.parseLong(timestamp));
        tbCwkMapper.updateByPrimaryKey(tbCwk);
        return ticket;
    }

    /**
     * 验证用户登录
     *
     * @param timestamp 时间戳
     * @param username  用户名
     * @param password  密码
     */
    public Ret userLogin(String timestamp, String username, String password) throws Exception {
        TbCwk tbCwk = findByName(username);
        if (tbCwk == null) {
            return Ret.warn("用户名或用户类型错误");
        }
        Integer loginfail = tbCwk.getLoginfail() == null ? 0 : tbCwk.getLoginfail();
        if (loginfail == 5) {
            Date locktime = tbCwk.getLocktime();
            Date date = new Date();
            //如果在锁定时间内,返回警告
            if (date.getTime() - locktime.getTime() < Constants.LOCKTIME) {
                return Ret.warn("您登陆失败次数过多，请稍后再试");
            }
            //如果已解锁
            tbCwk.setLoginfail(0);
            //更新数据库
            super.updateByPrimaryKey(tbCwk);
        }

        //检查账号密码
        boolean flag = PasswdEncryption.checkPasswd(
                tbCwk.getName(), tbCwk.getPwd(), tbCwk.getToken(), timestamp, password);
        //账号密码验证通过
        if (flag) {
            //存入ticket和登录时间
            String ticket = saveTicket(username, timestamp);
            tbCwk.setTicket(ticket);
            //返回ticket和成功状态
            return Ret.success(tbCwk);
        }
        tbCwk.setLoginfail(loginfail + 1);
        tbCwk.setToken(_StringUtils.getToken(tbCwk.getPwd()));
        //如果已经失败5次,添加上锁时间
        if (tbCwk.getLoginfail() + 1 == 5) {
            tbCwk.setLocktime(new Date());

        }
        //更新数据库
        super.updateByPrimaryKey(tbCwk);

        return Ret.warn("账号或密码错误");

    }


    public Ret receiveCaptcha(String phoneNum) {
        try {
            TbCwk tbCwk = new TbCwk();
            tbCwk.setPhoneno(phoneNum);
            if (select(tbCwk).size() > 0) {
                return Ret.warn("该手机号已经被绑定");
            }
            String randomString = RandomStringUtils.randomNumeric(6);
            String content = "您本次的验证码是" + randomString + "，请及时进行相关操作。";
            smsService.sendSms(phoneNum, content);
            tbPhonecaptchaService.saveCaptcha(phoneNum, randomString);

            return Ret.success("S", "验证码已发送", null);
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("获取验证码失败");
        }
    }


    /**
     * 用于重置密码时获取手机验证码
     *
     * @param phoneNum 用户手机号
     */
    public Ret getCaptcha(String phoneNum) {
        try {
            if (findByName(phoneNum) == null) {
                return Ret.warn("用户不存在");
            }
            String randomString = RandomStringUtils.randomNumeric(6);
            String content = "您本次的验证码是" + randomString + "，请及时进行相关操作。";
            smsService.sendSms(phoneNum, content);
            tbPhonecaptchaService.saveCaptcha(phoneNum, randomString);
            return Ret.success("S", "验证码已发送", null);
        } catch (Exception exception) {
            exception.printStackTrace();
            return Ret.error("获取验证码失败");
        }
    }

    /**
     * 用于用户注册时获取验证码
     *
     * @param username 用户名
     */
    public Ret getPhoneCaptcha(String username) {
        try {
            if (findByName(username) != null) {
                return Ret.warn("手机号已被注册");

            }
            String randomString = RandomStringUtils.randomNumeric(6);
            String content = "验证码是" + randomString + "，请在注册页面输入以完成注册。";
            smsService.sendSms(username, content);
            tbPhonecaptchaService.saveCaptcha(username, randomString);
            return Ret.success("S", "验证码已发送", null);
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("获取验证码失败");
        }


    }

    /**
     * 用户注册功能
     *
     * @param username     用户名
     * @param password     密码
     * @param phoneCatpcha 手机验证码
     * @param type         用户类型
     */
    public Ret regist(String username, String password, String phoneCatpcha, String type) {
        Date date = new Date();
        try {
            if (findByName(username) != null) {
                return Ret.warn("手机号已被注册");
            }

            if (tbPhonecaptchaService.selectBystoreCaptcha(phoneCatpcha, username)) {
                TbCwk tbCwk = new TbCwk();
                tbCwk.setLoginfail(0);
                tbCwk.setName(username);
                tbCwk.setNickname(username);
                tbCwk.setPwd(PasswdEncryption.toPasswd(password));
                tbCwk.setRegisttime(date);
                tbCwk.setPhoneno(username);
                tbCwk.setType(type);
                tbCwk.setCwkbeancount(0);
                if ("tea".equals(type)) {
                    tbCwk.setOrgname("克洛斯威智能钢琴");
                    tbCwk.setOrgid(1);
                }

                int num = super.insertSelective(tbCwk);

                if (num == 1) {
                    int cwkId = findByName(username).getId();
                    if ("tea".equals(type)) {
                        TbCwkteacher tbCwkteacher = new TbCwkteacher();
                        tbCwkteacher.setCwkid(cwkId);

                        teacherMapper.insertSelective(tbCwkteacher);
                    } else if ("stu".equals(type)) {
                        TbCwkstudent tbCwkstudent = new TbCwkstudent();
                        tbCwkstudent.setCwkid(cwkId);

                        studentMapper.insertSelective(tbCwkstudent);
                    } else {
                        ;
                    }
                }

                return Ret.success("S", "注册成功", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("注册失败");
        }


        return Ret.warn("验证码错误");


    }

    /**
     * ticket验证
     *
     * @param request 请求
     */

    public Ret checkTicket(HttpServletRequest request) throws Exception {
        String username = request.getParameter("username");

        String userId = request.getParameter("userId");
        String ticket = request.getParameter("ticket");

        TbCwk tbCwk;
        if (StringUtils.isBlank(ticket)) {
            return Ret.warn("ticket不能为空");
        }

        if (userId != null) {
            tbCwk = selectByPrimaryKey(Integer.valueOf(userId));
        } else {
            tbCwk = findByName(username);
        }

        if (tbCwk == null) {
            return Ret.warn("用户信息有误");
        }
        if (tbCwk.getTicket().equals(ticket)) {
            return Ret.success(tbCwk.getId());
        }

        return Ret.warn("ticket错误");

    }

    public List<WKTeacher> selectSignedTeacher(Integer studentId, Integer pianoGrade) {
        return tbCwkMapper.selectSignedTeacher(studentId, pianoGrade);
    }

    public List<WKTeacher> selectNotSignedTeacher(TeacherParam teacherParam) {
        return tbCwkMapper.selectNotSignedTeacher(teacherParam);
    }

    public List<HashMap<String, Object>> selectStudent(Integer teacherId) {
        return tbCwkMapper.selectStudent(teacherId);
    }

    public List<HashMap<String, Object>> selectTeacher(Integer studentId) {
        return tbCwkMapper.selectTeacher(studentId);
    }

    public WKStudent selectStudentSelf(Integer id) {
        return tbCwkMapper.studentSelf(id);
    }

    public WKTeacher selectTeacherSelf(Integer id) {
        return tbCwkMapper.teacherSelf(id);
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
            TbCwk liveuser = new TbCwk();
            liveuser.setName(username);
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
     * @param teachGrade 教师等级
     * @param level      直播间等级
     * @return 教师列表
     */
    public List<Map<String, Object>> selectTeacherListByliveroomInfo(String teachGrade, Integer level) {
        return tbCwkMapper.selectTeacherListByliveroomInfo(teachGrade, level);
    }

    /**
     * 查询用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    public HashMap<String, Object> userInfo(Integer userId) {
        return tbCwkMapper.getUserInfo(userId);
    }
    
    /**
     * 查询教师列表
     * @param signType
     * @param studentId
     * @param teachGrade
     * @param serviceType
     * @param level
     * @return
     */
    public List<Map<String, Object>> teacherList(Integer signType, 
												 Integer studentId, 
												 String teachGrade, 
												 Integer serviceType, 
												 Integer level) {
    	return tbCwkMapper.teacherList(signType, studentId, teachGrade, serviceType, level);
    }

    /**
     * 查询学生列表
     * @return
     */
    public List<WKStudent> studentList() {
    	return tbCwkMapper.studentList();
    }
}

