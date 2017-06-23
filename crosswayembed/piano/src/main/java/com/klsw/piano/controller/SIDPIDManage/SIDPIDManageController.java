package com.klsw.piano.controller.SIDPIDManage;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.piano.service.SIDPIDManage.OperationService;
import com.klsw.piano.service.SIDPIDManage.TbAgencyService;
import com.klsw.piano.service.SIDPIDManage.TbAgencyUserService;
import com.klsw.piano.service.TbSerialnumbersService;
import com.klsw.piano.util.PasswdEncryption;
import com.klsw.pianoCommon.api.model.TbAgency;
import com.klsw.pianoCommon.api.model.TbAgencyOperation;
import com.klsw.pianoCommon.api.model.TbAgencyUser;
import com.klsw.pianoCommon.api.model.TbSerialnumbers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * SID、PID相关管理操作
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/agency")
public class SIDPIDManageController {
    private final Logger logger = LoggerFactory.getLogger(SIDPIDManageController.class);
    public static final int PAGESIZE = 1;

    @Resource
    private TbAgencyUserService userService;

    @Resource
    private TbAgencyService agencyService;

    @Resource
    private TbSerialnumbersService serialService;

    @Resource
    private OperationService operationService;

    /*********************************** 用户部分 **************************************/
    /**
     * 进入主页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "index")
    public String index(HttpServletRequest request) {
        // 验证是否登录
        TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
        if (tbAgencyUser == null) { // 未登录
            return "redirect:/agency/toLogin";
        }
        return "agency/index";
    }

    @RequestMapping(value = "")
    public String toIndex(HttpServletRequest request) {
        // 验证是否登录
        TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
        if (tbAgencyUser == null) { // 未登录
            return "redirect:/agency/toLogin";
        }
        return "agency/index";
    }

    /**
     * 进入登录页面
     *
     * @return
     */
    @RequestMapping(value = "toLogin")
    public String toLogin() {
        return "agency/login";
    }

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public String Login(HttpServletRequest request) {
        HttpSession session = request.getSession();

        // 获取页面传入的参数
        String userName = request.getParameter("username");// 用户名
        String userPwd = request.getParameter("userpwd");// 用户密码
        String passWord = PasswdEncryption.getPwd(userName, userPwd);// 密码加密

        TbAgencyUser tbAgencyUser = new TbAgencyUser();
        tbAgencyUser.setUsername(userName);
        tbAgencyUser.setUserpwd(passWord);
        try {
            tbAgencyUser = userService.selectOne(tbAgencyUser);
            if (tbAgencyUser != null) {
                // 登录后将个人信息存入session
                session.setAttribute("AgencyUser", tbAgencyUser);
                return "<script>window.location.href='/agency/index';</script>";
            }
            return "<script>alert('用户名或密码错误，请重试'); history.go(-1);</script>";
        } catch (Exception e) {
            logger.error("msg",e);
            return "<script>alert('发生异常，请稍后重试！'); history.go(-1);</script>";
        }
    }

    /**
     * 管理员：用户信息列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "userList")
    public String userList(HttpServletRequest request, HttpServletResponse response, Model model) {
        response.setContentType("text/html;charset=utf-8");
        Integer id = 1;
        String idString = request.getParameter("pageId");
        if (!StringUtils.isEmpty(idString)) {
            id = new Integer(idString);
        }

        try {
            // 验证登录
            TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
            if (tbAgencyUser == null) {
                response.getWriter()
                        .println("<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>");
                return null;
            }

            // 验证权限
            if (!tbAgencyUser.getAuthority().contains("用户管理")) {
                response.getWriter().println("<script>alert('您无此权限！');window.location.href='/agency/index';</script>");
                return null;
            }

            // 获取模糊搜索内容
            String searchStr = request.getParameter("searchStr");
            if (searchStr == null) {
                searchStr = "";
            }

            // 定义分页
            PageHelper.startPage(id, PAGESIZE);

            List<TbAgencyUser> agencyUsers = Lists.newArrayList();
            agencyUsers = userService.selectAll();
            PageInfo<TbAgencyUser> pageInfo = new PageInfo<TbAgencyUser>(agencyUsers);

            // 绑定信息到页面
            model.addAttribute("agencyUsers", pageInfo);
            model.addAttribute("searchStr", searchStr);
            return "agency/user_list";
        } catch (Exception e) {
            logger.error("msg",e);
            return "agency/index";
        }
    }

    /**
     * 管理员：进入添加用户界面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "toAddUser")
    public String toAddUser(HttpServletRequest request) {
        // 验证登录
        TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
        if (tbAgencyUser == null) {
            return "agency/login";
        }

        // 验证权限
        if (!tbAgencyUser.getAuthority().contains("用户管理")) {
            return "agency/index";
        }

        return "agency/user_add";
    }

    /**
     * 管理员：添加用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        // 验证登录
        TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
        if (tbAgencyUser == null) {
            return "<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>";
        }

        // 验证权限
        if (!tbAgencyUser.getAuthority().contains("用户管理")) {
            return "<script>alert('您无此权限！');window.location.href='/agency/index';</script>";
        }

        try {
            /* 获取页面传入的用户名并进行验证 */
            String userName = request.getParameter("username");
            if (StringUtils.isEmpty(userName) || userName.length() < 2 || userName.length() > 20) {
                return "<script>alert('用户名为2位到20位之间！'); history.go(-1);</script>";
            }
            TbAgencyUser tbAgencyUser2 = new TbAgencyUser();
            tbAgencyUser2.setUsername(userName);
            tbAgencyUser2 = userService.selectOne(tbAgencyUser2);
            if (tbAgencyUser2 != null) {
                return "<script>alert('用户名已存在，请重试！'); history.go(-1);</script>";
            }

            tbAgencyUser2 = new TbAgencyUser();
			/* 获取页面传入的用户密码并进行验证 */
            String userPwd = request.getParameter("userpwd");
            if (StringUtils.isEmpty(userPwd) || userPwd.length() < 6 || userPwd.length() > 16) {
                return "<script>alert('密码为6位到16位之间！');history.go(-1);</script>";
            }
            userPwd = PasswdEncryption.getPwd(userName, userPwd);

			/* 获取页面传入的用户权限 */
            String authority = request.getParameter("authority");
            if (authority == null) {
                authority = "";
            }

			/* 获取页面传入的用户昵称 */
            String nickName = request.getParameter("nickname");
            if (nickName == null) {
                nickName = userName;
            }

			/* 获取页面传入的用户评论 */
            String remark = request.getParameter("remark");
            if (remark == null) {
                remark = "";
            }

			/* 获取页面传入的用户添加时间 */
            String addtime = request.getParameter("addtime");
            if (addtime == null) {
                tbAgencyUser2.setAddtime(new Date());
            }

			/* 存入用户相关信息 */
            tbAgencyUser2.setUsername(userName);
            tbAgencyUser2.setRemark(remark);
            tbAgencyUser2.setAddtime(new SimpleDateFormat("yyyy-MM-dd").parse(addtime));
            tbAgencyUser2.setAuthority(authority);
            tbAgencyUser2.setNickname(nickName);
            tbAgencyUser2.setUserpwd(userPwd);

            // 将用户信息插入到数据库
            userService.insert(tbAgencyUser2);
            return "<script>alert('添加成功！';window.location.href='/agency/user_list'</script>";
        } catch (Exception ex) {
            return "<script>alert('添加失败，请重试！'); history.go(-1);</script>";
        }
    }

    /**
     * 管理员：进入用户信息修改界面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "toMdyUser")
    public String toMdyUser(HttpServletRequest request, HttpServletResponse response, Model model) {
        response.setContentType("text/html;charset=utf-8");
        try {
            // 验证登录
            TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
            if (tbAgencyUser == null) {
                response.getWriter()
                        .println("<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>");
                return null;
            }

            // 验证权限
            if (!tbAgencyUser.getAuthority().contains("用户管理")) {
                response.getWriter().println("<script>alert('您无此权限！');window.location.href='/agency/index';</script>");
                return null;
            }

			/* 获取页面传入的用户id */
            String idString = request.getParameter("ids");
            Integer id = -1;
            if (!StringUtils.isEmpty(idString)) {
                id = new Integer(idString);
            }

            // 判断用户是否存在
            TbAgencyUser tAgencyUser = new TbAgencyUser();
            tAgencyUser.setId(id);
            tAgencyUser = userService.selectOne(tAgencyUser);

            if (tAgencyUser == null) { // 用户不存在
                response.getWriter()
                        .println("<script>alert('发生错误，用户不存在！');window.locatin.href='/agency/userList';</script>");
                return null;
            }
            model.addAttribute("tAgencyUser", tAgencyUser);
            return "agency/user_modify";
        } catch (Exception e) {
            logger.error("msg",e);
            return null;
        }
    }

    /**
     * 管理员：修改用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "mdyUser")
    @ResponseBody
    public String mdyUser(HttpServletRequest request, Model model, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        // 验证登录
        TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
        if (tbAgencyUser == null) {
            return "<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>";
        }

        // 验证权限
        if (!tbAgencyUser.getAuthority().contains("用户管理")) {
            return "<script>alert('您无此权限！');window.location.href='/agency/index';</script>";
        }

        // 获取页面传入的用户id
        Integer id = -1;
        String idString = request.getParameter("ids");
        if (!StringUtils.isEmpty(idString)) {
            id = new Integer(idString);
        }

        try {
            // 查询用户是否存在
            TbAgencyUser tbAgencyUser2 = new TbAgencyUser();
            tbAgencyUser2.setId(id);
            tbAgencyUser2 = userService.selectOne(tbAgencyUser2);
            if (tbAgencyUser2 == null) { // 用户不存在
                return "<script>alert('修改失败，用户不存在！'); history.go(-1);</script>";
            }
			
			/* 获取页面传入的用户名并进行验证 */
            String userName = request.getParameter("username");
            if (StringUtils.isEmpty(userName) || userName.length() < 2 || userName.length() > 20) {
                return "<script>alert('用户名为2位到20位之间！'); history.go(-1);</script>";
            }
            if (!userName.equals(tbAgencyUser2.getUsername())) {
                TbAgencyUser agencyUser = new TbAgencyUser();
                agencyUser.setUsername(userName);
                agencyUser = userService.selectOne(agencyUser);
                if (agencyUser != null) {
                    return "<script>alert('用户名已存在，请重试！'); history.go(-1);</script>";
                }
                tbAgencyUser2.setUsername(userName);
            }

			/* 获取页面传入的用户密码并进行验证 */
            String userPwd = request.getParameter("userpwd");
            if (!StringUtils.isEmpty(userPwd) && (userPwd.length() < 6 || userPwd.length() > 16)) {
                return "<script>alert('密码为6位到16位之间！');history.go(-1);</script>";
            }
            tbAgencyUser2.setUserpwd(PasswdEncryption.getPwd(tbAgencyUser2.getUsername(), userPwd));
			
			/* 获取页面传入的用户权限 */
            String authority = request.getParameter("authority");
            if (authority == null) {
                authority = "";
            }

			/* 获取页面传入的用户昵称 */
            String nickName = request.getParameter("nickname");
            if (nickName == null) {
                nickName = userName;
            }

			/* 获取页面传入的用户评论 */
            String remark = request.getParameter("remark");
            if (remark == null) {
                remark = "";
            }

			/* 获取页面传入的用户添加时间 */
            String addtime = request.getParameter("addtime");
            if (addtime == null) {
                tbAgencyUser2.setAddtime(new Date());
            }

			/* 存入用户信息 */
            tbAgencyUser2.setRemark(remark);
            tbAgencyUser2.setAddtime(new SimpleDateFormat("yyyy-MM-dd").parse(addtime));
            tbAgencyUser2.setAuthority(authority);
            tbAgencyUser2.setNickname(nickName);

            // 更新用户信息
            userService.updateByPrimaryKey(tbAgencyUser2);

            return "<script>alert('修改成功！');window.location.href='/agency/userList';</script>";
        } catch (Exception e) {
            logger.error("msg",e);
            return "<script>alert('修改异常，稍后重试！');history.go(-1);</script>";
        }
    }

    /**
     * 个人：进入信息修改界面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "toMdySelf")
    public String toMdySelf(HttpServletRequest request, Model model, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        // 验证是否登录
        TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
        if (tbAgencyUser == null) {
            return "agency/login";
        }

        try {
            // 判断用户是否存在
            TbAgencyUser tbAgencyUser2 = new TbAgencyUser();
            tbAgencyUser2.setUsername(tbAgencyUser.getUsername());
            tbAgencyUser2 = userService.selectOne(tbAgencyUser2);
            if (tbAgencyUser2 == null) {
                return "<script>alert('发生错误，用户名不存在！'); history.go(-1);</script>";
            }

            // 绑定信息到页面
            model.addAttribute("tbAgencyUser2", tbAgencyUser2);
            return "agency/user_self";
        } catch (Exception e) {
            logger.error("msg",e);
            return null;
        }
    }

    /**
     * 个人：修改信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "mdySelf")
    @ResponseBody
    public String mdySelf(HttpServletRequest request, Model model, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        // 验证登录
        TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
        if (tbAgencyUser == null) {
            return "<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>";
        }

        try {
			/* 获取页面传入的用户id */
			/*
			 * Integer id = -1; String idString = request.getParameter("id"); if
			 * (StringUtils.isEmpty(idString)) { id = new Integer(idString); }
			 */

            // 判断用户是否存在
            TbAgencyUser agencyUser = new TbAgencyUser();
            agencyUser.setId(tbAgencyUser.getId());
            agencyUser = userService.selectOne(agencyUser);
            if (agencyUser == null) {
                return "<script>alert('修改失败，用户不存在！'); history.go(-1);</script>";
            }

			/* 获取页面传入的用户名并进行验证 */
            String userName = request.getParameter("username");
            if (StringUtils.isEmpty(userName) || userName.length() < 2 || userName.length() > 20) {
                return "<script>alert('用户名为2位到20位之间！'); history.go(-1);</script>";
            }
            if (!userName.equals(tbAgencyUser.getUsername())) {
                TbAgencyUser tbAgencyUser2 = new TbAgencyUser();
                tbAgencyUser2.setUsername(userName);
                tbAgencyUser2 = userService.selectOne(tbAgencyUser2);
                if (tbAgencyUser2 != null) {
                    return "<script>alert('用户名已存在，请重试！'); history.go(-1);</script>";
                }
                tbAgencyUser.setUsername(userName);
            }

			/* 获取页面传入的用户权限 */
            String authority = request.getParameter("authority");
            if (authority == null) {
                authority = "";
            }

			/* 获取页面传入的用户昵称 */
            String nickName = request.getParameter("nickname");
            if (nickName == null) {
                nickName = userName;
            }

			/* 获取页面传入的用户评论 */
            String remark = request.getParameter("remark");
            if (remark == null) {
                remark = "";
            }

			/* 获取页面传入的用户密码并进行验证 */
            String userPwd = request.getParameter("userpwd");
            if (!StringUtils.isEmpty(userPwd)) {
                // 验证新密码是否符合要求
                if (StringUtils.isEmpty(userPwd) || userPwd.length() < 6 || userPwd.length() > 16) {
                    return "<script>alert('密码为6位到16位之间！');history.go(-1);</script>";
                }

                // 验证两次输入的密码是否一致
                String userpwd2 = request.getParameter("userpwd2");
                if (!userPwd.equals(userpwd2)) {
                    return "<script>alert('两次输入的密码不一致！'); history.go(-1);</script>";
                }
                tbAgencyUser.setUserpwd(PasswdEncryption.getPwd(userName, userPwd));
            }

            // 存入用户信息
            tbAgencyUser.setRemark(remark);
            tbAgencyUser.setAuthority(authority);
            tbAgencyUser.setNickname(nickName);

            // 更新用户个人信息
            userService.updateByPrimaryKey(tbAgencyUser);
            return "<script>alert('修改成功！'); window.location.href='/agency/index'</script>";
        } catch (Exception ex) {
            return "<script>alert('修改失败，请重试！'); history.go(-1);</script>";
        }
    }

    /**
     * 管理员：删除用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "delUser")
    @ResponseBody
    public String delUser(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        // 验证登录
        TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
        if (tbAgencyUser == null) {
            return "<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>";
        }

        // 验证权限
        if (!tbAgencyUser.getAuthority().contains("用户管理")) {
            return "<script>alert('您无此权限！');window.location.href='/agency/index';</script>";
        }

		/* 获取页面传入的用户id */
        Integer id = 1;
        String idString = request.getParameter("id");
        if (!StringUtils.isEmpty(idString)) {
            id = new Integer(idString);
        }

        try {
            // 判断用户是否存在
            TbAgencyUser agencyUser = new TbAgencyUser();
            agencyUser.setId(id);
            agencyUser = userService.selectOne(agencyUser);
            if (agencyUser != null) {
                userService.deleteByPrimaryKey(agencyUser);
                return "<script>alert('删除成功！');window.location.href='/agency/userList';</script>";
            } else {
                return "<script>alert('删除失败，用户不存在！'); history.go(-1);</script>";
            }
        } catch (Exception e) {
            return "<script>alert('删除失败，请重试！'); history.go(-1);</script>";
        }
    }

    /**
     * 个人：退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "Logout")
    public String Logout(HttpServletRequest request) {
        // 清空session
        request.getSession().setAttribute("AgencyUser", null);
        return "redirect:/agency/toLogin";
    }

    /*********************************** SN管理部分 **************************************/

    /**
     * SN编号对应关系列表
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "SNList")
    public String SNList(HttpServletRequest request, HttpServletResponse response, Model model) {
        response.setContentType("text/html;charset=utf-8");
        try {
            // 获取页面传入的展示页数
            Integer id = 1;
            String idString = request.getParameter("pageId");
            if (!StringUtils.isEmpty(idString)) {
                id = new Integer(idString);
            }

            // 验证登录
            TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
            if (tbAgencyUser == null) {
                response.getWriter()
                        .println("<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>");
                return null;
            }

            // 验证权限
            if (!tbAgencyUser.getAuthority().contains("序列号管理")) {
                response.getWriter().println("<script>alert('您无此权限！');window.location.href='/agency/index';</script>");
                return null;
            }

            // 获取模糊搜索内容
            String searchStr = request.getParameter("searchstr");
            if (searchStr == null) {
                searchStr = "";
            }

            // 定义分页查询
            PageHelper.startPage(id, PAGESIZE);
            List<TbAgency> agencyList = Lists.newArrayList();
            agencyList = agencyService.selectByLikeName(searchStr);
            PageInfo<TbAgency> pageInfo = new PageInfo<TbAgency>(agencyList);

            // 绑定信息到页面
            model.addAttribute("searchStr", searchStr);
            model.addAttribute("agencyList", pageInfo);
            return "agency/SN_list";
        } catch (Exception e) {
            logger.error("msg",e);
            return null;
        }
    }

    /**
     * 进入添加SN界面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "toAddSN")
    public String toAddSN(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            // 验证登录
            TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
            if (tbAgencyUser == null) {
                response.getWriter()
                        .println("<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>");
                return null;
            }

            // 验证权限
            if (!tbAgencyUser.getAuthority().contains("序列号管理")) {
                response.getWriter().println("<script>alert('您无此权限！');window.location.href='/agency/index';</script>");
                return null;
            }

            return "agency/SN_add";
        } catch (Exception e) {
            logger.error("msg",e);
            return null;
        }
    }

    /**
     * 添加SN
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "addSN")
    @ResponseBody
    public String addSN(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        // 验证登录
        TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
        if (tbAgencyUser == null) {
            return "<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>";
        }

        // 验证权限
        if (!tbAgencyUser.getAuthority().contains("序列号管理")) {
            return "<script>alert('您无此权限！');window.location.href='/agency/index';</script>";
        }

        try {
			/* 验证产品编号 */
            TbAgency tbAgency = new TbAgency();
            String pid = request.getParameter("pid");
            if (!StringUtils.isEmpty(pid) && pid.length() > 18) {
                return "<script>alert('产品编号最多18位，请重试！'); history.go(-1);</script>";
            }
            tbAgency.setPid(pid);
            if (agencyService.selectOne(tbAgency) != null) {
                return "<script>alert('产品编号已存在，请重试！'); history.go(-1);</script>";
            }

			/* 验证产品序列号 */
            String sn = request.getParameter("sn");
            if (StringUtils.isEmpty(sn) || sn.length() > 16 || sn.length() < 12) {
                return "<script>alert('产品序列号为12-16位，请重试！'); history.go(-1);</script>";
            }
            TbSerialnumbers tbSerialnumbers = new TbSerialnumbers();
            tbSerialnumbers.setNumber(sn);
            if (serialService.selectOne(tbSerialnumbers) == null) {
                return "<script>alert('产品序列号不存在，请重试！'); history.go(-1);</script>";
            }
            tbAgency = new TbAgency();
            tbAgency.setSn(sn);
            if (!agencyService.select(tbAgency).isEmpty()) {
                return "<script>alert('产品序列号已录入，请重试！'); history.go(-1);</script>";
            }

			/* 验证渠道号 */
            String sid = request.getParameter("sid");
            if (!StringUtils.isEmpty(sid) && sid.length() > 6) {
                return "<script>alert('渠道号最多6位，请重试！'); history.go(-1);</script>";
            }

            // 获取时间0
            String addtime = request.getParameter("addtime");
            Date date = new Date();
            if (!StringUtils.isEmpty(addtime)) {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(addtime);
            }

            // 获取注释
            String remark = request.getParameter("remark");
            if (StringUtils.isEmpty(remark)) {
                remark = "";
            }

            // 存入代理商信息
            tbAgency = new TbAgency();
            tbAgency.setAddtime(date);
            tbAgency.setPid(pid);
            tbAgency.setSid(sid);
            tbAgency.setSn(sn);
            tbAgency.setRemark(remark);
            agencyService.insert(tbAgency);

            // 存入操作信息
            tbAgency = agencyService.selectOne(tbAgency);
            Integer userid = new Integer(tbAgencyUser.getId());
            if (tbAgency != null) {
                TbAgencyOperation operation = new TbAgencyOperation();
                operation.setOperationtype("add");
                operation.setAgencyid(tbAgency.getId());
                operation.setUserid(userid);
                operation.setOperationtime(date);
                operation.setPid(pid);
                operation.setSid(sid);
                operation.setSn(sn);
                operation.setRemark(remark);
                operationService.insert(operation);
            }
        } catch (Exception ex) {
            return "<script>alert('添加失败，请重试！'); history.go(-1);</script>";
        }
        return "<script>alert('添加成功！'); window.location='/agency/SNList'</script>";
    }

    /**
     * 进入修改SN界面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "toMdySN")
    public String toMdySN(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {

            // 验证登录
            TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
            if (tbAgencyUser == null) {
                response.getWriter()
                        .println("<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>");
                return null;
            }

            // 验证权限
            if (!tbAgencyUser.getAuthority().contains("序列号管理")) {
                response.getWriter().println("<script>alert('您无此权限！');window.location.href='/agency/index';</script>");
                return null;
            }

            // 获取需要修改的代理商id并验证是否存在
            String idStr = request.getParameter("id");
            model.addAttribute("id", idStr);
            Integer id = -1;
            if (!StringUtils.isEmpty(idStr)) {
                id = new Integer(idStr);
            }
            TbAgency agency = new TbAgency();
            agency.setId(id);
            agency = agencyService.selectOne(agency);
            if (agency == null) {
                response.getWriter().println("<script>alert('发生错误，序列号对应关系不存在！'); history.go(-1);</script>");
                return null;
            }

            // 绑定信息到页面
            model.addAttribute("agency", agency);
            return "agency/SN_modify";
        } catch (Exception e) {
            logger.error("msg",e);
            return null;
        }
    }

    /**
     * 修改SN
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "mdySN")
    @ResponseBody
    public String SNMdy(HttpServletRequest request) {

        // 验证登录
        TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
        if (tbAgencyUser == null) {
            return "<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>";
        }

        // 验证权限
        if (!tbAgencyUser.getAuthority().contains("序列号管理")) {
            return "<script>alert('您无此权限！');window.location.href='/agency/index';</script>";
        }

        try {
            // 获取需要修改的代理商id并验证是否存在
            Integer id = -1;
            String idString = request.getParameter("id");
            if (!StringUtils.isEmpty(idString)) {
                id = new Integer(idString);
            }
            TbAgency agency = new TbAgency();
            agency.setId(id);
            if (agencyService.selectOne(agency) == null) {
                return "<script>alert('修改失败，用户不存在！'); history.go(-1);</script>";
            }

            // 获取产品编号并验证
            String pid = request.getParameter("pid");
            if (!StringUtils.isEmpty(pid) && pid.length() > 18) {
                return "<script>alert('产品编号最多18位，请重试！'); history.go(-1);</script>";
            }
			/*agency = new TbAgency();
			agency.setPid(pid);
			if (agencyService.selectOne(agency) != null) {
				return "<script>alert('产品编号已存在，请重试！'); history.go(-1);</script>";
			}*/

            // 获取产品序列号并验证
            String sn = request.getParameter("sn");
            if (StringUtils.isEmpty(sn) || sn.length() > 16 || sn.length() < 12) {
                return "<script>alert('产品序列号为12-16位，请重试！'); history.go(-1);</script>";
            }
            TbSerialnumbers tbSerialnumbers = new TbSerialnumbers();
            tbSerialnumbers.setNumber(sn);
            if (serialService.selectOne(tbSerialnumbers) == null) {
                return "<script>alert('产品序列号不存在，请重试！'); history.go(-1);</script>";
            }
			/*agency = new TbAgency();
			agency.setSn(sn);
			if (!agencyService.select(agency).isEmpty()) {
				return "<script>alert('产品序列号已录入，请重试！'); history.go(-1);</script>";
			}*/

            // 获取渠道号并验证
            String sid = request.getParameter("sid");
            if (!StringUtils.isEmpty(sid) && sid.length() > 6) {
                return "<script>alert('渠道号最多6位，请重试！'); history.go(-1);</script>";
            }

            // 获取时间
            String addtime = request.getParameter("addtime");
            Date date = new Date();
            if (!StringUtils.isEmpty(addtime)) {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(addtime);
            }

            // 获取注释
            String remark = request.getParameter("remark");
            if (StringUtils.isEmpty(remark)) {
                remark = "";
            }

            // 存入信息
            agency = new TbAgency();
            agency.setId(id);
            agency.setAddtime(date);
            agency.setPid(pid);
            agency.setSid(sid);
            agency.setSn(sn);
            agency.setRemark(remark);

            // 进行更新
            agencyService.updateByPrimaryKey(agency);

            // 存入操作信息
            Integer userid = new Integer(tbAgencyUser.getId());
            if (agency != null) {
                TbAgencyOperation operation = new TbAgencyOperation();
                operation.setOperationtype("mdy");
                operation.setAgencyid(id);
                operation.setUserid(userid);
                operation.setOperationtime(new Date());
                operation.setPid(pid);
                operation.setSid(sid);
                operation.setSn(sn);
                operation.setRemark(remark);
                operationService.insert(operation);
            }
            return "<script>alert('修改成功！'); window.location='/agency/SNList'</script>";
        } catch (Exception ex) {
            return "<script>alert('修改失败，请重试！'); history.go(-1);</script>";
        }
    }

    @RequestMapping(value = "delSN")
    @ResponseBody
    public String delSN(HttpServletRequest request) {

        // 验证登录
        TbAgencyUser tbAgencyUser = (TbAgencyUser) request.getSession().getAttribute("AgencyUser");
        if (tbAgencyUser == null) {
            return "<script>alert('您还未登录！');window.location.href='/agency/toLogin';</script>";
        }

        // 验证权限
        if (!tbAgencyUser.getAuthority().contains("序列号管理")) {
            return "<script>alert('您无此权限！');window.location.href='/agency/index';</script>";
        }

        try {
            // 获取需要删除的代理商id并验证是否存在
            Integer id = -1;
            String idString = request.getParameter("id");
            if (!StringUtils.isEmpty(idString)) {
                id = new Integer(idString);
            }
            TbAgency agency = new TbAgency();
            agency.setId(id);
            agency = agencyService.selectOne(agency);

            // 存入操作信息
            if (agency != null) {
                Integer userId = tbAgencyUser.getId();
                TbAgencyOperation operation = new TbAgencyOperation();
                operation.setAgencyid(agency.getId());
                operation.setOperationtime(new Date());
                operation.setOperationtype("del");
                operation.setUserid(userId);
                operation.setPid(agency.getPid());
                operation.setSid(agency.getSid());
                operation.setSn(agency.getSn());
                operation.setRemark(agency.getRemark());
                agencyService.delete(agency);
                return "<script>alert('删除成功！');window.location.href='/agency/SNList';</script>";
            } else {
                return "<script>alert('删除失败，序列号对应关系不存在！'); history.go(-1);</script>";
            }
        } catch (Exception e) {
            logger.error("msg",e);
            return "<script>alert('删除失败，请重试！'); history.go(-1);</script>";
        }
    }

    /*********************************** 验证部分 **************************************/

    /**
     * 验证产品序列号是否合法
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "validateSN")
    @ResponseBody
    public String ajaxValidatorSN(HttpServletRequest request) {
        try {
            String sn = request.getParameter("sn");
            if (StringUtils.isEmpty(sn) || sn.length() > 16 || sn.length() < 12) {
                return "产品序列号为12-16位，请重试！";
            }

            TbSerialnumbers tbSerialnumbers = new TbSerialnumbers();
            tbSerialnumbers.setNumber(sn);
            if (serialService.select(tbSerialnumbers).isEmpty()) {
                return "产品序列号不存在，请重试！";
            }

            TbAgency agency = new TbAgency();
            agency.setSn(sn);
            if (agencyService.selectOne(agency) != null) {
                return "产品序列号已录入，请重试！";
            }

            return " ";
        } catch (Exception e) {
            logger.error("msg",e);
            return e.getMessage();
        }
    }

    /**
     * 验证产品编号是否合法
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "validatePid")
    @ResponseBody
    public String ajaxValidatorPid(HttpServletRequest request) {
        try {
            String pid = request.getParameter("pid");
            if (!StringUtils.isEmpty(pid) && pid.length() > 18) {
                return "产品编号最多18位，请重试！";
            }
            TbAgency agency = new TbAgency();
            agency.setPid(pid);
            if (!agencyService.select(agency).isEmpty()) {
                return "产品编号已存在，请重试！";
            }

            return " ";
        } catch (Exception e) {
            logger.error("msg",e);
            return e.getMessage();
        }
    }

    /**
     * 验证渠道号是否合法
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "validateSid")
    @ResponseBody
    public String ajaxValidatorSid(HttpServletRequest request) {
        String sid = request.getParameter("sid");
        if (!StringUtils.isEmpty(sid) && sid.length() > 6) {
            return "渠道号最多6位，请重试！";
        }
        return " ";
    }

    @RequestMapping(value = "serialNum")
    public String serialNum() {

        return "agency/serial_number";
    }

}
