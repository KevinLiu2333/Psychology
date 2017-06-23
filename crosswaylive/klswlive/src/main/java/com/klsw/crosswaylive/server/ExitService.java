package com.klsw.crosswaylive.server;

import com.klsw.common.live.model.TbLiveroom;
import com.klsw.crosswaylive.service.TbLiveRoomService;
import com.klsw.crosswaylive.util.JsonUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExitService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExitService.class);

    @Resource
    private LiveRoomGroup liveRoomGroup;

    @Resource
    private TbLiveRoomService roomService;

    public void exitLiveRoom(ChannelHandlerContext ctx, TCPMessage tcpMessage) {

        String response;

        // 获取当前通信用户信息
        UserInfo userInfo = TCPService.getUserInfo(ctx);

        // 获取直播间Id
        Integer liveRoomId = userInfo.getLiveRoomId();

        // 未进入直播间
        if (liveRoomId == null) {
            response = JsonUtils.encode(TCPMessage.ResInfo("response", "W", "exit", "您还没有进入直播间"))
                    + System.getProperty("line.separator");
            ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
        }

        LiveRoom liveRoom = liveRoomGroup.get(liveRoomId);

        // 直播间不存在
        if (liveRoom == null) {
            response = JsonUtils.encode(TCPMessage.ResInfo("response", "W", "exit", "直播间不存在"))
                    + System.getProperty("line.separator");
            ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
        }

        // 获取需要退出直播的用户类型
        String userType = userInfo.getAudtype();

        LOGGER.info(userType);

        assert liveRoom != null;
        liveRoom.getChannels().remove(ctx.channel());
        liveRoom.setTotals(liveRoom.getTotals() - 1);

        // 直播交互者退出
        if (userType.equals("interact")) {
            Map<String, String> map = new HashMap<>();
            map.put("message", userInfo.getUsername());
            map.put("status", "S");

            if (liveRoom.getCurrentStreamPath().equals(userInfo.getUsername())) {
                map.put("action", "interact");

            } else {
                map.put("action", "");
            }

            liveRoom.getPrivateChannel().remove(ctx.channel());
            response = JsonUtils.encode(TCPMessage.ResInfo("response", "exit", map))
                    + System.getProperty("line.separator");
            liveRoom.getTeacher().writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
            ctx.close();
        }

        // 主播退出
        if (userType.equals("anchor")) {
            response = JsonUtils.encode(TCPMessage.ResInfo("response", "S", "exit", "anchor"))
                    + System.getProperty("line.separator");
            liveRoom.getChannels().writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
            try {
                // 更新直播间状态
                TbLiveroom tbLiveroom = roomService.selectByPrimaryKey(Integer.valueOf(userInfo.getUserId()));
                if (tbLiveroom != null) {
                    tbLiveroom.setStatus(2);
                    roomService.updateByPrimaryKey(tbLiveroom);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }

        ctx.close();
    }
}
