package com.alipay.util;

import com.alipay.config.AlipayConfig;
import com.alipay.sign.MD5;
import com.google.common.collect.Maps;
import com.klsw.apiCommon.api.model.TbCwkorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* *
 *类名：AlipaySubmit
 *功能：支付宝各接口请求提交类
 *详细：构造支付宝各接口表单HTML文本，获取远程HTTP数据
 *版本：3.3
 *日期：2012-08-13
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipaySubmit {

    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

    /**
     * 生成签名结果
     *
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestMysign(Map<String, String> sPara) {
        String prestr = AlipayCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if (AlipayConfig.sign_type.equals("MD5")) {
            mysign = MD5.sign(prestr, AlipayConfig.key, AlipayConfig.input_charset);
        }
        return mysign;
    }

    /**
     * 生成要请求给支付宝的参数数组
     *
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", AlipayConfig.sign_type);

        return sPara;
    }

    /**
     * 建立请求，以表单HTML形式构造（默认）
     *
     * @param sParaTemp     请求参数数组
     * @param strMethod     提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        //请求参数键集合
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW
                + "_input_charset=" + AlipayConfig.input_charset + "\" method=\"" + strMethod
                + "\">");
        //解析请求数组
        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }

    public static String buildBeanPayRequest(TbCwkorder tbCwkorder) {
        //用来拼写请求参数的stringBuilder
        StringBuilder extraParam = new StringBuilder();
        //用户id^订单序列号^充值类型^威豆订单ID
        extraParam.append(tbCwkorder.getCwkid()).append("^").append(tbCwkorder.getOrderserial()).append("^").append("威豆充值").append("^").append(tbCwkorder.getId());
        //新建一个参数map
        Map<String, String> sParaTemp = Maps.newHashMap();
        /**
         * 把请求参数打包成数组
         * 参数详情可见AlipayConfig
         */
        // 调用的接口名，无需修改..."create_direct_pay_by_user"
        sParaTemp.put("service", AlipayConfig.service);
        // 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
        sParaTemp.put("partner", AlipayConfig.partner);
        // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
        // 通过此参数和上一个参数得知订单请求的目的地和收款方
        sParaTemp.put("seller_id", AlipayConfig.partner);
        // 字符编码格式，目前使用的是utf-8
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        // 支付类型 ，无需修改...1
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        //订单号，必填项目，成功后会回调，根据此参数可得知成功支付的账单号
        sParaTemp.put("out_trade_no", tbCwkorder.getOrderserial());
        //业务描述
        sParaTemp.put("subject", "克洛斯威产品购买,业务交易号:" + tbCwkorder.getOrderserial());
        //设置总价
        sParaTemp.put("total_fee", String.valueOf(tbCwkorder.getTotalprice()));
        //设置额外的公共参数...用户id^订单序列号^充值类型^威豆订单ID
        sParaTemp.put("extra_common_param", extraParam.toString());
        //设置未付款交易的超时时间...7天
        sParaTemp.put("it_b_pay", "7d");
        // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        sParaTemp.put("return_url", AlipayConfig.return_url);
        return AlipaySubmit.buildRequest(sParaTemp, "post", "充值");
    }

//
//    /**
//     *
//     * @param order 订单详情
//     * @return
//     */
//  	public static String buildGoodsPayRequest(TMallOrder order) {
//  		//用来拼写请求参数的stringBuilder
//  		StringBuilder extraParam = new StringBuilder();
//
//  		//用户id^订单序列号^订单id
//  		extraParam.append(order.getUserid()).append("^").append(order.getSerial()).append("^").append(order.getId());
//
//  		//新建一个参数map
//  		Map<String, String> sParaTemp = Maps.newHashMap();
//  		/**
//  		 * 把请求参数打包成数组
//  		 * 参数详情可见AlipayConfig
//  		 */
//  		// 调用的接口名，无需修改..."create_direct_pay_by_user"
//  		sParaTemp.put("service", AlipayConfig.service);
//
//  		// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
//  		sParaTemp.put("partner", AlipayConfig.partner);
//
//  		// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
//  		// 通过此参数和上一个参数得知订单请求的目的地和收款方
//  		sParaTemp.put("seller_id", AlipayConfig.partner);
//
//  		// 字符编码格式，目前使用的是utf-8
//  		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//
//  		// 支付类型 ，无需修改...1
//  		sParaTemp.put("payment_type", AlipayConfig.payment_type);
//
//  		//订单号，必填项目，成功后会回调，根据此参数可得知成功支付的账单号
//  		sParaTemp.put("out_trade_no", order.getSerial());
//
//  		//业务描述
//  		sParaTemp.put("subject", "克洛斯威产品购买,业务交易号:" + order.getSerial());
//
//  		//设置总价
//  		sParaTemp.put("total_fee", String.valueOf(order.getTotalprice()));
//
//
//  		/*sParaTemp.put("notify_url", AlipayConfig.notify_url);*/
//  		//设置额外的公共参数...用户id^订单序列号^订单id
//  		sParaTemp.put("extra_common_param", extraParam.toString());
//
//  		//设置未付款交易的超时时间...7天
//  		sParaTemp.put("it_b_pay", "7d");
//
//  		// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//  		// 回调的网址"http://www.chinacrossway.com/alipay/notify";修改状态的controller路径
//  		sParaTemp.put("notify_url", AlipayConfig.notify_url);
//
//  		// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//  		// "http://www.chinacrossway.com/order/list"支付完成后回到此页面
// 		sParaTemp.put("return_url", AlipayConfig.return_url);
//  		return AlipaySubmit.buildRequest(sParaTemp, "post", "充值");
//      }
//

    /**
     * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数
     * 注意：远程解析XML出错，与服务器是否支持SSL等配置有关
     * @return 时间戳字符串
     * @throws IOException
     * @throws DocumentException
     * @throws MalformedURLException
     */
    /*public static String query_timestamp() throws MalformedURLException,
                                                        DocumentException, IOException {

        //构造访问query_timestamp接口的URL串
        String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayConfig.partner + "&_input_charset" +AlipayConfig.input_charset;
        StringBuffer result = new StringBuffer();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new URL(strUrl).openStream());

        List<Node> nodeList = doc.selectNodes("//alipay/*");

        for (Node node : nodeList) {
            // 截取部分不需要解析的信息
            if (node.getName().equals("is_success") && node.getText().equals("T")) {
                // 判断是否有成功标示
                List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1) {
                    result.append(node1.getText());
                }
            }
        }

        return result.toString();
    }*/
}
