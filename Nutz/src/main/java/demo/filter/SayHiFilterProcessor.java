package demo.filter;

import org.apache.log4j.Logger;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.impl.processor.AbstractProcessor;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/24 0024
 * Time: 16:10
 */
public class SayHiFilterProcessor extends AbstractProcessor implements ActionFilter {
    private final static Logger logger = Logger.getLogger("das");

    @Override
    public View match(ActionContext actionContext) {
        logger.debug("match?maybe.");
        return null;
    }

    @Override
    public void process(ActionContext ac) throws Throwable {
        logger.debug("before doNext");
        doNext(ac);
        logger.debug("after doNext");
    }
//    顺序:match--process
}
