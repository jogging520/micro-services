package com.northbrain.session.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Accessors(chain=true)
@ToString
@NoArgsConstructor
@RefreshScope
public class TokenProperty {
    @Value(Constants.SESSION_JWT_KEY)
    private String  key;            //密钥
    @Value(Constants.SESSION_JWT_COMPANY)
    private String  company;        //公司
    @Value(Constants.SESSION_JWT_AUDIENCE)
    private String  audience;       //受众
    @Value(Constants.SESSION_JWT_ISSUER)
    private String  issuer;         //发行者
    @Value(Constants.SESSION_JWT_LIFETIME)
    private Long    lifeTime;       //寿命
}