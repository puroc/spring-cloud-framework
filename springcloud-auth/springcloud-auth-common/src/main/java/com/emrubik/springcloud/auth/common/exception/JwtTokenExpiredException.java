package com.emrubik.springcloud.auth.common.exception;


public class JwtTokenExpiredException extends Exception {
    public JwtTokenExpiredException(String s) {
        super(s);
    }
}
