package com.emrubik.springcloud.common.exception.handler;

import com.emrubik.springcloud.domain.to.base.BaseResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;


@ControllerAdvice("com.emrubik.springcloud")
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    public ResponseEntity ExceptionHandler(HttpServletResponse response, Throwable t) {
        logger.error(t.getMessage(),t);
        BaseResp resp = new BaseResp();
        resp.setResultCode(BaseResp.RESULT_FAILED);
        resp.setMessage(t.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }

}