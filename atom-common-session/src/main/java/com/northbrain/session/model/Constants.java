package com.northbrain.session.model;

public class Constants {
    public final static String SESSION_STATUS_LOGIN                 =   "LOGIN";            //会话已登录状态
    public final static String SESSION_STATUS_LOGOUT                =   "LOGOUT";           //会话已主动登出状态
    public final static String SESSION_STATUS_CLEANED               =   "CLEANED";          //会话已主动登出状态

    public final static String SESSION_TYPE_COMMON                  =   "COMMON";           //普通类型

    public final static String SESSION_JWT_CLAIMS_SESSION_ID        =   "sessionId";        //JWT的申明内容的私有部分：会话编号
    public final static String SESSION_JWT_CLAIMS_APP_TYPE          =   "appType";          //JWT的申明内容的私有部分：应用类型
    public final static String SESSION_JWT_HEADER_PARAM_TYPE_NAME   =   "typ";              //JWT的申明内容的公共部分：类型
    public final static String SESSION_JWT_HEADER_PARAM_TYPE_VALUE  =   "JWT";              //JWT的申明内容的公共部分：类型取值
    public final static String SESSION_JWT_TOKEN_PREFIX             =   "Bearer ";          //JWT的前缀
    public final static String SESSION_JWT_TOKEN_KEY_CHARSET        =   "UTF-8";            //JWT的token的字符集

    public final static String SESSION_HINT_JWT_VERIFY_PUBLIC_CLAIM_FAILURE         =   "JWT校验公共部分不通过。";
    public final static String SESSION_HINT_JWT_VERIFY_EXPIRATION_FAILURE           =   "JWT已经失效。";

    public final static String SESSION_HTTP_REQUEST_MAPPING                 =   "/sessions";
    public final static String SESSION_HTTP_REQUEST_MAPPING_BY_ID           =   "/sessions/{sessionId}";
    public final static String SESSION_HTTP_REQUEST_MAPPING_JWT             =   "/sessions/token";
}
