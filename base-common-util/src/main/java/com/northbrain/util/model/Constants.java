package com.northbrain.util.model;

public class Constants {
    /**
     * 枚举值定义，如状态、类型
     */
    public final static String UTIL_TRACER_INVOKE_METHOD_BEGIN          =   ">>>>>开始调用:";                           //调用方法开始
    public final static String UTIL_TRACER_INVOKE_METHOD_END            =   "<<<<<结束调用:";                           //调用方法结束
    public final static String UTIL_TRACER_INVOKE_METHOD_COST           =   "，总计耗时（毫秒）:";                      //调用方法耗时

    public final static String UTIL_SECURITY_APP_UP_PUBLIC_KEY          =   "${security.app.up.public.key}";            //APP类型应用的上行公钥
    public final static String UTIL_SECURITY_APP_UP_PRIVATE_KEY         =   "${security.app.up.private.key}";           //APP类型应用的上行私钥
    public final static String UTIL_SECURITY_APP_DOWN_PUBLIC_KEY        =   "${security.app.down.public.key}";          //APP类型应用的下行公钥
    public final static String UTIL_SECURITY_APP_DOWN_PRIVATE_KEY       =   "${security.app.down.private.key}";         //APP类型应用的下行私钥
    public final static String UTIL_SECURITY_APP_TEMPORARY_PUBLIC_KEY   =   "${security.app.temporary.public.key}";     //APP类型应用的临时公钥
    public final static String UTIL_SECURITY_APP_TEMPORARY_PRIVATE_KEY  =   "${security.app.temporary.private.key}";    //APP类型应用的临时私钥
    public final static String UTIL_SECURITY_WEB_UP_PUBLIC_KEY          =   "${security.web.up.public.key}";            //WEB类型应用的上行公钥
    public final static String UTIL_SECURITY_WEB_UP_PRIVATE_KEY         =   "${security.web.up.private.key}";           //WEB类型应用的上行私钥
    public final static String UTIL_SECURITY_WEB_DOWN_PUBLIC_KEY        =   "${security.web.down.public.key}";          //WEB类型应用的下行公钥
    public final static String UTIL_SECURITY_WEB_DOWN_PRIVATE_KEY       =   "${security.web.down.private.key}";         //WEB类型应用的下行私钥
    public final static String UTIL_SECURITY_WEB_TEMPORARY_PUBLIC_KEY   =   "${security.web.temporary.public.key}";     //APP类型应用的临时公钥
    public final static String UTIL_SECURITY_WEB_TEMPORARY_PRIVATE_KEY  =   "${security.web.temporary.private.key}";    //APP类型应用的临时私钥
    public final static String UTIL_SECURITY_CMS_UP_PUBLIC_KEY          =   "${security.cms.up.public.key}";            //CMS类型应用的上行公钥
    public final static String UTIL_SECURITY_CMS_UP_PRIVATE_KEY         =   "${security.cms.up.private.key}";           //CMS类型应用的上行私钥
    public final static String UTIL_SECURITY_CMS_DOWN_PUBLIC_KEY        =   "${security.cms.down.public.key}";          //CMS类型应用的下行公钥
    public final static String UTIL_SECURITY_CMS_DOWN_PRIVATE_KEY       =   "${security.cms.down.private.key}";         //CMS类型应用的下行私钥
    public final static String UTIL_SECURITY_CMS_TEMPORARY_PUBLIC_KEY   =   "${security.cms.temporary.public.key}";     //APP类型应用的临时公钥
    public final static String UTIL_SECURITY_CMS_TEMPORARY_PRIVATE_KEY  =   "${security.cms.temporary.private.key}";    //APP类型应用的临时私钥
    public final static String UTIL_SECURITY_WCT_UP_PUBLIC_KEY          =   "${security.wct.up.public.key}";            //WCT类型应用的上行公钥
    public final static String UTIL_SECURITY_WCT_UP_PRIVATE_KEY         =   "${security.wct.up.private.key}";           //WCT类型应用的上行私钥
    public final static String UTIL_SECURITY_WCT_DOWN_PUBLIC_KEY        =   "${security.wct.down.public.key}";          //WCT类型应用的下行公钥
    public final static String UTIL_SECURITY_WCT_DOWN_PRIVATE_KEY       =   "${security.wct.down.private.key}";         //WCT类型应用的下行私钥
    public final static String UTIL_SECURITY_WCT_TEMPORARY_PUBLIC_KEY   =   "${security.wct.temporary.public.key}";     //APP类型应用的临时公钥
    public final static String UTIL_SECURITY_WCT_TEMPORARY_PRIVATE_KEY  =   "${security.wct.temporary.private.key}";    //APP类型应用的临时私钥
    public final static String UTIL_SECURITY_WCA_UP_PUBLIC_KEY          =   "${security.wca.up.public.key}";            //WCT类型应用的上行公钥
    public final static String UTIL_SECURITY_WCA_UP_PRIVATE_KEY         =   "${security.wca.up.private.key}";           //WCT类型应用的上行私钥
    public final static String UTIL_SECURITY_WCA_DOWN_PUBLIC_KEY        =   "${security.wca.down.public.key}";          //WCT类型应用的下行公钥
    public final static String UTIL_SECURITY_WCA_DOWN_PRIVATE_KEY       =   "${security.wca.down.private.key}";         //WCT类型应用的下行私钥
    public final static String UTIL_SECURITY_WCA_TEMPORARY_PUBLIC_KEY   =   "${security.wca.temporary.public.key}";     //APP类型应用的临时公钥
    public final static String UTIL_SECURITY_WCA_TEMPORARY_PRIVATE_KEY  =   "${security.wca.temporary.private.key}";    //APP类型应用的临时私钥
    public final static String UTIL_SECURITY_LED_UP_PUBLIC_KEY          =   "${security.led.up.public.key}";            //LED类型应用的上行公钥
    public final static String UTIL_SECURITY_LED_UP_PRIVATE_KEY         =   "${security.led.up.private.key}";           //LED类型应用的上行私钥
    public final static String UTIL_SECURITY_LED_DOWN_PUBLIC_KEY        =   "${security.led.down.public.key}";          //LED类型应用的下行公钥
    public final static String UTIL_SECURITY_LED_DOWN_PRIVATE_KEY       =   "${security.led.down.private.key}";         //LED类型应用的下行私钥
    public final static String UTIL_SECURITY_LED_TEMPORARY_PUBLIC_KEY   =   "${security.led.temporary.public.key}";     //APP类型应用的临时公钥
    public final static String UTIL_SECURITY_LED_TEMPORARY_PRIVATE_KEY  =   "${security.led.temporary.private.key}";    //APP类型应用的临时私钥
    public final static String UTIL_SECURITY_MON_UP_PUBLIC_KEY          =   "${security.mon.up.public.key}";            //MON类型应用的上行公钥
    public final static String UTIL_SECURITY_MON_UP_PRIVATE_KEY         =   "${security.mon.up.private.key}";           //MON类型应用的上行私钥
    public final static String UTIL_SECURITY_MON_DOWN_PUBLIC_KEY        =   "${security.mon.down.public.key}";          //MON类型应用的下行公钥
    public final static String UTIL_SECURITY_MON_DOWN_PRIVATE_KEY       =   "${security.mon.down.private.key}";         //MON类型应用的下行私钥
    public final static String UTIL_SECURITY_MON_TEMPORARY_PUBLIC_KEY   =   "${security.mon.temporary.public.key}";     //APP类型应用的临时公钥
    public final static String UTIL_SECURITY_MON_TEMPORARY_PRIVATE_KEY  =   "${security.mon.temporary.private.key}";    //APP类型应用的临时私钥

    public final static String UTIL_SECURITY_SYS_PUBLIC_KEY             =   "${security.sys.public.key}";               //系统公钥
    public final static String UTIL_SECURITY_SYS_PRIVATE_KEY            =   "${security.sys.private.key}";              //系统私钥

    public final static String UTIL_SECURITY_APP_TYPE_WEB               =   "web";                                      //网站应用类型
    public final static String UTIL_SECURITY_APP_TYPE_APP               =   "app";                                      //APP应用类型
    public final static String UTIL_SECURITY_APP_TYPE_CMS               =   "cms";                                      //后端管理系统应用类型
    public final static String UTIL_SECURITY_APP_TYPE_LED               =   "led";                                      //大屏应用类型
    public final static String UTIL_SECURITY_APP_TYPE_MON               =   "mon";                                      //监控系统应用类型
    public final static String UTIL_SECURITY_APP_TYPE_WCT               =   "wct";                                      //微信应用类型（用户）
    public final static String UTIL_SECURITY_APP_TYPE_WCA               =   "wca";                                      //微信应用类型（管理员）

    public final static int    UTIL_SECURITY_ASYMMETRIC_KEY_LENGTH      =   1024;                                       //非对称加解密的密钥对长度
    public final static String UTIL_SECURITY_ASYMMETRIC_ALGORITHM       =   "RSA";                                      //非对称加解密算法
    public final static String UTIL_SECURITY_SIGNATURE_ALGORITHM        =   "NONEwithRSA";                              //数字签名算法
    public final static String UTIL_SECURITY_ASYMMETRIC_PUBLIC_KEY      =   "公钥：";
    public final static String UTIL_SECURITY_ASYMMETRIC_PRIVATE_KEY     =   "私钥：";
    public final static String UTIL_SECURITY_PBKDF2_ALGORITHM           =   "PBKDF2WithHmacSHA1";                       //密码加解密算法
    public final static int    UTIL_SECURITY_PBKDF2_SALT_BYTE_SIZE      =   32/2;                                       //盐的长度
    public final static int    UTIL_SECURITY_PBKDF2_HASH_BIT_SIZE       =   128*4;                                      //生成密文的长度
    public final static int    UTIL_SECURITY_PBKDF2_ITERATIONS          =   1000;                                       //迭代次数
}
