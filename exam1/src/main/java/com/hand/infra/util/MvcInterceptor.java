package com.hand.infra.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MvcInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String code2 = (String) request.getAttribute("code");
        logger.info("perHandle-code2:"+code2);
        request.setAttribute("code2",code2);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        if(request.getAttribute("code") != null && request.getAttribute("code").equals(request.getAttribute("code2"))){
            request.setAttribute("codeRealCode2",request.getAttribute("code2"));
            logger.info("perHandle-codeRealCode2:"+request.getAttribute("code2"));
            request.removeAttribute("code");
            request.removeAttribute("code2");
        }else {
            logger.info("postHandle-codeRealCode2: empty value");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
