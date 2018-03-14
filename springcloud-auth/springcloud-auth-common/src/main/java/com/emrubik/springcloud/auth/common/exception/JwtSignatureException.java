package com.emrubik.springcloud.auth.common.exception;


public class JwtSignatureException extends Exception {
    public JwtSignatureException(String s) {
        super(s);
    }
}
