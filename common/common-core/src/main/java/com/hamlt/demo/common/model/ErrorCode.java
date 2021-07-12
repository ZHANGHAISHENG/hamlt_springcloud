package com.hamlt.demo.common.model;


public class ErrorCode {

    public int code;

    public String message;

    public ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通用错误码
     */
    public static final ErrorCode SUCCESS = new ErrorCode(0, "成功");

    public static final ErrorCode PARAM_ERROR = new ErrorCode(17, "参数错误");

    public static final ErrorCode SYS_BUSY = new ErrorCode(50, "服务繁忙,请稍后重试");

    public static final ErrorCode Distributed_Lock_Error = new ErrorCode(61, "分布式锁异常");

    public static class AuthCode {

    }


}
