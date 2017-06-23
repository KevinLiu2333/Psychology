package com.klsw.piano.controller;


import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.piano.configuration.AliyunConfig;
import com.klsw.piano.service.database.BookService;
import com.klsw.piano.service.database.CountService;
import com.klsw.piano.service.database.OpernService;
import com.klsw.piano.service.database.PressService;
import com.klsw.piano.service.database.SonSubjectService;
import com.klsw.piano.service.database.SubjectService;
import com.klsw.piano.service.database.TempletService;
import com.klsw.piano.util.Constants;
import com.klsw.piano.util.MyFileUtils;
import com.klsw.piano.util.MyMultipartConfig;
import com.klsw.pianoCommon.api.model.DbBook;
import com.klsw.pianoCommon.api.model.DbCount;
import com.klsw.pianoCommon.api.model.DbOpern;
import com.klsw.pianoCommon.api.model.DbPress;
import com.klsw.pianoCommon.api.model.DbSonsubject;
import com.klsw.pianoCommon.api.model.DbSubject;
import com.klsw.pianoCommon.api.model.DbTemplet;
import com.klsw.pianoCommon.api.model.Ret;

/**
 * 功能：
 * 		1.后台创建曲谱库并将曲谱上传至OSS
 * 	    2.提供资料库相关接口	
 * @author HKJ
 *
 */
@Controller
@RequestMapping(value = "/database")
public class DatabaseController {
	
	private static final List<String> SUBJECT_LIST = Lists.newArrayList("名人篇", "考级篇");
	
	private static String PUBLIC_ACCESS_PATH = "http://piano-static.klsw.com/";
	
	private static Logger logger = LoggerFactory.getLogger(DatabaseController.class);
	
	@Autowired
	private AliyunConfig aliyunConfig;
	
	@Autowired
	private MyMultipartConfig multipartConfig;
	
	@Autowired
	private MyFileUtils fileUtils;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private OpernService opernService;
	
	@Autowired
	private CountService countService;
	
	@Autowired
	private SonSubjectService sonSubjectService;
	
	@Autowired
	private PressService pressService;
	
	@Autowired
	private TempletService templetService;
	
	
	/******************************* 接口部分   **************************************/
	
	
	/**
	 * 接口：获取收藏列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getCollection")
	@ResponseBody
	public Ret getCollection(HttpServletRequest request) {
		Integer pageNum = 1;
		Integer pageSize = Constants.PAGE_SIZE;
		
		String pageNumStr = request.getParameter("pageNum");
		String pageSizeStr = request.getParameter("pageSize");
		if(!StringUtils.isEmpty(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		if(!StringUtils.isEmpty(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		
		String userIdStr = request.getParameter("userId");
		if(StringUtils.isEmpty(userIdStr)) {
			return Ret.error("参数有误");
		}
		
		try {
			PageHelper.startPage(pageNum, pageSize);
			
			List<DbOpern> collectionList = opernService.getCollection(Integer.valueOf(userIdStr));
			
			PageInfo<DbOpern> pageInfo = new PageInfo<>(collectionList);
			
			return Ret.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return Ret.error(e.getMessage());
		}
	}
	
	
	
	/**
	 * 接口：获取曲谱分类列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getSubjectList")
	@ResponseBody
	public Ret getSubjectList(HttpServletRequest request) {
		String pageNumStr = request.getParameter("pageNum");
		String pageSizeStr = request.getParameter("pageSize");
		Integer pageNum = 1;
		Integer pageSize = Constants.PAGE_SIZE;
		List<DbSubject> subjects = Lists.newArrayList();
		
		if(!StringUtils.isEmpty(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		
		if(!StringUtils.isEmpty(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		
		try {
			PageHelper.startPage(pageNum, pageSize);
			PageHelper.orderBy("subject_no");
			
			List<DbSubject> subjectList = subjectService.selectAll();
			if(subjectList.isEmpty()) {
				return Ret.warn("暂无分类");
			}
			
			
			for(DbSubject subject : subjectList) {
				DbSonsubject sonsubject = new DbSonsubject();
				sonsubject.setParentSubjectName(subject.getSubjectName());
				List<DbSonsubject> sonsubjects = sonSubjectService.select(sonsubject);
				if(sonsubjects == null || sonsubjects.isEmpty()) {
					subject.setHasSonSubject(false);
				} else {
					subject.setHasSonSubject(true);
				}
				subjects.add(subject);
			}
			
			PageInfo<DbSubject> pageInfo = new PageInfo<>(subjectList);
			pageInfo.setList(subjects);
			
			return Ret.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return Ret.error(e.getMessage());
		}
	}
	
	/**
	 * 接口：获取子分类列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getSonSubjectList")
	@ResponseBody
	public Ret getSonSubjectList(HttpServletRequest request) {
		String pageNumStr = request.getParameter("pageNum");
		String pageSizeStr = request.getParameter("pageSize");
		Integer pageNum = 1;
		Integer pageSize = Constants.PAGE_SIZE;
		if(!StringUtils.isEmpty(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		if(!StringUtils.isEmpty(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		
		String subjectName = request.getParameter("subjectName");
		if(StringUtils.isEmpty(subjectName)) {
			return Ret.warn("参数有误");
		}
		
		try {
			PageHelper.startPage(pageNum, pageSize);
			PageHelper.orderBy("son_subject_no");
			
			DbSonsubject dbSonsubject = new DbSonsubject();
			dbSonsubject.setParentSubjectName(subjectName);
			
			List<DbSonsubject> sonSubjectList = sonSubjectService.select(dbSonsubject);
			
			PageInfo<DbSonsubject> pageInfo = new PageInfo<>(sonSubjectList);
			
			return Ret.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return Ret.error("发生异常，请稍后重试！");
		}
	}

	
	/**
	 * 接口：获取书名列表，需要参数：分类名和页码序号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getBookList")
	@ResponseBody
	public Ret getBookList(HttpServletRequest request) {
		String pageNumStr = request.getParameter("pageNum");
		String pageSizeStr = request.getParameter("pageSize");
		Integer pageNum = 1;
		Integer pageSize = Constants.PAGE_SIZE;
		if(!StringUtils.isEmpty(pageNumStr)) {
			pageNum = Integer.parseInt(pageNumStr);
		}
		if(!StringUtils.isEmpty(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		
		String bookLabel = request.getParameter("bookLabel");
		if(StringUtils.isEmpty(bookLabel)) {
			return Ret.error("参数有误");
		}
		
		try {
			PageHelper.startPage(pageNum, pageSize);
			PageHelper.orderBy("book_no");
			
			DbBook dbBook = new DbBook();
			dbBook.setBookLabel(bookLabel);
			
			List<DbBook> bookList = bookService.select(dbBook);
			
			PageInfo<DbBook> pageInfo = new PageInfo<>(bookList);
			
			return Ret.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return Ret.error(e.getMessage());
		}
	}
	
	
	/**
	 * 接口：获取曲谱列表,并修改统计表中的统计次数
	 * @param request 请求
	 * @return
	 */
	@RequestMapping(value = "getOpernList")
	@ResponseBody
	public Ret getOpernList(HttpServletRequest request) {
		String pageNumStr = request.getParameter("pageNum");
		String pageSizeStr = request.getParameter("pageSize");
		Integer pageNum = 1;
		Integer pageSize = Constants.PAGE_SIZE;
		if(!StringUtils.isEmpty(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		if(!StringUtils.isEmpty(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		
		String userIdStr = request.getParameter("userId");
		if(StringUtils.isEmpty(userIdStr)) {
			return Ret.warn("用户Id不能为空");
		}
		Integer userId = Integer.valueOf(userIdStr);
		
		
		//后期扩展使用
//		String userType = request.getParameter("userType");
//		switch (userType) {
//		case "liveUser":
//			
//			break;
//
//		default:
//			break;
//		}
		
		String opernLabel = request.getParameter("opernLabel");
		if(StringUtils.isEmpty(opernLabel)) {
			return Ret.warn("曲谱对应的分类名和书名不能为空");
		}
		
		try {
			String[] bookInfo = opernLabel.split("_");
			String bookLabel = null;
			String bookName = null;
			if(bookInfo.length == 3) {
				bookLabel = bookInfo[0];
				bookName = bookInfo[1] + "_" + bookInfo[2];
			} else {
				bookLabel = bookInfo[0] + "_" + bookInfo[1];
				bookName = bookInfo[2] + "_" + bookInfo[3];
			}
			
			//修改统计表
			DbCount dbCount = new DbCount();
			dbCount.setName(bookName);
			dbCount.setLabel(bookLabel);
			
			DbCount dbCount2 = countService.selectOne(dbCount);
			if(dbCount2 == null) {
				dbCount.setAddTime(new Date());
				dbCount.setCount(1);
				dbCount.setType(1);
				dbCount.setPath("");
				countService.insert(dbCount);
			} else {
				dbCount2.setCount(dbCount2.getCount() + 1);
				dbCount2.setModifyTime(new Date());
				countService.updateByPrimaryKey(dbCount2);
			}
			
			//查询曲谱并分页
			PageHelper.startPage(pageNum, pageSize);
			PageHelper.orderBy("opern_no");
			
			List<HashMap<String, Object>> opernList = opernService.getOpernList(userId, opernLabel);
			
			PageInfo<HashMap<String, Object>> pageInfo = new PageInfo<>(opernList);
			
			return Ret.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return Ret.error(e.getMessage());
		}
		
	}
	
	
	/**
	 * 接口：获取热门曲谱列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getHotterOperns")
	@ResponseBody
	public Ret getHotterOperns(HttpServletRequest request) {
		Integer type = 1;//定义获取热门的类型：0表示按曲谱，1表示按书名，默认按书名
		Integer number = 20;//定义默认提供给客户端的最大数量
		
		String typeStr = request.getParameter("type");
		String numberStr = request.getParameter("number");
		
		if(!StringUtils.isEmpty(typeStr)) {
			type = Integer.parseInt(typeStr);
		}
		
		if(!StringUtils.isEmpty(numberStr)) {
			number = Integer.parseInt(numberStr);
		}
		
		DbCount dbCount = new DbCount();
		dbCount.setType(type);
		
		try {
			PageHelper.startPage(1, number);
			PageHelper.orderBy("count desc");
			
			List<DbCount> countList = countService.select(dbCount);
			
			return Ret.success(countList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return Ret.error(e.getMessage());
		}
		
	}
	
	
	/**
	 * 接口：搜索曲谱,并修改统计表中的统计次数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchOpern")
	@ResponseBody
	public Ret searchOpern(HttpServletRequest request) {
		Integer type = 1;//定义搜索的类型：0表示按曲谱，1表示按书名，2表示按分类名，默认为按书名
		Integer pageNum = 1;//获取客户端需要的页码,默认为第一页
		Integer pageSize = Constants.PAGE_SIZE;
		List<DbOpern> opernList = null;//定义曲谱列表
		List<DbBook> bookList = null;//定义书名列表
		
		String pageSizeStr = request.getParameter("pageSize");
		String pageNumStr = request.getParameter("pageNum");
		String typeStr = request.getParameter("type");
		String searchStr = request.getParameter("searchStr");
		if(!StringUtils.isEmpty(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		if(!StringUtils.isEmpty(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		
		if(StringUtils.isEmpty(searchStr)) {
			return Ret.error("参数有误");
		}
		
		if(!StringUtils.isEmpty(typeStr)) {
			type = Integer.parseInt(typeStr);
		}
		
		PageHelper.startPage(pageNum, pageSize);
		
		switch (type) {
		case 0:
			opernList = opernService.searchOperns(searchStr);
			if(opernList != null && !opernList.isEmpty() && opernList.size() > 0) {
				opernService.addSearchNum(opernList);//修改统计表中的统计次数
				
				PageInfo<DbOpern> pageInfo = new PageInfo<>(opernList);
				
				return Ret.success(pageInfo);
			}
			
			return Ret.warn("没有搜索到相关曲谱");
			
		case 1:
			bookList = bookService.searchByBookName(searchStr);
			break;

		case 2:
			bookList = bookService.searchBySubjectName(searchStr);
			break;
			
		default:
			return null;
		}
		
		bookService.addSearchNum(bookList);//修改统计表中的统计次数
		
		PageInfo<DbBook> pageInfo = new PageInfo<>(bookList);
		
		return Ret.success(pageInfo);
	}
	
	

	
	/******************************* 后台部分   **************************************/

	/**
	 * 仅对本地有效：创建曲谱库并将曲谱文件上传到OSS
	 */
	@RequestMapping(value = "createDatabase")
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public Ret createDatabase(HttpServletRequest request) {
		String subjectName = null;//分类名
		String sonSubjectName = null;//子分类名
		String bookName = null;//曲谱书名
		StringBuffer stringBuffer = null;//用于操作文件路径的变量1
		StringBuffer stringBuffer2 = null;//用于操作文件路径的变量2
		StringBuffer stringBuffer3 = null;//用于操作文件路径的变量3
		File[] subjectFiles = null;//分类文件数组
		File[] sonSubjectFiles = null;//子分类文件数组
		File[] bookFiles = null;//曲谱书文件数组
		File localFile = null;//本地文件
		
		try {
			localFile = new File("F:/Database");//获取本地文件
			if(!localFile.exists() || !localFile.isDirectory()) {
				return Ret.warn("本地文件有误");
			}
			
			doSubject(localFile);//处理分类表
			
			subjectFiles = localFile.listFiles();
			
			for(File subjectFile : subjectFiles) {
				//每次循环开始都将路径初始化为database
				stringBuffer = new StringBuffer("database");
				subjectName = subjectFile.getName().split("_")[1];
				//将路径改变为database/subjectName
				stringBuffer.append("/").append(subjectName);
				
				if(SUBJECT_LIST.contains(subjectName)) {//有子分类
					//处理子分类表
					doSonSubject(subjectFile);
					
					//获取该分类对应的子分类文件数组
					sonSubjectFiles = subjectFile.listFiles();
					for(File sonSubjectFile : sonSubjectFiles) {
						stringBuffer2 = new StringBuffer(stringBuffer.toString());
						sonSubjectName = sonSubjectFile.getName().split("_")[1];
						//将路径改变为 database/subjectName/sonSubjectName
						stringBuffer2.append("/").append(sonSubjectName);
						//处理曲谱书表
						doBook(sonSubjectFile, subjectName, sonSubjectName);
						//获取曲谱书文件数组
						bookFiles = sonSubjectFile.listFiles();
						for(File bookFile : bookFiles) {
							bookName = bookFile.getName().substring(bookFile.getName().indexOf("_") + 1);
							stringBuffer3 = new StringBuffer(stringBuffer2.toString());
							//将路径改变为 database/subjectName/sonSubjectName/bookName
							stringBuffer3.append("/").append(bookName);
							
							//处理曲谱表并上传文件
							doOpern(bookFile, stringBuffer3);
						}
					}
				} else {
					//没有子分类
					sonSubjectName = "";
					
					//处理曲谱书表
					doBook(subjectFile, subjectName, sonSubjectName);
					//获取曲谱书文件数组
					bookFiles = subjectFile.listFiles();
					for(File bookFile : bookFiles) {
						stringBuffer3 = new StringBuffer(stringBuffer.toString());
						bookName = bookFile.getName().substring(bookFile.getName().indexOf("_") + 1);
						//将路径改变为 database/subjectName/bookName
						stringBuffer3.append("/").append(bookName);
						
						//处理曲谱表并上传文件
						doOpern(bookFile, stringBuffer3);
					}
				}
			}
			
			return Ret.success();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return Ret.error(e.getMessage());
		}
	}
	
	
	/**
	 * 后台：模板列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "templetList")
	public String templetList(HttpServletRequest request, Model model) {
		try {
			List<DbTemplet> templetList = templetService.selectAll();
			
			model.addAttribute("templetList", templetList);
			
			return "database/templet";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	}
	
	
	/**
	 * 后台：获取模板信息
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTemplet")
	public DbTemplet  getTemplet(HttpServletRequest request){
		String templetName=request.getParameter("templetName");
		DbTemplet templet=new DbTemplet();
		templet.setName(templetName);
		try {
			templet =templetService.selectOne(templet);
			
			return templet;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	}
	
	
	
	/**
	 * 后台：进入模板添加界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toAddTemplet")
	public String toAddTemplet(HttpServletRequest request, Model model) {
		List<DbSubject> subjectList = null;
		
		try {
			subjectList = subjectService.selectAll();
			
			model.addAttribute("subjectList", subjectList);
			
			return "database/addtemplet";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	}
	
	
	/**
	 * 后台：添加模板
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addTemplet")
	@ResponseBody
	public String addTemplet(HttpServletRequest request) {
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String sonSubject = request.getParameter("sonSubject");
		String book = request.getParameter("book");
		String press = request.getParameter("press");
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(subject) || StringUtils.isEmpty(book)) {
			return "<script>alert('参数有误');history.go(-1);</script>";
		}
		
		try {
			DbTemplet dbTemplet = new DbTemplet();
			dbTemplet.setName(name);
			List<DbTemplet> templets = templetService.select(dbTemplet);
			if(templets != null && !templets.isEmpty()) {
				return "<script>alert('该模板已经存在');history.go(-1);</script>";
			}
			
			dbTemplet.setAddtime(new Date());
			dbTemplet.setSubject(subject);
			dbTemplet.setSonsubject(sonSubject == null ? "" : sonSubject);
			dbTemplet.setBook(book);
			dbTemplet.setPress(press == null ? "" : press);
			
			templetService.insert(dbTemplet);
			
			return "<script>alert('添加模板成功');window.location.href='/database/templetList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return "<script>alert('发生异常，请稍后重试');history.go(-1);</script>";
		}
	}
	
	/**
	 * 后台：进入编辑模板界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toEditTemplet")
	public String toEditeTemplet(HttpServletRequest request, Model model) {
		String templetId = request.getParameter("templetId");
		List<DbSubject> subjectList = null;
		
		try {
			subjectList = subjectService.selectAll();
			
			model.addAttribute("subjectList", subjectList);
			DbTemplet dbTemplet = new DbTemplet();
			dbTemplet.setId(Integer.valueOf(templetId));
			
			dbTemplet = templetService.selectByPrimaryKey(dbTemplet);
			
			model.addAttribute("dbTemplet", dbTemplet);
			
			return "database/modifytemplet";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	}
	
	
	/**
	 * 后台：编辑模板
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "editTemplet")
	@ResponseBody
	public String editTemplet(HttpServletRequest request) {
		String templetId = request.getParameter("templetId");
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String sonSubject = request.getParameter("sonSubject");
		String book = request.getParameter("book");
		String press = request.getParameter("press");
		
		if(StringUtils.isEmpty(templetId) || StringUtils.isEmpty(name) || 
				StringUtils.isEmpty(subject) || StringUtils.isEmpty(book)
				|| StringUtils.isEmpty(press)) {
			return "<script>alert('参数有误');history.go(-1);</script>";
		}
		
		if(sonSubject == null) {
			sonSubject = "";
		}
		
		try {
			DbTemplet dbTemplet = new DbTemplet();
			dbTemplet.setId(Integer.valueOf(templetId));
			dbTemplet.setName(name);
			dbTemplet.setSubject(subject);
			dbTemplet.setSonsubject(sonSubject);
			dbTemplet.setBook(book);
			dbTemplet.setPress(press);
			dbTemplet.setModifytime(new Date());
			templetService.updateByPrimaryKeySelective(dbTemplet);
			
			
			return "<script>alert('编辑模板成功');window.location.href='/database/templetList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return "<script>alert('发生异常，请稍后重试');history.go(-1);</script>";
		}
	}
	
	
	/**
	 * 后台：删除模板
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteTemplet")
	@ResponseBody
	public String deleteTemplet(HttpServletRequest request) {
		String templetIdStr = request.getParameter("templetId");
		Integer templetId = null;
		if(StringUtils.isEmpty(templetIdStr)) {
			return "<script>alert('参数不能为空');history.go(-1);</script>";
		}
		
		try {
			templetId = Integer.valueOf(templetIdStr);
			
			if(templetService.selectByPrimaryKey(templetId) == null) {
				return "<script>alert('参数有误');history.go(-1);</script>";
			}
			
			int num = templetService.deleteByPrimaryKey(templetId);
			
			if(num == 1) {
				return "<script>alert('删除模板成功');window.location.href='/database/templetList';</script>";
			} else {
				return "<script>alert('删除失败');history.go(-1);</script>";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return "<script>alert('发生异常');history.go(-1);</script>";
		}
	}
	
	
	/**
	 * 后台：分类列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "subjectList")
	public String subjectList(HttpServletRequest request, Model model) {
		List<DbSubject> subjectList = null;
		try {
			subjectList = subjectService.selectAll();
			
			model.addAttribute("subjectList", subjectList);
			
			return "database/subject";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	}
	
	
	/**
	 * 后台：进入修改分类界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toModifySubject")
	public String toModifySubject(HttpServletRequest request,Model model) {
		String subjectID = request.getParameter("subjectID"); 
	
		try {  
			DbSubject dbSubject = new DbSubject();
			dbSubject.setId(Integer.valueOf(subjectID));
			
			dbSubject = subjectService.selectByPrimaryKey(dbSubject);
			
			model.addAttribute("dbSubject", dbSubject);
			
			return "database/modifysubject";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	
	}
	
	/**
	 * 后台：修改分类界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ModifySubject") 
	@ResponseBody
	public String ModifySubject(HttpServletRequest request,Model model) {
		String subjectID = request.getParameter("subjectID"); 
		String subjectNo=request.getParameter("subjectNo");
		String subjectName=request.getParameter("subjectName");
	 
		try { 
			DbSubject dbSubject = new DbSubject();
			dbSubject.setId(Integer.valueOf(subjectID));
			dbSubject.setSubjectNo(subjectNo);
			dbSubject.setSubjectName(subjectName);
			dbSubject.setModifyTime(new Date()); 
		    subjectService.updateByPrimaryKeySelective(dbSubject);  
			return "<script>alert('修改分类成功');window.location.href='/database/subjectList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	
	}
	
	/**
	 * 后台：进入修改子分类界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toModifySonSubject")
	public String toModifySonSubject(HttpServletRequest request,Model model) {
		String sonSubjectID = request.getParameter("sonSubjectID"); 
 
		
		try {  
			DbSonsubject dbSonSubject = new DbSonsubject();
			dbSonSubject.setId(Integer.valueOf(sonSubjectID));
			
			dbSonSubject = sonSubjectService.selectByPrimaryKey(dbSonSubject);
			
			model.addAttribute("dbSonSubject", dbSonSubject);
			
			return "database/modifysonsubject";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	
	}
	
	/**
	 * 后台：修改子分类界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ModifySonSubject")
	@ResponseBody
	public String ModifySonSubject(HttpServletRequest request,Model model) {
		String sonsubjectID = request.getParameter("sonsubjectID"); 
		String sonsubjectNo=request.getParameter("sonsubjectNo");
		String sonsubjectName=request.getParameter("sonsubjectName");
		String parentsubjectName=request.getParameter("parentsubjectName");
		try {  
			DbSonsubject dbSonSubject = new DbSonsubject();
			dbSonSubject.setId(Integer.valueOf(sonsubjectID));
			dbSonSubject.setModifytime(new Date());
			dbSonSubject.setSonSubjectNo(sonsubjectNo);
			dbSonSubject.setSonSubjectName(sonsubjectName);
			dbSonSubject.setParentSubjectName(parentsubjectName);
			sonSubjectService.updateByPrimaryKey(dbSonSubject);
			return "<script>alert('修改子分类成功');window.location.href='/database/sonSubjectList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	
	}
	
	/**
	 * 后台：进入修改书界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toModifyBook")
	public String toModifyBook(HttpServletRequest request,Model model) {
		String bookID = request.getParameter("bookID"); 
 
		
		try {  
			DbBook dbBook = new DbBook();
			dbBook.setId(Integer.valueOf(bookID));
			
			dbBook = bookService.selectByPrimaryKey(dbBook);
			
			model.addAttribute("dbBook", dbBook);
			
			return "database/modifybook";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	
	}
	
	/**
	 * 后台：修改书
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "ModifyBook")
	@ResponseBody
	public String ModifyBook(HttpServletRequest request,Model model) {
		String bookID = request.getParameter("bookID"); 
		String bookNo=request.getParameter("bookNo");
		String bookName=request.getParameter("bookName");
		String bookLabel=request.getParameter("bookLabel");
		String press=request.getParameter("press");
		try {  
			DbBook dbBook = new DbBook();
			dbBook.setId(Integer.valueOf(bookID));
			dbBook.setModifyTime(new Date());
			dbBook.setBookNo(bookNo);
			dbBook.setBookName(bookName);
			dbBook.setBookLabel(bookLabel); 
			dbBook.setPress(press);
			bookService.updateByPrimaryKey(dbBook);
			return "<script>alert('修改书成功');window.location.href='/database/bookList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	
	}
	
	
	/**
	 * 后台：进入修改出版社界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toModifyPress")
	public String toModifyPress(HttpServletRequest request,Model model) {
		String pressID = request.getParameter("pressID"); 
 
		
		try {  
			DbPress dbPress = new DbPress();
			dbPress.setId(Integer.valueOf(pressID));
			
			dbPress = pressService.selectByPrimaryKey(dbPress);
			
			model.addAttribute("dbPress", dbPress);
			
			return "database/modifypress";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	
	}
	
	/**
	 * 后台：修改出版社
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "Modifypress")
	@ResponseBody
	public String Modifypress(HttpServletRequest request,Model model) {
		String pressID = request.getParameter("pressID"); 
		String pressno=request.getParameter("pressno");
		String name=request.getParameter("name");
		String abbreviation=request.getParameter("abbreviation"); 
		try {  
			DbPress dbPress = new DbPress();
			dbPress.setId(Integer.valueOf(pressID));
			dbPress.setModifytime(new Date()); 
			dbPress.setAbbreviation(abbreviation);
			dbPress.setName(name);
			dbPress.setPressno(pressno);
			pressService.updateByPrimaryKey(dbPress);
			return "<script>alert('修改出版社成功');window.location.href='/database/pressList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	
	}
	/**
	 * 后台：进入添加分类界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toAddSubject")
	public String toAddSubject(HttpServletRequest request) {
		
		return "database/addsubject";
	}
	
	
	/**
	 * 后台：添加分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addSubject")
	@ResponseBody
	public String addSubject(HttpServletRequest request) {
		String subjectNo = request.getParameter("subjectNo");
		String subjectName = request.getParameter("subjectName");
		
		if(StringUtils.isEmpty(subjectName) || StringUtils.isEmpty(subjectNo)) {
			return "<script>alert('参数有误');history.go(-1);</script>";
		}
		
		try {
			DbSubject subject = new DbSubject();
			subject.setSubjectName(subjectName);
			List<DbSubject> subjects = subjectService.select(subject);
			if(subjects != null && !subjects.isEmpty()) {
				return "<script>alert('该分类已经存在');history.go(-1);</script>";
			}
			
			subject.setAddTime(new Date());
			subject.setSubjectNo(subjectNo);
			
			subjectService.insert(subject);
			
			return "<script>alert('添加分类成功');window.location.href='/database/subjectList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return "<script>alert('发生异常，请稍后重试');history.go(-1);</script>";
		}
	}
	
	
	/**
	 * 后台：子分类列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sonSubjectList")
	public String sonSubjectList(HttpServletRequest request, Model model) {
		
		List<DbSonsubject> sonSubjectList = null;
		
		String pageNumStr = request.getParameter("pageNum");
		Integer pageNum = 1;
		if(!StringUtils.isEmpty(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		
		try {
			PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
			 sonSubjectList = sonSubjectService.selectAll();
			
			PageInfo<DbSonsubject> pageInfo = new PageInfo<>(sonSubjectList);
			
			model.addAttribute("pageInfo", pageInfo);
			
			return "database/sonsubject";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	}
	
	
	/**
	 * 后台：进入子分类添加界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toAddSonSubject")
	public String toAddSonSubject(HttpServletRequest request, Model model) {
		List<DbSubject> subjectList = null;
		
		try {
			subjectList = subjectService.selectAll();
			
			model.addAttribute("subjectList", subjectList);
			
			return "database/addsonsubject";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
		
	}
	
	
	/**
	 * 后台：添加子分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addSonSubject")
	@ResponseBody
	public String addSonSubject(HttpServletRequest request) {
		String subjectName = request.getParameter("subjectName");
		String sonSubjectNo = request.getParameter("sonSubjectNo");
		String sonSubjectName = request.getParameter("sonSubjectName");
		if(StringUtils.isEmpty(subjectName) || StringUtils.isEmpty(sonSubjectNo) 
				|| StringUtils.isEmpty(sonSubjectName)) {
			return "<script>alert('参数有误');history.go(-1);</script>";
		}
		
		try {
			DbSonsubject dbSonsubject = new DbSonsubject();
			dbSonsubject.setSonSubjectName(sonSubjectName);
			dbSonsubject.setParentSubjectName(subjectName);
			List<DbSonsubject> sonsubjects = sonSubjectService.select(dbSonsubject);
			if(sonsubjects != null && !sonsubjects.isEmpty()) {
				return "<script>alert('该子分类已经存在');history.go(-1);</script>";
			}
			
			dbSonsubject.setAddtime(new Date());
			dbSonsubject.setSonSubjectNo(sonSubjectNo);
			
			sonSubjectService.insert(dbSonsubject);
			
			return "<script>alert('添加子分类成功');window.location.href='/database/sonSubjectList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return "<script>alert('发生异常，请稍后重试');history.go(-1);</script>";
		}
	}
	
	
	/**
	 * 后台：出版社列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pressList")
	public String pressList(HttpServletRequest request, Model model) {
		List<DbPress> pressList = null;
		
		try {
			pressList = pressService.selectAll();
			
			model.addAttribute("pressList", pressList);
			
			return "database/press";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	}
	
	
	/**
	 * 后台：进入出版社添加界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toAddPress")
	public String toAddPress(HttpServletRequest request) {
		
		return "database/addpress";
	}
	
	
	/**
	 * 后台：添加出版社
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addPress")
	@ResponseBody
	public String addPress(HttpServletRequest request) {
		String pressNo = request.getParameter("pressNo");
		String pressName = request.getParameter("pressName");
		String abbreviation = request.getParameter("abbreviation");
		
		if(StringUtils.isEmpty(pressNo) || StringUtils.isEmpty(pressName) 
										|| StringUtils.isEmpty(abbreviation)) {
			return "<script>alert('参数有误');history.go(-1);</script>";
		}
		
		try {
			DbPress press = new DbPress();
			press.setName(pressName);
			List<DbPress> presses = pressService.select(press);
			if(presses != null && !presses.isEmpty()) {
				return "<script>alert('该出版社已经存在');history.go(-1);</script>";
			}
			
			press.setAddtime(new Date());
			press.setPressno(pressNo);
			press.setAbbreviation(abbreviation);
			
			pressService.insert(press);
			
			return "<script>alert('添加出版社成功');window.location.href='/database/pressList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return "<script>alert('发生异常，请稍后重试');history.go(-1);</script>";
		}
	}
	
	
	
	/**
	 * 后台：曲谱书列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "bookList")
	public String bookList(HttpServletRequest request, Model model) {
		List<DbBook> bookList = null;
		
		String pageNumStr = request.getParameter("pageNum");
		Integer pageNum = 1;
		if(!StringUtils.isEmpty(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		
		try {
			PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
			bookList = bookService.selectAll();
			
			PageInfo<DbBook> pageInfo = new PageInfo<>(bookList);
			
			model.addAttribute("pageInfo", pageInfo);
			
			return "database/book";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	}
	
	
	/**
	 * 后台：进入添加曲谱书界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toAddBook")
	public String toAddBook(HttpServletRequest request, Model model) {
		List<DbSubject> subjectList = null;
		List<DbPress> pressList = null; 
		
		try {
			subjectList = subjectService.selectAll();
			pressList = pressService.selectAll(); 
			model.addAttribute("subjectList", subjectList);
			model.addAttribute("pressList", pressList);
			
			return "database/addbook";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	}
	
	
	/**
	 * 后台：添加曲谱书
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addBook")
	@ResponseBody
	public String addBook(HttpServletRequest request) {
		String bookLabel = null;
		
		String subject = request.getParameter("subject");
		String sonSubject = request.getParameter("sonSubject");
		String bookNo = request.getParameter("bookNo");
		String bookName = request.getParameter("bookName");
		String press = request.getParameter("press");
		
		if(StringUtils.isEmpty(subject) || StringUtils.isEmpty(bookNo) || 
				StringUtils.isEmpty(bookName) || StringUtils.isEmpty(press)) {
			return "<script>alert('参数有误');history.go(-1);</script>";
		}
		
		bookLabel = StringUtils.isEmpty(sonSubject) ? subject : subject + "_" + sonSubject;
		
		try {
			DbBook dbBook = new DbBook();
			dbBook.setBookLabel(bookLabel);
			dbBook.setBookName(bookName + "_" + press);
			dbBook.setPress(press);
			List<DbBook> books = bookService.select(dbBook);
			if(books != null && !books.isEmpty()) {
				return "<script>alert('该书已经存在');history.go(-1);</script>";
			}
			
			dbBook.setAddTime(new Date());
			dbBook.setBookNo(bookNo);
			
			bookService.insert(dbBook);
			
			return "<script>alert('添加曲谱书成功');window.location.href='/database/bookList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return "<script>alert('发生异常，请稍后重试');history.go(-1);</script>";
		}
	}

	/**
	 * 后台：曲谱列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "opernList")
	public String opernList(HttpServletRequest request, Model model) {
		List<DbOpern> opernList = null;
		
		String pageNumStr = request.getParameter("pageNum");
		Integer pageNum = 1;
		if(!StringUtils.isEmpty(pageNumStr)) {
			pageNum = Integer.valueOf(pageNumStr);
		}
		
		try {
			PageHelper.startPage(pageNum, 20);
			opernList = opernService.selectAll();
			
			PageInfo<DbOpern> pageInfo = new PageInfo<>(opernList);
			
			model.addAttribute("pageInfo", pageInfo);
			
			return "database/opern";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	}
	
	/**
	 * 后台：进入曲谱添加界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toUploadOpern")
	public String toUploadOpern(HttpServletRequest request, Model model) {
		//定义模板列表
		List<DbTemplet> templetList = null;
		
		try {
			templetList = templetService.selectAll();
			
			if(templetList == null) {
				logger.info("模板表为空");
				return null;
			}
			
			model.addAttribute("templetList", templetList);
			
			return "database/addopern";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return null;
		}
	}
	
	
	/**
	 * 后台：添加和上传曲谱文件
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "uploadOpern")
	@ResponseBody
	@Transactional(rollbackFor = Exception.class)
	public String uploadOpern(HttpServletRequest request, @RequestParam("file")MultipartFile[] files) throws Exception {
		//获取页面传入的参数
		String subjectName = request.getParameter("subjectName");
		String sonSubjectName = request.getParameter("sonSubjectName");
		String bookName = request.getParameter("bookName");
		String press = request.getParameter("press");
		
		//作参数判断处理
		if(StringUtils.isEmpty(subjectName)) {
			return "<script>alert('分类名不能为空');history.go(-1);</script>";
		}
		if(StringUtils.isEmpty(bookName)) {
			return "<script>alert('书名不能为空');history.go(-1);</script>";
		}
		if(StringUtils.isEmpty(press)) {
			return "<script>alert('出版社不能为空');history.go(-1);</script>";
		}
		if(files.length == 0) {
			return "<script>alert('您还没有选择文件');history.go(-1);</script>";
		}
		
		/***********************定义变量**************************/
		File file = new File(multipartConfig.getLocation() + "\\file");
		if(!file.exists()) {
			file.createNewFile();
			file.setExecutable(true);
		}
		String opernNo = null;//定义曲谱编号
		String mainTitle = null;//定义主标题
		String assistantTitle = null;//定义曲谱副标题
		String opernLabel = null;
		String opernPath = "";//定义曲谱路径
		StringBuffer filePath = new StringBuffer(PUBLIC_ACCESS_PATH);//定义文件访问路径
		filePath.append("database").append("/").append(subjectName);
		if(!StringUtils.isEmpty(sonSubjectName)) {
			filePath.append("/").append(sonSubjectName);
		}
		filePath.append("/").append(bookName + "_" + press);
		
		try {
			//对文件进行处理: 设置曲谱相关属性,同时上传文件至OSS
			for(int i=0; i<files.length; i++) {
				MultipartFile multipartFile = files[i];
				
				//文件转换
				multipartFile.transferTo(file);
				
				String originalName = multipartFile.getOriginalFilename();
				StringBuffer sBuffer = new StringBuffer(filePath);
				String[] name = originalName.split("_");
				opernNo = name[0];//设置曲谱编号
				//设置单个文件访问路径
				sBuffer.append("/").append(name[1]).append("_").append(name[2]);
				
				//设置主标题
				mainTitle = name[1];
				
				//设置曲谱副标题
				if(name.length == 4) {
					assistantTitle = name[2];
					mainTitle += "_" + assistantTitle;
					sBuffer.append("_").append(name[3]);
				} else {
					assistantTitle = "";
				}
				
				//设置曲谱标签
				if(StringUtils.isEmpty(assistantTitle)) {
					opernLabel = subjectName + "_" + bookName + "_" + press;
				} else {
					opernLabel = sonSubjectName + "_" + sonSubjectName + "_" + bookName + "_" + press;
				}
				
				//设置曲谱路径
				opernPath += sBuffer.toString();
				
				if(i < files.length-1) {
					opernPath += ";";
				}
				
				//上传文件至OSS
				fileUtils.uploadFileToOss(file, sBuffer.toString().substring(PUBLIC_ACCESS_PATH.length()), Constants.pianoBucket);
			}
			
			//操作曲谱对应的书名表
			DbBook dbBook = new DbBook();
			dbBook.setBookName(bookName + "_" + press);
			if(bookService.select(dbBook) == null || bookService.select(dbBook).isEmpty()) {
				dbBook.setBookLabel(StringUtils.isEmpty(sonSubjectName) ? subjectName : sonSubjectName);
				dbBook.setPress(press);
				dbBook.setAddTime(new Date());
				bookService.insert(dbBook);
			}
			
			//操作曲谱表
			DbOpern dbOpern = new DbOpern();
			dbOpern.setBookName(bookName + "_" + press);//书名
			dbOpern.setMainTitle(mainTitle);//主标题
			dbOpern.setAssistantTitle(assistantTitle);//副标题
			List<DbOpern> operns = opernService.select(dbOpern);
			if(operns == null || operns.isEmpty()) {
				dbOpern.setAddTime(new Date());//添加时间
				dbOpern.setOpernNo(opernNo);//曲谱编号
				dbOpern.setOpernPath(opernPath);//曲谱路径
				dbOpern.setOpernLabel(opernLabel);
				opernService.insert(dbOpern);
			}
			
			return "<script>alert('上传成功');window.location.href='/database/opernList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			throw e;
		} finally {
			if(file.exists()) {
				file.delete();
			}
		}
	}
	
	
	/**
	 * 后台：删除曲谱文件并操作曲谱表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteOpern") 
	@ResponseBody
	public String deleteOpern(HttpServletRequest request) {
		DbOpern dbOpern = null;
		
		//获取页面传入的参数并作判断
		String opernIdStr = request.getParameter("opernId");
		Integer opernId = null;
		if(StringUtils.isEmpty(opernIdStr)) {
			return "<script>alert('参数不能为空');history.go(-1);</script>";
		}
		
		try {
			opernId = Integer.valueOf(opernIdStr);
			dbOpern = opernService.selectByPrimaryKey(opernId);
			if(dbOpern == null) {
				return "<script>alert('参数有误');history.go(-1);</script>";
			}
			
			String opernPath = dbOpern.getOpernPath();
			String[] paths = opernPath.split(";");
			for(String path : paths) {
				aliyunConfig.ossclient().deleteObject(Constants.pianoBucket, path.substring(path.indexOf("database")));
			}
			
			opernService.deleteByPrimaryKey(opernId);
			
			return "<script>alert('删除曲谱成功');window.locatin.href='/database/opernList';</script>";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return "<script>alert('发生异常，请稍后重试');history.go(-1);</script>";
		}
	}
	

	/******************************* 方法部分   **************************************/
	
	
	/**
	 * 本地方法：处理分类表
	 * @param subjects
	 * @return
	 */
	private void doSubject(File localFile) throws Exception {
		File[] subjects = localFile.listFiles();//分类文件数组
		DbSubject dbSubject = null;//分类对象
		File subjectFile = null;//分类文件
		
		//处理分类表
		for(int i=0; i<subjects.length; i++) {
			dbSubject = new DbSubject();
			subjectFile = subjects[i];
			String[] subjectInfo = subjectFile.getName().split("_");
			
			dbSubject.setSubjectName(subjectInfo[1]);
			List<DbSubject> subjects2 = subjectService.select(dbSubject);
			if(subjects2 != null && !subjects2.isEmpty()) {
				continue;
			}
			
			dbSubject.setAddTime(new Date());
			dbSubject.setSubjectNo(subjectInfo[0]);
			
			subjectService.insert(dbSubject);
		}
	}
	
	
	/**
	 * 本地方法：处理子分类表
	 * @param sonSubjects
	 * @param subjectName
	 */
	private void doSonSubject(File subjectFile) throws Exception {
		//获取分类名
		String subjectName = subjectFile.getName().split("_")[1];
		
		//判断该分类是否包含子分类
		if(SUBJECT_LIST.contains(subjectName)) {
			File[] sonSubjects = subjectFile.listFiles();//获取子分类文件数组
			DbSonsubject dbSonsubject = null;//子分类对象
			String sonSubjectOriginalName = null;//原始子分类文件名
			String[] name = null;//对原始子分类名进行分割得到的文件名信息数组
			
			//操作子分类表
			for(int i=0; i<sonSubjects.length; i++) {
				sonSubjectOriginalName = sonSubjects[i].getName();
				name = sonSubjectOriginalName.split("_");
				
				dbSonsubject = new DbSonsubject();
				dbSonsubject.setParentSubjectName(subjectName);
				if(name.length == 1) {
					dbSonsubject.setSonSubjectName(name[0]);
				} else {
					dbSonsubject.setSonSubjectName(name[1]);
				}
				
				List<DbSonsubject> sonsubjects2 = sonSubjectService.select(dbSonsubject);
				if(sonsubjects2 != null && !sonsubjects2.isEmpty()) {
					continue ;
				}
				
				dbSonsubject.setAddtime(new Date());
				if(name.length == 1) {
					dbSonsubject.setSonSubjectNo("");
				} else {
					dbSonsubject.setSonSubjectNo(name[0]);
				}
				
				sonSubjectService.insert(dbSonsubject);
			}
		}
	}
	
	/**
	 * 本地方法：处理曲谱书表
	 * @param subjectFile 		分类文件或子分类文件
	 * @param subjectName 		分类名
	 * @param sonSubjectName	子分类名
	 * @throws Exception
	 */
	private void doBook(File subjectFile,String subjectName, String sonSubjectName) throws Exception {
		DbBook dbBook = null;//曲谱书对象
		DbPress dbPress = null;//出版社对象
		File bookFile = null;//曲谱书文件
		String bookOriginalName = null;//原始曲谱书文件名（格式：编号_曲谱书名_出版社简称）
		String[] name = null;//数组[编号，曲谱书名，出版社简称]
		String bookName = null;//曲谱书名
		String bookNo = null;//曲谱书编号
		String press = null;//出版社(简称)
		
		//获取曲谱书所属分类名 (格式：分类名     或     分类名_子分类名)
		String bookLabel = StringUtils.isEmpty(sonSubjectName) ? subjectName : subjectName + "_" + sonSubjectName;
		
		//获取该分类目录下的所有曲谱书
		File[] books = subjectFile.listFiles();
		
		//操作数据库中的曲谱书表和出版社表
		for(int i=0; i<books.length; i++) {
			dbBook = new DbBook();
			bookFile = books[i];
			
			bookOriginalName = bookFile.getName();
			name = bookOriginalName.split("_");
			bookNo = name[0];
			bookName = name[1] + "_" + name[2];//数据库中曲谱书名保存格式：原曲谱书名_出版社简称
			press = name[2];
			
			dbPress = new DbPress();
			dbPress.setAbbreviation(press);
			
			List<DbPress> presses = pressService.select(dbPress);
			if(presses == null || presses.isEmpty()) {
				dbPress.setAddtime(new Date());
				dbPress.setPressno("");
				dbPress.setName("");
				pressService.insert(dbPress);//添加出版社
			}
			
			
			dbBook.setBookName(bookName);
			dbBook.setBookLabel(bookLabel);
			dbBook.setPress(press);
			
			List<DbBook> books2 = bookService.select(dbBook);
			if(books2 != null && !books2.isEmpty()) {
				continue;
			}
			
			dbBook.setAddTime(new Date());
			dbBook.setBookNo(bookNo);
			bookService.insert(dbBook);//添加曲谱书
		}
	}
	
	
	/**
	 * 本地方法：处理曲谱表并将曲谱上传到OSS
	 * @param bookFile 曲谱书
	 * @param stringBuffer  操作文件路径变量
	 * @throws Exception
	 */
	private void doOpern(File bookFile, StringBuffer stringBuffer) throws Exception {
		DbOpern dbOpern = null;//曲谱对象
		File opernFile = null;//曲谱文件
		String originalName = null;//曲谱原始文件名
		String[] opernInfo = null;//数组[编号,曲谱名主标题，曲谱名副标题，图片序号.图片后缀]
		String opernFilePath = null;//曲谱文件保存和访问路径
		String opernNo = null;//曲谱编号
		String mainTitle = null;//曲谱主标题
		String assistantTitle = null;//曲谱副标题
		File[] operns = bookFile.listFiles();//曲谱文件数组
		StringBuffer stringBuffer2 = null;//用于操作文件路径的变量
		String opernLabel = stringBuffer.toString().replace("/", "_");
		opernLabel = opernLabel.substring(opernLabel.indexOf("_") + 1);
		
		try {
			//处理曲谱表并上传曲谱文件至OSS
			for(int i=0; i<operns.length; i++) {
				opernFile = operns[i];
				originalName = opernFile.getName();
				opernInfo = originalName.split("_");
				
				//往路径中追加文件名作为新路径
				stringBuffer2 = new StringBuffer(stringBuffer.toString());
				stringBuffer2.append("/").append(originalName.substring(originalName.indexOf("_") + 1));
				opernFilePath = stringBuffer2.toString();		
				
				//若上传到OSS不成功，则抛出异常
				if(!fileUtils.uploadFileToOss(opernFile, opernFilePath, Constants.pianoBucket)) {
					throw new Exception();
				}
				
				//单个曲谱图片的访问路径
				opernFilePath = PUBLIC_ACCESS_PATH + opernFilePath;
				
				opernNo = opernInfo[0];
				mainTitle = opernInfo[1];
				if(opernInfo.length == 4) {
					assistantTitle = opernInfo[2];
					mainTitle += "_" + assistantTitle;
				} else {
					assistantTitle = "";
				}
				
				dbOpern = new DbOpern();
				dbOpern.setBookName(bookFile.getName().substring(bookFile.getName().indexOf("_") + 1));
				dbOpern.setOpernNo(opernNo);
				dbOpern.setMainTitle(mainTitle);
				dbOpern.setAssistantTitle(assistantTitle);
				dbOpern.setOpernLabel(opernLabel);
				
				DbOpern dbOpern2 = opernService.selectOne(dbOpern);//查询数据库中有无该条记录
				if(dbOpern2 == null) {
					dbOpern.setAddTime(new Date());
					dbOpern.setOpernPath(opernFilePath);
					opernService.insert(dbOpern);//插入一条曲谱信息
				} else {
					dbOpern2.setOpernPath(dbOpern2.getOpernPath() + ";" + opernFilePath);
					opernService.updateByPrimaryKeySelective(dbOpern2);//更新一条曲谱信息
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			throw e;
		}
	}
	
}
