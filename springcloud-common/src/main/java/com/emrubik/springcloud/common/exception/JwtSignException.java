package com.emrubik.springcloud.common.exception;

public class JwtSignException extends Exception {

    public JwtSignException() {
        super();
    }

    public JwtSignException(String msg) {
        super(msg);
    }

    public JwtSignException(String msg, Throwable t) {
        super(msg, t);
    }
}
