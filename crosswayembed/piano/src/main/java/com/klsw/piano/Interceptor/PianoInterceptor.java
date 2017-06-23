package com.klsw.piano.Interceptor;

import com.klsw.piano.service.TbSerialnumbersService;
import com.klsw.pianoCommon.api.model.TbSerialnumbers;
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
public class PianoInterceptor implements HandlerInterceptor {

    @Resource
    TbSerialnumbersService tbSerialnumbersService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setContentType("text/plain;charset=utf-8");
        String uid = request.getParameter("Uid");
        TbSerialnumbers tbSerialnumbers = tbSerialnumbersService.selectByPrimaryKey(uid.trim());
        if (tbSerialnumbers == null) {
            response.getWriter().println("uid验证错误");
            return false;
        }
        return true;
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
