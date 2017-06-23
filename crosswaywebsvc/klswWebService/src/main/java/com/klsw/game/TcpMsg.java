package com.klsw.game;


import com.alibaba.fastjson.JSONObject;

/**
 * Created by liukun on 2017/4/8.
 * udp信息
 */
public class TcpMsg {
    private String service;
    private Boolean ack;
    private String packetID;
    private JSONObject data;

    public TcpMsg() {
    }

    public TcpMsg(String service, Boolean ack, String packetID, JSONObject data) {
        this.service = service;
        this.ack = ack;
        this.packetID = packetID;
        this.data = data;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Boolean getAck() {
        return ack;
    }

    public void setAck(Boolean ack) {
        this.ack = ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public String getPacketID() {
        return packetID;
    }

    public void setPacketID(String packetID) {
        this.packetID = packetID;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "packetId:  " + packetID + "  service:  " + service;
    }
}
