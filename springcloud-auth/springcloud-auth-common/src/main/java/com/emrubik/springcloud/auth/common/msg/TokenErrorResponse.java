package com.emrubik.springcloud.auth.common.msg;

import com.emrubik.springcloud.auth.common.constants.CommonConstants;
import com.emrubik.springcloud.common.msg.BaseResponse;

public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(CommonConstants.EX_TOKEN_ERROR_CODE, message);
    }
}
