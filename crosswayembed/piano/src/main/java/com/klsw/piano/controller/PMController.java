package com.klsw.piano.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.piano.service.TbClassTypeService;
import com.klsw.piano.service.TbClassroomService;
import com.klsw.piano.service.TbCwkService;
import com.klsw.piano.service.TbHomeworkService;
import com.klsw.piano.util.Constants;
import com.klsw.piano.util.FileUploadConfig;
import com.klsw.piano.util.LoginHelper;
import com.klsw.piano.util.MyFileUtils;
import com.klsw.pianoCommon.api.model.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;


/**
 * Created by liukun on 2016/10/31.
 * 琴端
 */
@Controller
@RequestMapping(value = "/pm")
public class PMController {
    private final Logger logger = LoggerFactory.getLogger(PMController.class);

    // 文件上传配置文件
    @Resource
    private FileUploadConfig fileUploadConfig;

    @Resource
    private ExecutorService executor;

    // 文件上传工具类
    @Resource
    private MyFileUtils myFileUtils;

    // 琴上作业相关service
    @Resource
    private TbHomeworkService tbHomeworkService;

    // 用户相关service
    @Resource
    private TbCwkService tbCwkService;

    // 课程类型相关service
    @Resource
    private TbClassTypeService tbClassTypeService;

    // 课程相关service
    @Resource
    private TbClassroomService tbClassroomService;


    /************************************************接口******************************************************/
    /**
     * @param img     mid图片
     * @param rec     mid
     * @param request 请求
     * @param params  参数
     * @return 返回值
     */
    @ResponseBody
    @RequestMapping(value = "/addHomeworkF")
    public Object addHomeworkF(@RequestParam("img") MultipartFile img,
                               @RequestParam("rec") MultipartFile rec,
                               HttpServletRequest request,
                               @RequestParam Map<String, String> params) {
        String uid = params.get("uid");
        String path = params.get("path");
        String uname = params.get("uname");
        String title = params.get("title");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        try {
            if (StringUtils.isBlank(title)) {
                String noTitle = path;

                title = noTitle.substring(noTitle.lastIndexOf("/"));
                title = title.substring(title.indexOf(".") + 1, title.lastIndexOf(".") - 5);
            }

            // yyyyMMddHHmmssSSS
            String timenow = sdf.format(new Date());    // 这个作为一个文件夹 保存到数据库

            // 保存机器路径
            String pathForSavingfile = fileUploadConfig.getSavePath() + "/static/PM/HomeWork/" + timenow + "/";

            // 访问路径
            String pathForSaving = "/static/PM/HomeWork/" + timenow + "/";
            //图片访问路径
            String fullImgPath = pathForSaving + "score.png";
            //mid访问路径
            String fullFilePath = pathForSaving + "record.mid";
            //mp3访问路径
            final String fullMp3Path = pathForSaving + title.replace('.', '_').replace(' ', '_').replace('\'', '_') + ".mp3";
            //图片保存路径
            String allFullImgPath = pathForSavingfile + "score.png";
            //mid保存路径
            String allFullFilePath = pathForSavingfile + "record.mid";

            Ret ret = myFileUtils.uploadfile(allFullImgPath, img, false);
            if (!"S".equals(ret.getStatus())) {
                return "图片保存失败";
            }
            ret = myFileUtils.uploadfile(allFullFilePath, rec, false);
            if (!"S".equals(ret.getStatus())) {
                return "mid保存失败";
            }

            // midi转mp3
            // mid文件存储路径
            String midPath = allFullFilePath;

            // mp3文件存储路径
            final String mp3Path = pathForSavingfile + title.replace('.', '_').replace(' ', '_').replace('\'', '_') + ".mp3";

            // 标题
            String mp3Title = title.replace('.', '_').replace(' ', '_').replace('\'', '_');    // 标题

            // 作者
//            String mp3Author = uname;

            // 所有参数集合
            final String fullPara = midPath + " " + mp3Path; // + " " + mp3Title + " " + mp3Author;

            executor.execute(() -> {
                try {
                    int convert = myFileUtils.midtoMp3(fullPara);
                    File mp3 = new File(mp3Path);
                    if (convert == 0 && mp3.exists()) {
                        myFileUtils.uploadFileToOss(mp3, fullMp3Path, Constants.pianoBucket);
                        mp3.delete();
                    }
                } catch (Exception e) {
                    logger.error("msg", e);
                }
            });

//            int convert = myFileUtils.midtoMp3(fullPara);
//            File mp3 = new File(mp3Path);
//            if (convert != 0) {
//                return "转换文件失败";
//            } else if (mp3.exists()) {
//                OssUtils.uploadFile(mp3, fullMp3Path, Constants.pianoBucket);
//                mp3.delete();
//            } else {
//                return "mp3文件不存在";
//            }

            TbHomework tbHomework = new TbHomework();

            // 包名
            tbHomework.setFoldername(timenow);

            // 机器id
            tbHomework.setUid(uid);

            // 嵌入式mid的全路径
            tbHomework.setPmpath(path);

            // 标题
            tbHomework.setTitle(title);

            // midi访问路径
            tbHomework.setMidpath(fullFilePath);

            // mp3访问路径
            tbHomework.setMp3path(fullMp3Path);

            // midi图片路径
            tbHomework.setMidimgpath(fullImgPath);

            // 学生名
            tbHomework.setStudentname(uname);

            // midi名和标题相同
            tbHomework.setMidname(title);

            // 学生提交时间
            tbHomework.setStudenttime(new Date());
            path = path.substring(path.indexOf("NO.") + 3, path.lastIndexOf("/"));

            // 作品集
            tbHomework.setMdiportfolio(path);

            // 初始modifytime为上传作业的时间
            tbHomework.setModifytime(new Date());

            // 威客用户名参数
            String cwkname = request.getParameter("cwkname");
            // cwkname参数存在，不为空
            if (!StringUtils.isBlank(cwkname)) {
                TbCwk tbCwk = tbCwkService.findByName(cwkname);

                if (tbCwk != null) {

                    // 设置参数CWKID
                    tbHomework.setCwkid(tbCwk.getId());

                    // 初始状态为0
                    tbHomework.setStatus(0);
                }
            }

            tbHomeworkService.insert(tbHomework);
            // 成功
            return "0";
        } catch (Exception e) {
            logger.error("msg", e);

            return "5";
        }
    }
/************************************************后台******************************************************/
    /**
     * 后台：后台主页
     *
     * @return 主页页面
     */
    @RequestMapping(value = "/admin")
    public String toAddmin(HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }
        return "manage/index";
    }

    /**
     * 后台：添加课堂类别页面
     *
     * @return 添加结果
     */
    @RequestMapping(value = "/toAddCategory")
    public String toAddCategory(HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }
        return "PM/add_category";
    }

    /**
     * 后台：添加课程类别
     *
     * @param tbClasstype 课程类别
     * @return 添加结果
     */
    @RequestMapping(value = "/addCategory")
    @ResponseBody
    public String addCategory(TbClasstype tbClasstype, HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "<script>alert('您还未登录');window.location='/user/toLogin';</script>";
        }

        try {
            tbClassTypeService.insert(tbClasstype);

            return "<script>alert('添加成功！'); window.location='/PM/toAddCategory';</script>";
        } catch (Exception e) {
            logger.error("msg", e);
            return "<script>alert('添加失败！'); window.location='/PM/toAddCategory';</script>";
        }
    }

    @RequestMapping(value = "/Authenticate")
    public void testIp(HttpServletRequest request,
                       HttpServletResponse response) {
        BASE64Encoder encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            InputStream stream = request.getInputStream();
            byte[] bytes = decoder.decodeBuffer(stream);
            if (bytes.length == 0) {
                response.setStatus(400);
                return;
            }
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(hexStringToBytes("4be17505315731"));
            md.update(bytes);
            md.update(hexStringToBytes("3157310575e14b"));
            pw.write(encoder.encode(md.digest()));
            pw.flush();
        } catch (Exception e) {
            logger.error("msg", e);
            response.setStatus(400);
        }
    }

    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 后台：课堂类别主页
     *
     * @param params 参数
     * @return 主页地址
     */
    @RequestMapping(value = "/toCategoryList")
    public String toCategoryList(@RequestParam Map<String, String> params,
                                 HttpServletRequest request, Model model) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }

        try {
            Integer pageId = (params.get("pageId") == null)
                    ? 1
                    : Integer.parseInt(params.get("pageId"));

            PageHelper.startPage(pageId, 10);
            PageHelper.orderBy("ID");

            List<TbClasstype> tbClasstypeList = tbClassTypeService.selectAll();
            PageInfo<TbClasstype> pageInfo = new PageInfo<>(tbClasstypeList);

            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error("msg", e);
        }

        return "PM/category_list";
    }

    /**
     * 后台：修改课堂类别页面
     *
     * @param id 课堂id
     * @return
     */
    @RequestMapping(value = "/toEditCategory")
    public Object toEditClassType(@RequestParam("id") Integer id, HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "<script>alert('您还未登录');window.location='/user/toLogin'</script>";
        }
        ModelAndView mav = new ModelAndView("/PM/edit_category");

        try {
            TbClasstype tbClasstype = tbClassTypeService.selectByPrimaryKey(id);

            mav.addObject("classType", tbClasstype);

            return mav;
        } catch (Exception e) {
            logger.error("msg", e);

            return "<script>alert('出现异常！'); window.location='/PM/toCategoryList';</script>";
        }
    }

    /**
     * 后台：修改课堂类别
     *
     * @param tbClasstype 课程类别
     * @return 修改结果
     */
    @RequestMapping(value = "/editCategory")
    @ResponseBody
    public String editCategory(TbClasstype tbClasstype, HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "<script>alert('您还未登录');window.location='/user/toLogin'</script>";
        }

        try {
            tbClassTypeService.updateByPrimaryKeySelective(tbClasstype);

            return "<script>alert('添加成功！'); window.location='/PM/toCategoryList';</script>";
        } catch (Exception e) {
            logger.error("msg", e);

            return "<script>alert('出现异常！'); window.location='/PM/toCategoryList';</script>";
        }
    }

    /**
     * 后台：删除课堂类型
     *
     * @param id 课堂id
     * @return 删除结果
     */
    @RequestMapping(value = "/deleteClassType")
    @ResponseBody
    public Ret deleteClassType(@RequestParam("id") Integer id, HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return Ret.error("您还未登录");
        }

        try {
            tbClassTypeService.deleteByPrimaryKey(id);

            return Ret.success();
        } catch (Exception e) {
            logger.error("msg", e);

            return Ret.error("删除失败");
        }
    }

    /**
     * 后台：添加课堂页面
     *
     * @return 添加课程
     */
    @RequestMapping(value = "/toAddClass")
    public String toAddClass(HttpServletRequest request, Model model) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }

        try {
            List<TbClasstype> tbClasstypeList = tbClassTypeService.selectAll();

            model.addAttribute("list", tbClasstypeList);
        } catch (Exception e) {
            logger.error("msg", e);
        }

        return "PM/add_class";
    }

    /**
     * 后台：添加课堂
     *
     * @param tbClassroom 课堂
     * @return
     */
    @RequestMapping(value = "/addClass")
    @ResponseBody
    public String addClass(TbClassroom tbClassroom, HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "<script>alert('您还未登录');window.location='/user/toLogin'</script>";
        }

        tbClassroom.setAdddatetime(new Date());

        // 处理路径参数
        tbClassroom.setVideopath(tbClassroom.getVideopath().replace("\\", "/"));
        tbClassroom.setImgpath(tbClassroom.getImgpath().replace("\\", "/"));

        try {
            tbClassroomService.insert(tbClassroom);

            return "<script>alert('添加成功！'); window.location='/PM/toAddClass';</script>";
        } catch (Exception e) {
            logger.error("msg", e);
        }

        return "<script>alert('添加失败！'); window.location='/PM/toAddClass';</script>";
    }

    /**
     * 后台：删除课堂
     *
     * @param id 课堂id
     * @return
     */
    @RequestMapping(value = "/deleteClassRoom")
    @ResponseBody
    public Ret deleteClassRoom(@RequestParam("id") Integer id, HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return Ret.error("您还未登录");
        }

        try {
            tbClassroomService.deleteByPrimaryKey(id);

            return Ret.success();
        } catch (Exception e) {
            logger.error("msg", e);

            return Ret.error("删除失败");
        }
    }

    /**
     * 后台：修改课堂内容页面
     *
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/toEditClass")
    public Object toEditClassRoom(@RequestParam("id") Integer id,
                                  HttpServletRequest request, HttpServletResponse response) {
        if (!LoginHelper.validate(request)) {
            return "<script>alert('您还未登录');window.location='/user/toLogin';</script>";
        }

        ModelAndView mav = new ModelAndView("/PM/edit_class");

        try {
            TbClassroom tbClassroom = tbClassroomService.selectByPrimaryKey(id);
            List<TbClasstype> tbClasstypeList = tbClassTypeService.selectAll();

            mav.addObject("list", tbClasstypeList);
            mav.addObject("classroom", tbClassroom);
        } catch (Exception e) {
            logger.error("msg", e);

            return "<script>alert('出现异常！'); window.location='/PM/toClassList';</script>";
        }

        return mav;
    }

    /**
     * 后台：修改课堂内容
     *
     * @param tbClassroom 课堂
     * @return
     */
    @RequestMapping(value = "editClass")
    @ResponseBody
    public String editClass(TbClassroom tbClassroom, HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "<script>alert('您还未登录');window.location='/user/toLogin'</script>";
        }

        try {
            tbClassroomService.updateByPrimaryKeySelective(tbClassroom);

            return "<script>alert('修改成功！'); window.location='/PM/toClassList';</script>";
        } catch (Exception e) {
            logger.error("msg", e);

            return "<script>alert('出现异常！'); window.location='/PM/toClassList';</script>";
        }
    }

    /**
     * 后台：课堂列表页面
     *
     * @param params 参数列表
     * @return
     */
    @RequestMapping(value = "/toClassList")
    public String toClassList(@RequestParam Map<String, String> params,
                              Model model, HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }
        String type = params.get("type");
        Integer typeId = StringUtils.isNumeric(type) ? Integer.parseInt(type) : 8;
        try {
            Integer pageId = (params.get("pageId") == null)
                    ? 1
                    : Integer.parseInt(params.get("pageId"));

            PageHelper.startPage(pageId, 10);
            PageHelper.orderBy("ID");

            List<TbClassroom> tbClassroomList = tbClassroomService.selectClassroomList(typeId);
            PageInfo<TbClassroom> pageInfo = new PageInfo<>(tbClassroomList);

            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error("msg", e);
        }

        return "PM/class_list";
    }
}
