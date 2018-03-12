package com.emrubik.springcloud.auth.common.exception;


import com.emrubik.springcloud.auth.common.constants.CommonConstants;


public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
