package com.klsw.klswWebService.server;

import com.alibaba.fastjson.JSONObject;
import com.klsw.klswWebService.server.constants.Services;
import com.klsw.klswWebService.server.constants.Types;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.SocketAddress;

/**
 * Created by liukun on 2017/3/8.
 * handler
 */
@Component
@Scope("prototype")
@ChannelHandler.Sharable
public class LiveServerHandler extends ChannelHandlerAdapter {
    @Resource
    private LiveTCPService liveTCPService;
    @Resource
    private LiveRoomGroup liveRoomGroup;

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            System.out.println(ctx.channel().id());
            String body = (String) msg;
            System.out.println(msg);
            TCPMessage message = JSONObject.parseObject(body, TCPMessage.class);
            //请求参数验证
            if (message == null || !Services.LIST.contains(message.getService()) || !Types.LIST.contains(message.getData().getType()) || !liveTCPService.verify(ctx, message)) {
                liveTCPService.res(ctx, "verify", "E", "fail");
                return;
            }
            liveTCPService.execute(ctx, message);
        } catch (Exception e) {
            liveTCPService.res(ctx, "verify", "E", "fail");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();//将消息发送队列中的消息写入到SocketChannel中发送给对方
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        UserInfo userInfo = LiveTCPService.getUserInfo(ctx);
        Integer liveroomId;
        LiveRoom liveRoom;
        if (userInfo != null && (liveroomId = userInfo.getLiveRoomId()) != null && (liveRoom = liveRoomGroup.get(liveroomId)) != null) {
            liveRoom.deleteUser(userInfo.getAudtype(), ctx);
        }
        ctx.close();
    }

    @Override
    public boolean isSharable() {
        return super.isSharable();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "connected");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        System.out.println(ctx.channel().remoteAddress() + "handlerRemoved");
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        super.bind(ctx, localAddress, promise);
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "connected");
        super.connect(ctx, remoteAddress, localAddress, promise);
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.out.println("链接关闭");
        super.close(ctx, promise);
    }

    @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.deregister(ctx, promise);
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ctx.writeAndFlush(msg);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        super.flush(ctx);
    }

}



