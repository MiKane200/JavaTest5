package com.hand.infra.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(10)
public class WebLogSlowOrderAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //用于处理计算before和after的消耗时间，因为涉及到同步问题所以这里使用本地线程来搞
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.hand.api.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //开始时间
        startTime.set(System.currentTimeMillis());
        logger.info("slowOrderAspectStarting");
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret){
        logger.info("SlowOrderAspect RESPONSE：" + ret);
        logger.info("slowOrderAspectEnd");
        startTime.remove();
    }
}
