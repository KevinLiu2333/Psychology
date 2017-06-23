package com.klsw.piano.controller;

import com.klsw.piano.service.WxTokenService;
import com.klsw.piano.util.Constants;
import com.klsw.piano.util.WxSignUtils;
import com.klsw.piano.util._StringUtils;
import com.klsw.pianoCommon.api.model.WxToken;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by liukun on 2016/11/15.
 * 微信相关
 */
@Controller
@RequestMapping(value = "/wx")
public class WXController {
    private final Logger logger = LoggerFactory.getLogger(WXController.class);
    private static String TOKEN = "server";

    @Resource
    private WxTokenService wxTokenService;

    @ResponseBody
    @RequestMapping
    public String excute(HttpServletRequest request) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        if (WxSignUtils.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    public WxToken getWeixinJsApiToken() {
        System.out.println(new Date());
        HttpClient httpClient = new HttpClient();
        boolean flag = true;
        //从数据库中找出默认token实例
        try {
            WxToken token = wxTokenService.selectByPrimaryKey(1);
            if (token == null) {
                token = new WxToken();
                token.setId(1);
                flag = false;
            }
            long newUtp = System.currentTimeMillis() / 1000;
            //条件判断
            if (token.getUtp() == null || newUtp - token.getUtp() > 300) {
                //微信接口
                String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx8da0e4adbeb68b5a&secret="
                        + Constants.WEIXINSECRET;
                GetMethod get1 = new GetMethod(url);
                httpClient.executeMethod(get1);
                String response = get1.getResponseBodyAsString();
                JSONObject ja1 = new JSONObject(response);
                //获取access_token
                String access_token = ja1.getString("access_token");
                String jsApiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
                GetMethod get2 = new GetMethod(jsApiUrl);
                httpClient.executeMethod(get2);
                String responseStringJs = get2.getResponseBodyAsString();
                JSONObject ja2 = new JSONObject(responseStringJs);
                String jsapi_ticket = ja2.getString("ticket");
                token.setUtp(newUtp);
                token.setToken(access_token);
                token.setTicket(jsapi_ticket);
                if (flag) {
                    wxTokenService.updateByPrimaryKey(token);
                } else {
                    wxTokenService.insert(token);
                }
            }
            return token;
        } catch (Exception e) {
            logger.error("msg",e);
        }
        return null;
    }

    private boolean checkSignature(HttpServletRequest request) {
        try {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");

            String[] arrTmp = {TOKEN, timestamp, nonce};
            //字典排序
            Arrays.sort(arrTmp);
            //将字符串数组拼接
            String tmpStr = StringUtils.join(arrTmp, "");

            tmpStr = _StringUtils.getSha1(tmpStr);

            tmpStr = tmpStr.toLowerCase();
            return tmpStr.equals(signature);
        } catch (Exception e) {
            logger.error("msg",e);
            return false;
        }
    }

    //微信ticket计划任务
    @Scheduled(fixedRate = 7200000)
    public void ticketSchedule() {
        HttpClient httpClient = new HttpClient();
        //从数据库中找出默认token实例
        boolean flag = true;
        try {
            WxToken token = wxTokenService.selectByPrimaryKey(1);
            if (token == null) {
                token = new WxToken();
                token.setId(1);
                flag = false;
            }
            long newUtp = System.currentTimeMillis() / 1000;
            //条件判断
            if (token.getUtp() == null || newUtp - token.getUtp() > 7140) {
                //微信接口
                String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx8da0e4adbeb68b5a&secret="
                        + Constants.WEIXINSECRET;
                GetMethod get1 = new GetMethod(url);
                httpClient.executeMethod(get1);
                String response = get1.getResponseBodyAsString();
                JSONObject ja1 = new JSONObject(response);
                //获取access_token
                String access_token = ja1.getString("access_token");
                String jsApiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
                GetMethod get2 = new GetMethod(jsApiUrl);
                httpClient.executeMethod(get2);
                String responseStringJs = get2.getResponseBodyAsString();
                JSONObject ja2 = new JSONObject(responseStringJs);
                String jsapi_ticket = ja2.getString("ticket");
                token.setUtp(newUtp);
                token.setToken(access_token);
                token.setTicket(jsapi_ticket);
                if (flag) {
                    wxTokenService.updateByPrimaryKey(token);
                } else {
                    wxTokenService.insert(token);
                }

            }

        } catch (Exception e) {
            logger.error("msg",e);
        }
    }

}



