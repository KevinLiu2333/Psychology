package demo.chain;

import org.nutz.mvc.*;
import org.nutz.mvc.impl.NutActionChain;
import org.nutz.mvc.impl.processor.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码方式配置ActionChain
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/21 0021
 * Time: 11:03
 */
public class MyActionChainMaker implements ActionChainMaker{


    @Override
    public ActionChain eval(NutConfig config, ActionInfo ai) {
        List<Processor> list = null;
        Processor error = null;
        try {
            //提醒:config可以获取ioc等信息，ai可以获取方法上的各种配置以及方法本身
            //正常处理的列表
            list = new ArrayList<>();
            list.add(new UpdateRequestAttributesProcessor());//设置base/msg等内置属性
            list.add(new EncodingProcessor());//设置编码信息
            list.add(new ModuleProcessor());//获取入口类对象,从ico或者直接new
            list.add(new ActionFiltersProcessor());//处理filters
            list.add(new AdaptorProcessor());//处理adapter
            list.add(new MethodInvokeProcessor());//执行入口方法
            list.add(new ViewProcessor());//入口方法渲染
            for(Processor p : list){
                    p.init(config,ai);
            }

            //最后是专门负责兜底的异常处理器,这个处理器可以认为是全局异常处理器,对应@Fail
            error = new FailProcessor();
            error.init(config,ai);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return new NutActionChain(list,error,ai);
    }
}
