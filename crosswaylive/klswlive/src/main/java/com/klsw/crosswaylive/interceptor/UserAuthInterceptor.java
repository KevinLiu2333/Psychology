package com.klsw.crosswaylive.interceptor;

import com.klsw.common.live.model.Ret;
import com.klsw.crosswaylive.service.TbLiveUserService;
import com.klsw.crosswaylive.util.JsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ticket验证拦截器
 *
 * @author liukun
 */
@Component("userAuthInterceptor")
public class UserAuthInterceptor implements HandlerInterceptor {

    @Resource
    private TbLiveUserService liveUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setContentType("text/plain;charset=utf-8");
        Ret ret = liveUserService.checkTicket(request);
        try {
            if ("S".equals(ret.getStatus())) {
                return true;
            }
            ret = Ret.warn("ticket验证错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().print(JsonUtils.encode(ret));
        return false;

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
