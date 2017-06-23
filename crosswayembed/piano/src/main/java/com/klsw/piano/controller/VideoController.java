package com.klsw.piano.controller;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.piano.service.TbVideoService;
import com.klsw.piano.util.Constants;
import com.klsw.piano.util.FileUploadConfig;
import com.klsw.piano.util.LoginHelper;
import com.klsw.pianoCommon.api.model.TbVideo;
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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * 视频相关操作
 * 
 * @author HKJ
 *
 */
@RequestMapping(value = "/video")
@Controller
public class VideoController {
	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

	@Resource
	private TbVideoService videoService;

	@Resource
	private FileUploadConfig fileUploadConfig;

	/**
	 * 后台：视频列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "videoList")
	public String videoList(HttpServletRequest request, Model model) {
		if (!LoginHelper.validate(request)) {
			return "redirect:/user/toLogin";
		}

		String pageNum = request.getParameter("pageId");
		Integer pageId = 1;
		if (!StringUtils.isEmpty(pageNum)) {
			pageId = new Integer(pageNum);
		}

		PageHelper.startPage(pageId, Constants.PAGE_SIZE);
		List<TbVideo> videoList;
		try {
			videoList = videoService.selectAll();
		} catch (Exception e) {
			logger.error("msg",e);
			return "redirect:/pm/admin";
		}
		PageInfo<TbVideo> pageInfo = new PageInfo<>(videoList);
		model.addAttribute("videoList", pageInfo);

		return "video/video_list";
	}

	/**
	 * 后台：进入添加视频界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toAddVideo")
	public String toAddVideo(HttpServletRequest request) {
		if (!LoginHelper.validate(request)) {
			return "redirect:/user/toLogin";
		}

		return "video/add_video";
	}

	/**
	 * 后台：添加视频功能
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addVideo")
	@ResponseBody
	public String addVideo(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		// 判断是否登录
		if (!LoginHelper.validate(request)) {
			return "<script>alert('您还未登录!');window.location.href='/user/toLogin';</script>";
		}

		// 获取页面传入的参数
		String videoName = request.getParameter("videoname");// 视频名称
		String videoSize = request.getParameter("videosize");// 视频大小
		String videoDate = request.getParameter("videodate");// 视频日期

		// 判断视频名称是否传入
		if (StringUtils.isEmpty(videoName)) {
			return "<script>alert('视频名称不能为空');history.go(-1);</script>";
		}

		// 判断视频日期是否传入
		if (StringUtils.isEmpty(videoDate)) {
			return "<script>alert('视频日期不能为空');history.go(-1);</script>";
		}

		// 判断文件是否上传
		if (file.isEmpty()) {
			return "<script>alert('视频未上传');history.go(-1);</script>";
		}

		// 定义文件保存路径
		String filePath = fileUploadConfig.getSavePath() + "/static/video/";

		// 创建文件保存目录
		File uploadDir = new File(filePath);
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}

		// 创建文件
		String fileName = file.getOriginalFilename();
		File uploadFile = new File(filePath + fileName);

		try {
			// 文件上传
			byte[] bytes = file.getBytes();
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(uploadFile));
			bos.write(bytes);
			bos.close();

			// 添加视频
			DateFormat dateFormat = DateFormat.getDateInstance();
			TbVideo tbVideo = new TbVideo();
			tbVideo.setVideoname(videoName);
			tbVideo.setVideopath("/static/video/" + fileName);
			tbVideo.setVideosize(videoSize);
			tbVideo.setVideodate(dateFormat.parse(videoDate));
			videoService.insert(tbVideo);
			return "<script>alert('添加成功！');window.location.href='/video/videoList';</script>";
		} catch (Exception e) {
			if (uploadFile.exists()) {
				uploadFile.delete();// 异常时将文件进行删除
			}
			logger.error("msg",e);
			return "<script>alert('添加到数据库异常！');history.go(-1);</script>";
		}
	}

	@RequestMapping(value = "deleteVideo")
	@ResponseBody
	public String deleteVideo(HttpServletRequest request) {
		// 判断是否登录
		if (!LoginHelper.validate(request)) {
			return "<script>alert('您还未登录!');window.location.href='/user/toLogin';</script>";
		}

		// 获取页面传入需要删除的视频id
		String idStr = request.getParameter("id");
		if (StringUtils.isEmpty(idStr)) {
			return "<script>alert('发生错误');history.go(-1);</script>";
		}

		try {
			// 删除该id对应的视频
			TbVideo tbVideo = new TbVideo();
			tbVideo.setId(Integer.parseInt(idStr));
			tbVideo = videoService.selectByPrimaryKey(tbVideo);
			if(tbVideo == null) {
				return "<script>alert('该视频不存在');history.go(-1);</script>";
			}
			
			File file = new File(fileUploadConfig.getSavePath() + tbVideo.getVideopath());
			if (file.exists()) {
				file.delete();
			}
			videoService.deleteByPrimaryKey(tbVideo);

			return "<script>alert('删除成功！');window.location.href='/video/videoList';</script>";
		} catch (Exception e) {
			logger.error("msg",e);
			return "<script>alert('删除异常，请稍后重试！');history.go(-1);</script>";
		}
	}

	/**
	 * 后台：进入修改视频界面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toMdyVideo")
	public String toMdyVideo(HttpServletRequest request, Model model) {
		// 判断是否登录
		if (!LoginHelper.validate(request)) {
			return "redirect:/user/toLogin";
		}

		// 获取页面传入的需要修改的视频id
		String idString = request.getParameter("id");
		TbVideo tbVideo = new TbVideo();
		tbVideo.setId(Integer.parseInt(idString));
		try {
			tbVideo = videoService.selectByPrimaryKey(tbVideo);
			model.addAttribute("tbVideo", tbVideo);

			return "video/modify_video";
		} catch (Exception e) {
			logger.error("msg",e);
			return "redirect:/video/videoList";
		}
	}

	@RequestMapping(value = "mdyVideo")
	@ResponseBody
	public String mdyVideo(HttpServletRequest request) {
		// 判断是否登录
		if (!LoginHelper.validate(request)) {
			return "<script>alert('您还未登录!');window.location.href='/user/toLogin';</script>";
		}

		// 获取页面传入需要修改的视频id
		String idStr = request.getParameter("id");
		if (StringUtils.isEmpty(idStr)) {
			return "<script>alert('发生错误');history.go(-1);</script>";
		}

		try {
			//查询该视频是否存在
			TbVideo tbVideo = new TbVideo();
			tbVideo.setId(Integer.parseInt(idStr));
			tbVideo = videoService.selectByPrimaryKey(tbVideo);
			if(tbVideo == null) {
				return "<script>alert('该视频不存在');history.go(-1);</script>";
			}
			
			//获取页面传入的需要修改的视频名称
			String videoName = request.getParameter("videoname");
			if(StringUtils.isEmpty(videoName) || videoName.equals(tbVideo.getVideoname())) {
				return "<script>alert('与原来视频名相同！');history.go(-1);</script>";
			}
			
			//获取页面传入的需要修改的视频日期
			String videoDate = request.getParameter("videodate");
			if(StringUtils.isEmpty(videoDate)) {
				return "<script>alert('未选择视频日期');history.go(-1);</script>";
			}
			DateFormat dateFormat = DateFormat.getDateInstance();
			Date date = dateFormat.parse(videoDate);
			tbVideo.setVideodate(date);
			
			//修改视频名称
			tbVideo.setVideoname(videoName);
			
			//存入数据库
			videoService.updateByPrimaryKey(tbVideo);
			
			return "<script>alert('修改成功！');window.location.href='/video/videoList';</script>";
		} catch (Exception e) {
			logger.error("msg",e);
			return "<script>alert('发生异常，请稍后重试！');history.go(-1);</script>";
		}
	}
}
