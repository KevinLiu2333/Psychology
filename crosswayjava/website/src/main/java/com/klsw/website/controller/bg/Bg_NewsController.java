package com.klsw.website.controller.bg;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.common.mall.model.Ret;
import com.klsw.common.mall.model.TMallNews;
import com.klsw.website.controller._BaseController;
import com.klsw.website.service.NewsService;
import com.klsw.website.util.Bg_NewsUtils;
@Controller
@RequestMapping("manage/order")
public class Bg_NewsController extends _BaseController {
	@Autowired
	private NewsService newsService; 
	
	@Autowired Bg_NewsUtils bg_NewsUtils;
	private 
	static final int PAGESIZE = 5;
	static final int HOTESTPAGESIZE = 5;
	static final int LATESTPAGESIZE = 5;
	
	
	/**
	 * 上传
	 * @param request
	 * @param response
	 * @param file
	 * @throws IOException
	 * @throws FileUploadException
	 */
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	@ResponseBody
	public void upload(HttpServletRequest request,
						HttpServletResponse response,@RequestParam("imgFile")MultipartFile file) 
						throws IOException, FileUploadException{
		
		bg_NewsUtils.uploadfile(request, response,file);
	}
	
	@RequestMapping(value="filemanager",method = RequestMethod.POST)
	@ResponseBody
	public void filemanager(HttpServletRequest request,HttpServletResponse response) throws IOException{ 
		bg_NewsUtils.filemanager(request, response);
		
		
	}

	/**
	 * 进入新闻添加页面
	 * @return
	 */
	@RequestMapping(value="/toAddNews")
	public String toAddNews(){
		return "background/bg_news/bg_add_news";
	}

	/**
	 * 添加新闻功能
	 * @param request
	 * @param file
	 * @param title
	 * @param content
	 * @param ctime
	 * @param response
	 * @param referer
	 */
	@RequestMapping(value="/addNews")
	public void addNews(HttpServletRequest request,
						@RequestParam("file")MultipartFile file,
						@RequestParam("title")String title,
						@RequestParam("contentHtml")String content,
						@RequestParam("date")String ctime,
						HttpServletResponse response,
						@RequestHeader("Referer") String referer){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		TMallNews news = new TMallNews();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String msg = null;
		try{
			out = response.getWriter();
			if(StringUtils.isBlank(title)||StringUtils.isBlank(content)||StringUtils.isBlank(ctime)){
				out.println("<script language='javascript'>");
				out.println("alert('"+msg+"')");
				out.println("location.href='"+getRequestPathFromReferer(referer)+"'");
				out.println("</script>");
				return;
			}
			Ret ret = bg_NewsUtils.uploadfile(request,file);
			if("S".equals(ret.getStatus())){
				String imgUrl = (String)ret.getdata();
				news.setBrowse(0);
				news.setContent(content);
				news.setCtime(sdf.parse(ctime));
				//Date date=new Date();
				//news.setCtime(date);
				news.setImgurl(imgUrl);
				news.setTitle(title);
			//	newsService.insertUseGeneratedKeys(news);
				newsService.insert(news);
				msg="保存新闻成功";
				out.println("<script language='javascript'>");
				out.println("alert('"+msg+"')");
				out.println("location.href='"+getRequestPathFromReferer(referer)+"'");
				out.println("</script>");
				return;
			}
			msg = ret.getMessage();
			out.println("<script language='javascript'>");
			out.println("alert('"+msg+"')");
			out.println("location.href='"+getRequestPathFromReferer(referer)+"'");
			out.println("</script>");
			return;
		}catch(Exception e){
			msg = "保存新闻信息失败";
			out.println("<script language='javascript'>");
			out.println("alert('"+msg+"')");
			out.println("location.href='"+getRequestPathFromReferer(referer)+"'");
			out.println("</script>");
			e.printStackTrace();
		}

		
	}
	
	
	/**
	 * 进入后台新闻主页面
	 * @param request
	 * @return
	 */
	@RequestMapping("newsList")
	public ModelAndView newsList(Model model,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("background/bg_news/bg_news");
		List<TMallNews> newsList = null; 
//		Page<TMallNews> page = null;
		String pageNum = request.getParameter("pageNum");
		String title=request.getParameter("title");		
//		Map map = request.getParameterMap();
		if(pageNum==null){
			 /*page = */PageHelper.startPage(1, PAGESIZE);
		}
		else{
			/*page = */PageHelper.startPage(Integer.parseInt(pageNum), PAGESIZE);
		}
		
		PageHelper.orderBy("ctime desc");
		
		try { 
			if(StringUtils.isEmpty(title)){
				
				newsList = newsService.selectAll();
				
			}else{
				newsList=newsService.selectByNews(title);
			}
			 model.addAttribute("title",title);
			
		} catch (Exception e) {
			logger.error("bg_news newsList error >>> ", e);
		}
 
		PageInfo<TMallNews> PageInfo = new PageInfo<>(newsList); 
		mav.addObject("newsList", PageInfo);

		return mav;
	}
	 
	/**
	 * 进入新闻编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("toEditNews")
	public ModelAndView  toEditNews(HttpServletRequest request,Model model) {
		ModelAndView mav = new ModelAndView("background/bg_news/bg_editors_news");
		
		Integer newsId=Integer.parseInt(request.getParameter("id"));
		TMallNews news=new TMallNews();
		news.setId(newsId);
		try {
			news=newsService.selectOne(news);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("news",news);
		
		return mav;
	}
	 
	/**
	 * 编辑新闻功能
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="editNews", method = RequestMethod.POST)
	public String editNews(HttpServletRequest request,Model model) {
		Integer newsId=Integer.parseInt(request.getParameter("id"));
		String title=request.getParameter("title");
		String date=request.getParameter("date");
		String content=request.getParameter("content");
		TMallNews news=new TMallNews();
		news.setId(newsId);
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date2=sdf.parse(date);
			news=newsService.selectOne(news);
			news.setId(newsId);
	 		news.setTitle(title); 
			news.setContent(content);
			news.setCtime(date2);
			newsService.updateByPrimaryKeySelective(news);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return "redirect:newsList";
	} 
	
	
	/**
	 * 删除新闻功能
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteNews")
	public String deleteNews(HttpServletRequest request) {
		Integer newsId=Integer.parseInt(request.getParameter("id"));
		try {
			if(newsId != null) {
				TMallNews tMallNews = new TMallNews();
				tMallNews.setId(newsId);
				newsService.delete(tMallNews);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:newsList";
	}
}

