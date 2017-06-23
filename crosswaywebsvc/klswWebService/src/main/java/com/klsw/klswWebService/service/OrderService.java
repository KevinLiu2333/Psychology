package com.klsw.klswWebService.service;

import com.klsw.apiCommon.api.mapper.TbCwkorderMapper;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwkbean;
import com.klsw.apiCommon.api.model.TbCwkorder;
import com.klsw.apiCommon.api.model.TbCwkorderBean;
import com.klsw.klswWebService.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by liukun on 2016/12/15.
 * 订单页面
 */
@Service
public class OrderService extends _BaseService<TbCwkorder> {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    TbCwkorderMapper tbCwkorderMapper;

    @Autowired
    TbCwkService tbCwkService;

    @Autowired
    BeanOrderService beanOrderService;

    @Autowired
    TbCwkBeanService tbCwkBeanService;

    public void closeOrderBySerial(String orderserial, Integer cwkId, Integer orderId) throws Exception {
        TbCwkorder tbCwkorder = new TbCwkorder();
        tbCwkorder.setId(orderId);
        tbCwkorder.setStatus(Constants.ORDER_STATUS_CLOSED);
        tbCwkorderMapper.updateByPrimaryKeySelective(tbCwkorder);
    }

    /**
     * 更新订单为已支付
     *
     * @param orderSerial
     * @param userId
     * @return
     * @throws Exception
     */
    public int updateToPay(String orderSerial, Integer userId, Integer orderId) throws Exception {
        //选出上述属性的订单，目前为未支付状态
        TbCwkorder entity = tbCwkorderMapper.selectByPrimaryKey(orderId);
        if (entity == null || entity.getStatus() != 1) {
            return 0;
        }
        entity.setStatus(Constants.ORDER_STATUS_PAY);
        int result = tbCwkorderMapper.updateByPrimaryKeySelective(entity);
        if (result != 1) {
            return 0;
        }
        TbCwk tbCwk = tbCwkService.selectByPrimaryKey(userId);
        TbCwkorderBean tbCwkorderBean = new TbCwkorderBean();
        tbCwkorderBean.setOrderserial(entity.getOrderserial());
        tbCwkorderBean = beanOrderService.selectOne(tbCwkorderBean);
        if (tbCwkorderBean == null || tbCwk == null) {
            return 0;
        }
        Integer beanCount = tbCwkorderBean.getBeancount();
        logger.error("beancount:" + beanCount);
        tbCwk.setCwkbeancount(tbCwk.getCwkbeancount() + beanCount);

        //添加威豆
        if (tbCwkService.updateByPrimaryKey(tbCwk) == 0) {
            return 0;
        }
        TbCwkbean tbCwkbean = new TbCwkbean();
        tbCwkbean.setCwkid(userId);
        tbCwkbean.setChangetype(1);
        tbCwkbean.setAddtime(new Date());
        tbCwkbean.setChangenumber(beanCount);
        tbCwkbean.setDescribtion("威豆充值");
        tbCwkbean.setRemainbean(tbCwk.getCwkbeancount());
        tbCwkBeanService.insert(tbCwkbean);
        return 1;
    }

}
