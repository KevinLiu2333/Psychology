package com.kevin.springboot.note.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/4/12
 * Time: 10:45
 */
public class DemoEvent extends ApplicationEvent {

    private static final long serialVersionUID = -6312698201386841187L;
    private String msg;

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
