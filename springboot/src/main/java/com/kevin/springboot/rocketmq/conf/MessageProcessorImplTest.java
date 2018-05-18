package com.kevin.springboot.rocketmq.conf;

import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: qxb-810
 * Date: 2018/5/18
 * Time: 下午 03:43
 */
@Component
public class MessageProcessorImplTest implements MessageProcessor {
    @Override
    public boolean handleMessage(MessageExt messageExt) {
        System.out.println("receive : " + messageExt.toString());
        return true;
    }
}
