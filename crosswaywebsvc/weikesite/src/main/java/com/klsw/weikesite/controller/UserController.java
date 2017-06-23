package com.klsw.weikesite.controller;

import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.weikesite.utils.*;

import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private DomainConfig domainConfig;
    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/")
    public String toLogin() {

        return "weike_index";
    }
    @RequestMapping("/weike_index3")
    public String weike_index3() {

        return "weike_index3";
    }
    
    @RequestMapping(value="toLogin")
    public String enterLogin() {
    	return "login";
    }

    @RequestMapping("/register")
    public String toRegister() {

        return "register";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes model,
                        HttpServletRequest request,
                        HttpServletResponse res) {

        String url = domainConfig.getApiDomain() + "/getToken";
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
        }
        NameValuePair nvp1 = new NameValuePair("username", username);
        NameValuePair[] nvps = new NameValuePair[]{nvp1};
        try {
            String response = TestUtils.getPostRequest(nvps, url, null);
            Ret ret = JsonUtils.decode(response, Ret.class);
            if (!"S".equals(ret.getStatus())) {
                model.addFlashAttribute("msg", ret.getMessage());
                return "redirect:/";
            }
            String token = String.valueOf(ret.getdata());
            String timestamp = String.valueOf(System.currentTimeMillis());
            NameValuePair nvp2 = new NameValuePair("password", PasswdEncryption.createPassword(token, password, timestamp, username));
            NameValuePair nvp3 = new NameValuePair("timestamp", timestamp);
            nvps = new NameValuePair[]{nvp1, nvp2, nvp3};
            url = domainConfig.getApiDomain() + "/login";
            response = TestUtils.getPostRequest(nvps, url, null);
            ret = JsonUtils.decode(response, Ret.class);
            if (!"S".equals(ret.getStatus())) {
                model.addFlashAttribute("msg", ret.getMessage());
                return "redirect:/";
            }
            JSONObject object = new JSONObject(response);
            JSONObject userInfo = object.getJSONObject("data");
            TbCwk user = JsonUtils.decode(userInfo.toString(), TbCwk.class);
            request.getSession(true).setAttribute("user", user);
            if ("stu".equals(user.getType())) {
                return "redirect:/homework/student?pageNum=1&status=10";
            } else if ("tea".equals(user.getType())) {
                return "redirect:/homework/teacher?pageNum=1&status=10";
            } else {
                return "redirect:/";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            model.addFlashAttribute("msg", "登陆失败");
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/doRegister")
    public String doRegister(HttpServletRequest request, RedirectAttributes model,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("type") String type,
                             @RequestParam("phoneCaptcha") String phoneCaptcha) {
        String url = domainConfig.getApiDomain() + "/regist";
        NameValuePair username1 = new NameValuePair("username", username);
        NameValuePair type1 = new NameValuePair("type", type);
        NameValuePair password1 = new NameValuePair("password", password);
        NameValuePair phoneCaptcha1 = new NameValuePair("phoneCaptcha", PasswdEncryption.storeCaptcha(username, phoneCaptcha));
        NameValuePair[] nvps = new NameValuePair[]{username1, type1, password1, phoneCaptcha1};
        String response = "";
        try {
            response = TestUtils.getPostRequest(nvps, url, null);
            Ret ret = JsonUtils.decode(response, Ret.class);
            if ("S".equals(ret.getStatus())) {
                model.addFlashAttribute("msg", "注册成功");
                model.addFlashAttribute("username", username);
                model.addFlashAttribute("password", password);
                return "redirect:/";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        model.addFlashAttribute("msg", "注册失败");
        return "redirect:/register";
    }

    /****
     * 注册验证码获取
     * @param request
     * @return
     */
    @RequestMapping(value = "/getPhoneCaptcha")
    @ResponseBody
    public String getPhoneCaptcha(HttpServletRequest request) {
        String username = request.getParameter("username");
        String url = domainConfig.getApiDomain() + "/getPhoneCaptcha";
        NameValuePair username1 = new NameValuePair("username", username);
        NameValuePair[] nvps = new NameValuePair[]{username1};

        String response = "";
        try {
            response = TestUtils.getPostRequest(nvps, url, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
        return response;
    }
    
    
    
	/****
	 * 忘记密码验证码获取
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/getPassword")
    @ResponseBody
    public String getPassword(HttpServletRequest request) {
        String phoneNum = request.getParameter("phoneNum");
        String url = domainConfig.getApiDomain() + "/getCaptcha";
        NameValuePair username1 = new NameValuePair("phoneNum", phoneNum);
        NameValuePair[] nvps = new NameValuePair[]{username1};

        String response = "";
        try {
            response = TestUtils.getPostRequest(nvps, url, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
        return response;
    }
    
    /**
     * 用于绑定手机获取验证码
     * @param request
     * @return
     */
    @RequestMapping(value="/bindPhoneCaptcha")
    @ResponseBody
    public Ret bindPhone(HttpServletRequest request) {
    	String phoneNum = request.getParameter("phoneNum");
    	String url = domainConfig.getApiDomain() + "/receiveCaptcha";
    	NameValuePair nvp1 = new NameValuePair("phoneNum", phoneNum);
    	List<NameValuePair> list = Lists.newArrayList(nvp1);
		try {
			String response = TestUtils.loggedInRequest(request, list, url, null);
			Ret ret = JsonUtils.decode(response, Ret.class);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return Ret.error("发生异常");
		}
    	
    }

//    /**
//     * @param @param  homeworkId
//     * @param @param  pageNum
//     * @param @param  request
//     * @param @param  model
//     * @param @return
//     * @return String
//     * @throws
//     * @Description: 教师列表查询
//     * @author LiuKun
//     * @date 2016年9月18日
//     */
//    @RequestMapping("/teacherList")
//    public String teacherList(@RequestParam("homeworkId") Integer homeworkId,
//                              @RequestParam("pageNum") Integer pageNum,
//                              HttpServletRequest request,
//                              Model model) {
//        String url = domainConfig.getApiDomain() + "/teacherList";
//        NameValuePair nvp1 = new NameValuePair("pageNum", "1");
//        List<NameValuePair> list = Lists.newArrayList(nvp1);
//        try {
//
//            String response = TestUtils.loggedInRequest(request, list, url, null);
//            Ret ret = JsonUtils.decode(response, Ret.class);
//            log.info(response);
//            if ("S".equals(ret.getStatus())) {
//                model.addAttribute("pageInfo", ret.getdata());
//            }
//            return "teacher_list";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }

    @RequestMapping(value = "/orgInfo", method = RequestMethod.POST)
    @ResponseBody
    public Ret orgInfo(@RequestParam("orgId") String orgId,
                       HttpServletRequest request) {
        NameValuePair nvp1 = new NameValuePair("orgId", orgId);
        String url = domainConfig.getApiDomain() + "/orgInfo";
        List<NameValuePair> list = Lists.newArrayList(nvp1);
        try {
            String response = TestUtils.loggedInRequest(request, list, url, null);
            Ret ret = JsonUtils.decode(response, Ret.class);
            if ("S".equals(ret.getStatus())) {
                JSONObject object = new JSONObject(response);
                JSONObject orgInfo = object.getJSONObject("data");
                TbCwk org = JsonUtils.decode(orgInfo.toString(), TbCwk.class);
                return Ret.success(org.getDetails());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Ret.error("获取机构信息失败");
        }

        return Ret.error("获取机构信息失败");

    }



	/****
	 * 进入验证身份
	 * @return
	 */
	@RequestMapping(value="/toVerify")
	public String verify() {
		return "password";
	}
	
	
	/***
	 * 验证身份
	 */
	@RequestMapping(value="/verifyIdentity")
	@ResponseBody
	public Ret verifyIdentity(HttpServletRequest request,Model model) {
		String phoneNum = request.getParameter("phoneNum");
		String url = domainConfig.getApiDomain()+"/verifyIdentity";
		NameValuePair nvp1 = new NameValuePair("phoneNum",phoneNum);
		NameValuePair[] nvps = new NameValuePair[]{nvp1};
		String response = "";
		try {
			response = TestUtils.getPostRequest(nvps, url, null);
			log.info(response);
			Ret ret = JsonUtils.decode(response, Ret.class);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(response);
			return Ret.error("发生异常");
		}
	}
	
	/****
	 * 进入修改密码界面
	 * @param phoneNum
	 * @param phoneCaptcha
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toModifyPwd")
	public String toModifyPwd(@RequestParam("phoneNum")String phoneNum,
							  @RequestParam("phoneCaptcha")String phoneCaptcha,Model model) {
		String url = domainConfig.getApiDomain()+"/security";
		NameValuePair nvp1 = new NameValuePair("phoneNum",phoneNum);
		NameValuePair nvp2 = new NameValuePair("phoneCaptcha",PasswdEncryption.storeCaptcha(phoneNum, phoneCaptcha));
		NameValuePair[] nvps = new NameValuePair[]{nvp1,nvp2};
		String response = "";
		String msg = "";
		try {
			response = TestUtils.getPostRequest(nvps, url, null);
			log.info(response);
			Ret ret = JsonUtils.decode(response, Ret.class);
			if("S".equals(ret.getStatus())) {
				model.addAttribute("phoneNum", phoneNum);
				model.addAttribute("phoneCaptcha", phoneCaptcha);
				msg = "请您尽快在下面输入框内完成密码更改，以免超时！";
				model.addAttribute("msg", msg);
				return "password";
			} else {
				msg = "您输入的验证码或手机号有误，请重新输入！";
				model.addAttribute("msg", msg);
				return "password";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			msg = "请求异常，请稍后重试！";
			model.addAttribute("msg", msg);
			return "password";
		}
	}
	
	/****
	 * 修改密码
	 * @param phoneNum
	 * @param phoneCaptcha
	 * @param password
	 * @param rePassword
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modifyPwd")
	public String modifyPwd(@RequestParam("phoneNum")String phoneNum,
							@RequestParam("phoneCaptcha")String phoneCaptcha,
							@RequestParam("password")String password,
							@RequestParam("rePassword")String rePassword,Model model) {
		String url = domainConfig.getApiDomain()+"/resetPwd";
		if(StringUtils.isEmpty(phoneCaptcha)) {
			model.addAttribute("msg", "验证码不能为空");
			return "password";
		}
		NameValuePair nvp1 = new NameValuePair("phoneNum",phoneNum);
		NameValuePair nvp2 = new NameValuePair("phoneCaptcha",PasswdEncryption.storeCaptcha(phoneNum, phoneCaptcha));
		NameValuePair nvp3 = new NameValuePair("password",password);
		NameValuePair nvp4 = new NameValuePair("rePassword",rePassword);
		NameValuePair[] nvps = new NameValuePair[]{nvp1,nvp2,nvp3,nvp4};
		String response = "";
		try {
			response = TestUtils.getPostRequest(nvps, url, null);
			log.info(response);
			Ret ret = JsonUtils.decode(response, Ret.class);
			model.addAttribute("msg", ret.getMessage());
			if("S".equals(ret.getStatus())) {
				return "redirect:/";
			} else if("W".equals(ret.getMessage())) {
				model.addAttribute("phoneNum", phoneNum);
				model.addAttribute("phoneCaptcha", phoneCaptcha);
				return "password";
			} else {
				return "password";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			model.addAttribute("msg", "请求异常，请稍后重试!");
			return "password";
		}
	}
	
	/**
	 * 
	 * @Description: 用户上传头像
	 * @param @param request
	 * @param @param myFile
	 * @param @return
	 * @param @throws IOException
	 * @return String
	 * @throws
	 * @author LiuKun
	 * @date 2016年9月26日
	 */
	@RequestMapping(value="/uploadImage") 
	public String edit(HttpServletRequest request,
						@RequestParam("uploadImage")MultipartFile myFile) throws IOException {
		List<File> fileList = new ArrayList<File>();
		File file = new File(myFile.getOriginalFilename());
		log.info(file.exists()+"");;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(myFile.getBytes());
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			log.error(e1.getMessage());
		}
		fileList.add(file);
		String url = domainConfig.getApiDomain()+"/faviconUpload";
		String response;
		try {
			response = TestUtils.loggedInRequest(request, null, url,fileList);
			log.info(response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally{
			if(file.exists()){
				file.delete();
			}
		}
		return "redirect:/my/toModify";
	}
}
