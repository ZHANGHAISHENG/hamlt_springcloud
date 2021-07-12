package com.hamlt.demo.zk.snowflake.exception;

public class CheckLastTimeException extends RuntimeException {
    public CheckLastTimeException(String message){
        super(message);
    }
}
