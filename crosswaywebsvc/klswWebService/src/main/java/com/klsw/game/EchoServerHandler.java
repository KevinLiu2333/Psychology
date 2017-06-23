package com.klsw.game;

import com.klsw.klswWebService.util.JsonUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by liukun on 2017/4/8.
 * handler
 */
@Component
public class EchoServerHandler extends IoHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServerHandler.class);

    @Resource
    private TcpService tcpService;

    @Resource
    private NoteBattleSessions sessions;

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
//        IoBuffer buffer = (IoBuffer) message;
//        String body = buffer.getString(decoder);
        String body = message.toString();
        TcpMsg msg = null;
        try {
            msg = JsonUtils.decode(body, TcpMsg.class);
        } catch (Exception ignored) {
        }
        if (msg == null || !tcpService.checkSession(session, msg)) {
            return;
        }
        tcpService.execute(session, msg);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        LOGGER.info(session.getRemoteAddress().toString() + "connect");
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        LOGGER.info(session.getId() + "closed");
        sessions.getSessions().remove(session);
        sessions.getMatches().remove(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        LOGGER.info(session.getRemoteAddress() + cause.getLocalizedMessage());
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {

    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
    }
}













