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
 * 类名：TOKEN相关属性类
 * 用途：用于生成和解析JWT
 */
@Configuration
@Data
@Accessors(chain=true)
@ToString
@NoArgsConstructor
@RefreshScope
public class TokenProperty {
    @Value(Constants.UTIL_JSON_WEB_TOKEN_KEY)
    private String  key;            //密钥
    @Value(Constants.UTIL_JSON_WEB_TOKEN_COMPANY)
    private String  company;        //公司
    @Value(Constants.UTIL_JSON_WEB_TOKEN_AUDIENCE)
    private String  audience;       //受众
    @Value(Constants.UTIL_JSON_WEB_TOKEN_ISSUER)
    private String  issuer;         //发行者
    @Value(Constants.UTIL_JSON_WEB_TOKEN_LIFETIME)
    private Long    lifeTime;       //寿命
}
