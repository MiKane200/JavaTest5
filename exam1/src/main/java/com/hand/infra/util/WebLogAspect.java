package com.hand.infra.util;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Order(5)
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //用于处理计算before和after的消耗时间，因为涉及到同步问题所以这里使用本地线程来搞
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.hand.api.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //开始时间
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("URL:" + request.getRequestURL().toString());
        logger.info("HTTP_METHOD:" + request.getMethod());
        logger.info("IP:" + request.getRemoteAddr());
        logger.info("CLASS_MEHTOE:" + joinPoint.getSignature().getDeclaringTypeName());
        logger.info("ARGS:" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret){
        logger.info("RESPONSE：" + ret);
        //结束时间
        logger.info("costTime:" + (System.currentTimeMillis() - startTime.get()) + "ms");
        startTime.remove();
    }
}
