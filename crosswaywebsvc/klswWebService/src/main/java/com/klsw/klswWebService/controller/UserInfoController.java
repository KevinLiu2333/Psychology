package com.klsw.klswWebService.controller;

import com.klsw.apiCommon.api.model.*;
import com.klsw.klswWebService.service.StudentService;
import com.klsw.klswWebService.service.TbCwkService;
import com.klsw.klswWebService.service.TbPhonecaptchaService;
import com.klsw.klswWebService.service.TeacherService;
import com.klsw.klswWebService.util.Constants;
import com.klsw.klswWebService.util.MyFileUtils;
import com.klsw.klswWebService.util.PasswdEncryption;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * ClassName: UserInfoController
 *
 * @author LiuKun
 * @Description: 用户信息修改
 * @date 2016年9月7日
 */
@Controller
public class UserInfoController extends _BaseController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TbCwkService tbCwkService;

    @Autowired
    private TbPhonecaptchaService tbPhonecaptchaService;

    @Autowired
    private MyFileUtils myFileUtils;

    /**
     * 修改用户名
     *
     * @param username 用户名
     * @param newName  新用户名
     * @param type     用户类型
     * @return Ret
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/modifyName")
    @ResponseBody
    public Ret modifyName(@RequestParam("username") String username, @RequestParam("newName") String newName,
                          @RequestParam("type") String type) {

        try {
            if (newName.trim().equals(username.trim())) {
                return Ret.warn("修改前后用户名一致");
            }
            if (StringUtils.isBlank(newName)) {
                return Ret.warn("用户名不能为空");
            }
            TbCwk tbCwk = tbCwkService.findByName(newName, type);
            if (tbCwk != null) {
                return Ret.warn("用户名已存在");
            }
            tbCwk = tbCwkService.findByName(username, type);
            tbCwk.setName(newName);
            tbCwkService.updateByPrimaryKey(tbCwk);
            return Ret.success("修改用户名成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("修改用户名失败");

        }

    }

    /**
     * @param username     用户名
     * @param type         用户类型
     * @param phoneNum     电话号码
     * @param phoneCaptcha 手机验证码
     * @return Ret
     * @throws @Description: 添加或修改绑定手机
     * @author LiuKun
     * @date 2016年9月1日
     */
    @RequestMapping(value = "/modifyPhoneNumber")
    @ResponseBody
    public Ret modifyPhoneNumber(@RequestParam("username") String username, @RequestParam("type") String type,
                                 @RequestParam("phoneNum") String phoneNum, @RequestParam("phoneCaptcha") String phoneCaptcha) {
        try {
            TbCwk tbCwk = tbCwkService.findByName(username, type);
            if (!tbPhonecaptchaService.selectBystoreCaptcha(phoneCaptcha, phoneNum)) {
                return Ret.warn("您输入的手机号有误或验证码已过期!");
            }
            if (tbCwkService.findByName(phoneNum) != null && !username.equals(phoneNum)) {
                return Ret.warn("该手机号已经被绑定");
            }
            tbCwk.setPhoneno(phoneNum);
            tbCwk.setName(phoneNum);
            tbCwkService.updateByPrimaryKey(tbCwk);
            return Ret.success("S", "修改绑定手机成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("修改绑定手机失败");
        }

    }

    /**
     * 修改昵称
     *
     * @param username 用户名
     * @param type     类型
     * @param nickname 昵称
     * @return
     */
    @RequestMapping(value = "/modifyNickname")
    @ResponseBody
    public Ret modifyNickname(@RequestParam("username") String username, @RequestParam("type") String type,
                              @RequestParam("nickname") String nickname) {
        try {
            if (StringUtils.isBlank(nickname)) {
                return Ret.warn("昵称不能为空");
            }
            TbCwk tbCwk = tbCwkService.findByName(username, type);
            tbCwk.setNickname(nickname);
            tbCwkService.updateByPrimaryKey(tbCwk);
            return Ret.success("修改昵称成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("修改昵称失败");
        }

    }

    /**
     * @param request 包含头像文件
     * @param username 用户名
     * @param type 用户类型
     * @return Ret
     * @throws @Description: 上传头像
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/faviconUpload")
    @ResponseBody
    public Ret faviconUpload(HttpServletRequest request, @RequestParam("username") String username,
                             @RequestParam("type") String type, @RequestParam("file") MultipartFile[] fileList) {
        TbCwk tbCwk = tbCwkService.findByName(username, type);
        try {
            Ret ret1 = myFileUtils.uploadfile(fileList, "image", Constants.FAVICON_UPLOAD,Constants.wkBucket);
            if (!"S".equals(ret1.getStatus())) {
                return ret1;
            }
            String data = (String) ret1.getdata();
            tbCwk.setFfavicon(data);
            tbCwkService.updateByPrimaryKey(tbCwk);
            return Ret.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("头像上传失败");
        }

    }

    /**
     * @param timestamp   时间戳
     * @param username    用户名
     * @param type        用户类型
     * @param password    加密后的密码
     * @param newPassword 新设置的密码
     * @return
     */
    @RequestMapping(value = "/modifyPassword")
    @ResponseBody
    public Ret modifyPassword(@RequestParam("timestamp") String timestamp, @RequestParam("username") String username,
                              @RequestParam("type") String type, @RequestParam("password") String password,
                              @RequestParam("newPassword") String newPassword) {
        TbCwk tbCwk = tbCwkService.findByName(username, type);
        if (StringUtils.isBlank(newPassword)) {
            return Ret.warn("密码不能为空");
        }

        try {
            Ret ret = tbCwkService.userLogin(timestamp, username, password);
            if (!"S".equals(ret.getStatus())) {
                return ret;
            }
            String storePassword = PasswdEncryption.toPasswd(newPassword);
            tbCwk.setPwd(storePassword);
            tbCwkService.updateByPrimaryKey(tbCwk);
            return Ret.success("S", "修改密码成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("修改密码失败");
        }

    }

    /**
     * @param @param  username 用户名
     * @param @param  type 用户类型
     * @param @param  email 邮箱
     * @param @return
     * @return Ret
     * @throws @Description: 修改邮箱地址
     * @author LiuKun
     * @date 2016年9月7日
     */
    @RequestMapping(value = "/modifyEmailAddress")
    @ResponseBody
    public Ret modifyEmail(@RequestParam("username") String username, @RequestParam("type") String type,
                           @RequestParam("email") String email) {
        if (StringUtils.isBlank(email)) {
            return Ret.warn("邮箱地址不能为空");
        }
        try {
            TbCwk tbCwk = tbCwkService.findByName(username, type);
            tbCwk.setEmail(email);
            tbCwkService.updateByPrimaryKey(tbCwk);
            return Ret.success("修改邮箱成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("修改邮箱地址失败");
        }

    }

    /**
     * 修改用户简介
     *
     * @param username 用户名
     * @param type     类型
     * @param details  细节
     * @return Ret
     * @author LiuKun
     * @date 2016年9月26日
     */
    @RequestMapping(value = "/modifyDetails")
    @ResponseBody
    public Ret modifyDetails(@RequestParam("username") String username, @RequestParam("type") String type,
                             @RequestParam("details") String details) {
        if (StringUtils.isBlank(details)) {
            return Ret.warn("简介不能为空");
        }
        try {
            TbCwk tbCwk = tbCwkService.findByName(username, type);
            tbCwk.setDetails(details);
            tbCwkService.updateByPrimaryKey(tbCwk);
            return Ret.success("修改简介成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("修改简介失败");
        }

    }

    /**
     * @param username      	用户登录名
     * @param type          	用户类型
     * @param nickname			用户昵称
     * @param details			个人简介
     * @param sex				性别
     * @param birthday			生日
     * @param region			所在地区
     * @param university		毕业院校
     * @param pianoGrade		钢琴级别
     * @param price				单价
     * @param priceWithMonth	包月价格	
     * @return Ret
     * @Description: 用户个人信息修改
     * @author HKJ
     * @date 2017-6-13
     */
    @RequestMapping(value = "/modifyUserInfo")
    @ResponseBody
    public Ret modifyUserInfo(HttpServletRequest request, 
    						  @RequestParam("type")String type, 
    						  @RequestParam("username") String name) {
        try {
        	boolean flag = false;
        	
        	TbCwk tbCwk = tbCwkService.findByName(name, type);
        	if(tbCwk == null) {
        		return Ret.warn("参数有误");
        	}
        	
            Integer userId = tbCwk.getId();
            
            TbCwkteacher tbCwkteacher = teacherService.selectByUserId(userId);
            if(tbCwkteacher == null) {
            	teacherService.insertByUserId(userId);
            	tbCwkteacher = teacherService.selectByUserId(userId);
            }
            
            TbCwkstudent tbCwkstudent = studentService.selectByUserId(userId);
            if(tbCwkstudent == null) {
            	studentService.insertByUserId(userId);
            	tbCwkstudent = studentService.selectByUserId(userId);
            }
            
            //更新昵称
            String nickName = request.getParameter("nickName");
            if(StringUtils.isNotEmpty(nickName)) {
            	tbCwk.setNickname(nickName);
            	tbCwkService.updateByPrimaryKeySelective(tbCwk);
            	flag = true;
            }
            
            //更新个人简介
            String details = request.getParameter("details");
            if(StringUtils.isNotEmpty(details)) {
            	tbCwk.setDetails(details);
            	tbCwkService.updateByPrimaryKeySelective(tbCwk);
            	flag = true;
            }
            
            //更新性别
            String sex = request.getParameter("sex");
            if(StringUtils.isNotEmpty(sex)) {
            	tbCwk.setSex(sex);
            	tbCwkService.updateByPrimaryKeySelective(tbCwk);
            	flag = true;
            }
            
            //更新生日
            String birthStr = request.getParameter("birthday");
            if(StringUtils.isNotEmpty(birthStr)) {
                tbCwk.setBirthday(new Date(Long.valueOf(birthStr)));
                tbCwkService.updateByPrimaryKeySelective(tbCwk);
                flag = true;
            }
            
            //更新所在地区
            String region = request.getParameter("region");
            if(StringUtils.isNotEmpty(region)) {
            	tbCwk.setRegion(region);
            	tbCwkService.updateByPrimaryKeySelective(tbCwk);
            	flag = true;
            }
            
            //更新毕业院校
            String graduatedUniversity = request.getParameter("university");
            if(StringUtils.isNotEmpty(graduatedUniversity)) {
            	tbCwkteacher.setGraduatedUniversity(graduatedUniversity);
            	teacherService.updateByPrimaryKeySelective(tbCwkteacher);
            	flag = true;
            }
            
            //更新钢琴级别
            String pianoGradeStr = request.getParameter("pianoGrade");
            if(StringUtils.isNotEmpty(pianoGradeStr)) {
            	Byte pianoGrade = Byte.valueOf(pianoGradeStr);
            	tbCwkstudent.setPianoGrade(pianoGrade);
            	studentService.updateByPrimaryKeySelective(tbCwkstudent);
            	flag = true;
            }
            
            //更新单价
            String priceStr = request.getParameter("price");
            if(StringUtils.isNotEmpty(priceStr)) {
            	Float price = Float.valueOf(priceStr);
            	tbCwk.setPrice(price);
            	tbCwkService.updateByPrimaryKeySelective(tbCwk);
            	flag = true;
            }
            
          //更新包月价格
            String priceWithMonthStr = request.getParameter("priceWithMonth");
            if(StringUtils.isNotEmpty(priceWithMonthStr)) {
            	Float priceWithMonth = Float.valueOf(priceWithMonthStr);
            	tbCwk.setPricewithmonth(priceWithMonth);
            	tbCwkService.updateByPrimaryKeySelective(tbCwk);
            	flag = true;
            }
        	
            if(flag) {
            	return Ret.success("修改用户信息成功");
            } else {
            	return Ret.warn("您还没有传入任何需要修改的信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("修改用户信息失败");
        }

    }

    /**
     * 接口：教师信息修改
     *
     * @param wkTeacher 教师
     * @param name      姓名
     * @param type      类型
     * @return
     */
    @RequestMapping(value = "/modifyTeacherInfo")
    @ResponseBody
    public Ret modifyTeacherInfo(WKTeacher wkTeacher, @RequestParam("username") String name,
                                 @RequestParam("type") String type, HttpServletRequest request) {

        try {
            String myBirthday = request.getParameter("myBirthday");
            if (!StringUtils.isEmpty(myBirthday)) {
                Date date = DateUtils.parseDate(myBirthday, "yyyy-MM-dd");
                wkTeacher.setBirthday(date);
            }

            Integer userId = tbCwkService.selectId(name, type);
            TbCwk tbCwk = UserInfoController.getTeacher(wkTeacher);
            tbCwk.setId(userId);
            TbCwkteacher tbCwkteacher = UserInfoController.getTbCwkteacher(wkTeacher);
            tbCwkteacher.setCwkid(userId);

            TbCwkteacher cwkteacher = new TbCwkteacher();
            cwkteacher.setCwkid(userId);
            List<TbCwkteacher> cwkteachers = teacherService.select(cwkteacher);
            if (cwkteachers.size() == 0) {
                teacherService.insertSelective(tbCwkteacher);
            } else {
                tbCwkteacher.setId(teacherService.selectId(userId));
                teacherService.updateByPrimaryKeySelective(tbCwkteacher);
            }
            tbCwkService.updateByPrimaryKeySelective(tbCwk);
            return Ret.success("S", "修改成功", tbCwkService.selectTeacherSelf(userId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("修改失败");
        }
    }

    /**
     * 接口：学生信息修改
     *
     * @param wkStudent 威客学生
     * @param name      用户名
     * @param type      类型
     * @return
     */
    @RequestMapping(value = "/modifyStudentInfo")
    @ResponseBody
    public Ret modifyStudentInfo(WKStudent wkStudent, @RequestParam("username") String name,
                                 @RequestParam("type") String type, HttpServletRequest request) {

        try {
            String myBirthday = request.getParameter("myBirthday");
            if (!StringUtils.isEmpty(myBirthday)) {
                Date date = DateUtils.parseDate(myBirthday, "yyyy-MM-dd");
                wkStudent.setBirthday(date);
            }

            Integer userId = tbCwkService.selectId(name, type);
            TbCwk tbCwk = UserInfoController.getStudent(wkStudent);
            tbCwk.setId(userId);
            TbCwkstudent tbCwkstudent = UserInfoController.getTbCwkstudent(wkStudent);
            tbCwkstudent.setCwkid(userId);

            TbCwkstudent cwkstudent = new TbCwkstudent();
            cwkstudent.setCwkid(userId);
            List<TbCwkstudent> cwkstudents = studentService.select(cwkstudent);
            if (cwkstudents.size() == 0) {
                studentService.insertSelective(tbCwkstudent);
            } else {
                tbCwkstudent.setId(studentService.selectId(userId));
                studentService.updateByPrimaryKeySelective(tbCwkstudent);
            }
            tbCwkService.updateByPrimaryKeySelective(tbCwk);
            return Ret.success("S", "修改成功", tbCwkService.selectStudentSelf(userId));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            ;
            return Ret.error("修改失败");
        }
    }

    /**
     * 重置密码
     *
     * @param phoneNum     电话号码
     * @param phoneCaptcha 验证码
     * @param password     密码
     * @param rePassword   新密码
     * @return
     */
    @RequestMapping(value = "/resetPwd")
    @ResponseBody
    public Ret resetPwd(@RequestParam("phoneNum") String phoneNum, @RequestParam("phoneCaptcha") String phoneCaptcha,
                        @RequestParam("password") String password, @RequestParam("rePassword") String rePassword) {
        try {
            if (StringUtils.isBlank(password)) {
                return Ret.warn("密码不能为空");
            }
            if (StringUtils.isBlank(rePassword)) {
                return Ret.warn("确认密码不能为空");
            }
            if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(phoneCaptcha)) {
                return Ret.warn("非法请求");
            }
            TbCwk tbCwk = new TbCwk();
            tbCwk.setName(phoneNum);
            tbCwk = tbCwkService.selectOne(tbCwk);
            if (tbCwk == null) {
                return Ret.warn("该手机用户不存在");
            }
            if (!tbPhonecaptchaService.selectBystoreCaptcha(phoneCaptcha, phoneNum)) {
                return Ret.warn("验证码错误或验证码过期");
            }
            if (!password.equals(rePassword)) {
                return Ret.warn("两次输入的密码不一致");
            }
            String storePassword = PasswdEncryption.toPasswd(password);
            tbCwk.setPwd(storePassword);
            tbCwkService.updateByPrimaryKey(tbCwk);
            return Ret.success("S", "重置密码成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("重置密码失败");
        }
    }

    /**
     * 根据WKTeacher实体对象更新获取TbCwk实体对象
     *
     * @param wkTeacher 威客教师
     * @return
     */
    private static TbCwk getTeacher(WKTeacher wkTeacher) {
        TbCwk tbCwk = new TbCwk();
        tbCwk.setType(wkTeacher.getType());
        tbCwk.setDetails(wkTeacher.getDetails());
        tbCwk.setUserid(wkTeacher.getUserid());
        tbCwk.setFfavicon(wkTeacher.getFfavicon());
        tbCwk.setName(wkTeacher.getName());
        tbCwk.setPwd(wkTeacher.getPhoneno());
        tbCwk.setNickname(wkTeacher.getNickname());
        tbCwk.setOrgid(wkTeacher.getOrgid());
        tbCwk.setOrgname(wkTeacher.getOrgname());
        tbCwk.setSplit(wkTeacher.getSplit());
        tbCwk.setPrice(wkTeacher.getPrice());
        tbCwk.setPricewithmonth(wkTeacher.getPricewithmonth());
        tbCwk.setIssign(wkTeacher.getIssign());
        tbCwk.setCwkbeancount(wkTeacher.getCwkbeancount());
        tbCwk.setToken(wkTeacher.getToken());
        tbCwk.setLocktime(wkTeacher.getLocktime());
        tbCwk.setLoginfail(wkTeacher.getLoginfail());
        tbCwk.setPhoneno(wkTeacher.getPhoneno());
        tbCwk.setRegisttime(wkTeacher.getRegisttime());
        tbCwk.setTicket(wkTeacher.getTicket());
        tbCwk.setLastlogintime(wkTeacher.getLastlogintime());
        tbCwk.setLogintimestamp(wkTeacher.getLogintimestamp());
        tbCwk.setEmail(wkTeacher.getDetails());
        tbCwk.setSex(wkTeacher.getSex());
        tbCwk.setBirthday(wkTeacher.getBirthday());
        tbCwk.setSystemButton(wkTeacher.getSystemButton());
        tbCwk.setMessageButton(wkTeacher.getMessageButton());
        tbCwk.setRegion(wkTeacher.getRegion());
        tbCwk.setAge(wkTeacher.getAge());
        tbCwk.setRealname(wkTeacher.getrealname());
        return tbCwk;
    }

    /**
     * 根据WKStudent实体对象更新获取TbCwk实体对象
     *
     * @param wkTeacher
     * @return
     */
    private static TbCwk getStudent(WKStudent wkStudent) {
        TbCwk tbCwk = new TbCwk();
        tbCwk.setType(wkStudent.getType());
        tbCwk.setDetails(wkStudent.getDetails());
        tbCwk.setUserid(wkStudent.getUserid());
        tbCwk.setFfavicon(wkStudent.getFfavicon());
        tbCwk.setName(wkStudent.getName());
        tbCwk.setPwd(wkStudent.getPhoneno());
        tbCwk.setNickname(wkStudent.getNickname());
        tbCwk.setOrgid(wkStudent.getOrgid());
        tbCwk.setOrgname(wkStudent.getOrgname());
        tbCwk.setSplit(wkStudent.getSplit());
        tbCwk.setPrice(wkStudent.getPrice());
        tbCwk.setPricewithmonth(wkStudent.getPricewithmonth());
        tbCwk.setIssign(wkStudent.getIssign());
        tbCwk.setCwkbeancount(wkStudent.getCwkbeancount());
        tbCwk.setToken(wkStudent.getToken());
        tbCwk.setLocktime(wkStudent.getLocktime());
        tbCwk.setLoginfail(wkStudent.getLoginfail());
        tbCwk.setPhoneno(wkStudent.getPhoneno());
        tbCwk.setRegisttime(wkStudent.getRegisttime());
        tbCwk.setTicket(wkStudent.getTicket());
        tbCwk.setLastlogintime(wkStudent.getLastlogintime());
        tbCwk.setLogintimestamp(wkStudent.getLogintimestamp());
        tbCwk.setEmail(wkStudent.getDetails());
        tbCwk.setSex(wkStudent.getSex());
        tbCwk.setBirthday(wkStudent.getBirthday());
        tbCwk.setSystemButton(wkStudent.getSystemButton());
        tbCwk.setMessageButton(wkStudent.getMessageButton());
        tbCwk.setRegion(wkStudent.getRegion());
        tbCwk.setAge(wkStudent.getAge());
        tbCwk.setRealname(wkStudent.getRealname());
        return tbCwk;
    }

    /**
     * 根据WKTeacher对象获取TbCWKteacher实体对象
     *
     * @param wkTeacher
     * @return
     */
    private static TbCwkteacher getTbCwkteacher(WKTeacher wkTeacher) {
        TbCwkteacher tbCwkteacher = new TbCwkteacher();
        tbCwkteacher.setAuthenticationProcess(wkTeacher.getAuthenticationProcess());
        tbCwkteacher.setAverageScore(wkTeacher.getAverageScore());
        tbCwkteacher.setCertificatePath(wkTeacher.getCertificatePath());
        tbCwkteacher.setGraduatedUniversity(wkTeacher.getGraduatedUniversity());
        tbCwkteacher.setIsauthentication(wkTeacher.getIsauthentication());
        tbCwkteacher.setTeachExperience(wkTeacher.getTeachExperience());
        tbCwkteacher.setTeachGrade(wkTeacher.getTeachGrade());

        return tbCwkteacher;
    }

    /**
     * 根据WKStudent实体对象获取TbCwkstudent实体对象
     *
     * @param wkStudent 威客学生
     * @return
     */
    private static TbCwkstudent getTbCwkstudent(WKStudent wkStudent) {
        TbCwkstudent tbCwkstudent = new TbCwkstudent();
        tbCwkstudent.setPianoGrade(wkStudent.getPianoGrade());
        return tbCwkstudent;
    }
    
}
