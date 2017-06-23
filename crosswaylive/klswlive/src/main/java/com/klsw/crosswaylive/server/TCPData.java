package com.klsw.crosswaylive.server;

import java.util.Map;

/**
 * Created by liukun on 2017/3/24.
 * TCPData
 */
public class TCPData {
    private String type;
    private Map<String, String> info;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    public TCPData(String type, Map<String, String> info) {
        this.type = type;
        this.info = info;
    }

    public TCPData() {
    }
}
