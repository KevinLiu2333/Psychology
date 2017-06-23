package com.klsw.klswWebService.controller;

import com.google.common.collect.Maps;
import com.klsw.apiCommon.api.model.TbCwkorder;
import com.klsw.klswWebService.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/alipay")
public class AlipayNotifyController {

    private static final Logger logger = LoggerFactory.getLogger(AlipayNotifyController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 支付宝异步回调
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "notify", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public String notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("*****************************支付宝回调开始*****************************");

        String result = null;

        response.setCharacterEncoding("UTF-8");

        try {
            //获取支付宝POST过来反馈信息
            Map<String, String> params = Maps.newHashMap();
            //获取请求参数map
            Map<String, String[]> requestParams = request.getParameterMap();
            //迭代器遍历键
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                //参数名
                String name = (String) iter.next();
                //参数值...有可能一个参数名对应多个值
                String[] values = (String[]) requestParams.get(name);

                String valueStr = "";
                //遍历参数值
                for (int i = 0; i < values.length; i++) {
                    //是最后一条就不加逗号了
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //将键值对存入param中
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            // 通知时间
            String notify_time = params.get("notify_time");
            logger.info("通知时间：" + notify_time);

            // 通知类型
            String notify_type = params.get("notify_type");
            logger.info("通知类型：" + notify_type);

            // 通知校验ID
            String notify_id = params.get("notify_id");
            logger.info("通知校验ID：" + notify_id);

            // 签名方式
            String sign_type = params.get("sign_type");
            logger.info("签名方式：" + sign_type);

            // 签名
            String sign = params.get("sign");
            logger.info("签名：" + sign);

            //商户订单号
            String out_trade_no = params.get("out_trade_no");
            logger.info("商户订单号：" + out_trade_no);

            //支付宝交易号
            String trade_no = params.get("trade_no");
            logger.info("支付宝交易号：" + trade_no);

            //交易状态
            String trade_status = params.get("trade_status");
            logger.info("交易状态：" + trade_status);

            //交易金额
            String total_fee = params.get("total_fee");
            logger.info("交易金额：" + total_fee);

            //公用回传参数
            String extra_common_param = params.get("extra_common_param");
            logger.info("公用回传参数：" + extra_common_param);

            String buyer_email = params.get("buyer_email");
            logger.info("买家支付宝账号：" + buyer_email);

            boolean isVerify = false;
//                    AlipayNotify.verify(params);
            if (trade_status.equals("TRADE_SUCCESS")) {
                isVerify = true;
            }

            logger.info("是否验证成功： " + isVerify);

            //在验证交易成功的基础上进行的操作
            if (isVerify) {//验证成功
                //这个参数是之前传过去的
                //用户id^订单序列号^订单id
                Integer userId;
                String orderSerial;
                String orderType;
                Integer orderId;
                TbCwkorder tbCwkorder = new TbCwkorder();
                tbCwkorder.setOrderserial(out_trade_no);
                tbCwkorder = orderService.selectOne(tbCwkorder);
                if (StringUtils.isBlank(extra_common_param)) {
                    if (Double.parseDouble(request.getParameter("total_amount")) != tbCwkorder.getTotalprice()) {
                        return "fail";
                    }
                    userId = tbCwkorder.getCwkid();
                    orderSerial = trade_no;
                    orderType = tbCwkorder.getType();
                    orderId = tbCwkorder.getId();

                } else {
                    if (Integer.parseInt(total_fee) != tbCwkorder.getTotalprice()) {
                        return "fail";
                    }
                    String[] paramsArray = extra_common_param.split("\\^");
                    //得到其中的userId orderSerial orderId
                    userId = Integer.parseInt(paramsArray[0]);
                    //订单序列号
                    orderSerial = paramsArray[1];
                    //订单类型
                    orderType = paramsArray[2];
                    //订单ID
                    orderId = Integer.parseInt(paramsArray[3]);
                }

                // 交易完成

                //交易结束
                if (trade_status.equals("TRADE_FINISHED")) {
                    logger.info("-------------交易结束------------");
                    //设置为交易关闭状态
                    orderService.closeOrderBySerial(orderSerial, userId, orderId);
                    result = "success";
                    //交易成功
                } else if (trade_status.equals("TRADE_SUCCESS")) { // 交易成功
                    //订单设置为已支付状态
                    int updateCount = orderService.updateToPay(orderSerial, userId, orderId);

                    //如果有更新，更新成功
                    if (updateCount > 0) {
                        result = "success";


                    } else {
                        result = "fail";
                    }
                }
            } else {//验证失败
                result = "fail";
            }
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            result = "fail";
            throw e;
        }

        logger.info("业务处理结果：" + result);

        logger.info("*****************************支付宝回调结束*****************************");

        return result;
    }


//    public static void main(String[] args) {
//        Double d = 1.00;
//        System.out.println(Double.parseDouble("1") == d);
//    }

}
