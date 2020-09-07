package com.hynet.mall.exception;

import com.hynet.mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @progrm:
 * @description:将Exception和MallException两类异常进行拦截，并转为ApiRestResponse对象返回
 * @auther:
 * @create:
 */

@ControllerAdvice //拦截异常
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //1. 拦截Exception异常
    @ExceptionHandler(Exception.class) //说明处理的异常类型为Exception.class
    @ResponseBody //返回信息转出Json对象
    public ApiRestResponse handleException(Exception e){
        logger.error("Defualt Exception:",e);
        return ApiRestResponse.error(MallErrorEnum.SYSTEN_ERROR);
    }

    //2.拦截业务方面抛出的异常
    @ExceptionHandler(MallException.class)
    @ResponseBody
    public ApiRestResponse handleMallException(MallException e) {
        logger.error("Mall Exception: ",e);
        return  ApiRestResponse.error(e.getCode(),e.getMessage());
    }
}
