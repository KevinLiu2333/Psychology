package com.kevin.springboot.rpc;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/12/5
 * Time: 14:15
 */
public class SayHelloServiceImpl implements SayHelloService {
    @Override
    public String sayHello(String helloArg) {
        if ("hello".equals(helloArg)) {
            return "hello";
        } else {
            return "bye bye";
        }
    }
}
