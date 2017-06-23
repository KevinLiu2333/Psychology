package com.klsw.game;

import com.alibaba.fastjson.JSONObject;
import com.klsw.klswWebService.util.JsonUtils;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by liukun on 2017/5/15.
 * 游戏测试
 */
public class GameTest extends Thread {

    @Override
    public void run() {
        try {
            Socket s = new Socket("localhost", 29999);
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
            TcpMsg msg = new TcpMsg();
            msg.setAck(false);
            msg.setPacketID(String.valueOf(System.currentTimeMillis()));
            msg.setService("connect");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "13585149563");
            msg.setData(jsonObject);
            pw.print(JsonUtils.encode(msg)+"\n\n\n");
            pw.flush();
            Thread.sleep(200);
            msg.setPacketID(String.valueOf(System.currentTimeMillis()));
            msg.setService("match");
            msg.setData(null);
            pw.print(JsonUtils.encode(msg)+"\n\n\n");
            pw.flush();
            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
