package com.klsw.piano.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.piano.service.TbLogService;
import com.klsw.piano.service.TbUserexperienceService;
import com.klsw.piano.util.FileUploadConfig;
import com.klsw.piano.util.LoginHelper;
import com.klsw.piano.util.MyFileUtils;
import com.klsw.pianoCommon.api.model.Ret;
import com.klsw.pianoCommon.api.model.TbLog;
import com.klsw.pianoCommon.api.model.TbUserexperience;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/log")
public class LogController {

    private final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Resource
    private MyFileUtils myFileUtils;

    @Resource
    private TbUserexperienceService tbUserexperienceService;

    @Resource
    private TbLogService tbLogService;

    @Resource
    private FileUploadConfig fileUploadConfig;

    /**
     * 接口：插入日志
     *
     * @param params  参数
     * @param request 请求
     * @return 结果
     */
    @RequestMapping(value = "log", method = RequestMethod.POST)
    @ResponseBody
    public Object log(@RequestParam Map<String, String> params,
                      HttpServletRequest request) {
        String versionNo = params.get("VersionNo");
        String versionPlatform = params.get("VersionPlatform");
        String uid = params.get("Uid");
        InputStream is = null;
        try {
            is = request.getInputStream();
            Date time = new Date();
            String timeStr = new SimpleDateFormat("yyyyMMddHHmmssSSSSS").format(time);
            //savepath+"/static/LOG/xxxxxxxx"
            final String folderPath = fileUploadConfig.getSavePath() + "/static/LOG/" + timeStr.substring(0, 8);
            //xxxxxxxxxxxxxxxxxx.log
            final String fileName = timeStr + ".log";
            //savepath+"/static/LOG/xxxxxxxx/xxxxxxxx.log"
            String filePath = folderPath + "/" + fileName;
            byte[] buffer = new byte[4];
            is.read(buffer, 0, 4);
            //文件大小标识
            int legth = ((buffer[0] & 0xff) << 24) + ((buffer[1] & 0xff) << 16) + ((buffer[2] & 0xff) << 8) + (buffer[3] & 0xff);
            logger.info("log日志大小:" + legth);
            byte[] buffer1 = new byte[legth];
            buffer1 = IOUtils.toByteArray(is);
            final byte[] buffer2 = buffer1;
            is.close();
            logger.info("buffer大小" + String.valueOf(buffer1.length));
            //另起线程保存日志
            Ret ret = myFileUtils.decompress(buffer2, folderPath, fileName);
            if ("S".equals(ret.getStatus())) {
                TbLog log = new TbLog();
                log.setLogpath(filePath.substring(filePath.lastIndexOf("/static")));
                log.setSerialnumber(uid);
                log.setTime(time);
                log.setVersionno(versionNo);
                log.setVersionplatfrom(versionPlatform);
                tbLogService.insertUseGeneratedKeys(log);
                return "success";
            }
        } catch (Exception e) {
            logger.error("msg", e);

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("msg", e);
                }
            }
        }

        return "fail";
    }

    @RequestMapping(value = "/getRemark")
    public ModelAndView remark(@RequestParam("id") Integer id) {
        ModelAndView mav = new ModelAndView("Log/remark");
        try {
            TbLog log = tbLogService.selectByPrimaryKey(id);
            String remarks = log.getRemarks() == null ? "" : log.getRemarks();
            mav.addObject("remarks", remarks);
            mav.addObject("id", id);
        } catch (Exception e) {
            logger.error("msg", e);
        }

        return mav;
    }

    @RequestMapping(value = "/remark")
    @ResponseBody
    public String remark(@RequestParam("remarks") String remarks,
                         @RequestParam("id") Integer id) {

        try {
            TbLog log = tbLogService.selectByPrimaryKey(id);
            log.setRemarks(remarks);
            tbLogService.updateByPrimaryKey(log);
            return "<script>alert('保存成功'); history.go(-1)</script>";
        } catch (Exception e) {
            logger.error("msg", e);
            return "<script>alert('保存失败'); history.go(-1)</script>";
        }
    }

    /**
     * 后台：用戶体验列表
     *
     * @return 删除结果
     */
    @RequestMapping(value = "/ListUserExperience")
    public String listUserExperience(HttpServletRequest request, Model model) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }

        Integer pageId = request.getParameter("pageId") == null ? 1 : new Integer(request.getParameter("pageId"));
        try {
            PageHelper.startPage(pageId, 24);
            PageHelper.orderBy("ID desc");
            List<TbUserexperience> tbUserexperienceList = tbUserexperienceService.selectAll();
            PageInfo<TbUserexperience> pageInfo = new PageInfo<>(tbUserexperienceList);
            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error("msg", e);
        }
        return "Log/listUserExperience";
    }

    /**
     * 后台：删除系統日誌
     *
     * @return 删除结果
     */
    @RequestMapping(value = "/deleteLog")
    @ResponseBody
    public String deleteLog(HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "<script>alert('您还未登录');window.location.href=document.referrer;</script>";
        }

        Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            TbLog tbLog = tbLogService.selectByPrimaryKey(id);
            if (tbLog == null) {
                return "<script>alert('日志不存在！'); window.location.href=document.referrer;</script>";
            }
            String logPath = tbLog.getLogpath();
            File log = new File(fileUploadConfig.getSavePath() + logPath);
            if (log.exists()) {
                log.delete();
            }
            tbLogService.delete(tbLog);
            return "<script>alert('删除成功！'); window.location.href=document.referrer;</script>";
        } catch (Exception e) {
            logger.error("msg", e);
        }
        return "<script>alert('出现异常！'); window.location.href=document.referrer;</script>";
    }


    /**
     * 后台：删除用户列表
     *
     * @return 删除结果
     */
    @RequestMapping(value = "/deleteTbUserexperience")
    @ResponseBody
    public Ret deleteTbUserexperience(HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return Ret.error("您还未登录");
        }

        Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            TbUserexperience userexperience = tbUserexperienceService.selectByPrimaryKey(id);
            tbUserexperienceService.delete(userexperience);
            return Ret.success();
        } catch (Exception e) {
            logger.error("msg", e);
        }
        return Ret.error("删除失败");
    }

    /**
     * 后台,系统日志列表
     *
     * @param model   model
     * @param request 请求
     * @param params  参数
     */
    @RequestMapping(value = "/logList")
    public String logList(Model model, HttpServletRequest request,
                          @RequestParam Map<String, String> params) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }
        Integer pageId = StringUtils.isNumeric(params.get("pageId")) ? Integer.parseInt(params.get("pageId")) : 1;
        try {
            PageHelper.startPage(pageId, 10);
            PageHelper.orderBy("Time desc");

            List<TbLog> tbLogList = tbLogService.selectAll();
            PageInfo<TbLog> tbLogPageInfo = new PageInfo<>(tbLogList);
            model.addAttribute("pageInfo", tbLogPageInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("msg", e);
        }
        return "Log/logList";
    }


}
