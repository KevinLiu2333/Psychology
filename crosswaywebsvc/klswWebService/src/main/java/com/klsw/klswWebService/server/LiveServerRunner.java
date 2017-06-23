package com.klsw.klswWebService.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * Created by liukun on 2017/3/9.
 * tcp ip服务器
 */
@Component
public class LiveServerRunner implements CommandLineRunner {

    @Resource
    private LiveServerHandler serverHandler;

    @Resource
    private ExecutorService executorService;
    @Value("${tcp.server.port}")
    private int port;

    private final static Logger LOGGER = LoggerFactory.getLogger(LiveServerRunner.class);

    public void bind(int port) throws Exception {
        //配置服务端的NIO线程组,包含了一组Nio线程(Reactor线程组)
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //netty用于启动NIO服务端的辅助启动类,降低开发复杂度
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG, 1024)
                    //IO事件处理类
                    .childHandler(new ChildChannelHandler());
            //绑定端口,同步等待成功
            ChannelFuture f = b.bind(port).sync();
            //进行阻塞,等待服务端链路关闭之后main函数退出
            LOGGER.info("直播服务器开启,端口为" + port);
            f.channel().closeFuture().sync();
        } finally {
            //优雅退出,释放线程资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        protected void initChannel(SocketChannel arg0) throws Exception {
            //解决粘包问题，以换行符为结尾解包
            arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
            //提前将收到的消息解成字符串，方便后续操作
            arg0.pipeline().addLast(new StringDecoder());
            arg0.pipeline().addLast(serverHandler);
        }
    }

    @Override
    public void run(String... strings) throws Exception {
        executorService.execute(() -> {
            try {
                bind(port);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }

        });

    }
}
