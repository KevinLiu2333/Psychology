//package com.klsw.game;
//
//import com.alibaba.fastjson.JSONObject;
//import com.klsw.klswWebService.util.JsonUtils;
//import org.apache.mina.core.buffer.IoBuffer;
//import org.apache.mina.core.session.AttributeKey;
//import org.apache.mina.core.session.IdleStatus;
//import org.apache.mina.core.session.IoSession;
//import org.apache.mina.core.write.DefaultWriteRequest;
//import org.apache.mina.filter.keepalive.KeepAliveFilter;
//import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
//import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * Created by liukun on 2017/4/11.
// * 心跳机制
// */
//public class HachiKeepAliveFilterInMina extends KeepAliveFilter {
//    private static final Logger LOGGER = LoggerFactory.getLogger(KeepAliveFilter.class);
//    private static final int INTERVAL = 5;//in seconds
//    private static final int TIMEOUT = 30; //in seconds
//    private final AttributeKey WAITING_FOR_RESPONSE = new AttributeKey(getClass(), "waitingForResponse");
//    private IdleStatus interestedIdleStatus = getInterestedIdleStatus();
//
//    private void resetStatus(IoSession session) {
//        session.getConfig().setReaderIdleTime(0);
//        session.getConfig().setWriterIdleTime(0);
//        session.getConfig().setIdleTime(interestedIdleStatus, getRequestInterval());
//        session.removeAttribute(WAITING_FOR_RESPONSE);
//    }
//
//    private void markStatus(IoSession session) {
//        session.getConfig().setIdleTime(interestedIdleStatus, 0);
//        session.getConfig().setReaderIdleTime(getRequestTimeout());
//        session.setAttribute(WAITING_FOR_RESPONSE);
//    }
//
//    private void handlePingTimeout(IoSession session) throws Exception {
//        resetStatus(session);
//        KeepAliveRequestTimeoutHandler handler = getRequestTimeoutHandler();
//        if (handler == KeepAliveRequestTimeoutHandler.DEAF_SPEAKER) {
//            return;
//        }
//
//        handler.keepAliveRequestTimedOut(this, session);
//    }
//
//    @Override
//    public void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status) throws Exception {
//        KeepAliveMessageFactoryImpl impl = (KeepAliveMessageFactoryImpl) getMessageFactory();
//        Object pingMessage = impl.getRequest(session);
//        int timeout;
//        if (!session.containsAttribute(WAITING_FOR_RESPONSE)) {
//            if (pingMessage != null) {
//                session.setAttribute("timeout",0);
//                nextFilter.filterWrite(session, new DefaultWriteRequest(pingMessage));
//                // If policy is OFF, there's no need to wait for
//                // the response.
//                if (getRequestTimeoutHandler() != KeepAliveRequestTimeoutHandler.DEAF_SPEAKER) {
//                    markStatus(session);
//                } else {
//                    resetStatus(session);
//                }
//            }
//        } else {
//            if (!session.containsAttribute("timeout")) {
//                nextFilter.filterWrite(session, new DefaultWriteRequest(pingMessage));
//                session.setAttribute("timeout", 1);
//            } else if ((timeout = (int) session.getAttribute("timeout")) < 3) {
//                nextFilter.filterWrite(session, new DefaultWriteRequest(pingMessage));
//                session.setAttribute("timeout", timeout + 1);
//            } else {
//                handlePingTimeout(session);
//            }
//
//        }
//    }
//
//    /**
//     * IdleStatus 该过滤器所关注的空闲状态，默认认为读取空闲。 即当读取通道空闲的时候发送心跳包
//     *
//     * @param messageFactory 信息工厂
//     */
//    public HachiKeepAliveFilterInMina(KeepAliveMessageFactory messageFactory) {
//        //BOTH_IDLE,及表明如果当前连接的读写通道都空闲的时候在指定的时间间隔getRequestInterval后发送出发Idle事件
//        super(messageFactory, IdleStatus.READER_IDLE, new ExceptionHandler(), INTERVAL, TIMEOUT);
//    }
//
//    public HachiKeepAliveFilterInMina() {
//        super(new KeepAliveMessageFactoryImpl(), IdleStatus.READER_IDLE, new ExceptionHandler(), INTERVAL, TIMEOUT);
//        this.setForwardEvent(false); //此消息不会继续传递，不会被业务层看见
//        System.out.println("interval:" + getRequestInterval());
//    }
//
//}
//
///**
// * 心跳包请求后超时无反馈情况下的处理机制  默认为CLOSE  即关闭连接
// */
//class ExceptionHandler implements KeepAliveRequestTimeoutHandler {
//    private static final Logger LOGGER = LoggerFactory.getLogger(KeepAliveRequestTimeoutHandler.class);
//
//    public void keepAliveRequestTimedOut(KeepAliveFilter filter, IoSession session) throws Exception {
//        LOGGER.error("ip: " + session.getRemoteAddress() + " Connection lost, session will be closed");
//        session.close(true);
//    }
//}
//
///**
// * 该实例引用用于判断接受与发送的包是否是心跳包，以及心跳请求包的实现
// */
//class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
//    //    private static final byte int_req = -1;
////    private static final byte int_rep = -2;
////    private static final IoBuffer KAMSG_REP = IoBuffer.wrap(new byte[]{int_rep});
//    public Object getRequest(IoSession session) {
//        //发心跳包
//        TcpMsg msg = new TcpMsg();
//        msg.setService("heart_beat_c");
//        msg.setAck(false);
//        msg.setPacketID(String.valueOf(System.currentTimeMillis()));
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("ip", session.getRemoteAddress().toString());
//        msg.setData(jsonObject);
//        IoBuffer buf = IoBuffer.wrap(JsonUtils.encode(msg).getBytes());
//        return buf.duplicate();
//    }
//
//    public Object getResponse(IoSession session, Object request) {
//        return null;
//    }
//
//    //是否是心跳请求包，不管是对方的还是自己的
//    public boolean isRequest(IoSession session, Object message) {
//        return false;
//    }
//
//    //判断是否是心跳响应包，不管是对方的，还是自己的
//    //检查这个包是否是客户端回复的心跳包。如果是，就调用resetStatus(session);方法重置数据，主要清除超时的次数信息。
//    public boolean isResponse(IoSession session, Object message) {
//        IoBuffer realMessage = (IoBuffer) message;
//        try {
//            String body = realMessage.getString(EchoServerHandler.decoder);
//            System.out.println("receive" + body);
//            TcpMsg msg = JsonUtils.decode(body, TcpMsg.class);
//            return "heart_beat_p".equals(msg.getService());
//        } catch (Exception e) {
//            return false;
//        } finally {
//            realMessage.rewind();
//            realMessage.rewind();
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
