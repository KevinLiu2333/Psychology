package com.kevin.springboot.rpc;

import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/12/5
 * Time: 14:26
 */
public class Provider {

    @Test
    public void provider() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String,Object> servicePool = new HashMap<>();
//        先将服务确定好，才能调用，不允许客户端自动添加服务
        servicePool.put(SayHelloService.class.getName(),new SayHelloServiceImpl());
        ServerSocket server = new ServerSocket(1234);
        while (true){
            Socket socket =server.accept();

            //读取服务信息
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String interfaceName = input.readUTF();//接口名称
            String methodName = input.readUTF();//方法名称
            Class<?>[] parameterTypes = (Class<?>[])input.readObject();//参数类型
            Object[] arguments = (Object[])input.readObject();//参数对象
            //执行调用
            Class serviceInterfaceClass = Class.forName(interfaceName);
            Object service = servicePool.get(interfaceName);
            Method method = serviceInterfaceClass.getMethod(methodName,parameterTypes);
            Object result = method.invoke(service,arguments);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(result);
        }

    }
}
