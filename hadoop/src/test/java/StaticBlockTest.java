import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/8/18
 * Time: 17:30
 */
public class StaticBlockTest {

    private static SimpleDateFormat sdf;

    static {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    public String format(Date date){
        return sdf.format(date);
    }
}
