package com.klsw.weikesite.controller;

import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwkhomework;
import com.klsw.apiCommon.api.model.TbHomework;
import com.klsw.apiCommon.api.model.WKTeacher;
import com.klsw.weikesite.configuration.SendEmailConfiguration;
import com.klsw.weikesite.constants.Constants;
import com.klsw.weikesite.utils.*;
import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Authenticator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private DomainConfig domainConfig;
    public static final Logger log = LoggerFactory.getLogger(HomeworkController.class);

    @Autowired
    private SendEmailConfiguration senderEmail;

    /**
     * @param request：http请求
     * @param model：模块对象
     * @param pageNum        展示的页号
     * @param status         学生作业状态
     * @Description：学生登录主页
     * @return：视图名
     * @author HKJ
     */
    @RequestMapping(value = "/student")
    public String getHomeworkList(HttpServletRequest request, Model model,
                                  @RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("status") String status) {
        //获取session中保存的用户
        HttpSession session = request.getSession(true);
        TbCwk tbCwk = (TbCwk) session.getAttribute("user");

        //定义请求路径
        String url = domainConfig.getApiDomain() + "/homework/homeworkList";

        //设置请求参数
        NameValuePair nvp1 = new NameValuePair("pageNum", String.valueOf(pageNum));
        NameValuePair nvp2 = new NameValuePair("status", status);
        List<NameValuePair> list = Lists.newArrayList(nvp1, nvp2);
        try {
            //发送请求并获取响应信息
            String response = TestUtils.loggedInRequest(request, list, url, null);
            log.info(response);

            //对响应信息做转换
            Ret ret = JsonUtils.decode(response, Ret.class);

            //根据响应返回的状态选择绑定的信息到页面
            if ("S".equals(ret.getStatus())) {
                model.addAttribute("pageInfo", ret.getdata());
                model.addAttribute("status", status);
            } else {
                model.addAttribute("errorMessage", ret.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            model.addAttribute("errorMessage", "获取作业信息失败");
        }
        //绑定到页面的公共信息
        model.addAttribute("type", tbCwk.getType());
        model.addAttribute("index", 0);
        return "homework/all_homework";
    }

    /**
     * @param id：作业Id
     * @param status：作业状态
     * @param request：http请求
     * @param res：http相应
     * @param model：模块对象
     * @Description：作业详情
     * @return：视图名
     */
    @RequestMapping(value = "/homeworkDetail")
    public String homeworkDetail(@RequestParam("id") Integer id,
                                 @RequestParam("status") Integer status,
                                 HttpServletRequest request,
                                 HttpServletResponse res,
                                 Model model) {
        String url = domainConfig.getApiDomain() + "/homework/homeworkDetail";
        NameValuePair nvp1 = new NameValuePair("homeworkId", String.valueOf(id));
        List<NameValuePair> list = Lists.newArrayList(nvp1);
        try {

            String response = TestUtils.loggedInRequest(request, list, url, null);
            log.info(response);
            JSONObject object = new JSONObject(response);
            String responseStatus = object.getString("status");
            if ("S".equals(responseStatus)) {
                JSONObject homeworkInfo = object.getJSONObject("data");
                //如果未提交
                if (status == 0) {
                    TbHomework homework = JsonUtils.decode(homeworkInfo.toString(), TbHomework.class);
                    request.getSession(true).setAttribute("homework", homework);
                    model.addAttribute("homework", homework);
                    //如果已提交
                } else {
                    TbCwkhomework homework = JsonUtils.decode(homeworkInfo.toString(), TbCwkhomework.class);
                    //存入session
                    model.addAttribute("homework", homework);
                    //获取教师信息
                    Integer teacherId = homework.getTeacherid();

                    nvp1 = new NameValuePair("teacherId", String.valueOf(teacherId));
                    url = domainConfig.getApiDomain() + "/teacherInfo";

                    list.clear();
                    list.add(nvp1);
                    response = TestUtils.loggedInRequest(request, list, url, null);
                    object = new JSONObject(response);
                    JSONObject teacherInfo = object.getJSONObject("data");
                    WKTeacher teacher = JsonUtils.decode(teacherInfo.toString(), WKTeacher.class);
                    model.addAttribute("teacher", teacher);
                }

                model.addAttribute("status", status);
//                return "share";
                return "homework/homework_info";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return "login";
        }
        return "login";
    }

    /**
     * @param id
     * @param request
     * @param model
     * @param
     * @return String
     * @throws
     * @Description: 教师查询作业详情
     * @author LiuKun
     * @date 2016年9月21日
     */
    @RequestMapping(value = "/teacher/homeworkDetail")
    public String teacherHomeworkDetail(@RequestParam("id") Integer id,
                                        HttpServletRequest request,
                                        Model model) {
        String url = domainConfig.getApiDomain() + "/homework/teacher/homeworkDetail";
        NameValuePair nvp1 = new NameValuePair("homeworkId", String.valueOf(id));
        List<NameValuePair> list = Lists.newArrayList(nvp1);
        try {

            String response = TestUtils.loggedInRequest(request, list, url, null);
            log.info(response);
            JSONObject object = new JSONObject(response);
            String responseStatus = object.getString("status");
            if ("S".equals(responseStatus)) {
                JSONObject homeworkInfo = object.getJSONObject("data");
                TbCwkhomework homework = JsonUtils.decode(homeworkInfo.toString(), TbCwkhomework.class);
                Ret ret = Base64Utils.base64ImageString("http://piano-static.klsw.com" + homework.getMidimgpath());
                if ("S".equals(ret.getStatus())) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("data:image/png;base64,");
                    sb.append(ret.getdata());
                    homework.setMidimgpath(sb.toString());
                }
                //存入session
                model.addAttribute("homework", homework);
//                return "teacher_answer";
                return "homework/teacher_hw";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return "login";
        }

        return "login";

    }

    /**
     * @param teacherId：教师Id
     * @param homeworkId：作业Id
     * @param request：http请求
     * @param
     * @return Ret对象
     * @throws
     * @Description: 提交
     * @author LiuKun
     * @date 2016年9月22日
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Ret homeworkUpload(@RequestParam("teacherId") String teacherId,
                              @RequestParam("homeworkId") String homeworkId,
                              HttpServletRequest request) {

        NameValuePair nvp1 = new NameValuePair("teacherId", teacherId);
        NameValuePair nvp2 = new NameValuePair("homeworkId", homeworkId);
        List<NameValuePair> list = Lists.newArrayList(nvp1, nvp2);
        String url = domainConfig.getApiDomain() + "/homework/upload";

        Ret ret = null;
        try {
            String response = TestUtils.loggedInRequest(request, list, url, null);
            ret = JsonUtils.decode(response, Ret.class);

            //查询教师相关信息
            String url1 = domainConfig.getApiDomain() + "/teacherInfo";
            List<NameValuePair> list2 = Lists.newArrayList(nvp1);
            String response1 = TestUtils.loggedInRequest(request, list2, url1, null);
            Ret ret1 = JsonUtils.decode(response1, Ret.class);
            JSONObject object = new JSONObject(response1);
            JSONObject teacherInfo = object.getJSONObject("data");
            WKTeacher teacher = new WKTeacher();
            if ("S".equals(ret1.getStatus())) {
                teacher = JsonUtils.decode(teacherInfo.toString(), WKTeacher.class);
            } else {
                teacher = null;
            }
            if ("S".equals(ret.getStatus()) && teacher != null) {
                TbCwk tbCwk = (TbCwk) request.getSession().getAttribute("user");
                String studentName = tbCwk.getName();
                String body = teacher.getNickname() + "，您好！" + studentName + "向您提交了一份作业，请及时登录威客官网 http://wkweb.klsw.com 进行批改";
                UseAuthenticatorSender sender = senderEmail.getSender(teacher.getNickname(), studentName);
                if (teacher.getEmail() == null || teacher.getEmail() == "") {
                    sender = new UseAuthenticatorSender(Constants.EMAIL, "hekejun@klsw.com", "有学生提交作业了", body);
                }
                Authenticator auth = new SimpleAuthenticator(Constants.EMAIL, Constants.PASSWORD);
                sender.sendMails(auth);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ret;
        }
        return ret;
    }

    /**
     * 上传视频
     *
     * @param request：    http请求
     * @param file：       视频文件
     * @param homeworkId： 作业Id
     * @return RET对象
     */
    @RequestMapping(value = "/uploadMedia", method = RequestMethod.POST)
    @ResponseBody
    public Ret uploadMedia(HttpServletRequest request,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("homeworkId") String homeworkId) {
        File media = new File(file.getOriginalFilename());
        String url = domainConfig.getApiDomain() + "/homework/uploadMedia";
        Ret ret = null;
        try {
            NameValuePair nvp = new NameValuePair("homeworkId", homeworkId);
            List<NameValuePair> list = Lists.newArrayList(nvp);
            FileOutputStream fos = new FileOutputStream(media);
            fos.write(file.getBytes());
            fos.flush();
            fos.close();
            List<File> files = Lists.newArrayList(media);
            String response = TestUtils.loggedInRequest(request, list, url, files);
            ret = JsonUtils.decode(response, Ret.class);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Ret.error("上传视频失败");
        } finally {
            if (media.exists()) {
                media.delete();
            }
        }
    }

}
