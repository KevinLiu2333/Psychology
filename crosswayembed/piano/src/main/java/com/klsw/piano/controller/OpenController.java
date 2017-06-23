package com.klsw.piano.controller;

import com.klsw.piano.service.*;
import com.klsw.piano.util.*;
import com.klsw.pianoCommon.api.model.TbCwkhomework;
import com.klsw.pianoCommon.api.model.TbEventmidi;
import com.klsw.pianoCommon.api.model.TbHomework;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Created by liukun on 2016/11/16.
 * 微信分享
 */
@Controller
@RequestMapping(value = "/open")
public class OpenController {

    private static final Logger logger = LoggerFactory.getLogger(OpenController.class);
    @Resource
    private WXController wxController;

    @Resource
    private TbHomeworkService tbHomeworkService;

    @Resource
    private TbCwkhomeworkService tbCwkhomeworkService;

    @Resource
    private TbCwkService tbCwkService;

    @Resource
    private TbEventmidiService tbEventmidiService;


    /**
     * @param id      微信作业加密后Id
     * @param request 请求
     * @return 页面
     */
    @RequestMapping(value = "/work/{id}")
    public ModelAndView work(@PathVariable("id") String id,
                             HttpServletRequest request) {
        ModelAndView mav = null;
        try {
            String code = request.getParameter("code");
            String from = request.getParameter("from");
            logger.info("WxUserCode: " + code);
            if (code != null && !"singlemessage".equals(from)) {
                HttpClient client = new HttpClient();
                String uri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WxUtils.appId + "&secret=" + Constants.WEIXINSECRET + "&code=" + code + "&grant_type=authorization_code";
                GetMethod method = new GetMethod(uri);
                client.executeMethod(method);
                JSONObject jsonObject = JSONObject.fromObject(method.getResponseBodyAsString());
                logger.info(jsonObject.toString());
                if (jsonObject.containsKey("openid")) {
                    String openid = jsonObject.getString("openid");
                    String token = wxController.getWeixinJsApiToken().getToken();
                    uri = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token + "&openid=" + openid + "&lang=zh_CN";
                    logger.info("uri:  " + uri);
                    method = new GetMethod(uri);
                    client.executeMethod(method);
                    logger.info(method.getResponseBodyAsString());
                    jsonObject = JSONObject.fromObject(method.getResponseBodyAsString());
                    if (jsonObject.containsKey("subscribe") && (int) jsonObject.get("subscribe") == 0) {
                        mav = new ModelAndView("common/follow");
                        return mav;
                    }
                }
            }
            //时间戳参数
            long timestamp = System.currentTimeMillis() / 1000;
            StringBuilder noncestr = new StringBuilder();
            mav = new ModelAndView("PM/open/work");
            Random random = new Random();
            for (int i = 0; i < 16; i++) {
                noncestr.append(Constants.RANDOMSTRINGBASE.charAt(random.nextInt(Constants.RANDOMSTRINGBASE.length() - 1)));
            }
            //微信签名随机字符串

            //获取微信ticket
            String ticket = wxController.getWeixinJsApiToken().getTicket();
            String url = HttpUtils.getIpAddr(request);
            if (url.contains("#")) {
                url = url.substring(0, url.indexOf("#"));
            }
            String string1 = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
            //SHA加密
            //解密
            String weixinsource = Constants.WEIXINSOURCE;
            String hkid = DES.decrypt(id, "crossway", "waycross");
            if (hkid.startsWith("normal")) {
                hkid = hkid.replace("normal", "");
                TbHomework homework = tbHomeworkService.selectByPrimaryKey(Integer.parseInt(hkid));
                if (homework == null) {
                    homework = new TbHomework();
                }
                String fullmidpath = weixinsource + homework.getMidimgpath();
                String fullmp3path = weixinsource + homework.getMp3path();
                String fullmidimgpath = weixinsource + homework.getMidimgpath();
                mav.addObject("timestamp", timestamp);
                mav.addObject("noncestr", noncestr.toString());
                mav.addObject("signature", _StringUtils.getSha1(string1));
                mav.addObject("weixinsource", weixinsource);
                mav.addObject("fullmidpath", fullmidpath);
                mav.addObject("fullmp3path", fullmp3path);
                mav.addObject("MIDName", homework.getMidname());
                mav.addObject("Author", homework.getStudentname());
                mav.addObject("pageInfo", homework);
                mav.addObject("studenttime", homework.getStudenttime());
                mav.addObject("fullmidimgpath", fullmidimgpath);
                return mav;
            } else if (hkid.startsWith("wk")) {
                hkid = hkid.replace("wk", "");
                int inthkid = Integer.parseInt(hkid);
                TbCwkhomework homework = tbCwkhomeworkService.selectByPrimaryKey(inthkid);
                if (homework == null) {
                    homework = new TbCwkhomework();
                }
                String fullmidpath = weixinsource + homework.getMidimgpath();
                //批改后的作业保存在威客OSS上
                String fullmp3path = weixinsource + homework.getMp3path();
                String fullmidimgpath = weixinsource + homework.getMidimgpath();
                mav.addObject("timestamp", timestamp);
                mav.addObject("noncestr", noncestr.toString());
                mav.addObject("signature", _StringUtils.getSha1(string1));
                mav.addObject("weixinsource", weixinsource);
                mav.addObject("fullmidpath", fullmidpath);
                mav.addObject("fullmp3path", fullmp3path);
                mav.addObject("MIDName", homework.getTitle());
                mav.addObject("Author", tbCwkService.selectByPrimaryKey(homework.getStudentid()).getNickname());
                mav.addObject("pageInfo", homework);
                mav.addObject("studenttime", homework.getAddtime());
                mav.addObject("fullmidimgpath", fullmidimgpath);
                return mav;
            }
        } catch (Exception e) {
            logger.error("msg", e);
        }
        return mav;
    }

    /**
     * @param timestampString 活动分享参数
     * @param request         请求
     * @return 页面
     */
    @RequestMapping(value = "/event/{timestamp}")
    public ModelAndView event(@PathVariable("timestamp") Long timestampString,
                              HttpServletRequest request) {
        //时间戳参数
        long timestamp = System.currentTimeMillis() / 1000;
        StringBuilder noncestr = new StringBuilder();
        ModelAndView mav = new ModelAndView("PM/open/event");
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            noncestr.append(Constants.RANDOMSTRINGBASE.charAt(random.nextInt(Constants.RANDOMSTRINGBASE.length() - 1)));
        }
        //微信签名随机字符串
        try {
            //获取微信ticket
            String ticket = wxController.getWeixinJsApiToken().getTicket();
            logger.info("ticket:" + ticket);
            String url = HttpUtils.getIpAddr(request);
            if (url.contains("#")) {
                url = url.substring(0, url.indexOf("#"));
            }
            String string1 = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
            TbEventmidi tbEventmidi = new TbEventmidi();
            tbEventmidi.setTimestamp(timestampString);
            String WEIXINSOURCE = Constants.WEIXINSOURCE;
            tbEventmidi = tbEventmidiService.selectOne(tbEventmidi);
            mav.addObject("timestamp", timestamp);
            mav.addObject("noncestr", noncestr.toString());
            mav.addObject("signature", _StringUtils.getSha1(string1));
            mav.addObject("weixinsource", Constants.WEIXINSOURCE);
            mav.addObject("fullmidpath", WEIXINSOURCE + tbEventmidi.getMidpath());
            mav.addObject("fullmp3path", WEIXINSOURCE + tbEventmidi.getMp3path());
            mav.addObject("MIDName", tbEventmidi.getMidname());
            mav.addObject("Author", tbEventmidi.getAuthor());
            mav.addObject("addtime", tbEventmidi.getAddtime());
            return mav;
        } catch (Exception e) {
            logger.error("msg", e);
        }
        return mav;
    }

}
