package demo.localstring;

import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.Param;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/24 0024
 * Time: 15:45
 */
public class LocalString {

    public void changeLocal(@Param("lang") String lang) {
//        切换程序默认语言
        Mvcs.setDefaultLocalizationKey(lang);
    }
}
