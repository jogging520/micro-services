package com.northbrain.util.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String UTIL_TRACER_INVOKE_METHOD_BEGIN          =   ">>>>>开始调用:";                   //调用方法开始
    public final static String UTIL_TRACER_INVOKE_METHOD_END            =   "<<<<<结束调用:";                   //调用方法结束
    public final static String UTIL_TRACER_INVOKE_METHOD_COST           =   "，总计耗时（毫秒）:";               //调用方法耗时

    public final static String UTIL_SECURITY_APP_UP_PUBLIC_KEY          =   "${security.app.up.public.key}";    //APP类型应用的上行公钥
    public final static String UTIL_SECURITY_APP_UP_PRIVATE_KEY         =   "${security.app.up.private.key}";   //APP类型应用的上行私钥
    public final static String UTIL_SECURITY_APP_DOWN_PUBLIC_KEY        =   "${security.app.down.public.key}";  //APP类型应用的下行公钥
    public final static String UTIL_SECURITY_APP_DOWN_PRIVATE_KEY       =   "${security.app.down.private.key}"; //APP类型应用的下行私钥
    public final static String UTIL_SECURITY_WEB_UP_PUBLIC_KEY          =   "${security.web.up.public.key}";    //WEB类型应用的上行公钥
    public final static String UTIL_SECURITY_WEB_UP_PRIVATE_KEY         =   "${security.web.up.private.key}";   //WEB类型应用的上行私钥
    public final static String UTIL_SECURITY_WEB_DOWN_PUBLIC_KEY        =   "${security.web.down.public.key}";  //WEB类型应用的下行公钥
    public final static String UTIL_SECURITY_WEB_DOWN_PRIVATE_KEY       =   "${security.web.down.private.key}"; //WEB类型应用的下行私钥
    public final static String UTIL_SECURITY_CMS_UP_PUBLIC_KEY          =   "${security.cms.up.public.key}";    //CMS类型应用的上行公钥
    public final static String UTIL_SECURITY_CMS_UP_PRIVATE_KEY         =   "${security.cms.up.private.key}";   //CMS类型应用的上行私钥
    public final static String UTIL_SECURITY_CMS_DOWN_PUBLIC_KEY        =   "${security.cms.down.public.key}";  //CMS类型应用的下行公钥
    public final static String UTIL_SECURITY_CMS_DOWN_PRIVATE_KEY       =   "${security.cms.down.private.key}"; //CMS类型应用的下行私钥
    public final static String UTIL_SECURITY_WCT_UP_PUBLIC_KEY          =   "${security.wct.up.public.key}";    //WCT类型应用的上行公钥
    public final static String UTIL_SECURITY_WCT_UP_PRIVATE_KEY         =   "${security.wct.up.private.key}";   //WCT类型应用的上行私钥
    public final static String UTIL_SECURITY_WCT_DOWN_PUBLIC_KEY        =   "${security.wct.down.public.key}";  //WCT类型应用的下行公钥
    public final static String UTIL_SECURITY_WCT_DOWN_PRIVATE_KEY       =   "${security.wct.down.private.key}"; //WCT类型应用的下行私钥
    public final static String UTIL_SECURITY_LED_UP_PUBLIC_KEY          =   "${security.led.up.public.key}";    //LED类型应用的上行公钥
    public final static String UTIL_SECURITY_LED_UP_PRIVATE_KEY         =   "${security.led.up.private.key}";   //LED类型应用的上行私钥
    public final static String UTIL_SECURITY_LED_DOWN_PUBLIC_KEY        =   "${security.led.down.public.key}";  //LED类型应用的下行公钥
    public final static String UTIL_SECURITY_LED_DOWN_PRIVATE_KEY       =   "${security.led.down.private.key}"; //LED类型应用的下行私钥
    public final static String UTIL_SECURITY_MON_UP_PUBLIC_KEY          =   "${security.mon.up.public.key}";    //MON类型应用的上行公钥
    public final static String UTIL_SECURITY_MON_UP_PRIVATE_KEY         =   "${security.mon.up.private.key}";   //MON类型应用的上行私钥
    public final static String UTIL_SECURITY_MON_DOWN_PUBLIC_KEY        =   "${security.mon.down.public.key}";  //MON类型应用的下行公钥
    public final static String UTIL_SECURITY_MON_DOWN_PRIVATE_KEY       =   "${security.mon.down.private.key}"; //MON类型应用的下行私钥

    public final static String UTIL_SECURITY_SYS_PUBLIC_KEY             =   "${security.sys.public.key}";       //系统公钥
    public final static String UTIL_SECURITY_SYS_PRIVATE_KEY            =   "${security.sys.private.key}";      //系统私钥

    public final static String UTIL_SECURITY_APP_TYPE_WEB               =   "web";                              //网站应用类型
    public final static String UTIL_SECURITY_APP_TYPE_APP               =   "app";                              //APP应用类型
    public final static String UTIL_SECURITY_APP_TYPE_CMS               =   "cms";                              //后端管理系统应用类型
    public final static String UTIL_SECURITY_APP_TYPE_LED               =   "led";                              //大屏应用类型
    public final static String UTIL_SECURITY_APP_TYPE_MON               =   "mon";                              //监控系统应用类型
    public final static String UTIL_SECURITY_APP_TYPE_WCT               =   "wct";                              //微信应用类型

    public final static String UTIL_SECURITY_ASYMMETRIC_ALGORITHM       =   "RSA";                              //非对称加解密算法
    public final static String UTIL_SECURITY_SIGNATURE_ALGORITHM        =   "NONEwithRSA";                      //数字签名算法
}
