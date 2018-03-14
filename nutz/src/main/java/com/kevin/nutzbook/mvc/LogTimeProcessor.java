package com.kevin.nutzbook.mvc;

import org.nutz.lang.Stopwatch;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.impl.processor.AbstractProcessor;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/13
 * Time: 15:40
 */
public class LogTimeProcessor extends AbstractProcessor {

    private static final Log log = Logs.get();

    @Override
    public void process(ActionContext actionContext) throws Throwable {
        Stopwatch sw = Stopwatch.begin();
        try {
            doNext(actionContext);
        }finally {
            sw.stop();
            if (log.isDebugEnabled()){
                HttpServletRequest request = actionContext.getRequest();
                log.debugf("[%4s]URI=%s %sms", request.getMethod(), request.getRequestURI(), sw.getDuration());
            }

        }
    }
}













