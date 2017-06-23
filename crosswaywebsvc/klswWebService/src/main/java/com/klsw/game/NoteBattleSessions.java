package com.klsw.game;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by liukun on 2017/5/6.
 * 音符大作战
 */
@Component
public class NoteBattleSessions {
    //记录所有会话
    private List<IoSession> sessions = new ArrayList<>();
    //匹配中的会话
    private ConcurrentLinkedQueue<IoSession> matches = new ConcurrentLinkedQueue<>();

    public List<IoSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<IoSession> sessions) {
        this.sessions = sessions;
    }

    public ConcurrentLinkedQueue<IoSession> getMatches() {
        return matches;
    }

    public void setMatches(ConcurrentLinkedQueue<IoSession> matches) {
        this.matches = matches;
    }
}


