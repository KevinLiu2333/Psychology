package com.klsw.weikesite.controller;

import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.weikesite.utils.DomainConfig;
import com.klsw.weikesite.utils.ImageUtils;
import com.klsw.weikesite.utils.JsonUtils;
import com.klsw.weikesite.utils.TestUtils;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller
public class AnswerController {

    @Autowired
    private DomainConfig domainConfig;
    private static Logger log = Logger.getLogger(AnswerController.class);

    @RequestMapping(value = "/homework/answer")
    @ResponseBody
    public String answerHomework(HttpServletRequest request, HttpServletResponse response) {
        String teacherComment = request.getParameter("teachercomment");
        String homeworkId = request.getParameter("homeworkid");
        String base64Image = request.getParameter("image").substring(22);
        File image = ImageUtils.GenerateImage(base64Image);
        List<File> fileList = Lists.newArrayList(image);
        String url = domainConfig.getApiDomain() + "/homework/comment";
        NameValuePair nvp1 = new NameValuePair("comment", teacherComment);
        NameValuePair nvp2 = new NameValuePair("id", homeworkId);
        List<NameValuePair> list = Lists.newArrayList(nvp1, nvp2);
        String responseString;
        try {
            responseString = TestUtils.loggedInRequest(request, list, url, fileList);
            Ret ret = JsonUtils.decode(responseString, Ret.class);
            log.info(responseString);
            if ("S".equals(ret.getStatus())) {
                return "<script>alert('批改成功！');window.location.href='/homework/teacher?pageNum=1&status=1'</script>";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            image.delete();
        }
        return "<script>alert('批改失败！');window.location.reload()</script>";
    }

}
