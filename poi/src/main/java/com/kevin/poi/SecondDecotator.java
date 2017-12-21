package com.kevin.poi;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/10
 * Time: 13:25
 */
public class SecondDecotator extends Decorator {

    public SecondDecotator(A a) {
        super(a);
    }

    public void sayHi(){
        System.out.println("Hi");
    }

    @Override
    public void say() {
        sayHi();
        super.say();
    }
}
