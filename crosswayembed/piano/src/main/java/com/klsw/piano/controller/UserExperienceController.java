package com.klsw.piano.controller;

import com.klsw.piano.service.TbUserexperienceService;
import com.klsw.piano.util.DateUtils;
import com.klsw.piano.util.HttpUtils;
import com.klsw.pianoCommon.api.model.TbUserexperience;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by liukun on 2016/12/21.
 * 用户体验
 */
@Controller
@RequestMapping(value = "/UserExperience")
public class UserExperienceController {
    private final Logger logger = LoggerFactory.getLogger(UserExperienceController.class);
    @Resource
    private TbUserexperienceService tbUserexperienceService;

    @RequestMapping(value = "/AddUserExperience")
    @ResponseBody
    public Object addUserExperience(@RequestParam Map<String, String> params, HttpServletRequest request) {
        String ip = HttpUtils.getIpAddr(request);
        if (params.get("SerialNumber") == null) {
            return "1, SerialNumber is null";
        }
        Date date = new Date();
        try {
            String serialNumber = params.get("SerialNumber");
            String curPage = params.get("CurPage");
            String nextPage = params.get("NextPage");
            String button = params.get("Button");
            if (params.get("Time") != null) {
                date = DateUtils.parseDateString(params.get("Time"));
                if (date == null) {
                    date = new Date();
                }
            }
            TbUserexperience tbUserexperience = new TbUserexperience();
            tbUserexperience.setButton(button);
            tbUserexperience.setCurpage(curPage);
            tbUserexperience.setNextpage(nextPage);
            tbUserexperience.setSerialnumber(serialNumber);
            tbUserexperience.setTime(date);
            tbUserexperience.setIp(ip);
            tbUserexperienceService.insertUseGeneratedKeys(tbUserexperience);
            return "0, ok";
        } catch (Exception e) {
            logger.error("msg",e);
            return "2, error message: " + e.getMessage();
        }
    }

}
