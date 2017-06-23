package com.klsw.klswWebService.server;

import com.klsw.klswWebService.util.AliyunUtils;
import com.klsw.klswWebService.util.JsonUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConvertService {



    private final static Logger LOGGER = LoggerFactory.getLogger(ConvertService.class);

    @Resource
    private LiveRoomGroup liveRoomGroup;

    /**
     * @param ctx
     * @param tcpMessage
     */
    public void convert(ChannelHandlerContext ctx, TCPMessage tcpMessage) {

        //获取当前通信用户信息
        UserInfo userInfo = LiveTCPService.getUserInfo(ctx);

        //获取直播间Id
        Integer liveRoomId = userInfo.getLiveRoomId();

        //用户未进入直播间
        if (liveRoomId == null) {
            String response = JsonUtils.encode(TCPMessage.ResInfo("response", "W", "convert", "您还没有进入直播间")) + System.getProperty("line.separator");
            ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
        }

        //获取直播间信息
        LiveRoom liveRoom = liveRoomGroup.get(liveRoomId);

        //直播间不存在
        if (liveRoom == null) {
            String response = JsonUtils.encode(TCPMessage.ResInfo("response", "W", "convert", "直播间不存在")) + System.getProperty("line.separator");
            ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
        }

        //获取传入的流名称
        String streamName = tcpMessage.getData().getInfo().get("streamname");
        
        //获取老师名称
        assert liveRoom != null;
        String teacherName = liveRoom.getTeachername();
        
        //设置拉流地址
        String pullStreamPath = AliyunUtils.getPullStreamPath(streamName);
        
        //设置当前直播间的拉流名称
        liveRoom.setCurrentStreamPath(streamName);
        
        //判断是否为老师
        if(streamName.equals(teacherName)) {
        	streamName = "";
        	pullStreamPath = "";
        }
        
        //设置响应参数
        Map<String, String> map = new HashMap<>();
        map.put("pullstreampath", pullStreamPath);
        map.put("convertor", streamName);
        map.put("status", "S");
        
        String response = JsonUtils.encode(TCPMessage.ResInfo("response", "convert", map)) + System.getProperty("line.separator");
        liveRoom.getChannels().writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));

        LOGGER.info("当前拉流地址为：" + pullStreamPath);
    }
}
