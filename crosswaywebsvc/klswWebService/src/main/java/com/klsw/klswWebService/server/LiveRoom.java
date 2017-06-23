package com.klsw.klswWebService.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by liukun on 2017/3/9.
 * 直播间
 */
public class LiveRoom {
    private String currentStreamPath;

    private String teachername;
    private Integer liveRoomId;
    //直播间的唯一标识
    private UUID uuid;
    //直播间链接用户
    private ChannelGroup channels = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    //主播
    private Channel teacher;
    //预约用户...设置id参数方便查找
    private ChannelGroup privateChannel = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    //直播间在线总人数
    private int totals = 0;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getCurrentStreamPath() {
        return currentStreamPath;
    }

    public void setCurrentStreamPath(String currentStreamPath) {
        this.currentStreamPath = currentStreamPath;
    }

    private List<String> interactUser;

    public List<String> getInteractUser() {
        return interactUser;
    }

    public void setInteractUser(List<String> interactUser) {
        this.interactUser = interactUser;
    }

    public Integer getLiveRoomId() {
        return liveRoomId;
    }

    public void setLiveRoomId(Integer liveRoomId) {
        this.liveRoomId = liveRoomId;
    }

    public void addUser() {
        totals++;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ChannelGroup getChannels() {
        return channels;
    }

    public void setChannels(ChannelGroup channels) {
        this.channels = channels;
    }

    public Channel getTeacher() {
        return teacher;
    }

    public void setTeacher(Channel teacher) {
        this.teacher = teacher;
    }

    public ChannelGroup getPrivateChannel() {
        return privateChannel;
    }

    public void setPrivateChannel(ChannelGroup privateChannel) {
        this.privateChannel = privateChannel;
    }

    public int getTotals() {
        return totals;
    }

    public void setTotals(int totals) {
        this.totals = totals;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public LiveRoom(Integer liveRoomId, Channel teacher, String teachername, Date startTime, Date endTime, List<String> interactUser, String currentStreamPath) {
        this.teachername = teachername;
        this.liveRoomId = liveRoomId;
        this.teacher = teacher;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totals = 0;
        channels.add(teacher);
        this.uuid = UUID.randomUUID();
        this.interactUser = interactUser;
        this.currentStreamPath = currentStreamPath;
    }

    public boolean deleteUser(String audType, ChannelHandlerContext ctx) {
        if ("normal".equals(audType)) {
            channels.remove(ctx.channel());
            totals--;
            return true;
        } else if ("interact".equals(audType)) {
            channels.remove(ctx.channel());
            privateChannel.remove(ctx.channel());
            totals--;
            return true;
        }
        return false;
    }

    /**
     * 用户加入直播间
     *
     * @param audType 用户类型:互动/普通
     * @param ctx     ctx
     */
    public boolean addUser(String audType, ChannelHandlerContext ctx) {
        if ("normal".equals(audType)) {
            channels.add(ctx.channel());
            totals++;
            return true;
        } else if ("interact".equals(audType)) {
            channels.add(ctx.channel());
            privateChannel.add(ctx.channel());
            totals++;
            return true;
        }
        return false;
    }
}


