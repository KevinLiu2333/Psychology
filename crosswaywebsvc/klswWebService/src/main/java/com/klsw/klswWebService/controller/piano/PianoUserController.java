package com.klsw.klswWebService.controller.piano;

import com.alibaba.druid.util.StringUtils;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwkbindsn;
import com.klsw.klswWebService.service.BindsnService;
import com.klsw.klswWebService.service.TbCwkService;
import com.klsw.klswWebService.util.PasswdEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class PianoUserController {

    @Autowired
    private TbCwkService tbCwkService;

    @Autowired
    private BindsnService bindsnService;

    /**
     * 钢琴端威客登录
     *
     * @param request
     * @param cwkname 威客用户名
     * @param cwkpwd  威客密码
     * @param code    钢琴序列号
     * @return
     * @author Hkj
     */
    @RequestMapping(value = "/cwk/cwklogin")
    @ResponseBody
    public String cwkLogin(HttpServletRequest request, @RequestParam("cwkname") String cwkname,
                           @RequestParam("cwkpwd") String cwkpwd,
                           @RequestParam("code") String code) {
        String message = "";
        if (StringUtils.isEmpty(cwkname) || StringUtils.isEmpty(cwkpwd)) {
            message = "1, cwkname is null or cwkpwd is null, 用户名或密码为空";
            return message;
        }
        try {
            Date date = new Date();
            TbCwk cwk = new TbCwk();
            cwk = tbCwkService.findByName(cwkname);
            if (cwk == null || cwk.getPwd().equals(PasswdEncryption.toPasswd(cwkpwd))) {
                message = "2, cwk user does not exist or pwd is wrong, 用户名不存在或密码错误！";
                return message;
            }
            if (!StringUtils.isEmpty(code)) {
                TbCwkbindsn tbCwkbindsn = new TbCwkbindsn();
                tbCwkbindsn.setCwkid(cwk.getId());
                tbCwkbindsn = bindsnService.selectOne(tbCwkbindsn);
                if (tbCwkbindsn == null) {
                    tbCwkbindsn = new TbCwkbindsn();
                    tbCwkbindsn.setCwkid(cwk.getId());
                    tbCwkbindsn.setSn(code);
                    tbCwkbindsn.setTime(date);
                    bindsnService.insert(tbCwkbindsn);
                }
                if (!tbCwkbindsn.getSn().equals(code)) {
                    tbCwkbindsn.setSn(code);
                    tbCwkbindsn.setTime(date);
                    bindsnService.updateByPrimaryKeySelective(tbCwkbindsn);
                }
            }
            cwk.setLastlogintime(date);
            tbCwkService.updateByPrimaryKey(cwk);
            message = "0, ok, nickname=" + cwk.getNickname();
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            message = "3, unknown error: " + e.getMessage() + e.getCause() + ", 未知错误：" + e.getMessage();
            return message;
        }
    }

    /**
     * 钢琴端威客注册
     *
     * @param request
     * @param cwkname 威客用户名
     * @param cwkpwd  威客密码
     * @return
     * @author Hkj
     */
    @RequestMapping(value = "/cwk/cwkregister")
    @ResponseBody
    public String cwkRegister(HttpServletRequest request,
                              @RequestParam("cwkname") String cwkname,
                              @RequestParam("cwkpwd") String cwkpwd,
                              @RequestParam("code") String code) {
        String message = "";
        if (StringUtils.isEmpty(cwkname) || StringUtils.isEmpty(cwkpwd)) {
            message = "1, cwkname is null or cwkpwd is null, 用户名或密码为空";
            return message;
        }

        try {
            Date date = new Date();
            if (tbCwkService.findByName(cwkname) != null) {
                message = "2, cwkname already exits, choose another user name, 用户名已存在，请使用其他用户名！";
                return message;
            }
            TbCwk tbCwk = new TbCwk();
            tbCwk.setName(cwkname);
            tbCwk.setNickname(cwkname);
            tbCwk.setPwd(PasswdEncryption.toPasswd(cwkpwd));
            tbCwk.setType("stu");
            tbCwk.setRegisttime(date);
            tbCwk.setLastlogintime(date);
            tbCwk.setCwkbeancount(0);
            tbCwkService.insert(tbCwk);

            if (!StringUtils.isEmpty(code)) {
                TbCwkbindsn tbCwkbindsn = new TbCwkbindsn();
                tbCwkbindsn.setCwkid(tbCwk.getId());
                tbCwkbindsn = bindsnService.selectOne(tbCwkbindsn);
                if (tbCwkbindsn == null) {
                    tbCwkbindsn = new TbCwkbindsn();
                    tbCwkbindsn.setCwkid(tbCwk.getId());
                    tbCwkbindsn.setSn(code);
                    tbCwkbindsn.setTime(date);
                    bindsnService.insert(tbCwkbindsn);
                }
                if (!tbCwkbindsn.getSn().equals(code)) {
                    tbCwkbindsn.setSn(code);
                    tbCwkbindsn.setTime(date);
                    bindsnService.updateByPrimaryKeySelective(tbCwkbindsn);
                }
            }
            message = "0, ok, nickname=" + cwkname;
            return message;
        } catch (Exception exception) {
            message = "3, unknown error: " + exception.getMessage() + ", 未知错误：" + exception.getMessage();
            return message;
        }
    }

}
