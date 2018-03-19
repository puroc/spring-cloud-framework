package com.emrubik.springcloud.domain.to.base;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseResp<T> {
    public static final String RESULT_SUCCESS="1";
    public static final String RESULT_FAILED="0";
    private String resultCode;
    private String message;
    private List<T> payloads = new ArrayList<T>();
    public void setPayLoad(T payload){
        payloads.add(payload);
    }
}
