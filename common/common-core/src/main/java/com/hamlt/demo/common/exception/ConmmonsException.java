package com.hamlt.demo.common.exception;

import com.hamlt.demo.common.model.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConmmonsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected int code;
    protected String message;
    protected Object data;

    public ConmmonsException() {
        this.code = ErrorCode.SYS_BUSY.code;
        this.message = ErrorCode.SYS_BUSY.message;
    }

    public ConmmonsException(String message) {
        super(message);
        this.code = ErrorCode.SYS_BUSY.code;
        this.message = message;
    }

    public ConmmonsException(ErrorCode leagueCode) {
        super(leagueCode.message);
        this.code = leagueCode.code;
        this.message = leagueCode.message;
    }

    public ConmmonsException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ConmmonsException(int code, String message, Object data) {
        super(message);
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ConmmonsException(String message, Throwable e) {
        super(message, e);
        this.code = ErrorCode.SYS_BUSY.code;
        this.message = message;
    }

}
