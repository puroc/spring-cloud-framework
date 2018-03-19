package com.emrubik.springcloud.domain.to.base;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseReq<T> {
    private List<T> payloads = new ArrayList<T>();
    public void setPayLoad(T payload){
        payloads.add(payload);
    }
}
