package com.emrubik.springcloud.auth.common.msg;

import com.emrubik.springcloud.auth.common.constants.CommonConstants;
import com.emrubik.springcloud.common.msg.BaseResponse;


public class TokenForbiddenResponse  extends BaseResponse {
    public TokenForbiddenResponse(String message) {
        super(CommonConstants.EX_TOKEN_FORBIDDEN_CODE, message);
    }
}
