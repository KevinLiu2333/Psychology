package com.klsw.game;

import com.alibaba.fastjson.JSONObject;
import com.klsw.apiCommon.api.mapper.TbGamenotebattleMapper;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbGamenotebattle;
import com.klsw.klswWebService.service.NoteBattleService;
import com.klsw.klswWebService.service.TbCwkService;
import com.klsw.klswWebService.util.JsonUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by liukun on 2017/4/13.
 * tcp服务
 */
@Service
public class TcpService {
    private static Logger logger = LoggerFactory.getLogger(TcpService.class);
    @Resource
    private TbCwkService tbCwkService;

    @Resource
    private NoteBattleService noteBattleService;

    @Resource
    private NoteBattleSessions sessions;

    public void execute(IoSession session, TcpMsg msg) {
        switch (msg.getService()) {
            //建立连接
            case "connect":
                connect(session, msg);
                break;
            //开始匹配
            case "match":
                match(session, msg);
                break;
            //取消匹配
            case "matchCancel":
                matchCancel(session, msg);
                break;
            //游戏开始报文
            case "gameStart":
                gameStart(session, msg);
                break;
            case "gameStatus":
                gameStatus(session, msg);
                break;
            case "gameEnd":
                gameEnd(session, msg);
                break;
            case "gameProp":
                gameProp(session, msg);
                break;
            default:
                break;
        }
    }


    public void match(IoSession session, TcpMsg msg) {
        //匹配中的会话
        ConcurrentLinkedQueue<IoSession> matchingSessions = sessions.getMatches();
        logger.info("当前匹配中人数:" + matchingSessions.size());
        //如果匹配中的和会话长度为0,加入此条

        if (matchingSessions.size() == 0) {
            session.setAttribute("status", "matching");
            matchingSessions.offer(session);
            TcpMsg res = new TcpMsg("matching", false, String.valueOf(System.currentTimeMillis()), null);
            res(session, res);
        } else if (matchingSessions.contains(session)) {
            TcpMsg res = new TcpMsg("matching", false, String.valueOf(System.currentTimeMillis()), null);
            res(session, res);
        } else {
            //成功匹配
            //记录匹配信息
            IoSession session2 = matchingSessions.poll();
            Iterator<IoSession> iterator = matchingSessions.iterator();
            System.out.println("当前id" + session.getId());
            while (iterator.hasNext()) {
                System.out.println("队列id" + iterator.next().getId());
            }
            //记录状态
            session2.setAttribute("matchSession", session);
            session2.setAttribute("status", "matchSuccess");
            session.setAttribute("matchSession", session2);
            session.setAttribute("status", "matchSuccess");
            //随机生成歌曲号
            int songId = new Random().nextInt(10) + 1;
            //当前用户信息
            JSONObject userInfo = (JSONObject) session.getAttribute("userInfo");
            //匹配到的用户信息
            JSONObject userInfo2 = (JSONObject) session2.getAttribute("userInfo");
            userInfo.put("songId", songId);
            userInfo2.put("songId", songId);
            TcpMsg tcpMsg = new TcpMsg("matchSuccess", false, String.valueOf(System.currentTimeMillis()), userInfo);
            //发送匹配信息一
            res(session2, tcpMsg);
            tcpMsg = new TcpMsg("matchSuccess", false, String.valueOf(System.currentTimeMillis()), userInfo2);
            System.out.println("当前匹配中人数:" + matchingSessions.size());
            res(session, tcpMsg);
        }
    }

    /**
     * 游戏通信数据
     *
     * @param session 会话
     * @param msg     信息
     */
    public void gameStatus(IoSession session, TcpMsg msg) {
        if (session.containsAttribute("matchSession")) {
            IoSession matchSession = (IoSession) session.getAttribute("matchSession");
            res(matchSession, msg);
        }
    }

    /**
     * 游戏结束
     *
     * @param session 会话
     * @param msg     信息
     */
    public void gameEnd(IoSession session, TcpMsg msg) {
        //测试需要
        res(session, msg);
        if (session.containsAttribute("matchSession")) {
            IoSession matchSession = (IoSession) session.getAttribute("matchSession");
            res(matchSession, msg);
            JSONObject json = msg.getData();
            Integer score = json.getInteger("score");
            JSONObject userInfo = (JSONObject) session.getAttribute("userInfo");
            Integer songId = userInfo.getInteger("songId");
            Integer cwkId = userInfo.getInteger("Id");
            userInfo.remove("songId");
            session.setAttribute("useInfo", userInfo);
            TbGamenotebattle notebattle = new TbGamenotebattle();
            notebattle.setCwkid(cwkId);
            notebattle.setSongid(songId);
            TbGamenotebattleMapper mapper = noteBattleService.getTbGamenotebattleMapper();
            notebattle = mapper.selectOne(notebattle);
            if (notebattle == null) {
                notebattle = new TbGamenotebattle();
                notebattle.setSongid(songId);
                notebattle.setCwkid(cwkId);
                notebattle.setScore(score);
                notebattle.setAddtime(new Date());
                mapper.insert(notebattle);
            } else if (notebattle.getScore() < score) {
                notebattle.setAddtime(new Date());
                notebattle.setScore(score);
                mapper.updateByPrimaryKey(notebattle);
            }
            if (!"gameEnd".equals(matchSession.getAttribute("status"))) {
                session.setAttribute("status", "gameEnd");
            } else {
                session.setAttribute("status", "waiting");
                matchSession.getAttribute("status", "waiting");
            }

        }

    }

    /**
     * 取消匹配
     *
     * @param session 会话
     * @param msg     信息
     */
    public void matchCancel(IoSession session, TcpMsg msg) {
        sessions.getMatches().remove(session);
        session.removeAttribute("match");
        new GameTest().start();
    }

    public void gameStart(IoSession session, TcpMsg msg) {
        session.setAttribute("status", "gameStart");
    }

    public void gameProp(IoSession session, TcpMsg msg) {
//        测试需要
        res(session,msg);
        if (session.containsAttribute("matchSession")) {
            IoSession matchSession = (IoSession) session.getAttribute("matchSession");
            res(matchSession, msg);
        }
    }

    /**
     * @param session 开始连接
     * @param msg     信息
     */
    public void connect(IoSession session, TcpMsg msg) {
        TcpMsg res;
        if (sessions.getSessions().contains(session)) {
            logger.info("非正常链接");
            res = new TcpMsg("connect", false, String.valueOf(System.currentTimeMillis()), null);
            res(session, res);
            return;
        }
        JSONObject data = msg.getData();
        String name = data.getString("name");
        TbCwk tbCwk = tbCwkService.findByName(name);
        //成功登录,记录状态
        if (tbCwk != null) {
            sessions.getSessions().add(session);
            logger.info("当前链接数量:" + sessions.getSessions().size());
            session.setAttribute("connect", true);
            session.setAttribute("status", "wait");
            JSONObject userInfo = new JSONObject();
            userInfo.put("id", tbCwk.getId());
            userInfo.put("name", tbCwk.getNickname());
//            userInfo.put("favicon", tbCwk.getFfavicon());
            userInfo.put("avatar", (new Random()).nextInt(17) + 1);
            session.setAttribute("userInfo", userInfo);
            res = new TcpMsg("connect", false, String.valueOf(System.currentTimeMillis()), null);
            res(session, res);
        }
    }

    public void res(IoSession session, TcpMsg msg) {
        String strToClient = JsonUtils.encode(msg);
        logger.info("strToClient:" + strToClient);
        byte[] res = new byte[0];
        try {
            res = strToClient.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ignore) {
        }
        IoBuffer buf = IoBuffer.wrap(res);
        session.write(buf);
    }

    /**
     * @param session 会话
     * @param msg     信息
     * @return 检查结果
     */
    public boolean checkSession(IoSession session, TcpMsg msg) {
        return !(session.getAttribute("connect") == null && !"connect".equals(msg.getService()));
    }

    /**
     * 移除session
     *
     * @param session 会话
     */
    public void removeSession(IoSession session) {
        sessions.getSessions().remove(session);
        sessions.getMatches().remove(session);
    }
}




