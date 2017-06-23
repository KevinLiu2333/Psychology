package com.klsw.klswWebService.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.*;
import com.klsw.klswWebService.service.*;
import com.klsw.klswWebService.util.Constants;
import com.klsw.klswWebService.util.FileUploadConfig;
import com.klsw.klswWebService.util.MyFileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName: HomeworkController
 *
 * 
 * @author LiuKun 威客接口作业相关 2016年9月1日
 */
@RestController
@RequestMapping(value = "/homework")
public class HomeworkController {

	private static final Logger log = LoggerFactory.getLogger(HomeworkController.class);

	@Resource
	private HistorySignService historySignService;

	@Resource
	private FileUploadConfig fileUploadConfig;

	@Resource
	private TbCwkService tbCwkService;

	@Resource
	private HomeworkSignService homeworkSignService;

	@Resource
	private TbHomeworkService tbHomeworkService;

	@Resource
	private TbCwkHomeworkService tbCwkHomeworkService;

	@Resource
	private TbCwkVideoService tbCwkVideoService;

	@Resource
	private MyFileUtils myFileUtils;

	@Resource
	private TeacherService teacherService;

	/**
	 * 上传视频接口
	 *
	 * @param username
	 *            用户名
	 * @param type
	 *            用户类型
	 * @param homeworkId
	 *            作业id
	 * @param fileList
	 *            视频
	 * @return Ret
	 */
	@RequestMapping(value = "uploadMedia")
	@ResponseBody
	public Ret uploadMedia(@RequestParam("username") String username, @RequestParam("type") String type,
			@RequestParam("homeworkId") Integer homeworkId, @RequestParam("file") MultipartFile[] fileList) {
		TbCwkvideo tbCwkvideo = new TbCwkvideo();
		// true为插入,false为更新
		boolean flag = true;
		try {

			TbHomework tbHomework = tbHomeworkService.selectByPrimaryKey(homeworkId);
			if (tbHomework == null) {
				return Ret.warn("作业不存在");
			}
			tbCwkvideo.setHomeworkid(homeworkId);
			// 通过传入的homeworkId查询是否该作业已经上传过视频
			TbCwkvideo tbCwkvideo2 = tbCwkVideoService.selectOne(tbCwkvideo);
			// 如果已上传过视频
			if (tbCwkvideo2 != null) {
				flag = false;
			}
			Ret ret = myFileUtils.uploadfile(fileList, "media", Constants.HOMEWORK_UPLOAD, Constants.wkBucket);
			if ("S".equals(ret.getStatus())) {
				String snapshot = myFileUtils.getSnapShot(Constants.wkBucket, String.valueOf(ret.getdata()));
				log.info(snapshot);
				Integer cwkid = tbCwkService.selectId(username, type);
				tbCwkvideo.setSnapshot(snapshot);
				tbCwkvideo.setAddtime(new Date());
				tbCwkvideo.setCwkid(cwkid);
				tbCwkvideo.setHomeworkid(homeworkId);
				tbCwkvideo.setVideopath(String.valueOf(ret.getdata()));
				if (!flag) {
					String filePath = "/home/webservice/release/static"
							+ tbCwkvideo2.getVideopath().substring(0, tbCwkvideo2.getVideopath().length() - 1);
					File file = new File(filePath);
					file.delete();
					tbCwkvideo.setId(tbCwkvideo2.getId());
					tbCwkVideoService.updateByPrimaryKey(tbCwkvideo);
				} else {
					tbCwkVideoService.insert(tbCwkvideo);
				}
				return Ret.success("S", "上传视频成功", tbCwkvideo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return Ret.error("上传视频失败");
		}
		return Ret.error("上传视频失败");
	}

	/**
	 * Description: 查询视频信息
	 *
	 * @param homeworkId
	 *            作业id
	 * @return Ret
	 */
	@RequestMapping(value = "mediaInfo")
	@ResponseBody
	public Ret mediaInfo(@RequestParam("homeworkId") Integer homeworkId) {
		TbCwkvideo tbCwkvideo = new TbCwkvideo();
		tbCwkvideo.setHomeworkid(homeworkId);
		try {
			tbCwkvideo = tbCwkVideoService.selectOne(tbCwkvideo);
			return Ret.success(tbCwkvideo);
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return Ret.error("查询视频信息失败");
		}
	}

	/**
	 * 接口：学生提交作业
	 *
	 * @param username
	 *            用户名
	 * @param type
	 *            用户类型
	 * @param homeworkId
	 *            作品Id
	 * @param teacherId
	 *            教师Id
	 * @return Ret
	 */
	@RequestMapping(value = "/upload")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Ret homeworkUpload(@RequestParam("username") String username, @RequestParam("type") String type,
			@RequestParam("homeworkId") Integer homeworkId, @RequestParam("teacherId") Integer teacherId)
			throws Exception {

		// 根据用户名和用户类型查询用户Id
		Integer studentId = tbCwkService.selectId(username, type);

		TbCwkhomework tbCwkhomework = new TbCwkhomework();
		try {
			// 查询作业信息是否有误
			TbHomework tbHomework = tbHomeworkService.selectByPrimaryKey(homeworkId);
			if (tbHomework == null) {
				return Ret.warn("作业不存在");
			}

			// 查询教师信息是否有误
			TbCwk tbCwk = tbCwkService.selectByPrimaryKey(teacherId);
			if (tbCwk == null || !"tea".equals(tbCwk.getType())) {
				return Ret.warn("教师信息有误");
			}

			// 查询该作业是否被提交过
			tbCwkhomework.setHomeworkid(homeworkId);
			if (tbCwkHomeworkService.select(tbCwkhomework).size() != 0) {
				return Ret.warn("您已经提交过该作业");
			}

			// 查询签约信息
			// 先判断有没有试用签约
			TbCwkhomeworksign sign = new TbCwkhomeworksign();
			sign.setCwkstudentid(studentId);
			sign.setCwkteacherid(teacherId);
			sign.setType(1);
			sign = homeworkSignService.selectOne(sign);
			if (sign == null || sign.getUsedcount() == 1) { // 试用签约不存在或已经试用过
				sign = new TbCwkhomeworksign();
				// 查询是否存在正式签约
				sign.setCwkstudentid(studentId);
				sign.setCwkteacherid(teacherId);
				sign.setType(0);
				sign = homeworkSignService.selectOne(sign);
				Date date = new Date();
				if (sign == null) { // 签约信息有误
					return Ret.warn("您未签约该老师");
				} else if (date.after(sign.getEndtime()) || sign.getUsedcount() >= Constants.MONTH_COUNT) {
					return Ret.warn("签约时间已到或总次数已用完");
				} else if (sign.getTimestoday() <= 0) {
					return Ret.warn("您今日提交次数已用完");
				}
				sign.setTimestoday(sign.getTimestoday() - 1);
				sign.setUsedcount(sign.getUsedcount() + 1);
				// 更新签约历史记录表
				historySignService.updateByHomeworkSign(sign);

				if (sign.getUsedcount() >= Constants.MONTH_COUNT) {
					sign.setIsoverdue(true);
				}
			} else {
				sign.setUsedcount(1);
				// 更新签约历史记录表
				historySignService.updateByHomeworkSign(sign);
				sign.setIsoverdue(true);
			}

			// 设置威客作业信息
			tbCwkhomework.setTitle(tbHomework.getTitle().trim());
			tbCwkhomework.setStudentid(studentId);
			tbCwkhomework.setTeacherid(teacherId);
			tbCwkhomework.setTeachername(tbCwk.getNickname());
			tbCwkhomework.setMidimgpath(tbHomework.getMidimgpath());
			tbCwkhomework.setMdiportfolio(tbHomework.getMdiportfolio().trim());
			tbCwkhomework.setMp3path(tbHomework.getMp3path());
			tbCwkhomework.setAddtime(new Date());
			tbHomework.setStatus(1);
			tbHomework.setModifytime(new Date());
			tbHomework.setSubmittime(new Date());
			// 检测作业是否上传过视频
			TbCwkvideo video = new TbCwkvideo();
			video.setHomeworkid(homeworkId);
			video = tbCwkVideoService.selectOne(video);
			if (video != null) {
				// 将视频信息保存到作业中
				tbCwkhomework.setVideo(video.getVideopath());
			}
			tbCwkHomeworkService.insertSelective(tbCwkhomework);
			if (tbHomeworkService.updateByPrimaryKey(tbHomework) != 1) {
				return Ret.error("上传作业失败");
			}

			// 更新签约表
			homeworkSignService.updateByPrimaryKey(sign);

			return Ret.success("S", "上传作业成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			throw e;
		}

	}

	/**
	 * 获取学生作业列表
	 *
	 * @param username
	 *            用户名
	 * @param type
	 *            用户类型
	 * @param pageNum
	 *            页数
	 * @param status
	 *            状态0为未提交，1为已提交未批改，2为已批改，3为已读
	 * @return Ret
	 */
	@RequestMapping(value = "/homeworkList")
	@ResponseBody
	public Ret homeworkList(@RequestParam("username") String username, @RequestParam("type") String type,
			@RequestParam("pageNum") Integer pageNum, @RequestParam("status") Integer status) {

		if (!"stu".equals(type)) {
			return Ret.warn("用户类型错误");
		}
		if (pageNum == null || pageNum < 1) {
			pageNum = 1;
		}
		List<TbHomework> tbHomeworkList = null;
		Page<TbHomework> page = null;
		try {
			Integer CWKID = tbCwkService.selectId(username, type);
			TbHomework tbHomework = new TbHomework();
			tbHomework.setCwkid(CWKID);
			page = PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
			// 所有作业
			if (status == 10) {
				page.setOrderBy("ModifyTime desc");
				tbHomeworkList = tbHomeworkService.select(tbHomework);
				// 未提交作业
			} else if (status == 0) {
				tbHomework.setStatus(status);
				page.setOrderBy("ModifyTime desc");
				tbHomeworkList = tbHomeworkService.select(tbHomework);
			} else if (status == 1) {
				tbHomework.setStatus(status);
				page.setOrderBy("ModifyTime desc");
				tbHomeworkList = tbHomeworkService.select(tbHomework);
				// 已批改作业
			} else if (status == 2) {
				tbHomeworkList = tbHomeworkService.corected(CWKID);
			}
			PageInfo<TbHomework> pageInfo = new PageInfo<>(tbHomeworkList);
			if (!tbHomeworkList.isEmpty()) {
				List<TbHomework> homeworks = Lists.newArrayList();
				TbCwkvideo cwkVideo = null;
				for (TbHomework homework : tbHomeworkList) {
					cwkVideo = new TbCwkvideo();
					cwkVideo.setHomeworkid(homework.getId());
					cwkVideo = tbCwkVideoService.selectOne(cwkVideo);
					homework.setTbCwkvideo(cwkVideo);
					TbCwkhomework tbCwkhomework = new TbCwkhomework();
					tbCwkhomework.setHomeworkid(homework.getId());
					if ((tbCwkhomework = tbCwkHomeworkService.selectOne(tbCwkhomework)) != null) {
						homework.setGradeimgpath(tbCwkhomework.getGradeimgpath());
						homework.setTeachername(tbCwkhomework.getTeachername());
					}
					homeworks.add(homework);
				}
				pageInfo.setList(homeworks);
			}
			return Ret.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return Ret.error("获取所有作业失败");
		}

	}

	/**
	 * 学生给老师评分
	 *
	 * @param score
	 *            得分
	 * @param teacherId
	 *            教师id
	 * @param cwkhomeworkId
	 *            cwkhomeworkId作业id
	 * @return
	 */
	@RequestMapping(value = "/score")
	@ResponseBody
	public Ret teacherScore(@RequestParam("score") Float score, @RequestParam("teacherId") Integer teacherId,
			@RequestParam("CWKHomeworkId") Integer cwkhomeworkId) {
		try {
			TbCwkteacher tbCwkteacher = new TbCwkteacher();
			tbCwkteacher.setCwkid(teacherId);
			tbCwkteacher = teacherService.selectOne(tbCwkteacher);
			if (tbCwkteacher == null) {
				return Ret.warn("教师信息有误");
			}
			TbCwkhomework tbCwkhomework = tbCwkHomeworkService.selectByPrimaryKey(cwkhomeworkId);
			if (tbCwkhomework.getTeacherscore() != 0) {
				return Ret.warn("您已经评过分");
			}
			tbCwkhomework.setTeacherscore(score);
			Float as = tbCwkteacher.getAverageScore();
			Integer ct = tbCwkteacher.getCorrectingTimes() == null ? 0 : tbCwkteacher.getCorrectingTimes();
			as = (as * ct + score) / (ct + 1);
			tbCwkteacher.setAverageScore(as);
			tbCwkteacher.setCorrectingTimes(ct + 1);
			// 修改作业状态为已完成
			TbHomework homework = tbHomeworkService.selectByPrimaryKey(tbCwkhomework.getHomeworkid());
			homework.setStatus(3);
			tbHomeworkService.updateByPrimaryKey(homework);
			tbCwkHomeworkService.updateByPrimaryKey(tbCwkhomework);
			teacherService.updateByPrimaryKey(tbCwkteacher);
			return Ret.success("S", "评价成功", null);
		} catch (Exception e) {
			return Ret.error("评分失败");
		}

	}

	/**
	 * 接口：作業詳情
	 * 
	 * @param homeworkId
	 *            作业id
	 * @param username
	 *            用户名
	 * @param type
	 *            用户类型
	 * @return 返回值
	 */
	@RequestMapping(value = "detail")
	@ResponseBody
	public Ret homeworkDetail(@RequestParam("homeworkId") Integer homeworkId, @RequestParam("username") String username,
			@RequestParam("type") String type) {

		try {
			TbCwk student = null;
			TbCwk teacher = null;
			TbCwk currentUser = tbCwkService.findByName(username, type);
			if (currentUser == null) {
				return Ret.error("用戶不存在");
			}

			TbHomework tbHomework = tbHomeworkService.selectByPrimaryKey(homeworkId);
			if (tbHomework == null) {
				return Ret.warn("作业不存在");
			}
			;

			TbCwkvideo cwkvideo = new TbCwkvideo();
			cwkvideo.setHomeworkid(homeworkId);
			cwkvideo = tbCwkVideoService.selectOne(cwkvideo);

			// 未提交作业
			if ("stu".equals(type)) {
				if (!tbHomework.getCwkid().equals(currentUser.getId())) {
					return Ret.error("获取作业详情失败");
				}

				if (tbHomework.getStatus() == 0) {
					tbHomework.setTbCwkvideo(cwkvideo);
					tbHomework.setTeacherHeadPic("");
					tbHomework.setStudentHeadPic(currentUser.getFfavicon());
					return Ret.success(tbHomework);
				}
			}

			// 已提交未批改作业
			TbCwkhomework tbCwkhomework = new TbCwkhomework();
			tbCwkhomework.setHomeworkid(homeworkId);
			tbCwkhomework = tbCwkHomeworkService.homewrokDetail(homeworkId);
			if (tbCwkhomework == null) {
				return Ret.error("获取作业详情失败");
			}

			// 设置提交到威客的时间
			tbCwkhomework.setStudenttime(tbHomework.getStudenttime());

			if ("tea".equals(type)) {
				teacher = tbCwkService.findByName(username, type);
				student = tbCwkService.selectByPrimaryKey(tbCwkhomework.getStudentid());
			} else {
				student = tbCwkService.findByName(username, type);
				teacher = tbCwkService.selectByPrimaryKey(tbCwkhomework.getTeacherid());
			}

			tbCwkhomework.setTeacherHeadPic(teacher.getFfavicon());
			tbCwkhomework.setStudentHeadPic(student.getFfavicon());
			tbCwkhomework.setTbCwkvideo(cwkvideo);

			return Ret.success(tbCwkhomework);
			// 已批改未查看
		} catch (Exception e) {
			log.info("msg", e);
			return Ret.error("获取作业详情失败");
		}
	}

	/**
	 * @param comment
	 *            文字内容
	 * @param id
	 *            作业id
	 * @param img
	 *            图片
	 * @param audio
	 *            音频
	 * @param jsonArray
	 *            音频信息列表
	 * @param score
	 *            得分
	 * @param rhythm
	 *            节奏(1-4)
	 * @param accuracyrate
	 *            准确率(1-4)
	 * @param musicality
	 *            乐感(1-4)
	 * @return 批改结果
	 * @throws Exception
	 *             异常
	 */
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public Ret checkHomework(@RequestParam("comment") String comment, @RequestParam("id") Integer id,
			@RequestParam("img") MultipartFile[] img, @RequestParam("audio") MultipartFile[] audio,
			@RequestParam("audiolist") JSONArray jsonArray, @RequestParam("score") Integer score,
			@RequestParam(value = "rhythm", required = false) Integer rhythm,
			@RequestParam(value = "accuracyrate", required = false) Integer accuracyrate,
			@RequestParam(value = "musicality", required = false) Integer musicality) throws Exception {
		if (StringUtils.isBlank(comment)) {
			return Ret.warn("评论不能为空");
		}
		try {
			TbCwkhomework tbCwkHomework = tbCwkHomeworkService.selectByPrimaryKey(id);
			if (tbCwkHomework == null) {
				return Ret.warn("作业不存在");
			}

			TbHomework tbHomework = tbHomeworkService.selectByPrimaryKey(tbCwkHomework.getHomeworkid());
			Ret ret;
			if (img != null) {
				ret = myFileUtils.uploadfile(img, "image", Constants.HOMEWORK_UPLOAD, Constants.wkBucket);
				if ("S".equals(ret.getStatus())) {
					tbCwkHomework.setGradeimgpath(String.valueOf(ret.getdata()));
				}
			}

			if (audio != null && audio.length > 0) {
				ret = myFileUtils.uploadfile(audio, "media", Constants.HOMEWORK_UPLOAD, Constants.wkBucket);
				if ("S".equals(ret.getStatus())) {
					tbCwkHomework.setAudio(String.valueOf(ret.getdata()));
				}
			}

			Iterator<Object> it = jsonArray.iterator();
			int i = 1;
			File file;
			String fileUrl;
			String filePath;
			FileOutputStream fos;
			BASE64Decoder decoder = new BASE64Decoder();
			while (it.hasNext()) {
				JSONObject ob = (JSONObject) it.next();
				String base64audio = ob.getString("audio");
				if (!StringUtils.isBlank(base64audio)) {
					fileUrl = "/upload/homework/cwk-" + tbCwkHomework.getId() + "-" + tbCwkHomework.getTeacherid() + "/"
							+ i + ".m4a";
					i++;
					filePath = fileUploadConfig.getSavePath() + fileUrl;

					file = new File(filePath);
					if (!file.getParentFile().isDirectory()) {
						file.getParentFile().mkdirs();
					}
					file.createNewFile();
					fos = new FileOutputStream(file);
					byte[] buffer = decoder.decodeBuffer(base64audio);
					fos.write(buffer);
					fos.close();
					myFileUtils.uploadFileToOss(file, fileUrl, Constants.wkBucket);
					ob.put("audio", fileUrl);
				}

			}
			// 修改状态为已批阅
			tbHomework.setStatus(2);
			tbCwkHomework.setAudiolist(jsonArray.toString());
			tbCwkHomework.setGradelevel(score);
			tbHomework.setModifytime(new Date());
			tbCwkHomework.setTeachercomment(comment);
			tbCwkHomework.setTeachertime(new Date());
			tbCwkHomework.setRhythm(rhythm);
			tbCwkHomework.setAccuracyrate(accuracyrate);
			tbCwkHomework.setMusicality(musicality);
			tbCwkHomeworkService.updateByPrimaryKeySelective(tbCwkHomework);
			tbHomeworkService.updateByPrimaryKeySelective(tbHomework);
			return Ret.success("S", "批改作业成功", null);
		} catch (Exception e) {
			log.info("msg", e);
			throw e;
		}

	}

	/**
	 * 接口：教师通过学生Id查询该学生向他提交的所有作业
	 *
	 * @param studentId
	 *            学生Id
	 * @param username
	 *            老师Id
	 * @param pageNum
	 *            页码数
	 * @param status
	 *            状态
	 * @return 查询结果
	 */
	@RequestMapping(value = "studentHomeworkList")
	@ResponseBody
	public Ret studentHomeworkList(@RequestParam("studentId") Integer studentId,
			@RequestParam("username") String username, @RequestParam("pageNum") Integer pageNum,
			@RequestParam("status") Integer status) {
		TbCwk tbCwk = tbCwkService.findByName(username);

		try {
			if (!"tea".equals(tbCwk.getType())) {
				return Ret.warn("您无此权限!");
			}

			PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
			List<TbCwkhomework> studentHomework = tbCwkHomeworkService.studentHomeworkList(studentId, tbCwk.getId(),
					status);
			PageInfo<TbCwkhomework> pageInfo = new PageInfo<>(studentHomework);
			return Ret.success(pageInfo);
		} catch (Exception e) {
			log.info("msg", e);
			return Ret.error(e.getMessage());
		}
	}

	/**
	 * 接口：学生删除未提交作业
	 *
	 * @param homeworkIds
	 *            需要删除的作业ID
	 * @return 删除结果
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public Ret deleteHomework(@RequestParam("homeworkIds") String homeworkIds) {
		try {
			if (StringUtils.isEmpty(homeworkIds)) {
				return Ret.warn("参数为空");
			}
			List<String> homeworkIdList = Arrays.asList(homeworkIds.split(","));
			for (String homeworkId : homeworkIdList) {
				TbHomework tbHomework = new TbHomework();
				tbHomework.setId(Integer.parseInt(homeworkId));
				tbHomework = tbHomeworkService.selectOne(tbHomework);
				tbHomework.setCwkid(null);
				tbHomeworkService.updateByPrimaryKey(tbHomework);
			}
			return Ret.success("S", "删除成功", null);
		} catch (Exception e) {
			log.info("msg", e);
			return Ret.error("删除失败");
		}
	}

	/**
	 * 删除视频
	 *
	 * @param request
	 * @param videoId
	 * @return
	 */
	@RequestMapping(value = "deleteVideo")
	@ResponseBody
	public Ret deleteVideo(HttpServletRequest request, @RequestParam("videoId") Integer videoId,
			@RequestParam("username") String userName, @RequestParam("type") String type) {

		try {
			TbCwk tbCwk = tbCwkService.findByName(userName, type);
			if (tbCwk == null) {
				return Ret.error("用户参数信息有误");
			}

			boolean flag = false;

			TbCwkvideo video = tbCwkVideoService.selectByPrimaryKey(videoId);
			if (video == null) {
				return Ret.warn("视频参数有误");
			}

			if (!video.getCwkid().equals(tbCwk.getId())) {
				return Ret.error("您没有删除该视频的权限");
			}

			flag = myFileUtils.deleteFileInOss(Constants.wkBucket, video.getVideopath());

			if (flag) {
				myFileUtils.deleteFileInOss(Constants.wkBucket, video.getSnapshot());
				tbCwkVideoService.deleteByPrimaryKey(videoId);
				return Ret.success("S", "删除视频成功", null);
			}

			return Ret.error("删除视频失败");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("msg", e);
			return Ret.error(e.getMessage());
		}
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public Ret getHomeworkList(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("type") String type, @RequestParam("pageNum") Integer pageNum,
			@RequestParam("status") Integer status) {

		TbCwk tbCwk = tbCwkService.findByName(username, type);
		if (tbCwk == null) {
			return Ret.error("用戶不存在");
		}

		if (pageNum == null || pageNum < 1) {
			pageNum = 1;
		}

		try {
			if ("stu".equals(type)) {
				List<TbHomework> tbHomeworkList = null;
				Page<TbHomework> page = null;
				TbHomework tbHomework = new TbHomework();
				tbHomework.setCwkid(tbCwk.getId());
				page = PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
				// 所有作业
				if (status == 10) {
					page.setOrderBy("ModifyTime desc");
					tbHomeworkList = tbHomeworkService.select(tbHomework);
					// 未提交作业
				} else if (status == 0) {
					tbHomework.setStatus(status);
					page.setOrderBy("ModifyTime desc");
					tbHomeworkList = tbHomeworkService.select(tbHomework);
				} else if (status == 1) {
					tbHomework.setStatus(status);
					page.setOrderBy("ModifyTime desc");
					tbHomeworkList = tbHomeworkService.select(tbHomework);
					// 已批改作业
				} else if (status == 2) {
					tbHomeworkList = tbHomeworkService.corected(tbCwk.getId());
				}
				PageInfo<TbHomework> pageInfo = new PageInfo<>(tbHomeworkList);
				if (!tbHomeworkList.isEmpty()) {
					List<TbHomework> homeworks = Lists.newArrayList();
					TbCwkvideo cwkVideo = null;
					for (TbHomework homework : tbHomeworkList) {
						cwkVideo = new TbCwkvideo();
						cwkVideo.setHomeworkid(homework.getId());
						cwkVideo = tbCwkVideoService.selectOne(cwkVideo);
						homework.setTbCwkvideo(cwkVideo);
						TbCwkhomework tbCwkhomework = new TbCwkhomework();
						tbCwkhomework.setHomeworkid(homework.getId());
						if ((tbCwkhomework = tbCwkHomeworkService.selectOne(tbCwkhomework)) != null) {
							homework.setGradeimgpath(tbCwkhomework.getGradeimgpath());
							homework.setTeachername(tbCwkhomework.getTeachername());
						}
						homeworks.add(homework);
					}
					pageInfo.setList(homeworks);
				}
				return Ret.success(pageInfo);
			}

			if ("tea".equals(type)) {
				List<TbCwkhomework> homeworkList = null;
				Integer teacherId = tbCwkService.selectId(username, type);
				PageHelper.startPage(pageNum, Constants.PAGE_SIZE).setOrderBy("addtime desc");

				// 查询全部作业
				if (status == 10) {
					homeworkList = tbCwkHomeworkService.teacherHomework(teacherId);
					// 查询未批改的作业
				} else if (status == 1) {
					homeworkList = tbCwkHomeworkService.uncommentHomework(teacherId);
				} else if (status == 2) {
					homeworkList = tbCwkHomeworkService.commentHomework(teacherId);
				} else {
					return Ret.error("参数有误");
				}

				PageInfo<TbCwkhomework> pageInfo = new PageInfo<>(homeworkList);

				if (!homeworkList.isEmpty()) {
					List<TbCwkhomework> homeworks = Lists.newArrayList();
					TbCwkvideo cwkVideo = null;
					for (TbCwkhomework homework : homeworkList) {
						cwkVideo = new TbCwkvideo();
						cwkVideo.setHomeworkid(homework.getHomeworkid());
						cwkVideo = tbCwkVideoService.selectOne(cwkVideo);
						homework.setTbCwkvideo(cwkVideo);
						TbCwk student = tbCwkService.selectByPrimaryKey(homework.getStudentid());
						if (student != null) {
							homework.setStudentname(student.getNickname());
						}
						homeworks.add(homework);
					}
					pageInfo.setList(homeworks);
				}

				return Ret.success(pageInfo);
			}
			
			return Ret.error("参数有误");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return Ret.error("获取作业列表异常");
		}
	}
}
