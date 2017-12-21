import org.junit.Test;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/18
 * Time: 17:32
 */
public class Test1 {

    @Test
    public void test(){
        StaticBlockTest sbt = new StaticBlockTest();
        System.out.println(sbt.format(new Date()));
    }
}
