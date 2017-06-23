package com.klsw.weikesite.controller;

import com.alipay.util.AlipaySubmit;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwkorder;
import com.klsw.weikesite.utils.DomainConfig;
import com.klsw.weikesite.utils.JsonUtils;
import com.klsw.weikesite.utils.TestUtils;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by liukun on 2016/12/15.
 * 订单相关
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private DomainConfig domainConfig;

    /**
     * 威豆重置
     *
     * @param params  威豆充值参数
     * @param request 请求明细
     * @return 充值页面
     */
    @RequestMapping(value = "/topUpBean")
    public ModelAndView topUpBean(@RequestParam Map<String, String> params, HttpServletRequest request) {
        String url = domainConfig.getApiDomain() + "/order/topUpBean";
        try {
            //充值金额
            String price = params.get("price");
            if (!StringUtils.isNumeric(price) || Double.parseDouble(price) < 0) {
                //充值信息有误,返回指定页面
                //TO FIXED
                return null;
            }
            //生成订单序列号
            String username;
            String type;
            String ticket;
            Integer cwkId;
            //如果是app调用接口,使用app传入的参数
            if (params.get("from") != null && "app".equals(params.get("from"))) {
                username = params.get("username");
                type = params.get("type");
                ticket = params.get("ticket");
                cwkId = Integer.parseInt(params.get("id"));
            } else {
                TbCwk user = (TbCwk) request.getSession(true).getAttribute("user");
                //用户信息有误
                if (user == null) {
                    return null;
                }
                username = user.getName();
                type = user.getType();
                ticket = user.getTicket();
                cwkId = user.getId();
            }
            NameValuePair nvp1 = new NameValuePair("price", price);
//            NameValuePair nvp2 = new NameValuePair("orderSerial", orderSerial);
            NameValuePair nvp2 = new NameValuePair("username", username);
            NameValuePair nvp3 = new NameValuePair("type", type);
            NameValuePair nvp4 = new NameValuePair("ticket", ticket);
            NameValuePair list[] = new NameValuePair[]{nvp1, nvp2, nvp3, nvp4};
            String response = TestUtils.getPostRequest(list, url, null);
            //返回状态成功则成功插入了一条订单
            Ret ret = JsonUtils.decode(response, Ret.class);
            logger.info("接口返回信息:" + response);
            if (ret == null || !"S".equals(ret.getStatus())) {
                //充值信息有误,返回指定页面
                //TO FIXED
                return null;
            }
            JSONObject jsonObject = new JSONObject(response);
            JSONObject jsonOrder = jsonObject.getJSONObject("data");
            TbCwkorder tbCwkorder = JsonUtils.decode(jsonOrder.toString(), TbCwkorder.class);
            String html = AlipaySubmit.buildBeanPayRequest(tbCwkorder);
            return new ModelAndView("common/blank", "html", html);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 前往支付页面
     *
     * @return 页面
     */
    @RequestMapping(value = "/toRecharge")
    public String toRecharge() {
        return "weidou/recharge";
    }

}

