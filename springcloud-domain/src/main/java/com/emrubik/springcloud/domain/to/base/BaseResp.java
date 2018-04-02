package com.emrubik.springcloud.domain.to.base;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseResp<T> {
    public static final String RESULT_SUCCESS = "1";
    public static final String RESULT_FAILED = "0";
//    10开头的为机构相关结果码
    public static final String EXIST_SON_ORG = "10001";

    private String resultCode = RESULT_SUCCESS;
    private String message;
    private List<T> payloads = new ArrayList<T>();

    public void setPayLoad(T payload) {
        payloads.add(payload);
    }
}
