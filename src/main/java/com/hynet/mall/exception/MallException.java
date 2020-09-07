package com.hynet.mall.exception;

/*
 * @progrm:
 * @description: 自定义统一异常类,在异常信息中包括code和msg信息。
 * @auther:
 * @create:
 */
public class MallException extends RuntimeException{

    private final Integer code;
    private final String message;

    public MallException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public MallException(MallErrorEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
