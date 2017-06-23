package com.klsw.crosswaylive.server;

import com.google.common.collect.Lists;
import com.klsw.common.live.model.TbLiveroom;
import com.klsw.crosswaylive.server.constants.Attributes;
import com.klsw.crosswaylive.service.TbLiveRoomService;
import com.klsw.crosswaylive.service.TbLiveUserService;
import com.klsw.crosswaylive.service.VisitorService;
import com.klsw.crosswaylive.util.AliyunUtils;
import com.klsw.crosswaylive.util.Constants;
import com.klsw.crosswaylive.util.JsonUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.klsw.crosswaylive.server.constants.Types.login;

/**
 * Created by liukun on 2017/3/24.
 * TCP服务处理类
 */
@Service
public class TCPService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TCPService.class);
    @Resource
    private TbLiveUserService userService;

    @Resource
    private LiveRoomGroup liveRoomGroup;

    @Resource
    private VisitorService visitorService;

    @Resource
    private TbLiveRoomService liveRoomService;

    @Resource
    private RaiseService raiseService;

    @Resource
    private ConvertService convertService;

    @Resource
    private ExitService exitService;

    @Resource
    private ChatService chatService;

    @Resource
    private CollectService collectService;

    public void execute(ChannelHandlerContext ctx, TCPMessage message) {
        switch (message.type()) {
            //登录
            case "login":
                login(ctx, message);
                break;
            //开始直播
            case "startliving":
                startLiving(ctx, message);
                break;
            //加入直播
            case "joinliving":
                joinLiving(ctx, message);
                break;
            //开始直播
            case "startinteracting":
                startinteracting(ctx, message);
                break;
            //聊天
            case "chat":
                chatService.chat(ctx, message);
                break;
            //举手
            case "raise":
                raiseService.raise(ctx, message);
                break;
            //切换视频流
            case "convert":
                convertService.convert(ctx, message);
                break;
            //退出直播间
            case "exit":
                exitService.exitLiveRoom(ctx, message);
                break;
            //收藏曲谱
            case "collect":
                collectService.collect(ctx, message);
                break;
            case "shareopern":
                shareopern(ctx, message);
                break;
            case "paint":
                paint(ctx, message);
                break;
            default:
                break;

        }
    }

    /**
     * 登录请求处理
     *
     * @param ctx     context
     * @param message 信息
     */
    public void login(ChannelHandlerContext ctx, TCPMessage message) {
        //info参数:username usertype
        String usertype = message.info("usertype");
        String username = message.info("username");
        String ticket = message.info("ticket");
        if (StringUtils.isBlank(username) || StringUtils.isBlank(usertype)) {
            res(ctx, "login", "E", "fail");
            return;
        }
        //用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUsertype(usertype);
        userInfo.setUsername(username);
        String userId = "";
        //游客登录验证
        if ((usertype.equals("visitor") && (userId = String.valueOf(visitorService.selectByName(username))) != null) || (usertype.equals("user") && (userId = userService.CheckTicket(username, ticket)) != null)) {
            Map<String, Object> map = new HashMap<>();
            userInfo.setUserId(userId);
            map.put(Attributes.userInfo.name(), userInfo);
            map.put(Attributes.login.name(), true);
            setAttributes(ctx, map);
            res(ctx, "login", "S", "success");
            return;
        }
        res(ctx, "login", "E", "fail");
        ctx.close();
    }

    /**
     * 发起直播请求
     *
     * @param ctx     ctx
     * @param message 信息
     */
    public void startLiving(ChannelHandlerContext ctx, TCPMessage message) {
        try {
            Integer liveRoomId = Integer.parseInt(message.info("liveroomid"));
            UserInfo userInfo = (UserInfo) ctx.attr(AttributeKey.valueOf(Attributes.userInfo.name())).get();
            TbLiveroom liveroom = liveRoomService.selectByPrimaryKey(liveRoomId);
            if (liveroom == null || !userInfo.getUserId().equals(String.valueOf(liveroom.getAnchorid())) || liveroom.getStatus() > 1) {
                res(ctx, "startliving", "E", "fail");
                return;
            }
            userInfo.setAudtype("anchor");
            userInfo.setLiveRoomId(liveRoomId);
            ctx.attr(AttributeKey.valueOf(Attributes.userInfo.name())).set(userInfo);
            liveroom.setStatus(1);
            liveRoomService.updateByPrimaryKey(liveroom);
            List<String> interactUser = Lists.newArrayList(liveroom.getInteractuser().split(";"));
            //设置直播间相关参数
            LiveRoom liveRoom = new LiveRoom(liveRoomId, ctx.channel(), userInfo.getUsername(), liveroom.getStarttime(), liveroom.getEndtime(), interactUser, userInfo.getUsername());
            liveRoomGroup.put(liveRoomId, liveRoom);
            res(ctx, "startliving", "S", AliyunUtils.getPushStreamPath(userInfo.getUsername()));
        } catch (Exception e) {
            LOGGER.error("msg", e);
            res(ctx, "startliving", "E", "fail");
        }
    }

    /**
     * 普通用户建立tcp链接进入直播间
     *
     * @param ctx     ctx
     * @param message 信息
     */
    public void joinLiving(ChannelHandlerContext ctx, TCPMessage message) {
        try {
            //直播间id
            Integer liveRoomId = Integer.parseInt(message.info("liveroomid"));
            //用户类型
            String audtype = message.info("audtype");
            //参数验证
            LiveRoom liveRoom = liveRoomGroup.get(liveRoomId);
            if (StringUtils.isBlank(audtype) || liveRoom == null) {
                res(ctx, "joinliving", "W", "直播间不存在");
                return;
            }
            if (liveRoom.getTotals() > Constants.MAX_AUD && !"interact".equals(audtype)) {
                res(ctx, "joinliving", "W", "人数已满");
                return;
            }
            UserInfo userInfo = getUserInfo(ctx);
            if (liveRoom.addUser(audtype, ctx)) {
                userInfo.setLiveRoomId(liveRoomId);
                userInfo.setAudtype(audtype);
                setUserInfo(ctx, userInfo);
                Map<String, String> map = new HashMap<>();
                map.put("status", "S");
                map.put("message", AliyunUtils.getPullStreamPath(liveRoom.getTeachername()));
                if (!liveRoom.getCurrentStreamPath().equals(liveRoom.getTeachername()))
                    map.put("message2", AliyunUtils.getPullStreamPath(liveRoom.getCurrentStreamPath()));
                res(ctx, "joinliving", map);
                System.out.println(liveRoom.getChannels().size());
            } else {
                res(ctx, "joinliving", "E", "fail");
            }
        } catch (Exception e) {
            res(ctx, "joinliving", "E", "fail");
            LOGGER.error("msg", e);
        }
    }

    /**
     * 互动用户开始互动
     *
     * @param ctx     ctx
     * @param message 信息
     */
    public void startinteracting(ChannelHandlerContext ctx, TCPMessage message) {
        try {
            UserInfo userInfo = getUserInfo(ctx);
            Integer liveroomId = userInfo.getLiveRoomId();
            if (liveroomId == null || !userInfo.getAudtype().equals("interact")) {
                res(ctx, "startinteracting", "E", "fail");
                return;
            }
            Channel teacher = liveRoomGroup.get(liveroomId).getTeacher();
            Map<String, String> map = new HashMap<>();
            map.put("status", "S");
            map.put("message", AliyunUtils.getPullStreamPath(userInfo.getUsername()));
            map.put("username", userInfo.getUsername());
            res(ctx, "startinteracting", "S", AliyunUtils.getPushStreamPath(userInfo.getUsername()));
            res(teacher, "startinteracting", map);
        } catch (Exception e) {
            LOGGER.error("msg", e);
            res(ctx, "startinteracting", "E", "fail");
        }

    }

    /**
     * 教师分享曲谱
     *
     * @param ctx     ctx
     * @param message 信息
     */
    public void shareopern(ChannelHandlerContext ctx, TCPMessage message) {
        UserInfo userInfo = getUserInfo(ctx);
        Integer liveroomId = userInfo.getLiveRoomId();
        if (liveroomId == null || !userInfo.getAudtype().equals("anchor") || !liveRoomGroup.containsKey(liveroomId)) {
            return;
        }
        LiveRoom liveroom = liveRoomGroup.get(liveroomId);
        message.setService("response");
        message.getData().getInfo().put("status", "S");
        res(liveroom.getChannels(), message);

    }

    public void paint(ChannelHandlerContext ctx, TCPMessage message) {
        UserInfo userInfo = getUserInfo(ctx);
        Integer liveroomId = userInfo.getLiveRoomId();
        if (liveroomId == null || !userInfo.getAudtype().equals("anchor") || !liveRoomGroup.containsKey(liveroomId)) {
            return;
        }
        LiveRoom liveroom = liveRoomGroup.get(liveroomId);
        message.setService("response");
        message.getData().getInfo().put("status", "S");
        res(liveroom.getChannels(), message);

    }


    public static UserInfo getUserInfo(ChannelHandlerContext ctx) {
        return (UserInfo) ctx.attr(AttributeKey.valueOf(Attributes.userInfo.name())).get();
    }

    public UserInfo getUserInfo(Channel channel) {
        return (UserInfo) channel.attr(AttributeKey.valueOf(Attributes.userInfo.name())).get();
    }

    public void setUserInfo(ChannelHandlerContext ctx, UserInfo userInfo) {
        ctx.attr(AttributeKey.valueOf(Attributes.userInfo.name())).set(userInfo);
    }

    /**
     * @param ctx     ctx
     * @param type    返回请求类型
     * @param status  状态
     * @param message 信息
     */
    public void res(ChannelHandlerContext ctx, String type, String status, String message) {
        String response = JsonUtils.encode(TCPMessage.ResInfo("response", status, type, message)) + System.getProperty("line.separator");
        System.out.println(response);
        //返回结果
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }

    /**
     * @param ctx     ctx
     * @param type    返回请求类型
     * @param status  状态
     * @param message 信息
     */
    public void res(ChannelGroup ctx, String type, String status, String message) {
        String response = JsonUtils.encode(TCPMessage.ResInfo("response", status, type, message)) + System.getProperty("line.separator");
        //返回结果
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }

    public void res(ChannelGroup ctx, TCPMessage msg) {
        String response = JsonUtils.encode(msg) + System.getProperty("line.separator");
        //返回结果
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }

    public void res(Channel channel, String type, String status, String message) {
        String response = JsonUtils.encode(TCPMessage.ResInfo("response", status, type, message)) + System.getProperty("line.separator");
        //返回结果
        channel.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }

    public void res(Channel channel, String type, Map<String, String> params) {
        String response = JsonUtils.encode(TCPMessage.ResInfo("response", type, params)) + System.getProperty("line.separator");
        //返回结果
        channel.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }

    public void res(ChannelHandlerContext ctx, String type, Map<String, String> params) {
        String response = JsonUtils.encode(TCPMessage.ResInfo("response", type, params)) + System.getProperty("line.separator");
        //返回结果
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }

    public void setAttributes(ChannelHandlerContext ctx, Map<String, Object> attributes) {
        for (String key : attributes.keySet()) {
            Attribute<Object> attr = ctx.attr(AttributeKey.valueOf(key));
            attr.setIfAbsent(attributes.get(key));
        }
    }

    public boolean verify(ChannelHandlerContext ctx, TCPMessage message) {
        if (login.name().equals(message.type())) {
            return true;
        }
        AttributeKey<String> login = AttributeKey.valueOf(Attributes.login.name());
        return ctx.hasAttr(login);
    }

}

