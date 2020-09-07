package com.hynet.mall.filter;

/*
 * @progrm:
 * @description: 通过AOP统一拦截Web请求&响应日志
 * @auther:
 * @create:
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    //创建log对象。
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    //指定拦截点,指定主要拦截的是controller中的相关信息
    @Pointcut("execution(public * com.hynet.mall.controller.*.*(..))")
    public void webLog(){

    }

    //指定此方法是处理拦截点(webLog())前的拦截操作
    //主要处理的逻辑: 收到请求，记录请求内容。
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //收集的信息主要包括请求信息+类的信息
        //joinPoint 是关于类的信息。
        //1. 获取请求信息
        ServletRequestAttributes attributes =  (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //2. 开始记录log信息
        //2.1 创建logFactory
        //记录请求相关信息
        logger.info("URL: " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD: " + request.getMethod());
        logger.info("IP: "+request.getRemoteAddr());

        //2.2记录类相关信息
        //类名
        logger.info("CLASS_INFO: "+ joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());

        //参数
        logger.info("ARGS: " + Arrays.toString(joinPoint.getArgs()));
    }

    //指定该方法是拦截点之后的相关处理
    @AfterReturning(returning = "res",pointcut = "webLog()") //指定返回的参数是res ,拦截的点是webLog()
    public void doAfterReturning(Object res) throws JsonProcessingException {
        //处理完请求，返回内容
        //ObjectMapper().writeValueAsString(res) 将对象转成json String
        logger.info("RESPONSE: "+ new ObjectMapper().writeValueAsString(res));
    }
}

/*
*   1. 创建过滤器类。
*   2. 指定拦截点。
*   3. 需要在拦截点的前和后分别拦截
*
* */