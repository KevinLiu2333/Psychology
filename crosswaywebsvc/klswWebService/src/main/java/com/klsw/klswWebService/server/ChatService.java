package com.klsw.klswWebService.server;

import com.klsw.klswWebService.service.TbLiveRoomService;
import com.klsw.klswWebService.util.JsonUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ChatService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ChatService.class);
	
	@Resource
	private TbLiveRoomService roomService;
	
	@Resource
    private LiveRoomGroup liveRoomGroup;

	/**
	 * 
	 * @param ctx
	 * @param message
	 */
	public void chat(ChannelHandlerContext ctx, TCPMessage tcpMessage) {

		//获取当前通信用户信息
		UserInfo userInfo = (UserInfo) LiveTCPService.getUserInfo(ctx);
		
		//获取直播间Id
		Integer liveRoomId = userInfo.getLiveRoomId();
		
		//用户还未进入直播间
		if(liveRoomId == null) {
			String response = JsonUtils.encode(TCPMessage.ResInfo("response", "W", "chat", "您还没有进入直播间")) + System.getProperty("line.separator");
			ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
		}
		
		//获取直播间信息
		LiveRoom liveRoom = liveRoomGroup.get(liveRoomId);
		
		//直播间不存在
		if(liveRoom == null) {
			String response = JsonUtils.encode(TCPMessage.ResInfo("response", "W", "chat", "这是一个测试")) + System.getProperty("line.separator");
			ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
		}
		
		//获取聊天信息
		String message = (String) tcpMessage.getData().getInfo().get("message");
		
		//发送给所有直播间用户
		String response = JsonUtils.encode(TCPMessage.ResInfo("response", "S", "chat", message)) + System.getProperty("line.separator");
		liveRoom.getChannels().writeAndFlush(Unpooled.copiedBuffer(
				response.getBytes()));
//		ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
		
		LOGGER.info(message);
	}
}
