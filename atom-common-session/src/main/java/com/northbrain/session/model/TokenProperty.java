package com.northbrain.session.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Accessors(chain=true)
@ToString
@NoArgsConstructor
@ConfigurationProperties(prefix=Constants.SESSION_TOKEN_PROPERTY_PREFIX)
public class TokenProperty {
    private String  key;            //密钥
    private String  company;        //公司
    private String  audience;       //受众
    private String  issuer;         //发行者
    private Long    lifeTime;       //寿命
}
