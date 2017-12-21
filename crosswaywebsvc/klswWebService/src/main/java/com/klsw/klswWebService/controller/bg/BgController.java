package com.klsw.klswWebService.controller.bg;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.*;
import com.klsw.klswWebService.service.*;
import com.klsw.klswWebService.util.Constants;
import com.klsw.klswWebService.util.MyFileUtils;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
@RequestMapping(value = "/manage")
public class BgController {

    private static final Logger log = LoggerFactory.getLogger(BgController.class);

    @Autowired
    private MyStudentService myStudentService;

    @Autowired
    private MyTeacherService myTeacherService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TbCwkManagerService cwkManagerService;

    @Autowired
    private TbCwkSuggestionService tbCwkSuggestionService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TbCwkmessageService messageService;

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private TbCwkService cwkService;

    @Autowired
    private TeacherService cwkTeacharService;

    @Autowired
    private MyFileUtils myFileUtils;

    /********************************** 用户登录相关 *************************************/

    /**
     * 威客后台：进入登录界面
     *
     * @return
     */
    @RequestMapping(value = "toLogin")
    public String toLogin() {
        return "bg_user/login";
    }

    /**
     * 威客后台：登录
     *
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public String login(HttpServletRequest request, @RequestParam("username") String username,
                        @RequestParam("password") String password) {
        TbCwkmanager cwkmanager = new TbCwkmanager();
        cwkmanager.setName(username);
        cwkmanager.setPassword(password);
        try {
            cwkmanager = cwkManagerService.selectOne(cwkmanager);
            if (cwkmanager == null) {
                return "<script>alert('用户名或密码错误！');history.go(-1);</script>";
            }
            request.getSession().setAttribute("wkmanager", cwkmanager);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return "<script>alert('');history.go(-1);</script>";
        }
        return "<script>window.location.href='/manage/index';</script>";
    }

    /**
     * 威客后台：进入后台主页
     *
     * @return
     */
    @RequestMapping(value = "index")
    public String index(HttpServletRequest request) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        return "index";
    }

    /**
     * 威客后台：退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "exit")
    public String exit(HttpServletRequest request) {
        request.getSession().setAttribute("wkmanager", null);
        return "redirect:/manage/toLogin";
    }

    /********************************** 用户列表相关 *************************************/
    /**
     * 威客后台：教师列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "teaList")
    public String getTeacherList(HttpServletRequest request, Model model) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        String idString = request.getParameter("pageId");
        Integer id = 1;
        if (!StringUtils.isEmpty(idString)) {
            id = Integer.parseInt(idString);
        }
        try {
            PageHelper.startPage(id, 10);
            List<Teacher> teachers = myTeacherService.selectTeachers();
            PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teachers);
            model.addAttribute("teaList", pageInfo);
            return "tea_list";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:index";
        }
    }

    /**
     * 威客后台：学生列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "stuList")
    public String getStudentList(HttpServletRequest request, Model model) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        String pageNumStr = request.getParameter("pageNum");
        Integer pageNum = 1;
        if (!StringUtils.isEmpty(pageNumStr)) {
            pageNum = Integer.parseInt(pageNumStr);
        }
        try {
            PageHelper.startPage(pageNum, 10);
            List<WKStudent> studentList = cwkService.studentList();
            PageInfo<WKStudent> pageInfo = new PageInfo<WKStudent>(studentList);
            model.addAttribute("stuList", pageInfo);
            return "stu_list";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:index";
        }
    }

    /**
     * 威客后台：进入学生修改界面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "toStuModify")
    public String toStuModify(HttpServletRequest request, Model model, @RequestParam("studentId") Integer studentId) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        try {
            WKStudent wkStudent = cwkService.selectStudentSelf(studentId);
            model.addAttribute("wkStudent", wkStudent);
            return "stu_modify";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return null;
        }
    }

    /**
     * 威客后台：修改学生信息
     *
     * @param request
     * @param model
     * @param studentId    Id
     * @param name         用户名
     * @param nickName     昵称
     * @param realName     真实姓名
     * @param sex          性别
     * @param birthday     生日
     * @param phoneno      手机号
     * @param email        邮箱
     * @param region       所在地区
     * @param pianoGrade   钢琴等级
     * @param averageScore 平均得分
     * @return
     */
    @RequestMapping(value = "modifyStudentInfo")
    @ResponseBody
    public String modifyStudentInfo(HttpServletRequest request, Model model,
                                    @RequestParam("studentId") Integer studentId,
                                    @RequestParam("name") String name,
                                    @RequestParam("nickName") String nickName,
                                    @RequestParam("realName") String realName,
                                    @RequestParam("sex") String sex,
                                    @RequestParam("birthday") String birthday,
                                    @RequestParam("phoneNo") String phoneno,
                                    @RequestParam("email") String email,
                                    @RequestParam("region") String region,
                                    @RequestParam("pianoGrade") Byte pianoGrade,
                                    @RequestParam("averageScore") Float averageScore) {

        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }
        try {
            TbCwk tbCwk = new TbCwk();

            tbCwk.setId(studentId);
            tbCwk.setName(name);
            tbCwk.setNickname(nickName);
            tbCwk.setRealname(realName);
            tbCwk.setSex(sex);
            tbCwk.setBirthday(DateUtils.parseDate(birthday, "yyyy-MM-dd"));
            tbCwk.setPhoneno(phoneno);
            tbCwk.setEmail(email);
            tbCwk.setRegion(region);

            TbCwkstudent student = studentService.selectByUserId(studentId);
            student.setPianoGrade(pianoGrade);
            student.setAverageScore(averageScore);

            cwkService.updateByPrimaryKeySelective(tbCwk);
            studentService.updateByPrimaryKeySelective(student);

            return "<script>alert('修改学生信息成功');location.href='/manage/stuList';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return "<script>alert('修改学生信息失败，请稍后重试');history.go(-1);</script>";
        }
    }

    /**
     * 删除学生账号
     *
     * @param studentId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "deleteStudent")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public String deleteStudent(@RequestParam("studentId") Integer studentId) throws Exception {
        Student student = myStudentService.selectStudentInfo(studentId);
        if (student == null) {
            return "<script>alert('要删除的学生不存在');history.go(-1);</script>";
        }
        try {
            cwkService.deleteByPrimaryKey(studentId);
            TbCwkstudent cwkstudent = new TbCwkstudent();
            cwkstudent.setCwkid(studentId);
            studentService.delete(cwkstudent);
            return "<script>alert('删除学生成功！');window.location.href='/manage/stuList';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            throw e;
        }
    }

    /**
     * 威客后台：机构列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "orgList")
    public String getOrganizationList(HttpServletRequest request, Model model) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        String idString = request.getParameter("pageId");
        Integer id = 1;
        if (!StringUtils.isEmpty(idString)) {
            id = Integer.parseInt(idString);
        }
        try {
            PageHelper.startPage(id, 10);
            List<TbCwk> cwkList = Lists.newArrayList();
            TbCwk tbCwk = new TbCwk();
            tbCwk.setType("org");
            cwkList = cwkService.select(tbCwk);
            PageInfo<TbCwk> pageInfo = new PageInfo<TbCwk>(cwkList);
            model.addAttribute("orgList", pageInfo);
            return "org_list";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:index";
        }
    }

    /**
     * 威客后台：教师详情
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "teacherInfo")
    public String teacherInfo(HttpServletRequest request, Model model) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            return "redirect:/manage/teaList";
        }

        Teacher teacher = myTeacherService.selectTeacherInfo(Integer.parseInt(id));
        model.addAttribute("wkTeacher", teacher);

        return "teacher_info";
    }

    /****
     * 教师认证
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "bgAuthentication")
    @ResponseBody
    public String bgAuthentication(HttpServletRequest request, @RequestParam("teacherId") Integer teacherId,
                                   @RequestParam("authenticationProcess") Integer authenticationProcess) {
        try {
            TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
            if (cwkmanager == null) {
                return "<script>alert('您还没有登录');window.location.href='/manage/toLogin';</script>";
            }

            TbCwkteacher tbCwkteacher = teacherService.selectByPrimaryKey(teacherId);
            if (tbCwkteacher == null) {
                return "<script>alert('用户信息有误');history.go(-1);</script>";
            }

            tbCwkteacher.setAuthenticationProcess(authenticationProcess);
            teacherService.updateByPrimaryKeySelective(tbCwkteacher);
            return "<script>alert('操作成功');location.href='/manage/teaList';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return "<script>alert('操作异常，请稍后重试');history.go(-1);</script>";
        }
    }

    /**
     * 威客后台：删除教师账号
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "deleteTeacher")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public String deleteTeacher(@RequestParam("id") Integer teacherId, HttpServletRequest request) throws Exception {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "<script>alert('您还未登录!');window.location.href='/manage/toLogin';</script>";
        }

        try {
            cwkService.deleteByPrimaryKey(teacherId);
            TbCwkteacher cwkteacher = new TbCwkteacher();
            cwkteacher.setCwkid(teacherId);
            teacherService.delete(cwkteacher);
            return "<script>alert('删除成功！');window.location.href='/manage/teaList';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            throw e;
        }
    }

    /**
     * 威客后台：进入修改教师界面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "toMdyTeacher")
    public String toMdyTeacher(HttpServletRequest request, Model model) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            return "redirect:/manage/teaList";
        }
        model.addAttribute("wkTeacher", myTeacherService.selectTeacherInfo(Integer.parseInt(id)));
        return "teacher_modify";
    }

    /**
     * 威客后台：修改教师信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "mdyTeacher", method = RequestMethod.POST)
    public String mdyTeacher(HttpServletRequest request, @RequestParam("teachExperience") Byte teachExperience,
                             @RequestParam("graduatedUniversity") String graduatedUniversity,
                             @RequestParam("teachGrade") String teachGrade, @RequestParam("averageScore") Float averageScore) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }
        Integer teacherID = Integer.parseInt(request.getParameter("teacherID"));
        Teacher teacher = myTeacherService.selectTeacherInfo(teacherID);
        Integer teaCwkId = teacher.getId();
        TbCwkteacher cwkteacher = new TbCwkteacher();
        cwkteacher.setCwkid(teaCwkId);
        try {
            cwkteacher = cwkTeacharService.selectOne(cwkteacher);
            TbCwkteacher tbCwkteacher = new TbCwkteacher();
            tbCwkteacher.setId(cwkteacher.getId());
            tbCwkteacher.setCwkid(cwkteacher.getCwkid());
            tbCwkteacher.setAuthenticationProcess(cwkteacher.getAuthenticationProcess());
            tbCwkteacher.setCertificatePath(cwkteacher.getCertificatePath());
            tbCwkteacher.setTeachExperience(teachExperience);
            tbCwkteacher.setAverageScore(averageScore);
            tbCwkteacher.setGraduatedUniversity(graduatedUniversity);
            tbCwkteacher.setTeachGrade(teachGrade);
            cwkTeacharService.updateByPrimaryKey(tbCwkteacher);
            return "redirect:/manage/teaList";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "teacher_modify";
    }

    /********************************** 消息管理相关 *************************************/
    /**
     * 威客后台：消息列表界面
     *
     * @return
     */
    @RequestMapping(value = "messageList")
    public String messageList(HttpServletRequest request, Model model) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        String pageNum = request.getParameter("pageNum");
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = "1";
        }
        PageHelper.startPage(Integer.parseInt(pageNum), Constants.PAGE_SIZE);
        PageHelper.orderBy("addtime DESC");

        try {
            List<TbCwkmessage> messageList = messageService.selectAll();
            PageInfo<TbCwkmessage> pageInfo = new PageInfo<TbCwkmessage>(messageList);
            model.addAttribute("messageList", pageInfo);
        } catch (Exception e) {
            return "redirect:index";
        }
        return "message_list";
    }

    /**
     * 威客后台：进行消息添加界面
     *
     * @return
     */
    @RequestMapping(value = "toAddMessage")
    public String toAddMessage(HttpServletRequest request) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        return "add_message";
    }

    /**
     * 上传系统消息图片
     *
     * @param request
     * @param response
     * @param file
     * @throws IOException
     * @throws FileUploadException
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam("imgFile") MultipartFile file) throws IOException, FileUploadException {

        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        MultipartFile[] fileArray = {file};
        Ret ret = myFileUtils.uploadfile(fileArray, "image", Constants.MESSAGE_UPLOAD, Constants.wkBucket);
        if ("S".equals(ret.getStatus())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", 0);
            jsonObject.put("url", Constants.WKHOST + ret.getdata());
            return jsonObject.toString();
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("error", 1);
            jsonObject.put("message", ret.getMessage());
            return ret.getMessage();
        }
    }

    /**
     * 威客后台：添加系统消息功能
     *
     * @param request
     * @param file
     * @param messageType
     * @param content
     * @param addtime
     * @return
     */
    @RequestMapping(value = "addMessage")
    @ResponseBody
    public String addMessage(HttpServletRequest request, @RequestParam("file") MultipartFile file,
                             @RequestParam("messageType") String messageType, @RequestParam("contentHtml") String content,
                             @RequestParam("date") String addtime) {

        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        String title = request.getParameter("title");
        if (StringUtils.isEmpty(title)) {
            title = "无";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            TbCwkmessage tbCwkmessage = new TbCwkmessage();

            MultipartFile[] fileArray = {file};
            if (!file.isEmpty()) {
                Ret ret = myFileUtils.uploadfile(fileArray, "image", Constants.MESSAGE_UPLOAD, Constants.wkBucket);
                if ("S".equals(ret.getStatus())) {
                    tbCwkmessage.setImgpath(Constants.WKHOST + ret.getdata());
                }
            }
            if ("系统消息".equals(messageType)) {
                messageType = "1";
            }
            tbCwkmessage.setMessagetype(messageType);
            tbCwkmessage.setTitle(title);
            tbCwkmessage.setMessage(content);
            tbCwkmessage.setAddress("/messageInfo?messageId=");
            tbCwkmessage.setCwkid(0);
            tbCwkmessage.setAddtime(simpleDateFormat.parse(addtime));
            messageService.insertSelective(tbCwkmessage);
            return "<script>alert('添加系统消息成功');window.location.href='/manage/messageList';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return "<script>alert('添加系统消息异常');history.go(-1);</script>";
        }
    }

    /**
     * 后台：编辑系统消息
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "toEditMessage")
    public String toEditMessage(HttpServletRequest request, HttpServletResponse response, Model model)
            throws Exception {

        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        response.setContentType("text/html;charset=utf-8;");
        String messageId = request.getParameter("messageId");
        if (StringUtils.isEmpty(messageId)) {
            response.getWriter().println("<script>alert('参数信息有误!');history.go(-1);</script>");
        }
        TbCwkmessage cwkmessage = messageService.selectByMessageId(Integer.parseInt(messageId));
        model.addAttribute("tbCwkMessage", cwkmessage);
        return "edit_message";
    }

    /**
     * 保存编辑过的系统消息
     *
     * @param request
     * @param file
     * @param messageType
     * @param content
     * @param addtime
     * @return
     */
    @RequestMapping(value = "saveMessage")
    @ResponseBody
    public String saveMessage(HttpServletRequest request, @RequestParam("messageId") Integer messageId,
                              @RequestParam("file") MultipartFile file, @RequestParam("messageType") String messageType,
                              @RequestParam("contentHtml") String content, @RequestParam("date") String addtime) {

        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        String title = request.getParameter("title");
        if (StringUtils.isEmpty(title)) {
            title = "无";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            TbCwkmessage tbCwkmessage = new TbCwkmessage();

            MultipartFile[] fileArray = {file};
            if (!file.isEmpty()) {
                Ret ret = myFileUtils.uploadfile(fileArray, "image", Constants.MESSAGE_UPLOAD, Constants.wkBucket);
                if ("S".equals(ret.getStatus())) {
                    tbCwkmessage.setImgpath(Constants.WKHOST + ret.getdata());
                }
            }
            if ("系统消息".equals(messageType)) {
                messageType = "1";
            }
            tbCwkmessage.setId(messageId);
            tbCwkmessage.setMessagetype(messageType);
            tbCwkmessage.setTitle(title);
            tbCwkmessage.setMessage(content);
            tbCwkmessage.setAddress("/messageInfo?messageId=");
            tbCwkmessage.setCwkid(0);
            tbCwkmessage.setAddtime(simpleDateFormat.parse(addtime));
            messageService.updateByPrimaryKeySelective(tbCwkmessage);
            return "<script>alert('编辑系统消息成功');window.location.href='/manage/messageList';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return "<script>alert('编辑系统消息异常');history.go(-1);</script>";
        }
    }

    /**
     * 删除系统消息
     *
     * @param messageId
     * @return
     */
    @RequestMapping(value = "deleteMessage")
    @ResponseBody
    public String deleteMessage(@RequestParam("messageId") Integer messageId, HttpServletRequest request) {

        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        try {
            messageService.deleteByPrimaryKey(messageId);
            return "<script>alert('删除成功！');window.location.href='/manage/messageList';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return "<script>alert('删除异常，请重试！');history.go(-1);</script>";
        }
    }

    /********************************** 相关 *************************************/
    /**
     * 反馈消息
     *
     * @param messageId
     * @return
     */
    @RequestMapping(value = "suggesstionList")
    public String suggesstionList(HttpServletRequest request, Model model) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }
        String idString = request.getParameter("pageNum");
        Integer id = 1;
        if (!StringUtils.isEmpty(idString)) {
            id = Integer.parseInt(idString);
        }
        try {
            PageHelper.startPage(id, 10);
            List<Suggestion> suggestions = suggestionService.selectSuggestion();
            PageInfo<Suggestion> pageInfo = new PageInfo<Suggestion>(suggestions);
            model.addAttribute("suggesstionList", pageInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "cwk_suggestion";
    }

    /**
     * 删除反馈消息
     *
     * @param messageId
     * @return
     */
    @RequestMapping(value = "deleteSuggestion")
    @ResponseBody
    public String deleteSuggestion(@RequestParam("id") Integer id, HttpServletRequest request) {

        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }

        try {
            tbCwkSuggestionService.deleteByPrimaryKey(id);
            return "<script>alert('删除成功！');window.location.href='/manage/suggestionList';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return "<script>alert('删除异常，请重试！');history.go(-1);</script>";
        }
    }

    /**
     * 认证列表
     *
     * @param messageId
     * @return
     */
    @RequestMapping(value = "authenticationList")
    public String authenticationList(HttpServletRequest request, Model model) {
        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }
        String idString = request.getParameter("pageNum");
        Integer id = 1;
        if (!StringUtils.isEmpty(idString)) {
            id = Integer.parseInt(idString);
        }
        try {
            PageHelper.startPage(id, 10);
            List<Authentication> authenticationList = authenticationService.selectAuthentication();
            PageInfo<Authentication> pageInfo = new PageInfo<Authentication>(authenticationList);
            model.addAttribute("authenticationList", pageInfo);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
        }
        return "cwk_authentication";
    }

    /**
     * 删除认证消息
     *
     * @param messageId
     * @return
     */
    @RequestMapping(value = "deleteAuthentication")
    @ResponseBody
    public String deleteAuthentication(@RequestParam("id") Integer id, HttpServletRequest request) {

        TbCwkmanager cwkmanager = (TbCwkmanager) request.getSession().getAttribute("wkmanager");
        if (cwkmanager == null) {
            return "redirect:/manage/toLogin";
        }
        try {
            authenticationService.deleteByPrimaryKey(id);
            return "<script>alert('删除成功！');window.location.href='/manage/authenticationList';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("msg", e);
            return "<script>alert('删除异常，请重试！');history.go(-1);</script>";
        }
    }

    /**
     * 删除认证消息
     *
     * @param messageId
     * @return
     */
    @RequestMapping(value = "imgDetails")
    public String imgDetails() {

        return "image_details";
    }

    @ResponseBody
    @RequestMapping(value = "imgPath")
    public String imgPath() {
        return "imgDetails";
    }

}
