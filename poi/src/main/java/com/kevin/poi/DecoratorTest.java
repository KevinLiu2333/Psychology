package com.kevin.poi;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/10
 * Time: 13:27
 */
public class DecoratorTest {

    public static void main(String[] args) {
        A a ;
        a = new A();
        a = new FirstDecorator(a);
        a = new SecondDecotator(a);
        a.say();
    }
}
