package com.klsw.wk.hades.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.klsw.wk.hades.domain.Role;
import com.klsw.wk.hades.domain.User;
import com.klsw.wk.hades.domain.UserInfo;
import com.klsw.wk.hades.exception.AjaxException;
import com.klsw.wk.hades.exception.ServiceException;
import com.klsw.wk.hades.service.IRoleService;
import com.klsw.wk.hades.service.IUserService;
import com.klsw.wk.hades.util.Constants;
import com.klsw.wk.hades.util.EndecryptUtils;
import com.klsw.wk.hades.util.Pager;
import com.klsw.wk.hades.util.PagerPropertyUtils;
import com.klsw.wk.hades.util.RandomUtil;

@Controller
@Scope("prototype")
@RequestMapping("/user/")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("listUI")
    public String listUI(Model model, HttpServletRequest request) {
        try {
            return "/authorize/user/index";
        } catch (Exception e) {
            throw new AjaxException(e);
        }
    }


    /**
     * ajax分页动态加载模式
     *
     * @param gridPager Pager对象
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody Object list(String dtGridPager) throws Exception {
        Pager<User> pager = new Pager<User>();
		pager.setIsSuccess(false);
		try {
			pager = PagerPropertyUtils.copy(JSONObject.fromObject(dtGridPager), User.class);
			Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
			List<User> list = userService.queryListByPage(pager.getParameters());
			pager.setExhibitDatas(list);
			pager.setRecordCount(page.getTotal());
			pager.setIsSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pager;
    }

    @RequestMapping("addUI")
    public String addUI(Model model) {
        try {
            List<Role> list = roleService.queryListByPage(new HashMap<String, Object>());
            model.addAttribute("roleList", list);
            return "/authorize/user/addform";
        } catch (Exception e) {
            throw new AjaxException(e);
        }

    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(User userEntity) throws AjaxException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String password = userEntity.getPassword();
            //获取登录用户
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            User sessionUser = (User) request.getSession().getAttribute("userSession");
            // 加密用户输入的密码，得到密码和加密盐，保存到数据库
            User user = EndecryptUtils.md5Password(userEntity.getAccountName(), userEntity.getPassword(), 2);
            //设置添加用户的密码和加密盐
            userEntity.setPassword(user.getPassword());
            userEntity.setCredentialsSalt(user.getCredentialsSalt());
            //设置创建者姓名
            userEntity.setCreatorName(sessionUser.getUserName());
            userEntity.setCreateTime(new Date());
            //设置锁定状态：未锁定；删除状态：未删除
            userEntity.setLocked(0);
            userEntity.setDeleteStatus(0);
            UserInfo userInfo = new UserInfo();
            userEntity.setUserInfo(userInfo);
            int result = userService.insert(userEntity, password);
            if (result == 1) {
                setResponseSuccessMessage(map,null,"添加成功");
            } else {
                setResponseFailedMessage(map,null,"添加失败");
            }
        } catch (ServiceException e) {
            throw new AjaxException(e);
        }
        return map;
    }


    @RequestMapping("editUI")
    public String editUI(Model model, HttpServletRequest request, Long id) {
        try {
            User userEntity = userService.findById(id);
            List<Role> list = roleService.queryListByPage(new HashMap<String, Object>());
            model.addAttribute("userEny", userEntity);
            model.addAttribute("roleList", list);
            return "/authorize/user/editform";
        } catch (Exception e) {
            throw new AjaxException(e);
        }
    }

    @RequestMapping("edit")
    @ResponseBody
    public Object update(User userEntity) throws AjaxException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //获取登录用户
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            User sessionUser = (User) request.getSession().getAttribute("userSession");
            //设置创建者姓名
            userEntity.setCreatorName(sessionUser.getCreatorName());
            int result = userService.update(userEntity);
            if (result == 1) {
                setResponseSuccessMessage(map,null,"编辑成功");
            } else {
                setResponseFailedMessage(map,null,"编辑失败");
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }


    @RequestMapping(value="deleteBatch",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteBatch(String ids) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String[] userIds = ids.split(",");
            List<Long> list = new ArrayList<Long>();
            for (String string : userIds) {
                list.add(Long.valueOf(string));
            }
            int cnt = userService.deleteBatchById(list);
            if (cnt == list.size()) {
                setResponseSuccessMessage(result,null,"删除成功");
            } else {
                setResponseFailedMessage(result,null,"删除失败");
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return result;
    }

    @RequestMapping("resetPassword")
    @ResponseBody
    public Object resetPassword(User userEntity) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            //生成随机密码
            String password = "admin";//RandomUtil.generateString(6);

            //加密用户输入的密码，得到密码和加密盐，保存到数据库
            User user = EndecryptUtils.md5Password(userEntity.getAccountName(), password, 2);
            //设置添加用户的密码和加密盐
            userEntity.setPassword(user.getPassword());
            userEntity.setCredentialsSalt(user.getCredentialsSalt());
            if (userEntity.getId() == null) {
                user = null;
                user = userService.findByName(userEntity.getAccountName());
                if (user != null) {
                    userEntity.setId(user.getId());
                    userEntity.setUserName(user.getUserName());
                    int cnt = userService.updateOnly(userEntity, password);
                    if (cnt > 0) {
                        setResponseSuccessMessage(result,null,"密码重置成功");
                    } else {
                        setResponseFailedMessage(result,null,"密码重置失败");
                    }
                } else {
                    setResponseFailedMessage(result,null,"账户不存在");
                }
            } else {
                int cnt = userService.updateOnly(userEntity, password);
                if (cnt > 0) {
                    setResponseSuccessMessage(result,null,"密码重置成功");
                } else {
                    setResponseFailedMessage(result,null,"密码重置失败");
                }
            }

        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return result;
    }

    @RequestMapping("resetPassWithoutAuthc")
    @ResponseBody
    public Object resetPassWithoutAuthc(User userEntity) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            //生成随机密码
            String password = RandomUtil.generateString(6);

            //加密用户输入的密码，得到密码和加密盐，保存到数据库
            User user = EndecryptUtils.md5Password(userEntity.getAccountName(), password, 2);
            //设置添加用户的密码和加密盐
            userEntity.setPassword(user.getPassword());
            userEntity.setCredentialsSalt(user.getCredentialsSalt());
            if (userEntity.getId() == null) {
                user = null;
                user = userService.findByName(userEntity.getAccountName());
                if (user != null) {
                    userEntity.setId(user.getId());
                    userEntity.setUserName(user.getUserName());
                    int cnt = userService.updateOnly(userEntity, password);
                    if (cnt > 0) {
                        setResponseSuccessMessage(result,null,"密码重置成功");
                    } else {
                        setResponseFailedMessage(result,null,"密码重置失败");
                    }
                } else {
                    setResponseFailedMessage(result,null,"账户不存在");
                }
            } else {
                int cnt = userService.updateOnly(userEntity, password);
                if (cnt > 0) {
                    setResponseSuccessMessage(result,null,"密码重置成功");
                } else {
                    setResponseFailedMessage(result,null,"密码重置失败");
                }
            }

        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return result;
    }


    @RequestMapping("infoUI")
    public String infoUI(Model model, Long id) {
        try {
            User userEntity = userService.findById(id);
            model.addAttribute("userEntity", userEntity);
            return Constants.BACKGROUND_PATH + "/authorize/user/info";
        } catch (Exception e) {
            throw new AjaxException(e);
        }
    }

    @RequestMapping("info")
    @ResponseBody
    public Object info(User userEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = userService.update(userEntity);
            if (result == 1) {
                setResponseSuccessMessage(map,null,"编辑成功");
            } else {
                setResponseFailedMessage(map,null,"编辑失败");
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }


    @RequestMapping("passwordUI")
    public String passwordUI(Model model, User userEntity) {
        try {
            model.addAttribute("userEntity", userEntity);
            return Constants.BACKGROUND_PATH + "/authorize/user/password";
        } catch (Exception e) {
            throw new AjaxException(e);
        }
    }


    @RequestMapping("password")
    @ResponseBody
    public Object password(User userEntity) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String password = userEntity.getPassword();
            userEntity.setUserName(new String(userEntity.getUserName().getBytes("iso-8859-1"), "utf-8"));
            //加密用户输入的密码，得到密码和加密盐，保存到数据库
            User user = EndecryptUtils.md5Password(userEntity.getAccountName(), userEntity.getPassword(), 2);
            //设置添加用户的密码和加密盐
            userEntity.setPassword(user.getPassword());
            userEntity.setCredentialsSalt(user.getCredentialsSalt());
            int cnt = userService.updateOnly(userEntity, password);
            if (cnt > 0) {
                setResponseSuccessMessage(result,null,"密码修改成功");
            } else {
                setResponseFailedMessage(result,null,"密码修改失败");
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return result;
    }

    @RequestMapping("validateAccountName")
    @ResponseBody
    public Object validateAccount(String accountName) {
        try {
            User userEntity = userService.findByName(accountName);
            if (userEntity == null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
    }
}
