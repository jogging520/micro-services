package com.northbrain.util.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 类名：安全属性类
 * 用途：用于存储个应用类型的上下行公钥、私钥，公钥负责加密,私钥负责解密
 */
@Configuration
@Data
@Accessors(chain=true)
@ToString
@NoArgsConstructor
@RefreshScope
public class SecurityProperty {
    @Value(Constants.UTIL_SECURITY_APP_UP_PUBLIC_KEY)
    private String  appUpPublicKey;         //APP类型应用的上行公钥
    @Value(Constants.UTIL_SECURITY_APP_UP_PRIVATE_KEY)
    private String  appUpPrivateKey;        //APP类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_APP_DOWN_PUBLIC_KEY)
    private String  appDownPublicKey;       //APP类型应用的下行公钥
    @Value(Constants.UTIL_SECURITY_APP_DOWN_PRIVATE_KEY)
    private String  appDownPrivateKey;      //APP类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_WEB_UP_PUBLIC_KEY)
    private String  webUpPublicKey;         //WEB类型应用的上行公钥
    @Value(Constants.UTIL_SECURITY_WEB_UP_PRIVATE_KEY)
    private String  webUpPrivateKey;        //WEB类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_WEB_DOWN_PUBLIC_KEY)
    private String  webDownPublicKey;       //WEB类型应用的下行公钥
    @Value(Constants.UTIL_SECURITY_WEB_DOWN_PRIVATE_KEY)
    private String  webDownPrivateKey;      //WEB类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_CMS_UP_PUBLIC_KEY)
    private String  cmsUpPublicKey;         //CMS类型应用的上行公钥
    @Value(Constants.UTIL_SECURITY_CMS_UP_PRIVATE_KEY)
    private String  cmsUpPrivateKey;        //CMS类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_CMS_DOWN_PUBLIC_KEY)
    private String  cmsDownPublicKey;       //CMS类型应用的下行公钥
    @Value(Constants.UTIL_SECURITY_CMS_DOWN_PRIVATE_KEY)
    private String  cmsDownPrivateKey;      //CMS类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_WCT_UP_PUBLIC_KEY)
    private String  wctUpPublicKey;         //WCT类型应用的上行公钥
    @Value(Constants.UTIL_SECURITY_WCT_UP_PRIVATE_KEY)
    private String  wctUpPrivateKey;        //WCT类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_WCT_DOWN_PUBLIC_KEY)
    private String  wctDownPublicKey;       //WCT类型应用的下行公钥
    @Value(Constants.UTIL_SECURITY_WCT_DOWN_PRIVATE_KEY)
    private String  wctDownPrivateKey;      //WCT类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_LED_UP_PUBLIC_KEY)
    private String  ledUpPublicKey;         //LED类型应用的上行公钥
    @Value(Constants.UTIL_SECURITY_LED_UP_PRIVATE_KEY)
    private String  ledUpPrivateKey;        //LED类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_LED_DOWN_PUBLIC_KEY)
    private String  ledDownPublicKey;       //LED类型应用的下行公钥
    @Value(Constants.UTIL_SECURITY_LED_DOWN_PRIVATE_KEY)
    private String  ledDownPrivateKey;      //LED类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_MON_UP_PUBLIC_KEY)
    private String  monUpPublicKey;         //MON类型应用的上行公钥
    @Value(Constants.UTIL_SECURITY_MON_UP_PRIVATE_KEY)
    private String  monUpPrivateKey;        //MON类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_MON_DOWN_PUBLIC_KEY)
    private String  monDownPublicKey;       //MON类型应用的下行公钥
    @Value(Constants.UTIL_SECURITY_MON_DOWN_PRIVATE_KEY)
    private String  monDownPrivateKey;      //MON类型应用的上行私钥
    @Value(Constants.UTIL_SECURITY_SYS_PUBLIC_KEY)
    private String  sysPublicKey;           //系统公钥
    @Value(Constants.UTIL_SECURITY_SYS_PRIVATE_KEY)
    private String  sysPrivateKey;          //系统私钥
}
