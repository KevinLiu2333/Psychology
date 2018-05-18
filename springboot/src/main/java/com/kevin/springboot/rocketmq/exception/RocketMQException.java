package com.kevin.springboot.rocketmq.exception;

/**
 * Created with IntelliJ IDEA.
 * User: qxb-810
 * Date: 2018/5/18
 * Time: 下午 03:29
 */
public class RocketMQException extends Exception {
    public RocketMQException() {
        super();
    }

    public RocketMQException(String message) {
        super(message);
    }

    public RocketMQException(String message, Throwable cause) {
        super(message, cause);
    }

    public RocketMQException(Throwable cause) {
        super(cause);
    }

    protected RocketMQException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
