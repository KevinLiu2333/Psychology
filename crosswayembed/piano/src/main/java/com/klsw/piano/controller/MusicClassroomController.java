package com.klsw.piano.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.piano.service.TbMusicClassroomService;
import com.klsw.piano.util.Constants;
import com.klsw.piano.util.FileUploadConfig;
import com.klsw.piano.util.MyFileUtils;
import com.klsw.pianoCommon.api.model.Ret;
import com.klsw.pianoCommon.api.model.TbMusicclassroomVersion;
import com.klsw.pianoCommon.api.model.TbPmuser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * Created by liukun on 2017/3/4.
 * 音乐教室
 */
@Controller
@RequestMapping(value = "musicClassroom")
public class MusicClassroomController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicClassroomController.class);

    @Resource
    private TbMusicClassroomService classroomService;

    @Resource
    private MyFileUtils myFileUtils;

    @Resource
    private FileUploadConfig fileUploadConfig;


    @RequestMapping(value = "toAddVersion", method = RequestMethod.GET)
    public String toAddVersion(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TbPmuser user = (TbPmuser) session.getAttribute("tbPmuser");
        if (user == null) {
            return "/user/login";
        }
        return "musicclassroom/add_Version";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "pageNum", required = false) Integer pageNum, HttpServletRequest request) {
        TbPmuser user = (TbPmuser) request.getSession().getAttribute("tbPmuser");
        if (user == null) {
            return "/user/login";
        }
        pageNum = pageNum == null ? 1 : pageNum;
        PageHelper.startPage(pageNum, 5);
        try {
            List<TbMusicclassroomVersion> list = classroomService.selectAll();
            PageInfo<TbMusicclassroomVersion> pageInfo = new PageInfo<>(list);
            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception ignore) {
        }
        return "musicclassroom/list";
    }

    /**
     * @param apk           apk文件
     * @param opern         曲谱库文件
     * @param apk_version   apk版本
     * @param opern_version 曲谱库版本
     * @param isForceUpdate 是否强制升级
     * @return 添加结果
     */
    @RequestMapping(value = "addVersion", method = RequestMethod.POST)
    @ResponseBody
    public Ret addVersion(@RequestParam(value = "version") MultipartFile apk,
                          @RequestParam(value = "opern") MultipartFile opern,
                          @RequestParam(value = "apk_version") String apk_version,
                          @RequestParam(value = "opern_version") String opern_version,
                          @RequestParam(value = "isForceUpdate") Boolean isForceUpdate) {

        try {
            TbMusicclassroomVersion musicclassroomVersion = new TbMusicclassroomVersion();
            Ret ret;
            //常规参数
            musicclassroomVersion.setApkVersion(apk_version);
            musicclassroomVersion.setOpernVersion(opern_version);
            musicclassroomVersion.setIsforceupdate(isForceUpdate);
            //文件
            if (apk != null) {
                //转文件
                String apkUri = "/static/musicroom/" + apk_version + "/upgrade.apk";
                ret = myFileUtils.multipartFileToFile(fileUploadConfig.getSavePath() + apkUri, apk);
                if ("S".equals(ret.getStatus())) {
                    File _apk = (File) ret.getdata();
                    String apk_size = String.valueOf(_apk.length());
                    String apk_md5 = MyFileUtils.getMd5ByFile(_apk);
                    musicclassroomVersion.setApkPath(apkUri);
                    musicclassroomVersion.setApkSize(apk_size);
                    musicclassroomVersion.setApkMd5(apk_md5);
                    if (!myFileUtils.uploadFileToOss(_apk, apkUri, Constants.pianoBucket)) {
                        return Ret.error("保存失败");
                    }
                    _apk.delete();
                } else {
                    return Ret.error("保存失败");
                }
            }
            if (opern != null) {
                String opernUri = "/static/musicroom/" + apk_version + "/opern.db";
                ret = myFileUtils.multipartFileToFile(fileUploadConfig.getSavePath() + opernUri, opern);
                if ("S".equals(ret.getStatus())) {
                    File _opern = (File) ret.getdata();
                    String opern_size = String.valueOf(_opern.length());
                    String opern_md5 = MyFileUtils.getMd5ByFile(_opern);
                    musicclassroomVersion.setOpernSize(opern_size);
                    musicclassroomVersion.setOpernMd5(opern_md5);
                    musicclassroomVersion.setOpernPath(opernUri);
                    if (!myFileUtils.uploadFileToOss(_opern, opernUri, Constants.pianoBucket)) {
                        return Ret.error("保存失败");
                    }
                    _opern.delete();
                } else {
                    return Ret.error("保存失败");
                }
            }
            classroomService.insert(musicclassroomVersion);
            return Ret.success();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("msg", e);
            return Ret.error("保存失败");
        }

    }

    @RequestMapping(value = "version")
    @ResponseBody
    public Object getVersion() {

        try {
            TbMusicclassroomVersion musicclassroomVersion = classroomService.getVersion();
            musicclassroomVersion.setApkPath(Constants.VIDEOHOST + musicclassroomVersion.getApkPath());
            musicclassroomVersion.setOpernPath(Constants.VIDEOHOST + musicclassroomVersion.getOpernPath());
            return musicclassroomVersion;
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("查询失败");
        }
    }
}
