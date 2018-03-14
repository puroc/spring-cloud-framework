package com.emrubik.springcloud.auth.common.util;

import com.emrubik.springcloud.auth.common.constants.CommonConstants;
import com.emrubik.springcloud.auth.common.util.StringHelper;
import java.util.HashMap;
import java.util.Map;

public class BaseContextHandler {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getUserId() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_ID);
        return returnObjectValue(value);
    }

    public static String getUserName() {
        Object value = get(CommonConstants.CONTEXT_KEY_USERNAME);
        return returnObjectValue(value);
    }

    public static String getToken() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return StringHelper.getObjectValue(value);
    }

    public static void setToken(String token) {
        set(CommonConstants.CONTEXT_KEY_USER_TOKEN, token);
    }

    public static void setUserId(String userID) {
        set(CommonConstants.CONTEXT_KEY_USER_ID, userID);
    }

    public static void setUserName(String username) {
        set(CommonConstants.CONTEXT_KEY_USERNAME, username);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
