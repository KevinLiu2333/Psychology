package com.klsw.weikesite.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.weikesite.utils.DomainConfig;
import com.klsw.weikesite.utils.JsonUtils;
import com.klsw.weikesite.utils.TestUtils;

@Controller
public class FeedBackController {
	@Autowired
	private DomainConfig domainConfig;
	private static final Logger log = LoggerFactory.getLogger(FeedBackController.class);

	/**
	 * 进入反馈信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/my/toFeedBack")
	public String toFeedBack() {
		return "feedBack";
	}

	/***
	 * 提交反馈信息
	 * 
	 * @param feedType
	 * @param phone
	 * @param suggestion
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/my/feedBack", method = RequestMethod.POST)
	@ResponseBody
	public String feedBack(HttpServletRequest request, 
			@RequestParam("filehide")MultipartFile myFile) throws IOException {

		String suggestionType = request.getParameter("suggestionType");
		String phone = request.getParameter("phone");
		String suggestion = request.getParameter("suggestion");
		List<File> fileList = new ArrayList<File>();
		File file = null;
		String response;
		try {
			if(!myFile.isEmpty()) {
				file = new File(myFile.getOriginalFilename());
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(myFile.getBytes());
				fos.flush();
				fos.close();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			log.error(e1.getMessage());
		}
		try {
			String url = "";
			// 设置请求参数
			NameValuePair nvp1 = new NameValuePair("suggestion", suggestion);
			NameValuePair nvp2 = new NameValuePair("suggestionType", suggestionType);
			NameValuePair nvp3 = new NameValuePair("phone", phone);
			List<NameValuePair> list = Lists.newArrayList(nvp1, nvp2, nvp3);
			if(file != null) {
				fileList.add(file);
			} else {
				fileList = null;
			} 
			if(fileList != null) {
				url = domainConfig.getApiDomain() + "/feed/saveFeedBack";
			} else {
				url = domainConfig.getApiDomain() + "/feed/saveSuggestion";
			}
			response = TestUtils.loggedInRequest(request, list, url, fileList);
			Ret ret = JsonUtils.decode(response, Ret.class);
			if ("S".equals(ret.getStatus())) {
				return "<script>alert('提交成功');window.location.href='/my/index';</script>";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}finally{
			if(file != null) {
				if(file.exists()){
					file.delete();
				}
			}
		} 
		return "<script>alert('提交失败');history.go(-1);</script>";
	}
}
