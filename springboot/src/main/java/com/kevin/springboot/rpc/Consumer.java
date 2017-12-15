package com.kevin.springboot.rpc;

import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/12/5
 * Time: 14:17
 */
public class Consumer {

    @Test
    public void testRpc() throws NoSuchMethodException, IOException, ClassNotFoundException {
        String interfaceName = SayHelloService.class.getName();
        Method method = SayHelloService.class.getMethod("sayHello", String.class);

        //需要传递到远端的参数
        Object[] arguments = {"hello"};

        Socket socket = new Socket("127.0.0.1",1234);
        //将方法名和参数传递到远端
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        output.writeUTF(interfaceName);//接口方法
        output.writeUTF(method.getName());//方法名称
        output.writeObject(method.getParameterTypes());
        output.writeObject(arguments);
        //从远端读取方法执行结果
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        Object result = input.readObject();
        System.out.println(result);
    }
}
