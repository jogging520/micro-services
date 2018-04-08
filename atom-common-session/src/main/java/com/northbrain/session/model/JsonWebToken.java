package com.northbrain.session.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix="session.jwt.token")
public class JsonWebToken {
    private String  key;            //密钥
    private String  company;        //公司
    private String  audience;       //受众
    private String  issuer;         //发行者
    private Long    lifeTime;       //寿命
}
