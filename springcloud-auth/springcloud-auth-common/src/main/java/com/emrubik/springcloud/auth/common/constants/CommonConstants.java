package com.emrubik.springcloud.auth.common.constants;


public class CommonConstants {
    public final static String RESOURCE_TYPE_MENU = "menu";
    public final static String RESOURCE_TYPE_BTN = "button";
    public static final Integer EX_TOKEN_ERROR_CODE = 40101;
    public static final Integer EX_USER_INVALID_CODE = 40102;
    public static final Integer EX_TOKEN_FORBIDDEN_CODE = 40301;
    public static final Integer EX_OTHER_CODE = 500;

    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_USER_NAME = "userName";
}
