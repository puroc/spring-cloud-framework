package com.emrubik.springcloud.domain.to.base;

import lombok.Data;

@Data
public class PageResp<T> extends BaseResp<T> {

    private int totalNum;
}
