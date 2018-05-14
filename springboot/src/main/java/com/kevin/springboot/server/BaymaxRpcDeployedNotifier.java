package com.kevin.springboot.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: qxb-810
 * Date: 2018/5/14
 * Time: 10:19
 */
@Component
public class BaymaxRpcDeployedNotifier implements SmartApplicationListener {
    private final static int BACHELOR = 110;

    private ApplicationContext applicationContext;

    private RpcServer rpcServer;

    public BaymaxRpcDeployedNotifier(ApplicationContext applicationContext, RpcServer rpcServer) {
        this.applicationContext = applicationContext;
        this.rpcServer = rpcServer;
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == ContextRefreshedEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return ApplicationContext.class.isAssignableFrom(aClass);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        rpcServer.start();
    }

    @Override
    public int getOrder() {
        return BACHELOR;
    }
}
