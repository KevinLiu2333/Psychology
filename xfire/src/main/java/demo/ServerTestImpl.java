package demo;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/31 0031
 * Time: 10:36
 */
public class ServerTestImpl implements ServerTest {

    public String say(String str) {
        System.out.println(str);
        return str;
    }
}
