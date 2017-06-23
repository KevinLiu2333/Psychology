package com.klsw.piano.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.klsw.piano.service.TbHomeworkService;
import com.klsw.pianoCommon.api.model.TbHomework;

/**
 * MID下载
 *
 * @author HZY
 */
@Controller
@RequestMapping(value = "/load")
public class DownLoadController {
	
	private static Logger logger = LoggerFactory.getLogger(DownLoadController.class);
	
	@Resource
	private TbHomeworkService homeworkService;

	/**
	 * 进入到mid搜索界面
	 * @return
	 */
	@RequestMapping(value = "/loadMID", method = RequestMethod.GET)
	public String loadMID() {
		return "downLoadMID/downLoad";

	}

	/**
	 * 进行搜索并返回搜索结果
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/downLoad", method = RequestMethod.POST)
	public String downLoadMID(HttpServletRequest request, HttpServletResponse response, Model model) {
		String MIDName = request.getParameter("MIDName");
		String studentTime = request.getParameter("studentTime");
		String endTime = request.getParameter("endTime");

		String studentName = request.getParameter("StudentName");
		List<TbHomework> homeworks = homeworkService.loadMID(MIDName, studentTime, endTime, studentName);
		if (homeworks.size() > 5) {
			model.addAttribute("error", "该搜索条数过多！请重新搜索。");
			return "downLoadMID/downLoad";
		} else if (homeworks.size() == 0) {
			model.addAttribute("error", "该搜索不存在！");
			return "downLoadMID/downLoad";
		}
		model.addAttribute("homeworks", homeworks);

		return "downLoadMID/showMid";
	}

 
     

	// 文件下载相关代码
	@RequestMapping("/downloadMid")
	public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
		String path = "http://piano-static.klsw.com";
		String fullPath = path + request.getParameter("tpurl");
		String fileName = request.getParameter("midName") + ".mid";
		String agent = request.getHeader("User-Agent").toLowerCase();
		agent = getBrowserName(agent);
		URL fileurl = null;
		try {
			if (agent.equals("firefox")) {
				// 处理乱码
				fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			} else {
				fileName = URLEncoder.encode(fileName, "UTF-8");


			}
			//
			fileurl = new URL(fullPath);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
		} 
		response.reset();
		response.setContentType(request.getServletContext().getMimeType(fileName));


		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

		byte[] buffer = new byte[1024];
		InputStream fis = null;
		BufferedInputStream bis = null;
		try {

			fis = fileurl.openStream();
			bis = new BufferedInputStream(fis);
			OutputStream os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("msg", e);
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("msg", e);
				}
			}
		}

		return null;

	}

	// 以下为服务器端判断客户端浏览器类型的方法

	public String getBrowserName(String agent) {
		if (agent.indexOf("msie 7") > 0) {
			return "ie7";
		} else if (agent.indexOf("msie 8") > 0) {
			return "ie8";
		} else if (agent.indexOf("msie 9") > 0) {
			return "ie9";
		} else if (agent.indexOf("msie 10") > 0) {
			return "ie10";
		} else if (agent.indexOf("msie") > 0) {
			return "ie";
		} else if (agent.indexOf("opera") > 0) {
			return "opera";
		} else if (agent.indexOf("opera") > 0) {
			return "opera";
		} else if (agent.indexOf("firefox") > 0) {
			return "firefox";
		} else if (agent.indexOf("webkit") > 0) {
			return "webkit";
		} else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
			return "ie11";
		} else {
			return "Others";
		}
	}
}
