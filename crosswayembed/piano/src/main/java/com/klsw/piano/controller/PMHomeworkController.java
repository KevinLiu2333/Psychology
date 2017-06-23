package com.klsw.piano.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.klsw.piano.service.TbCwkService;
import com.klsw.piano.service.TbCwkhomeworkService;
import com.klsw.piano.service.TbHomeworkService;
import com.klsw.piano.util.*;
import com.klsw.pianoCommon.api.model.PMhomeworkList;
import com.klsw.pianoCommon.api.model.TbCwk;
import com.klsw.pianoCommon.api.model.TbCwkhomework;
import com.klsw.pianoCommon.api.model.TbHomework;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 琴上作业接口
 */
@Controller
@RequestMapping(value = "/PMHomework")
public class PMHomeworkController {
    private static final Logger logger = LoggerFactory.getLogger(PMHomeworkController.class);
    private static final int PAGESIZE = 8;
    @Resource
    private FileUploadConfig fileUploadConfig;
    @Resource
    private TbHomeworkService tbHomeworkService;
    @Resource
    private TbCwkService tbCwkService;
    @Resource
    private TbCwkhomeworkService tbCwkHomeworkService;
    @Resource
    private MyFileUtils myFileUtils;

    /**
     * @param params 请求参数
     * @return PMhomeworkList
     * @Description: 琴上查询作业列表
     * @date 2016年10月26日
     */
    @RequestMapping(value = "/GetHomework")
    @ResponseBody
    public PMhomeworkList getHomeWork(@RequestParam Map<String, String> params) {
        String uid = params.get("Uid");
        String cwkname = params.get("cwkname");
        Integer pageId = StringUtils.isNumeric(params.get("PageId")) ? Integer.parseInt(params.get("PageId")) : 1;
        List<Map<String, Object>> pageContent = Lists.newArrayList();
        TbCwk tbCwk = null;
        Integer cwkId = null;
        PMhomeworkList pMhomeworkList = new PMhomeworkList();
        try {
            // 如果是登录状态下并且可以查询到用户信息
            if (!StringUtils.isBlank(cwkname) && (tbCwkService.findByName(cwkname) != null)) {

                // 根据用户名查询用户信息
                tbCwk = tbCwkService.findByName(cwkname);
                // 用户Id
                pMhomeworkList.setCwkID(tbCwk.getId());

                // 用户昵称
                pMhomeworkList.setCwkNickName(tbCwk.getNickname());

                // 用户名
                pMhomeworkList.setCwkName(tbCwk.getName());
                cwkId = tbCwk.getId();
            }
            int pageSize = StringUtils.isNumeric(params.get("PageSize")) ? Integer.parseInt(params.get("PageSize")) : 5;
            //分页
            // 分页查询作业内容
            PageHelper.startPage(pageId, pageSize);

            // 根据cwkId和机器的uid查询作业内容,如果未登陆,则cwkid=null
            List<TbHomework> tbHomeworkList = tbHomeworkService.PMhomeworkList(cwkId, uid);
            PageInfo<TbHomework> pageInfo = new PageInfo<>(tbHomeworkList);

            // 当前页数
            pMhomeworkList.setCurrentPageIndex(pageInfo.getPageNum());

            // 总页数
            pMhomeworkList.setTotalPageCount(pageInfo.getPages());

            // 增加用户信息
            for (TbHomework homework : tbHomeworkList) {
                // 作业常规参数开始
                Map<String, Object> map = Maps.newLinkedHashMap();
                map.put("ID", homework.getId());
                map.put("HomeworkID", homework.getId());
                map.put("MIDName", homework.getMidname());
                map.put("StudentTime", DateUtils.formatDate(homework.getStudenttime()));
                map.put("CWKID", homework.getCwkid());
                map.put("LastTeacherTime", null);
                map.put("MDIPortfolio", homework.getMdiportfolio());
                map.put("GradeCount", String.valueOf(tbCwkHomeworkService.selectGradeCount(homework.getId())));
                // 作业常规参数结束
                // 如果该作业曾上传威客，且威客用户和当前查询用户对应
                if ((homework.getCwkid() != null) && (homework.getCwkid().equals(cwkId))) {

                    // 学生名为威客用户名
                    map.put("StudentName", tbCwk.getNickname());

                    // 状态根据不同的状态码赋值
                    switch (homework.getStatus()) {
                        case 0:
                            map.put("Status", "已上传");

                            break;

                        case 1:
                            map.put("Status", "已提交");

                            break;

                        case 2:
                            map.put("Status", "已批改");

                            break;

                        case 3:
                            map.put("Status", "学生已查看");

                            break;

                        default:
                            break;
                    }

                    // 否则学生名为钢琴用户名
//                } else if ((homework.getCwkid() == null) && (cwkId != null)) {
                } else if (cwkId != null) {

                    // 已登录但曲谱未提交
                    map.put("StudentName", homework.getStudentname());
                    map.put("Status", "上传");
                } else {
                    // 未登录威客
                    map.put("StudentName", homework.getStudentname());
                    map.put("Status", "未登录威客");
                }

                // 教师已批改
                if (homework.getTeachertime() != null) {
                    map.put("teachertime", DateUtils.formatDate(homework.getTeachertime()));
                    map.put("LastTeacherTime", DateUtils.formatDate(homework.getTeachertime()));
                }
                pageContent.add(map);
            }
            pMhomeworkList.setPageContent(pageContent);
            return pMhomeworkList;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("msg", e);
            return pMhomeworkList;
        }
    }

    /**
     * @param username   用户名
     * @param cwkname    威客名
     * @param homeworkId 作业id
     * @return 删除结果
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "username", required = false) String username,
                         @RequestParam(value = "cwkname", required = false) String cwkname,
                         @RequestParam("homeworkId") Integer homeworkId) {
        try {
            TbHomework homework = tbHomeworkService.selectByPrimaryKey(homeworkId);
            if (homework == null) {
                return "error";
            }
            if (StringUtils.isBlank(cwkname)) {
                if (!homework.getStudentname().equals(username)) {
                    return "error";
                }
                tbHomeworkService.delete(homework);
                return "success";
            } else {
                TbCwk cwk = tbCwkService.findByName(cwkname);
                if (cwk == null || !cwk.getId().equals(homework.getCwkid())) {
                    return "error";
                }
                tbHomeworkService.delete(homework);
                TbCwkhomework tbCwkhomework = new TbCwkhomework();
                tbCwkhomework.setCwkhomeworkid(homeworkId);
                List<TbCwkhomework> tbCwkhomeworks = tbCwkHomeworkService.select(tbCwkhomework);
                for (TbCwkhomework entity : tbCwkhomeworks) {
                    tbCwkHomeworkService.delete(entity);
                }
                return "success";
            }
        } catch (Exception e) {
            logger.error("msg", e);
            return "error";
        }
    }


    /**
     * 查看作业详细信息
     *
     * @param params  参数列表
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "/GetHomeWorkView")
    @ResponseBody
    public Object getHomeWorkView(@RequestParam Map<String, String> params, HttpServletRequest request) {
        Map<String, Object> resultMap = Maps.newLinkedHashMap();
        try {
            Integer cwkID;
            String cwkNickName;
            String VersionNo = params.get("VersionNo");
            String VersionPlatform = params.get("VersionPlatform");
            String Uid = params.get("Uid");
            String CWKName = params.get("cwkname");
            String homeworkID = params.get("HomeworkID");
            Integer id = (StringUtils.isBlank(homeworkID) || "undefined".equals(homeworkID))
                    ? -1
                    : Integer.parseInt(homeworkID);
            //DES加密结果
            String hkID = DES.encrypt("normal" + String.valueOf(id), "crossway", "waycross");
            String hurl = request.getRequestURL().toString();
            String shareUrl = WxUtils.shareUrl(Constants.WEIXIN_URL + "/Open/Work/" + hkID);
            String shareQrCodePath = "";
            File dir = new File(fileUploadConfig.getSavePath() + "/static/PM/Homework/QrCode/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!StringUtils.isBlank(Constants.WEIXINPIC_URL)) {
                myFileUtils.createQRCode(shareUrl,
                        fileUploadConfig.getSavePath() + "/static/PM/Homework/QrCode/cwk-" + homeworkID
                                + ".png", 200);
                shareQrCodePath = Constants.WEIXINPIC_URL + "/static/PM/Homework/QrCode/cwk-" + id + ".png";

            }
            TbHomework tbHomework = tbHomeworkService.selectByPrimaryKey(id);
            String studentName = tbHomework.getStudentname();
            TbCwk user = new TbCwk();
            if (!StringUtils.isBlank(CWKName)) {
                user = tbCwkService.findByName(CWKName);
                if (user != null && user.getId().equals(tbHomework.getCwkid())) {
                    cwkID = user.getId();
                    cwkNickName = user.getNickname();
                    studentName = cwkNickName;

                }
            }
            Map<String, Object> pageContent = new LinkedHashMap<>();
            pageContent.put("MIDName", tbHomework.getMidname());
            pageContent.put("MDIPortfolio", tbHomework.getMdiportfolio());
            pageContent.put("MIDIMGPath", Constants.VIDEOHOST + tbHomework.getMidimgpath() + Constants.SITE_VERSION);
            pageContent.put("StudentName", studentName);
            pageContent.put("StudentTimeString", DateUtils.formatDate(tbHomework.getStudenttime()));
            resultMap.put("shareUrl", shareUrl);
            resultMap.put("shareQrCodePath", shareQrCodePath);
            resultMap.put("pageContent", Lists.newArrayList(pageContent));
            return resultMap;
        } catch (Exception e) {
            logger.error("msg", e);
            return "error";
        }
    }

    /**
     * 已批改作业列表 接口
     *
     * @param params 参数
     * @return 返回
     */
    @RequestMapping(value = "/GetHomeworkInfoList")
    @ResponseBody
    public Object getHomeWorkInfoList(@RequestParam Map<String, String> params) {
        Map<String, Object> resultMap = Maps.newLinkedHashMap();
        try {
            // 机器码
            String uid = params.get("Uid");
            Integer HWID;
            // homeworkId
            if (StringUtils.isNumeric(params.get("HomeworkID"))) {
                HWID = Integer.parseInt(params.get("HomeworkID"));
            } else {
                return "fail";
            }
            // 威客用户名
            String CWKName = params.get("cwkname");
            // 页码
            Integer id = (StringUtils.isBlank(params.get("PageId")) || params.get("PageId").equals("undefined"))
                    ? 1
                    : Integer.parseInt(params.get("PageId"));
            // 每页条数
            Integer pageSize = StringUtils.isNumeric(params.get("PageSize")) ? Integer.parseInt(params.get("PageSize")) : 5;
            // 威客Id
            Integer cwkId = null;
            // 用户昵称
            String cwkNickName = null;
            // 通过威客用户名查询是否存在用户
            TbCwk tbCwk = tbCwkService.findByName(CWKName);
            if (tbCwk != null) {
                cwkId = tbCwk.getId();
                cwkNickName = tbCwk.getNickname();
            }
            //分页
            PageHelper.startPage(id, pageSize);
            List<TbCwkhomework> tbCwkhomeworkList = tbCwkHomeworkService.homeworkInfolist(HWID);
            PageInfo<TbCwkhomework> pageInfo = new PageInfo<>(tbCwkhomeworkList);
            List<Map<String, Object>> pageContent = Lists.newArrayList();
            for (TbCwkhomework entity : tbCwkhomeworkList) {
                Map<String, Object> map = new LinkedHashMap<>();
                TbHomework homework = tbHomeworkService.selectByPrimaryKey(entity.getHomeworkid());
                //原作业ID
                map.put("ID", homework.getId());
                //威客作业ID
                map.put("HomeworkID", homework.getId());
                map.put("CWKHomeworkID", entity.getId());
                //MIDName
                map.put("MIDName", homework.getMidname());
                map.put("TeacherTime", DateUtils.formatDate(entity.getTeachertime()));
                TbCwk teacher = tbCwkService.selectByPrimaryKey(entity.getTeacherid());
                if (teacher != null) {
                    map.put("TeacherName", teacher.getNickname());
                } else {
                    map.put("TeacherName", "");
                }

                map.put("CWKID", homework.getCwkid());
                pageContent.add(map);
            }
            // 各个返回参数
            resultMap.put("currentPageIndex", pageInfo.getPageNum());
            resultMap.put("totalPageCount", pageInfo.getPages());
            resultMap.put("cwkName", CWKName);
            resultMap.put("cwkID", cwkId);
            resultMap.put("cwkNickName", cwkNickName);
            resultMap.put("pageContent", pageContent);
            return resultMap;
        } catch (Exception e) {
            logger.error("msg", e);
            return "error";
        }
    }

    /**
     * @param
     * @return String
     * @throws
     * @Description: 上传作业到威客页面
     * 钢琴端上传作业接口在piano项目 pm/addHomeworkF
     * @author LiuKun
     * @date 2016年10月27日
     */
    @RequestMapping(value = "/HomeWorkCWKUpload")
    @ResponseBody
    public String homeWorkCWKUpload(@RequestParam Map<String, String> params) {
        try {
            if (!StringUtils.isNumeric(params.get("HomeworkID"))) {
                return "fail";
            }
            Integer homeworkID = Integer.parseInt(params.get("HomeworkID"));
            String cwkName = params.get("cwkname");
            if (cwkName == null) {
                return "fail";
            }
            TbCwk tbCwk = tbCwkService.findByName(cwkName);
            TbHomework tbHomework = tbHomeworkService.selectByPrimaryKey(homeworkID);
            if ((tbCwk != null) && (tbHomework != null)) {
                // 如果用户信息和作业信息无误
                tbHomework.setCwkid(tbCwk.getId());
                tbHomework.setStatus(0);
                tbHomework.setSubmittime(new Date());
                tbHomework.setModifytime(new Date());
                tbHomeworkService.updateByPrimaryKey(tbHomework);
                return "okey";
            } else {
                return "用户信息有误";
            }
        } catch (Exception e) {
            logger.error("msg", e);

            return "fail";
        }
    }

    /**
     * 作业批改详细信息
     *
     * @param params 参数列表
     * @return 返回
     */
    @RequestMapping(value = "/GetHomeWorkInfo")
    @ResponseBody
    public Object GetHomeWorkInfo(@RequestParam Map<String, String> params) {
        Map<String, Object> resultMap = Maps.newLinkedHashMap();

        try {
            int CWKHomeworkID = (StringUtils.isBlank(params.get("CWKHomeworkID"))
                    || "undefined".equals(params.get("CWKHomeworkID")))
                    ? -1
                    : Integer.parseInt(params.get("CWKHomeworkID"));
            int HomeworkID = (StringUtils.isBlank(params.get("HomeworkID"))
                    || "undefined".equals(params.get("HomeworkID")))
                    ? -1
                    : Integer.parseInt(params.get("HomeworkID"));

            if (CWKHomeworkID > 0) {
                String hkID = String.valueOf(CWKHomeworkID);

                hkID = DES.encrypt("wk" + hkID, "crossway", "waycross");
                String shareUrl = WxUtils.shareUrl(Constants.WEIXIN_URL + "/Open/Work/" + hkID);
                String shareQrCodePath = Constants.VIDEOHOST + "/static/PM/Homework/QrCode/cwk-"
                        + CWKHomeworkID + ".png";
                File dir = new File(fileUploadConfig.getSavePath() + "/static/PM/Homework/QrCode/");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                myFileUtils.createQRCode(shareUrl,
                        fileUploadConfig.getSavePath() + "/static/PM/Homework/QrCode/cwk-"
                                + CWKHomeworkID + ".png", 200);
                Integer cwkID = null;
                String cwkNickName = "";
                String uid = params.get("Uid");
                String cwkName = params.get("cwkname");

                // 威客登录状态
                if (!StringUtils.isBlank(cwkName)) {
                    TbCwk user = tbCwkService.findByName(cwkName);

                    cwkID = user.getId();
                    cwkNickName = user.getNickname();
                }

                TbCwkhomework tbCwkhomework = tbCwkHomeworkService.selectByPrimaryKey(CWKHomeworkID);
                TbHomework tbhomework = tbHomeworkService.selectByPrimaryKey(tbCwkhomework.getHomeworkid());
                Map<String, Object> pageContent = new LinkedHashMap<>();
                pageContent.put("MIDName", tbCwkhomework.getMidname());
                pageContent.put("StudentName", tbhomework.getStudentname());
                pageContent.put("MDIPortfolio", tbhomework.getMdiportfolio());
                pageContent.put("GradeIMGPath", Constants.WKHOST + tbCwkhomework.getGradeimgpath()
                        + Constants.SITE_VERSION);
                pageContent.put("GradeLevImg", Constants.VIDEOHOST + "/static/PM/Homework/star_three.png");
                pageContent.put("TeacherTimeString", DateUtils.formatDate(tbCwkhomework.getTeachertime()));
                pageContent.put("TeacherComment", tbCwkhomework.getTeachercomment());
                TbCwk teacher = tbCwkService.selectByPrimaryKey(tbCwkhomework.getTeacherid());
                if (teacher != null) {
                    pageContent.put("TeacherName", teacher.getNickname());
                } else {
                    pageContent.put("TeacherName", "");
                }

                //批改后的作业保存在威客
                resultMap.put("shareUrl", shareUrl);
                resultMap.put("shareQrCodePath", shareQrCodePath);
                resultMap.put("pageContent", Lists.newArrayList(pageContent));
                return resultMap;
            }
        } catch (Exception e) {
            logger.error("msg", e);
            return "error";
        }

        return "error, homework not exist";
    }
}

