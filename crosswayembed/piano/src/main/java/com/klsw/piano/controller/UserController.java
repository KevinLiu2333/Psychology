package com.klsw.piano.controller;

import com.alibaba.druid.util.StringUtils;
import com.klsw.piano.service.TbPmUserService;
import com.klsw.piano.util.LoginHelper;
import com.klsw.pianoCommon.api.model.TbPmuser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liukun on 2016/11/3.
 * 用户相关
 */
@RequestMapping(value="/user")
@Controller
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    TbPmUserService tbPmUserService;

    /**
     * 后台：琴后台用户进入登录界面
     * @return
     * @author HKJ
     */
    @RequestMapping(value="/toLogin")
    public String toLogin(){
        return "user/login";
    }

    /**
     * 后台：琴后台用户登录
     * @param username
     *          用户名
     * @param password
     *          登录密码
     * @param response
     * @param request
     * @return
     * @author HKJ
     */
    @RequestMapping(value="/login")
    public String login(@RequestParam("username")String username,
                              @RequestParam("password")String password,
                              HttpServletResponse response,
                              HttpServletRequest request){
        //根据用户名查询用户
        TbPmuser tbPmuser = tbPmUserService.findByName(username);
        response.setContentType("text/html;charset=utf-8");
        try {
            //用户名为空
            if (tbPmuser == null) {
                response.getWriter().println("<script>alert('用户名不存在！');window.location='/User/toLogin'</script>");
                return null;
                //密码错误
            }else if(!tbPmuser.getUserpwd().equals(password)){
                response.getWriter().println("<script>alert('密码错误！');window.location='/User/toLogin'</script>");
                return null;
                //登录成功
            }else{
                request.getSession(true).setAttribute("tbPmuser",tbPmuser);
                return "redirect:/PM/admin";
            }

        }catch (Exception e){
            logger.error("msg",e);
            return "redirect:/User/toLogin";
        }

    }
    
    /**
     * 后台：琴后台用户进入修改密码界面
     * @return
     * @author HKJ
     */
    @RequestMapping(value="/toUpdatePwd")
    public String toUpdatePwd(HttpServletRequest request){
    	if(!LoginHelper.validate(request)) {
    		return "redirect:/user/toLogin";
    	}
    	return "user/password";
    }
    
    /**
     * 后台：更新琴后台用户密码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="updatePwd")
    public String updatePwd(HttpServletRequest request,HttpServletResponse response) {
    	response.setContentType("text/html;charset=UTF-8;");
    	
    	try {
    		if(!LoginHelper.validate(request)) {
    			return "redirect:/user/toLogin";
    		}
    		
        	TbPmuser pmuser = (TbPmuser) request.getSession().getAttribute("tbPmuser");
        	
//        	String oldPwd = request.getParameter("oldpwd");
        	String newPwd = request.getParameter("newpwd");
        	String rePwd = request.getParameter("repwd");
        	
        	if(/*StringUtils.isEmpty(oldPwd) ||*/StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(rePwd)) {
        		response.getWriter().println("<script>alert('密码输入不能为空');history.go(-1);</script>");
        		return null;
        	}
        	if(!newPwd.equals(rePwd)) {
        		response.getWriter().println("<script>alert('两次输入密码不一致');history.go(-1);</script>");
        		return null;
        	}
        	if(pmuser.getUserpwd().equals(newPwd)) {
        		response.getWriter().println("<script>alert('与原密码相同');history.go(-1);</script>");
        		return null;
        	}
        	pmuser.setUserpwd(newPwd);
			tbPmUserService.updateByPrimaryKey(pmuser);
			response.getWriter().println("<script>alert('更新成功');window.location.href='/PM/admin';</script>");
			return null;
		} catch (Exception e) {
			logger.error("msg",e);
			return null;
		}
    }
    
    /**
     * 后台：琴后台用户退出登录
     * @param request
     * @return
     * @author HKJ
     */
    @RequestMapping(value="exit")
    public String exit(HttpServletRequest request) {
    	if(!LoginHelper.validate(request)) {
    		return "redirect:/user/toLogin";
    	}
    	request.getSession().setAttribute("tbPmuser", null);
    	return "redirect:/user/toLogin";
    }
    
    /**
     * 后台：进入琴后台用户资料编辑界面
     * @param request
     * @return
     */
    @RequestMapping(value="toEditSelf")
    public String toEditSelf(HttpServletRequest request,Model model) {
    	if(!LoginHelper.validate(request)) {
    		return "redirect:/user/toLogin";
    	}
    	
    	TbPmuser pmuser = (TbPmuser) request.getSession().getAttribute("tbPmuser");
    	model.addAttribute("tbPmuser", pmuser);
    	
    	return "user/editSelf";
    }
    
    /**
     * 后台：琴后台用户资料编辑
     * @param request
     * @return
     */
    @RequestMapping(value="editSelf")
    @ResponseBody
    public String editSelf(HttpServletRequest request) {
    	//判断是否登录
    	if(!LoginHelper.validate(request)) {
    		return "<script>alert('您还未登录');window.location='/user/toLogin';</script>";
    	}
    	
    	//获取用户原信息
    	TbPmuser pmuser = (TbPmuser) request.getSession().getAttribute("tbPmuser");
    	
    	//获取页面传入的参数
    	String userName = request.getParameter("username");
    	String nickName = request.getParameter("nickname");
    	String phoneNo = request.getParameter("phoneno");
    	String email = request.getParameter("email");
    	String sex = request.getParameter("sex");
    	
    	//更新用户信息
    	pmuser.setUsername(userName);
    	pmuser.setNickname(nickName);
    	pmuser.setPhoneno(phoneNo);
    	pmuser.setEmail(email);
    	pmuser.setSex(sex.equals("1")?true:false);
    	try {
			tbPmUserService.updateByPrimaryKey(pmuser);
			return "<script>alert('编辑成功');window.location='/pm/admin';</script>";
		} catch (Exception e) {
			logger.error("msg",e);
			return "<script>alert('编辑异常，请稍后重试！');history.go(-1);</script>";
		}
    }

}





