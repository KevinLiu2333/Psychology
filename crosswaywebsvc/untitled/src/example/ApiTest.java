package example;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/28 0028
 * Time: 11:00
 */
public class ApiTest {
    @Test
    public void test1() {
        HelloWorld helloWorld = new HelloWorldService().getHelloWorldPort();
        String result = helloWorld.sayHelloWorldFrom("刘坤");
        System.out.println(result);
    }
}
