package com.klsw.klswWebService.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liukun on 2017/3/24.
 * 通信协议类
 */
public class TCPMessage {
    private String service;
    private TCPData data;

    public TCPData getData() {
        return data;
    }

    public void setData(TCPData data) {
        this.data = data;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public static TCPMessage ResInfo(String service, String status, String type, String message) {
        Map<String, String> map = new HashMap<>();
        map.put("status", status);
        map.put("message", message);
        return new TCPMessage(service, new TCPData(type, map));
    }

    public static TCPMessage ResInfo(String service, String type, Map<String, String> info) {
        return new TCPMessage(service, new TCPData(type, info));
    }

    public TCPMessage(String service, TCPData data) {
        this.service = service;
        this.data = data;
    }

    public String info(String key) {
        return this.data.getInfo().get(key);
    }

    public Map<String, String> infoMap() {
        return this.data.getInfo();
    }

    public String type() {
        return this.data.getType();
    }

    public TCPMessage() {
    }
}







