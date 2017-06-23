package com.klsw.game;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liukun on 2017/3/3.
 * 服务器
 */
@Component
public class ServerRunner implements CommandLineRunner {
    private final static Logger logger = LoggerFactory.getLogger(ServerRunner.class);

    @Resource
    private EchoServerHandler serverHandler;

    @Resource
    private ExecutorService executorService;
    //端口配置
    @Value("${game.port}")
    private Integer port;

    @Override
    public void run(String... args) throws Exception {
        executorService.execute(()->{
            //Acceptor
            NioSocketAcceptor acceptor = new NioSocketAcceptor();
            //提升整体性能
            acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
            acceptor.getFilterChain().addLast(
                    "codec",
                    new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                            "\n",
                            "\n")));
//        acceptor.getFilterChain().addLast("keep-alive", new HachiKeepAliveFilterInMina());
            // 设置MINA2的IoHandler实现类
            acceptor.setHandler(serverHandler);
            // 设置会话超时时间（单位：毫秒），不设置则默认是10秒，请按需设置
//        acceptor.setSessionRecycler(new ExpiringSessionRecycler(10 * 1000));
            // ** TCP通信配置
            SocketSessionConfig sessionConfig = acceptor.getSessionConfig();
            sessionConfig.setReuseAddress(true);
            // 设置输入缓冲区的大小，压力测试表明：调整到2048后性能反而降低
            sessionConfig.setReceiveBufferSize(1024);
            // ** TCP服务端开始侦听
            try {
                acceptor.bind(new InetSocketAddress(port));
                logger.info("游戏TCP服务器正在端口 29999 上监听中...");
            } catch (IOException e) {
                logger.error(e.getMessage());
            }

        });

    }
}
