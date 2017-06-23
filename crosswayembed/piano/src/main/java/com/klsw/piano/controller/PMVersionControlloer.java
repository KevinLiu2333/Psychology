package com.klsw.piano.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.klsw.piano.service.TbVersionService;
import com.klsw.piano.util.*;
import com.klsw.pianoCommon.api.model.Ret;
import com.klsw.pianoCommon.api.model.TbPmuser;
import com.klsw.pianoCommon.api.model.TbVersion;
import org.apache.commons.lang3.StringUtils;
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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/PMVersion")
public class PMVersionControlloer {
    private static final Logger log = LoggerFactory.getLogger(PMVersionControlloer.class);
    private static final int PAGESIZE = 5;
    private static String TEST_IP = "112.27.209.151";
    private static String IP_REGEX = "(192.168.1.)(25[0-4]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
    private static String IP_REGEX2 = "^(36.5.15)\\d\\.\\d{2,3}$";
//    public static void main(String[] args) {
//        System.out.println("36.5.157.33".matches(IP_REGEX2));
//        System.out.println("36.5.157.333".matches(IP_REGEX2));
//        System.out.println("36.5.157.3333".matches(IP_REGEX2));
//        System.out.println("36.5.154.33".matches(IP_REGEX2));
//        System.out.println("36.5.157.333".matches(IP_REGEX2));
//        System.out.println("36.5.147.33".matches(IP_REGEX2));
//    }

    @Resource
    private TbVersionService tbVersionService;

    @Resource
    private MyFileUtils myFileUtils;

    @Resource
    private TbVersionService versionService;

    @Resource
    private FileUploadConfig fileUploadConfig;


    /************************************* 接口部分 *****************************************/
    /**
     * 接口：查询版本列表(选择升级版本)
     *
     * @param params 参数列表
     * @return 版本列表
     */
    @RequestMapping(value = "/getVersionList", method = RequestMethod.POST)
    @ResponseBody
    public Object getVersionList(@RequestParam Map<String, String> params, HttpServletRequest request) {
        Map<String, Object> resultMap = Maps.newLinkedHashMap();
        List<TbVersion> tbVersions;
        String ip = HttpUtils.getIpAddr(request);
        String multiBlock = request.getParameter("multiBlock");
        try {
            //页码参数
            Integer pageId = StringUtils.isNumeric(params.get("PageId")) ? Integer.parseInt(params.get("PageId")) : 1;
            String versionNo = params.get("VersionNo");
            TbVersion myVersion = tbVersionService.selectByVersionNo(versionNo);
            if (myVersion == null || myVersion.getVersiondate() == null || myVersion.getParentversionno() == null) {
                return "fail";
            }
            //分页
            PageHelper.startPage(pageId, PAGESIZE);
            //公司网络显示隐藏版本
            if (TEST_IP.equals(ip) || ip.matches(IP_REGEX) || ip.matches(IP_REGEX2)) {
                tbVersions = tbVersionService.selectLaterVersion2(myVersion);
            } else {
                tbVersions = tbVersionService.selectLaterVersion(myVersion);
            }
            List<Map<String, Object>> pageContent = new ArrayList<>();
            for (TbVersion entity : tbVersions) {
                Map<String, Object> map = new LinkedHashMap<>();
                //版本id相同,当前版本
                if (entity.getVersionid().equals(myVersion.getVersionid())) {
                    map.put("CanUpdateDiscribe", "当前版本,Current version");
                } else {
                    map.put("CanUpdateDiscribe", "可以升级到此版本,Can update");
                }
                map.put("VersionID", entity.getVersionid());
                map.put("VersionNo", entity.getVersionno());
                map.put("VersionDate", entity.getVersiondate());
                map.put("VersionDescription", entity.getVersiondescription());
                map.put("VersionSize", entity.getVersionsize());
                String versionurl = entity.getVersionpath();
                //分块下载处理
                if (!StringUtils.isBlank(multiBlock) && multiBlock.equals("true")) {
                    if (versionurl.contains(";")) {
                        String[] sp = versionurl.split(";");
                        map.put("VersionPath", Constants.VIDEOHOST + sp[0] + Constants.SITE_VERSION + ";" + sp[1]);
                    } else {
                        map.put("VersionPath", Constants.VIDEOHOST + entity.getVersionpath() + Constants.SITE_VERSION);
                    }

                } else {
                    versionurl = entity.getVersionpath().contains(";") ? entity.getVersionpath().substring(0, entity.getVersionpath().lastIndexOf(";")) : entity.getVersionpath();

                    String path = Constants.VIDEOHOST + versionurl + Constants.SITE_VERSION;
                    map.put("VersionPath", path);
                }
                map.put("VersionPlatform", entity.getVersionplatform());
                map.put("VersionUpgradeNo", entity.getVersionupgradeno());
                map.put("UserName", entity.getUsername());
                map.put("IsForceUpgrade", entity.getIsforceupgrade());
                map.put("IsShowInVersionList", entity.getIsshowinversionlist());
                map.put("IsTestVersion", entity.getIstestversion());
                map.put("ParentVersionNo", entity.getParentversionno());
                map.put("About", entity.getAbout());
                pageContent.add(map);
            }
            PageInfo<TbVersion> pageInfo = new PageInfo<>(tbVersions);
            resultMap.put("currentPageIndex", pageInfo.getPageNum());
            resultMap.put("totalPageCount", pageInfo.getPages());
            resultMap.put("pageContent", pageContent);
            return resultMap;
        } catch (Exception e) {
            log.error("msg", e);
            return "fail";
        }
    }

    /**
     * 接口：获取历史版本
     *
     * @param request 请求
     * @return 返回结果
     */
    @RequestMapping(value = "/getHistoryVersionList", method = RequestMethod.POST)
    @ResponseBody
    public Object getHistoryVersion(HttpServletRequest request) {
        String idStr = request.getParameter("PageId"); // 传过来的当前页码
        Integer id = 1;
        if (!StringUtils.isEmpty(idStr)) {
            id = new Integer(idStr);
        }
        String VersionNo = request.getParameter("VersionNo"); // 钢琴当前版本号
        String VersionPlatform = request.getParameter("VersionPlatform"); //
        //钢琴平台
        String Uid = request.getParameter("Uid"); // 钢琴 Serial Number
        //分页
        PageHelper.startPage(id, PAGESIZE);
        List<TbVersion> versionList = Lists.newArrayList();
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            TbVersion tbVersion = new TbVersion();
            tbVersion.setIsshowinversionlist(true);
            String ip = HttpUtils.getIpAddr(request);
            if (ip.matches(IP_REGEX) || TEST_IP.equals(ip) || ip.matches(IP_REGEX2)) {
                //内部ip
                PageHelper.orderBy("VersionNo desc");
                versionList = versionService.select(tbVersion);
            } else {
                //外部ip
                versionList = versionService.selectHistoryVersion();
            }
            List<Map<String, Object>> pageContent = new ArrayList<>();
            for (TbVersion entity : versionList) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("VersionNo", entity.getVersionno());
                map.put("VersionDate", entity.getVersiondate());
                map.put("VersionDescription", entity.getVersiondescription());
                map.put("VersionSize", entity.getVersionsize());
                map.put("VersionPath", Constants.VIDEOHOST + entity.getVersionpath() + Constants.SITE_VERSION);
                map.put("About", entity.getAbout());
                pageContent.add(map);
            }
            PageInfo<TbVersion> pageInfo = new PageInfo<>(versionList);
            result.put("currentPageIndex", pageInfo.getPageNum());
            result.put("totalPageCount", pageInfo.getPages() == 0 ? 1 : pageInfo.getPages());
            result.put("pageContent", pageContent);
            return result;
        } catch (Exception e) {
            log.error("msg", e);
            result.put("message", e.getMessage());
            return result;
        }
    }

    /*************************************** 后台部分 ***********************************/


//	/**
//	 * 后台：添加版本
//	 *
//	 * @param params
//	 * @param file
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping("/PM_VersionAdd")
//	public String PM_VersionAdd(@RequestParam Map<String, String> params, @RequestParam("file") MultipartFile file,
//			HttpServletResponse response) {
//
//		TbVersion tbVersion = new TbVersion();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//		// Boolean.parseBoolean()如果值为null则返回空
//		// 是否为测试版
//		boolean isTestVersion = Boolean.parseBoolean(params.get("isTestVersion"));
//		// 是否强制升级
//		boolean isForceUpgrade = Boolean.parseBoolean(params.get("isForceUpgrade"));
//		// 是否显示在版本列表
//		boolean isShowInVersionList = Boolean.parseBoolean(params.get("isShowInVersionList"));
//		try {
//			if (file.isEmpty()) {
//				response.getWriter()
//						.println("<script>alert('请选择版本文件！'); window.location='/PM/PM_VersionList';</script>");
//				return null;
//			}
//
//			// 设置版本号
//			tbVersion.setVersionno(params.get("VersionNo"));
//
//			// 设置父版本号
//			tbVersion.setParentversionno(params.get("parentVersionNo"));
//
//			// 设置版本时间
//			tbVersion.setVersiondate(sdf.parse((params.get("VersionDate")).trim()));
//
//			// 版本大小
//			tbVersion.setVersionsize(params.get("versionSize"));
//
//			// 版本平台号
//			tbVersion.setVersionplatform(params.get("versionPlatform"));
//
//			// 版本描述
//			tbVersion.setVersiondescription("versionDescription");
//
//			//版本更新内容说明
//			tbVersion.setAbout("about");
//
//			// 是否为测试版本
//			tbVersion.setIstestversion(isTestVersion);
//
//			// 是否强制升级
//			tbVersion.setIsforceupgrade(isForceUpgrade);
//
//			// 是否显示在版本列表
//			tbVersion.setIsshowinversionlist(isShowInVersionList);
//
//			// 获取上传文件原文件名
//			String fileName = file.getOriginalFilename();
//			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
//
//			String url = "/static/PM/upgrade/" + params.get("versionNo") + "/";
//			tbVersion.setVersionpath(url + fileName);
//			Ret ret = myFileUtils.uploadfile(fileName, url, file);
//			if ("S".equals(ret.getStatus())) {
//				tbVersionService.insert(tbVersion);
//				response.getWriter().println("<script>alert('添加成功！'); window.location='/PM/PM_VersionList';</script>");
//				return null;
//			} else {
//				response.getWriter().println("<script>alert('添加失败！请重试！'); history.go(-1);</script>");
//				return null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "";
//		}
//	}


    /**
     * 后台：系统版本列表
     *
     * @param request 请求
     * @param model   模板
     * @return 返回值
     */
    @RequestMapping(value = "versionList")
    public String versionList(HttpServletRequest request, Model model) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }

        String pageStr = request.getParameter("pageId");
        Integer pageNum = null;
        if (StringUtils.isEmpty(pageStr)) {
            pageNum = 1;
        } else {
            pageNum = new Integer(pageStr);
        }
        PageHelper.startPage(pageNum, 8);
        PageHelper.orderBy("VersionNo DESC");

        List<TbVersion> versionList = Lists.newArrayList();
        try {
            versionList = versionService.selectAll();
            PageInfo<TbVersion> pageInfo = new PageInfo<>(versionList);
            model.addAttribute("versionList", pageInfo);
            return "PMVersion/version_list";
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
            return "manage/index";
        }
    }

    /**
     * 后台：进入添加版本界面
     *
     * @return 返回页面
     */
    @RequestMapping(value = "toAddVersion")
    public String PM_VersionAdd(HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }
        return "PMVersion/add_version";
    }

    /**
     * 后台：添加版本
     *
     * @param filehide 文件
     * @param request  请求
     * @return 返回值
     */
    @RequestMapping(value = "addVersion")
    @ResponseBody
    public String PM_VersionAdd(@RequestParam("file") MultipartFile[] filehide, HttpServletRequest request) {
        HttpSession session = request.getSession();
        TbPmuser user = (TbPmuser) session.getAttribute("tbPmuser");
        if (user == null) {
            return "<script>alert('请登录后重试！');window.location.href='/User/toLogin'</script>";
        }
        File file;
        FileOutputStream fos;
        String blockName;
        BufferedInputStream bis;
        int bufferSize = 1024 * 1024 * 10;
        byte[] bytes = new byte[bufferSize];
        int fileIndex = 1;
        try {
            String isTestVersion = request.getParameter("isTestVersion");
            String isForceUpgrade = request.getParameter("isForceUpgrade");
            String isShowInVersionList = request.getParameter("isShowInVersionList");
            TbVersion tbVersion = new TbVersion();
            tbVersion.setVersionno(request.getParameter("versionNo"));
            tbVersion.setParentversionno(request.getParameter("parentVersionNo"));
            tbVersion.setVersiondate(
                    new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("VersionDate").trim()));
            tbVersion.setVersionsize(request.getParameter("versionSize"));
            tbVersion.setVersionplatform(request.getParameter("versionPlatform"));
            tbVersion.setVersiondescription(request.getParameter("versionDescription"));
            tbVersion.setIstestversion("on".equals(isTestVersion));
            tbVersion.setIsforceupgrade("on".equals(isForceUpgrade));
            tbVersion.setIsshowinversionlist("on".equals(isShowInVersionList));
            tbVersion.setAbout(request.getParameter("about"));
            if (filehide != null) {
                // 获取上传的原文件名
                String fileName = filehide[0].getOriginalFilename();
                fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

                // 定义文件保存路径
                String folder = fileUploadConfig.getSavePath() + "/static/PM/upgrade/" + tbVersion.getVersionno() + "/";
                String filePath = folder + fileName;

                // 设置版本保存地址
                String versionPath = "/static/PM/upgrade/" + tbVersion.getVersionno();

                log.info("上传文件路径:" + versionPath);
                log.info("上传文件名:" + fileName);
                //上传完整文件
                Ret ret = myFileUtils.uploadfile(filePath, filehide[0], true);
                if (!"S".equals(ret.getStatus())) {
//                    // 插入版本信息
//                    versionService.insert(tbVersion);
//                    return "<script>alert('添加成功！'); window.location.href='/PMVersion/versionList';</script>";
                    return "<script>alert('添加失败！请重试！'); history.go(-1);</script>";
                }
                //文件分块
                bis = new BufferedInputStream(filehide[0].getInputStream());
                while (bis.available() > 0) {
                    if (bis.available() < bufferSize) {
                        bytes = new byte[bis.available()];
                    }
                    blockName = fileIndex + ".pkg";
                    file = new File(folder + blockName);
                    fos = new FileOutputStream(file);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    bis.read(bytes);
                    bos.write(bytes);
                    bos.flush();
                    bos.close();
                    log.info("第" + fileIndex + "份文件");
                    if (!myFileUtils.uploadFileToOss(file, versionPath + "/" + blockName, Constants.pianoBucket)) {
                        return "<script>alert('添加失败！请重试！'); history.go(-1);</script>";
                    }
                    fileIndex++;
                }
                bis.close();
                System.out.println("结束,共" + fileIndex + "份文件");
                tbVersion.setVersionpath(versionPath + "/" + fileName + ";" + (fileIndex - 1));
            }
            versionService.insert(tbVersion);
            return "<script>alert('添加成功！'); window.location.href='/PMVersion/versionList';</script>";
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());

        }
        return "<script>alert('添加失败！请重试！'); history.go(-1);</script>";
    }

    /**
     * 后台：进入版本信息修改界面
     *
     * @param request 请求
     * @param model   页面
     * @return 返回信息
     */
    @RequestMapping(value = "toMdyVersion")
    public String toMdyVersion(HttpServletRequest request, Model model) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }
        // 获取版本序号
        String idString = request.getParameter("id");

        try {
            // 查询该版本序号对应版本信息
            TbVersion tbVersion = new TbVersion();
            tbVersion.setVersionid(new Integer(idString));
            tbVersion = versionService.selectOne(tbVersion);
            // 绑定信息到页面
            model.addAttribute("tbVersion", tbVersion);
            return "PMVersion/modify_version";
        } catch (Exception e) {
            log.error("msg", e);
            return "redirect:/PMVersion/versionList";
        }
    }

    /**
     * 后台：修改版本信息
     *
     * @param request 请求
     * @return 返回信息
     */
    @RequestMapping(value = "mdyVersion")
    @ResponseBody
    public String MdyVersion(HttpServletRequest request) {
        // 登录判断（登录成功才有权限）
        HttpSession session = request.getSession();
        TbPmuser user = (TbPmuser) session.getAttribute("tbPmuser");
        if (user == null) {
            return "<script>alert('请登录后重试！');window.location.href='/User/toLogin'</script>";
        }

        // 获取需要修改的版本序号
        String idStr = request.getParameter("id");

        // 获取页面传过来的需要修改的参数
        String isForceUpgradeStr = request.getParameter("isForceUpgrade");
        String isShowInVersionListStr = request.getParameter("isShowInVersionList");
        String isTestVersionStr = request.getParameter("isTestVersion");

        try {
            Integer id = null;
            Boolean isForceUpgrade = null;
            Boolean isShowInVersionList = null;
            Boolean isTestVersion = null;

            if (!StringUtils.isEmpty(idStr)) {
                id = new Integer(idStr);
            }
            if (!StringUtils.isEmpty(isForceUpgradeStr)) {
                isForceUpgrade = Boolean.parseBoolean(isForceUpgradeStr);
            }
            if (!StringUtils.isEmpty(isShowInVersionListStr)) {
                isShowInVersionList = Boolean.parseBoolean(isShowInVersionListStr);
            }
            if (!StringUtils.isEmpty(isTestVersionStr)) {
                isTestVersion = Boolean.parseBoolean(isTestVersionStr);
            }

            TbVersion tbVersion = new TbVersion();
            tbVersion.setVersionid(id);
            tbVersion = versionService.selectOne(tbVersion);
            tbVersion.setIsshowinversionlist(isShowInVersionList);
            tbVersion.setIsforceupgrade(isForceUpgrade);
            tbVersion.setIstestversion(isTestVersion);

            // 进行版本更新
            versionService.updateByPrimaryKey(tbVersion);

            return "<script>alert('修改成功！');window.location.href='/PMVersion/versionList'</script>";
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }
        return "<script>alert('修改失败！请重试！');history.go(-1);</script>";
    }

    /**
     * 后台：删除版本
     *
     * @param request 请求
     * @return 返回信息
     */
    @RequestMapping(value = "deleteVersion")
    @ResponseBody
    public String deleteVersion(HttpServletRequest request) {
        // 登录判断（成功登录才有权限）
        HttpSession session = request.getSession();
        TbPmuser user = (TbPmuser) session.getAttribute("tbPmuser");
        if (user == null) {
            return "<script>alert('请登录后重试！');window.location.href='/User/toLogin'</script>";
        }

        // 获取要删除的版本序号
        String idStr = request.getParameter("id");
        Integer id = null;
        if (!StringUtils.isEmpty(idStr)) {
            id = new Integer(idStr);
        }

        try {
            TbVersion tbVersion = new TbVersion();
            tbVersion.setVersionid(id);
            tbVersion = versionService.selectOne(tbVersion);

            // 获取需要删除的文件路径
            String filePath = fileUploadConfig.getSavePath() + "/static/PM/upgrade/" + tbVersion.getVersionno() + "/";

            // 删除文件及文件夹
            clearFile(filePath);

            // 删除版本
            versionService.delete(tbVersion);

            return "<script>alert('删除成功！');window.location.href='/PMVersion/versionList'</script>";
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }
        return "<script>alert('删除失败！请重试！');history.go(-1);</script>";
    }

    /*************************************** 方法部分 ***********************************/
    /**
     * 方法功能：清理文件
     *
     * @param filePath
     */
    public static void clearFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            deleteFile(file);
        }
    }

    /**
     * 方法功能：删除文件（夹）具体方法
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i]);
            }
        }
        file.delete();
    }
}
