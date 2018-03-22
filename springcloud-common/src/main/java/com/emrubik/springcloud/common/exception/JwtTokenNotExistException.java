package com.emrubik.springcloud.common.exception;

public class JwtTokenNotExistException extends Exception {

    public JwtTokenNotExistException(){
        super();
    }

    public JwtTokenNotExistException(String msg) {
        super(msg);
    }

    public JwtTokenNotExistException(String msg, Throwable t) {
        super(msg, t);
    }
}
