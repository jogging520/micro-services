package com.northbrain.user.model;

public class Constants {
    public final static String  USER_STATUS_ACTIVE                      =   "ACTIVE";   //用户状态：在用
    public final static String  AFFILIATION_STATUS_ACTIVE               =   "ACTIVE";   //归属状态：在用
    public final static String  AFFILIATION_TYPE_HOME                   =   "HOME";     //归属类型：隶属
    public final static String  AFFILIATION_TYPE_DEFAULT                =   "DEFAULT";  //归属类型：默认
    public final static String  AFFILIATION_TYPE_NORMAL                 =   "NORMAL";   //归属类型：普通

    public final static int     USER_LOGGING_TYPE_PASSWORD              =   0;          //用户名+密码登录方式
    public final static int     USER_LOGGING_TYPE_CAPTCHA               =   1;          //验证码登录方式


    public final static String  USER_HTTP_REQUEST_MAPPING               =   "/users";   //用户rest资源
    public final static String  USER_SPECIFIED_HTTP_REQUEST_MAPPING     =   "/users/{userId}";   //用户rest资源
}
