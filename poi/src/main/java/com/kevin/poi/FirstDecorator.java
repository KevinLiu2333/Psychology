package com.kevin.poi;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/10
 * Time: 13:23
 */
public class FirstDecorator extends Decorator {

    public FirstDecorator(A a) {
        super(a);
    }

    private void sayHello(){
        System.out.println("Hello");
    }

    @Override
    public void say() {
        this.sayHello();
        super.say();
    }
}
