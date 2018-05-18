package com.kevin.springboot.rocketmq.conf;

import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * Created with IntelliJ IDEA.
 * User: qxb-810
 * Date: 2018/5/18
 * Time: 下午 03:42
 */
public interface MessageProcessor {
    /**
     * 处理消息的接口
     *
     * @param messageExt
     * @return
     */
    boolean handleMessage(MessageExt messageExt);
}
