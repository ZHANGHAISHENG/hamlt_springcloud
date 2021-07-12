package com.hamlt.demo.common.exception;


import com.hamlt.demo.common.model.ErrorCode;

/**
 * 分布式锁异常
 *
 */
public class LockException extends ConmmonsException {
    private static final long serialVersionUID = 1L;

    public LockException() {
        super(ErrorCode.Distributed_Lock_Error.code, ErrorCode.Distributed_Lock_Error.message);

    }

    public LockException(String message) {
        super(ErrorCode.Distributed_Lock_Error.code, message);
    }
}
