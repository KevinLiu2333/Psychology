package com.kevin.poi;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/10
 * Time: 10:34
 */
public class Decorator extends A {

    private A a;

    public Decorator(A a) {
        this.a = a;
    }

    public void say() {
       this.a.say();
    }
}
