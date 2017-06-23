package com.klsw.klswWebService.controller;

import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwkorder;
import com.klsw.apiCommon.api.model.TbCwkorderBean;
import com.klsw.klswWebService.service.BeanOrderService;
import com.klsw.klswWebService.service.OrderService;
import com.klsw.klswWebService.service.TbCwkService;
import com.klsw.klswWebService.util.RandomStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by liukun on 2016/12/15.
 * 刘坤
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Resource
    private OrderService orderService;
    @Resource
    private BeanOrderService beanOrderService;
    @Resource
    private TbCwkService tbCwkService;

    /**
     * 充值威豆接口
     * 威豆订单存入数据库
     *
     * @return 返回记录结果
     */
    @RequestMapping(value = "/topUpBean")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Ret topUpBean(@RequestParam Map<String, String> params) throws RuntimeException {
        try {
            String name = params.get("username");
            logger.info("用户名:" + name);
            String type = params.get("type");
            logger.info("用户类型:" + type);
            Integer cwkId = tbCwkService.selectId(name, type);
            String orderSerial = RandomStringUtil.genSerial();
            logger.info("序列号:" + orderSerial);
            String price = params.get("price");
            logger.info(price);
            if (!StringUtils.isNumeric(price) || StringUtils.isBlank(orderSerial)) {
                return Ret.warn("充值信息有误");
            }
            TbCwkorder tbCwkorder = new TbCwkorder();
            TbCwkorderBean tbCwkorderBean = new TbCwkorderBean();
            tbCwkorder.setCwkid(cwkId);
            tbCwkorder.setOrderserial(orderSerial);
            tbCwkorder.setCtime(new Date());
            tbCwkorder.setTotalprice(Double.parseDouble(price));
            tbCwkorder.setType("威豆充值");
            tbCwkorder.setStatus(1);
            tbCwkorderBean.setUserid(cwkId);
            tbCwkorderBean.setBeancount((int) Double.parseDouble(price) * 100);
            tbCwkorderBean.setOrderserial(orderSerial);
            tbCwkorderBean.setPrice(Double.parseDouble(price));
            tbCwkorderBean.setCtime(new Date());
            //更新订单表
            if (orderService.insert(tbCwkorder) != 1) {
                return Ret.error("订单异常");
            }
            //更新威豆订单表
            if (beanOrderService.insert(tbCwkorderBean) != 1) {
                return Ret.error("订单异常");
            }
            TbCwkorder cwkorder = new TbCwkorder();
            cwkorder.setOrderserial(orderSerial);
            cwkorder = orderService.selectOne(cwkorder);
            logger.info("订单id" + cwkorder.getId());
            //返回订单ID
            return Ret.success(tbCwkorder);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("订单异常");
        }
    }

//    public static void main(String[] args) {
//        System.out.println(Double.parseDouble("0.01"));
//    }
}



