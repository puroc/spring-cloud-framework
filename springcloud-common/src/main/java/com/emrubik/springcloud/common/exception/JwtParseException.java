package com.emrubik.springcloud.common.exception;

public class JwtParseException extends Exception {

    public JwtParseException() {
        super();
    }

    public JwtParseException(String msg) {
        super(msg);
    }

    public JwtParseException(String msg, Throwable t) {
        super(msg, t);
    }
}
