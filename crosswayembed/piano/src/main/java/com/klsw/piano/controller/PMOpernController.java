package com.klsw.piano.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.piano.service.TbHomeworkService;
import com.klsw.piano.service.TbMidClassService;
import com.klsw.piano.service.TbMidService;
import com.klsw.piano.util.*;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 后台曲谱管理
 *
 * @author HKJ
 */
@Controller
@RequestMapping("/PM")
public class PMOpernController {


    private static final Logger logger = LoggerFactory.getLogger(PMOpernController.class);

    @Resource
    private FileUploadConfig fileUploadConfig;

    @Resource
    private TbMidService tbMidService;

    @Resource
    private TbMidClassService tbMidClassService;

    @Resource
    private TbHomeworkService tbHomeworkService;

    @Resource
    private MyFileUtils myFileUtils;

    /**
     * 后台：曲谱列表
     *
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "PM_MIDList")
    public String songList(HttpServletRequest request, Model model) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }

        String idString = request.getParameter("id");
        Integer id;
        if (StringUtils.isEmpty(idString)) {
            id = 1;
        } else {
            id = new Integer(idString);
        }

        try {
            List<TbMid> midList;
            PageHelper.startPage(id, 24);
            midList = tbMidService.selectByNotNull();

            PageInfo<TbMid> midlist = new PageInfo<>(midList);
            if (midList != null) {
                List<TbMid> list = Lists.newArrayList();
                for (TbMid tbMid : midList) {
                    Integer classID = new Integer(tbMid.getMidclassify());
                    TbMidclass tbMidclass = new TbMidclass();
                    tbMidclass.setId(classID);
                    tbMidclass = tbMidClassService.selectOne(tbMidclass);
                    tbMid.setTypename(tbMidclass.getClassname());
                    list.add(tbMid);
                }
                midlist.setList(list);
            }
            model.addAttribute("midlist", midlist);
        } catch (Exception exception) {
            logger.info(exception.getMessage());
        }
        return "PM/song_list";
    }

    /**
     * 后台：添加（上传）曲谱
     *
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "addMID")
    @ResponseBody
    public String addMID(HttpServletRequest request, @RequestParam("file") MultipartFile fileData) {
        //获取请求参数
        String midName = request.getParameter("midName");//曲谱名
        String midAuthor = request.getParameter("midAuthor");//曲谱作者
        String portfolio = request.getParameter("portfolio");//取自作品集
        String midSize = request.getParameter("midSize");//曲谱大小
        String hmidPath = request.getParameter("hmidPath");//曲谱路径
        String midClassName = request.getParameter("midClassName");//曲谱类别
        hmidPath = hmidPath.replace("\"", "");

        //设置上传文件路径
        String filePath = fileUploadConfig.getSavePath() + "/static/MID/";
        File uploadDir = new File(filePath);
        if (!uploadDir.isDirectory()) {
            if (!uploadDir.mkdirs()) {
                return "<script>alert('文件上传异常！');window.location.href='/PM/toUploadMID'</script>";
            }

        }
        String fileName = fileData.getOriginalFilename();
        File uploadFile = new File(filePath + fileName);

        //判断用户是否登录
        HttpSession session = request.getSession();
        TbPmuser user = (TbPmuser) session.getAttribute("tbPmuser");
        if (user == null) {
            return "<script>alert('请登录后重试！');window.location.href='/User/toLogin'</script>";
        }
     

        try {
            //进行文件上传
            byte[] bytes = fileData.getBytes();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(uploadFile));
            bos.write(bytes);
            bos.close();

            //查询mid类别
            TbMidclass tbMidclass = new TbMidclass();
            tbMidclass.setClassname(midClassName);
            tbMidclass = tbMidClassService.selectOne(tbMidclass);

            //添加曲谱
            TbMid tbMid = new TbMid();
            tbMid.setMidname(midName);
            tbMid.setMidauthor(midAuthor);
            tbMid.setPortfolio(portfolio);
            tbMid.setMidsize(midSize);
            tbMid.setDownloads(0);//设置下载次数
            tbMid.setMidpath(hmidPath);
            tbMid.setAddtime(new Date());//设置添加曲谱时间
            tbMid.setMidclassify(String.valueOf(tbMidclass.getId()));
            tbMidService.insert(tbMid);
            return "<script>alert('添加到数据库成功！');window.location.href='/PM/PM_MIDList'</script>";
        } catch (Exception e) {
            if (uploadFile.exists()) {
                boolean b = uploadFile.delete();//异常时将文件进行删除
            }
            logger.info("msg",e);
            return "<script>alert('添加到数据库异常！');window.location.href='/PM/toUploadMID'</script>";
        }
    }

    /**
     * 后台： 删除曲谱
     *
     * @param request 请求
     * @return 操作结果
     */
    @RequestMapping(value = "deleteMID")
    @ResponseBody
    public String deleteMID(HttpServletRequest request) {
        String idString = request.getParameter("id");
        Integer id;
        if (StringUtils.isEmpty(idString)) {
            return "<script>alert('没有传入要删除的MID序号');window.location.href='/PM/PM_MIDList'</script>";
        } else {
            id = new Integer(idString);
        }
        HttpSession session = request.getSession();
        TbPmuser user = (TbPmuser) session.getAttribute("tbPmuser");
        if (user != null) {        //有管理员权限
            TbMid tbMid = new TbMid();
            tbMid.setMidid(id);
            try {
                tbMidService.delete(tbMid);
                return "<script>alert('删除成功！');window.location.href='/PM/PM_MIDList'</script>";
            } catch (Exception e) {
                logger.info("msg",e);
                return "<script>alert('删除失败！请重试!');window.location.href='/PM/PM_MIDList'</script>";
            }

        } else {
            return "<script>alert('请登录后重试！');window.location.href='/User/toLogin'</script>";
        }
    }

    /**
     * 后台：进入曲谱上传界面
     *
     * @return
     */
    @RequestMapping(value = "toUploadMID")
    public String toUploadMID(Model model, HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }
        List<TbMidclass> tbMidClassList = Lists.newArrayList();
        try {
            tbMidClassList = tbMidClassService.selectAll();
        } catch (Exception e) {
            logger.error("msg",e);
        }
        model.addAttribute("tbMidClassList", tbMidClassList);
        return "PM/upload_opern";
    }


    /**
     * 后台：验证曲谱上传
     *
     * @return
     */
    @RequestMapping(value = "uploadCheck")
    @ResponseBody
    public String uploadMID(HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }

        String fileName = request.getParameter("fileName");
        String filePath = fileUploadConfig.getSavePath() + "/static/MID/";// 文件上传后的保存路径
        if (!StringUtils.isEmpty(fileName)) {
            File uploadDir = new File(filePath);
            if (!uploadDir.isDirectory()) {
                uploadDir.mkdirs();
            }
            File uploadFile = new File(filePath + fileName);
            if (uploadFile.exists()) {
                return "2";  //存在相同文件名的文件
            } else {
                return "/static/MID/" + fileName;
            }
        } else {
            return "1";//传入的文件名为空
        }
    }

    /**
     * 后台：进入曲谱编辑界面
     *
     * @param request 请求
     * @param model   模板
     * @return
     */
    @RequestMapping(value = "toEditMID")
    public String toEditMID(HttpServletRequest request, Model model) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }
        String midID = request.getParameter("id");
        String message = "";
        if (!StringUtils.isEmpty(midID)) {
            TbMid tbMid = new TbMid();
            tbMid.setMidid(new Integer(midID));
            try {
                tbMid = tbMidService.selectOne(tbMid);
                if (tbMid == null) {
                    message = "您需要编辑的曲谱不存在";
                    model.addAttribute("message", message);
                    return "redirect:/PM/PM_MidList";
                }
                TbMidclass tbMidclass = new TbMidclass();
                tbMidclass.setId(new Integer(tbMid.getMidclassify()));
                tbMidclass = tbMidClassService.selectOne(tbMidclass);

                tbMid.setTypename(tbMidclass.getClassname());
                model.addAttribute("tbMid", tbMid);

                List<TbMidclass> tbMidclasses = Lists.newArrayList();
                tbMidclasses = tbMidClassService.selectAll();
                model.addAttribute("tbMidclasses", tbMidclasses);
                return "PM/edit_opern";
            } catch (Exception e) {
                logger.error("msg",e);
                message = "发生异常!请稍后重试!";
                model.addAttribute("message", message);
                return "redirect:/PM/PM_MidList";
            }
        }
        message = "您还没有传入需要编辑的曲谱序列号";
        model.addAttribute("message", message);
        return "redirect:/PM/PM_MidList";
    }

    /**
     * 后台：曲谱编辑
     *
     * @param request  请求
     * @param fileData 文件
     * @return
     */
    @RequestMapping(value = "editMID")
    @ResponseBody
    public String editMID(HttpServletRequest request,
                          @RequestParam("file") MultipartFile fileData) {
        //获取请求参数
        String midIDStr = request.getParameter("midID");//曲谱序列号
        String midName = request.getParameter("midName");//曲谱名
        String midAuthor = request.getParameter("midAuthor");//曲谱作者
        String portfolio = request.getParameter("portfolio");//取自作品集
        String midSize = request.getParameter("midSize");//曲谱大小
        String hmidPath = request.getParameter("hmidPath");//曲谱路径
        String midClassName = request.getParameter("midClassName");//曲谱类别
        hmidPath = hmidPath.replace("\"", "");

        if (StringUtils.isEmpty(midIDStr)) {
            return "<script>alert('更新曲谱失败！请重新尝试!');window.location.href='/PM/PM_MidList'</script>";
        }
        Integer midID = new Integer(midIDStr);
        //设置上传文件路径
        String filePath = fileUploadConfig.getSavePath() + "/static/MID/";
        File uploadDir = new File(filePath);
        if (!uploadDir.isDirectory()) {
            if (!uploadDir.mkdirs()) {
                return "<script>alert('更新曲谱失败！请重新尝试!');window.location.href='/PM/PM_MidList'</script>";
            }
        }
        File uploadFile = null;

        //判断用户是否登录
        HttpSession session = request.getSession();
        TbPmuser user = (TbPmuser) session.getAttribute("tbPmuser");
        if (user == null) {
            return "<script>alert('请登录后重试！');window.location.href='/User/toLogin'</script>";
        }

        try {
            if (!fileData.isEmpty()) {
                String fileName = fileData.getOriginalFilename();
                uploadFile = new File(filePath + fileName);
                //进行文件上传
                byte[] bytes = fileData.getBytes();
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(uploadFile));
                bos.write(bytes);
                bos.close();
            }

            //查询mid类别
            TbMidclass tbMidclass = new TbMidclass();
            tbMidclass.setClassname(midClassName);
            tbMidclass = tbMidClassService.selectOne(tbMidclass);

            //更新曲谱
            TbMid tbMid = new TbMid();
            tbMid.setMidid(midID);
            tbMid = tbMidService.selectOne(tbMid);
            if (tbMid == null) {
                return "<script>alert('更新曲谱异常！');window.location.href='/PM/PM_MidList'</script>";
            }
            tbMid.setMidname(midName);
            tbMid.setMidauthor(midAuthor);
            tbMid.setPortfolio(portfolio);
            tbMid.setMidsize(midSize);
            tbMid.setMidpath(hmidPath);
            tbMid.setAddtime(new Date());//设置更新曲谱时间
            tbMid.setMidclassify(String.valueOf(tbMidclass.getId()));
            tbMid.setDownloads(tbMid.getDownloads());
            tbMidService.updateByPrimaryKeySelective(tbMid);
            return "<script>alert('更新曲谱成功！');window.location.href='/PM/PM_MIDList'</script>";
        } catch (Exception e) {
            if (uploadFile.exists()) {
                uploadFile.delete();//异常时将文件进行删除
            }
            logger.info("msg",e);
            return "<script>alert('更新曲谱异常！');window.location.href='/PM/PM_MidList'</script>";
        }
    }

    @RequestMapping(value = "toSpecialOpern")
    public String toSpecialOpern(HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }

        return "PM/add_specialopern";
    }

    /**
     * 特殊活动增加曲谱生成二维码
     *
     * @param mdiportfolio
     * @param midname
     * @param mp3
     * @param img
     * @param author
     * @param midi
     * @return
     */
    @RequestMapping(value = "addSpecialOpern")
    @ResponseBody
    public Ret addSpecialOpern(@RequestParam("mdiportfolio") String mdiportfolio,
                               @RequestParam("midname") String midname,
                               @RequestParam("mp3") MultipartFile mp3,
                               @RequestParam("img") MultipartFile img,
                               @RequestParam("author") String author,
                               @RequestParam("midi") MultipartFile midi) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
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
            String fullMp3Path = pathForSaving + midname.replace('.', '_').replace(' ', '_').replace('\'', '_') + ".mp3";
            //图片保存路径
            String allFullImgPath = pathForSavingfile + "score.png";
            //mid保存路径
            String allFullFilePath = pathForSavingfile + "record.mid";
            //mp3保存路径
            String allFullMp3Path = pathForSavingfile + midname.replace('.', '_').replace(' ', '_').replace('\'', '_') + ".mp3";
            Ret ret = myFileUtils.uploadfile(allFullImgPath, img, false);
            if (!"S".equals(ret.getStatus())) {
                return Ret.error("保存失败,请重试");
            }
            ret = myFileUtils.uploadfile(allFullFilePath, midi, false);
            if (!"S".equals(ret.getStatus())) {
                return Ret.error("保存失败,请重试");
            }
            ret = myFileUtils.uploadfile(allFullMp3Path, mp3, true);
            if (!"S".equals(ret.getStatus())) {
                return Ret.error("保存失败,请重试");
            }
            TbHomework tbHomework = new TbHomework();
            tbHomework.setMidname(midname);
            tbHomework.setMdiportfolio(mdiportfolio);
            tbHomework.setStudentname(author);
            tbHomework.setMidimgpath(fullImgPath);
            tbHomework.setMidpath(fullFilePath);
            tbHomework.setMp3path(fullMp3Path);
            tbHomework.setFoldername(timenow);
            tbHomework.setTitle(midname);
            tbHomework.setModifytime(new Date());
            tbHomeworkService.insertUseGeneratedKeys(tbHomework);
            tbHomework = tbHomeworkService.selectByPrimaryKey(tbHomework);
            //生成二维码
            int homeworkId = tbHomework.getId();
            String shareQrCodePath = Constants.WEIXINPIC_URL + "/static/PM/Homework/QrCode/cwk-" + homeworkId + ".png";
            String hkID = DES.encrypt("normal" + String.valueOf(homeworkId), "crossway", "waycross");
            String shareUrl = Constants.WEIXIN_URL + "/Open/Work/" + hkID;
            myFileUtils.createQRCode(shareUrl,
                    fileUploadConfig.getSavePath() + "/static/PM/Homework/QrCode/cwk-" + homeworkId
                            + ".png", 200);
            return Ret.success(shareQrCodePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ret.error("保存失败");
    }

}
