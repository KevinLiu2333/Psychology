package com.klsw.klswWebService.interceptor;

import com.klsw.apiCommon.api.model.Ret;
import com.klsw.klswWebService.service.TbCwkService;
import com.klsw.klswWebService.util.JsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ticket验证拦截器
 *
 * @author liukun
 */
@Component("userAuthInterceptor")
public class UserAuthInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private TbCwkService tbCwkService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        LoginRequired access = method.getAnnotation(LoginRequired.class);
//        if (access == null) {
//            // 如果注解为null, 说明不需要拦截, 直接放过
//            return true;
//        } else {
        response.setContentType("text/plain;charset=utf-8");
        try {
            Ret ret = tbCwkService.checkTicket(request);
            if ("S".equals(ret.getStatus())) {
                return true;
            }
            Ret ret1 = Ret.warn("ticket验证错误");
            response.getWriter().print(JsonUtils.encode(ret1));
        } catch (Exception e) {
            response.sendRedirect("/ticketFail");
            e.printStackTrace();
            return false;

        }
        return false;
//        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}
