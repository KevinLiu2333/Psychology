package JSONTest;

import com.klsw.crosswaylive.server.TCPData;
import com.klsw.crosswaylive.server.TCPMessage;
import com.klsw.crosswaylive.server.constants.Types;
import org.junit.Test;

/**
 * Created by liukun on 2017/3/24.
 * json测试
 */
public class JSONTest {

    @Test
    public void test(){
        TCPMessage message = new TCPMessage();
        @SuppressWarnings("unused")
		TCPData data = new TCPData();

        message.setService("work");
        System.out.println(message.getData().getType());
    }

    @Test
    public void enumTest(){
        System.out.println(Types.LIST.contains("startliving"));
    }


}





