/*package com.klsw.website.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.klsw.common.mall.model.TMallUser;
import com.klsw.website.service.UserService;
import com.klsw.website.util.Constants;
import com.klsw.website.util.JsonUtils;
import com.klsw.website.util.Ret;
import com.klsw.website.util.UploadUtils;

@Controller
public class ModifyInfomationController extends _BaseController {

	@Autowired
	private UploadUtils uploadUtils;
	
	@Autowired
	private UserService userService; 
	
	@RequestMapping(value="/information/modifyMessage")
	public String setPortrait(HttpServletRequest request,TMallUser tMallUser,
			@RequestParam("filehide")List<MultipartFile> fileList) {
		
		TMallUser user = (TMallUser)request.getSession().getAttribute(Constants.USER);
		try {
			Ret ret = uploadUtils.uploadFile(fileList, "image", "upload/information");
			if("S".equals(ret.getStatus())) {
				String imgPath = JsonUtils.encode(ret.getdata());
				tMallUser.setId(user.getId());
				tMallUser.setCtime(user.getCtime());
				tMallUser.setPassword(user.getPassword());
				tMallUser.setName(user.getName());
				tMallUser.setLtime(new Date());
				tMallUser.setImgpath(imgPath);
				try {
					userService.updateByPrimaryKey(tMallUser);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return "redirect:/information/myAccount";
	}
	
}
*/