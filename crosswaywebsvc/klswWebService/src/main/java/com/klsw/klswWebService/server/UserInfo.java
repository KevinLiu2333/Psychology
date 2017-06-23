package com.klsw.klswWebService.server;

/**
 * Created by liukun on 2017/3/25.
 * 连接用户信息
 */
public class UserInfo {
    private String userId;
    private String usertype;
    private String username;
    private Integer liveRoomId;
    private String audtype;

    public String getAudtype() {
        return audtype;
    }

    public void setAudtype(String audtype) {
        this.audtype = audtype;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLiveRoomId() {
        return liveRoomId;
    }

    public void setLiveRoomId(Integer liveRoomId) {
        this.liveRoomId = liveRoomId;
    }
}
