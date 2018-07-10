package com.northbrain.session.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String SESSION_STATUS_LOGIN                                 =   "LOGIN";            //会话已登录状态
    public final static String SESSION_STATUS_LOGOUT                                =   "LOGOUT";           //会话已主动登出状态
    public final static String SESSION_STATUS_CLEANED                               =   "CLEANED";          //会话已主动登出状态

    public final static String SESSION_HISTORY_CREATE                               =   "CREATE";   //历史归档：创建
    public final static String SESSION_HISTORY_UPDATE                               =   "UPDATE";   //历史归档：更新
    public final static String SESSION_HISTORY_DELETE                               =   "DELETE";   //历史归档：删除

    public final static String SESSION_TYPE_COMMON                                  =   "COMMON";           //普通类型

    public final static String SESSION_JWT_KEY                                      =   "${jwt.key}";                       //密钥
    public final static String SESSION_JWT_COMPANY                                  =   "${jwt.company}";                   //公司
    public final static String SESSION_JWT_AUDIENCE                                 =   "${jwt.audience}";                  //受众
    public final static String SESSION_JWT_ISSUER                                   =   "${jwt.issuer}";                    //发行者
    public final static String SESSION_JWT_LIFETIME                                 =   "${jwt.lifeTime}";                  //寿命

    public final static String SESSION_JWT_CLAIMS_SESSION                           =   "session";          //JWT的申明内容的私有部分：会话编号
    public final static String SESSION_JWT_CLAIMS_APP_TYPE                          =   "appType";          //JWT的申明内容的私有部分：应用类型
    public final static String SESSION_JWT_HEADER_PARAM_TYPE_NAME                   =   "typ";              //JWT的申明内容的公共部分：类型
    public final static String SESSION_JWT_HEADER_PARAM_TYPE_VALUE                  =   "JWT";              //JWT的申明内容的公共部分：类型取值
    public final static String SESSION_JWT_TOKEN_PREFIX                             =   "Bearer ";          //JWT的前缀
    public final static String SESSION_JWT_TOKEN_KEY_CHARSET                        =   "UTF-8";            //JWT的token的字符集

    public final static String SESSION_HINT_JWT_VERIFY_PUBLIC_CLAIM_FAILURE         =   "JWT校验公共部分不通过。";
    public final static String SESSION_HINT_JWT_VERIFY_EXPIRATION_FAILURE           =   "JWT已经失效。";

    public final static String SESSION_ATTEMPT_AUTO_DESCRIPTION                     =   "system auto.";   //系统自动生成

    /**
     * restful资源定义
     */
    public final static String SESSION_HTTP_REQUEST_MAPPING                         =   "/sessions";
    public final static String SESSION_LOGIN_HTTP_REQUEST_MAPPING                   =   "/sessions/login";
    public final static String SESSION_LOGOUT_HTTP_REQUEST_MAPPING                  =   "/sessions/logout";
    public final static String SESSION_SPECIFIED_HTTP_REQUEST_MAPPING               =   "/sessions/{sessionId}";
    public final static String SESSION_JWT_HTTP_REQUEST_MAPPING                     =   "/sessions/token";
    public final static String SESSION_ATTEMPT_HTTP_REQUEST_MAPPING                 =   "/sessions/attempt";


    /**
     * 操作定义
     */
    public final static String SESSION_OPERATION_SERIAL_NO                          =   "本次操作会话实体的流水号为：";

    /**
     * 错误码定义
     */
    public final static String SESSION_ERRORCODE_SUCCESS                            =   "SUCCESS";
}
