package com.emrubik.springcloud.common.util;

import com.alibaba.fastjson.JSON;

public class JsonHelper {

    public static String toJson(Object obj){
        return JSON.toJSONString(obj);
    }

    public static <T> T toObj(String json, Class<T> clazz){
        return JSON.parseObject(json,clazz);
    }

}
