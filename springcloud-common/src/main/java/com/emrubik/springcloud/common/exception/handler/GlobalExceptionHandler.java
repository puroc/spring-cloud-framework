package com.emrubik.springcloud.common.exception.handler;

import com.emrubik.springcloud.domain.to.base.BaseResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;


@ControllerAdvice("com.emrubik.springcloud")
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity ExceptionHandler(HttpServletResponse response, Throwable t) {
        log.error(t.getMessage(),t);
        BaseResp resp = new BaseResp();
        resp.setResultCode(BaseResp.RESULT_FAILED);
        resp.setMessage(t.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidExceptionHandler(HttpServletResponse response, Throwable t) {
        BaseResp resp = new BaseResp();
        resp.setResultCode(BaseResp.RESULT_FAILED);
        resp.setMessage(t.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

}