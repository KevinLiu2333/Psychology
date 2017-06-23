package com.klsw.website.controller.bg;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.common.mall.model.TMallBusinessMessage; 
import com.klsw.website.controller._BaseController;
import com.klsw.website.service.BusinessMessageService;

 
@Controller
@RequestMapping("/manage")
public class Bg_messageController extends _BaseController {
	static final int PAGESIZE = 3;
	@Autowired
	private BusinessMessageService messageService;
	
	@RequestMapping(value="order/messageList")
    public ModelAndView messageList(HttpServletRequest request,Model model){
    	ModelAndView mav=new ModelAndView("background/bg_message/bg_message_list");
    	String pageNum=request.getParameter("pageNum");
		List<TMallBusinessMessage> messageList=null;
	 	if(pageNum==null){
			   PageHelper.startPage(1, PAGESIZE);
		}
		else{
			  PageHelper.startPage(Integer.parseInt(pageNum), PAGESIZE);
		} 
		 PageHelper.orderBy("ctime desc"); 
		try {
			messageList=messageService.selectAll(); 
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	PageInfo<TMallBusinessMessage> pageInfo = new PageInfo<>(messageList);
		mav.addObject("messageList", pageInfo); 
    	return mav;
    }
	 
	
}

