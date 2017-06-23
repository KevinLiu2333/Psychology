package com.klsw.klswWebService.controller;

import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwksuggestion;
import com.klsw.klswWebService.service.TbCwkService;
import com.klsw.klswWebService.service.TbCwkSuggestionService;
import com.klsw.klswWebService.util.Constants;
import com.klsw.klswWebService.util.FileUploadConfig;
import com.klsw.klswWebService.util.MyFileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/feed")
public class FeedBackController {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedBackController.class);

	@Autowired
	private TbCwkSuggestionService tbCwkSuggestionService;

	@Autowired
	private TbCwkService tbCwkService;

	@Autowired
	private FileUploadConfig fileUploadConfig;

	@Autowired
	private MyFileUtils myFileUtils;

	/****
	 * 保存建议反馈
	 *
	 * @param id
	 * @param systemButton
	 * @param messageButton
	 * @return
	 */
	@RequestMapping(value = "saveFeedBack")
	@ResponseBody
	public Ret saveFeedBack(HttpServletRequest request, @RequestParam("file") MultipartFile[] myFile,
			@RequestParam("username") String username) {

		String suggestionType = request.getParameter("suggestionType");
		String suggestion = request.getParameter("suggestion");
		String phone = request.getParameter("phone");
		TbCwk cwk = tbCwkService.findByName(username);
		if (cwk == null) {
			return Ret.warn("用戶不存在");
		}
		try {
			TbCwksuggestion tbCwksuggestion = new TbCwksuggestion();
			if (Arrays.asList(myFile).size() > 0 && !Arrays.asList(myFile).isEmpty()) {
				Ret ret1 = myFileUtils.uploadfile(myFile, "image", Constants.FEEDIMAGE_UPLOAD,Constants.wkBucket);
				if (!"S".equals(ret1.getStatus())) {
					return Ret.error("文件上传失败");
				}
				String data = (String) ret1.getdata();
				tbCwksuggestion.setImgpath(data);
			}
			tbCwksuggestion.setProposerid(cwk.getId());
			tbCwksuggestion.setAddtime(new Date());
			tbCwksuggestion.setPhone(phone);
			tbCwksuggestion.setReplytime(new Date());
			tbCwksuggestion.setSuggestiontype(suggestionType);
			tbCwksuggestion.setSuggestion(suggestion);
			tbCwkSuggestionService.insert(tbCwksuggestion);
			return Ret.success("S","提交成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return Ret.error("提交失败");
		}
	}
	
	@RequestMapping(value="saveSuggestion")
	@ResponseBody
	public Ret saveSuggestion(HttpServletRequest request,@RequestParam("username") String username) {
		String suggestionType = request.getParameter("suggestionType");
		String suggestion = request.getParameter("suggestion");
		String phone = request.getParameter("phone");
		TbCwk cwk = tbCwkService.findByName(username);
		if (cwk == null) {
			return Ret.warn("用戶不存在");
		}
		try {
			TbCwksuggestion tbCwksuggestion = new TbCwksuggestion();
			tbCwksuggestion.setProposerid(cwk.getId());
			tbCwksuggestion.setAddtime(new Date());
			tbCwksuggestion.setPhone(phone);
			tbCwksuggestion.setReplytime(new Date());
			tbCwksuggestion.setSuggestiontype(suggestionType);
			tbCwksuggestion.setSuggestion(suggestion);
			tbCwksuggestion.setImgpath("");
			tbCwkSuggestionService.insert(tbCwksuggestion);
			return Ret.success("S","提交成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return Ret.error("提交失败");
		}
	}

	/**
	 *
	 * @Description: 上传
	 */
	/*
	 * @RequestMapping(value="/feedUpload")
	 * 
	 * @ResponseBody public Ret feedUpload(HttpServletRequest request,
	 * 
	 * @RequestParam("username")String username,
	 * 
	 * @RequestParam("file")MultipartFile[] fileList){ TbCwk tbCwk =
	 * tbCwkService.findByName(username); if (tbCwk == null) { return
	 * Ret.warn("用戶不存在"); }
	 * 
	 * try { Ret ret1 =
	 * fileUtils.uploadfile(fileList,"image",Constants.FEEDIMAGE_UPLOAD);
	 * if(!"S".equals(ret1.getStatus())){ return ret1; } TbCwksuggestion
	 * tbCwksuggestion=new TbCwksuggestion(); String data =
	 * (String)ret1.getdata(); tbCwksuggestion.setImgpath(data);
	 * tbCwkSuggestionService.updateByPrimaryKey(tbCwksuggestion); return
	 * Ret.success("上传成功"); } catch (Exception e) { e.printStackTrace(); return
	 * Ret.error("上传失败"); }
	 * 
	 * }
	 */
}
