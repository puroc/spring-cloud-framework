package com.emrubik.springcloud.common.exception;

public class JwtExpireException extends Exception {

    public JwtExpireException() {
        super();
    }

    public JwtExpireException(String msg) {
        super(msg);
    }

    public JwtExpireException(String msg, Throwable t) {
        super(msg, t);
    }
}
