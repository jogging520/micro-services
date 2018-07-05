package com.northbrain.user.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String  USER_STATUS_ACTIVE                          =   "ACTIVE";   //用户状态：在用
    public final static String  USER_HISTORY_CREATE                         =   "CREATE";   //历史归档：创建
    public final static String  USER_HISTORY_UPDATE                         =   "UPDATE";   //历史归档：更新
    public final static String  USER_HISTORY_DELETE                         =   "DELETE";   //历史归档：删除

    public final static int     USER_LOGGING_TYPE_PASSWORD                  =   0;          //用户名+密码登录方式
    public final static int     USER_LOGGING_TYPE_CAPTCHA                   =   1;          //验证码登录方式
    public final static int     USER_LOGGING_MAX_ATTEMPT_TIME               =   5;          //尝试登录的最大次数

    public final static String  USER_COUNTER_TYPE_COMMON                    =   "COMMON";   //普通类型的计数器

    public final static String  USER_COUNTER_STATUS_WRONG_PASSWORD          =   "WRONG_PASSWORD";   //密码错误

    public final static String  USER_COUNTER_AUTO_DESCRIPTION               =   "system auto.";   //系统自动生成

    /**
     * restful资源定义
     */
    public final static String  USER_HTTP_REQUEST_MAPPING                   =   "/users";
    public final static String  USER_AUTHENTICATION_HTTP_REQUEST_MAPPING    =   "/users/authentication";   //用户rest资源

    /**
     * 操作定义
     */
    public final static String  USER_OPERATION_SERIAL_NO                    =   "本次操作用户实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String  USER_ERRORCODE_SUCCESS                      =   "CODE_PARTY_USER_000";
    public final static String  USER_ERRORCODE_HAS_EXISTS                   =   "CODE_PARTY_USER_001";
}
