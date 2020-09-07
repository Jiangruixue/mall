package com.hynet.mall.exception;

/*
 * @progrm:
 * @description: 接口错误枚举
 * @auther:
 * @create:
 */
public enum MallErrorEnum {

    //业务异常从10000开始
    NEED_USER_NAME(10001, "用户名不能为空"),
    NEED_USER_PASSWORD(10002,"密码不能为空"),
    PASSWORD_TOO_SHORT(10003,"登录密码太短，8位以上"),
    USER_EXIST(10004,"用户已存在,注册失败"),
    INSERT_DATABASE_FAIL(10005,"插入数据量失败,请稍后再试"),
    SIGNATURE_NOT_EMPTY(10006,"个性签名不能为空"),
    PASSWORD_ERROR(10006,"登录密码错误"),
    USER_NOLOGIN(10007,"用户未登录"),
    UPDATE_USER_INFO_ERROR(10008,"更新用户信息出现出错,请稍后再试"),
    NO_ADMIN_ROLE(10009,"当前用户不是管理员用户"),
    NO_NORMAL_ROLE(10010,"当前用户不是普通用户"),
    //系统异常从20000开始。
    SYSTEN_ERROR(20000,"系统出现异常，请稍后再试");

    //错误码
    Integer code;

    //错误信息。
    String msg;

    MallErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
