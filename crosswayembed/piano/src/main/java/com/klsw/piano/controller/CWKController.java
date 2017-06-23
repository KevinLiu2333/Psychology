package com.klsw.piano.controller;

import com.alibaba.druid.util.StringUtils;
import com.klsw.piano.service.TbCwkBindSNService;
import com.klsw.piano.service.TbCwkService;
import com.klsw.piano.util.PasswdEncryption;
import com.klsw.pianoCommon.api.model.TbCwk;
import com.klsw.pianoCommon.api.model.TbCwkbindsn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 钢琴端威客登录和注册
 *
 * @author HKJ
 */
@Controller
@RequestMapping(value = "/CWK")
public class CWKController {
    private static final Logger log = LoggerFactory.getLogger(CWKController.class);

    @Resource
    private TbCwkService cwkService;

    @Resource
    private TbCwkBindSNService bindSnService;

    /**
     * 接口：钢琴端用户威客注册
     *
     * @param request 请求
     */
    @RequestMapping(value = "CWKRegister")
    @ResponseBody
    public String CWKRegister(HttpServletRequest request) {
        String cwkname = request.getParameter("cwkname");
        String cwkpwd = request.getParameter("cwkpwd");
        if (StringUtils.isEmpty(cwkname) || StringUtils.isEmpty(cwkpwd)) {
            return "1, cwkname is null or cwkpwd is null, 用户名或密码为空！";
        }

        try {
            Date time = new Date();
            TbCwk tbCwk = new TbCwk();
            tbCwk.setName(cwkname);
            if (!cwkService.select(tbCwk).isEmpty()) {
                return "2, cwkname already exits, choose another user name, 用户名已存在，请使用其他用户名！";
            }
            TbCwk mysqlCwk = new TbCwk();
            mysqlCwk.setName(cwkname);
            mysqlCwk.setNickname(cwkname);
            mysqlCwk.setPwd(PasswdEncryption.toPasswd(cwkpwd));
            mysqlCwk.setType("stu");
            mysqlCwk.setRegisttime(time);
            mysqlCwk.setLastlogintime(time);
            mysqlCwk.setCwkbeancount(0);
            cwkService.insertSelective(mysqlCwk);

            String sn = request.getParameter("code");
            if (!StringUtils.isEmpty(sn)) {
                TbCwkbindsn cwkBind = new TbCwkbindsn();
                cwkBind.setCwkid(mysqlCwk.getId());
                cwkBind = bindSnService.selectOne(cwkBind);
                if (cwkBind == null) {
                    cwkBind = new TbCwkbindsn();
                    cwkBind.setCwkid(mysqlCwk.getId());
                    cwkBind.setTime(new Date());
                    cwkBind.setSn(sn);
                    bindSnService.insert(cwkBind);
                } else if (!cwkBind.getSn().equals(sn)) {
                    cwkBind.setSn(sn);
                    cwkBind.setTime(new Date());
                    bindSnService.updateByPrimaryKey(cwkBind);
                }
            }

            return "0, ok, nickname=" + cwkname; // 注册成功
        } catch (Exception ex) {
            log.info(ex.getMessage());
            return "3, unknown error: " + ex.getMessage() + ", 未知错误：" + ex.getMessage();
        }
    }

    /**
     * 接口：钢琴端用户威客登录
     *
     * @param request 请求
     */
    @RequestMapping(value = "CWKLogin")
    @ResponseBody
    public String CWKLogin(HttpServletRequest request) {
        String cwkname = request.getParameter("cwkname");
        String cwkpwd = request.getParameter("cwkpwd");
        if (StringUtils.isEmpty(cwkname) || StringUtils.isEmpty(cwkpwd)) {
            return "1, cwkname is null or cwkpwd is null, 用户名或密码为空！";
        }

        try {

            // 查询有没有cwk用户：tb_CWK
            TbCwk cwk = new TbCwk();
            cwk.setName(cwkname);
            cwk = cwkService.selectOne(cwk);

            if (cwk == null || !cwk.getPwd()
                    .equals(PasswdEncryption.toPasswd(cwkpwd, cwk.getPwd().substring(0, 2).getBytes()))) {
                return "2, cwk user does not exist or pwd is wrong, 用户名不存在或密码错误！";
            }

            if ("tea".equals(cwk.getType())) {
                return "4,cwk user type is only student on piano, 钢琴端仅有学生可以登录！";
            }

            String sn = request.getParameter("code");
            if (!StringUtils.isEmpty(sn)) {
                TbCwkbindsn cwkBind = new TbCwkbindsn();
                cwkBind.setCwkid(cwk.getId());
                cwkBind = bindSnService.selectOne(cwkBind);
                if (cwkBind == null) {
                    cwkBind = new TbCwkbindsn();
                    cwkBind.setCwkid(cwk.getId());
                    cwkBind.setTime(new Date());
                    cwkBind.setSn(sn);
                    bindSnService.insert(cwkBind);
                } else if (!cwkBind.getSn().equals(sn)) {
                    cwkBind.setSn(sn);
                    cwkBind.setTime(new Date());
                    bindSnService.updateByPrimaryKey(cwkBind);
                }
            }

            cwk.setLastlogintime(new Date());
            cwkService.updateByPrimaryKeySelective(cwk);
            return "0, ok, nickname=" + cwk.getNickname() + ",ticket=" + cwk.getTicket(); // 登录成功
        } catch (Exception ex) {
            log.info(ex.getMessage());
            return "3, unknown error: " + ex.getMessage() + ", 未知错误：" + ex.getMessage();
        }

    }

}
