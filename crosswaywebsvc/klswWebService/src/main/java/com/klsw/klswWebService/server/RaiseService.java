package com.klsw.klswWebService.server;

import com.klsw.klswWebService.util.JsonUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RaiseService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(RaiseService.class);
	
	@Resource
	private LiveRoomGroup liveRoomGroup;
	
	/**
	 * 
	 * @param ctx
	 * @param message
	 */
	public void raise(ChannelHandlerContext ctx, TCPMessage message) {
		
		String response = null;
		
		//获取当前通信用户信息
		UserInfo userInfo = (UserInfo) LiveTCPService.getUserInfo(ctx);
		
		//获取直播间Id
		Integer liveRoomId = userInfo.getLiveRoomId();
		
		//用户未进入直播间
		if(liveRoomId == null) {
			response = JsonUtils.encode(TCPMessage.ResInfo("response", "W", "raise", "您还没有进入直播间")) + System.getProperty("line.separator");
			ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
		}
		
		//获取直播间信息
		LiveRoom liveRoom = liveRoomGroup.get(liveRoomId);
		
		//直播间不存在
		if(liveRoom == null) {
			response = JsonUtils.encode(TCPMessage.ResInfo("response", "W", "raise", "直播间不存在")) + System.getProperty("line.separator");
			ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
		}
		
//		//获取直播用户类型
//		String userType = userInfo.getAudtype();
//		
//		//普通用户不能发言
//		if(userType.equals("normal")) {
//			response = JsonUtils.encode(TCPMessage.ResInfo("response", "W", "raise", "普通用户不能发言"));
//		}
		
		response = JsonUtils.encode(TCPMessage.ResInfo("response", "S", "raise", userInfo.getUsername())) + System.getProperty("line.separator");
		liveRoom.getTeacher().writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
		
		LOGGER.info(response);
		LOGGER.info(userInfo.getUserId());
	}
}
